package scala.xml.factory;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.Seq;
import scala.collection.immutable.List;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.xml.Comment;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.Node;
import scala.xml.PCData;
import scala.xml.PCData$;
import scala.xml.ProcInstr;
import scala.xml.Text;
import scala.xml.Text$;
import scala.xml.Utility$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=ca\u0002\b\u0010!\u0003\r\tA\u0006\u0005\u00069\u0001!\t!\b\u0005\bC\u0001\u0011\r\u0011\"\u0001#\u0011\u001d1\u0003A1A\u0005\u0002\tBqa\n\u0001C\u0002\u0013\u0005\u0001\u0006C\u0003H\u0001\u0019E\u0001\nC\u0003i\u0001\u0011E\u0011\u000eC\u0003u\u0001\u0011\u0005Q\u000fC\u0003{\u0001\u0011\u00051\u0010C\u0004\u0002\b\u0001!\t!!\u0003\t\u000f\u0005U\u0001\u0001\"\u0001\u0002\u0018!9\u00111\u0005\u0001\u0005\u0002\u0005\u0015\u0002bBA\u0018\u0001\u0011\u0005\u0011\u0011\u0007\u0005\b\u0003{\u0001A\u0011AA \u0005-qu\u000eZ3GC\u000e$xN]=\u000b\u0005A\t\u0012a\u00024bGR|'/\u001f\u0006\u0003%M\t1\u0001_7m\u0015\u0005!\u0012!B:dC2\f7\u0001A\u000b\u0003/u\u001a\"\u0001\u0001\r\u0011\u0005eQR\"A\n\n\u0005m\u0019\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002=A\u0011\u0011dH\u0005\u0003AM\u0011A!\u00168ji\u0006q\u0011n\u001a8pe\u0016\u001cu.\\7f]R\u001cX#A\u0012\u0011\u0005e!\u0013BA\u0013\u0014\u0005\u001d\u0011un\u001c7fC:\fq\"[4o_J,\u0007K]8d\u0013:\u001cHO]\u0001\u0006G\u0006\u001c\u0007.Z\u000b\u0002SA!!fL\u00195\u001b\u0005Y#B\u0001\u0017.\u0003\u001diW\u000f^1cY\u0016T!AL\n\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u00021W\t9\u0001*Y:i\u001b\u0006\u0004\bCA\r3\u0013\t\u00194CA\u0002J]R\u00042!\u000e\u001d<\u001d\tIb'\u0003\u00028'\u00059\u0001/Y2lC\u001e,\u0017BA\u001d;\u0005\u0011a\u0015n\u001d;\u000b\u0005]\u001a\u0002C\u0001\u001f>\u0019\u0001!QA\u0010\u0001C\u0002}\u0012\u0011!Q\t\u0003\u0001\u000e\u0003\"!G!\n\u0005\t\u001b\"a\u0002(pi\"Lgn\u001a\t\u0003\t\u0016k\u0011!E\u0005\u0003\rF\u0011AAT8eK\u000611M]3bi\u0016$baO%W1v\u0013\u0007\"\u0002&\u0006\u0001\u0004Y\u0015a\u00019sKB\u0011Aj\u0015\b\u0003\u001bF\u0003\"AT\n\u000e\u0003=S!\u0001U\u000b\u0002\rq\u0012xn\u001c;?\u0013\t\u00116#\u0001\u0004Qe\u0016$WMZ\u0005\u0003)V\u0013aa\u0015;sS:<'B\u0001*\u0014\u0011\u00159V\u00011\u0001L\u0003\u0011q\u0017-\\3\t\u000be+\u0001\u0019\u0001.\u0002\u000b\u0005$HO]:\u0011\u0005\u0011[\u0016B\u0001/\u0012\u0005!iU\r^1ECR\f\u0007\"\u00020\u0006\u0001\u0004y\u0016!B:d_B,\u0007C\u0001#a\u0013\t\t\u0017C\u0001\tOC6,7\u000f]1dK\nKg\u000eZ5oO\")1-\u0002a\u0001I\u0006A1\r[5mIJ,g\u000eE\u0002fM\u000ek\u0011!L\u0005\u0003O6\u00121aU3r\u0003%\u0019wN\\:ueV\u001cG\u000f\u0006\u0005<U2tw\u000e\u001d:t\u0011\u0015Yg\u00011\u00012\u0003\u0011A\u0017m\u001d5\t\u000b54\u0001\u0019\u0001\u001b\u0002\u0007=dG\rC\u0003K\r\u0001\u00071\nC\u0003X\r\u0001\u00071\nC\u0003r\r\u0001\u0007!,A\u0004biR\u00148+Z9\t\u000by3\u0001\u0019A0\t\u000b\r4\u0001\u0019\u00013\u0002\u0015\u0015\fX\t\\3nK:$8\u000fF\u0002$mbDQa^\u0004A\u0002\u0011\f1a\u001952\u0011\u0015Ix\u00011\u0001e\u0003\r\u0019\u0007NM\u0001\u000b]>$W-R9vC2\u001cHCC\u0012}}~\f\t!a\u0001\u0002\u0006!)Q\u0010\u0003a\u0001\u0007\u0006\ta\u000eC\u0003K\u0011\u0001\u00071\nC\u0003X\u0011\u0001\u00071\nC\u0003r\u0011\u0001\u0007!\fC\u0003_\u0011\u0001\u0007q\fC\u0003d\u0011\u0001\u0007A-\u0001\u0005nC.,gj\u001c3f)-Y\u00141BA\u0007\u0003\u001f\t\t\"a\u0005\t\u000b)K\u0001\u0019A&\t\u000b]K\u0001\u0019A&\t\u000bEL\u0001\u0019\u0001.\t\u000byK\u0001\u0019A0\t\u000b\rL\u0001\u0019\u00013\u0002\u00115\f7.\u001a+fqR$B!!\u0007\u0002 A\u0019A)a\u0007\n\u0007\u0005u\u0011C\u0001\u0003UKb$\bBBA\u0011\u0015\u0001\u00071*A\u0001t\u0003)i\u0017m[3Q\u0007\u0012\u000bG/\u0019\u000b\u0005\u0003O\ti\u0003E\u0002E\u0003SI1!a\u000b\u0012\u0005\u0019\u00016\tR1uC\"1\u0011\u0011E\u0006A\u0002-\u000b1\"\\1lK\u000e{W.\\3oiR!\u00111GA\u001e!\u0011)g-!\u000e\u0011\u0007\u0011\u000b9$C\u0002\u0002:E\u0011qaQ8n[\u0016tG\u000f\u0003\u0004\u0002\"1\u0001\raS\u0001\u000e[\u0006\\W\r\u0015:pG&s7\u000f\u001e:\u0015\r\u0005\u0005\u0013\u0011JA'!\u0011)g-a\u0011\u0011\u0007\u0011\u000b)%C\u0002\u0002HE\u0011\u0011\u0002\u0015:pG&s7\u000f\u001e:\t\r\u0005-S\u00021\u0001L\u0003\u0005!\bBBA\u0011\u001b\u0001\u00071\n"
)
public interface NodeFactory {
   void scala$xml$factory$NodeFactory$_setter_$ignoreComments_$eq(final boolean x$1);

