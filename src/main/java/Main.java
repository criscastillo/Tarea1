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

import org.json.*;

public class Main {

  public static void main(String[] args) {
	
    port(Integer.valueOf(System.getenv("PORT")));
	
    get("/status", (req, res) -> {
            res.status(201);
            return "";
        });
    
    get("/", (req, res) -> "Hello World");
    
    post("/validarFirma", (req, res) -> {
    	String mensaje;
    	String hash;
    	JSONObject body = new JSONObject(req.body());
    	
    	if(body == null || !body.has("mensaje") || !body.has("hash")) {
    		res.status(400);
            return "parametros no validos";
    	}
    	
    	try {
    		mensaje = body.getString("mensaje");
        	hash = body.getString("hash");
    	} catch(JSONException e) {
    		res.status(500);
    		return "parametros no string";
    	}
    	    	
    	boolean hashValido = false;
    	res.status(200);
    	
    	if(hash.toLowerCase().equals(DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(mensaje.getBytes("UTF-8"))).toLowerCase()))
    		hashValido = true;
    		
    	res.body("{\n \"valido\":"+hashValido+",\n \"mensaje\":\""+mensaje+"\"\n}");
    	return ""+hashValido;
    		            
    });
    
  }
}
