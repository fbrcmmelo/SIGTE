package com.project.sigte2.util;

import java.util.List;

public class PaginacaoUtil<T> {

	// essa variavel armazena a quantidade de linhas que vamos ter nas páginas.
	private int tamanho;
	/*
	 * essa variavel vai armazenar o numero da pagina atual selecionada pelo
	 * cliente, esse numero é enviado ao servidor por requisição do cliente , assim
	 * retornaremos a resposta conforme o numero da página, e saberemos qual pagina
	 * o cliente esta
	 */
	private int pagina;
	// essa variavel armazena o total de paginas que nos temos no sistema de
	// paginação.
	// ex:se tivermos 15 registros no bd, e queremos mostrar 5 por pagina, o meu
	// total de paginas sera 3.
	private long totalPaginas;
	// essa variavel vai armazenar o resultado de uma consulta por paginas, se uma
	// pagina tiver 5 registros
	// ela retorna 5 registros. depois neccista de um for each para mostrar ao
	// cliente na interface
	private List<T> registros;

	public PaginacaoUtil(int tamanho, int pagina, long totalPaginas, List<T> registros) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalPaginas = totalPaginas;
		this.registros = registros;
	}

	public int getTamanho() {
		return tamanho;
	}

	public int getPagina() {
		return pagina;
	}

	public long getTotalPaginas() {
		return totalPaginas;
	}

	public List<T> getRegistros() {
		return registros;
	}

}
