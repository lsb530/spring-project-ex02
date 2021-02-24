package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
@Log4j
public class BoardServiceTests {
    @Setter(onMethod_ = @Autowired)
    private BoardService service;

    @Test
    public void testExist() {
        log.info(service);
        assertNotNull(service);
    }

    @Test
    public void testRegister() {
        BoardVO board = new BoardVO();
        for(int i=0; i<100 ; i++ ) {
            board.setTitle("새로 작성하는 글"+i).setContent("새로 작성하는 내용"+i).setWriter("newbie"+i);
            service.register(board);
            log.info("생성된 게시물의 번호: " + board.getBno());
        }
        board.setTitle("새로 작성하는 글").setContent("새로 작성하는 내용").setWriter("newbie");
        service.register(board);
        log.info("생성된 게시물의 번호: " + board.getBno());
    }

    @Test
    public void testGetList() {
//        service.getList().forEach(board -> log.info(board));
        service.getList(new Criteria(2, 10)).forEach(board -> log.info(board));
    }

    @Test
    public void testGet() {
        log.info(service.get(1L));
    }

    @Test
    public void testDelete() {
        // 게시물 번호의 존재 여부를 확인하고 테스트할 것
        log.info("REMOVE RESULT: " + service.remove(2L));
    }

    @Test
    public void testUpdate() {
        BoardVO board = service.get(1L);
        if(board == null) return;
        board.setTitle("제목 수정합니다.");
        log.info("MODIFY RESULT: " + service.modify(board));
    }
}