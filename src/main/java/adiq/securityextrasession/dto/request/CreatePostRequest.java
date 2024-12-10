package adiq.securityextrasession.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Getter
@Setter

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePostRequest {

    String description;
    List<String> images;

}
