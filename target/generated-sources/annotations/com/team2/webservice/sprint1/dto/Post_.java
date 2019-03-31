package com.team2.webservice.sprint1.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ {

	public static volatile SingularAttribute<Post, String> img;
	public static volatile SingularAttribute<Post, String> hashtags;
	public static volatile SingularAttribute<Post, Long> pid;
	public static volatile SingularAttribute<Post, String> writer;
	public static volatile SingularAttribute<Post, String> content;

	public static final String IMG = "img";
	public static final String HASHTAGS = "hashtags";
	public static final String PID = "pid";
	public static final String WRITER = "writer";
	public static final String CONTENT = "content";

}

