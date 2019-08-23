package pjblake.pwtg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageButton;

public class CharacterSelection extends AppCompatActivity{

    int x=0;
    int sound;
   @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        final GifImageButton b1 = findViewById(R.id.technicalB);
        final GifImageButton b2 = findViewById(R.id.powerhouseB);
        final TextView title = findViewById(R.id.main_title);
        final Button b3 = findViewById(R.id.easy);
        final Button b4 = findViewById(R.id.medium);
        final Button b5 = findViewById(R.id.hard);
        final Button b6 = findViewById(R.id.backselect);
        b3.setVisibility(View.INVISIBLE); b4.setVisibility(View.INVISIBLE); b5.setVisibility(View.INVISIBLE);
        //b3,b3,b5 -> buttony do poziomów trudności
        final TextView tv = findViewById(R.id.test);
        sound = getIntent().getIntExtra("sound",2);
        Intent svc=new Intent(this, BackgroundSoundService.class);
        if(sound==0)
        {
            startService(svc);
            BackgroundSoundService.pause();
        }
        else
        {
            startService(svc);
            BackgroundSoundService.resume();
        }
        final Intent intent = new Intent(this,NewGame.class);
        final Intent intent1 = new Intent(this,MainActivity.class);
        super.onStart();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent.putExtra("wrestler","TC");
                //startActivity(intent);
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                title.setText(R.string.difficulty);
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent.putExtra("wrestler","PH");
                //startActivity(intent);
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                title.setText(R.string.difficulty);
            }
        });
        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent.putExtra("difficulty","E");
                intent.putExtra("sound",sound);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent.putExtra("difficulty","M");
                intent.putExtra("sound",sound);
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent.putExtra("difficulty","H");
                intent.putExtra("sound",sound);
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                intent1.putExtra("sound",sound);
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void onStart() {
        if(sound==0)
        {
            BackgroundSoundService.pause();
        }
        super.onStart();
    }

    @Override
    protected  void onResume()
    {
            if (x > 0) {
                BackgroundSoundService.resume();
            }
            x++;
            super.onResume();
    }
    @Override
    protected void onPause()
    {
        BackgroundSoundService.pause();
        //Intent svc = new Intent(this,BackgroundSoundService.class);
        //stopService(svc);
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        Intent svc = new Intent(this,BackgroundSoundService.class);
        stopService(svc);
        super.onDestroy();
    }

}
