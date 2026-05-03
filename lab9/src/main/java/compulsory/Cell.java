package compulsory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Cell {
    private final int row, col;
    private final boolean wall;
    private final boolean exit;


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cell c)) return false;
        return row == c.row && col == c.col;
    }

}
