package com.ezen.springdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ezen.springdb.dto.BoardDTO;

public interface BoardMapper {
	
	// resultset.get()으로 꺼낼 필요가 없어짐
	// 중요한 점은 dto의 이름과 컬럼의 이름이 같아야 한다
	@Select("SELECT * FROM board WHERE board_id=#{board_id}")
	BoardDTO get(Integer board_id);
	
	@Select("SELECT * FROM board ORDER BY write_date DESC")
	List<BoardDTO> getAll();
	
	@Insert("INSERT INTO board VALUES(board_id_seq.NEXTVAL,"
			+ "#{writer_id},#{writer_pw},#{write_title},#{write_content},sysdate,0,0,0)")
	Integer add(BoardDTO board); // Integer로 설정한 이유 : 인서트 하고 나면 1행이 추가되었다는 게 끝이기 때문에

	// 무언가 수정을 할 때는 기본키를 조건으로 바꾼다
	@Update("UPDATE board SET write_title=#{write_title}, write_content=#{write_content} "
			+ "WHERE board_id=#{board_id}")
	Integer modify(BoardDTO board);
	
	
	@Delete("DELETE FROM board WHERE board_id=#{board_id}")
	Integer delete(Integer board_id);
	
	
}
