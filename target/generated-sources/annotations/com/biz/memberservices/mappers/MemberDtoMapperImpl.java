package com.biz.memberservices.mappers;

import com.biz.memberservices.dtos.MemberDTO;
import com.biz.memberservices.entities.MemberEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-10T07:28:37-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 21.0.1 (Eclipse Adoptium)"
)
@Component
public class MemberDtoMapperImpl implements MemberDtoMapper {

    @Override
    public MemberDTO toMemberDto(MemberEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MemberDTO.MemberDTOBuilder memberDTO = MemberDTO.builder();

        memberDTO.pid( entity.getMemberPid() );
        memberDTO.memberIdentifier( entity.getMemberId() );
        memberDTO.memberFirstName( entity.getFirstName() );
        memberDTO.memberLastName( entity.getLastName() );
        memberDTO.birthDate( entity.getBirthDate() );

        return memberDTO.build();
    }
}
