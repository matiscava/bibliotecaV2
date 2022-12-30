-- # Creamos Usuarios
    INSERT INTO biblioteca.user (`id`,`address`, `age`, `dni`, `email`, `lastname`, `max_book`, `name`, `password`, `phone`)
        VALUES (1,'Av. Cabildo 123', 23, 234563212, 'jose@mail.com', 'Perez', 2, 'Jose', 'jose123', 11436985);
    INSERT INTO biblioteca.user (`id`,`address`, `age`, `dni`, `email`, `lastname`, `max_book`, `name`, `password`, `phone`)
        VALUES (2,'Av. Siempreviva 456', 45, 11468963, 'hsimpson@mail.com', 'Simpson', 2, 'Homero', 'homero123', 117258963);
    INSERT INTO biblioteca.user (`id`,`address`, `age`, `dni`, `email`, `lastname`, `max_book`, `name`, `password`, `phone`)
        VALUES (3,'Calle False 123', 36, 11468964, 'lmessi@mail.com', 'Messi', 3, 'Lionel', 'messi123', 11257436);

-- # Creamos Admins
    INSERT INTO biblioteca.admin (`id`, `dni`, `email`, `lastname`, `name`, `password`, `phone`)
        VALUES (1, '33442211', 'matiscava@mail.com', 'Scavarelli', 'Matias', 'mati123', 1122334455);
    INSERT INTO biblioteca.admin (`id`, `dni`, `email`, `lastname`, `name`, `password`, `phone`)
        VALUES (2, '15247896', 'admin@mail.com', 'Scaloni', 'Lionel', 'scaloneta', 1169874532);

-- # Creamos Autores

    INSERT INTO biblioteca.author (`id`, `born_date`, `death_date`, `info`, `lastname`, `name`)
        VALUES (1, '1265-01-01', '1321-09-14', 'Dante Alighieri, bautizado Durante di Alighiero degli Alighieri, fue un poeta y escritor italiano, conocido por escribir la Divina comedia', 'Alighieri', 'Dante');
    INSERT INTO biblioteca.author (`id`, `born_date`, `death_date`, `info`, `lastname`, `name`)
        VALUES (2, '1947-09-21', null, 'Escritor estadounidense. Se ganó el favor de la crítica con su primera novela, Carrie (1974).', 'King', 'Stephen');
    INSERT INTO biblioteca.author (`id`, `born_date`, `death_date`, `info`, `lastname`, `name`)
        VALUES (3, '1965-07-31', null, 'Joanne Rowling (Yate, 31 de julio de 1965), quien escribe bajo los seudónimos de J. K. Rowling y Robert Galbraith, es una escritora, productora de cine y guionista británica, conocida por ser la autora de la serie de libros Harry Potter.', 'Rowling', 'J.K.');

-- # Creamos Titulos

    INSERT INTO biblioteca.title (`id`, `title`, `author_id`) VALUES (1, 'La Divina Comedia',1);
    INSERT INTO biblioteca.title (`id`, `title`, `author_id`) VALUES (2, 'IT',2);
    INSERT INTO biblioteca.title (`id`, `title`, `author_id`) VALUES (3, 'Harry Potter y la piedra filosofal',3);
    INSERT INTO biblioteca.title (`id`, `title`, `author_id`) VALUES (4, 'Animales Fantasticos y donde encontrarlos',3);

-- # Creamos Libros
    INSERT INTO biblioteca.book (`id`, `edition`, `editorial`, `enabled`, `year`, `title_id`)
        VALUES (1, 4, 'Terramar', TRUE, 2010, 1);
    INSERT INTO biblioteca.book (`id`, `edition`, `editorial`, `enabled`, `year`, `title_id`)
        VALUES (2, 2, 'ABADA', TRUE, 2021, 1);
    INSERT INTO biblioteca.book (`id`, `edition`, `editorial`, `enabled`, `year`, `title_id`)
        VALUES (3, 9, 'Salamandra', TRUE, 2016, 3);
    INSERT INTO biblioteca.book (`id`, `edition`, `editorial`, `enabled`, `year`, `title_id`)
        VALUES (4, 2, 'Debolsillo', TRUE, 2017, 2);
    INSERT INTO biblioteca.book (`id`, `edition`, `editorial`, `enabled`, `year`, `title_id`)
        VALUES (5, 3, 'Debolsillo', TRUE, 2018, 2);
    INSERT INTO biblioteca.book (`id`, `edition`, `editorial`, `enabled`, `year`, `title_id`)
            VALUES (6, 2, 'Salamandra', TRUE, 2022, 4);

-- # Creamos algunos tickets viejos
    INSERT INTO biblioteca.ticket (`id`, `is_returned`, `take_out_date`, `book_id`, `user_id`) VALUES (1, TRUE, '2022-11-21', 1, 1);
    INSERT INTO biblioteca.ticket (`id`, `is_returned`, `take_out_date`, `book_id`, `user_id`) VALUES (2, TRUE, '2022-07-01', 5, 1);
    INSERT INTO biblioteca.ticket (`id`, `is_returned`, `take_out_date`, `book_id`, `user_id`) VALUES (3, TRUE, '2022-08-05', 3, 2);
    INSERT INTO biblioteca.ticket (`id`, `is_returned`, `take_out_date`, `book_id`, `user_id`) VALUES (4, TRUE, '2022-03-20', 4, 3);





