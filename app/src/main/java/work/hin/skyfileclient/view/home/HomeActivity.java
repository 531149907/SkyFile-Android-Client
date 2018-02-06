package work.hin.skyfileclient.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseActivity;
import work.hin.skyfileclient.presenter.home.HomePresenter;
import work.hin.skyfileclient.view.fab.FabActivity;

public class HomeActivity extends BaseActivity<ViewContract, HomePresenter> implements ViewContract {
    @BindView(R.id.appbar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list_item_cart)
    TableRow navItemCart;
    @BindView(R.id.list_item_file)
    TableRow navItemFile;
    @BindView(R.id.list_item_info)
    TableRow navItemInfo;
    @BindView(R.id.list_item_notification)
    TableRow navItemNotice;
    @BindView(R.id.list_item_settings)
    TableRow navItemSettings;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        init();
        initListeners(navItemCart, navItemFile, navItemInfo, navItemNotice, navItemSettings, fab);
    }

    @Override
    public void init() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setTitle(R.string.main_home_title);
    }

    @Override
    public HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                break;
            case R.id.action_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.list_item_cart:
                break;
            case R.id.list_item_file:
                break;
            case R.id.list_item_info:
                break;
            case R.id.list_item_notification:
                break;
            case R.id.list_item_settings:
                break;
            case R.id.fab:
                intent = new Intent(this, FabActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fab_activity_alpha_in, R.anim.fab_activity_alpha_out);
                break;
        }
    }

    @Override
    public void updateFileList() {

    }
}
