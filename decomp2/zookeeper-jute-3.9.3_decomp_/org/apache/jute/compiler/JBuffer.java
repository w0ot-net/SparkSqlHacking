package org.apache.jute.compiler;

public class JBuffer extends JCompType {
   public JBuffer() {
      super("struct buffer", " ::std::string", "byte[]", "byte[]", "Buffer", "byte[]", "byte[]");
   }

   public String genCppGetSet(String fname, int fIdx) {
      String cgetFunc = "  virtual const " + this.getCppType() + "& get" + fname + "() const {\n";
      cgetFunc = cgetFunc + "    return m" + fname + ";\n";
      cgetFunc = cgetFunc + "  }\n";
      String getFunc = "  virtual " + this.getCppType() + "& get" + fname + "() {\n";
      getFunc = getFunc + "    bs_.set(" + fIdx + ");return m" + fname + ";\n";
      getFunc = getFunc + "  }\n";
      return cgetFunc + getFunc;
   }

   public String getSignature() {
      return "B";
   }

   public String genJavaReadWrapper(String fname, String tag, boolean decl) {
      String ret = "";
      if (decl) {
         ret = "    byte[] " + fname + ";\n";
      }

      return ret + "        " + fname + "=a_.readBuffer(\"" + tag + "\");\n";
   }

   public String genJavaWriteWrapper(String fname, String tag) {
      return "        a_.writeBuffer(" + fname + ",\"" + tag + "\");\n";
   }

   public String genJavaCompareTo(String fname, String other) {
      StringBuilder sb = new StringBuilder();
      sb.append("    {\n");
      sb.append("      byte[] my = " + fname + ";\n");
      sb.append("      byte[] ur = " + other + ";\n");
      sb.append("      ret = org.apache.jute.Utils.compareBytes(my,0,my.length,ur,0,ur.length);\n");
      sb.append("    }\n");
      return sb.toString();
   }

   public String genJavaCompareTo(String fname) {
      return this.genJavaCompareTo(fname, "peer." + fname);
   }

   public String genJavaCompareToWrapper(String fname, String other) {
      return "    " + this.genJavaCompareTo(fname, other);
   }

   public String genJavaEquals(String fname, String peer) {
      return "    ret = java.util.Arrays.equals(" + fname + "," + peer + ");\n";
   }

   public String genJavaHashCode(String fname) {
      return "    ret = java.util.Arrays.hashCode(" + fname + ");\n";
   }

   public String genJavaSlurpBytes(String b, String s, String l) {
      StringBuilder sb = new StringBuilder();
      sb.append("        {\n");
      sb.append("           int i = org.apache.jute.Utils.readVInt(" + b + ", " + s + ");\n");
      sb.append("           int z = WritableUtils.getVIntSize(i);\n");
      sb.append("           " + s + " += z+i; " + l + " -= (z+i);\n");
      sb.append("        }\n");
      return sb.toString();
   }

   public String genJavaCompareBytes() {
      StringBuilder sb = new StringBuilder();
      sb.append("        {\n");
      sb.append("           int i1 = org.apache.jute.Utils.readVInt(b1, s1);\n");
      sb.append("           int i2 = org.apache.jute.Utils.readVInt(b2, s2);\n");
      sb.append("           int z1 = WritableUtils.getVIntSize(i1);\n");
      sb.append("           int z2 = WritableUtils.getVIntSize(i2);\n");
      sb.append("           s1+=z1; s2+=z2; l1-=z1; l2-=z2;\n");
      sb.append("           int r1 = org.apache.jute.Utils.compareBytes(b1,s1,l1,b2,s2,l2);\n");
      sb.append("           if (r1 != 0) { return (r1<0)?-1:0; }\n");
      sb.append("           s1+=i1; s2+=i2; l1-=i1; l1-=i2;\n");
      sb.append("        }\n");
      return sb.toString();
   }
}
