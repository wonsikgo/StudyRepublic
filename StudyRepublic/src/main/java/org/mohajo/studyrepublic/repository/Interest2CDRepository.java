package org.mohajo.studyrepublic.repository;




import java.util.List;

import org.mohajo.studyrepublic.domain.Interest2CD;
import org.mohajo.studyrepublic.domain.StudyInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 윤성호
 * @since 2019.01.24
 * @version
 * - AdminCommentRepository추가
 * 
 */
@Transactional(readOnly = true)
public interface Interest2CDRepository extends JpaRepository<Interest2CD, String>{
	
	@Query(value = "select * from interest_2_cd where interest_1_code = 'P'", nativeQuery = true) 
	List <Interest2CD> Pinterest2List();
	
	@Query(value = "select * from interest_2_cd where interest_1_code = 'D'", nativeQuery = true) 
	List <Interest2CD> Dinterest2List();	
	
	@Query(value = "select * from interest_2_cd where interest_1_code = 'W'", nativeQuery = true) 
	List <Interest2CD> Winterest2List();	
	
	@Query(value = "select * from interest_2_cd where interest_1_code = 'N'", nativeQuery = true) 
	List <Interest2CD> Ninterest2List();	
	
	@Query(value="select * from popular_pr_interest limit 3",nativeQuery=true)
	public List<Interest2CD> findPremiumPopularTag();
	
	@Query(value="select * from popular_bs_interest limit 3",nativeQuery=true)
	public List<Interest2CD> findBasicPopularTag();
}
