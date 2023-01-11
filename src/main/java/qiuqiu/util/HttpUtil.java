package qiuqiu.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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
}
