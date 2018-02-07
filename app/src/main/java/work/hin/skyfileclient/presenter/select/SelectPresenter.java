package work.hin.skyfileclient.presenter.select;

import java.util.ArrayList;
import java.util.List;

import work.hin.skyfileclient.core.base.BasePresenter;
import work.hin.skyfileclient.model.dao.FileScanCallback;
import work.hin.skyfileclient.model.dao.FileScanTask;
import work.hin.skyfileclient.model.domain.LocalFile;
import work.hin.skyfileclient.view.select.ViewContract;

import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.SELECT_OFFICE;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.SELECT_OTHER;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.SELECT_PDF;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.SELECT_PHOTO;
import static work.hin.skyfileclient.config.constant.UPLOAD_STATIC.SELECT_TEXT;

/**
 * Created by zhouzhixuan on 2018/2/7.
 */

public class SelectPresenter extends BasePresenter<ViewContract> implements FileScanCallback {
    public void onListLoad(int type) {
        List<String> fileType = new ArrayList<>();
        switch (type) {
            case SELECT_PDF:
                fileType.add(".pdf");
                break;
            case SELECT_OFFICE:
                fileType.add(".doc");
                //fileType.add(".docx");
                //fileType.add(".ppt");
                //fileType.add(".pptx");
                //fileType.add(".xls");
                //fileType.add(".xlsx");
                break;
            case SELECT_PHOTO:
                fileType.add(".jpg");
                //fileType.add(".jpeg");
                //fileType.add(".png");
                //fileType.add(".bmp");
                break;
            case SELECT_TEXT:
                fileType.add(".txt");
                break;
            case SELECT_OTHER:
                fileType.add(".apk");
                break;
        }

        String path = System.getenv("EXTERNAL_STORAGE");
        for (int i = 0; i < fileType.size(); i++) {
            FileScanTask task = new FileScanTask(getContext(), this);
            task.execute(path, fileType.get(i));
        }

    }

    @Override
    public void processList(List<LocalFile> fileList) {
        getMvpView().setListData(fileList);
    }
}
