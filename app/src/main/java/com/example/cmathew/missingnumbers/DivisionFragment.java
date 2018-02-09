package com.example.cmathew.missingnumbers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DivisionFragment extends Fragment {
    private EditText valueEntry;
    private EditText factorEntry;
    private TextView quotientDisplay;
    private Button calculateButton;

    public DivisionFragment() {
        // Required empty public constructor
    }

    public static DivisionFragment newInstance() {
        DivisionFragment fragment = new DivisionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_division, container, false);

        this.valueEntry = view.findViewById(R.id.dividing_value_entry);
        this.factorEntry = view.findViewById(R.id.dividing_divisor_entry);
        this.quotientDisplay = view.findViewById(R.id.dividing_quotient_display);

        this.calculateButton = view.findViewById(R.id.dividing_calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt(valueEntry.getText().toString());
                int factor = Integer.parseInt(factorEntry.getText().toString());
                int passCount = 0;
                while (value >= factor) {
                    value -= factor;
                    passCount ++;
                }

                quotientDisplay.setText(String.format("Quotient: %d Remainder: %d", passCount, value));
            }
        });

        return view;
    }

}
