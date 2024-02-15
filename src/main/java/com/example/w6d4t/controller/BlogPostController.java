package com.example.w6d4t.controller;


import com.cloudinary.Cloudinary;
import com.example.w6d4t.exception.NotFoundException;
import com.example.w6d4t.model.BlogPost;
import com.example.w6d4t.model.BlogPostRequest;
import com.example.w6d4t.model.CustomResponse;
import com.example.w6d4t.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;
@Autowired
    private Cloudinary cloudinary;

    @GetMapping("/post")
    public ResponseEntity<CustomResponse> getAll(Pageable pageable){
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), blogPostService.cercaTuttiIBlogPosts(pageable), HttpStatus.OK);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @GetMapping("/post/{id}")
    public ResponseEntity<CustomResponse> getBlogPostById(@PathVariable int id){
        try{
            return CustomResponse.success(HttpStatus.OK.toString(), blogPostService.cercaPostPerId(id), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/post")
    public ResponseEntity<CustomResponse> saveBlogPost(@RequestBody @Validated BlogPostRequest blogPostRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CustomResponse.error(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).toList().toString(), HttpStatus.BAD_REQUEST);
        }  try {
                return CustomResponse.success(HttpStatus.OK.toString(), blogPostService.salvaBlogPost(blogPostRequest), HttpStatus.OK);
            } catch (NotFoundException e) {
                return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    @PutMapping("/post/{id}")
    public ResponseEntity<CustomResponse>  updateBlogPost(@PathVariable int id, @RequestBody @Validated BlogPostRequest blogPostRequest,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return CustomResponse.error(bindingResult.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        try{
            return CustomResponse.success(HttpStatus.OK.toString(),  blogPostService.aggiornaBlogPost(id, blogPostRequest), HttpStatus.OK);
        }
        catch (NotFoundException e){
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return CustomResponse.error(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/post/{id}")
    public ResponseEntity<CustomResponse> deleteBlogPost(@PathVariable int id) {
        blogPostService.cancellaBlogPost(id);
        try {
            return CustomResponse.emptyResponse("Auto con id=" + id + " cancellata", HttpStatus.OK);
        } catch (NotFoundException e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return CustomResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }}
        @PatchMapping("/post/{id}/upload")
        public ResponseEntity<CustomResponse> uploadLogo ( @PathVariable int id, @RequestParam("upload") MultipartFile file){
            try {
                BlogPost blogPost = blogPostService.uploadLogo(id, (String) cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));
                return CustomResponse.success(HttpStatus.OK.toString(), blogPost, HttpStatus.OK);
            } catch (IOException e) {
                return CustomResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
