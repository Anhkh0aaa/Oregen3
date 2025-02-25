package me.banbeucmas.oregen3.editor;

import me.banbeucmas.oregen3.data.Generator;
import me.banbeucmas.oregen3.editor.type.EditType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Editor {

    public static HashMap<UUID, EditType> chanceSet = new HashMap<>();
    public static HashMap<UUID, EditType> editSet = new HashMap<>();
    public static HashMap<UUID, Object> optionSet = new HashMap<>();

    public static void markChanceSet(Player player, Generator generator, String material) {
        chanceSet.put(player.getUniqueId(), EditType.SET_CHANCE);
        optionSet.put(player.getUniqueId(), new Object() {
            HashMap<String, Object> parse() {
                HashMap<String, Object> options = new HashMap<>();
                options.put("Generator", generator.getId());
                options.put("Material", material);
                return options;
            }
        }.parse());
    }

    public static void markEditType(Player player, Generator generator, EditType type) {
        editSet.put(player.getUniqueId(), type);
        optionSet.put(player.getUniqueId(), new Object() {
            HashMap<String, Object> parse() {
                HashMap<String, Object> options = new HashMap<>();
                options.put("Generator", generator.getId());
                return options;
            }
        }.parse());
    }

    public static void clearPlayerMarking(Player player) {
        chanceSet.remove(player.getUniqueId());
        editSet.remove(player.getUniqueId());
    }
}
