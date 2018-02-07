package work.hin.skyfileclient.presenter.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work.hin.skyfileclient.core.base.BasePresenter;
import work.hin.skyfileclient.model.domain.File;
import work.hin.skyfileclient.model.domain.UploadItem;
import work.hin.skyfileclient.view.home.ViewContract;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class HomePresenter extends BasePresenter<ViewContract> {
    public void refreshList() {
        HashMap<String, List<File>> data = new HashMap<>();
        List<UploadItem> uploadData = new ArrayList<>();

        List<File> fileList1 = new ArrayList<>();
        fileList1.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/3", "3.4 MB"));
        fileList1.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/3", "3.4 MB"));
        fileList1.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/3", "3.4 MB"));
        fileList1.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/3", "3.4 MB"));

        data.put("2018年3月", fileList1);

        List<File> fileList2 = new ArrayList<>();
        fileList2.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/2", "3.4 MB"));
        fileList2.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/2", "3.4 MB"));
        fileList2.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/2", "3.4 MB"));
        fileList2.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/2", "3.4 MB"));
        fileList2.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/2", "3.4 MB"));
        fileList2.add(new File(1, 1, "PDF", "年度工作汇报.pdf", "12345", "8765eikmnhbgvfntnnymum6m7m", "99", "23,21,20,454", "2018/2", "3.4 MB"));

        data.put("2018年2月", fileList2);

        getMvpView().updateFileList(data);
        getMvpView().updateUploadList(uploadData);
    }
}
