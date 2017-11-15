package com.pepg.creativestone.Data;

/**
 * Created by pengu on 2017-11-13.
 */

public class Card {

    String name;
    int numA, numB, type, castTarget, needSp, moveChance, moveChanceMax;
    String detail;
    int specialCardNo;
    boolean drawEff, handEff, fieldEff, deathEff, fieldPhasestartEff, attackingEff, defensingEff;

    /**
     * @param inputType 미니언  기술
     * @param inputNumA 공격력  수치
     * @param inputNumB 체력    타입
     */

    public Card(int inputType, int inputNumA, int inputNumB) {
        name = "";
        detail = "";
        specialCardNo = -1;
        type = inputType;
        numA = inputNumA;
        numB = inputNumB;
        moveChanceMax = 1;
        needSp = (numA + numB) / 3 + 1;

        if (!checkSpecialCard(type)) {
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
                        numB = 1;
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
                moveChanceMax = 1;
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
                        castTarget = 1;
                        break;
                    case (1):
                        name = "*수호의 축복";
                        castTarget = 3;
                        break;
                    case (2):
                        name = "*신성한 축복";
                        castTarget = 3;
                        break;
                    case (3):
                        name += "필드";
                        castTarget = 5;
                        fieldEff = true;
                        break;
                    case (4):
                        name += "화살";
                        castTarget = 1;
                        break;
                    case (5):
                        name += "칼날";
                        castTarget = 1;
                        break;
                    case (6):
                        name += "번개";
                        castTarget = 1;
                        break;
                    case (7):
                        name += "번개";
                        castTarget = 1;
                        break;
                    case (8):
                        name += "지진";
                        castTarget = 2;
                        break;
                    case (9):
                        name += "메테오";
                        castTarget = 2;
                        break;
                    default:
                        name += "데이터";
                        break;
                }
            }
        }
    }


    private boolean checkSpecialCard(int type) {
        if (type == 0) {
            if (numA == 0 && numB == 0) {
                specialCardNo = 1;
                name = "창조의 정령";
                detail = "이 카드가 파괴될 때, 카드 주인이 '치명적인' 카드를 2개 얻는다.";
                deathEff = true;
                return true;
            }
            if (numA == 4 && numB == 4) {
                specialCardNo = 2;
                name = "황금 고블린";
                detail = "이 카드가 필드에 존재할 때, 자신의 턴 시작마다 각자 카드를 1개 얻는다. 이 카드가 파괴될 때, 카드 주인이 황금 카드를 얻는다(미구현).";
                fieldPhasestartEff = true;
                return true;
            }
            if (numA == 9 && numB == 9) {
                specialCardNo = 3;
                name = "드래곤 로드";
                detail = "이 카드는 자신의 턴 시작마다 체력이 2배가 된다.";
                fieldPhasestartEff = true;
                return true;
            }
            if (numA == 3 && numB == 8) {
                specialCardNo = 4;
                name = "고대 골렘";
                detail = "이 카드가 물리적 피해를 입을 때, '소형 골렘'을 필드에 소환한다.";
                attackingEff = true;
                defensingEff = true;
                return true;
            }
        }
        if (type == 1) {

        }
        return false;
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

    public void setMoveChance(int i) {
        moveChance = i;
    }

    public int getMoveChance() {
        return moveChance;
    }

    public void setMoveChanceMax(int i) {
        moveChanceMax = i;
    }

    public int getMoveChanceMax() {
        return moveChanceMax;
    }

    public void resetMoveChance() {
        moveChance = moveChanceMax;
    }

    public int getNeedSp() {
        return needSp;
    }

    public String getDetail() {
        return detail;
    }

    public int getSpecialCardNo() {
        return specialCardNo;
    }

    public int getType() {
        return type;
    }

    public int getCastTarget() {
        return castTarget;
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

    public boolean isAttackingEff() {
        return attackingEff;
    }

    public boolean isDefensingEff() {
        return defensingEff;
    }
}
