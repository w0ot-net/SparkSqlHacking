package org.apache.spark;

import scala.Enumeration;
import scala.Predef.;
import scala.collection.immutable.Set;
import scala.runtime.ModuleSerializationProxy;

public final class TaskState$ extends Enumeration {
   public static final TaskState$ MODULE$ = new TaskState$();
   private static final Enumeration.Value LAUNCHING;
   private static final Enumeration.Value RUNNING;
   private static final Enumeration.Value FINISHED;
   private static final Enumeration.Value FAILED;
   private static final Enumeration.Value KILLED;
   private static final Enumeration.Value LOST;
   private static final Set FINISHED_STATES;

   static {
      LAUNCHING = MODULE$.Value();
      RUNNING = MODULE$.Value();
      FINISHED = MODULE$.Value();
      FAILED = MODULE$.Value();
      KILLED = MODULE$.Value();
      LOST = MODULE$.Value();
      FINISHED_STATES = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Enumeration.Value[]{MODULE$.FINISHED(), MODULE$.FAILED(), MODULE$.KILLED(), MODULE$.LOST()})));
   }

   public Enumeration.Value LAUNCHING() {
      return LAUNCHING;
   }

   public Enumeration.Value RUNNING() {
      return RUNNING;
   }

   public Enumeration.Value FINISHED() {
      return FINISHED;
   }

   public Enumeration.Value FAILED() {
      return FAILED;
   }

   public Enumeration.Value KILLED() {
      return KILLED;
   }

   public Enumeration.Value LOST() {
      return LOST;
   }

   private Set FINISHED_STATES() {
      return FINISHED_STATES;
   }

   public boolean isFailed(final Enumeration.Value state) {
      boolean var5;
      label32: {
         Enumeration.Value var10000 = this.LOST();
         if (var10000 == null) {
            if (state == null) {
               break label32;
            }
         } else if (var10000.equals(state)) {
            break label32;
         }

         var10000 = this.FAILED();
         if (var10000 == null) {
            if (state == null) {
               break label32;
            }
         } else if (var10000.equals(state)) {
            break label32;
         }

         var5 = false;
         return var5;
      }

      var5 = true;
      return var5;
   }

   public boolean isFinished(final Enumeration.Value state) {
      return this.FINISHED_STATES().contains(state);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TaskState$.class);
   }

   private TaskState$() {
   }
}
