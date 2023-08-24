package com.example.jpastudy.persistence;

import com.example.jpastudy.domain.Member;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * count 쿼리가 안나간다.
     */
    @Test
    void 페이징_Slice_반환() {
        Pageable pageable = PageRequest.of(2, 40);
        회원을_저장한다(400);
        Slice<Member> page = memberRepository.readAllBy(pageable);
        /**
         * Hibernate:
         *     select
         *         m1_0.id,
         *         m1_0.name
         *     from
         *         member m1_0 offset ? rows fetch first ? rows only
         */
    }

    /**
     * 전체 페이지 및 totalElment를 조회하기 위해 추가적인 cout 쿼리가 필요하다
     */
    @Test
    void 페이징_Page_반환() {
        Pageable pageable = PageRequest.of(2, 40);
        회원을_저장한다(400);
        Page<Member> page = memberRepository.findAll(pageable);
        /**
         * Hibernate:
         *     select
         *         m1_0.id,
         *         m1_0.name
         *     from
         *         member m1_0 offset ? rows fetch first ? rows only
         * Hibernate:
         *     select
         *         count(m1_0.id)
         *     from
         *         member m1_0
         */
    }

    private void 회원을_저장한다(int count) {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            members.add(new Member("name" + i));
        }
        memberRepository.saveAll(members);
    }
}
