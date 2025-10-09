package qiuqiu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BaseTests.class)
@ComponentScan("qiuqiu")
//@ActiveProfiles(profiles = {"test"})
public class BaseTests {
    @Test
    public void baseTest() {

    }
}
