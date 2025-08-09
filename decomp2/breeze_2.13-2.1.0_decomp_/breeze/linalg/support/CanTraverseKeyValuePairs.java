package breeze.linalg.support;

import scala.Function1;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t-aaB\u0012%!\u0003\r\na\u000b\u0005\u0006g\u00011\t\u0001\u000e\u0005\b\u0003s\u0002a\u0011\u0001B\u0004\u000f\u0015\u0011F\u0005#\u0001T\r\u0015\u0019C\u0005#\u0001V\u0011\u00151F\u0001\"\u0001X\r\u001dAF\u0001%A\u0002\u0002eCQa\u0017\u0004\u0005\u0002qCQ!\u0018\u0004\u0007\u0002yCQ!\u001e\u0004\u0005\u0002YDa!\u001e\u0004\u0005\u0002\u0005%\u0001bBA\u000e\r\u0019\u0005\u0011Q\u0004\u0004\u0007\u0003s!\u0001!a\u000f\t\rYcA\u0011AA6\u0011\u0019\u0019D\u0002\"\u0001\u0002r!9\u0011\u0011\u0010\u0007\u0005\u0002\u0005m\u0004bBAC\t\u0011\r\u0011qQ\u0004\b\u0003'#\u00012AAK\r\u001d\t9\n\u0002E\u0001\u00033CaA\u0016\n\u0005\u0002\u0005uuaBAP\t!\r\u0011\u0011\u0015\u0004\b\u0003G#\u0001\u0012AAS\u0011\u00191V\u0003\"\u0001\u00020\u001e9\u0011\u0011\u0017\u0003\t\u0004\u0005MfaBA[\t!\u0005\u0011q\u0017\u0005\u0007-b!\t!!1\b\u000f\u0005\rG\u0001c\u0001\u0002F\u001a9\u0011q\u0019\u0003\t\u0002\u0005%\u0007B\u0002,\u001c\t\u0003\t\u0019nB\u0004\u0002V\u0012A\u0019!a6\u0007\u000f\u0005eG\u0001#\u0001\u0002\\\"1aK\bC\u0001\u0003K<q!a:\u0005\u0011\u0007\tIOB\u0004\u0002l\u0012A\t!!<\t\rY\u000bC\u0011AA\u007f\u0005a\u0019\u0015M\u001c+sCZ,'o]3LKf4\u0016\r\\;f!\u0006L'o\u001d\u0006\u0003K\u0019\nqa];qa>\u0014HO\u0003\u0002(Q\u00051A.\u001b8bY\u001eT\u0011!K\u0001\u0007EJ,WM_3\u0004\u0001U1A\u0006\u0010B\u0001\u0005\u000b\u0019\"\u0001A\u0017\u0011\u00059\nT\"A\u0018\u000b\u0003A\nQa]2bY\u0006L!AM\u0018\u0003\r\u0005s\u0017PU3g\u0003!!(/\u0019<feN,GcA\u001b9\u000bB\u0011aFN\u0005\u0003o=\u0012A!\u00168ji\")\u0011(\u0001a\u0001u\u0005!aM]8n!\tYD\b\u0004\u0001\u0005\u000bu\u0002!\u0019\u0001 \u0003\t\u0019\u0013x.\\\t\u0003\u007f\t\u0003\"A\f!\n\u0005\u0005{#a\u0002(pi\"Lgn\u001a\t\u0003]\rK!\u0001R\u0018\u0003\u0007\u0005s\u0017\u0010C\u0003G\u0003\u0001\u0007q)\u0001\u0002g]B1\u0001JBA\u0000\u0005\u0007q!!S\u0002\u000f\u0005)\u000bfBA&Q\u001d\tau*D\u0001N\u0015\tq%&\u0001\u0004=e>|GOP\u0005\u0002S%\u0011q\u0005K\u0005\u0003K\u0019\n\u0001dQ1o)J\fg/\u001a:tK.+\u0017PV1mk\u0016\u0004\u0016-\u001b:t!\t!F!D\u0001%'\t!Q&\u0001\u0004=S:LGO\u0010\u000b\u0002'\n!2*Z=WC2,X\rU1jeN4\u0016n]5u_J,2A\u00172s'\t1Q&\u0001\u0004%S:LG\u000f\n\u000b\u0002k\u0005)a/[:jiR\u0019QgX8\t\u000b\u0001D\u0001\u0019A1\u0002\u0003-\u0004\"a\u000f2\u0005\u0013\r4\u0001\u0015!A\u0001\u0006\u0004q$!A&)\u0007\t,\u0007\u000e\u0005\u0002/M&\u0011qm\f\u0002\fgB,7-[1mSj,G-M\u0003$S*d7N\u0004\u0002/U&\u00111nL\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013n]Br!\u0001\u00148\n\u0003ABQ\u0001\u001d\u0005A\u0002E\f\u0011!\u0019\t\u0003wI$\u0011b\u001d\u0004!\u0002\u0003\u0005)\u0019\u0001 \u0003\u0003\u0005C#A]3\u0002\u0015YL7/\u001b;BeJ\f\u0017\u0010F\u00026o~DQ\u0001_\u0005A\u0002e\fq!\u001b8eS\u000e,7\u000f\u0005\u0003/ur\f\u0017BA>0\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002/{&\u0011ap\f\u0002\u0004\u0013:$\bbBA\u0001\u0013\u0001\u0007\u00111A\u0001\u0004CJ\u0014\b\u0003\u0002\u0018\u0002\u0006EL1!a\u00020\u0005\u0015\t%O]1z)-)\u00141BA\u0007\u0003\u001f\t\u0019\"a\u0006\t\u000baT\u0001\u0019A=\t\u000f\u0005\u0005!\u00021\u0001\u0002\u0004!1\u0011\u0011\u0003\u0006A\u0002q\faa\u001c4gg\u0016$\bBBA\u000b\u0015\u0001\u0007A0\u0001\u0004mK:<G\u000f\u001b\u0005\u0007\u00033Q\u0001\u0019\u0001?\u0002\rM$(/\u001b3f\u0003\u0015QXM]8t)\u001d)\u0014qDA\u0012\u0003kAa!!\t\f\u0001\u0004a\u0018a\u00028v[j+'o\u001c\u0005\b\u0003KY\u0001\u0019AA\u0014\u0003!QXM]8LKf\u001c\b#BA\u0015\u0003_\tgbA7\u0002,%\u0019\u0011QF\u0018\u0002\u000fA\f7m[1hK&!\u0011\u0011GA\u001a\u0005!IE/\u001a:bi>\u0014(bAA\u0017_!1\u0011qG\u0006A\u0002E\f\u0011B_3s_Z\u000bG.^3\u0003\u000f=\u0003\u0018I\u001d:bsV!\u0011QHA#'\u0011aQ&a\u0010\u0011\u000fQ\u0003\u0011\u0011\t?\u0002DA)a&!\u0002\u0002DA\u00191(!\u0012\u0005\u0013Md\u0001\u0015!A\u0001\u0006\u0004q\u0004fCA#K\u0006%\u00131KA,\u0003C\n\u0014bIA&\u0003\u001b\n\t&a\u0014\u000f\u00079\ni%C\u0002\u0002P=\na\u0001R8vE2,\u0017\u0007\u0002\u0013n]B\ndaI5k\u0003+Z\u0017\u0007\u0002\u0013n]B\n\u0014bIA-\u00037\ny&!\u0018\u000f\u00079\nY&C\u0002\u0002^=\nQA\u00127pCR\fD\u0001J7oaEJ1%a\u0019\u0002f\u0005%\u0014q\r\b\u0004]\u0005\u0015\u0014bAA4_\u0005!Aj\u001c8hc\u0011!SN\u001c\u0019\u0015\u0005\u00055\u0004#BA8\u0019\u0005\rS\"\u0001\u0003\u0015\u000bU\n\u0019(!\u001e\t\rer\u0001\u0019AA!\u0011\u00191e\u00021\u0001\u0002xA1\u0011q\u000e\u0004}\u0003\u0007\n!#[:Ue\u00064XM]:bE2,\u0017iZ1j]R!\u0011QPAB!\rq\u0013qP\u0005\u0004\u0003\u0003{#a\u0002\"p_2,\u0017M\u001c\u0005\u0007s=\u0001\r!!\u0011\u0002\u000f=\u0004\u0018I\u001d:bsV!\u0011\u0011RAH+\t\tY\tE\u0003\u0002p1\ti\tE\u0002<\u0003\u001f#\u0011b\u001d\t!\u0002\u0003\u0005)\u0019\u0001 )\u0007\u0005=U-A\u0005Pa\u0006\u0013(/Y=J\u0013B\u0019\u0011q\u000e\n\u0003\u0013=\u0003\u0018I\u001d:bs&K5c\u0001\n\u0002\u001cB!\u0011q\u000e\u0007})\t\t)*A\u0005Pa\u0006\u0013(/Y=T'B\u0019\u0011qN\u000b\u0003\u0013=\u0003\u0018I\u001d:bsN\u001b6cA\u000b\u0002(B)\u0011q\u000e\u0007\u0002*B\u0019a&a+\n\u0007\u00055vFA\u0003TQ>\u0014H\u000f\u0006\u0002\u0002\"\u0006Iq\n]!se\u0006LH\n\u0014\t\u0004\u0003_B\"!C(q\u0003J\u0014\u0018-\u001f'M'\rA\u0012\u0011\u0018\t\u0006\u0003_b\u00111\u0018\t\u0004]\u0005u\u0016bAA`_\t!Aj\u001c8h)\t\t\u0019,A\u0005Pa\u0006\u0013(/Y=G\rB\u0019\u0011qN\u000e\u0003\u0013=\u0003\u0018I\u001d:bs\u001a35cA\u000e\u0002LB)\u0011q\u000e\u0007\u0002NB\u0019a&a4\n\u0007\u0005EwFA\u0003GY>\fG\u000f\u0006\u0002\u0002F\u0006Iq\n]!se\u0006LH\t\u0012\t\u0004\u0003_r\"!C(q\u0003J\u0014\u0018-\u001f#E'\rq\u0012Q\u001c\t\u0006\u0003_b\u0011q\u001c\t\u0004]\u0005\u0005\u0018bAAr_\t1Ai\\;cY\u0016$\"!a6\u0002\u0013=\u0003\u0018I\u001d:bs\u000e\u001b\u0005cAA8C\tIq\n]!se\u0006L8iQ\n\u0004C\u0005=\b#BA8\u0019\u0005E\b\u0003BAz\u0003sl!!!>\u000b\u0007\u0005]\b&\u0001\u0003nCRD\u0017\u0002BA~\u0003k\u0014qaQ8na2,\u0007\u0010\u0006\u0002\u0002jB\u00191H!\u0001\u0005\u000b\r\u0004!\u0019\u0001 \u0011\u0007m\u0012)\u0001B\u0003t\u0001\t\u0007a\b\u0006\u0003\u0002~\t%\u0001\"B\u001d\u0003\u0001\u0004Q\u0004"
)
public interface CanTraverseKeyValuePairs {
   static OpArray opArray() {
      return CanTraverseKeyValuePairs$.MODULE$.opArray();
   }

