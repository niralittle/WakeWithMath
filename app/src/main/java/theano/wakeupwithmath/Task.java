package theano.wakeupwithmath;

import java.util.Random;

/**
 * Created by Богдан on 22.04.2015.
 */
public class Task {
    private Random r;
    private Example[] exampels;
    private int a,b,op,s;
    private String res;
    private final int NUM_OF_TASK = 5;

    public class Example{
        private String task;
        private int result;

        public Example(String task, int result) {
            this.task = task;
            this.result = result;
        }

        public String getTask() {
            return task;
        }

        public int getResult() {
            return result;
        }
    }

    public Task(){

        r = new Random();
        exampels = new Example[NUM_OF_TASK];
        for (int i = 0; i<NUM_OF_TASK; i++){
            generate(i);
        }
    }

    public Example[] getExampels(){
        return this.exampels;
    }

    public int getNum(){
        return this.NUM_OF_TASK;
    }

    private void generate(int i) {
        b = r.nextInt(20);
        a = r.nextInt(50)+b;
        if ((a % b)==0) op = r.nextInt(4);
        else op = r.nextInt(3);
        switch(op) {
            case 0: {
                s = a+b;
                res = String.valueOf(a)+" + "+String.valueOf(b)+" ??? ";
                break;
            }

            case 1: {
                s = a-b;
                res = String.valueOf(a)+" - "+String.valueOf(b)+" ??? ";
                break;
            }

            case 2: {
                s = a*b;
                res = String.valueOf(a)+" * "+String.valueOf(b)+" ??? ";
                break;
            }

            case 3: {
                s = a/b;
                res = String.valueOf(a)+" / "+String.valueOf(b)+" ??? ";
            }
            ;
        }
        exampels[i] = new Example(res,s);
    }
}
