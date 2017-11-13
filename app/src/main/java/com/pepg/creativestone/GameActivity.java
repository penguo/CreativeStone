package com.pepg.creativestone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.pepg.creativestone.Adapter.ListRcvAdapter;
import com.pepg.creativestone.Data.Card;
import com.pepg.creativestone.Data.GameSystem;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    RecyclerView rcvRed, rcvBlue, rcvRedField, rcvBlueField;
    static ListRcvAdapter listRcvAdapterRed, listRcvAdapterBlue, listRcvAdapterRedField, listRcvAdapterBlueField;
    static TextView tvLog, tvLogNow, tvSpRed, tvSpBlue;
    public static ArrayList<Card> cardsRed, cardsBlue, cardsRedField, cardsBlueField;
    public static int turn, side, spRed, spBlue, gameLogCheck, i;
    static GameSystem gameSystem;
    static Button btn1, btn2, btn3;
    public static boolean clickableCard, isAttacking;
    public static int selectedCard, phase, attackingCard;
    static ScrollView scrollview;
    static ArrayList<String> gameLog;
    public static Card recentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLog = new ArrayList<>();
        gameLog.add("\n");
        gameLog.add("\n");
        gameLog.add("\n");
        gameLog.add("\n");
        gameLog.add("\n");
        gameLogCheck = 0;
        tvLog = (TextView) findViewById(R.id.game_tv_log);
        tvLogNow = (TextView) findViewById(R.id.game_tv_lognow);
        tvSpRed = (TextView) findViewById(R.id.game_tv_sp_red);
        tvSpBlue = (TextView) findViewById(R.id.game_tv_sp_blue);
        rcvRed = (RecyclerView) findViewById(R.id.game_rcv_red);
        rcvBlue = (RecyclerView) findViewById(R.id.game_rcv_blue);
        rcvRedField = (RecyclerView) findViewById(R.id.game_rcv_redfield);
        rcvBlueField = (RecyclerView) findViewById(R.id.game_rcv_bluefield);
        btn1 = (Button) findViewById(R.id.game_btn1);
        btn2 = (Button) findViewById(R.id.game_btn2);
        btn3 = (Button) findViewById(R.id.game_btn3);
        scrollview = (ScrollView) findViewById(R.id.game_scrollview);

        cardsRed = new ArrayList<>();
        cardsBlue = new ArrayList<>();
        cardsRedField = new ArrayList<>();
        cardsBlueField = new ArrayList<>();

        rcvRed.setLayoutManager(new LinearLayoutManager(this));
        rcvBlue.setLayoutManager(new LinearLayoutManager(this));
        rcvRedField.setLayoutManager(new LinearLayoutManager(this));
        rcvBlueField.setLayoutManager(new LinearLayoutManager(this));

        listRcvAdapterRed = new ListRcvAdapter(cardsRed, this, 0, false);
        listRcvAdapterBlue = new ListRcvAdapter(cardsBlue, this, 1, false);
        listRcvAdapterRedField = new ListRcvAdapter(cardsRedField, this, 0, true);
        listRcvAdapterBlueField = new ListRcvAdapter(cardsBlueField, this, 1, true);
        rcvRed.setAdapter(listRcvAdapterRed);
        rcvBlue.setAdapter(listRcvAdapterBlue);
        rcvRedField.setAdapter(listRcvAdapterRedField);
        rcvBlueField.setAdapter(listRcvAdapterBlueField);
        tvSpRed.setText(spRed + "");
        tvSpBlue.setText(spBlue + "");
        clickableCard = false;
        gameSystem = new GameSystem(cardsRed, cardsBlue);
        gameStart();
    }

    public static void addTvLog(String log) {
        tvLogNow.setText(log);
        gameLog.add("\n-" + log);
        while (gameLog.size() > 5) {
            gameLog.remove(0);
        }
        tvLog.setText(gameLog.get(0).toString() + gameLog.get(1).toString() + gameLog.get(2).toString() + gameLog.get(3).toString() + gameLog.get(4).toString());
        scrollview.fullScroll(ScrollView.FOCUS_DOWN);
    }

    public static void updateSpView() {
        tvSpRed.setText(spRed + "");
        tvSpBlue.setText(spBlue + "");
    }

    public static void gameStart() {
        addTvLog("게임 시작.");
        addTvLog("카드를 분배합니다.");
        gameSystem.drawCard(0);
        gameSystem.drawCard(1);
        gameSystem.drawCard(0);
        gameSystem.drawCard(1);
        gameSystem.drawCard(0);
        gameSystem.drawCard(1);
        gameSystem.addCard(0,0,0,0);
        side = 0;
        phaseStart();
    }

    public static void phaseStart() {
        if (side == 0) {
            turn++;
        }
        addTvLog(turn + "번째 턴. " + getTeam() + "의 차례입니다.");
        int turnSp = 10 + (turn * 5);
        if (side == 0) {
            spRed = turnSp;
            for (i = 0; i < cardsRedField.size(); i++) {
                if(cardsRedField.get(i).isFieldPhasestartEff()){
                    gameSystem.checkFieldPhasestartEff(cardsRedField.get(i), 0);
                }
            }
        } else if (side == 1) {
            spBlue = turnSp;
            for (i = 0; i < cardsBlueField.size(); i++) {
                if(cardsBlueField.get(i).isFieldPhasestartEff()){
                    gameSystem.checkFieldPhasestartEff(cardsBlueField.get(i), 0);
                }
            }
        }
        updateSpView();
        phase1();
    }

    public static void phase1() {
        phase = 1;
        resetButton();
        resetRcv();
        btn1.setText("카드 1장 뽑기");
        btn2.setText("카드 1장 교체");
        btn3.setVisibility(View.GONE);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTvLog("카드를 1장 뽑았습니다.");
                gameSystem.drawCard(side);
                if (recentCard.isDrawEff()) {
                    gameSystem.checkDrawEff(recentCard, side);
                }
                phase2();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                addTvLog("교체할 카드를 선택하세요.");
                clickableCard = true;
                if (side == 0) {
                    listRcvAdapterRed.notifyDataSetChanged();
                } else if (side == 1) {
                    listRcvAdapterBlue.notifyDataSetChanged();
                }
            }
        });
    }

    public static void phase1Click() {
        if (side == 0) {
            cardsRed.remove(selectedCard);
            gameSystem.drawCard(side);
            listRcvAdapterRed.notifyDataSetChanged();
        } else if (side == 1) {
            cardsBlue.remove(selectedCard);
            gameSystem.drawCard(side);
            listRcvAdapterBlue.notifyDataSetChanged();
        }
        addTvLog("카드를 버리고 새로 뽑았습니다.");
        if (recentCard.isDrawEff()) {
            gameSystem.checkDrawEff(recentCard, side);
        }
        phase2();
    }

    public static void phase2() {
        phase = 2;
        resetButton();
        clickableCard = true;
        if (side == 0) {
            for (i = 0; i < cardsRedField.size(); i++) {
                if(cardsRedField.get(i).isFieldEff()){
                    gameSystem.checkFieldEff(cardsRedField.get(i), 0);
                }
            }
            listRcvAdapterRed.notifyDataSetChanged();
            listRcvAdapterRedField.notifyDataSetChanged();
        } else if (side == 1) {
            for (i = 0; i < cardsBlueField.size(); i++) {
                if(cardsBlueField.get(i).isFieldEff()){
                    gameSystem.checkFieldEff(cardsBlueField.get(i), 0);
                }
            }
            listRcvAdapterBlue.notifyDataSetChanged();
            listRcvAdapterBlueField.notifyDataSetChanged();
        }
        addTvLog("카드를 선택하여 행동하거나 턴을 넘기세요.");
        btn1.setText("턴 넘기기");
        btn2.setVisibility(View.GONE);
        btn3.setVisibility(View.GONE);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (side == 0) {
                    side = 1;
                } else if (side == 1) {
                    side = 0;
                }
                phaseStart();
            }
        });
    }

    public static void phase2Attack() {
        if (side == 0) {
            listRcvAdapterRed.notifyDataSetChanged();
            listRcvAdapterRedField.notifyDataSetChanged();
        } else if (side == 1) {
            listRcvAdapterBlue.notifyDataSetChanged();
            listRcvAdapterBlueField.notifyDataSetChanged();
        }
        attackingCard = selectedCard;
        addTvLog("공격할 상대를 선택하세요!");
        clickableCard = true;
        isAttacking = true;
        if (side == 0) {
            listRcvAdapterBlueField.notifyDataSetChanged();
        } else if (side == 1) {
            listRcvAdapterRedField.notifyDataSetChanged();
        }
    }

    public static void phase2AttackSelect() {
        if (side == 0) {
            listRcvAdapterBlue.notifyDataSetChanged();
            listRcvAdapterBlueField.notifyDataSetChanged();
            addTvLog(cardsRedField.get(attackingCard).getName() + "의 공격!");
            gameSystem.attack(cardsRedField.get(attackingCard), cardsBlueField.get(selectedCard));
            if (cardsBlueField.get(selectedCard).getNumB() <= 0) {
                addTvLog(cardsBlueField.get(selectedCard).getName() + " 죽었다!");
                if (cardsBlueField.get(selectedCard).isDeathEff()) {
                    gameSystem.checkDeathEff(cardsBlueField.get(selectedCard), 1);
                }
                cardsBlueField.remove(selectedCard);
            }
            if (cardsRedField.get(attackingCard).getNumB() <= 0) {
                addTvLog(cardsRedField.get(attackingCard).getName() + " 죽었다!");
                if (cardsRedField.get(attackingCard).isDeathEff()) {
                    gameSystem.checkDeathEff(cardsRedField.get(attackingCard), 0);
                }
                cardsRedField.remove(attackingCard);
            }
        } else if (side == 1) {
            listRcvAdapterRed.notifyDataSetChanged();
            listRcvAdapterRedField.notifyDataSetChanged();
            addTvLog(cardsRedField.get(attackingCard).getName() + "의 공격!");
            gameSystem.attack(cardsBlueField.get(attackingCard), cardsRedField.get(selectedCard));
            if (cardsRedField.get(selectedCard).getNumB() <= 0) {
                addTvLog(cardsRedField.get(selectedCard).getName() + " 죽었다!");
                if (cardsRedField.get(selectedCard).isDeathEff()) {
                    gameSystem.checkDeathEff(cardsRedField.get(selectedCard), 0);
                }
                cardsRedField.remove(selectedCard);
            }
            if (cardsBlueField.get(attackingCard).getNumB() <= 0) {
                addTvLog(cardsBlueField.get(attackingCard).getName() + " 죽었다!");
                if (cardsBlueField.get(attackingCard).isDeathEff()) {
                    gameSystem.checkDeathEff(cardsBlueField.get(attackingCard), 1);
                }
                cardsBlueField.remove(attackingCard);
            }
        }
        isAttacking = false;
        phase2();
    }

    public static void phase2Summon() {
        if (side == 0) {
            if (spRed >= cardsRed.get(selectedCard).getNeedSp()) {
                spRed -= cardsRed.get(selectedCard).getNeedSp();
                cardsRedField.add(cardsRed.get(selectedCard));
                cardsRed.remove(selectedCard);
                listRcvAdapterRed.notifyDataSetChanged();
                listRcvAdapterRedField.notifyDataSetChanged();
                updateSpView();
                addTvLog("소환되었습니다.");
            } else {
                addTvLog("SP가 부족합니다.");
            }
        } else if (side == 1) {
            if (spBlue >= cardsBlue.get(selectedCard).getNeedSp()) {
                spBlue -= cardsBlue.get(selectedCard).getNeedSp();
                cardsBlueField.add(cardsBlue.get(selectedCard));
                cardsBlue.remove(selectedCard);
                listRcvAdapterBlue.notifyDataSetChanged();
                listRcvAdapterBlueField.notifyDataSetChanged();
                updateSpView();
                addTvLog("소환되었습니다.");
            } else {
                addTvLog("SP가 부족합니다.");
            }
        }
        phase2();
    }


    public static String getTeam() {
        if (side == 0) {
            return "Red";
        } else if (side == 1) {
            return "Blue";
        } else {
            return "Unknown";
        }
    }

    public static void resetButton() {
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn1.setOnClickListener(null);
        btn2.setOnClickListener(null);
        btn3.setOnClickListener(null);
    }

    public static void resetRcv() {
        clickableCard = false;
        isAttacking = false;
        listRcvAdapterRed.notifyDataSetChanged();
        listRcvAdapterRedField.notifyDataSetChanged();
        listRcvAdapterBlue.notifyDataSetChanged();
        listRcvAdapterBlueField.notifyDataSetChanged();
    }
}