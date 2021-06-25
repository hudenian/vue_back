package com.huma.pdf;

import com.huma.common.constants.ErrorMsgEnUs;
import com.huma.common.constants.ErrorMsgZhCn;
import com.huma.common.enums.RespCodeEnum;
import com.huma.common.exception.BusinessException;
import com.huma.common.utils.IoUtil;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hudenian
 * @date 2021/5/14
 */
@Slf4j
public class PdfUtil {

    static final String CERTIFICATE = "荣誉证书";

    public static void fillData(AcroFields fields, Map<String, String> data) throws IOException, DocumentException {
        for (String key : data.keySet()) {
            String value = data.get(key);
            fields.setField(key, value);
        }
    }

    public void browserExportPdf(Map<String, String> mapParam, String templatePath) {
        try {
            ServletRequestAttributes servletRequestAttributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (servletRequestAttributes != null) {
                OutputStream out = response(servletRequestAttributes);

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
                fillData(fields, data);

                /* 必须要调用这个，否则文档不会生成的 */
                ps.setFormFlattening(true);
                ps.close();

                out.write(bos.toByteArray());
                out.flush();
                out.close();
                bos.close();
            }
        } catch (Exception e) {
            log.error("certificate create fail,error msg:{}", e.getMessage(), e);
        }
    }

    private OutputStream response(ServletRequestAttributes servletRequestAttributes) {
        HttpServletResponse response = servletRequestAttributes.getResponse();
        if (response != null) {
            response.setContentType("application/pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            try {
                response.setHeader("Content-disposition", "attachment; filename="
                        .concat(String.valueOf(URLEncoder.encode(CERTIFICATE + ".pdf", "UTF-8"))));
                return response.getOutputStream();
            } catch (Exception e) {
                log.error("Donation certificate create fail,error msg:{}", e.getMessage(), e);
                throw new BusinessException(RespCodeEnum.BIZ_FAILED, ErrorMsgZhCn.PDF_DOWNLOAD_ERROR, ErrorMsgEnUs.PDF_DOWNLOAD_ERROR);
            }
        } else {
            throw new BusinessException(RespCodeEnum.BIZ_FAILED, ErrorMsgZhCn.PDF_DOWNLOAD_ERROR, ErrorMsgEnUs.PDF_DOWNLOAD_ERROR);
        }
    }
}
