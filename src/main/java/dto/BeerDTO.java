package dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hamcrest.Matcher;

import enums.BeerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {
	
	public BeerDTO(Long id2, String name2, String brand2, int max2, int quantity2, BeerType type2) {
		// TODO Auto-generated constructor stub
	}

	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 200)
	private String brand;
	
	@NotNull
	@Max(500)
	private String max;
	
	@NotNull
	@Max(100)
	private String quantity;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private BeerType type;

	public Object getId() {
		return null;
	}

	public Object getName() {
		return null;
	}

	public Object getQuantity() {
		return null;
	}

	public Matcher getBrand() {
		return null;
	}

	public Object getType() {
		return null;
	}

	public void setBrand(Object object) {
	}
}
