Use http://localhost:8080/api/users/{id} to read user's data.
![image](https://user-images.githubusercontent.com/43272648/199038526-887ac6ec-9967-4f1e-a678-e53fe316576b.png)


According to requirements there must be single REST-endpoint to retreive read user's data. So to test the app by postman you cannot create your own users by POST method.
In order to test the app there is migration file with test-users (db.insert-test-users.xml)

![image](https://user-images.githubusercontent.com/43272648/199034269-33c8947f-4b0e-44c7-b4f8-cfdaa6e73fee.png)

Before running integration tests please comment these insertions.
