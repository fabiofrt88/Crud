package co.develhope.crud.controllers;

import co.develhope.crud.entities.Car;


import co.develhope.crud.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    // Create
    @PostMapping("")
    public Car create(@RequestBody Car car){
        return carRepository.saveAndFlush(car);
    }

    // Read one
    @GetMapping("")
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    //Return single Car
    @GetMapping("/{id}")
    public Car singleCar(@PathVariable long id) {
        if (carRepository.existsById(id) == true) {
            Car car = carRepository.getById(id);
            return car;
        } else return new Car();
    }

    //Update single Car
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable long id,  @RequestParam String type) throws ClassNotFoundException {
        Car car;
        if (carRepository.existsById(id)){
            car = carRepository.getById(id);
            car.setType(type);
            car = carRepository.saveAndFlush(car);
        }else{
            car = new Car();
        }
        return car;
    }

    //Delete single Car
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id, HttpServletResponse response){
        if (carRepository.existsById(id)){
            carRepository.deleteById(id);
        }else response.setStatus(407);

        carRepository.deleteById(id);
    }

    //Delete all Car
    @DeleteMapping("")
    public void deleteCar(){
        carRepository.deleteAll();
    }




}
