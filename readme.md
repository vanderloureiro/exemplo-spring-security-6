# Exemplo Spring Security 6

Esse projeto é um simples exemplo usando o Spring Security 6 e Spring Boot 3, apresentando as diferenças das últimas versões.


### Endpoints

GET http://localhost:8080/publica

É um endpoint que apenas retorna uma String e pode ser acessado nem autenticação

GET http://localhost:8080/privada

Também retorna apenas uma String, mas exige uma autenticação Basic Auth com:

Username: usuario <br>
Password: 123456