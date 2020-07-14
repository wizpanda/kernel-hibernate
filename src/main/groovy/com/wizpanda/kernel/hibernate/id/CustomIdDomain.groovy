package com.wizpanda.kernel.hibernate.id

import grails.compiler.GrailsCompileStatic
import grails.gorm.hibernate.mapping.MappingBuilder

/**
 * Class to be implemented by all domains where custom id needs to be generated using {@link CustomIdGenerator} class. This is an
 * alternative approach towards {@link org.hibernate.id.UUIDHexGenerator}.
 *
 * @author Shashank Agrawal
 */
@GrailsCompileStatic
abstract class CustomIdDomain {

    // A empty mapping black needs to be there in the child classes if no mapping is application on child domains
    static mapping = MappingBuilder.orm {
        id generator: CustomIdGenerator.class.name, length: 24
    }

    String id
}
