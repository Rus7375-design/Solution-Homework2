package entities;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private Room currentRoom;
    private List<Item> inventory;  // Список для хранения предметов

    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.health = 100; // Начальное здоровье
        this.inventory = new ArrayList<>();  // Инициализируем инвентарь как пустой список
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println(name + " погиб!");
        } else {
            System.out.println(name + " получил " + damage + " урона. Осталось здоровья: " + health);
        }
    }

    public void heal(int amount) {
        health += amount;
        System.out.println(name + " восстановил " + amount + " здоровья. Теперь у него " + health + " HP.");
    }

    // Метод для добавления предмета в инвентарь
    public void addItem(Item item) {
        inventory.add(item);
    }

    // Метод для отображения инвентаря
    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Инвентарь пуст.");
        } else {
            System.out.println("Инвентарь:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }
}
