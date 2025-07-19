-- -----------------------------------------------------
-- Table `bootcamps`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bootcamps (
  id          BIGINT PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(50)  NOT NULL,
  description VARCHAR(90)  NOT NULL,
  date        DATETIME     NOT NULL,
  duration    BIGINT       NOT NULL
);

-- -----------------------------------------------------
-- Junction table `bootcamp_capability`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bootcamp_capability (
  id           BIGINT PRIMARY KEY AUTO_INCREMENT,
  bootcamp_id  BIGINT  NOT NULL,
  capability_id BIGINT  NOT NULL,

  CONSTRAINT FK_bc_bootcamp
    FOREIGN KEY (bootcamp_id)
    REFERENCES bootcamps (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  CONSTRAINT FK_bc_capability
    FOREIGN KEY (capability_id)
    REFERENCES capabilities (id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);