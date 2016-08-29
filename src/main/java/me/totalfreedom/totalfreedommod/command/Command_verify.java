package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.IMPOSTOR, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Imposter? Verify!", usage = "/<command>")
public class Command_verify extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        Player player = (Player) sender;
        
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAre you an imposter that needs to verify?"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bWell, It's simple."));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bJust go to &chttp://thunderfreedom.boards.net/"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bOnce you are at the site, login to your account if you are not logged in already."));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bNow, scroll down till you see 'Shoutbox'"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bThen, type in the shoutbox, 'Verify.'"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bNow, go back to Minecraft and say, 'Verified'"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bNow all you have to do is wait for a Telnet+ to add you back."));
        return true;
    }
    
}
