package com.team2.webservice.sprint1.dto;

<<<<<<< HEAD
import java.util.Date;
=======
>>>>>>> timeline
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

<<<<<<< HEAD
	public static volatile SingularAttribute<Comment, Date> postTime;
=======
	public static volatile SingularAttribute<Comment, String> date;
>>>>>>> timeline
	public static volatile SingularAttribute<Comment, Long> pid;
	public static volatile SingularAttribute<Comment, String> writer;
	public static volatile SingularAttribute<Comment, String> content;
	public static volatile SingularAttribute<Comment, Long> cid;

<<<<<<< HEAD
	public static final String POST_TIME = "postTime";
=======
	public static final String DATE = "date";
>>>>>>> timeline
	public static final String PID = "pid";
	public static final String WRITER = "writer";
	public static final String CONTENT = "content";
	public static final String CID = "cid";

}

