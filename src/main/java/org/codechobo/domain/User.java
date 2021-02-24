package org.codechobo.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    String name;
    int age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date birth;
}
