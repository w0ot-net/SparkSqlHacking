package py4j;

import java.math.BigDecimal;

public class ReturnObject {
   private String name;
   private Object primitiveObject;
   private boolean isReference;
   private boolean isMap;
   private boolean isList;
   private boolean isNull;
   private boolean isError;
   private boolean isVoid;
   private boolean isArray;
   private boolean isIterator;
   private boolean isSet;
   private boolean isDecimal;
   private int size;
   private String commandPart;

   public static ReturnObject getArrayReturnObject(String name, int size) {
      ReturnObject rObject = new ReturnObject();
      rObject.name = name;
      rObject.size = size;
      rObject.isArray = true;
      rObject.commandPart = 't' + name;
      return rObject;
   }

   public static ReturnObject getDecimalReturnObject(Object object) {
      BigDecimal decimal = (BigDecimal)object;
      ReturnObject rObject = new ReturnObject();
      rObject.isDecimal = true;
      rObject.commandPart = 'D' + decimal.toPlainString();
      return rObject;
   }

   public static ReturnObject getErrorReferenceReturnObject(String name) {
      ReturnObject rObject = new ReturnObject();
      rObject.name = name;
      rObject.isError = true;
      StringBuilder builder = new StringBuilder();
      builder.append('x');
      builder.append('r');
      builder.append(name);
      rObject.commandPart = builder.toString();
      return rObject;
   }

   public static ReturnObject getErrorReturnObject() {
      ReturnObject rObject = new ReturnObject();
      rObject.isError = true;
      rObject.commandPart = String.valueOf('x');
      return rObject;
   }

   public static ReturnObject getErrorReturnObject(Throwable throwable) {
      ReturnObject rObject = new ReturnObject();
      rObject.isError = true;
      StringBuilder builder = new StringBuilder();
      builder.append('x');
      builder.append('s');
      builder.append(StringUtil.escape(Protocol.getThrowableAsString(throwable)));
      rObject.commandPart = builder.toString();
      return rObject;
   }

   public static ReturnObject getIteratorReturnObject(String name) {
      ReturnObject rObject = new ReturnObject();
      rObject.name = name;
      rObject.isIterator = true;
      rObject.commandPart = 'g' + name;
      return rObject;
   }

   public static ReturnObject getListReturnObject(String name, int size) {
      ReturnObject rObject = new ReturnObject();
      rObject.name = name;
      rObject.size = size;
      rObject.isList = true;
      rObject.commandPart = 'l' + name;
      return rObject;
   }

   public static ReturnObject getMapReturnObject(String name, int size) {
      ReturnObject rObject = new ReturnObject();
      rObject.name = name;
      rObject.size = size;
      rObject.isMap = true;
      rObject.commandPart = 'a' + name;
      return rObject;
   }

   public static ReturnObject getNullReturnObject() {
      ReturnObject rObject = new ReturnObject();
      rObject.isNull = true;
      rObject.commandPart = String.valueOf('n');
      return rObject;
   }

   public static ReturnObject getPrimitiveReturnObject(Object primitive) {
      ReturnObject rObject = new ReturnObject();
      rObject.primitiveObject = primitive;
      char primitiveType = Protocol.getPrimitiveType(primitive);
      if (primitiveType == 's') {
         rObject.commandPart = primitiveType + StringUtil.escape(primitive.toString());
      } else if (primitiveType == 'j') {
         rObject.commandPart = primitiveType + Protocol.encodeBytes((byte[])primitive);
      } else {
         rObject.commandPart = primitiveType + primitive.toString();
      }

      return rObject;
   }

   public static ReturnObject getReferenceReturnObject(String name) {
      ReturnObject rObject = new ReturnObject();
      rObject.name = name;
      rObject.isReference = true;
      rObject.commandPart = 'r' + name;
      return rObject;
   }

   public static ReturnObject getSetReturnObject(String name, int size) {
      ReturnObject rObject = new ReturnObject();
      rObject.name = name;
      rObject.size = size;
      rObject.isSet = true;
      rObject.commandPart = 'h' + name;
      return rObject;
   }

   public static ReturnObject getVoidReturnObject() {
      ReturnObject rObject = new ReturnObject();
      rObject.isVoid = true;
      rObject.commandPart = String.valueOf('v');
      return rObject;
   }

   private ReturnObject() {
   }

   public String getCommandPart() {
      return this.commandPart;
   }

   public String getName() {
      return this.name;
   }

   public Object getPrimitiveObject() {
      return this.primitiveObject;
   }

   public int getSize() {
      return this.size;
   }

   public boolean isArray() {
      return this.isArray;
   }

   public boolean isDecimal() {
      return this.isDecimal;
   }

   public boolean isError() {
      return this.isError;
   }

   public boolean isIterator() {
      return this.isIterator;
   }

   public boolean isList() {
      return this.isList;
   }

   public boolean isMap() {
      return this.isMap;
   }

   public boolean isNull() {
      return this.isNull;
   }

   public boolean isReference() {
      return this.isReference;
   }

   public boolean isSet() {
      return this.isSet;
   }

   public boolean isVoid() {
      return this.isVoid;
   }

   public void setArray(boolean isArray) {
      this.isArray = isArray;
   }

   public void setCommandPart(String commandPart) {
      this.commandPart = commandPart;
   }

   public void setError(boolean isError) {
      this.isError = isError;
   }

   public void setIterator(boolean isIterator) {
      this.isIterator = isIterator;
   }

   public void setList(boolean isList) {
      this.isList = isList;
   }

   public void setMap(boolean isMap) {
      this.isMap = isMap;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setNull(boolean isNull) {
      this.isNull = isNull;
   }

   public void setPrimitiveObject(Object primitiveObject) {
      this.primitiveObject = primitiveObject;
   }

   public void setReference(boolean isReference) {
      this.isReference = isReference;
   }

   public void setSet(boolean isSet) {
      this.isSet = isSet;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public void setVoid(boolean isVoid) {
      this.isVoid = isVoid;
   }
}
