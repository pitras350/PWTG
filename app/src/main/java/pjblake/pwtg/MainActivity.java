package pjblake.pwtg;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv;
    //ImageView iv,iv1;
    Button bt,bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks())
        {
            @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState)
            {
                activity.setRequestedOrientation
            }
        }*/
        toolbar = findViewById(R.id.main_bar);
        tv = findViewById(R.id.main_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //iv = findViewById(R.id.animacja);
        //iv.setImageResource(R.drawable.anim);
        //final AnimationDrawable[] face = {(AnimationDrawable) iv.getDrawable()};
        //face[0].start();
        final Intent intent,intent1,intent2;
        //wybór postaci
        bt = findViewById(R.id.button);
        intent = new Intent(this,CharacterSelection.class);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GifImageView z = findViewById(R.id.zmianaAni);
                z.setImageResource(R.drawable.chopoh);
                startActivity(intent);
            }
        });
        //opcje
        bt1 = findViewById(R.id.button1);
        intent1 = new Intent(this,Options.class);
        bt1.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
                GifImageView z = findViewById(R.id.zmianaAni);
                z.setImageResource(R.drawable.newkick);
                startActivity(intent1);
            }
        });
        //pomoc i o autorze
        bt2 = findViewById(R.id.button2);
        intent2 = new Intent(this,Help.class);
        bt2.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
                GifImageView z = findViewById(R.id.zmianaAni);
                z.setImageResource(R.drawable.newpunch);
                startActivity(intent2);
            }
        });
        //iv1 = findViewById(R.id.test);
        //iv1.setImageResource(R.drawable.powierzchnia);
    }

    /*
    if(i%2==0) {
                    iv.setImageResource(R.drawable.anima);
                    face[0] = (AnimationDrawable) iv.getDrawable();
                    face[0].start();
                    i++;
                }
                else
                {
                    iv.setImageResource(R.drawable.anim);
                    final AnimationDrawable[] face = {(AnimationDrawable) iv.getDrawable()};
                    face[0].start();
                    i++;
                }
     */
}
