package sudark.chestshop;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class ShopOpenEvent implements Listener {
    @EventHandler
    public void onShopOpen(PlayerInteractEvent e) {
        Player pl = e.getPlayer();
        if (!pl.getWorld().getName().equals("BEEF-Main")) return;

        if (e.getClickedBlock() == null) return;

        if (pl.getGameMode().equals(GameMode.CREATIVE)) return;

        Material bl = e.getClickedBlock().getType();

        IntializeInventory in = new IntializeInventory();

        switch (bl) {
            case STONECUTTER -> in.open(pl, 1, e);
            case BARREL -> in.open(pl, 2, e);
            case BLAST_FURNACE -> in.open(pl, 3, e);
            case STRIPPED_CHERRY_LOG -> in.open(pl, 4, e);
            case YELLOW_WOOL -> in.open(pl, 5, e);
            case YELLOW_CONCRETE_POWDER -> in.open(pl, 6, e);
            case LOOM -> in.open(pl, 7, e);
            case GILDED_BLACKSTONE -> {
                pl.sendTitle("这只是个§e石头", "—————————————");
                pl.damage(-1);
                pl.playSound(pl, Sound.ENTITY_LINGERING_POTION_THROW, 1, 1);
            }
            case POLISHED_BASALT ->
                Bukkit.dispatchCommand(
                        e.getPlayer(),
                        ((CommandBlock) e.getClickedBlock().getLocation().subtract(0, 1, 0).getBlock().getState()).getCommand()
                );
        }


    }
}
