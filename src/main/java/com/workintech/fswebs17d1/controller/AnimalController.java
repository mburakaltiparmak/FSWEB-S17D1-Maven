package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/workintech/animal")
public class AnimalController {
    private Map<Integer, Animal> animals = new HashMap<>();
    @Value("${project.developer.fullname}")
    private String fullName;
    @Value("${course.name}")
    private String courseName;
    @PostConstruct
    public void loadAll(){
        this.animals=new HashMap<>();
        this.animals.put(1,new Animal(1,"maymun"));
        System.out.println("animalsMap = " + animals);
        System.out.println("fullName = " + fullName);
        System.out.println("courseName = " + courseName);
    }
    @GetMapping
    public List<Animal> getAnimalList(){
        return new ArrayList<>(this.animals.values());
    }
    @GetMapping("/{id}")
    public Animal getAnimalId(@PathVariable("id") Integer id){
        return this.animals.get(id);
    }
    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        this.animals.put(animal.getId(),animal);
    }
    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable("id") Integer id,@RequestBody Animal animal ){
        this.animals.put(id, new Animal(id,animal.getName()));
        return animals.get(id);

    }
    @DeleteMapping("/{id}")
    public Animal deleteAnimal(@PathVariable Integer id){
        Animal animal = animals.get(id);
        animals.remove(id,animal);
        return animal;
    }



}
