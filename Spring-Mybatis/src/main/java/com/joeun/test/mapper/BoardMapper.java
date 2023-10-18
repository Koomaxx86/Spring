package com.joeun.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.joeun.test.dto.Board;
import com.joeun.test.dto.Option;

@Mapper
public interface BoardMapper {

	// 메소드 명은 Mapper.xml 매핑 파일의 SQL 태그 id 값과 일치해야한다.
	public List<Board> list(int searchType, String keyword) throws Exception;
	
	// public List<Board> list(@Param("searchType") int searchType, @Param("keyword") String keyword) throws Exception;
	// @Param 어노테이션을 생략할 때에는 메소드의 매개변수명과 매핑파일의 파라미터명을 일치시켜야한다.
	public List<Board> list(Option option) throws Exception;

	public Board select(Integer boardNo) throws Exception;
	
	// insert, update, delete 요청의 경우 반환결과는 DB에 적용된 행의 수이다.
	public Integer insert(Board board) throws Exception;

	// public Integer update(Board board) throws Exception;
	/*
	   map
	  {
	    "boardNo" 	: "100" 
	  	"title" 	: "제목",
	  	"writer" 	: "작성자",
	  	"content" 	: "내용",
	  }
	*/
	public Integer update(Map<String, String> map) throws Exception;

	// @Param("파라미터명") 	: XML 매핑 파일의 SQL 쿼리에서 매핑될 파라미터명을 명시하는 어노테이션
	public Integer delete(@Param("boardNo") Integer no) throws Exception;

}












