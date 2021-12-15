CREATE TABLE IF NOT EXISTS gift_certificate (
                                                   id INT NOT NULL AUTO_INCREMENT,
                                                   nam VARCHAR(45) NULL,
                                                   description VARCHAR(45) NULL,
                                                   price DOUBLE NULL,
                                                   duration INT NULL,
                                                   create_date TIMESTAMP NULL,
                                                   last_update_date TIMESTAMP NULL,
                                                   PRIMARY KEY (id),
                                                   UNIQUE INDEX id_UNIQUE (id ASC) );

CREATE TABLE IF NOT EXISTS tag (
                                     id INT NOT NULL AUTO_INCREMENT,
                                     name VARCHAR(45) NULL,
                                     PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS gift_certificate_m2m_tag (
                                                   tag_id INT NOT NULL,
                                                   certificate_id INT NOT NULL,
                                                   CONSTRAINT certificate_fk
                                                       FOREIGN KEY (certificate_id)
                                                           REFERENCES gift_certificate (id)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION,
                                                   CONSTRAINT tag_fk
                                                       FOREIGN KEY (tag_id)
                                                           REFERENCES tag (id)
                                                           ON DELETE NO ACTION
                                                           ON UPDATE NO ACTION);