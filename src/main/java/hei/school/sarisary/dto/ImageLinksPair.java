package hei.school.sarisary.dto;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageLinksPair {
  private URL originalLink;
  private URL processedLink;
}
