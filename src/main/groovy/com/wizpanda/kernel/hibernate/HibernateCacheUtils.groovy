package com.wizpanda.kernel.hibernate

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.hibernate.SessionFactory

/**
 * A simple utility class to provide session & transaction utility methods for Grails + Hibernate.
 *
 * @author Shashank Agrawal
 */
@Slf4j
@CompileStatic
@Singleton(strict = false)
class HibernateCacheUtils {

    private SessionFactory sessionFactory

    private HibernateCacheUtils() {
        HibernateSessionUtils
        this.sessionFactory = HibernateSessionUtils.instance.getSessionFactory()
    }

    /**
     * Clear/evict the entire 2nd level cache of Hibernate.
     * @return
     */
    void evictSecondLevelCache() {
        log.debug "Evicting 2nd level cache"
        sessionFactory.getCache().evictAllRegions()
    }

    void evictEntityRegionCache(Set<Class> classes) {
        log.debug "Evicting entity data from 2nd level cache"

        classes.each { Class className ->
            sessionFactory.getCache().evictEntityData(className)
        }

        sessionFactory.getCache().evictQueryRegions()
        log.debug "2nd level query cache evicted"
    }
}
