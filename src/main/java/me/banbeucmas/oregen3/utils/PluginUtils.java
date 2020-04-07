package me.banbeucmas.oregen3.utils;

import com.cryptomorin.xseries.XSound;
import me.banbeucmas.oregen3.Oregen3;
import me.banbeucmas.oregen3.data.DataManager;
import me.banbeucmas.oregen3.data.MaterialChooser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;

import java.util.Optional;
import java.util.UUID;

public class PluginUtils {
    public static OfflinePlayer getOwner(final Location loc) {
        if (!Oregen3.getHook().isOnIsland(loc)) {
            return null;
        }

        final UUID uuid = Oregen3.getHook().getIslandOwner(loc);
        if (uuid == null) {
            return null;
        }

        if (Oregen3.DEBUG) {
            System.out.println("UUID: " + uuid);
        }

        return Bukkit.getServer().getOfflinePlayer(uuid);
    }

    public static MaterialChooser getChooser(final Location loc) {
        final Oregen3 plugin = Oregen3.getPlugin();
        MaterialChooser mc = DataManager.getChoosers().get(plugin.getConfig().getString("defaultGenerator"));
        if (plugin.hasDependency()) {
            final OfflinePlayer p = getOwner(loc);
            if (p == null) {
                return mc;
            }
            for (final MaterialChooser chooser : DataManager.getChoosers().values()) {
                //TODO: Support island-only world?
                if (plugin.getPerm().playerHas(null, p, chooser.getPermission())
                        && chooser.getPriority() >= mc.getPriority()
                        && getLevel(p.getUniqueId(), loc) >= chooser.getLevel()) {
                    mc = chooser;
                }
            }
        }
        return mc;
    }

    private static long getLevel(final UUID id, final Location loc) {
        return Oregen3.getHook().getIslandLevel(id, loc);
    }

    public static Sound getCobbleSound() {
        final Optional<XSound> sound = XSound.matchXSound(Oregen3.getPlugin().getConfig().getString("sound.created"));
        return sound.map(XSound::parseSound).orElse(null);
    }
}
