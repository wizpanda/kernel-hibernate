package com.wizpanda.kernel.hibernate

import grails.plugins.Plugin

class KernelHibernateGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "4.0.3 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def title = "Kernel Hibernate"
    def author = "Shashank Agrawal"
    def authorEmail = "creative@wizpanda.com"
    def description = "A Grails plugin to provide some utility classes for GORM with Hibernate"
}
