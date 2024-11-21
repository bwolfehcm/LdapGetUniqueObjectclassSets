# LdapGetUniqueObjectclassSets

Small java program that reads an LDIF file and reports back all of the Unique Sets of Objectclasses in use.

Requires the unboundid-ldapsdk that is listed as a dependency in the pom.xml. Uses maven to pull the dependency when building the project. the jar file needs to be in the classpath to run the command.

```java -jar .\ldapGetUniqueObjectSets-1.0-SNAPSHOT.jar example-com.ldif```