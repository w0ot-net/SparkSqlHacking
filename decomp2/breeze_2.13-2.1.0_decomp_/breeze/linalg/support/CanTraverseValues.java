package breeze.linalg.support;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt.;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005UgaB\u0013'!\u0003\r\t!\f\u0005\u0006k\u0001!\tA\u000e\u0005\u0006u\u00011\ta\u000f\u0005\b\u0003G\u0001a\u0011AA[\u0011\u001d\tI\f\u0001C\u0001\u0003w;Qa\u0013\u0014\t\u000213Q!\n\u0014\t\u00029CQA\u0015\u0004\u0005\u0002M3q\u0001\u0016\u0004\u0011\u0002\u0007\u0005Q\u000bC\u00036\u0011\u0011\u0005a\u0007C\u0003X\u0011\u0019\u0005\u0001\fC\u0003j\u0011\u0011\u0005!\u000eC\u0003j\u0011\u0011\u0005\u0001\u000fC\u0003|\u0011\u0019\u0005AP\u0002\u0004\u0002\u0004\u0019\u0001\u0011Q\u0001\u0005\u0007%:!\t!!\u0005\t\rirA\u0011AA\f\u0011\u001d\t\u0019C\u0004C\u0001\u0003KAq!a\f\u0007\t\u0007\t\tdB\u0004\u0002>\u0019A\u0019!a\u0010\u0007\u000f\u0005\u0005c\u0001#\u0001\u0002D!1!\u000b\u0006C\u0001\u0003\u000f:q!!\u0013\u0007\u0011\u0007\tYEB\u0004\u0002N\u0019A\t!a\u0014\t\rI;B\u0011AA-\u000f\u001d\tYF\u0002E\u0002\u0003;2q!a\u0018\u0007\u0011\u0003\t\t\u0007\u0003\u0004S5\u0011\u0005\u00111N\u0004\b\u0003[2\u00012AA8\r\u001d\t\tH\u0002E\u0001\u0003gBaAU\u000f\u0005\u0002\u0005utaBA@\r!\r\u0011\u0011\u0011\u0004\b\u0003\u00073\u0001\u0012AAC\u0011\u0019\u0011\u0006\u0005\"\u0001\u0002\u0010\u001e9\u0011\u0011\u0013\u0004\t\u0004\u0005MeaBAK\r!\u0005\u0011q\u0013\u0005\u0007%\u000e\"\t!a*\u0003#\r\u000bg\u000e\u0016:bm\u0016\u00148/\u001a,bYV,7O\u0003\u0002(Q\u000591/\u001e9q_J$(BA\u0015+\u0003\u0019a\u0017N\\1mO*\t1&\u0001\u0004ce\u0016,'0Z\u0002\u0001+\u0015q\u0013\u0011WAV'\t\u0001q\u0006\u0005\u00021g5\t\u0011GC\u00013\u0003\u0015\u00198-\u00197b\u0013\t!\u0014G\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003]\u0002\"\u0001\r\u001d\n\u0005e\n$\u0001B+oSR\f\u0001\u0002\u001e:bm\u0016\u00148/\u001a\u000b\u0005y\u00055fH\u0004\u0002>}1\u0001\u0001\"B \u0003\u0001\u0004\u0001\u0015A\u00014o!\u0011\t\u0005\"!+\u000f\u0005\t+aBA\"K\u001d\t!\u0015J\u0004\u0002F\u00116\taI\u0003\u0002HY\u00051AH]8pizJ\u0011aK\u0005\u0003S)J!a\n\u0015\u0002#\r\u000bg\u000e\u0016:bm\u0016\u00148/\u001a,bYV,7\u000f\u0005\u0002N\r5\taeE\u0002\u0007_=\u0003\"!\u0014)\n\u0005E3#\u0001\u0007'poB\u0013\u0018n\\\"b]R\u0013\u0018M^3sg\u00164\u0016\r\\;fg\u00061A(\u001b8jiz\"\u0012\u0001\u0014\u0002\u000e-\u0006dW/Z:WSNLGo\u001c:\u0016\u0005Yc6C\u0001\u00050\u0003\u00151\u0018n]5u)\t9\u0014\fC\u0003[\u0015\u0001\u00071,A\u0001b!\tiD\fB\u0005^\u0011\u0001\u0006\t\u0011!b\u0001=\n\t\u0011)\u0005\u0002`EB\u0011\u0001\u0007Y\u0005\u0003CF\u0012qAT8uQ&tw\r\u0005\u00021G&\u0011A-\r\u0002\u0004\u0003:L\bF\u0001/g!\t\u0001t-\u0003\u0002ic\tY1\u000f]3dS\u0006d\u0017N_3e\u0003)1\u0018n]5u\u0003J\u0014\u0018-\u001f\u000b\u0003o-DQ\u0001\\\u0006A\u00025\f1!\u0019:s!\r\u0001dnW\u0005\u0003_F\u0012Q!\u0011:sCf$RaN9sofDQ\u0001\u001c\u0007A\u00025DQa\u001d\u0007A\u0002Q\faa\u001c4gg\u0016$\bC\u0001\u0019v\u0013\t1\u0018GA\u0002J]RDQ\u0001\u001f\u0007A\u0002Q\fa\u0001\\3oORD\u0007\"\u0002>\r\u0001\u0004!\u0018AB:ue&$W-A\u0003{KJ|7\u000fF\u00028{~DQA`\u0007A\u0002Q\fqA\\;n5\u0016\u0014x\u000e\u0003\u0004\u0002\u00025\u0001\raW\u0001\nu\u0016\u0014xNV1mk\u0016\u0014qa\u00149BeJ\f\u00170\u0006\u0003\u0002\b\u0005=1\u0003\u0002\b0\u0003\u0013\u0001b!\u0014\u0001\u0002\f\u00055\u0001\u0003\u0002\u0019o\u0003\u001b\u00012!PA\b\t\u0015ifB1\u0001_)\t\t\u0019\u0002E\u0003\u0002\u00169\ti!D\u0001\u0007)\u0019\tI\"a\b\u0002\u001c9\u0019Q(a\u0007\t\r}\u0002\u0002\u0019AA\u000f!\u0015\t)\u0002CA\u0007\u0011\u001d\t\t\u0003\u0005a\u0001\u0003\u0017\tAA\u001a:p[\u0006\u0011\u0012n\u001d+sCZ,'o]1cY\u0016\fu-Y5o)\u0011\t9#!\f\u0011\u0007A\nI#C\u0002\u0002,E\u0012qAQ8pY\u0016\fg\u000eC\u0004\u0002\"E\u0001\r!a\u0003\u0002\u000f=\u0004\u0018I\u001d:bsV!\u00111GA\u001d+\t\t)\u0004E\u0003\u0002\u00169\t9\u0004E\u0002>\u0003s!\u0011\"\u0018\n!\u0002\u0003\u0005)\u0019\u00010)\u0007\u0005eb-A\u0005Pa\u0006\u0013(/Y=J\u0013B\u0019\u0011Q\u0003\u000b\u0003\u0013=\u0003\u0018I\u001d:bs&K5c\u0001\u000b\u0002FA!\u0011Q\u0003\bu)\t\ty$A\u0005Pa\u0006\u0013(/Y=T'B\u0019\u0011QC\f\u0003\u0013=\u0003\u0018I\u001d:bsN\u001b6cA\f\u0002RA)\u0011Q\u0003\b\u0002TA\u0019\u0001'!\u0016\n\u0007\u0005]\u0013GA\u0003TQ>\u0014H\u000f\u0006\u0002\u0002L\u0005Iq\n]!se\u0006LH\n\u0014\t\u0004\u0003+Q\"!C(q\u0003J\u0014\u0018-\u001f'M'\rQ\u00121\r\t\u0006\u0003+q\u0011Q\r\t\u0004a\u0005\u001d\u0014bAA5c\t!Aj\u001c8h)\t\ti&A\u0005Pa\u0006\u0013(/Y=G\rB\u0019\u0011QC\u000f\u0003\u0013=\u0003\u0018I\u001d:bs\u001a35cA\u000f\u0002vA)\u0011Q\u0003\b\u0002xA\u0019\u0001'!\u001f\n\u0007\u0005m\u0014GA\u0003GY>\fG\u000f\u0006\u0002\u0002p\u0005Iq\n]!se\u0006LH\t\u0012\t\u0004\u0003+\u0001#!C(q\u0003J\u0014\u0018-\u001f#E'\r\u0001\u0013q\u0011\t\u0006\u0003+q\u0011\u0011\u0012\t\u0004a\u0005-\u0015bAAGc\t1Ai\\;cY\u0016$\"!!!\u0002\u0013=\u0003\u0018I\u001d:bs\u000e\u001b\u0005cAA\u000bG\tIq\n]!se\u0006L8iQ\n\u0004G\u0005e\u0005#BA\u000b\u001d\u0005m\u0005\u0003BAO\u0003Gk!!a(\u000b\u0007\u0005\u0005&&\u0001\u0003nCRD\u0017\u0002BAS\u0003?\u0013qaQ8na2,\u0007\u0010\u0006\u0002\u0002\u0014B\u0019Q(a+\u0005\u000bu\u0003!\u0019\u00010\t\u000f\u0005\u0005\"\u00011\u0001\u00020B\u0019Q(!-\u0005\r\u0005M\u0006A1\u0001_\u0005\u00111%o\\7\u0015\t\u0005\u001d\u0012q\u0017\u0005\b\u0003C\u0019\u0001\u0019AAX\u0003!1w\u000e\u001c3MK\u001a$X\u0003BA_\u0003\u0007$b!a0\u0002P\u0006EG\u0003BAa\u0003\u000f\u00042!PAb\t\u0019\t)\r\u0002b\u0001=\n\t!\t\u0003\u0004@\t\u0001\u0007\u0011\u0011\u001a\t\na\u0005-\u0017\u0011YAU\u0003\u0003L1!!42\u0005%1UO\\2uS>t'\u0007C\u0004\u0002\"\u0011\u0001\r!a,\t\u000f\u0005MG\u00011\u0001\u0002B\u0006\t!\r"
)
public interface CanTraverseValues {
   static OpArray opArray() {
      return CanTraverseValues$.MODULE$.opArray();
   }

