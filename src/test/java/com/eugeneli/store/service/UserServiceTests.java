package com.eugeneli.store.service;

import com.eugeneli.store.entity.User;
import com.eugeneli.store.mapper.UserMapper;
import com.eugeneli.store.service.ex.ServiceException;
import com.eugeneli.store.service.ex.UsernameNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.rowset.serial.SerialException;
import java.util.Date;

//表示标注当前的类是一个测试类不会随同项目一起打包
@SpringBootTest
@RunWith(SpringRunner.class)//表示启动这个单元测试类 单元测试类是不能够运行的 需要传递一个参数 必须是SpringRunner的实例类型
public class UserServiceTests {

    //idea有检测的功能 接口是不能直接创建bean的 动态代理技术来解决
    @Autowired
    private IUserService userService;

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
        user.setUsername("123");
        user.setEmail("123123@qq.com");
        user.setPassword("123");
        userService.reg(user);


    }


    @Test
    public void login(){

        try {
            String username="ak";
            String password="ak";
            User user = userService.login(username, password);
            System.out.println("登录成功"+user);

        }catch (ServiceException e){
            System.out.println("登录失败"+e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }


    }


    /**
     * 更新密码测试
     */
    @Test
    public void changePassword(){

        userService.changePassword(2,"管理员","123","321");
    }

    /**
     * 通过id获取用户信息测试
     */
    @Test
    public void getByUid(){
        User userByUid = userService.getUserByUid(2);
        System.out.println("用户信息："+userByUid);
    }

    @Test
    public void changeInfo(){

        User user=new User();
        user.setPhone("13110770770");
        user.setEmail("152@qq.com");
        user.setGender(0);

        userService.changeInfo(3,"管理员",user);
    }



}
