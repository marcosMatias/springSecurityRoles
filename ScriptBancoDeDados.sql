/*
-- DROP
DROP TRIGGER permissao_s_trg;
DROP TRIGGER usuario_s_trg;
DROP TRIGGER permiss_usua_s_trg;
DROP TRIGGER monitor_aces_s_trg;

DROP SEQUENCE xxmcc.permissao_s;
DROP SEQUENCE xxmcc.usuario_s;
DROP SEQUENCE xxmcc.permissao_usuari_s;
DROP SEQUENCE xxmcc.monitor_acesso_s;

DROP TABLE xxmcc.permissao_usuario;
DROP TABLE xxmcc.usuario;
DROP TABLE xxmcc.permissao;
DROP TABLE xxmcc.monitor_acesso;

*/
----------------------------------------
-- TABELAS

-----------------------------------------
CREATE TABLE xxmcc.permissao(
    id_permissao         NUMBER(15)NOT NULL
    ,cd_permissao         VARCHAR2(150)NOT NULL
    ,dt_criacao           DATE NOT NULL
    ,nm_usuario_criacao   VARCHAR2(100) NOT NULL
    ,dt_alteracao         DATE NOT NULL
    ,nm_usuario_alteracao VARCHAR2(100) NOT NULL
 
)
;

ALTER TABLE xxmcc.permissao
    ADD CONSTRAINT permissao_pk PRIMARY KEY(id_permissao)
        ;

ALTER TABLE xxmcc.permissao
    ADD CONSTRAINT permissao_uk1 UNIQUE(cd_permissao)
        ;



CREATE TABLE xxmcc.usuario(
    id_usuario           NUMBER(15)NOT NULL
    ,nm_usuario           VARCHAR2(200)NOT NULL
    ,nm_login             VARCHAR2(250)NOT NULL
    ,ds_senha             VARCHAR2(250)NOT NULL
    ,nm_empresa           VARCHAR2(200)
    ,ds_email             VARCHAR2(250)NOT NULL
    ,in_ativo             VARCHAR2(1)NOT NULL
    ,dt_criacao           DATE NOT NULL
    ,nm_usuario_criacao   VARCHAR2(100) NOT NULL
    ,dt_alteracao         DATE NOT NULL
    ,nm_usuario_alteracao VARCHAR2(100) NOT NULL
  
)
;

ALTER TABLE xxmcc.usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY(id_usuario)
        ;

ALTER TABLE xxmcc.usuario
    ADD CONSTRAINT usuario_uk1 UNIQUE(nm_login
    ,ds_email)
        ;




CREATE TABLE xxmcc.permissao_usuario(
    id_permissao_usuario NUMBER(15)NOT NULL
    ,id_permissao         NUMBER(15)NOT NULL
    ,id_usuario           NUMBER(15)NOT NULL
    ,dt_criacao           DATE NOT NULL
    ,nm_usuario_criacao   VARCHAR2(100)NOT NULL
    ,dt_alteracao         DATE NOT NULL
    ,nm_usuario_alteracao VARCHAR2(100)NOT NULL
)
;

ALTER TABLE xxmcc.permissao_usuario
    ADD CONSTRAINT permissao_usuar_pk PRIMARY KEY(id_permissao_usuario)
        ;

ALTER TABLE xxmcc.permissao_usuario
    ADD CONSTRAINT permissao_usua_uk1 UNIQUE(id_permissao
    ,id_usuario)
        ;



ALTER TABLE xxmcc.permissao_usuario
    ADD CONSTRAINT permis_permiusu_fk FOREIGN KEY(id_permissao)
        REFERENCES xxmcc.permissao(id_permissao);

ALTER TABLE xxmcc.permissao_usuario
    ADD CONSTRAINT usuari_permiusu_fk FOREIGN KEY(id_usuario)
        REFERENCES xxmcc.usuario(id_usuario);
        
        
        
        
        
