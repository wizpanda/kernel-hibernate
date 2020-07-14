package com.wizpanda.kernel.hibernate.id

import grails.compiler.GrailsCompileStatic
import org.bson.types.ObjectId
import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator

/**
 * A simple domain identity generator (hibernate generator strategy) which generates a unique id using MongoDB's
 * {@link ObjectId}. The advantage of using MongoDB's ObjectId over UUID is that the length of ObjectId generated is lesser (24 characters)
 * than UUID (32 characters) length hence saves database space.
 *
 * @author Shashank Agrawal
 * @see org.hibernate.id.factory.internal.DefaultIdentifierGeneratorFactory
 */
@GrailsCompileStatic
class CustomIdGenerator implements IdentifierGenerator {

    @Override
    Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return new ObjectId().toHexString()
    }

}
