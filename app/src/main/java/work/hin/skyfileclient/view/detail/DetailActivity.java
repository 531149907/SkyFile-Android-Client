package work.hin.skyfileclient.view.detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseActivity;
import work.hin.skyfileclient.presenter.detail.DetailPresenter;

public class DetailActivity extends BaseActivity<ViewContract, DetailPresenter> implements ViewContract {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.file_small_title)
    TextView smallTitle;
    @BindView(R.id.file_big_title)
    TextView bigTitle;
    @BindView(R.id.content_layout)
    LinearLayout contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        init();
    }

    @Override
    public DetailPresenter initPresenter() {
        return new DetailPresenter();
    }

    @Override
    public void init() {
        startAnimation();
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

    private void startAnimation() {
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(bigTitle, "translationY", 200, 0);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(bigTitle, "alpha", 0, 1);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(smallTitle, "translationY", 150, 0);
        ObjectAnimator oa4 = ObjectAnimator.ofFloat(smallTitle, "alpha", 0, 1);
        ObjectAnimator oa5 = ObjectAnimator.ofFloat(contentLayout, "translationY", 100, 0);
        ObjectAnimator oa6 = ObjectAnimator.ofFloat(contentLayout, "alpha", 0, 1);

        oa1.setDuration(600);
        oa2.setDuration(600);
        oa3.setDuration(500);
        oa4.setDuration(500);
        oa5.setDuration(400);
        oa6.setDuration(400);

        oa1.setStartDelay(100);
        oa2.setStartDelay(100);
        oa3.setStartDelay(200);
        oa4.setStartDelay(200);
        oa5.setStartDelay(300);
        oa6.setStartDelay(300);


        AnimatorSet set = new AnimatorSet();
        set.playTogether(oa1, oa2, oa3, oa4, oa5, oa6);
        set.setInterpolator(new DecelerateInterpolator(1.2f));
        set.setStartDelay(50);
        set.start();
    }
}
