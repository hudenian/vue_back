package com.huma.common.utils;

import com.huma.common.constants.SysConstant;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author hudenian
 * @date 2021/5/17
 */
public class IoUtil {

    public static InputStream readFileToInputStream(String templatePathPng) throws IOException {
        InputStream in;
        if (templatePathPng.contains(SysConstant.CLASSPATH)) {
            templatePathPng = templatePathPng.replace(SysConstant.CLASSPATH, "").trim();
            ClassPathResource resource = new ClassPathResource(templatePathPng);
            in = resource.getInputStream();
        } else {
            in = new FileInputStream(templatePathPng);
        }
        return in;
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
