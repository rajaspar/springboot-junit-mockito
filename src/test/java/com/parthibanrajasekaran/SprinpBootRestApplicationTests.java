package com.parthibanrajasekaran;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parthibanrajasekaran.controller.AddBookResponse;
import com.parthibanrajasekaran.controller.Library;
import com.parthibanrajasekaran.controller.LibraryController;
import com.parthibanrajasekaran.repository.LibraryRepository;
import com.parthibanrajasekaran.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class SprinpBootRestApplicationTests {

	@MockBean
	LibraryService libraryService;

	@Autowired
	LibraryController libraryController;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	LibraryRepository libraryRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void verifyBuildId(){
		LibraryService service = new LibraryService();
		final String actualId = service.createId("Zalenium",2022);
		assertEquals("Zalenium2022",actualId, "checkBuildId validated successfully");
	}

	@Test
	public void verifyAddBookIfBookDoesNotExistsWithoutServer(){
		Library library = buildLibrary();

		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = null;
		try {
			requestBody = objectMapper.writeValueAsString(buildLibrary());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		when(libraryService.createId(library.getIsbn(),library.getAisle())).thenReturn(library.getId());

		when(libraryService.verifyIfBookWithIdExists(library.getId())).thenReturn(false);

		// mocking repository.save and just return the required object
		when(libraryRepository.save(any())).thenReturn(library);

		try {
			assert requestBody != null;
			this.mockMvc.perform(post("/addBook")
							.contentType(MediaType.APPLICATION_JSON)
							.content(requestBody))
					.andDo(print())
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.id").value(library.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void verifyAddBookIfBookDoesNotExists(){
		Library library = buildLibrary();

		when(libraryService.createId(library.getIsbn(),library.getAisle())).thenReturn(library.getId());

		when(libraryService.verifyIfBookWithIdExists(library.getId())).thenReturn(false);

		ResponseEntity<AddBookResponse> response = libraryController.addBook(buildLibrary());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		AddBookResponse addBookResponse = response.getBody();
		assertEquals(library.getId(), addBookResponse.getId());
		assertEquals("Book has been successfully added", addBookResponse.getMsg());
	}

	@Test
	public void verifyAddBookIfBookExists(){
		Library library = buildLibrary();

		when(libraryService.createId(library.getIsbn(),library.getAisle())).thenReturn(library.getId());

		when(libraryService.verifyIfBookWithIdExists(library.getId())).thenReturn(true);

		ResponseEntity<AddBookResponse> response = libraryController.addBook(buildLibrary());
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

		AddBookResponse addBookResponse = response.getBody();
		assertEquals(library.getId(), addBookResponse.getId());
		assertEquals("Book already exists", addBookResponse.getMsg());
	}

	private Library buildLibrary(){
		Library library = new Library();
		library.setBook_name("SpringBoot");
		library.setAuthor("Adhvik");
		library.setAisle(2022);
		library.setIsbn("SAFe");
		library.setId("SAFe2022");
		return library;
	}

}
