package com.perfmath.spring.soba.webflow.services;

import java.util.ArrayList;
import java.util.List;

public class OnlineServiceImpl implements OnlineService {

    public List<String> getServices() {
        List<String> services = new ArrayList<String>();
        services.add("Online Banking");
        services.add("Mobile and Text Banking");
        services.add("Bill Payment Service");
        services.add("Online Statements");
        services.add("Online and Mobile Deposit");
        services.add("Electronic Funds Transfers");
        services.add("Check Images");
        services.add("Email and Text Alerts");
        services.add("Budget Tools");
        services.add("Interactive Demos");
        services.add("Frequently Asked Questions");
        return services;
    }
}
