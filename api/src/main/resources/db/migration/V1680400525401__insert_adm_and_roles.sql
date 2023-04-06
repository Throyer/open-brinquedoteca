-- insert admin
INSERT INTO "usuario"
  ("nome", "sobrenome", "email", "senha")
VALUES
  ('admin', 'ADM', 'admin@email.com', '$2a$10$QBuMJLbVmHzgvTwpxDynSetACNdCBjU5zgo.81RWEDzH46aUrgcNK');

-- insert roles
INSERT INTO "cargo"
  ("nome")
VALUES
  ('ADMINISTRADOR'),
  ('CURADOR');

-- put roles into admin
INSERT INTO "usuario_cargo"
  ("usuario_id", "cargo_id")
VALUES
  (
    (SELECT id FROM "usuario" WHERE "email" = 'admin@email.com'),
    (SELECT id FROM "cargo" WHERE "nome" = 'ADMINISTRADOR')
  ),
  (
    (SELECT id FROM "usuario" WHERE "email" = 'admin@email.com'),
    (SELECT id FROM "cargo" WHERE "nome" = 'CURADOR')
  );