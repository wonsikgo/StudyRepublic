package org.mohajo.studyrepublic.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;

/**
 * @author	이미연
 * @since	2019. 1. 22.
 * @version	0.0
 * - 레벨테스트 응답 테이블 복합키 클래스
 */
@Data
@Embeddable
public class LeveltestResponseId implements Serializable {

	/*private LeveltestId leveltestId;*/
	/*private String id;*/
	private int leveltestId;
	@Embedded
	private StudyMemberId studyMemberId;
	
}
