package com.sofyun.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.XmlUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private static final String SEND_URL = "http://api.sms137.com:8888/v2sms.aspx";

    private static final String SMS_USER = "xxx";

    private static final String SMS_PWD = "xxx";

    private static final String SMS_ID = "xxx";

    private static final String SMS_RIME = "yyyyMMddhhmmss";

    private static boolean send(String mobile, String content){
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", SMS_ID);
        String timestamp = DateUtil.format(new Date(), SMS_RIME);
        params.put("timestamp", timestamp);
        params.put("sign", md5(SMS_USER + SMS_PWD + timestamp));
        params.put("mobile", mobile);
        params.put("content", content);
        params.put("sendTime", "");
        params.put("action", "send");
        params.put("extno", "");
        try {
            String xml = post(params, SEND_URL);
            Document document = XmlUtil.readXML(xml);
            Object value = XmlUtil.getByXPath("//returnsms/message", document, XPathConstants.STRING);
            logger.info("SMS RESULT : " + value.toString());
            if ("ok".equals(value.toString())){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    private static String post(Map<String, String> params, String url) throws Exception{

        HttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(params!=null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        InputStream inputStream = httpResponse.getEntity().getContent();

        String jsonstr = IOUtils.toString(inputStream,Charset.forName("UTF-8"));

        inputStream.close();

        return jsonstr;
    }

    private static HttpClient getHttpClient() {
        int timeout = (int) TimeUnit.SECONDS.toMillis(2);
        return HttpClients.custom()
                .setDefaultSocketConfig(SocketConfig.custom().setSoTimeout(timeout).setSoKeepAlive(true)
                        .setTcpNoDelay(true).build())
                .setDefaultConnectionConfig(ConnectionConfig.custom().setCharset(StandardCharsets.UTF_8).build())
                .setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(timeout)
                        .setConnectTimeout(timeout).setSocketTimeout(timeout).build())
                .setMaxConnPerRoute(20)
                .setMaxConnTotal(200)
                .build();
    }


    /**
     * 随机数
     * @param count
     * @return
     */
    public static String createCode(int count) {
        String code = "";
        for (int i=0;i<count;i++){
            code += RandomUtil.randomInt(0,9);
        }
       return code;
    }

    public static boolean sendVcode(String sign, String moblie, String code){
        String content = sign + "尊敬的客户，您的验证码是"+ code +"，请于5分钟内正确输入。如非本人操作，请忽略此短信";
        logger.info(content);
        return send(moblie,content);
    }

}
