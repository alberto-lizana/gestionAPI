
/* POBLAMIENTO DE ROLES */
INSERT INTO roles(nombre) 
    VALUES  ('ROLE_ADMINISTRADOR'), ('ROLE_GESTION_INVENTARIO'), ('ROLE_USUARIO');


/*POBLAMIENTO DE TIPOS DE PRODUCTO */
INSERT INTO tipo_producto (nombre, descripcion)
    VALUES
        ('Perifericos', 'Teclados, mouse, audifonos y otros perifericos'),
        ('Tarjetas graficas', 'GPU dedicadas para gaming y trabajo'),
        ('Procesadores', 'CPU para computadores de escritorio'),
        ('Memorias RAM', 'Modulos de memoria DDR4 y DDR5'),
        ('Almacenamiento', 'Unidades SSD y otros dispositivos de almacenamiento'),
        ('Placas madre', 'Placas base para procesadores AMD e Intel'),
        ('Monitores', 'Monitores para gaming y productividad'),
        ('Gabinetes', 'Gabinetes para computadores de escritorio'),
        ('Fuentes de poder', 'Fuentes de alimentacion para computadores'),
        ('Consolas', 'Consolas de videojuegos');

INSERT INTO productos (nombre, precio, stock, tipo_producto_id, fecha_creacion, fecha_actualizacion)
    VALUES
        ('Teclado mecanico Redragon Kumara', 34990, 15, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Perifericos'), SYSDATE, SYSDATE),
        ('Mouse Logitech G502 Hero', 44990, 12, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Perifericos'), SYSDATE, SYSDATE),
        ('Audifonos HyperX Cloud II', 79990, 8, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Perifericos'), SYSDATE, SYSDATE),
        ('Tarjeta grafica NVIDIA RTX 4060', 329990, 5, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Tarjetas graficas'), SYSDATE, SYSDATE),
        ('Tarjeta grafica NVIDIA RTX 4070 SUPER', 699990, 3, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Tarjetas graficas'), SYSDATE, SYSDATE),
        ('Procesador AMD Ryzen 5 5600', 119990, 10, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Procesadores'), SYSDATE, SYSDATE),
        ('Procesador AMD Ryzen 7 7800X3D', 399990, 4, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Procesadores'), SYSDATE, SYSDATE),
        ('Memoria RAM Kingston Fury 16 GB DDR4', 29990, 20, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Memorias RAM'), SYSDATE, SYSDATE),
        ('Memoria RAM Corsair Vengeance 32 GB DDR5', 89990, 10, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Memorias RAM'), SYSDATE, SYSDATE),
        ('SSD NVMe Samsung 990 EVO 1 TB', 89990, 12, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Almacenamiento'), SYSDATE, SYSDATE),
        ('Placa madre MSI B550M PRO-VDH', 109990, 6, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Placas madre'), SYSDATE, SYSDATE),
        ('Monitor LG UltraGear 24 pulgadas', 159990, 7, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Monitores'), SYSDATE, SYSDATE),
        ('Gabinete Corsair 4000D Airflow', 99990, 5, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Gabinetes'), SYSDATE, SYSDATE),
        ('Fuente de poder Corsair RM750e 750W', 109990, 6, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Fuentes de poder'), SYSDATE, SYSDATE),
        ('PlayStation 5', 549990, 4, 
            (SELECT id FROM tipo_producto WHERE nombre = 'Consolas'), SYSDATE, SYSDATE);