package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.View;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005ba\u0002\b\u0010!\u0003\r\tA\u0006\u0005\u0006m\u0001!\ta\u000e\u0005\u0006w\u0001!)\u0005\u0010\u0005\u0006{\u0001!\tE\u0010\u0005\u0006\t\u0002!\t%\u0012\u0005\u0006!\u0002!\t\"\u0015\u0005\u0006+\u0002!\tE\u0016\u0005\f5\u0002\u0001\n1!A\u0001\n\u0013YV\fC\u0006`\u0001A\u0005\u0019\u0011!A\u0005\n\u00014w!B5\u0010\u0011\u0003Qg!\u0002\b\u0010\u0011\u0003Y\u0007\"B:\u000b\t\u0003!\b\"B;\u000b\t\u00032\b\u0002C@\u000b\u0003\u0003%I!!\u0001\u0003\u0015%sG-\u001a=fIN+\u0017O\u0003\u0002\u0011#\u0005I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003%M\t!bY8mY\u0016\u001cG/[8o\u0015\u0005!\u0012!B:dC2\f7\u0001A\u000b\u0003/\t\u001ab\u0001\u0001\r\u001dW9\u001a\u0004CA\r\u001b\u001b\u0005\u0019\u0012BA\u000e\u0014\u0005\u0019\te.\u001f*fMB\u0019QD\b\u0011\u000e\u0003=I!aH\b\u0003\u0007M+\u0017\u000f\u0005\u0002\"E1\u0001AAB\u0012\u0001\t\u000b\u0007AEA\u0001B#\t)\u0003\u0006\u0005\u0002\u001aM%\u0011qe\u0005\u0002\b\u001d>$\b.\u001b8h!\tI\u0012&\u0003\u0002+'\t\u0019\u0011I\\=\u0011\u00071j\u0003%D\u0001\u0012\u0013\tq\u0011\u0003E\u0003\u001e_\u0001\n$'\u0003\u00021\u001f\ti\u0011J\u001c3fq\u0016$7+Z9PaN\u0004\"!\b\u0001\u0011\u0007u\u0001\u0001\u0005\u0005\u0003-i\u0001\n\u0014BA\u001b\u0012\u0005]IE/\u001a:bE2,g)Y2u_JLH)\u001a4bk2$8/\u0001\u0004%S:LG\u000f\n\u000b\u0002qA\u0011\u0011$O\u0005\u0003uM\u0011A!\u00168ji\u0006aAo\\%oI\u0016DX\rZ*fcV\t!'\u0001\u0005dC:,\u0015/^1m)\ty$\t\u0005\u0002\u001a\u0001&\u0011\u0011i\u0005\u0002\b\u0005>|G.Z1o\u0011\u0015\u00195\u00011\u0001)\u0003\u0011!\b.\u0019;\u0002\u0019M\fW.Z#mK6,g\u000e^:\u0016\u0005\u0019kECA H\u0011\u0015AE\u00011\u0001J\u0003\u0005y\u0007c\u0001\u0017K\u0019&\u00111*\u0005\u0002\r\u0013R,'/\u00192mK>s7-\u001a\t\u0003C5#QA\u0014\u0003C\u0002=\u0013\u0011AQ\t\u0003A!\nq#\u00199qYf\u0004&/\u001a4feJ,G-T1y\u0019\u0016tw\r\u001e5\u0016\u0003I\u0003\"!G*\n\u0005Q\u001b\"aA%oi\u0006y\u0011\u000e^3sC\ndWMR1di>\u0014\u00180F\u0001X!\ra\u0003,M\u0005\u00033F\u0011!bU3r\r\u0006\u001cGo\u001c:z\u00039\u0019X\u000f]3sI\r\fg.R9vC2$\"a\u0010/\t\u000b\r;\u0001\u0019\u0001\u0015\n\u0005ur\u0016BA\u0010\u0012\u0003I\u0019X\u000f]3sIM\fW.Z#mK6,g\u000e^:\u0016\u0005\u0005,GCA c\u0011\u0015\u0019\u0005\u00021\u0001d!\ra#\n\u001a\t\u0003C\u0015$QA\u0014\u0005C\u0002=K!\u0001R4\n\u0005!\f\"AB*fc>\u00038/\u0001\u0006J]\u0012,\u00070\u001a3TKF\u0004\"!\b\u0006\u0014\u0005)a\u0007cA7qc9\u0011AF\\\u0005\u0003_F\t!bU3r\r\u0006\u001cGo\u001c:z\u0013\t\t(O\u0001\u0005EK2,w-\u0019;f\u0015\ty\u0017#\u0001\u0004=S:LGO\u0010\u000b\u0002U\u0006!aM]8n+\t9(\u0010\u0006\u0002yyB\u0019Q\u0004A=\u0011\u0005\u0005RH!B>\r\u0005\u0004!#!A#\t\u000bud\u0001\u0019\u0001@\u0002\u0005%$\bc\u0001\u0017Ks\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0001\t\u0005\u0003\u000b\ty!\u0004\u0002\u0002\b)!\u0011\u0011BA\u0006\u0003\u0011a\u0017M\\4\u000b\u0005\u00055\u0011\u0001\u00026bm\u0006LA!!\u0005\u0002\b\t1qJ\u00196fGRDsACA\u000b\u00037\ti\u0002E\u0002\u001a\u0003/I1!!\u0007\u0014\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0004Q\u001dI\u0011QCA\u000e\u0003;\u0001"
)
public interface IndexedSeq extends Seq, scala.collection.IndexedSeq, IndexedSeqOps {
   static IndexedSeq from(final IterableOnce it) {
      return IndexedSeq$.MODULE$.from(it);
   }

