-- APPROVED SPONSORS
INSERT INTO REGISTEREDUSER VALUES ('manuel@gmail.com', 'SPONSOR', '12345', 'C/ Malaga 12', 'Malaga', 'Spain', '123A', 'Manuel',   0, 'Lopez R', '60000000', 1, 0, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('luis@gmail.com',   'SPONSOR', '12345', 'C/ Malaga 13', 'Malaga', 'Spain', '234H', 'Luis',     0, 'Rodrigo', '60000001', 1, 1, 23406, null, null);
INSERT INTO REGISTEREDUSER VALUES ('alex@gmail.com',   'SPONSOR', '12345', 'C/ Malaga 14', 'Malaga', 'Spain', '423A', 'Alex',     0, 'Karvouniaris', '60000002', 1, 0, 23426, null, null);
INSERT INTO REGISTEREDUSER VALUES ('cris@gmail.com',   'SPONSOR', '12345', 'C/ Malaga 15', 'Malaga', 'Spain', '153B', 'Cristian', 0, 'Cardas', '60000003', 1, 1, 23452, null, null);
INSERT INTO REGISTEREDUSER VALUES ('miguel@gmail.com', 'SPONSOR', '12345', 'C/ Malaga 16', 'Malaga', 'Spain', '103F', 'Miguel',   0, 'Mejia', '60000004', 1, 0, 23453, null, null);
INSERT INTO REGISTEREDUSER VALUES ('diego@gmail.com',  'SPONSOR', '12345', 'C/ Malaga 17', 'Malaga', 'Spain', '273E', 'Diego',    0, 'Arroyo', '60000005', 1, 1, 23453, null, null);

-- ADMIN USER
INSERT INTO REGISTEREDUSER(TYPE, EMAIL, PASSWORD, WORKPLACE, ADMINGROUP) VALUES
                          ('ADMIN', 'admin@acoes.org', '12345', 'Honduras', 'ACOES');

-- OTHER USERS
INSERT INTO REGISTEREDUSER VALUES ('ana@gmail.com',     'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '789A', 'Ana',     1, 'Lopez', '60020020',     0, null, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('juan@gmail.com',    'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '987N', 'Juan',    0, 'Garcia', '60020700',    0, null, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('john@gmail.com',    'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '999B', 'John',    0, 'Perez', '60020080',     0, null, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('ricardo@gmail.com', 'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '222C', 'Ricardo', 0, 'Castillo', '60020400',  0, null, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('enrique@gmail.com', 'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '222D', 'Enrique', 0, 'Conejo', '60020000',    0, null, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('lola@gmail.com',    'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '222E', 'Lola',    1, 'Reviriego', '60920000', 0, null, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('maria@gmail.com',   'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '222F', 'Maria',   1, 'Rodriguez', '60020000', 0, null, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('adri@gmail.com',    'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '222G', 'Adrian',  0, 'Garcia', '60021000',    0, null, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('raul@gmail.com',    'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '222H', 'Raul',    0, 'Lopez', '60320000',     0, null, 23456, null, null);
INSERT INTO REGISTEREDUSER VALUES ('antonio@gmail.com', 'SPONSOR', '12345', 'C/ Malaga 10', 'Malaga', 'Spain', '222I', 'Antonio', 0, 'Merino', '60120000',    0, null, 23456, null, null);

-- NOTIFICATIONS
INSERT INTO NOTIFICATION VALUES (0, '2019-05-27 23:32:00', 0, 'ana@gmail.com');
INSERT INTO NOTIFICATION VALUES (1, '2019-02-13 12:23:00', 1, 'juan@gmail.com');
INSERT INTO NOTIFICATION VALUES (2, '2019-03-20 08:17:00', 0, 'john@gmail.com');
INSERT INTO NOTIFICATION VALUES (3, '2019-04-01 16:42:00', 1, 'ricardo@gmail.com');
INSERT INTO NOTIFICATION VALUES (4, '2019-03-08 17:36:00', 0, 'enrique@gmail.com');
INSERT INTO NOTIFICATION VALUES (5, '2019-04-22 15:20:00', 1, 'lola@gmail.com');
INSERT INTO NOTIFICATION VALUES (6, '2018-12-21 09:01:00', 0, 'maria@gmail.com');
INSERT INTO NOTIFICATION VALUES (7, '2019-01-07 11:07:00', 1, 'adri@gmail.com');
INSERT INTO NOTIFICATION VALUES (8, '2019-04-30 10:16:00', 0, 'raul@gmail.com');
INSERT INTO NOTIFICATION VALUES (9, '2019-05-01 22:28:00', 1, 'antonio@gmail.com');

-- PAYMENTS
INSERT INTO PAYMENT VALUES (0, 45, 'Donation', 'PayPal', '2019-01-07 11:07:00', 'john@gmail.com');
INSERT INTO PAYMENT VALUES (1, 4, 'Donation', 'Visa', '2019-03-12 18:07:00', 'ricardo@gmail.com');
INSERT INTO PAYMENT VALUES (2, 87, 'Donation', 'Visa', '2019-04-08 09:47:00', 'manuel@gmail.com');
INSERT INTO PAYMENT VALUES (3, 100, 'Subscription', 'Mastercard', '2019-03-01 12:00:00', 'miguel@gmail.com');
