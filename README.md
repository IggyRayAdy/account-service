# account-service

server
-------------------------
## docker compose for db:  
    docker-compose.yml
  
## default database param:  
    url: jdbc:postgresql://localhost:5432/account-service  
    username: account-service  
    password:  

## swager:
  http://localhost:8080/swagger-ui:html

client
-------------------------
## config into:  
    application.yml

## params for client start:  
    params:  
        ids-range-first: -10
        ids-range-end: 1  
        r-count: 2  
        w-count: 2  
