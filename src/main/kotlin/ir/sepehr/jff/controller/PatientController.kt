package ir.sepehr.jff.controller

import ir.sepehr.jff.config.TenantContext
import ir.sepehr.jff.document.Patient
import ir.sepehr.jff.document.PatientReq
import ir.sepehr.jff.repository.PatientRepository
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/patients")
class PatientController(
    private val patientsRepository: PatientRepository
) {

    @GetMapping("/{tenant}")
    fun getAllPatients(@PathVariable tenant: String): ResponseEntity<List<Patient>> {
        TenantContext.setCurrentTenant(tenant)
        val patients = patientsRepository.findAll()
        return ResponseEntity.ok(patients)
    }
    
    @PostMapping
    fun createPatient(@RequestParam tenant: String, @RequestBody request: PatientReq): ResponseEntity<Patient> {
        TenantContext.setCurrentTenant(tenant)
        val pat = patientsRepository.save(Patient(
            name = request.name,
            description = request.description
        ))
        return ResponseEntity(pat, HttpStatus.CREATED)
    }
}
