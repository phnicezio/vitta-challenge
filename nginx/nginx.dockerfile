FROM nginx:latest
MAINTAINER Paulo Nicezio
COPY /nginx/config/nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
ENTRYPOINT ["nginx"]
# Parametros extras para o entrypoint
CMD ["-g", "daemon off;"]