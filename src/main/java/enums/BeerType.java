package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BeerType {
	
	LAGER("Lager"),
	MALZBIER("Malzbier"),
	WITBIER("Witbier"),
	WEISS("Weiss"),
	ALE("Ale"),
	IPA("IPA"),
	STOUT("Stout");
	
	BeerType(String string) {
		this.description = "";
	}

	private final String description;
}
