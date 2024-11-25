package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient {

	private static void insertTest(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {

			// Transaction 시작
			tx.begin();

			Board board = new Board();
			board.setTitle("JPA 제목");
			board.setWriter("관리자");
			board.setContent("JPA 글 등록 잘 되네요.");
			board.setCreateDate(new Date());
			board.setCnt(0L);

			// 글 등록
			em.persist(board);

			// Transaction commit
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Transaction rollback
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void searchTest(EntityManagerFactory emf,long num) {
		EntityManager em = emf.createEntityManager();

		try {
			Board searchBoard = em.find(Board.class, num);
			System.out.println("---> " + searchBoard.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	

	public static void allSearchTest(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		try {
			String jpql = "SELECT b FROM Board b ORDER BY b.seq DESC";
			List<Board> boardList = em.createQuery(jpql,Board.class).getResultList();
			for(Board brd : boardList) {
				System.out.println("---> " +brd.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
//		for(int i = 0 ; i < 10 ; i ++) 
//			insertTest(emf);
//		searchTest(emf,1L);
		allSearchTest(emf);
		emf.close();
	}


//	public static void main(String[] args) {
//
//		// EntityManager 생성
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
//		EntityManager em = emf.createEntityManager();
//
//		// Transaction 생성
//		EntityTransaction tx = em.getTransaction();
//
//		try {
//
////			Board searchBoard = em.find(Board.class,1L);
////			System.out.println("---> " + searchBoard.toString());
//
//			// Transaction 시작
//			tx.begin();
//
//			Board board = new Board();
//			board.setTitle("JPA 제목");
//			board.setWriter("관리자");
//			board.setContent("JPA 글 등록 잘 되네요.");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//
//			// 글 등록
//			em.persist(board);
//
//			// Transaction commit
//			tx.commit();
//
//			String jpql = "SELECT b FROM Board b ORDER BY b.seq DESC";
//			List<Board> boardList = em.createQuery(jpql, Board.class).getResultList();
//			for (Board brd : boardList) {
//				System.out.println("---> " + brd.toString());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			// Transaction rollback
//			tx.rollback();
//		} finally {
//			em.close();
//			emf.close();
//		}
//	}

}
