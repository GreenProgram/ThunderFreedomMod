package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "A better reload than the built in one", usage = "/<command>", aliases = "trfrl, rl, rel, trfreload")
public class Command_reload extends FreedomCommand
{
    
    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length >= 2)
        {
            return false;
        }
        
        FUtil.bcastMsg(ChatColor.RED + sender.getName() + " - Reloading");
        
        server.reload();
        for (Player player : server.getOnlinePlayers())
        {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "okickall");
        }
        
        FUtil.bcastMsg(ChatColor.GREEN + sender.getName() + " - Reload Complete!");
        
        return true;
    }

}
