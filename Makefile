start:
	docker run --name localstack --rm -d -p 4566:4566 -p 4510-4559:4510-4559 localstack/localstack

createQueue:
	docker exec localstack bash -c 'awslocal sqs create-queue --queue-name DEV_CONTRATACAO'

sendMessage:
	docker exec localstack bash -c 'awslocal sqs send-message --queue-url http://localhost:4566/000000000000/DEV_CONTRATACAO --message-body "{\"cep\":\"49030170\", \"nome\": \"Diego Andrade\"}"'

delete:
	docker rm -f localstack