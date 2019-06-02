package com.team2.webservice.sprint1.vo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LikeRecord.class)
public abstract class LikeRecord_ {

	public static volatile SingularAttribute<LikeRecord, Long> lid;
	public static volatile SingularAttribute<LikeRecord, Member> member;
	public static volatile SingularAttribute<LikeRecord, Boolean> like_boolean;
	public static volatile SingularAttribute<LikeRecord, Board> board;

	public static final String LID = "lid";
	public static final String MEMBER = "member";
	public static final String LIKE_BOOLEAN = "like_boolean";
	public static final String BOARD = "board";

}

