package com.staok.spboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.staok.spboot.entity.User;
import com.staok.spboot.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaokSpringBootApplicationTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testQuery() throws Exception {
        User user = userMapper.selectById("169b61437b064df0a952d0202363a1c3");
        Assert.assertNotNull(user);
    }

}
