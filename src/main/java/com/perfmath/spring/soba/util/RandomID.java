package com.perfmath.spring.soba.util;
import java.util.*;
public class RandomID {
String id;

public RandomID(int n) {

	Random rand = new Random ();
	StringBuffer strbuf = new StringBuffer ();
	int k = 100000000 + rand.nextInt(899999999);
	strbuf.append(Integer.toString(k));
	id = strbuf.toString();
}

public String getId() {
	return id;
}

}
