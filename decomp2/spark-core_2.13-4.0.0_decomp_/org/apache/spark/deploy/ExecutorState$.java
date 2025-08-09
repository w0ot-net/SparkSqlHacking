package org.apache.spark.deploy;

import scala.Enumeration;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorState$ extends Enumeration {
   public static final ExecutorState$ MODULE$ = new ExecutorState$();
   private static final Enumeration.Value LAUNCHING;
   private static final Enumeration.Value RUNNING;
   private static final Enumeration.Value KILLED;
   private static final Enumeration.Value FAILED;
   private static final Enumeration.Value LOST;
   private static final Enumeration.Value EXITED;
   private static final Enumeration.Value DECOMMISSIONED;
   private static final Seq finishedStates;

   static {
      LAUNCHING = MODULE$.Value();
      RUNNING = MODULE$.Value();
      KILLED = MODULE$.Value();
      FAILED = MODULE$.Value();
      LOST = MODULE$.Value();
      EXITED = MODULE$.Value();
      DECOMMISSIONED = MODULE$.Value();
      finishedStates = new .colon.colon(MODULE$.KILLED(), new .colon.colon(MODULE$.FAILED(), new .colon.colon(MODULE$.LOST(), new .colon.colon(MODULE$.EXITED(), scala.collection.immutable.Nil..MODULE$))));
   }

   public Enumeration.Value LAUNCHING() {
      return LAUNCHING;
   }

   public Enumeration.Value RUNNING() {
      return RUNNING;
   }

   public Enumeration.Value KILLED() {
      return KILLED;
   }

   public Enumeration.Value FAILED() {
      return FAILED;
   }

   public Enumeration.Value LOST() {
      return LOST;
   }

   public Enumeration.Value EXITED() {
      return EXITED;
   }

   public Enumeration.Value DECOMMISSIONED() {
      return DECOMMISSIONED;
   }

   private Seq finishedStates() {
      return finishedStates;
   }

   public boolean isFinished(final Enumeration.Value state) {
      return this.finishedStates().contains(state);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorState$.class);
   }

   private ExecutorState$() {
   }
}
