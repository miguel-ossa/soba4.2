/**
 * 
 */
package com.perfmath.spring.soba.aop.proxy;

import java.sql.Connection;
public interface IJDBCConnection {
public Connection checkConnection (String connectionDescriptor);
}
