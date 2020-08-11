package pjblake.pwtg;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv;
    Button btCharacter,btOptions,btHow,btSound;
    int s=1;
    int x=0;
    int xd=5;
    int sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(pjblake.pwtg.R.layout.activity_main);
        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);
        int muzyka = getIntent().getIntExtra("muzyka",5);
        btSound = findViewById(R.id.soundmenu);
        sound=getIntent().getIntExtra("sound",5);

        if(sound==0)
        {
            BackgroundSoundService.updateSound(0,0);
            btSound.setBackgroundResource(R.drawable.soundoff);

        }else
        {
            xd = muzyka;
            if(xd!=5){xd = new BackgroundSoundService().changeSong(MainActivity.this,4,sound);}
            btSound.setBackgroundResource(R.drawable.soundon);
        }
        toolbar = findViewById(R.id.main_bar);
        tv = findViewById(R.id.main_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Intent intentCharacter,intentOptions,intentHow;
        //wybór postaci
        btCharacter = findViewById(R.id.button);
        intentCharacter = new Intent(this,CharacterSelection.class);
        btCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GifImageView z = findViewById(R.id.zmianaAni);
                z.setImageResource(R.drawable.chopp);
                intentCharacter.putExtra("sound",sound);
                startActivity(intentCharacter);
            }
        });
        //opcje
        btOptions = findViewById(R.id.button1);
        intentOptions = new Intent(this,Options.class);
        btOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GifImageView z = findViewById(R.id.zmianaAni);
                z.setImageResource(R.drawable.kickp);
                intentOptions.putExtra("sound",sound);
                startActivity(intentOptions);
            }
        });
        //jak grać
        btHow = findViewById(R.id.button2);
        intentHow = new Intent(this,HowTo.class);
        btHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GifImageView z = findViewById(R.id.zmianaAni);
                z.setImageResource(R.drawable.punchp);
                intentHow.putExtra("sound",sound);
                startActivity(intentHow);
            }
        });

        btSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s==0)
                {
                    BackgroundSoundService.resume();
                    btSound.setBackgroundResource(R.drawable.soundon);
                    s=1; sound=getIntent().getIntExtra("sound",5);
                    if(getIntent().getIntExtra("sound",5)==0) { sound=5; }
                    BackgroundSoundService.updateSound(sound/20f,sound/20f);
                }
                else
                {
                    BackgroundSoundService.pause();
                    btSound.setBackgroundResource(R.drawable.soundoff);
                    s=0; sound=0;
                }
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
            BackgroundSoundService.updateSound(sound/20f,sound/20f);
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
