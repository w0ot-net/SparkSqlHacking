package org.apache.ivy.osgi.p2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.osgi.util.DelegatingHandler;
import org.xml.sax.Attributes;

public class PropertiesParser {
   static class PropertiesHandler extends DelegatingHandler {
      private static final String PROPERTIES = "properties";
      private static final String SIZE = "size";
      Map properties;

      public PropertiesHandler(String... props) {
         super("properties");
         final List<String> propList = Arrays.asList(props);
         this.addChild(new PropertyHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(PropertyHandler child) {
               if (propList.isEmpty() || propList.contains(child.name)) {
                  PropertiesHandler.this.properties.put(child.name, child.value);
               }

            }
         });
      }

      protected void handleAttributes(Attributes atts) {
         int size = Integer.parseInt(atts.getValue("size"));
         this.properties = new HashMap(size);
      }
   }

   static class PropertyHandler extends DelegatingHandler {
      private static final String PROPERTY = "property";
      private static final String NAME = "name";
      private static final String VALUE = "value";
      String name;
      String value;

      public PropertyHandler() {
         super("property");
      }

      protected void handleAttributes(Attributes atts) {
         this.name = atts.getValue("name");
         this.value = atts.getValue("value");
      }
   }
}
