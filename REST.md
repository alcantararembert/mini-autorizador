# REST Requests

The REST endpoints:

1. API endpoints are for creating and managing data about cards and transactions.

# API Endpoints

# Card Requests

## Create a card
****Request:****
```
POST /cartoes

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json

{
    "numeroCartao": "6549873025634501",
    "senha": "1234"
}

```
****Returns:****
```
HTTP 201 CREATED

{
    "numeroCartao": "6549873025634501",
    "senha": "1234"
}
```
****Error:****
```
HTTP 409 CONFLICT
{
    "numeroCartao": "6549873025634501",
    "senha": "1234"
}
```

## Get card balance
**Request:**
```
GET /cartoes/{numeroCartao}

Host: localhost:8080
Auth:
Content-type: application/json
Accept: application/json
```
**Returns:**
```
{
    500.00
}
```
**Error:**
```
HTTP 404 NOT FOUND
{
    "status": 404,
    "message": "CARTAO_NAO_ENCONTRADO"
}
```

# Transaction Requests

## Create Transaction
**Request:**
```
POST /transacoes

Host: localhost:8080
Auth: 
Content-type: application/json
Accept: application/json

{
    "numeroCartao": "6549873025634501",
    "senhaCartao": "1234",
    "valor": 10.00
}
```
**Returns:**
```
HTTP 201 CREATED

OK
```
**Error:**
```
HTTP 422 UNPROCESSABLE ENTITY
{
    "status": 422,
    "message": "SALDO_INSUFICIENTE"
}
```
```
HTTP 422 UNPROCESSABLE ENTITY
{
    "status": 422,
    "message": "SENHA_INVALIDA"
}
```
```
HTTP 422 UNPROCESSABLE ENTITY
{
    "status": 422,
    "message": "CARTAO_INEXISTENTE"
}
```

