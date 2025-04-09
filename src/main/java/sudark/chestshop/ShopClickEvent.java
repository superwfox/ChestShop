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

public class ShopClickEvent implements Listener {

    @EventHandler
    public void onShopClick(InventoryClickEvent e) {

        if (e.getClickedInventory() instanceof PlayerInventory) return;

        if (e.getCurrentItem() == null) return;

        if(e.getCurrentItem().getType().equals(Material.LIGHT_GRAY_STAINED_GLASS_PANE)){
            e.setCancelled(true);
            return;
        }

        String name = e.getView().getTitle();
        Player pl = (Player) e.getWhoClicked();
        ItemStack good = e.getCurrentItem();

        if (name.equals("建材商 | §lBLOCKS")) {
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }

            order(pl, 1, good,Sound.BLOCK_BONE_BLOCK_BREAK);
            return;
        }

        if (name.equals("农贸商 | §lARBORS")) {
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }

            order(pl, 1, good,Sound.ENTITY_WANDERING_TRADER_YES);
        }

        if (name.equals("铁匠铺 | HANDTOOLS")) {
            e.setCancelled(true);

            if (pl.getLevel() < 50) {
                cancel(pl);
                return;
            }

            order(pl, 50, good,Sound.BLOCK_ANVIL_BREAK);
        }

        if (name.equals("木匠铺 | §lWOODS")) {
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }

            order(pl, 1, good,Sound.BLOCK_NETHER_GOLD_ORE_PLACE);
        }

        if (name.equals("染坊 | §lWOOL")) {
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }

            order(pl, 1, good,Sound.BLOCK_WOOL_PLACE);
        }

        if (name.equals("染坊 | §lCONCRETE")) {
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }

            order(pl, 1, good,Sound.BLOCK_SAND_HIT);
        }

        if (name.equals("染坊 | §lDYE")) {
            e.setCancelled(true);

            if (pl.getLevel() < 1) {
                cancel(pl);
                return;
            }

            order(pl, 1, good,Sound.UI_LOOM_SELECT_PATTERN);
        }
    }

    public void cancel(Player pl) {
        pl.playSound(pl, Sound.ENTITY_VILLAGER_NO, 1, 1);
        pl.damage(-1, DamageSource.builder(DamageType.OUT_OF_WORLD).build());
    }

    public void order(Player pl, int price, ItemStack good,Sound sound) {
        pl.giveExpLevels(-price);
        pl.getInventory().addItem(good);
        pl.playSound(pl, sound, 1, 1);
    }


}
