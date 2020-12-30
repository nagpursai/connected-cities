package com.mastercard.challange.connectedcities.service;

public interface ConnectedService {

    /**
     * Check if both cities, origin and destination are connected directly or indirectly
     * @param origin start city
     * @param destination end city
     * @return <b>YES</b> if cities are connected</br><b>NO</b> if not cities are not connected
     */
    String connected(String origin, String destination);
}
