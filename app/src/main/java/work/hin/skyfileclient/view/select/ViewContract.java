package work.hin.skyfileclient.view.select;

import java.util.List;

import work.hin.skyfileclient.core.base.BaseView;
import work.hin.skyfileclient.model.domain.LocalFile;

/**
 * Created by zhouzhixuan on 2018/2/7.
 */

public interface ViewContract extends BaseView{
    void setListData(List<LocalFile> data);
}
