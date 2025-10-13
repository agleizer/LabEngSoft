-- ============================================================
-- POPULATE: Caminho Fácil
-- Gera dados variados e realistas para testes do sistema
-- ============================================================

TRUNCATE TABLE public.relatorio_avaliacao, public.avaliacao, public.calcada, public.rua, public.relatorio RESTART IDENTITY CASCADE;

-- ============================================================
-- RUAS (12 principais para os testes)
-- ============================================================
INSERT INTO rua (nome, bairro) VALUES
('Avenida Paulista', 'Bela Vista'),
('Rua Augusta', 'Consolação'),
('Rua da Consolação', 'Consolação'),
('Rua Vergueiro', 'Liberdade'),
('Avenida Rebouças', 'Pinheiros'),
('Rua Joaquim Antunes', 'Pinheiros'),
('Rua dos Pinheiros', 'Pinheiros'),
('Rua Teodoro Sampaio', 'Pinheiros'),
('Rua Maria Antônia', 'Consolação'),
('Rua Piauí', 'Consolação'),
('Rua Major Sertório', 'Consolação'),
('Rua Dona Veridiana', 'Consolação');

-- ============================================================
-- CALÇADAS (duas por rua)
-- ============================================================
INSERT INTO calcada (avaliacao_media, latitude_ini, longitude_ini, latitude_fim, longitude_fim, nome, rua_id)
VALUES
-- Paulista
(4.3, -23.5651, -46.6529, -23.5650, -46.6535, 'Trecho 1', 1),
(3.9, -23.5647, -46.6539, -23.5643, -46.6543, 'Trecho 2', 1),

-- Augusta
(4.4, -23.5561, -46.6620, -23.5559, -46.6624, 'Trecho 1', 2),
(3.6, -23.5554, -46.6611, -23.5550, -46.6608, 'Trecho 2', 2),

-- Consolação
(4.1, -23.5520, -46.6470, -23.5515, -46.6468, 'Trecho 1', 3),
(3.4, -23.5525, -46.6460, -23.5521, -46.6456, 'Trecho 2', 3),

-- Vergueiro
(4.5, -23.5730, -46.6350, -23.5725, -46.6346, 'Trecho 1', 4),
(3.7, -23.5721, -46.6342, -23.5717, -46.6338, 'Trecho 2', 4),

-- Rebouças
(4.0, -23.5665, -46.6840, -23.5661, -46.6835, 'Trecho 1', 5),
(4.2, -23.5669, -46.6846, -23.5672, -46.6842, 'Trecho 2', 5),

-- Joaquim Antunes
(3.9, -23.5681, -46.6832, -23.5680, -46.6837, 'Trecho 1', 6),
(4.1, -23.5688, -46.6830, -23.5685, -46.6825, 'Trecho 2', 6),

-- Pinheiros
(4.3, -23.5675, -46.6830, -23.5671, -46.6826, 'Trecho 1', 7),
(3.8, -23.5668, -46.6842, -23.5665, -46.6847, 'Trecho 2', 7),

-- Teodoro
(4.0, -23.5679, -46.6822, -23.5675, -46.6818, 'Trecho 1', 8),
(3.6, -23.5670, -46.6824, -23.5666, -46.6821, 'Trecho 2', 8),

-- Maria Antônia
(4.2, -23.5490, -46.6525, -23.5487, -46.6521, 'Trecho 1', 9),
(3.9, -23.5493, -46.6529, -23.5490, -46.6525, 'Trecho 2', 9),

-- Piauí
(4.1, -23.5489, -46.6512, -23.5485, -46.6508, 'Trecho 1', 10),
(3.7, -23.5492, -46.6510, -23.5488, -46.6506, 'Trecho 2', 10),

-- Major Sertório
(3.8, -23.5483, -46.6497, -23.5479, -46.6493, 'Trecho 1', 11),
(4.0, -23.5486, -46.6495, -23.5482, -46.6491, 'Trecho 2', 11),

-- Dona Veridiana
(4.3, -23.5478, -46.6530, -23.5474, -46.6526, 'Trecho 1', 12),
(3.6, -23.5480, -46.6525, -23.5476, -46.6521, 'Trecho 2', 12);

-- ============================================================
-- AVALIAÇÕES (duas ou três por calçada, todas diferentes)
-- ============================================================
INSERT INTO avaliacao (comentario, data_aval, iluminacao_noturna, nota_cadeirante, nota_carrinho, nota_cego,
                       nota_geral, nota_idoso, presenca_piso_tatil, rebaixamento_guia, sem_obstaculos, calcada_id)
