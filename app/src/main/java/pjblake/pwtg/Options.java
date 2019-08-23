package pjblake.pwtg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import pjblake.pwtg.R;

public class Options extends AppCompatActivity{
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);
        final SeekBar sb = findViewById(R.id.seekglos);
        Button bt = findViewById(R.id.confirmation);
        Button bt2 = findViewById(R.id.backoptions);
        Button bt3 = findViewById(R.id.author);
        Button bt4 = findViewById(R.id.musicauthor);
        final TextView tv = findViewById(R.id.textAuthor);
        final TextView tv1 = findViewById(R.id.textMusic);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundSoundService.updateSound((float) sb.getProgress() / 20, (float) sb.getProgress() / 20);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("sound",sb.getProgress());
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("Autor: Piotr Jabłoński \n" + "Email: piter2012@o2.pl");
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv1.setText("Muzyka: \n" + "LUKHASH - \"The Other Side\" \n" + "Thomas Allan - \"Walks like Chaplin\" \n"  );
            }
        });

    }
    @Override
    protected  void onResume()
    {
        //
        super.onResume();
        if(x>0) {
            BackgroundSoundService.resume();
        }
        x++;
    }
    @Override
    protected void onPause()
    {
        BackgroundSoundService.pause();
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        Intent svc = new Intent(this,BackgroundSoundService.class);
        stopService(svc);
        super.onDestroy();
    }
}
