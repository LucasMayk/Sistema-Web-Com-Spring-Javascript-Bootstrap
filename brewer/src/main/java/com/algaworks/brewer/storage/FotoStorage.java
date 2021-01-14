package com.algaworks.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {

	// Interface metodo para salvar a foto temporarialmente na pasta - temp.
	public String salvarTemporariamente(MultipartFile[] files);

	// Inteface metodo recuperar a foto da pasta temporaria - temp.
	public byte[] recuperarFotoTemporaria(String nome);

	// Inteface metodo salvar foto no direitório permanente - brewerfotos. 
	public void salvar(String foto);

	// Interface metodo recuperar a foto da pasta local - brewerfotos.
	public byte[] recuperar(String foto);

	// Recuperar a imagem da cerveja para a pagina de ResumoVenda.
	public byte[] recuperarThumbnail(String fotoCerveja);

	public void excluir(String foto);
	
}
