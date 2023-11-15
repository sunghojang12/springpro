package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.board.entity.Board;

@Mapper // mybatis API
public interface BoardMapper {
	
	public List<Board> getLists(); // 전체 리스트
	public void boardInsert(Board vo); // 등록
	public Board boardContent(int idx); // 상세보기
	public void boardDelete(int idx); // 삭제
	public void boardUpdate(Board vo); // 수정
	
	
	public void boardCount(int idx); // 조회수 증가
}
