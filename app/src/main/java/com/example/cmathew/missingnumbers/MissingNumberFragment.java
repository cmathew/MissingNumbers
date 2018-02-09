package com.example.cmathew.missingnumbers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MissingNumberFragment extends Fragment {
    private FrameLayout frameLayout;
    private EditText text;
    private Button gapButton;
    private TextView resultDisplay;

    public MissingNumberFragment() {
        // Required empty public constructor
    }

    public static MissingNumberFragment newInstance() {
        MissingNumberFragment fragment = new MissingNumberFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_missing_number, container, false);
        this.frameLayout = view.findViewById(R.id.main_content);
        this.text = view.findViewById(R.id.sequence_entry);
        this.gapButton = view.findViewById(R.id.determine_gaps_button);
        this.resultDisplay = view.findViewById(R.id.ranges_found_display);

        gapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> missingRanges = determineMissingRanges();
                String output = TextUtils.join(", ", missingRanges);
                resultDisplay.setText(output);
            }
        });

        return view;
    }

    private int[] parseInputNumbers() {
        String input = text.getText().toString();
        String[] numberStrings = input.split(",\\s*");
        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        return numbers;
    }

    private List<String> determineMissingRanges() {
        List<String> missingRanges = new ArrayList<>();

        int[] numbers = parseInputNumbers();
        int i = 0;
        int j = 1;
        while (i < numbers.length && j < numbers.length) {
            // detect missing numbers
            int difference = numbers[j] - numbers[i];
            if (difference == 2) {
                missingRanges.add(String.format("%d", numbers[i] + 1));
            } else if (difference > 2) {
                missingRanges.add(String.format("%d -> %d", numbers[i] + 1, numbers[j] - 1));
            }

            i++;
            j++;
        }

        return missingRanges;
    }
}
