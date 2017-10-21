import java.util.Arrays;
import pieces.*;
public class Match {
    private static void swap(NutsAndBolts nb, int i, int j) {
        Bolt tmpBolts = nb.bolts[i];
        nb.bolts[i] = nb.bolts[j];
        nb.bolts[j] = tmpBolts;

    }

    public static void match(NutsAndBolts nutsAndBolts) {
        int n = nutsAndBolts.N();
        for(int i = 0; i < n; ++i) {
            Nut obj = nutsAndBolts.nuts[i];
            for(int j = i; j < n; ++j) {
                int compare = nutsAndBolts.nuts[i].compareTo(nutsAndBolts.bolts[j]);
                if (compare == 0) {
                    swap(nutsAndBolts, i, j);
                    break;

                }
            }
        }

    }


}
