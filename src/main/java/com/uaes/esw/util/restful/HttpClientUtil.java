package com.uaes.esw.util.restful;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * @author zhenghuan.wang
 */
public class HttpClientUtil {
    public static String putRequest(final URI uri, String username, String password, String body) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPut httpput = new HttpPut(uri);
            httpput.addHeader("Content-type", "application/json");
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("ISO-8859-1")));
            String authHeader = "Basic " + new String(encodedAuth, "ISO-8859-1");
            httpput.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
            StringEntity entity = new StringEntity(body);
            httpput.setEntity(entity);

            System.out.println("Executing request " + httpput.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpput);
            //String resEntity = EntityUtils.toString(response.getEntity());
            int resCode = response.getStatusLine().getStatusCode();
            try {
                System.out.println("-----try--------------");
                System.out.println(response.getStatusLine());
                //System.out.println(resEntity);
            } finally {
                System.out.println("-----finally--------");
                response.close();
            }
            System.out.println("resCode:" + resCode);
            return resCode + "";
        } finally {
            httpclient.close();
        }
    }
}
