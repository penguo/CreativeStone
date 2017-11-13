package com.pepg.creativestone.Data;

/**
 * Created by pengu on 2017-11-13.
 */

public class Card {

    String name;
    int numA;
    int numB;

    /**
     * @param type 미니언  기술
     * @param numA 공격력  수치
     * @param numB 체력    타입
     */

    public Card(int type, int numA, int numB) {
        name = "";
        this.numA = numA;
        this.numB = numB;
        if (type == 0 || type == 1) { //is 미니언
            switch (numA) { //공격력
                case (0):
                    name += "";
                    break;
                case (1):
                    name += "허약한";
                    break;
                case (2):
                    name += "허약한";
                    break;
                case (3):
                    name += "평범한";
                    break;
                case (4):
                    name += "평범한";
                    break;
                case (5):
                    name += "강인한";
                    break;
                case (6):
                    name += "강인한";
                    break;
                case (7):
                    name += "흉포한";
                    break;
                case (8):
                    name += "흉포한";
                    break;
                case (9):
                    name += "치명적인";
                    break;
                default:
                    name += "존재할수없는";
                    break;
            }
            name += " ";
            switch (numB) { //체력
                case (0):
                    name += "정령";
                    break;
                case (1):
                    name += "슬라임";
                    break;
                case (2):
                    name += "코볼트";
                    break;
                case (3):
                    name += "노움";
                    break;
                case (4):
                    name += "고블린";
                    break;
                case (5):
                    name += "트롤";
                    break;
                case (6):
                    name += "오우거";
                    break;
                case (7):
                    name += "거인";
                    break;
                case (8):
                    name += "골렘";
                    break;
                case (9):
                    name += "드래곤";
                    break;
                default:
                    name += "데이터";
                    break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setNumA(int numA) {
        this.numA = numA;
    }

    public int getNumA() {
        return numA;
    }

    public void setNumB(int numB) {
        this.numB = numB;
    }

    public int getNumB() {
        return numB;
    }

    public int getNeedSp() {
        return numA + numB;
    }
}
