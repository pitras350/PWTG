package pjblake.pwtg;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class NewGame extends AppCompatActivity {
    int click=0;
    int PA=3;
    int start=0;
    int inicjatywa;
    int ataka,atakb,atakc,atakd;
    int numerAtakuA,numerAtakuB,numerAtakuC,numerAtakuD;
    int goodContition=140,tiredCondition=100,exhaustedCondition=60;
    int xd;
    int x=0;
    int sound;
    ArrayList<String> listOfMoves = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        final String str = getIntent().getStringExtra("wrestler");
        final String str1 = getIntent().getStringExtra("difficulty");
        sound = getIntent().getIntExtra("sound",5);
        final Random rand = new Random();
        final Button b1 = findViewById(R.id.choice1);
        final Button b2 = findViewById(R.id.choice2);
        final Button b3 = findViewById(R.id.choice3);
        final Button b4 = findViewById(R.id.choicePIN);
        final TextView tv = findViewById(R.id.test);
        final TextView hpRival = findViewById(R.id.punktyzdrowiaENEMY);
        final TextView hpMine = findViewById(R.id.punktyzdrowiaPLAYER);
        final GifImageView iv = findViewById(R.id.tlo);
        final int hpP = 200, hpE = 200;
        int value = 0;
        int atak = 0;
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);
        if(sound==0) { BackgroundSoundService.pause(); }
        else { BackgroundSoundService.resume();}
        /* announcer stage */
        iv.setImageResource(R.drawable.announcer);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                b4.setVisibility(View.VISIBLE);
            }
        }, 5000);
        b4.setText("Dalej");
        String l = "To starcie odb??dzie si?? na standardowych zasadach i zwyci??zca zostanie mistrzem PWTG";
        tv.setText(l);
        final ProgressBar hpPlayer = findViewById(R.id.hpplayer);
        hpPlayer.setScaleY(5f);
        hpPlayer.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        final ProgressBar hpEnemy = findViewById(R.id.hpenemy);
        hpEnemy.setScaleY(5f);
        hpEnemy.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PA!=1) {
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b1.setVisibility(View.VISIBLE);
                            b2.setVisibility(View.VISIBLE);
                            b3.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                }
                else
                {
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                }
                int hpT = hpEnemy.getProgress();
                int hpTT = hpPlayer.getProgress();
                if(sound==0){int xd=7;}
                else{ if(hpT<tiredCondition || hpTT<tiredCondition){ if(xd!=4){xd = new BackgroundSoundService().changeSong(NewGame.this,3,sound);}}}
                hpT = hpT - ataka;
                hpEnemy.setProgress(hpT);
                if (hpEnemy.getProgress() <= goodContition) { hpEnemy.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN); }
                if (hpEnemy.getProgress() <= tiredCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.rgb(255,125,0), PorterDuff.Mode.SRC_IN); }
                if (hpEnemy.getProgress() <= exhaustedCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN); }
                if (hpPlayer.getProgress() <= goodContition) { hpEnemy.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN); }
                if (hpPlayer.getProgress() <= tiredCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.rgb(255,125,0), PorterDuff.Mode.SRC_IN); }
                if (hpPlayer.getProgress() <= exhaustedCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN); }
                String noweZycieRywala = "RYWAL: " + Integer.toString(hpEnemy.getProgress()) + "/" + hpE;
                hpRival.setText(noweZycieRywala);
                zmianaTla(numerAtakuA,str);
                Calculations player = new Calculations(str1,"P");
                tv.setText("Tw??j zawodnik wykona??: " + jakiAtak(numerAtakuA));
                listOfMoves.add("Tw??j zawodnik wykona??: "+ jakiAtak(numerAtakuA)+ " za " + Integer.toString(player.attackCalculator(player.attackValue(numerAtakuA)))+ " punkt??w obra??e??");
                Random rand = new Random();
                int tabPOM[] = new int[30];
                int tabATK[] = new int[3];
                int range1=10,range2=20,range3=30;
                int losuj;
                if(hpEnemy.getProgress()>goodContition) {
                    for(int i=0; i<10; i++)
                    {
                        tabPOM[i]=i;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range1);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range1-1];
                        range1--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                else if(hpEnemy.getProgress()>tiredCondition && hpEnemy.getProgress()<=goodContition) {
                    for(int i=0; i<20; i++)
                    {
                        tabPOM[i]=i;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range2);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range2-1];
                        range2--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                else if(hpEnemy.getProgress()<=tiredCondition && hpEnemy.getProgress()>exhaustedCondition){
                    for(int i=0; i<30; i++)
                    {
                        tabPOM[i]=i;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range3);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range3-1];
                        range3--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                else if(hpEnemy.getProgress()<=exhaustedCondition){
                    for(int i=0; i<20; i++)
                    {
                        tabPOM[i]=i+10;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range2);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range2-1];
                        range2--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                b1.setText(jakiAtak(numerAtakuA));
                b2.setText(jakiAtak(numerAtakuB));
                b3.setText(jakiAtak(numerAtakuC));
                ataka= player.attackCalculator(player.attackValue(numerAtakuA));
                atakb= player.attackCalculator(player.attackValue(numerAtakuB));
                atakc= player.attackCalculator(player.attackValue(numerAtakuC));
                PA--;
                if (PA == 0) {
                    inicjatywa = 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b1.setVisibility(View.INVISIBLE);
                            b2.setVisibility(View.INVISIBLE);
                            b3.setVisibility(View.INVISIBLE);
                            b4.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                    b4.setText("Nast??pna akcja");
                    }
                if (hpPlayer.getProgress() == 0) {
                    if (str.equals("PH")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.tcwin);
                                tv.setText("Przegrana przez Knock-Out, nowym mistrzem PWTG zostaje: Technician!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                    if (str.equals("TC")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);;
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Przegrana przez Knock-Out, nowym mistrzem PWTG zostaje: Powerhouse!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }}
                if (hpEnemy.getProgress() == 0) {
                    if (str.equals("PH")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Wygrana przez Knock-Out, nowym mistrzem PWTG zostaje: Powerhouse!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                    if (str.equals("TC")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Wygrana przez Knock-Out, nowym mistrzem PWTG zostaje: Technician!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                }}});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PA!=1) {
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b1.setVisibility(View.VISIBLE);
                            b2.setVisibility(View.VISIBLE);
                            b3.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                }
                else
                {
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                }
                int hpT = hpEnemy.getProgress();
                int hpTT = hpPlayer.getProgress();
                if(sound==0){int xd=7;}
                else{ if(hpT<tiredCondition || hpTT<tiredCondition){ if(xd!=4){xd = new BackgroundSoundService().changeSong(NewGame.this,3,sound);}}}
                hpT=hpT-atakb;
                hpEnemy.setProgress(hpT);
                if (hpEnemy.getProgress() <= goodContition) { hpEnemy.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN); }
                if (hpEnemy.getProgress() <= tiredCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.rgb(255,125,0), PorterDuff.Mode.SRC_IN); }
                if (hpEnemy.getProgress() <= exhaustedCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN); }
                if (hpPlayer.getProgress() <= goodContition) { hpEnemy.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN); }
                if (hpPlayer.getProgress() <= tiredCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.rgb(255,125,0), PorterDuff.Mode.SRC_IN); }
                if (hpPlayer.getProgress() <= exhaustedCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN); }
                String noweZycieRywala = "RYWAL: " + Integer.toString(hpEnemy.getProgress()) + "/" + hpE;
                hpRival.setText(noweZycieRywala);
                zmianaTla(numerAtakuB,str);
                Calculations player = new Calculations(str1,"P");
                tv.setText("Tw??j zawodnik wykona??: " + jakiAtak(numerAtakuB));
                listOfMoves.add("Tw??j zawodnik wykona??: "+ jakiAtak(numerAtakuB)+ " za " + Integer.toString(player.attackCalculator(player.attackValue(numerAtakuB)))+ " punkt??w obra??e??");
                Random rand = new Random();
                int tabPOM[] = new int[30];
                int tabATK[] = new int[3];
                int range1=10,range2=20,range3=30;
                int losuj;
                if(hpEnemy.getProgress()>goodContition) {
                    for(int i=0; i<10; i++)
                    {
                        tabPOM[i]=i;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range1);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range1-1];
                        range1--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                else if(hpEnemy.getProgress()>tiredCondition && hpEnemy.getProgress()<=goodContition) {
                    for(int i=0; i<20; i++)
                    {
                        tabPOM[i]=i;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range2);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range2-1];
                        range2--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                else if(hpEnemy.getProgress()<=tiredCondition && hpEnemy.getProgress()>exhaustedCondition){
                    for(int i=0; i<30; i++)
                    {
                        tabPOM[i]=i;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range3);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range3-1];
                        range3--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                else if(hpEnemy.getProgress()<=exhaustedCondition){
                    for(int i=0; i<20; i++)
                    {
                        tabPOM[i]=i+10;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range2);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range2-1];
                        range2--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                b1.setText(jakiAtak(numerAtakuA));
                b2.setText(jakiAtak(numerAtakuB));
                b3.setText(jakiAtak(numerAtakuC));

                ataka= player.attackCalculator(player.attackValue(numerAtakuA));
                atakb= player.attackCalculator(player.attackValue(numerAtakuB));
                atakc= player.attackCalculator(player.attackValue(numerAtakuC));

                PA--;
                if (PA == 0) {
                    inicjatywa = 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b1.setVisibility(View.INVISIBLE);
                            b2.setVisibility(View.INVISIBLE);
                            b3.setVisibility(View.INVISIBLE);
                            b4.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                    b4.setText("Nast??pna akcja");
                }
                if (hpPlayer.getProgress() == 0) {
                    if (str.equals("PH")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.tcwin);
                                tv.setText("Przegrana przez Knock-Out, nowym mistrzem PWTG zostaje: Technician!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                    if (str.equals("TC")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Przegrana przez Knock-Out, nowym mistrzem PWTG zostaje: Powerhouse!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }}
                if (hpEnemy.getProgress() == 0) {
                    if (str.equals("PH")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Wygrana przez Knock-Out, nowym mistrzem PWTG zostaje: Powerhouse!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                    if (str.equals("TC")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Wygrana przez Knock-Out, nowym mistrzem PWTG zostaje: Technician!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                }}});
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PA!=1) {
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b1.setVisibility(View.VISIBLE);
                            b2.setVisibility(View.VISIBLE);
                            b3.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                }
                else
                {
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                }
                int hpT = hpEnemy.getProgress();
                int hpTT = hpPlayer.getProgress();
                if(sound==0){int xd=7;}
                else{ if(hpT<tiredCondition || hpTT<tiredCondition){ if(xd!=4){xd = new BackgroundSoundService().changeSong(NewGame.this,3,sound);}}}
                hpT=hpT-atakc;
                hpEnemy.setProgress(hpT);
                if (hpEnemy.getProgress() <= goodContition) { hpEnemy.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN); }
                if (hpEnemy.getProgress() <= tiredCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.rgb(255,125,0), PorterDuff.Mode.SRC_IN); }
                if (hpEnemy.getProgress() <= exhaustedCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN); }
                if (hpPlayer.getProgress() <= goodContition) { hpEnemy.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN); }
                if (hpPlayer.getProgress() <= tiredCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.rgb(255,125,0), PorterDuff.Mode.SRC_IN); }
                if (hpPlayer.getProgress() <= exhaustedCondition) { hpEnemy.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN); }
                String noweZycieRywala = "RYWAL: " + Integer.toString(hpEnemy.getProgress()) + "/" + hpE;
                hpRival.setText(noweZycieRywala);
                zmianaTla(numerAtakuC,str);
                Calculations player = new Calculations(str1,"P");
                tv.setText("Tw??j zawodnik wykona??: " + jakiAtak(numerAtakuC));
                listOfMoves.add("Tw??j zawodnik wykona??: "+ jakiAtak(numerAtakuC)+ " za " + Integer.toString(player.attackCalculator(player.attackValue(numerAtakuC)))+ " punkt??w obra??e??");
                Random rand = new Random();
                int tabPOM[] = new int[30];
                int tabATK[] = new int[3];
                int range1=10,range2=20,range3=30;
                int losuj;
                if(hpEnemy.getProgress()>goodContition) {
                    for(int i=0; i<10; i++)
                    {
                        tabPOM[i]=i;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range1);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range1-1];
                        range1--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                else if(hpEnemy.getProgress()>tiredCondition && hpEnemy.getProgress()<=goodContition) {
                    for(int i=0; i<20; i++)
                    {
                        tabPOM[i]=i;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range2);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range2-1];
                        range2--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                else if(hpEnemy.getProgress()<=tiredCondition && hpEnemy.getProgress()>exhaustedCondition){
                    for(int i=0; i<30; i++)
                    {
                        tabPOM[i]=i;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range3);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range3-1];
                        range3--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                else if(hpEnemy.getProgress()<=exhaustedCondition){
                    for(int i=0; i<20; i++)
                    {
                        tabPOM[i]=i+10;
                    }
                    for (int i=0; i<3; i++)
                    {
                        losuj=rand.nextInt(range2);
                        tabATK[i]=tabPOM[losuj];
                        tabPOM[losuj]=tabPOM[range2-1];
                        range2--;
                    }
                    numerAtakuA = tabATK[0];
                    numerAtakuB = tabATK[1];
                    numerAtakuC = tabATK[2];
                }
                b1.setText(jakiAtak(numerAtakuA));
                b2.setText(jakiAtak(numerAtakuB));
                b3.setText(jakiAtak(numerAtakuC));
                ataka= player.attackCalculator(player.attackValue(numerAtakuA));
                atakb= player.attackCalculator(player.attackValue(numerAtakuB));
                atakc= player.attackCalculator(player.attackValue(numerAtakuC));
                PA--;
                if (PA == 0) {
                    inicjatywa = 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b1.setVisibility(View.INVISIBLE);
                            b2.setVisibility(View.INVISIBLE);
                            b3.setVisibility(View.INVISIBLE);
                            b4.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                    b4.setText("Nast??pna akcja");
                }
                if (hpPlayer.getProgress() == 0) {
                    if (str.equals("PH")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.tcwin);
                                tv.setText("Przegrana przez Knock-Out, nowym mistrzem PWTG zostaje: Technician!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                    if (str.equals("TC")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Przegrana przez Knock-Out, nowym mistrzem PWTG zostaje: Powerhouse!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }}
                if (hpEnemy.getProgress() == 0) {
                    if (str.equals("PH")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Wygrana przez Knock-Out, nowym mistrzem PWTG zostaje: Powerhouse!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                    if (str.equals("TC")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Wygrana przez Knock-Out, nowym mistrzem PWTG zostaje: Technician!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.INVISIBLE);
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                b1.setVisibility(View.INVISIBLE);
                                                b2.setVisibility(View.INVISIBLE);
                                                b3.setVisibility(View.INVISIBLE);
                                                b4.setVisibility(View.VISIBLE);
                                            }
                                        }, 5000);
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                }}});
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(start==0)
                {
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b4.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                    String l="";
                    b4.setText("Dalej");
                    if(str.equals("TC"))
                    {
                        if(sound==0){int xd=7;}
                        else{int xd = new BackgroundSoundService().changeSong(NewGame.this,1,sound);}
                        iv.setImageResource(R.drawable.tcentry);
                        l = "Pierwszym zawodnikiem b??dzie uosobienie umiej??tno??ci ringowych - Technician!";
                    }
                    else{
                        if(sound==0){int xd=7;}
                        else{int xd = new BackgroundSoundService().changeSong(NewGame.this,0,sound);}
                        iv.setImageResource(R.drawable.phentry);
                        l = "Pierwszym zawodnikiem b??dzie najsilniejszy cz??owiek ??wiata - Powerhouse!";
                    }
                    tv.setText(l);
                    start++;
                }
                else if(start==1)
                {
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b4.setVisibility(View.VISIBLE);
                        }
                    }, 5000);
                    String l="";
                    b4.setText("Dalej");
                    if(str.equals("TC"))
                    {
                        Log.i("test",String.valueOf(sound));
                        if(sound==0){int xd=7;}
                        else{int xd = new BackgroundSoundService().changeSong(NewGame.this,0,sound);}
                        iv.setImageResource(R.drawable.phentry);
                        l = "Jego rywalem b??dzie najsilniejszy cz??owiek ??wiata - Powerhouse!";
                    }
                    else{
                        Log.i("test",String.valueOf(sound));
                        if(sound==0){int xd=7;}
                        else{int xd = new BackgroundSoundService().changeSong(NewGame.this,1,sound);}
                        iv.setImageResource(R.drawable.tcentry);
                        l = "Jego rywalem b??dzie uosobienie umiej??tno??ci ringowych - Technician!";
                    }
                    tv.setText(l);
                    start++;
                }
                else if(start==2) {
                    if(sound==0){int xd=7;}
                    else{int xd = new BackgroundSoundService().changeSong(NewGame.this,2,sound);}
                    inicjatywa = rand.nextInt(2);
                    if (str1.equals("E")) {
                        inicjatywa = 0;
                    } else if (str1.equals("H")) {
                        inicjatywa = 1;
                    }
                    if (inicjatywa == 0) // rzut na inicjatyw??
                    {
                        b1.setVisibility(View.INVISIBLE);
                        b2.setVisibility(View.INVISIBLE);
                        b3.setVisibility(View.INVISIBLE);
                        b4.setVisibility(View.INVISIBLE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.VISIBLE);
                                b2.setVisibility(View.VISIBLE);
                                b3.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        if (str.equals("PH")) {
                            iv.setImageResource(R.drawable.phbg);
                        } else {
                            iv.setImageResource(R.drawable.tcbg);
                        }
                        tv.setBackgroundColor(Color.BLACK);
                        String k = "Po szybkim zmierzeniu wzrokiem rywala uda??o Ci si?? wyprowadzi?? pierwszy ruch.";
                        tv.setText(k);
                        PA = 3;
                    } else {
                        b1.setVisibility(View.INVISIBLE);
                        b2.setVisibility(View.INVISIBLE);
                        b3.setVisibility(View.INVISIBLE);
                        b4.setVisibility(View.INVISIBLE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("Nast??pna akcja");
                        if (str.equals("TC")) {
                            iv.setImageResource(R.drawable.phbg);
                        } else {
                            iv.setImageResource(R.drawable.tcbg);
                        }
                        tv.setBackgroundColor(Color.BLACK);
                        String k = "Rywal wydaje si?? gro??ny i to on jako pierwszy zaskakuje Ci?? atakiem!";
                        tv.setText(k);
                        PA = 0;
                    }
                    Random rand = new Random();
                    int tabPOM[] = new int[30];
                    int tabATK[] = new int[3];
                    int range1=10,range2=20,range3=30;
                    int losuj;
                    if(hpPlayer.getProgress()>goodContition) {
                        for(int i=0; i<10; i++)
                        {
                            tabPOM[i]=i;
                        }
                        for (int i=0; i<3; i++)
                        {
                            losuj=rand.nextInt(range1);
                            tabATK[i]=tabPOM[losuj];
                            tabPOM[losuj]=tabPOM[range1-1];
                            range1--;
                        }
                        numerAtakuA = tabATK[0];
                        numerAtakuB = tabATK[1];
                        numerAtakuC = tabATK[2];
                    }
                    if(hpPlayer.getProgress()<=goodContition && hpPlayer.getProgress()>tiredCondition) {
                        for(int i=0; i<20; i++)
                        {
                            tabPOM[i]=i;
                        }
                        for (int i=0; i<3; i++)
                        {
                            losuj=rand.nextInt(range2);
                            tabATK[i]=tabPOM[losuj];
                            tabPOM[losuj]=tabPOM[range2-1];
                            range2--;
                        }
                        numerAtakuA = tabATK[0];
                        numerAtakuB = tabATK[1];
                        numerAtakuC = tabATK[2];
                    }
                    if(hpPlayer.getProgress()<=tiredCondition && hpPlayer.getProgress()>exhaustedCondition) {
                        for(int i=0; i<30; i++)
                        {
                            tabPOM[i]=i;
                        }
                        for (int i=0; i<3; i++)
                        {
                            losuj=rand.nextInt(range3);
                            tabATK[i]=tabPOM[losuj];
                            tabPOM[losuj]=tabPOM[range3-1];
                            range3--;
                        }
                        numerAtakuA = tabATK[0];
                        numerAtakuB = tabATK[1];
                        numerAtakuC = tabATK[2];
                    }
                    if(hpPlayer.getProgress()<=exhaustedCondition) {
                        for(int i=0; i<20; i++)
                        {
                            tabPOM[i]=i+10;
                        }
                        for (int i=0; i<3; i++)
                        {
                            losuj=rand.nextInt(range2);
                            tabATK[i]=tabPOM[losuj];
                            tabPOM[losuj]=tabPOM[range2-1];
                            range2--;
                        }
                        numerAtakuA = tabATK[0];
                        numerAtakuB = tabATK[1];
                        numerAtakuC = tabATK[2];
                    }
                    numerAtakuD = rand.nextInt(10);
                    b1.setText(jakiAtak(numerAtakuA));
                    b2.setText(jakiAtak(numerAtakuB));
                    b3.setText(jakiAtak(numerAtakuC));
                    Calculations player = new Calculations(str1,"P");
                    ataka= player.attackCalculator(player.attackValue(numerAtakuA));
                    atakb= player.attackCalculator(player.attackValue(numerAtakuB));
                    atakc= player.attackCalculator(player.attackValue(numerAtakuC));
                    Calculations enemy = new Calculations(str1,"E");
                    atakd= enemy.attackCalculator(enemy.attackValue(numerAtakuD));
                    /*ataka = poziomTrudnosci(str1, "P", silaAtaku(numerAtakuA));
                    atakb = poziomTrudnosci(str1, "P", silaAtaku(numerAtakuB));
                    atakc = poziomTrudnosci(str1, "P", silaAtaku(numerAtakuC));
                    atakd = poziomTrudnosci(str1, "E", silaAtaku(numerAtakuD));*/
                    hpMine.setText(R.string.player_max_hp);
                    hpRival.setText(R.string.enemy_max_hp);
                    start++;
                }
                else{
                    if(PA!=2) {
                        b1.setVisibility(View.INVISIBLE);
                        b2.setVisibility(View.INVISIBLE);
                        b3.setVisibility(View.INVISIBLE);
                        b4.setVisibility(View.INVISIBLE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                    }
                    else
                    {
                        b1.setVisibility(View.INVISIBLE);
                        b2.setVisibility(View.INVISIBLE);
                        b3.setVisibility(View.INVISIBLE);
                        b4.setVisibility(View.INVISIBLE);
                    }
                int hpT=hpPlayer.getProgress();
                int hpTT=hpEnemy.getProgress();
                if(sound==0){int xd=7;}
                else{if(hpT<tiredCondition||hpTT<tiredCondition)
                {if(xd!=4){xd = new BackgroundSoundService().changeSong(NewGame.this,3,sound);}}}
                hpT=hpT-atakd;
                hpPlayer.setProgress(hpT);
                String noweZycieMoje = "GRACZ: " + Integer.toString(hpPlayer.getProgress()) + "/" + hpP;
                hpMine.setText(noweZycieMoje);
                if (hpEnemy.getProgress() <= goodContition)
                {hpEnemy.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);}
                if (hpEnemy.getProgress() <= tiredCondition)
                {hpEnemy.getProgressDrawable().setColorFilter(Color.rgb(255,125,0), PorterDuff.Mode.SRC_IN);}
                if (hpEnemy.getProgress() <= exhaustedCondition)
                {hpEnemy.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);}
                if (hpPlayer.getProgress() <= goodContition)
                {hpEnemy.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);}
                if (hpPlayer.getProgress() <= tiredCondition)
                {hpEnemy.getProgressDrawable().setColorFilter(Color.rgb(255,125,0), PorterDuff.Mode.SRC_IN);}
                if (hpPlayer.getProgress() <= exhaustedCondition)
                {hpEnemy.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);}
                Random rand = new Random();
                if(str.equals("PH"))
                {
                    zmianaTla(numerAtakuD,"TC");
                }
                else
                {
                    zmianaTla(numerAtakuD,"PH");
                }
                tv.setText("Rywal wykona??: "+ jakiAtak(numerAtakuD));
                Calculations enemy = new Calculations(str1,"E");
                listOfMoves.add("Tw??j rywal wykona??: "+ jakiAtak(numerAtakuD)+ " za " + Integer.toString(enemy.attackCalculator(enemy.attackValue(numerAtakuD)))+ " punkt??w obra??e??");
                if(hpPlayer.getProgress()>goodContition) {
                    numerAtakuD = rand.nextInt(10);
                }
                else if(hpPlayer.getProgress()<=goodContition && hpPlayer.getProgress()>tiredCondition) {
                    numerAtakuD = rand.nextInt(20);
                }
                else if(hpPlayer.getProgress()<=tiredCondition && hpPlayer.getProgress()>exhaustedCondition){
                    numerAtakuD = rand.nextInt(30);
                }
                else if(hpPlayer.getProgress()<=exhaustedCondition)
                {
                    numerAtakuD=rand.nextInt(20)+10;
                }
                atakd= enemy.attackCalculator(enemy.attackValue(numerAtakuD));
                PA++;
                if (PA == 3) {
                    inicjatywa = 0;
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            b1.setVisibility(View.VISIBLE);
                            b2.setVisibility(View.VISIBLE);
                            b3.setVisibility(View.VISIBLE);
                            b4.setVisibility(View.INVISIBLE);
                        }
                    }, 5000);
                }
                if (hpPlayer.getProgress() == 0) {
                    if (str.equals("PH")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.INVISIBLE);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.VISIBLE);
                                    }
                                }, 5000);
                                iv.setImageResource(R.drawable.tcwin);
                                tv.setText("Przegrana przez Knock-Out, nowym mistrzem PWTG zostaje: Technician!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                            });
                        }
                    if (str.equals("TC")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.INVISIBLE);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.VISIBLE);
                                    }
                                }, 5000);
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Przegrana przez Knock-Out, nowym mistrzem PWTG zostaje: Powerhouse!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }}
                if (hpEnemy.getProgress() == 0) {
                    if (str.equals("PH")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.INVISIBLE);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.VISIBLE);
                                    }
                                }, 5000);
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Wygrana przez Knock-Out, nowym mistrzem PWTG zostaje: Powerhouse!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                    if (str.equals("TC")) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.VISIBLE);
                            }
                        }, 5000);
                        b4.setText("KONIEC WALKI!");
                        b4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                b1.setVisibility(View.INVISIBLE);
                                b2.setVisibility(View.INVISIBLE);
                                b3.setVisibility(View.INVISIBLE);
                                b4.setVisibility(View.INVISIBLE);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        b1.setVisibility(View.INVISIBLE);
                                        b2.setVisibility(View.INVISIBLE);
                                        b3.setVisibility(View.INVISIBLE);
                                        b4.setVisibility(View.VISIBLE);
                                    }
                                }, 5000);
                                iv.setImageResource(R.drawable.phwin);
                                tv.setText("Wygrana przez Knock-Out, nowym mistrzem PWTG zostaje: Technician!");
                                b4.setText("Przejd?? do podsumowania");
                                b4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getApplicationContext(),Summary.class);
                                        intent.putExtra("difficulty",str1);
                                        intent.putExtra("hpE",hpEnemy.getProgress());
                                        intent.putExtra("hpP",hpPlayer.getProgress());
                                        intent.putStringArrayListExtra("lista Akcji",listOfMoves);
                                        intent.putExtra("sound",sound);
                                        startActivity(intent);
                                    }
                                });}
                        });
                    }
                }
                }
            }
        }
        );
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
    void zmianaTla(int v, String str)
    {
        GifImageView iv = findViewById(R.id.tlo);

            if(str.equals("PH")) {
                switch (v) {
                    case 0: {
                        iv.setImageResource(R.drawable.punchp);
                        break;
                    }
                    case 1: {
                        iv.setImageResource(R.drawable.kickp);
                        break;
                    }
                    case 2: {
                        iv.setImageResource(R.drawable.chopchestp);
                        break;
                    }
                    case 3: {
                        iv.setImageResource(R.drawable.elbowp);
                        break;
                    }
                    case 4: {
                        iv.setImageResource(R.drawable.kneep);
                        break;
                    }
                    case 5: {
                        iv.setImageResource(R.drawable.slapp);
                        break;
                    }
                    case 6: {
                        iv.setImageResource(R.drawable.backelbowp);
                        break;
                    }
                    case 7: {
                        iv.setImageResource(R.drawable.lowkickp);
                        break;
                    }
                    case 8: {
                        iv.setImageResource(R.drawable.gutpunchp);
                        break;
                    }
                    case 9: {
                        iv.setImageResource(R.drawable.headbuttp);
                        break;
                    }
                    case 10: {
                        iv.setImageResource(R.drawable.lariatp);
                        break;
                    }
                    case 11: {
                        iv.setImageResource(R.drawable.scoopslamp);
                        break;
                    }
                    case 12: {
                        iv.setImageResource(R.drawable.suplexp);
                        break;
                    }
                    case 13: {
                        iv.setImageResource(R.drawable.clotheslinep);
                        break;
                    }
                    case 14: {
                        iv.setImageResource(R.drawable.dropkickp);
                        break;
                    }
                    case 15: {
                        iv.setImageResource(R.drawable.ddtp);
                        break;
                    }
                    case 16: {
                        iv.setImageResource(R.drawable.neckbreakerp);
                        break;
                    }
                    case 17: {
                        iv.setImageResource(R.drawable.bigbootp);
                        break;
                    }
                    case 18: {
                        iv.setImageResource(R.drawable.enzugirip);
                        break;
                    }
                    case 19: {
                        iv.setImageResource(R.drawable.shoulderblockp);
                        break;
                    }
                    case 20: {
                        iv.setImageResource(R.drawable.spearp);
                        break;
                    }
                    case 21: {
                        iv.setImageResource(R.drawable.bigsplashp);
                        break;
                    }
                    case 22: {
                        iv.setImageResource(R.drawable.crossbodyp);
                        break;
                    }
                    case 23: {
                        iv.setImageResource(R.drawable.backbreakerp);
                        break;
                    }
                    case 24: {
                        iv.setImageResource(R.drawable.bulldogp);
                        break;
                    }
                    case 25: {
                        iv.setImageResource(R.drawable.chokeslamp);
                        break;
                    }
                    case 26: {
                        iv.setImageResource(R.drawable.firemanscarryp);
                        break;
                    }
                    case 27: {
                        iv.setImageResource(R.drawable.cutterp);
                        break;
                    }
                    case 28: {
                        iv.setImageResource(R.drawable.powerbombp);
                        break;
                    }
                    case 29: {
                        iv.setImageResource(R.drawable.kneelingpiledriverp);
                        break;
                    }
                    default: {
                        iv.setImageResource(R.drawable.phbg);
                        break;
                    }
                }
            }
            else {
                switch (v) {
                    case 0: {
                        iv.setImageResource(R.drawable.puncht);
                        break;
                    }
                    case 1: {
                        iv.setImageResource(R.drawable.kickt);
                        break;
                    }
                    case 2: {
                        iv.setImageResource(R.drawable.chopchestt);
                        break;
                    }
                    case 3: {
                        iv.setImageResource(R.drawable.elbowt);
                        break;
                    }
                    case 4: {
                        iv.setImageResource(R.drawable.kneet);
                        break;
                    }
                    case 5: {
                        iv.setImageResource(R.drawable.slapt);
                        break;
                    }
                    case 6: {
                        iv.setImageResource(R.drawable.backelbowt);
                        break;
                    }
                    case 7: {
                        iv.setImageResource(R.drawable.lowkickt);
                        break;
                    }
                    case 8: {
                        iv.setImageResource(R.drawable.gutpuncht);
                        break;
                    }
                    case 9: {
                        iv.setImageResource(R.drawable.headbuttt);
                        break;
                    }
                    case 10: {
                        iv.setImageResource(R.drawable.lariatt);
                        break;
                    }
                    case 11: {
                        iv.setImageResource(R.drawable.scoopslamt);
                        break;
                    }
                    case 12: {
                        iv.setImageResource(R.drawable.suplext);
                        break;
                    }
                    case 13: {
                        iv.setImageResource(R.drawable.clotheslinet);
                        break;
                    }
                    case 14: {
                        iv.setImageResource(R.drawable.dropkickt);
                        break;
                    }
                    case 15: {
                        iv.setImageResource(R.drawable.ddtt);
                        break;
                    }
                    case 16: {
                        iv.setImageResource(R.drawable.neckbreakert);
                        break;
                    }
                    case 17: {
                        iv.setImageResource(R.drawable.bigboott);
                        break;
                    }
                    case 18: {
                        iv.setImageResource(R.drawable.enzugirit);
                        break;
                    }
                    case 19: {
                        iv.setImageResource(R.drawable.shoulderblockt);
                        break;
                    }
                    case 20: {
                        iv.setImageResource(R.drawable.speart);
                        break;
                    }
                    case 21: {
                        iv.setImageResource(R.drawable.bigsplasht);
                        break;
                    }
                    case 22: {
                        iv.setImageResource(R.drawable.crossbodyt);
                        break;
                    }
                    case 23: {
                        iv.setImageResource(R.drawable.backbreakert);
                        break;
                    }
                    case 24: {
                        iv.setImageResource(R.drawable.bulldogt);
                        break;
                    }
                    case 25: {
                        iv.setImageResource(R.drawable.chokeslamt);
                        break;
                    }
                    case 26: {
                        iv.setImageResource(R.drawable.firemanscarryt);
                        break;
                    }
                    case 27: {
                        iv.setImageResource(R.drawable.cuttert);
                        break;
                    }
                    case 28: {
                        iv.setImageResource(R.drawable.powerbombt);
                        break;
                    }
                    case 29: {
                        iv.setImageResource(R.drawable.kneelingpiledrivert);
                        break;
                    }
                    default: {
                        iv.setImageResource(R.drawable.tcbg);
                        break;
                    }
                }
            }
    }
    String jakiAtak(int v)
    {
        ArrayList<String> listaakcji = new ArrayList<>();
        listaakcji.add(0,"Punch");
        listaakcji.add(1,"Kick");
        listaakcji.add(2,"Chop to chest");
        listaakcji.add(3,"Elbow smash");
        listaakcji.add(4,"Knee");
        listaakcji.add(5,"Slap");
        listaakcji.add(6,"Back elbow");
        listaakcji.add(7,"Low kick");
        listaakcji.add(8,"Gut punch");
        listaakcji.add(9,"Headbutt");

        listaakcji.add(10,"Lariat");
        listaakcji.add(11,"Scoopslam");
        listaakcji.add(12,"Suplex");
        listaakcji.add(13,"Clothesline");
        listaakcji.add(14,"Dropkick");
        listaakcji.add(15,"DDT");
        listaakcji.add(16,"Neckbreaker");
        listaakcji.add(17,"Big boot");
        listaakcji.add(18,"Enzugiri");
        listaakcji.add(19,"Shoulder block");

        listaakcji.add(20,"Spear");
        listaakcji.add(21,"Big splash");
        listaakcji.add(22,"Crossbody");
        listaakcji.add(23,"Backbreaker");
        listaakcji.add(24,"Bulldog");
        listaakcji.add(25,"Chokeslam");
        listaakcji.add(26,"Fireman's carry takeover");
        listaakcji.add(27,"Cutter");
        listaakcji.add(28,"Powerbomb");
        listaakcji.add(29,"Kneeling piledriver");

        String nazwa="";
        nazwa = listaakcji.get(v);
        listaakcji.clear();
        return nazwa;
    }
    /*int poziomTrudnosci(String v,String who,int value) {
        int atak = value;
        if (v.equals("E")) {
            if(who.equals("P"))
            {
                atak=atak+2;
            }
            else
            {
                atak=atak-2;
                if(atak<0)
                {
                    atak=1;
                }
            }
        } else if (v.equals("H")) {
            if(who.equals("E"))
            {
                atak=atak+1;
            }
        }
        return atak;
    }

    int silaAtaku(int value) {
        Random rand = new Random();
        int atak = 0;
        switch (value) {
            case 0: {
                atak = rand.nextInt(3)+3; //3-5 punch
                break;
            }
            case 1: {
                atak = rand.nextInt(4)+3; //3-6 kick
                break;
            }
            case 2: {
                atak = rand.nextInt(2)+3; //3-4 chop(chest)
                break;
            }
            case 3: {
                atak = rand.nextInt(3)+4; //4-6 elbow smash
                break;
            }
            case 4: {
                atak = rand.nextInt(4)+4; //4-7 knee
                break;
            }
            case 5: {
                atak = rand.nextInt(3)+3; //3-5 slap
                break;
            }
            case 6: {
                atak = rand.nextInt(3)+4; //4-6 back elbow
                break;
            }
            case 7: {
                atak = rand.nextInt(3)+5; //3-7 low kick
                break;
            }
            case 8: {
                atak = rand.nextInt(2)+3; //3-4 gut punch
                break;
            }
            case 9: {
                atak = rand.nextInt(2)+4; //4-5 headbutt
                break;
            }
            case 10: {
                atak = rand.nextInt(4)+7; //7-10 lariat
                break;
            }
            case 11: {
                atak = rand.nextInt(3)+7; //7-9 scoopslam
                break;
            }
            case 12: {
                atak = rand.nextInt(2)+9; //9-10 suplex
                break;
            }
            case 13: {
                atak = rand.nextInt(4)+6; //6-9 clothesline
                break;
            }
            case 14: {
                atak = rand.nextInt(3)+8; //8-10 dropkick
                break;
            }
            case 15: {
                atak = rand.nextInt(6)+6; //6-11 DDT
                break;
            }
            case 16: {
                atak = rand.nextInt(4)+7; //7-10 neckbreaker
                break;
            }
            case 17: {
                atak = rand.nextInt(7)+5; //5-11 big boot
                break;
            }
            case 18: {
                atak = rand.nextInt(2)+10; //10-11 enzugiri
                break;
            }
            case 19: {
                atak = rand.nextInt(3)+6; //6-8 shoulder block
                break;
            }
            case 20: {
                atak = rand.nextInt(5)+13; //13-17 spear
                break;
            }
            case 21: {
                atak = rand.nextInt(3)+12; //12-14 big splash
                break;
            }
            case 22: {
                atak = rand.nextInt(3)+13; //13-15 crossbody
                break;
            }
            case 23: {
                atak = rand.nextInt(4)+12; //12-15 backbreaker
                break;
            }
            case 24: {
                atak = rand.nextInt(6)+11; //11-16 bulldog
                break;
            }
            case 25: {
                atak = rand.nextInt(3)+14; //14-16 chokeslam
                break;
            }
            case 26: {
                atak = rand.nextInt(4)+15; //15-19 fireman's carry takeover
                break;
            }
            case 27: {
                atak = rand.nextInt(2)+16; //16-17 cutter
                break;
            }
            case 28: {
                atak = rand.nextInt(6)+13; //13-18 powerbomb
                break;
            }
            case 29: {
                atak = rand.nextInt(7)+15; //15-21 kneeling piledriver
                break;
            }

            }
        return atak;
    }*/
}


