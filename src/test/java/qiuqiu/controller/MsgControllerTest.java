package qiuqiu.controller;

import org.springframework.mock.web.MockHttpServletRequest;
import qiuqiu.BaseTests;
import qiuqiu.enums.MaterialEnum;

import javax.annotation.Resource;

/**
 * @author Jing Tao
 * @date 2025/10/9 18:35
 */
public class MsgControllerTest extends BaseTests {

    private MockHttpServletRequest request;

    @Resource
    private MsgController msgController;

//    @Test
    public void testProcessImg() {
        request = new MockHttpServletRequest();
        request.setAttribute("picUrl", "http://mmbiz.qpic.cn/mmbiz_jpg/BuxA5fWIgY5I90qSS4HKl8mX66ibwYxtvEG3jqvlozGiaYVwtm9mm4JicvcVErxjtZIygIhQgVOZwlHRhJY2vMr5A/0");
        request.setAttribute("type", MaterialEnum.IMAGE.getType());
        request.setAttribute("msgId", "12423423423423");
        request.setAttribute("sender", "jj");
        msgController.processMessage(request, null);
    }

//    @Test
    public void testProcessAddDailyImg() {
        request = new MockHttpServletRequest();
        request.setAttribute("type", MaterialEnum.TEXT.getType());
        request.setAttribute("message", "2");
        request.setAttribute("sender", "jj");
        request.setAttribute("msgId", System.currentTimeMillis() + "");
        msgController.processMessage(request, null);
        testProcessAddDailyImg();
    }
}