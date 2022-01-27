package com.eugeneli.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    //连接处
    //HikariProxyConnection@1829496747 wrapping com.mysql.cj.jdbc.ConnectionImpl@1d4fb213
    @Test
    void getMysqlConnect() throws SQLException {

        System.out.println(dataSource.getConnection());
    }

}
