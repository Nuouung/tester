package jpa.tester._20220629.inheritence.repository;

import jpa.tester._20220629.inheritence.entity.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContentRepository {

    private final EntityManager entityManager;

    // c
    public Long save(Content content) {
        entityManager.persist(content);

        return content.getId();
    }

    // r
    public Optional<Content> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Content.class, id));
    }

    public List<Content> findAll() {
        return entityManager.createQuery("select c from Content c", Content.class)
                .getResultList();
    }

    // u

    // d
}
