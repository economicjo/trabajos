#!/bin/bash

# read env variables
export $( grep -vE "^(#.*|\s*)$" .env )
read -r VERSION < ../VERSION

build-ci()
{
    if [ -z "$1" ]; then echo "Missing image tag"; exit 1; fi

    docker build --build-arg VITE_KEYCLOAK_URL=$VITE_KEYCLOAK_URL \
                 --build-arg VITE_KEYCLOAK_REALM=$VITE_KEYCLOAK_REALM \
                 --build-arg VITE_KEYCLOAK_CLIENTID=$VITE_KEYCLOAK_CLIENTID \
                 -t $DOCKER_REG_NAME/$DOCKER_IMAGE_NAME:$1 \
                 .
}

build()
{
    docker-compose build service-portal-frontend
}

test()
{
    echo "testing..."
}

deploy()
{
    docker tag $DOCKER_IMAGE_NAME $DOCKER_REG_NAME/$DOCKER_IMAGE_NAME:latest
    docker tag $DOCKER_IMAGE_NAME $DOCKER_REG_NAME/$DOCKER_IMAGE_NAME:$VERSION
    docker push $DOCKER_REG_NAME/$DOCKER_IMAGE_NAME:latest
    docker push $DOCKER_REG_NAME/$DOCKER_IMAGE_NAME:$VERSION
}


echo "!!! Helper script for docker !!!"
if [ -z ${DOCKER_IMAGE_NAME+x} ]; then echo "docker name is unset"; exit; else echo "docker name is set to '$DOCKER_IMAGE_NAME'"; fi
if [ -z ${DOCKER_REG_NAME+x} ]; then echo "docker registry is unset"; exit; else echo "docker registry is set to '$DOCKER_REG_NAME'"; fi


if [ "$1" = "build" ]; then build;
elif [ "$1" = "build-ci" ]; then build-ci $2;
elif [ "$1" = "test" ]; then test;
elif [ "$1" = "deploy" ]; then deploy;
else
    echo "Valid options are either build, build-ci, test, or deploy"
fi
