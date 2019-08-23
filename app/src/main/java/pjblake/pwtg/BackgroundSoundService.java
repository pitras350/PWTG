package pjblake.pwtg;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sql.DataSource;

public class BackgroundSoundService extends Service {
    private static final String TAG = null;
    private static MediaPlayer player;
    static ArrayList<Integer> playlist;
    static Timer timer;

    public IBinder onBind(Intent arg0) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Random rand = new Random();
        int c = rand.nextInt(2);

        player = MediaPlayer.create(this,R.raw.main);
        player.setVolume(0.25f,0.25f);
        player.setLooping(true);
        //timer = new Timer();
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return 1;
    }

    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }
    /*
    public void onStop() {
        player.pause();
        player.release();

    }
    public static void onPause() {
        player.pause();
        player.release();
    }*/
    @Override
    public void onDestroy() {
       // player.stop();
       // player.reset();
       // player.release();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {

    }
    public static void pause()
    {
        player.pause();
    }
    public static void resume()
    {
        player.start();
    }
    public static void updateSound(float a,float b)
    {
        player.setVolume(a,b);
    }
    public static void finito()
    {
        player.setVolume(0,0);
        player.stop();
        player.reset();
        player.release();
        player = null;
    }


}
