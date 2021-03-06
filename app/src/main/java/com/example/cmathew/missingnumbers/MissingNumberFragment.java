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
    private static int RANGE_MIN = 0;
    private static int RANGE_MAX = 10;

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

        gapButton.setText(getString(R.string.determine_gaps_button_text, RANGE_MIN, RANGE_MAX));
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
        for (int i = 0, j = 1; i < numbers.length; i++, j++) {
            // Last iteration is a special case. We pretend that there
            // is a value of RANGE_MAX + 1 at the end of our data
            int rangeMaxExclusive = (RANGE_MAX + 1);
            int rangeUpperBound;
            if (j >= numbers.length) {
                rangeUpperBound = rangeMaxExclusive;
            } else {
                rangeUpperBound = Math.min(rangeMaxExclusive, numbers[j]);
            }

            // detect missing numbers
            int difference = rangeUpperBound - numbers[i];
            if (difference == 1) {
                continue;
            }

            // At least one missing number is present.
            // Find candidates for smallest and largest missing numbers
            // that we care about
            int smallestMissing = Math.max(RANGE_MIN, numbers[i] + 1);
            int largestMissing = rangeUpperBound - 1;
            int clampedDifference = largestMissing - smallestMissing;
            if (clampedDifference == 0) {
                // arbitrarily pick smallestMissing or largestMissing
                missingRanges.add(String.valueOf(smallestMissing));
            } else if (clampedDifference > 0) {
                missingRanges.add(String.format("%d -> %d", smallestMissing, largestMissing));
            }
        }

        return missingRanges;
    }
}
