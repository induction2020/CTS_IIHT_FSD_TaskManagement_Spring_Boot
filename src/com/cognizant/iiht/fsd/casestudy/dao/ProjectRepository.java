package com.cognizant.iiht.fsd.casestudy.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.iiht.fsd.casestudy.model.Project;


@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}