VALUES
-- Paulista
('Calçada larga e bem iluminada.', CURRENT_DATE, TRUE, 5, 5, 5, 5, 5, TRUE, TRUE, TRUE, 1),
('Piso tátil interrompido em frente ao shopping.', CURRENT_DATE, TRUE, 3, 4, 3, 3.5, 4, TRUE, TRUE, FALSE, 1),
('Degrau próximo ao metrô atrapalha.', CURRENT_DATE, TRUE, 3, 3, 3, 3, 3, FALSE, TRUE, FALSE, 2),

-- Augusta
('Muito movimento, mas boa acessibilidade.', CURRENT_DATE, TRUE, 4, 5, 4, 4.5, 5, TRUE, TRUE, TRUE, 3),
('Calçada estreita e cheia de postes.', CURRENT_DATE, TRUE, 2, 3, 3, 2.5, 3, FALSE, TRUE, FALSE, 4),
('Piso tátil novo e bem sinalizado.', CURRENT_DATE, TRUE, 5, 5, 4, 4.7, 5, TRUE, TRUE, TRUE, 4),

-- Consolação
('Bem cuidada, mas algumas rachaduras.', CURRENT_DATE, TRUE, 4, 4, 3, 3.8, 4, TRUE, TRUE, TRUE, 5),
('Pouca iluminação à noite.', CURRENT_DATE, FALSE, 3, 3, 3, 3, 3, TRUE, TRUE, FALSE, 6),

-- Vergueiro
('Boa largura e piso regular.', CURRENT_DATE, TRUE, 5, 5, 4, 4.5, 5, TRUE, TRUE, TRUE, 7),
('Trecho com buracos pequenos.', CURRENT_DATE, TRUE, 3, 3, 3, 3, 3, TRUE, TRUE, FALSE, 8),

-- Rebouças
('Excelente para cadeirantes.', CURRENT_DATE, TRUE, 5, 5, 5, 4.9, 5, TRUE, TRUE, TRUE, 9),
('Obstáculos próximos às árvores.', CURRENT_DATE, TRUE, 3, 3, 3, 3, 3, TRUE, FALSE, FALSE, 9),
('Boa iluminação e sinalização.', CURRENT_DATE, TRUE, 4, 4, 4, 4, 4, TRUE, TRUE, TRUE, 10),

-- Joaquim Antunes
('Rampa de esquina danificada.', CURRENT_DATE, TRUE, 3, 3, 3, 3, 3, TRUE, FALSE, TRUE, 11),
('Piso tátil completo e em ótimo estado.', CURRENT_DATE, TRUE, 5, 5, 5, 5, 5, TRUE, TRUE, TRUE, 12),

-- Pinheiros
('Boa iluminação, mas calçada estreita.', CURRENT_DATE, TRUE, 3, 4, 4, 3.5, 4, TRUE, TRUE, FALSE, 13),
('Bem conservada e segura.', CURRENT_DATE, TRUE, 5, 5, 5, 4.8, 5, TRUE, TRUE, TRUE, 14),

-- Teodoro Sampaio
('Trecho com obras e obstáculos temporários.', CURRENT_DATE, TRUE, 2, 3, 2, 2.5, 3, TRUE, FALSE, FALSE, 15),
('Reformada recentemente, excelente.', CURRENT_DATE, TRUE, 5, 5, 5, 5, 5, TRUE, TRUE, TRUE, 16),

-- Maria Antônia
('Boa manutenção, piso nivelado.', CURRENT_DATE, TRUE, 4, 4, 4, 4, 4, TRUE, TRUE, TRUE, 17),
('Árvores atrapalham passagem.', CURRENT_DATE, TRUE, 3, 3, 3, 3, 3, TRUE, TRUE, FALSE, 18),

-- Piauí
('Rampa suave e bem localizada.', CURRENT_DATE, TRUE, 5, 5, 4, 4.5, 5, TRUE, TRUE, TRUE, 19),
('Pouca iluminação.', CURRENT_DATE, FALSE, 3, 3, 3, 3, 3, TRUE, TRUE, FALSE, 20),

-- Major Sertório
('Piso tátil danificado.', CURRENT_DATE, TRUE, 3, 3, 3, 3, 3, FALSE, TRUE, FALSE, 21),
('Ótimo estado geral.', CURRENT_DATE, TRUE, 5, 5, 5, 5, 5, TRUE, TRUE, TRUE, 22),

-- Dona Veridiana
('Boa calçada, mas estreita.', CURRENT_DATE, TRUE, 4, 4, 3, 3.8, 4, TRUE, TRUE, TRUE, 23),
('Sem piso tátil, mas bem iluminada.', CURRENT_DATE, TRUE, 3, 3, 2, 2.8, 3, FALSE, TRUE, TRUE, 24);
