package com.lc.demo.utils;

import ch.qos.logback.core.util.FileUtil;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

/**
 * Description:
 * <p>
 * ClassName: FileUtils
 * date: 2021/3/24 22:08
 *
 * @author liangc
 * @version 1.0
 */
public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * 获取流文件
     *
     * @param ins
     * @param file
     */
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     *
     * @param file
     */
    public static void deleteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }


//    private ByteArrayOutputStream writeZip(List<String> files, String zipname) throws IOException {
//        String fileName = zipname + ".zip";
//        ZipOutputStream zos = new ZipOutputStream(os);
//        byte[] buf = new byte[8192];
//        int len;
//        for (int i = 0; i < files.size(); i++) {
//            File file = new File(files.get(i));
//            if (!file.isFile()) {
//                continue;
//            }
//            ZipEntry ze = new ZipEntry(file.getName());
//            zos.putNextEntry(ze);
//            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
//            while ((len = bis.read(buf)) > 0) {
//                zos.write(buf, 0, len);
//            }
//            zos.closeEntry();
//        }
//        zos.closeEntry();
//        zos.close();
//    }

    /**
     *
     * @param templateFile 模板文件位置
     * @param contentMap   替换内容
     * @param fileName     生成文件名
     * @return 文件流
     */
    public static FileOutputStream generateDoc(String templateFile, Map<String, String> contentMap, String fileName) throws IOException {

        InputStream inputStream = new FileInputStream(templateFile);
        XWPFDocument document = WordUtils.changWordForCommon(inputStream, contentMap);

        //导出到文件
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        FileOutputStream outputStream = new FileOutputStream(fileName);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.close();
        return outputStream;
    }
}
