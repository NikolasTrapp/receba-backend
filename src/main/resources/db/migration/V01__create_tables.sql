------------------------------------------------------------------------------------------------------------------------
CREATE SCHEMA IF NOT EXISTS receba;
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receba.users
(
    id                 uuid         NOT NULL,
    avatar             varchar(255) NULL,
    email              varchar(255) NOT NULL,
    favorite_thing     varchar(255) NULL,
    password           varchar(255) NOT NULL,
    phone              varchar(255) NULL,
    username           varchar(255) NOT NULL,
    user_creation_date timestamp(6) NOT NULL
);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receba.comments
(
    id           uuid         NOT NULL,
    parent       uuid         NULL,
    user_id      uuid         NOT NULL,
    content      varchar(255) NOT NULL,
    created_date timestamp(6) NOT NULL
);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receba.followers
(
    id          uuid         NOT NULL,
    user_id     uuid         NULL,
    follow_date timestamp(6) NOT NULL
);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receba.likes
(
    comment_id   uuid         NULL,
    id           uuid         NOT NULL,
    post_id      uuid         NULL,
    user_id      uuid         NULL,
    created_date timestamp(6) NOT NULL

);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receba.notifications
(
    id           uuid         NOT NULL,
    target_user  uuid         NULL,
    message      varchar(255) NOT NULL,
    created_date timestamp(6) NOT NULL
);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receba.posts
(
    id           uuid         NOT NULL,
    user_id      uuid         NULL,
    created_date timestamp(6) NOT NULL
);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receba.roles
(
    role_id   bigserial    NOT NULL,
    authority varchar(255) NULL
);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receba.user_role_junction
(
    role_id int8 NOT NULL,
    user_id uuid NOT NULL
);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE oauth2_authorization
(
    id                            varchar(100) NOT NULL,
    registered_client_id          varchar(100) NOT NULL,
    principal_name                varchar(200) NOT NULL,
    authorization_grant_type      varchar(100) NOT NULL,
    authorized_scopes             varchar(1000)     DEFAULT NULL,
    attributes                    character varying DEFAULT NULL,
    state                         varchar(500)      DEFAULT NULL,
    authorization_code_value      character varying DEFAULT NULL,
    authorization_code_issued_at  timestamp         DEFAULT NULL,
    authorization_code_expires_at timestamp         DEFAULT NULL,
    authorization_code_metadata   character varying DEFAULT NULL,
    access_token_value            character varying DEFAULT NULL,
    access_token_issued_at        timestamp         DEFAULT NULL,
    access_token_expires_at       timestamp         DEFAULT NULL,
    access_token_metadata         character varying DEFAULT NULL,
    access_token_type             varchar(100)      DEFAULT NULL,
    access_token_scopes           varchar(1000)     DEFAULT NULL,
    oidc_id_token_value           character varying DEFAULT NULL,
    oidc_id_token_issued_at       timestamp         DEFAULT NULL,
    oidc_id_token_expires_at      timestamp         DEFAULT NULL,
    oidc_id_token_metadata        character varying DEFAULT NULL,
    refresh_token_value           character varying DEFAULT NULL,
    refresh_token_issued_at       timestamp         DEFAULT NULL,
    refresh_token_expires_at      timestamp         DEFAULT NULL,
    refresh_token_metadata        character varying DEFAULT NULL,
    user_code_value               character varying DEFAULT NULL,
    user_code_issued_at           timestamp         DEFAULT NULL,
    user_code_expires_at          timestamp         DEFAULT NULL,
    user_code_metadata            character varying DEFAULT NULL,
    device_code_value             character varying DEFAULT NULL,
    device_code_issued_at         timestamp         DEFAULT NULL,
    device_code_expires_at        timestamp         DEFAULT NULL,
    device_code_metadata          character varying DEFAULT NULL,
    PRIMARY KEY (id)
);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS oauth_client_details
(
    client_id               VARCHAR(255) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256),
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(256)
);
------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS oauth_code
(
    code           VARCHAR(256) PRIMARY KEY,
    authentication BYTEA
);
------------------------------------------------------------------------------------------------------------------------


ALTER TABLE receba.users
    DROP CONSTRAINT IF EXISTS pk_user_id,
    ADD CONSTRAINT pk_user_id PRIMARY KEY (id);

ALTER TABLE receba.users
    DROP CONSTRAINT IF EXISTS uq_user_email,
    ADD CONSTRAINT uq_user_email UNIQUE (email);

