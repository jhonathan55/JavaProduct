package com.model;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;

public class ProductoServicio {
    ArrayList<Producto> listaProductos = new ArrayList<Producto>();

    public ProductoServicio() {
    }

    public ProductoServicio(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void cargarDatos(String rutaArchivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Producto producto = new Producto(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
                listaProductos.add(producto);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar los datos del archivo");
        }
    }

}
