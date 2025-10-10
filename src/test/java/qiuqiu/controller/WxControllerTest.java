package qiuqiu.controller;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import qiuqiu.BaseTests;

import javax.annotation.Resource;

/**
 * @author Jing Tao
 * @date 2025/10/9 18:35
 */
public class WxControllerTest extends BaseTests {

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    @Resource
    private WxController wxController;

//    @Test
    public void testProcessImg() throws Exception {
        testProcessAddDailyImgStep1();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        String xml = "{\"Content\":\"13\",\"CreateTime\":\"1760086146\",\"ToUserName\":\"gh_fffa730a3a73\",\"FromUserName\":\"oiOx76PwDdjbOR1Ai2MQ0sm3pFCs\",\"MsgType\":\"text\",\"MsgId\":\"25198374790000242\"}";
        wxController.receiveMessage(xml, request, response);
        System.out.println(1);
    }

//    @Test
    public void testProcessAddDailyImgStep1() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        String xml = "{\"MediaId\":\"qWLM0E3KChpb_3TjM7hbuEDyWnicDlJ8eluFq1sb8I_rr1OTmYHNlik-_qb4-lHt\",\"CreateTime\":\"1760086159\",\"ToUserName\":\"gh_fffa730a3a73\",\"FromUserName\":\"oiOx76PwDdjbOR1Ai2MQ0sm3pFCs\",\"MsgType\":\"image\",\"PicUrl\":\"http://mmbiz.qpic.cn/mmbiz_jpg/BuxA5fWIgY6vrwYicp2TVCxAMtIbNn0eIWoNFBW4uSbKiahfvFVtAMKx2jgGMDQy6DRzN4yJyMCEgnCXCYUwHVcA/0\",\"MsgId\":\"25198372444086744\"}";
        wxController.receiveMessage(xml, request, response);
        System.out.println(1);
    }
}
