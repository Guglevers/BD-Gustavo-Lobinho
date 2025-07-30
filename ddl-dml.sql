-- Sistema de Gerenciamento Escoteiro
-- Estrutura de banco de dados para controle de membros, especialidades e conquistas

-- Tabelas de referência básica
CREATE TABLE tipo_sanguineo (
    id_tipo_sanguineo INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(4) NOT NULL
);

CREATE TABLE area_de_conhecimento (
    id_area_de_conhecimento INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Tabela principal de pessoas
CREATE TABLE pessoa (
    id_pessoa INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    id_tipo_sanguineo INT,
    FOREIGN KEY (id_tipo_sanguineo) REFERENCES tipo_sanguineo(id_tipo_sanguineo)
);

-- Sistema de responsáveis e vínculos
CREATE TABLE responsavel (
    id_responsavel INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE vinculo (
    id_pessoa INT NOT NULL,
    id_responsavel INT NOT NULL,
    PRIMARY KEY (id_pessoa, id_responsavel),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
    FOREIGN KEY (id_responsavel) REFERENCES responsavel(id_responsavel)
);

-- Sistema de distintivos
CREATE TABLE distintivo (
    id_distintivo INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE desafio_distintivo (
    id_desafio_distintivo INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255),
    id_distintivo INT,
    FOREIGN KEY (id_distintivo) REFERENCES distintivo(id_distintivo)
);

CREATE TABLE desafio_de_distintivos_feitas (
    id_distintivo INT NOT NULL,
    id_pessoa INT NOT NULL,
    data_inicio DATE,
    data_fim DATE,
    PRIMARY KEY (id_distintivo, id_pessoa),
    FOREIGN KEY (id_distintivo) REFERENCES distintivo(id_distintivo),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);

-- Sistema de insígnias
CREATE TABLE insignia (
    id_insignia INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE desafio_insignia (
    id_desafios_insignia INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    id_insignia INT,
    FOREIGN KEY (id_insignia) REFERENCES insignia(id_insignia)
);

CREATE TABLE desafio_de_insignia_feita (
    id_insignia INT NOT NULL,
    id_pessoa INT NOT NULL,
    data DATE,
    PRIMARY KEY (id_insignia, id_pessoa),
    FOREIGN KEY (id_insignia) REFERENCES insignia(id_insignia),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);

-- Sistema de especialidades
CREATE TABLE especialidade (
    id_especialidade INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    id_area_de_conhecimento INT,
    FOREIGN KEY (id_area_de_conhecimento) REFERENCES area_de_conhecimento(id_area_de_conhecimento)
);

CREATE TABLE desafio_especialidade (
    id_desafios_especialidade INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255),
    id_especialidade INT,
    FOREIGN KEY (id_especialidade) REFERENCES especialidade(id_especialidade)
);

CREATE TABLE desafio_de_especialidade_feita (
    id_desafios_especialidade INT NOT NULL,
    id_pessoa INT NOT NULL,
    data DATE,
    PRIMARY KEY (id_desafios_especialidade, id_pessoa),
    FOREIGN KEY (id_desafios_especialidade) REFERENCES desafio_especialidade(id_desafios_especialidade),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);

-- Inserção de dados básicos do sistema

-- Tipos sanguíneos disponíveis
INSERT INTO tipo_sanguineo (tipo) VALUES
 ('O+'),
 ('O-'),
 ('A+'),
 ('A-'),
 ('B+'),
 ('B-'),
 ('AB+'),
 ('AB-');

-- Áreas de conhecimento escoteiro
INSERT INTO area_de_conhecimento (nome) VALUES
('Habilidade Escoteira'),
('Ciencia e Tecnologia'),
('Servico'),
('Cultura'),
('Desportos');

-- Cadastro de membros do grupo escoteiro
INSERT INTO pessoa (nome, data_nascimento, telefone, email, id_tipo_sanguineo) VALUES
('Ana Clara Ferreira', '2005-03-15', '11987654321', 'anaclara@email.com', 1),
('Bruno Henrique Santos', '2006-07-22', '11876543210', 'bruno@email.com', 3),
('Carla Rodrigues Silva', '2007-11-08', '11765432109', 'carla@email.com', 2),
('Daniel Costa Oliveira', '2008-02-14', '11654321098', 'daniel@email.com', 4),
('Eduardo Martins Lima', '2005-09-30', '11543210987', 'eduardo@email.com', 5),
('Fernanda Alves Souza', '2006-12-05', '11432109876', 'fernanda@email.com', 6),
('Gabriel Pereira Cruz', '2007-04-17', '11321098765', 'gabriel@email.com', 7),
('Helena Dias Barbosa', '2008-08-25', '11210987654', 'helena@email.com', 8);

-- Responsáveis pelos membros
INSERT INTO responsavel (nome, telefone, email) VALUES
('Maria José Ferreira', '11999888777', 'mariajose@email.com'),
('João Carlos Santos', '11888777666', 'joaocarlos@email.com'),
('Sandra Regina Silva', '11777666555', 'sandra@email.com'),
('Ricardo Costa Oliveira', '11666555444', 'ricardo@email.com'),
('Luiza Martins Lima', '11555444333', 'luiza@email.com'),
('Marcos Alves Souza', '11444333222', 'marcos@email.com'),
('Cristina Pereira Cruz', '11333222111', 'cristina@email.com'),
('Antonio Dias Barbosa', '11222111000', 'antonio@email.com');

-- Vínculos entre membros e responsáveis
INSERT INTO vinculo (id_pessoa, id_responsavel) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8);

-- Distintivos escoteiros
INSERT INTO distintivo (nome) VALUES
('Cruzeiro do Sul'),
('Distintivo Bronze'),
('Distintivo Prata');

-- Desafios dos distintivos
INSERT INTO desafio_distintivo (descricao, id_distintivo) VALUES
('Completar todas as etapas do programa educativo', 1),
('Demonstrar conhecimento em primeiros socorros', 2),
('Liderar uma atividade de grupo', 3);

-- Distintivos conquistados pelos membros
INSERT INTO desafio_de_distintivos_feitas (id_distintivo, id_pessoa, data_inicio, data_fim) VALUES
(2, 1, '2024-01-15', '2024-03-20'),
(2, 2, '2024-02-10', '2024-04-15'),
(2, 3, '2024-03-05', '2024-05-10'),
(2, 4, '2024-04-12', '2024-06-18'),
(2, 5, '2024-05-08', '2024-07-12'),
(2, 6, '2024-06-20', '2024-08-25'),
(2, 7, '2024-07-15', '2024-09-20'),
(3, 1, '2024-09-01', '2024-11-15'),
(1, 1, '2024-12-01', '2024-12-31');

-- Insígnias especiais
INSERT INTO insignia (nome) VALUES
('Insígnia Campeões da Natureza'),
('Insígnia do Aprender'),
('Insígnia da Boa Ação'),
('Insígnia Reduzir, Reciclar, Reutilizar'),
('Insígnia Escoteiros pela Energia Solar'),
('Insígnia da Lusofonia'),
('Insígnia Cone Sul');

-- Desafios das insígnias
INSERT INTO desafio_insignia (nome, id_insignia) VALUES
('Desenvolver projeto de conservação ambiental', 1),
('Completar curso de educação continuada', 2),
('Realizar 50 boas ações documentadas', 3),
('Implementar programa de reciclagem', 4),
('Construir dispositivo solar funcional', 5),
('Participar de intercâmbio cultural lusófono', 6),
('Colaborar em projeto internacional sul-americano', 7);

-- Insígnias conquistadas
INSERT INTO desafio_de_insignia_feita (id_insignia, id_pessoa, data) VALUES
(1, 3, '2024-04-22'),
(2, 1, '2024-05-18'),
(3, 7, '2024-06-30'),
(4, 5, '2024-08-15'),
(5, 2, '2024-09-10'),
(6, 4, '2024-10-25'),
(7, 6, '2024-11-12');

-- Especialidades por área de conhecimento
INSERT INTO especialidade (nome, id_area_de_conhecimento) VALUES
('Fogo de Conselho', 1),
('Acampamento', 1),
('Culinaria Mateira', 1),
('Ferramenta de Corte', 1),
('Historia do Escotismo', 1),
('Cidadania do Mundo', 1),
('Almoxarifado', 1),
('Acampamento de Minimo Impacto', 1),
('Culinaria', 1),
('Astronomia', 2),
('Anatomia Humana', 2),
('Aeromodelismo', 2),
('Aquariofilia', 2),
('Arqueologia', 2),
('Arquitetura', 2),
('Astronautica', 2),
('Animais Peçonhentos', 2),
('Alfabetizacao', 3),
('Administracao', 3),
('Agricultura', 3),
('Animacao da Fe', 3),
('Aquicultura', 3),
('Arte Digital', 3),
('Baba', 3),
('Barismo', 3),
('Biblioteconomia', 3),
('Canto', 4),
('Arte em Origami', 4),
('Artes Cenicas', 4),
('Artes Graficas', 4),
('Artesanato', 4),
('Bateria', 4),
('Budismo', 4),
('Anime', 4),
('Arte da Marinharia', 4),
('Corrida de Rua', 5),
('Basquetebol', 5),
('Arco e Flecha', 5),
('Capoeira', 5),
('Artes Marciais', 5),
('Boxe', 5),
('Ciclismo', 5),
('Corrida de Orientacao', 5),
('Canoagem', 5);

-- Desafios das especialidades
INSERT INTO desafio_especialidade (descricao, id_especialidade) VALUES
('Organizar e conduzir fogo de conselho completo', 1),
('Montar acampamento seguindo normas de segurança', 2),
('Preparar refeição completa em fogareiro a lenha', 3),
('Demonstrar uso seguro de ferramentas cortantes', 4),
('Apresentar palestra sobre Baden-Powell', 5),
('Elaborar projeto sobre direitos humanos', 6),
('Organizar sistema de controle de materiais', 7),
('Realizar acampamento sem deixar rastros', 8),
('Preparar banquete para 20 pessoas', 9),
('Identificar constelações do hemisfério sul', 10),
('Explicar funcionamento de sistemas corporais', 11),
('Construir e fazer voar aeromodelo', 12),
('Manter aquário marinho por 6 meses', 13),
('Conduzir escavação arqueológica simulada', 14),
('Projetar casa sustentável', 15),
('Estudar programa espacial brasileiro', 16),
('Criar guia de animais peçonhentos regionais', 17),
('Ensinar leitura para adultos', 18),
('Gerenciar projeto social comunitário', 19),
('Cultivar horta orgânica', 20),
('Organizar celebração inter-religiosa', 21),
('Desenvolver sistema de piscicultura', 22),
('Criar exposição digital interativa', 23),
('Cuidar de grupo infantil por uma semana', 24),
('Preparar diferentes tipos de café especial', 25),
('Catalogar acervo de biblioteca', 26),
('Apresentar recital de música popular', 27),
('Ensinar técnicas avançadas de origami', 28),
('Dirigir peça teatral', 29),
('Produzir revista em quadrinhos', 30),
('Criar linha de produtos artesanais', 31),
('Formar banda e apresentar show', 32),
('Ministrar curso sobre filosofia budista', 33),
('Organizar festival de anime', 34),
('Construir réplica funcional de embarcação', 35),
('Completar maratona de 42km', 36),
('Integrar equipe competitiva de basquete', 37),
('Participar de campeonato de tiro com arco', 38),
('Ensinar capoeira para iniciantes', 39),
('Competir em torneio de artes marciais', 40),
('Treinar para competição de boxe', 41),
('Participar de travessia ciclística', 42),
('Completar percurso de orientação noturna', 43),
('Navegar em rio por travessia de 50km', 44);

-- Especialidades conquistadas pelos membros
INSERT INTO desafio_de_especialidade_feita (id_desafios_especialidade, id_pessoa, data) VALUES
(1, 1, '2024-02-14'),
(15, 1, '2024-05-20'),
(28, 1, '2024-08-10'),
(36, 1, '2024-11-25'),
(12, 1, '2024-12-15'),
(11, 2, '2024-03-08'),
(13, 2, '2024-06-12'),
(14, 2, '2024-09-05'),
(16, 2, '2024-12-20'),
(2, 3, '2024-01-30'),
(21, 3, '2024-07-18'),
(19, 4, '2024-04-25'),
(17, 4, '2024-10-08'),
(8, 5, '2024-05-15'),
(8, 6, '2024-02-28'),
(40, 6, '2024-09-22'),
(18, 7, '2024-06-05'),
(43, 7, '2024-11-10');