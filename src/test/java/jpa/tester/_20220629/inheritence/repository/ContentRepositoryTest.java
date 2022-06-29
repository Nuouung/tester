package jpa.tester._20220629.inheritence.repository;

import jpa.tester._20220629.inheritence.dto.ContentWrapper;
import jpa.tester._20220629.inheritence.entity.Content;
import jpa.tester._20220629.inheritence.entity.Lesson;
import jpa.tester._20220629.inheritence.entity.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ContentRepositoryTest {

    @Autowired ContentRepository contentRepository;

    List<Long> idList = new ArrayList<>();

    @BeforeEach
    void initData(){
        Lesson lesson = new Lesson();
        lesson.setVideoInfo("동영상 정보");
        lesson.setDescription("동영상 정보입니다");

        contentRepository.save(lesson);

        Question question = new Question();
        question.setQuestions("질문1/질문2/질문3");
        question.setDescription("질문입니다");

        contentRepository.save(question);

        idList.add(lesson.getId());
        idList.add(question.getId());
    }

    @Test
    void save() {
        Lesson lesson = new Lesson();
        lesson.setVideoInfo("동영상 정보");
        lesson.setDescription("동영상 정보입니다");

        contentRepository.save(lesson);

        Question question = new Question();
        question.setQuestions("질문1/질문2/질문3");
        question.setDescription("질문입니다");

        contentRepository.save(question);

        idList.add(lesson.getId());
        idList.add(question.getId());

        Content foundLesson = contentRepository.findById(lesson.getId())
                .orElseThrow(null);

        Content foundQuestion = contentRepository.findById(question.getId())
                .orElseThrow(null);

        assertThat(lesson.getId()).isEqualTo(foundLesson.getId());
        assertThat(question.getId()).isEqualTo(foundQuestion.getId());
    }

    @Test
    void findById() {
        Content content1 = contentRepository.findById(idList.get(0)).orElseThrow(null);
        Content content2 = contentRepository.findById(idList.get(1)).orElseThrow(null);

        Lesson lesson = (Lesson) content1;
        Question question = (Question) content2;

        assertThat(lesson.getVideoInfo()).isEqualTo("동영상 정보");
        assertThat(question.getQuestions()).isEqualTo("질문1/질문2/질문3");
    }

    @Test
    void find_realistic_example() {
        List<Content> contentList = contentRepository.findAll();

        List<ContentWrapper> contentWrapperList = new ArrayList<>();
        for (Content content : contentList) {
            if (content instanceof Lesson) {
                ContentWrapper wrapper = new ContentWrapper();
                wrapper.setId(content.getId());
                wrapper.setDescription(content.getDescription());
                wrapper.setVideoInfo(((Lesson) content).getVideoInfo());
                wrapper.setDataType("LESSON");

                contentWrapperList.add(wrapper);

                continue;
            }

            if (content instanceof Question) {
                ContentWrapper wrapper = new ContentWrapper();
                wrapper.setId(content.getId());
                wrapper.setDescription(content.getDescription());
                wrapper.setQuestions(((Question) content).getQuestions());
                wrapper.setDataType("QUESTION");

                contentWrapperList.add(wrapper);
            }
        }

        assertThat(contentWrapperList.get(0).getDataType()).isEqualTo("LESSON");
        assertThat(contentWrapperList.get(0).getVideoInfo()).isEqualTo("동영상 정보");

        assertThat(contentWrapperList.get(1).getDataType()).isEqualTo("QUESTION");
        assertThat(contentWrapperList.get(1).getQuestions()).isEqualTo("질문1/질문2/질문3");
    }
}