   void traverse(final Object from, final KeyValuePairsVisitor fn);

   boolean isTraversableAgain(final Object from);

   public interface KeyValuePairsVisitor {
      void visit(final Object k, final Object a);

      // $FF: synthetic method
      static void visitArray$(final KeyValuePairsVisitor $this, final Function1 indices, final Object arr) {
         $this.visitArray(indices, arr);
      }

      default void visitArray(final Function1 indices, final Object arr) {
         this.visitArray(indices, arr, 0, .MODULE$.array_length(arr), 1);
      }

      // $FF: synthetic method
      static void visitArray$(final KeyValuePairsVisitor $this, final Function1 indices, final Object arr, final int offset, final int length, final int stride) {
         $this.visitArray(indices, arr, offset, length, stride);
      }

      default void visitArray(final Function1 indices, final Object arr, final int offset, final int length, final int stride) {
         for(int i = 0; i < length; ++i) {
            this.visit(indices.apply(BoxesRunTime.boxToInteger(i * stride + offset)), .MODULE$.array_apply(arr, i * stride + offset));
         }

      }

      void zeros(final int numZero, final Iterator zeroKeys, final Object zeroValue);

      // $FF: synthetic method
      static void visit$mcZI$sp$(final KeyValuePairsVisitor $this, final int k, final boolean a) {
         $this.visit$mcZI$sp(k, a);
      }

