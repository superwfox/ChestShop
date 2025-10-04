package sudark.chestshop;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemRarity {

    public static final Map<Material, Rarity> RARITY_MAP = new HashMap<>();

    public static Rarity checkRarity(ItemStack m) {
        if (m.getType().name().contains("DIAMOND")) return Rarity.UNCOMMON;
        if (m.getType().name().contains("NETHERITE")) return Rarity.RARE;
        return RARITY_MAP.getOrDefault(m.getType(), Rarity.COMMON);
    }

    public enum Rarity {
        COMMON, UNCOMMON, RARE, EPIC, EXTRAORDINARY
    }

    static {
        RARITY_MAP.put(Material.IRON_INGOT, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.LAPIS_LAZULI, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.REDSTONE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.ENCHANTED_BOOK, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.SADDLE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.DIAMOND_PICKAXE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.MUSIC_DISC_13, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.PRISMARINE_CRYSTALS, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.NAME_TAG, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.GOLD_INGOT, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.EMERALD, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.DIAMOND, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.GOLDEN_APPLE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.IRON_SWORD, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.ENDER_PEARL, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.BLAZE_ROD, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.TOTEM_OF_UNDYING, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.TURTLE_SCUTE, Rarity.UNCOMMON); // 海龟壳
        RARITY_MAP.put(Material.SLIME_BALL, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.GHAST_TEAR, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.ENDER_EYE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.EXPERIENCE_BOTTLE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.SPECTRAL_ARROW, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.TIPPED_ARROW, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.HONEY_BOTTLE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.BREWING_STAND, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.ENCHANTING_TABLE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.GOLDEN_CARROT, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.FIRE_CHARGE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.BOOKSHELF, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.OBSERVER, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.DISPENSER, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.CHAIN, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.LANTERN, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.SOUL_LANTERN, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.CAMPFIRE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.SOUL_CAMPFIRE, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.COMPARATOR, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.HOPPER, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.PISTON, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.STICKY_PISTON, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.NOTE_BLOCK, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.CAULDRON, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.SPYGLASS, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.LEAD, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.HONEYCOMB, Rarity.UNCOMMON);
        RARITY_MAP.put(Material.TARGET, Rarity.UNCOMMON);

        RARITY_MAP.put(Material.ELYTRA, Rarity.RARE);
        RARITY_MAP.put(Material.TRIDENT, Rarity.RARE);
        RARITY_MAP.put(Material.HEART_OF_THE_SEA, Rarity.RARE);
        RARITY_MAP.put(Material.NAUTILUS_SHELL, Rarity.RARE);
        RARITY_MAP.put(Material.BEACON, Rarity.RARE);
        RARITY_MAP.put(Material.NETHERITE_INGOT, Rarity.RARE);
        RARITY_MAP.put(Material.SPONGE, Rarity.RARE);
        RARITY_MAP.put(Material.WITHER_SKELETON_SKULL, Rarity.RARE);
        RARITY_MAP.put(Material.SHULKER_SHELL, Rarity.RARE);
        RARITY_MAP.put(Material.CONDUIT, Rarity.RARE);
        RARITY_MAP.put(Material.MUSIC_DISC_PIGSTEP, Rarity.RARE);
        RARITY_MAP.put(Material.ANCIENT_DEBRIS, Rarity.RARE);
        RARITY_MAP.put(Material.TURTLE_EGG, Rarity.RARE);
        RARITY_MAP.put(Material.WITHER_ROSE, Rarity.RARE);
        RARITY_MAP.put(Material.END_CRYSTAL, Rarity.RARE);
        RARITY_MAP.put(Material.LODESTONE, Rarity.RARE);
        RARITY_MAP.put(Material.RESPAWN_ANCHOR, Rarity.RARE);
        RARITY_MAP.put(Material.BUDDING_AMETHYST, Rarity.RARE);
        RARITY_MAP.put(Material.DEEPSLATE_EMERALD_ORE, Rarity.RARE); // 极难生成
        RARITY_MAP.put(Material.MANGROVE_PROPAGULE, Rarity.RARE);
        RARITY_MAP.put(Material.SCULK_SENSOR, Rarity.RARE);
        RARITY_MAP.put(Material.REINFORCED_DEEPSLATE, Rarity.RARE);
        RARITY_MAP.put(Material.MUSIC_DISC_5, Rarity.RARE);
        RARITY_MAP.put(Material.MUSIC_DISC_OTHERSIDE, Rarity.RARE);
        RARITY_MAP.put(Material.SCULK_CATALYST, Rarity.RARE);
        RARITY_MAP.put(Material.SCULK_SHRIEKER, Rarity.RARE);
        RARITY_MAP.put(Material.SCULK, Rarity.RARE);
        RARITY_MAP.put(Material.SCULK_VEIN, Rarity.RARE);
        RARITY_MAP.put(Material.TINTED_GLASS, Rarity.RARE);
        RARITY_MAP.put(Material.CANDLE, Rarity.RARE);
        RARITY_MAP.put(Material.BIG_DRIPLEAF, Rarity.RARE);
        RARITY_MAP.put(Material.SMALL_DRIPLEAF, Rarity.RARE);
        RARITY_MAP.put(Material.SPORE_BLOSSOM, Rarity.RARE);
        RARITY_MAP.put(Material.POWDER_SNOW_BUCKET, Rarity.RARE);
        RARITY_MAP.put(Material.BRUSH, Rarity.RARE);

        RARITY_MAP.put(Material.DRAGON_HEAD, Rarity.EPIC);
        RARITY_MAP.put(Material.ENCHANTED_GOLDEN_APPLE, Rarity.EPIC);
        RARITY_MAP.put(Material.DRAGON_EGG, Rarity.EPIC);
        RARITY_MAP.put(Material.SUSPICIOUS_SAND, Rarity.EPIC); // 考古道具
        RARITY_MAP.put(Material.SUSPICIOUS_GRAVEL, Rarity.EPIC);
        RARITY_MAP.put(Material.SNIFFER_EGG, Rarity.EPIC); // 古代生物蛋
        RARITY_MAP.put(Material.SNIFFER_SPAWN_EGG, Rarity.EPIC);
        RARITY_MAP.put(Material.FILLED_MAP, Rarity.EPIC);
        RARITY_MAP.put(Material.MAP, Rarity.EPIC); // 替代特殊宝藏图

        RARITY_MAP.put(Material.MUSIC_DISC_WAIT, Rarity.EXTRAORDINARY);
        RARITY_MAP.put(Material.NETHER_STAR, Rarity.EXTRAORDINARY);
        RARITY_MAP.put(Material.COMMAND_BLOCK, Rarity.EXTRAORDINARY);
        RARITY_MAP.put(Material.REPEATING_COMMAND_BLOCK, Rarity.EXTRAORDINARY);
        RARITY_MAP.put(Material.CHAIN_COMMAND_BLOCK, Rarity.EXTRAORDINARY);
        RARITY_MAP.put(Material.RECOVERY_COMPASS, Rarity.EXTRAORDINARY);

    }


}
