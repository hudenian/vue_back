package com.huma.mail;

import com.huma.mail.config.MailConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hudenian
 * @date 2021/6/8
 */
@Slf4j
public class MailUtil {

    @Resource
    private MailConfig mailConfig;

    public static void main(String[] args) {
        MailInfo mailInfo = new MailInfo();
        mailInfo.setSubject("测试邮件发送");

        String content = "<br>" + "您好!" + "<br/>" + "<br>" + "&nbsp&nbsp&nbsp&nbsp系统检测到<br/>" +
                "<br>" + "&nbsp&nbsp&nbsp&nbsp时间：" + "132" + "<br/>" +
                "<br>" + "<u>&nbsp&nbsp系统自动发送，请勿回复。<u/><br/>";

        mailInfo.setContent(content);

        List<String> toAddressList = new ArrayList<>();
        toAddressList.add("hudenian@matrixelements.com");
        // 收件人
        mailInfo.setToAddress(toAddressList);

        boolean flg = new MailUtil().sendEmail(mailInfo);
        System.out.println("发邮件：" + flg);

    }

    /**
     * 发送 邮件方法 (Html格式，支持附件)
     *
     * @return void
     */
    public boolean sendEmail(MailInfo mailInfo) {

        String mailSmtpHost = mailConfig.getMailHost();
        String mailFrom = mailConfig.getMailFrom();
        String mailUser = mailConfig.getMailUser();
        String mailPassword = mailConfig.getMailPwd();

       /* String mailSmtpHost = "smtp.163.com";
        String mailFrom = "hudenian@163.com";
        String mailUser =  "hudenian@163.com";
        String mailPassword = "SXDRTMJXQEHIYAAY";*/

        try {
            HtmlEmail email = new HtmlEmail();
            // 配置信息
            email.setHostName(mailSmtpHost);
            email.setFrom(mailFrom);
            email.setAuthentication(mailUser, mailPassword);
            email.setCharset("UTF-8");
            email.setSubject(mailInfo.getSubject());
            email.setHtmlMsg(mailInfo.getContent());

            // 添加附件
            List<EmailAttachment> attachments = mailInfo.getAttachments();
            if (null != attachments && attachments.size() > 0) {
                for (EmailAttachment attachment : attachments) {
                    email.attach(attachment);
                }
            }

            // 收件人
            List<String> toAddress = mailInfo.getToAddress();
            if (null != toAddress && toAddress.size() > 0) {
                for (String address : toAddress) {
                    email.addTo(address);
                }
            }
            // 抄送人
            List<String> ccAddress = mailInfo.getCcAddress();
            if (null != ccAddress && ccAddress.size() > 0) {
                for (String address : ccAddress) {
                    email.addCc(address);
                }
            }
            //邮件模板 密送人
            List<String> bccAddress = mailInfo.getBccAddress();
            if (null != bccAddress && bccAddress.size() > 0) {
                for (String address : bccAddress) {
                    email.addBcc(address);
                }
            }
            email.send();
            return true;
        } catch (EmailException e) {
            log.error("发送邮件失败，失败原因",e);
            return false;
        }

    }
}

