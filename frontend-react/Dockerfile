FROM node:24-alpine AS build

WORKDIR /app
COPY package*.json ./
RUN npm install

COPY . .
ARG VITE_ANGULAR_MFE_URL=/mfe/
ENV VITE_ANGULAR_MFE_URL=${VITE_ANGULAR_MFE_URL}
RUN npm run build

FROM nginx:1.27-alpine

COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80
