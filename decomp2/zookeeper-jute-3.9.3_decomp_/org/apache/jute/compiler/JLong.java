package org.apache.jute.compiler;

public class JLong extends JType {
   public JLong() {
      super("int64_t", "int64_t", "long", "long", "Long", "Long", "long", "toLong");
   }

   public String getSignature() {
      return "l";
   }

   public String genJavaHashCode(String fname) {
      return "    ret = java.lang.Long.hashCode(" + fname + ");\n";
   }
}
