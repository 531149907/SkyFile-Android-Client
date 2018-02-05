package work.hin.skyfileclient.core.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public abstract class BaseFragment<V extends BaseView, T extends BasePresenter<V>> extends Fragment implements View.OnClickListener, BaseView {

    protected View mRootView;
    protected T presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.attach(getActivity(), (V) this);
        }
    }

    protected <S extends View> S $(int resId) {
        return mRootView.findViewById(resId);
    }

    protected void initListeners(@Nullable View... views) {
        if (views != null) {
            for (View view : views) {
                view.setOnClickListener(this);
            }
        }
    }

    public View getRootView() {
        return this.mRootView;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detach();
        }
        super.onDestroy();
    }

    public abstract T initPresenter();

    public abstract void init(View mRootView);
}

