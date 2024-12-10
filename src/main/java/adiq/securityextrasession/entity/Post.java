package adiq.securityextrasession.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String description;
    @ElementCollection
    List<String> images;
    LocalDate createdDate;
    LocalDate updatedDate;

    @ManyToOne
    User user;

    @PreUpdate
    @PrePersist
    void prePersist() {
        createdDate = LocalDate.now();
        updatedDate = LocalDate.now();
    }
}
