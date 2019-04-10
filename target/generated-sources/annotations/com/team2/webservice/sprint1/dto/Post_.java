package com.team2.webservice.sprint1.dto;

<<<<<<< HEAD
=======
import java.sql.Blob;
>>>>>>> timeline
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Post.class)
public abstract class Post_ {

<<<<<<< HEAD
	public static volatile SingularAttribute<Post, byte[]> img;
=======
	public static volatile SingularAttribute<Post, Blob> img;
>>>>>>> timeline
	public static volatile SingularAttribute<Post, Long> pid;
	public static volatile SingularAttribute<Post, String> writer;
	public static volatile SingularAttribute<Post, String> content;
	public static volatile SingularAttribute<Post, String> hashtag;

	public static final String IMG = "img";
	public static final String PID = "pid";
	public static final String WRITER = "writer";
	public static final String CONTENT = "content";
	public static final String HASHTAG = "hashtag";

}

