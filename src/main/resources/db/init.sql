--
-- MySQL Zanniv Script
--
/* ---------------------------------------------------------------
 * Suppression des tables
 */

SET foreign_key_checks = 0;
DROP TABLE `User`;
DROP TABLE `Birthday`;
SET foreign_key_checks = 1;

/* ---------------------------------------------------------------
 * Creation des tables
 */
-- DROP TABLE `User`;
CREATE TABLE `User` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255)  NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);

-- DROP TABLE `Birthday`;
CREATE TABLE `Birthday` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `date` DATE NOT NULL,
    `firstname` VARCHAR(255) NOT NULL,
    `lastname` VARCHAR(255),
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`)
        REFERENCES `User`(`id`)
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
    (4, DATE("1998-03-14"), 'Finn', 'TheHuman', 4),
    (5, DATE("1222-10-16"), 'Bonnie', 'Bubblegum', 4),
    (6, DATE("1987-07-09"), 'Rebecca', 'Sugar', 4);
