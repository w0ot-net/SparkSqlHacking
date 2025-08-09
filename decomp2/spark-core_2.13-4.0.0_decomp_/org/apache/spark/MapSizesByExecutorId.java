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
   bytes = "\u0006\u0005\u0005]d!B\r\u001b\u0001j\u0001\u0003\u0002C\u001c\u0001\u0005+\u0007I\u0011\u0001\u001d\t\u0011]\u0003!\u0011#Q\u0001\neB\u0001\u0002\u0017\u0001\u0003\u0016\u0004%\t!\u0017\u0005\t;\u0002\u0011\t\u0012)A\u00055\")a\f\u0001C\u0001?\"9A\rAA\u0001\n\u0003)\u0007b\u00025\u0001#\u0003%\t!\u001b\u0005\bi\u0002\t\n\u0011\"\u0001v\u0011\u001d9\b!!A\u0005BaD\u0011\"a\u0001\u0001\u0003\u0003%\t!!\u0002\t\u0013\u0005\u001d\u0001!!A\u0005\u0002\u0005%\u0001\"CA\u000b\u0001\u0005\u0005I\u0011IA\f\u0011%\ti\u0002AA\u0001\n\u0003\ty\u0002C\u0005\u0002$\u0001\t\t\u0011\"\u0011\u0002&!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131\u0006\u0005\n\u0003[\u0001\u0011\u0011!C!\u0003_A\u0011\"!\r\u0001\u0003\u0003%\t%a\r\b\u0015\u0005]\"$!A\t\u0002i\tIDB\u0005\u001a5\u0005\u0005\t\u0012\u0001\u000e\u0002<!1al\u0005C\u0001\u0003'B\u0011\"!\f\u0014\u0003\u0003%)%a\f\t\u0013\u0005U3#!A\u0005\u0002\u0006]\u0003\"CA/'\u0005\u0005I\u0011QA0\u0011%\tigEA\u0001\n\u0013\tyG\u0001\u000bNCB\u001c\u0016N_3t\u0005f,\u00050Z2vi>\u0014\u0018\n\u001a\u0006\u00037q\tQa\u001d9be.T!!\b\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0012aA8sON!\u0001!I\u0014+!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0019\te.\u001f*fMB\u0011!\u0005K\u0005\u0003S\r\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002,i9\u0011AF\r\b\u0003[Ej\u0011A\f\u0006\u0003_A\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002I%\u00111gI\u0001\ba\u0006\u001c7.Y4f\u0013\t)dG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00024G\u0005!\u0011\u000e^3s+\u0005I\u0004cA\u0016;y%\u00111H\u000e\u0002\t\u0013R,'/\u0019;peB!!%P F\u0013\tq4E\u0001\u0004UkBdWM\r\t\u0003\u0001\u000ek\u0011!\u0011\u0006\u0003\u0005j\tqa\u001d;pe\u0006<W-\u0003\u0002E\u0003\nq!\t\\8dW6\u000bg.Y4fe&#\u0007c\u0001$J\u00176\tqI\u0003\u0002IG\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005);%aA*fcB)!\u0005\u0014(R)&\u0011Qj\t\u0002\u0007)V\u0004H.Z\u001a\u0011\u0005\u0001{\u0015B\u0001)B\u0005\u001d\u0011En\\2l\u0013\u0012\u0004\"A\t*\n\u0005M\u001b#\u0001\u0002'p]\u001e\u0004\"AI+\n\u0005Y\u001b#aA%oi\u0006)\u0011\u000e^3sA\u0005\u0001RM\\1cY\u0016\u0014\u0015\r^2i\r\u0016$8\r[\u000b\u00025B\u0011!eW\u0005\u00039\u000e\u0012qAQ8pY\u0016\fg.A\tf]\u0006\u0014G.\u001a\"bi\u000eDg)\u001a;dQ\u0002\na\u0001P5oSRtDc\u00011cGB\u0011\u0011\rA\u0007\u00025!)q'\u0002a\u0001s!)\u0001,\u0002a\u00015\u0006!1m\u001c9z)\r\u0001gm\u001a\u0005\bo\u0019\u0001\n\u00111\u0001:\u0011\u001dAf\u0001%AA\u0002i\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001kU\tI4nK\u0001m!\ti'/D\u0001o\u0015\ty\u0007/A\u0005v]\u000eDWmY6fI*\u0011\u0011oI\u0001\u000bC:tw\u000e^1uS>t\u0017BA:o\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u00051(F\u0001.l\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\t\u0011\u0010\u0005\u0002{\u007f6\t1P\u0003\u0002}{\u0006!A.\u00198h\u0015\u0005q\u0018\u0001\u00026bm\u0006L1!!\u0001|\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tA+\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005-\u0011\u0011\u0003\t\u0004E\u00055\u0011bAA\bG\t\u0019\u0011I\\=\t\u0011\u0005M1\"!AA\u0002Q\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\r!\u00151\u00151DA\u0006\u0013\tYt)\u0001\u0005dC:,\u0015/^1m)\rQ\u0016\u0011\u0005\u0005\n\u0003'i\u0011\u0011!a\u0001\u0003\u0017\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019\u00110a\n\t\u0011\u0005Ma\"!AA\u0002Q\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002)\u0006AAo\\*ue&tw\rF\u0001z\u0003\u0019)\u0017/^1mgR\u0019!,!\u000e\t\u0013\u0005M\u0011#!AA\u0002\u0005-\u0011\u0001F'baNK'0Z:Cs\u0016CXmY;u_JLE\r\u0005\u0002b'M)1#!\u0010\u0002JA9\u0011qHA#si\u0003WBAA!\u0015\r\t\u0019eI\u0001\beVtG/[7f\u0013\u0011\t9%!\u0011\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0005\u0003\u0002L\u0005ESBAA'\u0015\r\ty%`\u0001\u0003S>L1!NA')\t\tI$A\u0003baBd\u0017\u0010F\u0003a\u00033\nY\u0006C\u00038-\u0001\u0007\u0011\bC\u0003Y-\u0001\u0007!,A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u0005\u0014\u0011\u000e\t\u0006E\u0005\r\u0014qM\u0005\u0004\u0003K\u001a#AB(qi&|g\u000e\u0005\u0003#{eR\u0006\u0002CA6/\u0005\u0005\t\u0019\u00011\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002rA\u0019!0a\u001d\n\u0007\u0005U4P\u0001\u0004PE*,7\r\u001e"
)
public class MapSizesByExecutorId implements Product, Serializable {
   private final Iterator iter;
   private final boolean enableBatchFetch;

