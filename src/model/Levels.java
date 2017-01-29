package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jvanroos on 26/01/2017.
 */
public class Levels {
    private List<Level> levels;

    public int setLevels(String FileName){
        levels = new ArrayList<Level>();

        Level level = new Level();
        int numLevel = 0;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(FileName));
            String row = br.readLine();

            while (row !=null){
                if(row.trim().equals("")){
                    row = br.readLine();
                    continue;
                }

                if(row.startsWith(";")) {
                    if(level.getHeight() > 0){  // als er al lijnen zijn dan zijn we aan het einde van huidige level
                        levels.add(level);      // en voegen we de level toe aan de levels lijst
                        level = new Level();    // en maken een nieuwe level aan...
                    }
                    numLevel = Integer.parseInt(row.substring(1).trim());   // levelnummer opslaan
                } else {
                    level.addRow(row);
                    System.out.println(row);
                }
                row = br.readLine();
            }
            levels.add(level);
        } catch (IOException e) {
            System.out.println("Could not read file!");
        }
        return levels.size();
    }

    public int getHeight(int level) {
        return levels.get(level).getHeight();
    }

    public int getWidth(int level) {
        return levels.get(level).getWidth();
    }

    public int getLevelNumber(int level){
        return levels.get(level).getLevelNumber();
    }

    public Tiles getTile(int level, int x, int y){
        return levels.get(level).getCellContent(x, y);
    }

    private Tiles charToTile(char c){
        if(c == '#') return Tiles.WALL;
        if(c == '$') return Tiles.BOX;
        if(c == '.') return Tiles.GOAL;
        if(c == '+') return Tiles.BOX_ON_GOAL;
        if(c == '-') return Tiles.PLAYER_ON_GOAL;
        return Tiles.EMPTY;
    }

    private class Level {
        private int width = 0;
        private int levelNumber;
        private List<String> rows = new ArrayList<>();
        // private List<String> moves = new ArrayList<>();  // moves of the Player ... move to Player class ?
        // private int pushes; // moves of the boxes ... move to Box class ?

        public void addRow(String row){
            rows.add(row);
            if(row.length() > width) {        // max width of row is width of grid
                width = row.length();
            }
        }

        public void setLevelNumber(int levelNumber){
            this.levelNumber = levelNumber;
        }

        public int getWidth(){
            return width;
        }

        public int getHeight(){
            return rows.size();
        }

        public int getLevelNumber(){
            return levelNumber;
        }

        public Tiles getCellContent(int x, int y){
            String row = rows.get(y);
            if(x >= row.length()){      // opvulling van cellen die buiten de WALLs vallen
                return Tiles.EMPTY;
            }else {
                return charToTile(row.charAt(x));
            }
        }
    }   // einde van class Level

}
