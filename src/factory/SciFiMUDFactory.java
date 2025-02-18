package factory;

import entities.IGameEntity;
import entities.Room;
import entities.NPC;

public class SciFiMUDFactory implements IMUDAbstractFactory {
    @Override
    public IGameEntity createRoom() {
        return new Room("Space Station", "A high-tech space station orbiting Earth.");
    }

    @Override
    public IGameEntity createNPC() {
        return new NPC("Android", "A highly intelligent AI-driven android.");
    }
}