package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Statistics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005Q2\u0001BB\u0004\u0011\u0002\u0007\u0005aB\f\u0005\u0006'\u0001!\t\u0001\u0006\u0005\b1\u0001\u0011\rQ\"\u0001\u001a\u0011\u001dq\u0002A1A\u0005\u0002}Aq\u0001\u000b\u0001C\u0002\u0013\u0005\u0011\u0006C\u0004.\u0001\t\u0007I\u0011A\u0015\u0003\u0019MKXNY8mgN#\u0018\r^:\u000b\u0005!I\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005)Y\u0011a\u0002:fM2,7\r\u001e\u0006\u0002\u0019\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u0010!\t\u0001\u0012#D\u0001\f\u0013\t\u00112B\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003U\u0001\"\u0001\u0005\f\n\u0005]Y!\u0001B+oSR\f1b]=nE>dG+\u00192mKV\t!\u0004\u0005\u0002\u001c95\tq!\u0003\u0002\u001e\u000f\tY1+_7c_2$\u0016M\u00197f\u00031\u0019\u00180\u001c2pYN\u001cu.\u001e8u+\u0005\u0001\u0003CA\u0011#\u001b\u0005\u0001\u0011BA\u0012%\u0005\u00111\u0016.Z<\n\u0005\u00152#AC*uCRL7\u000f^5dg*\u0011qeB\u0001\u0005kRLG.A\busB,7+_7c_2\u001cu.\u001e8u+\u0005Q\u0003CA\u0011,\u0013\taCEA\u0004D_VtG/\u001a:\u0002!\rd\u0017m]:Ts6\u0014w\u000e\\\"pk:$(cA\u00182e\u0019!\u0001\u0007\u0001\u0001/\u00051a$/\u001a4j]\u0016lWM\u001c;?!\tY\u0002\u0001\u0005\u00024I5\ta\u0005"
)
public interface SymbolsStats {
   void scala$reflect$internal$SymbolsStats$_setter_$symbolsCount_$eq(final Statistics.View x$1);

   void scala$reflect$internal$SymbolsStats$_setter_$typeSymbolCount_$eq(final Statistics.Counter x$1);

   void scala$reflect$internal$SymbolsStats$_setter_$classSymbolCount_$eq(final Statistics.Counter x$1);

   SymbolTable symbolTable();

   Statistics.View symbolsCount();

   Statistics.Counter typeSymbolCount();

   Statistics.Counter classSymbolCount();

   static void $init$(final SymbolsStats $this) {
      $this.scala$reflect$internal$SymbolsStats$_setter_$symbolsCount_$eq(((Statistics)$this).newView("#symbols", .MODULE$, (JFunction0.mcI.sp)() -> ((Statistics)$this).symbolTable().getCurrentSymbolIdCount()));
      $this.scala$reflect$internal$SymbolsStats$_setter_$typeSymbolCount_$eq(((Statistics)$this).newCounter("#type symbols", .MODULE$));
      $this.scala$reflect$internal$SymbolsStats$_setter_$classSymbolCount_$eq(((Statistics)$this).newCounter("#class symbols", .MODULE$));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
