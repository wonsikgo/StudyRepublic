package org.mohajo.studyrepublic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "education_cd", schema = "StudyRepublic")
public class EducationCD {

@Id
@Column(name = "EUCATION_CODE")
private String educationCode;
}
