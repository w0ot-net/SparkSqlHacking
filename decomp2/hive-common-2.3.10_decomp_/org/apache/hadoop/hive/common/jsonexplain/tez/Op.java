package org.apache.hadoop.hive.common.jsonexplain.tez;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class Op {
   public final String name;
   public final TezJsonParser parser;
   public final String operatorId;
   public Op parent;
   public final List children;
   public final Map attrs;
   public final JSONObject opObject;
   public final Vertex vertex;
   public final String outputVertexName;
   public final OpType type;

   public Op(String name, String id, String outputVertexName, List children, Map attrs, JSONObject opObject, Vertex vertex, TezJsonParser tezJsonParser) throws JSONException {
      this.name = name;
      this.operatorId = id;
      this.type = this.deriveOpType(this.operatorId);
      this.outputVertexName = outputVertexName;
      this.children = children;
      this.attrs = attrs;
      this.opObject = opObject;
      this.vertex = vertex;
      this.parser = tezJsonParser;
   }

   private OpType deriveOpType(String operatorId) {
      if (operatorId != null) {
         if (operatorId.startsWith(Op.OpType.MAPJOIN.toString())) {
            return Op.OpType.MAPJOIN;
         } else if (operatorId.startsWith(Op.OpType.MERGEJOIN.toString())) {
            return Op.OpType.MERGEJOIN;
         } else {
            return operatorId.startsWith(Op.OpType.RS.toString()) ? Op.OpType.RS : Op.OpType.OTHERS;
         }
      } else {
         return Op.OpType.OTHERS;
      }
   }

   private void inlineJoinOp() throws Exception {
      if (this.type == Op.OpType.MAPJOIN) {
         JSONObject joinObj = this.opObject.getJSONObject(this.name);
         JSONObject verticeObj = joinObj.getJSONObject("input vertices:");
         Map<String, Vertex> posToVertex = new LinkedHashMap();

         for(String pos : JSONObject.getNames(verticeObj)) {
            String vertexName = verticeObj.getString(pos);
            Connection c = null;

            for(Connection connection : this.vertex.parentConnections) {
               if (connection.from.name.equals(vertexName)) {
                  posToVertex.put(pos, connection.from);
                  c = connection;
                  break;
               }
            }

            if (c != null) {
               this.parser.addInline(this, c);
            }
         }

         this.attrs.remove("input vertices:");
         JSONObject keys = joinObj.getJSONObject("keys:");
         Set<Vertex> parentVertexes = new HashSet();

         for(Connection connection : this.vertex.parentConnections) {
            parentVertexes.add(connection.from);
         }

         parentVertexes.removeAll(posToVertex.values());
         Map<String, String> posToOpId = new LinkedHashMap();
         if (keys.length() != 0) {
            for(String key : JSONObject.getNames(keys)) {
               if (posToVertex.containsKey(key)) {
                  Vertex vertex = (Vertex)posToVertex.get(key);
                  if (vertex.rootOps.size() == 1) {
                     posToOpId.put(key, ((Op)vertex.rootOps.get(0)).operatorId);
                  } else if (vertex.rootOps.size() == 0 && vertex.vertexType == Vertex.VertexType.UNION) {
                     posToOpId.put(key, vertex.name);
                  } else {
                     Op singleRSOp = vertex.getSingleRSOp();
                     if (singleRSOp == null) {
                        throw new Exception("There are none or more than one root operators in a single vertex " + vertex.name + " when hive explain user is trying to identify the operator id.");
                     }

                     posToOpId.put(key, singleRSOp.operatorId);
                  }
               } else if (this.parent != null) {
                  posToOpId.put(key, this.parent.operatorId);
               } else {
                  if (parentVertexes.size() != 1) {
                     throw new Exception("Can not find the source operator on one of the branches of map join.");
                  }

                  Vertex vertex = (Vertex)parentVertexes.iterator().next();
                  parentVertexes.clear();
                  if (vertex.rootOps.size() == 1) {
                     posToOpId.put(key, ((Op)vertex.rootOps.get(0)).operatorId);
                  } else if (vertex.rootOps.size() == 0 && vertex.vertexType == Vertex.VertexType.UNION) {
                     posToOpId.put(key, vertex.name);
                  } else {
                     Op singleRSOp = vertex.getSingleRSOp();
                     if (singleRSOp == null) {
                        throw new Exception("There are none or more than one root operators in a single vertex " + vertex.name + " when hive explain user is trying to identify the operator id.");
                     }

                     posToOpId.put(key, singleRSOp.operatorId);
                  }
               }
            }
         }

         this.attrs.remove("keys:");
         StringBuffer sb = new StringBuffer();
         JSONArray conditionMap = joinObj.getJSONArray("condition map:");

         for(int index = 0; index < conditionMap.length(); ++index) {
            JSONObject cond = conditionMap.getJSONObject(index);
            String k = (String)cond.keys().next();
            JSONObject condObject = new JSONObject((String)cond.get(k));
            String type = condObject.getString("type");
            String left = condObject.getString("left");
            String right = condObject.getString("right");
            if (keys.length() != 0) {
               sb.append((String)posToOpId.get(left) + "." + keys.get(left) + "=" + (String)posToOpId.get(right) + "." + keys.get(right) + "(" + type + "),");
            } else {
               sb.append("(" + type + "),");
            }
         }

         this.attrs.remove("condition map:");
         this.attrs.put("Conds:", sb.substring(0, sb.length() - 1));
      } else {
         Map<String, String> posToOpId = new LinkedHashMap();
         if (this.vertex.mergeJoinDummyVertexs.size() != 0) {
            posToOpId.put(this.vertex.tag, this.parent.operatorId);

            for(Vertex v : this.vertex.mergeJoinDummyVertexs) {
               if (v.rootOps.size() != 1) {
                  throw new Exception("Can not find a single root operators in a single vertex " + v.name + " when hive explain user is trying to identify the operator id.");
               }

               posToOpId.put(v.tag, ((Op)v.rootOps.get(0)).operatorId);
            }
         } else {
            if (this.vertex.tagToInput.size() != this.vertex.parentConnections.size()) {
               throw new Exception("tagToInput size " + this.vertex.tagToInput.size() + " is different from parentConnections size " + this.vertex.parentConnections.size());
            }

            for(Map.Entry entry : this.vertex.tagToInput.entrySet()) {
               Connection c = null;

               for(Connection connection : this.vertex.parentConnections) {
                  if (connection.from.name.equals(entry.getValue())) {
                     Vertex v = connection.from;
                     if (v.rootOps.size() == 1) {
                        posToOpId.put(entry.getKey(), ((Op)v.rootOps.get(0)).operatorId);
                     } else if (v.rootOps.size() == 0 && v.vertexType == Vertex.VertexType.UNION) {
                        posToOpId.put(entry.getKey(), v.name);
                     } else {
                        Op singleRSOp = v.getSingleRSOp();
                        if (singleRSOp == null) {
                           throw new Exception("There are none or more than one root operators in a single vertex " + v.name + " when hive explain user is trying to identify the operator id.");
                        }

                        posToOpId.put(entry.getKey(), singleRSOp.operatorId);
                     }

                     c = connection;
                     break;
                  }
               }

               if (c == null) {
                  throw new Exception("Can not find " + (String)entry.getValue() + " while parsing keys of merge join operator");
               }
            }
         }

         JSONObject joinObj = this.opObject.getJSONObject(this.name);
         JSONObject keys = joinObj.getJSONObject("keys:");
         if (keys.length() != 0) {
            for(String key : JSONObject.getNames(keys)) {
               if (!posToOpId.containsKey(key)) {
                  throw new Exception("Can not find the source operator on one of the branches of merge join.");
               }
            }

            if (this.vertex != null) {
               for(Vertex v : this.vertex.mergeJoinDummyVertexs) {
                  this.parser.addInline(this, new Connection((String)null, v));
               }
            }
         }

         this.attrs.remove("keys:");
         StringBuffer sb = new StringBuffer();
         JSONArray conditionMap = joinObj.getJSONArray("condition map:");

         for(int index = 0; index < conditionMap.length(); ++index) {
            JSONObject cond = conditionMap.getJSONObject(index);
            String k = (String)cond.keys().next();
            JSONObject condObject = new JSONObject((String)cond.get(k));
            String type = condObject.getString("type");
            String left = condObject.getString("left");
            String right = condObject.getString("right");
            if (keys.length() != 0) {
               sb.append((String)posToOpId.get(left) + "." + keys.get(left) + "=" + (String)posToOpId.get(right) + "." + keys.get(right) + "(" + type + "),");
            } else {
               sb.append("(" + type + "),");
            }
         }

         this.attrs.remove("condition map:");
         this.attrs.put("Conds:", sb.substring(0, sb.length() - 1));
      }

   }

   private String getNameWithOpIdStats() {
      StringBuffer sb = new StringBuffer();
      sb.append(TezJsonParserUtils.renameReduceOutputOperator(this.name, this.vertex));
      if (this.operatorId != null) {
         sb.append(" [" + this.operatorId + "]");
      }

      if (!TezJsonParserUtils.OperatorNoStats.contains(this.name) && this.attrs.containsKey("Statistics:")) {
         sb.append(" (" + (String)this.attrs.get("Statistics:") + ")");
      }

      this.attrs.remove("Statistics:");
      return sb.toString();
   }

   public void print(Printer printer, int indentFlag, boolean branchOfJoinOp) throws Exception {
      if (this.parser.printSet.contains(this)) {
         printer.println(TezJsonParser.prefixString(indentFlag) + " Please refer to the previous " + this.getNameWithOpIdStats());
      } else {
         this.parser.printSet.add(this);
         if (!branchOfJoinOp) {
            printer.println(TezJsonParser.prefixString(indentFlag) + this.getNameWithOpIdStats());
         } else {
            printer.println(TezJsonParser.prefixString(indentFlag, "<-") + this.getNameWithOpIdStats());
         }

         branchOfJoinOp = false;
         if (this.type == Op.OpType.MAPJOIN || this.type == Op.OpType.MERGEJOIN) {
            this.inlineJoinOp();
            branchOfJoinOp = true;
         }

         List<Connection> noninlined = new ArrayList();
         if (this.parent == null && this.vertex != null) {
            for(Connection connection : this.vertex.parentConnections) {
               if (!this.parser.isInline(connection.from)) {
                  noninlined.add(connection);
               }
            }
         }

         ++indentFlag;
         if (!this.attrs.isEmpty()) {
            printer.println(TezJsonParser.prefixString(indentFlag) + TezJsonParserUtils.attrsToString(this.attrs));
         }

         if (this.parser.inlineMap.containsKey(this)) {
            for(int index = 0; index < ((List)this.parser.inlineMap.get(this)).size(); ++index) {
               Connection connection = (Connection)((List)this.parser.inlineMap.get(this)).get(index);
               connection.from.print(printer, indentFlag, connection.type, this.vertex);
            }
         }

         if (this.parent != null) {
            this.parent.print(printer, indentFlag, branchOfJoinOp);
         } else {
            for(int index = 0; index < noninlined.size(); ++index) {
               Vertex v = ((Connection)noninlined.get(index)).from;
               v.print(printer, indentFlag, ((Connection)noninlined.get(index)).type, this.vertex);
            }
         }

      }
   }

   public static enum OpType {
      MAPJOIN,
      MERGEJOIN,
      RS,
      OTHERS;
   }
}
