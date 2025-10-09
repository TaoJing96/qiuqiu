package qiuqiu.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;

import java.io.InputStream;

import static com.qcloud.cos.demo.ci.ClientUtils.getCosClient;

/**
 * @author Jing Tao
 * @date 2025/10/4 22:32
 */
public class WxUtil {

    private static final String BUCKET_NAME = "7072-prod-7go6fjep656bf9a5-1316521764";
    private static final String REGION = "ap-shanghai";

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
