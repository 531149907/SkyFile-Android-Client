package work.hin.skyfileclient.model.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.text.format.Formatter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import work.hin.skyfileclient.model.domain.LocalFile;
import work.hin.skyfileclient.util.LogUtil;

/**
 * Created by zhouzhixuan on 2018/2/7.
 */

public class FileScanTask extends AsyncTask<String, String, String> {
    private FileScanCallback callback;
    private List<LocalFile> list;
    private Context context;

    //usage: sdcard_path, ".file_type"

    public FileScanTask(Context context,FileScanCallback callback) {
        this.callback = callback;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        list = new ArrayList<>();
    }

    @Override
    protected String doInBackground(String... params) {
        String path = params[1].substring(params[1].lastIndexOf(".") + 1);
        File file = new File(params[0]);
        scanFile(file,path,list);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        callback.processList(list);
    }

    private void scanFile(File file,String ext,List<LocalFile> resultList){
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    File tmp = files[i];
                    if (tmp.isFile()) {
                        String fileName = tmp.getName();
                        if (fileName.contains(".")){
                            fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
                            if (ext != null && ext.equalsIgnoreCase(fileName)) {
                                LocalFile localFile = new LocalFile();
                                String fileSize = Formatter.formatFileSize(context,tmp.length());
                                localFile.setFileName(tmp.getName().substring(0,tmp.getName().lastIndexOf(".")));
                                localFile.setFilePath(tmp.getAbsolutePath());
                                localFile.setFileSize(fileSize);
                                localFile.setFileType(tmp.getName().substring(tmp.getName().lastIndexOf(".")+1));
                                resultList.add(localFile);
                            }
                        }
                    } else
                        scanFile(tmp, ext, list);
                }
            }
        } else {
            if (file.isFile()) {
                String fileName = file.getName();
                String filePath = file.getName();
                if (fileName.contains(".")){
                    fileName = fileName
                            .substring(fileName.lastIndexOf(".") + 1);
                    if (ext != null && ext.equalsIgnoreCase(fileName)) {
                        LocalFile localFile = new LocalFile();
                        String fileSize = Formatter.formatFileSize(context,file.length());
                        localFile.setFileType(file.getName().substring(file.getName().lastIndexOf(".")+1));
                        localFile.setFilePath(file.getAbsolutePath());
                        localFile.setFileSize(fileSize);
                        localFile.setFileName(file.getName().substring(0,file.getName().lastIndexOf(".")));
                        resultList.add(localFile);
                    }
                }
            }
        }
    }
}
