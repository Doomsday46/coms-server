package com.saviorru.comsserver.domain.schematictype;

import com.saviorru.comsserver.domain.schematictype.PlayerGrid;

import java.util.List;

public class OlympicGrid implements PlayerGrid {

    private List<List<Integer>> matrix;
    private final Integer SPACE = 0;
    private final Integer STEP = 10;
    private final Integer SPACE_CYRCLE = 7;
    private final Integer STEP_CYRCLE = 13;

    public OlympicGrid(List<List<Integer>> matrix) {
        if (matrix == null) throw new NullPointerException();
        this.matrix = matrix;
    }

    @Override
    public List<Integer> getNumbersByTour(Integer tour) {
        if (tour < 1 || tour - 1 > matrix.size()) throw new IndexOutOfBoundsException();
        return matrix.get(tour - 1);
    }

    @Override
    public Integer getTourCount() {
        return matrix.size();
    }

    @Override
    public String toString() {
        StringBuilder tree = new StringBuilder();
        double space = SPACE;
        int step = STEP;
        int element = 0;
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
                if (element == 0) printS(space, matrix.get(i).get(j), tree);
                else printS(step, matrix.get(i).get(j), tree);
                element++;
            }
            tree.append("\n");
            for (int z = 0; z < matrix.get(i).size() / 2; z++) {
                printRelation(space, i + 1, tree);
            }
            element = 0;
            space += SPACE_CYRCLE * (i + 1);
            step += STEP_CYRCLE * (i + 1);
            //tree.append("\n");
            tree.append("\n");
        }
        return tree.toString();
    }

    private void printRelation(double space, int round, StringBuilder stringBuilder) {
        int shift = (STEP / 2 - 1) * round;
        int spaceS = (STEP / 2 + 1) * round;
        double spaceLeft = space - 4;
        while (spaceLeft > 0) {
            spaceLeft--;
            stringBuilder.append(" ");
        }
        for (int i = 0; i < shift; i++)
            stringBuilder.append(" ");
        stringBuilder.append("\\");
        for (int i = 0; i < spaceS * round - space; i++)
            stringBuilder.append(" ");
        stringBuilder.append("/");
        for (int k = 0; k < space + 14; k++) {
            stringBuilder.append(" ");
        }
    }

    private void printS(double space, int Integer, StringBuilder stringBuilder) {
        while (space > 0) {
            stringBuilder.append(" ");
            space--;
        }
        if (Integer == 0) {
            stringBuilder.append("(" + " " + ")");
            return;
        }
        stringBuilder.append("(").append(Integer).append(")");
    }
}
