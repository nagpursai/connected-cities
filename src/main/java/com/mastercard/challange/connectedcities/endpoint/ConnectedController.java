package com.mastercard.challange.connectedcities.endpoint;

import com.mastercard.challange.connectedcities.service.ConnectedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConnectedController {

    @Autowired
    private ConnectedService connectedService;

    /**
     * Endpoint will check that if both of the given cities are connected or not.
     * @param origin origin city
     * @param destination destination city
     * @return <b>YES</b> if cities are connected</br><b>NO</b> if not cities are not connected
     */
    @GetMapping(path = "/connected")
    public String connected(@RequestParam(value = "origin", required = false) String origin,
                            @RequestParam(value = "destination", required = false) String destination) {
        log.info("Getting path between {} and {}", origin, destination);
        return connectedService.connected(origin, destination);
    }
}
