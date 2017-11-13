package com.pepg.creativestone.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pepg.creativestone.Data.Card;
import com.pepg.creativestone.GameActivity;
import com.pepg.creativestone.R;

import java.util.ArrayList;

import static com.pepg.creativestone.GameActivity.clickableCard;
import static com.pepg.creativestone.GameActivity.isAttacking;
import static com.pepg.creativestone.GameActivity.phase;
import static com.pepg.creativestone.GameActivity.phase1Click;
import static com.pepg.creativestone.GameActivity.phase2Attack;
import static com.pepg.creativestone.GameActivity.phase2AttackSelect;
import static com.pepg.creativestone.GameActivity.phase2Summon;
import static com.pepg.creativestone.GameActivity.selectedCard;

/**
 * Created by pengu on 2017-08-10.
 */

public class ListRcvAdapter extends RecyclerView.Adapter<ListRcvAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<Card> data;
    private boolean isField;
    private int side;

    public ListRcvAdapter(ArrayList<Card> data, Activity activity, int side, boolean isField) {
        this.activity = activity;
        this.data = data;
        this.side = side;
        this.isField = isField;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumA, tvNumB;
        LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.item_layout);
            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);
            tvNumA = (TextView) itemView.findViewById(R.id.item_tv_numA);
            tvNumB = (TextView) itemView.findViewById(R.id.item_tv_numB);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvNumA.setText(data.get(position).getNumA() + "");
        holder.tvNumB.setText(data.get(position).getNumB() + "");
        if (clickableCard) {
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (side == GameActivity.side) {
                        if (phase == 1) {
                            selectedCard = position;
                            clickableCard = false;
                            phase1Click();
                        } else if (phase == 2) {
                            if (!isAttacking) {
                                cardView(position);
                            }
                        }
                    } else if (isAttacking) {
                        selectedCard = position;
                        clickableCard = false;
                        phase2AttackSelect();
                    }
                }
            });
        }
    }

    private void removeItemView(int position) {
        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, dbManager.getSize()); // 지워진 만큼 다시 채워넣기.
    }

    private void cardView(final int position) {
        LayoutInflater li = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout updateLayout = (LinearLayout) li.inflate(R.layout.dialog_item, null);
        final TextView name = (TextView) updateLayout.findViewById(R.id.dialog_tv_name);
        final TextView numA = (TextView) updateLayout.findViewById(R.id.dialog_tv_numA);
        final TextView numB = (TextView) updateLayout.findViewById(R.id.dialog_tv_numB);
        final Button btn1 = (Button) updateLayout.findViewById(R.id.dialog_btn1);
        final Button btn2 = (Button) updateLayout.findViewById(R.id.dialog_btn2);
        final Button btn3 = (Button) updateLayout.findViewById(R.id.dialog_btn3);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final AlertDialog dialog;

        name.setText(data.get(position).getName());
        numA.setText(data.get(position).getNumA() + "");
        numB.setText(data.get(position).getNumB() + "");

        builder.setView(updateLayout);
        dialog = builder.create();
        dialog.show();

        btn2.setVisibility(View.GONE);
        if (isField) {
            btn1.setText("공격");
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCard = position;
                    clickableCard = false;
                    phase2Attack();
                    dialog.dismiss();
                }
            });
        } else {
            btn1.setText("소환");
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCard = position;
                    clickableCard = false;
                    phase2Summon();
                    dialog.dismiss();
                }
            });
        }
        btn3.setText("취소");
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}