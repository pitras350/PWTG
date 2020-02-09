package pjblake.pwtg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import pjblake.pwtg.R;

public class Options extends AppCompatActivity{
    int x=0;
    int s=0,s1=0;
    int sound;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        final SeekBar sb = findViewById(R.id.seekglos);
        Button bt = findViewById(R.id.confirmation);
        Button bt2 = findViewById(R.id.backoptions);
        Button bt3 = findViewById(R.id.author);
        Button bt4 = findViewById(R.id.musicauthor);
        final TextView tv = findViewById(R.id.textAuthor);
        final TextView tv1 = findViewById(R.id.textMusic);
        sound = getIntent().getIntExtra("sound",5);
        if(sound==0) { BackgroundSoundService.pause(); }
        else { BackgroundSoundService.resume();}
        sb.setProgress(sound);
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
                //
                if(s==0)
                {
                    tv.setText("Autor: Piotr Jabłoński \n" + "Email: piter2012@o2.pl");
                    tv.setVisibility(View.VISIBLE);
                    s=1;
                }
                else
                {
                    tv.setText("Autor: Piotr Jabłoński \n" + "Email: piter2012@o2.pl");
                    tv.setVisibility(View.INVISIBLE);
                    s=0;
                }
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s1==0)
                {
                    tv1.setText("Muzyka: \n"+"Menu główne - "+"LUKHASH - \"The Other Side\" \n"+ "Wejścia zawodników: \n" + "MagikStudio - \"Thunder Rock\" \n" + "STEEP - \"It could be you\" \n" + "W walkach: \n" +"Avercage - \"Enflammer\" \n" + "Golden Duck Orchestra - \"ON SALE\"" );
                    tv1.setVisibility(View.VISIBLE);
                    tv1.setMovementMethod(new ScrollingMovementMethod());
                    s1=1;
                }
                else
                {
                    tv1.setText("Muzyka: \n"+"Menu główne - "+"LUKHASH - \"The Other Side\" \n"+ "Wejścia zawodników: \n" + "MagikStudio - \"Thunder Rock\" \n" + "STEEP - \"It could be you\" \n" + "W walkach: \n" +"Avercage - \"Enflammer\" \n" + "Golden Duck Orchestra - \"ON SALE\"" );
                    tv1.setVisibility(View.INVISIBLE);
                    s1=0;
                }

            }
        });

    }
    @Override
    protected  void onResume()
    {
        super.onResume();
        if(x>0) {
            BackgroundSoundService.resume();
            BackgroundSoundService.updateSound(sound/20,sound/20);
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
