package org.apache.logging.log4j.core.config.composite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.util.PluginManager;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.core.filter.CompositeFilter;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.util.Strings;

public class DefaultMergeStrategy implements MergeStrategy {
   private static final String APPENDERS = "appenders";
   private static final String PROPERTIES = "properties";
   private static final String LOGGERS = "loggers";
   private static final String SCRIPTS = "scripts";
   private static final String FILTERS = "filters";
   private static final String STATUS = "status";
   private static final String NAME = "name";
   private static final String REF = "ref";

   public void mergeRootProperties(final Node rootNode, final AbstractConfiguration configuration) {
      for(Map.Entry attribute : configuration.getRootNode().getAttributes().entrySet()) {
         boolean isFound = false;

         for(Map.Entry targetAttribute : rootNode.getAttributes().entrySet()) {
            if (((String)targetAttribute.getKey()).equalsIgnoreCase((String)attribute.getKey())) {
               if (((String)attribute.getKey()).equalsIgnoreCase("status")) {
                  Level targetLevel = Level.getLevel(Strings.toRootUpperCase((String)targetAttribute.getValue()));
                  Level sourceLevel = Level.getLevel(Strings.toRootUpperCase((String)attribute.getValue()));
                  if (targetLevel != null && sourceLevel != null) {
                     if (sourceLevel.isLessSpecificThan(targetLevel)) {
                        targetAttribute.setValue((String)attribute.getValue());
                     }
                  } else if (sourceLevel != null) {
                     targetAttribute.setValue((String)attribute.getValue());
                  }
               } else if (((String)attribute.getKey()).equalsIgnoreCase("monitorInterval")) {
                  int sourceInterval = Integers.parseInt((String)attribute.getValue());
                  int targetInterval = Integers.parseInt((String)targetAttribute.getValue());
                  if (targetInterval == 0 || sourceInterval < targetInterval) {
                     targetAttribute.setValue((String)attribute.getValue());
                  }
               } else if (((String)attribute.getKey()).equalsIgnoreCase("packages")) {
                  String sourcePackages = (String)attribute.getValue();
                  String targetPackages = (String)targetAttribute.getValue();
                  if (sourcePackages != null) {
                     if (targetPackages != null) {
                        targetAttribute.setValue(targetPackages + "," + sourcePackages);
                     } else {
                        targetAttribute.setValue(sourcePackages);
                     }
                  }
               } else {
                  targetAttribute.setValue((String)attribute.getValue());
               }

               isFound = true;
            }
         }

         if (!isFound) {
            rootNode.getAttributes().put((String)attribute.getKey(), (String)attribute.getValue());
         }
      }

   }

