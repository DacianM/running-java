package metrodigital.runningjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RunningJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunningJavaApplication.class, args);
    }

}
