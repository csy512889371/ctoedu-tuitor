# 调用接口方式一

```xml
user: 
  userServicePath: http://localhost:7900/simple/
```

```java
@RestController
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;

  @Value("${user.userServicePath}")
  private String userServicePath;

  @GetMapping("/movie/{id}")
  public User findById(@PathVariable Long id) {
    return this.restTemplate.getForObject(this.userServicePath + id, User.class);
  }
}

```

# 调用接口方式二 Feign
UserFeignClient


