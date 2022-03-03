package com.example;

import com.example.model.CustomMessage;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class MessageHubApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Test
	public void testGetIdentitySuccess() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:"+randomServerPort+"/identity";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.hasBody());
	}

	@Test
	public void testGetUserListSuccess() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:"+randomServerPort+"/users";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void testReceiveMessage() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:"+randomServerPort+"/receive";
		URI uri = new URI(baseUrl);

		CustomMessage message = new CustomMessage("1644386285274000", "How are you");

		ResponseEntity<String> result = restTemplate.postForEntity(uri, message, String.class);

		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals("Message delivered successfully", result.getBody());
	}
}
