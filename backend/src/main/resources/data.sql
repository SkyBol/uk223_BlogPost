--USERS
insert into users(id, email,first_name,last_name, password)
values ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'admin@example.com', 'James','Bond', '1234' ),
       ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'user@example.com', 'Tyler','Durden', '1234'),
       ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'uuser@ex.com', 'Unk','Unauthorized', '1234')
ON CONFLICT DO NOTHING;


--ROLES
INSERT INTO role(id, name)
VALUES ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', 'USER'),
       ('80b6d9b4-9c7b-4e2f-bdb4-36566c6be3eb', 'ADMIN'),
       ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', 'VERIFIED')
ON CONFLICT DO NOTHING;

--AUTHORITIES
INSERT INTO authority(id, name)
VALUES ('2ebf301e-6c61-4076-98e3-2a38b31daf86', 'USER_CREATE'),
       ('a1055078-1de4-4735-b8a1-aff0cc55bdd8', 'USER_READ'),
       ('0d668f10-7d94-4e8d-b67d-9ef270dbf0f1', 'USER_UPDATE'),
       ('2f0dff88-5e11-4bb4-9e28-4e2f6e71fff9', 'USER_DELETE'),
       ('37950ab8-6c13-443a-a949-3d19a4d94389', 'BLOGPOST_CREATE'),
       ('186f449a-4bd7-438b-ada1-67e2666942b5', 'BLOGPOST_READ'),
       ('412399ec-d50c-4e9f-9a82-abd4acf6a333', 'BLOGPOST_UPDATE'),
       ('02219556-9aee-4973-a1fb-61dbbe05a3c9', 'BLOGPOST_DELETE')
ON CONFLICT DO NOTHING;

--assign roles to users
insert into users_role(users_id, role_id)
values ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'd29e709c-0ff1-4f4c-a7ef-09f656c390f1'),
       ('ba804cb9-fa14-42a5-afaf-be488742fc54', '80b6d9b4-9c7b-4e2f-bdb4-36566c6be3eb'),
       ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'd29e709c-0ff1-4f4c-a7ef-09f656c390f1')
ON CONFLICT DO NOTHING;

--assign authorities to roles
INSERT INTO role_authority(role_id, authority_id)
VALUES -- User --
       ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '2ebf301e-6c61-4076-98e3-2a38b31daf86'),
       ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', 'a1055078-1de4-4735-b8a1-aff0cc55bdd8'),
       ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '0d668f10-7d94-4e8d-b67d-9ef270dbf0f1'),
       ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '2f0dff88-5e11-4bb4-9e28-4e2f6e71fff9'),
       ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '37950ab8-6c13-443a-a949-3d19a4d94389'),
       ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '186f449a-4bd7-438b-ada1-67e2666942b5'),
       ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '412399ec-d50c-4e9f-9a82-abd4acf6a333'),
       ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '02219556-9aee-4973-a1fb-61dbbe05a3c9')
ON CONFLICT DO NOTHING;