If are trying to run from consol use following commmand

If Profile = dev 
java -jar -Dspring.profiles.active=dev backbase-0.0.1-SNAPSHOT.jar

dev profile is for reading and storing data from h2.

If profile = test
it will read from memory again with test profile

IF Profile = default (no profile selected)
It will read and store from real database I have used postgres db.



