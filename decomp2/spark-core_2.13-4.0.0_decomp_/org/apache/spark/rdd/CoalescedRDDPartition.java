package org.apache.spark.rdd;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.ArrayImplicits.;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t%b!\u0002\u0013&\u0001\u001ej\u0003\u0002\u0003%\u0001\u0005+\u0007I\u0011A%\t\u00115\u0003!\u0011#Q\u0001\n)C\u0001B\n\u0001\u0003\u0016\u0004%\tA\u0014\u0005\t1\u0002\u0011\t\u0012)A\u0005\u001f\"AA\r\u0001BK\u0002\u0013\u0005Q\r\u0003\u0005j\u0001\tE\t\u0015!\u0003g\u0011!Q\u0007A!f\u0001\n\u0003Y\u0007\u0002C<\u0001\u0005#\u0005\u000b\u0011\u00027\t\u000be\u0004A\u0011\u0001>\t\u0013\u0005%\u0001\u00011A\u0005\u0002\u0005-\u0001\"CA\n\u0001\u0001\u0007I\u0011AA\u000b\u0011!\t\t\u0003\u0001Q!\n\u00055\u0001bBA\u0012\u0001\u0011%\u0011Q\u0005\u0005\b\u0003\u0017\u0002A\u0011AA'\u0011%\t)\u0006AA\u0001\n\u0003\t9\u0006C\u0005\u0002b\u0001\t\n\u0011\"\u0001\u0002d!I\u0011\u0011\u0010\u0001\u0012\u0002\u0013\u0005\u00111\u0010\u0005\n\u0003\u000f\u0003\u0011\u0013!C\u0001\u0003\u0013C\u0011\"!$\u0001#\u0003%\t!a$\t\u0013\u0005M\u0005!!A\u0005B\u0005U\u0005\u0002CAQ\u0001\u0005\u0005I\u0011A%\t\u0013\u0005\r\u0006!!A\u0005\u0002\u0005\u0015\u0006\"CAU\u0001\u0005\u0005I\u0011IAV\u0011%\tI\fAA\u0001\n\u0003\tY\fC\u0005\u0002F\u0002\t\t\u0011\"\u0011\u0002H\"I\u00111\u001a\u0001\u0002\u0002\u0013\u0005\u0013QZ\u0004\u000b\u0003\u001f,\u0013\u0011!E\u0001O\u0005Eg!\u0003\u0013&\u0003\u0003E\taJAj\u0011\u0019IH\u0004\"\u0001\u0002n\"I\u00111\u001a\u000f\u0002\u0002\u0013\u0015\u0013Q\u001a\u0005\n\u0003_d\u0012\u0011!CA\u0003cD\u0011Ba\u0001\u001d#\u0003%\t!a$\t\u0013\t\u0015A$!A\u0005\u0002\n\u001d\u0001\"\u0003B\u000f9E\u0005I\u0011AAH\u0011%\u0011y\u0002HA\u0001\n\u0013\u0011\tCA\u000bD_\u0006dWm]2fIJ#E\tU1si&$\u0018n\u001c8\u000b\u0005\u0019:\u0013a\u0001:eI*\u0011\u0001&K\u0001\u0006gB\f'o\u001b\u0006\u0003U-\na!\u00199bG\",'\"\u0001\u0017\u0002\u0007=\u0014xmE\u0003\u0001]QB4\b\u0005\u00020e5\t\u0001GC\u00012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0004G\u0001\u0004B]f\u0014VM\u001a\t\u0003kYj\u0011aJ\u0005\u0003o\u001d\u0012\u0011\u0002U1si&$\u0018n\u001c8\u0011\u0005=J\u0014B\u0001\u001e1\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001P#\u000f\u0005u\u001aeB\u0001 C\u001b\u0005y$B\u0001!B\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0019\n\u0005\u0011\u0003\u0014a\u00029bG.\fw-Z\u0005\u0003\r\u001e\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u0012\u0019\u0002\u000b%tG-\u001a=\u0016\u0003)\u0003\"aL&\n\u00051\u0003$aA%oi\u00061\u0011N\u001c3fq\u0002*\u0012a\u0014\u0019\u0003!Z\u00032!\u0015*U\u001b\u0005)\u0013BA*&\u0005\r\u0011F\t\u0012\t\u0003+Zc\u0001\u0001B\u0005X\t\u0005\u0005\t\u0011!B\u0001;\n\u0019q\fJ\u0019\u0002\tI$G\r\t\u0015\u0003\ti\u0003\"aL.\n\u0005q\u0003$!\u0003;sC:\u001c\u0018.\u001a8u#\tq\u0016\r\u0005\u00020?&\u0011\u0001\r\r\u0002\b\u001d>$\b.\u001b8h!\ty#-\u0003\u0002da\t\u0019\u0011I\\=\u0002\u001dA\f'/\u001a8ug&sG-[2fgV\ta\rE\u00020O*K!\u0001\u001b\u0019\u0003\u000b\u0005\u0013(/Y=\u0002\u001fA\f'/\u001a8ug&sG-[2fg\u0002\n\u0011\u0003\u001d:fM\u0016\u0014(/\u001a3M_\u000e\fG/[8o+\u0005a\u0007cA\u0018n_&\u0011a\u000e\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005A$hBA9s!\tq\u0004'\u0003\u0002ta\u00051\u0001K]3eK\u001aL!!\u001e<\u0003\rM#(/\u001b8h\u0015\t\u0019\b'\u0001\nqe\u00164WM\u001d:fI2{7-\u0019;j_:\u0004\u0003F\u0001\u0005[\u0003\u0019a\u0014N\\5u}Q91\u0010`?\u0002\u0006\u0005\u001d\u0001CA)\u0001\u0011\u0015A\u0015\u00021\u0001K\u0011\u00151\u0013\u00021\u0001\u007fa\ry\u00181\u0001\t\u0005#J\u000b\t\u0001E\u0002V\u0003\u0007!\u0011bV?\u0002\u0002\u0003\u0005)\u0011A/\t\u000b\u0011L\u0001\u0019\u00014\t\u000f)L\u0001\u0013!a\u0001Y\u00069\u0001/\u0019:f]R\u001cXCAA\u0007!\u0011a\u0014q\u0002\u001b\n\u0007\u0005EqIA\u0002TKF\f1\u0002]1sK:$8o\u0018\u0013fcR!\u0011qCA\u000f!\ry\u0013\u0011D\u0005\u0004\u00037\u0001$\u0001B+oSRD\u0011\"a\b\f\u0003\u0003\u0005\r!!\u0004\u0002\u0007a$\u0013'\u0001\u0005qCJ,g\u000e^:!\u0003-9(/\u001b;f\u001f\nTWm\u0019;\u0015\t\u0005]\u0011q\u0005\u0005\b\u0003Si\u0001\u0019AA\u0016\u0003\rywn\u001d\t\u0005\u0003[\t9$\u0004\u0002\u00020)!\u0011\u0011GA\u001a\u0003\tIwN\u0003\u0002\u00026\u0005!!.\u0019<b\u0013\u0011\tI$a\f\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0015\u0006\u001b\u0005u\u0012\u0011\n\t\u0006_\u0005}\u00121I\u0005\u0004\u0003\u0003\u0002$A\u0002;ie><8\u000f\u0005\u0003\u0002.\u0005\u0015\u0013\u0002BA$\u0003_\u00111\"S(Fq\u000e,\u0007\u000f^5p]\u000e\u0012\u00111I\u0001\u000eY>\u001c\u0017\r\u001c$sC\u000e$\u0018n\u001c8\u0016\u0005\u0005=\u0003cA\u0018\u0002R%\u0019\u00111\u000b\u0019\u0003\r\u0011{WO\u00197f\u0003\u0011\u0019w\u000e]=\u0015\u0013m\fI&a\u0017\u0002^\u0005}\u0003b\u0002%\u0010!\u0003\u0005\rA\u0013\u0005\bM=\u0001\n\u00111\u0001\u007f\u0011\u001d!w\u0002%AA\u0002\u0019DqA[\b\u0011\u0002\u0003\u0007A.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u0015$f\u0001&\u0002h-\u0012\u0011\u0011\u000e\t\u0005\u0003W\n)(\u0004\u0002\u0002n)!\u0011qNA9\u0003%)hn\u00195fG.,GMC\u0002\u0002tA\n!\"\u00198o_R\fG/[8o\u0013\u0011\t9(!\u001c\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005u\u0004\u0007BA@\u0003\u000bSC!!!\u0002hA!\u0011KUAB!\r)\u0016Q\u0011\u0003\n/F\t\t\u0011!A\u0003\u0002u\u000babY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\f*\u001aa-a\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011\u0011\u0013\u0016\u0004Y\u0006\u001d\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002\u0018B!\u0011\u0011TAP\u001b\t\tYJ\u0003\u0003\u0002\u001e\u0006M\u0012\u0001\u00027b]\u001eL1!^AN\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$2!YAT\u0011!\tyBFA\u0001\u0002\u0004Q\u0015a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u00055\u0006#BAX\u0003k\u000bWBAAY\u0015\r\t\u0019\fM\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\\\u0003c\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QXAb!\ry\u0013qX\u0005\u0004\u0003\u0003\u0004$a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003?A\u0012\u0011!a\u0001C\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t9*!3\t\u0011\u0005}\u0011$!AA\u0002)\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003/\u000bQcQ8bY\u0016\u001c8-\u001a3S\t\u0012\u0003\u0016M\u001d;ji&|g\u000e\u0005\u0002R9M)A$!6\u0002jBQ\u0011q[Ao\u0015\u0006\u0005h\r\\>\u000e\u0005\u0005e'bAAna\u00059!/\u001e8uS6,\u0017\u0002BAp\u00033\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c85a\u0011\t\u0019/a:\u0011\tE\u0013\u0016Q\u001d\t\u0004+\u0006\u001dH!C,\u001d\u0003\u0003\u0005\tQ!\u0001^!\u0011\ti#a;\n\u0007\u0019\u000by\u0003\u0006\u0002\u0002R\u0006)\u0011\r\u001d9msRI10a=\u0002v\u0006}(\u0011\u0001\u0005\u0006\u0011~\u0001\rA\u0013\u0005\u0007M}\u0001\r!a>1\t\u0005e\u0018Q \t\u0005#J\u000bY\u0010E\u0002V\u0003{$!bVA{\u0003\u0003\u0005\tQ!\u0001^\u0011\u0015!w\u00041\u0001g\u0011\u001dQw\u0004%AA\u00021\fq\"\u00199qYf$C-\u001a4bk2$H\u0005N\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011IA!\u0007\u0011\t=j'1\u0002\t\t_\t5!J!\u0005gY&\u0019!q\u0002\u0019\u0003\rQ+\b\u000f\\35a\u0011\u0011\u0019Ba\u0006\u0011\tE\u0013&Q\u0003\t\u0004+\n]A!C,\"\u0003\u0003\u0005\tQ!\u0001^\u0011!\u0011Y\"IA\u0001\u0002\u0004Y\u0018a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\t\u0011\t\u0005e%QE\u0005\u0005\u0005O\tYJ\u0001\u0004PE*,7\r\u001e"
)
public class CoalescedRDDPartition implements Partition, Product {
   private final int index;
   private final transient RDD rdd;
   private final int[] parentsIndices;
   private final transient Option preferredLocation;
   private Seq parents;

