CREATE TABLE user (
  id UUID NOT NULL,
  username VARCHAR(256) NOT NULL,
  email VARCHAR(256) NOT NULL,
  password VARCHAR(256) NOT NULL,
  enabled BIT NOT NULL DEFAULT true,
  account_non_expired BIT NOT NULL DEFAULT true,
  account_non_locked BIT NOT NULL DEFAULT true,
  credentials_non_expired BIT NOT NULL DEFAULT true,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,

  PRIMARY KEY(id)
);