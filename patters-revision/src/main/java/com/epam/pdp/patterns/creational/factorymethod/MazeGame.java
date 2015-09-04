package com.epam.pdp.patterns.creational.factorymethod;

import java.util.LinkedList;
import java.util.List;

public abstract class MazeGame {
    private int maze_size;
    private List<Room> rooms = new LinkedList<>();

    public MazeGame(int maze_size) {
        this.maze_size = maze_size;
        for (int i = 0; i < maze_size; i++) {
            rooms.add(createRoom());
        }
    }

    protected abstract Room createRoom();

}