CREATE TABLE xxmcc.MONITOR_ACESSO(
    id_monitor_acesso    NUMBER(15) NOT NULL,
    nm_login             VARCHAR2(250) NOT NULL,
    tp_metodo            VARCHAR2(20) NOT NULL,
    ds_uri               VARCHAR2(200) NOT NULL,
    dt_acesso            DATE NOT NULL,
    nr_ip                VARCHAR2(50) NOT NULL,    
    dt_criacao           DATE NOT NULL,
    nm_usuario_criacao   VARCHAR2(100) NOT NULL,
    dt_alteracao         DATE NOT NULL,
    nm_usuario_alteracao VARCHAR2(100) NOT NULL
)
;

CREATE INDEX MONITOR_ACESSO_n1 ON xxmcc.MONITOR_ACESSO ( nr_ip ASC ) ;

CREATE INDEX MONITOR_ACESSO_n2 ON xxmcc.MONITOR_ACESSO ( dt_acesso  ASC ) ;

CREATE INDEX MONITOR_ACESSO_n3 ON xxmcc.MONITOR_ACESSO ( nm_login  ASC ) ;

ALTER TABLE xxmcc.MONITOR_ACESSO ADD CONSTRAINT MONITOR_ACESSO_pk PRIMARY KEY ( id_monitor_acesso )  ;


        

----------------------------------------
-- SEQUENCE

-----------------------------------------   



CREATE SEQUENCE xxmcc.PERMISSAO_S
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999999999
MINVALUE 1
NOCACHE;



CREATE SEQUENCE xxmcc.USUARIO_S
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999999999
MINVALUE 1
NOCACHE;


CREATE SEQUENCE xxmcc.PERMISSAO_USUARI_S
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999999999
MINVALUE 1
NOCACHE;

CREATE SEQUENCE xxmcc.MONITOR_ACESSO_S
INCREMENT BY 1
START WITH 1
MAXVALUE 999999999999999
MINVALUE 1
NOCACHE;




---------------------------------------------------------
-- AD_ZD_TABLE (utilizado nas versoes Oracle 19c)
-------------------------------------------------------

-- xxmcc = owner customizado.

BEGIN


  AD_ZD_TABLE.UPGRADE('XXMCC','PERMISSAO'); 
  AD_ZD_TABLE.UPGRADE('XXMCC','USUARIO'); 
  AD_ZD_TABLE.UPGRADE('XXMCC','PERMISSAO_USUARIO'); 
  AD_ZD_TABLE.UPGRADE('XXMCC','MONITOR_ACESSO'); 

END;



----------------------------------------
-- TRIGGERS

-----------------------------------------   

-- na versão Oracle 19c existe a view editavel, objeto com o # no final
-- criado no owner customizado (XXMCC)
create or replace TRIGGER PERMISSAO_S_TRG
 BEFORE INSERT OR UPDATE ON XXMCC.PERMISSAO#
 FOR EACH ROW

BEGIN
  BEGIN
     --Populando a primary key
     IF INSERTING AND :new.ID_PERMISSAO IS NULL THEN
        SELECT XXMCC.PERMISSAO_S.nextval INTO :new.ID_PERMISSAO FROM DUAL;
     END IF;

     IF INSERTING THEN
       IF :new.nm_usuario_criacao IS NULL THEN
        :new.nm_usuario_criacao   := 'ANONYMOUS';-- FUNCAO PARA RECUPERAR USERNAME
       END IF;
        :new.dt_criacao           := SYSDATE;
     END IF;
     --
     --Populando campos de alteração
     IF INSERTING OR UPDATING THEN
       IF :new.nm_usuario_alteracao IS NULL THEN
        :new.nm_usuario_alteracao :='ANONYMOUS';-- FUNCAO PARA RECUPERAR USERNAME
       END IF;
        :new.dt_alteracao         := SYSDATE;
     END IF;
  END;
END PERMISSAO_S_TRG;
--

create or replace TRIGGER USUARIO_S_TRG
 BEFORE INSERT OR UPDATE ON XXMCC.USUARIO#
 FOR EACH ROW

  --
