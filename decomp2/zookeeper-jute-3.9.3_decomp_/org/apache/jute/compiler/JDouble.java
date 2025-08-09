package org.apache.jute.compiler;

public class JDouble extends JType {
   public JDouble() {
      super("double", "double", "double", "double", "Double", "Double", "double", "toDouble");
   }

   public String getSignature() {
      return "d";
   }

   public String genJavaHashCode(String fname) {
      return "    ret = java.lang.Double.hashCode(" + fname + ");\n";
   }
}
