package jpa.tester._20220629.inheritence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LESSON")
@Getter @Setter
public class Lesson extends Content {

    private String videoInfo;

}
