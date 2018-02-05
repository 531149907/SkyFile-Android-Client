package work.hin.skyfileclient.view.splash.onboard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.hin.skyfileclient.R;
import work.hin.skyfileclient.core.base.BaseFragment;
import work.hin.skyfileclient.core.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnBoard_1 extends BaseFragment {
    @BindView(R.id.)

    public OnBoard_1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_on_board_1, container, false);
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

    }
}
