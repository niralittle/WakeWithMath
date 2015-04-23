package theano.wakeupwithmath;

import java.util.Random;


public class ProblemFactory {

    private static int a, b, op, result;
    private static String content;
    private static final int NUM_OF_TASKS = 3;

    public static Problem[] generateProblemSet() {
        Problem[] problems = new Problem[NUM_OF_TASKS];
        for (int i = 0; i < NUM_OF_TASKS; ++i) {
            problems[i] = generate();
        }
        return problems;
    }

    private static Problem generate() {
        Random r = new Random();
        b = r.nextInt(20);
        a = r.nextInt(50) + b;
        if ((a % b) == 0) op = r.nextInt(4);
        else op = r.nextInt(3);
        switch (op) {
            case 0: {
                result = a + b;
                content = String.valueOf(a)+" + "+String.valueOf(b)+" = ";
                break;
            }
            case 1: {
                result = a - b;
                content = String.valueOf(a)+" - "+String.valueOf(b)+" = ";
                break;
            }
            case 2: {
                result = a * b;
                content = String.valueOf(a)+" * "+String.valueOf(b)+" = ";
                break;
            }
            case 3: {
                if (b == 0) b = r.nextInt();
                result = a / b;
                content = String.valueOf(a)+" / "+String.valueOf(b)+" = ";
            }
        }
        return new Problem(content, result);
    }

    public static class Problem {
        private String content;
        private int result;

        public Problem(String task, int result) {
            this.content = task;
            this.result = result;
        }

        public String getContent() {
            return content;
        }

        public int getResult() {
            return result;
        }
    }
}