ALTER TABLE receba.users
    DROP CONSTRAINT IF EXISTS uq_user_phone,
    ADD CONSTRAINT uq_user_phone UNIQUE (phone);

------------------------------------------------------------------------------------------------------------------------
-- Posts Table
------------------------------------------------------------------------------------------------------------------------
ALTER TABLE receba.posts
    DROP CONSTRAINT IF EXISTS fk_posts_user_id,
    ADD CONSTRAINT fk_posts_user_id FOREIGN KEY (user_id) REFERENCES receba.users (id),
    DROP CONSTRAINT IF EXISTS pk_posts_id,
    ADD CONSTRAINT pk_posts_id PRIMARY KEY (id);

------------------------------------------------------------------------------------------------------------------------
-- Notifications Table
------------------------------------------------------------------------------------------------------------------------

ALTER TABLE receba.notifications
    DROP CONSTRAINT IF EXISTS pk_notification_id,
    ADD CONSTRAINT pk_notification_id PRIMARY KEY (id);

------------------------------------------------------------------------------------------------------------------------
-- Comments Table
------------------------------------------------------------------------------------------------------------------------

ALTER TABLE receba.comments
    DROP CONSTRAINT IF EXISTS pk_comments_id,
    ADD CONSTRAINT pk_comments_id PRIMARY KEY (id);

ALTER TABLE receba.comments
    DROP CONSTRAINT IF EXISTS fk_comment_parent_id,
    ADD CONSTRAINT fk_comment_parent_id FOREIGN KEY (parent) REFERENCES receba.comments (id),
    DROP CONSTRAINT IF EXISTS fk_comment_user_id,
    ADD CONSTRAINT fk_comment_user_id FOREIGN KEY (user_id) REFERENCES receba.users (id);

------------------------------------------------------------------------------------------------------------------------
-- Likes Table
------------------------------------------------------------------------------------------------------------------------

ALTER TABLE receba.likes
    DROP CONSTRAINT IF EXISTS pk_likes_id,
    ADD CONSTRAINT pk_likes_id PRIMARY KEY (id);

ALTER TABLE receba.likes
    DROP CONSTRAINT IF EXISTS fk_likes_comment_id,
    ADD CONSTRAINT fk_likes_comment_id FOREIGN KEY (comment_id) REFERENCES receba.comments (id),
    DROP CONSTRAINT IF EXISTS fk_likes_user_id,
    ADD CONSTRAINT fk_likes_user_id FOREIGN KEY (user_id) REFERENCES receba.users (id),
    DROP CONSTRAINT IF EXISTS fk_likes_post_id,
    ADD CONSTRAINT fk_likes_post_id FOREIGN KEY (post_id) REFERENCES receba.posts (id);

------------------------------------------------------------------------------------------------------------------------
-- Followers Table
------------------------------------------------------------------------------------------------------------------------

ALTER TABLE receba.followers
    DROP CONSTRAINT IF EXISTS pk_followers_id,
    ADD CONSTRAINT pk_followers_id PRIMARY KEY (id);

ALTER TABLE receba.followers
    DROP CONSTRAINT IF EXISTS fk_followers_user_id,
    ADD CONSTRAINT fk_followers_user_id FOREIGN KEY (user_id) REFERENCES receba.users (id);

------------------------------------------------------------------------------------------------------------------------
-- Roles Table
------------------------------------------------------------------------------------------------------------------------

ALTER TABLE receba.roles
    DROP CONSTRAINT IF EXISTS pk_roles_id,
    ADD CONSTRAINT pk_roles_id PRIMARY KEY (role_id);

------------------------------------------------------------------------------------------------------------------------
-- User Role Junction Table
------------------------------------------------------------------------------------------------------------------------

ALTER TABLE receba.user_role_junction
    DROP CONSTRAINT IF EXISTS pk_user_role_junction_id,
    ADD CONSTRAINT pk_user_role_junction_id PRIMARY KEY (role_id, user_id);

ALTER TABLE receba.user_role_junction
    DROP CONSTRAINT IF EXISTS fk_user_role_junction_user_id,
    ADD CONSTRAINT fk_user_role_junction_user_id FOREIGN KEY (user_id) REFERENCES receba.users (id),
    DROP CONSTRAINT IF EXISTS fk_user_role_junction_role_id,
    ADD CONSTRAINT fk_user_role_junction_role_id FOREIGN KEY (role_id) REFERENCES receba.roles (role_id);
------------------------------------------------------------------------------------------------------------------------