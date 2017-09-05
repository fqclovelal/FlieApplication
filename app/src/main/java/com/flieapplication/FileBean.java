package com.flieapplication;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/9/5 0005.
 */
@Entity
public class FileBean {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String fileName;
    @NotNull
    @Unique
    private String fileUrl;
    @Generated(hash = 894216748)
    public FileBean(Long id, @NotNull String fileName, @NotNull String fileUrl) {
        this.id = id;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
    @Generated(hash = 1910776192)
    public FileBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileUrl() {
        return this.fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
