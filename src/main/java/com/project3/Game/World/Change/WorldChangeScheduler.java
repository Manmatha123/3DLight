package com.project3.Game.World.Change;

import com.project3.Game.World.World;

import java.util.Stack;

public class WorldChangeScheduler {
    private Stack<WorldChange> changeStack;

    public WorldChangeScheduler() {
        this.changeStack = new Stack<>();
    }

    public void addChange(WorldChange change) {
        changeStack.push(change);
    }

    public void makeChanges(World world) {
        while(changeStack.size() != 0) {
            System.out.println("Making World Change");
            WorldChange change = changeStack.pop();
            change.makeChange(world);
        }
    }

}