      default void visit$mcZI$sp(final int k, final boolean a) {
         this.visit(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToBoolean(a));
      }

      // $FF: synthetic method
      static void visit$mcBI$sp$(final KeyValuePairsVisitor $this, final int k, final byte a) {
         $this.visit$mcBI$sp(k, a);
      }

      default void visit$mcBI$sp(final int k, final byte a) {
         this.visit(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToByte(a));
      }

      // $FF: synthetic method
      static void visit$mcCI$sp$(final KeyValuePairsVisitor $this, final int k, final char a) {
         $this.visit$mcCI$sp(k, a);
      }

      default void visit$mcCI$sp(final int k, final char a) {
         this.visit(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToCharacter(a));
      }

      // $FF: synthetic method
      static void visit$mcDI$sp$(final KeyValuePairsVisitor $this, final int k, final double a) {
         $this.visit$mcDI$sp(k, a);
      }

      default void visit$mcDI$sp(final int k, final double a) {
         this.visit(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToDouble(a));
      }

      // $FF: synthetic method
      static void visit$mcFI$sp$(final KeyValuePairsVisitor $this, final int k, final float a) {
         $this.visit$mcFI$sp(k, a);
      }

      default void visit$mcFI$sp(final int k, final float a) {
         this.visit(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToFloat(a));
      }

