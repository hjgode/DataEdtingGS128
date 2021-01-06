package hsm.dataeditgs128;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
import android.os.Looper;

class BeepHelper
{
    static ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);

    public static void beep(final int duration)
    {
        toneG.startTone(/*ToneGenerator.TONE_DTMF_S */ ToneGenerator.TONE_PROP_BEEP2, duration);
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable r = new  Runnable() {
            public void run() {
                handler.postDelayed(this, duration + 50);
                toneG.release();
            }
        };
    }
}