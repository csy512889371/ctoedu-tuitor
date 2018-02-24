# 目前，在Spring cloud 中服务之间通过restful方式调用有两种方式 
- restTemplate+Ribbon 
- feign

从实践上看，采用feign的方式更优雅（feign内部也使用了ribbon做负载均衡）。

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

## 自定义连接

```java
@FeignClient(name = "xxxx", url = "http://localhost:8761/", configuration = Configuration2.class)
public interface FeignClient2 {
  @RequestMapping(value = "/eureka/apps/{serviceName}")
  public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName);
}
```


## 解决第一次请求报超时异常的方案

```xml

# 解决第一次请求报超时异常的方案：
# hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 5000
# 或者：
# hystrix.command.default.execution.timeout.enabled: false
# 或者：
feign.hystrix.enabled: false ## 索性禁用feign的hystrix支持

# 超时的解决方案： http://stackoverflow.com/questions/27375557/hystrix-command-fails-with-timed-out-and-no-fallback-available
# hystrix配置： https://github.com/Netflix/Hystrix/wiki/Configuration#execution.isolation.thread.timeoutInMilliseconds

```

## 相关配置

```java
@Configuration
public class Configuration1 {
  @Bean
  public Contract feignContract() {
    return new feign.Contract.Default();
  }

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}


@Configuration
public class Configuration2 {
  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    return new BasicAuthRequestInterceptor("user", "password123");
  }

  @Bean
  @Scope("prototype")
  public Feign.Builder feignBuilder() {
    return Feign.builder();
  }
}

```

## 熔断器

pom.xml

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
</dependency>
```

```java
@FeignClient(name = "microservice-provider-user", fallback = HystrixClientFallback.class)
public interface UserFeignClient {
  @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id);
}

@Component
public class HystrixClientFallback implements UserFeignClient {

  @Override
  public User findById(Long id) {
    User user = new User();
    user.setId(0L);
    return user;
  }
}

```

工厂方式
```java
@FeignClient(name = "microservice-provider-user", fallbackFactory = HystrixClientFactory.class)
public interface UserFeignClient {
  @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id);
}

@Component
public class HystrixClientFallback implements UserFeignClient {
  @Override
  public User findById(Long id) {
    User user = new User();
    user.setId(0L);
    return user;
  }
}
```
