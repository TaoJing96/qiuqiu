package qiuqiu.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author Jing Tao
 * @date 2023/1/11 21:42
 */
@Slf4j
public class HttpUtil {

    private static RestTemplate restTemplate = initRestTemplate();

    private static RestTemplate initRestTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(10000);
        factory.setConnectTimeout(15000);
        return new RestTemplate(factory);
    }

    public static <T> T post(Class<T> clazz, String body, String url, Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.forEach(httpHeaders::add);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("post fail, body=" + body);
            throw new RuntimeException("post fail");
        }
        return JsonUtil.fromJson(response.getBody(), clazz);
    }

    public static byte[] downloadFile(String imageUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36");
        return restTemplate.execute(
                imageUrl,
                HttpMethod.GET,
                requestCallback -> {
                    // 额外请求配置（可选）
                },
                response -> {
                    // 验证响应状态
                    if (response.getStatusCode() != HttpStatus.OK) {
                        throw new IOException("图片下载失败，状态码: " + response.getStatusCode());
                    }
                    try (InputStream responseStream = response.getBody();
                         ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = responseStream.read(buffer)) != -1) {
                            baos.write(buffer, 0, len);
                        }
                        return baos.toByteArray();
                    }
                }
        );
    }

    public static void main(String[] args) throws IOException {
        String imageUrl = "http://mmbiz.qpic.cn/mmbiz_jpg/BuxA5fWIgY5I90qSS4HKl8mX66ibwYxtvUuTKlCqSS0ibd2TC5tqyB0nwJA6THUNvRKEHVQ6Na2gGHZlnJwjItGw/0";
        byte[] bytes = downloadFile(imageUrl);
        WxUtil.uploadInputStream(new ByteArrayInputStream(bytes), "test.jpg", "image/jpeg");
        System.out.println(1);
    }
}
