package com.marwaeltayeb.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<History> {

    public HistoryAdapter(Context context, ArrayList<History> historyList) {
        super(context, 0, historyList);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.history_list_item, parent, false);
        }

        History currentHistoryRecord = getItem(position);

        TextView firstNumber =listItemView.findViewById(R.id.txt_first_num);
        firstNumber.setText(currentHistoryRecord.getFirstNumber());

        TextView operator =listItemView.findViewById(R.id.txt_operator);
        operator.setText(currentHistoryRecord.getOperator());

        TextView secondNumber =listItemView.findViewById(R.id.txt_second_num);
        secondNumber.setText(currentHistoryRecord.getSecondNumber());

        TextView result =listItemView.findViewById(R.id.txt_result);
        result.setText(currentHistoryRecord.getResult());

        return listItemView;
    }

}

