package org.mohajo.studyrepublic.repository;

import org.mohajo.studyrepublic.domain.AdminComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 이요한
 * @since 2019.01.22
 * @version
 * - AdminCommentRepository추가
 * 
 */

public interface AdminCommentRepository extends JpaRepository<AdminComment, Integer>{

}
