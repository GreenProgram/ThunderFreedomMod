package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Helps with alien issues!", usage = "/<command>", aliases = "[aliens]")
public class Command_alien extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
        if (args.length > 1)
        {
            return false;
        }
        
        Player player = (Player) sender;
        
        sender.sendMessage(cc("&a            ______              ______\n" +
"&a           /___   \\___\\ || /___/   ___\\\n" +
"&a          //\\]/\\ ___  \\\\||//  ___ /\\[/\\\\\n" +
"&a          \\\\/[\\//  _)   \\/   (_  \\\\/]\\//\n" +
"&a           \\___/ _/   o    o   \\_ \\___/\n" +
"&a               _/                \\_\n" +
"&a              //'VvvvvvvvvvvvvvvV'\\\\\n" +
"&a             ( \\.'^^^^^^^^^^^^^^'./ )\n" +
"&a              \\____   . .. .   ____/\n" +
"&a   ________        \\ . .''. . /        ________\n" +
"&a  /______  \\________)________(________/ _______\\\n" +
"&a /|       \\ \\                        / /       |\\\n" +
"&a(\\|____   / /                        \\ \\   ____|/)\n" +
"&a(\\_____>- \\/                          \\/ -<_____/)\n" +
"&a(\\_____>-  |                          |  -<_____/)\n" +
"&a(\\_____>-   .. ..   .'''. .''.   .'''.   -<_____/)\n" +
"&a \\_____>- .'  :  '. :   : : .'   :...:   -<_____/\n" +
"&a  |       :   :   : '...' :''.   '.....        |\n" +
"&a  |                       :   '.       '....   |\n" +
"&a  |                                            |\n" +
"&a  |            .::.  .....          ::.        |\n" +
"&a  |          .:' ':. ::''::         '::..::::: |\n" +
"&a  |        .::::..:: ::  ::     ..:::::::'     |\n" +
"&a  |      .:'    ''': ::::::    :::''  ::'      |\n" +
"&a  |      ::        : :'  ':.          ::       |\n" +
"&a  |                  :    '':         ::       |\n" +
"&a  |                                            |\n" +
"&a  |                      /\\  _______           |\n" +
"&a  |     __  |  /        /          /           |\n" +
"&a  |    /  \\ |  \\__     |___       /    __      |\n" +
"&a  |    |__/ |   \\      |         /    \\  /     |\n" +
"&a  |    |    |___ \\____ \\        /      \\/ /    |\n" +
"&a  |    |                \\__    /______  \\/     |\n" +
"&a  |____________________________________________|\n" +
"&a           /       )          (       \\\n" +
"&a          /       /            \\       \\\n" +
"&a         / / / /\\ \\            / /\\ \\ \\ \\\n" +
"&a        ( ( ( ( (  )          (  ) ) ) ) )\n" +
"&a        'v'v'v'v'(_)          (_)'v'v'v'v'\n" +
"&a                  \\)          (/"));
        
        return true;
    }
    
    public String cc(String msg)
    {
	return ChatColor.translateAlternateColorCodes('&', msg);
    }
    
}