/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Pablo Martin
 */
public class conexion {
    Statement sta;
    Connection conexion;
    BasicDataSource bdSource = new BasicDataSource();
    ResultSet resultadoConsulta;

    public int abrirConexion() {
        //abre la conexion entre la aplicacion y la base de datos
        try {
            conexion = bdSource.getConnection();
            if (conexion != null) {
                System.out.println("Conectado a la BBDD");
            } else {
                System.out.println("No se ha podido conectar a la Base de Datos");
            }
            return 0;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            // Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null,ex);
            return -1;
        }
    }

    public conexion() {
        //constructor que implementa al metodo de abrir la conexion de app con la BBDD
        bdSource.setUrl("jdbc:mysql://127.0.0.1/videoclub");
        bdSource.setUsername("root");
        bdSource.setPassword("");

    }
     public int cerrarConexion() {
        //cierra la conexion de la aplicacion con la base de datos
        try {
            conexion.close();
            return 0;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return -1;
            // Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
     public void deleteFilm(String nombrePelicula) {
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("Delete from peliculas where titulo='" + nombrePelicula + "';");
            sta.close();
        } catch (Exception e) {
            System.err.println("No se pudo borrar la pelicula");
        }
    }
 public void insertarPelicula(String titulo, String genero, int director, int actor, String release ) {
        //inserta un album nuevo a la tabla album
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO peliculas VALUE ('" + titulo + "' , '" + genero + "' ,'" + director + "' , '" + actor + "' , '" + release + "');");
            sta.close();
            //System.out.println("INSERT INTO peliculas VALUE ('" + titulo + "' , '" + genero + "' , '" + release + "');");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
    public ArrayList<String> consulta_peliculas() {
         // este metodo lo usaremos para que se muestre todas las canciones en 
        //un comboBox, para ellos usaremos una arrayList y una query para mostrar 
        //todas las canciones
        ArrayList<String> pelis = new ArrayList<String>();
        try {
            sta = conexion.createStatement();
            String query = "SELECT titulo from peliculas";
            ResultSet rs = sta.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                pelis.add(rs.getString("titulo"));
            }
//            System.out.println(query);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
//             System.out.println(pelis);
        }
        return pelis;
    }
    
     public ArrayList<String> consulta_actores() {
         // este metodo lo usaremos para que se muestre todas las canciones en 
        //un comboBox, para ellos usaremos una arrayList y una query para mostrar 
        //todas las canciones
        ArrayList<String> listaActores = new ArrayList<String>();
        try {
            sta = conexion.createStatement();
            String query = "SELECT nombre from actores";
            ResultSet rs = sta.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                listaActores.add(rs.getString("nombre"));
            }
//            System.out.println(query);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
//             System.out.println(pelis);
        }
        return listaActores;
    }
     public ArrayList<String> consulta_directores() {
         // este metodo lo usaremos para que se muestre todas las canciones en 
        //un comboBox, para ellos usaremos una arrayList y una query para mostrar 
        //todas las canciones
        ArrayList<String> listaDirectores = new ArrayList<String>();
        try {
            sta = conexion.createStatement();
            String query = "SELECT nombre from directores";
            ResultSet rs = sta.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                listaDirectores.add(rs.getString("nombre"));
            }
//            System.out.println(query);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
//             System.out.println(pelis);
        }
        return listaDirectores;
    }
       public void actualizaPelis(String nameFilm, String newFilm,String newGenero,int newDirector, int newActor, String _newRelease) {
      //este metodo sirve para modificar una cancion, basicamente se declaran los strings necesarios y se realiza la 
      //query adecuada para realizar los cambios en la cancion
        String query = "UPDATE peliculas SET";
        try {
            sta = conexion.createStatement();
            query += " titulo = '" + newFilm + "'" + ','+ "Genero='" + newGenero + "'" + ','+ "Actor='" + newActor + "'" + ',' + "Director='" + newDirector + "'" + ',' + "año='" + _newRelease + "'WHERE titulo like '" + nameFilm + "';";
            System.out.println(query);
            sta.executeUpdate(query);
            sta.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());      
        }
    }


 public void deleteActor(String nombreActor) {
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("Delete from actores where nombre='" + nombreActor + "';");
            sta.close();
        } catch (Exception e) {
            System.err.println("No se pudo borrar la pelicula");
        }
    }
  public void deleteDirector(String nombreDirector) {
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("Delete from directores where nombre='" + nombreDirector + "';");
            sta.close();
        } catch (Exception e) {
            System.err.println("No se pudo borrar la pelicula");
        }
    }


public void insertarDirector(String nombre, String apellido1, String apellido2, String l_nacimiento ) {
        //inserta un album nuevo a la tabla album
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO directores VALUE ('" + nombre + "' , '" + apellido1 + "' ,'" + apellido2 + "' , '"  + l_nacimiento + "');");
            sta.close();
            //System.out.println("INSERT INTO peliculas VALUE ('" + titulo + "' , '" + genero + "' , '" + release + "');");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
    public void insertarActor(String nombre, String apellido1, String apellido2, String l_nacimiento ) {
        //inserta un album nuevo a la tabla album
        try {
            sta = conexion.createStatement();
            sta.executeUpdate("INSERT INTO actores VALUE ('" + nombre + "' , '" + apellido1 + "' ,'" + apellido2 + "' , '"  + l_nacimiento + "');");
            sta.close();
            //System.out.println("INSERT INTO peliculas VALUE ('" + titulo + "' , '" + genero + "' , '" + release + "');");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

      public void actualizaDirectores(String nameDirector, String _newDirector,String newApellido1,String newApellido2,String newCiudad) {
      //este metodo sirve para modificar una cancion, basicamente se declaran los strings necesarios y se realiza la 
      //query adecuada para realizar los cambios en la cancion
        String query = "UPDATE directores SET";
        try {
            sta = conexion.createStatement();
            query += " nombre = '" + _newDirector + "'" + ','+ "Apellido1='" + newApellido1 + "'" + ','+ "Apellido2='" + newApellido2 + "'"   + ',' + "Ciudad='" + newCiudad + "'WHERE nombre like '" + nameDirector + "';";
            System.out.println(query);
            sta.executeUpdate(query);
            sta.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());      
        }
    }
  public void actualizaActores(String nameActor, String _newActor,String newApellido1,String newApellido2,String newCiudad) {
      //este metodo sirve para modificar una cancion, basicamente se declaran los strings necesarios y se realiza la 
      //query adecuada para realizar los cambios en la cancion
        String query = "UPDATE directores SET";
        try {
            sta = conexion.createStatement();
            query += " nombre = '" + _newActor + "'" + ','+ "Apellido1='" + newApellido1 + "'" + ','+ "Apellido2='" + newApellido2 + "'" + ',' + "Ciudad='" + newCiudad + "'WHERE nombre like '" + nameActor + "';";
            System.out.println(query);
            sta.executeUpdate(query);
            sta.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());      
        }
    }
}
