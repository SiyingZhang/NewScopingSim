package edu.scopingsim.utils;

import spark.Request;
import spark.Response;
import spark.Session;

public class ServiceUtils {

	public ServiceUtils() {
		super();
	}
	
	public boolean hasEvent(Request req, Response res) {
		Session session = req.session(true);
		return session.attribute("event") != null;
	}
}
