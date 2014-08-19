package com.slocamo.beans.client;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import com.slocamo.entity.client.Coupon;

@JsonAutoDetect
@XmlRootElement
public class Coupons {
private List<Coupon> coupons;

/**
 * @return the coupons
 */
public List<Coupon> getCoupons() {
	return coupons;
}

/**
 * @param coupons the coupons to set
 */
public void setCoupons(List<Coupon> coupons) {
	this.coupons = coupons;
}
}
