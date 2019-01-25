package org.mohajo.studyrepublic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 신상용
 * @since 2019.01.22
 * @version
 * StudyFileshareboardReply domain 클래스 추가
 */

@Getter
@Setter
@Entity
@Table(name = "study_qnaboard_reply")
public class StudyQnaboardReply extends StudyBoardReply{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "study_qnaboard_reply_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int studyQnaboardReplyId;
	@Column(name = "study_qnaboard_id")
	private int studyQnaboardId;
}