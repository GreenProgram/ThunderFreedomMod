package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.TotalFreedomMod;
import static me.totalfreedom.totalfreedommod.TotalFreedomMod.build;
import me.totalfreedom.totalfreedommod.config.ConfigEntry;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FLog;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.NON_OP, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about ThunderFreedomMod or reloads it", usage = "/<command> [reload]", aliases = "trfm")
public class Command_thunderfreedommod extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 1)
        {
            if (!args[0].equals("reload"))
            {
                return false;
            }

            if (!plugin.al.isAdmin(sender))
            {
                noPerms();
                return true;
            }

            plugin.config.load();
            plugin.services.stop();
            plugin.services.start();

            final String message = String.format("%s v%s reloaded.",
                    TotalFreedomMod.pluginName,
                    TotalFreedomMod.pluginVersion);

            msg(message);
            FLog.info(message);
            return true;
        }

        msg(ChatColor.translateAlternateColorCodes('&', "           &7&m----&9Thunder&8Freedom&9Mod&7&m----&r"));
        msg(ChatColor.translateAlternateColorCodes('&', "&9Thunder&8Freedom&9Mod&7 was made for &9Thunder&8Freedom&7!"));
        msg(ChatColor.translateAlternateColorCodes('&', "&9Thunder&8Freedom&9Mod&7 is currently in version: 1.0"));
        msg(ChatColor.translateAlternateColorCodes('&', "&9Thunder&8Freedom&9Mod&7 was made by &9heroguy42&7."));
        msg(ChatColor.translateAlternateColorCodes('&', "&7Note: &8TRFM was forked from &9TotalFreedomMod&7."));
        msg(ChatColor.translateAlternateColorCodes('&', "&7Running on: &9" + ConfigEntry.SERVER_NAME.getString() + "&7."));
        msg(ChatColor.translateAlternateColorCodes('&', "           &7&m----&9Thunder&8Freedom&9Mod&7&m----&r"));

        return true;
    }
}
