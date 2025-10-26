# springWebSocket-stomp
Simple Text oriented message protocol

Open browser to the Thymeleaf page (example transaction id txn-123):

http://localhost:8080/payment/status/txn-123

You should see: Processing payment... please wait. and the websocket connecting.

Simulate LFI webhook (send JSON to webhook endpoint):
curl -X POST http://localhost:8080/lfi/webhook/payment \
  -H "Content-Type: application/json" \
  -d '{"transactionId":"txn-123","status":"SUCCESS","message":"Settled by bank. Ref: BANKREF001"}'

After you POST, the browser page will update immediately to show: Payment successful. Settled by bank. Ref: BANKREF001curl -X POST http://localhost:8080/lfi/webhook/payment \
  -H "Content-Type: application/json" \
  -d '{"transactionId":"txn-123","status":"FAILED","message":"Insufficient funds"}'


  # comands 

  wscat -c ws://localhost:8080/ws/websocket
