CREATE TABLE IF NOT EXISTS phones (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    brand VARCHAR(100),
    model VARCHAR(100),
    storage INTEGER,
    price DECIMAL(10, 2),
    os VARCHAR(50),
    quantity INTEGER,
    camera_mp INTEGER,
    has_flashlight BOOLEAN,
    cooling VARCHAR(100),
    provider VARCHAR(100)
);