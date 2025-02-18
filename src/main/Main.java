package main;

import entities.Player;
import entities.Room;
import entities.Item;
import entities.NPC;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Создание комнат
        Room forest = new Room("Зачарованный лес", "Волшебный лес, полный магии.");
        Room cave = new Room("Пещера", "Темная пещера с таинственным светом.");

        // Установка связей
        forest.setExit("вперед", cave);
        cave.setExit("назад", forest);

        // Добавление предметов
        Item sword = new Item("меч", "Старый, но надежный меч.");
        forest.addItem(sword);

        // Создание игрока
        Player player = new Player("Игрок", forest);


        // Создание контроллера
        MUDController controller = new MUDController(player);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в MUD!");

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("выход")) {
                System.out.println("Выход из игры.");
                break;
            }
            controller.processCommand(command);
        }

        // Добавление боя
        NPC goblin = new NPC("Гоблин", "Злобный зелёный монстр", 30, 10);
        System.out.println("Вы встретили гоблина! Начинаем бой...");
        controller.fightNPC(goblin, player);

        scanner.close();
    }
}
