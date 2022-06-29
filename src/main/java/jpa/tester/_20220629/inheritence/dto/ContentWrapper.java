package jpa.tester._20220629.inheritence.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ContentWrapper {

    private Long id;

    private String description;

    private String videoInfo;
    private String questions; // xxx/xxx/xxx/xxx 형태

    private String dataType;
}
