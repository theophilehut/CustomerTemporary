package polito.mad.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TodayMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerViewTM;
    private RecyclerView.Adapter mAdapterTM;
    private RecyclerView.LayoutManager layoutManagerTM;
    TodayMenuData tmd = new TodayMenuData("Pizza", "10", "Pizza Italiana", "Pasta");
    public List<TodayMenuData> TodayMenuDataset = new ArrayList<TodayMenuData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_menu);
        TodayMenuDataset.add(tmd);
        setContentView(R.layout.activity_today_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabTM = (FloatingActionButton) findViewById(R.id.fabTM);
        fabTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TodayMenu.this, AddTodayMenu.class);
                startActivityForResult(i, 1);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Add the recycler view

        recyclerViewTM = (RecyclerView) findViewById(R.id.today_menu_recycler);
        mAdapterTM = new TodayMenuAdapter(TodayMenuDataset);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewTM.setLayoutManager(mLayoutManager);
        recyclerViewTM.setAdapter(mAdapterTM);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.today_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            Intent profile = new Intent(TodayMenu.this,MainActivity.class);
            startActivity(profile);
            finish();

        } else if (id == R.id.nav_restaurant_menu) {

            Intent todayMenu = new Intent(TodayMenu.this,TodayMenu.class);
            startActivity(todayMenu);
            finish();

        } else if (id == R.id.nav_reservations) {

            Intent reservations = new Intent(TodayMenu.this,Reservations.class);
            startActivity(reservations);
            finish();

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            this.setTitle(data.getStringExtra("name"));
            TodayMenuDataset.add(new TodayMenuData(
                    data.getStringExtra("dishes"),
                    data.getStringExtra("price"),
                    data.getStringExtra("description"),
                    data.getStringExtra("notes")));
            mAdapterTM.notifyDataSetChanged();

        }
    }
}
