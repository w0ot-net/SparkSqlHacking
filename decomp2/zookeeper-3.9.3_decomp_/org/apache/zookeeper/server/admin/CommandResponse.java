package org.apache.zookeeper.server.admin;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandResponse {
   public static final String KEY_COMMAND = "command";
   public static final String KEY_ERROR = "error";
   private final String command;
   private final String error;
   private final Map data;
   private final Map headers;
   private int statusCode;
   private InputStream inputStream;

   public CommandResponse(String command) {
      this(command, (String)null, 200);
   }

   public CommandResponse(String command, String error, int statusCode) {
      this(command, error, statusCode, (InputStream)null);
   }

   public CommandResponse(String command, String error, int statusCode, InputStream inputStream) {
      this.command = command;
      this.error = error;
      this.data = new LinkedHashMap();
      this.headers = new HashMap();
      this.statusCode = statusCode;
      this.inputStream = inputStream;
   }

   public String getCommand() {
      return this.command;
   }

   public String getError() {
      return this.error;
   }

   public int getStatusCode() {
      return this.statusCode;
   }

   public void setStatusCode(int statusCode) {
      this.statusCode = statusCode;
   }

   public InputStream getInputStream() {
      return this.inputStream;
   }

   public void setInputStream(InputStream inputStream) {
      this.inputStream = inputStream;
   }

   public Object put(String key, Object value) {
      return this.data.put(key, value);
   }

   public void putAll(Map m) {
      this.data.putAll(m);
   }

   public void addHeader(String name, String value) {
      this.headers.put(name, value);
   }

   public Map getHeaders() {
      return this.headers;
   }

   public Map toMap() {
      Map<String, Object> m = new LinkedHashMap(this.data);
      m.put("command", this.command);
      m.put("error", this.error);
      m.putAll(this.data);
      return m;
   }
}
