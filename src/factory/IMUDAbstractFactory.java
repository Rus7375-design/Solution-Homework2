package factory;

import entities.IGameEntity;

public interface IMUDAbstractFactory {
    IGameEntity createRoom();
    IGameEntity createNPC();
}