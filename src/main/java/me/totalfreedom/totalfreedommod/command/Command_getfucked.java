package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Does multiple punishments at once", usage = "/<command> <player> [reason]")
public class Command_getfucked extends FreedomCommand
{
    
    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {   
        if (args.length < 0)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        String reason = null;
        if (args.length > 1)
        {
            reason = StringUtils.join(args, " ", 1, args.length);
        }

        if (player == null)
        {
            msg(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }
        
        FUtil.bcastMsg(ChatColor.RED + sender.getName() + "- Fucking " + player.getName());
        
        Bukkit.dispatchCommand(sender, "smite " + player.getName() + reason);
        Bukkit.dispatchCommand(sender, "stfu " + player.getName());
        Bukkit.dispatchCommand(sender, "blockcmd " + player.getName());
        
        return true;
    }

}

