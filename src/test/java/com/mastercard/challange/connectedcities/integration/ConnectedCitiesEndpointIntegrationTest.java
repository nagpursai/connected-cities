package com.mastercard.challange.connectedcities.integration;


import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ConnectedControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @MethodSource("data")
    public void testLowerCase(String origin, String destination, String expectedResult) throws URISyntaxException {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("origin", origin);
        queryParams.put("destination", destination);
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
        Assert.assertEquals(expectedResult, response);
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
