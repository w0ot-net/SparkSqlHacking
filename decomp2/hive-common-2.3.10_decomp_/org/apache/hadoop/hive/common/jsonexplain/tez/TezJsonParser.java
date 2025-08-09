package org.apache.hadoop.hive.common.jsonexplain.tez;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.hive.common.jsonexplain.JsonParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TezJsonParser implements JsonParser {
   public final Map stages = new LinkedHashMap();
   protected final Logger LOG = LoggerFactory.getLogger(this.getClass().getName());
   public final Set printSet = new LinkedHashSet();
   public final Map inlineMap = new LinkedHashMap();

   public void extractStagesAndPlans(JSONObject inputObject) throws Exception {
      JSONObject dependency = inputObject.getJSONObject("STAGE DEPENDENCIES");
      if (dependency != null && dependency.length() > 0) {
         for(String stageName : JSONObject.getNames(dependency)) {
            this.stages.put(stageName, new Stage(stageName, this));
         }

         for(String stageName : JSONObject.getNames(dependency)) {
            JSONObject dependentStageNames = dependency.getJSONObject(stageName);
            ((Stage)this.stages.get(stageName)).addDependency(dependentStageNames, this.stages);
         }
      }

      JSONObject stagePlans = inputObject.getJSONObject("STAGE PLANS");
      if (stagePlans != null && stagePlans.length() > 0) {
         for(String stageName : JSONObject.getNames(stagePlans)) {
            JSONObject stagePlan = stagePlans.getJSONObject(stageName);
            ((Stage)this.stages.get(stageName)).extractVertex(stagePlan);
         }
      }

   }

   public static String prefixString(int indentFlag) {
      StringBuilder sb = new StringBuilder();

      for(int index = 0; index < indentFlag; ++index) {
         sb.append("  ");
      }

      return sb.toString();
   }

   public static String prefixString(int indentFlag, String tail) {
      StringBuilder sb = new StringBuilder();

      for(int index = 0; index < indentFlag; ++index) {
         sb.append("  ");
      }

      int len = sb.length();
      return sb.replace(len - tail.length(), len, tail).toString();
   }

   public void print(JSONObject inputObject, PrintStream outputStream) throws Exception {
      this.LOG.info("JsonParser is parsing:" + inputObject.toString());
      this.extractStagesAndPlans(inputObject);
      Printer printer = new Printer();
      if (inputObject.has("cboInfo")) {
         printer.println(inputObject.getString("cboInfo"));
         printer.println();
      }

      for(Stage candidate : this.stages.values()) {
         if (candidate.tezStageDependency != null && candidate.tezStageDependency.size() > 0) {
            printer.println("Vertex dependency in root stage");

            for(Map.Entry entry : candidate.tezStageDependency.entrySet()) {
               StringBuilder sb = new StringBuilder();
               sb.append(((Vertex)entry.getKey()).name);
               sb.append(" <- ");
               boolean printcomma = false;

               for(Connection connection : (List)entry.getValue()) {
                  if (printcomma) {
                     sb.append(", ");
                  } else {
                     printcomma = true;
                  }

                  sb.append(connection.from.name + " (" + connection.type + ")");
               }

               printer.println(sb.toString());
            }

            printer.println();
         }
      }

      for(Stage candidate : this.stages.values()) {
         if (candidate.childStages.isEmpty()) {
            candidate.print(printer, 0);
         }
      }

      outputStream.println(printer.toString());
   }

   public void addInline(Op op, Connection connection) {
      List<Connection> list = (List)this.inlineMap.get(op);
      if (list == null) {
         list = new ArrayList();
         list.add(connection);
         this.inlineMap.put(op, list);
      } else {
         list.add(connection);
      }

   }

   public boolean isInline(Vertex v) {
      for(List list : this.inlineMap.values()) {
         for(Connection connection : list) {
            if (connection.from.equals(v)) {
               return true;
            }
         }
      }

      return false;
   }
}
