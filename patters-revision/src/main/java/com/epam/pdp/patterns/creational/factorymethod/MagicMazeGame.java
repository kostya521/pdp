package com.epam.pdp.patterns.creational.factorymethod;

public class MagicMazeGame extends MazeGame {
    public MagicMazeGame(int maze_size) {
        super(maze_size);
    }

    @Override
    protected Room createRoom() {
        return new MagicRoom();
    }

}
