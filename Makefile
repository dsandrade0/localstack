start:
	docker run --name localstack --rm -d -p 4566:4566 -p 4510-4559:4510-4559 localstack/localstack

createQueue:
	docker exec localstack bash -c 'awslocal sqs create-queue --queue-name DEV_CONTRATACAO_BEM_VINDO'

queueArn:
	docker exec localstack bash -c 'awslocal sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/DEV_CONTRATACAO_BEM_VINDO --attribute-names QueueArn'

listQueue:
	docker exec localstack bash -c 'awslocal sqs list-queues'

sendMessage:
	docker exec localstack bash -c 'awslocal sqs send-message --queue-url http://localhost:4566/000000000000/DEV_CONTRATACAO --message-body "{\"cep\":\"49030170\", \"nome\": \"Diego Andrade\"}"'

create-topic:
	docker exec localstack bash -c 'awslocal sns create-topic --name DEV_SNS_CONTRATACAO'

list-topics:
	docker exec localstack bash -c 'awslocal sns list-topics'

topic-subscribe:
	docker exec localstack bash -c 'awslocal sns subscribe --topic-arn "arn:aws:sns:us-east-1:000000000000:DEV_SNS_CONTRATACAO" --protocol sqs --notification-endpoint "arn:aws:sqs:us-east-1:000000000000:DEV_CONTRATACAO"'

sendMessageTopic:
	docker exec localstack bash -c 'awslocal sns publish --topic-arn "arn:aws:sns:us-east-1:000000000000:DEV_SNS_CONTRATACAO" --message "{\"cep\":\"49030220\", \"nome\": \"Daniel Braz\"}"'

listSubscription:
	docker exec localstack bash -c 'awslocal sns list-subscriptions'



delete:
	docker rm -f localstack