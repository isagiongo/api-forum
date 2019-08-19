INSERT INTO USUARIO (nome, email, senha) VALUES ('Isadora Giongo', 'isa@gmail.com', '123456');
INSERT INTO USUARIO (nome, email, senha) VALUES ('Veronica Torres', 'veronica@gmail.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO USUARIO (nome, email, senha) VALUES ('Joana Maranhão', 'joana@gmail.com', '$2a$10$eBs3HWRky0MFCB9uQzmpHOShbOyapH/8SUXC4nyia0NxAlnKAyMMi
');

INSERT INTO CURSO (nome, categoria) VALUES ('Java Básico', 'Desenvolvimento BackEnd');
INSERT INTO CURSO (nome, categoria) VALUES ('Angular', 'Desenvolvimento FrontEnd');
INSERT INTO CURSO (nome, categoria) VALUES ('Testes com JUnit', 'Desenvolvimento BackEnd');
INSERT INTO CURSO (nome, categoria) VALUES ('Selenium', 'Testes');

INSERT INTO TOPICO (titulo, mensagem, data_criacao, status, autor_id, curso_id)
VALUES ('Nullpointer exception', 'Minha aplicacao esta devolvendo null ao tentar incluir objeto', '2019-08-10 08:12:33', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO (titulo, mensagem, data_criacao, status, autor_id, curso_id)
VALUES ('Erro ao subir o server', 'Não consigo inicializar o server', '2019-02-12 15:12:33', 'NAO_RESPONDIDO', 1, 2);
INSERT INTO TOPICO (titulo, mensagem, data_criacao, status, autor_id, curso_id)
VALUES ('Mockar método', 'Não estou conseguindo mockar o método', '2019-12-10 22:12:33', 'NAO_RESPONDIDO', 1, 3);
INSERT INTO TOPICO (titulo, mensagem, data_criacao, status, autor_id, curso_id)
VALUES ('Buscar por id', 'Objeto não possui id para identificação', '2019-03-10 08:18:33', 'NAO_RESPONDIDO', 1, 4);

