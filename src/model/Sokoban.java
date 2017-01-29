package model;

/**
 * Created by jvanroos on 25/01/2017.
 */
public class Sokoban {
    private Levels levels;
    private int numLevels;
    private int level;

    public Sokoban(String fileName){

        level = 0;
        levels = new Levels();
        numLevels = levels.setLevels(fileName);

        int w;
        int h;



    }

}
