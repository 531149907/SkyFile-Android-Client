package work.hin.skyfileclient.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseActivity;
import work.hin.skyfileclient.core.base.BaseFragment;
import work.hin.skyfileclient.presenter.splash.SplashPresenter;
import work.hin.skyfileclient.util.FragmentManagement;
import work.hin.skyfileclient.view.detail.DetailActivity;
import work.hin.skyfileclient.view.splash.onboard.OnBoard_1;
import work.hin.skyfileclient.view.splash.onboard.OnBoard_2;
import work.hin.skyfileclient.view.splash.onboard.OnBoard_3;

public class SplashActivity extends BaseActivity<ViewContract, SplashPresenter> implements ViewContract {
    @BindView(R.id.next_button)
    Button nextButton;

    private int fragmentIndex = 0;
    private static final int TOTAL_FRAGMENTS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        init();
        initListeners(nextButton);
    }

    @Override
    public SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    public void init() {
        onNextPageClick();
    }

    @Override
    public void onNextPageClick() {
        if (fragmentIndex == TOTAL_FRAGMENTS) {
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        BaseFragment fragment = null;
        switch (fragmentIndex) {
            case 0:
                fragment = new OnBoard_1();
                break;
            case 1:
                fragment = new OnBoard_2();
                break;
            case 2:
                fragment = new OnBoard_3();
                nextButton.setText("完成");
                break;
        }
        FragmentManagement
                .switchFragment(getSupportFragmentManager(),
                        fragment,
                        R.id.fragment_container,
                        false);
        fragmentIndex = (fragmentIndex + 1) % (TOTAL_FRAGMENTS + 1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_button:
                onNextPageClick();
                break;
        }
    }


}
