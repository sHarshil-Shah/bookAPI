# trainAPI

### Check out online
http://65.1.105.69:8082/swagger-ui/

### Public docker image
https://hub.docker.com/r/harshil99/book

### Get it up and running on local device
#### Run following command on terminal
1. sudo yum install docker -y
2. sudo service docker start
3. sudo chmod 666 /var/run/docker.sock
4. docker pull harshil99/book
5. docker run -d -p 8082:8080 harshil99/book



### Prerequisite

1. JDK 1.8 and above
2. H2 Database
3. Maven
    
### Import data to H2

Initial data will be loaded via S3 bucket in init method in DemoApplication

### REST APIs
| Request  | Endpoint | Purpose |
| ------------- | ------------- | ------------- |
| GET  | /api/  | get all books |
| GET  | /api/{id}  | get book by id |
| POST | /api/Book | create book|
| GET | api/search/{title} |  search by title of the book |
