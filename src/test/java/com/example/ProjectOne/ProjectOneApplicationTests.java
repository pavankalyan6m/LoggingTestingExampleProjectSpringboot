package com.example.ProjectOne;

import com.example.ProjectOne.Entity.User;
import com.example.ProjectOne.Controller.UserController;
import com.example.ProjectOne.Repository.UserJpaRepository;
import com.example.ProjectOne.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
class ProjectOneApplicationTests {

	//private static final Logger logger = LoggerFactory.getLogger(ProjectOneApplicationTests.class);

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@Mock
	private UserJpaRepository userJpaRepository;


	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		// Initialize Mockito annotations and create a MockMvc instance for testing.
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	void testGetAllUsers() throws Exception {
		User user1 = new User(1, "User1", "user1@gmail.com");
		User user2 = new User(2, "User2","user2@gmail.com");

		when(userService.getAllFBUsers()).thenReturn(Arrays.asList(user1, user2));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fb-api/getAllUsers")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		//Just printing out the response
		String responseContent = result.getResponse().getContentAsString();
		System.out.println(responseContent);
	}

	@Test
	void testGetUserById() throws Exception{
		User user1 = new User(1, "User1", "user1@gmail.com");
		User user2 = new User(2, "User2","user2@gmail.com");

		int user_id = 2;

		// Mock the behavior of the UserService to return user1 when getUserById is called with userId
		when(userService.getUserById(2)).thenReturn(Optional.of(user2));



		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fb-api/getUserDetailsById/{id}",user_id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user2.getUsername()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.userEmail").value(user2.getUserEmail()))
				.andReturn();


		//Just printing out the response
		String responseContent = result.getResponse().getContentAsString();
		System.out.println(responseContent);

	}

	@Test
	void testDeleteUserById() throws Exception {
		// Create sample users for testing
		User user1 = new User(1, "user1", "user1@gmail.com");
		User user2 = new User(2, "user2", "user2@gmail.com");
		User user3 = new User(3, "user3", "user3@gmail.com");

		// Save the sample users to the database using the userJpaRepository
		userJpaRepository.save(user1);
		userJpaRepository.save(user2);
		userJpaRepository.save(user3);


		log.info("inside deleteByUserId test method");
		log.info("users inserted successfully...");

		int user_id = 2;

		// Define the expected behavior when the service's deleteUserDetailsById method is called
		doNothing().when(userService).deleteUserDetailsById(user_id);

		// Perform the DELETE request
		mockMvc.perform(MockMvcRequestBuilders.delete("/fb-api/deleteUserById/{id}", user_id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		// Verify that user2 is deleted by trying to retrieve it
		Optional<User> deletedUser = userJpaRepository.findById(user_id);
		assertFalse(deletedUser.isPresent()); // Expect the user not to be found in the database
	}

	// Similarly, you can write tests for other controller methods
}
