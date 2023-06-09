package com.springboot_to_aws.posts;

import com.springboot_to_aws.domain.posts.Posts;
import com.springboot_to_aws.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After  //Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    //테스트중 데이터 침범을 막기위해
    public void cleanup(){
        postsRepository.deleteAll();
    }
    @Test
    public void postsave_load(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                //테이블 posts의 insert/update 쿼리를 실행
                //id값이 있으면 update, 없으면 insert
                .title(title)
                .content(content)
                .author("hsoo0720@naver.com")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();
        //findAll --> 테이블 posts에 있는 모든 데이터를 조회해오는 메소드.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
    @Test
    public void BaseTimeEntity_enroll(){

        //given
        LocalDateTime now = LocalDateTime.of(2023,5,3,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println(">>>>>>>>> createDate = " + posts.getCreatedDate()+", modifiedDate = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
