package sudark.chestshop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static sudark.chestshop.ChestShop.Blank;
import static sudark.chestshop.ChestShop.amountKey;

public class InitializeInventory {

    static ItemStack[] shopSetting1 = {
            new ItemStack(Material.GRASS_BLOCK, 32),
            new ItemStack(Material.STONE, 32),
            new ItemStack(Material.COBBLESTONE, 32),
            new ItemStack(Material.DIRT, 32),
            new ItemStack(Material.SAND, 32),
            new ItemStack(Material.COBBLED_DEEPSLATE, 32),
            new ItemStack(Material.ANDESITE, 16),
            new ItemStack(Material.GRANITE, 16),
            new ItemStack(Material.DIORITE, 16),

            new ItemStack(Material.CLAY, 32),
            new ItemStack(Material.BLACKSTONE, 16),
            new ItemStack(Material.DARK_PRISMARINE, 16),
            new ItemStack(Material.GLASS, 16),
            new ItemStack(Material.ICE, 16),
            new ItemStack(Material.SCAFFOLDING, 16),
            new ItemStack(Material.QUARTZ_BLOCK, 16),
            new ItemStack(Material.PRISMARINE, 16),
            new ItemStack(Material.CALCITE, 16),

            new ItemStack(Material.TERRACOTTA, 16),
            new ItemStack(Material.OCHRE_FROGLIGHT, 8),
            new ItemStack(Material.PEARLESCENT_FROGLIGHT, 8),
            new ItemStack(Material.VERDANT_FROGLIGHT, 8),
            new ItemStack(Material.SEA_LANTERN, 8),
            new ItemStack(Material.END_ROD, 8),
            new ItemStack(Material.MAGMA_BLOCK, 8),
            new ItemStack(Material.RED_SAND, 32),
            new ItemStack(Material.GRAVEL, 32),

            new ItemStack(Material.SMOOTH_QUARTZ, 16),
            new ItemStack(Material.OBSIDIAN, 6),
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
            new ItemStack(Material.HONEY_BOTTLE, 16),

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
            new ItemStack(Material.MANGROVE_LOG, 8),
            new ItemStack(Material.PALE_OAK_LOG, 8),
            new ItemStack(Material.BAMBOO_BLOCK, 8),
            new ItemStack(Material.STRIPPED_ACACIA_LOG, 8),
            new ItemStack(Material.STRIPPED_BIRCH_LOG, 8),
            new ItemStack(Material.STRIPPED_CHERRY_LOG, 8),
            new ItemStack(Material.STRIPPED_DARK_OAK_LOG, 8),
            new ItemStack(Material.STRIPPED_JUNGLE_LOG, 8),

            new ItemStack(Material.STRIPPED_OAK_LOG, 8),
            new ItemStack(Material.STRIPPED_SPRUCE_LOG, 8),
            new ItemStack(Material.STRIPPED_CRIMSON_STEM, 8),
            new ItemStack(Material.STRIPPED_WARPED_STEM, 8),
            new ItemStack(Material.STRIPPED_MANGROVE_LOG, 8),
            new ItemStack(Material.STRIPPED_PALE_OAK_LOG, 8),
            new ItemStack(Material.STRIPPED_BAMBOO_BLOCK, 8),
            new ItemStack(Material.DARK_OAK_LEAVES, 8),
            new ItemStack(Material.OAK_LEAVES, 8),

            new ItemStack(Material.SPRUCE_LEAVES, 8),
            new ItemStack(Material.JUNGLE_LEAVES, 8),
            new ItemStack(Material.ACACIA_LEAVES, 8),
            new ItemStack(Material.AZALEA_LEAVES, 8),
            new ItemStack(Material.FLOWERING_AZALEA_LEAVES, 8),
            new ItemStack(Material.PALE_OAK_LEAVES, 8),
            new ItemStack(Material.CHERRY_LEAVES, 8),
            new ItemStack(Material.MANGROVE_LEAVES, 8),
            new ItemStack(Material.BIRCH_LEAVES, 8)
    };

