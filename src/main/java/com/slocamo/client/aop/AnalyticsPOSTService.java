package com.slocamo.client.aop;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.slocamo.auth.bean.entity.Session;
import com.slocamo.beans.client.Coupons;
import com.slocamo.client.dao.AnalyticsPOSTDAO;
import com.slocamo.entity.analytics.CampaignRequest;
import com.slocamo.entity.analytics.LoggingRequest;
import com.slocamo.entity.analytics.Notification;
import com.slocamo.entity.client.Coupon;
import com.slocamo.entity.client.CouponRedeems;
import com.slocamo.entity.client.UserCoupons;

/**
 * @author vvr@slocamo.com
 * 
 */
@Aspect
public class AnalyticsPOSTService {

	private AnalyticsPOSTDAO analyticsPOSTDAO;
	private Logger logger = Logger.getLogger(AnalyticsPOSTService.class);

	/**
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.slocamo.client.service.CouponService..*(..))")
	public Object addCouponServiceAnalytics(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return addToAnalytics(proceedingJoinPoint);
	}

	/**
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.slocamo.auth.service.UserSecurityService.addSessionDetails*(..))")
	public Object addLoginServiceAnalytics(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return addToAnalytics(proceedingJoinPoint);
	}

	/**
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	private Object addToAnalytics(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {
		Object result = null;
		Object[] args = proceedingJoinPoint.getArgs();
		Timestamp currTimeStamp = new Timestamp(new Date().getTime());
		LoggingRequest loggingRequest = null;
		String requestId = null;
		try {
			if (args.length > 0) {
				loggingRequest = (LoggingRequest) args[0];
				requestId = loggingRequest.getUid() + ":" + currTimeStamp;
				loggingRequest.setTimestamp(currTimeStamp);
				loggingRequest.setRequestId(requestId);
				loggingRequest.setStatus("-1");
			}
		} catch (IndexOutOfBoundsException e) {
			logger.error("loggingRequest not sent: " + e);

		} catch (ClassCastException e) {
			logger.error("loggingRequest not sent: " + e);

		}

		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			if (loggingRequest != null) {
				analyticsPOSTDAO.saveToLogTables(loggingRequest);
			}
			throw e;
		}

		if (loggingRequest == null) {
			return result;
		}

		// Setting the status to 0 -> success
		loggingRequest.setStatus("0");
		String methodName = proceedingJoinPoint.getSignature().getName();
		try {
			if (methodName.contains("get")) {
				if (methodName.contains("getCoupons")) {
					analyticsPOSTDAO.saveToLogTables(loggingRequest);
					Long storeId = (Long) args[1];
					List<CampaignRequest> requests = translateToCampaigns(
							(Coupons) result, storeId, currTimeStamp, requestId);
					for (CampaignRequest request : requests) {
						analyticsPOSTDAO.saveToLogTables(request);
					}
				}
			} else if (methodName.contains("addSessionDetails")) {
				Notification notification = new Notification();
				notification.setTimeStamp(currTimeStamp);
				notification.setNotificationType("ACTION_LOGIN");
				Session session = (Session) result;
				notification.setRecordID("" + session.getUid());
				notification.setStatus("0");
				//Updating logging request
				String requestID = session.getUid() + ":" + currTimeStamp;
				loggingRequest.setRequestId(requestID);
				notification.setRequestId(requestID);
				loggingRequest.setUid(""+session.getUid());
				analyticsPOSTDAO.saveToLogTables(loggingRequest);
				analyticsPOSTDAO.saveToLogTables(notification);
			} else if (methodName.contains("postRedeemCoupon")) {
				CouponRedeems couponRedeems = (CouponRedeems) args[1];
				Notification notification = new Notification();
				notification.setTimeStamp(currTimeStamp);
				notification.setRequestId(requestId);
				notification.setRecordID("" + couponRedeems.getIdcoupons());
				notification.setBusinessVendor("" + couponRedeems.getIdstore());
				notification.setNotificationType("ACTION_REDEEM");
				notification.setStatus("0");
				analyticsPOSTDAO.saveToLogTables(loggingRequest);
				analyticsPOSTDAO.saveToLogTables(notification);
			} else if (methodName.contains("postUserCoupons")) {
				UserCoupons userCoupons = (UserCoupons) args[1];
				Notification notification = new Notification();
				notification.setTimeStamp(currTimeStamp);
				notification.setRequestId(requestId);
				notification.setRecordID("" + userCoupons.getIdcoupons());
				//TODO:user_coupons must have storeId entry as well
				//notification.setBillingParameter("" + userCoupons.getUid());
				notification.setNotificationType("ACTION_SHARE");
				notification.setStatus("0");
				analyticsPOSTDAO.saveToLogTables(loggingRequest);
				analyticsPOSTDAO.saveToLogTables(notification);
			}
		} catch (Exception e) {
			logger.error("Error adding to campaignRequests/notifications. " + e);
		}
		return result;
	}

	private List<CampaignRequest> translateToCampaigns(Coupons result,
			Long storeId, Timestamp currTimeStamp, String requestId) {
		List<Coupon> coupons = result.getCoupons();
		List<CampaignRequest> campaignRequests = new ArrayList<CampaignRequest>();
		for (Coupon coupon : coupons) {
			CampaignRequest campaignRequest = new CampaignRequest();
			campaignRequest.setRecordID(String.valueOf((coupon.getIdcoupons()
					.longValue())));
			campaignRequest.setBusinessVendor(String.valueOf(storeId));
			campaignRequest.setRequestId(requestId);
			campaignRequest.setTimeStamp(currTimeStamp);
			campaignRequests.add(campaignRequest);
		}
		return campaignRequests;
	}
	
	@Autowired
	public void setAnalyticsPOSTDAO(AnalyticsPOSTDAO analyticsPOSTDAO) {
		this.analyticsPOSTDAO = analyticsPOSTDAO;
	}

}
