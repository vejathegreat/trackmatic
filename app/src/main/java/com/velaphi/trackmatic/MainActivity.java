package com.velaphi.trackmatic;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.velaphi.trackmatic.marsRover.MarsRoverFragment;
import com.velaphi.trackmatic.taxProblem.SalesTaxFragment;
import com.velaphi.trackmatic.trainsProblem.TrainsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });
    }

    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

}
