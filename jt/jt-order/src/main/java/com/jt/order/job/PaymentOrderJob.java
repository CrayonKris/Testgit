package com.jt.order.job;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jt.order.mapper.OrderMapper;

public class PaymentOrderJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//通过加载文件，获取上下文对象
		ApplicationContext applicationContext=(ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
		//获取orderMapper
		applicationContext.getBean(OrderMapper.class).paymentOrderScan(new DateTime().minusDays(1).toDate());
	}
}
