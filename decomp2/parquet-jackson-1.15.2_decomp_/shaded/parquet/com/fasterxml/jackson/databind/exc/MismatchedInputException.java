package shaded.parquet.com.fasterxml.jackson.databind.exc;

import java.io.Closeable;
import shaded.parquet.com.fasterxml.jackson.core.JsonLocation;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.util.ClassUtil;

public class MismatchedInputException extends JsonMappingException {
   protected Class _targetType;

   protected MismatchedInputException(JsonParser p, String msg) {
      this(p, msg, (JavaType)null);
   }

   protected MismatchedInputException(JsonParser p, String msg, JsonLocation loc) {
      super((Closeable)p, (String)msg, (JsonLocation)loc);
   }

   protected MismatchedInputException(JsonParser p, String msg, Class targetType) {
      super((Closeable)p, (String)msg);
      this._targetType = targetType;
   }

   protected MismatchedInputException(JsonParser p, String msg, JavaType targetType) {
      super((Closeable)p, (String)msg);
      this._targetType = ClassUtil.rawClass(targetType);
   }

   /** @deprecated */
   @Deprecated
   public static MismatchedInputException from(JsonParser p, String msg) {
      return from(p, (Class)null, msg);
   }

   public static MismatchedInputException from(JsonParser p, JavaType targetType, String msg) {
      return new MismatchedInputException(p, msg, targetType);
   }

   public static MismatchedInputException from(JsonParser p, Class targetType, String msg) {
      return new MismatchedInputException(p, msg, targetType);
   }

   public MismatchedInputException setTargetType(JavaType t) {
      this._targetType = t.getRawClass();
      return this;
   }

   public Class getTargetType() {
      return this._targetType;
   }
}
