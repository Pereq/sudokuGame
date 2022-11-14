package pl.lodz.p.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PartOfTheBoard {
    protected List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
    
    public boolean verify() {
        Set<Integer> fieldsToVerify = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            fieldsToVerify.add(fields.get(i).getFieldValue());
        }
        return fieldsToVerify.size() == 9;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("fields", fields).toString();
    }
}
