package com.example.mud.controller;

import java.util.Scanner;
import com.example.mud.player.Player;
import com.example.mud.room.Room;
import com.example.mud.item.Item;

public class MUDController {
    private final Player player;
    private boolean running;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
    }

    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в MUD игру! Введите 'help' для списка команд.");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            handleInput(input);
        }

        System.out.println("Выход из игры...");
        scanner.close();
    }

    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                pickUp(argument);
                break;
            case "inventory":
                checkInventory();
                break;
            case "help":
                showHelp();
                break;
            case "quit":
            case "exit":
                running = false;
                break;
            default:
                System.out.println("Неизвестная команда.");
                break;
        }
    }

    private void lookAround() {
        Room currentRoom = player.getCurrentRoom();
        System.out.println(currentRoom.getDescription());
    }

    private void move(String direction) {
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("Вы переместились " + direction + ".");
            lookAround();
        } else {
            System.out.println("Вы не можете идти в этом направлении!");
        }
    }

    private void pickUp(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            player.addItem(item);
            currentRoom.removeItem(item);
            System.out.println("Вы подняли " + itemName + ".");
        } else {
            System.out.println("Здесь нет предмета с названием " + itemName + "!");
        }
    }

    private void checkInventory() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Ваш инвентарь пуст.");
        } else {
            System.out.println("Вы несете:");
            player.getInventory().forEach(item -> System.out.println("- " + item.getName()));
        }
    }

    private void showHelp() {
        System.out.println("Доступные команды:");
        System.out.println("look - Осмотреть текущую комнату");
        System.out.println("move <направление> - Переместиться в указанном направлении");
        System.out.println("pick up <название предмета> - Поднять предмет с земли");
        System.out.println("inventory - Показать предметы в инвентаре");
        System.out.println("help - Показать это меню помощи");
        System.out.println("quit/exit - Выйти из игры");
    }
}