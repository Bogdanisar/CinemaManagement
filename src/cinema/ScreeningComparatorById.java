package cinema;

import javafx.scene.Scene;

import java.util.Comparator;

public class ScreeningComparatorById implements Comparator<Screening> {
    public int compare(Screening s1, Screening s2) {
        if (s1.id == s2.id) {
            return 0;
        }

        if (s1.id < s2.id) {
            return -1;
        }

        return 1;
    }
}
