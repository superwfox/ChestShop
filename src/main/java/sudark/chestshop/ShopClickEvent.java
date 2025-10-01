package sudark.chestshop;

import io.papermc.paper.event.player.PlayerPickItemEvent;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.Score;

import java.util.Arrays;
import java.util.List;

import static sudark.chestshop.InitializeInventory.*;

public class ShopClickEvent implements Listener {

    @EventHandler
    public void onItemPick(PlayerPickItemEvent e) {
        Player pl = e.getPlayer();
        Block bl = pl.getTargetBlockExact(10, FluidCollisionMode.NEVER);

        if (bl == null) return;

        List<ItemStack[]> shops = Arrays.asList(
                shopSetting1,
                shopSetting4,
                shopSetting5,
                shopSetting6
        );

        for (ItemStack[] shop : shops) {
            for (int i = 0; i < shop.length; i++) {
                if (bl.getType().equals(shop[i].getType())) {
                    ItemStack item = shop[i];

                    if (pl.getLevel() < 1) {
                        pl.sendMessage("[§e口袋商店§f] 等级不足!");
                        return;
                    }

                    if (pl.getInventory().contains(item.getType(), 32)) return;
                    pl.playSound(pl, Sound.ENTITY_VILLAGER_TRADE, 1, 1);
                    pl.sendActionBar("[§e口袋商店§f] §b" + item.getType() + " §f:§e " + item.getAmount());
                    pl.giveExpLevels(-2);
                    pl.getInventory().addItem(item);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onShopClick(InventoryClickEvent e) {

        if (e.getClickedInventory() instanceof PlayerInventory) return;

        if (e.getCurrentItem() == null) return;
        String name = e.getView().getTitle();
        Player pl = (Player) e.getWhoClicked();
        ItemStack good = e.getCurrentItem();

        if (name.equals("口袋商店 | §lMobileShop")) {
            e.setCancelled(true);
            InitializeInventory in = new InitializeInventory();
            switch (good.getType()) {
                case STONECUTTER -> in.open(pl, 1);
                case BARREL -> in.open(pl, 2);
                case BLAST_FURNACE -> in.open(pl, 3);
                case STRIPPED_CHERRY_LOG -> in.open(pl, 4);
                case YELLOW_WOOL -> in.open(pl, 5);
                case YELLOW_CONCRETE_POWDER -> in.open(pl, 6);
                case LOOM -> in.open(pl, 7);
                case PIGLIN_BRUTE_SPAWN_EGG -> in.open(pl, 8);
            }
            return;
        }

        if (name.equals("建材铺 | §lBLOCKS")) {
            if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }
            beWell(pl, 1);
            order(pl, 1, good, Sound.BLOCK_BONE_BLOCK_BREAK);
            return;
        }

        if (name.equals("农贸商 | §lARBORS")) {
            if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }
            beWell(pl, 2);
            order(pl, 1, good, Sound.ENTITY_WANDERING_TRADER_YES);
            return;
        }

        if (name.equals("铁匠铺 | §lHANDTOOLS")) {
            if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);

            if (pl.getLevel() < 50) {
                cancel(pl);
                return;
            }
            beWell(pl, 3);
            order(pl, 50, good, Sound.BLOCK_ANVIL_BREAK);
            return;
        }

        if (name.equals("木料铺 | §lWOODS")) {
            if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }
            beWell(pl, 4);
            order(pl, 1, good, Sound.BLOCK_NETHER_GOLD_ORE_PLACE);
            return;
        }

        if (name.equals("羊毛铺 | §lWOOL")) {
            if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }
            beWell(pl, 5);
            order(pl, 1, good, Sound.BLOCK_WOOL_PLACE);
            return;
        }

        if (name.equals("混凝土坊 | §lCONCRETE")) {
            if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }
            beWell(pl, 6);
            order(pl, 1, good, Sound.BLOCK_SAND_HIT);
            return;
        }

        if (name.equals("染坊 | §lDYE")) {
            if (e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }
            beWell(pl, 7);
            order(pl, 1, good, Sound.UI_LOOM_SELECT_PATTERN);
        }
    }

    public void beWell(Player pl, int num) {
        if (pl.getInventory().firstEmpty() == -1) {
            return;
        }
        Score score = pl.getScoreboard().getObjective("SHOP_" + num).getScore(pl);
        int affection = score.getScore();
        score.setScore(affection + 1);
    }

    public void cancel(Player pl) {
        pl.playSound(pl, Sound.ENTITY_VILLAGER_NO, 1, 1);
        pl.damage(-1, DamageSource.builder(DamageType.OUT_OF_WORLD).build());
    }

    public void order(Player pl, int price, ItemStack good, Sound sound) {
        if (pl.getInventory().firstEmpty() == -1) {
            pl.playSound(pl, Sound.ENTITY_VILLAGER_NO, 1, 1);
            return;
        }
        if ((pl.getScoreboardTags().contains("craftsman")) && price < 50) {
            pl.giveExp(-price);
        } else {
            pl.giveExpLevels(-price);
        }

        pl.getInventory().addItem(good);
        pl.playSound(pl, sound, 1, 1);
    }


}
