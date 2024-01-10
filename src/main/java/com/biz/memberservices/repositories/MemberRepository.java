package com.biz.memberservices.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.biz.memberservices.entities.MemberEntity;

public interface MemberRepository extends CrudRepository<MemberEntity, Long>{
	
	@Query("SELECT m FROM MemberEntity m WHERE m.memberPid =:pid")
	public Optional<MemberEntity> findMemberEntityByMemberPid(@Param("pid") Long pid);
	

}
