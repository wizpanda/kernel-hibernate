package com.wizpanda.kernel.hibernate

import grails.util.Holders
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.resource.transaction.spi.TransactionStatus

/**
 * A simple utility class to provide session & transaction utility methods for Grails + Hibernate.
 *
 * @author Shashank Agrawal
 */
@Slf4j
@CompileStatic
@Singleton(strict = false)
class HibernateSessionUtils {

    private final SessionFactory sessionFactory

    private HibernateSessionUtils() {
        sessionFactory = Holders.getApplicationContext().getBean("sessionFactory") as SessionFactory
    }

    /**
     * Get the {@link SessionFactory} instance.
     * @return
     */
    SessionFactory getSessionFactory() {
        return sessionFactory
    }

    /**
     * Flush & clear the current ongoing Hibernate's {@link Session}.
     */
    void flushAndClear() {
        Session session = getSessionFactory().currentSession
        session.flush()
        session.clear()
    }

    /**
     * Create a new {@link Transaction} within the current {@link Session}.
     */
    Transaction newTransaction() {
        sessionFactory.currentSession.beginTransaction()
    }

    /**
     * Commit the ongoing {@link Transaction} only it is active
     */
    boolean commitTransaction() {
        Transaction transaction = sessionFactory.currentSession.transaction
        if (transaction.status == TransactionStatus.ACTIVE) {
            transaction.commit()
            return true
        } else {
            log.warn "Could not commit current {$transaction.status} Transaction because it is not ACTIVE"
            return false
        }
    }
}
