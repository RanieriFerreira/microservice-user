package mapper;

import com.studies.microserviceuser.domain.User;
import com.studies.microserviceuser.request.UserPostRequest;
import com.studies.microserviceuser.request.UserPutRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostRequest userPostRequest);
    public abstract User toUser(UserPutRequest userPutRequest);
}
