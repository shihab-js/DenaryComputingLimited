package shihab.denary.com.denarycomputinglimited;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;

import shihab.denary.com.denarycomputinglimited.adapter.ViewPageAdapter;
import shihab.denary.com.denarycomputinglimited.fragments.AboutFragment;
import shihab.denary.com.denarycomputinglimited.fragments.CoursesFragment;
import shihab.denary.com.denarycomputinglimited.fragments.DevelopmentFragment;
import shihab.denary.com.denarycomputinglimited.fragments.EventsFragment;
import shihab.denary.com.denarycomputinglimited.fragments.GeneralFragment;
import shihab.denary.com.denarycomputinglimited.fragments.ProjectsFragment;

public class SecondActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        setDataToViewPager();

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        bmb = findViewById(R.id.bmb);
        boomMenuSetup();



    }

    private void boomMenuSetup() {

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(R.drawable.ic_launcher_foreground)
                    .normalText("Butter Doesn't fly!")
                    .subNormalText("Little butter Doesn't fly, either!");
            bmb.addBuilder(builder);
        }

    }

    private void setDataToViewPager() {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new GeneralFragment(),"General");
        adapter.addFragment(new CoursesFragment(),"Courses");
        adapter.addFragment(new EventsFragment(),"Events");
        adapter.addFragment(new DevelopmentFragment(),"Development");
        adapter.addFragment(new ProjectsFragment(),"Projects");
        adapter.addFragment(new AboutFragment(),"About Us");
        viewPager.setAdapter(adapter);

    }






    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(getApplicationContext(), "Click Home", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Toast.makeText(getApplicationContext(), "Click About", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                Toast.makeText(getApplicationContext(), "Click Help", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.exit:
                Toast.makeText(getApplicationContext(), "Click Exit", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
