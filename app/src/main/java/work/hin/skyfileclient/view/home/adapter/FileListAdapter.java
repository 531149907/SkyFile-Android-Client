package work.hin.skyfileclient.view.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import work.hin.skyfileclient.R;
import work.hin.skyfileclient.model.domain.File;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class FileListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int SECTION_HEADER = 0;
    private static final int SECTION_DATE_HEADER = 1;
    private static final int FILE_TYPE = 2;
    private static final int SPACE = 3;

    private HashMap<String, List<File>> data;
    private OnItemClickListener onItemClickListener;
    private Context context;

    private int totalDataCount = 0;
    private List<Integer> sections = new ArrayList<>();
    private List<Integer> files = new ArrayList<>();
    private List<Integer> spaces = new ArrayList<>();
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, String> dateSectionMapper = new HashMap<>();
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Integer> fileMapper = new HashMap<>();

    public interface OnItemClickListener {
        void onItemClick(File file);
    }

    public void addOnClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FileListAdapter(Context context) {
        this.context = context;
        this.data = new HashMap<>();
    }

    public void setData(HashMap<String, List<File>> data) {
        this.data = data;
        sortType();
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
            case SECTION_DATE_HEADER:
                view = inflater.inflate(R.layout.file_list_date_header, parent, false);
                return new DateSectionViewHolder(view);
            case FILE_TYPE:
                view = inflater.inflate(R.layout.file_list_item, parent, false);
                return new FileViewHolder(view);
            case SPACE:
                view = inflater.inflate(R.layout.file_list_space, parent, false);
                return new SpaceViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SectionViewHolder) {
            SectionViewHolder sectionHolder = (SectionViewHolder) holder;
            sectionHolder.sectionTitle.setText("文件列表");
        } else if (holder instanceof DateSectionViewHolder) {
            DateSectionViewHolder dateSectionHolder = (DateSectionViewHolder) holder;
            String title = dateSectionMapper.get(position);
            dateSectionHolder.sectionDateTitle.setText(title);
        } else if (holder instanceof FileViewHolder) {
            FileViewHolder fileHolder = (FileViewHolder) holder;
            final File item = getFileByPosition(position);
            fileHolder.frame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(item);
                    }
                }
            });
            int iconR;
            switch (item.getFileType().toUpperCase()) {
                case "PDF":
                    iconR = R.drawable.ic_file_type_pdf;
                    break;
                case "DOC":
                case "DOCX":
                case "PPT":
                case "PPTX":
                case "XLS":
                case "XLSX":
                    iconR = R.drawable.ic_file_type_office;
                    break;
                case "TXT":
                    iconR = R.drawable.ic_file_type_text;
                    break;
                case "MP3":
                case "M4A":
                    iconR = R.drawable.ic_file_type_music;
                    break;
                case "JPG":
                case "PNG":
                case "BMP":
                case "GIF":
                    iconR = R.drawable.ic_file_type_photo;
                    break;
                case "AVI":
                case "MKV":
                case "MP4":
                    iconR = R.drawable.ic_file_type_video;
                    break;
                default:
                    iconR = R.drawable.ic_file_type_other;
            }
            fileHolder.fileIcon.setImageDrawable(context.getDrawable(iconR));
            fileHolder.fileName.setText(item.getFileOriginalName());
            fileHolder.fileInfo.setText(item.getFileSize() + " · " + item.getFileType() + " · " + item.getUploadDate());
            if (files.contains(position + 1)) {
                fileHolder.dividerContinued.setAlpha(1.0f);
            } else {
                fileHolder.dividerEnd.setAlpha(1.0f);
            }
        }
    }

    @Override
    public int getItemCount() {
        return totalDataCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SECTION_HEADER;
        } else if (sections.contains(position)) {
            return SECTION_DATE_HEADER;
        } else if (files.contains(position)) {
            return FILE_TYPE;
        } else if (spaces.contains(position)) {
            return SPACE;
        } else {
            return SPACE;
        }
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {
        TextView sectionTitle;

        public SectionViewHolder(View itemView) {
            super(itemView);
            this.sectionTitle = itemView.findViewById(R.id.section_title);
        }
    }

    class DateSectionViewHolder extends RecyclerView.ViewHolder {
        TextView sectionDateTitle;


        public DateSectionViewHolder(View itemView) {
            super(itemView);
            this.sectionDateTitle = itemView.findViewById(R.id.section_date_title);
        }
    }

    class FileViewHolder extends RecyclerView.ViewHolder {
        TextView fileName;
        ImageView fileIcon;
        TextView fileInfo;
        ImageView dividerContinued;
        ImageView dividerEnd;
        RelativeLayout frame;

        public FileViewHolder(View itemView) {
            super(itemView);
            this.frame = itemView.findViewById(R.id.frame);
            this.fileName = itemView.findViewById(R.id.file_name);
            this.fileInfo = itemView.findViewById(R.id.file_info);
            this.fileIcon = itemView.findViewById(R.id.file_icon);
            this.dividerContinued = itemView.findViewById(R.id.divider_continued);
            this.dividerEnd = itemView.findViewById(R.id.divider_end);
        }
    }

    class SpaceViewHolder extends RecyclerView.ViewHolder {
        public SpaceViewHolder(View itemView) {
            super(itemView);
        }
    }

    private void sortType() {
        if (!data.isEmpty()) {
            int count = 0;
            for (String key : data.keySet()) {
                count++;
                sections.add(count);
                dateSectionMapper.put(count, key);
                for (File file : data.get(key)) {
                    count++;
                    files.add(count);
                    fileMapper.put(count, file.getId());
                }
                count++;
                spaces.add(count);
            }
            totalDataCount = count;
        }
    }

    @Nullable
    private File getFileByPosition(int position) {
        int fileId = fileMapper.get(position);
        for (String key : data.keySet()) {
            for (File file : data.get(key)) {
                if (file.getId() == fileId) {
                    return file;
                }
            }
        }
        return null;
    }
}
