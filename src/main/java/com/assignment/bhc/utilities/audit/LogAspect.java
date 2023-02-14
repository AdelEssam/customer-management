package com.assignment.bhc.utilities.audit;


import com.assignment.bhc.dto.AccountRequestDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import java.util.Date;


@Aspect
@Component
public class LogAspect {

	private  ThreadLocal action = new ThreadLocal();

	@Autowired
	private Tracer tracer;


	@Around("@annotation(LoggableAction) && within(com.assignment..*)")
	public Object aroundLoggableAction(ProceedingJoinPoint jp) throws Throwable {
		Span span = tracer.currentSpan();
		LoggableAction annotation = ((MethodSignature) jp.getSignature()).getMethod()
				.getAnnotation(LoggableAction.class);
		action.set(annotation.action());
		return execute(jp, annotation.layer(),span.context().traceId());
	}

	public Object execute(ProceedingJoinPoint jp, String layer,String traceID) throws Throwable {
		long startTime = System.currentTimeMillis();
		Signature methodSignature = jp.getSignature();
		LogRecord logRecord = new LogRecord((String)action.get(),
				layer + "." + methodSignature.getDeclaringType().getSimpleName() + "." + methodSignature.getName());
		logRecord.setCustomerID(getCustomerID(jp.getArgs()));
		logRecord.setActionDate(new Date().toString());
		logRecord.setTransactionID(traceID);
		try {
			return jp.proceed();
		} catch (Throwable e) {
			logRecord.setErrorMessage(e.getMessage());
			throw e;
		} finally {
			logRecord.setResponseTime(System.currentTimeMillis() - startTime);
			//here we can add log as audit for log all transaction
			System.out.println(logRecord.toString());

		}
	}

	private String getCustomerID(Object[] args) {
		for (Object arg : args) {
			if (arg instanceof AccountRequestDto) {
				AccountRequestDto accountDto = (AccountRequestDto) arg;
				if(accountDto!=null){
					return accountDto.getCustomerID().toString();
				}
			}else if(arg instanceof String){
					return arg.toString();
			}
		}
		return null;
	}

}
