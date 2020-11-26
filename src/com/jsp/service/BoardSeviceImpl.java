package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.dao.BoardDAO;
import com.jsp.dto.BoardVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class BoardSeviceImpl implements BoardService {
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String,Object>();
		
		//현재 page 번호에 맞는 리스트를 perPageNum개수만큼 가져온다
		List<BoardVO> boardList = boardDAO.selectSearchBoardList(cri);
		
		int totalCount = boardDAO.selectSearchBoardListCount(cri);
		
		PageMaker pageMaker =new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("boardList", boardList);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		BoardVO board = boardDAO.selectBoardByBno(bno);
		return board;			 
	}

	@Override
	public BoardVO getBoardModify(int bno) throws SQLException {
		BoardVO board = boardDAO.selectBoardByBno(bno);
		return board;
	}

	@Override
	public void write(BoardVO board) throws SQLException {
		int  bno = boardDAO.selectBoardSequenceNextValue();
		
		board.setBno(bno);
		boardDAO.insertBoard(board);
	}

	@Override
	public void modify(BoardVO board) throws SQLException {
		boardDAO.updateBoard(board);
	}

	@Override
	public void remove(int bno) throws SQLException {
		boardDAO.deleteBoard(bno);
	}

}
