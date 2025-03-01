-- Заполнение таблицы lots случайными данными
INSERT INTO lots (date, oil_depot_id, fuel_id, price_per_ton, total_price, volume_of_fuel, status, remaining_fuel)
SELECT
    -- Генерация случайной даты в 2026 году
    DATE '2026-01-01' + (random() * 365)::int AS date,

    -- Случайный id нефтебазы
    oil_depot_id,

    -- Случайный id топлива
    fuel_id,

    -- Цена за 1 тонну (от 40 000 до 60 000 рублей)
    price_per_ton,

    -- Общая стоимость лота (цена за тонну * объем)
    round(price_per_ton * volume_of_fuel, 2)  AS total_price,

    -- Стартовый вес (от 10 000 до 100 000 литров)
    volume_of_fuel,

    -- Статус "ACCEPTED"
    'ACCEPTED'                                AS status,

    -- Доступный остаток (по умолчанию равен стартовому весу)
    volume_of_fuel                            AS remaining_fuel

FROM (
         -- Генерация 20 случайных лотов
         SELECT (SELECT id FROM oil_depots ORDER BY random() LIMIT 1) AS oil_depot_id,
                (SELECT id FROM fuels ORDER BY random() LIMIT 1)      AS fuel_id,
                round((40000 + (random() * 20000))::numeric, 2)       AS price_per_ton,
                round((10000 + (random() * 90000))::numeric)          AS volume_of_fuel
         FROM generate_series(1, 1000)) AS lots_data;
