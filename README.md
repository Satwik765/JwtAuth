# JwtAuth
## Authentication using Json web token
### I had tried to add authenthication and authorization to my rest controller using **Json web token**, generated my token using **Jwts** class of **jsonwebtoken** package, and used *HS/SHA 512* algorithm as Cryptographic hash function.
### I have used below code to configure web security, so that signup and signin api endpoints can be accessed without token
#### 
```
@Configuration
@EnableWebSecurity
public class SecurityConfig {
 
    @Autowired
    BearerTokenAuthFilter bearerTokenAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(request -> {
                request.requestMatchers("/auth/SignUp**", "/auth/SignIn**").permitAll();
                request.anyRequest().authenticated();
            })
            .csrf(AbstractHttpConfigurer::disable)
            .addFilterAfter(bearerTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
return http.build();
    }
}
```
