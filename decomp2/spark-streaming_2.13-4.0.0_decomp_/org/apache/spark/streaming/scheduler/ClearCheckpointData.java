package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c!\u0002\f\u0018\u0001^\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\t\u0003!\u0011#Q\u0001\nyBQa\u0011\u0001\u0005\u0002\u0011Cqa\u0012\u0001\u0002\u0002\u0013\u0005\u0001\nC\u0004K\u0001E\u0005I\u0011A&\t\u000fY\u0003\u0011\u0011!C!/\"9\u0001\rAA\u0001\n\u0003\t\u0007bB3\u0001\u0003\u0003%\tA\u001a\u0005\bY\u0002\t\t\u0011\"\u0011n\u0011\u001d!\b!!A\u0005\u0002UDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004~\u0001\u0005\u0005I\u0011\t@\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\b\u0015\u0005%q#!A\t\u0002]\tYAB\u0005\u0017/\u0005\u0005\t\u0012A\f\u0002\u000e!11\t\u0005C\u0001\u0003KA\u0001b \t\u0002\u0002\u0013\u0015\u0013\u0011\u0001\u0005\n\u0003O\u0001\u0012\u0011!CA\u0003SA\u0011\"!\f\u0011\u0003\u0003%\t)a\f\t\u0013\u0005m\u0002#!A\u0005\n\u0005u\"aE\"mK\u0006\u00148\t[3dWB|\u0017N\u001c;ECR\f'B\u0001\r\u001a\u0003%\u00198\r[3ek2,'O\u0003\u0002\u001b7\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sON)\u0001A\t\u0015-_A\u00111EJ\u0007\u0002I)\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\t1\u0011I\\=SK\u001a\u0004\"!\u000b\u0016\u000e\u0003]I!aK\f\u0003#){'mR3oKJ\fGo\u001c:Fm\u0016tG\u000f\u0005\u0002$[%\u0011a\u0006\n\u0002\b!J|G-^2u!\t\u0001\u0014H\u0004\u00022o9\u0011!GN\u0007\u0002g)\u0011A'N\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tQ%\u0003\u00029I\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001e<\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tAD%\u0001\u0003uS6,W#\u0001 \u0011\u0005}\u0002U\"A\r\n\u0005\u0005K\"\u0001\u0002+j[\u0016\fQ\u0001^5nK\u0002\na\u0001P5oSRtDCA#G!\tI\u0003\u0001C\u0003=\u0007\u0001\u0007a(\u0001\u0003d_BLHCA#J\u0011\u001daD\u0001%AA\u0002y\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001MU\tqTjK\u0001O!\tyE+D\u0001Q\u0015\t\t&+A\u0005v]\u000eDWmY6fI*\u00111\u000bJ\u0001\u000bC:tw\u000e^1uS>t\u0017BA+Q\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003a\u0003\"!\u00170\u000e\u0003iS!a\u0017/\u0002\t1\fgn\u001a\u0006\u0002;\u0006!!.\u0019<b\u0013\ty&L\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002EB\u00111eY\u0005\u0003I\u0012\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u001a6\u0011\u0005\rB\u0017BA5%\u0005\r\te.\u001f\u0005\bW\"\t\t\u00111\u0001c\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\ta\u000eE\u0002pe\u001el\u0011\u0001\u001d\u0006\u0003c\u0012\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0019\bO\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u0001<z!\t\u0019s/\u0003\u0002yI\t9!i\\8mK\u0006t\u0007bB6\u000b\u0003\u0003\u0005\raZ\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Yy\"91nCA\u0001\u0002\u0004\u0011\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\t\f\u0001\u0002^8TiJLgn\u001a\u000b\u00021\u00061Q-];bYN$2A^A\u0004\u0011\u001dYg\"!AA\u0002\u001d\f1c\u00117fCJ\u001c\u0005.Z2la>Lg\u000e\u001e#bi\u0006\u0004\"!\u000b\t\u0014\u000bA\ty!a\u0007\u0011\r\u0005E\u0011q\u0003 F\u001b\t\t\u0019BC\u0002\u0002\u0016\u0011\nqA];oi&lW-\u0003\u0003\u0002\u001a\u0005M!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u0011QDA\u0012\u001b\t\tyBC\u0002\u0002\"q\u000b!![8\n\u0007i\ny\u0002\u0006\u0002\u0002\f\u0005)\u0011\r\u001d9msR\u0019Q)a\u000b\t\u000bq\u001a\u0002\u0019\u0001 \u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011GA\u001c!\u0011\u0019\u00131\u0007 \n\u0007\u0005UBE\u0001\u0004PaRLwN\u001c\u0005\t\u0003s!\u0012\u0011!a\u0001\u000b\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0002cA-\u0002B%\u0019\u00111\t.\u0003\r=\u0013'.Z2u\u0001"
)
public class ClearCheckpointData implements JobGeneratorEvent, Product, Serializable {
   private final Time time;

   public static Option unapply(final ClearCheckpointData x$0) {
      return ClearCheckpointData$.MODULE$.unapply(x$0);
   }

   public static ClearCheckpointData apply(final Time time) {
      return ClearCheckpointData$.MODULE$.apply(time);
   }

   public static Function1 andThen(final Function1 g) {
      return ClearCheckpointData$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ClearCheckpointData$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time time() {
      return this.time;
   }

   public ClearCheckpointData copy(final Time time) {
      return new ClearCheckpointData(time);
   }

   public Time copy$default$1() {
      return this.time();
   }

   public String productPrefix() {
      return "ClearCheckpointData";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.time();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ClearCheckpointData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof ClearCheckpointData) {
               label40: {
                  ClearCheckpointData var4 = (ClearCheckpointData)x$1;
                  Time var10000 = this.time();
                  Time var5 = var4.time();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public ClearCheckpointData(final Time time) {
      this.time = time;
      Product.$init$(this);
   }
}
