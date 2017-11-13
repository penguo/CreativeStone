package com.pepg.creativestone.Data;

import java.util.ArrayList;
import java.util.Random;

import static com.pepg.creativestone.GameActivity.addTvLog;
import static com.pepg.creativestone.GameActivity.recentCard;

/**
 * Created by pengu on 2017-11-13.
 */

public class GameSystem {
    ArrayList<Card> cardsRed;
    ArrayList<Card> cardsBlue;
    int type, numA, numB;
    Random random = new Random();

    public GameSystem(ArrayList<Card> cardsRed, ArrayList<Card> cardsBlue) {
        this.cardsRed = cardsRed;
        this.cardsBlue = cardsBlue;
    }

    public void drawCard(int side) {
        type = random.nextInt(2);
        numA = random.nextInt(10);
        numB = random.nextInt(10);
        recentCard = new Card(type, numA, numB);
        if (side == 0) {
            cardsRed.add(recentCard);
        } else if (side == 1) {
            cardsBlue.add(recentCard);
        }
    }

    public void addCard(int side, int type, int numA, int numB) {
        recentCard = new Card(type, numA, numB);
        if (side == 0) {
            cardsRed.add(recentCard);
        } else if (side == 1) {
            cardsBlue.add(recentCard);
        }
    }

    public void attack(Card atkCard, Card defCard) {
        defCard.setNumB(defCard.getNumB() - atkCard.getNumA());
        atkCard.setNumB(atkCard.getNumB() - defCard.getNumA());
    }

    public void checkDrawEff(Card drawCard, int side) {
        if (drawCard.getNumA() == 0 && drawCard.getNumB() == 0) {

        }
    }

    public void checkHandEff(Card handCard, int side) {
        if (handCard.getNumA() == 0 && handCard.getNumB() == 0) {

        }
    }

    public void checkFieldEff(Card fieldCard, int side) {
    }

    public void checkFieldPhasestartEff(Card fieldCard, int side) {
        if (fieldCard.getSpecialCardNo() == 2) {
            drawCard(0);
            drawCard(1);
            addTvLog("필드에 있는 '황금 고블린'의 효과로 카드를 1장 획득합니다.");
        }
        if (fieldCard.getSpecialCardNo() == 3) {
            fieldCard.setNumB(fieldCard.getNumB() * 2);
            addTvLog("필드에 있는 '드래곤 로드'가 자신의 효과로 체력이 2배가 됩니다.");
        }
    }

    public void checkDeathEff(Card deathCard, int side) {
        if (deathCard.getSpecialCardNo() == 1) {
            addCard(side, 0, 9, random.nextInt(10));
            addCard(side, 0, 9, random.nextInt(10));
            addTvLog("'창조의 정령'의 효과로 '치명적인' 카드를 2장 획득합니다.");
        }
    }

}
