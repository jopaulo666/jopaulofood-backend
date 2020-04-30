package br.com.jopaulo.jopaulofood.application.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.jopaulo.jopaulofood.util.IoUtils;

@Service
public class ImageService {
	
	@Value("${jopaulofood.files.logotipo}")
	private String logotipoDir;
	
	public void uploadLogotipo(MultipartFile multipartFile, String fileName) {
		try {
			IoUtils.copy(multipartFile.getInputStream(), fileName, logotipoDir);
		} catch (IOException e) {
			throw new ApplicationServiceException(e);
		}
	}
	
}
