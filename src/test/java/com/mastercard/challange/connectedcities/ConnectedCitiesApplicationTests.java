package com.mastercard.challange.connectedcities;

import com.mastercard.challange.connectedcities.endpoint.ConnectedController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

class ConnectedCitiesApplicationTests {

    @Autowired
    private ConnectedController endpoint;

    @Test
    public void main() {
        ConnectedCitiesApplication.main(new String[] {});
        Assert.isTrue(true,"Application started");
    }

}
