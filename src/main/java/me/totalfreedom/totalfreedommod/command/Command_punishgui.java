package me.totalfreedom.totalfreedommod.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = Rank.TRFDEVELOPER, source = SourceType.ONLY_IN_GAME, blockHostConsole = false)
@CommandParameters(description = "A simple punish gui.", usage = "/<command> <player> [reason]", aliases = "punish,p")
public class Command_punishgui extends FreedomCommand implements Listener
{
    public List<Inventory> punishGuis;
    public Inventory punishGui;

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
                            final Player targetPlayer = getPlayer(args[0]);

                if (targetPlayer == null)
                {
                sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
                return true;
                }
        
        punishGuis = new ArrayList<Inventory>();
    
        punishGui = Bukkit.createInventory(null, 27, "§c§lPUNISH GUI");
        final ItemStack RedGlassPaneItem = new ItemStack(Material.SAND);
        final ItemMeta RedGlassPaneMeta = RedGlassPaneItem.getItemMeta();
        RedGlassPaneMeta.setDisplayName((new StringBuilder()).append(ChatColor.RED).append(ChatColor.BOLD).append("MUTE").toString());
        RedGlassPaneMeta.setLore(Arrays.asList("§cMute the target player", "§c(§bSUPER_ADMIN§c)"));
        RedGlassPaneItem.setItemMeta(RedGlassPaneMeta);
        punishGui.setItem(0, RedGlassPaneItem);
        
        Player player = (Player) sender;
        openGui(player);
        
        return true;
        
    }
    
    public void openGui(Player player)
    {
        Inventory inv = punishGui;
        punishGuis.add(inv);
        player.openInventory(inv);
    }
    
    //if(punishGuis.contains(event.getInventory())){
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("PUNISH GUI")){
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            
            if (event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR){
                player.closeInventory();
                return;
            }
            
            switch (event.getCurrentItem().getType()) {
                case SAND:
                    player.chat("Hey skiddos!");
                    player.closeInventory();
                    player.sendMessage(String.format("%sHello %sSkid!", ChatColor.RED, ChatColor.AQUA));
            }
            
            
        }
    }

}
