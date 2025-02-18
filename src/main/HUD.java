package main;

import entities.Player;

public class HUD {
    public static void display(Player player) {
        System.out.println("===== СТАТУС ИГРОКА =====");
        System.out.println("Имя: " + player.getName());
        System.out.println("Здоровье: " + player.getHealth());
        System.out.println("=========================");
    }
}
