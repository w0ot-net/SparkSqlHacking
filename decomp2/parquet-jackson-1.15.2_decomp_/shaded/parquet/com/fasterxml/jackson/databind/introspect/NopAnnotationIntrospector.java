package shaded.parquet.com.fasterxml.jackson.databind.introspect;

import java.io.Serializable;
import shaded.parquet.com.fasterxml.jackson.core.Version;
import shaded.parquet.com.fasterxml.jackson.databind.AnnotationIntrospector;
import shaded.parquet.com.fasterxml.jackson.databind.cfg.PackageVersion;

public abstract class NopAnnotationIntrospector extends AnnotationIntrospector implements Serializable {
   private static final long serialVersionUID = 1L;
   public static final NopAnnotationIntrospector instance = new NopAnnotationIntrospector() {
      private static final long serialVersionUID = 1L;

      public Version version() {
         return PackageVersion.VERSION;
      }
   };

   public Version version() {
      return Version.unknownVersion();
   }
}
