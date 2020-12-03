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



    @Override
    public void onReceive(Context context, Intent intent)
    {
        String ScanResult = intent.getStringExtra("data");//Read the scan result from the Intent
        int version = intent.getIntExtra("version", 0);
        String sAimId;
        String _data="";
        if(version==0)
            sAimId="not supported";
        else
            sAimId = intent.getStringExtra("aimId");

        Log.d(Consts.TAG, "data="+ScanResult+", aimId=" + sAimId);

        Bundle bundle = new Bundle();
        //load
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        Boolean isEnabled = prefs.getBoolean(PREF_KEY_ENABLE, true);
        String gs1replace = prefs.getString(PREF_KEY_GS1REPLACE, "#");
        Boolean process90only=prefs.getBoolean(PREF_KEY_PROCESS90ONLY, true);

        _data=ScanResult;

        if(!isEnabled){
            bundle.putString("data", ScanResult);
            Log.d(Consts.TAG, "DataEditHex disabled, returning unchanged ScanResult " + ScanResult);
        }
        else {
            //---------------------------------------------
            //Modify the scan result as needed.
            //---------------------------------------------
            if(sAimId.equals("]C1")){
                if(process90only && ScanResult.startsWith("]C190")) {
                    Log.d(Consts.TAG, "aimId=]C1 and starts with 90");
                    String temp = ScanResult.replace("\u001D", gs1replace);
                    _data = "]C1" + temp;//ScanResult;
                }else{
                    Log.d(Consts.TAG, "aimId is ]C1 and does not start with 90. Removing GS1 ]C1 id");
                    _data=ScanResult.substring(3);
                }
            }else{
                Log.d(Consts.TAG, "not aimId==]C1, no change");
            }

            //return edited data
            bundle.putString("data", _data);

            //show a Toast message?
/*
            if (isAppForground(context)) {
                // App is in Foreground
                Toast.makeText(context, "Intent Detected: " + ScanResult + " / " + hex, Toast.LENGTH_LONG).show();
            } else {
                // App is in Background
                ;
            }
*/

            Log.d("DataEditGS128: ", "Intent Detected: in/out = " + ScanResult + " / " + _data);

            setResultExtras(bundle);
        }//if !currentMode
        int resultCode = Consts.DATA_EDIT_RESULT_SUCCESS;
        final PendingResult result = goAsync();
        result.setResultExtras(bundle);
        if(isOrderedBroadcast()){
            result.setResultCode(resultCode);
            Log.d(Consts.TAG, "isOrderedBroadcast: TRUE");
        }else{
            result.setResultCode(resultCode);
            Log.d(Consts.TAG, "isOrderedBroadcast: FALSE");
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