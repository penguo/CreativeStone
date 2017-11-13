package com.pepg.creativestone.Data;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pengu on 2017-11-13.
 */

public class GameSystem {
    ArrayList<Card> cardsRed;
    ArrayList<Card> cardsBlue;

    public GameSystem(ArrayList<Card> cardsRed, ArrayList<Card> cardsBlue) {
        this.cardsRed = cardsRed;
        this.cardsBlue = cardsBlue;
    }

    public void drawCard(int side) {
        Random random = new Random();
        int type = random.nextInt(2);
        int numA = random.nextInt(10);
        int numB = random.nextInt(10);
        if (side == 0) {
            cardsRed.add(new Card(type, numA, numB));
        } else if (side == 1) {
            cardsBlue.add(new Card(type, numA, numB));
        }
    }

    public void attack(Card atkCard, Card defCard) {
        defCard.setNumB(defCard.getNumB() - atkCard.getNumA());
        atkCard.setNumB(atkCard.getNumB() - defCard.getNumA());
    }
}
