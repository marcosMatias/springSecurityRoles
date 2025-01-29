package br.com.company.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.company.auth.model.RoleModel;
import br.com.company.auth.model.UserModel;
import br.com.company.auth.model.UserPermissionModel;
import br.com.company.auth.model.dto.ResponseDto;
import br.com.company.auth.model.dto.UserUpdatePermissionDto;
import br.com.company.auth.repository.UserPermissionRepository;
import br.com.company.exception.BusinessException;
import br.com.company.service.util.UtilService;



@Service
public class UserPermissionService {
	
	private final UserService userService;
	private final RoleService roleService;
	private final UserPermissionRepository userPermissionRepository;
	private final UtilService utilService;

	public UserPermissionService(UserService userService, RoleService roleService,UserPermissionRepository userPermissionRepository,UtilService utilService) {
		this.userService = userService;
		this.roleService = roleService;
		this.userPermissionRepository = userPermissionRepository;
		this.utilService = utilService;

	}
	
	@Transactional
	public ResponseDto insertPermission(UserUpdatePermissionDto permission) {

		UserModel user = userService.recuperarUsuario(permission.getLogin());

		RoleModel role = roleService.getRoleByName(permission.getName());

		if (userPermissionRepository.findByUserAndRole(user, role).isPresent()) {

			throw new BusinessException("A permissão " + permission.getName() + " ja esta cadastrada para o usuario "
					+ permission.getLogin());
		}
		
		UserPermissionModel userPermissionModel = new UserPermissionModel();
		
		userPermissionModel.setUser(user);
		userPermissionModel.setRole(role);
		
		userPermissionRepository.save(userPermissionModel);

		return new ResponseDto(200, utilService.retornarLocalDateTimeNowString(), "Permissão concedida ao usuário "+user.getLogin(),"PermissionInsert");
		
		
		
	}

}
