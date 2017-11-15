package com.pepg.creativestone.Data;

import java.util.ArrayList;
import java.util.Random;

import static com.pepg.creativestone.GameActivity.addTvLog;
import static com.pepg.creativestone.GameActivity.hpBlue;
import static com.pepg.creativestone.GameActivity.hpRed;
import static com.pepg.creativestone.GameActivity.recentCard;
import static com.pepg.creativestone.GameActivity.updateHpView;

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
        type = random.nextInt(3);
        numA = random.nextInt(10);
        numB = random.nextInt(10);
        if (type >= 2) {
            type = 0;
        }
        addCard(side, type, numA, numB);
    }

    public void changeCard(int side, Card selectedCard) {
        type = selectedCard.getType();
        int recentNumA = selectedCard.getNumA();
        int recentNumB = selectedCard.getNumB();
        numA = recentNumA + random.nextInt(10 - recentNumA);
        numB = recentNumB + random.nextInt(10 - recentNumB);
        addCard(side, type, numA, numB);
    }

    public void addCard(int side, int type, int numA, int numB) {
        recentCard = new Card(type, numA, numB);
        if (side == 0) {
            cardsRed.add(recentCard);
        } else if (side == 1) {
            cardsBlue.add(recentCard);
        }
    }

    public void attack(Card atkCard, Card defCard, int atkSide) {
        checkAttackingEff(atkCard, atkSide);
        if(atkSide==0){
            checkDefensingEff(defCard, 1);
        }else{
            checkDefensingEff(defCard, 0);
        }
        defCard.setNumB(defCard.getNumB() - atkCard.getNumA());
        atkCard.setNumB(atkCard.getNumB() - defCard.getNumA());
    }

    public void attackDirect(Card atkCard, int atkSide) {
        if(atkSide==0){
            checkAttackingEff(atkCard, 0);
        }else{
            checkAttackingEff(atkCard, 1);
        }
        if (atkSide == 0) {
            hpBlue -= atkCard.getNumA();
        } else if (atkSide == 1) {
            hpRed -= atkCard.getNumA();
        }
        addTvLog("직접 공격!");
        updateHpView();
    }

    public void casting(int type, Card castCard, ArrayList<Card> targetCards) {
        switch (type) {
            case (2):
                for (int i = 0; i < targetCards.size(); i++) {
                    targetCards.get(i).setNumB(targetCards.get(i).getNumB() - castCard.getNumA());
                }
                addTvLog("적 모두에게 " + castCard.getNumA() + " 광역 데미지!!");
                break;
            case (4):
                addTvLog("ERROR: gameSystem casting error");
                break;
        }
    }

    public void castingTarget(int type, Card castCard, Card targetCard) {
        switch (type) {
            case (1):
                switch (castCard.getNumB()) {
                    case (0):
                        targetCard.setNumB(0);
                        break;
                    case (4):
                        targetCard.setNumB(targetCard.getNumB() - castCard.getNumA());
                        break;
                    case (5):
                        targetCard.setNumB(targetCard.getNumB() - castCard.getNumA());
                        break;
                    case (6):
                        targetCard.setNumB(targetCard.getNumB() - castCard.getNumA());
                        break;
                    case (7):
                        targetCard.setNumB(targetCard.getNumB() - castCard.getNumA());
                        break;
                }
                break;
            case (3):
                switch (castCard.getNumB()) {
                    case (1):
                        targetCard.setNumB(targetCard.getNumB() + castCard.getNumA());
                        break;
                    case (2):
                        targetCard.setNumA(targetCard.getNumA() + castCard.getNumA());
                        break;
                }
                break;
        }
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

    public void checkAttackingEff(Card atkCard, int side){
        if(atkCard.getSpecialCardNo() == 4){

        }
    }

    public void checkDefensingEff(Card defCard, int side){

    }

}
