package work.hin.skyfileclient.view.fab;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseActivity;
import work.hin.skyfileclient.custom.animation.fab.FabAnimation;
import work.hin.skyfileclient.presenter.fab.FabPresenter;

public class FabActivity extends BaseActivity<ViewContract, FabPresenter> implements ViewContract {
    @BindView(R.id.main_fab)
    FloatingActionButton mainFab;
    @BindViews({R.id.fab_pdf, R.id.fab_office, R.id.fab_photo, R.id.fab_text, R.id.fab_other})
    List<FloatingActionButton> miniFabs;
    @BindViews({R.id.label_pdf, R.id.label_office, R.id.label_photo, R.id.label_text, R.id.label_other})
    List<CardView> labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fab_activity_alpha_in, R.anim.fab_activity_alpha_out);

        init();
        initListeners(mainFab);
    }

    @Override
    public FabPresenter initPresenter() {
        return new FabPresenter();
    }

    @Override
    public void init() {
        startAnimation();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_fab:
                finish();
        }
    }

    private void startAnimation() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mainFab, "rotation", 0, 135);
                animator.setStartDelay(30);
                animator.setDuration(160);
                animator.setInterpolator(new DecelerateInterpolator(1.5f));
                animator.start();

                FabAnimation fabAnimation = new FabAnimation(miniFabs, labels);
                fabAnimation.play();
            }
        }, 300);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fab_activity_alpha_in, R.anim.fab_activity_alpha_out);
    }

}
