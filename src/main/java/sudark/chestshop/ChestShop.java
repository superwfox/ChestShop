package sudark.chestshop;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChestShop extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ShopOpenEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ShopClickEvent(), this);

        Bukkit.getPluginCommand("enchants").setExecutor(new Enchant());
        WEAPONS.initialiseWeapon();
    }

}
