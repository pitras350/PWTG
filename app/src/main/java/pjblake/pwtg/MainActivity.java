package pjblake.pwtg;

import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv;
    ImageView iv,iv1;
    Button bt,bt1,bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_bar);
        tv = findViewById(R.id.main_title);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/bm.TTF");
        tv.setTypeface(type);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        iv = findViewById(R.id.animacja);
        iv.setImageResource(R.drawable.anim);
        final AnimationDrawable[] face = {(AnimationDrawable) iv.getDrawable()};
        face[0].start();

        bt = findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
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

            }
        });
        bt1 = findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
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

            }
        });
        bt2 = findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
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

            }
        });
        bt3 = findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
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

            }
        });
        iv1 = findViewById(R.id.test);
        iv1.setImageResource(R.drawable.powierzchnia);
    }
}
