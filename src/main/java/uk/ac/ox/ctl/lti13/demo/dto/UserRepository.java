package uk.ac.ox.ctl.lti13.demo.dto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUserEmail(String email);
}
