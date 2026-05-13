package homework;

import compulsory.Cell;
import java.util.Optional;

public class SharedMemory {
    private Cell bunnyPosition = null;

    public synchronized void reportBunny(Cell position) {
        bunnyPosition = position;
    }

    public synchronized Optional<Cell> getBunnyPosition() {
        return Optional.ofNullable(bunnyPosition);
    }
}
