package me.totalfreedom.totalfreedommod.command;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.PlayerInventory;
import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Get the neccesary tools for administrating", usage = "/<command>")
public class Command_admintools extends FreedomCommand
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        PlayerInventory inv = sender_p.getInventory();
        
        sender_p.getInventory().clear();
         
        final ItemStack heldItem = new ItemStack(Material.WOOD_AXE);
        final ItemMeta heldItemMeta = heldItem.getItemMeta();
        heldItemMeta.setDisplayName((new StringBuilder()).append(ChatColor.GOLD).append("The ").append(ChatColor.YELLOW).append("Wand").toString());
        heldItemMeta.addEnchant(Enchantment.DURABILITY, 127, true);
        heldItem.setItemMeta(heldItemMeta);

        final ItemStack compassItem = new ItemStack(Material.COMPASS);
        final ItemMeta compassMeta = heldItem.getItemMeta();
        compassMeta.setDisplayName((new StringBuilder()).append(ChatColor.DARK_RED).append("The ").append(ChatColor.RED).append("Compass").toString());
        compassMeta.addEnchant(Enchantment.DURABILITY, 127, true);
        compassItem.setItemMeta(compassMeta);
    
        final ItemStack stickItem = new ItemStack(Material.STICK);
        final ItemMeta stickItemMeta = heldItem.getItemMeta();
        stickItemMeta.setDisplayName((new StringBuilder()).append(ChatColor.YELLOW).append("Log ").append(ChatColor.YELLOW).append("Stick").toString());
        stickItemMeta.addEnchant(Enchantment.DURABILITY, 127, true);
        stickItem.setItemMeta(stickItemMeta);
        
        final ItemStack blockItem = new ItemStack(Material.EMERALD_BLOCK);
        final ItemMeta blockItemMeta = heldItem.getItemMeta();      
        blockItemMeta.setDisplayName((new StringBuilder()).append(ChatColor.DARK_GREEN).append("Emerald ").append(ChatColor.GREEN).append("Block").toString());
        blockItemMeta.addEnchant(Enchantment.DURABILITY, 127, true);
        blockItem.setItemMeta(blockItemMeta);
        
        PlayerInventory pi = sender_p.getInventory();
        
        Potion potion = new Potion(PotionType.INVISIBILITY, 1);
                                pi.addItem(potion.toItemStack(1));
                                sender_p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 0));
    
    
              inv.addItem(stickItem);
              inv.addItem(heldItem);
              inv.addItem(compassItem);
              inv.addItem(blockItem);
              
             sender_p.sendMessage(ChatColor.RED + "Administrating tools has been placed into your inventory");
        return true;
            }
 }