package work.hin.skyfileclient.presenter.upload;

import android.os.Handler;

import work.hin.skyfileclient.core.base.BasePresenter;
import work.hin.skyfileclient.view.upload.ViewContract;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class UploadPresenter extends BasePresenter<ViewContract> {
    private int statusMock = 0;

    public void obtainProcessStatus() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isActivityAlive()) {
                    getMvpView().updateView(++statusMock);
                }
            }
        }, 4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isActivityAlive()) {
                    getMvpView().updateView(++statusMock);
                }
            }
        }, 6000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isActivityAlive()) {
                    getMvpView().updateView(++statusMock);
                }
            }
        }, 8000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isActivityAlive()) {
                    getMvpView().updateView(++statusMock);
                }
            }
        }, 10000);
    }
}
