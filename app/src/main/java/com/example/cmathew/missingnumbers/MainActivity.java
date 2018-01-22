package com.example.cmathew.missingnumbers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gapButton = findViewById(R.id.determine_gaps_button);

        gapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> missingRanges = determineMissingRanges();
                TextView display = findViewById(R.id.ranges_found_display);
                String output = TextUtils.join(", ", missingRanges);
                display.setText(output);
            }
        });
    }

    private int[] parseInputNumbers() {
        EditText text = findViewById(R.id.sequence_entry);
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