   static CanTraverseValues canTraverseIterator() {
      return CanTraverseValues$.MODULE$.canTraverseIterator();
   }

   static CanTraverseValues canTraverseTraversable() {
      return CanTraverseValues$.MODULE$.canTraverseTraversable();
   }

   ValuesVisitor traverse(final Object from, final ValuesVisitor fn);

   boolean isTraversableAgain(final Object from);

   default Object foldLeft(final Object from, final Object b, final Function2 fn) {
      ObjectRef bb = ObjectRef.create(b);
      this.traverse(from, new ValuesVisitor(bb, fn) {
         private final ObjectRef bb$1;
         private final Function2 fn$1;

         public void visit$mcZ$sp(final boolean a) {
            CanTraverseValues.ValuesVisitor.super.visit$mcZ$sp(a);
         }

         public void visit$mcB$sp(final byte a) {
            CanTraverseValues.ValuesVisitor.super.visit$mcB$sp(a);
         }

         public void visit$mcC$sp(final char a) {
            CanTraverseValues.ValuesVisitor.super.visit$mcC$sp(a);
         }

         public void visit$mcD$sp(final double a) {
            CanTraverseValues.ValuesVisitor.super.visit$mcD$sp(a);
         }

         public void visit$mcF$sp(final float a) {
            CanTraverseValues.ValuesVisitor.super.visit$mcF$sp(a);
         }

         public void visit$mcI$sp(final int a) {
            CanTraverseValues.ValuesVisitor.super.visit$mcI$sp(a);
         }

         public void visit$mcJ$sp(final long a) {
            CanTraverseValues.ValuesVisitor.super.visit$mcJ$sp(a);
         }

         public void visit$mcS$sp(final short a) {
            CanTraverseValues.ValuesVisitor.super.visit$mcS$sp(a);
         }

         public void visit$mcV$sp(final BoxedUnit a) {
            CanTraverseValues.ValuesVisitor.super.visit$mcV$sp(a);
         }

         public void visitArray(final Object arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray(arr);
         }

         public void visitArray$mcZ$sp(final boolean[] arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcZ$sp(arr);
         }

         public void visitArray$mcB$sp(final byte[] arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcB$sp(arr);
         }

         public void visitArray$mcC$sp(final char[] arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcC$sp(arr);
         }

         public void visitArray$mcD$sp(final double[] arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcD$sp(arr);
         }

         public void visitArray$mcF$sp(final float[] arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcF$sp(arr);
         }

         public void visitArray$mcI$sp(final int[] arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcI$sp(arr);
         }

         public void visitArray$mcJ$sp(final long[] arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcJ$sp(arr);
         }

         public void visitArray$mcS$sp(final short[] arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcS$sp(arr);
         }

         public void visitArray$mcV$sp(final BoxedUnit[] arr) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcV$sp(arr);
         }

         public void visitArray(final Object arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray(arr, offset, length, stride);
         }

         public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcZ$sp(arr, offset, length, stride);
         }

         public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcB$sp(arr, offset, length, stride);
         }

