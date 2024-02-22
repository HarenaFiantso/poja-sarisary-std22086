package hei.school.sarisary.repository.model;

import hei.school.sarisary.PojaGenerated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@PojaGenerated
@Entity
@Getter
@Setter
public class BlackAndWhiteEntity {
  @Id
  private String id;

  private String original;
  private String edited;
  private String operation;
  private Timestamp time;
}
