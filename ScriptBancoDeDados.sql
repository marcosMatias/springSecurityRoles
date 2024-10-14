
----------------------------------------
-- TABELAS

-----------------------------------------
CREATE TABLE permissao(
    id_permissao         NUMBER(15)NOT NULL
    ,cd_permissao         VARCHAR2(150)NOT NULL
 
)
;

ALTER TABLE permissao
    ADD CONSTRAINT permissao_pk PRIMARY KEY(id_permissao)
        ;

ALTER TABLE permissao
    ADD CONSTRAINT permissao_uk1 UNIQUE(cd_permissao)
        ;



CREATE TABLE usuario(
    id_usuario           NUMBER(15)NOT NULL
    ,nm_usuario           VARCHAR2(200)NOT NULL
    ,nm_login             VARCHAR2(250)NOT NULL
    ,ds_senha             VARCHAR2(250)NOT NULL
    ,nm_empresa           VARCHAR2(200)
    ,ds_email             VARCHAR2(250)NOT NULL
    ,in_ativo             VARCHAR2(1)NOT NULL
  
)
;

ALTER TABLE usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY(id_usuario)
        ;

ALTER TABLE usuario
    ADD CONSTRAINT usuario_uk1 UNIQUE(nm_login
    ,ds_email)
        ;




CREATE TABLE permissao_usuario(
    id_permissao_usuario NUMBER(15)NOT NULL
    ,id_permissao         NUMBER(15)NOT NULL
    ,id_usuario           NUMBER(15)NOT NULL
    ,dt_criacao           DATE NOT NULL
    ,nm_usuario_criacao   VARCHAR2(100)NOT NULL
    ,dt_alteracao         DATE NOT NULL
    ,nm_usuario_alteracao VARCHAR2(100)NOT NULL
)
;

ALTER TABLE permissao_usuario
    ADD CONSTRAINT permissao_usuar_pk PRIMARY KEY(id_permissao_usuario)
        ;

ALTER TABLE permissao_usuario
    ADD CONSTRAINT permissao_usua_uk1 UNIQUE(id_permissao
    ,id_usuario)
        ;



ALTER TABLE permissao_usuario
    ADD CONSTRAINT permis_permiusu_fk FOREIGN KEY(id_permissao)
        REFERENCES permissao(id_permissao);

ALTER TABLE permissao_usuario
    ADD CONSTRAINT usuari_permiusu_fk FOREIGN KEY(id_usuario)
        REFERENCES usuario(id_usuario);
        

----------------------------------------
-- SEQUENCE

-----------------------------------------   



CREATE SEQUENCE PERMISSAO_S
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999999999
MINVALUE 1
NOCACHE;



CREATE SEQUENCE USUARIO_S
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999999999
MINVALUE 1
NOCACHE;


CREATE SEQUENCE PERMISSAO_USUARI_S
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999999999
MINVALUE 1
NOCACHE;





----------------------------------------
-- TRIGGERS

-----------------------------------------   





CREATE OR REPLACE TRIGGER PERMISSAO_S_TRG
 BEFORE INSERT OR UPDATE ON PERMISSAO
 FOR EACH ROW

BEGIN
  BEGIN
     --Populando a primary key
     IF INSERTING AND :new.ID_PERMISSAO IS NULL THEN
        SELECT PERMISSAO_S.nextval INTO :new.ID_PERMISSAO FROM DUAL;
     END IF;
     
     --
  END;
END PERMISSAO_S_TRG;
--

CREATE OR REPLACE TRIGGER USUARIO_S_TRG
 BEFORE INSERT OR UPDATE ON USUARIO
 FOR EACH ROW

  --
BEGIN
  BEGIN
     --Populando a primary key
     IF INSERTING AND :new.id_usuario IS NULL THEN
        SELECT USUARIO_S.nextval INTO :new.id_usuario FROM DUAL;
     END IF;
     
  END;
END USUARIO_S_TRG;



--
CREATE OR REPLACE TRIGGER PERMISS_USUA_S_TRG
 BEFORE INSERT OR UPDATE ON PERMISSAO_USUARIO
 FOR EACH ROW

  --
BEGIN
  BEGIN
     --Populando a primary key
     IF INSERTING AND :new.id_permissao_usuario IS NULL THEN
        SELECT PERMISSAO_USUARI_S.nextval INTO :new.id_permissao_usuario FROM DUAL;
     END IF;
 
     --
  END;
END PERMISS_USUA_S_TRG;
