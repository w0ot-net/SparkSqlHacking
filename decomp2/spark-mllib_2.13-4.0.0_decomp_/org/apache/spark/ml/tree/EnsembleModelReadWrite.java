package org.apache.spark.ml.tree;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.Params;
import org.apache.spark.sql.SparkSession;
import org.json4s.JObject;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t%sA\u0002\u0010 \u0011\u0003\t\u0013F\u0002\u0004,?!\u0005\u0011\u0005\f\u0005\u0006g\u0005!\t!\u000e\u0005\u0006m\u0005!\ta\u000e\u0005\u0006o\u0006!\t\u0001\u001f\u0004\u0007\u0003\u000b\n\u0001)a\u0012\t\u0015\u0005\u0005TA!f\u0001\n\u0003\t\u0019\u0007\u0003\u0006\u0002l\u0015\u0011\t\u0012)A\u0005\u0003KB!\"!\u001c\u0006\u0005+\u0007I\u0011AA8\u0011)\t\u0019)\u0002B\tB\u0003%\u0011\u0011\u000f\u0005\u0007g\u0015!\t!!\"\t\u0013\u0005=U!!A\u0005\u0002\u0005E\u0005\"CAL\u000bE\u0005I\u0011AAM\u0011%\ty+BI\u0001\n\u0003\t\t\fC\u0005\u00026\u0016\t\t\u0011\"\u0011\u00028\"I\u0011qY\u0003\u0002\u0002\u0013\u0005\u00111\r\u0005\n\u0003\u0013,\u0011\u0011!C\u0001\u0003\u0017D\u0011\"a6\u0006\u0003\u0003%\t%!7\t\u0013\u0005\u001dX!!A\u0005\u0002\u0005%\b\"CAz\u000b\u0005\u0005I\u0011IA{\u0011%\tI0BA\u0001\n\u0003\nY\u0010C\u0005\u0002~\u0016\t\t\u0011\"\u0011\u0002\u0000\"I!\u0011A\u0003\u0002\u0002\u0013\u0005#1A\u0004\b\u0005\u000f\t\u0001\u0012\u0001B\u0005\r\u001d\t)%\u0001E\u0001\u0005\u0017Aaa\r\r\u0005\u0002\t]\u0001b\u0002B\r1\u0011\u0005!1\u0004\u0005\n\u0005OA\u0012\u0011!CA\u0005SA\u0011Ba\f\u0019\u0003\u0003%\tI!\r\t\u0013\t}\u0002$!A\u0005\n\t\u0005\u0013AF#og\u0016l'\r\\3N_\u0012,GNU3bI^\u0013\u0018\u000e^3\u000b\u0005\u0001\n\u0013\u0001\u0002;sK\u0016T!AI\u0012\u0002\u00055d'B\u0001\u0013&\u0003\u0015\u0019\b/\u0019:l\u0015\t1s%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002Q\u0005\u0019qN]4\u0011\u0005)\nQ\"A\u0010\u0003-\u0015s7/Z7cY\u0016lu\u000eZ3m%\u0016\fGm\u0016:ji\u0016\u001c\"!A\u0017\u0011\u00059\nT\"A\u0018\u000b\u0003A\nQa]2bY\u0006L!AM\u0018\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u0015\u0002\u0011M\fg/Z%na2,\"\u0001\u000f!\u0015\u000beb$lZ8\u0011\u00059R\u0014BA\u001e0\u0005\u0011)f.\u001b;\t\u000bu\u001a\u0001\u0019\u0001 \u0002\u0011%t7\u000f^1oG\u0016\u0004\"a\u0010!\r\u0001\u0011)\u0011i\u0001b\u0001\u0005\n\tQ*\u0005\u0002D\rB\u0011a\u0006R\u0005\u0003\u000b>\u0012qAT8uQ&twME\u0002H\u0013>3A\u0001S\u0001\u0001\r\naAH]3gS:,W.\u001a8u}A\u0011!*T\u0007\u0002\u0017*\u0011A*I\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003\u001d.\u0013a\u0001U1sC6\u001c\bG\u0001)U!\rQ\u0013kU\u0005\u0003%~\u0011\u0011\u0003\u0016:fK\u0016s7/Z7cY\u0016lu\u000eZ3m!\tyD\u000bB\u0005V\u0001\u0006\u0005\t\u0011!B\u0001-\n\u0019q\fJ\u0019\u0012\u0005\r;\u0006C\u0001\u0016Y\u0013\tIvDA\tEK\u000eL7/[8o)J,W-T8eK2DQaW\u0002A\u0002q\u000bA\u0001]1uQB\u0011Q\f\u001a\b\u0003=\n\u0004\"aX\u0018\u000e\u0003\u0001T!!\u0019\u001b\u0002\rq\u0012xn\u001c;?\u0013\t\u0019w&\u0001\u0004Qe\u0016$WMZ\u0005\u0003K\u001a\u0014aa\u0015;sS:<'BA20\u0011\u0015A7\u00011\u0001j\u00031\u0019\b/\u0019:l'\u0016\u001c8/[8o!\tQW.D\u0001l\u0015\ta7%A\u0002tc2L!A\\6\u0003\u0019M\u0003\u0018M]6TKN\u001c\u0018n\u001c8\t\u000bA\u001c\u0001\u0019A9\u0002\u001b\u0015DHO]1NKR\fG-\u0019;b!\t\u0011X/D\u0001t\u0015\t!x%\u0001\u0004kg>tGg]\u0005\u0003mN\u0014qAS(cU\u0016\u001cG/\u0001\u0005m_\u0006$\u0017*\u001c9m)%I\u0018\u0011HA\u001e\u0003{\t\t\u0005E\u0004/ur\fy\"!\r\n\u0005m|#A\u0002+va2,7\u0007E\u0002~\u00033q1A`A\n\u001d\ry\u0018q\u0002\b\u0005\u0003\u0003\tiA\u0004\u0003\u0002\u0004\u0005-a\u0002BA\u0003\u0003\u0013q1aXA\u0004\u0013\u0005A\u0013B\u0001\u0014(\u0013\t!S%\u0003\u0002#G%\u0019\u0011\u0011C\u0011\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003+\t9\"A\nEK\u001a\fW\u000f\u001c;QCJ\fWn\u001d*fC\u0012,'OC\u0002\u0002\u0012\u0005JA!a\u0007\u0002\u001e\tAQ*\u001a;bI\u0006$\u0018M\u0003\u0003\u0002\u0016\u0005]\u0001#\u0002\u0018\u0002\"\u0005\u0015\u0012bAA\u0012_\t)\u0011I\u001d:bsB1a&a\n}\u0003WI1!!\u000b0\u0005\u0019!V\u000f\u001d7feA\u0019!&!\f\n\u0007\u0005=rD\u0001\u0003O_\u0012,\u0007#\u0002\u0018\u0002\"\u0005M\u0002c\u0001\u0018\u00026%\u0019\u0011qG\u0018\u0003\r\u0011{WO\u00197f\u0011\u0015YF\u00011\u0001]\u0011\u0015AG\u00011\u0001j\u0011\u0019\ty\u0004\u0002a\u00019\u0006I1\r\\1tg:\u000bW.\u001a\u0005\u0007\u0003\u0007\"\u0001\u0019\u0001/\u0002\u001bQ\u0014X-Z\"mCN\u001ch*Y7f\u0005A)en]3nE2,gj\u001c3f\t\u0006$\u0018m\u0005\u0004\u0006[\u0005%\u0013q\n\t\u0004]\u0005-\u0013bAA'_\t9\u0001K]8ek\u000e$\b\u0003BA)\u00037rA!a\u0015\u0002X9\u0019q,!\u0016\n\u0003AJ1!!\u00170\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0018\u0002`\ta1+\u001a:jC2L'0\u00192mK*\u0019\u0011\u0011L\u0018\u0002\rQ\u0014X-Z%E+\t\t)\u0007E\u0002/\u0003OJ1!!\u001b0\u0005\rIe\u000e^\u0001\biJ,W-\u0013#!\u0003!qw\u000eZ3ECR\fWCAA9!\u0011\t\u0019(! \u000f\t\u0005U\u0014\u0011\u0010\b\u0004\u007f\u0006]\u0014B\u0001\u0011\"\u0013\r\tYhH\u0001\u001b\t\u0016\u001c\u0017n]5p]R\u0013X-Z'pI\u0016d'+Z1e/JLG/Z\u0005\u0005\u0003\u007f\n\tI\u0001\u0005O_\u0012,G)\u0019;b\u0015\r\tYhH\u0001\n]>$W\rR1uC\u0002\"b!a\"\u0002\f\u00065\u0005cAAE\u000b5\t\u0011\u0001C\u0004\u0002b)\u0001\r!!\u001a\t\u000f\u00055$\u00021\u0001\u0002r\u0005!1m\u001c9z)\u0019\t9)a%\u0002\u0016\"I\u0011\u0011M\u0006\u0011\u0002\u0003\u0007\u0011Q\r\u0005\n\u0003[Z\u0001\u0013!a\u0001\u0003c\nabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u001c*\"\u0011QMAOW\t\ty\n\u0005\u0003\u0002\"\u0006-VBAAR\u0015\u0011\t)+a*\u0002\u0013Ut7\r[3dW\u0016$'bAAU_\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u00055\u00161\u0015\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003gSC!!\u001d\u0002\u001e\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!/\u0011\t\u0005m\u0016QY\u0007\u0003\u0003{SA!a0\u0002B\u0006!A.\u00198h\u0015\t\t\u0019-\u0001\u0003kCZ\f\u0017bA3\u0002>\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAg\u0003'\u00042ALAh\u0013\r\t\tn\f\u0002\u0004\u0003:L\b\"CAk!\u0005\u0005\t\u0019AA3\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\u001c\t\u0007\u0003;\f\u0019/!4\u000e\u0005\u0005}'bAAq_\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0015\u0018q\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002l\u0006E\bc\u0001\u0018\u0002n&\u0019\u0011q^\u0018\u0003\u000f\t{w\u000e\\3b]\"I\u0011Q\u001b\n\u0002\u0002\u0003\u0007\u0011QZ\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002:\u0006]\b\"CAk'\u0005\u0005\t\u0019AA3\u0003!A\u0017m\u001d5D_\u0012,GCAA3\u0003!!xn\u0015;sS:<GCAA]\u0003\u0019)\u0017/^1mgR!\u00111\u001eB\u0003\u0011%\t)NFA\u0001\u0002\u0004\ti-\u0001\tF]N,WN\u00197f\u001d>$W\rR1uCB\u0019\u0011\u0011\u0012\r\u0014\tai#Q\u0002\t\u0005\u0005\u001f\u0011)\"\u0004\u0002\u0003\u0012)!!1CAa\u0003\tIw.\u0003\u0003\u0002^\tEAC\u0001B\u0005\u0003\u0015\u0011W/\u001b7e)\u0019\u0011iBa\t\u0003&A1\u0011\u0011\u000bB\u0010\u0003\u000fKAA!\t\u0002`\t\u00191+Z9\t\u000b\u0001R\u0002\u0019A,\t\u000f\u0005\u0005$\u00041\u0001\u0002f\u0005)\u0011\r\u001d9msR1\u0011q\u0011B\u0016\u0005[Aq!!\u0019\u001c\u0001\u0004\t)\u0007C\u0004\u0002nm\u0001\r!!\u001d\u0002\u000fUt\u0017\r\u001d9msR!!1\u0007B\u001e!\u0015q#Q\u0007B\u001d\u0013\r\u00119d\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000f9\n9#!\u001a\u0002r!I!Q\b\u000f\u0002\u0002\u0003\u0007\u0011qQ\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B\"!\u0011\tYL!\u0012\n\t\t\u001d\u0013Q\u0018\u0002\u0007\u001f\nTWm\u0019;"
)
public final class EnsembleModelReadWrite {
   public static Tuple3 loadImpl(final String path, final SparkSession sparkSession, final String className, final String treeClassName) {
      return EnsembleModelReadWrite$.MODULE$.loadImpl(path, sparkSession, className, treeClassName);
   }

