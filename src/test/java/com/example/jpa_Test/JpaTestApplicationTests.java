package com.example.jpa_Test;

import com.example.jpa_Test.Article.Article;
import com.example.jpa_Test.Article.ArticleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import com.example.jpa_Test.Member.Member;
import com.example.jpa_Test.Member.MemberRepository;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@SpringBootTest
class JpaTestApplicationTests {

	// ArticleRepository 를 스트링부트가 알아서 찾아줌.
	@Autowired
	ArticleRepository articleRepository;

	// 게시물 저장
	@Test
	void contextLoads() {
		Article article = new Article();
		article.setTitle("asdf");
		article.setContent("wqerr");

		articleRepository.save(article); // DB에 반영

		Article article2 = new Article();
		article2.setTitle("제목2");
		article2.setContent("제목2");

		articleRepository.save(article2);
	}

	// 전체 게시물 가져오기
	@Test
	void test2() {
		List<Article> articleList = articleRepository.findAll();

		for (Article article : articleList) {
			System.out.println(article.getTitle());
		}
	}

	// 아이디를 이용해 게시물 하나만 가져오기
	@Test
	void test3() {
		Article article = articleRepository.findById(1).get();
		System.out.println(article.getTitle());
	}

	@Test
	void test4() {
		Article article = articleRepository.findById(1).get();

		article.setTitle("제목1");
		articleRepository.save(article);
	}

	// 아이디로 해당 게시물 삭제
	@Test
	void test5() {
		articleRepository.deleteById(1);
	}

	// 객체로 삭제
	@Test
	void test6() {
		Article article = articleRepository.findById(2).get();
		articleRepository.delete(article);
	}

	@Autowired
	AccountRepository accountRepository;

	@Test
	// 트랜젝션
	void test7() {
		Account account1 = new Account();
		account1.setOwner("hong");
		account1.setAmount(10000);

		Account account2 = new Account();
		account2.setOwner("kim");
		account2.setAmount(10000);

		accountRepository.save(account1);
		accountRepository.save(account2);
	}


	// 트랜잭션을 제대로 처리하지 않으면 데이터가 꼬일 수 있음
	@Test
	void test8() {
		// hong -> kim 1000원 보내기
		Account hong = accountRepository.findById(1).get();
		Account kim = accountRepository.findById(2).get();

		hong.setAmount(hong.getAmount() - 1000); // 1000 감소
		accountRepository.save(hong);

		if (true) {
			throw new RuntimeException("예외 발생");
		}

		kim.setAmount(kim.getAmount() + 1000); // 1000 증가
		accountRepository.save(kim);
	}


	@Test
	@Transactional
	@Rollback(false)
	void test9() {
		Account hong = accountRepository.findById(1).get();
		Account kim = accountRepository.findById(2).get();

		hong.setAmount(hong.getAmount() - 1000); // 1000 감소
		accountRepository.save(hong);

		if (true) {
			throw new RuntimeException("예외 발생");
		}

		kim.setAmount(kim.getAmount() + 1000); // 1000 증가
		accountRepository.save(kim);
	}


	// JPA -> 영속성 컨텍스트
	@Test
	@Transactional
	@Rollback(false)
	void test10() {
		Article article1 = new Article();
		article1.setTitle("제목3");
		article1.setContent("내용3");
		articleRepository.save(article1);

		Article article2 = new Article();
		article2.setTitle("제목4");
		article2.setContent("내용4");
		articleRepository.save(article2);

		//DB에서 바로 꺼내오는 것이 아니다.
		//1차 캐시에 저장되어 있는 것을 가져옴
		Article target = articleRepository.findById(article1.getId()).get();
		System.out.println(target.getTitle());
		System.out.println(target.getContent());
	}

	@Test
	@Transactional
	@Rollback(false)
	void test11() {
		Article article = articleRepository.findById(3).get();
		System.out.println(article.getTitle());
		System.out.println(article.getContent());
	}

	@Test
	@Transactional
	@Rollback(false)
	void test12() {
		Article article1 = new Article();
		article1.setTitle("제목1");
		article1.setContent("내용1");

		System.out.println("=======article1 저장========");
		articleRepository.save(article1);
		System.out.println("============================");

		Article article2 = new Article();
		article2.setTitle("제목2");
		article2.setContent("내용2");

		System.out.println("=======article2 저장========");
		articleRepository.save(article2);
		System.out.println("============================");
	}

	// jpa 쓰기 지연 기능
	@Test
	@Transactional
	@Rollback(false)
	void test13() {
		Article article1 = articleRepository.findById(1).get();
		System.out.println(article1.getId());
		System.out.println(article1.getTitle());

		Article article2 = articleRepository.findById(2).get();
		System.out.println(article2.getId());
		System.out.println(article2.getTitle());

		System.out.println("=======article1 삭제========");
		articleRepository.delete(article1);
		System.out.println("============================");

		System.out.println("=======article2 삭제========");
		articleRepository.delete(article2);
		System.out.println("============================");
	}

	@Test
	@Transactional
	@Rollback(false)
	void test14() {
		Article article = articleRepository.findById(3).get(); // select 쿼리 날라감

		article.setTitle("제목33");
		article.setContent("내용33");

//		articleRepository.save(article); // update 쿼리 날라감
	}

	@Autowired
	MemberRepository memberRepositoty;

	// 영속성 컨텍스트
	// 연관 관계
	@Test
	@Transactional
	@Rollback(false)
	void test15() {
		Member member = new Member();

		member.setName("회원1");
		memberRepositoty.save(member);

		Article article = new Article();
		article.setTitle("제목1");
		article.setContent("내용1");
//		article.setMember(member);

		articleRepository.save(article);
	}

	@Test
	@Transactional
	@Rollback(false)
	void test16() {
		Article article = articleRepository.findById(1).get();

		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getContent());
//		System.out.println("작성자 : " + article.getMember().getName());
	}

	@Test
	@Transactional
	@Rollback(false)
	void test17() {
		Member member = new Member();
		member.setName("회원1");

		Article article1 = new Article();
		article1.setTitle("제목1");
		article1.setContent("내용1");

		Article article2 = new Article();
		article2.setTitle("제목2");
		article2.setContent("내용2");

		memberRepositoty.save(member);
		articleRepository.save(article1);
		articleRepository.save(article2);

		member.getArticles().add(article1);
		member.getArticles().add(article2);
	}

	@Test
	@Transactional
	void test18() {
		Member member = memberRepositoty.findById(2).get();

		List<Article> articles = member.getArticles();

		for (Article article : articles) {
			System.out.println(article.getTitle());
		}
	}

	// 지연로딩

	// 영속성 전이
}