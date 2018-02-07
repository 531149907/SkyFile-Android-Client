package work.hin.skyfileclient.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2018/2/6.
 */

public class UploadItem implements Parcelable {
    private int id;
    private String fileName;
    private String fileSize;
    private String fileType;
    private String percentage;

    protected UploadItem(Parcel in) {
        id = in.readInt();
        fileName = in.readString();
        fileSize = in.readString();
        fileType = in.readString();
        percentage = in.readString();
    }

    public static final Creator<UploadItem> CREATOR = new Creator<UploadItem>() {
        @Override
        public UploadItem createFromParcel(Parcel in) {
            return new UploadItem(in);
        }

        @Override
        public UploadItem[] newArray(int size) {
            return new UploadItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(fileName);
        parcel.writeString(fileSize);
        parcel.writeString(fileType);
        parcel.writeString(percentage);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
