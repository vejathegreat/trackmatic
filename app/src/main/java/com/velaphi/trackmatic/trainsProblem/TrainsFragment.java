package com.velaphi.trackmatic.trainsProblem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.velaphi.trackmatic.R;

import java.util.ArrayList;
import java.util.List;

public class TrainsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    TextView results;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trains, container, false);
        results = view.findViewById(R.id.results);
        processResult();

        return view;
    }

    private void processResult() {
        String output = "";
        TrainsRoutes trainsRoutes = new TrainsRoutes(Constants.SAMPLE_DATE);

        List<Town<String>> towns = new ArrayList<>();
        towns.add(new Town<>("A"));
        towns.add(new Town<>("B"));
        towns.add(new Town<>("C"));
        output += "Output #1: " + trainsRoutes.distanceOfRoute(towns) + "\n";

        towns.clear();
        towns.add(new Town<>("A"));
        towns.add(new Town<>("D"));
        output += "Output #2: " + trainsRoutes.distanceOfRoute(towns) + "\n";

        towns.add(new Town<>("C"));
        output += "Output #3: " + trainsRoutes.distanceOfRoute(towns) + "\n";

        towns.clear();
        towns.add(new Town<>("A"));
        towns.add(new Town<>("E"));
        towns.add(new Town<>("B"));
        towns.add(new Town<>("C"));
        towns.add(new Town<>("D"));
        output += "Output #4: " + trainsRoutes.distanceOfRoute(towns) + "\n";

        towns.clear();
        towns.add(new Town<>("A"));
        towns.add(new Town<>("E"));
        towns.add(new Town<>("D"));
        output += "Output #5: " + trainsRoutes.distanceOfRoute(towns) + "\n";

        output += "Output #6: " + trainsRoutes.numberOfTripsMaxStops(new Town<>("C"), new Town<>("C"), 3) + "\n";
        output += "Output #7: " + trainsRoutes.numberOfTripsExacltyStops(new Town<>("A"), new Town<>("C"), 4) + "\n";
        output += "Output #8: " + trainsRoutes.lengthShortestRoute(new Town<>("A"), new Town<>("C")) + "\n";
        output += "Output #9: " + trainsRoutes.lengthShortestRoute(new Town<>("B"), new Town<>("B")) + "\n";
        output += "Output #10: " + trainsRoutes.numberDifferenRoutes(new Town<>("C"), new Town<>("C"), 30) + "\n";
        results.setText(output);
    }

}
