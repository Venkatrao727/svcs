package com.slocamo.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.slocamo.client.service.CouponService;
import com.slocamo.common.util.RestUtils;
import com.slocamo.entity.analytics.LoggingRequest;
import com.slocamo.entity.client.CouponRedeems;
import com.slocamo.entity.client.UserCoupons;

/**
 * @author vvr@slocamo.com
 * 
 */
@Controller
public class CouponController {
	private CouponService couponService;

	/**
	 * @param couponId
	 * @return This method returns the coupon settings for a coupon.
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/coupon-settings", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Object getCouponSettings(HttpServletRequest request,
			@ModelAttribute(value = "loggingRequest") LoggingRequest loggingRequest,
			@RequestParam(value = "coupon-id") Long couponId,
			@Value("#{request.getAttribute('uid')}") Long uid) {
		RestUtils.addDetailsForLoggingRequest(request, loggingRequest);
		return couponService.getCouponSettings(loggingRequest, couponId);
	}

	/**
	 * @param storeId
	 * @param uid
	 * @param currentTime
	 * @return This method returns all valid coupons for a store. i.e.., It
	 *         returns all valid coupons from currentTime.
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/coupons", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Object getCoupons(HttpServletRequest request,
			@ModelAttribute(value = "loggingRequest") LoggingRequest loggingRequest,
			@RequestParam(value = "store-id") Long storeId,
			@RequestParam(value = "currentTime") Long currentTime,
			@Value("#{request.getAttribute('uid')}") Long uid) {
		RestUtils.addDetailsForLoggingRequest(request, loggingRequest);
		return couponService.getCoupons(loggingRequest, storeId, uid, currentTime);
	}

	@RequestMapping(value = "/coupon-redeems", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public void postRedeemCoupon(HttpServletRequest request,
			@ModelAttribute(value = "loggingRequest") LoggingRequest loggingRequest,
			@ModelAttribute(value = "couponRedeems") CouponRedeems couponRedeems,
			@Value("#{request.getAttribute('uid')}") Long uid) {
		RestUtils.addDetailsForLoggingRequest(request, loggingRequest);
		couponRedeems.setUid(uid);
		couponService.postRedeemCoupon(loggingRequest, couponRedeems);
	}

	@RequestMapping(value = "/user-coupons", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public void postUserCoupons(HttpServletRequest request,
			@ModelAttribute(value = "loggingRequest") LoggingRequest loggingRequest,
			@ModelAttribute(value = "userCoupons") UserCoupons userCoupons,
			@Value("#{request.getAttribute('uid')}") Long uid) {
		RestUtils.addDetailsForLoggingRequest(request, loggingRequest);
		userCoupons.setUid(uid);
		userCoupons.setForwarded_by(uid);
		couponService.postUserCoupons(loggingRequest, userCoupons);
	}

	/**
	 * @return the couponService
	 */
	public CouponService getCouponService() {
		return couponService;
	}

	/**
	 * @param couponService
	 *            the couponService to set
	 */
	@Autowired
	public void setCouponService(CouponService couponService) {
		this.couponService = couponService;
	}
}