package org.apache.spark.deploy.history;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class CompactionResultCode$ extends Enumeration {
   public static final CompactionResultCode$ MODULE$ = new CompactionResultCode$();
   private static final Enumeration.Value SUCCESS;
   private static final Enumeration.Value NOT_ENOUGH_FILES;
   private static final Enumeration.Value LOW_SCORE_FOR_COMPACTION;

   static {
      SUCCESS = MODULE$.Value();
      NOT_ENOUGH_FILES = MODULE$.Value();
      LOW_SCORE_FOR_COMPACTION = MODULE$.Value();
   }

   public Enumeration.Value SUCCESS() {
      return SUCCESS;
   }

   public Enumeration.Value NOT_ENOUGH_FILES() {
      return NOT_ENOUGH_FILES;
   }

   public Enumeration.Value LOW_SCORE_FOR_COMPACTION() {
      return LOW_SCORE_FOR_COMPACTION;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CompactionResultCode$.class);
   }

   private CompactionResultCode$() {
   }
}
