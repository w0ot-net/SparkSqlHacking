package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eg!\u0002\u0014(\u0001\u001d\n\u0004\"\u0002$\u0001\t\u00039\u0005bB+\u0001\u0005\u0004%\tA\u0016\u0005\b\u0003/\u0002\u0001\u0015!\u0003X\u0011%\tI\u0006\u0001b\u0001\n\u0013\tY\u0006\u0003\u0005\u0002b\u0001\u0001\u000b\u0011BA/\u0011\u001d\t\u0019\u0007\u0001C\u0001\u0003KB\u0011\"a\u001d\u0001#\u0003%\t!!\u001e\t\u000f\u0005-\u0005\u0001\"\u0001\u0002\u000e\"9\u00111\u0013\u0001\u0005\n\u0005U\u0005bBAN\u0001\u0011\u0005\u0011Q\u0014\u0005\b\u0003c\u0003A\u0011BAZ\u0011\u001d\tI\f\u0001C\u0001\u0003wC\u0011\"a3\u0001#\u0003%\t!!4\b\re;\u0003\u0012A\u0014[\r\u00191s\u0005#\u0001(7\")ai\u0004C\u0001G\u001a!Am\u0004\u0001f\u0011!9\u0017C!b\u0001\n\u0003A\u0007\u0002C7\u0012\u0005\u0003\u0005\u000b\u0011B5\t\u000b\u0019\u000bB\u0011\u00018\t\u0013A\f\u0002\u0019!a\u0001\n\u0003\t\b\"\u0003:\u0012\u0001\u0004\u0005\r\u0011\"\u0001t\u0011%I\u0018\u00031A\u0001B\u0003&1\u000eC\u0004{#\u0001\u0007I\u0011A>\t\u0011}\f\u0002\u0019!C\u0001\u0003\u0003Aq!!\u0002\u0012A\u0003&A\u0010C\u0005\u0002\bE\u0011\r\u0011\"\u0001\u0002\n!A\u00111D\t!\u0002\u0013\tY\u0001C\u0004\u0002\u001eE!\t!a\b\u0007\r\u0005\u001dr\u0002BA\u0015\u0011\u00191e\u0004\"\u0001\u0002.!9!P\ba\u0001\n\u0003Y\b\u0002C@\u001f\u0001\u0004%\t!!\u000e\t\u000f\u0005\u0015a\u0004)Q\u0005y\"I\u0011\u0011\b\u0010C\u0002\u0013\u0005\u00111\b\u0005\t\u0003\u000br\u0002\u0015!\u0003\u0002>!I\u0011qI\b\u0002\u0002\u0013%\u0011\u0011\n\u0002\u0007\rB#&/Z3\u000b\u0005!J\u0013a\u00014q[*\u0011!fK\u0001\u0006[2d\u0017N\u0019\u0006\u0003Y5\nQa\u001d9be.T!AL\u0018\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0014aA8sOV\u0011!\u0007T\n\u0004\u0001MJ\u0004C\u0001\u001b8\u001b\u0005)$\"\u0001\u001c\u0002\u000bM\u001c\u0017\r\\1\n\u0005a*$AB!osJ+g\r\u0005\u0002;\u0007:\u00111(\u0011\b\u0003y\u0001k\u0011!\u0010\u0006\u0003}}\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002m%\u0011!)N\u0001\ba\u0006\u001c7.Y4f\u0013\t!UI\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Ck\u00051A(\u001b8jiz\"\u0012\u0001\u0013\t\u0004\u0013\u0002QU\"A\u0014\u0011\u0005-cE\u0002\u0001\u0003\u0006\u001b\u0002\u0011\rA\u0014\u0002\u0002)F\u0011qJ\u0015\t\u0003iAK!!U\u001b\u0003\u000f9{G\u000f[5oOB\u0011AgU\u0005\u0003)V\u00121!\u00118z\u0003\u0011\u0011xn\u001c;\u0016\u0003]\u00032\u0001W\tK\u001d\tIe\"\u0001\u0004G!R\u0013X-\u001a\t\u0003\u0013>\u00192aD\u001a]!\ti&-D\u0001_\u0015\ty\u0006-\u0001\u0002j_*\t\u0011-\u0001\u0003kCZ\f\u0017B\u0001#_)\u0005Q&\u0001\u0002(pI\u0016,\"A\u001a7\u0014\u0007E\u0019\u0014(\u0001\u0004qCJ,g\u000e^\u000b\u0002SB\u0019!.E6\u000e\u0003=\u0001\"a\u00137\u0005\u000b5\u000b\"\u0019\u0001(\u0002\u000fA\f'/\u001a8uAQ\u0011\u0011n\u001c\u0005\u0006OR\u0001\r![\u0001\u0005SR,W.F\u0001l\u0003!IG/Z7`I\u0015\fHC\u0001;x!\t!T/\u0003\u0002wk\t!QK\\5u\u0011\u001dAh#!AA\u0002-\f1\u0001\u001f\u00132\u0003\u0015IG/Z7!\u0003\u0015\u0019w.\u001e8u+\u0005a\bC\u0001\u001b~\u0013\tqXG\u0001\u0003M_:<\u0017!C2pk:$x\fJ3r)\r!\u00181\u0001\u0005\bqf\t\t\u00111\u0001}\u0003\u0019\u0019w.\u001e8uA\u0005A1\r[5mIJ,g.\u0006\u0002\u0002\fA1\u0011QBA\fW&l!!a\u0004\u000b\t\u0005E\u00111C\u0001\b[V$\u0018M\u00197f\u0015\r\t)\"N\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\r\u0003\u001f\u00111!T1q\u0003%\u0019\u0007.\u001b7ee\u0016t\u0007%\u0001\u0004jgJ{w\u000e^\u000b\u0003\u0003C\u00012\u0001NA\u0012\u0013\r\t)#\u000e\u0002\b\u0005>|G.Z1o\u0005\u001d\u0019V/\\7bef,B!a\u000b\u00024M\u0019adM\u001d\u0015\u0005\u0005=\u0002\u0003\u00026\u001f\u0003c\u00012aSA\u001a\t\u0015ieD1\u0001O)\r!\u0018q\u0007\u0005\bq\u0006\n\t\u00111\u0001}\u0003\u0015qw\u000eZ3t+\t\ti\u0004\u0005\u0004\u0002\u000e\u0005}\u00121I\u0005\u0005\u0003\u0003\nyA\u0001\u0006MSN$()\u001e4gKJ\u0004BA[\t\u00022\u00051an\u001c3fg\u0002\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0013\u0011\t\u00055\u00131K\u0007\u0003\u0003\u001fR1!!\u0015a\u0003\u0011a\u0017M\\4\n\t\u0005U\u0013q\n\u0002\u0007\u001f\nTWm\u0019;\u0002\u000bI|w\u000e\u001e\u0011\u0002\u0013M,X.\\1sS\u0016\u001cXCAA/!\u001d\ti!a\u0006K\u0003?\u00022\u0001\u0017\u0010K\u0003)\u0019X/\\7be&,7\u000fI\u0001\u0004C\u0012$G#\u0002%\u0002h\u0005E\u0004bBA5\r\u0001\u0007\u00111N\u0001\u0002iB!!(!\u001cK\u0013\r\ty'\u0012\u0002\t\u0013R,'/\u00192mK\"9!P\u0002I\u0001\u0002\u0004a\u0018!D1eI\u0012\"WMZ1vYR$#'\u0006\u0002\u0002x)\u001aA0!\u001f,\u0005\u0005m\u0004\u0003BA?\u0003\u000fk!!a \u000b\t\u0005\u0005\u00151Q\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\"6\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u0013\u000byHA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ!\\3sO\u0016$2\u0001SAH\u0011\u0019\t\t\n\u0003a\u0001\u0011\u0006)q\u000e\u001e5fe\u00069\u0001O]8kK\u000e$Hc\u0001%\u0002\u0018\"1\u0011\u0011T\u0005A\u0002)\u000baa];gM&D\u0018\u0001\u0004;sC:\u001c\u0018m\u0019;j_:\u001cXCAAP!\u0015Q\u0014\u0011UAS\u0013\r\t\u0019+\u0012\u0002\t\u0013R,'/\u0019;peB1A'a*\u0002,rL1!!+6\u0005\u0019!V\u000f\u001d7feA!!(!,K\u0013\r\ty+\u0012\u0002\u0005\u0019&\u001cH/A\bhKR$&/\u00198tC\u000e$\u0018n\u001c8t)\u0011\ty*!.\t\r\u0005]6\u00021\u0001X\u0003\u0011qw\u000eZ3\u0002\u000f\u0015DHO]1diR1\u0011qTA_\u0003\u0003Da!a0\r\u0001\u0004a\u0018\u0001C7j]\u000e{WO\u001c;\t\u0013\u0005\rG\u0002%AA\u0002\u0005\u0015\u0017A\u0004<bY&$\u0017\r^3Tk\u001a4\u0017\u000e\u001f\t\u0007i\u0005\u001d'*!\t\n\u0007\u0005%WGA\u0005Gk:\u001cG/[8oc\u0005\tR\r\u001f;sC\u000e$H\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005='\u0006BAc\u0003s\u0002"
)
public class FPTree implements Serializable {
   private final Node root = new Node((Node)null);
   private final Map summaries;

