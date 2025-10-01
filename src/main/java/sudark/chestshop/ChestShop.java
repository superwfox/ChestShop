package sudark.chestshop;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.Scoreboard;

public final class ChestShop extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ShopOpenEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ShopClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ShopCloseEvent(), this);

        Bukkit.getPluginCommand("enchants").setExecutor(new Enchant());
        WEAPONS.initialiseWeapon();

        ensureObjectiveExists("SHOP_1");
        ensureObjectiveExists("SHOP_2");
        ensureObjectiveExists("SHOP_3");
        ensureObjectiveExists("SHOP_4");
        ensureObjectiveExists("SHOP_5");
        ensureObjectiveExists("SHOP_6");
        ensureObjectiveExists("SHOP_7");
    }

    public static Plugin get(){
        return Bukkit.getPluginManager().getPlugin("ChestShop");
    }

    public static void ensureObjectiveExists(String name) {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        if (board.getObjective(name) == null) {
            board.registerNewObjective(name, Criteria.DUMMY, Component.text(name));
        }
    }


}