   static Builder newBuilder() {
      return IndexedSeq$.MODULE$.newBuilder();
   }

   static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      IndexedSeq$ var10000 = IndexedSeq$.MODULE$;
      return x;
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      SeqFactory.Delegate tabulate_this = IndexedSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$7$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      SeqFactory.Delegate tabulate_this = IndexedSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$5$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      SeqFactory.Delegate tabulate_this = IndexedSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$3$adapted);
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      SeqFactory.Delegate tabulate_this = IndexedSeq$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$1$adapted);
   }

   static Object tabulate(final int n, final Function1 f) {
      SeqFactory.Delegate tabulate_this = IndexedSeq$.MODULE$;
      IterableOnce from_source = new View.Tabulate(n, f);
      return ((IndexedSeq$)tabulate_this).from(from_source);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$4);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$3);
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$2);
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedSeq$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$1);
   }

   static Object fill(final int n, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedSeq$.MODULE$;
      IterableOnce from_source = new View.Fill(n, elem);
      return ((IndexedSeq$)fill_this).from(from_source);
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(IndexedSeq$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(IndexedSeq$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      SeqFactory.Delegate unfold_this = IndexedSeq$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return ((IndexedSeq$)unfold_this).from(from_source);
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      SeqFactory.Delegate iterate_this = IndexedSeq$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return ((IndexedSeq$)iterate_this).from(from_source);
   }

   // $FF: synthetic method
   boolean scala$collection$immutable$IndexedSeq$$super$canEqual(final Object that);

   // $FF: synthetic method
   boolean scala$collection$immutable$IndexedSeq$$super$sameElements(final IterableOnce that);

   // $FF: synthetic method
   static IndexedSeq toIndexedSeq$(final IndexedSeq $this) {
      return $this.toIndexedSeq();
   }

   default IndexedSeq toIndexedSeq() {
      return this;
   }

   // $FF: synthetic method
   static boolean canEqual$(final IndexedSeq $this, final Object that) {
      return $this.canEqual(that);
   }

   default boolean canEqual(final Object that) {
      if (that instanceof IndexedSeq) {
         IndexedSeq var2 = (IndexedSeq)that;
         return this.length() == var2.length() && this.scala$collection$immutable$IndexedSeq$$super$canEqual(that);
      } else {
         return this.scala$collection$immutable$IndexedSeq$$super$canEqual(that);
      }
   }

   // $FF: synthetic method
   static boolean sameElements$(final IndexedSeq $this, final IterableOnce o) {
      return $this.sameElements(o);
   }

   default boolean sameElements(final IterableOnce o) {
      if (!(o instanceof IndexedSeq)) {
         return this.scala$collection$immutable$IndexedSeq$$super$sameElements(o);
      } else {
         IndexedSeq var2 = (IndexedSeq)o;
         if (this != var2) {
            int length = this.length();
            boolean equal = length == var2.length();
            if (equal) {
               int index = 0;
               int preferredLength = Math.min(this.applyPreferredMaxLength(), var2.applyPreferredMaxLength());

               for(int maxApplyCompare = (long)length > (long)preferredLength << 1 ? preferredLength : length; index < maxApplyCompare && equal; ++index) {
                  equal = BoxesRunTime.equals(this.apply(index), var2.apply(index));
               }

               if (index < length && equal) {
                  Iterator thisIt = this.iterator().drop(index);

                  for(Iterator thatIt = var2.iterator().drop(index); equal && thisIt.hasNext(); equal = BoxesRunTime.equals(thisIt.next(), thatIt.next())) {
                  }
               }
            }

            if (!equal) {
               return false;
            }
         }

         return true;
      }
   }

   // $FF: synthetic method
   static int applyPreferredMaxLength$(final IndexedSeq $this) {
      return $this.applyPreferredMaxLength();
   }

   default int applyPreferredMaxLength() {
      return IndexedSeqDefaults$.MODULE$.defaultApplyPreferredMaxLength();
   }

   // $FF: synthetic method
   static SeqFactory iterableFactory$(final IndexedSeq $this) {
      return $this.iterableFactory();
   }

   default SeqFactory iterableFactory() {
      return IndexedSeq$.MODULE$;
   }

   static void $init$(final IndexedSeq $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
