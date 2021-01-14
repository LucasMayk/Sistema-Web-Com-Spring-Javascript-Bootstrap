package com.algaworks.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.util.UriComponentsBuilder;

//Envelopando a page de filtros - page.
public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		
		// Validação de espaço no input.
		String httpUrl = httpServletRequest.getRequestURL().append(
				httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20").replaceAll("excluido", "");
		this.uriBuilder = UriComponentsBuilder.fromHttpUrl(httpUrl);
	}
	
	// Listando os dados na página com filtros - page.
	public List<T> getConteudo() {
		return page.getContent();
	}
	
	// Verificar se o filtro está vazia - page.
	public boolean isVazia() {
		return page.getContent().isEmpty();
	}
	
	// Filtro da página atual - page.
	public int getAtual() {
		return page.getNumber();
	}
	
	/* Enquanto estiver no primeiro filtro a 
  	função de voltar filtro estar bloqueada - page. */
	public boolean isPrimeiro() {
		return page.isFirst();
	}
	
	/* Enquanto estiver no ultimo filtro a 
  	função de avançar filtro estar bloqueada - page. */
	public boolean isUltima() {
		return page.isLast();
	}
	
	// Total de páginas para exibir os dados - page.
	public int getTotal() {
		return page.getTotalPages();
	}
	
	// Contruir URL para o filtro - page.
	public String urlParaPagina(int pagina) {
		return uriBuilder.replaceQueryParam("page", pagina).build(true).encode().toUriString();
	}
	
	// Ordenando os dados com URL - sort.
	public String urlOrdenada(String propriedade) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(uriBuilder.build(true).encode().toUriString());
		
		String  valorSort = String.format("%s,%s", propriedade, inverterDirecao(propriedade));
		
		return uriBuilderOrder.replaceQueryParam("sort", valorSort).build(true).encode().toUriString();
	}
	
	// Ordenando - filtro.
	public String inverterDirecao(String propriedade) {
		String direcao = "asc";
		
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		if (order != null) {
			direcao = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc";
		}
		
		return direcao;
	}
	
	// Invertendo a seta de ordenação.
	public boolean descendente(String propriedade) {
		return inverterDirecao(propriedade).equals("desc");
	}
	
	// Iniciar a ordenação dem seta.
	public boolean ordenada(String propriedade) {
		Order order = page.getSort() != null ? page.getSort().getOrderFor(propriedade) : null;
		
		if (order == null) {
			return false;
		}
		
		return page.getSort().getOrderFor(propriedade) != null ? true : false;
	}
}
