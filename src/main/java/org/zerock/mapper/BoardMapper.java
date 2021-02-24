package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardMapper {
    //    @Select("select * from tbl_board where bno > 0")
    List<BoardVO> getList();

    List<BoardVO> getListWithPaging(Criteria criteria);

    void insert(BoardVO board);

    Integer insertSelectKey(BoardVO board);

    BoardVO read(Long bno);

    int delete(Long bno);

    int update(BoardVO board);

    int getTotalCount(Criteria criteria);
}
