# Desafio Técnico API

Aplicação responsável por prover testes integrados automaziados para a aplicação exemplo spring-boot-h2-crud.

## Tecnologias Utilizadas

- Java 11
- Gradle
- Rest-assured
- Lombok
- Spring-boot

## Comando para a execução dos testes
```bash
clean test -Pspring.profiles.active=hom -PtestSuite=testNg
```