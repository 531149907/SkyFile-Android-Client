package work.hin.skyfileclient.view.detail;

import work.hin.skyfileclient.core.base.BaseView;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public interface ViewContract extends BaseView {
    void setFileBasicInfo(String name, String size, String date, String protect);

    void setNodeInfo(String nodeCount, String integrity, float percentage, String shards);

    void setStorageInfo(String sha256, String root, String treeInfo);
}
