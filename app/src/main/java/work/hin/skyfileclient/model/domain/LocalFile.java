package work.hin.skyfileclient.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2018/2/7.
 */

public class LocalFile implements Parcelable {
    private String fileName;
    private String fileSize;
    private String fileType;
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalFile(){}

    public LocalFile(String fileName, String fileSize, String fileType, String filePath) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    protected LocalFile(Parcel in) {
        fileName = in.readString();
        fileSize = in.readString();
        fileType = in.readString();
        filePath = in.readString();
    }

    public static final Creator<LocalFile> CREATOR = new Creator<LocalFile>() {
        @Override
        public LocalFile createFromParcel(Parcel in) {
            return new LocalFile(in);
        }

        @Override
        public LocalFile[] newArray(int size) {
            return new LocalFile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fileName);
        parcel.writeString(fileSize);
        parcel.writeString(fileType);
        parcel.writeString(filePath);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
