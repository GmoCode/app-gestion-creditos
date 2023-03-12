CREATE TABLE TBL_CLIENTE (
    CLIENTE_ID NUMERIC (6)NOT NULL PRIMARY KEY,
    NOMBRES VARCHAR(120) NOT NULL,
    APELLIDOS VARCHAR(120) NOT NULL,
    DNI NUMERIC (8) NOT NULL UNIQUE,
    TELEFONO VARCHAR(12) NOT NULL,
    CORREO VARCHAR (120) NOT NULL,
    ESTADO CHAR(1) DEFAULT '1'
);

CREATE TABLE TBL_CATEGORIA (
    CLIENTE_ID NUMERIC (6)NOT NULL PRIMARY KEY,
    NOMBRE_CATEGORIA VARCHAR(120) NOT NULL,
    ESTADO CHAR(1) DEFAULT '1'
);



CREATE TABLE TBL_PRODUCTO (
    PRODUCTO_ID NUMERIC (6)NOT NULL PRIMARY KEY,
    CODIGO_PRODUCTO CHAR(4) NOT NULL UNIQUE,
    NOMBRE_PRODUCTO VARCHAR(120) NOT NULL,
    TASA_INTERES NUMBER (6,2),
    ESTADO CHAR(1) DEFAULT '1'
);

 

 -- SEGURIDAD

CREATE TABLE TBL_USUARIO
(    ID_USUARIO NUMERIC(6) NOT NULL PRIMARY KEY,
     USUARIO VARCHAR2(20 BYTE) NOT NULL UNIQUE,
     CLAVE VARCHAR2(120 BYTE) NOT NULL ,
     NOMBRE VARCHAR2(120 BYTE)NOT NULL,
     ESTADO CHAR(1) DEFAULT 1 NOT NULL
    );

CREATE TABLE TBL_AUTHORITY
(    AUTHORITY_ID NUMERIC(6) NOT NULL PRIMARY KEY,
     NOMBRE VARCHAR2(120 BYTE)NOT NULL,
     ESTADO CHAR(1 BYTE) DEFAULT 1 NOT NULL
);

CREATE TABLE TBL_USUARIO_AUTHORITY
(USUARIO_AUTHORITY_ID NUMERIC(6) NOT NULL PRIMARY KEY,
 ID_USUARIO NUMERIC(6) NOT NULL REFERENCES TBL_USUARIO(ID_USUARIO),
 AUTHORITY_ID NUMERIC(6) NOT NULL REFERENCES TBL_AUTHORITY(AUTHORITY_ID),
 ESTADO CHAR(1 BYTE) DEFAULT 1 NOT NULL
 );

-- 

 CREATE TABLE TBL_DESEMBOLSO(
    DESEMBOLSO_ID NUMERIC (6)NOT NULL PRIMARY KEY,
    ID_USUARIO NUMERIC(6) REFERENCES TBL_USUARIO (ID_USUARIO),
    CLIENTE_ID NUMERIC(6) REFERENCES TBL_CLIENTE (CLIENTE_ID),
    PRODUCTO_ID NUMERIC (6) REFERENCES TBL_PRODUCTO (PRODUCTO_ID),
    TIPO_CAMBIO VARCHAR(20) NOT NULL,
    MONTO_CREDITO NUMBER (6,2),
    TOTAL_CUOTAS NUMERIC(3),
    FECHA_PRESTAMO DATE,
    MONTO_TOTAL NUMBER (6,2),
    ESTADO CHAR(1) DEFAULT '1'
 );

 CREATE TABLE TBL_DESEMBOLSO_DETALLE(
    DESEMBOLSO_DETALLE_ID NUMERIC (6) NOT NULL PRIMARY KEY,
    DESEMBOLSO_ID NUMERIC(6) REFERENCES TBL_DESEMBOLSO (DESEMBOLSO_ID),
    NUMERO_CUOTA NUMERIC(3) NOT NULL,
    FECHA_PAGO DATE NOT NULL,
    CAPITAL_CUOTA NUMBER (6,2) NOT NULL,
    INTERES_CUOTA NUMBER (6,2) not NULL,
    PAGO_CUOTA NUMBER (6,2) NOT NULL
 );