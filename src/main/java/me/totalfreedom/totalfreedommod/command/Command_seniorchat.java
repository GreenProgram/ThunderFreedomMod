package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SENIOR_ADMIN, source = SourceType.BOTH)
@CommandParameters(
        description = "SeniorChat - Talk privately with other seniors. Using <command> itself will toggle SeniorChat on and off for all messages.",
        usage = "/<command> [message...]",
        aliases = "p,sc")
public class Command_seniorchat extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (senderIsConsole)
            {
                msg("Only in-game players can toggle SeniorChat.");
                return true;
            }

            FPlayer userinfo = plugin.pl.getPlayer(playerSender);
            userinfo.setSeniorChat(!userinfo.inSeniorChat());
            msg("Toggled Senior Chat " + (userinfo.inSeniorChat() ? "on" : "off") + ".");
            FPlayer playerdata = plugin.pl.getPlayer(playerSender);
            playerdata.setAdminChat(false);
        }
        else
        {
            if(StringUtils.join(args, " ").contains("&k") || StringUtils.join(args, " ").contains("&0") || StringUtils.join(args, " ").contains("&m"))
            {
                msg("You may not use that color as it is against the policy!");
                return true;
            }
        
        plugin.cm.seniorChat(sender, FUtil.colorize(StringUtils.join(args, " ")));
        }
        return true;
    }
}


