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

import theano.wakeupwithmath.util.SystemUiHider;


public class WakeUp extends Activity {

    private static MediaPlayer mp;

    private static BackgroundSound mBackgroundSound;

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final boolean TOGGLE_ON_CLICK = true;
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up);

        mBackgroundSound = new BackgroundSound();
        mBackgroundSound.execute();
        showTasks();
    }
    private Button check;
    private EditText result;
    private TextView answer;
    private TextView task;
    private Task.Example[] exm;
    private int N;

    private boolean problemsSolved;
    private void showTasks() {
        problemsSolved = false;
        Task t = new Task();
        exm = t.getExampels();
        check = (Button) findViewById(R.id.check);
        result = (EditText) findViewById(R.id.result);
        task = (TextView) findViewById(R.id.task);
        answer = (TextView) findViewById(R.id.answer);

        N = 0;
        while(N<exm.length){
            task.setText(exm[N].getTask());
            result.setText("");
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (String.valueOf(result.getText()).equals(String.valueOf(exm[N].getResult()))){
                        N++;
                    }
                    else {
                        answer.setText("Не вірно");
                    }

                }
            });
        }
        problemsSolved = true;
        checkInput();


    }

    public void checkInput() {
        if (problemsSolved) {
            mp.stop();
            mBackgroundSound.cancel(true);
            finish();
            System.exit(0);
        }
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
