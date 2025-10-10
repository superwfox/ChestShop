package sudark.chestshop;

import io.papermc.paper.event.player.PlayerPickItemEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static sudark.chestshop.ChestShop.amountKey;
import static sudark.chestshop.ChestShop.get;
import static sudark.chestshop.InitializeInventory.*;
import static sudark.chestshop.ItemRarity.RARITY_MAP;

public class ToggleOpenAndBlockPlace implements Listener {

    static Map<Location, List<Location>> GoldMiner = new HashMap<>();
    static Map<Location, Integer> GoldDig = new HashMap<>();
    static Transformation turn = new Transformation(
            new Vector3f(0f, -0.5f, 0f),            // 平移
            new Quaternionf().rotateX((float) Math.toRadians(90)), // 绕X轴旋转90°
            new Vector3f(0.85f, 1f, 0.75f),                    // 缩放
            new Quaternionf()
    );

    int dxyz[][] = {{0, 1, 0}, {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}};

    @EventHandler
    public void onPlayerTouchWealth(PlayerToggleSneakEvent e) {

        Player pl = e.getPlayer();
        if (pl.isSneaking()) return;

        Block bl = pl.getTargetBlockExact(6);
        if (bl == null || bl.getType() != Material.NETHERITE_BLOCK) return;

        Location loc = bl.getLocation();

        //存了有效值就取出 否则继续
        if (GoldDig.containsKey(loc) && GoldDig.get(loc) > 0) {
            int levelPoints = GoldDig.get(loc);
            pl.sendActionBar("[§e掘金§f] §f" + levelPoints + " §b福禄");
            pl.giveExp(levelPoints);
            pl.playSound(pl, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            GoldDig.remove(loc);
            return;
        } else {
            loc.getNearbyEntities(1, 2, 1).forEach(entity -> {
                if (entity instanceof TextDisplay td) td.remove();
                if (entity instanceof ItemDisplay id) id.remove();
            });
        }

        //检查漏斗情况
        List<Location> detect = new ArrayList<>();
        for (int[] dxy : dxyz) {
            Location nowLoc = loc.clone().add(dxy[0], dxy[1], dxy[2]);
            if (nowLoc.getBlock().getType() == Material.HOPPER) {
                detect.add(nowLoc);
            }
        }

        if (detect.isEmpty()) {
            pl.sendActionBar("[§e掘金§f] 没有任何连接它的漏斗");
            return;
        }

        pl.sendActionBar("[§e掘金§f] 检测到 §b" + detect.size() + " §f个漏斗");
        GoldMiner.put(loc, detect);

        if (!GoldDig.containsKey(loc) || GoldDig.get(loc) == 0) createTask(loc);
    }

    public static void createTask(Location oriLoc) {

        loadDisplay(oriLoc);

        new BukkitRunnable() {
            @Override
            public void run() {
                List<Location> hoppers = GoldMiner.get(oriLoc);
                if (hoppers == null || hoppers.isEmpty()) {
                    GoldMiner.remove(oriLoc);
                    cancel();
                    return;
                }

                if (oriLoc.getBlock().getType() != Material.NETHERITE_BLOCK) {
                    GoldMiner.remove(oriLoc);
                    GoldDig.remove(oriLoc);
                    oriLoc.getNearbyEntities(1, 2, 1).forEach(entity -> {
                        if (entity instanceof TextDisplay td) td.remove();
                        if (entity instanceof ItemDisplay id) id.remove();
                    });
                    cancel();
                    return;
                }

                List<Location> toRemove = new ArrayList<>();
                for (Location loc : hoppers) {
                    if (loc.getBlock().getType() == Material.HOPPER) {
                        GoldDig.merge(oriLoc, getValue(loc), Integer::sum);
                    } else {
                        toRemove.add(loc);
                    }
                }
                hoppers.removeAll(toRemove);

                AtomicBoolean exist = new AtomicBoolean(false);
                oriLoc.getNearbyEntities(1, 2, 1).forEach(entity -> {
                    if (entity instanceof TextDisplay td) {
                        exist.set(true);
                        td.setText("§e§lM : " + GoldDig.getOrDefault(oriLoc, 0));
                    }
                });

                if (!exist.get()) {
                    loadDisplay(oriLoc);
                }

                if (hoppers.isEmpty()) {
                    GoldMiner.remove(oriLoc);
                    cancel();
                }
            }
        }.runTaskTimer(get(), 0, 4L);
    }

    public static void loadDisplay(Location loc) {
        loc.getNearbyEntities(1, 2, 1).forEach(entity -> {
            if (entity instanceof TextDisplay td) td.remove();
            if (entity instanceof ItemDisplay id) id.remove();
        });

        TextDisplay td = loc.getWorld().spawn(loc.clone().add(0.5, 1.5, 0.5), TextDisplay.class);
        td.setSeeThrough(false);
        td.setBillboard(TextDisplay.Billboard.VERTICAL);
        td.setText("§e§lM : " + GoldDig.getOrDefault(loc, 0));

        ItemDisplay id = loc.getWorld().spawn(loc.clone().add(0.5, 1.5, 0.5), ItemDisplay.class);
        id.setItemStack(new ItemStack(Material.GOLD_INGOT));
        id.setTransformation(turn);

    }

    public static int getValue(Location loc) {
        Block block = loc.getBlock();
        int value = 0;
        if (block.getState() instanceof Container container) {
            Inventory inv = container.getInventory();

            for (ItemStack item : inv.getContents()) {
                if (item == null || item.getType().isAir()) continue;

                ItemRarity.Rarity rarity = RARITY_MAP.getOrDefault(item.getType(), ItemRarity.Rarity.COMMON);
                int c = switch (rarity) {
                    case COMMON -> 1;
                    case UNCOMMON -> 3;
                    case RARE -> 9;
                    case EPIC -> 27;
                    case EXTRAORDINARY -> 81;
                };

                value += c * item.getAmount();
            }

            inv.clear();
        }
        return value;
    }

    //选取购买专用
    List<ItemStack[]> shops = Arrays.asList(
            shopSetting1,
            shopSetting2,
            shopSetting4,
            shopSetting5,
            shopSetting6,
            shopSetting7
    );

    @EventHandler
    public void onPick(PlayerPickItemEvent e) {
        Player pl = e.getPlayer();
        Block bl = pl.getTargetBlockExact(7, FluidCollisionMode.NEVER);
        Material src = bl.getType();
        ItemStack own;

        PlayerInventory inv = pl.getInventory();

        int slot = findMatchingSlot(pl, src, inv);
        if (slot != -1) {
            own = pl.getInventory().getItem(slot);
            ItemStack hand = pl.getInventory().getItemInMainHand();

            // 取消原事件，防止 Paper 自动切换
            e.setCancelled(true);
            inv.setItem(slot, hand);
            pl.getInventory().setItemInMainHand(own);

            return;
        }

        for (ItemStack[] shop : shops) {
            for (ItemStack itemStack : shop) {
                if (bl.getType().equals(itemStack.getType())) {

                    if (pl.getLevel() < 1) {
                        pl.sendMessage("[§e口袋商店§f] 等级不足!");
                        return;
                    }

                    if (pl.getInventory().contains(itemStack.getType(), 32)) return;
                    pl.playSound(pl, Sound.ENTITY_VILLAGER_TRADE, 1, 1);
                    pl.sendActionBar("[§e口袋商店§f] §b" + itemStack.getType() + " §f:§e " + itemStack.getAmount());
                    pl.giveExpLevels(-2);
                    pl.getInventory().addItem(itemStack);
                    return;
                }
            }
        }
    }

    private int findMatchingSlot(Player pl, Material type, Inventory inv) {
        for (int i = 0; i < 36; i++) {
            ItemStack item = inv.getItem(i);
            if (item != null && item.getType() == type)
                return i;
        }
        return -1;
    }

    @EventHandler
    public void onShopOpen(PlayerToggleSneakEvent e) {
        Player pl = e.getPlayer();
        Material material = pl.getItemInHand().getType();

        if (material.equals(Material.GOLD_INGOT)) {
            if (pl.isSneaking()) {
                pl.openInventory(getInventory(pl));
                pl.sendMessage("[§e口袋商店§f] 多买东西和老板搞好关系解锁对应店铺");
            }
        }

        if (material.equals(Material.NETHERITE_INGOT)) {
            if (pl.isSneaking()) {
                pl.openInventory(getAdvancedInventory(pl));
            }
        }

    }

    @EventHandler
    public void onPlayerHandBlock(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        if (!pdc.has(amountKey, PersistentDataType.INTEGER)) return;

        int amount = pdc.getOrDefault(amountKey, PersistentDataType.INTEGER, 0);
        if (amount > 0) {
            if (item.getAmount() > 1) amount = amount * item.getAmount();
            item.setAmount(1);
            pdc.set(amountKey, PersistentDataType.INTEGER, amount - 1);
            meta.setLore(List.of("§7+" + (amount - 1)));
            event.getPlayer().sendActionBar("§7§l" + (amount - 1));
            item.setItemMeta(meta);
        } else {
            pdc.remove(amountKey);
            item.setItemMeta(meta);
        }

    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        if (!pdc.has(amountKey, PersistentDataType.INTEGER)) return;

        int amount = pdc.getOrDefault(amountKey, PersistentDataType.INTEGER, 0);
        if (amount > 0) {
            item.setAmount(1);
            pdc.set(amountKey, PersistentDataType.INTEGER, amount - 1);
            meta.setLore(List.of("§7+" + (amount - 1)));
            p.sendActionBar("§7§l" + (amount - 1));
            item.setItemMeta(meta);
            p.setItemInHand(item);
        } else {
            pdc.remove(amountKey);
            item.setItemMeta(meta);
        }
    }

    Inventory getAdvancedInventory(Player pl) {
        Inventory inv = Bukkit.createInventory(pl, 9, "口袋商铺 | §lMobileShop");

        ItemStack STONECUTTER = shop(Material.STONECUTTER, "§r§e建材铺");
        ItemStack BARREL = shop(Material.BARREL, "§r§e农贸商");
        ItemStack STRIPPED_CHERRY_LOG = shop(Material.STRIPPED_CHERRY_LOG, "§r§e木料铺");
        ItemStack YELLOW_WOOL = shop(Material.SANDSTONE_WALL, "§r§e加工铺");
        ItemStack YELLOW_CONCRETE_POWDER = shop(Material.YELLOW_CONCRETE_POWDER, "§r§e土坊");
        ItemStack LOOM = shop(Material.LOOM, "§r§e染坊");

        if (capableToOpen(pl, 1)) inv.addItem(STONECUTTER);
        if (capableToOpen(pl, 2)) inv.addItem(BARREL);
        if (capableToOpen(pl, 4)) inv.addItem(STRIPPED_CHERRY_LOG);
        if (capableToOpen(pl, 5)) inv.addItem(YELLOW_WOOL);
        if (capableToOpen(pl, 6)) inv.addItem(YELLOW_CONCRETE_POWDER);
        if (capableToOpen(pl, 7)) inv.addItem(LOOM);
        return inv;
    }

    Inventory getInventory(Player pl) {
        Inventory inv = Bukkit.createInventory(pl, 9, "口袋商店 | §lMobileShop");

        ItemStack STONECUTTER = shop(Material.STONECUTTER, "§r§e建材铺");
        ItemStack BARREL = shop(Material.BARREL, "§r§e农贸商");
        ItemStack BLAST_FURNACE = shop(Material.BLAST_FURNACE, "§r§e铁匠铺");
        ItemStack STRIPPED_CHERRY_LOG = shop(Material.STRIPPED_CHERRY_LOG, "§r§e木料铺");
        ItemStack YELLOW_WOOL = shop(Material.SANDSTONE_WALL, "§r§e加工铺");
        ItemStack YELLOW_CONCRETE_POWDER = shop(Material.YELLOW_CONCRETE_POWDER, "§r§e土坊");
        ItemStack LOOM = shop(Material.LOOM, "§r§e染坊");
        ItemStack PIGLIN_BRUTE_SPAWN_EGG = shop(Material.PIGLIN_BRUTE_SPAWN_EGG, "§r§e猪人小铺");

        if (capableToOpen(pl, 1)) inv.addItem(STONECUTTER);
        if (capableToOpen(pl, 2)) inv.addItem(BARREL);
        if (capableToOpen(pl, 3)) inv.addItem(BLAST_FURNACE);
        if (capableToOpen(pl, 4)) inv.addItem(STRIPPED_CHERRY_LOG);
        if (capableToOpen(pl, 5)) inv.addItem(YELLOW_WOOL);
        if (capableToOpen(pl, 6)) inv.addItem(YELLOW_CONCRETE_POWDER);
        if (capableToOpen(pl, 7)) inv.addItem(LOOM);
        inv.addItem(PIGLIN_BRUTE_SPAWN_EGG);
        return inv;
    }

    ItemStack shop(Material m, String name) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    Boolean capableToOpen(Player pl, int num) {
        return pl.getScoreboard().getObjective("SHOP_" + num).getScore(pl).getScore() > 50;
    }
}
