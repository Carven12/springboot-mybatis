package com.lc.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.lc.demo.model.Value;
import com.lc.demo.model.Wind;
import com.lc.demo.utils.JsonUtil;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liangc
 */
@Controller
@RequestMapping(value="/v1/api")
public class DownLoadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DownLoadController.class);

    @ResponseBody
    @RequestMapping(value = "/getPdf", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getPdf() throws IOException, DocumentException {

        String path = ResourceUtils.getFile("classpath:static/json/wind.json").getPath();
        JSONObject json = JsonUtil.readJsonFile(path);
        JSONArray windHrArr = json.getJSONArray("windHR");
        LOGGER.info("windHrArr:", windHrArr.toJSONString());
        JSONArray windTechArr = json.getJSONArray("windTech");
        LOGGER.info("windTechArr:", windTechArr.toJSONString());

        // A4纸大小 左、右、上、下
        Document document = new Document(PageSize.A4, 80, 79, 20, 45);

        // 中文处理
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.EMBEDDED);
        Font textFont = new Font(bfChinese, 12, Font.NORMAL);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        //------------开始写数据-------------------
        Font titleChinese = new Font(bfChinese, 20, Font.BOLD);
        Paragraph title = new Paragraph("万得管理文化介绍", titleChinese);

        // 居中设置
        title.setAlignment(Element.ALIGN_CENTER);

        //设置上面空白宽度
        title.setLeading(1f);
        document.add(title);
        Font boldChinese = new Font(bfChinese, 12, Font.NORMAL);

        // 署名
        Paragraph signature = new Paragraph("梁超  部门：医药大数据 工号：22317", boldChinese);
        signature.setAlignment(Element.ALIGN_RIGHT);
        document.add(signature);

        // 写入正文
        windHrArr.stream().forEach(
                jsonObject -> {
                    Wind wind = JSONObject.parseObject(jsonObject.toString(), Wind.class);
                    AtomicReference<String> str = new AtomicReference<>(wind.getCategory() + ":" + wind.getKey());
                    wind.getValue().stream().forEach(obj -> {
                        Value value = JSONObject.parseObject(obj.toString(), Value.class);
                        str.set(str + " " + value.getItem());
                    });

                    Paragraph textHr = new Paragraph(str.toString(), textFont);
                    textHr.setAlignment(Element.ALIGN_LEFT);
                    try {
                        document.add(textHr);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }
        );

        windTechArr.stream().forEach(
                jsonObject -> {
                    Wind wind = JSONObject.parseObject(jsonObject.toString(), Wind.class);

                    AtomicReference<String> str = new AtomicReference<>(wind.getCategory() + ":" + wind.getKey());
                    wind.getValue().stream().forEach(obj -> {
                        Value value = JSONObject.parseObject(obj.toString(), Value.class);
                        str.set(str + " " + value.getItem());
                        if(Strings.isNotBlank(value.getUrl())) {
                            str.set(str + " 地址：" + value.getUrl());
                        }
                    });

                    Paragraph textHr = new Paragraph(str.toString(), textFont);
                    textHr.setAlignment(Element.ALIGN_LEFT);
                    try {
                        document.add(textHr);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }
        );

        //------------完成写入数据-------------------
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Content-Disposition", "attachment;filename=demo.pdf");
        return new ResponseEntity<byte[]>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.CREATED);
    }



}
