package com.example.first_app;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User {
    private String name;
    private String age;
}

@RestController
@RequestMapping("/api")
public class FirstController {
    
    // Basic GET request
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    // GET with path variable
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable String id) {
        return "User with ID: " + id;
    }

    // GET with request parameters
    @GetMapping("/users")
    public String getUserByQueryParam(@RequestParam(required = false) String name, 
                                    @RequestParam(defaultValue = "18") int age) {
        return "User: " + name + ", Age: " + age;
    }

    // POST with request body
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return user;
    }

    // PUT request to update resource
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        // Update logic here
        return user;
    }

    // PATCH request for partial update
    @PatchMapping("/users/{id}")
    public User partialUpdateUser(@PathVariable String id, 
                                @RequestBody Map<String, Object> updates) {
        // Partial update logic here
        return new User("Updated Name", "Updated Age");
    }

    // DELETE request
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        // Delete logic here
        return ResponseEntity.noContent().build();
    }

    // Return JSON response with specific status
    @GetMapping("/users/json")
    public ResponseEntity<User> getUserJson() {
        User user = new User("John", "25");
        return ResponseEntity.ok(user);
    }

    // Return HTML response
    @GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
    public String getHtml() {
        return "<html><body><h1>Hello Spring Boot!</h1></body></html>";
    }

    // Return file response
    @GetMapping("/file")
    public ResponseEntity<Resource> getFile() {
        try {
            Path path = Paths.get("example.txt");
            Resource resource = new UrlResource(path.toUri());
            
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                    "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Request with headers
    @PostMapping("/headers")
    public ResponseEntity<String> handleHeaders(
            @RequestHeader("Authorization") String auth,
            @RequestHeader Map<String, String> headers) {
        return ResponseEntity.ok("Received headers: " + headers);
    }
}
