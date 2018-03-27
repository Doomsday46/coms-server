package com.saviorru.comsserver.domain.schematictype;

import com.saviorru.comsserver.domain.schematictype.PlayerGrid;

import java.util.List;

public class RoundGrid implements PlayerGrid {

    private List<List<Integer>> matrix;


    public RoundGrid(List<List<Integer>> matrix){
        if (matrix == null) throw new NullPointerException();
        this.matrix = matrix;
    }

    @Override
    public List<Integer> getNumbersByTour(Integer tour){
        if (tour == null) throw new NullPointerException();
        if (tour > matrix.size()-1 || tour < 0) throw new IndexOutOfBoundsException();
        return matrix.get(tour);
    }

    @Override
    public Integer getTourCount() {
        return matrix.size();
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(" x |");
        for (int i =1; i < matrix.get(0).size(); i++) {
            Integer digit = matrix.get(0).get(i);
            result.append(resizeForContent(digit));
            result.append('|');
        }
        result.append(" \n");
        for (int i =1; i<matrix.size(); i++)
        {
            for (int k =0; k< result.toString().split("\n")[0].length(); k++)
                result.append('-');
            result.append('\n');
            Integer digit = matrix.get(0).get(i);
            result.append(resizeForContent(digit));
            result.append('|');
            for (int j = 1; j < matrix.get(i).size(); j++)
            {
                if (matrix.get(i).get(j) == -1)
                {
                    result.append(" x ");
                    result.append('|');
                    continue;
                }
                switch (matrix.get(i).get(j))
                {
                    case 0: result.append("   "); break;
                    case 1: result.append(" * "); break;
                    default: result.append(matrix.get(i).get(j)); break;
                }
                result.append('|');
            }
            result.append('\n');
        }
        return result.toString();
    }

    private String resizeForContent(Integer digit)
    {
        StringBuilder result = new StringBuilder();
        Integer spacesCount = 0;
        if (digit < 100)
            spacesCount +=1;
        if (digit < 10)
            spacesCount +=1;
        switch (spacesCount)
        {
            case 0: {
                result.append(digit);
            } break;
            case 1: {
                result.append(digit);
                result.append(' ');
            } break;
            case 2: {
                result.append(' ');
                result.append(digit);
                result.append(' ');
            } break;
        }
        return result.toString();
    }
}
