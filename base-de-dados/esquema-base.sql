CREATE TABLE tbl_time (
  id_time SERIAL PRIMARY KEY,
  nm_time VARCHAR(255) NOT NULL,
  dt_time DATE NOT NULL
);

CREATE TABLE tbl_integrante (
  id_integrante SERIAL PRIMARY KEY,
  nm_integrante VARCHAR(255) NOT NULL,
  it_funcao VARCHAR(255) NOT NULL,
  it_franquia VARCHAR(255) NOT NULL
);

CREATE TABLE tbl_composicao_time (
  id_cp_time SERIAL PRIMARY KEY,
  fk_time INTEGER NOT NULL,
  fk_integrante INTEGER NOT NULL,
  FOREIGN KEY (fk_time) REFERENCES tbl_time (id_time),
  FOREIGN KEY (fk_integrante) REFERENCES tbl_integrante (id_integrante)
);
