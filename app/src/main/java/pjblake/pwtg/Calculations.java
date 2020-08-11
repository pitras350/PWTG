package pjblake.pwtg;

import java.util.Random;

public class Calculations {
    String difficulty;
    String whosAttacking;

    public Calculations(String difficulty, String whosAttacking)
    {
        this.difficulty = difficulty;
        this.whosAttacking = whosAttacking;
    }

    int attackCalculator(int strength) {
        int attack = strength;
        if (difficulty.equals("E")) {
            if(whosAttacking.equals("P"))
            {
                attack=attack+2;
            }
            else
            {
                attack=attack-2;
                if(attack<0)
                {
                    attack=1;
                }
            }
        } else if (difficulty.equals("H")) {
            if(whosAttacking.equals("E"))
            {
                attack=attack+1;
            }
        }
        return attack;
    }
    int attackValue(int number)
    {
        Random rand = new Random();
        int atak = 0;
        switch (number) {
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
    }

}

