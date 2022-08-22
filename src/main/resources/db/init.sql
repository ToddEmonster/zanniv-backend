--
-- MySQL Zanniv Script
--

/* ---------------------------------------------------------------
 * Creation des tables
 */
-- DROP TABLE `User`;
CREATE TABLE `User` (
                        `id_user` BIGINT,
                        `username` VARCHAR(255),
                        `password` VARCHAR(255),
                        `email` varchar(255),
                        PRIMARY KEY (`id_user`)
);

-- DROP TABLE `Birthday`;
CREATE TABLE `Birthday` (
                            `id_birthday` BIGINT,
                            `date` DATE,
                            `firstname` VARCHAR(255),
                            `lastname` VARCHAR(255),
                            `id_user` BIGINT,
                            PRIMARY KEY (`id_birthday`),
                            FOREIGN KEY (`id_user`)
                                REFERENCES `User`(`id_user`)
);

/* ---------------------------------------------------------------
 * Insertion d'un jeu de données initial
 */

INSERT INTO zanniv.`User`
VALUES
    (1, 'toddemon', 'todd', 'todd@hell.kitchen'),
    (2, 'matcha', 'webtoon', 'matilda@necro.mancer');

INSERT INTO zanniv.`Birthday`
VALUES
    (1, DATE("1996-06-04"), 'Todd', 'Raharivelo', 1),
    (2, DATE("1922-08-15"), 'Steven', 'Universe', 1),
    (3, DATE("1022-06-27"), 'Marceline', 'Vampire Queen', 1),
    (4, DATE("1998-03-14"), 'Finn', 'TheHuman', 2),
    (5, DATE("1222-10-16"), 'Bonnie', 'Bubblegum', 2),
    (6, DATE("1987-07-09"), 'Rebecca', 'Sugar', 2);
