package com.berk.controller;

import com.berk.repository.entity.Movie;
import com.berk.repository.entity.User;
import com.berk.service.GenreService;
import com.berk.service.MovieCommentService;
import com.berk.service.MovieService;
import com.berk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
private  final MovieService movieService;
private final GenreService genreService;
private final UserService userService;
private final MovieCommentService movieCommentService;


      @GetMapping("/findall")
      public ModelAndView getMoviePage(Long userId){
          User user;
          ModelAndView modelAndView=new ModelAndView();
          if (userId==null){
//              user=new User();
              modelAndView.addObject("user",null);
          }else{
              user=userService.findById(userId).get();
              modelAndView.addObject("user",user);
          }
          List<Movie> movieList=movieService.findAll();
          modelAndView.addObject("movies",movieList);
          modelAndView.addObject("genreservice",genreService);
      //    modelAndView.addObject("genres",genreService.findAll());
          ;
          modelAndView.setViewName("movies");
          return modelAndView;
      }

        @GetMapping("/findbyid/{id}")
        public ModelAndView getMovieById(@PathVariable Long id,@RequestParam(required = false) Long userId){
          //movie/findbyid/1
          ModelAndView modelAndView=new ModelAndView();
            User user;
            if (userId==null){
                modelAndView.addObject("user",null);
            }else{
                user=userService.findById(userId).get();
                modelAndView.addObject("user",user);
            }

          Optional<Movie> movie=movieService.findById(id);
          modelAndView.addObject("movie",movie.get());
          modelAndView.addObject("genreservice",genreService);
          modelAndView.addObject("commentservice",movieCommentService);
          modelAndView.addObject("userservice",userService);
          modelAndView.setViewName("moviesDetail");
          return modelAndView;
        }


}
