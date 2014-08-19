package com.slocamo.client.service;

import com.slocamo.entity.analytics.LoggingRequest;
import com.slocamo.beans.client.Coupons;
import com.slocamo.entity.client.CouponRedeems;
import com.slocamo.entity.client.UserCoupons;

/**
 * @author vvr@slocamo.com
 * 
 */
public interface CouponService {

	/**
	 * @param storeId
	 * @param userId
	 * @param time
	 * @return all valid coupons. i.e.., returns coupons that are valid based
	 *          on the time passed as a request param
	 */
	Coupons getCoupons(LoggingRequest loggingRequest, Long storeId, Long userId, Long time);

	/**
	 * @param loggingRequest 
	 * @param idCoupons
	 * @return the settings of a coupon.
	 */
	Object getCouponSettings(LoggingRequest loggingRequest, long idCoupons);

	/**
	 * @param idStore
	 * @return
	 */
	Object getCouponIdsForStore(LoggingRequest loggingRequest, long idStore);

	/**
	 * @param couponRedeems
	 * @return
	 */
	void postRedeemCoupon(LoggingRequest loggingRequest, CouponRedeems couponRedeems);

	/**
	 * @param userCoupons
	 * @return
	 */
	void postUserCoupons(LoggingRequest loggingRequest, UserCoupons userCoupons);
	
	Object getCouponIdsForRange(Long storeId, String from, String to);
}
