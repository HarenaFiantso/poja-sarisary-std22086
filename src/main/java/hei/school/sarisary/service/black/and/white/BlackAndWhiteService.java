package hei.school.sarisary.service.black.and.white;

import hei.school.sarisary.dto.ImageLinksPair;
import hei.school.sarisary.file.BucketComponent;
import hei.school.sarisary.file.FileHash;
import hei.school.sarisary.repository.BlackAndWhiteRepository;
import hei.school.sarisary.repository.model.BlackAndWhite;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BlackAndWhiteService {

  private final ImageConversionService imageConversionService;
  private final BlackAndWhiteRepository blackAndWhiteRepository;
  private final BucketComponent bucketComponent;

  public List<FileHash> applyBlackAndWhiteFilter(MultipartFile file, String key)
      throws IOException {
    List<FileHash> fileHashes = new ArrayList<>();
    String fileSuffix =
        Objects.requireNonNullElse(file.getContentType(), "application/tmp").split("/")[0];
    String originalFileKey = String.format("og_%s", key);
    String processedFileKey = String.format("pc_%s_bw", key);
    File originalImage = File.createTempFile(originalFileKey, "." + fileSuffix);
    File processedImage =
        imageConversionService.savedAsFile(
            convertBufferType(ImageIO.read(file.getInputStream())),
            Objects.requireNonNullElse(file.getContentType(), "unsupported"),
            String.format("%s.%s", processedFileKey, fileSuffix));
    ImageIO.write(ImageIO.read(file.getInputStream()), fileSuffix, originalImage);
    saveImages(key);
    fileHashes.add(bucketComponent.upload(originalImage, originalFileKey));
    fileHashes.add(bucketComponent.upload(processedImage, processedFileKey));
    return fileHashes;
  }

  private BufferedImage convertBufferType(BufferedImage image) {
    BufferedImage convertedImage =
        new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
    Graphics imageGraphics = convertedImage.getGraphics();
    imageGraphics.drawImage(image, 0, 0, null);
    imageGraphics.dispose();
    return convertedImage;
  }

  private void saveImages(String key) {
    String originalFileKey = String.format("og_%s", key);
    String processedFileKey = String.format("pc_%s_%s", key, "black_and_white");
    blackAndWhiteRepository.save(
        BlackAndWhite.builder()
            .imageId(key)
            .originalImagePath(originalFileKey)
            .processedImagePath(processedFileKey)
            .processingDate(Timestamp.from(Instant.now()))
            .processingOperation("black_and_white")
            .build());
  }

  public ImageLinksPair getImages(String key) {
    Optional<BlackAndWhite> imageProcess = blackAndWhiteRepository.findById(key);
    return imageProcess
        .map(
            process ->
                new ImageLinksPair(
                    bucketComponent.presign(process.getOriginalImagePath(), Duration.ofMinutes(2)),
                    bucketComponent.presign(process.getProcessedImagePath(), Duration.ofMinutes(2))))
        .orElse(null);
  }
}
