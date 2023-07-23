package fr.inti.galaxy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.inti.galaxy.entities.Formation;
import fr.inti.galaxy.entities.ModuleFormation;
import fr.inti.galaxy.entities.Utilisateur;
import fr.inti.galaxy.repositories.services.FilesStorageService;
import fr.inti.galaxy.repositories.services.UtilisateurService;
import fr.inti.galaxy.repositories.services.impl.FormationServiceImpl;
import fr.inti.galaxy.repositories.services.impl.UtilisateurServiceImpl;
import jakarta.annotation.Resource;

@SpringBootApplication
public class GalaxyApplication {
	
    @Bean
    CommandLineRunner commandLineRunnerUserDetails(UtilisateurService accountService) {
    	return args -> {
    		accountService.addnewRole("USER");
    		accountService.addnewRole("ADMIN");
    		accountService.addNewUser("user1", "1234", "user1@gmail.com", "1234");
    		accountService.addNewUser("user2", "1234", "user2@gmail.com", "1234");
    		accountService.addNewUser("admin", "1234", "admin@gmail.com", "1234");
    		
    		accountService.addRoleToUser("user1", "USER");
    		accountService.addRoleToUser("user2", "USER");
    		accountService.addRoleToUser("admin", "USER");
    		accountService.addRoleToUser("admin", "ADMIN");
    	};
    }
	
	  @Resource
	  FilesStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(GalaxyApplication.class, args);
	}
	@Bean
    CommandLineRunner commandLineRunner( UtilisateurServiceImpl userService
    		, FormationServiceImpl formationService,PasswordEncoder passwordEncoder, UtilisateurService accountService){
        return args -> {
//          storageService.deleteAll();
            storageService.init();
        	ModuleFormation moduleFormation1 = new ModuleFormation();
        	moduleFormation1.setModuleName("JavaScript");
        	ModuleFormation moduleFormation5 = new ModuleFormation();
        	moduleFormation5.setModuleName("HTML");
        	ModuleFormation moduleFormation2 = new ModuleFormation();
        	moduleFormation2.setModuleName("Java");
        	ModuleFormation moduleFormation3 = new ModuleFormation();
        	moduleFormation3.setModuleName("JEE");
        	ModuleFormation moduleFormation4 = new ModuleFormation();
        	moduleFormation4.setModuleName("Spring");
        	Formation formation1 = new Formation();
        	formation1.setFormationName("FormationJava");
        	List<ModuleFormation> moduleToAdd = new ArrayList<>();
        	moduleToAdd.add(moduleFormation1);
        	moduleToAdd.add(moduleFormation2);
        	moduleToAdd.add(moduleFormation3);
        	moduleToAdd.add(moduleFormation4);
        	moduleToAdd.add(moduleFormation5);
        	formation1.setModules(moduleToAdd);
        	formationService.save(formation1);
        	Utilisateur user1 = new Utilisateur();
        	user1.setActive(true);
        	String[] auth = new String[]{"ADMIN"};
        	user1.setAuthorities(auth);
        	user1.setEmail("dare@dare.com");
        	user1.setFirstName("dare");
        	user1.setJoinDate(new Date());
        	user1.setLastLoginDate(new Date());
        	user1.setLastLoginDateDisplay(new Date());
        	user1.setLastName("Deville");
        	user1.setNotLocked(true);
        	user1.setPassword(passwordEncoder.encode("password"));
        	user1.setProfileImageUrl("url/images");
        	user1.setRole("Admin");
        	user1.setUserId(UUID.randomUUID().toString());
        	user1.setUsername("User");
        	userService.addNewUtilisateur(user1);
    		accountService.addRoleToUser("User", "USER");
    		accountService.addRoleToUser("User", "ADMIN");
//           Stream.of("Hassan","Imane","Mohamed").forEach(name->{
//               CustomerDTO customer=new CustomerDTO();
//               customer.setName(name);
//               customer.setEmail(name+"@gmail.com");
//               bankAccountService.saveCustomer(customer);
//           });
//           bankAccountService.listCustomers().forEach(customer->{
//               try {
//                   bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
//                   bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());
//
//               } catch (CustomerNotFoundException e) {
//                   e.printStackTrace();
//               }
//           });
//            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
//            for (BankAccountDTO bankAccount:bankAccounts){
//                for (int i = 0; i <10 ; i++) {
//                    String accountId;
//                    if(bankAccount instanceof SavingBankAccountDTO){
//                        accountId=((SavingBankAccountDTO) bankAccount).getId();
//                    } else{
//                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
//                    }
//                    bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
//                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
//                }
//            }
        };
    }
 
   

    
}
