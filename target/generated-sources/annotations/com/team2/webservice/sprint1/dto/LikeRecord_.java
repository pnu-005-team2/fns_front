package com.team2.webservice.sprint1.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LikeRecord.class)
public abstract class LikeRecord_ {

	public static volatile SingularAttribute<LikeRecord, Long> uid;
	public static volatile SingularAttribute<LikeRecord, Long> lid;
	public static volatile SingularAttribute<LikeRecord, Long> pid;
	public static volatile SingularAttribute<LikeRecord, Boolean> like_boolean;

	public static final String UID = "uid";
	public static final String LID = "lid";
	public static final String PID = "pid";
	public static final String LIKE_BOOLEAN = "like_boolean";

}

