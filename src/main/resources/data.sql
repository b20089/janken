/*INSERT INTO chamber (userName,chamberName) VALUES ('ほんだ','秘密の小部屋');
INSERT INTO chamber (userName,chamberName) VALUES ('いがき','1602');
INSERT INTO chamber (userName,chamberName) VALUES ('ふくやす','マリーナシティ');
INSERT INTO userinfo (userName,age,height) VALUES ('いがき',30,176.1);
INSERT INTO userinfo (userName,age,height) VALUES ('ほんだ',18,172.3);
INSERT INTO userinfo (userName,age,height) VALUES ('ふくやす',NULL,199.9);
*/

INSERT INTO users (name) VALUES 'CPU';
INSERT INTO users (name) VALUES 'ほんだ';
INSERT INTO users (name) VALUES 'いがき';
INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (2, 1, 'Gu', 'Choki', 'FALSE');
INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (2, 1, 'Gu', 'Gu', 'FALSE');
INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (2, 1, 'Gu', 'Pa', 'FALSE');
INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (1, 2, 'Gu', 'FALSE');
INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (2, 3, 'Choki', 'FALSE');
