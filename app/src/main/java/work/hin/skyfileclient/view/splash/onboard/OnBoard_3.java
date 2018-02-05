package work.hin.skyfileclient.view.splash.onboard;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseFragment;
import work.hin.skyfileclient.core.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnBoard_3 extends BaseFragment {
    @BindView(R.id.title_small)
    TextView smallTitle;
    @BindView(R.id.title_big)
    TextView bigTitle;
    @BindView(R.id.illustration)
    ImageView illustration;
    @BindView(R.id.description_text)
    TextView descriptionText;

    private Runnable animationRunnable;

    public OnBoard_3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_on_board_3, container, false);
        ButterKnife.bind(this, mRootView);

        init(mRootView);

        return mRootView;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void init(View mRootView) {
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(smallTitle, "translationY", 100, 0);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(smallTitle, "alpha", 0, 1);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(illustration, "translationY", 100, 0);
        ObjectAnimator oa4 = ObjectAnimator.ofFloat(illustration, "alpha", 0, 1);
        ObjectAnimator oa5 = ObjectAnimator.ofFloat(bigTitle, "translationY", 100, 0);
        ObjectAnimator oa6 = ObjectAnimator.ofFloat(bigTitle, "alpha", 0, 1);
        ObjectAnimator oa7 = ObjectAnimator.ofFloat(descriptionText, "translationY", 100, 0);
        ObjectAnimator oa8 = ObjectAnimator.ofFloat(descriptionText, "alpha", 0, 1);

        oa1.setDuration(450);
        oa2.setDuration(450);
        oa3.setDuration(400);
        oa4.setDuration(400);
        oa5.setDuration(350);
        oa6.setDuration(350);
        oa7.setDuration(300);
        oa8.setDuration(300);

        oa1.setStartDelay(100);
        oa2.setStartDelay(100);
        oa3.setStartDelay(150);
        oa4.setStartDelay(150);
        oa5.setStartDelay(200);
        oa6.setStartDelay(200);
        oa7.setStartDelay(250);
        oa8.setStartDelay(250);

        final AnimatorSet set = new AnimatorSet();
        set.playTogether(oa1, oa2, oa3, oa4, oa5, oa6, oa7, oa8);
        set.setInterpolator(new DecelerateInterpolator(1.2f));

        animationRunnable = new Runnable() {
            @Override
            public void run() {
                set.start();
            }
        };

        smallTitle.post(animationRunnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        smallTitle.removeCallbacks(animationRunnable);
    }
}
