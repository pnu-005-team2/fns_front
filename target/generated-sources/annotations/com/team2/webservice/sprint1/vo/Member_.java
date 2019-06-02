package com.team2.webservice.sprint1.vo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Member.class)
public abstract class Member_ {

	public static volatile ListAttribute<Member, LikeRecord> likeRecords;
	public static volatile SingularAttribute<Member, Integer> uid;
	public static volatile SingularAttribute<Member, String> password;
	public static volatile SingularAttribute<Member, String> img;
	public static volatile SingularAttribute<Member, String> gender;
	public static volatile SingularAttribute<Member, String> intro;
	public static volatile SingularAttribute<Member, String> name;
	public static volatile ListAttribute<Member, Board> boards;
	public static volatile SingularAttribute<Member, String> email;

	public static final String LIKE_RECORDS = "likeRecords";
	public static final String UID = "uid";
	public static final String PASSWORD = "password";
	public static final String IMG = "img";
	public static final String GENDER = "gender";
	public static final String INTRO = "intro";
	public static final String NAME = "name";
	public static final String BOARDS = "boards";
	public static final String EMAIL = "email";

}

