package me.totalfreedom.totalfreedommod.command;
 
import java.util.Arrays;
import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getPlayer;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
 
@CommandPermissions(level = Rank.TRFDEVELOPER, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "A simple Punish GUI", usage = "/<command> [player]")
public class Command_punish implements Listener
{
    String[] args;
    private Inventory inv;
    private ItemStack mute;
    private CommandSender sender;
    
    public Command_punish() {
        inv = Bukkit.getServer().createInventory(null, 9, "&c&lPUNISH MENU");
        
        mute = createItem(DyeColor.RED, ChatColor.RED + "" + ChatColor.BOLD + "MUTE");
        
        inv.setItem(2, mute);
    }
    
     private ItemStack createItem(DyeColor dc, String name)
     {
         ItemStack i = new Wool(dc).toItemStack(1);
         ItemMeta im = i.getItemMeta();
         im.setDisplayName(name);
         im.setLore(Arrays.asList("Mute the target player!"));
         i.setItemMeta(im);
         return i;
     }
     
     public void show(Player p) {
         p.openInventory(inv);
     }
     
     @EventHandler
     public void onInventoryClick(InventoryClickEvent e) {
         if (!e.getInventory().equals(inv)) return;
         if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Mute")) {
             final Player player = getPlayer(args[0]);
         Bukkit.dispatchCommand(sender, "stfu " + player);
     }
     }
}