package org.apache.jute.compiler;

public class JString extends JCompType {
   public JString() {
      super("char *", " ::std::string", "string", "String", "String", "String", "string");
   }

   public String getSignature() {
      return "s";
   }

   public String genJavaReadWrapper(String fname, String tag, boolean decl) {
      String ret = "";
      if (decl) {
         ret = "    String " + fname + ";\n";
      }

      return ret + "        " + fname + "=a_.readString(\"" + tag + "\");\n";
   }

   public String genJavaWriteWrapper(String fname, String tag) {
      return "        a_.writeString(" + fname + ",\"" + tag + "\");\n";
   }
}
