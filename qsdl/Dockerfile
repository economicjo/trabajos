## Base
FROM python:3-alpine as base

RUN apk update && apk add build-base
RUN apk update && apk add curl
RUN curl -sSL https://raw.githubusercontent.com/python-poetry/poetry/master/get-poetry.py | python -
ENV PATH=$PATH:/root/.poetry/bin

WORKDIR /app

COPY qsdl ./qsdl
COPY README.md .
COPY pyproject.toml .
COPY poetry.lock .

RUN poetry install

## Test
FROM base as test
COPY tests ./tests
ENTRYPOINT [ "poetry", "run", "pytest" ]

## Build
FROM base as build
RUN poetry build

## Final
FROM python:3-alpine as final

COPY --from=build /app/dist /app/dist
RUN pip3 install /app/dist/*.whl

WORKDIR /generated_content

ENTRYPOINT ["qsdl"]
CMD ["--help"]

## To generate a Spring app from the input.qsdl in UNIX-like OS ##
# docker run -v $(pwd):/generated_content --user $(id -u $(whoami)):$(id -g $(whoami)) \
#   -it qsdl -g spring ./util/examples/input.qsdl
## To generate a Spring app from the input.qsdl in Windows ##
# docker run -v ${pwd}:/generated_content -it qsdl -g spring ./util/examples/input.qsdl