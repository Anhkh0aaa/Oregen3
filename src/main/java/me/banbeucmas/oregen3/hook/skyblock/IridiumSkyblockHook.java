package me.banbeucmas.oregen3.hook.skyblock;

import com.iridium.iridiumskyblock.api.IridiumSkyblockAPI;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IridiumSkyblockHook implements SkyblockHook {

    private final IridiumSkyblockAPI iridium = IridiumSkyblockAPI.getInstance();

    @Override
    public double getIslandLevel(UUID uuid, Location loc) {
        Player player = Bukkit.getPlayer(uuid);

        assert player != null;
        if (iridium.getIslandByName(player.getName()).isPresent()) {
            return iridium.getIslandByName(player.getName()).get().getLevel();
        }
        return 0;

    }

    @Override
    public UUID getIslandOwner(Location loc) {
        return null;
    }

    @Override
    public UUID getIslandOwner(UUID uuid, World world) {
        Player player = Bukkit.getPlayer(uuid);
        assert player != null;
        if (iridium.getIslandByName(player.getName()).isPresent()) {
            return iridium.getIslandByName(player.getName()).get().getOwner().getUuid();
        }
        return null;

    }

    @Override
    public List<UUID> getMembers(UUID uuid, World world) {
        Player player = Bukkit.getPlayer(uuid);
        assert player != null;
        Optional<Island> userIsland = iridium.getIslandByName(player.getName());
        if (userIsland.isPresent()) {
            List<User> userList = userIsland.get().getMembers();
            List<UUID> listUuid = new ArrayList<>();
            for (User user : userList) {
                listUuid.add(user.getUuid());
            }
            return listUuid;
        }
        return null;
    }
}
