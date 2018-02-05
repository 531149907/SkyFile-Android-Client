package work.hin.skyfileclient.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class FileShards implements Parcelable {
    private int id;
    private String shardName;
    private String location;
    private int status;

    protected FileShards(Parcel in) {
        id = in.readInt();
        shardName = in.readString();
        location = in.readString();
        status = in.readInt();
    }

    public static final Creator<FileShards> CREATOR = new Creator<FileShards>() {
        @Override
        public FileShards createFromParcel(Parcel in) {
            return new FileShards(in);
        }

        @Override
        public FileShards[] newArray(int size) {
            return new FileShards[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShardName() {
        return shardName;
    }

    public void setShardName(String shardName) {
        this.shardName = shardName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shardName);
        parcel.writeString(location);
        parcel.writeInt(status);
    }
}
