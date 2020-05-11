import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (strings.length == 1) {
                if (Bukkit.getPlayer(strings[0]) != null) {
                    Player player1 = Bukkit.getPlayer(strings[0]);
                    Location l = player1.getLocation();
                    player.teleport(l);
                    player.sendMessage(ChatColor.GREEN+"Teleported to: "+player1.getName());
                } else {
                    player.sendMessage(ChatColor.RED+"The Player isnt connected");
                }

            } else if (strings.length == 0) {
                player.sendMessage(ChatColor.WHITE + "Please itroduce /tpplayer" + ChatColor.RED + " + <Player`s name");
            }
        }
        return true;
    }
}
