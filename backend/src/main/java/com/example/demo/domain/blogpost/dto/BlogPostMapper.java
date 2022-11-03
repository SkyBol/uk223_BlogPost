package com.example.demo.domain.blogpost.dto;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.dto.UserRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BlogPostMapper {
    User fromUserRegisterDTO(UserRegisterDTO dto);
}
