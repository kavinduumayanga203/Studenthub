package com.nsbm.studenthub.controller;

import com.nsbm.studenthub.entity.Student;
import com.nsbm.studenthub.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

 private final StudentRepository repo;

 public StudentController(StudentRepository repo) {
  this.repo = repo;
 }

 @GetMapping
 public Page<Student> getAll(
   @RequestParam(defaultValue="0") int page,
   @RequestParam(defaultValue="5") int size,
   @RequestParam(defaultValue="id") String sort) {

  Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
  return repo.findAll(pageable);
 }

 @PostMapping
 public Student save(@RequestBody Student s) {
  return repo.save(s);
 }

 @GetMapping("/{id}")
 public Student get(@PathVariable Long id) {
  return repo.findById(id).orElseThrow();
 }

 @DeleteMapping("/{id}")
 public void delete(@PathVariable Long id) {
  repo.deleteById(id);
 }
}
