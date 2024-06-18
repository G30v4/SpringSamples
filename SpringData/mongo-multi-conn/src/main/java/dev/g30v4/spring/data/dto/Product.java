package dev.g30v4.spring.data.dto;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    private String id;
    @Indexed
    private String name;
    private String description;
    @Builder.Default
    private int version = 1;
    private ZonedDateTime createdAt;
}
