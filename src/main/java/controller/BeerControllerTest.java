package controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import dto.BeerDTO;
import dto.BeerDTOBuilder;
import service.BeerService;
import service.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class BeerControllerTest {
	
	private static final String BEER_API_URL_PATH = "/api/v1/beers";
	private static final long VALID_BEER_ID = 1l;
	private static final long INVALID_BEER_ID = 2l;
	private static final String BEER_API_SUBPATH_INCREMENT_URL = "/increment";
	private static final String BEER_API_SUBPATH_DECREMENT_URL = "/decrement";
	
	private MockMvc mockMvc;
	
	@Mock
	private BeerService beerService;
	
	@InjectMocks
	private BeerController beerController;
	
	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(beerController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((s, locale) -> new MappingJackson2JsonView())
				.build();
	}
	
	@Test
	void whenPOSTisCalledThenBeerIsCreated() {
		// given
		BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
		
		// when
		Mockito.when(beerService.createBeer(beerDTO)).thenReturn(beerDTO);
		
		// then
		mockMvc.perform(post(BEER_API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.contentType(asJsonString(beerDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", is(beerDTO.getName())))
				.andExpect(jsonPath("$.brand", is(beerDTO.getBrand())))
				.andExpect(jsonPath("$.type", is(beerDTO.getType().toString())));
		}
	
	private Object post(String beerApiUrlPath) {
		return null;
	}

	private Object jsonPath(String string, Matcher<Object> matcher) {
		return null;
	}

	private Object status() {
		return null;
	}

	private Object asJsonString(BeerDTO beerDTO) {
				return null;
	}

	@Test
	void whenPOSTisCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
		// given
		   BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
		   beerDTO.setBrand(null);
			
			// then
			mockMvc.perform(post(BEER_API_URL_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(beerDTO)))
				.andExpect(status().isBadRequest());
	}
		
	@Test
	void whenGETisCalledWithValidNameThenOkStatusIsReturned() throws Exception {
		// given
		BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
			
		// when
		when(beerService.findByName(beerDTO.getName())).thenReturn(beerDTO);
		
		// then
		mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH + "/" + beerDTO.getName())
			.contentType(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOK())
		    .andExpect(jsonPath("$.name", is(beerDTO.getName())))
			.andExpect(jsonPath("$.brand", is(beerDTO.getBrand())))
			.andExpect(jsonPath("$.type", is(beerDTO.getType().toString())));
	 }
	
	
	@Test
	void whenGETisCalledWithoutRegisterdNameThenNotFoundStatusIsReturned() throws Exception {
		// given
		BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
			
		// when
		when(beerService.findByName((String) beerDTO.getName())).thenReturn(beerDTO);
		
		// then
		mockMvc.perform(MockMvcRequestBuilders.get(BEER_API_URL_PATH + "/" + beerDTO.getName())
			.contentType(MediaType.APPLICATION_JSON))
		    .andExpect(status().isNotFound());
	 }
			
			
		// then
//		mockMvc.perform(post(BEER_API_URL_PATH)
//				.contentType(MediaType.APPLICATION_JSON)
//				.contentType(asJsonString(beerDTO)))
//				.andExpect(status().isCreated())
//				.andExpect(jsonPath("$.name", is(beerDTO.getName())))
//				.andExpect(jsonPath("$.brand", is(beerDTO.getBrand())))
//				.andExpect(jsonPath("$.type", is(beerDTO.getType().toStyring())));
		
 }

