package controller;

public class BeerNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public BeerNotFoundException(Long id) {
	}

	public BeerNotFoundException(String name) {
	}
}
