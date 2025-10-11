package qiuqiu.dao.mapper;

import qiuqiu.BaseTests;
import qiuqiu.dao.po.Counter;

import javax.annotation.Resource;

/**
 * @author Jing Tao
 * @date 2025/10/10 19:09
 */
public class CounterMapperTest extends BaseTests {

    @Resource
    private CountersMapper counterMapper;

//    @Test
    public void testGetCounter() {
        Integer id = 1;
        Counter counter = counterMapper.getCounter(id);
        System.out.println(counter);
    }
}
