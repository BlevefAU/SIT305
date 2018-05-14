package com.example.bleve.knightprinciple;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class bgm extends Service {
    private MediaPlayer  player;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//         background music play
        player = MediaPlayer.create(this, R.raw.melody2);
        player.setLooping(true);
        player.start();
        return  START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