   public static void saveImpl(final Params instance, final String path, final SparkSession sparkSession, final JObject extraMetadata) {
      EnsembleModelReadWrite$.MODULE$.saveImpl(instance, path, sparkSession, extraMetadata);
   }

   public static class EnsembleNodeData implements Product, Serializable {
      private final int treeID;
      private final DecisionTreeModelReadWrite.NodeData nodeData;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int treeID() {
         return this.treeID;
      }

      public DecisionTreeModelReadWrite.NodeData nodeData() {
         return this.nodeData;
      }

      public EnsembleNodeData copy(final int treeID, final DecisionTreeModelReadWrite.NodeData nodeData) {
         return new EnsembleNodeData(treeID, nodeData);
      }

      public int copy$default$1() {
         return this.treeID();
      }

      public DecisionTreeModelReadWrite.NodeData copy$default$2() {
         return this.nodeData();
      }

      public String productPrefix() {
         return "EnsembleNodeData";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.treeID());
            }
            case 1 -> {
               return this.nodeData();
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
         return x$1 instanceof EnsembleNodeData;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "treeID";
            }
            case 1 -> {
               return "nodeData";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.treeID());
         var1 = Statics.mix(var1, Statics.anyHash(this.nodeData()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label51: {
               if (x$1 instanceof EnsembleNodeData) {
                  EnsembleNodeData var4 = (EnsembleNodeData)x$1;
                  if (this.treeID() == var4.treeID()) {
                     label44: {
                        DecisionTreeModelReadWrite.NodeData var10000 = this.nodeData();
                        DecisionTreeModelReadWrite.NodeData var5 = var4.nodeData();
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

      public EnsembleNodeData(final int treeID, final DecisionTreeModelReadWrite.NodeData nodeData) {
         this.treeID = treeID;
         this.nodeData = nodeData;
         Product.$init$(this);
      }
   }

   public static class EnsembleNodeData$ implements Serializable {
      public static final EnsembleNodeData$ MODULE$ = new EnsembleNodeData$();

      public Seq build(final DecisionTreeModel tree, final int treeID) {
         Tuple2 var5 = DecisionTreeModelReadWrite.NodeData$.MODULE$.build(tree.rootNode(), 0);
         if (var5 != null) {
            Seq nodeData = (Seq)var5._1();
            if (nodeData != null) {
               return (Seq)nodeData.map((nd) -> new EnsembleNodeData(treeID, nd));
            }
         }

         throw new MatchError(var5);
      }

      public EnsembleNodeData apply(final int treeID, final DecisionTreeModelReadWrite.NodeData nodeData) {
         return new EnsembleNodeData(treeID, nodeData);
      }

      public Option unapply(final EnsembleNodeData x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.treeID()), x$0.nodeData())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EnsembleNodeData$.class);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
