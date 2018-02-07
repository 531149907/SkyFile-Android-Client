package work.hin.skyfileclient.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public abstract class BaseActivity<V extends BaseView, T extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener, BaseView {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        presenter = initPresenter();
    }

    protected <S extends View> S $(int resId) {
        return super.findViewById(resId);
    }

    protected void initListeners(@Nullable View... views) {
        if (views != null) {
            for (View view : views) {
                view.setOnClickListener(this);
            }
        }
    }

    protected <S extends View> void initListeners(@Nullable List<S> views) {
        for (int i = 0; i < views.size(); i++) {
            views.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.attach(this, (V) this);
        }
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detach();
        }
        super.onDestroy();
    }

    public abstract T initPresenter();

    public abstract void init();
}

