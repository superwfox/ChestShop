package sudark.chestshop.item;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sudark.chestshop.shop.InitializeInventory;

public class Enchant implements CommandExecutor {

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label,
            String[] args) {

        if (cmd.getName().equalsIgnoreCase("enchants") && sender instanceof BlockCommandSender block) {
            Player player = block.getBlock().getLocation().add(0.5, 2.0, 0.5)
                    .getNearbyPlayers(0.5).stream().findFirst().orElse(null);
            if (player == null)
                return false;

            if (args.length == 1) {
                Location loc = player.getLocation();
                float yaw = loc.getYaw();

                double x = -Math.sin(Math.toRadians(yaw));
                double z = Math.cos(Math.toRadians(yaw));

                loc.add(x * -2, 0, z * -2);
                player.teleport(loc);

                switch (Integer.parseInt(args[0])) {
                    case 1 -> InitializeInventory.open(player, 1);
                    case 2 -> InitializeInventory.open(player, 2);
                    case 3 -> InitializeInventory.open(player, 3);
                    case 4 -> InitializeInventory.open(player, 4);
                    case 5 -> InitializeInventory.open(player, 5);
                    case 6 -> InitializeInventory.open(player, 6);
                    case 7 -> InitializeInventory.open(player, 7);
                    case 8 -> InitializeInventory.open(player, 8);
                }
                return true;
            }

            int purse = player.getLevel();
            int price = Integer.parseInt(args[2]);
            if (purse >= price) {
                if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                    player.sendTitle("[§e附魔错误§f]", "§7您这麒麟臂可不敢随便附魔", 10, 30, 20);
                } else {
                    Enchantment enchant = Enchantment.getByName(args[0].toUpperCase());
                    int level = Integer.parseInt(args[1]);
                    ItemStack item = player.getInventory().getItemInMainHand();
                    ItemMeta meta = item.getItemMeta();
                    if (meta.hasEnchant(enchant) && meta.getEnchantLevel(enchant) >= level) {
                        player.sendTitle("[§e重复附魔§f]", "§7物品存在重复等级附魔", 10, 30, 20);
                    } else {
                        meta.addEnchant(enchant, level, true);
                        item.setItemMeta(meta);
                        player.giveExpLevels(-price);
                        player.sendMessage("§e§lLevel : §r§6" + (purse - price) + "§f | §eCOST : " + price);
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0F, 1.0F);
                    }
                }
            } else {
                player.sendTitle("", "§e§lLevel : §c" + purse);
            }
        }

        return false;
    }
}
