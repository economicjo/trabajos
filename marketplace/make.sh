#!/bin/bash

export $( grep -vE "^(#.*|\s*)$" .env )

QSDL_IMAGE="253594432138.dkr.ecr.eu-central-1.amazonaws.com/21f-qsdl-dev:latest"

qsdl()
{
  # docker pull $QSDL_IMAGE
  # docker run -it --rm \
  #   -v "$PRJ_ROOT/domain:/generated_content" \
  #   --user "$(id -u $(whoami)):$(id -g $(whoami))" \
  #   $QSDL_IMAGE main.qsdl -g openapi -o .

  # not everyone has aws access yet..

  if false ; then echo ;
  elif [ "$1" = "--api" ]; then
    exec qsdl domain/main.qsdl -g openapi -o .
  elif [ "$1" = "--fe" ]; then
    exec npm run qsdl:i18n
    exec npm run qsdl:msw
    exec npm run openapi:client
  fi
}

build()
{
    echo "building..."
}

test()
{
    echo "testing..."
}

release()
{
    echo "releasing..."
    standard-version -a "$@" 
}

if false ; then echo ;
elif [ "$1" = "qsdl" ]; then qsdl ${@:2};
elif [ "$1" = "build" ]; then build ${@:2};
elif [ "$1" = "test" ]; then test ${@:2};
elif [ "$1" = "release" ]; then release ${@:2};
else
    echo "Usage: bash makefile.sh [OPTIONS]"
    echo
    echo -e "\033[1;4;32m""Options:""\033[0;34m"
    compgen -A function
    echo
    echo -e "\033[0m"
fi
