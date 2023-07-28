package com.github.hugovallada;

import com.google.gson.annotations.SerializedName;

public record Cep(
		String cep,
		@SerializedName("logradouro")
		String rua,
		String Complemento,
		String bairro,
		String localidade,
		String uf,
		String ibge,
		String gia,
		String ddd,

		String siafi
) {
}
