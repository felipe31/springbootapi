package com.example.springbootapi;

import com.example.springbootapi.model.Movie;
import com.example.springbootapi.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = SpringBootAPIApplication.class)
@AutoConfigureMockMvc
class SpringBootAPIApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private MovieRepository movieRepository;

	@BeforeEach
	public void setup() {
		movieRepository.deleteAll();
		movieRepository.save(new Movie(1990, "Title 1", "Studio 1", "Not consecutive Producer 1", true));
		movieRepository.save(new Movie(1990, "Title 2", "Studio 1", "Not consecutive Producer 1", false));
		movieRepository.save(new Movie(1990, "Title 3", "Studio 1", "Not consecutive Producer 1", true));

		movieRepository.save(new Movie(1990, "Title 4", "Studio 2", "Not consecutive Producer 2", true));
		movieRepository.save(new Movie(1991, "Title 5", "Studio 2", "Not consecutive Producer 2", false));
		movieRepository.save(new Movie(2011, "Title 6", "Studio 2", "Not consecutive Producer 2", true));

		movieRepository.save(new Movie(2001, "Title 7", "Studio 3", "Consecutive Producer 1", true));
		movieRepository.save(new Movie(2022, "Title 8", "Studio 3", "Consecutive Producer 1", true));

		movieRepository.save(new Movie(2002, "Title 9", "Studio 4", "Consecutive Producer 2", true));
		movieRepository.save(new Movie(2002, "Title 10", "Studio 4", "Consecutive Producer 2", true));

		movieRepository.save(new Movie(2001, "Title 11", "Studio 3", "Consecutive Producer 3", true));
		movieRepository.save(new Movie(2022, "Title 12", "Studio 3", "Consecutive Producer 3", true));
	}


	@Test
	public void testGetAllMovies() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/consecutive-winners"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.min").isArray())
				.andExpect(jsonPath("$.min[0].interval").value(0))
				.andExpect(jsonPath("$.min[0].producer").value("Consecutive Producer 2"))
				.andExpect(jsonPath("$.min[0].previousWin").value(2002))
				.andExpect(jsonPath("$.min[0].followingWin").value(2002))
				.andExpect(jsonPath("$.min[1]").doesNotExist())
				.andExpect(jsonPath("$.max").isArray())
				.andExpect(jsonPath("$.max[0].interval").value(21))
				.andExpect(jsonPath("$.max[0].producer").value("Consecutive Producer 1"))
				.andExpect(jsonPath("$.max[0].previousWin").value(2001))
				.andExpect(jsonPath("$.max[0].followingWin").value(2022))
				.andExpect(jsonPath("$.max[1].interval").value(21))
				.andExpect(jsonPath("$.max[1].producer").value("Consecutive Producer 3"))
				.andExpect(jsonPath("$.max[1].previousWin").value(2001))
				.andExpect(jsonPath("$.max[1].followingWin").value(2022))
				.andExpect(jsonPath("$.max[2]").doesNotExist());
	}

	// Add more test methods for other controller endpoints and behaviors

}
