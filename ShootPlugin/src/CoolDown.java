import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class CoolDown {
    private Map<Player,Integer> cooldownTime = new HashMap<>();
    private Map<Player, BukkitRunnable> cooldownTask = new HashMap<>();

    public CoolDown(Map<Player, Integer> cooldownTime, Map<Player, BukkitRunnable> cooldownTask) {
        this.cooldownTime = cooldownTime;
        this.cooldownTask = cooldownTask;
    }

    public Map<Player, Integer> getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(Map<Player, Integer> cooldownTime) {
        this.cooldownTime = cooldownTime;
    }

    public Map<Player, BukkitRunnable> getCooldownTask() {
        return cooldownTask;
    }

    public void setCooldownTask(Map<Player, BukkitRunnable> cooldownTask) {
        this.cooldownTask = cooldownTask;
    }
}
