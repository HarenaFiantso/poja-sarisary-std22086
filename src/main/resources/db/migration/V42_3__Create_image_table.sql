CREATE TABLE IF NOT EXISTS processed_images (
    image_id TEXT PRIMARY KEY,
    original_image_path TEXT,
    processed_image_path TEXT NOT NULL,
    processing_operation TEXT NOT NULL,
    processing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
