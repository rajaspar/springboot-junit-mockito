package com.parthibanrajasekaran;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.parthibanrajasekaran.controller.AddBookResponse;
import com.parthibanrajasekaran.controller.Library;
import com.parthibanrajasekaran.controller.LibraryController;
import com.parthibanrajasekaran.repository.LibraryRepository;
import com.parthibanrajasekaran.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class SprinpBootRestApplicationTests {

	@MockBean
	LibraryService libraryService;

	@Autowired
	LibraryController libraryController;

	@MockBean
	LibraryRepository libraryRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void verifyBuildId(){
		final String actualId = libraryService.createId("Zalenium",2022);
		assertEquals("Zalenium2022",actualId, "checkBuildId validated successfully");
	}

	@Test
	public void verifyAddBookIfBookDoesNotExists(){
		Library library = new Library();
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
		Library library = new Library();
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
		library.setId("SpringBoot2022");
		return library;
	}

}
