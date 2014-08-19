package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "slocamo_feedback")
public class SlocamoFeedback {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String feedback_text;
	private Long uid;
	private Long create_date;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the feedback_text
	 */
	public String getFeedback_text() {
		return feedback_text;
	}

	/**
	 * @param feedback_text
	 *            the feedback_text to set
	 */
	public void setFeedback_text(String feedback_text) {
		this.feedback_text = feedback_text;
	}


	/**
	 * @return the create_date
	 */
	public Long getCreate_date() {
		return create_date;
	}

	/**
	 * @param create_date
	 *            the create_date to set
	 */
	public void setCreate_date(Long create_date) {
		this.create_date = create_date;
	}

	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
}