      // $FF: synthetic method
      static void visit$mcII$sp$(final KeyValuePairsVisitor $this, final int k, final int a) {
         $this.visit$mcII$sp(k, a);
      }

      default void visit$mcII$sp(final int k, final int a) {
         this.visit(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToInteger(a));
      }

      // $FF: synthetic method
      static void visit$mcJI$sp$(final KeyValuePairsVisitor $this, final int k, final long a) {
         $this.visit$mcJI$sp(k, a);
      }

      default void visit$mcJI$sp(final int k, final long a) {
         this.visit(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToLong(a));
      }

      // $FF: synthetic method
      static void visit$mcSI$sp$(final KeyValuePairsVisitor $this, final int k, final short a) {
         $this.visit$mcSI$sp(k, a);
      }

      default void visit$mcSI$sp(final int k, final short a) {
         this.visit(BoxesRunTime.boxToInteger(k), BoxesRunTime.boxToShort(a));
      }

      // $FF: synthetic method
      static void visit$mcVI$sp$(final KeyValuePairsVisitor $this, final int k, final BoxedUnit a) {
         $this.visit$mcVI$sp(k, a);
      }

      default void visit$mcVI$sp(final int k, final BoxedUnit a) {
         this.visit(BoxesRunTime.boxToInteger(k), a);
      }

      // $FF: synthetic method
      static void visitArray$mcZI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final boolean[] arr) {
         $this.visitArray$mcZI$sp(indices, arr);
      }

