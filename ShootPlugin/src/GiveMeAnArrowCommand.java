import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveMeAnArrowCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            ItemStack item = new ItemStack(Material.ARROW);
            if(player.getInventory().firstEmpty()!=-1){
                player.getInventory().addItem(item);
            }
            else{
                player.sendMessage(ChatColor.DARK_RED+ "Your inventory is full, Sorry");
            }
        }
        return true;
    }
}
