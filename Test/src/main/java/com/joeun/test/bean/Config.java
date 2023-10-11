package com.joeun.test.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.joeun.test.dto.Board;

@Configuration		// 스프링 설정 빈으로 등록
public class Config {
	// 빈을 등록해주는 클래스로 지정
	
	// 자바 코드로 빈을 등록하겠다.
	// 1. 객체를 반환하는 메소드를 정의
	// 2. 메소드에 @Bean 어노테이션 정의
	
	@Bean			// IoC 컨테이너에 빈으로 등록
	public Board getboard() {
		return new Board("제목", "작성자", "내용");
	}
	
	@Bean(name = "gallery")
	public Board getGallery() {
		return new Board("갤러리 제목", "갤러리 작성자", "갤러리 내용");
	}
	
	@Bean(name = "board")
	public Board board() {
		return new Board("게시판 제목", "게시판 작성자", "게시판 내용");
	}
	

}
