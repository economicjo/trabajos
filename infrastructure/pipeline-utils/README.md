# pipeline-utils

Contains custom Docker images we use in the jobs of CI pipelines we have.

## cli-tools

This Ubuntu-based image has [aws-cli](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-welcome.html) and [helm](https://helm.sh/) installed. It is used in deployment stages of the pipelines. An example deployment in a CI job with this image could be as follows:

```
deployment-example:
  image: $CI_REGISTRY/21.financeag/sw-development/infrastructure/cli-tools:latest
  stage: deployment
  variables:
    VERSION: latest
  script:
    - mkdir /root/.aws
    - echo ${AWS_EKS_CONFIG} | base64 -d > /root/.aws/config
    - echo ${AWS_EKS_CREDENTIALS} | base64 -d > /root/.aws/credentials
    - /usr/local/bin/aws eks update-kubeconfig --region eu-central-1 --name ${KUBERNETES_CLUSTER_NAME} --role-arn arn:aws:iam::${AWS_EKS_ACCOUNT_ID}:role/${AWS_EKS_ACCOUNT_ROLE_NAME}
    - helm upgrade ${CI_PROJECT_NAME}-backend --namespace ${CI_PROJECT_NAME} --set-string image.tag=${VERSION} --atomic --install --create-namespace ./deployment/backend
```