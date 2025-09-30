package qiuqiu.dto;

import lombok.Data;

/**
 * @author Jing Tao
 * @date 2025/9/30 16:17
 */
@Data
public class ImageRequest {

    // 开发者微信号（接收方，即公众号的原始ID）
    private String ToUserName;
    // 发送方帐号（一个OpenID，代表发送消息的用户）
    private String FromUserName;
    // 消息创建时间（整型，单位是秒）
    private String CreateTime;
    // 消息类型，对于图片消息来说，值为 "image"
    private String MsgType;
    // 图片链接（由微信服务器生成的临时URL，有效期较短）
    private String PicUrl;
    // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String MediaId;
    // 消息id，64位整型
    private String MsgId;
}
