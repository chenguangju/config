package cn.rabbitmq.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

	public static void deleteService(String id) {
        try {
            URL url = new URL("http://localhost:7777/name");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");

          /*  byte[] b = (user1 + ":" + password1).getBytes();
            String tUserPwdEncryption = Base64.encodeBase64String(b);
			String tAuthorization = "Basic " + tUserPwdEncryption;

            connection.setRequestProperty("Authorization", tAuthorization);*/
            connection.connect();
            /*BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sbf = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sbf.append(lines);
            }
            reader.close();*/
            connection.disconnect();
        } catch (Exception e) {
        	/*logger.error("DCM通过ID删除服务失败", e);*/
        }
    }
}
