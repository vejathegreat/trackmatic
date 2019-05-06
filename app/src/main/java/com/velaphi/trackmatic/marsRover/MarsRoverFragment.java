package com.velaphi.trackmatic.marsRover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.velaphi.trackmatic.R;

public class MarsRoverFragment extends Fragment {

    private EditText xCoordinatesEditText;
    private EditText yCoordinatesEditText;
    private Spinner xAxisSpinner;
    private Spinner yAxisSpinner;
    private Spinner cardinalDirectionSpinner;
    private TextView output;
    private TextView instructionsTextview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mars_rover, container, false);
        setupView(view);
        return view;
    }

    private void setupView(View view) {
        xCoordinatesEditText = view.findViewById(R.id.x_axis_editText);
        yCoordinatesEditText = view.findViewById(R.id.y_axis_editText);
        xAxisSpinner = view.findViewById(R.id.x_axis_spinner);
        yAxisSpinner = view.findViewById(R.id.y_axis_spinner);
        cardinalDirectionSpinner  = view.findViewById(R.id.direction_spinner);
        instructionsTextview = view.findViewById(R.id.instruction_editText);
        Button commandButton = view.findViewById(R.id.command_button);
        output = view.findViewById(R.id.results_textview);
        commandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directRover();
            }
        });
    }

    private void directRover() {
        String xCoordinate = xCoordinatesEditText.getText().toString();
        String yCoordinate = yCoordinatesEditText.getText().toString();
        String xAxis = xAxisSpinner.getSelectedItem().toString();
        String yAxis = yAxisSpinner.getSelectedItem().toString();
        String direction = cardinalDirectionSpinner.getSelectedItem().toString();
        String instructions = instructionsTextview.getText().toString();

        MarsRover rover = new MarsRover();
        String result = xCoordinate + " " + yCoordinate + "\n" + xAxis + " " + yAxis + " " + direction + "\n" + instructions;

        output.setText(rover.execute(result));
    }
}
