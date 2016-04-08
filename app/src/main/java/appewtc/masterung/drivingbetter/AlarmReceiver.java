package appewtc.masterung.drivingbetter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

/**
 * Created by apple on 2/11/16 AD.
 */
public class AlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "warnning", Toast.LENGTH_SHORT).show();
        MediaPlayer effectMediaPlayer = MediaPlayer.create(context, R.raw.horse);
        effectMediaPlayer.start();


    } // OnReceive
} // Main Class
