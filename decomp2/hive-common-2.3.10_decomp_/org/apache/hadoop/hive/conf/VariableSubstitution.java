package org.apache.hadoop.hive.conf;

import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VariableSubstitution extends SystemVariables {
   private static final Logger l4j = LoggerFactory.getLogger(VariableSubstitution.class);
   private final HiveVariableSource hiveVariableSource;

   public VariableSubstitution(HiveVariableSource hiveVariableSource) {
      this.hiveVariableSource = hiveVariableSource;
   }

   protected String getSubstitute(Configuration conf, String var) {
      String val = super.getSubstitute(conf, var);
      if (val == null && this.hiveVariableSource != null) {
         Map<String, String> vars = this.hiveVariableSource.getHiveVariable();
         if (var.startsWith("hivevar:")) {
            val = (String)vars.get(var.substring("hivevar:".length()));
         } else {
            val = (String)vars.get(var);
         }
      }

      return val;
   }

   public String substitute(HiveConf conf, String expr) {
      if (expr == null) {
         return expr;
      } else if (HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVEVARIABLESUBSTITUTE)) {
         l4j.debug("Substitution is on: " + expr);
         int depth = HiveConf.getIntVar(conf, HiveConf.ConfVars.HIVEVARIABLESUBSTITUTEDEPTH);
         return this.substitute(conf, expr, depth);
      } else {
         return expr;
      }
   }
}
