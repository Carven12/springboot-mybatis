package com.lc.demo.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author liangc
 */
@Data
public class Wind {

    /**
     * 栏目
     */
    private String category;

    /**
     * 关键词
     */
    private String key;

    /**
     * 值（数组）
     */
    private JSONArray value;

}
