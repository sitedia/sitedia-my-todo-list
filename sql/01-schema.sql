CREATE SCHEMA MYTODOLIST;

CREATE TABLE MYTODOLIST.SPRING_SESSION (
    SESSION_ID CHAR(36),
    CREATION_TIME BIGINT NOT NULL,
    LAST_ACCESS_TIME BIGINT NOT NULL,
    MAX_INACTIVE_INTERVAL INT NOT NULL,
    PRINCIPAL_NAME VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (SESSION_ID)
);

CREATE TABLE MYTODOLIST.SPRING_SESSION_ATTRIBUTES (
    SESSION_ID CHAR(36),
    ATTRIBUTE_NAME VARCHAR(200),
    ATTRIBUTE_BYTES BYTEA,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_ID) REFERENCES MYTODOLIST.SPRING_SESSION(SESSION_ID) ON DELETE CASCADE
);

CREATE TABLE MYTODOLIST.CLIENTDETAILS (
    appId VARCHAR(256) PRIMARY KEY,
    resourceIds VARCHAR(256),
    appSecret VARCHAR(256),
    scope VARCHAR(256),
    grantTypes VARCHAR(256),
    redirectUrl VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additionalInformation VARCHAR(4096),
    autoApproveScopes VARCHAR(256)
);

CREATE TABLE MYTODOLIST.USERS (
	id serial NOT NULL,
    mail VARCHAR(256) NOT NULL,
    first_name VARCHAR(256),
    last_name VARCHAR(256),
    password VARCHAR(256) NOT NULL,
    role VARCHAR(256) NOT NULL,
    date_of_birth TIMESTAMP
);

CREATE TABLE MYTODOLIST.TODOS (
	id serial NOT NULL,
    user_id BIGINT NOT NULL,
    title VARCHAR(256) NOT NULL,
    description VARCHAR(2048),
    creation_date TIMESTAMP NOT NULL
);

CREATE TABLE MYTODOLIST.CATEGORIES (
	id serial NOT NULL,
    user_id BIGINT NOT NULL,
    name VARCHAR(256) NOT NULL,
    description VARCHAR(256)
);

CREATE TABLE MYTODOLIST.TODO_CATEGORIES (
	id serial NOT NULL,
    todo_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL
);


ALTER TABLE MYTODOLIST.USERS ADD PRIMARY KEY (id);
ALTER TABLE MYTODOLIST.USERS ADD CONSTRAINT user_mail_uk UNIQUE (mail);

ALTER TABLE MYTODOLIST.TODOS ADD PRIMARY KEY (id);
ALTER TABLE MYTODOLIST.TODOS ADD FOREIGN KEY (user_id) REFERENCES MYTODOLIST.USERS(id) ON DELETE CASCADE;

ALTER TABLE MYTODOLIST.CATEGORIES ADD PRIMARY KEY (id);
ALTER TABLE MYTODOLIST.CATEGORIES ADD FOREIGN KEY (user_id) REFERENCES MYTODOLIST.USERS(id) ON DELETE CASCADE;

ALTER TABLE MYTODOLIST.TODO_CATEGORIES ADD PRIMARY KEY (id);
ALTER TABLE MYTODOLIST.TODO_CATEGORIES ADD FOREIGN KEY (todo_id) REFERENCES MYTODOLIST.TODOS(id) ON DELETE CASCADE;
ALTER TABLE MYTODOLIST.TODO_CATEGORIES ADD FOREIGN KEY (category_id) REFERENCES MYTODOLIST.CATEGORIES(id) ON DELETE CASCADE;
