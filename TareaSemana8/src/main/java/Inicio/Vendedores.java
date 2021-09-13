/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;
import datos.ClsVendedorJDBC;
import dominio.ClsVendedores;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author HP
 */
public class Vendedores {
     public static void ej1() {
        //paso 1 definir el string de conexion
        String url = "jdbc:mysql://localhost:3306/dbPrgIIvendedor?zeroDateTimeBehavior=CONVERT_TO_NULL";

        try {
            //paso 2 crear el obj conexion
            Connection conexion = DriverManager.getConnection(url, "root", "Junior2021");
            //paso 3 crear el obj statement
            Statement sentencia = conexion.createStatement();
            //paso 4 crear instruccion sql
            String sql = "select * from tb_PrgIIVendedor";
            //paso 5 ejecutamos el query
            ResultSet resultado = sentencia.executeQuery(sql);

            //paso 6 procesar el resultado
            while (resultado.next()) {
                System.out.println("Codigo=" + resultado.getInt(1));
                System.out.println("Nombre=" + resultado.getString(2));
                System.out.println("Enero=" + resultado.getInt(3));
                System.out.println("Febrero=" + resultado.getInt(4));
                System.out.println("Marzo=" + resultado.getInt(5));
            }

        } catch (SQLException ex) {

            ex.printStackTrace(System.out);
        }
    }

    public static void insert() {
        ClsVendedorJDBC vendedorJDBC = new ClsVendedorJDBC();
        ClsVendedores insert = new ClsVendedores();

        Scanner t = new Scanner(System.in);
        System.out.println("Ingrese Nombre");
        String nombre = t.nextLine();

        System.out.println("Ingrese Enero");
        int enero = t.nextInt();

        System.out.println("Ingrese Febrero");
        int febrero = t.nextInt();

        System.out.println("Ingrese Marzo");
        int marzo = t.nextInt();

        insert.setNombre(nombre);
        insert.setEnero(enero);
        insert.setFebrero(febrero);
        insert.setMarzo(marzo);
        vendedorJDBC.insert(insert);
       

        List<ClsVendedores> todos = vendedorJDBC.seleccion();

        for (ClsVendedores vendedor : todos) {
            System.out.println("vendedor = " + vendedor);
        }
    }

    public static void delete() {
        ClsVendedorJDBC vendedorJDBC = new ClsVendedorJDBC();
        ClsVendedores delete = new ClsVendedores();
        Scanner t = new Scanner(System.in);

        System.out.println("Ingrese el Codigo de Vendedor a Eliminar");
        int codigo = t.nextInt();

        delete.setCodigo_de_vendedor(codigo);
        vendedorJDBC.delete(delete);

        List<ClsVendedores> todos = vendedorJDBC.seleccion();

        for (ClsVendedores vendedor : todos) {
            System.out.println("vendedor = " + vendedor);
        }

    }

    public static void select() {
        ClsVendedorJDBC vendedorJDBC = new ClsVendedorJDBC();
        List<ClsVendedores> todos = vendedorJDBC.seleccion();

        for (ClsVendedores ven : todos) {
            System.out.println("Vendedor:" + ven);
        }

    }

    public static void update() {
        ClsVendedorJDBC vendedorJDBC = new ClsVendedorJDBC();
        ClsVendedores Actual = new ClsVendedores();
        Scanner t = new Scanner(System.in);

        System.out.println("Ingrese el Nombre");
        String nombre = t.nextLine();

        System.out.println("Ingrese enero");
        int enero = t.nextInt();

        System.out.println("Ingrese febrero");
        int febrero = t.nextInt();

        System.out.println("Ingrese marzo");
        int marzo = t.nextInt();

        System.out.println("Ingrese el Codigo de Vendedor");
        int codigo = t.nextInt();

        Actual.setNombre(nombre);
        Actual.setEnero(enero);
        Actual.setFebrero(febrero);
        Actual.setMarzo(marzo);
        Actual.setCodigo_de_vendedor(codigo);
        vendedorJDBC.update(Actual);

        List<ClsVendedores> todos = vendedorJDBC.seleccion();

        for (ClsVendedores vendedor : todos) {
            System.out.println("vendedor = " + vendedor);
        }
    }

    public static void main(String[] args) {

        Scanner t = new Scanner(System.in);
        Vendedores click = new Vendedores();
        String opc = "";
        do {
            System.out.println("1.Tabla");
            System.out.println("2.Insetar Datos");
            System.out.println("3.Actualizar Datos");
            System.out.println("4.Borrar Datos");
            System.out.println("5.salir");
            opc = t.nextLine();
            switch (opc) {
                case "1":
                    click.select();
                    break;
                case "2":
                    click.insert();
                    break;
                case "3":
                    click.update();
                    break;
                case "4":
                    click.delete();
                    break;

            }
        } while (!opc.equals("5"));

    }
}

