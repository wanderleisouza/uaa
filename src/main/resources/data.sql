INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('app-mobile', '{bcrypt}$2y$10$a79TfSe3l9rsGqEJMxstFOyCo/49P1fwPmlvhWBbS8Fgr8COlg0PW', 'http://localhost:8080/code', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'client_credentials,password,refresh_token', '{}');
INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information) VALUES ('tc', '{bcrypt}$2y$10$.WtpvjJeu.exrCSabWhhcOhMa3mEPJTCLMQ9j7sFacJ6suMtySkZS', 'http://localhost:8080/code', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'client_credentials,password,refresh_token', '{}');

INSERT INTO PERMISSION (NAME) VALUES
 ('can_create'),
 ('can_read'),
 ('can_update'),
 ('can_delete');

INSERT INTO role (NAME) VALUES
		('ROLE_USER'),('ROLE_CUSTOMER_HAPPINESS'),('ROLE_ADMIN');

INSERT INTO PERMISSION_ROLE (PERMISSION_ID, ROLE_ID) VALUES
     (1,3), /* create -> admin */
     (1,1), /* create -> user */
     (2,1), /* read -> all */
     (2,2),  
     (2,3),
     (3,1), /* update -> all */
     (3,2),
     (3,3),
     (4,3); /* delete -> admin */
     
insert into user (id, username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('1', 'jane-doe','{bcrypt}$2y$10$KJqQfEwLL3G.dcyMb0xZqeUKGv0cL0.NZN5DwVsNyRfa4Mjm/qYlS', 'jane@mail.com', '1', '1', '1', '1');
insert into user (id, username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('2', 'john-doe','{bcrypt}$2y$10$3qO2b9/onOhP3UW8LE9T7euvP4gWX4fqF.Gf7Z86NOnaVXf8CV3Au', 'john@mail.com', '1', '1', '1', '1');
insert into user (id, username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('3', 'judite','{bcrypt}$2y$10$MXXgXo51qxETRwhjBuLGOOlPaZgCslwwSqfRRm1zA7Pdro00/Oxpy', 'judite@mail.com', '1', '1', '1', '1');
insert into user (id, username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('4', 'chuck-norris','{bcrypt}$2y$10$xKQLivLpDXRqTHsYuNJVZemfp5P5rfZ5kFbvovxigESNWFH3mFYzy', 'chuck@mail.com', '1', '1', '1', '1');

INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
    VALUES
    (1, 1), /* user -> jane-doe */
    (1, 2), /* user -> john-doe */
    (2, 3), /* customer_happiness -> judite */
    (3, 4); /* admin -> chuck_norriss */