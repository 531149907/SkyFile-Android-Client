package work.hin.skyfileclient.view.splash;

import android.os.Bundle;

import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseActivity;
import work.hin.skyfileclient.presenter.splash.SplashPresenter;

public class SplashActivity extends BaseActivity<ViewContract, SplashPresenter> implements ViewContract{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    public void onNextPageClick(int pageIndex) {

    }
}
