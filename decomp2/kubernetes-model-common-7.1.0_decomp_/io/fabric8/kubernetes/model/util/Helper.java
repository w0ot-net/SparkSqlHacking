package io.fabric8.kubernetes.model.util;

import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Helper {
   private Helper() {
      throw new IllegalStateException("Utility class");
   }

   public static String loadJson(String path) {
      try {
         InputStream resourceAsStream = Helper.class.getResourceAsStream(path);

         String var3;
         try {
            Scanner scanner = (new Scanner((InputStream)Objects.requireNonNull(resourceAsStream))).useDelimiter("\\A");
            var3 = scanner.hasNext() ? scanner.next() : "";
         } catch (Throwable var5) {
            if (resourceAsStream != null) {
               try {
                  resourceAsStream.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (resourceAsStream != null) {
            resourceAsStream.close();
         }

         return var3;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   /** @deprecated */
   @Deprecated
   public static String getAnnotationValue(Class kubernetesResourceType, Class annotationClass) {
      Annotation annotation = getAnnotation(kubernetesResourceType, annotationClass);
      if (annotation instanceof Group) {
         return ((Group)annotation).value();
      } else {
         return annotation instanceof Version ? ((Version)annotation).value() : null;
      }
   }

   private static Annotation getAnnotation(Class kubernetesResourceType, Class annotationClass) {
      return (Annotation)Arrays.stream(kubernetesResourceType.getAnnotations()).filter((annotation) -> annotation.annotationType().equals(annotationClass)).findFirst().orElse((Object)null);
   }
}
