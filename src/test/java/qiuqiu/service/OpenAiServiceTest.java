package qiuqiu.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import qiuqiu.BaseTests;
import qiuqiu.service.impl.OpenAiServiceImpl;

import javax.annotation.Resource;

/**
 * @author Jing Tao
 * @date 2023/1/11 22:11
 */
public class OpenAiServiceTest extends BaseTests {

    @Resource
    private OpenAiServiceImpl openAiService;

    @Test
    @Disabled
    public void testComplete() {
        String complete = openAiService.complete("我迷路了");
        System.out.println(complete);
    }
}
