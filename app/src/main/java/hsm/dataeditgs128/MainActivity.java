package hsm.dataeditgs128;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    // broadcast a custom intent.
    public void broadcastIntent(View view){
        Intent intent = new Intent();
        intent.setAction("com.honeywell.decode.intent.action.EDIT_DATA");// ("hsm.dataeditgs128.DataEditing");
        intent.putExtra("data", "ScanData123");
        intent.putExtra("aimId", "]C1");
        intent.putExtra("version",1);
        //sendBroadcast(intent);
        sendOrderedBroadcast(intent,
                null,
                new NotificationResultReceiver(),
                null,
                Activity.RESULT_OK,
                null,
                null);
    }

    public class NotificationResultReceiver extends BroadcastReceiver {

        @Override public void onReceive(Context context, Intent i) {
            final int code = getResultCode();
            Bundle bundle=i.getExtras();
            String data=bundle.getString("data");
            Log.d(Consts.TAG, "NotificationResultReceiver: code="+code);
            if (code == Activity.RESULT_OK) {
                // app is not active
                // generate System Notification
            } else {
                // LocalBroadcastReceiver registered.
                // app is active
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
