package io.wisoft.springboot.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저저장_불러오기() {
        //given
        String name = "테스트이름";
        String email = "테스트이메일";
        String picture = "테스트사진";
        Role role = Role.GUEST;
        userRepository.save(User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(role)
                .build());

        //when
        List<User> userList = userRepository.findAll();

        //then
        User user = userList.get(0);
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(picture, user.getPicture());
        assertEquals(role, user.getRole());

    }


}