package com.slocamo.beans.client;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.slocamo.entity.client.Store;


@XmlRootElement
@JsonAutoDetect
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonSerialize(include = Inclusion.NON_DEFAULT)
public class Stores {
	private Long count;
	@JsonProperty("storeList")
	private List<Store> stores;

	/**
	 * @return the stores
	 */
	@XmlElement(name = "storeList")
	public List<Store> getStores() {
		return stores;
	}

	/**
	 * @param stores
	 *            the stores to set
	 */
	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	// private Integer count = 0;

}
