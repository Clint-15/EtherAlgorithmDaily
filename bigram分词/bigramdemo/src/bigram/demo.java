package bigram;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ether
 */
public class demo {
    public static void main(String[] args) {
        class solution {
            public String[] findOcurrences(String text, String first, String second) {
                String[] words = text.split(" ");
                List<String> list = new ArrayList<>();
                for (int i = 2; i < words.length; i++) {
                    if (words[i - 2].equals(first) && words[i - 1].equals(second)) {
                        list.add(words[i]);
                    }
                }
                int size = list.size();
                String[] ret = new String[size];
                for (int i = 0; i < size; i++) {
                    ret[i] = list.get(i);
                }

                return ret;
            }
        }
    }
}
