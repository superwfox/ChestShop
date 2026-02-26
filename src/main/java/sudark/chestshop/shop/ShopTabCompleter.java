package sudark.chestshop.shop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ShopTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            List<String> options = new ArrayList<>(ShopData.SHOP_NAMES);
            options.add("reload");
            String input = args[0].toLowerCase();
            options.removeIf(s -> !s.toLowerCase().startsWith(input));
            return options;
        }
        return List.of();
    }
}
