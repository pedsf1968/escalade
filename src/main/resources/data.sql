INSERT INTO user (id,  firstname, lastname, phone, email, alias, password, is_member, address_id)
VALUES (1,'Admin','ADMIN',null,'admin@escalade.org','ADMIN','$2a$10$IRKMI.Gzq83HyHvat5y0feUuxwYl5Eu22Rmr56q1WyVkiqv2vboSO', 1,1);
INSERT INTO user (id,  firstname, lastname, phone, email, alias, password, is_member, address_id)
VALUES (2,'Martin','DUPONT','0324593874','martin.dupont@gmail.com','dupont','$2a$10$TpRLNPP8Q1XJqONRvS4QI.ea2f5.jsB/.U4ZnL3iSyFiGZ6pBPHfe', 0,1);
INSERT INTO user (id,  firstname, lastname, phone, email, alias, password, is_member, address_id)
VALUES (3,'Paul','TINTIN','0654654874','paul.depres@hotmail.com','tintin','$2a$10$b/DpB2NSMWMokB.ZOP/D4ehRFqXRSx3fKib5BqgWqE8Ox9EaAm6Ty', 0,1);
-- insertion roles
INSERT INTO role(id,name)
VALUES (1,'ROLE_ADMIN');
INSERT INTO role(id,name)
VALUES (2,'ROLE_USER');
INSERT INTO user_roles VALUES (1,1);
INSERT INTO user_roles VALUES (2,2);
INSERT INTO user_roles VALUES (3,2);
-- insertion topo
INSERT INTO site (id, name, type, has_comment, photo_url, map_url) VALUES (1,'La falaise', 'TOPO', 0,'ailefroide.jpg','');
INSERT INTO topo (site_id, date, manager_id) VALUES (1, NOW(),1);
INSERT INTO site (id, name, type, has_comment, photo_url, map_url) VALUES (2,'Le Rock', 'TOPO', 0,'annot.jpg','');
INSERT INTO topo (site_id, date, manager_id) VALUES (2, NOW(),2);
INSERT INTO site (id, name, type, has_comment, photo_url, map_url) VALUES (3,'La gorge', 'TOPO', 0,'semiond.jpg','');
INSERT INTO topo (site_id, date, manager_id) VALUES (3, NOW(),3);
INSERT INTO site (id, name, type, has_comment, photo_url, map_url) VALUES (4,'Côté est', 'SECTEUR', 0,'autoire.jpg','');
INSERT INTO secteur (site_id, topo_id, equipement) VALUES (4, 1,'100m de corde');

