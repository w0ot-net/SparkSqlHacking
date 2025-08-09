package breeze.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Map;
import scala.collection.MapOps;
import scala.collection.Set;
import scala.collection.ArrayOps.;
import scala.collection.immutable.List;
import scala.collection.mutable.WeakHashMap;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001da\u0001\u0002\r\u001a\u0001yAQ\u0001\u0011\u0001\u0005\u0002\u0005CQ\u0001\u0012\u0001\u0005B\u0015CQ\u0001\u0013\u0001\u0005\u0002%CQa\u0013\u0001\u0005\u00021CQ\u0001\u0015\u0001\u0005\u0002ECQ!\u0016\u0001\u0005\u0002YCQ!\u0016\u0001\u0005\u0002\rDQ!\u0016\u0001\u0005\u0002EDQa\u001e\u0001\u0005\u0002aDq!a\u0001\u0001\t\u0003\t)\u0001C\u0004\u0002\u0014\u0001!I!!\u0006\t\u000f\u0005m\u0002\u0001\"\u0003\u0002>!I\u0011Q\f\u0001A\u0002\u0013%\u0011q\f\u0005\n\u0003s\u0002\u0001\u0019!C\u0005\u0003wB\u0001\"!!\u0001A\u0003&\u0011\u0011M\u0004\b\u0003/K\u0002\u0012AAM\r\u0019A\u0012\u0004#\u0001\u0002\u001c\"1\u0001)\u0005C\u0001\u0003CC\u0011\"a)\u0012\u0005\u0004%I!!*\t\u0011\u0005\u0015\u0017\u0003)A\u0005\u0003OCa\u0001R\t\u0005\u0002\u0005E\u0007bBAq#\u0011\u0005\u00111\u001d\u0005\n\u0003{\f\u0012\u0011!C\u0005\u0003\u007f\u0014\u0001\"\u00138uKJtWM\u001d\u0006\u00035m\tA!\u001e;jY*\tA$\u0001\u0004ce\u0016,'0Z\u0002\u0001+\ty2f\u0005\u0003\u0001A\u0019\"\u0004CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g\r\u0005\u0003\"O%J\u0013B\u0001\u0015#\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002+W1\u0001A!\u0002\u0017\u0001\u0005\u0004i#!\u0001+\u0012\u00059\n\u0004CA\u00110\u0013\t\u0001$EA\u0004O_RD\u0017N\\4\u0011\u0005\u0005\u0012\u0014BA\u001a#\u0005\r\te.\u001f\t\u0003kur!AN\u001e\u000f\u0005]RT\"\u0001\u001d\u000b\u0005ej\u0012A\u0002\u001fs_>$h(C\u0001$\u0013\ta$%A\u0004qC\u000e\\\u0017mZ3\n\u0005yz$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001f#\u0003\u0019a\u0014N\\5u}Q\t!\tE\u0002D\u0001%j\u0011!G\u0001\u0006CB\u0004H.\u001f\u000b\u0003S\u0019CQa\u0012\u0002A\u0002%\n\u0011\u0001^\u0001\u0007S:$XM\u001d8\u0015\u0005%R\u0005\"B$\u0004\u0001\u0004I\u0013!B2mK\u0006\u0014H#A'\u0011\u0005\u0005r\u0015BA(#\u0005\u0011)f.\u001b;\u0002\tML'0Z\u000b\u0002%B\u0011\u0011eU\u0005\u0003)\n\u00121!\u00138u\u0003%Ig\u000e^3s]\u0006cG\u000e\u0006\u0002X?B\u0019\u0001,X\u0015\u000e\u0003eS!AW.\u0002\u0013%lW.\u001e;bE2,'B\u0001/#\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003=f\u0013A\u0001T5ti\")\u0001M\u0002a\u0001C\u0006\t1\rE\u00026E&J!AX \u0015\u0005\u0011\u0004HCA3i!\r\tc-K\u0005\u0003O\n\u0012Q!\u0011:sCfDQ![\u0004A\u0004)\f!a\u0019;\u0011\u0007-t\u0017&D\u0001m\u0015\ti'%A\u0004sK\u001adWm\u0019;\n\u0005=d'\u0001C\"mCN\u001cH+Y4\t\u000b\u0001<\u0001\u0019A3\u0015\u0005I4\bcA:uS5\t1,\u0003\u0002v7\n\u00191+\u001a;\t\u000b\u0001D\u0001\u0019\u0001:\u0002\u0015%tG/\u001a:o\u0017\u0016L8/\u0006\u0002z}R\u0019!0!\u0001\u0011\tM\\\u0018&`\u0005\u0003yn\u00131!T1q!\tQc\u0010B\u0003\u0000\u0013\t\u0007QFA\u0001W\u0011\u0015\u0001\u0017\u00021\u0001{\u00031Ig\u000e^3s]Z\u000bG.^3t+\u0011\t9!!\u0004\u0015\t\u0005%\u0011\u0011\u0003\t\u0006gn\fY!\u000b\t\u0004U\u00055AABA\b\u0015\t\u0007QFA\u0001L\u0011\u0019\u0001'\u00021\u0001\u0002\n\u0005YqO]5uK>\u0013'.Z2u)\ri\u0015q\u0003\u0005\b\u00033Y\u0001\u0019AA\u000e\u0003\rywn\u001d\t\u0005\u0003;\t9#\u0004\u0002\u0002 )!\u0011\u0011EA\u0012\u0003\tIwN\u0003\u0002\u0002&\u0005!!.\u0019<b\u0013\u0011\tI#a\b\u0003%=\u0013'.Z2u\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0015\u0006\u0017\u00055\u0012\u0011\b\t\u0006C\u0005=\u00121G\u0005\u0004\u0003c\u0011#A\u0002;ie><8\u000f\u0005\u0003\u0002\u001e\u0005U\u0012\u0002BA\u001c\u0003?\u00111\"S(Fq\u000e,\u0007\u000f^5p]\u000e\u0012\u00111G\u0001\u000be\u0016\fGm\u00142kK\u000e$HcA'\u0002@!9\u0011\u0011\t\u0007A\u0002\u0005\r\u0013aA8jgB!\u0011QDA#\u0013\u0011\t9%a\b\u0003#=\u0013'.Z2u\u0013:\u0004X\u000f^*ue\u0016\fW\u000eK\u0003\r\u0003\u0017\nI\u0006E\u0003\"\u0003_\ti\u0005\u0005\u0003\u0002P\u0005USBAA)\u0015\u0011\t\u0019&a\t\u0002\t1\fgnZ\u0005\u0005\u0003/\n\tF\u0001\fDY\u0006\u001c8OT8u\r>,h\u000eZ#yG\u0016\u0004H/[8oG\t\ti\u0005K\u0003\r\u0003[\tI$A\u0003j]:,'/\u0006\u0002\u0002bA9\u00111MA5S\u00055TBAA3\u0015\r\t9gW\u0001\b[V$\u0018M\u00197f\u0013\u0011\tY'!\u001a\u0003\u0017]+\u0017m\u001b%bg\"l\u0015\r\u001d\t\u0006\u0003_\n)(K\u0007\u0003\u0003cRA!a\u001d\u0002R\u0005\u0019!/\u001a4\n\t\u0005]\u0014\u0011\u000f\u0002\u000e/\u0016\f7NU3gKJ,gnY3\u0002\u0013%tg.\u001a:`I\u0015\fHcA'\u0002~!I\u0011q\u0010\b\u0002\u0002\u0003\u0007\u0011\u0011M\u0001\u0004q\u0012\n\u0014AB5o]\u0016\u0014\b\u0005K\u0002\u0010\u0003\u000b\u00032!IAD\u0013\r\tII\t\u0002\niJ\fgn]5f]RDs\u0001AAG\u0003'\u000b)\nE\u0002\"\u0003\u001fK1!!%#\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002\u0003!Ie\u000e^3s]\u0016\u0014\bCA\"\u0012'\u0011\t\u0002%!(\u0011\t\u0005u\u0011qT\u0005\u0004}\u0005}ACAAM\u00039!\u0018\u0010]3e\u0013:$XM\u001d8feN,\"!a*\u0011\u0015\u0005%\u0016qVAZ\u0003o\u000b9-\u0004\u0002\u0002,*!\u0011qMAW\u0015\ta6$\u0003\u0003\u00022\u0006-&aC!vi>,\u0006\u000fZ1uKJ\u0004\u0002\"a\u0019\u00026\u0006]\u0016qY\u0005\u0004y\u0006\u0015\u0004\u0007BA]\u0003\u0003\u0004b!a\u0014\u0002<\u0006}\u0016\u0002BA_\u0003#\u0012Qa\u00117bgN\u00042AKAa\t)\t\u0019\rFA\u0001\u0002\u0003\u0015\t!\f\u0002\u0004?\u0012\n\u0014a\u0004;za\u0016$\u0017J\u001c;fe:,'o\u001d\u00111\t\u0005%\u0017Q\u001a\t\u0005\u0007\u0002\tY\rE\u0002+\u0003\u001b$!\"a4\u0015\u0003\u0003\u0005\tQ!\u0001.\u0005\ryFEM\u000b\u0005\u0003'\fI\u000e\u0006\u0003\u0002V\u0006m\u0007\u0003B\"\u0001\u0003/\u00042AKAm\t\u0015aSC1\u0001.\u0011%\ti.FA\u0001\u0002\b\ty.\u0001\u0006fm&$WM\\2fIE\u0002Ba\u001b8\u0002X\u0006Aam\u001c:DY\u0006\u001c8/\u0006\u0003\u0002f\u0006-H\u0003BAt\u0003[\u0004Ba\u0011\u0001\u0002jB\u0019!&a;\u0005\u000b12\"\u0019A\u0017\t\r\u00014\u0002\u0019AAx!\u0019\t\t0!?\u0002j:!\u00111_A{!\t9$%C\u0002\u0002x\n\na\u0001\u0015:fI\u00164\u0017\u0002BA_\u0003wT1!a>#\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011\t\u0001\u0005\u0003\u0002P\t\r\u0011\u0002\u0002B\u0003\u0003#\u0012aa\u00142kK\u000e$\b"
)
public class Interner implements Function1, Serializable {
   private static final long serialVersionUID = 1L;
   private transient WeakHashMap inner;

