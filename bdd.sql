CREATE TABLE bdd_kubra.Utilisateur (
    id INT AUTO_INCREMENT,
    email VARCHAR( 60 ) NOT NULL,
    mot_de_passe VARCHAR( 56 ) NOT NULL,
    date_inscription DATETIME NOT NULL,
    PRIMARY KEY ( id ),
    UNIQUE ( email )
);

CREATE TABLE bdd_kubra.Action (
    id_action INT AUTO_INCREMENT,
    symbole VARCHAR ( 6 ) NOT NULL,
    nom VARCHAR( 30 ) NOT NULL,
    PRIMARY KEY ( id_action ),
    UNIQUE ( symbole,nom )
);

CREATE TABLE bdd_kubra.Portefeuille (
    id_portefeuille INT NOT NULL,
    id_action INT NOT NULL,
    quantite INT NOT NULL,
    date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    prix_unitaire REAL NOT NULL,
    prix_total REAL AS (prix_unitaire*quantite),
    type VARCHAR( 5 ) NOT NULL,
    FOREIGN KEY ( id_portefeuille ) REFERENCES Utilisateur ( id ),
    FOREIGN KEY ( id_action ) REFERENCES Action ( id_action ),
    CHECK ( UPPER( type ) LIKE 'ACHAT' OR UPPER( type ) LIKE 'VENTE' )
);

CREATE TABLE bdd_kubra.Porteaction (
    id_utilisateur INT NOT NULL,
    id_action INT NOT NULL,
    quantite INT NOT NULL,
    valeur DOUBLE NOT NULL,
    FOREIGN KEY (id_utilisateur ) REFERENCES Utilisateur ( id ),
    FOREIGN KEY ( id_action ) REFERENCES Action ( id_action ),
    CHECK ( quantite > 0)
);

INSERT INTO bdd_kubra.Action ( symbole,nom ) VALUES ( 'AI','Air liquide' ),
                                                    ( 'AIR','Airbus' ),
                                                    ( 'ALO','Alstom' ),
                                                    ( 'MT','ArcelorMittal' ),
                                                    ( 'ATO','Atos' ),
                                                    ( 'CS','AXA' ),
                                                    ( 'BNP','BNP Paribas' ),
                                                    ( 'EN','Bouygues' ),
                                                    ( 'CAP','Capgemini' ),
                                                    ( 'CA','Carrefour' ),
                                                    ( 'ACA','Crédit agricole' ),
                                                    ( 'BN','Danone' ),
                                                    ( 'DSY','Dassault Systèmes' ),
                                                    ( 'ENGI','Engie' ),
                                                    ( 'EI','EssilorLuxottica' ),
                                                    ( 'RMS','Hermès International' ),
                                                    ( 'KER','Kering' ),
                                                    ( 'LR','Legrand' ),
                                                    ( 'OR','L\'Oréal' ),
                                                    ( 'MC','LVMH' ),
                                                    ( 'ML','Michelin' ),
                                                    ( 'ORA','Orange' ),
                                                    ( 'RI','Pernod Ricard' ),
                                                    ( 'PUB','Publicis Groupe' ),
                                                    ( 'RNO','Renault' ),
                                                    ( 'SAF','Safran' ),
                                                    ( 'SGO','Saint-Gobain' ),
                                                    ( 'SAN','Sanofi' ),
                                                    ( 'SU','Schneider Electric' ),
                                                    ( 'GLE','Société générale' ),
                                                    ( 'STLA','Stellantis' ),
                                                    ( 'STM','STMicroelectronics' ),
                                                    ( 'TEP','Teleperformance' ),
                                                    ( 'HO','Thales' ),
                                                    ( 'FP','Total' ),
                                                    ( 'UL','Unibail-Rodamco-Westfield' ),
                                                    ( 'VIE','Veolia' ),
                                                    ( 'DG','Vinci' ),
                                                    ( 'VIV','Vivendi' ),
                                                    ( 'WLN','Worldline' );