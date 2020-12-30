package com.mastercard.challange.connectedcities.service.impl;

import com.mastercard.challange.connectedcities.service.ConnectedService;
import com.mastercard.challange.connectedcities.util.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class ConnectedServiceImpl implements ConnectedService {

    public static final String NO = "no";

    public static final String YES = "yes";

    @Autowired
    private CSVUtil csvUtil;

    /**
     * {@inheritDoc}
     */
    public String connected(String origin, String destination) {
        if (validateInput(origin, destination)) {
            return NO;
        }
        return isConnected(origin.trim().toLowerCase(), destination.trim().toLowerCase(), csvUtil.readCsv());
    }

    /* Validate given cities in input are valid or not */
    private boolean validateInput(String origin, String destination) {
        return ObjectUtils.isEmpty(origin) || ObjectUtils.isEmpty(destination);
    }

    /* This function will check that if origin city to destination city is there any connectivity exists */
    private String isConnected(String origin, String destination, Map<String, Set<String>> cities) {
        boolean isConnected = isIdenticalInput(origin, destination, cities);

        if (!isConnected && cities.containsKey(origin) && cities.containsKey(destination)) {
            //bfs
            Set<String> visited = new HashSet<>();
            Queue<String> tobeVisited = new LinkedList<>();

            tobeVisited.add(origin);

            while (!tobeVisited.isEmpty() && !isConnected) {
                String city = tobeVisited.poll();
                isConnected = city.equals(destination);

                cities.get(city).stream().forEach(c -> {
                    if (!visited.contains(c)) {
                        tobeVisited.add(c);
                    }
                });
                visited.add(city);
            }
        }

        return isConnected ? YES : NO;
    }

    /* Validate if both of the cities are same and exists in given CSV file */
    private boolean isIdenticalInput(String origin, String destination, Map<String, Set<String>> cities) {
        return origin.equals(destination) && cities.containsKey(origin);
    }
}
