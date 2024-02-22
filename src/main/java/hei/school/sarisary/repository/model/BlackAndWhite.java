package hei.school.sarisary.repository.model;

import hei.school.sarisary.PojaGenerated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@PojaGenerated
@Entity
@Data
@Builder
public class BlackAndWhite {
  @Id private String imageId;

  private String originalImagePath;
  private String processedImagePath;
  private String processingOperation;
  private Timestamp processingDate;

  public BlackAndWhite() {}
}
