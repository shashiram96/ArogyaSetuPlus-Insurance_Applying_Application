package ArogyaSetuPlus.app.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ArogyaSetuPlus.app.entity.DcPlanDetailsEntity;
import ArogyaSetuPlus.app.repository.IPlanDetailsRepository;

@Configuration
public class Dataloader {
    @Bean
    CommandLineRunner insertDemoPlans(IPlanDetailsRepository repo) {
	return args ->{
	    if(repo.count() ==0) {
		repo.save(new DcPlanDetailsEntity("SNAP", "Special Needs Assistance Program", "Employment income less than ₹ 1275000 per year"));
                repo.save(new DcPlanDetailsEntity("CCAP", "Child Care Assistance Program", "Employment income plus number of children aged 16 or less than 2 are eligible"));
                repo.save(new DcPlanDetailsEntity("MEDAID", "Medical Aid Program", "Citizens with total income (employment income + property income) below ₹ 1275000"));
                repo.save(new DcPlanDetailsEntity("MEDCARE", "Senior Citizen Healthcare Program", "Citizens aged 65 years or older are eligible for senior healthcare benefits"));
                repo.save(new DcPlanDetailsEntity("CAJW", "Citizen Assistance for Jobless Graduates", "Unemployed citizens who have graduated are eligible"));
                repo.save(new DcPlanDetailsEntity("QHP", "Qualified Healthcare Program", "All citizens, irrespective of their income or employment status"));
                System.out.println("Demo plan data inserted.");
	    }
	};
    }

}
