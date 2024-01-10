# Kirana Register for Jar

Welcome to the Kirana Register for Jar project, comprising two services: TransactionService and DailyReportService. Below, you'll find information on how to use each service and the available APIs.

## TransactionService

### Save New Transaction
- **Endpoint:** `POST /transactions`
- **Request:** `{ "currency": "USD", "amount": 50.0 }`
- **Response:** `{ "id": 1, "currency": "USD", "amount": 50.0, "timestamp": 1704850839597 }`

### Get All Transactions
- **Endpoint:** `GET /transactions`
- **Response:** `[ { "id": 1, "currency": "USD", "amount": 50.0, "timestamp": 1704850839597 }, ... ]`

### Get Transactions by Currency
- **Endpoint:** `GET /transactions/{currency}`
- **Response:** `[ { "id": 1, "currency": "USD", "amount": 50.0, "timestamp": 1704850839597 }, ... ]`

### Get Transactions Converted to Currency
- **Endpoint:** `GET /transactions/convert/{targetCurrency}`
- **Response:** `[ { "id": 1, "currency": "USD", "amount": 50.0, "convertedAmount": 63.5, "targetCurrency": "EUR", "timestamp": 1704850839597 }, ... ]`

## DailyReportService

### Get Daily Report by Date
- **Endpoint:** `GET /daily-reports`
- **Request:** `{ "date": "10/01/2024" }`
- **Response:** `{ "date": "10/01/2024", "countOfTransactions": 50, "transactions": [ ... ], "dailyReportSummaryPerCurrency": { "USD": { "transactionsCount": 30, "creditedAmount": 500.0, "debitedAmount": -200.0 }, "EUR": { "transactionsCount": 20, "creditedAmount": 300.0, "debitedAmount": -150.0 }, ... } }`

## Postman Collection

Explore and test the APIs using the [Postman collection](https://www.postman.com/research-meteorologist-96331936/workspace/jar/collection/19808396-703648bd-36da-4935-9b10-03c227c9f819?action=share&creator=19808396).