   public Node root() {
      return this.root;
   }

   private Map summaries() {
      return this.summaries;
   }

   public FPTree add(final Iterable t, final long count) {
      .MODULE$.require(count > 0L);
      ObjectRef curr = ObjectRef.create(this.root());
      Node var5 = (Node)curr.elem;
      var5.count_$eq(var5.count() + count);
      t.foreach((item) -> {
         $anonfun$add$1(this, count, curr, item);
         return BoxedUnit.UNIT;
      });
      return this;
   }

   public long add$default$2() {
      return 1L;
   }

   public FPTree merge(final FPTree other) {
      other.transactions().foreach((x0$1) -> {
         if (x0$1 != null) {
            List t = (List)x0$1._1();
            long c = x0$1._2$mcJ$sp();
            return this.add(t, c);
         } else {
            throw new MatchError(x0$1);
         }
      });
      return this;
   }

   private FPTree project(final Object suffix) {
      FPTree tree = new FPTree();
      if (this.summaries().contains(suffix)) {
         Summary summary = (Summary)this.summaries().apply(suffix);
         summary.nodes().foreach((node) -> {
            List t = scala.package..MODULE$.List().empty();

            for(Node curr = node.parent(); !curr.isRoot(); curr = curr.parent()) {
               Object var4 = curr.item();
               t = t.$colon$colon(var4);
            }

            return tree.add(t, node.count());
         });
      }

      return tree;
   }

