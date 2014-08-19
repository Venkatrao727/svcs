package com.slocamo.client.dao;

import java.util.List;

import com.slocamo.entity.client.Coupon;
import com.slocamo.entity.client.CouponRedeems;
import com.slocamo.entity.client.UserCoupons;

/**
 * @author vvr@slocamo.com
 *
 */
public interface CouponDAO {

	/**
	 * @param storeId
	 * @param userId
	 * @param time
	 * @return all valid coupons from that time.
	 */
	List<Coupon> getCoupons(Long storeId, Long userId, Long time);

	/**
	 * @param idCoupons
	 * @return coupon settings for a coupon
	 */
	List<Object> getCouponSettings(long idCoupons);

	/**
	 * @param idStore
	 * @return coupon ids for a store
	 */
	List<Object> getCouponIdsForStore(long idStore);

	void postUserCoupons(UserCoupons userCoupons);

	void postRedeemCoupon(CouponRedeems couponRedeems);

	Object getCouponIdsForRange(Long storeId, String from, String to);


}
