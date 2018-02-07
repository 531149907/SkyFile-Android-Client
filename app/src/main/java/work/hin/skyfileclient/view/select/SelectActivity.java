package work.hin.skyfileclient.view.select;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseActivity;
import work.hin.skyfileclient.model.domain.File;
import work.hin.skyfileclient.model.domain.LocalFile;
import work.hin.skyfileclient.presenter.select.SelectPresenter;
import work.hin.skyfileclient.view.detail.DetailActivity;
import work.hin.skyfileclient.view.home.HomeActivity;
import work.hin.skyfileclient.view.home.adapter.FileListAdapter;
import work.hin.skyfileclient.view.select.adapter.LocalFileAdapter;
import work.hin.skyfileclient.view.upload.UploadActivity;

public class SelectActivity extends BaseActivity<ViewContract, SelectPresenter> implements ViewContract {
    @BindView(R.id.appbar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.function_bar)
    FrameLayout functionBar;
    @BindView(R.id.confirm_button)
    Button confirmButton;
    @BindView(R.id.recycler_view)
    RecyclerView fileList;

    private int type;
    private LocalFileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);

        init();
        initListeners(confirmButton);
    }

    @Override
    public SelectPresenter initPresenter() {
        return new SelectPresenter();
    }

    @Override
    public void init() {
        type = getIntent().getIntExtra("type", 0);
        adapter = new LocalFileAdapter(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fileList.setNestedScrollingEnabled(false);
        fileList.setLayoutManager(new LinearLayoutManager(this));
        fileList.setAdapter(adapter);
        fileList.getItemAnimator().setChangeDuration(0);

    }

    @Override
    public void setListData(List<LocalFile> data) {
        adapter.setData(data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_button:
                LocalFile item = adapter.getResult();
                if(item!=null){
                    Intent intent = new Intent(this, UploadActivity.class);
                    intent.putExtra("data",item);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this,"请选择一个文件",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onListLoad(type);
    }
}
