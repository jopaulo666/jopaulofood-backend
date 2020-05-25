package br.com.jopaulo.jopaulofood.application.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.jopaulo.jopaulofood.util.IoUtils;

@Service
public class ImageService {
	
	@Value("${jopaulofood.files.logotipo}")
	private String logotiposDir;
	
	@Value("${jopaulofood.files.categoria}")
	private String categoriasDir;
	
	@Value("${jopaulofood.files.comida}")
	private String comidasDir;
	
	public void uploadLogotipo(MultipartFile multipartFile, String fileName) {
		try {
			IoUtils.copy(multipartFile.getInputStream(), fileName, logotiposDir);
		} catch (IOException e) {
			throw new ApplicationServiceException(e);
		}
	}
	
	public byte[] getBytes(String type, String imgName) {
		try {
			String dir;
			
			if ("comida".equals(type)) {
				dir = comidasDir;
			} else if ("logotipo".equals(type)) {
				dir = logotiposDir;
			} else if ("categoria".equals(type)) {
				dir = categoriasDir;
			} else {
				throw new Exception(type + "Imagem inválida");
			}
			
			return IoUtils.getBytes(Paths.get(dir, imgName));
			
		} catch (Exception e) {
			throw new ApplicationServiceException(e);
		}
	}
	
}
