package org.apache.jute.compiler;

public class JVector extends JCompType {
   private static int level = 0;
   private JType mElement;

   private static String getId(String id) {
      return id + getLevel();
   }

   private static String getLevel() {
      return Integer.toString(level);
   }

   private static void incrLevel() {
      ++level;
   }

   private static void decrLevel() {
      --level;
   }

   public JVector(JType t) {
      super("struct " + extractVectorName(t), " ::std::vector<" + t.getCppType() + ">", "System.Collections.Generic.List<" + t.getCsharpType() + ">", "java.util.List<" + t.getJavaType() + ">", "Vector", "System.Collections.Generic.List<" + t.getCsharpType() + ">", "java.util.ArrayList<" + t.getJavaType() + ">");
      this.mElement = t;
   }

   public String getSignature() {
      return "[" + this.mElement.getSignature() + "]";
   }

   public String genJavaCompareTo(String fname) {
      return "    throw new UnsupportedOperationException(\"comparing " + fname + " is unimplemented\");\n";
   }

   public String genJavaReadWrapper(String fname, String tag, boolean decl) {
      StringBuilder ret = new StringBuilder("");
      if (decl) {
         ret.append("      java.util.List " + fname + ";\n");
      }

      ret.append("    {\n");
      incrLevel();
      ret.append("      Index " + getId("vidx") + " = a_.startVector(\"" + tag + "\");\n");
      ret.append("      if (" + getId("vidx") + "!= null) {");
      ret.append("          " + fname + "=new java.util.ArrayList<" + this.mElement.getJavaType() + ">();\n");
      ret.append("          for (; !" + getId("vidx") + ".done(); " + getId("vidx") + ".incr()) {\n");
      ret.append(this.mElement.genJavaReadWrapper(getId("e"), getId("e"), true));
      ret.append("            " + fname + ".add(" + getId("e") + ");\n");
      ret.append("          }\n");
      ret.append("      }\n");
      ret.append("    a_.endVector(\"" + tag + "\");\n");
      decrLevel();
      ret.append("    }\n");
      return ret.toString();
   }

   public String genJavaReadMethod(String fname, String tag) {
      return this.genJavaReadWrapper(fname, tag, false);
   }

   public String genJavaWriteWrapper(String fname, String tag) {
      StringBuilder ret = new StringBuilder("    {\n");
      incrLevel();
      ret.append("      a_.startVector(" + fname + ",\"" + tag + "\");\n");
      ret.append("      if (" + fname + "!= null) {");
      ret.append("          int " + getId("len") + " = " + fname + ".size();\n");
      ret.append("          for(int " + getId("vidx") + " = 0; " + getId("vidx") + "<" + getId("len") + "; " + getId("vidx") + "++) {\n");
      ret.append("            " + this.mElement.getJavaWrapperType() + " " + getId("e") + " = (" + this.mElement.getJavaWrapperType() + ") " + fname + ".get(" + getId("vidx") + ");\n");
      ret.append(this.mElement.genJavaWriteWrapper(getId("e"), getId("e")));
      ret.append("          }\n");
      ret.append("      }\n");
      ret.append("      a_.endVector(" + fname + ",\"" + tag + "\");\n");
      ret.append("    }\n");
      decrLevel();
      return ret.toString();
   }

   public String genJavaWriteMethod(String fname, String tag) {
      return this.genJavaWriteWrapper(fname, tag);
   }

   public JType getElementType() {
      return this.mElement;
   }

   public String genCsharpWriteWrapper(String fname, String tag) {
      StringBuilder ret = new StringBuilder("    {\n");
      incrLevel();
      ret.append("      a_.StartVector(" + capitalize(fname) + ",\"" + tag + "\");\n");
      ret.append("      if (" + capitalize(fname) + "!= null) {");
      ret.append("          int " + getId("len") + " = " + capitalize(fname) + ".Count;\n");
      ret.append("          for(int " + getId("vidx") + " = 0; " + getId("vidx") + "<" + getId("len") + "; " + getId("vidx") + "++) {\n");
      ret.append("            " + this.mElement.getCsharpWrapperType() + " " + getId("e") + " = (" + this.mElement.getCsharpWrapperType() + ") " + capitalize(fname) + "[" + getId("vidx") + "];\n");
      ret.append(this.mElement.genCsharpWriteWrapper(getId("e"), getId("e")));
      ret.append("          }\n");
      ret.append("      }\n");
      ret.append("      a_.EndVector(" + capitalize(fname) + ",\"" + tag + "\");\n");
      ret.append("    }\n");
      decrLevel();
      return ret.toString();
   }

   String genCsharpWriteMethod(String fname, String tag) {
      return this.genCsharpWriteWrapper(fname, tag);
   }

   public String genCsharpReadWrapper(String fname, String tag, boolean decl) {
      StringBuilder ret = new StringBuilder();
      if (decl) {
         ret.append("      System.Collections.Generic.List<" + this.mElement.getCsharpType() + "> " + capitalize(fname) + ";\n");
      }

      ret.append("    {\n");
      incrLevel();
      ret.append("      IIndex " + getId("vidx") + " = a_.StartVector(\"" + tag + "\");\n");
      ret.append("      if (" + getId("vidx") + "!= null) {");
      ret.append("          " + capitalize(fname) + "=new System.Collections.Generic.List<" + this.mElement.getCsharpType() + ">();\n");
      ret.append("          for (; !" + getId("vidx") + ".Done(); " + getId("vidx") + ".Incr()) {\n");
      ret.append(this.mElement.genCsharpReadWrapper(getId("e"), getId("e"), true));
      ret.append("            " + capitalize(fname) + ".Add(" + getId("e") + ");\n");
      ret.append("          }\n");
      ret.append("      }\n");
      ret.append("    a_.EndVector(\"" + tag + "\");\n");
      decrLevel();
      ret.append("    }\n");
      return ret.toString();
   }

   String genCsharpReadMethod(String fname, String tag) {
      return this.genCsharpReadWrapper(fname, tag, false);
   }

   static String extractVectorName(JType jvType) {
      return JRecord.extractMethodSuffix(jvType) + "_vector";
   }
}
