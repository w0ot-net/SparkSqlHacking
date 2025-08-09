package org.apache.logging.log4j.core.config.json;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.Reconfigurable;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.core.config.status.StatusConfiguration;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.core.util.Patterns;

public class JsonConfiguration extends AbstractConfiguration implements Reconfigurable {
   private final List status = new ArrayList();
   private JsonNode root;

   public JsonConfiguration(final LoggerContext loggerContext, final ConfigurationSource configSource) {
      super(loggerContext, configSource);
      File configFile = configSource.getFile();

      try {
         InputStream configStream = configSource.getInputStream();

         byte[] buffer;
         try {
            buffer = toByteArray(configStream);
         } catch (Throwable var13) {
            if (configStream != null) {
               try {
                  configStream.close();
               } catch (Throwable var12) {
                  var13.addSuppressed(var12);
               }
            }

            throw var13;
         }

         if (configStream != null) {
            configStream.close();
         }

         InputStream is = new ByteArrayInputStream(buffer);
         this.root = this.getObjectMapper().readTree(is);
         if (this.root.size() == 1) {
            for(JsonNode node : this.root) {
               this.root = node;
            }
         }

         this.processAttributes(this.rootNode, this.root);
         StatusConfiguration statusConfig = (new StatusConfiguration()).withStatus(this.getDefaultStatus());
         int monitorIntervalSeconds = 0;

         for(Map.Entry entry : this.rootNode.getAttributes().entrySet()) {
            String key = (String)entry.getKey();
            String value = this.getConfigurationStrSubstitutor().replace((String)entry.getValue());
            if ("status".equalsIgnoreCase(key)) {
               statusConfig.withStatus(value);
            } else if ("dest".equalsIgnoreCase(key)) {
               statusConfig.withDestination(value);
            } else if ("shutdownHook".equalsIgnoreCase(key)) {
               this.isShutdownHookEnabled = !"disable".equalsIgnoreCase(value);
            } else if ("shutdownTimeout".equalsIgnoreCase(key)) {
               this.shutdownTimeoutMillis = Long.parseLong(value);
            } else if ("packages".equalsIgnoreCase(key)) {
               this.pluginPackages.addAll(Arrays.asList(value.split(Patterns.COMMA_SEPARATOR)));
            } else if ("name".equalsIgnoreCase(key)) {
               this.setName(value);
            } else if ("monitorInterval".equalsIgnoreCase(key)) {
               monitorIntervalSeconds = Integers.parseInt(value);
            } else if ("advertiser".equalsIgnoreCase(key)) {
               this.createAdvertiser(value, configSource, buffer, "application/json");
            }
         }

         this.initializeWatchers(this, configSource, monitorIntervalSeconds);
         statusConfig.initialize();
         if (this.getName() == null) {
            this.setName(configSource.getLocation());
         }
      } catch (Exception ex) {
         LOGGER.error("Error parsing " + configSource.getLocation(), ex);
      }

   }

   protected ObjectMapper getObjectMapper() {
      return (new ObjectMapper()).configure(Feature.ALLOW_COMMENTS, true);
   }

   public void setup() {
      Iterator<Map.Entry<String, JsonNode>> iter = this.root.fields();
      List<Node> children = this.rootNode.getChildren();

      while(iter.hasNext()) {
         Map.Entry<String, JsonNode> entry = (Map.Entry)iter.next();
         JsonNode n = (JsonNode)entry.getValue();
         if (n.isObject()) {
            LOGGER.debug("Processing node for object {}", entry.getKey());
            children.add(this.constructNode((String)entry.getKey(), this.rootNode, n));
         } else if (n.isArray()) {
            LOGGER.error("Arrays are not supported at the root configuration.");
         }
      }

      LOGGER.debug("Completed parsing configuration");
      if (this.status.size() > 0) {
         for(Status s : this.status) {
            LOGGER.error("Error processing element {}: {}", s.name, s.errorType);
         }
      }

   }

   public Configuration reconfigure() {
      try {
         ConfigurationSource source = this.getConfigurationSource().resetInputStream();
         return source == null ? null : new JsonConfiguration(this.getLoggerContext(), source);
      } catch (IOException ex) {
         LOGGER.error("Cannot locate file {}", this.getConfigurationSource(), ex);
         return null;
      }
   }

