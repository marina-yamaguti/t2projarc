-- Inserções de Clientes
INSERT INTO clientes (codigo, nome, email) VALUES
    (1, 'João Silva', 'joao.silva@example.com'),
    (2, 'Maria Oliveira', 'maria.oliveira@example.com'),
    (3, 'Pedro Santos', 'pedro.santos@example.com'),
    (4, 'Ana Costa', 'ana.costa@example.com'),
    (5, 'Lucas Almeida', 'lucas.almeida@example.com'),
    (6, 'Fernanda Lima', 'fernanda.lima@example.com'),
    (7, 'Bruno Pereira', 'bruno.pereira@example.com'),
    (8, 'Juliana Ribeiro', 'juliana.ribeiro@example.com'),
    (9, 'Ricardo Gomes', 'ricardo.gomes@example.com'),
    (10, 'Tatiane Martins', 'tatiane.martins@example.com'),
    (11, 'Carlos Souza', 'carlos.souza@example.com');

-- Inserções de Aplicativos
INSERT INTO aplicativos (codigo, nome, custo_mensal) VALUES
    (1, 'App A', 9.99),
    (2, 'App B', 19.99),
    (3, 'App C', 29.99),
    (4, 'App D', 39.99),
    (5, 'App E', 49.99);

-- Inserções de Assinaturas
INSERT INTO assinaturas (inicio_vigencia, fim_vigencia, aplicativo_codigo, cliente_codigo) VALUES
    ('2024-01-01', '2025-01-01', 1, 1),
    ('2024-02-01', '2025-02-01', 2, 2),
    ('2024-03-01', '2025-03-01', 3, 3),
    ('2024-04-01', '2025-04-01', 4, 4),
    ('2024-05-01', '2025-05-01', 5, 5),
    ('2024-05-01', '2024-06-01', 5, 5),
    ('2024-06-01', '2025-06-01', 5, 6),
    ('2024-07-01', '2025-07-01', 5, 7);