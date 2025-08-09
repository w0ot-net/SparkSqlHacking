package org.apache.hive.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.hadoop.hive.common.classification.InterfaceStability;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE})
@InterfaceStability.Unstable
public @interface HiveVersionAnnotation {
   String version();

   String shortVersion();

   String user();

   String date();

   String url();

   String revision();

   String branch();

   String srcChecksum();
}
