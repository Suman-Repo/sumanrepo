# Use an official Node.js runtime as the base image
FROM node:16-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install the dependencies
RUN npm install

# Copy the rest of the app's source code to the container
COPY . .

# Building our application
RUN npm run build

# Install Nginx
FROM nginx:alpine

# Copy build files to Nginx's default html directory
COPY --from=0 /app/build /usr/share/nginx/html

# Expose port 80, where the React app will run
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]

