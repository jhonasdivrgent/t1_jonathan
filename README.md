# Proyecto T1 - Sistema de Compras (Java + Maven + Hibernate)

Este proyecto corresponde al Examen t1 del curso **Lenguaje de Programación II (Cibertec)**.  
Permite gestionar **clientes y sus pedidos** mediante una aplicación de escritorio desarrollada en **Java (Swing)** con **Maven** y **Hibernate** para la persistencia en base de datos MySQL.

---

## 📂 Estructura del Proyecto

```
t1_jonathan/
 ├── src/main/java/com/t1/
 │    ├── model/       # Entidades JPA (Cliente, Pedido)
 │    ├── dao/         # Clases DAO para CRUD (ClienteDAO, PedidoDAO)
 │    ├── util/        # Utilitarios (JPAUtil para conexión a BD)
 │    └── view/        # Interfaces gráficas con Swing (ClienteView, PedidoView, MainView, ListadoPedido)
 ├── src/main/resources/
 │    └── META-INF/persistence.xml   # Configuración de Hibernate/JPA
 ├── bd/               # Scripts SQL para crear la BD y tablas
 ├── pom.xml           # Configuración Maven y dependencias
 └── README.md         # Documentación del proyecto
```

---

## 🗄️ Base de Datos

- Nombre de la BD: **COMPRA**
- Tablas:
  - `clientes` (idcliente PK, nombres, apellidos, celular)
  - `pedidos` (idpedido PK, total_items, precio, idcliente FK)

📌 Los **scripts de creación e inserción de datos** se encuentran en la carpeta [`bd/`](./bd/).

---

## 🔧 Configuración de Conexión

La conexión a MySQL se configura en el archivo:

```
src/main/resources/META-INF/persistence.xml
```

Ahí puedes modificar las propiedades:

```xml
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/COMPRA?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="tu_password"/>
```

---

## 🖥️ Ejecución del Proyecto

1. Ejecutar los scripts SQL en MySQL (`bd/compra.sql`) para crear la base de datos y poblarla con datos de prueba.  
2. Compilar el proyecto con Maven en Eclipse o consola:  
   ```bash
   mvn clean install
   ```
3. Ejecutar la clase principal:  
   ```
   com.t1.view.MainView
   ```
4. Se abrirá la aplicación Swing con las siguientes pestañas:  
   - **Clientes**: registrar y actualizar clientes.  
   - **Pedidos**: registrar y actualizar pedidos.  
   - **Listado de Pedidos**: visualizar todos los pedidos registrados.  

---

## ⚙️ Tecnologías Utilizadas

- **Java 17**  
- **Swing** (interfaces gráficas)  
- **Maven** (gestión de dependencias y build)  
- **Hibernate (JPA)** (mapeo objeto-relacional)  
- **MySQL** (base de datos relacional)  
- **SLF4J** (logging)  

---

## 📌 Funcionalidades

- Registro de clientes con validación de datos obligatorios.  
- Registro de pedidos asociados a clientes existentes.  
- Listado de clientes y pedidos en tablas Swing.  
- Actualización de registros desde la interfaz gráfica.  
- Persistencia automática con Hibernate y JPA.  

---

✍️ **Autor**: Jonathan  
📚 **Curso**: Lenguaje de Programación II – Cibertec
