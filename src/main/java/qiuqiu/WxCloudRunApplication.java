package qiuqiu;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"qiuqiu.dao"})
@Slf4j
public class WxCloudRunApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxCloudRunApplication.class, args);
        log.info("start success");
    }
}
