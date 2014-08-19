package com.slocamo.entity.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@Entity
@IdClass(com.slocamo.beans.client.UserFeedbackId.class)
@Table(name = "user_feedback")
@JsonAutoDetect
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "getUserFeedback", query = "select u.feedback, count(u.iduser_feedback) as count "
				+ "from UserFeedback u where u.idstore=:storeId group by u.feedback"),
		@NamedQuery(name = "getUserFeedbackWithDateRange", query = "select u.feedback, count(u.iduser_feedback) as count "
				+ "from UserFeedback u where u.idstore=:storeId and FROM_UNIXTIME(u.created) >= STR_TO_DATE(:from,  '%d-%m-%Y') "
				+ "and FROM_UNIXTIME(u.created) <= STR_TO_DATE(:to, '%d-%m-%Y') group by u.feedback"),
		@NamedQuery(name = "getUserCommentsWithDateRange", query = "select u.comments from UserFeedback u where u.idstore=:storeId "
				+ "and FROM_UNIXTIME(u.created) >= STR_TO_DATE(:from,  '%d-%m-%Y') "
				+ "and FROM_UNIXTIME(u.created) <= STR_TO_DATE(:to, '%d-%m-%Y')") })
public class UserFeedback {
	public static final String GET_USER_FEEDBACK = "getUserFeedback";
	public static final String GET_USER_FEEDBACK_WITH_DATE_RANGE = "getUserFeedbackWithDateRange";
	public static String GET_USER_COMMENTS_WITH_DATE_RANGE = "getUserCommentsWithDateRange";
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long iduser_feedback;
	@Id
	private Long uid;
	@Id
	private Long idstore;
	private String feedback;
	private String comments;
	private Long created;
	private Long updated;

	/**
	 * @return the iduser_feedback
	 */
	public Long getIduser_feedback() {
		return iduser_feedback;
	}

	/**
	 * @param iduser_feedback
	 *            the iduser_feedback to set
	 */
	public void setIduser_feedback(Long iduser_feedback) {
		this.iduser_feedback = iduser_feedback;
	}

	/**
	 * @return the uid
	 */
	public Long getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * @return the idstore
	 */
	public Long getIdstore() {
		return idstore;
	}

	/**
	 * @param idstore
	 *            the idstore to set
	 */
	public void setIdstore(Long idstore) {
		this.idstore = idstore;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback
	 *            the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the created
	 */
	public Long getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Long created) {
		this.created = created;
	}

	/**
	 * @return the updated
	 */
	public Long getUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Long updated) {
		this.updated = updated;
	}

}
