package entities;

import java.util.Random;  // Добавляем импорт для Random

public class NPC implements IGameEntity {
    private String name;
    private String description;
    private int health;
    private int attackPower;

    // Конструктор с 2 параметрами (имя и описание)
    public NPC(String name, String description) {
        this.name = name;
        this.description = description;
        this.health = 100;  // Значение по умолчанию
        this.attackPower = 10;  // Значение по умолчанию
    }

    // Конструктор с 4 параметрами
    public NPC(String name, String description, int health, int attackPower) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.attackPower = attackPower;
    }

    // Остальные методы
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println(name + " побежден!");
        } else {
            System.out.println(name + " получил " + damage + " урона. Осталось здоровья: " + health);
        }
    }

    public void attack(Player player) {
        Random rand = new Random();  // Теперь Random доступен
        int damage = rand.nextInt(attackPower) + 1;
        System.out.println(name + " атакует " + player.getName() + " на " + damage + " урона!");
        player.takeDamage(damage);
    }

    @Override
    public void describe() {
        System.out.println("NPC: " + name + " - " + description + " (Здоровье: " + health + ")");
    }
}
