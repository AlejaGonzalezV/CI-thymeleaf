package com.workshop.main.security;

import java.util.Optional;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.workshop.main.model.TsscAdmin;
import com.workshop.main.repositories.TsscAdminRepository;


@Service
public class MyCustomAdmDetailsService implements UserDetailsService {

private TsscAdminRepository admRepo;
	
	@Autowired
	public MyCustomAdmDetailsService(TsscAdminRepository admRepo) {		
		this.admRepo = admRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TsscAdmin adm = admRepo.findByUsername(username);
		if (adm != null) {
			User.UserBuilder builder = User.withUsername(username).password(adm.getPassword()).roles(adm.getSuperAdmin());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}