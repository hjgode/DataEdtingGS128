package hsm.dataeditgs128;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Formatter;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by E841719 on 22.01.2016.
 */
public class DataEditing  extends BroadcastReceiver implements hsm.dataeditgs128.Common
{
    private final String TAG = "DataEditGS128: ";

    // Result success, continue further processing, wedge
    private static final int DATA_EDIT_RESULT_SUCCESS = 0;
    // Result continue, continue further processing, wedge
    private static final int DATA_EDIT_RESULT_CONTINUE = 1;
    // Result handled, stop further processing, no wedge
    private static final int DATA_EDIT_RESULT_HANDLED = 2;
    // Result error, stop further processing and bad read notification, no wedge
    private static final int DATA_EDIT_RESULT_ERROR = 3;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String ScanResult = intent.getStringExtra("data");//Read the scan result from the Intent
        int version = intent.getIntExtra("version", 0);
        String sAimId;
        if(version==0)
            sAimId="not supported";
        else
            sAimId = intent.getStringExtra("aimId");

        Log.d(TAG, "data="+ScanResult+", aimId=" + sAimId);

        Bundle bundle = new Bundle();
        //load
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Boolean currentMode = prefs.getBoolean(PREF_KEY_ENABLE, true);

        if(!currentMode){
            bundle.putString("data", ScanResult);
            Log.d(TAG, "DataEditHex disabled, returning unchanged ScanResult " + ScanResult);
        }
        else {
            //---------------------------------------------
            //Modify the scan result as needed.
            //---------------------------------------------

            //Return the Modified scan result string
            //convert data to hex string
            byte[] digits = ScanResult.getBytes();

            //see http://stackoverflow.com/questions/19450452/how-to-convert-byte-array-to-hex-format-in-java
            Formatter formatter = new Formatter();
            for (byte b : digits) {
                formatter.format("%02x", b);
            }
            String hex = formatter.toString();

            //return edited data
            bundle.putString("data", hex);

            if (isAppForground(context)) {
                // App is in Foreground
                Toast.makeText(context, "Intent Detected: " + ScanResult + " / " + hex, Toast.LENGTH_LONG).show();
            } else {
                // App is in Background
                ;
            }

            Log.d("DataEditGS128: ", "Intent Detected: " + ScanResult + " / " + hex);

            setResultExtras(bundle);
        }//if !currentMode
        int resultCode = DATA_EDIT_RESULT_SUCCESS;
        final PendingResult result = goAsync();
        result.setResultExtras(bundle);
        if(isOrderedBroadcast()){
            result.setResultCode(resultCode);
            Log.d(TAG, "isOrderedBroadcast: TRUE");
        }else{
            Log.d(TAG, "isOrderedBroadcast: FALSE");
        }
        result.finish();
    }
    public boolean isAppForground(Context mContext) {

        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(mContext.getPackageName())) {
                return false;
            }
        }

        return true;
    }
}