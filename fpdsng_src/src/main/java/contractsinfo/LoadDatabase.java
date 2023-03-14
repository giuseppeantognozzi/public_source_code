package contractsinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	// @Order(Ordered.LOWEST_PRECEDENCE)
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {

		return args -> {
			log.info("Preloading employee data " + repository.save(new Employee("Frank Brown", "analyst")));
			log.info("Preloading  employee data " + repository.save(new Employee("Ed Spirolo", "Big Cheese")));
			log.info("Preloading  employee data " + repository.save(new Employee("Joe Antognozzi", "Developer")));
			log.info("Preloading  employee data " + repository.save(new Employee("Forrest Richardson", "manager")));
			log.info("Preloading employee data " + repository.save(new Employee("George Spak", "DBA")));
			log.info("Preloading  employee data " + repository.save(new Employee("Ivonne Barclot", "Appian Developer")));
			log.info("Preloading  employee data " + repository.save(new Employee("Jim Rock", "Tester")));
			log.info("Preloading  employee data "
					+ repository.save(new Employee("Jamie Bullock", "unix administrator")));

		};
	}

	// @Order(Ordered.HIGHEST_PRECEDENCE)
	@Bean
	CommandLineRunner run(AddressRepository repository) {

		return args -> {

			log.info("Preloading  address data "
					+ repository.save(new Address("653 Pineridge rd", "Condon", "59826", "Montana, USA")));
			log.info("Preloading  address data "
					+ repository.save(new Address("101 stanley drive", "Williamsburg", "23185", "Virginia, USA")));
			log.info("Preloading  address data "
					+ repository.save(new Address("3271 Deerfield Ct", "Williamsburg", "23188", "Virginia, USA")));
			log.info("Preloading  address data "
					+ repository.save(new Address("112 Hell Rd", "Richmond", "12345", "Richmond, USA")));

			log.info("Preloading  address data "
					+ repository.save(new Address("123 Richmond Rd", "Chicago", "34533", "Illinois, USA")));
			log.info("Preloading  address data "
					+ repository.save(new Address("445 Bow Creek", "Cheyenne", "22990", "Wyoming, USA")));
			log.info("Preloading  address data "
					+ repository.save(new Address("234 Beach Rd", "Miami", "23188", "Florida, USA")));
			log.info("Preloading  address data "
					+ repository.save(new Address("Blackfoot Rd", "Atlanta", "12345", "Georgia, USA")));

		};
	}

}
