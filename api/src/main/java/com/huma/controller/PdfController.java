package com.huma.controller;

import com.huma.common.constants.SysConfig;
import com.huma.pdf.PdfUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hudenian
 * @date 2021/6/25
 */
@Slf4j
@RestController
@Api(tags = "下载pdf文件接口")
@RequestMapping(value = "pdf", produces = MediaType.APPLICATION_JSON_VALUE)
public class PdfController {

    @Resource
    private SysConfig sysConfig;

    @GetMapping("certificate")
    @ApiOperation(value = "pdf证书", notes = "pdf证书")
    public void certificate() {
        Map<String, String> mapParam = new HashMap<>(5);
        mapParam.put("fill_1", "胡马");
        mapParam.put("fill_2", "第二");
        mapParam.put("fill_3", "第一");
        mapParam.put("fill_4", "比慢跑");
        mapParam.put("fill_5", "最后一等");
        new PdfUtil().browserExportPdf(mapParam, sysConfig.getCertificateTemplatePath());
    }
}
