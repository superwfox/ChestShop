package sudark.chestshop;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sudark.chestshop.InitializeInventory.shopSetting3;

public class WEAPONS {
    static List<ItemStack> WEAPONS = new ArrayList<>();

    static void initialiseWeapon() {

        new BukkitRunnable() {
            @Override
            public void run() {
                UpdateItems();
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("ChestShop"), 0, 12 * 20 * 60 * 60);

        WEAPONS.add(Golden(Material.GOLDEN_PICKAXE));
        WEAPONS.add(Golden(Material.GOLDEN_AXE));
        WEAPONS.add(Golden(Material.GOLDEN_SWORD));
        WEAPONS.add(Golden(Material.GOLDEN_SHOVEL));
        WEAPONS.add(Golden(Material.GOLDEN_HOE));

        WEAPONS.add(Golden(Material.GOLDEN_HELMET));
        WEAPONS.add(Golden(Material.GOLDEN_CHESTPLATE));
        WEAPONS.add(Golden(Material.GOLDEN_LEGGINGS));
        WEAPONS.add(Golden(Material.GOLDEN_BOOTS));

        WEAPONS.add(Protect(Golden(Material.GOLDEN_HORSE_ARMOR)));
        WEAPONS.add(Protect(Golden(Material.WOLF_ARMOR)));

        WEAPONS.add(new ItemStack(Material.ANVIL));

        WEAPONS.add(Golden(Material.GOLDEN_APPLE));

        WEAPONS.add(Golden(Material.NETHERITE_AXE));
        WEAPONS.add(Golden(Material.NETHERITE_PICKAXE));
        WEAPONS.add(Golden(Material.NETHERITE_SWORD));
        WEAPONS.add(Golden(Material.NETHERITE_SHOVEL));
        WEAPONS.add(Golden(Material.NETHERITE_HOE));

        ItemStack crossbow = new ItemStack(Material.CROSSBOW);
        ItemMeta crossbowMeta = crossbow.getItemMeta();
        crossbowMeta.setLore(List.of("§r§l§6错金加工 | GILDED"));
        crossbowMeta.setUnbreakable(true);
        crossbowMeta.addEnchant(Enchantment.INFINITY, 1, true);
        crossbow.setItemMeta(crossbowMeta);


        ItemStack shield = new ItemStack(Material.SHIELD, 1);
        BlockStateMeta shieldMeta = (BlockStateMeta) shield.getItemMeta();
        shieldMeta.setUnbreakable(true);
        shieldMeta.setEnchantmentGlintOverride(true);
        shieldMeta.setLore(java.util.Arrays.asList(
                "§7散发着微弱的紫色光芒，",
                "§7盾面上刻有古老的符文"
        ));

        Banner bannerState = (Banner) shieldMeta.getBlockState();
        bannerState.setBaseColor(DyeColor.ORANGE);
        bannerState.addPattern(new Pattern(DyeColor.BLACK, PatternType.CREEPER));
        bannerState.addPattern(new Pattern(DyeColor.BLACK, PatternType.GLOBE));
        bannerState.update();

        shieldMeta.setBlockState(bannerState);
        shield.setItemMeta(shieldMeta);

        WEAPONS.add(shield);
        WEAPONS.add(crossbow);
    }

    static void UpdateItems() {
        List<ItemStack> tempWeapons = new ArrayList<>(WEAPONS);
        shopSetting3.clear();
        ItemStack glass = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(" ");
        glass.setItemMeta(meta);
        shopSetting3.add(glass);
        shopSetting3.add(glass);
        Random rand = new Random();
        for (int i = 2; i < 7 && !tempWeapons.isEmpty(); i++) {
            int randomIndex = rand.nextInt(tempWeapons.size());
            shopSetting3.add(tempWeapons.get(randomIndex));
            tempWeapons.remove(randomIndex);
        }
    }

    static ItemStack Golden(Material item) {
        ItemStack result = new ItemStack(item, 1);
        ItemMeta meta = result.getItemMeta();
        meta.setLore(List.of("§r§l§6鎏金打造 | GILDED"));
        meta.setUnbreakable(true);
        result.setItemMeta(meta);
        return result;
    }

    static ItemStack Protect(ItemStack item) {
        item.addUnsafeEnchantment(Enchantment.PROTECTION, 10);
        item.addUnsafeEnchantment(Enchantment.PROJECTILE_PROTECTION, 10);
        item.addUnsafeEnchantment(Enchantment.BLAST_PROTECTION, 10);
        item.addUnsafeEnchantment(Enchantment.FIRE_PROTECTION, 10);
        return item;
    }
}
