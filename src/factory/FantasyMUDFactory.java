package factory;

import entities.IGameEntity;
import entities.Room;
import entities.NPC;

public class FantasyMUDFactory implements IMUDAbstractFactory {
    @Override
    public IGameEntity createRoom() {
        return new Room("Enchanted Forest", "A mystical forest full of magic.");
    }

    @Override
    public IGameEntity createNPC() {
        return new NPC("Elf", "A wise elf with ancient knowledge.");
    }
}