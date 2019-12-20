package pjblake.pwtg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        TextView tv = findViewById(R.id.podsumowanie);
        Button bt = findViewById(R.id.button3);
        String lista="";
        final int sound = getIntent().getIntExtra("sound",0);
        final ArrayList<String>moves = getIntent().getStringArrayListExtra("lista Akcji");
        int hpE= getIntent().getIntExtra("hpE",0);
        int hpP= getIntent().getIntExtra("hpP",0);
        if(hpE>hpP)
        {
            lista="PORAŻKA! \n \n";
        }
        else
        {
            lista="ZWYCIĘSTWO! \n \n";
        }
        for(int i=0; i<moves.size(); i++)
        {
            lista=lista+moves.get(i)+"\n";
        }
        tv.setText(lista);
        tv.setMovementMethod(new ScrollingMovementMethod());
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("sound",sound);
                startActivity(intent);
            }
        });

    }
    @Override
    protected  void onResume()
    {
        BackgroundSoundService.resume();
        super.onResume();
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
