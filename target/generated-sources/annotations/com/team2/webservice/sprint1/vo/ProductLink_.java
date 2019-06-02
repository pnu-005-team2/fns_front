package com.team2.webservice.sprint1.vo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductLink.class)
public abstract class ProductLink_ {

	public static volatile SingularAttribute<ProductLink, Integer> position_x;
	public static volatile SingularAttribute<ProductLink, Long> plid;
	public static volatile SingularAttribute<ProductLink, Integer> position_y;
	public static volatile SingularAttribute<ProductLink, String> linktext;
	public static volatile SingularAttribute<ProductLink, Board> board;

	public static final String POSITION_X = "position_x";
	public static final String PLID = "plid";
	public static final String POSITION_Y = "position_y";
	public static final String LINKTEXT = "linktext";
	public static final String BOARD = "board";

}

