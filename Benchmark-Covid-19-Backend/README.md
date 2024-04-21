# Benchmark sobre Covid-19 [Backend]

## 📃 Introdução

A aplicação visa disponibilizar rotas de API para acessar, criar, editar e excluir parâmetros de benchmarks referentes à Covid-19 entre países ou cidades/estados. Além disso, oferece rotas para obtenção dos resultados dos benchmarks.

Para a produção de uma benchmark as seguintes APIs são utilizadas para coleta de informações:
 - [Ninjas](https://api-ninjas.com/api/covid19) que abrange todos os países.
 - [Brasil.io](https://brasil.io/dataset/covid19/caso/) para cidades ou estados do Brasil..

## 💻 Tecnologias usadas:

- #### Linguagem: Java

- Spring Boot (Extenção do framework Spring)
- MySQL (Banco de dados relacional)
- Docker-Compose (Execução da Aplicação e do Banco de Dados em contêineres)
- JUnit (Framework de testes unitários)

## 💾 Instalações
Há 2 maneiras diferentes para execução da aplicação, através do docker ou localmente:</br>
 - **Docker** : Para execução de maneira mais simples da aplicação é necessário ter instalado a ferramenta [docker-compose](https://docs.docker.com/compose/install/).</br>
 - **Localmente** : Para execução local da aplicação é necessário ter instalado [MySQL](https://www.mysql.com/downloads/), [Java](https://www.java.com/pt-BR/download/) (recomendável 17) e [Maven](https://maven.apache.org/download.cgi).

## 💿 Rodando a aplicação

**Docker** : Para iniciar a aplicação utilizando docker, apenas utilize o comando:

    $ docker-compose up
**Local** : Para iniciar a aplicação localmente deve-se adicionar um banco de dados MySQL com os parametros definidos em src\main\resources\application.properties e executar os seguintes comandos para buildar a aplicação e executa-la:

    $ mvn package
    $ java -jar target/Covid19.jar
    
## ✅ Testes unitários 

Os testes já são executados quando há o build da aplicação, porém caso queira pode rodar o teste localmente: 

    $ mvn test

## 🔗 Rotas
Path                                | Método |  Descrição
----------------------------------- | ------ | -----
/api/benchmark                      |  GET   |  Retorna todos os registros de Benchmarks
/api/benchmark/:id                  |  GET   |  Retorna o registro de uma Benchmark a patir de sua ID
/api/benchmark/id                   |  GET   |  Retorna a ID de uma Benchmar a partir dos parametros
/api/benchmark                      |  POST  |  Inseri o registro de uma Benchmark
/api/benchmark/:id                  |  PUT   |  Altera dados de uma Benchmark
/api/benchmark/:id                  | DELETE |  Exclui o registro de uma Benchmarks a patir de sua ID
/api/benchmark/:id/result/citystate |  GET   |  Retorna o resultado de uma Benchmark entre cidades ou estados
/api/benchmark/:id/result/country   |  GET   |  Retorna o resultado de uma Benchmark entre países

## 💬 Status Code Previstos

| Código |       Status          | Observação                                                      |
|--------|-----------------------|-----------------------------------------------------------------|
| 200    | Ok                    |Requisição foi bem-sucedida.                                     | 
| 201    | Created               |Requisição foi bem-sucedida e um novo recurso foi criado         | 
| 204    | No content            |Requisição foi bem-sucedida, mas não há conteúdo para retornar   | 
| 400    | Bad Request           |Sintaxe inválida para algum atributo da requisição               | 
| 404    | Not Found             |Recurso requisitado não foi encontrado                           |
| 429    | Too Many Requests     |Muitas requisições referente à API Ninjas ou Brasil.io           | 
| 500    | Internal Server Error |Um erro interno no servidor impediu de atender à requisição      | 

## 📖 API

- ### GET /api/benchmark
#### response.body:
```json
[
    {
        "id": 1,
        "start_date": "2020-03-18",
        "end_date": "2021-11-24",
        "place_type": "city",
        "place_name_1": "São José dos Campos",
        "place_name_2": "São Paulo"
    },
    {
        "id": 2,
        "start_date": "2021-03-06",
        "end_date": "2021-12-18",
        "place_type": "country",
        "place_name_1": "US",
        "place_name_2": "Brazil"
    }
]
```
- ### GET /api/benchmark/:id
#### response.body:
```json
{
    "id": 1,
    "start_date": "2020-03-18",
    "end_date": "2021-11-24",
    "place_type": "city",
    "place_name_1": "São José dos Campos",
    "place_name_2": "São Paulo"
}
```
- ### GET /api/benchmark/id
#### request.params:
```json
"start_date": "2020-03-18"
"end_date": "2021-11-24"
"place_type": "city"
"place_name_1": "São José dos Campos"
"place_name_2": "São Paulo"
```
#### response.body:
```json
1
```
- ### POST /api/benchmark
#### request.body:
```json
{
    "start_date" : "2021-03-06",
    "end_date" : "2021-12-18",
    "place_type" : "country",
    "place_name_1" : "US",
    "place_name_2" : "Brazil"
}
```
#### response.body :
```json
{
    "id": 2,
    "start_date": "2021-03-06",
    "end_date": "2021-12-18",
    "place_type": "country",
    "place_name_1": "US",
    "place_name_2": "Brazil"
}
```
- ### PUT /api/benchmark/:id
#### request.body:
```json
{
    "start_date" : "2021-12-05",
    "end_date" : "2021-12-06",
    "place_type" : "country",
    "place_name_1" : "US",
    "place_name_2" : "Canada"
}
```
#### request.body:
```json
{
    "id": 2,
    "start_date": "2021-12-05",
    "end_date": "2021-12-06",
    "place_type": "country",
    "place_name_1": "US",
    "place_name_2": "Canada"
}
```
- ### GET /api/benchmark/:id/result/citystate
#### response.body:
```json
{
    "id": 1,
    "start_cases_1": 1,
    "start_deaths__1": 0,
    "start_estimated_population_1": 729737,
    "start_case_rate_1": 1.3704E-6,
    "start_death_rate_1": 0.0,
    "end_cases_1": 93253,
    "end_deaths__1": 1958,
    "end_estimated_population_1": 729737,
    "end_case_rate_1": 0.12779,
    "end_death_rate_1": 0.021,
    "start_cases_2": 214,
    "start_deaths__2": 3,
    "start_estimated_population_2": 12325232,
    "start_case_rate_2": 1.73628E-5,
    "start_death_rate_2": 0.014,
    "end_cases_2": 974660,
    "end_deaths__2": 39158,
    "end_estimated_population_2": 12325232,
    "end_case_rate_2": 0.0790784,
    "end_death_rate_2": 0.0402
}
```
- ### GET /api/benchmark/:id/result/country
#### response.body:
```json
[
    {
        "id": 1153,
        "country_number": 1,
        "type": "cases",
        "date": "2021-03-06",
        "cases": 29136550
    },
    {
        "id": 1154,
        "country_number": 1,
        "type": "deaths",
        "date": "2021-03-07",
        "cases": 894
    },
   { 
        "id": 1155,
        "country_number": 2,
        "type": "cases",
        "date": "2021-03-06",
        "cases": 291
    },
    {
        "id": 1156,
        "country_number": 2,
        "type": "deaths",
        "date": "2021-03-07",
        "cases": 429
    }
]
```

## 💡 Possíveis pontos de avanço

- Otimização para o acesso às APIs Ninjas e Brasil.io de forma que evite muitos requests
- Criação de mais classes e métodos de test
- Implementação de monitoramento de LOGs 

## ☕ Considerações finais
Este projeto foi criado com doses generosas de café.

[Contato Linkedin](https://www.linkedin.com/in/dev-lucas-marques-sjc/)
