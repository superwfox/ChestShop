package sudark.chestshop;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.Scoreboard;
import sudark.chestshop.item.Enchant;
import sudark.chestshop.shop.*;

public final class ChestShop extends JavaPlugin {

    public static NamespacedKey amountKey = new NamespacedKey("sudark", "extra_amount");

    public static ItemStack Blank = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1);

    @Override
    public void onEnable() {
        ItemMeta meta = Blank.getItemMeta();
        meta.setDisplayName(" ");
        Blank.setItemMeta(meta);

        saveDefaultConfig();
        ShopData.load();

        Bukkit.getPluginManager().registerEvents(new ToggleOpenAndBlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new ShopClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ShopCloseEvent(), this);

        Bukkit.getPluginCommand("enchants").setExecutor(new Enchant());
        Bukkit.getPluginCommand("shop").setExecutor(new ShopCommand());
        Bukkit.getPluginCommand("shop").setTabCompleter(new ShopTabCompleter());
        WEAPONS.initialiseWeapon();

        ensureObjectiveExists("SHOP_1");
        ensureObjectiveExists("SHOP_2");
        ensureObjectiveExists("SHOP_3");
        ensureObjectiveExists("SHOP_4");
        ensureObjectiveExists("SHOP_5");
        ensureObjectiveExists("SHOP_6");
        ensureObjectiveExists("SHOP_7");
    }

    public static ChestShop get() {
        return (ChestShop) Bukkit.getPluginManager().getPlugin("ChestShop");
    }

    public static void ensureObjectiveExists(String name) {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        if (board.getObjective(name) == null) {
            board.registerNewObjective(name, Criteria.DUMMY, Component.text(name));
        }
    }
}