   public Iterator transactions() {
      return this.getTransactions(this.root());
   }

   private Iterator getTransactions(final Node node) {
      LongRef count = LongRef.create(node.count());
      return node.children().iterator().flatMap((x0$1) -> {
         if (x0$1 != null) {
            Object item = x0$1._1();
            Node child = (Node)x0$1._2();
            return this.getTransactions(child).map((x0$2) -> {
               if (x0$2 != null) {
                  List t = (List)x0$2._1();
                  long c = x0$2._2$mcJ$sp();
                  count.elem -= c;
                  return new Tuple2(t.$colon$colon(item), BoxesRunTime.boxToLong(c));
               } else {
                  throw new MatchError(x0$2);
               }
            });
         } else {
            throw new MatchError(x0$1);
         }
      }).$plus$plus(() -> count.elem > 0L ? scala.package..MODULE$.Iterator().single(new Tuple2(scala.collection.immutable.Nil..MODULE$, BoxesRunTime.boxToLong(count.elem))) : scala.package..MODULE$.Iterator().empty());
   }

   public Iterator extract(final long minCount, final Function1 validateSuffix) {
      return this.summaries().iterator().flatMap((x0$1) -> {
         if (x0$1 != null) {
            Object item = x0$1._1();
            Summary summary = (Summary)x0$1._2();
            return BoxesRunTime.unboxToBoolean(validateSuffix.apply(item)) && summary.count() >= minCount ? scala.package..MODULE$.Iterator().single(new Tuple2(scala.collection.immutable.Nil..MODULE$.$colon$colon(item), BoxesRunTime.boxToLong(summary.count()))).$plus$plus(() -> {
               FPTree qual$1 = this.project(item);
               Function1 x$2 = qual$1.extract$default$2();
               return qual$1.extract(minCount, x$2).map((x0$2) -> {
                  if (x0$2 != null) {
                     List t = (List)x0$2._1();
                     long c = x0$2._2$mcJ$sp();
                     return new Tuple2(t.$colon$colon(item), BoxesRunTime.boxToLong(c));
                  } else {
                     throw new MatchError(x0$2);
                  }
               });
            }) : scala.package..MODULE$.Iterator().empty();
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public Function1 extract$default$2() {
      return (x$1) -> BoxesRunTime.boxToBoolean($anonfun$extract$default$2$1(x$1));
   }

   // $FF: synthetic method
   public static final void $anonfun$add$1(final FPTree $this, final long count$1, final ObjectRef curr$1, final Object item) {
      Summary summary = (Summary)$this.summaries().getOrElseUpdate(item, () -> new Summary());
      summary.count_$eq(summary.count() + count$1);
      Node child = (Node)((Node)curr$1.elem).children().getOrElseUpdate(item, () -> {
         Node newNode = new Node((Node)curr$1.elem);
         newNode.item_$eq(item);
         summary.nodes().$plus$eq(newNode);
         return newNode;
      });
      child.count_$eq(child.count() + count$1);
      curr$1.elem = child;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$extract$default$2$1(final Object x$1) {
      return true;
   }

   public FPTree() {
      this.summaries = (Map)scala.collection.mutable.Map..MODULE$.empty();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Node implements Serializable {
      private final Node parent;
      private Object item;
      private long count;
      private final Map children;

      public Node parent() {
         return this.parent;
      }

      public Object item() {
         return this.item;
      }

      public void item_$eq(final Object x$1) {
         this.item = x$1;
      }

      public long count() {
         return this.count;
      }

      public void count_$eq(final long x$1) {
         this.count = x$1;
      }

      public Map children() {
         return this.children;
      }

      public boolean isRoot() {
         return this.parent() == null;
      }

      public Node(final Node parent) {
         this.parent = parent;
         this.count = 0L;
         this.children = (Map)scala.collection.mutable.Map..MODULE$.empty();
      }
   }

   private static class Summary implements Serializable {
      private long count = 0L;
      private final ListBuffer nodes;

      public long count() {
         return this.count;
      }

      public void count_$eq(final long x$1) {
         this.count = x$1;
      }

      public ListBuffer nodes() {
         return this.nodes;
      }

      public Summary() {
         this.nodes = scala.collection.mutable.ListBuffer..MODULE$.empty();
      }
   }
}
