package sudark.chestshop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class IntializeInventory {

    static ItemStack[] shopSetting1 = {
            new ItemStack(Material.GRASS_BLOCK, 32),
            new ItemStack(Material.STONE, 32),
            new ItemStack(Material.COBBLESTONE, 32),
            new ItemStack(Material.DIRT, 32),
            new ItemStack(Material.SAND, 32),
            new ItemStack(Material.CLAY, 32),
            new ItemStack(Material.DEEPSLATE, 32),
            new ItemStack(Material.QUARTZ_BLOCK, 16),
            new ItemStack(Material.PRISMARINE, 16),

            new ItemStack(Material.DARK_PRISMARINE, 16),
            new ItemStack(Material.GLASS, 16)
    };

    static ItemStack[] shopSetting2 = {
            new ItemStack(Material.COOKED_BEEF, 16),
            new ItemStack(Material.COOKED_CHICKEN, 16),
            new ItemStack(Material.COOKED_COD, 16),
            new ItemStack(Material.COOKED_MUTTON, 16),
            new ItemStack(Material.BREAD, 16),
            new ItemStack(Material.BROWN_MUSHROOM, 16),
            new ItemStack(Material.RED_MUSHROOM, 16),
            new ItemStack(Material.MOSS_BLOCK, 16),
            new ItemStack(Material.AIR, 16),

            new ItemStack(Material.ACACIA_SAPLING, 8),
            new ItemStack(Material.BAMBOO, 8),
            new ItemStack(Material.OAK_SAPLING, 8),
            new ItemStack(Material.BIRCH_SAPLING, 8),
            new ItemStack(Material.CHERRY_SAPLING, 8),
            new ItemStack(Material.DARK_OAK_SAPLING, 8),
            new ItemStack(Material.JUNGLE_SAPLING, 8),
            new ItemStack(Material.SPRUCE_SAPLING, 8),
            new ItemStack(Material.SWEET_BERRIES, 16),

            new ItemStack(Material.MELON_SEEDS, 4),
            new ItemStack(Material.PUMPKIN_SEEDS, 4),
            new ItemStack(Material.WHEAT_SEEDS, 4),
            new ItemStack(Material.SUGAR_CANE, 4),
            new ItemStack(Material.CARROT, 4),
            new ItemStack(Material.POTATO, 4),
            new ItemStack(Material.BEETROOT_SEEDS, 4),
            new ItemStack(Material.NETHER_WART, 4),
            new ItemStack(Material.GLOW_BERRIES, 4)
    };

    static List<ItemStack> shopSetting3 = new ArrayList<>(
            //铁匠铺 随机商品 详细请见WEAPONS
    );

    static ItemStack[] shopSetting4 = {
            new ItemStack(Material.ACACIA_LOG, 8),
            new ItemStack(Material.BIRCH_LOG, 8),
            new ItemStack(Material.CHERRY_LOG, 8),
            new ItemStack(Material.DARK_OAK_LOG, 8),
            new ItemStack(Material.JUNGLE_LOG, 8),
            new ItemStack(Material.OAK_LOG, 8),
            new ItemStack(Material.SPRUCE_LOG, 8),
            new ItemStack(Material.CRIMSON_STEM, 8),
            new ItemStack(Material.WARPED_STEM, 8),
            new ItemStack(Material.MANGROVE_ROOTS, 8),
            new ItemStack(Material.MANGROVE_LOG, 8)
    };

    static ItemStack[] shopSetting5 = {
            new ItemStack(Material.WHITE_WOOL, 16),
            new ItemStack(Material.ORANGE_WOOL, 16),
            new ItemStack(Material.MAGENTA_WOOL, 16),
            new ItemStack(Material.LIGHT_BLUE_WOOL, 16),
            new ItemStack(Material.YELLOW_WOOL, 16),
            new ItemStack(Material.LIME_WOOL, 16),
            new ItemStack(Material.PINK_WOOL, 16),
            new ItemStack(Material.GRAY_WOOL, 16),
            new ItemStack(Material.LIGHT_GRAY_WOOL, 16),

            new ItemStack(Material.CYAN_WOOL, 16),
            new ItemStack(Material.PURPLE_WOOL, 16),
            new ItemStack(Material.BLUE_WOOL, 16),
            new ItemStack(Material.BROWN_WOOL, 16),
            new ItemStack(Material.GREEN_WOOL, 16),
            new ItemStack(Material.RED_WOOL, 16),
            new ItemStack(Material.BLACK_WOOL, 16)
    };

    static ItemStack[] shopSetting6 = {
            new ItemStack(Material.WHITE_CONCRETE, 8),
            new ItemStack(Material.ORANGE_CONCRETE, 8),
            new ItemStack(Material.MAGENTA_CONCRETE, 8),
            new ItemStack(Material.LIGHT_BLUE_CONCRETE, 8),
            new ItemStack(Material.YELLOW_CONCRETE, 8),
            new ItemStack(Material.LIME_CONCRETE, 8),
            new ItemStack(Material.PINK_CONCRETE, 8),
            new ItemStack(Material.GRAY_CONCRETE, 8),
            new ItemStack(Material.LIGHT_GRAY_CONCRETE, 8),

            new ItemStack(Material.CYAN_CONCRETE, 8),
            new ItemStack(Material.PURPLE_CONCRETE, 8),
            new ItemStack(Material.BLUE_CONCRETE, 8),
            new ItemStack(Material.BROWN_CONCRETE, 8),
            new ItemStack(Material.GREEN_CONCRETE, 8),
            new ItemStack(Material.RED_CONCRETE, 8),
            new ItemStack(Material.BLACK_CONCRETE, 8)
    };

    static ItemStack[] shopSetting7 = {
            new ItemStack(Material.WHITE_DYE, 8),
            new ItemStack(Material.ORANGE_DYE, 8),
            new ItemStack(Material.MAGENTA_DYE, 8),
            new ItemStack(Material.LIGHT_BLUE_DYE, 8),
            new ItemStack(Material.YELLOW_DYE, 8),
            new ItemStack(Material.LIME_DYE, 8),
            new ItemStack(Material.PINK_DYE, 8),
            new ItemStack(Material.GRAY_DYE, 8),
            new ItemStack(Material.LIGHT_GRAY_DYE, 8),

            new ItemStack(Material.CYAN_DYE, 8),
            new ItemStack(Material.PURPLE_DYE, 8),
            new ItemStack(Material.BLUE_DYE, 8),
            new ItemStack(Material.BROWN_DYE, 8),
            new ItemStack(Material.GREEN_DYE, 8),
            new ItemStack(Material.RED_DYE, 8),
            new ItemStack(Material.BLACK_DYE, 8)
    };

    public void open(Player pl, int mode, PlayerInteractEvent e) {
        e.setCancelled(true);
        switch (mode) {
            case 1: {
                Inventory inv = Bukkit.createInventory(pl, 18, "建材商 | §lBLOCKS");
                inv.setContents(shopSetting1);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 2: {
                Inventory inv = Bukkit.createInventory(pl, 27, "农贸商 | §lARBORS");
                inv.setContents(shopSetting2);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 3: {
                Inventory inv = Bukkit.createInventory(pl, 9, "铁匠铺 | §lHANDTOOLS");
                inv.setContents(shopSetting3.toArray(new ItemStack[0]));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 4: {
                Inventory inv = Bukkit.createInventory(pl, 18, "木匠铺 | §lWOODS");
                inv.setContents(shopSetting4);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 5: {
                Inventory inv = Bukkit.createInventory(pl, 18, "染坊 | §lWOOL");
                inv.setContents(shopSetting5);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 6: {
                Inventory inv = Bukkit.createInventory(pl, 18, "染坊 | §lCONCRETE");
                inv.setContents(shopSetting6);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 7: {
                Inventory inv = Bukkit.createInventory(pl, 18, "染坊 | §lDYE");
                inv.setContents(shopSetting7);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
        }
    }

    public void fillEmpty(Inventory inv) {
        ItemStack glass = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(" ");
        glass.setItemMeta(meta);
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack current = inv.getItem(i);
            if (current == null || current.getType() == Material.AIR) {
                inv.setItem(i, glass);
            }
        }
    }

}
