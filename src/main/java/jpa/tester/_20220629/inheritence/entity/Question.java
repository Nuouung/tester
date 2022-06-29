package jpa.tester._20220629.inheritence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("QUESTION")
@Getter @Setter
public class Question extends Content {

    private String questions; // xxx/xxx/xxx/xxx 형태
}
