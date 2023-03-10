package les08.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ether
 */
public class BestArrange {

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program p1, Program p2) {
            return p1.end - p2.end;
        }
    }

    public static int bestArrange(Program[] programs, int timePoint) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;

        // 依次遍历所有的会议
        for (int i = 0; i < programs.length; i++) {
            if (timePoint <= programs[i].start) {
                result++;
                timePoint = programs[i].end;
            }
        }

        return result;
    }
}