   public static Option unapply(final MapSizesByExecutorId x$0) {
      return MapSizesByExecutorId$.MODULE$.unapply(x$0);
   }

   public static MapSizesByExecutorId apply(final Iterator iter, final boolean enableBatchFetch) {
      return MapSizesByExecutorId$.MODULE$.apply(iter, enableBatchFetch);
   }

   public static Function1 tupled() {
      return MapSizesByExecutorId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return MapSizesByExecutorId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Iterator iter() {
      return this.iter;
   }

   public boolean enableBatchFetch() {
      return this.enableBatchFetch;
   }

   public MapSizesByExecutorId copy(final Iterator iter, final boolean enableBatchFetch) {
      return new MapSizesByExecutorId(iter, enableBatchFetch);
   }

   public Iterator copy$default$1() {
      return this.iter();
   }

   public boolean copy$default$2() {
      return this.enableBatchFetch();
   }

   public String productPrefix() {
      return "MapSizesByExecutorId";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.iter();
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.enableBatchFetch());
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
      return x$1 instanceof MapSizesByExecutorId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "iter";
         }
         case 1 -> {
            return "enableBatchFetch";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.iter()));
      var1 = Statics.mix(var1, this.enableBatchFetch() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof MapSizesByExecutorId) {
               MapSizesByExecutorId var4 = (MapSizesByExecutorId)x$1;
               if (this.enableBatchFetch() == var4.enableBatchFetch()) {
                  label44: {
                     Iterator var10000 = this.iter();
                     Iterator var5 = var4.iter();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
                     }
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

   public MapSizesByExecutorId(final Iterator iter, final boolean enableBatchFetch) {
      this.iter = iter;
      this.enableBatchFetch = enableBatchFetch;
      Product.$init$(this);
   }
}
