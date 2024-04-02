USE ventassamil

-- Índices para la tabla "caja":
CREATE INDEX idx_monto_Actual_caja ON caja (monto_Actual);

-- Índices para la tabla "cliente":
-- CREATE INDEX idx_id_cliente ON cliente (id);
-- CREATE INDEX idx_nombre_cliente ON cliente (nombre);
-- CREATE INDEX idx_correo_cliente ON cliente (correo);

-- Índices para la tabla "producto":
CREATE INDEX idx_id_producto ON producto (id);
CREATE INDEX idx_size_producto ON producto (size);
CREATE INDEX idx_prioridad_producto ON producto (prioridad);

-- Índices para la tabla "inventario":
CREATE INDEX idx_idProducto_inventario ON inventario (idProducto);
CREATE INDEX idx_entrada_inventario ON inventario (entrada);
CREATE INDEX idx_salida_inventario ON inventario (salida);
CREATE INDEX idx_stock_inventario ON inventario (stock);

-- Índices para la tabla "proveedor":
CREATE INDEX idx_id_proveedor ON proveedor (id);
CREATE INDEX idx_nombre_proveedor ON proveedor (nombre);
CREATE INDEX idx_descripcion_proveedor ON proveedor (descripcion);
CREATE INDEX idx_correo_proveedor ON proveedor (correo);

-- Índices para la tabla "detalleproducto":
CREATE INDEX idx_id_detalleproducto ON detalleproducto (id);
CREATE INDEX idx_idProducto_detalleproducto ON detalleproducto (idProducto);
CREATE INDEX idx_idProveedor_detalleproducto ON detalleproducto (idProveedor);

-- Índices para la tabla "compra":
CREATE INDEX idx_id_compra ON compra (id);
CREATE INDEX idx_idProveedor_compra ON compra (idProveedor);
CREATE INDEX idx_fecha_compra ON compra (fecha);

-- Índices para la tabla "venta":
CREATE INDEX idx_id_venta ON venta (id);
CREATE INDEX idx_nombreCliente_venta ON venta (nombreCliente);
CREATE INDEX idx_total_venta ON venta (total);
CREATE INDEX idx_fecha_venta ON venta (fecha);

-- Índices para la tabla "detalleventa":
CREATE INDEX idx_id_detalleventa ON detalleventa (id);
CREATE INDEX idx_idVenta_detalleventa ON detalleventa (idVenta);
CREATE INDEX idx_idProducto_detalleventa ON detalleventa (idProducto);

-- Índices para la tabla "cuentaporcobrar":
CREATE INDEX idx_id_cuentaporcobrar ON cuentaporcobrar (id);
CREATE INDEX idx_idVenta_cuentaporcobrar ON cuentaporcobrar (idVenta);
CREATE INDEX idx_deuda_cuentaporcobrar ON cuentaporcobrar (deuda);
CREATE INDEX idx_totalFaltante_cuentaporcobrar ON cuentaporcobrar (totalFaltante);
CREATE INDEX idx_fecha_cuentaporcobrar ON cuentaporcobrar (fecha);
CREATE INDEX idx_descripcion_cuentaporcobrar ON cuentaporcobrar (descripcion);

-- Índices para la tabla "cuentaporpagar":
CREATE INDEX idx_id_cuentaporpagar ON cuentaporpagar (id);
CREATE INDEX idx_idCompra_cuentaporpagar ON cuentaporpagar (idCompra);
CREATE INDEX idx_idProveedor_cuentaporpagar ON cuentaporpagar (idProveedor);
CREATE INDEX idx_deuda_cuentaporpagar ON cuentaporpagar (deuda);
CREATE INDEX idx_totalFaltante_cuentaporpagar ON cuentaporpagar (totalFaltante);
CREATE INDEX idx_fecha_cuentaporpagar ON cuentaporpagar (fecha);

-- Índices para la tabla "detallecompra":
CREATE INDEX idx_id_detallecompra ON detallecompra (id);
CREATE INDEX idx_idCompra_detallecompra ON detallecompra (idCompra);
CREATE INDEX idx_idProducto_detallecompra ON detallecompra (idProducto);
CREATE INDEX idx_cantidadP_detallecompra ON detallecompra (cantidadP);
CREATE INDEX idx_precioCompra_detallecompra ON detallecompra (precioCompra);

-- Índices para la tabla "salidacaja":
CREATE INDEX idx_id_salidacaja ON salidacaja (id);
CREATE INDEX idx_idCaja_salidacaja ON salidacaja (idCaja);
CREATE INDEX idx_monto_salidacaja ON salidacaja (monto);
CREATE INDEX idx_fecha_salidacaja ON salidacaja (fecha);

-- Índices para la tabla "ingresos":
CREATE INDEX idx_id_ingresos ON ingresos (id);
CREATE INDEX idx_concepto_ingresos ON ingresos (concepto);
CREATE INDEX idx_monto_ingresos ON ingresos (monto);
CREATE INDEX idx_fecha_ingresos ON ingresos (fecha);

-- Índices para la tabla "gastos":
CREATE INDEX idx_id_gastos ON gastos (id);
CREATE INDEX idx_concepto_gastos ON gastos (concepto);
CREATE INDEX idx_monto_gastos ON gastos (monto);
CREATE INDEX idx_fecha_gastos ON gastos (fecha);

-- Índices para la tabla "historial":
CREATE INDEX idx_id_historial ON historial (id);
CREATE INDEX idx_idUsuario_historial ON historial (idUsuario);
CREATE INDEX idx_login_historial ON historial (login);
CREATE INDEX idx_logout_historial ON historial (logout);

-- Índices para la tabla "PagoCC":
CREATE INDEX idx_id_pagocc ON PagoCC (id);
CREATE INDEX idx_idVenta_pagocc ON PagoCC (idVenta);
CREATE INDEX idx_monto_pagocc ON PagoCC (monto);
CREATE INDEX idx_fecha_pagocc ON PagoCC (fecha);

-- Índices para la tabla "PagoCP":
CREATE INDEX idx_id_pagocp ON PagoCP (id);
CREATE INDEX idx_idCompra_pagocp ON PagoCP (idCompra);
CREATE INDEX idx_monto_pagocp ON PagoCP (monto);
CREATE INDEX idx_fecha_pagocp ON PagoCP (fecha);

-- Índices para la tabla "HistorialCaja":
CREATE INDEX idx_id_historialcaja ON HistorialCaja (id);
CREATE INDEX idx_idCaja_historialcaja ON HistorialCaja (idCaja);
CREATE INDEX idx_usuarioId_historialcaja ON HistorialCaja (usuarioId);
CREATE INDEX idx_fecha_Apertura_historialcaja ON HistorialCaja (fecha_Apertura);
CREATE INDEX idx_monto_Apertura_historialcaja ON HistorialCaja (monto_Apertura);
CREATE INDEX idx_fecha_Cierre_historialcaja ON HistorialCaja (fecha_Cierre);
CREATE INDEX idx_monto_Cierre_historialcaja ON HistorialCaja (monto_Cierre);


