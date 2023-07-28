package com.github.hugovallada;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://viacep.com.br/ws/14010090/json/"))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		if (response.statusCode() != 200) {
			System.out.println("Algo aconteceu");
			return;
		}
		System.out.println(response.statusCode());
		System.out.println(response.body());

		var gson = new Gson();
		var cep = gson.fromJson(response.body(), Cep.class);
		System.out.println(cep);
		System.out.println(cep.bairro());

		var gsonCamelCase = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
				.create();

		var cepWithCamelCase = gsonCamelCase.fromJson(response.body(), Cep.class);
		System.out.println(cepWithCamelCase);
	}
}
