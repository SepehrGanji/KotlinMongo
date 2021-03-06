package ir.sepehr.jff.config

import org.slf4j.LoggerFactory

object TenantContext {
    private val currentTenant: ThreadLocal<String?> = InheritableThreadLocal()
    private val logger = LoggerFactory.getLogger(TenantContext::class.java)

    fun getCurrentTenant(): String? {
        val tenant = currentTenant.get()
        logger.info("TenantContext-MongoDb: Get Tenant = {}", tenant)
        return tenant
    }

    fun setCurrentTenant(tenant: String?) {
        if (tenant != null) {
            logger.info("TenantContext-MongoDb: Set Tenant = {}", tenant)
            currentTenant.set(tenant)
        } else {
            clear()
        }
    }

    fun clear() {
        logger.info("TenantContext-MongoDb: Clear Tenant = {}", getCurrentTenant())
        currentTenant.set(null)
    }
}
