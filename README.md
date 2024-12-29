# Commands

Generate image

```shell
./gradlew buildImage
```

Load jib image to docker

```shell
docker load < build/jib-image.tar
```

Build raw image
```shell
docker build -t ktor-sample-all:1.0.0 .
```

Run raw image
```shell
docker run -p 8080:8080 ktor-sample-all:1.0.0
```

Create a kubernetes cluster in kind

```shell
kind create cluster
```

Load docker image to kind

```shell
kind load docker-image ktor-sample-image:1.0.0
```

Create a kubernetes cluster in kind

```shell
kind delete cluster
```

Apply kubernetes file

```shell
kubectl --context kind-kind apply -f Kubernetes.yaml
```

Delete kubernetes file

```shell
kubectl --context kind-kind delete -f Kubernetes.yaml
```

Redirect traffic from service to localport

```shell
kubectl --context kind-kind port-forward service/ktor-sample-service 8080:8080
```

Review pods cpu and memory usage

```shell
kubectl top pods
```