
import net.minecraft.server.v1_15_R1.Explosion;
import net.minecraft.server.v1_15_R1.ItemFireworks;
import net.minecraft.server.v1_15_R1.ParticleParam;
import net.minecraft.server.v1_15_R1.Particles;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.HashMap;


public class ShootListener implements Listener {
    private Main plugin;
    private CoolDown coolDown;

    public ShootListener(Main plugin) {
        this.plugin = plugin;
        this.coolDown = new CoolDown(new HashMap<>(),new HashMap<>());
    }

    @EventHandler
    public void triggerEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        if ( action.equals(Action.RIGHT_CLICK_AIR)){
            ItemStack item = new ItemStack(Material.ARROW,1);
            if( player.getItemInHand().equals(item)){
                if(!coolDown.getCooldownTime().containsKey(player)) {
                    shoot(player);
                    cooldown(player);
                }
                else{
                    player.sendMessage(ChatColor.RED+"You have to wait"+coolDown.getCooldownTime().get(player) + "seconds");
                }
            }
        }
    }

    public void shoot(Player player){
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST,10,(float)1.8);
        Location l = player.getLocation();
        l.setY(l.getY()+1.1);
        Vector vector = l.getDirection().multiply(0.35);
        for(int i = 0; i < 40 ; i++){
            Location actual = l.add(vector);
            player.getWorld().spawnParticle(Particle.FLAME,actual,0,0,0,0,1);
            if( actual.getBlock()!=null){
                Block block = actual.getBlock();
                if(block.getType().equals(Material.AIR)){
                    Collection<Entity> entities = player.getWorld().getNearbyEntities(actual,0.5,0.5,0.5);
                    if( entities.isEmpty()){
                        player.getWorld().playEffect(actual, Effect.FIREWORK_SHOOT,5);
                    }
                    else{
                        boolean cancel = false;
                        for( Entity e : entities){
                            if( e instanceof Player){
                                if(player.getName().equals(e.getName())){
                                    continue;
                                }
                            }
                            if( e instanceof Monster){
                                //Damage
                                ((Monster) e).setNoDamageTicks(0);
                                ((Monster) e).damage(5);
                            }

                            player.getWorld().playEffect(actual, Effect.FIREWORK_SHOOT,5);
                            cancel = true;
                        }
                        if(cancel){
                            break;
                        }
                    }
                }else{
                    break;
                }
            }
        }
    }

    public void cooldown (Player player){
        coolDown.getCooldownTime().put(player,2);
        coolDown.getCooldownTask().put(player, new BukkitRunnable() {
            @Override
            public void run() {
                coolDown.getCooldownTime().put(player,coolDown.getCooldownTime().get(player) - 1);
                if(coolDown.getCooldownTime().get(player) == 0 ){
                    coolDown.getCooldownTime().remove(player);
                    coolDown.getCooldownTask().remove(player);
                    cancel();
                }
            }
        });
        coolDown.getCooldownTask().get(player).runTaskTimer(plugin,20,20);
    }
}
