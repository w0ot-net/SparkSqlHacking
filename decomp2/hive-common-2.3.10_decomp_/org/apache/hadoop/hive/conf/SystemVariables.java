package org.apache.hadoop.hive.conf;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.valcoersion.JavaIOTmpdirVariableCoercion;
import org.apache.hadoop.hive.conf.valcoersion.VariableCoercion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemVariables {
   private static final Logger l4j = LoggerFactory.getLogger(SystemVariables.class);
   protected static Pattern varPat = Pattern.compile("\\$\\{[^\\}\\$ ]+\\}");
   private static final SystemVariables INSTANCE = new SystemVariables();
   private static final Map COERCIONS;
   public static final String ENV_PREFIX = "env:";
   public static final String SYSTEM_PREFIX = "system:";
   public static final String HIVECONF_PREFIX = "hiveconf:";
   public static final String HIVEVAR_PREFIX = "hivevar:";
   public static final String METACONF_PREFIX = "metaconf:";
   public static final String SET_COLUMN_NAME = "set";

   protected String getSubstitute(Configuration conf, String variableName) {
      try {
         if (variableName.startsWith("system:")) {
            String propertyName = variableName.substring("system:".length());
            String originalValue = System.getProperty(propertyName);
            return this.applyCoercion(variableName, originalValue);
         }
      } catch (SecurityException se) {
         l4j.warn("Unexpected SecurityException in Configuration", se);
      }

      if (variableName.startsWith("env:")) {
         return System.getenv(variableName.substring("env:".length()));
      } else {
         return conf != null && variableName.startsWith("hiveconf:") ? conf.get(variableName.substring("hiveconf:".length())) : null;
      }
   }

   private String applyCoercion(String variableName, String originalValue) {
      return COERCIONS.containsKey(variableName) ? ((VariableCoercion)COERCIONS.get(variableName)).getCoerced(originalValue) : originalValue;
   }

   public static boolean containsVar(String expr) {
      return expr != null && varPat.matcher(expr).find();
   }

   static String substitute(String expr) {
      return expr == null ? null : INSTANCE.substitute((Configuration)null, expr, 1);
   }

   static String substitute(Configuration conf, String expr) {
      return expr == null ? null : INSTANCE.substitute(conf, expr, 1);
   }

   protected final String substitute(Configuration conf, String expr, int depth) {
      Matcher match = varPat.matcher("");
      String eval = expr;
      StringBuilder builder = new StringBuilder();

      int s;
      for(s = 0; s <= depth; ++s) {
         match.reset(eval);
         builder.setLength(0);
         int prev = 0;

         boolean found;
         for(found = false; match.find(prev); prev = match.end()) {
            String group = match.group();
            String var = group.substring(2, group.length() - 1);
            String substitute = this.getSubstitute(conf, var);
            if (substitute == null) {
               substitute = group;
            } else {
               found = true;
            }

            builder.append(eval.substring(prev, match.start())).append(substitute);
         }

         if (!found) {
            return eval;
         }

         builder.append(eval.substring(prev));
         eval = builder.toString();
      }

      if (s > depth) {
         throw new IllegalStateException("Variable substitution depth is deeper than " + depth + " for expression " + expr);
      } else {
         return eval;
      }
   }

   static {
      COERCIONS = ImmutableMap.builder().put(JavaIOTmpdirVariableCoercion.INSTANCE.getName(), JavaIOTmpdirVariableCoercion.INSTANCE).build();
   }
}
