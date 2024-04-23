package com.model;

import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private ProductoServicio productoServicio = new ProductoServicio();
    private Scanner sc = new Scanner(System.in);

    public Menu() {

    }

    public Menu(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("1 Listar Producto");
            /*
             * mejorar el editar para validar que el codigo exista y validar que el campo
             * exista
             */
            System.out.println("2 Editar Datos");
            System.out.println("3 Importar Datos");
            /* add new product */
            // exportar datos nuevamente excel o csv
            System.out.println("4 Salir");
            System.out.println("Seleccione una opcion: ");
            opcion = sc.nextInt();
            procesarOpcion(opcion);
        } while (opcion != 4);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                // listarProductos();
                // System.out.println("Listando productos");
                if (productoServicio.getListaProductos().isEmpty()) {
                    System.out.println("No hay productos para mostrar");
                    break;
                }
                productoServicio.getListaProductos().forEach(producto -> {
                    System.out.println("Descripcion: " + producto.getDescripcion());
                    System.out.println("Talla: " + producto.getTalla());
                    System.out.println("Articulo: " + producto.getArticulo());
                    System.out.println("Precio: " + producto.getPrecio());
                    System.out.println("Codigo: " + producto.getCodigo());
                    System.out.println("Marca: " + producto.getMarca());
                    System.out.println("Color: " + producto.getColor());
                    System.out.println("-------------------------------------------------");
                });
                break;
            case 2:
                System.out.println("Ingrese el codigo del producto: ");
                String codigo = sc.next();
                System.out.println("Ingrese el campo a editar: ");
                String campo = sc.next();
                System.out.println("Ingrese el nuevo valor: ");
                String nuevoValor = sc.next();
                if (editarProducto(codigo, campo, nuevoValor)) {
                    System.out.println("Producto editado correctamente");
                } else {
                    System.out.println("No se pudo editar el producto, revise el codigo o el campo ingresado");
                }
                break;
            case 3:
                importarDatos();
                break;
            case 4:
                System.out.println("Saliendo del sistema");

                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private void importarDatos() {
        // System.out.println("Ingrese la ruta del archivo: ");
        // String rutaArchivo = sc.next();
        String rutaArchivo = "C:/Users/jhona/Documents/java/desafio28/src/main/java/com/data/ProductosImportados.csv";
        productoServicio.cargarDatos(rutaArchivo);
    }

    private boolean editarProducto(String codigo, String campo, String nuevoValor) {
        Optional<Producto> productoBuscado = productoServicio.getListaProductos().stream()
                .filter(producto -> producto.getCodigo().equals(codigo)).findFirst();
        if (productoBuscado.isPresent()) {
            Producto producto = productoBuscado.get();
            switch (campo.toLowerCase()) {
                case "descripcion":
                    producto.setDescripcion(nuevoValor);
                    break;
                case "talla":
                    producto.setTalla(nuevoValor);
                    break;
                case "articulo":
                    producto.setArticulo(nuevoValor);
                    break;
                case "precio":
                    producto.setPrecio(nuevoValor);
                    break;
                case "marca":
                    producto.setMarca(nuevoValor);
                    break;
                case "color":
                    producto.setColor(nuevoValor);
                    break;
                default:
                    return false;
            }
            return true;
        }
        return false;
    }

}
