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
        System.out.println("Welcome to the MUD game! Type 'help' for a list of commands.");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            handleInput(input);
        }

        System.out.println("Exiting game...");
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
                System.out.println("Unknown command.");
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
            System.out.println("You moved " + direction + ".");
            lookAround();
        } else {
            System.out.println("You can't go that way!");
        }
    }

    private void pickUp(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            player.addItem(item);
            currentRoom.removeItem(item);
            System.out.println("You picked up " + itemName + ".");
        } else {
            System.out.println("No item named " + itemName + " here!");
        }
    }

    private void checkInventory() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("You are carrying:");
            player.getInventory().forEach(item -> System.out.println("- " + item.getName()));
        }
    }

    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("look - Describe the current room");
        System.out.println("move <direction> - Move in a specified direction");
        System.out.println("pick up <itemName> - Pick up an item from the ground");
        System.out.println("inventory - List the items you are carrying");
        System.out.println("help - Show this help menu");
        System.out.println("quit/exit - End the game");
    }
}
