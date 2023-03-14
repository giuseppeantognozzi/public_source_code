package contractsinfo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

 

@RestController
class AddressController {

	private final AddressRepository repository;

	AddressController(AddressRepository repository) {
		this.repository = repository;
	}

 
	@GetMapping("/addresses")
	CollectionModel<EntityModel<Address>> all() {

		List<EntityModel<Address>> addresses = repository.findAll().stream()
				.map(address -> EntityModel.of(address,
						linkTo(methodOn(AddressController.class).one(address.getId())).withSelfRel(),
						linkTo(methodOn(AddressController.class).all()).withRel("addresses")))
				.collect(Collectors.toList());

		return CollectionModel.of(addresses, linkTo(methodOn(AddressController.class).all()).withSelfRel());
	}
 

	@PostMapping("/addresses")
	Address newAddress(@RequestBody Address newAddress) {
		return repository.save(newAddress);
	}

 
	@GetMapping("/addresses/{id}")
	EntityModel<Address> one(@PathVariable Long id) {

		Address address = repository.findById(id) //
				.orElseThrow(() -> new AddressNotFoundException(id));

		return EntityModel.of(address, //
				linkTo(methodOn(AddressController.class).one(id)).withSelfRel(),
				linkTo(methodOn(AddressController.class).all()).withRel("addresses"));
	}
 

	@PutMapping("/addresses/{id}")
	Address replaceAddress(@RequestBody Address newAddress, @PathVariable Long id) {

		return repository.findById(id) //
				.map(address -> {
					address.setStreet(newAddress.getStreet());
					address.setCity(newAddress.getCity());
					address.setZip(newAddress.getZip());
					address.setState(newAddress.getState());
					return repository.save(address);
				}) //
				.orElseGet(() -> {
					newAddress.setId(id);
					return repository.save(newAddress);
				});
	}

	// return XML
	@GetMapping(value = "/getAddressInfoXml/{id}", 
				produces = { "application/xml", "text/xml" }, 
				consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public Address  getObject(@PathVariable Long id) {

		Address address = repository.findById(id) 
				.orElseThrow(() -> new AddressNotFoundException(id));
		
		return address;

	}
	
	
	// return XML
	@GetMapping(value = "/getAllAddressesInfoXml", 
			  produces = { "application/xml", "text/xml" }, 
			  consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public List<Address> getAllObjects() {
		
		return repository.findAll();

	}
	

	@DeleteMapping("/addresses/{id}")
	void deleteAddress(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
