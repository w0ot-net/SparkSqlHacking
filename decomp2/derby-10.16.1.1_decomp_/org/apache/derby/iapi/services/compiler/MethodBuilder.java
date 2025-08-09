package org.apache.derby.iapi.services.compiler;

public interface MethodBuilder {
   void addThrownException(String var1);

   String getName();

   void complete();

   void getParameter(int var1);

   void push(byte var1);

   void push(boolean var1);

   void push(short var1);

   void push(int var1);

   void push(long var1);

   void push(float var1);

   void push(double var1);

   void push(String var1);

   void pushNull(String var1);

   void getField(LocalField var1);

   void getField(String var1, String var2, String var3);

   void getStaticField(String var1, String var2, String var3);

   void setField(LocalField var1);

   void putField(LocalField var1);

   void putField(String var1, String var2);

   void putField(String var1, String var2, String var3);

   void pushNewStart(String var1);

   void pushNewComplete(int var1);

   void pushNewArray(String var1, int var2);

   void pushThis();

   void upCast(String var1);

   void cast(String var1);

   void isInstanceOf(String var1);

   void pop();

   void endStatement();

   void methodReturn();

   void conditionalIfNull();

   void conditionalIf();

   void startElseCode();

   void completeConditional();

   int callMethod(short var1, String var2, String var3, String var4, int var5);

   Object describeMethod(short var1, String var2, String var3, String var4);

   int callMethod(Object var1);

   void callSuper();

   void getArrayElement(int var1);

   void setArrayElement(int var1);

   void swap();

   void dup();

   boolean statementNumHitLimit(int var1);
}
