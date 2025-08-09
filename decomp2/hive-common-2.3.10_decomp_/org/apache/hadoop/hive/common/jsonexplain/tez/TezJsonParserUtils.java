package org.apache.hadoop.hive.common.jsonexplain.tez;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TezJsonParserUtils {
   public static List OperatorNoStats = Arrays.asList("File Output Operator", "Reduce Output Operator");

   public static String renameReduceOutputOperator(String operatorName, Vertex vertex) {
      return operatorName.equals("Reduce Output Operator") && vertex.edgeType != null ? vertex.edgeType.name() : operatorName;
   }

   public static String attrsToString(Map attrs) {
      StringBuffer sb = new StringBuffer();
      boolean first = true;

      for(Map.Entry entry : attrs.entrySet()) {
         if (first) {
            first = false;
         } else {
            sb.append(",");
         }

         sb.append((String)entry.getKey() + (String)entry.getValue());
      }

      return sb.toString();
   }
}
