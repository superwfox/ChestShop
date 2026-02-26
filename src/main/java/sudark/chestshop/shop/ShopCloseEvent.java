package sudark.chestshop.shop;

import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import sudark.chestshop.ChestShop;
import sudark.chestshop.item.ItemRarity;

import java.util.HashMap;
import java.util.Map;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;
import static sudark.chestshop.item.ItemRarity.checkRarity;

public class ShopCloseEvent implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {

        if (!e.getView().getTitle().equals("狮首祭台 | §lCYCLE"))
            return;

        Player pl = (Player) e.getPlayer();

        int respond = 0;
        Map<Material, Integer> countMap = new HashMap<>();

        for (ItemStack item : e.getInventory().getContents()) {

            if (item == null || item.getType().isAir())
                continue;
            countMap.merge(item.getType(), item.getAmount(), Integer::sum);

            int c = 0;
            switch (checkRarity(item)) {
                case COMMON -> c = 1;
                case UNCOMMON -> c = 3;
                case RARE -> c = 9;
                case EPIC -> c = 27;
                case EXTRAORDINARY -> c = 81;
            }
            respond += item.getAmount() * c;
        }

        if (respond == 0) {
            pl.playSound(pl, Sound.ENTITY_GHAST_DEATH, 0.7f, 0.6f);
            title(pl, "[§e 狮首祭台 §f]", "§7并非诚意 何谈福禄");
            return;
        }

        int max = countMap.values().stream().max(Integer::compare).orElse(0);
        if (max > 64) {
            respond *= Math.pow(1.1, max / 64);
        }

        pl.giveExp(respond);
        pl.playSound(pl, Sound.ENTITY_GHAST_DEATH, 0.7f, 0.3f);

        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                gld(pl);
                if (i == 7)
                    cancel();
            }
        }.runTaskTimer(getPlugin(ChestShop.class), 0, 10l);

        pl.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 2, 1));
        pl.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 2, 1));
        title(pl, "[§e 狮首祭台 §f]", "§7你得到了 " + respond + " 福禄"
                + (max > 64 ? "§7 (x" + String.format("%.3f", Math.pow(1.1, max / 64)) + ")" : ""));

    }

    public void gld(Player pl) {
        Particle.DustTransition dustTransition = new Particle.DustTransition(Color.YELLOW, Color.AQUA, 0.8F);
        ItemStack goldBlockItem = new ItemStack(Material.GOLD_INGOT);
        final Item gold = pl.getWorld().dropItemNaturally(pl.getLocation().add(0, 1.5, 0), goldBlockItem);
        pl.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, pl.getLocation().add(0, 1.5, 0), 10, 0.5, 0.5, 0.5,
                0.10000000149011612, dustTransition);
        gold.setCanPlayerPickup(false);

        (new BukkitRunnable() {
            public void run() {
                gold.remove();
            }
        }).runTaskLater(Bukkit.getPluginManager().getPlugin("ChestShop"), 80L);
    }

    public void title(Player pl, String t1, String t2) {
        new BukkitRunnable() {
            StringBuilder temt = new StringBuilder("_");
            int i = 0;

            @Override
            public void run() {
                temt.append(t2.toCharArray()[i]);
                pl.sendTitle(t1, temt + "§f_", 0, 20, 50);
                i++;
                if (i == t2.length()) {
                    cancel();
                }
            }
        }.runTaskTimer(getPlugin(ChestShop.class), 0, 2);
    }
}
