import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class commandFull implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) Bukkit.getPlayer(commandSender.getName());
            while(player.getInventory().firstEmpty()!=-1){
                player.getInventory().addItem(new ItemStack(Material.DIAMOND));
            }
            return true;
        }else {
            return true;
        }
    }
}
