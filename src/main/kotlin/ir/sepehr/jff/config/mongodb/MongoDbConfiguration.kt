package ir.sepehr.jff.config.mongodb

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.client.internal.MongoClientImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoDbConfiguration {
    @Bean
    @Primary
    fun mongoDbFactory(): MultiTenantMongoDbFactory {
        val dbName = "test"
        val uri = "mongodb://localhost:27017"
        val username = ""
        val password = ""

        //val mongoCredential = MongoCredential.createCredential(username, "admin", password.toCharArray())

        val mongoClient = MongoClientImpl(
            MongoClientSettings.builder()
                .applyConnectionString(ConnectionString(uri))
                .build(),
            null
        )
        return MultiTenantMongoDbFactory(mongoClient, dbName)
    }

    @Bean
    @Primary
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoDbFactory())
    }
}
