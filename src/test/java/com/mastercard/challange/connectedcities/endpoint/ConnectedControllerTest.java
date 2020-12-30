package com.mastercard.challange.connectedcities.endpoint;


import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class ConnectedControllerTest {

    @Autowired
    private ConnectedController endpoint;

    @ParameterizedTest
    @MethodSource("data")
    void contextLoads(String origin, String destination, String expectedResult) {
        String str = endpoint.connected(origin, destination);
        Assert.assertEquals("Path found", expectedResult, str);
    }

    public static Collection<Object[]> data() {
        Collection<Object[]> params = new ArrayList<>();
        params.add(new Object[]{"Boston", "Philadelphia", "yes"});
        params.add(new Object[]{"boston", "philadelphia", "yes"});
        params.add(new Object[]{"BOSTON", "PHILADELPHIA", "yes"});
        params.add(new Object[]{"New York", "Croton-Harmon", "yes"});
        params.add(new Object[]{"Philadelphia", "Pittsburgh", "yes"});
        params.add(new Object[]{"A", "B", "no"});
        params.add(new Object[]{"", "Philadelphia", "no"});
        params.add(new Object[]{"Philadelphia", "Philadelphia", "yes"});
        params.add(new Object[]{"A", "A", "no"});//fail because this do not exists

        return params;
    }
}
