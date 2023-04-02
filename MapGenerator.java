//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    public int[][] map;
    public int bricksWidth;
    public int bricksHeight;

    public MapGenerator(int row, int col) {
        this.map = new int[row][col];
        int[][] var3 = this.map;
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int[] map1 = var3[var5];

            for(int j = 0; j < this.map[0].length; ++j) {
                map1[j] = 1;
            }
        }

        this.bricksWidth = 540 / col;
        this.bricksHeight = 150 / row;
    }

    public void draw(Graphics2D g) {
        for(int i = 0; i < this.map.length; ++i) {
            for(int j = 0; j < this.map[0].length; ++j) {
                if (this.map[i][j] > 0) {
                    g.setColor(Color.red);
                    g.fillRect(j * this.bricksWidth + 80, i * this.bricksHeight + 50, this.bricksWidth, this.bricksHeight);
                    g.setStroke(new BasicStroke(3.0F));
                    g.setColor(Color.black);
                    g.drawRect(j * this.bricksWidth + 80, i * this.bricksHeight + 50, this.bricksWidth, this.bricksHeight);
                }
            }
        }

    }

    public void setBricksValue(int value, int row, int col) {
        this.map[row][col] = value;
    }
}