    static ItemStack[] shopSetting5 = {
            new ItemStack(Material.OAK_STAIRS, 16),
            new ItemStack(Material.SPRUCE_STAIRS, 16),
            new ItemStack(Material.BIRCH_STAIRS, 16),
            new ItemStack(Material.ACACIA_STAIRS, 16),
            new ItemStack(Material.DARK_OAK_STAIRS, 16),
            new ItemStack(Material.BAMBOO_STAIRS, 16),
            new ItemStack(Material.CRIMSON_STAIRS, 16),
            new ItemStack(Material.WARPED_STAIRS, 16),
            new ItemStack(Material.STONE_BRICK_STAIRS, 16),

            new ItemStack(Material.SANDSTONE_STAIRS, 16),
            new ItemStack(Material.RED_SANDSTONE_STAIRS, 16),
            new ItemStack(Material.COBBLESTONE_STAIRS, 16),
            new ItemStack(Material.BRICK_STAIRS, 16),
            new ItemStack(Material.NETHER_BRICK_STAIRS, 16),
            new ItemStack(Material.QUARTZ_STAIRS, 16),
            new ItemStack(Material.POLISHED_GRANITE_STAIRS, 16),
            new ItemStack(Material.POLISHED_DIORITE_STAIRS, 16),
            new ItemStack(Material.POLISHED_ANDESITE_STAIRS, 16),

            new ItemStack(Material.DEEPSLATE_BRICK_STAIRS, 16),
            new ItemStack(Material.DEEPSLATE_TILE_STAIRS, 16),
            new ItemStack(Material.PRISMARINE_STAIRS, 16),
            new ItemStack(Material.PRISMARINE_BRICK_STAIRS, 16),
            new ItemStack(Material.DARK_PRISMARINE_STAIRS, 16),
            new ItemStack(Material.BLACKSTONE_STAIRS, 16),
            new ItemStack(Material.POLISHED_BLACKSTONE_BRICK_STAIRS, 16),
            new ItemStack(Material.POLISHED_BLACKSTONE_STAIRS, 16),

            new ItemStack(Material.OAK_SLAB, 32),
            new ItemStack(Material.SPRUCE_SLAB, 32),
            new ItemStack(Material.BIRCH_SLAB, 32),
            new ItemStack(Material.ACACIA_SLAB, 32),
            new ItemStack(Material.DARK_OAK_SLAB, 32),
            new ItemStack(Material.BAMBOO_SLAB, 32),
            new ItemStack(Material.CRIMSON_SLAB, 32),
            new ItemStack(Material.WARPED_SLAB, 32),
            new ItemStack(Material.STONE_BRICK_SLAB, 32),

            new ItemStack(Material.SANDSTONE_SLAB, 32),
            new ItemStack(Material.RED_SANDSTONE_SLAB, 32),
            new ItemStack(Material.COBBLESTONE_SLAB, 32),
            new ItemStack(Material.BRICK_SLAB, 32),
            new ItemStack(Material.NETHER_BRICK_SLAB, 32),
            new ItemStack(Material.SMOOTH_QUARTZ_SLAB, 32),
            new ItemStack(Material.POLISHED_GRANITE_SLAB, 32),
            new ItemStack(Material.POLISHED_DIORITE_SLAB, 32),
            new ItemStack(Material.POLISHED_ANDESITE_SLAB, 32),
            new ItemStack(Material.DEEPSLATE_BRICK_SLAB, 32),

            new ItemStack(Material.DEEPSLATE_TILE_SLAB, 32),
            new ItemStack(Material.PRISMARINE_SLAB, 32),
            new ItemStack(Material.PRISMARINE_BRICK_SLAB, 32),
            new ItemStack(Material.DARK_PRISMARINE_SLAB, 32),
            new ItemStack(Material.BLACKSTONE_SLAB, 32),
            new ItemStack(Material.POLISHED_BLACKSTONE_BRICK_SLAB, 32),
            new ItemStack(Material.POLISHED_BLACKSTONE_SLAB, 32),
            new ItemStack(Material.COBBLED_DEEPSLATE_SLAB, 32),
            new ItemStack(Material.BAMBOO_MOSAIC_SLAB, 32),
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
            new ItemStack(Material.BLACK_CONCRETE, 8),
            new ItemStack(Blank),
            new ItemStack(Blank),

            new ItemStack(Material.WHITE_TERRACOTTA, 8),
            new ItemStack(Material.ORANGE_TERRACOTTA, 8),
            new ItemStack(Material.MAGENTA_TERRACOTTA, 8),
            new ItemStack(Material.LIGHT_BLUE_TERRACOTTA, 8),
            new ItemStack(Material.YELLOW_TERRACOTTA, 8),
            new ItemStack(Material.LIME_TERRACOTTA, 8),
            new ItemStack(Material.PINK_TERRACOTTA, 8),
            new ItemStack(Material.GRAY_TERRACOTTA, 8),
            new ItemStack(Material.LIGHT_GRAY_TERRACOTTA, 8),

            new ItemStack(Material.CYAN_TERRACOTTA, 8),
            new ItemStack(Material.PURPLE_TERRACOTTA, 8),
            new ItemStack(Material.BLUE_TERRACOTTA, 8),
            new ItemStack(Material.BROWN_TERRACOTTA, 8),
            new ItemStack(Material.GREEN_TERRACOTTA, 8),
            new ItemStack(Material.RED_TERRACOTTA, 8),
            new ItemStack(Material.BLACK_TERRACOTTA, 8),
            new ItemStack(Blank),
            new ItemStack(Blank),

            new ItemStack(Material.WHITE_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.LIME_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.PINK_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.GRAY_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, 8),

            new ItemStack(Material.CYAN_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.BLUE_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.BROWN_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.GREEN_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.RED_GLAZED_TERRACOTTA, 8),
            new ItemStack(Material.BLACK_GLAZED_TERRACOTTA, 8),

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
            new ItemStack(Material.BLACK_DYE, 8),
            new ItemStack(Blank),
            new ItemStack(Blank),

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
            new ItemStack(Material.BLACK_WOOL, 16),
            new ItemStack(Blank),
            new ItemStack(Blank),

            new ItemStack(Material.WHITE_STAINED_GLASS, 16),
            new ItemStack(Material.ORANGE_STAINED_GLASS, 16),
            new ItemStack(Material.MAGENTA_STAINED_GLASS, 16),
            new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS, 16),
            new ItemStack(Material.YELLOW_STAINED_GLASS, 16),
            new ItemStack(Material.LIME_STAINED_GLASS, 16),
            new ItemStack(Material.PINK_STAINED_GLASS, 16),
            new ItemStack(Material.GRAY_STAINED_GLASS, 16),
            new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS, 16),

            new ItemStack(Material.CYAN_STAINED_GLASS, 16),
            new ItemStack(Material.PURPLE_STAINED_GLASS, 16),
            new ItemStack(Material.BLUE_STAINED_GLASS, 16),
            new ItemStack(Material.BROWN_STAINED_GLASS, 16),
            new ItemStack(Material.GREEN_STAINED_GLASS, 16),
            new ItemStack(Material.RED_STAINED_GLASS, 16),
            new ItemStack(Material.BLACK_STAINED_GLASS, 16),
    };

    static final String shop1Name = "建材铺 | §lBLOCKS §r§8价格：§61L";
    static final String shop1NamePro = "建材铺 PRO | §lBLOCKS §r§8价格：§6128L";

    static final String shop2Name = "农贸商 | §lARBORS §r§8价格：§61L";
    static final String shop2NamePro = "农贸商 PRO | §lARBORS §r§8价格：§6128L";

    static final String shop3Name = "铁匠铺 | §lHANDTOOLS §r§8价格：§650L";

    static final String shop4Name = "木料铺 | §lWOODS §r§8价格：§61L";
    static final String shop4NamePro = "木料铺 PRO | §lWOODS §r§8价格：§6128L";

    static final String shop5Name = "加工铺 | §lBUILDINGS §r§8价格：§61L";
    static final String shop5NamePro = "加工铺 PRO | §lBUILDINGS §r§8价格：§6128L";

    static final String shop6Name = "土坊 | §lCONCRETE §r§8价格：§61L";
    static final String shop6NamePro = "土坊 PRO | §lCONCRETE §r§8价格：§6128L";

    static final String shop7Name = "染坊 | §lDYES §r§8价格：§61L";
    static final String shop7NamePro = "染坊 PRO | §lDYES §r§8价格：§6128L";


    public static void open(Player pl, int mode) {
        switch (mode) {

            case 1: {
                Inventory inv = Bukkit.createInventory(pl, 36, shop1Name);
                inv.setContents(shopSetting1);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 21: {
                Inventory inv = Bukkit.createInventory(pl, 36, shop1NamePro);
                inv.setContents(expendSize(shopSetting1));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }

            case 2: {
                Inventory inv = Bukkit.createInventory(pl, 27, shop2Name);
                inv.setContents(shopSetting2);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 22: {
                Inventory inv = Bukkit.createInventory(pl, 27, shop2NamePro);
                inv.setContents(expendSize(shopSetting2));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }

            case 3: {
                Inventory inv = Bukkit.createInventory(pl, 9, shop3Name);
                inv.setContents(shopSetting3.toArray(new ItemStack[0]));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }

            case 4: {
                Inventory inv = Bukkit.createInventory(pl, 36, shop4Name);
                inv.setContents(shopSetting4);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 24: {
                Inventory inv = Bukkit.createInventory(pl, 36, shop4NamePro);
                inv.setContents(expendSize(shopSetting4));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }

            case 5: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop5Name);
                inv.setContents(shopSetting5);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 25: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop5NamePro);
                inv.setContents(expendSize(shopSetting5));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }

            case 6: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop6Name);
                inv.setContents(shopSetting6);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 26: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop6NamePro);
                inv.setContents(expendSize(shopSetting6));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }

            case 7: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop7Name);
                inv.setContents(shopSetting7);
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 27: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop7NamePro);
                inv.setContents(expendSize(shopSetting7));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }

            case 8: {
                Inventory inv = Bukkit.createInventory(pl, 54, "狮首祭台 | §lCYCLE");
                pl.openInventory(inv);
            }

        }
    }

    public static ItemStack[] expendSize(ItemStack[] list) {
        ItemStack[] modified = Arrays.stream(list).map(item -> {
            if (item == null) return null;
            ItemStack copy = item.clone(); // 克隆避免修改原物品
            ItemMeta meta = copy.getItemMeta();

            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(amountKey, PersistentDataType.INTEGER, copy.getAmount() * 128);
            meta.setLore(List.of("§7+" + copy.getAmount() * 128));
            copy.setAmount(1);

            copy.setItemMeta(meta);
            return copy;

        }).toArray(ItemStack[]::new);
        return modified;
    }

    public static void fillEmpty(Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack current = inv.getItem(i);
            if (current == null || current.getType() == Material.AIR) {
                inv.setItem(i, Blank);
            }
        }
    }

}
