package cl.aiep.java.cms;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cl.aiep.java.cms.model.Administrador;
import cl.aiep.java.cms.model.Curso;
import cl.aiep.java.cms.model.Estudiante;
import cl.aiep.java.cms.repository.CursoRepository;
import cl.aiep.java.cms.service.AdministradorService;
import cl.aiep.java.cms.service.UsuarioService;

@SpringBootApplication
public class CmsSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsSecurityApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner datosIniciales(AdministradorService aService, UsuarioService uService, CursoRepository cRepo) {
		return args -> {
			if(aService.contarAdmin() == 0) {
				Administrador admin = Administrador.builder()
													.username("admin")
													.password("1234")
													.build();
				aService.crearAdmin(admin);
														
			}
			
			if(uService.contarUsuarios() == 0) {
				Estudiante estudiante = Estudiante.builder()
											.nombre1("Pedro")
											.nombre2("Pablo")
											.apellidoPaterno("Bucaretti")
											.apellidoMaterno("Colina")
											.direccion("No disponible")
											.region("No disponible")
											.telefono("912345678")
											.email("correo@dominio.com")
											.rut("12345678-9")
											.password("1234")
											.region("No disponible")
											.comuna("No disponible")
											.build()
									;
				uService.crearUsuario(estudiante);
			}
			if(cRepo.count() == 0) {
				Curso cursoJava = Curso.builder()
										.nombre("Reac Native")
										.fechaInicio(LocalDate.of(2022, 3, 3))
										.fechaFin(LocalDate.of(2023, 12, 20))
										.cupos(30)
										.descripcion("React Native, es un framework de código abierto creado por Meta Platforms, Inc.​ Se utiliza para desarrollar aplicaciones para Android, ​ Android TV, ​ iOS, macOS, ​ tvOS, Web, .")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/react.png")))
										.build();
				Curso cursoSpring = Curso.builder()
										.nombre("Python")
										.fechaInicio(LocalDate.of(2022, 2, 6))
										.fechaFin(LocalDate.of(2023, 12, 25))
										.cupos(30)
										.descripcion("Python es un lenguaje de alto nivel de programación interpretado cuya filosofía hace hincapié en la legibilidad de su código, se utiliza para desarrollar aplicaciones de todo tipo, ejemplos: Instagram, Netflix, Spotify, Panda 3D, entre otros.​")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/python.png")))
										.build();
				Curso cursoOracle = Curso.builder()
										.nombre("Flutter")
										.fechaInicio(LocalDate.of(2022, 3, 3))
										.fechaFin(LocalDate.of(2023, 10, 25))
										.cupos(30)
										.descripcion("Flutter es un SDK de código fuente abierto de desarrollo de aplicaciones móviles creado por Google. Suele usarse para desarrollar interfaces de usuario para aplicaciones en Android, iOS y Web.")
										.imagen(Files.readAllBytes(Paths.get("src/main/resources/static/img/flutetr.png")))
										.build();
				cRepo.save(cursoJava);
				cRepo.save(cursoSpring);
				cRepo.saveAndFlush(cursoOracle);
			}
		};
	}
	
	

}
