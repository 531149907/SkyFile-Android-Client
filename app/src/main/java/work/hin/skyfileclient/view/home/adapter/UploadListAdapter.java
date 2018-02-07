package work.hin.skyfileclient.view.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import work.hin.skyfileclient.R;
import work.hin.skyfileclient.model.domain.UploadItem;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class UploadListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int SECTION_HEADER = 0;
    private static final int UPLOAD_TYPE = 2;

    private List<UploadItem> data;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(UploadItem item);
    }

    public void addOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public UploadListAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void setData(List<UploadItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case SECTION_HEADER:
                view = inflater.inflate(R.layout.file_list_section_header, parent, false);
                return new SectionViewHolder(view);
            case UPLOAD_TYPE:
                view = inflater.inflate(R.layout.file_list_upload, parent, false);
                return new UploadViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SectionViewHolder) {
            SectionViewHolder sectionHolder = (SectionViewHolder) holder;
            sectionHolder.sectionTitle.setText("上传中");
        } else {
            UploadViewHolder uploadHolder = (UploadViewHolder) holder;
            final UploadItem item = data.get(position - 1);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(item);
                    }
                }
            });
            uploadHolder.fileName.setText(item.getFileName());
            uploadHolder.fileInfo.setText(item.getFileSize() + " · " + item.getFileType());
            uploadHolder.percentage.setText(item.getPercentage() + " %");
        }
    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? SECTION_HEADER : UPLOAD_TYPE;
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {
        TextView sectionTitle;

        public SectionViewHolder(View itemView) {
            super(itemView);
            this.sectionTitle = itemView.findViewById(R.id.section_title);
        }
    }

    class UploadViewHolder extends RecyclerView.ViewHolder {
        TextView percentage;
        TextView fileName;
        TextView fileInfo;

        public UploadViewHolder(View itemView) {
            super(itemView);
            this.percentage = itemView.findViewById(R.id.percentage);
            this.fileInfo = itemView.findViewById(R.id.file_info);
            this.fileName = itemView.findViewById(R.id.file_name);
        }
    }
}
