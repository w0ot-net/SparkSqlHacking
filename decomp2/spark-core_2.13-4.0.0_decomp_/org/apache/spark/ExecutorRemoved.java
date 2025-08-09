package org.apache.spark;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001\u0002\f\u0018\tzA\u0001\u0002\u000e\u0001\u0003\u0016\u0004%\t!\u000e\u0005\t}\u0001\u0011\t\u0012)A\u0005m!)q\b\u0001C\u0001\u0001\"9A\tAA\u0001\n\u0003)\u0005bB$\u0001#\u0003%\t\u0001\u0013\u0005\b'\u0002\t\t\u0011\"\u0011U\u0011\u001da\u0006!!A\u0005\u0002uCq!\u0019\u0001\u0002\u0002\u0013\u0005!\rC\u0004i\u0001\u0005\u0005I\u0011I5\t\u000fA\u0004\u0011\u0011!C\u0001c\"9a\u000fAA\u0001\n\u0003:\bbB=\u0001\u0003\u0003%\tE\u001f\u0005\bw\u0002\t\t\u0011\"\u0011}\u0011\u001di\b!!A\u0005By<\u0011\"!\u0001\u0018\u0003\u0003EI!a\u0001\u0007\u0011Y9\u0012\u0011!E\u0005\u0003\u000bAaa\u0010\t\u0005\u0002\u0005u\u0001bB>\u0011\u0003\u0003%)\u0005 \u0005\n\u0003?\u0001\u0012\u0011!CA\u0003CA\u0011\"!\n\u0011\u0003\u0003%\t)a\n\t\u0013\u0005M\u0002#!A\u0005\n\u0005U\"aD#yK\u000e,Ho\u001c:SK6|g/\u001a3\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001?\u0015B\u0003C\u0001\u0011$\u001b\u0005\t#\"\u0001\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0011\n#AB!osJ+g\r\u0005\u0002!M%\u0011q%\t\u0002\b!J|G-^2u!\tI\u0013G\u0004\u0002+_9\u00111FL\u0007\u0002Y)\u0011Q&H\u0001\u0007yI|w\u000e\u001e \n\u0003\tJ!\u0001M\u0011\u0002\u000fA\f7m[1hK&\u0011!g\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003a\u0005\n!\"\u001a=fGV$xN]%e+\u00051\u0004CA\u001c<\u001d\tA\u0014\b\u0005\u0002,C%\u0011!(I\u0001\u0007!J,G-\u001a4\n\u0005qj$AB*ue&twM\u0003\u0002;C\u0005YQ\r_3dkR|'/\u00133!\u0003\u0019a\u0014N\\5u}Q\u0011\u0011i\u0011\t\u0003\u0005\u0002i\u0011a\u0006\u0005\u0006i\r\u0001\rAN\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002B\r\"9A\u0007\u0002I\u0001\u0002\u00041\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0013*\u0012aGS\u0016\u0002\u0017B\u0011A*U\u0007\u0002\u001b*\u0011ajT\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001U\u0011\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002S\u001b\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005)\u0006C\u0001,\\\u001b\u00059&B\u0001-Z\u0003\u0011a\u0017M\\4\u000b\u0003i\u000bAA[1wC&\u0011AhV\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002=B\u0011\u0001eX\u0005\u0003A\u0006\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"a\u00194\u0011\u0005\u0001\"\u0017BA3\"\u0005\r\te.\u001f\u0005\bO\"\t\t\u00111\u0001_\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t!\u000eE\u0002l]\u000el\u0011\u0001\u001c\u0006\u0003[\u0006\n!bY8mY\u0016\u001cG/[8o\u0013\tyGN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u0001:v!\t\u00013/\u0003\u0002uC\t9!i\\8mK\u0006t\u0007bB4\u000b\u0003\u0003\u0005\raY\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Vq\"9qmCA\u0001\u0002\u0004q\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003y\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002+\u00061Q-];bYN$\"A]@\t\u000f\u001dt\u0011\u0011!a\u0001G\u0006yQ\t_3dkR|'OU3n_Z,G\r\u0005\u0002C!M)\u0001#a\u0002\u0002\u0014A1\u0011\u0011BA\bm\u0005k!!a\u0003\u000b\u0007\u00055\u0011%A\u0004sk:$\u0018.\\3\n\t\u0005E\u00111\u0002\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u000b\u00037i!!a\u0006\u000b\u0007\u0005e\u0011,\u0001\u0002j_&\u0019!'a\u0006\u0015\u0005\u0005\r\u0011!B1qa2LHcA!\u0002$!)Ag\u0005a\u0001m\u00059QO\\1qa2LH\u0003BA\u0015\u0003_\u0001B\u0001IA\u0016m%\u0019\u0011QF\u0011\u0003\r=\u0003H/[8o\u0011!\t\t\u0004FA\u0001\u0002\u0004\t\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u0007\t\u0004-\u0006e\u0012bAA\u001e/\n1qJ\u00196fGR\u0004"
)
public class ExecutorRemoved implements Product, Serializable {
   private final String executorId;

   public static Option unapply(final ExecutorRemoved x$0) {
      return ExecutorRemoved$.MODULE$.unapply(x$0);
   }

   public static ExecutorRemoved apply(final String executorId) {
      return ExecutorRemoved$.MODULE$.apply(executorId);
   }

   public static Function1 andThen(final Function1 g) {
      return ExecutorRemoved$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ExecutorRemoved$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String executorId() {
      return this.executorId;
   }

   public ExecutorRemoved copy(final String executorId) {
      return new ExecutorRemoved(executorId);
   }

   public String copy$default$1() {
      return this.executorId();
   }

   public String productPrefix() {
      return "ExecutorRemoved";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.executorId();
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
      return x$1 instanceof ExecutorRemoved;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "executorId";
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
            if (x$1 instanceof ExecutorRemoved) {
               label40: {
                  ExecutorRemoved var4 = (ExecutorRemoved)x$1;
                  String var10000 = this.executorId();
                  String var5 = var4.executorId();
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

   public ExecutorRemoved(final String executorId) {
      this.executorId = executorId;
      Product.$init$(this);
   }
}
