package hei.school.sarisary.endpoint.rest.controller.black.and.white;

import hei.school.sarisary.dto.ImageLinksPair;
import hei.school.sarisary.dto.ImageUploadRequest;
import hei.school.sarisary.file.FileHash;
import hei.school.sarisary.service.black.and.white.BlackAndWhiteService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/black-and-white/")
public class BlackAndWhiteController {

  private final BlackAndWhiteService blackAndWhiteService;

  @GetMapping("/{id}")
  public ResponseEntity<ImageLinksPair> retrieveImageLinks(@PathVariable String id) {
    Optional<ImageLinksPair> links = Optional.of(blackAndWhiteService.getImages(id));
    return links.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @PutMapping(
      value = "/{id}",
      consumes = {
        MediaType.MULTIPART_FORM_DATA_VALUE,
      })
  public List<FileHash> applyBlackAndWhiteFilter(
      @ModelAttribute ImageUploadRequest image, @PathVariable String id) throws IOException {
    return blackAndWhiteService.applyBlackAndWhiteFilter(image.getImage(), id);
  }
}
