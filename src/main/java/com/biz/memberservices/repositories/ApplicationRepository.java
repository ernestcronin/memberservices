package com.biz.memberservices.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz.memberservices.entities.ApplicationEntity;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

	@Query("select a from ApplicationEntity a where a.name= :name")
	public Optional<ApplicationEntity> findApplicationEntityByName(@Param("name") String name);
}
