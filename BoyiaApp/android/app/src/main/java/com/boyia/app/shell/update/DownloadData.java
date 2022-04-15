package com.boyia.app.shell.update;

import com.boyia.app.common.db.BoyiaData;
import com.boyia.app.common.db.DBAnnotation.DBColumn;
import com.boyia.app.common.db.DBAnnotation.DBTable;

@DBTable(name = "downloads")
public class DownloadData extends BoyiaData {
    public static final int PAUSE = 0x1;
    // DOWNLOADING是一种特殊状态，此过程不用存储在数据库中
    public static final int DOWNLOADING = 0x2;
    public static final int FINISHED = 0x3;
    public static final int ERROR = 0x4;

    @DBColumn(name = "file_url")
    private String fileUrl;

    @DBColumn(name = "file_path")
    private String filePath;

    @DBColumn(name = "file_name")
    private String fileName;

    @DBColumn(name = "current_size")
    private long currentSize = 0L;

    @DBColumn(name = "max_len")
    private long maxLength = 0L;

    @DBColumn(name = "status")
    private int status = PAUSE;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(long currentSize) {
        this.currentSize = currentSize;
    }

    public long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(long maxLength) {
        this.maxLength = maxLength;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
