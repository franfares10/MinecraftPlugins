import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class commandKill implements CommandExecutor {
    private MainPlugin main;

    public commandKill(MainPlugin main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player killer = (Player)commandSender;
            FileConfiguration config = main.getConfig();
            int contadorKills = Integer.parseInt(config.getString("Players."+killer.getUniqueId()+".kills"));
            killer.sendMessage("Mataste: "+ ChatColor.RED+ String.valueOf(contadorKills) + ChatColor.WHITE+" Zombie");

        }
        return true;
    }
}
