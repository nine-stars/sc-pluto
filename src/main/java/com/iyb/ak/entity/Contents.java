package com.iyb.ak.entity;

import com.iyb.ak.entity.base.BaseEntity;

import javax.persistence.*;

@Table(name = "t_contents")
public class Contents extends BaseEntity{
    /**
     * post表主键
     */
    @Id
    private Integer cid;

    /**
     * 内容标题
     */
    private String title;

    /**
     * 内容缩略名
     */
    private String slug;

    /**
     * 内容生成时的GMT unix时间戳
     */
    private Integer created;

    /**
     * 内容更改时的GMT unix时间戳
     */
    private Integer modified;

    /**
     * 内容所属用户id
     */
    @Column(name = "author_id")
    private Integer authorId;

    /**
     * 内容类别
     */
    private String type;

    /**
     * 内容状态
     */
    private String status;

    /**
     * 标签列表
     */
    private String tags;

    /**
     * 分类列表
     */
    private String categories;

    /**
     * 点击次数
     */
    private Integer hits;

    /**
     * 内容所属评论数
     */
    @Column(name = "comments_num")
    private Integer commentsNum;

    /**
     * 是否允许评论
     */
    @Column(name = "allow_comment")
    private Boolean allowComment;

    /**
     * 是否允许ping
     */
    @Column(name = "allow_ping")
    private Boolean allowPing;

    /**
     * 允许出现在聚合中
     */
    @Column(name = "allow_feed")
    private Boolean allowFeed;

    /**
     * 内容文字
     */
    private String content;

    /**
     * 获取post表主键
     *
     * @return cid - post表主键
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * 设置post表主键
     *
     * @param cid post表主键
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取内容标题
     *
     * @return title - 内容标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置内容标题
     *
     * @param title 内容标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容缩略名
     *
     * @return slug - 内容缩略名
     */
    public String getSlug() {
        return slug;
    }

    /**
     * 设置内容缩略名
     *
     * @param slug 内容缩略名
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * 获取内容生成时的GMT unix时间戳
     *
     * @return created - 内容生成时的GMT unix时间戳
     */
    public Integer getCreated() {
        return created;
    }

    /**
     * 设置内容生成时的GMT unix时间戳
     *
     * @param created 内容生成时的GMT unix时间戳
     */
    public void setCreated(Integer created) {
        this.created = created;
    }

    /**
     * 获取内容更改时的GMT unix时间戳
     *
     * @return modified - 内容更改时的GMT unix时间戳
     */
    public Integer getModified() {
        return modified;
    }

    /**
     * 设置内容更改时的GMT unix时间戳
     *
     * @param modified 内容更改时的GMT unix时间戳
     */
    public void setModified(Integer modified) {
        this.modified = modified;
    }

    /**
     * 获取内容所属用户id
     *
     * @return author_id - 内容所属用户id
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 设置内容所属用户id
     *
     * @param authorId 内容所属用户id
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取内容类别
     *
     * @return type - 内容类别
     */
    public String getType() {
        return type;
    }

    /**
     * 设置内容类别
     *
     * @param type 内容类别
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取内容状态
     *
     * @return status - 内容状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置内容状态
     *
     * @param status 内容状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取标签列表
     *
     * @return tags - 标签列表
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置标签列表
     *
     * @param tags 标签列表
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 获取分类列表
     *
     * @return categories - 分类列表
     */
    public String getCategories() {
        return categories;
    }

    /**
     * 设置分类列表
     *
     * @param categories 分类列表
     */
    public void setCategories(String categories) {
        this.categories = categories;
    }

    /**
     * 获取点击次数
     *
     * @return hits - 点击次数
     */
    public Integer getHits() {
        return hits;
    }

    /**
     * 设置点击次数
     *
     * @param hits 点击次数
     */
    public void setHits(Integer hits) {
        this.hits = hits;
    }

    /**
     * 获取内容所属评论数
     *
     * @return comments_num - 内容所属评论数
     */
    public Integer getCommentsNum() {
        return commentsNum;
    }

    /**
     * 设置内容所属评论数
     *
     * @param commentsNum 内容所属评论数
     */
    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    /**
     * 获取是否允许评论
     *
     * @return allow_comment - 是否允许评论
     */
    public Boolean getAllowComment() {
        return allowComment;
    }

    /**
     * 设置是否允许评论
     *
     * @param allowComment 是否允许评论
     */
    public void setAllowComment(Boolean allowComment) {
        this.allowComment = allowComment;
    }

    /**
     * 获取是否允许ping
     *
     * @return allow_ping - 是否允许ping
     */
    public Boolean getAllowPing() {
        return allowPing;
    }

    /**
     * 设置是否允许ping
     *
     * @param allowPing 是否允许ping
     */
    public void setAllowPing(Boolean allowPing) {
        this.allowPing = allowPing;
    }

    /**
     * 获取允许出现在聚合中
     *
     * @return allow_feed - 允许出现在聚合中
     */
    public Boolean getAllowFeed() {
        return allowFeed;
    }

    /**
     * 设置允许出现在聚合中
     *
     * @param allowFeed 允许出现在聚合中
     */
    public void setAllowFeed(Boolean allowFeed) {
        this.allowFeed = allowFeed;
    }

    /**
     * 获取内容文字
     *
     * @return content - 内容文字
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容文字
     *
     * @param content 内容文字
     */
    public void setContent(String content) {
        this.content = content;
    }
}