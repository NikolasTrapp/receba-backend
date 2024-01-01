------------------------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS receba.oauth2_registered_client
(
    id                            varchar(100)                            NOT NULL,
    client_id                     varchar(100)                            NOT NULL,
    client_id_issued_at           timestamp     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 varchar(200)                            DEFAULT NULL,
    client_secret_expires_at      timestamp                               DEFAULT NULL,
    client_name                   varchar(200)                            NOT NULL,
    client_authentication_methods varchar(1000)                           NOT NULL,
    authorization_grant_types     varchar(1000)                           NOT NULL,
    redirect_uris                 varchar(1000)                           DEFAULT NULL,
    scopes                        varchar(1000)                           NOT NULL,
    client_settings               varchar(2000)                           NOT NULL,
    token_settings                varchar(2000)                           NOT NULL,
    PRIMARY KEY (id)
);
------------------------------------------------------------------------------------------------------------------------

INSERT INTO receba.oauth2_registered_client
(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name,
 client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
VALUES ('f3f7bcf7-003c-4892-8a4b-cb6700fef787', 'receba-frontend', '2023-12-28 18:27:45.973',
        '$2a$10$DAVagDezQ57QPSHjov4f1eAXLijCWOZsXQFiDbgMNfMwc2xCPDU4.', NULL, 'f3f7bcf7-003c-4892-8a4b-cb6700fef787',
        'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/authorized', 'READ,WRITE',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.core.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000]}');