package com.perfmath.spring.soba.aop.aspectj;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfBasicLoggingAspect {

	@Pointcut("execution(* com.perfmath.spring..getConnection*(String)) && args(connDescriptor)")
	public void getConnectionExecution(String connDescriptor) {
	}

	@Pointcut("bean(jdbcConnection*)")
	public void inJDBCConnection() {
	}

	@Around("getConnectionExecution(connDescriptor) && inJDBCConnection ()")
	public Object logAround(ProceedingJoinPoint joinPoint, String connDescriptor)
			throws Throwable {
		long startTime = System.currentTimeMillis();
		String signature = joinPoint.getTarget().getClass().getName() + "."
				+ joinPoint.getSignature().getName();
		try {
			System.out.println("perfBasic logging: startTime=" + startTime
					+ " for " + signature);
			Object result = joinPoint.proceed();
			long endTime = System.currentTimeMillis();
			String logData = "perfBasic logging: endTime=" + endTime + " for "
					+ signature + ":elapsed time=" + (endTime - startTime)
					+ " ms";
			System.out.println(logData);
			return result;
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument "
					+ Arrays.toString(joinPoint.getArgs()) + " in "
					+ joinPoint.getSignature().getName() + "()");
			throw e;
		}
	}
}
