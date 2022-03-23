INSERT INTO anonymous_users (id) VALUES ('a0000000000000000000000000000000'), ('a1111111111111111111111111111111'), ('a2222222222222222222222222222222'), ('a3333333333333333333333333333333'), ('a4444444444444444444444444444444'), ('a5555555555555555555555555555555'), ('a6666666666666666666666666666666'), ('a7777777777777777777777777777777'), ('a8888888888888888888888888888888');
INSERT INTO users (id, email) VALUES ('a3333333333333333333333333333333', 'leopold.hubert.etu@univ-lille.fr'), ('a4444444444444444444444444444444', 'maxime.boutry.etu@univ-lille.fr'), ('a5555555555555555555555555555555', 'quentin.dubois.etu@univ-lille.fr'), ('a6666666666666666666666666666666', 'baptiste.momut.etu@univ-lille.fr'), ('a7777777777777777777777777777777', 'mohamed.bourdim.etu@univ-lille.fr'), ('a8888888888888888888888888888888', 'jean.heysen.etu@univ-lille.fr');
INSERT INTO accounts (id, password) VALUES ('a3333333333333333333333333333333', '$2a$10$/4JlSJLX3m5eQV.7RYfhS.cvpj6dj6T0jysft/kAouHO0M8NfpIPS'), ('a4444444444444444444444444444444', '$2a$10$/4JlSJLX3m5eQV.7RYfhS.cvpj6dj6T0jysft/kAouHO0M8NfpIPS'), ('a5555555555555555555555555555555', '$2a$10$/4JlSJLX3m5eQV.7RYfhS.cvpj6dj6T0jysft/kAouHO0M8NfpIPS');
INSERT INTO events (id, title, description) VALUES ('b0000000000000000000000000000000', 'event1', 'super event1!'), ('b1111111111111111111111111111111', 'event2', 'super event2!'), ('b2222222222222222222222222222222', 'event3', 'super event3!');
INSERT INTO participes (user_id, event_id, username, owner) VALUES ('a3333333333333333333333333333333', 'b0000000000000000000000000000000', 'Hubert Léopold', 'true'), ('a4444444444444444444444444444444', 'b1111111111111111111111111111111', 'Boutry Maxime', 'true'), ('a3333333333333333333333333333333', 'b1111111111111111111111111111111', 'Léo', 'false'), ('a3333333333333333333333333333333', 'b2222222222222222222222222222222', 'Burn', 'true');
INSERT INTO entries (id, user_id, event_id, amount) VALUES ('c0000000000000000000000000000000', 'a4444444444444444444444444444444', 'b1111111111111111111111111111111', 150), ('c1111111111111111111111111111111', 'a3333333333333333333333333333333', 'b1111111111111111111111111111111', 20), ('c2222222222222222222222222222222', 'a3333333333333333333333333333333', 'b1111111111111111111111111111111', 200);