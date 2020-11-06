package com.user.github.controller;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@RestController
@Api(value = "Github")
@RequestMapping(path = "/")
@CrossOrigin(origins = "*")
public class GitHubController {
	
    private static final String baseUrl = "https://api.github.com/users/"; 

	@PostConstruct
	public void init() {
	}

	private static final Logger log = LoggerFactory.getLogger(GitHubController.class);
	
	@GetMapping(path = "list", produces = "application/json")
	private String list(@RequestParam(value = "username", defaultValue = "") String username) throws IOException, InterruptedException, JSONException {
        var request = HttpRequest.newBuilder().uri(java.net.URI.create(baseUrl + username + "/repos"))
                .GET()
                .build();

        var response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());       
        
        return response.body();         
        
    }
	
}