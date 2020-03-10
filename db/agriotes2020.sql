CREATE USER agriotes_user@localhost IDENTIFIED BY 'agriotes_pwd';
GRANT ALL ON agriotes2020.* TO agriotes_user@localhost;
GRANT EXECUTE ON agriotes2020.* TO agriotes_user@localhost;
