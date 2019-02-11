package org.mohajo.studyrepublic.repository;

import java.util.Date;
import java.util.List;

import org.mohajo.studyrepublic.domain.Member;
import org.mohajo.studyrepublic.domain.PopularStudy;
import org.mohajo.studyrepublic.domain.QStudy;
import org.mohajo.studyrepublic.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;


/**
 * @author	이미연
 * @since	2019. 1. 22.
 * @version	0.0
 * - 기능 설명 1
 */
public interface StudyRepository extends JpaRepository<Study, String>, QuerydslPredicateExecutor<Study> {

	/**	
	*	@author	이미연
	*/

	//페이징
	public default Predicate makePredicate(String type, String keyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QStudy study = QStudy.study;
		//검색에 필요한 타입, 키워드를 이용해서 쿼리를 생성 (p.269)
		
		return builder;
	}

//	@Query(value = "select sv from StudyView sv where sv.typeCode = ?1 and sv.studyStatusCode not in ('C', 'D')")
////	@Query(value = "select s.* from study_view s where s.typeCode = ?1 and s.studyStatusCode not in ('C', 'D')", nativeQuery=true)
////	public List<Study> findValidStudyByTypeCode(TypeCD typeCode, Pageable paging);
//	public Page<StudyView> findValidStudyByTypeCode(TypeCD typeCode, Pageable paging);
	//쿼리문 안에는 Study 클래스에 설정한 TypeCD 타입 변수명을 사용한다.
		//@Query(value = "select s from (select s from Study s where s.typeCode = ?1) where s.studyStatusCode in ('C', 'D')")
		//위 테스트 해보고, 성능 비교
	//OrderBy 를 등록일, 시작일로 구분
	
	/*select avg(score) from review r where study_id = "BO00001";*/
	@Query(value = "select avg(score) from Review r where r.studyId = ?1")
	public float getAverageScore(String studyId);
	
	@Query(value = "select s from Study s where s.startDate between ?1 and ?2")
	public List<Study> getByStartDates(Date s1, Date s2);
	
	
	/**
	*	@author	이요한
	*/
	@Query(value="select * from popular_study where TYPE_CODE='P' order by rand() limit 2",nativeQuery=true)
//	public List<Study> findPrStudyBytypeCode();
	public List<PopularStudy> findPrStudyBytypeCode();
	
	@Query(value="select * from study natural join study_interest where interest_2_code in ('P02','P08') "
			+ "and STUDY_STATUS_CODE ='O' order by rand() limit 2",nativeQuery=true)
//	public List<Study> findBsStudyBytypeCode();
	public List<PopularStudy> findBsStudyBytypeCode();
	
//	@Query(value ="select * from (select * from study_member where id= :member AND (study_member_status_code = 'ME' || study_member_s+tatus_code = 'LE')) a1 join study s1"
//			+ " using (study_id) where s1.study_status_code='G'", nativeQuery=true)
//	List<Study> findByMemberId(Member member);
/*	@Query(value ="select * from study s1 join (select * from study_member where id= :member AND (study_member_status_code = 'ME' || study_member_s+tatus_code = 'LE')) a1"
	+ " using (study_id) where s1.study_status_code='G'", nativeQuery=true)
	List<Study> findByMemberId(Member member);*/
	
	
}