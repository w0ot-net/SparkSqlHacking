package org.apache.spark.mllib.tree.configuration;

import scala.Enumeration;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i:aa\u0003\u0007\t\u00029AbA\u0002\u000e\r\u0011\u0003q1\u0004C\u0003#\u0003\u0011\u0005A%\u0002\u0003\u001b\u0003\u0001)\u0003bB\u0015\u0002\u0005\u0004%\tA\u000b\u0005\u0007W\u0005\u0001\u000b\u0011B\u0013\t\u000f1\n!\u0019!C\u0001U!1Q&\u0001Q\u0001\n\u0015BqAL\u0001C\u0002\u0013\u0005!\u0006\u0003\u00040\u0003\u0001\u0006I!\n\u0005\ba\u0005\t\t\u0011\"\u00032\u0003e)en]3nE2,7i\\7cS:LgnZ*ue\u0006$XmZ=\u000b\u00055q\u0011!D2p]\u001aLw-\u001e:bi&|gN\u0003\u0002\u0010!\u0005!AO]3f\u0015\t\t\"#A\u0003nY2L'M\u0003\u0002\u0014)\u0005)1\u000f]1sW*\u0011QCF\u0001\u0007CB\f7\r[3\u000b\u0003]\t1a\u001c:h!\tI\u0012!D\u0001\r\u0005e)en]3nE2,7i\\7cS:LgnZ*ue\u0006$XmZ=\u0014\u0005\u0005a\u0002CA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"aC#ok6,'/\u0019;j_:\fa\u0001P5oSRt4\u0001\u0001\u000b\u00021A\u0011aeJ\u0007\u0002\u0003%\u0011\u0001\u0006\t\u0002\u0006-\u0006dW/Z\u0001\b\u0003Z,'/Y4f+\u0005)\u0013\u0001C!wKJ\fw-\u001a\u0011\u0002\u0007M+X.\u0001\u0003Tk6\u0004\u0013\u0001\u0002,pi\u0016\fQAV8uK\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012A\r\t\u0003gaj\u0011\u0001\u000e\u0006\u0003kY\nA\u0001\\1oO*\tq'\u0001\u0003kCZ\f\u0017BA\u001d5\u0005\u0019y%M[3di\u0002"
)
public final class EnsembleCombiningStrategy {
   public static Enumeration.Value Vote() {
      return EnsembleCombiningStrategy$.MODULE$.Vote();
   }

   public static Enumeration.Value Sum() {
      return EnsembleCombiningStrategy$.MODULE$.Sum();
   }

   public static Enumeration.Value Average() {
      return EnsembleCombiningStrategy$.MODULE$.Average();
   }

   public static Enumeration.ValueSet ValueSet() {
      return EnsembleCombiningStrategy$.MODULE$.ValueSet();
   }

   public static Enumeration.ValueOrdering ValueOrdering() {
      return EnsembleCombiningStrategy$.MODULE$.ValueOrdering();
   }

   public static Enumeration.Value withName(final String s) {
      return EnsembleCombiningStrategy$.MODULE$.withName(s);
   }

   public static Enumeration.Value apply(final int x) {
      return EnsembleCombiningStrategy$.MODULE$.apply(x);
   }

   public static int maxId() {
      return EnsembleCombiningStrategy$.MODULE$.maxId();
   }

   public static Enumeration.ValueSet values() {
      return EnsembleCombiningStrategy$.MODULE$.values();
   }

   public static String toString() {
      return EnsembleCombiningStrategy$.MODULE$.toString();
   }
}
