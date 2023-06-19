package kg.manas.crm.entities;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;



import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "id_generator", strategy = GenerationType.SEQUENCE)
    protected Long id;

    @Column(name="created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdAt;
    @Column(name="updated_at")
    protected LocalDateTime updatedAt;
    @Column(name="deleted_at")
    protected LocalDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
