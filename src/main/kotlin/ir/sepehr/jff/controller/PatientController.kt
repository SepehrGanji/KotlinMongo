package ir.sepehr.jff.controller

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

    @GetMapping
    fun getAllPatients(): ResponseEntity<List<Patient>> {
        val patients = patientsRepository.findAll()
        return ResponseEntity.ok(patients)
    }

    @GetMapping("/{id}")
    fun getOnePatient(@PathVariable("id") id: String): ResponseEntity<Patient> {
        val patient = patientsRepository.findOneById(ObjectId(id))
        return ResponseEntity.ok(patient)
    }

    @PostMapping
    fun createTask(@RequestBody request: PatientReq): ResponseEntity<Patient> {
        val task = patientsRepository.save(Patient(
            name = request.name,
            description = request.description
        ))
        return ResponseEntity(task, HttpStatus.CREATED)
    }
}
