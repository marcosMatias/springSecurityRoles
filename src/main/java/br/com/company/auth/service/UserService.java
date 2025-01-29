package br.com.company.auth.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.company.auth.model.UserModel;
import br.com.company.auth.model.dto.ResponseDto;
import br.com.company.auth.model.dto.UserDto;
import br.com.company.auth.model.dto.UserLoginDto;
import br.com.company.auth.model.dto.UserUpdatePasswordDto;
import br.com.company.auth.model.dto.UserUpdateStatusDto;
import br.com.company.auth.model.dto.UserViewDto;
import br.com.company.auth.repository.UserRepository;
import br.com.company.exception.BadRequestException;
import br.com.company.exception.ResourceNotFoundException;
import br.com.company.service.util.UtilService;



@Service
public class UserService implements UserDetailsService{
	
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	private final UtilService utilService;
	
	public UserService(UserRepository userRepository, 
			           ModelMapper modelMapper, 
			           PasswordEncoder passwordEncoder,
			           UtilService utilService) {
		
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
		this.utilService = utilService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepository.findByLoginIgnoreCase(username);
	}

	@Transactional
	public ResponseDto createUser(UserDto userDto) {
		
		userDto.setActive("S");
		userDto.setLogin(userDto.getEmail());
		userDto.setPassword(encryptPassword(userDto.getPassword()));
		
		userRepository.save(toUserDtoToModel(userDto));
				
		
		return new ResponseDto(200,utilService.retornarLocalDateTimeNowString(),"Usuario "+userDto.getLogin()+" cadastrado com sucesso","createUser");
	}
	
	
	@Transactional
	public ResponseDto updatePassword(UserUpdatePasswordDto updatePassword) {

		UserModel userModel =  recuperarUsuario(updatePassword.getLogin());

		userModel.setPassword(encryptPassword(updatePassword.getPassword()));

		userRepository.save(userModel);

		return new ResponseDto(200,utilService.retornarLocalDateTimeNowString(),"Senha atualizada com sucesso!","updatePassword" );
	}
	
	
	@Transactional
	public ResponseDto updateStatus(UserUpdateStatusDto userUpdateStatusDto)  {

		UserModel userModel =  recuperarUsuario(userUpdateStatusDto.getLogin());
		
			
		if ((userUpdateStatusDto.getActive().toUpperCase().equals("N"))	|| userUpdateStatusDto.getActive().toUpperCase().equals("S")) {

			userModel.setActive(userUpdateStatusDto.getActive().toUpperCase());

			userRepository.save(userModel);

			return new ResponseDto(200, utilService.retornarLocalDateTimeNowString(), "Status Atualizado!","updateStatus");
		}
		
		throw new BadRequestException("O campo 'active' deve ser preenchido com 'S' ou 'N'. Por favor, verifique!");
	}
	
	
	@Transactional(readOnly = true)
	public List<UserViewDto> getAllUsers(){
		
		List<UserViewDto>  users = toUserCollectionModelToDto(userRepository.findAll());
		
		return Optional.of(users)
				       .filter(u -> !u.isEmpty())
				       .orElseThrow(()-> new ResourceNotFoundException("Nenhum usuario encontrado!"));
		
	}
	
	
	
	@Transactional(readOnly = true)
	public UserViewDto getUser(UserLoginDto userLoginDto) {

		return toUserViewDto(recuperarUsuario(userLoginDto.getLogin()));
	}
	
	
	
	
	private String encryptPassword(String password) {
		
		
		return passwordEncoder.encode(password);
	}
	
	@Transactional(readOnly = true)
	public UserModel recuperarUsuario(String login) {
		
		return userRepository.findByEmailIgnoreCase(login)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário "+login+" não foi encontrado. Por favor, verifique o endereço de e-mail e tente novamente."));
	}
	

	private UserViewDto toUserViewDto(UserModel userModel) {
		return modelMapper.map(userModel, UserViewDto.class);
	}
	
	private List<UserViewDto> toUserCollectionModelToDto(List<UserModel> users){
		
		return users.stream().map(this::toUserViewDto).collect(Collectors.toList());
	}
	
	private UserModel toUserDtoToModel(UserDto userDto) {
		return modelMapper.map(userDto, UserModel.class);
	}
	
}
