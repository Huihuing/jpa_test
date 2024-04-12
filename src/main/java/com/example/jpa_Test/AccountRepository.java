package com.example.jpa_Test;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속 받아 interface 생성
// <대상엔터티, ID타입>
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
