## Movie Rating Portal

### Member
```
 - Vishwa Deep Ghimire 
 - Rajiv Gyawali 
 - Sushil Subedi 
 - Diptesh Shrestha 
 - Abhay Rawal 
```

## Swagger

#### Dependencies
```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>3.0.0</version>
</dependency>

```
#### Configuration
```
@EnableSwagger2
@Configuration
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
//                .pathMapping("/");
    }
}
```
#### Swagger URL
* HOST: API_GATEWAY:PORT
* API Docs

     http://___HOST/SERVICE-NAME___/v2/api-docs
* Swagger-Ui

    http://___HOST/SERVICE-NAME___/swagger-ui/index.html


Ref Docs:

    https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api