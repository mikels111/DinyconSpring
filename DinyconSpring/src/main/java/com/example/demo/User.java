package com.example.demo;
import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private int enabled;
    private int role;

    public User(Long id, String username,String password,int enabled,int role){
        this.id=id;
        this.username=username;
        this.password=password;
        this.enabled=enabled;
        this.role=role;
    }

}
