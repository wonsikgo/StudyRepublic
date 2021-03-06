package org.mohajo.studyrepublic.domain;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

/**
 * @author 신상용
 * @since 2019.01.22
 * @version
 * StudyNoticeboard domain 클래스 추가
 */

@Data
@Entity
@Table(name = "study_noticeboard")
public class StudyNoticeboard /*extends StudyBoard*/ implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "study_noticeboard_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int studyNoticeboardId;
	
	//@Temporal(TemporalType.TIME)
	@DateTimeFormat(iso=ISO.TIME)
	@Column(name = "starttime")
	private String startTime;
	
	//@Temporal(TemporalType.TIME)
	@DateTimeFormat(iso=ISO.TIME)
	@Column(name = "endtime")
	private String endTime;
	
	@DateTimeFormat(iso=ISO.DATE)
	@Column
	private Date day;
	
	@Column
	private String location;
	
	@Column(name = "study_id")
	private String studyId;
	@Column
	private int number;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date date=new Date();
	@Column
	private String id;
	@Column
	private int status;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name="study_id", referencedColumnName="studyId", insertable=false, updatable=false),
		@JoinColumn(name="id", referencedColumnName="id", insertable=false, updatable=false)
	})
	private StudyMember studyMember;
	
	@OneToMany
	@JoinColumn(name="study_noticeboard_id")
	private List<StudyNoticeboardFile> studyNoticeboardFile;
	
	@OneToMany
	@JoinColumn(name="study_noticeboard_id")
	private List<StudyNoticeboardReply> studyNoticeboardReply;

	/*public void setDay(String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		try {
			this.day = sdf.parse(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setStartTime(String startTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		try {
			this.startTime = (Time) sdf.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setEndTime(String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		try {
			this.endTime = (Time) sdf.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
/**
 * 작성자: 이미연
 * 작성일: 2019-01-30
 * 참고사이트:	https://medium.com/@SlackBeck/%EC%A4%91%EC%B2%A9%EB%90%9C-fk-foreign-key-%EB%A5%BC-jpa%EB%A1%9C-%EC%97%B0%EA%B4%80-%EA%B4%80%EA%B3%84-%EB%A7%A4%ED%95%91-%ED%95%98%EA%B8%B0-216ba5f2b8ed
 * 참조 엔티티는 읽기전용이므로 insert, update 불가함에 주의
 */
//	@ManyToOne
//	@JoinColumns({
//			@JoinColumn(name="study_id", referencedColumnName = "studyId", insertable = false, updatable = false),
//			@JoinColumn(name="id", referencedColumnName = "id", insertable = false, updatable = false)
//	})
//	private StudyMember studyMember;
}
