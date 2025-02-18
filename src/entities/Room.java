package entities;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room implements IGameEntity {
    private String name;
    private String description;
    private Map<String, Room> exits;
    private List<Item> items;

    public String getName() {
        return name;
    }


    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    @Override
    public void describe() {
        System.out.println("Вы находитесь в: " + name);
        System.out.println(description);
        if (!items.isEmpty()) {
            System.out.println("Здесь есть предметы:");
            for (Item item : items) {
                System.out.println("- " + item.getName());
            }
        }
        if (!exits.isEmpty()) {
            System.out.print("Выходы: ");
            for (String dir : exits.keySet()) {
                System.out.print(dir + " ");
            }
            System.out.println();
        }
    }
}
