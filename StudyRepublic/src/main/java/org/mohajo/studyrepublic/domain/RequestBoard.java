/**
 * 
 */
package org.mohajo.studyrepublic.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 윤원식
 * @since 2019. 1. 23.
 * @version
 * -requestBoard 도메인 추가
 */


@Getter
@Setter
@ToString
@Entity
@Table(name = "requestboard")
public class RequestBoard{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "requestboard_id")
	private int requestBoardId;

	protected String id;
	protected String title;
	protected String content;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date date= new Date();
	protected int notice;
	protected int status;
	protected int hit;
	@Column(name = "likecount")
	protected int likeCount;
	@Column(name = "replycount")
	protected int replyCount;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="requestboard_id")
	private List<RequestBoardReply> requestBoardReply;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="requestboard_id")
	private List<RequestBoardFile> requestBoardFile;
	
	@ManyToOne
    @JoinColumn(name="id",insertable=false, updatable=false)
	protected Member member;



}
