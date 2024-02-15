package com.example.w6d4t.controller;


import com.example.w6d4t.exception.NotFoundException;
import com.example.w6d4t.model.Autore;
import com.example.w6d4t.model.AutoreRequest;
import com.example.w6d4t.model.CustomResponse;
import com.example.w6d4t.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AutoreController {
    @Autowired
    private AutoreService autoreService;
    @GetMapping("/autori")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.cercaTuttiGliAutori(pageable), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @GetMapping("/autori/{id}")
    public ResponseEntity<CustomResponse> getAutoreById(@PathVariable int id){
        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.cercaAutorePerId(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/autori")
    public ResponseEntity<CustomResponse> saveAutore(@RequestBody @Validated AutoreRequest autoreRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);

        }

        try{
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.salvaAutore(autoreRequest), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/autori/{id}")
    public ResponseEntity<CustomResponse> updateAutore(@PathVariable  int id, @RequestBody @Validated AutoreRequest autoreRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            return CustomResponse.success(HttpStatus.OK.toString(), autoreService.aggiornaAutore(id, autoreRequest), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/autori/{id}")
    public ResponseEntity<CustomResponse> deleteAutore(@PathVariable int id){
        try{
            autoreService.cancellaAutore(id);
            return CustomResponse.emptyResponse("Persona con id=" + id + " cancellata", HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
