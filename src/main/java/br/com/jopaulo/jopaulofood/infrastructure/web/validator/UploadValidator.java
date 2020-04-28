package br.com.jopaulo.jopaulofood.infrastructure.web.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import br.com.jopaulo.jopaulofood.util.FileType;

public class UploadValidator implements ConstraintValidator<UploadContraint, MultipartFile>{
	
	private List<FileType> acceptedFileTypes;
	
	

	@Override
	public void initialize(UploadContraint constraintAnnotation) {
		acceptedFileTypes = Arrays.asList(constraintAnnotation.acceptedTypes());
	}

	@Override
	public boolean isValid(MultipartFile multpartFile, ConstraintValidatorContext context) {
		if (multpartFile == null) {
			return true;
		}
		
		for (FileType fileType : acceptedFileTypes) {
			if (fileType.sameOf(multpartFile.getContentType())) {
				return true;
			}
		}
		return false;
	}	
}
