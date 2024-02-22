package hei.school.sarisary.repository.model;

import hei.school.sarisary.PojaGenerated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@PojaGenerated
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlackAndWhite {
  @Id private String imageId;

  private String originalImagePath;
  private String processedImagePath;
  private String processingOperation;
  private Timestamp processingDate;
}
