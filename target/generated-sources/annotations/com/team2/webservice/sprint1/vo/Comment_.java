package com.team2.webservice.sprint1.vo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, String> writer;
	public static volatile SingularAttribute<Comment, String> content;
	public static volatile SingularAttribute<Comment, String> Date;
	public static volatile SingularAttribute<Comment, Board> board;
	public static volatile SingularAttribute<Comment, Long> cid;

	public static final String WRITER = "writer";
	public static final String CONTENT = "content";
	public static final String DATE = "Date";
	public static final String BOARD = "board";
	public static final String CID = "cid";

}

