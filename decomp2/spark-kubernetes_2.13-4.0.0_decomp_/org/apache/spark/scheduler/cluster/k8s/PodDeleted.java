package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001\u0002\f\u0018\u0001\u0012B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0001\")q\n\u0001C\u0001!\"91\u000bAA\u0001\n\u0003!\u0006b\u0002,\u0001#\u0003%\ta\u0016\u0005\bE\u0002\t\t\u0011\"\u0011d\u0011\u001da\u0007!!A\u0005\u00025Dq!\u001d\u0001\u0002\u0002\u0013\u0005!\u000fC\u0004y\u0001\u0005\u0005I\u0011I=\t\u0013\u0005\u0005\u0001!!A\u0005\u0002\u0005\r\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)\u0002C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0011\u0002\u001a!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013QD\u0004\n\u0003C9\u0012\u0011!E\u0001\u0003G1\u0001BF\f\u0002\u0002#\u0005\u0011Q\u0005\u0005\u0007\u001fB!\t!a\u000f\t\u0013\u0005]\u0001#!A\u0005F\u0005e\u0001\"CA\u001f!\u0005\u0005I\u0011QA \u0011%\t\u0019\u0005EA\u0001\n\u0003\u000b)\u0005C\u0005\u0002RA\t\t\u0011\"\u0003\u0002T\tQ\u0001k\u001c3EK2,G/\u001a3\u000b\u0005aI\u0012aA69g*\u0011!dG\u0001\bG2,8\u000f^3s\u0015\taR$A\u0005tG\",G-\u001e7fe*\u0011adH\u0001\u0006gB\f'o\u001b\u0006\u0003A\u0005\na!\u00199bG\",'\"\u0001\u0012\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001)3f\f\u001a\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g!\taS&D\u0001\u0018\u0013\tqsCA\u0007GS:\fG\u000eU8e'R\fG/\u001a\t\u0003MAJ!!M\u0014\u0003\u000fA\u0013x\u000eZ;diB\u00111g\u000f\b\u0003ier!!\u000e\u001d\u000e\u0003YR!aN\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013B\u0001\u001e(\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001P\u001f\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005i:\u0013a\u00019pIV\t\u0001\t\u0005\u0002B\u00196\t!I\u0003\u0002D\t\u0006)Qn\u001c3fY*\u0011QIR\u0001\u0004CBL'BA$I\u0003)YWOY3s]\u0016$Xm\u001d\u0006\u0003\u0013*\u000bqAZ1ce&\u001c\u0007HC\u0001L\u0003\tIw.\u0003\u0002N\u0005\n\u0019\u0001k\u001c3\u0002\tA|G\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005E\u0013\u0006C\u0001\u0017\u0001\u0011\u0015q4\u00011\u0001A\u0003\u0011\u0019w\u000e]=\u0015\u0005E+\u0006b\u0002 \u0005!\u0003\u0005\r\u0001Q\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005A&F\u0001!ZW\u0005Q\u0006CA.a\u001b\u0005a&BA/_\u0003%)hn\u00195fG.,GM\u0003\u0002`O\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0005d&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012\u0001\u001a\t\u0003K*l\u0011A\u001a\u0006\u0003O\"\fA\u0001\\1oO*\t\u0011.\u0001\u0003kCZ\f\u0017BA6g\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\ta\u000e\u0005\u0002'_&\u0011\u0001o\n\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003gZ\u0004\"A\n;\n\u0005U<#aA!os\"9q\u000fCA\u0001\u0002\u0004q\u0017a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001{!\rYhp]\u0007\u0002y*\u0011QpJ\u0001\u000bG>dG.Z2uS>t\u0017BA@}\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u0015\u00111\u0002\t\u0004M\u0005\u001d\u0011bAA\u0005O\t9!i\\8mK\u0006t\u0007bB<\u000b\u0003\u0003\u0005\ra]\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002e\u0003#Aqa^\u0006\u0002\u0002\u0003\u0007a.\u0001\u0005iCND7i\u001c3f)\u0005q\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0011\fa!Z9vC2\u001cH\u0003BA\u0003\u0003?Aqa\u001e\b\u0002\u0002\u0003\u00071/\u0001\u0006Q_\u0012$U\r\\3uK\u0012\u0004\"\u0001\f\t\u0014\u000bA\t9#a\r\u0011\r\u0005%\u0012q\u0006!R\u001b\t\tYCC\u0002\u0002.\u001d\nqA];oi&lW-\u0003\u0003\u00022\u0005-\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u0011QGA\u001d\u001b\t\t9D\u0003\u0002LQ&\u0019A(a\u000e\u0015\u0005\u0005\r\u0012!B1qa2LHcA)\u0002B!)ah\u0005a\u0001\u0001\u00069QO\\1qa2LH\u0003BA$\u0003\u001b\u0002BAJA%\u0001&\u0019\u00111J\u0014\u0003\r=\u0003H/[8o\u0011!\ty\u0005FA\u0001\u0002\u0004\t\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u000b\t\u0004K\u0006]\u0013bAA-M\n1qJ\u00196fGR\u0004"
)
public class PodDeleted implements FinalPodState, Product, Serializable {
   private final Pod pod;

   public static Option unapply(final PodDeleted x$0) {
      return PodDeleted$.MODULE$.unapply(x$0);
   }

   public static PodDeleted apply(final Pod pod) {
      return PodDeleted$.MODULE$.apply(pod);
   }

   public static Function1 andThen(final Function1 g) {
      return PodDeleted$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return PodDeleted$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Pod pod() {
      return this.pod;
   }

   public PodDeleted copy(final Pod pod) {
      return new PodDeleted(pod);
   }

   public Pod copy$default$1() {
      return this.pod();
   }

   public String productPrefix() {
      return "PodDeleted";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.pod();
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
      return x$1 instanceof PodDeleted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "pod";
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
            if (x$1 instanceof PodDeleted) {
               label40: {
                  PodDeleted var4 = (PodDeleted)x$1;
                  Pod var10000 = this.pod();
                  Pod var5 = var4.pod();
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

   public PodDeleted(final Pod pod) {
      this.pod = pod;
      Product.$init$(this);
   }
}
