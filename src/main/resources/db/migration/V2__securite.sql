CREATE TABLE privilege (
    id          uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    code        varchar(100) NOT NULL UNIQUE,
    libelle     varchar(255) NOT NULL,
    created_at  timestamptz  NOT NULL DEFAULT now(),
    updated_at  timestamptz  NOT NULL DEFAULT now()
);

CREATE TABLE profil (
    id            uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    code          varchar(50)  NOT NULL UNIQUE,
    libelle       varchar(255) NOT NULL,
    native_profil boolean      NOT NULL DEFAULT false,
    created_at    timestamptz  NOT NULL DEFAULT now(),
    updated_at    timestamptz  NOT NULL DEFAULT now()
);

CREATE TABLE profil_privilege (
    profil_id    uuid NOT NULL REFERENCES profil(id)    ON DELETE CASCADE,
    privilege_id uuid NOT NULL REFERENCES privilege(id) ON DELETE CASCADE,
    PRIMARY KEY (profil_id, privilege_id)
);

CREATE TABLE utilisateur (
    id                       uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    username                 varchar(100) NOT NULL UNIQUE,
    password                 varchar(255) NOT NULL,
    nom                      varchar(100) NOT NULL,
    prenom                   varchar(100) NOT NULL,
    telephone                varchar(30),
    email                    varchar(255) NOT NULL UNIQUE,
    actif                    boolean      NOT NULL DEFAULT false,
    activation_key           varchar(100),
    confirmation_expire_date timestamptz,
    reset_key                varchar(100),
    reset_date               timestamptz,
    reset_expire_date        timestamptz,
    profil_id                uuid NOT NULL REFERENCES profil(id),
    created_at               timestamptz  NOT NULL DEFAULT now(),
    updated_at               timestamptz  NOT NULL DEFAULT now()
);

CREATE INDEX idx_utilisateur_profil ON utilisateur(profil_id);

-- Les 7 profils natifs du cahier des charges
INSERT INTO profil (code, libelle, native_profil) VALUES
    ('ROOT',       'Super administrateur',                 true),
    ('ADMIN_UTDA', 'Administrateur UTDA',                  true),
    ('SUP_UTDA',   'Superviseur UTDA',                     true),
    ('DTN',        'Directeur technique national',         true),
    ('DTS',        'Directeur technique de section',       true),
    ('RESP_SECT',  'Responsable de section',               true),
    ('RESP_CLUB',  'Responsable de club',                  true);

-- Privilèges de ce module (les autres modules ajouteront les leurs)
INSERT INTO privilege (code, libelle) VALUES
    ('UTILISATEUR_LIRE',      'Consulter les utilisateurs'),
    ('UTILISATEUR_CREER',     'Créer un utilisateur'),
    ('UTILISATEUR_MODIFIER',  'Modifier un utilisateur'),
    ('UTILISATEUR_DESACTIVER','Activer ou désactiver un utilisateur'),
    ('PROFIL_LIRE',           'Consulter les profils'),
    ('PROFIL_GERER',          'Créer, modifier et supprimer un profil'),
    ('PRIVILEGE_LIRE',        'Consulter les privilèges'),
    ('REFERENTIEL_LIRE',      'Consulter les données de base'),
    ('REFERENTIEL_GERER',     'Gérer les données de base');

-- ROOT : tous les privilèges
INSERT INTO profil_privilege (profil_id, privilege_id)
SELECT p.id, pr.id FROM profil p CROSS JOIN privilege pr WHERE p.code = 'ROOT';

-- ADMIN_UTDA : données de base
INSERT INTO profil_privilege (profil_id, privilege_id)
SELECT p.id, pr.id FROM profil p, privilege pr
WHERE p.code = 'ADMIN_UTDA' AND pr.code IN ('REFERENTIEL_LIRE', 'REFERENTIEL_GERER');