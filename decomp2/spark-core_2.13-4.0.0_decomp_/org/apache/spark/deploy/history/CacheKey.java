package org.apache.spark.deploy.history;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dd!B\r\u001b\u0005j!\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011\u0015\u0003!\u0011#Q\u0001\nuB\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u0011\")A\n\u0001C\u0001\u001b\")!\u000b\u0001C!'\"9A\u000bAA\u0001\n\u0003)\u0006b\u0002-\u0001#\u0003%\t!\u0017\u0005\bI\u0002\t\n\u0011\"\u0001f\u0011\u001d9\u0007!!A\u0005B!Dq\u0001\u001d\u0001\u0002\u0002\u0013\u0005\u0011\u000fC\u0004v\u0001\u0005\u0005I\u0011\u0001<\t\u000fq\u0004\u0011\u0011!C!{\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00111\u0002\u0005\n\u0003+\u0001\u0011\u0011!C!\u0003/A\u0011\"a\u0007\u0001\u0003\u0003%\t%!\b\t\u0013\u0005}\u0001!!A\u0005B\u0005\u0005rACA\u00135\u0005\u0005\t\u0012\u0001\u000e\u0002(\u0019I\u0011DGA\u0001\u0012\u0003Q\u0012\u0011\u0006\u0005\u0007\u0019N!\t!!\u0011\t\u0011I\u001b\u0012\u0011!C#\u0003\u0007B\u0011\"!\u0012\u0014\u0003\u0003%\t)a\u0012\t\u0013\u000553#!A\u0005\u0002\u0006=\u0003\"CA/'\u0005\u0005I\u0011BA0\u0005!\u0019\u0015m\u00195f\u0017\u0016L(BA\u000e\u001d\u0003\u001dA\u0017n\u001d;pefT!!\b\u0010\u0002\r\u0011,\u0007\u000f\\8z\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7\u0003\u0002\u0001&W9\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0007C\u0001\u0014-\u0013\tisEA\u0004Qe>$Wo\u0019;\u0011\u0005=BdB\u0001\u00197\u001d\t\tT'D\u00013\u0015\t\u0019D'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005A\u0013BA\u001c(\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000f\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005]:\u0013!B1qa&#W#A\u001f\u0011\u0005y\u0012eBA A!\t\tt%\u0003\u0002BO\u00051\u0001K]3eK\u001aL!a\u0011#\u0003\rM#(/\u001b8h\u0015\t\tu%\u0001\u0004baBLE\rI\u0001\nCR$X-\u001c9u\u0013\u0012,\u0012\u0001\u0013\t\u0004M%k\u0014B\u0001&(\u0005\u0019y\u0005\u000f^5p]\u0006Q\u0011\r\u001e;f[B$\u0018\n\u001a\u0011\u0002\rqJg.\u001b;?)\rq\u0005+\u0015\t\u0003\u001f\u0002i\u0011A\u0007\u0005\u0006w\u0015\u0001\r!\u0010\u0005\u0006\r\u0016\u0001\r\u0001S\u0001\ti>\u001cFO]5oOR\tQ(\u0001\u0003d_BLHc\u0001(W/\"91h\u0002I\u0001\u0002\u0004i\u0004b\u0002$\b!\u0003\u0005\r\u0001S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005Q&FA\u001f\\W\u0005a\u0006CA/c\u001b\u0005q&BA0a\u0003%)hn\u00195fG.,GM\u0003\u0002bO\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\rt&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00014+\u0005![\u0016!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001j!\tQw.D\u0001l\u0015\taW.\u0001\u0003mC:<'\"\u00018\u0002\t)\fg/Y\u0005\u0003\u0007.\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A\u001d\t\u0003MML!\u0001^\u0014\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005]T\bC\u0001\u0014y\u0013\tIxEA\u0002B]fDqa\u001f\u0007\u0002\u0002\u0003\u0007!/A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002}B!q0!\u0002x\u001b\t\t\tAC\u0002\u0002\u0004\u001d\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9!!\u0001\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u001b\t\u0019\u0002E\u0002'\u0003\u001fI1!!\u0005(\u0005\u001d\u0011un\u001c7fC:Dqa\u001f\b\u0002\u0002\u0003\u0007q/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA5\u0002\u001a!91pDA\u0001\u0002\u0004\u0011\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003I\fa!Z9vC2\u001cH\u0003BA\u0007\u0003GAqa_\t\u0002\u0002\u0003\u0007q/\u0001\u0005DC\u000eDWmS3z!\ty5cE\u0003\u0014\u0003W\t9\u0004E\u0004\u0002.\u0005MR\b\u0013(\u000e\u0005\u0005=\"bAA\u0019O\u00059!/\u001e8uS6,\u0017\u0002BA\u001b\u0003_\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\tI$a\u0010\u000e\u0005\u0005m\"bAA\u001f[\u0006\u0011\u0011n\\\u0005\u0004s\u0005mBCAA\u0014)\u0005I\u0017!B1qa2LH#\u0002(\u0002J\u0005-\u0003\"B\u001e\u0017\u0001\u0004i\u0004\"\u0002$\u0017\u0001\u0004A\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003#\nI\u0006\u0005\u0003'\u0013\u0006M\u0003#\u0002\u0014\u0002VuB\u0015bAA,O\t1A+\u001e9mKJB\u0001\"a\u0017\u0018\u0003\u0003\u0005\rAT\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA1!\rQ\u00171M\u0005\u0004\u0003KZ'AB(cU\u0016\u001cG\u000f"
)
public final class CacheKey implements Product, Serializable {
   private final String appId;
   private final Option attemptId;

   public static Option unapply(final CacheKey x$0) {
      return CacheKey$.MODULE$.unapply(x$0);
   }

   public static CacheKey apply(final String appId, final Option attemptId) {
      return CacheKey$.MODULE$.apply(appId, attemptId);
   }

   public static Function1 tupled() {
      return CacheKey$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return CacheKey$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String appId() {
      return this.appId;
   }

   public Option attemptId() {
      return this.attemptId;
   }

   public String toString() {
      String var10000 = this.appId();
      return var10000 + this.attemptId().map((id) -> "/" + id).getOrElse(() -> "");
   }

   public CacheKey copy(final String appId, final Option attemptId) {
      return new CacheKey(appId, attemptId);
   }

   public String copy$default$1() {
      return this.appId();
   }

   public Option copy$default$2() {
      return this.attemptId();
   }

   public String productPrefix() {
      return "CacheKey";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.appId();
         }
         case 1 -> {
            return this.attemptId();
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
      return x$1 instanceof CacheKey;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "appId";
         }
         case 1 -> {
            return "attemptId";
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
         label52: {
            if (x$1 instanceof CacheKey) {
               label45: {
                  CacheKey var4 = (CacheKey)x$1;
                  String var10000 = this.appId();
                  String var5 = var4.appId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label45;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label45;
                  }

                  Option var7 = this.attemptId();
                  Option var6 = var4.attemptId();
                  if (var7 == null) {
                     if (var6 == null) {
                        break label52;
                     }
                  } else if (var7.equals(var6)) {
                     break label52;
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

   public CacheKey(final String appId, final Option attemptId) {
      this.appId = appId;
      this.attemptId = attemptId;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
