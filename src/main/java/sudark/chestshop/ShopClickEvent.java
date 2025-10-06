package sudark.chestshop;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.Score;

import java.util.Map;

import static sudark.chestshop.InitializeInventory.*;

public class ShopClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() instanceof PlayerInventory) return;

        Player pl = (Player) e.getWhoClicked();
        ItemStack good = e.getCurrentItem();
        if (good == null) return;

        String name = e.getView().getTitle();

        // 统一判断是否点击了玻璃板
        if (good.getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE) {
            e.setCancelled(true);
            return;
        }

// 只处理特定商店界面
        switch (name) {
            case "口袋商店 | §lMobileShop" -> {
                e.setCancelled(true);
                handleMobileShop(pl, good);
            }
            case "口袋商铺 | §lMobileShop" -> {
                e.setCancelled(true);
                handleMobileSuperMarket(pl, good);
            }
            case shop1Name -> {
                e.setCancelled(true);
                handleShop(pl, good, 1, 1, Sound.BLOCK_BONE_BLOCK_BREAK);
            }
            case shop1NamePro, shop2NamePro, shop4NamePro, shop5NamePro, shop6NamePro, shop7NamePro -> {
                e.setCancelled(true);
                handleShop(pl, good, 128, -1, Sound.BLOCK_BONE_BLOCK_BREAK);
            }
            case shop2Name -> {
                e.setCancelled(true);
                handleShop(pl, good, 1, 2, Sound.ENTITY_WANDERING_TRADER_YES);
            }
            case shop3Name -> {
                e.setCancelled(true);
                handleShop(pl, good, 50, 3, Sound.BLOCK_ANVIL_BREAK);
            }
            case shop4Name -> {
                e.setCancelled(true);
                handleShop(pl, good, 1, 4, Sound.BLOCK_NETHER_GOLD_ORE_PLACE);
            }
            case shop5Name -> {
                e.setCancelled(true);
                handleShop(pl, good, 1, 5, Sound.BLOCK_SAND_HIT);
            }
            case shop6Name -> {
                e.setCancelled(true);
                handleShop(pl, good, 1, 6, Sound.UI_LOOM_SELECT_PATTERN);
            }
            case shop7Name -> {
                e.setCancelled(true);
                handleShop(pl, good, 1, 7, Sound.UI_LOOM_SELECT_PATTERN);
            }
        }
    }

    private static final Map<Material, Integer> mobileShopPage1 = Map.of(
            Material.STONECUTTER, 1,
            Material.BARREL, 2,
            Material.BLAST_FURNACE, 3,
            Material.STRIPPED_CHERRY_LOG, 4,
            Material.SANDSTONE_WALL, 5,
            Material.YELLOW_CONCRETE_POWDER, 6,
            Material.LOOM, 7,
            Material.PIGLIN_BRUTE_SPAWN_EGG, 8
    );

    private static final Map<Material, Integer> mobileShopPage2 = Map.of(
            Material.STONECUTTER, 21,
            Material.BARREL, 22,
            Material.STRIPPED_CHERRY_LOG, 24,
            Material.SANDSTONE_WALL, 25,
            Material.YELLOW_CONCRETE_POWDER, 26,
            Material.LOOM, 27
    );

    private void handleMobileSuperMarket(Player pl, ItemStack good) {
        InitializeInventory in = new InitializeInventory();
        in.open(pl, mobileShopPage2.get(good.getType()));
    }

    private void handleMobileShop(Player pl, ItemStack good) {
        InitializeInventory in = new InitializeInventory();
        in.open(pl, mobileShopPage1.get(good.getType()));
    }

    // 通用商店处理
// minLevel: 购买最低等级
// wellId: 需要调用beWell的商店编号，-1表示不调用
// sound: 播放音效
    private void handleShop(Player pl, ItemStack good, int minLevel, int wellId, Sound sound) {
        if (pl.getLevel() < minLevel) {
            cancel(pl);
            return;
        }

        if (wellId != -1) {
            beWell(pl, wellId);
        }

        order(pl, minLevel, good, sound);
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
