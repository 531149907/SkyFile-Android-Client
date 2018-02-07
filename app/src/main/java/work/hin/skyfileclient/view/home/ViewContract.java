package work.hin.skyfileclient.view.home;

import java.util.HashMap;
import java.util.List;

import work.hin.skyfileclient.core.base.BaseView;
import work.hin.skyfileclient.model.domain.File;
import work.hin.skyfileclient.model.domain.UploadItem;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public interface ViewContract extends BaseView {
    void updateFileList(HashMap<String, List<File>> data);

    void updateUploadList(List<UploadItem> data);
}
