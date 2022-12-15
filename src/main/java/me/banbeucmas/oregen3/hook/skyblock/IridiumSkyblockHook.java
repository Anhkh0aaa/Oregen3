package me.banbeucmas.oregen3.hook.skyblock;

import com.iridium.iridiumskyblock.IridiumSkyblock;
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

    private final IridiumSkyblockAPI iridiumSkyblock = IridiumSkyblockAPI.getInstance();

    @Override
    public double getIslandLevel(UUID uuid, Location loc) {
        Player player = Bukkit.getPlayer(uuid);

        return iridiumSkyblock.getIslandByName(player.getName()).get().getLevel();
    }

    @Override
    public UUID getIslandOwner(Location loc) {
//        Island island = iridiumSkyblock.getIslandViaLocation(loc).get();
//        return island == null ? null : island.getOwner().getUuid();
        return null;
    }

    @Override
    public UUID getIslandOwner(UUID uuid, World world) {
        Player player = Bukkit.getPlayer(uuid);
        return iridiumSkyblock.getIslandByName(player.getName()).get().getOwner().getUuid();
    }

    @Override
    public List<UUID> getMembers(UUID uuid, World world) {
        Player player = Bukkit.getPlayer(uuid);
        Optional<Island> userIsland = iridiumSkyblock.getIslandByName(player.getName());
        List<User> userList = userIsland.get().getMembers();
        List<UUID> listUuid = new ArrayList<>();
        for (User user : userList) {
            listUuid.add(user.getUuid());
        }
        return listUuid;
    }
}