   public void mergConfigurations(final Node target, final Node source, final PluginManager pluginManager) {
      for(Node sourceChildNode : source.getChildren()) {
         boolean isFilter = this.isFilterNode(sourceChildNode);
         boolean isMerged = false;

         for(Node targetChildNode : target.getChildren()) {
            if (isFilter) {
               if (this.isFilterNode(targetChildNode)) {
                  this.updateFilterNode(target, targetChildNode, sourceChildNode, pluginManager);
                  isMerged = true;
                  break;
               }
            } else if (targetChildNode.getName().equalsIgnoreCase(sourceChildNode.getName())) {
               switch (Strings.toRootLowerCase(targetChildNode.getName())) {
                  case "properties":
                  case "scripts":
                  case "appenders":
                     Iterator var22 = sourceChildNode.getChildren().iterator();

                     Node node;
                     for(; var22.hasNext(); targetChildNode.getChildren().add(node)) {
                        node = (Node)var22.next();

                        for(Node targetNode : targetChildNode.getChildren()) {
                           if (Objects.equals(targetNode.getAttributes().get("name"), node.getAttributes().get("name"))) {
                              targetChildNode.getChildren().remove(targetNode);
                              break;
                           }
                        }
                     }

                     isMerged = true;
                     break;
                  case "loggers":
                     Map<String, Node> targetLoggers = new HashMap();

                     for(Node node : targetChildNode.getChildren()) {
                        targetLoggers.put(node.getName(), node);
                     }

                     for(Node node : sourceChildNode.getChildren()) {
                        Node targetNode = this.getLoggerNode(targetChildNode, (String)node.getAttributes().get("name"));
                        Node loggerNode = new Node(targetChildNode, node.getName(), node.getType());
                        if (targetNode != null) {
                           targetNode.getAttributes().putAll(node.getAttributes());

                           for(Node sourceLoggerChild : node.getChildren()) {
                              if (this.isFilterNode(sourceLoggerChild)) {
                                 boolean foundFilter = false;

                                 for(Node targetChild : targetNode.getChildren()) {
                                    if (this.isFilterNode(targetChild)) {
                                       this.updateFilterNode(loggerNode, targetChild, sourceLoggerChild, pluginManager);
                                       foundFilter = true;
                                       break;
                                    }
                                 }

                                 if (!foundFilter) {
                                    Node childNode = new Node(loggerNode, sourceLoggerChild.getName(), sourceLoggerChild.getType());
                                    childNode.getAttributes().putAll(sourceLoggerChild.getAttributes());
                                    childNode.getChildren().addAll(sourceLoggerChild.getChildren());
                                    targetNode.getChildren().add(childNode);
                                 }
                              } else {
                                 Node childNode = new Node(loggerNode, sourceLoggerChild.getName(), sourceLoggerChild.getType());
                                 childNode.getAttributes().putAll(sourceLoggerChild.getAttributes());
                                 childNode.getChildren().addAll(sourceLoggerChild.getChildren());
                                 if (childNode.getName().equalsIgnoreCase("AppenderRef")) {
                                    for(Node targetChild : targetNode.getChildren()) {
                                       if (this.isSameReference(targetChild, childNode)) {
                                          targetNode.getChildren().remove(targetChild);
                                          break;
                                       }
                                    }
                                 } else {
                                    for(Node targetChild : targetNode.getChildren()) {
                                       if (this.isSameName(targetChild, childNode)) {
                                          targetNode.getChildren().remove(targetChild);
                                          break;
                                       }
                                    }
                                 }

                                 targetNode.getChildren().add(childNode);
                              }
                           }
                        } else {
                           loggerNode.getAttributes().putAll(node.getAttributes());
                           loggerNode.getChildren().addAll(node.getChildren());
                           targetChildNode.getChildren().add(loggerNode);
                        }
                     }

                     isMerged = true;
                     break;
                  default:
                     targetChildNode.getChildren().addAll(sourceChildNode.getChildren());
                     isMerged = true;
               }
            }
         }

         if (!isMerged) {
            if (sourceChildNode.getName().equalsIgnoreCase("Properties")) {
               target.getChildren().add(0, sourceChildNode);
            } else {
               target.getChildren().add(sourceChildNode);
            }
         }
      }

   }

   private Node getLoggerNode(final Node parentNode, final String name) {
      for(Node node : parentNode.getChildren()) {
         String nodeName = (String)node.getAttributes().get("name");
         if (name == null && nodeName == null) {
            return node;
         }

         if (nodeName != null && nodeName.equals(name)) {
            return node;
         }
      }

      return null;
   }

   private void updateFilterNode(final Node target, final Node targetChildNode, final Node sourceChildNode, final PluginManager pluginManager) {
      if (CompositeFilter.class.isAssignableFrom(targetChildNode.getType().getPluginClass())) {
         Node node = new Node(targetChildNode, sourceChildNode.getName(), sourceChildNode.getType());
         node.getChildren().addAll(sourceChildNode.getChildren());
         node.getAttributes().putAll(sourceChildNode.getAttributes());
         targetChildNode.getChildren().add(node);
      } else {
         PluginType pluginType = pluginManager.getPluginType("filters");
         Node filtersNode = new Node(targetChildNode, "filters", pluginType);
         Node node = new Node(filtersNode, sourceChildNode.getName(), sourceChildNode.getType());
         node.getAttributes().putAll(sourceChildNode.getAttributes());
         List<Node> children = filtersNode.getChildren();
         children.add(targetChildNode);
         children.add(node);
         List<Node> nodes = target.getChildren();
         nodes.remove(targetChildNode);
         nodes.add(filtersNode);
      }

   }

   private boolean isFilterNode(final Node node) {
      return Filter.class.isAssignableFrom(node.getType().getPluginClass());
   }

   private boolean isSameName(final Node node1, final Node node2) {
      String value = (String)node1.getAttributes().get("name");
      return value != null && Strings.toRootLowerCase(value).equals(Strings.toRootLowerCase((String)node2.getAttributes().get("name")));
   }

   private boolean isSameReference(final Node node1, final Node node2) {
      String value = (String)node1.getAttributes().get("ref");
      return value != null && Strings.toRootLowerCase(value).equals(Strings.toRootLowerCase((String)node2.getAttributes().get("ref")));
   }
}
