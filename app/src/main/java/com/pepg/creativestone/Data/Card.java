package com.pepg.creativestone.Data;

/**
 * Created by pengu on 2017-11-13.
 */

public class Card {

    String name;
    int numA;
    int numB;
    String detail;
    int specialCardNo;
    boolean drawEff, handEff, fieldEff, deathEff, fieldPhasestartEff;

    /**
     * @param type 미니언  기술
     * @param numA 공격력  수치
     * @param numB 체력    타입
     */

    public Card(int type, int numA, int numB) {
        name = "";
        detail = "";
        specialCardNo = -1;
        this.numA = numA;
        this.numB = numB;
        if (type == 0) {
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
                    detail = "적에게 피해를 주고 사라진다.";
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

        /**
         * 기술
         * */
        if (type == 1) {
            name += "*";
            switch (numA) { //수치
                case (0):
                    name += "허상의";
                    break;
                case (1):
                    name += "약한";
                    break;
                case (2):
                    name += "평범한";
                    break;
                case (3):
                    name += "위력적인";
                    break;
                case (4):
                    name += "위력적인";
                    break;
                case (5):
                    name += "강력한";
                    break;
                case (6):
                    name += "강력한";
                    break;
                case (7):
                    name += "날카로운";
                    break;
                case (8):
                    name += "파괴적인";
                    break;
                case (9):
                    name += "우주적인";
                    break;
                default:
                    name += "존재할수없는";
                    break;
            }
            name += " ";
            switch (numB) { //타입
                case (0):
                    name = "*소멸의 화살";
                    detail = "미니언이 맞을 경우 무조건 죽는다.";
                    break;
                case (1):
                    name += "번개";
                    break;
                case (2):
                    name += "번개";
                    break;
                case (3):
                    name += "번개";
                    break;
                case (4):
                    name += "번개";
                    break;
                case (5):
                    name += "번개";
                    break;
                case (6):
                    name += "번개";
                    break;
                case (7):
                    name += "번개";
                    break;
                case (8):
                    name += "지진";
                    break;
                case (9):
                    name += "메테오";
                    break;
                default:
                    name += "데이터";
                    break;
            }
        }
        checkSpecialCard(type);
    }

    private void checkSpecialCard(int type) {
        if (type == 0) {
            if (numA == 0 && numB == 0) {
                specialCardNo = 1;
                name = "창조의 정령";
                detail = "이 카드가 파괴될 때, 카드 주인이 '치명적인' 카드를 2개 얻는다.";
                deathEff = true;
            }
            if (numA == 4 && numB == 4) {
                specialCardNo = 2;
                name = "황금 고블린";
                detail = "이 카드가 필드에 존재할 때, 자신의 턴 시작마다 각자 카드를 1개 얻는다. 이 카드가 파괴될 때, 카드 주인이 황금 카드를 얻는다(미구현).";
                fieldPhasestartEff = true;
            }
            if (numA == 9 && numB == 9) {
                specialCardNo = 3;
                name = "드래곤 로드";
                detail = "이 카드는 자신의 턴 시작마다 체력이 2배가 된다.";
                fieldPhasestartEff = true;
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

    public String getDetail() {
        return detail;
    }

    public int getSpecialCardNo() {
        return specialCardNo;
    }

    public boolean isDrawEff() {
        return drawEff;
    }

    public boolean isHandEff() {
        return handEff;
    }

    public boolean isFieldEff() {
        return fieldEff;
    }

    public boolean isDeathEff() {
        return deathEff;
    }

    public boolean isFieldPhasestartEff() {
        return fieldPhasestartEff;
    }
}
