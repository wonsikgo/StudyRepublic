package org.mohajo.studyrepublic.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author 이요한
 * @since 2019.01.22
 * @version
 * - REPORT_WHY_CD 도메인 추가
 */

@Data
@Entity
@Table(name = "report_why_cd", schema="StudyRepublic")
public class ReportWhyCD {
	@Id
	private int reportWhyCode;
	
	private String codeValueEnglish;
	private String codeValueKorean;
	/**
	 * @param reportWhyCode
	 */
	public ReportWhyCD() {
		
	}
	public ReportWhyCD(int reportWhyCode) {
		super();
		this.reportWhyCode = reportWhyCode;
	}



}
