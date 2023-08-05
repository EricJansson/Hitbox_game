package GameFiles;

import GameFiles.GameWindow;

public class MainGame {
    public static void main(String[] args) {
        // System.out.println("Hello world!");
        new GameWindow();
    }
}

    /* TODO
            Make another function that renders the airbournes above the entities
            Make 2 more backgrounds that cycle to simulate animation in the background
            Add sprite animations to Entities
            Make enemies spawn on certain tiles.
                Generate and spawn from a list on entities with certain coordinates attached
                Create enemy class with those coordinates
            Fix collision, bump into obstacles and walls/water
            Fix hitbox going out of bounds for one frame
            Tidy up the workspace - Get packages going
    */