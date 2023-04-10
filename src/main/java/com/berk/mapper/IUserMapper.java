package com.berk.mapper;

import com.berk.dto.request.MovieCommentCreateRequestDto;
import com.berk.dto.request.UserResgisterRequestDto;
import com.berk.dto.response.LoginResponseDto;
import com.berk.repository.entity.MovieComment;
import com.berk.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);

    User toUser(final UserResgisterRequestDto dto);

    LoginResponseDto toLoginResponseDto(final User user);

    MovieComment toMovieComment(final MovieCommentCreateRequestDto dto);
}
