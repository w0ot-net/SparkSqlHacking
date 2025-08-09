package org.apache.spark;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i;a!\u0006\f\t\u0002YabA\u0002\u0010\u0017\u0011\u00031r\u0004C\u0003'\u0003\u0011\u0005\u0001\u0006C\u0004*\u0003\t\u0007I\u0011\u0001\u0016\t\r=\n\u0001\u0015!\u0003,\u0011\u001d\u0001\u0014A1A\u0005\u0002)Ba!M\u0001!\u0002\u0013Y\u0003b\u0002\u001a\u0002\u0005\u0004%\tA\u000b\u0005\u0007g\u0005\u0001\u000b\u0011B\u0016\t\u000fQ\n!\u0019!C\u0001U!1Q'\u0001Q\u0001\n-BqAN\u0001C\u0002\u0013\u0005!\u0006\u0003\u00048\u0003\u0001\u0006Ia\u000b\u0005\bq\u0005\u0011\r\u0011\"\u0001+\u0011\u0019I\u0014\u0001)A\u0005W!9!(\u0001b\u0001\n\u0013Y\u0004B\u0002#\u0002A\u0003%A(\u0002\u0003\u001f\u0003\u0001Y\u0003\"B#\u0002\t\u00031\u0005\"B'\u0002\t\u0003q\u0005b\u0002)\u0002\u0003\u0003%I!U\u0001\n)\u0006\u001c8n\u0015;bi\u0016T!a\u0006\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005eQ\u0012AB1qC\u000eDWMC\u0001\u001c\u0003\ry'o\u001a\t\u0003;\u0005i\u0011A\u0006\u0002\n)\u0006\u001c8n\u0015;bi\u0016\u001c\"!\u0001\u0011\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0003\r\nQa]2bY\u0006L!!\n\u0012\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tA$A\u0005M\u0003Vs5\tS%O\u000fV\t1\u0006\u0005\u0002-[5\t\u0011!\u0003\u0002/I\t)a+\u00197vK\u0006QA*Q+O\u0007\"Kej\u0012\u0011\u0002\u000fI+fJT%O\u000f\u0006A!+\u0016(O\u0013:;\u0005%\u0001\u0005G\u0013:K5\u000bS#E\u0003%1\u0015JT%T\u0011\u0016#\u0005%\u0001\u0004G\u0003&cU\tR\u0001\b\r\u0006KE*\u0012#!\u0003\u0019Y\u0015\n\u0014'F\t\u000691*\u0013'M\u000b\u0012\u0003\u0013\u0001\u0002'P'R\u000bQ\u0001T(T)\u0002\nqBR%O\u0013NCU\tR0T)\u0006#ViU\u000b\u0002yA\u0019QHQ\u0016\u000e\u0003yR!a\u0010!\u0002\u0013%lW.\u001e;bE2,'BA!#\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003\u0007z\u00121aU3u\u0003A1\u0015JT%T\u0011\u0016#ul\u0015+B)\u0016\u001b\u0006%\u0001\u0005jg\u001a\u000b\u0017\u000e\\3e)\t9%\n\u0005\u0002\"\u0011&\u0011\u0011J\t\u0002\b\u0005>|G.Z1o\u0011\u0015Y%\u00031\u0001M\u0003\u0015\u0019H/\u0019;f!\ta\u0013#\u0001\u0006jg\u001aKg.[:iK\u0012$\"aR(\t\u000b-\u001b\u0002\u0019\u0001'\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003I\u0003\"a\u0015-\u000e\u0003QS!!\u0016,\u0002\t1\fgn\u001a\u0006\u0002/\u0006!!.\u0019<b\u0013\tIFK\u0001\u0004PE*,7\r\u001e"
)
public final class TaskState {
   public static boolean isFinished(final Enumeration.Value state) {
      return TaskState$.MODULE$.isFinished(state);
   }

   public static boolean isFailed(final Enumeration.Value state) {
      return TaskState$.MODULE$.isFailed(state);
   }

   public static Enumeration.Value LOST() {
      return TaskState$.MODULE$.LOST();
   }

   public static Enumeration.Value KILLED() {
      return TaskState$.MODULE$.KILLED();
   }

   public static Enumeration.Value FAILED() {
      return TaskState$.MODULE$.FAILED();
   }

   public static Enumeration.Value FINISHED() {
      return TaskState$.MODULE$.FINISHED();
   }

   public static Enumeration.Value RUNNING() {
      return TaskState$.MODULE$.RUNNING();
   }

   public static Enumeration.Value LAUNCHING() {
      return TaskState$.MODULE$.LAUNCHING();
   }

   public static Enumeration.ValueSet ValueSet() {
      return TaskState$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return TaskState$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return TaskState$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return TaskState$.MODULE$.apply(x);
   }

   public static int maxId() {
      return TaskState$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return TaskState$.MODULE$.values();
   }

   public static String toString() {
      return TaskState$.MODULE$.toString();
   }
}