      default void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr) {
         this.visitArray(indices, arr);
      }

      // $FF: synthetic method
      static void visitArray$mcBI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final byte[] arr) {
         $this.visitArray$mcBI$sp(indices, arr);
      }

      default void visitArray$mcBI$sp(final Function1 indices, final byte[] arr) {
         this.visitArray(indices, arr);
      }

      // $FF: synthetic method
      static void visitArray$mcCI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final char[] arr) {
         $this.visitArray$mcCI$sp(indices, arr);
      }

      default void visitArray$mcCI$sp(final Function1 indices, final char[] arr) {
         this.visitArray(indices, arr);
      }

      // $FF: synthetic method
      static void visitArray$mcDI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final double[] arr) {
         $this.visitArray$mcDI$sp(indices, arr);
      }

      default void visitArray$mcDI$sp(final Function1 indices, final double[] arr) {
         this.visitArray(indices, arr);
      }

      // $FF: synthetic method
      static void visitArray$mcFI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final float[] arr) {
         $this.visitArray$mcFI$sp(indices, arr);
      }

      default void visitArray$mcFI$sp(final Function1 indices, final float[] arr) {
         this.visitArray(indices, arr);
      }

      // $FF: synthetic method
      static void visitArray$mcII$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final int[] arr) {
         $this.visitArray$mcII$sp(indices, arr);
      }

      default void visitArray$mcII$sp(final Function1 indices, final int[] arr) {
         this.visitArray(indices, arr);
      }

      // $FF: synthetic method
      static void visitArray$mcJI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final long[] arr) {
         $this.visitArray$mcJI$sp(indices, arr);
      }

      default void visitArray$mcJI$sp(final Function1 indices, final long[] arr) {
         this.visitArray(indices, arr);
      }

      // $FF: synthetic method
      static void visitArray$mcSI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final short[] arr) {
         $this.visitArray$mcSI$sp(indices, arr);
      }

      default void visitArray$mcSI$sp(final Function1 indices, final short[] arr) {
         this.visitArray(indices, arr);
      }

      // $FF: synthetic method
      static void visitArray$mcVI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final BoxedUnit[] arr) {
         $this.visitArray$mcVI$sp(indices, arr);
      }

      default void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr) {
         this.visitArray(indices, arr);
      }

      // $FF: synthetic method
      static void visitArray$mcZI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
         $this.visitArray$mcZI$sp(indices, arr, offset, length, stride);
      }

      default void visitArray$mcZI$sp(final Function1 indices, final boolean[] arr, final int offset, final int length, final int stride) {
         this.visitArray(indices, arr, offset, length, stride);
      }

      // $FF: synthetic method
      static void visitArray$mcBI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
         $this.visitArray$mcBI$sp(indices, arr, offset, length, stride);
      }

      default void visitArray$mcBI$sp(final Function1 indices, final byte[] arr, final int offset, final int length, final int stride) {
         this.visitArray(indices, arr, offset, length, stride);
      }

      // $FF: synthetic method
      static void visitArray$mcCI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
         $this.visitArray$mcCI$sp(indices, arr, offset, length, stride);
      }

      default void visitArray$mcCI$sp(final Function1 indices, final char[] arr, final int offset, final int length, final int stride) {
         this.visitArray(indices, arr, offset, length, stride);
      }

      // $FF: synthetic method
      static void visitArray$mcDI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
         $this.visitArray$mcDI$sp(indices, arr, offset, length, stride);
      }

      default void visitArray$mcDI$sp(final Function1 indices, final double[] arr, final int offset, final int length, final int stride) {
         this.visitArray(indices, arr, offset, length, stride);
      }

      // $FF: synthetic method
      static void visitArray$mcFI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
         $this.visitArray$mcFI$sp(indices, arr, offset, length, stride);
      }

      default void visitArray$mcFI$sp(final Function1 indices, final float[] arr, final int offset, final int length, final int stride) {
         this.visitArray(indices, arr, offset, length, stride);
      }

      // $FF: synthetic method
      static void visitArray$mcII$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
         $this.visitArray$mcII$sp(indices, arr, offset, length, stride);
      }

      default void visitArray$mcII$sp(final Function1 indices, final int[] arr, final int offset, final int length, final int stride) {
         this.visitArray(indices, arr, offset, length, stride);
      }

      // $FF: synthetic method
      static void visitArray$mcJI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
         $this.visitArray$mcJI$sp(indices, arr, offset, length, stride);
      }

      default void visitArray$mcJI$sp(final Function1 indices, final long[] arr, final int offset, final int length, final int stride) {
         this.visitArray(indices, arr, offset, length, stride);
      }

      // $FF: synthetic method
      static void visitArray$mcSI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
         $this.visitArray$mcSI$sp(indices, arr, offset, length, stride);
      }

      default void visitArray$mcSI$sp(final Function1 indices, final short[] arr, final int offset, final int length, final int stride) {
         this.visitArray(indices, arr, offset, length, stride);
      }

      // $FF: synthetic method
      static void visitArray$mcVI$sp$(final KeyValuePairsVisitor $this, final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
         $this.visitArray$mcVI$sp(indices, arr, offset, length, stride);
      }

      default void visitArray$mcVI$sp(final Function1 indices, final BoxedUnit[] arr, final int offset, final int length, final int stride) {
         this.visitArray(indices, arr, offset, length, stride);
      }

      // $FF: synthetic method
      static void zeros$mcZ$sp$(final KeyValuePairsVisitor $this, final int numZero, final Iterator zeroKeys, final boolean zeroValue) {
         $this.zeros$mcZ$sp(numZero, zeroKeys, zeroValue);
      }

      default void zeros$mcZ$sp(final int numZero, final Iterator zeroKeys, final boolean zeroValue) {
         this.zeros(numZero, zeroKeys, BoxesRunTime.boxToBoolean(zeroValue));
      }

      // $FF: synthetic method
      static void zeros$mcB$sp$(final KeyValuePairsVisitor $this, final int numZero, final Iterator zeroKeys, final byte zeroValue) {
         $this.zeros$mcB$sp(numZero, zeroKeys, zeroValue);
      }

      default void zeros$mcB$sp(final int numZero, final Iterator zeroKeys, final byte zeroValue) {
         this.zeros(numZero, zeroKeys, BoxesRunTime.boxToByte(zeroValue));
      }

      // $FF: synthetic method
      static void zeros$mcC$sp$(final KeyValuePairsVisitor $this, final int numZero, final Iterator zeroKeys, final char zeroValue) {
         $this.zeros$mcC$sp(numZero, zeroKeys, zeroValue);
      }

      default void zeros$mcC$sp(final int numZero, final Iterator zeroKeys, final char zeroValue) {
         this.zeros(numZero, zeroKeys, BoxesRunTime.boxToCharacter(zeroValue));
      }

      // $FF: synthetic method
      static void zeros$mcD$sp$(final KeyValuePairsVisitor $this, final int numZero, final Iterator zeroKeys, final double zeroValue) {
         $this.zeros$mcD$sp(numZero, zeroKeys, zeroValue);
      }

      default void zeros$mcD$sp(final int numZero, final Iterator zeroKeys, final double zeroValue) {
         this.zeros(numZero, zeroKeys, BoxesRunTime.boxToDouble(zeroValue));
      }

      // $FF: synthetic method
      static void zeros$mcF$sp$(final KeyValuePairsVisitor $this, final int numZero, final Iterator zeroKeys, final float zeroValue) {
         $this.zeros$mcF$sp(numZero, zeroKeys, zeroValue);
      }

      default void zeros$mcF$sp(final int numZero, final Iterator zeroKeys, final float zeroValue) {
         this.zeros(numZero, zeroKeys, BoxesRunTime.boxToFloat(zeroValue));
      }

      // $FF: synthetic method
      static void zeros$mcI$sp$(final KeyValuePairsVisitor $this, final int numZero, final Iterator zeroKeys, final int zeroValue) {
         $this.zeros$mcI$sp(numZero, zeroKeys, zeroValue);
      }

      default void zeros$mcI$sp(final int numZero, final Iterator zeroKeys, final int zeroValue) {
         this.zeros(numZero, zeroKeys, BoxesRunTime.boxToInteger(zeroValue));
      }

      // $FF: synthetic method
      static void zeros$mcJ$sp$(final KeyValuePairsVisitor $this, final int numZero, final Iterator zeroKeys, final long zeroValue) {
         $this.zeros$mcJ$sp(numZero, zeroKeys, zeroValue);
      }

      default void zeros$mcJ$sp(final int numZero, final Iterator zeroKeys, final long zeroValue) {
         this.zeros(numZero, zeroKeys, BoxesRunTime.boxToLong(zeroValue));
      }

      // $FF: synthetic method
      static void zeros$mcS$sp$(final KeyValuePairsVisitor $this, final int numZero, final Iterator zeroKeys, final short zeroValue) {
         $this.zeros$mcS$sp(numZero, zeroKeys, zeroValue);
      }

      default void zeros$mcS$sp(final int numZero, final Iterator zeroKeys, final short zeroValue) {
         this.zeros(numZero, zeroKeys, BoxesRunTime.boxToShort(zeroValue));
      }

      // $FF: synthetic method
      static void zeros$mcV$sp$(final KeyValuePairsVisitor $this, final int numZero, final Iterator zeroKeys, final BoxedUnit zeroValue) {
         $this.zeros$mcV$sp(numZero, zeroKeys, zeroValue);
      }

      default void zeros$mcV$sp(final int numZero, final Iterator zeroKeys, final BoxedUnit zeroValue) {
         this.zeros(numZero, zeroKeys, zeroValue);
      }

      static void $init$(final KeyValuePairsVisitor $this) {
      }
   }

   public static class OpArray implements CanTraverseKeyValuePairs {
      public void traverse(final Object from, final KeyValuePairsVisitor fn) {
         fn.visitArray(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), .MODULE$.array_length(from)), from);
      }

      public boolean isTraversableAgain(final Object from) {
         return true;
      }

      public void traverse$mcD$sp(final double[] from, final KeyValuePairsVisitor fn) {
         this.traverse(from, fn);
      }

      public void traverse$mcF$sp(final float[] from, final KeyValuePairsVisitor fn) {
         this.traverse(from, fn);
      }

      public void traverse$mcI$sp(final int[] from, final KeyValuePairsVisitor fn) {
         this.traverse(from, fn);
      }

      public void traverse$mcJ$sp(final long[] from, final KeyValuePairsVisitor fn) {
         this.traverse(from, fn);
      }

      public boolean isTraversableAgain$mcD$sp(final double[] from) {
         return this.isTraversableAgain(from);
      }

      public boolean isTraversableAgain$mcF$sp(final float[] from) {
         return this.isTraversableAgain(from);
      }

      public boolean isTraversableAgain$mcI$sp(final int[] from) {
         return this.isTraversableAgain(from);
      }

      public boolean isTraversableAgain$mcJ$sp(final long[] from) {
         return this.isTraversableAgain(from);
      }
   }

   public static class OpArrayII$ extends CanTraverseKeyValuePairs$OpArray$mcI$sp {
      public static final OpArrayII$ MODULE$ = new OpArrayII$();
   }

   public static class OpArraySS$ extends OpArray {
      public static final OpArraySS$ MODULE$ = new OpArraySS$();
   }

   public static class OpArrayLL$ extends CanTraverseKeyValuePairs$OpArray$mcJ$sp {
      public static final OpArrayLL$ MODULE$ = new OpArrayLL$();
   }

   public static class OpArrayFF$ extends CanTraverseKeyValuePairs$OpArray$mcF$sp {
      public static final OpArrayFF$ MODULE$ = new OpArrayFF$();
   }

   public static class OpArrayDD$ extends CanTraverseKeyValuePairs$OpArray$mcD$sp {
      public static final OpArrayDD$ MODULE$ = new OpArrayDD$();
   }

   public static class OpArrayCC$ extends OpArray {
      public static final OpArrayCC$ MODULE$ = new OpArrayCC$();
   }
}
