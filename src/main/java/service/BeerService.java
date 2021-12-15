package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controller.BeerAlreadyRegisteredException;
import controller.BeerNotFoundException;
import dto.BeerDTO;
import entity.Beer;
import lombok.AllArgsConstructor;
import repository.BeerRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {
	
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper = BeerMapper.INSTANCE;
	
	public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
		verifyIfIsAlreadyRegistered(beerDTO.getName());
		Beer beer = beerMapper.toModel(beerDTO);
		Beer savedBeer = beerRepository.save(beer);
		return beerMapper.toDTO(savedBeer);
	}
	
	private void verifyIfIsAlreadyRegistered(Object name) {
	}

	public BeerDTO findByName(String name) throws BeerNotFoundException {
		Beer foundBeer = beerRepository.findByName(name)
				.orElseThrow(() -> new BeerNotFoundException(name));
		return beerMapper.toDTO(foundBeer);
	}
	
	public List<BeerDTO> listAll() {
		return (List<BeerDTO>) beerRepository.findAll()
				.stream()
				.map(beerMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public void deleteById(Long id) throws BeerNotFoundException {
		verifyIfExists(id);
		beerRepository.deleteById(id);
	}
	
	private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
		Optional<Beer> optSavedBeer = beerRepository.findByName(name);
		if (optSavedBeer.isPresent()) {
			throw new BeerAlreadyRegisteredException(name);
		}
	}
	
	private Beer verifyIfExists(Long id) throws BeerNotFoundException {
		return beerRepository.findById(id)
				.orElseThrow(() -> new BeerNotFoundException(id));
	}

	public Object increment(Object id, int quantityToIncrement) {
		return null;
	}
}
