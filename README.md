# Desafio Técnico API

Aplicação responsável por prover testes integrados automaziados para a aplicação [super-hero-sample-api](https://github.com/mazui00/super-heroes-sample-api).

## Tecnologias Utilizadas

- Java 11
- Gradle
- Rest-assured
- TestNG
- Lombok
- Spring-boot
- Extent-Report

## Comando para a execução dos testes
### Execução em ambiente local:

```bash
clean test -PtestSuite=testng
```
### Simulando execução em ambiente de homologação:

```bash
clean test -PtestSuite=testng -PspringProfile=hom
```
Para simular um ambiente de homologação foi utilizado o Heroku para hospedar a aplicação em teste.

> **Heroku:** https://super-heroes-sample-api.herokuapp.com

||
|  ---------    |
| **_Nota_** :Acessar o endereço pelo browser para garantir que a aplicação estará em execução antes dos testes| 

### Extent-report
O report encontra-se em:
```
build/reports/extent-report/report.html
```