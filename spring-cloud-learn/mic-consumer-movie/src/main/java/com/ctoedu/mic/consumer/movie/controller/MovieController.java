package com.ctoedu.mic.consumer.movie.controller;

import com.ctoedu.mic.consumer.movie.entity.User;
import com.ctoedu.mic.consumer.movie.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MovieController {

  @Autowired
  private UserFeignClient userFeignClient;

  @GetMapping("/movie/{id}")
  public User findById(@PathVariable Long id) {
    return this.userFeignClient.findById(id);
  }

  @GetMapping("/test")
  public User testPost(User user) {
    return this.userFeignClient.postUser(user);
  }

  @GetMapping("/test-get")
  public User testGet(User user) {
    return this.userFeignClient.getUser(user);
  }
}
