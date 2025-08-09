package org.apache.spark;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=b!\u0002\f\u0018\u0001^i\u0002\u0002\u0003\u001b\u0001\u0005+\u0007I\u0011A\u001b\t\u0011e\u0002!\u0011#Q\u0001\nYBQA\u000f\u0001\u0005\u0002mBqa\u0010\u0001\u0002\u0002\u0013\u0005\u0001\tC\u0004C\u0001E\u0005I\u0011A\"\t\u000f9\u0003\u0011\u0011!C!\u001f\"9\u0001\fAA\u0001\n\u0003I\u0006bB/\u0001\u0003\u0003%\tA\u0018\u0005\bI\u0002\t\t\u0011\"\u0011f\u0011\u001da\u0007!!A\u0005\u00025Dqa\u001c\u0001\u0002\u0002\u0013\u0005\u0003\u000fC\u0004s\u0001\u0005\u0005I\u0011I:\t\u000fQ\u0004\u0011\u0011!C!k\"9a\u000fAA\u0001\n\u0003:x\u0001C=\u0018\u0003\u0003E\ta\u0006>\u0007\u0011Y9\u0012\u0011!E\u0001/mDaA\u000f\t\u0005\u0002\u0005=\u0001b\u0002;\u0011\u0003\u0003%)%\u001e\u0005\n\u0003#\u0001\u0012\u0011!CA\u0003'A\u0011\"a\u0006\u0011\u0003\u0003%\t)!\u0007\t\u0013\u0005\u0015\u0002#!A\u0005\n\u0005\u001d\"!\u0005%fCJ$(-Z1u%\u0016\u001c\bo\u001c8tK*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014xm\u0005\u0003\u0001=\u0011:\u0003CA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#AB!osJ+g\r\u0005\u0002 K%\u0011a\u0005\t\u0002\b!J|G-^2u!\tA\u0013G\u0004\u0002*_9\u0011!FL\u0007\u0002W)\u0011A&L\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0011%\u0003\u00021A\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001a4\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0001\u0004%\u0001\fsKJ,w-[:uKJ\u0014En\\2l\u001b\u0006t\u0017mZ3s+\u00051\u0004CA\u00108\u0013\tA\u0004EA\u0004C_>dW-\u00198\u0002/I,'/Z4jgR,'O\u00117pG.l\u0015M\\1hKJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002=}A\u0011Q\bA\u0007\u0002/!)Ag\u0001a\u0001m\u0005!1m\u001c9z)\ta\u0014\tC\u00045\tA\u0005\t\u0019\u0001\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tAI\u000b\u00027\u000b.\na\t\u0005\u0002H\u00196\t\u0001J\u0003\u0002J\u0015\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003\u0017\u0002\n!\"\u00198o_R\fG/[8o\u0013\ti\u0005JA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001)\u0011\u0005E3V\"\u0001*\u000b\u0005M#\u0016\u0001\u00027b]\u001eT\u0011!V\u0001\u0005U\u00064\u0018-\u0003\u0002X%\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A\u0017\t\u0003?mK!\u0001\u0018\u0011\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005}\u0013\u0007CA\u0010a\u0013\t\t\u0007EA\u0002B]fDqa\u0019\u0005\u0002\u0002\u0003\u0007!,A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002MB\u0019qM[0\u000e\u0003!T!!\u001b\u0011\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002lQ\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t1d\u000eC\u0004d\u0015\u0005\u0005\t\u0019A0\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003!FDqaY\u0006\u0002\u0002\u0003\u0007!,\u0001\u0005iCND7i\u001c3f)\u0005Q\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003A\u000ba!Z9vC2\u001cHC\u0001\u001cy\u0011\u001d\u0019g\"!AA\u0002}\u000b\u0011\u0003S3beR\u0014W-\u0019;SKN\u0004xN\\:f!\ti\u0004c\u0005\u0003\u0011y\u0006\u0015\u0001#B?\u0002\u0002YbT\"\u0001@\u000b\u0005}\u0004\u0013a\u0002:v]RLW.Z\u0005\u0004\u0003\u0007q(!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u0011qAA\u0007\u001b\t\tIAC\u0002\u0002\fQ\u000b!![8\n\u0007I\nI\u0001F\u0001{\u0003\u0015\t\u0007\u000f\u001d7z)\ra\u0014Q\u0003\u0005\u0006iM\u0001\rAN\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tY\"!\t\u0011\t}\tiBN\u0005\u0004\u0003?\u0001#AB(qi&|g\u000e\u0003\u0005\u0002$Q\t\t\u00111\u0001=\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003S\u00012!UA\u0016\u0013\r\tiC\u0015\u0002\u0007\u001f\nTWm\u0019;"
)
public class HeartbeatResponse implements Product, Serializable {
   private final boolean reregisterBlockManager;

   public static Option unapply(final HeartbeatResponse x$0) {
      return HeartbeatResponse$.MODULE$.unapply(x$0);
   }

   public static HeartbeatResponse apply(final boolean reregisterBlockManager) {
      return HeartbeatResponse$.MODULE$.apply(reregisterBlockManager);
   }

   public static Function1 andThen(final Function1 g) {
      return HeartbeatResponse$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return HeartbeatResponse$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean reregisterBlockManager() {
      return this.reregisterBlockManager;
   }

   public HeartbeatResponse copy(final boolean reregisterBlockManager) {
      return new HeartbeatResponse(reregisterBlockManager);
   }

   public boolean copy$default$1() {
      return this.reregisterBlockManager();
   }

   public String productPrefix() {
      return "HeartbeatResponse";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToBoolean(this.reregisterBlockManager());
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
      return x$1 instanceof HeartbeatResponse;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "reregisterBlockManager";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.reregisterBlockManager() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof HeartbeatResponse) {
               HeartbeatResponse var4 = (HeartbeatResponse)x$1;
               if (this.reregisterBlockManager() == var4.reregisterBlockManager() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public HeartbeatResponse(final boolean reregisterBlockManager) {
      this.reregisterBlockManager = reregisterBlockManager;
      Product.$init$(this);
   }
}
