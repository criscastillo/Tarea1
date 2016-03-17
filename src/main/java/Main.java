import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

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
    	
    	if(req.params("mensaje")==null || req.params("hash")==null)
    		parametrosValidos = false;
    	
    	if(parametrosValidos) {
    		res.status(200);
            return "";
    	} else {
    		res.status(400);
            return "";
    	}
    });
    
  }
}
