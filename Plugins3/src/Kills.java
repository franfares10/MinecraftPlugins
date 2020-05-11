import net.minecraft.server.v1_15_R1.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Kills implements Listener {
    private MainPlugin main;

    public Kills(MainPlugin main) {
        this.main = main;
    }

    @EventHandler
    public void matarZombie(EntityDeathEvent event){
        Player killer = event.getEntity().getKiller();
        EntityType entidad = event.getEntityType(); //nos retorna la clase de la entidad asesinada
        if(killer !=null && killer.getType().equals(EntityType.PLAYER) && entidad.equals(EntityType.ZOMBIE)){
            FileConfiguration config = main.getConfig();
            config.set("Players."+killer.getUniqueId()+".name",killer.getName());
            config.set("Players."+killer.getUniqueId()+".kills",1);
            if(config.contains("Players."+killer.getUniqueId()+".kills")){
                int contadorKills = Integer.parseInt(config.getString("Players."+killer.getUniqueId()+".kills"));
                config.set("Players."+killer.getUniqueId()+".kills",contadorKills++);
                main.saveConfig();
                return;
            }else{
                config.set("Players."+killer.getUniqueId()+".kills",1);
                main.saveConfig();
                return;
            }



        }
    }

    @EventHandler
    public void obtenerGemas(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        EntityType entidad = event.getEntityType(); //nos retorna la clase de la entidad asesinada
        if (killer != null && killer.getType().equals(EntityType.PLAYER) && entidad.equals(EntityType.PLAYER)) {
            ItemStack itemStack = new ItemStack(Material.EMERALD, 1);
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + "PERONIST EMERALD");
            List<String> lore = new ArrayList<>();

            lore.add("                                                                           ");
            lore.add("");
            lore.add(ChatColor.BLUE + "You´ve earned the Peronist Emerald");
            lore.add(ChatColor.BLUE + "What a great Peronist you are, you deserve this emerald");
            lore.add(ChatColor.GREEN+"Price: 5 gamba");
            lore.add("");
            lore.add("                                                                           ");

            meta.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
            meta.setLore(lore);
            itemStack.setItemMeta(meta);

            if (killer.getInventory().firstEmpty() != -1) {
                killer.getInventory().addItem(itemStack);
            } else {
                killer.sendMessage(ChatColor.RED + "Your inventory is full so you cant have the Peronist Emerald");
            }

        }
    }
}
