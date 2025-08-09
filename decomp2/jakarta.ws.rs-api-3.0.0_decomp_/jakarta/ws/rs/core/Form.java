package jakarta.ws.rs.core;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Form {
   private final MultivaluedMap parameters;

   public Form() {
      this(new AbstractMultivaluedMap(new LinkedHashMap()) {
      });
   }

   public Form(String parameterName, String parameterValue) {
      this();
      this.parameters.add(parameterName, parameterValue);
   }

   public Form(MultivaluedMap store) {
      this.parameters = store;
   }

   public Form param(String name, String value) {
      this.parameters.add(name, value);
      return this;
   }

   public MultivaluedMap asMap() {
      return this.parameters;
   }
}
