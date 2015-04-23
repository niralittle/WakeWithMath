package theano.wakeupwithmath;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import theano.wakeupwithmath.util.SystemUiHider;


public class WakeUp extends Activity {

    private static MediaPlayer mp;

    private static BackgroundSound mBackgroundSound;

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final boolean TOGGLE_ON_CLICK = true;
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    TextView task1;
    TextView task2;
    TextView task3;

    EditText res1;
    EditText res2;
    EditText res3;

    private Button check;
    private TextView result;
    private ProblemFactory.Problem[] problems;
    private int N;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

        task1 = (TextView) findViewById(R.id.task1);
        task2 = (TextView) findViewById(R.id.task2);
        task3 = (TextView) findViewById(R.id.task3);

        res1 = (EditText) findViewById(R.id.res1);
        res2 = (EditText) findViewById(R.id.res2);
        res3 = (EditText) findViewById(R.id.res3);

        mBackgroundSound = new BackgroundSound();
        mBackgroundSound.execute();

        showTasks();
    }

    private void showTasks() {
        problems = ProblemFactory.generateProblemSet();
        task1 = (TextView) findViewById(R.id.task1);
        task1.setText(problems[0].getContent());
        task2 = (TextView) findViewById(R.id.task2);
        task2.setText(problems[1].getContent());
        task3 = (TextView) findViewById(R.id.task3);
        task3.setText(problems[2].getContent());
    }

    public void checkInput(View view) {
        if (problemsSolved()) {
            mp.stop();
            mBackgroundSound.cancel(true);
            finish();
            System.exit(0);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Щось ви ще не прокинулись, спробуйте-но ще", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean problemsSolved() {
        if (res1.getText().toString().isEmpty()
                || res2.getText().toString().isEmpty()
                || res3.getText().toString().isEmpty()) {
            return false;
        }
        if (!(problems[0].getResult() == Integer.parseInt(res1.getText().toString()))
                || !(problems[1].getResult() == Integer.parseInt(res2.getText().toString()))
                || !(problems[2].getResult() == Integer.parseInt(res3.getText().toString()))) {
            return false;
        }

        return true;
    }


    public class BackgroundSound extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            mp = MediaPlayer.create(getApplicationContext(), notification);
            mp.start();
            return null;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }




}
