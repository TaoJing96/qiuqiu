package qiuqiu.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.squareup.okhttp.OkHttpClient;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpHostConfig;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

import java.io.InputStream;

import static com.qcloud.cos.demo.ci.ClientUtils.getCosClient;

/**
 * @author Jing Tao
 * @date 2025/10/4 22:32
 */
public class WxUtil {

    private static final WxMpService WX_MP_SERVICE = initWxMpService();
    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final String BUCKET_NAME = "7072-prod-7go6fjep656bf9a5-1316521764";
    private static final String REGION = "ap-shanghai";

    private static WxMpService initWxMpService() {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId("wx505e6119afdd7117");
        config.setSecret("AAQ9G7sEAAABAAAAAAAAvxLci9Ek8HjaVl/naCAAAAAraAdzKm8i8JwFJ68cDOJBtIHsmv3F8e00LCv7f+tYvtD/TFAZoZAu4s/DMZ9Pl/zk0GdGYDOdrh6ks0Jc1u0jN3WocpvdX5igh0wC3Rse4WMYqJ3NdBF5yai+grGA5o60UQTfNYYB3vkiULYa69j4abES02oi/4I=");
        config.setHostConfig(new WxMpHostConfig());

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }

    public static String uploadInputStream(InputStream inputStream, String cosKey, String contentType) {
        COSClient cosClient = null;
        try {
            cosClient = getCosClient("AKIDBFAEG976zy9NhuvvmT3Yt55vgHjz990h", "Z3e9YourgMsFUcgdRBoHvaBe2dItNm6I", REGION);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            PutObjectRequest putRequest = new PutObjectRequest(BUCKET_NAME, cosKey, inputStream, metadata);
            PutObjectResult putObjectResult = cosClient.putObject(putRequest);

            return String.format("https://%s.cos.%s.myqcloud.com/%s", BUCKET_NAME, REGION, cosKey);
        } catch (Exception e) {
            throw new RuntimeException("上传InputStream到COS失败", e);
        } finally {
            if (cosClient != null) {
                cosClient.shutdown();
            }
        }
    }
}
