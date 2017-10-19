docker-compose up && docker run --rm -d --name=rabbitmq --hostname rabbitmq -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password -P --net devbox_default rabbitmq:3
