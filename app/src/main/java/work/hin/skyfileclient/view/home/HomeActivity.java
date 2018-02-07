package work.hin.skyfileclient.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseActivity;
import work.hin.skyfileclient.model.domain.File;
import work.hin.skyfileclient.model.domain.UploadItem;
import work.hin.skyfileclient.presenter.home.HomePresenter;
import work.hin.skyfileclient.view.detail.DetailActivity;
import work.hin.skyfileclient.view.fab.FabActivity;
import work.hin.skyfileclient.view.home.adapter.FileListAdapter;
import work.hin.skyfileclient.view.home.adapter.UploadListAdapter;
import work.hin.skyfileclient.view.upload.UploadActivity;

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
    @BindView(R.id.recycler_view_upload)
    RecyclerView uploadList;
    @BindView(R.id.recycler_view_file)
    RecyclerView fileList;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private UploadListAdapter uploadAdapter;
    private FileListAdapter fileAdapter;

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

        uploadList.setNestedScrollingEnabled(false);
        fileList.setNestedScrollingEnabled(false);

        uploadAdapter = new UploadListAdapter(this);
        uploadAdapter.addOnClickListener(new UploadListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UploadItem item) {
                Intent intent = new Intent(HomeActivity.this, UploadActivity.class);
                intent.putExtra("data", item);
                startActivity(intent);
            }
        });
        fileAdapter = new FileListAdapter(this);
        fileAdapter.addOnClickListener(new FileListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(File file) {
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                intent.putExtra("data", file);
                startActivity(intent);
            }
        });

        uploadList.setLayoutManager(new LinearLayoutManager(this));
        uploadList.setAdapter(uploadAdapter);
        uploadList.getItemAnimator().setChangeDuration(0);

        fileList.setLayoutManager(new LinearLayoutManager(this));
        fileList.setAdapter(fileAdapter);
        fileList.getItemAnimator().setChangeDuration(0);
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
    public void updateFileList(HashMap<String, List<File>> data) {
        fileAdapter.setData(data);
    }

    @Override
    public void updateUploadList(List<UploadItem> data) {
        uploadAdapter.setData(data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
