package org.apache.spark.sql;

import java.util.concurrent.atomic.AtomicReference;

public final class SparkSessionCompanion$ {
   public static final SparkSessionCompanion$ MODULE$ = new SparkSessionCompanion$();
   private static final InheritableThreadLocal org$apache$spark$sql$SparkSessionCompanion$$activeThreadSession = new InheritableThreadLocal();
   private static final AtomicReference org$apache$spark$sql$SparkSessionCompanion$$defaultSession = new AtomicReference();

   public InheritableThreadLocal org$apache$spark$sql$SparkSessionCompanion$$activeThreadSession() {
      return org$apache$spark$sql$SparkSessionCompanion$$activeThreadSession;
   }

   public AtomicReference org$apache$spark$sql$SparkSessionCompanion$$defaultSession() {
      return org$apache$spark$sql$SparkSessionCompanion$$defaultSession;
   }

   private SparkSessionCompanion$() {
   }
}
