package org.apache.jute.compiler;

public class JInt extends JType {
   public JInt() {
      super("int32_t", "int32_t", "int", "int", "Int", "Integer", "int", "toInt");
   }

   public String getSignature() {
      return "i";
   }
}
