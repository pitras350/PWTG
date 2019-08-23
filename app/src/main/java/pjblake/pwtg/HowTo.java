package pjblake.pwtg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HowTo extends AppCompatActivity{

    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
        final Button bt1 = findViewById(R.id.bt1);
        final Button bt2 = findViewById(R.id.bt2);
        final ImageView iv = findViewById(R.id.howToSrc);
        final TextView tv = findViewById(R.id.tv1);
        bt2.setVisibility(View.INVISIBLE);
        Button bt3 = findViewById(R.id.backhowto);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0)
                {
                   iv.setImageResource(R.drawable.onehit);
                   tv.setText(" Ataki wyprowadzone są poprzez przyciski po prawej stronie ");
                   bt2.setVisibility(View.VISIBLE);
                }
                if(i==1)
                {
                   iv.setImageResource(R.drawable.swap);
                   tv.setText(" Inicjatywa zmienia się po 3 atakach jednego z rywali ");
                }
                if(i==2)
                {
                   iv.setImageResource(R.drawable.nohp);
                   tv.setText(" Walka kończy się gdy jeden z rywali straci wszystkie punkty życia ");
                   bt1.setText("Powrót do Menu");
                }
                if(i==3)
                {
                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(intent);
                }
                i++;
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==1)
                {
                    iv.setImageResource(R.drawable.fullhp);
                    tv.setText(" Tak prezentuje się ekran walki ");
                    bt2.setVisibility(View.INVISIBLE);
                }
                if(i==2)
                {
                    iv.setImageResource(R.drawable.onehit);
                    tv.setText(" Ataki wyprowadzone są poprzez przyciski po prawej stronie ");
                }
                if(i==3)
                {
                    iv.setImageResource(R.drawable.swap);
                    tv.setText(" Inicjatywa zmienia się po 3 atakach jednego z rywali ");
                    bt1.setText("Następna Porada");
                }
                i--;
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

