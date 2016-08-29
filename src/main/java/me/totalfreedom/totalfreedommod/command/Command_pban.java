package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.admin.Admin;
import me.totalfreedom.totalfreedommod.banning.Ban;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.TRFDEVELOPER, source = SourceType.BOTH)
@CommandParameters(description = "Permanently ban a user!", usage = "/<command> <player>")
public class Command_pban extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length < 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            msg(FreedomCommand.PLAYER_NOT_FOUND, ChatColor.RED);
            return true;
        }
        
         Admin admin = getAdmin(player);
        if (admin != null)
        {
            FUtil.adminAction(sender.getName(), "Removing " + player.getName() + " from the superadmin list", true);
            plugin.al.removeAdmin(admin);
        }

        String reason;
        {
            reason = "You have been permanently banned from this server.";
        }

        // strike with lightning effect:
        final Location targetPos = player.getLocation();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                targetPos.getWorld().strikeLightning(strike_pos);
            }
        }

        FUtil.adminAction(sender.getName(), "Adding " + player.getName() + " to the permban list", true);
        plugin.bm.addBan(Ban.forPlayer(player, sender, FUtil.parseDateOffset("55439y"), reason));

        player.kickPlayer(ChatColor.RED + "You have been permbanned from the server. Please make an appeal at thunderfreedom.boards.net");
        
        FUtil.bcastMsg(ChatColor.GRAY + "[CONSOLE]" + ChatColor.WHITE + "<" + ChatColor.RED + "markbyron" + ChatColor.WHITE + "> Enjoy your permban, jackass!");

        return true;
    }
}