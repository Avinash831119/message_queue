package com.example;

import com.example.model.CustomMessage;
import com.example.service.MessageService;
import com.example.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.ExpectedCount.times;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class MessageClientApplicationTests {

	/*@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private MessageService messageService = new MessageService();

	@InjectMocks
	private UserService userService = new UserService();*/

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@Test
	public void publishMessageTest() throws UnsupportedEncodingException {

		mockServer = MockRestServiceServer.createServer(restTemplate);

		mockServer.expect(once(), requestTo("http://localhost:9001/receive"))
				.andRespond(withSuccess("Message delivered successfully", MediaType.APPLICATION_JSON));

		CustomMessage customMessage = new CustomMessage("1644386285274000", "How are you");

		String message = messageService.publishMessage(customMessage);

		Assert.assertEquals("Message delivered successfully", message);
	}

	@Test
	public void fetchUserListTest() throws UnsupportedEncodingException, URISyntaxException {

		mockServer = MockRestServiceServer.createServer(restTemplate);

		List<String> userList = new ArrayList<>(Arrays.asList("1644386285274000"));

		mockServer.expect(once(), requestTo("http://localhost:9001/users"))
				.andRespond(withSuccess(String.valueOf(userList), MediaType.APPLICATION_JSON));

		List<String> list = userService.fetchUserList();

		Assert.assertEquals("1644386285274000", String.valueOf(list.get(0)));
	}

	@Test
	public void fetchUserIdentityTest() throws UnsupportedEncodingException, URISyntaxException {

		mockServer = MockRestServiceServer.createServer(restTemplate);

		List<String> userList = new ArrayList<>(Arrays.asList("1644386285274000"));

		mockServer.expect(once(), requestTo("http://localhost:9001/identity"))
				.andRespond(withSuccess("1644386285274000", MediaType.APPLICATION_JSON));

		String identity = userService.fetchUserIdentity();

		Assert.assertEquals("1644386285274000", identity);
	}


}
