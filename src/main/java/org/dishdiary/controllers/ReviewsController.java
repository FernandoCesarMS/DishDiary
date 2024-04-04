package org.dishdiary.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewsController {
    @RequestMapping(value="/api/reviews", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getReviews(){
        return new ResponseEntity<>("Valor retornado com sucesso", HttpStatus.OK);
    }
}
