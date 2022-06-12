package cl.aiep.java.cms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.aiep.java.cms.security.UserSeguridad;
import cl.aiep.java.cms.model.Administrador;
import cl.aiep.java.cms.model.Estudiante;
import cl.aiep.java.cms.repository.AdministradorRepository;
import cl.aiep.java.cms.repository.EstudianteRepository;

@Service
public class ServicioDetallesUsuario implements UserDetailsService 
{

	@Autowired
	EstudianteRepository estudianteRepository;
	
	@Autowired
	AdministradorRepository administradorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Optional<Estudiante> usuarioOpt = estudianteRepository.findByRut(username);
		if(usuarioOpt.isPresent()) 
		{
			return new UserSeguridad(usuarioOpt.get(), null);
		}else {
			Optional<Administrador> adminOpt = administradorRepository.findByUsername(username);
			if(adminOpt.isPresent()) 
			{
				return new UserSeguridad(null, adminOpt.get());
			}
		}
		throw new UsernameNotFoundException("Usuario no encontrado");
	}
	
	
}
