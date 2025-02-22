package main;

import entities.Player;
import entities.Room;
import entities.Item;
import entities.NPC;
import controller.MUDController;

public class Main {
    public static void main(String[] args) {
        // Создание комнат
        Room forest = new Room("Зачарованный лес", "Волшебный лес, полный магии.");
        Room cave = new Room("Пещера", "Темная пещера с таинственным светом.");

        // Установка связей между комнатами
        forest.setExit("вперед", cave);
        cave.setExit("назад", forest);

        // Добавление предметов в комнату
        Item sword = new Item("меч", "Старый, но надежный меч.");
        forest.addItem(sword);

        // Создание игрока
        Player player = new Player("Игрок", forest);

        // Создание контроллера и запуск игры
        MUDController controller = new MUDController(player);
        controller.runGameLoop();
    }
}
