package org.apache.spark.storage;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dd\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\t\u0002\u0011\t\u0012)A\u0005y!AQ\t\u0001BK\u0002\u0013\u00051\b\u0003\u0005G\u0001\tE\t\u0015!\u0003=\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015a\u0005\u0001\"\u0011<\u0011\u001di\u0005!!A\u0005\u00029Cq!\u0015\u0001\u0012\u0002\u0013\u0005!\u000bC\u0004^\u0001E\u0005I\u0011\u0001*\t\u000fy\u0003\u0011\u0011!C!?\"9q\rAA\u0001\n\u0003A\u0007b\u00027\u0001\u0003\u0003%\t!\u001c\u0005\bg\u0002\t\t\u0011\"\u0011u\u0011\u001dY\b!!A\u0005\u0002qD\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u000f%\tyBGA\u0001\u0012\u0003\t\tC\u0002\u0005\u001a5\u0005\u0005\t\u0012AA\u0012\u0011\u001995\u0003\"\u0001\u0002<!I\u0011QH\n\u0002\u0002\u0013\u0015\u0013q\b\u0005\n\u0003\u0003\u001a\u0012\u0011!CA\u0003\u0007B\u0011\"!\u0013\u0014\u0003\u0003%\t)a\u0013\t\u0013\u0005u3#!A\u0005\n\u0005}#aB\"bG\",\u0017\n\u001a\u0006\u00037q\tqa\u001d;pe\u0006<WM\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0013)]A\u0011QEJ\u0007\u00025%\u0011qE\u0007\u0002\b\u00052|7m[%e!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u001d\u0001&o\u001c3vGR\u0004\"aL\u001c\u000f\u0005A*dBA\u00195\u001b\u0005\u0011$BA\u001a#\u0003\u0019a$o\\8u}%\t1&\u0003\u00027U\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001d:\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t1$&A\u0006tKN\u001c\u0018n\u001c8V+&#U#\u0001\u001f\u0011\u0005u\neB\u0001 @!\t\t$&\u0003\u0002AU\u00051\u0001K]3eK\u001aL!AQ\"\u0003\rM#(/\u001b8h\u0015\t\u0001%&\u0001\u0007tKN\u001c\u0018n\u001c8V+&#\u0005%\u0001\u0003iCND\u0017!\u00025bg\"\u0004\u0013A\u0002\u001fj]&$h\bF\u0002J\u0015.\u0003\"!\n\u0001\t\u000bi*\u0001\u0019\u0001\u001f\t\u000b\u0015+\u0001\u0019\u0001\u001f\u0002\t9\fW.Z\u0001\u0005G>\u0004\u0018\u0010F\u0002J\u001fBCqAO\u0004\u0011\u0002\u0003\u0007A\bC\u0004F\u000fA\u0005\t\u0019\u0001\u001f\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t1K\u000b\u0002=).\nQ\u000b\u0005\u0002W76\tqK\u0003\u0002Y3\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u00035*\n!\"\u00198o_R\fG/[8o\u0013\tavKA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002AB\u0011\u0011MZ\u0007\u0002E*\u00111\rZ\u0001\u0005Y\u0006twMC\u0001f\u0003\u0011Q\u0017M^1\n\u0005\t\u0013\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A5\u0011\u0005%R\u0017BA6+\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tq\u0017\u000f\u0005\u0002*_&\u0011\u0001O\u000b\u0002\u0004\u0003:L\bb\u0002:\r\u0003\u0003\u0005\r![\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003U\u00042A^=o\u001b\u00059(B\u0001=+\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003u^\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019Q0!\u0001\u0011\u0005%r\u0018BA@+\u0005\u001d\u0011un\u001c7fC:DqA\u001d\b\u0002\u0002\u0003\u0007a.\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00011\u0002\b!9!oDA\u0001\u0002\u0004I\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003%\fa!Z9vC2\u001cHcA?\u0002\u0012!9!/EA\u0001\u0002\u0004q\u0007f\u0001\u0001\u0002\u0016A!\u0011qCA\u000e\u001b\t\tIB\u0003\u0002[9%!\u0011QDA\r\u00051!UM^3m_B,'/\u00119j\u0003\u001d\u0019\u0015m\u00195f\u0013\u0012\u0004\"!J\n\u0014\u000bM\t)#!\r\u0011\u000f\u0005\u001d\u0012Q\u0006\u001f=\u00136\u0011\u0011\u0011\u0006\u0006\u0004\u0003WQ\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003_\tICA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\r\u0002:5\u0011\u0011Q\u0007\u0006\u0004\u0003o!\u0017AA5p\u0013\rA\u0014Q\u0007\u000b\u0003\u0003C\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002A\u0006)\u0011\r\u001d9msR)\u0011*!\u0012\u0002H!)!H\u0006a\u0001y!)QI\u0006a\u0001y\u00059QO\\1qa2LH\u0003BA'\u00033\u0002R!KA(\u0003'J1!!\u0015+\u0005\u0019y\u0005\u000f^5p]B)\u0011&!\u0016=y%\u0019\u0011q\u000b\u0016\u0003\rQ+\b\u000f\\33\u0011!\tYfFA\u0001\u0002\u0004I\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\r\t\u0004C\u0006\r\u0014bAA3E\n1qJ\u00196fGR\u0004"
)
public class CacheId extends BlockId implements Product, Serializable {
   private final String sessionUUID;
   private final String hash;

   public static Option unapply(final CacheId x$0) {
      return CacheId$.MODULE$.unapply(x$0);
   }

   public static CacheId apply(final String sessionUUID, final String hash) {
      return CacheId$.MODULE$.apply(sessionUUID, hash);
   }

   public static Function1 tupled() {
      return CacheId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return CacheId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String sessionUUID() {
      return this.sessionUUID;
   }

   public String hash() {
      return this.hash;
   }

   public String name() {
      String var10000 = this.sessionUUID();
      return "cache_" + var10000 + "_" + this.hash();
   }

   public CacheId copy(final String sessionUUID, final String hash) {
      return new CacheId(sessionUUID, hash);
   }

   public String copy$default$1() {
      return this.sessionUUID();
   }

   public String copy$default$2() {
      return this.hash();
   }

   public String productPrefix() {
      return "CacheId";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.sessionUUID();
         }
         case 1 -> {
            return this.hash();
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
      return x$1 instanceof CacheId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "sessionUUID";
         }
         case 1 -> {
            return "hash";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof CacheId) {
               label48: {
                  CacheId var4 = (CacheId)x$1;
                  String var10000 = this.sessionUUID();
                  String var5 = var4.sessionUUID();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  var10000 = this.hash();
                  String var6 = var4.hash();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public CacheId(final String sessionUUID, final String hash) {
      this.sessionUUID = sessionUUID;
      this.hash = hash;
      Product.$init$(this);
   }
}
