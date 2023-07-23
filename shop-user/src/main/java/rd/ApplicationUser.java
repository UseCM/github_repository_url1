package rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EntityScan("com.rd.pojo")
@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationUser {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationUser.class,args);
    }
}
