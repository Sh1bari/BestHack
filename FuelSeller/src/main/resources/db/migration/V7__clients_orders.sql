CREATE TABLE clients
(
    id          UUID NOT NULL,
    mail        VARCHAR,
    name        VARCHAR,
    middle_name VARCHAR,
    surname     VARCHAR,
    full_name   VARCHAR,
    create_at   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_clients PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    date          TIMESTAMP WITHOUT TIME ZONE,
    lot_id        BIGINT,
    oil_depot_id  BIGINT,
    fuel_id       BIGINT,
    volume        DOUBLE PRECISION,
    delivery_type VARCHAR(255),
    client_id     UUID,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_FUEL FOREIGN KEY (fuel_id) REFERENCES fuels (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_LOT FOREIGN KEY (lot_id) REFERENCES lots (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_OIL_DEPOT FOREIGN KEY (oil_depot_id) REFERENCES oil_depots (id);