   void scala$xml$factory$NodeFactory$_setter_$ignoreProcInstr_$eq(final boolean x$1);

   void scala$xml$factory$NodeFactory$_setter_$cache_$eq(final HashMap x$1);

   boolean ignoreComments();

   boolean ignoreProcInstr();

   HashMap cache();

   Node create(final String pre, final String name, final MetaData attrs, final NamespaceBinding scope, final Seq children);

   // $FF: synthetic method
   static Node construct$(final NodeFactory $this, final int hash, final List old, final String pre, final String name, final MetaData attrSeq, final NamespaceBinding scope, final Seq children) {
      return $this.construct(hash, old, pre, name, attrSeq, scope, children);
   }

   default Node construct(final int hash, final List old, final String pre, final String name, final MetaData attrSeq, final NamespaceBinding scope, final Seq children) {
      Node el = this.create(pre, name, attrSeq, scope, children);
      this.cache().update(BoxesRunTime.boxToInteger(hash), old.$colon$colon(el));
      return el;
   }

   // $FF: synthetic method
   static boolean eqElements$(final NodeFactory $this, final Seq ch1, final Seq ch2) {
      return $this.eqElements(ch1, ch2);
   }

   default boolean eqElements(final Seq ch1, final Seq ch2) {
      return ((IterableOnceOps)ch1.view().zipAll(ch2.view(), (Object)null, (Object)null)).forall((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$eqElements$1(x0$1)));
   }

   // $FF: synthetic method
   static boolean nodeEquals$(final NodeFactory $this, final Node n, final String pre, final String name, final MetaData attrSeq, final NamespaceBinding scope, final Seq children) {
      return $this.nodeEquals(n, pre, name, attrSeq, scope, children);
   }

