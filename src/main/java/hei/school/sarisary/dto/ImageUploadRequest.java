package hei.school.sarisary.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@RequiredArgsConstructor
public class ImageUploadRequest {
  private MultipartFile image;
}
