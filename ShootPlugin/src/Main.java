import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    private PluginDescriptionFile pdf = getDescription();
    private String version = pdf.getVersion();

    public void onEnable(){
        addListeners();
        addCommands();
    }

    public void onDisable(){}

    public void addListeners(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ShootListener(this),this);
    }

    public void addCommands(){
        this.getCommand("givemeanarrow").setExecutor(new GiveMeAnArrowCommand());
    }

}
