/**
 * 
 */
package org.mohajo.studyrepublic.studypage;

import java.util.List;
import java.util.Optional;

import org.mohajo.studyrepublic.domain.QStudyFileshareboard;
import org.mohajo.studyrepublic.domain.QStudyMember;
import org.mohajo.studyrepublic.domain.QStudyNoticeboard;
import org.mohajo.studyrepublic.domain.QStudyQnaboard;
import org.mohajo.studyrepublic.domain.StudyFileshareboard;
import org.mohajo.studyrepublic.domain.StudyMember;
import org.mohajo.studyrepublic.domain.StudyNoticeboard;
import org.mohajo.studyrepublic.domain.StudyQnaboard;
import org.mohajo.studyrepublic.domain.StudyQnaboardReply;
import org.mohajo.studyrepublic.repository.StudyFileshareboardRepository;
import org.mohajo.studyrepublic.repository.StudyMemberRepository;
import org.mohajo.studyrepublic.repository.StudyNoticeboardRepository;
import org.mohajo.studyrepublic.repository.StudyQnaboardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;

/**
 * @author 신상용
 * @since 2019. 1. 27.
 * @version 0.0
 * - 기능설명
 */
public class StudyPagePredicate {
	/**
	 * 동적 쿼리 메서드
	 * 해당 쿼리를 이용해서 공지사항의 글을 내림차순으로 3개만 가지고 온다.
	 * 
	 * 수정 시 사용방법 : StudyNoticeboard을 자신이 찾고자하는 table의 domain으로 설정한다.
	 * snb를 autowired 된 자신이 사용하고자하는 repository 변수명으로 변경한다.
	 * 그리고, builder.and(domain, 칼럼(변수), 조건) 안에 있는 조건을 자신이 원하는 조건으로 설명한다.
	 * 만약 동적으로 사용하는 파라미터를 바꿔야 한다면, studyId를 자신이 원하는 양식에 맞게 바꾸면 된다.
	 * 
	 * @param String studyId, StudyNoticeboardRepository
	 * @return List<T> findAll(Predicate 조건을 만족한 것);
	 * @author shin.sangyong
	 * @since 2019.01.25 16:52
	 * @version 1.1	lastEdit 2019.01.29 03:16
	 *
	 */
	public List<StudyNoticeboard> studyNoticeResultPredicate(String studyId, int listCount, StudyNoticeboardRepository snb) {
				
		BooleanBuilder builder = new BooleanBuilder();
		
		QStudyNoticeboard studynoticeboard = QStudyNoticeboard.studyNoticeboard;
		
		builder.and(studynoticeboard.studyId.like(studyId));
		
		/*
		 * Pageable 변수를 선언하고 PageRequest.of를 통해서 PageRequest객체를 생성과 동시에 초기화 시켜준다.
		 */
		Pageable pageable = PageRequest.of(0, listCount, Sort.Direction.DESC, "number");
		
		/*
		 * 그리고  QuerydslPredicateExecutor를 상속 받은 repository를 통해서 findAll()을 실행해주고.
		 *  여기에 Parameter로 Predicate를 정의해준 BooleanBuilder와 Pageable 객체를 넣어준다.
		 */
		Page p = snb.findAll(builder, pageable);
		/*
		 * 아래 내용은 Page 타입의 getContent()를 통해서 List를 받아오는 방법을 몰랐을 대 구현한 코드. 참고용.
			Iterable<StudyNoticeboard> result = snb.findAll(builder, pageable);
			Iterator<StudyNoticeboard> iterator = result.iterator();
			List<StudyNoticeboard> list = new ArrayList<StudyNoticeboard>();
			
			while(iterator.hasNext()) {
				list.add(iterator.next());
		}*/
		return p.getContent();
	}
	/**
	 * 동적쿼리 메서드
	 * 
	 * 해당 쿼리를 이용해서 Qna게시판의 글을 내림차순으로 3개만 가지고 온다.
	 * 
	 * @param String studyId, StudyNoticeboardRepository
	 * @return List<T> findAll(Predicate 및 Pageable 조건을 만족한 것);
	 * @author shin.sangyong
	 * @since 2019.01.29 03:16
	 * @version 1.0	lastEdit 2019.01.29 03:16
	 */
	public List<StudyQnaboard> studyQnaResultPredicate(String studyId, int listCount, StudyQnaboardRepository sqb) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QStudyQnaboard studyqnaboard = QStudyQnaboard.studyQnaboard;
		builder.and(studyqnaboard.studyId.like(studyId));
		Pageable pageable = PageRequest.of(0, listCount, Sort.Direction.DESC, "studyId");
		Page p = sqb.findAll(builder, pageable);
		
		List<StudyQnaboard> l = p.getContent();
		System.out.println("실행에 들어감");
		for(StudyQnaboard sqb2 : l) {
			System.out.println("실행에 들어감2");
			//해당 부분에서 예외가 발생한다. sqb2.getStudyQnaboardReply()
			/*System.out.println(":" + sqb2.getStudyQnaboardReply());
			for(StudyQnaboardReply sqbr : sqb2.getStudyQnaboardReply()) {
				System.out.println("실행에 들어감3");
				System.out.println(sqbr.getId() + "|||" + sqbr.getStudyId());
			}*/
		}
		
		return p.getContent();
	}
	/**
	 * 동적쿼리 메서드
	 * 
	 * 해당 쿼리를 이용해서 파일공유 게시판의 글을 내림차순으로 3개만 가지고 온다.
	 * 
	 * @param String studyId, StudyNoticeboardRepository
	 * @return List<T> findAll(Predicate 및 Pageable 조건을 만족한 것);
	 * @author shin.sangyong
	 * @since 2019.01.29 03:16
	 * @version 1.0	lastEdit 2019.01.29 03:16
	 */
	public List<StudyFileshareboard> studyFileshareResultPredicate(String studyId, int listCount, StudyFileshareboardRepository sfsb) {
		
		BooleanBuilder builder = new BooleanBuilder();
		QStudyFileshareboard studyfileshareboard = QStudyFileshareboard.studyFileshareboard;
		builder.and(studyfileshareboard.studyId.like(studyId));
		Pageable pageable = PageRequest.of(0, listCount, Sort.Direction.DESC, "studyId");
		Page p = sfsb.findAll(builder, pageable);
		return p.getContent();
	}
	
	public List<StudyMember> findByThisStudyMemberPredicate(String studyId, StudyMemberRepository smr) {
		BooleanBuilder builder = new BooleanBuilder();
		QStudyMember studymember = QStudyMember.studyMember;
		builder.and(studymember.studyId.like(studyId))
			.and(studymember.studyMemberStatusCode.codeValueEnglish.eq("LEADER")
					.or(studymember.studyMemberStatusCode.codeValueEnglish.eq("MEMBER")));
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "enrollDate");
		Page p = smr.findAll(builder, pageable);
		
		return p.getContent();
	}
	
	public Optional<StudyNoticeboard> findByNoticeboardNumberAndStudyId(StudyNoticeboard studyNoticeboard, StudyNoticeboardRepository snr) {
		BooleanBuilder builder = new BooleanBuilder();
		QStudyNoticeboard noticeboard = QStudyNoticeboard.studyNoticeboard;
		builder.and(noticeboard.studyId.eq(studyNoticeboard.getStudyId()))
		.and(noticeboard.number.eq(studyNoticeboard.getNumber()));
		
		return snr.findOne(builder);
	}
}
