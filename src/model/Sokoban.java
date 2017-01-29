package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import static model.Tiles.*;

public class Sokoban {
    private Levels levels;
    private int[][] cellStatus;	// array to copy the current level to
    private int w;
    private int h;
    private Tiles[][] activeLevel;
    private int numLevels;


    public Sokoban(String fileName){
        // Create new Levels to access inner class methods
        levels = new Levels();
        numLevels = levels.setLevels(fileName);

        createLevel(0);

    }

    public void createLevel(int level) {
        h = levels.getHeight(level);
        w = levels.getWidth(level);
        activeLevel = new Tiles[w][h];
        cellStatus = new int[w][h];

        for(int i=0; i <h; i++){
            for(int j = 0; j < w; j++){
                activeLevel[j][i] = levels.getTile(level, j, i);
                System.out.print(activeLevel[j][i] + ";");
                switch(activeLevel[j][i]){
                    case WALL:
                        cellStatus[j][i] = WALL.ordinal();
                        System.out.print("(" + cellStatus[j][i]+ ")");
                        break;

                    case BOX:
                        cellStatus[j][i] = BOX.ordinal();
                        System.out.print("(" + cellStatus[j][i]+ ")");
                        break;

                    case BOX_ON_GOAL:
                        cellStatus[j][i] = BOX_ON_GOAL.ordinal();
                        System.out.print("(" + cellStatus[j][i]+ ")");
                        break;

                    case GOAL:
                        cellStatus[j][i] = GOAL.ordinal();
                        System.out.print("(" + cellStatus[j][i]+ ")");
                        break;

                    case EMPTY:
                        cellStatus[j][i] = EMPTY.ordinal();
                        System.out.print("(" + cellStatus[j][i]+ ")");
                        break;

                    case PLAYER:
                        cellStatus[j][i] = PLAYER.ordinal();
                        System.out.print("(" + cellStatus[j][i]+ ")");
                        break;

                    case PLAYER_ON_GOAL:
                        cellStatus[j][i] = PLAYER_ON_GOAL.ordinal();
                        System.out.print("(" + cellStatus[j][i]+ ")");
                        break;

                }
            }
            System.out.println();
        }
    }

}
