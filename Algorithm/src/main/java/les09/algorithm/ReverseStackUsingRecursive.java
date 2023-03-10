package les09.algorithm;

import java.util.Stack;

/**
 * @author ether
 */
public class ReverseStackUsingRecursive {
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverseStack(stack);
        stack.push(i);
    }


    // 栈底上方的元素相对顺序不变，移除栈底元素并返回，
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);

            return last;
        }
    }
}
