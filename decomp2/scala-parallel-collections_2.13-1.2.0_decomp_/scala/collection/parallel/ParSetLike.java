package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import scala.Equals;
import scala.Function1;
import scala.collection.Set;
import scala.reflect.ScalaSignature;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005caB\f\u0019!\u0003\r\ta\b\u0005\u0006=\u0002!\ta\u0018\u0005\u0006G\u00021\t\u0001\u001a\u0005\u0006O\u0002!)\u0001\u001b\u0005\u0006U\u00021\ta\u001b\u0005\u0006[\u00021\tA\u001c\u0005\u0006a\u0002!\t!\u001d\u0005\u0006a\u0002!\t\u0001\u001e\u0005\u0006m\u0002!\ta\u001e\u0005\u0006m\u0002!\t!\u001f\u0005\u0006w\u0002!\t\u0001 \u0005\u0006w\u0002!\tA \u0005\b\u0003\u0003\u0001A\u0011AA\u0002\u0011\u001d\t\t\u0001\u0001C\u0001\u0003\u000fAq!a\u0003\u0001\t\u0003\ti\u0001C\u0004\u0002\u0012\u0001!\t%a\u0005\t\u000f\u0005]\u0001\u0001\"\u0011\u0002\u001a!9\u0011\u0011\u0005\u0001\u0005\u0002\u0005\r\u0002bBA\u0015\u0001\u0019\u0005\u00111\u0006\u0005\b\u0003[\u0001A\u0011AA\u0018\u0011\u001d\ti\u0003\u0001C\u0001\u0003gAq!a\u000e\u0001\t\u0003\tI\u0004C\u0004\u00028\u0001!\t!!\u0010\u0003\u0015A\u000b'oU3u\u0019&\\WM\u0003\u0002\u001a5\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\u001c9\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003u\tQa]2bY\u0006\u001c\u0001!F\u0003!WU\u0002uiE\u0003\u0001C\u0015*6\f\u0005\u0002#G5\tA$\u0003\u0002%9\t1\u0011I\\=SK\u001a\u0004bAJ\u0014*i}2U\"\u0001\r\n\u0005!B\"a\u0004)be&#XM]1cY\u0016d\u0015n[3\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\u0001\u0011\r!\f\u0002\u0002)F\u0011a&\r\t\u0003E=J!\u0001\r\u000f\u0003\u000f9{G\u000f[5oOB\u0011!EM\u0005\u0003gq\u00111!\u00118z!\tQS\u0007\u0002\u00047\u0001\u0011\u0015\ra\u000e\u0002\u0003\u0007\u000e+\"\u0001O\u001f\u0012\u00059J\u0004c\u0001\u0014;y%\u00111\b\u0007\u0002\f!\u0006\u0014\u0018\n^3sC\ndW\r\u0005\u0002+{\u0011)a(\u000eb\u0001[\t\t\u0001\f\u0005\u0002+\u0001\u00121\u0011\t\u0001CC\u0002\t\u0013AAU3qeF\u0011af\u0011\t\u0004M\u0011K\u0013BA#\u0019\u0005\u0019\u0001\u0016M]*fiB\u0011!f\u0012\u0003\u0007\u0011\u0002!)\u0019A%\u0003\u0015M+\u0017/^3oi&\fG.\u0005\u0002/\u0015J\u00191*T)\u0007\t1\u0003\u0001A\u0013\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004\u001d>KS\"\u0001\u000e\n\u0005AS\"aA*fiB)aJU\u0015U\r&\u00111K\u0007\u0002\u0007'\u0016$x\n]:\u0011\u00059{\u0005\u0003\u0002\u0012WSaK!a\u0016\u000f\u0003\u0013\u0019+hn\u0019;j_:\f\u0004C\u0001\u0012Z\u0013\tQFDA\u0004C_>dW-\u00198\u0011\u0005\tb\u0016BA/\u001d\u0005\u0019)\u0015/^1mg\u00061A%\u001b8ji\u0012\"\u0012\u0001\u0019\t\u0003E\u0005L!A\u0019\u000f\u0003\tUs\u0017\u000e^\u0001\tG>tG/Y5ogR\u0011\u0001,\u001a\u0005\u0006M\n\u0001\r!K\u0001\u0005K2,W.A\u0003baBd\u0017\u0010\u0006\u0002YS\")am\u0001a\u0001S\u0005)A\u0005\u001d7vgR\u0011q\b\u001c\u0005\u0006M\u0012\u0001\r!K\u0001\u0007I5Lg.^:\u0015\u0005}z\u0007\"\u00024\u0006\u0001\u0004I\u0013!C5oi\u0016\u00148/Z2u)\ty$\u000fC\u0003t\r\u0001\u00071)\u0001\u0003uQ\u0006$HCA v\u0011\u0015\u0019x\u00011\u0001N\u0003\u0011!\u0013-\u001c9\u0015\u0005}B\b\"B:\t\u0001\u0004\u0019ECA {\u0011\u0015\u0019\u0018\u00021\u0001N\u0003\u0011!#-\u0019:\u0015\u0005}j\b\"B:\u000b\u0001\u0004\u0019ECA \u0000\u0011\u0015\u00198\u00021\u0001N\u0003)!\u0013-\u001c9%i&dG-\u001a\u000b\u0004\u007f\u0005\u0015\u0001\"B:\r\u0001\u0004\u0019EcA \u0002\n!)1/\u0004a\u0001\u001b\u0006A1/\u001e2tKR|e\rF\u0002Y\u0003\u001fAQa\u001d\bA\u0002\r\u000ba!Z9vC2\u001cHc\u0001-\u0002\u0016!)1o\u0004a\u0001c\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u001cA\u0019!%!\b\n\u0007\u0005}ADA\u0002J]R\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u00041\u0006\u0015\u0002BBA\u0014#\u0001\u0007\u0011'A\u0003pi\",'/A\u0003f[B$\u00180F\u0001@\u0003\u0015)h.[8o)\ry\u0014\u0011\u0007\u0005\u0006gN\u0001\r!\u0014\u000b\u0004\u007f\u0005U\u0002\"B:\u0015\u0001\u0004\u0019\u0015\u0001\u00023jM\u001a$2aPA\u001e\u0011\u0015\u0019X\u00031\u0001N)\ry\u0014q\b\u0005\u0006gZ\u0001\ra\u0011"
)
public interface ParSetLike extends ParIterableLike, Function1, Equals {
   boolean contains(final Object elem);

