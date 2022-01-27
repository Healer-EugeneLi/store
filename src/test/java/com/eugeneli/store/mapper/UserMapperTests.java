package com.eugeneli.store.mapper;

import com.eugeneli.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//表示标注当前的类是一个测试类不会随同项目一起打包
@SpringBootTest
@RunWith(SpringRunner.class)//表示启动这个单元测试类 单元测试类是不能够运行的 需要传递一个参数 必须是SpringRunner的实例类型
public class UserMapperTests {

    //idea有检测的功能 接口是不能直接创建bean的 动态代理技术来解决
    @Autowired
    private UserMapper userMapper;

    /**
     * 单元测试的方法 可以单独独立运行 不用启动整个项目 可以做单元测试 提升了代码测试效率
     * 必须被@Test 注解修饰
     * 返回值类型必须是void
     * 方法的参数列表不指定任何类型
     * 方法的访问修饰符必须是public
     */
    @Test
    public void insert(){

        User user=new User();
        user.setUsername("lyj");
        user.setEmail("861269630@qq.com");
        user.setPassword("lyjlyj");

        Integer result = userMapper.insert(user);
        System.out.println("insert result"+result);

    }


    @Test
    public void findUserByUsername(){
        String username="lyj";

        User user = userMapper.findUserByUsername(username);
        System.out.println("查询结果"+user);
    }

    @Test
    public void updatePasswordByUid(){

        userMapper.updatePasswordByUid(1,"321","管理员",new Date());

    }

    @Test
    public void findByUid(){
        User byUid = userMapper.findByUid(1);
        System.out.println(byUid);
    }

    /**
     * 更新用户信息
     */
    @Test
    public void updateInfoByUid(){
        User user=new User();
        user.setUid(1);
        user.setPhone("13110770289");
        user.setEmail("lyj@163.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid(){

        userMapper.updateAvatarByUid(2,"123.jpg","管理者",new Date());
    }
}
