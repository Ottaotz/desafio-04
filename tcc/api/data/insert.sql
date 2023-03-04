INSERT INTO usuario (nome, email, apelido, data_nascimento, senha, imagem_perfil, ativo)
VALUES ('John Doe', 'johndoe@example.com', 'johnd', '1990-01-01', 'password123', 'https://lh3.googleusercontent.com/a-/AAuE7mDlbqjs7q5r05ijcnGPxmHJ4pWc4CUyyVgXPVU3', true);

INSERT INTO usuario (nome, email, apelido, data_nascimento, senha, imagem_perfil, ativo)
VALUES ('Victor Constantine', 'vicConst@example.com', 'vcC', '1990-01-01', 'password123', 'https://images.fineartamerica.com/images/artworkimages/mediumlarge/3/cat-craziness-cute-kawaii-adorable-funny-cool-kitty-cutesy-japanese-random-awesome-trippy-weird-rahim-rd.jpg', true);

INSERT INTO usuario (nome, email, apelido, data_nascimento, senha, imagem_perfil, ativo)
VALUES ('Emma Zapson', 'emmaZ@example.com', 'iLvCts', '1990-01-01', 'password123', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6AQC0Q8K5lJV6zw4AJd2YvnwkKg12uUgSeA&usqp=CAU', true);

INSERT INTO post (usuario_id, imagem, legenda, total_curtidas, data_postagem, visualizacao)
VALUES
	(1, 'https://media.tenor.com/_k0gzG8pCXsAAAAC/gatinho-gatinho-cobertor.gifg', 'This is a caption', 10, '2022-01-01 10:00:00', 'PUBLICO'),
	(1, 'https://seres.vet/blog/wp-content/uploads/2020/10/gato-vomitando-bola-de-pelo-2.jpg', 'Another caption', 5, '2022-02-01 10:00:00', 'PUBLICO'),
	(2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTffoQE6xhKmu7dhs6BxYYQSpNdahUzSbKjYAnX8S2wFxsjcO09ODomODEsjlSRtzMZJNQ&usqp=CAU', 'Yet another caption', 20, '2022-03-01 10:00:00', 'PUBLICO');
