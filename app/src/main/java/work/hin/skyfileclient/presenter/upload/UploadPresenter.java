package work.hin.skyfileclient.presenter.upload;

import android.os.Handler;

import work.hin.skyfileclient.core.base.BasePresenter;
import work.hin.skyfileclient.view.upload.ViewContract;

import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.UPLOAD_CODING;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.UPLOAD_SPLITTING;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.UPLOAD_STORING;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.UPLOAD_SUCCESS;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class UploadPresenter extends BasePresenter<ViewContract> {
    public void obtainProcessStatus() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isActivityAlive()) {
                    getMvpView().updateView(UPLOAD_SPLITTING);
                }
            }
        }, 4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isActivityAlive()) {
                    getMvpView().updateView(UPLOAD_CODING);
                }
            }
        }, 8000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isActivityAlive()) {
                    getMvpView().updateView(UPLOAD_STORING);
                }
            }
        }, 12000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isActivityAlive()) {
                    getMvpView().updateView(UPLOAD_SUCCESS);
                }
            }
        }, 16000);
    }
}
