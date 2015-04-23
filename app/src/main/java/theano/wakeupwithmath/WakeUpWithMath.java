package theano.wakeupwithmath;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class WakeUpWithMath extends FragmentActivity {

    private static int hour = 0;
    private static int minute = 0;
    Button setTime;
    Button schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up_with_math);
        setTime = (Button) findViewById(R.id.setTime);
        schedule = (Button) findViewById(R.id.schedule);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wake_up_with_math, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showTimePickerDialog(View view) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setButtons(setTime, schedule);
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void scheduleAlarm(View view) {
        Intent intent = new Intent(this, WakeUp.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        PendingIntent wakeUp = PendingIntent.getActivity(this, 123, intent, 0);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        long time = calculateMillis();
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, wakeUp);
        Toast.makeText(this, "Будильник наведено", Toast.LENGTH_SHORT).show();
        schedule.setVisibility(View.INVISIBLE);
        setTime.setEnabled(false);
    }

    private long calculateMillis() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        return cal.getTimeInMillis();
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        Button setTimeButton;
        Button schedule;
        public void setButtons(Button setTime, Button schedule) {
            this.setTimeButton = setTime;
            this.schedule = schedule;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            WakeUpWithMath.minute = minute;
            WakeUpWithMath.hour = hourOfDay;
            setTimeButton.setText("" + hour + ':' + minute);
            schedule.setEnabled(true);
        }
    }
}
