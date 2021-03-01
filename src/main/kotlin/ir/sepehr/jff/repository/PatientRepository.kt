package ir.sepehr.jff.repository

import ir.sepehr.jff.document.Patient
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PatientRepository : MongoRepository<Patient, String> {
    fun findOneById(id: ObjectId): Patient
    override fun deleteAll()
}
