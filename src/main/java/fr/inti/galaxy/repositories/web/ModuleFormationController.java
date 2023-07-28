package fr.inti.galaxy.repositories.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.inti.galaxy.dtos.ModuleFormationDTO;
import fr.inti.galaxy.entities.ModuleFormation;
import fr.inti.galaxy.mappers.MapperImpl;
import fr.inti.galaxy.repositories.ModuleFormationRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class ModuleFormationController {
	
	@Autowired
	ModuleFormationRepository mfr;
	
	@GetMapping("/modules")
	public List<ModuleFormationDTO> modules() {
		return mfr.findAll().stream().map(MapperImpl::fromModuleFormation).collect(Collectors.toList());
	}

	@GetMapping("/modules/{id}")
	public ModuleFormationDTO getModule(@PathVariable(name = "id") int id) {
		return MapperImpl.fromModuleFormation(mfr.getReferenceById(id));
	}
	
	@PostMapping("/addModule")
    public ModuleFormation addNewModule(@RequestBody ModuleFormation moduleFormation) {
        return  mfr.save(moduleFormation);
    }
	
	@DeleteMapping("/deleteModule/{id}")
    public void deleteModule(@PathVariable("id") int id) {
        mfr.delete(mfr.getReferenceById(id));
    }
	
	@PutMapping("/updateModule")
    public void updateModule(@RequestBody ModuleFormation moduleFormation) {
		mfr.save(moduleFormation);
    }

}
