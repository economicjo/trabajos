# build stage
FROM node:16.15-alpine as build-stage

WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .

## provide build args
ARG VITE_KEYCLOAK_URL
ARG VITE_KEYCLOAK_REALM
ARG VITE_KEYCLOAK_CLIENTID

## build the application with vite
RUN npm run build

# production stage
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html

# utilize environment variable replacement
COPY utils/nginx/nginx.conf /etc/nginx/templates/default.conf.template

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
