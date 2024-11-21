# LdapGetUniqueObjectclassSets

Small java program that reads an LDIF file and reports back all of the Unique Sets of Objectclasses in use.

Requires the unboundid-ldapsdk that is listed as a dependency in the pom.xml. Uses maven to pull the dependency when building the project. the jar file needs to be in the classpath to run the command.

I used netbeans IDE to build the LdapGetUniqueObjectclassSets jar file
```
PS C:\Users\Brian\test> cp C:\Users\Brian\NetBeansProjects\ldapGetUniqueObjectclassSets\example-com.ldif ./
PS C:\Users\Brian\test> cp C:\Users\Brian\NetBeansProjects\ldapGetUniqueObjectclassSets\target\ldapGetUniqueObjectSets-1.0-SNAPSHOT.jar .\
PS C:\Users\Brian\test> cp C:\Users\Brian\.m2\repository\com\unboundid\unboundid-ldapsdk\6.0.0\unboundid-ldapsdk-6.0.0.jar .\

java -jar .\ldapGetUniqueObjectSets-1.0-SNAPSHOT.jar example-com.ldif

```