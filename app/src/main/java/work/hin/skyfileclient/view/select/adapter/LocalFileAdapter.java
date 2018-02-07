package work.hin.skyfileclient.view.select.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work.hin.skyfileclient.R;
import work.hin.skyfileclient.model.domain.LocalFile;

/**
 * Created by zhouzhixuan on 2018/2/7.
 */

public class LocalFileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<LocalFile> data;
    private Context context;
    private HashMap<Integer, Boolean> radioStatus;

    @SuppressLint("UseSparseArrays")
    public LocalFileAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
        radioStatus = new HashMap<>();
    }

    public void setData(List<LocalFile> data) {
        this.data = data;
        for (int i = 0; i < data.size(); i++) {
            radioStatus.put(i, false);
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_file_list_item, parent, false);
        return new LocalFileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final LocalFileViewHolder viewHolder = (LocalFileViewHolder) holder;
        LocalFile item = data.get(position);
        viewHolder.fileName.setText(item.getFileName());
        viewHolder.fileInfo.setText(item.getFileSize() + " · " + item.getFileType().toUpperCase());
        viewHolder.frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int old = 0;
                for (int pos : radioStatus.keySet()) {
                    if (radioStatus.get(pos)) {
                        old = pos;
                        radioStatus.put(pos, false);
                    }
                }
                radioStatus.put(position, true);
                viewHolder.radioButton.setChecked(true);
                notifyItemChanged(old);
            }
        });
        viewHolder.radioButton.setChecked(radioStatus.get(position));
        if (position + 1 >= data.size()) {
            viewHolder.dividerEnd.setAlpha(1.0f);
        }else{
            viewHolder.dividerContinued.setAlpha(1.0f);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class LocalFileViewHolder extends RecyclerView.ViewHolder {
        TextView fileName;
        TextView fileInfo;
        ImageView dividerContinued;
        ImageView dividerEnd;
        RelativeLayout frame;
        RadioButton radioButton;

        public LocalFileViewHolder(View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.file_name);
            fileInfo = itemView.findViewById(R.id.file_info);
            dividerContinued = itemView.findViewById(R.id.divider_continued);
            dividerEnd = itemView.findViewById(R.id.divider_end);
            frame = itemView.findViewById(R.id.frame);
            radioButton = itemView.findViewById(R.id.radio_button);
        }
    }

    public LocalFile getResult() {
        for (int pos : radioStatus.keySet()) {
            if (radioStatus.get(pos)) {
                return data.get(pos);
            }
        }
        return null;
    }
}