   default boolean nodeEquals(final Node n, final String pre, final String name, final MetaData attrSeq, final NamespaceBinding scope, final Seq children) {
      boolean var12;
      label39: {
         String var10000 = n.prefix();
         if (var10000 == null) {
            if (pre != null) {
               break label39;
            }
         } else if (!var10000.equals(pre)) {
            break label39;
         }

         var10000 = n.label();
         if (var10000 == null) {
            if (name != null) {
               break label39;
            }
         } else if (!var10000.equals(name)) {
            break label39;
         }

         MetaData var11 = n.attributes();
         if (var11 == null) {
            if (attrSeq != null) {
               break label39;
            }
         } else if (!var11.equals(attrSeq)) {
            break label39;
         }

         if (this.eqElements(n.child(), children)) {
            var12 = true;
            return var12;
         }
      }

      var12 = false;
      return var12;
   }

   // $FF: synthetic method
   static Node makeNode$(final NodeFactory $this, final String pre, final String name, final MetaData attrSeq, final NamespaceBinding scope, final Seq children) {
      return $this.makeNode(pre, name, attrSeq, scope, children);
   }

   default Node makeNode(final String pre, final String name, final MetaData attrSeq, final NamespaceBinding scope, final Seq children) {
      int hash = Utility$.MODULE$.hashCode(pre, name, Statics.anyHash(attrSeq), Statics.anyHash(scope), children);
      Option var9 = this.cache().get(BoxesRunTime.boxToInteger(hash));
      if (var9 instanceof Some) {
         Some var10 = (Some)var9;
         List list = (List)var10.value();
         Option var12 = list.find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$makeNode$1(this, pre, name, attrSeq, scope, children, x$1)));
         if (var12 instanceof Some) {
            Some var13 = (Some)var12;
            Node x = (Node)var13.value();
            return x;
         } else {
            return this.cons$1(list, hash, pre, name, attrSeq, scope, children);
         }
      } else if (.MODULE$.equals(var9)) {
         return this.cons$1(scala.collection.immutable.Nil..MODULE$, hash, pre, name, attrSeq, scope, children);
      } else {
         throw new MatchError(var9);
      }
   }

   // $FF: synthetic method
   static Text makeText$(final NodeFactory $this, final String s) {
      return $this.makeText(s);
   }

   default Text makeText(final String s) {
      return Text$.MODULE$.apply(s);
   }

   // $FF: synthetic method
   static PCData makePCData$(final NodeFactory $this, final String s) {
      return $this.makePCData(s);
   }

   default PCData makePCData(final String s) {
      return PCData$.MODULE$.apply(s);
   }

   // $FF: synthetic method
   static Seq makeComment$(final NodeFactory $this, final String s) {
      return $this.makeComment(s);
   }

   default Seq makeComment(final String s) {
      return (Seq)(this.ignoreComments() ? scala.collection.immutable.Nil..MODULE$ : new scala.collection.immutable..colon.colon(new Comment(s), scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   static Seq makeProcInstr$(final NodeFactory $this, final String t, final String s) {
      return $this.makeProcInstr(t, s);
   }

   default Seq makeProcInstr(final String t, final String s) {
      return (Seq)(this.ignoreProcInstr() ? scala.collection.immutable.Nil..MODULE$ : new scala.collection.immutable..colon.colon(new ProcInstr(t, s), scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   static boolean $anonfun$eqElements$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Node x = (Node)x0$1._1();
         Node y = (Node)x0$1._2();
         return x == y;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private Node cons$1(final List old, final int hash$1, final String pre$1, final String name$1, final MetaData attrSeq$1, final NamespaceBinding scope$1, final Seq children$1) {
      return this.construct(hash$1, old, pre$1, name$1, attrSeq$1, scope$1, children$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$makeNode$1(final NodeFactory $this, final String pre$1, final String name$1, final MetaData attrSeq$1, final NamespaceBinding scope$1, final Seq children$1, final Node x$1) {
      return $this.nodeEquals(x$1, pre$1, name$1, attrSeq$1, scope$1, children$1);
   }

   static void $init$(final NodeFactory $this) {
      $this.scala$xml$factory$NodeFactory$_setter_$ignoreComments_$eq(false);
      $this.scala$xml$factory$NodeFactory$_setter_$ignoreProcInstr_$eq(false);
      $this.scala$xml$factory$NodeFactory$_setter_$cache_$eq(new HashMap());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
