package sudark.chestshop.shop;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import sudark.chestshop.ChestShop;

public class ShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("§7权限不足");
            return true;
        }

        if (args.length == 1 && args[0].equals("reload")) {
            ChestShop.get().reloadConfig();
            ShopData.load();
            sender.sendMessage("[§eShop§f] §b配置已重载");
            return true;
        }

        if (args.length == 1 && ShopData.SHOP_NAMES.contains(args[0])) {
            if (!(sender instanceof Player pl)) {
                sender.sendMessage("§7仅玩家可用");
                return true;
            }
            ItemStack hand = pl.getInventory().getItemInMainHand();
            if (hand.getType() == Material.AIR) {
                pl.sendMessage("[§eShop§f] §7手持物品为空");
                return true;
            }
            ShopData.addItem(args[0], hand.getType(), hand.getAmount());
            pl.sendMessage("[§eShop§f] §b" + args[0] + " §f+= §e" + hand.getType().name() + ":" + hand.getAmount());
            return true;
        }

        sender.sendMessage("[§eShop§f] §7/shop reload | /shop <商店名>");
        return true;
    }
}
