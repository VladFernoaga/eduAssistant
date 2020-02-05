# Run docker compose for pgAdmin and posgres

1. `docker-compose up -d` (create services in detached mode)
2. open pgAdmin with 0.0.0.0:8000 in the browser
3. To get the url to connect to the postgres image use `docker inspect 96d796d9af8d | findstr "IPAddress"`
4. pgAdmin: email: 1234@admin.com password: 1234
