package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(
        description = "Clear your chat.",
        usage = "/<command>",
        aliases = "cmc")
public class Command_clearmychat extends FreedomCommand
{

    @Override
    protected boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        for (int i = 0;  i <= 98; i++)
        {
            sender.sendMessage("");
        }
        sender.sendMessage(ChatColor.RED + "ThunderFreedom - Clearing your chat");
        return true;
    }
    
}
