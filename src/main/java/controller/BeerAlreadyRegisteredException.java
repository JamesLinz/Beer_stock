package controller;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BeerAlreadyRegisteredException extends Exception {
	private static final long serialVersionUID = 1L;

	public BeerAlreadyRegisteredException(String beerName) {
		super(String.format("Beer with name is already registered in the system.", beerName));
	}
}
