package in.TakshilaLearning.TakshilaLearning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import in.TakshilaLearning.TakshilaLearning.CourseContentList.CourseContentFragment;
import in.TakshilaLearning.TakshilaLearning.LoginAuthorization.LoginSignUpFragment;
import in.TakshilaLearning.TakshilaLearning.SearchCoursesList.SearchFragment;

public class MainActivity extends AppCompatActivity {
    Fragment selectedFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    selectedFragment = new SearchFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).commit();

                    return true;
                case R.id.navigation_courses:
                    selectedFragment = new CourseContentFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).commit();

                 //   Intent intent = new Intent(MainActivity.this, CourseContent.class);
                  //  startActivity(intent);
                    return true;
                case R.id.navigation_help:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View view = getSupportActionBar().getCustomView();
        ImageView name = view.findViewById(R.id.user_inf);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You have clicked tittle", Toast.LENGTH_LONG).show();

            }
        });


        //navigation bar
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment selectedFragment = new LoginSignUpFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).commit();


    }

    public void changeFragment(Fragment selectedFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main_container,selectedFragment).commit();
    }

}