   private Node constructNode(final String name, final Node parent, final JsonNode jsonNode) {
      PluginType<?> type = this.pluginManager.getPluginType(name);
      Node node = new Node(parent, name, type);
      this.processAttributes(node, jsonNode);
      Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.fields();
      List<Node> children = node.getChildren();

      while(iter.hasNext()) {
         Map.Entry<String, JsonNode> entry = (Map.Entry)iter.next();
         JsonNode n = (JsonNode)entry.getValue();
         if (!n.isArray() && !n.isObject()) {
            LOGGER.debug("Node {} is of type {}", entry.getKey(), n.getNodeType());
         } else {
            if (type == null) {
               this.status.add(new Status(name, n, JsonConfiguration.ErrorType.CLASS_NOT_FOUND));
            }

            if (!n.isArray()) {
               LOGGER.debug("Processing node for object {}", entry.getKey());
               children.add(this.constructNode((String)entry.getKey(), node, n));
            } else {
               LOGGER.debug("Processing node for array {}", entry.getKey());

               for(int i = 0; i < n.size(); ++i) {
                  String pluginType = this.getType(n.get(i), (String)entry.getKey());
                  PluginType<?> entryType = this.pluginManager.getPluginType(pluginType);
                  Node item = new Node(node, (String)entry.getKey(), entryType);
                  this.processAttributes(item, n.get(i));
                  if (pluginType.equals(entry.getKey())) {
                     LOGGER.debug("Processing {}[{}]", entry.getKey(), i);
                  } else {
                     LOGGER.debug("Processing {} {}[{}]", pluginType, entry.getKey(), i);
                  }

                  Iterator<Map.Entry<String, JsonNode>> itemIter = n.get(i).fields();
                  List<Node> itemChildren = item.getChildren();

                  while(itemIter.hasNext()) {
                     Map.Entry<String, JsonNode> itemEntry = (Map.Entry)itemIter.next();
                     if (((JsonNode)itemEntry.getValue()).isObject()) {
                        LOGGER.debug("Processing node for object {}", itemEntry.getKey());
                        itemChildren.add(this.constructNode((String)itemEntry.getKey(), item, (JsonNode)itemEntry.getValue()));
                     } else if (((JsonNode)itemEntry.getValue()).isArray()) {
                        JsonNode array = (JsonNode)itemEntry.getValue();
                        String entryName = (String)itemEntry.getKey();
                        LOGGER.debug("Processing array for object {}", entryName);

                        for(int j = 0; j < array.size(); ++j) {
                           itemChildren.add(this.constructNode(entryName, item, array.get(j)));
                        }
                     }
                  }

                  children.add(item);
               }
            }
         }
      }

      String t;
      if (type == null) {
         t = "null";
      } else {
         t = type.getElementName() + ':' + type.getPluginClass();
      }

      String p = node.getParent() == null ? "null" : (node.getParent().getName() == null ? "root" : node.getParent().getName());
      LOGGER.debug("Returning {} with parent {} of type {}", node.getName(), p, t);
      return node;
   }

   private String getType(final JsonNode node, final String name) {
      Iterator<Map.Entry<String, JsonNode>> iter = node.fields();

      while(iter.hasNext()) {
         Map.Entry<String, JsonNode> entry = (Map.Entry)iter.next();
         if (((String)entry.getKey()).equalsIgnoreCase("type")) {
            JsonNode n = (JsonNode)entry.getValue();
            if (n.isValueNode()) {
               return n.asText();
            }
         }
      }

      return name;
   }

   private void processAttributes(final Node parent, final JsonNode node) {
      Map<String, String> attrs = parent.getAttributes();
      Iterator<Map.Entry<String, JsonNode>> iter = node.fields();

      while(iter.hasNext()) {
         Map.Entry<String, JsonNode> entry = (Map.Entry)iter.next();
         if (!((String)entry.getKey()).equalsIgnoreCase("type")) {
            JsonNode n = (JsonNode)entry.getValue();
            if (n.isValueNode()) {
               attrs.put((String)entry.getKey(), n.asText());
            }
         }
      }

   }

   public String toString() {
      return this.getClass().getSimpleName() + "[location=" + this.getConfigurationSource() + "]";
   }

   private static enum ErrorType {
      CLASS_NOT_FOUND;

      // $FF: synthetic method
      private static ErrorType[] $values() {
         return new ErrorType[]{CLASS_NOT_FOUND};
      }
   }

   private static class Status {
      private final JsonNode node;
      private final String name;
      private final ErrorType errorType;

      public Status(final String name, final JsonNode node, final ErrorType errorType) {
         this.name = name;
         this.node = node;
         this.errorType = errorType;
      }

      public String toString() {
         return "Status [name=" + this.name + ", errorType=" + this.errorType + ", node=" + this.node + "]";
      }
   }
}
