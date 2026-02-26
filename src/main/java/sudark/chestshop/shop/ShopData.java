package sudark.chestshop.shop;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import sudark.chestshop.ChestShop;

import java.util.*;

public class ShopData {

    public static final List<String> SHOP_NAMES = List.of("建材铺", "农贸商", "木料铺", "加工铺", "土坊", "染坊");

    static final Map<String, List<ItemStack>> shops = new LinkedHashMap<>();

    public static void load() {
        shops.clear();
        FileConfiguration cfg = ChestShop.get().getConfig();

        for (String name : SHOP_NAMES) {
            List<String> raw = cfg.getStringList("shops." + name);
            List<ItemStack> items = new ArrayList<>();
            for (String entry : raw) {
                String[] parts = entry.split(":");
                try {
                    Material mat = Material.valueOf(parts[0]);
                    int amount = Integer.parseInt(parts[1]);
                    items.add(new ItemStack(mat, amount));
                } catch (Exception ignored) {
                }
            }
            shops.put(name, items);
        }
    }

    public static void save() {
        FileConfiguration cfg = ChestShop.get().getConfig();
        for (var e : shops.entrySet()) {
            List<String> raw = new ArrayList<>();
            for (ItemStack item : e.getValue()) {
                raw.add(item.getType().name() + ":" + item.getAmount());
            }
            cfg.set("shops." + e.getKey(), raw);
        }
        ChestShop.get().saveConfig();
    }

    public static ItemStack[] getShop(String name) {
        List<ItemStack> list = shops.get(name);
        if (list == null)
            return new ItemStack[0];
        return list.stream().map(ItemStack::clone).toArray(ItemStack[]::new);
    }

    public static void addItem(String shopName, Material mat, int amount) {
        List<ItemStack> list = shops.get(shopName);
        if (list == null)
            return;
        list.removeIf(i -> i.getType() == mat);
        list.add(new ItemStack(mat, amount));
        save();
    }
}
