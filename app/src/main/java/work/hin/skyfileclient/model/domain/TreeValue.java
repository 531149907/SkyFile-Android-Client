package work.hin.skyfileclient.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class TreeValue implements Parcelable {
    private int id;
    private int fileId;
    private int fileShardId;
    private int level;
    private int order;
    private String value;

    protected TreeValue(Parcel in) {
        id = in.readInt();
        fileId = in.readInt();
        fileShardId = in.readInt();
        level = in.readInt();
        order = in.readInt();
        value = in.readString();
    }

    public static final Creator<TreeValue> CREATOR = new Creator<TreeValue>() {
        @Override
        public TreeValue createFromParcel(Parcel in) {
            return new TreeValue(in);
        }

        @Override
        public TreeValue[] newArray(int size) {
            return new TreeValue[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileShardId() {
        return fileShardId;
    }

    public void setFileShardId(int fileShardId) {
        this.fileShardId = fileShardId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(fileId);
        parcel.writeInt(fileShardId);
        parcel.writeInt(level);
        parcel.writeInt(order);
        parcel.writeString(value);
    }
}