BEGIN
  BEGIN
     --Populando a primary key
     IF INSERTING AND :new.id_usuario IS NULL THEN
        SELECT XXMCC.USUARIO_S.nextval INTO :new.id_usuario FROM DUAL;
     END IF;
     
     IF INSERTING THEN
       IF :new.nm_usuario_criacao IS NULL THEN
        :new.nm_usuario_criacao   := 'ANONYMOUS';-- FUNCAO PARA RECUPERAR USERNAME
       END IF;
        :new.dt_criacao           := SYSDATE;
     END IF;
     --
     --Populando campos de alteração
     IF INSERTING OR UPDATING THEN
       IF :new.nm_usuario_alteracao IS NULL THEN
        :new.nm_usuario_alteracao :='ANONYMOUS';-- FUNCAO PARA RECUPERAR USERNAME
       END IF;
        :new.dt_alteracao         := SYSDATE;
     END IF;

  END;
END USUARIO_S_TRG;




--
CREATE OR REPLACE TRIGGER PERMISS_USUA_S_TRG
 BEFORE INSERT OR UPDATE ON XXMCC.PERMISSAO_USUARIO#
 FOR EACH ROW

  --
BEGIN
  BEGIN
     --Populando a primary key
     IF INSERTING AND :new.id_permissao_usuario IS NULL THEN
        SELECT XXMCC.PERMISSAO_USUARI_S.nextval INTO :new.id_permissao_usuario FROM DUAL;
     END IF;
     
     IF INSERTING THEN
       IF :new.nm_usuario_criacao IS NULL THEN
        :new.nm_usuario_criacao   := 'ANONYMOUS';-- FUNCAO PARA RECUPERAR USERNAME
       END IF;
        :new.dt_criacao           := SYSDATE;
     END IF;
     --
     --Populando campos de alteração
     IF INSERTING OR UPDATING THEN
       IF :new.nm_usuario_alteracao IS NULL THEN
        :new.nm_usuario_alteracao :='ANONYMOUS';-- FUNCAO PARA RECUPERAR USERNAME
       END IF;
        :new.dt_alteracao         := SYSDATE;
     END IF;
 
     --
  END;
END PERMISS_USUA_S_TRG;


CREATE OR REPLACE TRIGGER MONITOR_ACES_S_TRG
 BEFORE INSERT OR UPDATE ON xxmcc.MONITOR_ACESSO#
 FOR EACH ROW

  --
BEGIN
  BEGIN
     --Populando a primary key
     IF INSERTING AND :new.id_monitor_acesso IS NULL THEN
        SELECT XXMCC.MONITOR_ACESSO_S.nextval INTO :new.id_monitor_acesso FROM DUAL;
     END IF;
     --Populando campos de criação
     IF INSERTING THEN
       IF :new.nm_usuario_criacao IS NULL THEN
        :new.nm_usuario_criacao   := 'ANONYMOUS';-- FUNCAO PARA RECUPERAR USERNAME
       END IF;
        :new.dt_criacao           := SYSDATE;
     END IF;
     --
     --Populando campos de alteração
     IF INSERTING OR UPDATING THEN
       IF :new.nm_usuario_alteracao IS NULL THEN
        :new.nm_usuario_alteracao := 'ANONYMOUS';-- FUNCAO PARA RECUPERAR USERNAME
       END IF;
        :new.dt_alteracao         := SYSDATE;
     END IF;
     --
  END;
END MONITOR_ACES_S_TRG;
---------------------------------------------------------------
-- INSERINDO ROLE_ADMIN 
---------------------------------------------------------------
INSERT INTO permissao(cd_permissao)VALUES('ROLE_ADMIN');

---------------------------------------------------------------
-- INSERINDO USUARIO ADMIN 
---------------------------------------------------------------

INSERT INTO usuario(
    nm_usuario
    ,nm_login
    ,ds_senha
    ,nm_empresa
    ,ds_email
    ,in_ativo
)VALUES(
    'Administrador da API'
   ,'adminapi@email.com.br'
   ,'$2a$14$RTzvZ8F07N3jp4w9K5V44.yh76cDR9j4RpEhAKaSnhY.Lq5b83LGi' -- 12345678
   ,'My Company'
   ,'adminapi@email.com.br'
   ,'S'
);

---------------------------------------------------------------
-- INSERINDO AS PERMISSAO PARA O USUARIO ADMIN 
---------------------------------------------------------------
insert into permissao_usuario(id_permissao,id_usuario) values(1,1);