# Servicio de generación de Zicode
Este servicio permite recuperar las ciudades y regiones que se encuentran dentro del código postal de un país en concreto:

## Cómo funciona

Tipo	HTTPS REST (GET)

URL Desarrollo	https://api-dev.tendam.es/zipcode/verify?<params>

Invocación de ejemplo (Desarrollo)	https://api-dev.tendam.es/zipcode/verify?country=ES&zipcode=01165

URL Producción	https://api.tendam.es/zipcode/verify?<params>

Seguridad	

HTTPS

La peticion se realiza por HTTPS. El servidor guarda un certificado SSL para securizar el canal y asegurar su identidad.
Autenticación

La llamada se debe realizar usando “HTTP Basic Authentication”, por lo que el cliente debe enviar usuario y password en la cabecera HTTP de cada invocación. De este modo será posible identificar al proveedor logístico que está invocando el método y gestionar el acceso a los datos del BackEnd de Cortefiel.

El código de usuario será zipcode_usr

password desarrollo:pwd

Password:consultar

Desarrollo: igual que el usuario (user1)

Producción: <consultar>

Filtro por IP

El cliente debe proporcionar una (o varias) IP estáticas y públicas.
Grupo Tendam dará acceso a estas IPs para que el cliente pueda consumir el servicio.
Frecuencia de invocación	24x7 (online): alrededor de 30.000 al día
Limitación en la volumetría de invocación	
La llamada, al ser de tipo GET, no precisa de mensaje en el cuerpo de la llamada.

No existe restricción en la volumetría.

Parámetros de invocación al servicio
La URL de invocación al servicio debe contener los siguientes parámetros:

## Especificación

| Entorno       | Protocolo    | Host           | Puerto |
| :----------- |:-------------|:-------------|:------------:|
| Desarrollo   | HTTPS     | api-dev.tendam.es | 443 |
| Producción   | HTTPS     | api.tendam.es       | 443 |


## Endpoints

| Ruta       | Método    |         Descripción |
| :----------- |:-------------|:-------------|
| /zipcode/generate   | GET     | Genera una peticion Zipcode devolviendo un codigo de area |
| /zipcode/health     | GET     | Verificación de disponibilidad del servicio |

country

String de 2	Si	
Código ISO 3166-1 de dos caracteres con el país cuyo código postal se desea comprobar.

ES
zipcode

String	Si	
Código postal a comprobar.

28341
source 

String 	
No

(recomendable)

Entorno del cliente que realiza la llamada	
TEST (puestos locales de test)

DEVELOPMENT

STAGING

PRODUCTION  

Mensaje de entrada
La llamada, al ser de tipo GET, no precisa de mensaje en el cuerpo de la llamada.

Formato y ejemplo
N/A (llamada tipo GET)

Descripción de los campos
N/A (llamada tipo GET)

Mensaje de salida
Formato y ejemplo
El mensaje de salida tendrá siempre un formato JSON.

1 resultados
{
   "verifyLevel": "Verified",
   "count": 1,
   "results": [   {
      "city": "RUMES",
      "region": null,
      "zipcode": "7610",
      "country": "BE"
   }]
}


### Ejecución

El servicio se ejecuta como contenedor en un cluster de Kubernetes (EKS) en AWS

### Ficheros para la construcción

En el directorio raíz se encuentran los siguientes ficheros

En el directorio raíz se encuentran los siguientes ficheros

| Nombre            | Descripción|
| :---------------- |:-----------|
| Dockerfile        | Genera imagen con aplicación y configuración
| Jenkinsfile       | Define el proceso completo de construcción y despliegue
| ansible-deploy.yml    | Define el despliegue en Kubernetes
| k8s/				|Define los objetos de Kubernetes necesarios para desplegar el servicio. Deployment, Service y regla de escalado


# Getting Started

## Project configuration

### Software requirements

* Download and install [STS 4](https://spring.io/tools)
* Download and install [OpenJDK11 HotSpot](https://adoptopenjdk.net/)
* Apache Maven 3.5.0+

### Running the application

1. Clone repository
2. Open STS 4 and select previous folder as workspace
3. Import projects as Maven projects
4. Setup Maven (Internet access required) and JDK11 in STS
5. Run class _ZipcodeApplication.java_ as "Spring Boot Application"

## Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#production-ready)
* [MyBatis Framework](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-security)
* [Spring LDAP](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/htmlsingle/#boot-features-ldap)

## Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [MyBatis Quick Start](https://github.com/mybatis/spring-boot-starter/wiki/Quick-Start)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
