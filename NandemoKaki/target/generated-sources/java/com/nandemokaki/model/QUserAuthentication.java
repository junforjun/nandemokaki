package com.nandemokaki.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUserAuthentication is a Querydsl query type for UserAuthentication
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUserAuthentication extends EntityPathBase<UserAuthentication> {

    private static final long serialVersionUID = 443490609L;

    public static final QUserAuthentication userAuthentication = new QUserAuthentication("userAuthentication");

    public final StringPath userAuth = createString("userAuth");

    public final StringPath userId = createString("userId");

    public QUserAuthentication(String variable) {
        super(UserAuthentication.class, forVariable(variable));
    }

    public QUserAuthentication(Path<? extends UserAuthentication> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAuthentication(PathMetadata<?> metadata) {
        super(UserAuthentication.class, metadata);
    }

}

