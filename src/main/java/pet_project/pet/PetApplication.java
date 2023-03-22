package pet_project.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class PetApplication {

	public static void main(String[] args) {

		SpringApplication.run(PetApplication.class, args);
	}
}
/*	@Scheduled(fixedDelayString= "${someJob.delay}")  // cron expression ---> cron = "* * * * * *" cron = "'sec' 'min' 'h' 'day' 'm' 'd of the week'"
	void someJob() throws InterruptedException{
		System.out.println("Now is " + new Date());
		//Thread.sleep(1000L);
	}

}*/
/*@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SchedulingConfiguration {

}*/
