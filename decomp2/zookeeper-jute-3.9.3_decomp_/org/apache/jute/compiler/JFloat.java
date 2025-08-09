package org.apache.jute.compiler;

public class JFloat extends JType {
   public JFloat() {
      super("float", "float", "float", "float", "Float", "Float", "float", "toFloat");
   }

   public String getSignature() {
      return "f";
   }

   public String genJavaHashCode(String fname) {
      return "    ret = java.lang.Float.hashCode(" + fname + ");\n";
   }
}
