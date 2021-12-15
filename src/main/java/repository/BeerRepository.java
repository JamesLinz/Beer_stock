package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long> {
	
	Optional<Beer> findByName(Object object);
}
