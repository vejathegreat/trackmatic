package com.velaphi.trackmatic;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.velaphi.trackmatic.marsRover.MarsRoverFragment;
import com.velaphi.trackmatic.taxProblem.SalesTaxFragment;
import com.velaphi.trackmatic.trainsProblem.TrainsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFragment(new TrainsFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_tax:
                    openFragment(new SalesTaxFragment());
                    break;
                case R.id.action_trains:
                    openFragment(new TrainsFragment());
                    break;
                case R.id.action_rover:
                    openFragment(new MarsRoverFragment());
                    break;
            }
            return true;
        });
    }

    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

}
