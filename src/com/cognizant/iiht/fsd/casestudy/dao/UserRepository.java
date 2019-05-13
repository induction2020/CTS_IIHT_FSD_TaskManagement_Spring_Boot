package com.cognizant.iiht.fsd.casestudy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cognizant.iiht.fsd.casestudy.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE firstName = :searchKey "
			+ " or lastName = :searchKey "
			+ " or employeeId = :searchKey ") 
	List<User> findUserBySearchKey(@Param("searchKey") String searchKey);
	// https://www.concretepage.com/angular-2/angular-2-custom-pipe-example
}
