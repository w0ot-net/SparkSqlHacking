package org.apache.hadoop.hive.common.jsonexplain.tez;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.hadoop.fs.Path;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class Stage {
   String externalName;
   public final String internalName;
   public final TezJsonParser parser;
   public final List parentStages = new ArrayList();
   public final List childStages = new ArrayList();
   public final Map vertexs = new LinkedHashMap();
   public final Map attrs = new TreeMap();
   Map tezStageDependency;
   Op op;

   public Stage(String name, TezJsonParser tezJsonParser) {
      this.internalName = name;
      this.externalName = name;
      this.parser = tezJsonParser;
   }

   public void addDependency(JSONObject object, Map stages) throws JSONException {
      if (object.has("DEPENDENT STAGES")) {
         String names = object.getString("DEPENDENT STAGES");

         for(String name : names.split(",")) {
            Stage parent = (Stage)stages.get(name.trim());
            this.parentStages.add(parent);
            parent.childStages.add(this);
         }
      }

      if (object.has("CONDITIONAL CHILD TASKS")) {
         String names = object.getString("CONDITIONAL CHILD TASKS");
         this.externalName = this.internalName + "(CONDITIONAL CHILD TASKS: " + names + ")";

         for(String name : names.split(",")) {
            Stage child = (Stage)stages.get(name.trim());
            child.externalName = child.internalName + "(CONDITIONAL)";
            child.parentStages.add(this);
            this.childStages.add(child);
         }
      }

   }

   public void extractVertex(JSONObject object) throws Exception {
      if (object.has("Tez")) {
         this.tezStageDependency = new TreeMap();
         JSONObject tez = (JSONObject)object.get("Tez");
         JSONObject vertices = tez.getJSONObject("Vertices:");
         if (!tez.has("Edges:")) {
            for(String vertexName : JSONObject.getNames(vertices)) {
               this.vertexs.put(vertexName, new Vertex(vertexName, vertices.getJSONObject(vertexName), this.parser));
            }
         } else {
            JSONObject edges = tez.getJSONObject("Edges:");

            for(String to : JSONObject.getNames(edges)) {
               this.vertexs.put(to, new Vertex(to, vertices.getJSONObject(to), this.parser));
            }

            for(String to : JSONObject.getNames(edges)) {
               Object o = edges.get(to);
               Vertex v = (Vertex)this.vertexs.get(to);
               if (o instanceof JSONObject) {
                  JSONObject obj = (JSONObject)o;
                  String parent = obj.getString("parent");
                  Vertex parentVertex = (Vertex)this.vertexs.get(parent);
                  if (parentVertex == null) {
                     parentVertex = new Vertex(parent, vertices.getJSONObject(parent), this.parser);
                     this.vertexs.put(parent, parentVertex);
                  }

                  String type = obj.getString("type");
                  if (!"CONTAINS".equals(type)) {
                     v.addDependency(new Connection(type, parentVertex));
                     parentVertex.setType(type);
                     parentVertex.children.add(v);
                  } else {
                     parentVertex.addDependency(new Connection(type, v));
                     v.children.add(parentVertex);
                  }

                  this.tezStageDependency.put(v, Arrays.asList(new Connection(type, parentVertex)));
               } else {
                  JSONArray from = (JSONArray)o;
                  List<Connection> list = new ArrayList();

                  for(int index = 0; index < from.length(); ++index) {
                     JSONObject obj = from.getJSONObject(index);
                     String parent = obj.getString("parent");
                     Vertex parentVertex = (Vertex)this.vertexs.get(parent);
                     if (parentVertex == null) {
                        parentVertex = new Vertex(parent, vertices.getJSONObject(parent), this.parser);
                        this.vertexs.put(parent, parentVertex);
                     }

                     String type = obj.getString("type");
                     if (!"CONTAINS".equals(type)) {
                        v.addDependency(new Connection(type, parentVertex));
                        parentVertex.setType(type);
                        parentVertex.children.add(v);
                     } else {
                        parentVertex.addDependency(new Connection(type, v));
                        v.children.add(parentVertex);
                     }

                     list.add(new Connection(type, parentVertex));
                  }

                  this.tezStageDependency.put(v, list);
               }
            }
         }

         for(Vertex v : this.vertexs.values()) {
            if (v.vertexType == Vertex.VertexType.MAP || v.vertexType == Vertex.VertexType.REDUCE) {
               v.extractOpTree();
               v.checkMultiReduceOperator();
            }
         }
      } else {
         String[] names = JSONObject.getNames(object);
         if (names != null) {
            for(String name : names) {
               if (name.contains("Operator")) {
                  this.op = this.extractOp(name, object.getJSONObject(name));
               } else if (!object.get(name).toString().isEmpty()) {
                  this.attrs.put(name, object.get(name).toString());
               }
            }
         }
      }

   }

   Op extractOp(String opName, JSONObject opObj) throws Exception {
      Map<String, String> attrs = new TreeMap();
      Vertex v = null;
      if (opObj.length() > 0) {
         String[] names = JSONObject.getNames(opObj);

         for(String name : names) {
            Object o = opObj.get(name);
            if (this.isPrintable(o) && !o.toString().isEmpty()) {
               attrs.put(name, o.toString());
            } else {
               if (!(o instanceof JSONObject)) {
                  throw new Exception("Unsupported object in " + this.internalName);
               }

               JSONObject attrObj = (JSONObject)o;
               if (attrObj.length() > 0) {
                  if (name.equals("Processor Tree:")) {
                     JSONObject object = new JSONObject(new LinkedHashMap());
                     object.put(name, attrObj);
                     v = new Vertex((String)null, object, this.parser);
                     v.extractOpTree();
                  } else {
                     for(String attrName : JSONObject.getNames(attrObj)) {
                        if (!attrObj.get(attrName).toString().isEmpty()) {
                           attrs.put(attrName, attrObj.get(attrName).toString());
                        }
                     }
                  }
               }
            }
         }
      }

      Op op = new Op(opName, (String)null, (String)null, (List)null, attrs, (JSONObject)null, v, this.parser);
      if (v != null) {
         this.parser.addInline(op, new Connection((String)null, v));
      }

      return op;
   }

   private boolean isPrintable(Object val) {
      if (!(val instanceof Boolean) && !(val instanceof String) && !(val instanceof Integer) && !(val instanceof Long) && !(val instanceof Byte) && !(val instanceof Float) && !(val instanceof Double) && !(val instanceof Path)) {
         return val != null && val.getClass().isPrimitive();
      } else {
         return true;
      }
   }

   public void print(Printer printer, int indentFlag) throws Exception {
      if (this.parser.printSet.contains(this)) {
         printer.println(TezJsonParser.prefixString(indentFlag) + " Please refer to the previous " + this.externalName);
      } else {
         this.parser.printSet.add(this);
         printer.println(TezJsonParser.prefixString(indentFlag) + this.externalName);
         ++indentFlag;

         for(Vertex candidate : this.vertexs.values()) {
            if (!this.parser.isInline(candidate) && candidate.children.isEmpty()) {
               candidate.print(printer, indentFlag, (String)null, (Vertex)null);
            }
         }

         if (!this.attrs.isEmpty()) {
            printer.println(TezJsonParser.prefixString(indentFlag) + TezJsonParserUtils.attrsToString(this.attrs));
         }

         if (this.op != null) {
            this.op.print(printer, indentFlag, false);
         }

         ++indentFlag;

         for(Stage stage : this.parentStages) {
            stage.print(printer, indentFlag);
         }

      }
   }
}