   public static Interner forClass(final Class c) {
      return Interner$.MODULE$.forClass(c);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   public String toString() {
      return Function1.toString$(this);
   }

   public Object apply(final Object t) {
      return this.intern(t);
   }

   public synchronized Object intern(final Object t) {
      return ((Reference)this.inner().getOrElseUpdate(t, () -> new WeakReference(t))).get();
   }

   public void clear() {
      this.inner().clear();
   }

   public int size() {
      return this.inner().size();
   }

   public List internAll(final List c) {
      return c.map((t) -> this.apply(t));
   }

   public Object internAll(final Object c, final ClassTag ct) {
      return .MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(c), (t) -> this.apply(t), ct);
   }

   public Set internAll(final Set c) {
      return (Set)c.map((t) -> this.apply(t));
   }

   public Map internKeys(final Map c) {
      return (Map)((MapOps)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$)).$plus$plus(c.map((x0$1) -> {
         if (x0$1 != null) {
            Object k = x0$1._1();
            Object v = x0$1._2();
            Tuple2 var2 = new Tuple2(this.intern(k), v);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }));
   }

   public Map internValues(final Map c) {
      return (Map)((MapOps)scala.collection.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$)).$plus$plus(c.map((x0$1) -> {
         if (x0$1 != null) {
            Object k = x0$1._1();
            Object v = x0$1._2();
            Tuple2 var2 = new Tuple2(k, this.intern(v));
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }));
   }

   private WeakHashMap inner() {
      return this.inner;
   }

   private void inner_$eq(final WeakHashMap x$1) {
      this.inner = x$1;
   }

   private void writeObject(final ObjectOutputStream oos) throws IOException {
      oos.defaultWriteObject();
   }

   private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
      ois.defaultReadObject();
      this.inner_$eq(new WeakHashMap());
   }

   public Interner() {
      Function1.$init$(this);
      this.inner = new WeakHashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
