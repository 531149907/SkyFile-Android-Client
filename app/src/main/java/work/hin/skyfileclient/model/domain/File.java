package work.hin.skyfileclient.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class File implements Parcelable {
    private int id;
    private int ownerId;
    private String fileType;
    private String fileOriginalName;
    private String password;
    private String hash;
    private String health;
    private String shareIds;
    private String uploadDate;
    private String fileSize;

    public File(int id, int ownerId, String fileType, String fileOriginalName, String password, String hash, String health, String shareIds, String uploadDate, String fileSize) {
        this.id = id;
        this.ownerId = ownerId;
        this.fileType = fileType;
        this.fileOriginalName = fileOriginalName;
        this.password = password;
        this.hash = hash;
        this.health = health;
        this.shareIds = shareIds;
        this.uploadDate = uploadDate;
        this.fileSize = fileSize;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    protected File(Parcel in) {
        id = in.readInt();
        ownerId = in.readInt();
        fileType = in.readString();
        fileOriginalName = in.readString();
        password = in.readString();
        hash = in.readString();
        health = in.readString();
        shareIds = in.readString();
        uploadDate = in.readString();
        fileSize = in.readString();
    }

    public static final Creator<File> CREATOR = new Creator<File>() {
        @Override
        public File createFromParcel(Parcel in) {
            return new File(in);
        }

        @Override
        public File[] newArray(int size) {
            return new File[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileOriginalName() {
        return fileOriginalName;
    }

    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getShareIds() {
        return shareIds;
    }

    public void setShareIds(String shareIds) {
        this.shareIds = shareIds;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(ownerId);
        parcel.writeString(fileType);
        parcel.writeString(fileOriginalName);
        parcel.writeString(password);
        parcel.writeString(hash);
        parcel.writeString(health);
        parcel.writeString(shareIds);
        parcel.writeString(uploadDate);
        parcel.writeString(fileSize);
    }
}
