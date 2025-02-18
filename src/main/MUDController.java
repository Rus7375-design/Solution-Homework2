package main;

import entities.Player;
import entities.NPC;
import entities.Room;
import entities.Item;
import java.util.Scanner;


public class MUDController {
    private Player player;

    public MUDController(Player player) {
        this.player = player;
    }

    public void processCommand(String command) {
        String[] parts = command.split(" ", 2);
        String action = parts[0].toLowerCase();
        String argument = (parts.length > 1) ? parts[1] : "";

        switch (action) {
            case "посмотри":
                player.getCurrentRoom().describe();
                break;
            case "двигаться":
                movePlayer(argument);
                break;
            case "подобрать":
                pickUpItem(argument);
                break;
            case "инвентарь":
                player.showInventory();
                break;
            default:
                System.out.println("Неизвестная команда.");
        }
    }

    private void movePlayer(String direction) {
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("Вы переместились в: " + nextRoom.getName());
            nextRoom.describe();
        } else {
            System.out.println("Вы не можете идти в этом направлении.");
        }
    }

    private void pickUpItem(String itemName) {
        Item item = player.getCurrentRoom().removeItem(itemName);
        if (item != null) {
            player.addItem(item);
            System.out.println("Вы подобрали: " + item.getName());
        } else {
            System.out.println("Такого предмета здесь нет.");
        }
    }
    public void fightNPC(NPC npc, Player player) {
        if (!npc.isAlive()) {
            System.out.println(npc.getName() + " уже побежден.");
            return;
        }

        System.out.println("Вы вступили в бой с " + npc.getName() + "!");
        while (player.getHealth() > 0 && npc.getHealth() > 0) {
            npc.attack(player);
            if (player.getHealth() <= 0) {
                System.out.println("Вы проиграли!");
                return;
            }

            System.out.println("Вы атакуете " + npc.getName() + "!");
            npc.takeDamage(15); // Урон от игрока

            HUD.display(player); // Обновляем статус игрока
        }

        System.out.println("Бой окончен.");
    }

}
