import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;

import com.heroku.sdk.jdbc.DatabaseUrl;

public class Main {

  public static void main(String[] args) {
	
    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");
	
    get("/", (req, res) -> "Hello World");

    get("/status", (req, res) -> {
            res.status(201);
            return null;
        });
    
    post("/validarFirma", (req, res) -> {
    	boolean parametrosValidos = true;
    	
    	if(req.attribute("mensaje")==null || req.attribute("hash")==null)
    		parametrosValidos = false;
    	
    	if(parametrosValidos) {
    		res.status(200);
            return null;
    	} else {
    		res.status(400);
            return null;
    	}
        
    });
    
    
  }

}
