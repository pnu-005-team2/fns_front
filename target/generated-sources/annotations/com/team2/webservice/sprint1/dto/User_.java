package com.team2.webservice.sprint1.dto;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Long> uid;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> gender;
	public static volatile SingularAttribute<User, String> id;

	public static final String UID = "uid";
	public static final String PASSWORD = "password";
	public static final String GENDER = "gender";
	public static final String ID = "id";

}

