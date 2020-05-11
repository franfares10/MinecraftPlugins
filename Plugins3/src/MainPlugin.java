import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MainPlugin extends JavaPlugin {
    public String rutaConfig;
    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage(Color.GREEN+"The Server is Enable");
        registerCommands();
        registerEvents();
        registerConfig();
    }
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(Color.RED+"The Server is Diseble");
    }

    public void registerConfig(){
        File config = new File("config.yml"); //el segundo parametro es el nombre del archivo
        rutaConfig = config.getPath();
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true); //si no existe pone lo pone por default
            saveConfig();
        }
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Listen(this),this);
        pm.registerEvents(new Kills(this),this);
    }
    public void registerCommands(){
        this.getCommand("tpplayer").setExecutor(new TpPlayer());
        this.getCommand("kills").setExecutor(new commandKill(this));
        this.getCommand("full").setExecutor(new commandFull());

    }
}
