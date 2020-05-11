import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;


public class Listen implements Listener {

    private MainPlugin plugin;
    public Listen(MainPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void entrar(PlayerJoinEvent event){
        Player player = event.getPlayer();
        FileConfiguration configuration = plugin.getConfig();
        String path = "Config.mensaje-bienvenida";
        if(configuration.getString(path).equals("true")){
            String text = "Config.mensaje-bienvenida-texto";
            List<String> mensaje = configuration.getStringList(text);
            for(int i = 0; i < mensaje.size(); i++){
                if( i == 1){
                    player.sendMessage(configuration.getString(text).replaceAll("%player%", ChatColor.BLUE+player.getName()));
                }
                else{
                    player.sendMessage(mensaje.get(i));
                }
            }

        }
    }
}
