package org.apache.hadoop.hive.common.jsonexplain.tez;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class Vertex implements Comparable {
   public final String name;
   public final TezJsonParser parser;
   public final List parentConnections = new ArrayList();
   public final List children = new ArrayList();
   public final JSONObject vertexObject;
   public boolean dummy;
   public final List rootOps = new ArrayList();
   public final List mergeJoinDummyVertexs = new ArrayList();
   public boolean hasMultiReduceOp = false;
   public String executionMode = "";
   public Map tagToInput = new LinkedHashMap();
   public String tag;
   public VertexType vertexType;
   public EdgeType edgeType;

   public Vertex(String name, JSONObject vertexObject, TezJsonParser tezJsonParser) {
      this.name = name;
      if (this.name != null) {
         if (this.name.contains("Map")) {
            this.vertexType = Vertex.VertexType.MAP;
         } else if (this.name.contains("Reduce")) {
            this.vertexType = Vertex.VertexType.REDUCE;
         } else if (this.name.contains("Union")) {
            this.vertexType = Vertex.VertexType.UNION;
         } else {
            this.vertexType = Vertex.VertexType.UNKNOWN;
         }
      } else {
         this.vertexType = Vertex.VertexType.UNKNOWN;
      }

      this.dummy = false;
      this.vertexObject = vertexObject;
      this.parser = tezJsonParser;
   }

   public void addDependency(Connection connection) throws JSONException {
      this.parentConnections.add(connection);
   }

   public void extractOpTree() throws Exception {
      if (this.vertexObject.length() != 0) {
         for(String key : JSONObject.getNames(this.vertexObject)) {
            if (key.equals("Map Operator Tree:")) {
               this.extractOp(this.vertexObject.getJSONArray(key).getJSONObject(0));
            } else if (!key.equals("Reduce Operator Tree:") && !key.equals("Processor Tree:")) {
               if (key.equals("Join:")) {
                  JSONArray array = this.vertexObject.getJSONArray(key);

                  for(int index = 0; index < array.length(); ++index) {
                     JSONObject mpOpTree = array.getJSONObject(index);
                     Vertex v = new Vertex((String)null, mpOpTree, this.parser);
                     v.extractOpTree();
                     v.dummy = true;
                     this.mergeJoinDummyVertexs.add(v);
                  }
               } else if (key.equals("Merge File Operator")) {
                  JSONObject opTree = this.vertexObject.getJSONObject(key);
                  if (!opTree.has("Map Operator Tree:")) {
                     throw new Exception("Merge File Operator does not have a Map Operator Tree");
                  }

                  this.extractOp(opTree.getJSONArray("Map Operator Tree:").getJSONObject(0));
               } else if (key.equals("Execution mode:")) {
                  this.executionMode = " " + this.vertexObject.getString(key);
               } else if (key.equals("tagToInput:")) {
                  JSONObject tagToInput = this.vertexObject.getJSONObject(key);

                  for(String tag : JSONObject.getNames(tagToInput)) {
                     this.tagToInput.put(tag, (String)tagToInput.get(tag));
                  }
               } else {
                  if (!key.equals("tag:")) {
                     throw new Exception("Unsupported operator tree in vertex " + this.name);
                  }

                  this.tag = this.vertexObject.getString(key);
               }
            } else {
               this.extractOp(this.vertexObject.getJSONObject(key));
            }
         }
      }

   }

   Op extractOp(JSONObject operator) throws Exception {
      String[] names = JSONObject.getNames(operator);
      if (names.length != 1) {
         throw new Exception("Expect only one operator in " + operator.toString());
      } else {
         String opName = names[0];
         JSONObject attrObj = (JSONObject)operator.get(opName);
         Map<String, String> attrs = new TreeMap();
         List<Op> children = new ArrayList();
         String id = null;
         String outputVertexName = null;

         for(String attrName : JSONObject.getNames(attrObj)) {
            if (attrName.equals("children")) {
               Object childrenObj = attrObj.get(attrName);
               if (childrenObj instanceof JSONObject) {
                  if (((JSONObject)childrenObj).length() != 0) {
                     children.add(this.extractOp((JSONObject)childrenObj));
                  }
               } else {
                  if (!(childrenObj instanceof JSONArray)) {
                     throw new Exception("Unsupported operator " + this.name + "'s children operator is neither a jsonobject nor a jsonarray");
                  }

                  if (((JSONArray)childrenObj).length() != 0) {
                     JSONArray array = (JSONArray)childrenObj;

                     for(int index = 0; index < array.length(); ++index) {
                        children.add(this.extractOp(array.getJSONObject(index)));
                     }
                  }
               }
            } else if (attrName.equals("OperatorId:")) {
               id = attrObj.get(attrName).toString();
            } else if (attrName.equals("outputname:")) {
               outputVertexName = attrObj.get(attrName).toString();
            } else if (!attrObj.get(attrName).toString().isEmpty()) {
               attrs.put(attrName, attrObj.get(attrName).toString());
            }
         }

         Op op = new Op(opName, id, outputVertexName, children, attrs, operator, this, this.parser);
         if (!children.isEmpty()) {
            for(Op child : children) {
               child.parent = op;
            }
         } else {
            this.rootOps.add(op);
         }

         return op;
      }
   }

   public void print(Printer printer, int indentFlag, String type, Vertex callingVertex) throws Exception {
      if (this.parser.printSet.contains(this) && !this.hasMultiReduceOp) {
         if (type != null) {
            printer.println(TezJsonParser.prefixString(indentFlag, "<-") + " Please refer to the previous " + this.name + " [" + type + "]");
         } else {
            printer.println(TezJsonParser.prefixString(indentFlag, "<-") + " Please refer to the previous " + this.name);
         }

      } else {
         this.parser.printSet.add(this);
         if (type != null) {
            printer.println(TezJsonParser.prefixString(indentFlag, "<-") + this.name + " [" + type + "]" + this.executionMode);
         } else if (this.name != null) {
            printer.println(TezJsonParser.prefixString(indentFlag) + this.name + this.executionMode);
         }

         if (this.hasMultiReduceOp && callingVertex.vertexType != Vertex.VertexType.UNION) {
            Op choose = null;

            for(Op op : this.rootOps) {
               if (op.outputVertexName.equals(callingVertex.name)) {
                  choose = op;
               }
            }

            if (choose == null) {
               throw new Exception("Can not find the right reduce output operator for vertex " + this.name);
            }

            choose.print(printer, indentFlag, false);
         } else {
            for(Op op : this.rootOps) {
               if (this.dummy) {
                  op.print(printer, indentFlag, true);
               } else {
                  op.print(printer, indentFlag, false);
               }
            }
         }

         if (this.vertexType == Vertex.VertexType.UNION) {
            ++indentFlag;

            for(int index = 0; index < this.parentConnections.size(); ++index) {
               Connection connection = (Connection)this.parentConnections.get(index);
               connection.from.print(printer, indentFlag, connection.type, this);
            }
         }

      }
   }

   public void checkMultiReduceOperator() {
      if (this.name.contains("Reduce") && this.rootOps.size() >= 2) {
         for(Op op : this.rootOps) {
            if (op.type != Op.OpType.RS) {
               return;
            }
         }

         this.hasMultiReduceOp = true;
      }
   }

   public void setType(String type) {
      switch (type) {
         case "BROADCAST_EDGE":
            this.edgeType = Vertex.EdgeType.BROADCAST;
            break;
         case "SIMPLE_EDGE":
            this.edgeType = Vertex.EdgeType.SHUFFLE;
            break;
         case "CUSTOM_SIMPLE_EDGE":
            this.edgeType = Vertex.EdgeType.PARTITION_ONLY_SHUFFLE;
            break;
         case "CUSTOM_EDGE":
            this.edgeType = Vertex.EdgeType.MULTICAST;
            break;
         default:
            this.edgeType = Vertex.EdgeType.UNKNOWN;
      }

   }

   public int compareTo(Vertex o) {
      return this.name.compareTo(o.name);
   }

   public Op getSingleRSOp() {
      if (this.rootOps.size() == 0) {
         return null;
      } else {
         Op ret = null;

         for(Op op : this.rootOps) {
            if (op.type == Op.OpType.RS) {
               if (ret != null) {
                  return null;
               }

               ret = op;
            }
         }

         return ret;
      }
   }

   public static enum VertexType {
      MAP,
      REDUCE,
      UNION,
      UNKNOWN;
   }

   public static enum EdgeType {
      BROADCAST,
      SHUFFLE,
      MULTICAST,
      PARTITION_ONLY_SHUFFLE,
      UNKNOWN;
   }
}
