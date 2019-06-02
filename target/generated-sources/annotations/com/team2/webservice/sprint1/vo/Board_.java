package com.team2.webservice.sprint1.vo;

import java.sql.Blob;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Board.class)
public abstract class Board_ {

	public static volatile ListAttribute<Board, LikeRecord> likeRecords;
	public static volatile SingularAttribute<Board, Blob> img;
	public static volatile ListAttribute<Board, ProductLink> productLinks;
	public static volatile ListAttribute<Board, Comment> comments;
	public static volatile SingularAttribute<Board, Member> member;
	public static volatile SingularAttribute<Board, Long> pid;
	public static volatile SingularAttribute<Board, String> content;
	public static volatile SingularAttribute<Board, String> hashtag;

	public static final String LIKE_RECORDS = "likeRecords";
	public static final String IMG = "img";
	public static final String PRODUCT_LINKS = "productLinks";
	public static final String COMMENTS = "comments";
	public static final String MEMBER = "member";
	public static final String PID = "pid";
	public static final String CONTENT = "content";
	public static final String HASHTAG = "hashtag";

}

