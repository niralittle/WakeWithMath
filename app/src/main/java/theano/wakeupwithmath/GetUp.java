package theano.wakeupwithmath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class GetUp extends Activity implements View.OnClickListener {

    private Button check;
    private EditText result;
    private TextView answer;
    private TextView task;
    private Task.Example[] exemples;
    private int N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getup);
        check = (Button) findViewById(R.id.check);
        result = (EditText) findViewById(R.id.result);
        task = (TextView) findViewById(R.id.textView);
        answer = (TextView) findViewById(R.id.answer);
        check.setOnClickListener(this);
        Task t = new Task();
        exemples = t.getExampels();
        N = 0;
        init();
    }

    private void init() {
        result.setText("");
        if (N<exemples.length)
            task.setText(exemples[N].getTask());
        //else next();
    }

   /* private void next() {
        Intent intent = new Intent(this, GetUpTwo.class);
        startActivity(intent);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {

        if (String.valueOf(result.getText()).equals(String.valueOf(exemples[N].getResult()))){
            answer.setText("You right!"+String.valueOf(N));
            N++;
            init();
        }
        else answer.setText("You wrong!");

    }
}
