package com.slocamo.client.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slocamo.common.BaseDAO;
import com.slocamo.entity.client.Coupon;
import com.slocamo.entity.client.CouponRedeems;
import com.slocamo.entity.client.CouponSetting;
import com.slocamo.entity.client.UserCoupons;

/**
 * @author vvr@slocamo.com
 * 
 */
@Repository(value = "couponDAO")
public class CouponDAOImpl extends BaseDAO implements CouponDAO {

	@SuppressWarnings("unchecked")
	public List<Coupon> getCoupons(Long storeId, Long userId, Long time) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", userId);
		params.put("storeId", storeId);
		params.put("currentTime", time);
		return (List<Coupon>) executeToList(Coupon.GET_VALID_COUPONS, null,
				null, params);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getCouponSettings(long idCoupons) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idcoupons", idCoupons);
		return (List<Object>) executeToList(CouponSetting.GET_COUPON_SETTINGS,
				null, null, params);
	}

	public List<Object> getCouponIdsForStore(long idStore) {
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("idStore", idStore);
		// return (List<Object>) DAOHelper.executeToList(
		// queryMap.get("GET_COUPON_IDS_FOR_STORE"), null, null, params);
		return null;
	}

	public void postUserCoupons(UserCoupons userCoupons) {
		persist(userCoupons);

	}

	public void postRedeemCoupon(CouponRedeems couponRedeems) {
		persist(couponRedeems);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getCouponIdsForRange(Long storeId, String from, String to) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", storeId);
		params.put("from", from);
		params.put("to", to);
		return (List<Object>) executeToList(Coupon.GET_COUPONS_IN_RANGE, null,
				null, params);
	}

}
