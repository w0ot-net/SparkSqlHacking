package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationFeature;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;

public class StackTraceElementDeserializer extends StdScalarDeserializer {
   private static final long serialVersionUID = 1L;
   protected final JsonDeserializer _adapterDeserializer;

   /** @deprecated */
   @Deprecated
   public StackTraceElementDeserializer() {
      this((JsonDeserializer)null);
   }

   protected StackTraceElementDeserializer(JsonDeserializer ad) {
      super(StackTraceElement.class);
      this._adapterDeserializer = ad;
   }

   public static JsonDeserializer construct(DeserializationContext ctxt) throws JsonMappingException {
      if (ctxt == null) {
         return new StackTraceElementDeserializer();
      } else {
         JsonDeserializer<?> adapterDeser = ctxt.findNonContextualValueDeserializer(ctxt.constructType(Adapter.class));
         return new StackTraceElementDeserializer(adapterDeser);
      }
   }

   public StackTraceElement deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonToken t = p.currentToken();
      if (t != JsonToken.START_OBJECT && t != JsonToken.FIELD_NAME) {
         if (t == JsonToken.START_ARRAY && ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            p.nextToken();
            StackTraceElement value = this.deserialize(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
               this.handleMissingEndArrayForSingle(p, ctxt);
            }

            return value;
         } else {
            return (StackTraceElement)ctxt.handleUnexpectedToken(this._valueClass, p);
         }
      } else {
         Adapter adapted;
         if (this._adapterDeserializer == null) {
            adapted = (Adapter)ctxt.readValue(p, Adapter.class);
         } else {
            adapted = (Adapter)this._adapterDeserializer.deserialize(p, ctxt);
         }

         return this.constructValue(ctxt, adapted);
      }
   }

   protected StackTraceElement constructValue(DeserializationContext ctxt, Adapter adapted) {
      return this.constructValue(ctxt, adapted.className, adapted.methodName, adapted.fileName, adapted.lineNumber, adapted.moduleName, adapted.moduleVersion, adapted.classLoaderName);
   }

   /** @deprecated */
   @Deprecated
   protected StackTraceElement constructValue(DeserializationContext ctxt, String className, String methodName, String fileName, int lineNumber, String moduleName, String moduleVersion) {
      return this.constructValue(ctxt, className, methodName, fileName, lineNumber, moduleName, moduleVersion, (String)null);
   }

   protected StackTraceElement constructValue(DeserializationContext ctxt, String className, String methodName, String fileName, int lineNumber, String moduleName, String moduleVersion, String classLoaderName) {
      return new StackTraceElement(className, methodName, fileName, lineNumber);
   }

   public static final class Adapter {
      public String className = "";
      public String classLoaderName;
      public String declaringClass;
      public String format;
      public String fileName = "";
      public String methodName = "";
      public int lineNumber = -1;
      public String moduleName;
      public String moduleVersion;
      public boolean nativeMethod;
   }
}
