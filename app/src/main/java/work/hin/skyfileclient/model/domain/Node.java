package work.hin.skyfileclient.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class Node implements Parcelable {
    private int id;
    private String username;
    private String password;
    private String location;
    private int count;
    private int status;

    protected Node(Parcel in) {
        id = in.readInt();
        username = in.readString();
        password = in.readString();
        location = in.readString();
        count = in.readInt();
        status = in.readInt();
    }

    public static final Creator<Node> CREATOR = new Creator<Node>() {
        @Override
        public Node createFromParcel(Parcel in) {
            return new Node(in);
        }

        @Override
        public Node[] newArray(int size) {
            return new Node[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(location);
        parcel.writeInt(count);
        parcel.writeInt(status);
    }
}
