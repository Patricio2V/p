/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conexionmongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 *
 * @author GoldenGamers
 */
public class CRUDMongoDB {
    public static void main(String[]args) {
        
        MongoClient mongo = crearConexion();
        
        //SI NO EXISTE LA BASE DE DATOS, LA CREAMOS
        if (mongo != null) {
            DB db = mongo.getDB("Pruebas");
            
            //System.out.println("BASE DE DATOS CREADA");
            // CREA UNA COLECCION(TABLA) SI NO EXISTE 
            //INSERTA EL DOCUMENTO(REGISTRO) A LA COLECCION
            //insertarUsuario(db, "usuarios", "Sergio", "Mexico");
            //insertarUsuario(db, "usuarios", "Camilo", "Chile");
            //insertarUsuario(db, "usuarios", "Juan", "Argentina");
            
            //mostrarColeccion(db, "usuarios");
            //buscarPorNombre(db, "usuarios", "Sergio");
            
            //System.out.println("ANTES DEL UPDATE");
            //mostrarColeccion(db, "usuarios");
            //actualizarDocumento(db, "usuarios", "Sergio");
            //System.out.println("DESPUES DEL UPDATE");
            //mostrarColeccion(db, "usuarios");
            
            //System.out.println("ANTES DEL DELETE");
            //mostrarColeccion(db, "usuarios");
            //borrarDocumento(db, "usuarios", "Argentina");
            //System.out.println("DESPUES DEL DELETE");
            //mostrarColeccion(db, "usuarios");
        }
                
    }
    
    public static MongoClient crearConexion() {
        System.out.println("prueba conexion mongodb");
        
        MongoClient mongo = null;
        
        mongo = new MongoClient("localhost", 27017);
        
        return mongo;
    }
    
    public static void insertarUsuario(DB db, String coleccion, String nombre, String pais) {
        
        DBCollection colec = db.getCollection(coleccion);
        
        //CREA EL DOCUMENTO(REGISTRO) E INSERTA LA INFORMACION RECIBIDA
        BasicDBObject documento = new BasicDBObject();
        documento.put("nombre", nombre);
        documento.put("pais", pais);
        
        colec.insert(documento);
        
    }
    
    //MUESTRA TODOS LOS DOCUMENTOS DE LA COLECCION USUARIOS
    public static void mostrarColeccion(DB db, String coleccion) {
        DBCollection colec = db.getCollection(coleccion);
        
        DBCursor cursor = colec.find();
        
        while(cursor.hasNext()) {
            System.out.println("* "+ cursor.next().get("nombre") + " - " + cursor.curr().get("pais"));
        }
    }
    
    //MUESTRA TODOS LOS DOCUMENTOS DE LA COLECCION USUARIOS QUE COINCIDAN CON EL NOMBRE
    public static void buscarPorNombre(DB db, String coleccion, String nombre) {
        DBCollection colect = db.getCollection(coleccion);
        
        //CREAMOS LA CONSULTA CON EL CAMPO NOMBRE
        BasicDBObject consulta = new BasicDBObject();
        consulta.put("nombre", nombre);
        
        // BUSCA Y MUSTRA TODOS LOS DOCUMENTOS QUE COINCIDAN CON LA CONSULTA
        DBCursor cursor = colect.find(consulta);
        while(cursor.hasNext()){
            System.out.println("-- " + cursor.next().get("nombre") + " - " + cursor.curr().get("pais"));
        }
    }
    
    public static void actualizarDocumento(DB db, String coleccion, String nombre) {
        DBCollection colec = db.getCollection(coleccion);
        
        //SENTENCIA CON LA INFORMACION A REEMPLAZAR
        BasicDBObject actualizarPais = new BasicDBObject();
        actualizarPais.append("$set", new BasicDBObject().append("pais", "Peru"));
        
        //BUSCA EL DOCUMENTO EN LA COLECCION
        BasicDBObject buscarPorNombre = new BasicDBObject();
        buscarPorNombre.append("nombre", nombre);
        
        //REALIZA EL UPDATE
        colec.update(buscarPorNombre, actualizarPais);
    }
    
    public static void borrarDocumento(DB db, String coleccion, String nombre){
        DBCollection colec = db.getCollection(coleccion);
        
        colec.remove(new BasicDBObject().append("pais", nombre));
    }
}
