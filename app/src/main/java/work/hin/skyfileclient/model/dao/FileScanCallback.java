package work.hin.skyfileclient.model.dao;

import java.util.List;

import work.hin.skyfileclient.model.domain.LocalFile;

/**
 * Created by zhouzhixuan on 2018/2/7.
 */

public interface FileScanCallback {
    void processList(List<LocalFile> fileList);
}
