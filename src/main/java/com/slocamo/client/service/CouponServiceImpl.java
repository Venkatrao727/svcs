package com.slocamo.client.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slocamo.beans.client.CouponIds;
import com.slocamo.beans.client.CouponSettings;
import com.slocamo.beans.client.Coupons;
import com.slocamo.client.dao.CouponDAO;
import com.slocamo.entity.analytics.LoggingRequest;
import com.slocamo.entity.client.CouponRedeems;
import com.slocamo.entity.client.UserCoupons;

/**
 * @author vvr@slocamo.com
 * 
 */
@Service(value = "couponService")
public class CouponServiceImpl implements CouponService {
	private CouponDAO couponDAO;
	Logger logger = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.service.CouponService#getCoupons(java.lang.Long,
	 * java.lang.Long, java.lang.Long)
	 */
	@Transactional
	public Coupons getCoupons(LoggingRequest loggingRequest, Long storeId,
			Long uid, Long time) {
		Coupons coupons = new Coupons();
		if (logger.isDebugEnabled()) {
			logger.debug("Querying for valid coupons with storeId: " + storeId
					+ " uid: " + uid + "time: " + time);
		}
		coupons.setCoupons(couponDAO.getCoupons(storeId, uid, time));
		return coupons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.service.CouponService#getCouponSettings(long)
	 */
	@Transactional
	public Object getCouponSettings(LoggingRequest loggingRequest,
			long idCoupons) {
		if (logger.isDebugEnabled()) {
			logger.debug("Querying for couponsettings for coupon-id: "
					+ idCoupons);
		}
		CouponSettings couponSettings = new CouponSettings();
		couponSettings.setCouponSettings(getCouponDAO().getCouponSettings(
				idCoupons));
		return couponSettings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.slocamo.rest.service.CouponService#getCouponIdsForStore(long)
	 */
	@Transactional
	public Object getCouponIdsForStore(LoggingRequest loggingRequest,
			long idStore) {
		CouponIds couponIds = new CouponIds();
		couponIds.setCouponIds(getCouponDAO().getCouponIdsForStore(idStore));
		return couponIds;
	}

	@Transactional
	public void postRedeemCoupon(LoggingRequest loggingRequest,
			CouponRedeems couponRedeems) {
		couponDAO.postRedeemCoupon(couponRedeems);
	}

	@Transactional
	public void postUserCoupons(LoggingRequest loggingRequest,
			UserCoupons userCoupons) {
		couponDAO.postUserCoupons(userCoupons);
	}


	@Override
	public Object getCouponIdsForRange(Long storeId, String from, String to) {
		return couponDAO.getCouponIdsForRange(storeId, from, to);
	}
	
	/**
	 * @return the couponDAO
	 */
	public CouponDAO getCouponDAO() {
		return couponDAO;
	}

	/**
	 * @param couponDAO
	 *            the couponDAO to set
	 */
	@Autowired
	public void setCouponDAO(CouponDAO couponDAO) {
		this.couponDAO = couponDAO;
	}

}