   public static Option $lessinit$greater$default$4() {
      return CoalescedRDDPartition$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option unapply(final CoalescedRDDPartition x$0) {
      return CoalescedRDDPartition$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$4() {
      return CoalescedRDDPartition$.MODULE$.apply$default$4();
   }

   public static CoalescedRDDPartition apply(final int index, final RDD rdd, final int[] parentsIndices, final Option preferredLocation) {
      return CoalescedRDDPartition$.MODULE$.apply(index, rdd, parentsIndices, preferredLocation);
   }

   public static Function1 tupled() {
      return CoalescedRDDPartition$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return CoalescedRDDPartition$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   // $FF: synthetic method
   public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
      return super.equals(x$1);
   }

   public int hashCode() {
      return Partition.hashCode$(this);
   }

   public boolean equals(final Object other) {
      return Partition.equals$(this, other);
   }

   public int index() {
      return this.index;
   }

   public RDD rdd() {
      return this.rdd;
   }

   public int[] parentsIndices() {
      return this.parentsIndices;
   }

   public Option preferredLocation() {
      return this.preferredLocation;
   }

   public Seq parents() {
      return this.parents;
   }

   public void parents_$eq(final Seq x$1) {
      this.parents = x$1;
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         this.parents_$eq(.MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(this.parentsIndices()), (x$2) -> $anonfun$writeObject$2(this, BoxesRunTime.unboxToInt(x$2)), scala.reflect.ClassTag..MODULE$.apply(Partition.class))).toImmutableArraySeq());
         oos.defaultWriteObject();
      });
   }

   public double localFraction() {
      int loc = this.parents().count((p) -> BoxesRunTime.boxToBoolean($anonfun$localFraction$1(this, p)));
      return this.parents().isEmpty() ? (double)0.0F : (double)loc / (double)this.parents().size();
   }

   public CoalescedRDDPartition copy(final int index, final RDD rdd, final int[] parentsIndices, final Option preferredLocation) {
      return new CoalescedRDDPartition(index, rdd, parentsIndices, preferredLocation);
   }

   public int copy$default$1() {
      return this.index();
   }

   public RDD copy$default$2() {
      return this.rdd();
   }

   public int[] copy$default$3() {
      return this.parentsIndices();
   }

   public Option copy$default$4() {
      return this.preferredLocation();
   }

   public String productPrefix() {
      return "CoalescedRDDPartition";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.index());
         }
         case 1 -> {
            return this.rdd();
         }
         case 2 -> {
            return this.parentsIndices();
         }
         case 3 -> {
            return this.preferredLocation();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof CoalescedRDDPartition;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "index";
         }
         case 1 -> {
            return "rdd";
         }
         case 2 -> {
            return "parentsIndices";
         }
         case 3 -> {
            return "preferredLocation";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   // $FF: synthetic method
   public static final Partition $anonfun$parents$1(final CoalescedRDDPartition $this, final int x$1) {
      return $this.rdd().partitions()[x$1];
   }

   // $FF: synthetic method
   public static final Partition $anonfun$writeObject$2(final CoalescedRDDPartition $this, final int x$2) {
      return $this.rdd().partitions()[x$2];
   }

   // $FF: synthetic method
   public static final boolean $anonfun$localFraction$3(final Seq parentPreferredLocations$1, final Object elem) {
      return parentPreferredLocations$1.contains(elem);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$localFraction$1(final CoalescedRDDPartition $this, final Partition p) {
      Seq parentPreferredLocations = (Seq)$this.rdd().context().getPreferredLocs($this.rdd(), p.index()).map((x$3) -> x$3.host());
      return $this.preferredLocation().exists((elem) -> BoxesRunTime.boxToBoolean($anonfun$localFraction$3(parentPreferredLocations, elem)));
   }

   public CoalescedRDDPartition(final int index, final RDD rdd, final int[] parentsIndices, final Option preferredLocation) {
      this.index = index;
      this.rdd = rdd;
      this.parentsIndices = parentsIndices;
      this.preferredLocation = preferredLocation;
      Partition.$init$(this);
      Product.$init$(this);
      this.parents = .MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(parentsIndices), (x$1) -> $anonfun$parents$1(this, BoxesRunTime.unboxToInt(x$1)), scala.reflect.ClassTag..MODULE$.apply(Partition.class))).toImmutableArraySeq();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
