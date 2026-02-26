package sudark.chestshop.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static sudark.chestshop.ChestShop.Blank;
import static sudark.chestshop.ChestShop.amountKey;

public class InitializeInventory {

    public static List<ItemStack> shopSetting3 = new ArrayList<>();

    public static final String shop1Name = "建材铺 | §lBLOCKS §r§8价格：§61L";
    public static final String shop1NamePro = "建材铺 PRO | §lBLOCKS §r§8价格：§6128L";

    public static final String shop2Name = "农贸商 | §lARBORS §r§8价格：§61L";
    public static final String shop2NamePro = "农贸商 PRO | §lARBORS §r§8价格：§6128L";

    public static final String shop3Name = "铁匠铺 | §lHANDTOOLS §r§8价格：§650L";

    public static final String shop4Name = "木料铺 | §lWOODS §r§8价格：§61L";
    public static final String shop4NamePro = "木料铺 PRO | §lWOODS §r§8价格：§6128L";

    public static final String shop5Name = "加工铺 | §lBUILDINGS §r§8价格：§61L";
    public static final String shop5NamePro = "加工铺 PRO | §lBUILDINGS §r§8价格：§6128L";

    public static final String shop6Name = "土坊 | §lCONCRETE §r§8价格：§61L";
    public static final String shop6NamePro = "土坊 PRO | §lCONCRETE §r§8价格：§6128L";

    public static final String shop7Name = "染坊 | §lDYES §r§8价格：§61L";
    public static final String shop7NamePro = "染坊 PRO | §lDYES §r§8价格：§6128L";

    static ItemStack[] getShop(int num) {
        return switch (num) {
            case 1 -> ShopData.getShop("建材铺");
            case 2 -> ShopData.getShop("农贸商");
            case 4 -> ShopData.getShop("木料铺");
            case 5 -> ShopData.getShop("加工铺");
            case 6 -> ShopData.getShop("土坊");
            case 7 -> ShopData.getShop("染坊");
            default -> new ItemStack[0];
        };
    }

    public static void open(Player pl, int mode) {
        switch (mode) {
            case 1: {
                Inventory inv = Bukkit.createInventory(pl, 36, shop1Name);
                inv.setContents(getShop(1));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 21: {
                Inventory inv = Bukkit.createInventory(pl, 36, shop1NamePro);
                inv.setContents(expendSize(getShop(1)));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 2: {
                Inventory inv = Bukkit.createInventory(pl, 27, shop2Name);
                inv.setContents(getShop(2));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 22: {
                Inventory inv = Bukkit.createInventory(pl, 27, shop2NamePro);
                inv.setContents(expendSize(getShop(2)));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 3: {
                Inventory inv = Bukkit.createInventory(pl, 9, shop3Name);
                inv.setContents(shopSetting3.toArray(new ItemStack[0]));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 4: {
                Inventory inv = Bukkit.createInventory(pl, 36, shop4Name);
                inv.setContents(getShop(4));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 24: {
                Inventory inv = Bukkit.createInventory(pl, 36, shop4NamePro);
                inv.setContents(expendSize(getShop(4)));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 5: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop5Name);
                inv.setContents(getShop(5));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 25: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop5NamePro);
                inv.setContents(expendSize(getShop(5)));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 6: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop6Name);
                inv.setContents(getShop(6));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 26: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop6NamePro);
                inv.setContents(expendSize(getShop(6)));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 7: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop7Name);
                inv.setContents(getShop(7));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 27: {
                Inventory inv = Bukkit.createInventory(pl, 54, shop7NamePro);
                inv.setContents(expendSize(getShop(7)));
                fillEmpty(inv);
                pl.openInventory(inv);
                break;
            }
            case 8: {
                Inventory inv = Bukkit.createInventory(pl, 54, "狮首祭台 | §lCYCLE");
                pl.openInventory(inv);
            }
        }
    }

    public static ItemStack[] expendSize(ItemStack[] list) {
        return Arrays.stream(list).map(item -> {
            if (item == null)
                return null;
            ItemStack copy = item.clone();
            ItemMeta meta = copy.getItemMeta();

            PersistentDataContainer container = meta.getPersistentDataContainer();
            container.set(amountKey, PersistentDataType.INTEGER, copy.getAmount() * 128);
            meta.setLore(List.of("§7+" + copy.getAmount() * 128));
            copy.setAmount(1);

            copy.setItemMeta(meta);
            return copy;
        }).toArray(ItemStack[]::new);
    }

    public static void fillEmpty(Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack current = inv.getItem(i);
            if (current == null || current.getType() == Material.AIR) {
                inv.setItem(i, Blank);
            }
        }
    }
}
