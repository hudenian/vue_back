package com.huma.pdf;

import com.huma.common.utils.IoUtil;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hudenian
 * @date 2021/6/25
 */
public class PdfCreate {

    public static void main(String[] args) {
        String templatePath = "D:\\git-vue-study\\vueback\\pdf\\src\\main\\resources\\jianzhuan.pdf";
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("fill_1", "胡马");
        mapParam.put("fill_2", "第二");
        mapParam.put("fill_3", "第一");
        mapParam.put("fill_4", "比慢跑");
        mapParam.put("fill_5", "最后一等");
        browserExportPdf(mapParam, templatePath);
    }

    public static void browserExportPdf(Map<String, String> mapParam, String templatePath) {
        try {
            Map<String, String> data = new HashMap<>(10);
            data.put("fill_1", mapParam.get("fill_1"));
            data.put("fill_2", mapParam.get("fill_2"));
            data.put("fill_3", mapParam.get("fill_3"));
            data.put("fill_4", mapParam.get("fill_4"));
            data.put("fill_5", mapParam.get("fill_5"));

            InputStream in = IoUtil.readFileToInputStream(templatePath);

            PdfReader reader = new PdfReader(in);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper ps = new PdfStamper(reader, bos);
            /* 使用中文字体 */
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<>();
            fontList.add(bf);

            /* 取出报表模板中的所有字段 */
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            PdfUtil.fillData(fields, data);

            /* 必须要调用这个，否则文档不会生成的 */
            ps.setFormFlattening(true);
            ps.close();

            FileOutputStream fos = new FileOutputStream("c:\\奖状.pdf");
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bos.close();

        } catch (Exception e) {
            System.out.println("certificate create fail" + e.getMessage());
        }
    }
}