         public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcC$sp(arr, offset, length, stride);
         }

         public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcD$sp(arr, offset, length, stride);
         }

         public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcF$sp(arr, offset, length, stride);
         }

         public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcI$sp(arr, offset, length, stride);
         }

         public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcJ$sp(arr, offset, length, stride);
         }

         public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcS$sp(arr, offset, length, stride);
         }

         public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
            CanTraverseValues.ValuesVisitor.super.visitArray$mcV$sp(arr, offset, length, stride);
         }

         public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
            CanTraverseValues.ValuesVisitor.super.zeros$mcZ$sp(numZero, zeroValue);
         }

         public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
            CanTraverseValues.ValuesVisitor.super.zeros$mcB$sp(numZero, zeroValue);
         }

         public void zeros$mcC$sp(final int numZero, final char zeroValue) {
            CanTraverseValues.ValuesVisitor.super.zeros$mcC$sp(numZero, zeroValue);
         }

         public void zeros$mcD$sp(final int numZero, final double zeroValue) {
            CanTraverseValues.ValuesVisitor.super.zeros$mcD$sp(numZero, zeroValue);
         }

         public void zeros$mcF$sp(final int numZero, final float zeroValue) {
            CanTraverseValues.ValuesVisitor.super.zeros$mcF$sp(numZero, zeroValue);
         }

         public void zeros$mcI$sp(final int numZero, final int zeroValue) {
            CanTraverseValues.ValuesVisitor.super.zeros$mcI$sp(numZero, zeroValue);
         }

         public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
            CanTraverseValues.ValuesVisitor.super.zeros$mcJ$sp(numZero, zeroValue);
         }

         public void zeros$mcS$sp(final int numZero, final short zeroValue) {
            CanTraverseValues.ValuesVisitor.super.zeros$mcS$sp(numZero, zeroValue);
         }

         public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
            CanTraverseValues.ValuesVisitor.super.zeros$mcV$sp(numZero, zeroValue);
         }

         public void visit(final Object a) {
            this.bb$1.elem = this.fn$1.apply(this.bb$1.elem, a);
         }

         public void zeros(final int numZero, final Object zeroValue) {
            .MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numZero).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> this.bb$1.elem = this.fn$1.apply(this.bb$1.elem, zeroValue));
         }

         public {
            this.bb$1 = bb$1;
            this.fn$1 = fn$1;
            CanTraverseValues.ValuesVisitor.$init$(this);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      return bb.elem;
   }

   static void $init$(final CanTraverseValues $this) {
   }

   public interface ValuesVisitor {
      void visit(final Object a);

      default void visitArray(final Object arr) {
         this.visitArray(arr, 0, scala.runtime.ScalaRunTime..MODULE$.array_length(arr), 1);
      }

      default void visitArray(final Object arr, final int offset, final int length, final int stride) {
         if (stride == 1) {
            int index$macro$2 = offset;

            for(int limit$macro$4 = length + offset; index$macro$2 < limit$macro$4; ++index$macro$2) {
               this.visit(scala.runtime.ScalaRunTime..MODULE$.array_apply(arr, index$macro$2));
            }
         } else {
            int index$macro$7 = 0;

            for(int limit$macro$9 = length; index$macro$7 < limit$macro$9; ++index$macro$7) {
               this.visit(scala.runtime.ScalaRunTime..MODULE$.array_apply(arr, index$macro$7 * stride + offset));
            }
         }

      }

      void zeros(final int numZero, final Object zeroValue);

      default void visit$mcZ$sp(final boolean a) {
         this.visit(BoxesRunTime.boxToBoolean(a));
      }

      default void visit$mcB$sp(final byte a) {
         this.visit(BoxesRunTime.boxToByte(a));
      }

      default void visit$mcC$sp(final char a) {
         this.visit(BoxesRunTime.boxToCharacter(a));
      }

      default void visit$mcD$sp(final double a) {
         this.visit(BoxesRunTime.boxToDouble(a));
      }

      default void visit$mcF$sp(final float a) {
         this.visit(BoxesRunTime.boxToFloat(a));
      }

      default void visit$mcI$sp(final int a) {
         this.visit(BoxesRunTime.boxToInteger(a));
      }

      default void visit$mcJ$sp(final long a) {
         this.visit(BoxesRunTime.boxToLong(a));
      }

      default void visit$mcS$sp(final short a) {
         this.visit(BoxesRunTime.boxToShort(a));
      }

      default void visit$mcV$sp(final BoxedUnit a) {
         this.visit(a);
      }

      default void visitArray$mcZ$sp(final boolean[] arr) {
         this.visitArray(arr);
      }

      default void visitArray$mcB$sp(final byte[] arr) {
         this.visitArray(arr);
      }

      default void visitArray$mcC$sp(final char[] arr) {
         this.visitArray(arr);
      }

      default void visitArray$mcD$sp(final double[] arr) {
         this.visitArray(arr);
      }

      default void visitArray$mcF$sp(final float[] arr) {
         this.visitArray(arr);
      }

      default void visitArray$mcI$sp(final int[] arr) {
         this.visitArray(arr);
      }

      default void visitArray$mcJ$sp(final long[] arr) {
         this.visitArray(arr);
      }

      default void visitArray$mcS$sp(final short[] arr) {
         this.visitArray(arr);
      }

      default void visitArray$mcV$sp(final BoxedUnit[] arr) {
         this.visitArray(arr);
      }

      default void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
         this.visitArray(arr, offset, length, stride);
      }

      default void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
         this.visitArray(arr, offset, length, stride);
      }

      default void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
         this.visitArray(arr, offset, length, stride);
      }

      default void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
         this.visitArray(arr, offset, length, stride);
      }

      default void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
         this.visitArray(arr, offset, length, stride);
      }

      default void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
         this.visitArray(arr, offset, length, stride);
      }

      default void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
         this.visitArray(arr, offset, length, stride);
      }

      default void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
         this.visitArray(arr, offset, length, stride);
      }

      default void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
         this.visitArray(arr, offset, length, stride);
      }

      default void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
         this.zeros(numZero, BoxesRunTime.boxToBoolean(zeroValue));
      }

      default void zeros$mcB$sp(final int numZero, final byte zeroValue) {
         this.zeros(numZero, BoxesRunTime.boxToByte(zeroValue));
      }

      default void zeros$mcC$sp(final int numZero, final char zeroValue) {
         this.zeros(numZero, BoxesRunTime.boxToCharacter(zeroValue));
      }

      default void zeros$mcD$sp(final int numZero, final double zeroValue) {
         this.zeros(numZero, BoxesRunTime.boxToDouble(zeroValue));
      }

      default void zeros$mcF$sp(final int numZero, final float zeroValue) {
         this.zeros(numZero, BoxesRunTime.boxToFloat(zeroValue));
      }

      default void zeros$mcI$sp(final int numZero, final int zeroValue) {
         this.zeros(numZero, BoxesRunTime.boxToInteger(zeroValue));
      }

      default void zeros$mcJ$sp(final int numZero, final long zeroValue) {
         this.zeros(numZero, BoxesRunTime.boxToLong(zeroValue));
      }

      default void zeros$mcS$sp(final int numZero, final short zeroValue) {
         this.zeros(numZero, BoxesRunTime.boxToShort(zeroValue));
      }

      default void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
         this.zeros(numZero, zeroValue);
      }

      static void $init$(final ValuesVisitor $this) {
      }
   }

   public static class OpArray implements CanTraverseValues {
      public Object foldLeft(final Object from, final Object b, final Function2 fn) {
         return CanTraverseValues.super.foldLeft(from, b, fn);
      }

      public ValuesVisitor traverse(final Object from, final ValuesVisitor fn) {
         fn.visitArray(from);
         return fn;
      }

      public boolean isTraversableAgain(final Object from) {
         return true;
      }

      public OpArray() {
         CanTraverseValues.$init$(this);
      }
   }

   public static class OpArrayII$ extends OpArray {
      public static final OpArrayII$ MODULE$ = new OpArrayII$();
   }

   public static class OpArraySS$ extends OpArray {
      public static final OpArraySS$ MODULE$ = new OpArraySS$();
   }

   public static class OpArrayLL$ extends OpArray {
      public static final OpArrayLL$ MODULE$ = new OpArrayLL$();
   }

   public static class OpArrayFF$ extends OpArray {
      public static final OpArrayFF$ MODULE$ = new OpArrayFF$();
   }

   public static class OpArrayDD$ extends OpArray {
      public static final OpArrayDD$ MODULE$ = new OpArrayDD$();
   }

   public static class OpArrayCC$ extends OpArray {
      public static final OpArrayCC$ MODULE$ = new OpArrayCC$();
   }
}
