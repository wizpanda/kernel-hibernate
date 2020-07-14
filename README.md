# Hibernate utility for "Grails + Hibernate" application

This is a simple [Grails](https://grails.org/) plugin to provide some common utility classes & methods which is used in almost all
 applications. 
 
## Support Grails

Only Grails 4+ applications are supported.

## Uses

### Provide a 24 character unique id using `CustomIdDomain`

By default, Grails domain uses the auto-increment `Long id` in every domain classes. This `id` is not secure for many scenarios as it is
guessable. An alternative to this approach is to use Hibernate's UUID generator-

```groovy
static mapping = {
    id generator: "uuid", length: 32
}
```
The problem here is that the length of the `id` is quite long i.e. 32 characters. So you can use `CustomIdDomain` here. To use this, just
extend this class to your domain class and remove any `String id` property & it's `mapping` (if added)-

```groovy
package com.wizpanda.user

import com.wizpanda.kernel.hibernate.id.CustomIdDomain

class User extends CustomIdDomain {

    // Note- If there is no mapping defined, make sure to add an empty mapping block as defined below
    static mapping = {
    }

    String email
    String firstName
}
```

### `HibernateSessionUtils`

This is a utility class to provide some methods. For example, you can use `HibernateSessionUtils.flushAndClear()` when bulk importing
some Grails domain records to prevent the servers from getting out of memory. For example, the below code creates 1 Million domain
records in bulk. After a certain number of records, your server will end-up with no memory left because Grails caches those records
until the session is flushed.

To prevent that, we added a check which will flush & clear the Hibernate's current session-  

```groovy
(1..1000000).eachWithIndex { int number, int index ->
    
    User user = new User(name: "John Doe", email: "john${number}@example.com")
    user.save()

    if (index % 1000 == 0) {
        HibernateSessionUtils.flushAndClear()
    }
}
```
