package spire.syntax;

import algebra.lattice.Bool;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!D\u0001\u0006C_>d7+\u001f8uCbT!!\u0002\u0004\u0002\rMLh\u000e^1y\u0015\u00059\u0011!B:qSJ,7\u0001A\n\u0004\u0001)\u0001\u0002CA\u0006\u000f\u001b\u0005a!\"A\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u0005=a!AB!osJ+g\r\u0005\u0002\u0012%5\tA!\u0003\u0002\u0014\t\ti\u0001*Z=uS:<7+\u001f8uCb\fa\u0001J5oSR$C#\u0001\f\u0011\u0005-9\u0012B\u0001\r\r\u0005\u0011)f.\u001b;\u0002\u000f\t|w\u000e\\(qgV\u00111D\t\u000b\u00039u\"\"!H\u0016\u0011\u0007Eq\u0002%\u0003\u0002 \t\t9!i\\8m\u001fB\u001c\bCA\u0011#\u0019\u0001!Qa\t\u0002C\u0002\u0011\u0012\u0011!Q\t\u0003K!\u0002\"a\u0003\u0014\n\u0005\u001db!a\u0002(pi\"Lgn\u001a\t\u0003\u0017%J!A\u000b\u0007\u0003\u0007\u0005s\u0017\u0010C\u0004-\u0005\u0005\u0005\t9A\u0017\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#\u0007\u000e\t\u0004]i\u0002cBA\u00188\u001d\t\u0001TG\u0004\u00022i5\t!G\u0003\u00024\u0011\u00051AH]8pizJ\u0011aB\u0005\u0003m\u0019\tq!\u00197hK\n\u0014\u0018-\u0003\u00029s\u00059\u0001/Y2lC\u001e,'B\u0001\u001c\u0007\u0013\tYDH\u0001\u0003C_>d'B\u0001\u001d:\u0011\u0015q$\u00011\u0001!\u0003\u0005\t\u0007"
)
public interface BoolSyntax extends HeytingSyntax {
   // $FF: synthetic method
   static BoolOps boolOps$(final BoolSyntax $this, final Object a, final Bool evidence$24) {
      return $this.boolOps(a, evidence$24);
   }

   default BoolOps boolOps(final Object a, final Bool evidence$24) {
      return new BoolOps(a, evidence$24);
   }

   static void $init$(final BoolSyntax $this) {
   }
}
