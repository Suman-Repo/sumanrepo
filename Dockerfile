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

# Expose port 3000, where the React app will run
EXPOSE 3000

# Start the app using react-scripts
CMD ["npm", "start"]

