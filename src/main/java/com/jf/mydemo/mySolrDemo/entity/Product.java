package com.jf.mydemo.mySolrDemo.entity;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-05-30 15:37
 * @Description: solr测试类用的实体类
 * To change this template use File | Settings | File and Templates.
 */

public class Product {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private String keywords;
    @Field
    private String description;
    @Field
    private String sn;

    public Product() {
        super();
    }

    public Product(String id, String name, String keywords, String description,
                   String sn) {
        super();
        this.id = id;
        this.name = name;
        this.keywords = keywords;
        this.description = description;
        this.sn = sn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }
}
