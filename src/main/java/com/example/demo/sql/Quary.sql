CREATE DATABASE mobileshop;

USE mobileshop;

CREATE TABLE Customers (
                           id VARCHAR(10) PRIMARY KEY,
                           name VARCHAR(100),
                           address VARCHAR(100),
                           salary DECIMAL(10, 2)
);

CREATE TABLE Items (
                       code VARCHAR(10) PRIMARY KEY,
                       name VARCHAR(100),
                       price DECIMAL(10, 2),
                       quantity INT
);

CREATE TABLE Orders (
                        oid VARCHAR(10) PRIMARY KEY,
                        date DATE,
                        customerID VARCHAR(10),
                        FOREIGN KEY (customerID) REFERENCES Customers(id)
);

CREATE TABLE OrderDetails (
                              oid VARCHAR(10),
                              code VARCHAR(10),
                              qty INT,
                              payment DECIMAL(10, 2),
                              PRIMARY KEY (oid, code),
                              FOREIGN KEY (oid) REFERENCES Orders(oid),
                              FOREIGN KEY (code) REFERENCES Items(code)
);