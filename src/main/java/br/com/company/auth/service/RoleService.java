package br.com.company.auth.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.company.auth.model.RoleModel;
import br.com.company.auth.model.dto.ResponseDto;
import br.com.company.auth.model.dto.RoleDto;
import br.com.company.auth.repository.RoleRepository;
import br.com.company.exception.ResourceNotFoundException;
import br.com.company.service.util.UtilService;

@Service
public class RoleService {

	private final RoleRepository roleRepository;
	private final ModelMapper modelMapper;
	private final UtilService utilService;

	public RoleService(RoleRepository roleRepository,
					  ModelMapper modelMapper,
					  UtilService utilService) {
		
		this.roleRepository = roleRepository;
		this.modelMapper = modelMapper;
		this.utilService = utilService;
	}
	
	@Transactional
	public ResponseDto createRole(RoleDto role) {
		
		roleRepository.save(toRoleModel(role));
		
		return new ResponseDto(201,utilService.retornarLocalDateTimeNowString(),"Permissao criada com sucesso!","create role");
	}
	
	
	
	
	@Transactional(readOnly = true)
	public List<RoleDto> getAllRoles(){
		
		 List<RoleDto> roles = toCollectionRoleModelToDto(roleRepository.findAll());
		  
		  return Optional.of(roles)
				  		 .filter(r -> !r.isEmpty())
				  		 .orElseThrow(()->  new ResourceNotFoundException("Nenhuma permissao encontrada!"));
	}
	
	
	@Transactional(readOnly = true)
	public RoleDto getRoleDtoByName(String roleName) {
		
		return toRoleDto(getRoleByName(roleName));
		
		
	}
	
	@Transactional(readOnly = true)
	public RoleModel getRoleByName(String role) {
		
		return roleRepository.findByNameIgnoreCase(role).orElseThrow(()-> new ResourceNotFoundException("Permissao "+role+" nao localizada!"));
	} 
	
	
	private RoleModel toRoleModel(RoleDto roleDto) {

		return modelMapper.map(roleDto, RoleModel.class);
	}

	private RoleDto toRoleDto(RoleModel roleModel) {

		return modelMapper.map(roleModel, RoleDto.class);
	}

	private List<RoleDto> toCollectionRoleModelToDto(List<RoleModel> roles) {

		return roles.stream().map(this::toRoleDto).collect(Collectors.toList());
	}

}
