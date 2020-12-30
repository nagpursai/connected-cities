package com.mastercard.challange.connectedcities.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Component
@Slf4j
public class CSVUtil {

    @Value("${file.delimeter:,}")
    private String delimeter;

    @Value("${file.filePath}")
    private String filePath;

    @Autowired
    ResourceLoader resourceLoader;

    private Map<String, Set<String>> map = new HashMap<>();

    /**
     * This method will read the CSV file from given path and parse it in a
     * Map of city with all connected cities Node
     * @return map of cities with all its connected cities node
     */
    public Map<String, Set<String>> readCsv() {
        Resource resource = resourceLoader.getResource(filePath);

        try (Stream<String> lines = Files.lines(Paths.get(resource.getFile().getPath()))) {
            lines.forEach(this::parseLine);
        } catch (IOException e) {
            log.error("Can not read the file exception message {} and stacktrace {}", e.getMessage(), e.getStackTrace());
        }
        return map;
    }

    /* this method is responsible to parse a single line from CSV into map */
    private void parseLine(String line) {
        if (!ObjectUtils.isEmpty(line)) {
            String[] cities = line.split(delimeter);
            String origin = cities[0].trim().toLowerCase();
            String destination = cities[1].trim().toLowerCase();

            Set<String> originConnectedTo = getAllConnectedCities(origin);
            Set<String> destinationConnectedTo = getAllConnectedCities(destination);

            originConnectedTo.add(destination);
            destinationConnectedTo.add(origin);
        }
    }

    /* this method will add all cities to a given city node */
    private Set<String> getAllConnectedCities(String city) {
        if (!map.containsKey(city)) {
            map.put(city, new HashSet<>());
        }
        return map.get(city);
    }
}
