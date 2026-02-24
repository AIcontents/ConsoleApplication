CREATE TABLE IF NOT EXISTS parts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    quantity INTEGER NOT NULL,
    price REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    part_id INTEGER,
    quantity INTEGER,
    status TEXT,
    created_at TEXT,
    FOREIGN KEY(part_id) REFERENCES parts(id)
);
