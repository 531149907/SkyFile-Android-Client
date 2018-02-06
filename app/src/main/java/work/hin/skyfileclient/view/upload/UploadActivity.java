package work.hin.skyfileclient.view.upload;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseActivity;
import work.hin.skyfileclient.presenter.upload.UploadPresenter;

import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.UPLOAD_CODING;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.UPLOAD_ENCRYPTING;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.UPLOAD_SPLITING;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.UPLOAD_STORING;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.UPLOAD_SUCCESS;

public class UploadActivity extends BaseActivity<ViewContract, UploadPresenter> implements ViewContract {
    @BindView(R.id.illustration)
    ImageView illustration;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);

        init();
    }

    @Override
    public UploadPresenter initPresenter() {
        return new UploadPresenter();
    }

    @Override
    public void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        startAnimation();
        presenter.obtainProcessStatus();
    }

    @Override
    public void updateView(int processIndex) {
        getSupportActionBar().setTitle(getString(R.string.main_upload_title) + " (" + (processIndex + 1) + "/5)");
        Drawable illustrationDrawable;
        String titleResource;
        String descriptionResource;
        switch (processIndex) {
            default:
            case UPLOAD_ENCRYPTING:
                illustrationDrawable = getDrawable(R.drawable.ic_illustration_encryption);
                titleResource = getString(R.string.upload_status_title_1);
                descriptionResource = getString(R.string.upload_status_text_1);
                break;
            case UPLOAD_SPLITING:
                illustrationDrawable = getDrawable(R.drawable.ic_illustration_file_split);
                titleResource = getString(R.string.upload_status_title_2);
                descriptionResource = getString(R.string.upload_status_text_2);
                break;
            case UPLOAD_CODING:
                illustrationDrawable = getDrawable(R.drawable.ic_illustration_coding);
                titleResource = getString(R.string.upload_status_title_3);
                descriptionResource = getString(R.string.upload_status_text_3);
                break;
            case UPLOAD_STORING:
                illustrationDrawable = getDrawable(R.drawable.ic_illustration_node_process);
                titleResource = getString(R.string.upload_status_title_4);
                descriptionResource = getString(R.string.upload_status_text_4);
                break;
            case UPLOAD_SUCCESS:
                illustrationDrawable = getDrawable(R.drawable.ic_illustration_upload);
                titleResource = getString(R.string.upload_status_title_5);
                descriptionResource = getString(R.string.upload_status_text_5);
                break;
        }
        switchAnimation(illustrationDrawable, titleResource, descriptionResource);
    }

    private void switchAnimation(final Drawable illustrationR, final String titleR, final String descriptionR) {
        ObjectAnimator oa1 = ObjectAnimator.ofFloat(illustration, "alpha", 1, 0);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(title, "alpha", 1, 0);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(description, "alpha", 1, 0);

        oa1.setDuration(150);
        oa2.setDuration(150);
        oa3.setDuration(150);

        oa1.setStartDelay(100);
        oa2.setStartDelay(100);
        oa3.setStartDelay(150);

        final AnimatorSet set = new AnimatorSet();
        set.playTogether(oa1, oa2, oa3);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                illustration.setImageDrawable(illustrationR);
                title.setText(titleR);
                description.setText(descriptionR);

                ObjectAnimator oa1 = ObjectAnimator.ofFloat(illustration, "translationY", 100, 0);
                ObjectAnimator oa2 = ObjectAnimator.ofFloat(illustration, "alpha", 0, 1);
                ObjectAnimator oa3 = ObjectAnimator.ofFloat(title, "translationY", 100, 0);
                ObjectAnimator oa4 = ObjectAnimator.ofFloat(title, "alpha", 0, 1);
                ObjectAnimator oa5 = ObjectAnimator.ofFloat(description, "translationY", 100, 0);
                ObjectAnimator oa6 = ObjectAnimator.ofFloat(description, "alpha", 0, 1);

                oa1.setDuration(450);
                oa2.setDuration(450);
                oa3.setDuration(400);
                oa4.setDuration(400);
                oa5.setDuration(350);
                oa6.setDuration(350);

                oa1.setStartDelay(300);
                oa2.setStartDelay(300);
                oa3.setStartDelay(350);
                oa4.setStartDelay(350);
                oa5.setStartDelay(400);
                oa6.setStartDelay(400);

                final AnimatorSet set = new AnimatorSet();
                set.playTogether(oa1, oa2, oa3, oa4, oa5, oa6);
                set.setInterpolator(new DecelerateInterpolator(1.2f));
                set.start();
            }
        });
        set.start();
    }

    private void startAnimation() {
        illustration.setImageDrawable(getDrawable(R.drawable.ic_illustration_encryption));
        title.setText(R.string.upload_status_title_1);
        description.setText(R.string.upload_status_text_1);
        getSupportActionBar().setTitle(getString(R.string.main_upload_title) + " (1/5)");

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(illustration, "translationY", 100, 0);
        ObjectAnimator oa2 = ObjectAnimator.ofFloat(illustration, "alpha", 0, 1);
        ObjectAnimator oa3 = ObjectAnimator.ofFloat(title, "translationY", 100, 0);
        ObjectAnimator oa4 = ObjectAnimator.ofFloat(title, "alpha", 0, 1);
        ObjectAnimator oa5 = ObjectAnimator.ofFloat(description, "translationY", 100, 0);
        ObjectAnimator oa6 = ObjectAnimator.ofFloat(description, "alpha", 0, 1);

        oa1.setDuration(450);
        oa2.setDuration(450);
        oa3.setDuration(400);
        oa4.setDuration(400);
        oa5.setDuration(350);
        oa6.setDuration(350);

        oa1.setStartDelay(100);
        oa2.setStartDelay(100);
        oa3.setStartDelay(150);
        oa4.setStartDelay(150);
        oa5.setStartDelay(200);
        oa6.setStartDelay(200);

        final AnimatorSet set = new AnimatorSet();
        set.playTogether(oa1, oa2, oa3, oa4, oa5, oa6);
        set.setInterpolator(new DecelerateInterpolator(1.2f));
        set.start();
    }
}
