package org.zerock.service;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardService {
    void register(BoardVO board);

    BoardVO get(Long bno);

    boolean modify(BoardVO board);

    boolean remove(Long bno);

//    List<BoardVO> getList();

    List<BoardVO> getList(Criteria criteria);

    int getTotal(Criteria criteria);
}