   // $FF: synthetic method
   static boolean apply$(final ParSetLike $this, final Object elem) {
      return $this.apply(elem);
   }

   default boolean apply(final Object elem) {
      return this.contains(elem);
   }

   ParSet $plus(final Object elem);

   ParSet $minus(final Object elem);

   // $FF: synthetic method
   static ParSet intersect$(final ParSetLike $this, final ParSet that) {
      return $this.intersect(that);
   }

   default ParSet intersect(final ParSet that) {
      return (ParSet)this.filter(that);
   }

   // $FF: synthetic method
   static ParSet intersect$(final ParSetLike $this, final Set that) {
      return $this.intersect(that);
   }

   default ParSet intersect(final Set that) {
      return (ParSet)this.filter(that);
   }

   // $FF: synthetic method
   static ParSet $amp$(final ParSetLike $this, final ParSet that) {
      return $this.$amp(that);
   }

   default ParSet $amp(final ParSet that) {
      return this.intersect(that);
   }

   // $FF: synthetic method
   static ParSet $amp$(final ParSetLike $this, final Set that) {
      return $this.$amp(that);
   }

   default ParSet $amp(final Set that) {
      return this.intersect(that);
   }

   // $FF: synthetic method
   static ParSet $bar$(final ParSetLike $this, final ParSet that) {
      return $this.$bar(that);
   }

   default ParSet $bar(final ParSet that) {
      return this.union(that);
   }

   // $FF: synthetic method
   static ParSet $bar$(final ParSetLike $this, final Set that) {
      return $this.$bar(that);
   }

   default ParSet $bar(final Set that) {
      return this.union(that);
   }

   // $FF: synthetic method
   static ParSet $amp$tilde$(final ParSetLike $this, final ParSet that) {
      return $this.$amp$tilde(that);
   }

   default ParSet $amp$tilde(final ParSet that) {
      return this.diff(that);
   }

   // $FF: synthetic method
   static ParSet $amp$tilde$(final ParSetLike $this, final Set that) {
      return $this.$amp$tilde(that);
   }

   default ParSet $amp$tilde(final Set that) {
      return this.diff(that);
   }

   // $FF: synthetic method
   static boolean subsetOf$(final ParSetLike $this, final ParSet that) {
      return $this.subsetOf(that);
   }

   default boolean subsetOf(final ParSet that) {
      return this.forall(that);
   }

   // $FF: synthetic method
   static boolean equals$(final ParSetLike $this, final Object that) {
      return $this.equals(that);
   }

   default boolean equals(final Object that) {
      if (!(that instanceof ParSet)) {
         return false;
      } else {
         ParSet var4 = (ParSet)that;
         boolean var7;
         if (this != var4) {
            label42: {
               if (var4.canEqual(this) && this.size() == var4.size()) {
                  try {
                     var7 = this.subsetOf(var4);
                  } catch (ClassCastException var6) {
                     var7 = false;
                  }

                  if (var7) {
                     break label42;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }
   }

   // $FF: synthetic method
   static int hashCode$(final ParSetLike $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return .MODULE$.unorderedHash(this, "ParSet".hashCode());
   }

   // $FF: synthetic method
   static boolean canEqual$(final ParSetLike $this, final Object other) {
      return $this.canEqual(other);
   }

   default boolean canEqual(final Object other) {
      return true;
   }

   ParSet empty();

   // $FF: synthetic method
   static ParSet union$(final ParSetLike $this, final Set that) {
      return $this.union(that);
   }

   default ParSet union(final Set that) {
      return (ParSet)this.sequentially((x$1) -> (Set)x$1.union(that));
   }

   // $FF: synthetic method
   static ParSet union$(final ParSetLike $this, final ParSet that) {
      return $this.union(that);
   }

   default ParSet union(final ParSet that) {
      return (ParSet)this.sequentially((x$2) -> (Set)x$2.union((Set)that.seq()));
   }

   // $FF: synthetic method
   static ParSet diff$(final ParSetLike $this, final Set that) {
      return $this.diff(that);
   }

   default ParSet diff(final Set that) {
      return (ParSet)this.sequentially((x$3) -> (Set)x$3.diff(that));
   }

   // $FF: synthetic method
   static ParSet diff$(final ParSetLike $this, final ParSet that) {
      return $this.diff(that);
   }

   default ParSet diff(final ParSet that) {
      return (ParSet)this.sequentially((x$4) -> (Set)x$4.diff((Set)that.seq()));
   }

   static void $init$(final ParSetLike $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
