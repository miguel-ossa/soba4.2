package com.perfmath.spring.soba.model.domain;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Timestamp;

public class TimestampAdapter extends XmlAdapter<String,Timestamp> {
    public Timestamp unmarshal(String val) throws Exception {
        return Timestamp.valueOf(val);
    }
    public String marshal(Timestamp val) throws Exception {
        return val.toString();
    }
}
