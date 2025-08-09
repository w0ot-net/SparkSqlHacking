package org.apache.spark.deploy.master;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q:a!\u0004\b\t\u00029AbA\u0002\u000e\u000f\u0011\u0003q1\u0004C\u0003#\u0003\u0011\u0005A%\u0002\u0003\u001b\u0003\u0001)\u0003bB\u0015\u0002\u0005\u0004%\tA\u000b\u0005\u0007W\u0005\u0001\u000b\u0011B\u0013\t\u000f1\n!\u0019!C\u0001U!1Q&\u0001Q\u0001\n\u0015BqAL\u0001C\u0002\u0013\u0005!\u0006\u0003\u00040\u0003\u0001\u0006I!\n\u0005\ba\u0005\u0011\r\u0011\"\u0001+\u0011\u0019\t\u0014\u0001)A\u0005K!9!'AA\u0001\n\u0013\u0019\u0014aC,pe.,'o\u0015;bi\u0016T!a\u0004\t\u0002\r5\f7\u000f^3s\u0015\t\t\"#\u0001\u0004eKBdw.\u001f\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sOB\u0011\u0011$A\u0007\u0002\u001d\tYqk\u001c:lKJ\u001cF/\u0019;f'\t\tA\u0004\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcDA\u0006F]VlWM]1uS>t\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003a\u0001\"AJ\u0014\u000e\u0003\u0005I!\u0001\u000b\u0011\u0003\u000bY\u000bG.^3\u0002\u000b\u0005c\u0015JV#\u0016\u0003\u0015\na!\u0011'J-\u0016\u0003\u0013\u0001\u0002#F\u0003\u0012\u000bQ\u0001R#B\t\u0002\na\u0002R#D\u001f6k\u0015jU*J\u001f:+E)A\bE\u000b\u000e{U*T%T'&{e*\u0012#!\u0003\u001d)fj\u0013(P/:\u000b\u0001\"\u0016(L\u001d>;f\nI\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002iA\u0011QGO\u0007\u0002m)\u0011q\u0007O\u0001\u0005Y\u0006twMC\u0001:\u0003\u0011Q\u0017M^1\n\u0005m2$AB(cU\u0016\u001cG\u000f"
)
public final class WorkerState {
   public static Enumeration.Value UNKNOWN() {
      return WorkerState$.MODULE$.UNKNOWN();
   }

   public static Enumeration.Value DECOMMISSIONED() {
      return WorkerState$.MODULE$.DECOMMISSIONED();
   }

   public static Enumeration.Value DEAD() {
      return WorkerState$.MODULE$.DEAD();
   }

   public static Enumeration.Value ALIVE() {
      return WorkerState$.MODULE$.ALIVE();
   }

   public static Enumeration.ValueSet ValueSet() {
      return WorkerState$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return WorkerState$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return WorkerState$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return WorkerState$.MODULE$.apply(x);
   }

   public static int maxId() {
      return WorkerState$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return WorkerState$.MODULE$.values();
   }

   public static String toString() {
      return WorkerState$.MODULE$.toString();
   }
}
