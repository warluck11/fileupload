package com.itvedant.form.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itvedant.form.FileStorageProperties;
import com.itvedant.form.dto.AddUserDto;
import com.itvedant.form.entity.User;
import com.itvedant.form.exceptions.StorageException;
import com.itvedant.form.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private final Path rootLocation;
	
	
	
	
	public UserService(FileStorageProperties properties) {
		
		this.rootLocation = Paths.get(properties.getUploadDir());
		
		try {
			
			Files.createDirectories(rootLocation);
		}catch (IOException e) {
			throw new StorageException("Could not initialize directory");
		}
	}




	public String create(AddUserDto addUserDto, MultipartFile file) {
		
		User user = new User();
		
		user.setName(addUserDto.getName());
		user.setEmail(addUserDto.getEmail());
		user.setAge(addUserDto.getAge());
		
		Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()));
		try(InputStream inputStream = file.getInputStream()){
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new StorageException("Could not save file");
		}
		
		this.userRepository.save(user);
		
		
		return "User Created!!!";
		
	}
	
	
	

}
