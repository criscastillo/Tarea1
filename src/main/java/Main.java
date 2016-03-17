import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import javax.xml.bind.DatatypeConverter;

public class Main {

  public static void main(String[] args) {
	
    port(Integer.valueOf(System.getenv("PORT")));
	
    get("/status", (req, res) -> {
            res.status(201);
            return "";
        });
    
    get("/", (req, res) -> "Hello World");
    
    post("/validarFirma", (req, res) -> {
    	boolean parametrosValidos = true;
    	String mensaje = req.params("mensaje");
    	String hash = req.params("hash");
    	boolean hashValido = false;
    	
    	if(mensaje==null || hash==null)
    		parametrosValidos = false;
    	
    	if(parametrosValidos) {
    		res.status(200);
    		if(hash.toLowerCase().equals(DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(mensaje.getBytes("UTF-8"))).toLowerCase()))
    			hashValido = true;
    		res.body("{\n \"valido\":"+hashValido+",\n \"mensaje\":\""+mensaje+"\"\n}");
    		return ""+hashValido;
    		            
    	} else {
    		res.status(400);
            return "";
    	}
    });
    
  }
}
