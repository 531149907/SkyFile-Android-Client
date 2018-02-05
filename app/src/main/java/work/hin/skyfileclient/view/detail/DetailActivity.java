package work.hin.skyfileclient.view.detail;

import android.os.Bundle;

import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseActivity;
import work.hin.skyfileclient.presenter.detail.DetailPresenter;

public class DetailActivity extends BaseActivity<ViewContract, DetailPresenter> implements ViewContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
    }

    @Override
    public DetailPresenter initPresenter() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void setFileBasicInfo(String name, String size, String date, String protect) {

    }

    @Override
    public void setNodeInfo(String nodeCount, String integrity, float percentage, String shards) {

    }

    @Override
    public void setStorageInfo(String sha256, String root, String treeInfo) {

    }
}
