package org.apache.jute.compiler;

public abstract class JType {
   private String mCName;
   private String mCppName;
   private String mCsharpName;
   private String mJavaName;
   protected String mMethodSuffix;
   private String mWrapper;
   private String mSharpWrapper;
   private String mUnwrapMethod;

   JType(String cname, String cppname, String csharpName, String javaname, String suffix, String wrapper, String csharpWrapper, String unwrap) {
      this.mCName = cname;
      this.mCppName = cppname;
      this.mCsharpName = "Id".equals(csharpName) ? "ZKId" : csharpName;
      this.mJavaName = javaname;
      this.mMethodSuffix = suffix;
      this.mWrapper = wrapper;
      this.mSharpWrapper = csharpWrapper;
      this.mUnwrapMethod = unwrap;
   }

   abstract String getSignature();

   String genCppDecl(String fname) {
      return "  " + this.mCppName + " m" + fname + ";\n";
   }

   String genCDecl(String name) {
      return "    " + this.mCName + " " + name + ";\n";
   }

   public String genCsharpDecl(String name) {
      return "  private " + this.mCsharpName + " " + name + ";\n";
   }

   String genJavaDecl(String fname) {
      return "  private " + this.mJavaName + " " + fname + ";\n";
   }

   String genJavaConstructorParam(String fname) {
      return "        " + this.mJavaName + " " + fname;
   }

   String genCppGetSet(String fname, int fIdx) {
      String getFunc = "  virtual " + this.mCppName + " get" + fname + "() const {\n";
      getFunc = getFunc + "    return m" + fname + ";\n";
      getFunc = getFunc + "  }\n";
      String setFunc = "  virtual void set" + fname + "(" + this.mCppName + " m_) {\n";
      setFunc = setFunc + "    m" + fname + "=m_; bs_.set(" + fIdx + ");\n";
      setFunc = setFunc + "  }\n";
      return getFunc + setFunc;
   }

   String genCsharpGetSet(String fname, int fIdx) {
      String getFunc = "  public " + this.getCsharpType() + " " + capitalize(fname) + " { get; set; } ";
      return getFunc;
   }

   static String capitalize(String s) {
      return s.substring(0, 1).toUpperCase() + s.substring(1);
   }

   String genJavaGetSet(String fname, int fIdx) {
      String getFunc = "  public " + this.mJavaName + " get" + capitalize(fname) + "() {\n";
      getFunc = getFunc + "    return " + fname + ";\n";
      getFunc = getFunc + "  }\n";
      String setFunc = "  public void set" + capitalize(fname) + "(" + this.mJavaName + " m_) {\n";
      setFunc = setFunc + "    " + fname + "=m_;\n";
      setFunc = setFunc + "  }\n";
      return getFunc + setFunc;
   }

   String getCType() {
      return this.mCName;
   }

   String getCppType() {
      return this.mCppName;
   }

   String getCsharpType() {
      return this.mCsharpName;
   }

   String getJavaType() {
      return this.mJavaName;
   }

   String getJavaWrapperType() {
      return this.mWrapper;
   }

   String getCsharpWrapperType() {
      return this.mSharpWrapper;
   }

   String getMethodSuffix() {
      return this.mMethodSuffix;
   }

   String genJavaWriteMethod(String fname, String tag) {
      return "    a_.write" + this.mMethodSuffix + "(" + fname + ",\"" + tag + "\");\n";
   }

   String genJavaReadMethod(String fname, String tag) {
      return "    " + fname + "=a_.read" + this.mMethodSuffix + "(\"" + tag + "\");\n";
   }

   String genJavaReadWrapper(String fname, String tag, boolean decl) {
      String ret = "";
      if (decl) {
         ret = "    " + this.mWrapper + " " + fname + ";\n";
      }

      return ret + "    " + fname + "=new " + this.mWrapper + "(a_.read" + this.mMethodSuffix + "(\"" + tag + "\"));\n";
   }

   String genJavaWriteWrapper(String fname, String tag) {
      return "        a_.write" + this.mMethodSuffix + "(" + fname + "." + this.mUnwrapMethod + "(),\"" + tag + "\");\n";
   }

   String genJavaCompareTo(String fname) {
      return "    ret = (" + fname + " == peer." + fname + ")? 0 :((" + fname + "<peer." + fname + ")?-1:1);\n";
   }

   String genJavaEquals(String fname, String peer) {
      return "    ret = (" + fname + "==" + peer + ");\n";
   }

   String genJavaHashCode(String fname) {
      return "    ret = (int)" + fname + ";\n";
   }

   String genJavaConstructorSet(String fname, String name) {
      return "    this." + fname + "=" + name + ";\n";
   }

   String genCsharpWriteMethod(String fname, String tag) {
      return "    a_.Write" + this.mMethodSuffix + "(" + capitalize(fname) + ",\"" + tag + "\");\n";
   }

   String genCsharpReadMethod(String fname, String tag) {
      return "    " + capitalize(fname) + "=a_.Read" + this.mMethodSuffix + "(\"" + tag + "\");\n";
   }

   String genCsharpReadWrapper(String fname, String tag, boolean decl) {
      String ret = "";
      if (decl) {
         ret = "    " + this.mWrapper + " " + fname + ";\n";
      }

      return ret + "    " + fname + "=a_.Read" + this.mMethodSuffix + "(\"" + tag + "\");\n";
   }

   String genCsharpWriteWrapper(String fname, String tag) {
      return this.mUnwrapMethod == null ? "        a_.Write" + this.mMethodSuffix + "(" + fname + "," + tag + ");\n" : "        a_.Write" + this.mMethodSuffix + "(" + fname + "." + this.mUnwrapMethod + "(),\"" + tag + "\");\n";
   }

   String genCsharpCompareTo(String name) {
      return "    ret = (" + capitalize(name) + " == peer." + capitalize(name) + ")? 0 :((" + capitalize(name) + "<peer." + capitalize(name) + ")?-1:1);\n";
   }

   String genCsharpEquals(String name, String peer) {
      String[] peerSplit = peer.split("\\.");
      return "    ret = (" + capitalize(name) + "==" + peerSplit[0] + "." + capitalize(peerSplit[1]) + ");\n";
   }

   String genCsharpHashCode(String fname) {
      return "    ret = (int)" + capitalize(fname) + ";\n";
   }

   String genCsharpConstructorSet(String mName, String fname) {
      return capitalize(fname) + "=" + mName + ";\n";
   }

   public String genCsharpConstructorParam(String fname) {
      return "  " + this.mCsharpName + " " + fname + "\n";
   }
}
