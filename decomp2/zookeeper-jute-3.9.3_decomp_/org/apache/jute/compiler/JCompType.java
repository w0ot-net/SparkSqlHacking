package org.apache.jute.compiler;

abstract class JCompType extends JType {
   JCompType(String cType, String cppType, String csharpType, String javaType, String suffix, String wrapper, String csharpWrapper) {
      super(cType, cppType, csharpType, javaType, suffix, wrapper, csharpWrapper, (String)null);
   }

   String genCppGetSet(String fname, int fIdx) {
      String cgetFunc = "  virtual const " + this.getCppType() + "& get" + fname + "() const {\n";
      cgetFunc = cgetFunc + "    return m" + fname + ";\n";
      cgetFunc = cgetFunc + "  }\n";
      String getFunc = "  virtual " + this.getCppType() + "& get" + fname + "() {\n";
      getFunc = getFunc + "    bs_.set(" + fIdx + ");return m" + fname + ";\n";
      getFunc = getFunc + "  }\n";
      return cgetFunc + getFunc;
   }

   String genJavaCompareTo(String fname) {
      return "    ret = " + fname + ".compareTo(peer." + fname + ");\n";
   }

   String genJavaEquals(String fname, String peer) {
      return "    ret = " + fname + ".equals(" + peer + ");\n";
   }

   String genJavaHashCode(String fname) {
      return "    ret = " + fname + ".hashCode();\n";
   }

   String genCsharpHashCode(String fname) {
      return "    ret = " + capitalize(fname) + ".GetHashCode();\n";
   }

   String genCsharpEquals(String name, String peer) {
      String[] peerSplit = peer.split("\\.");
      return "    ret = " + capitalize(name) + ".Equals(" + peerSplit[0] + "." + capitalize(peerSplit[1]) + ");\n";
   }

   String genCsharpCompareTo(String name) {
      return "    ret = " + capitalize(name) + ".CompareTo(peer." + capitalize(name) + ");\n";
   }
}
