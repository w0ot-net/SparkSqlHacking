package scala.xml.dtd.impl;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u0005=bAB\f\u0019\u0003\u0003Q\u0002\u0005C\u0003&\u0001\u0011\u0005q\u0005C\u0004+\u0001\t\u0007i\u0011A\u0016\t\u000f=\u0002\u0001\u0019!C\ta!9A\u0007\u0001a\u0001\n#)\u0004BB\u001e\u0001A\u0003&\u0011\u0007C\u0005=\u0001\u0001\u0007\t\u0019!C\t{!I\u0011\u000b\u0001a\u0001\u0002\u0004%\tB\u0015\u0005\n)\u0002\u0001\r\u0011!Q!\nyB\u0011\"\u0016\u0001A\u0002\u0003\u0007I\u0011\u0003\u0019\t\u0013Y\u0003\u0001\u0019!a\u0001\n#9\u0006\"C-\u0001\u0001\u0004\u0005\t\u0015)\u00032\u0011%Q\u0006\u00011AA\u0002\u0013E1\fC\u0005c\u0001\u0001\u0007\t\u0019!C\tG\"IQ\r\u0001a\u0001\u0002\u0003\u0006K\u0001\u0018\u0005\bM\u0002\u0011\r\u0011\"\u0002h\u0011\u0019A\u0007\u0001)A\u0007\r\")\u0011\u000e\u0001C\u0005U\")q\u000f\u0001C\tq\")!\u0010\u0001C\tw\")Q\u0010\u0001C\t}\"9\u00111\u0002\u0001\u0005\u0012\u00055\u0001bBA\u000b\u0001\u0011E\u0011q\u0003\u0002\u000f\u0005\u0006\u001cXMQ3sef\u001cV\r\u001e5j\u0015\tI\"$\u0001\u0003j[Bd'BA\u000e\u001d\u0003\r!G\u000f\u001a\u0006\u0003;y\t1\u0001_7m\u0015\u0005y\u0012!B:dC2\f7C\u0001\u0001\"!\t\u00113%D\u0001\u001f\u0013\t!cD\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\t\u0001\u0006\u0005\u0002*\u00015\t\u0001$\u0001\u0003mC:<W#\u0001\u0017\u0011\u0005%j\u0013B\u0001\u0018\u0019\u0005\u0011\u0011\u0015m]3\u0002\u0007A|7/F\u00012!\t\u0011#'\u0003\u00024=\t\u0019\u0011J\u001c;\u0002\u000fA|7o\u0018\u0013fcR\u0011a'\u000f\t\u0003E]J!\u0001\u000f\u0010\u0003\tUs\u0017\u000e\u001e\u0005\bu\u0011\t\t\u00111\u00012\u0003\rAH%M\u0001\u0005a>\u001c\b%\u0001\u0004g_2dwn^\u000b\u0002}A!q\bR\u0019G\u001b\u0005\u0001%BA!C\u0003\u001diW\u000f^1cY\u0016T!a\u0011\u0010\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002F\u0001\n9\u0001*Y:i\u001b\u0006\u0004\bcA$Oc9\u0011\u0001\n\u0014\t\u0003\u0013zi\u0011A\u0013\u0006\u0003\u0017\u001a\na\u0001\u0010:p_Rt\u0014BA'\u001f\u0003\u0019\u0001&/\u001a3fM&\u0011q\n\u0015\u0002\u0004'\u0016$(BA'\u001f\u0003)1w\u000e\u001c7po~#S-\u001d\u000b\u0003mMCqAO\u0004\u0002\u0002\u0003\u0007a(A\u0004g_2dwn\u001e\u0011\u0002\u0011\u0019Lg.\u00197UC\u001e\fABZ5oC2$\u0016mZ0%KF$\"A\u000e-\t\u000fiR\u0011\u0011!a\u0001c\u0005Ia-\u001b8bYR\u000bw\rI\u0001\u0007M&t\u0017\r\\:\u0016\u0003q\u0003B!\u001812c5\taL\u0003\u0002`\u0005\u0006I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0003Cz\u00131!T1q\u0003)1\u0017N\\1mg~#S-\u001d\u000b\u0003m\u0011DqAO\u0007\u0002\u0002\u0003\u0007A,A\u0004gS:\fGn\u001d\u0011\u0002\u0011\u0015l\u0007\u000f^=TKR,\u0012AR\u0001\nK6\u0004H/_*fi\u0002\na\u0001Z8D_6\u0004Hc\u0001$le\")A.\u0005a\u0001[\u0006\t!\u000f\u0005\u0002oa:\u0011qNA\u0007\u0002\u0001%\u0011\u0011/\f\u0002\u0007%\u0016<W\t\u001f9\t\u000bM\f\u0002\u0019\u0001;\u0002\u0019\r|W\u000e\u001d$v]\u000e$\u0018n\u001c8\u0011\t\t*XNR\u0005\u0003mz\u0011\u0011BR;oGRLwN\\\u0019\u0002\u0013\r|W\u000e\u001d$jeN$HC\u0001$z\u0011\u0015a'\u00031\u0001n\u0003!\u0019w.\u001c9MCN$HC\u0001$}\u0011\u0015a7\u00031\u0001n\u0003)\u0019w.\u001c9G_2dwn\u001e\u000b\u0003\r~Dq!!\u0001\u0015\u0001\u0004\t\u0019!\u0001\u0002sgB)\u0011QAA\u0004[6\t!)C\u0002\u0002\n\t\u00131aU3r\u0003-\u0019w.\u001c9G_2dwn^\u0019\u0015\u000b\u0019\u000by!a\u0005\t\r\u0005EQ\u00031\u0001G\u0003\u00111w\u000e\\\u0019\t\u000b1,\u0002\u0019A7\u0002\u0011Q\u0014\u0018M^3sg\u0016$2ANA\r\u0011\u0015ag\u00031\u0001nQ-\u0001\u0011QDA\u0012\u0003K\tI#a\u000b\u0011\u0007\t\ny\"C\u0002\u0002\"y\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#!a\n\u00025QC\u0017n\u001d\u0011dY\u0006\u001c8\u000fI<jY2\u0004#-\u001a\u0011sK6|g/\u001a3\u0002\u000bMLgnY3\"\u0005\u00055\u0012A\u0002\u001a/cAr\u0003\u0007"
)
public abstract class BaseBerrySethi {
   private int pos = 0;
   private HashMap follow;
   private int finalTag;
   private Map finals;
   private final Set emptySet;

   public abstract Base lang();

   public int pos() {
      return this.pos;
   }

   public void pos_$eq(final int x$1) {
      this.pos = x$1;
   }

   public HashMap follow() {
      return this.follow;
   }

   public void follow_$eq(final HashMap x$1) {
      this.follow = x$1;
   }

   public int finalTag() {
      return this.finalTag;
   }

   public void finalTag_$eq(final int x$1) {
      this.finalTag = x$1;
   }

   public Map finals() {
      return this.finals;
   }

   public void finals_$eq(final Map x$1) {
      this.finals = x$1;
   }

   public final Set emptySet() {
      return this.emptySet;
   }

   private Set doComp(final Base.RegExp r, final Function1 compFunction) {
      if (r instanceof Base.Alt) {
         Base.Alt var6 = (Base.Alt)r;
         return (Set)((IterableOnceOps)var6.rs().map((rx) -> this.compFirst(rx))).foldLeft(this.emptySet(), (x$4, x$5) -> (Set)x$4.$plus$plus(x$5));
      } else if (this.lang().Eps().equals(r)) {
         return this.emptySet();
      } else if (r instanceof Base.Meta) {
         Base.Meta var7 = (Base.Meta)r;
         return (Set)compFunction.apply(var7.r());
      } else if (r instanceof Base.Sequ) {
         Base.Sequ var8 = (Base.Sequ)r;
         Tuple2 var10 = var8.rs().span((x$6) -> BoxesRunTime.boxToBoolean($anonfun$doComp$3(x$6)));
         if (var10 != null) {
            Seq l1 = (Seq)var10._1();
            Seq l2 = (Seq)var10._2();
            if (l1 != null && l2 != null) {
               Tuple2 var9 = new Tuple2(l1, l2);
               scala.collection.Seq l1 = (scala.collection.Seq)var9._1();
               scala.collection.Seq l2 = (scala.collection.Seq)var9._2();
               return (Set)((IterableOnceOps)((IterableOps)l1.$plus$plus((IterableOnce)l2.take(1))).map(compFunction)).foldLeft(this.emptySet(), (x$8, x$9) -> (Set)x$8.$plus$plus(x$9));
            }
         }

         throw new MatchError(var10);
      } else if (r instanceof Base.Star) {
         Base.Star var17 = (Base.Star)r;
         Base.RegExp t = var17.r();
         return (Set)compFunction.apply(t);
      } else {
         throw new IllegalArgumentException((new StringBuilder(19)).append("unexpected pattern ").append(r.getClass()).toString());
      }
   }

   public Set compFirst(final Base.RegExp r) {
      return this.doComp(r, (rx) -> this.compFirst(rx));
   }

   public Set compLast(final Base.RegExp r) {
      return this.doComp(r, (rx) -> this.compLast(rx));
   }

   public Set compFollow(final scala.collection.Seq rs) {
      this.follow().update(BoxesRunTime.boxToInteger(0), rs.isEmpty() ? this.emptySet() : rs.foldRight(.MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{this.pos()})), (p, fol) -> {
         Set first = this.compFollow1(fol, p);
         return p.isNullable() ? (Set)fol.$plus$plus(first) : first;
      }));
      return (Set)this.follow().apply(BoxesRunTime.boxToInteger(0));
   }

   public Set compFollow1(final Set fol1, final Base.RegExp r) {
      if (r instanceof Base.Alt) {
         Base.Alt var5 = (Base.Alt)r;
         return (Set).MODULE$.Set().apply((Seq)((IterableOps)var5.rs().reverseMap((x$10) -> this.compFollow1(fol1, x$10))).flatten(.MODULE$.$conforms()));
      } else if (r instanceof Base.Meta) {
         Base.Meta var6 = (Base.Meta)r;
         return this.compFollow1(fol1, var6.r());
      } else if (r instanceof Base.Star) {
         Base.Star var7 = (Base.Star)r;
         return this.compFollow1((Set)fol1.$plus$plus(this.compFirst(var7.r())), var7.r());
      } else if (r instanceof Base.Sequ) {
         Base.Sequ var8 = (Base.Sequ)r;
         return (Set)var8.rs().foldRight(fol1, (p, fol) -> {
            Set first = this.compFollow1(fol, p);
            return p.isNullable() ? (Set)fol.$plus$plus(first) : first;
         });
      } else {
         throw new IllegalArgumentException((new StringBuilder(20)).append("unexpected pattern: ").append(r.getClass()).toString());
      }
   }

   public void traverse(final Base.RegExp r) {
      if (r instanceof Base.Alt) {
         Base.Alt var4 = (Base.Alt)r;
         var4.rs().foreach((rx) -> {
            $anonfun$traverse$1(this, rx);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var11 = BoxedUnit.UNIT;
      } else if (r instanceof Base.Sequ) {
         Base.Sequ var5 = (Base.Sequ)r;
         var5.rs().foreach((rx) -> {
            $anonfun$traverse$2(this, rx);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else if (r instanceof Base.Meta) {
         Base.Meta var6 = (Base.Meta)r;
         this.traverse(var6.r());
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else if (r instanceof Base.Star) {
         Base.Star var7 = (Base.Star)r;
         Base.RegExp t = var7.r();
         this.traverse(t);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new IllegalArgumentException((new StringBuilder(14)).append("unexp pattern ").append(r.getClass()).toString());
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doComp$3(final Base.RegExp x$6) {
      return x$6.isNullable();
   }

   // $FF: synthetic method
   public static final void $anonfun$traverse$1(final BaseBerrySethi $this, final Base.RegExp r) {
      $this.traverse(r);
   }

   // $FF: synthetic method
   public static final void $anonfun$traverse$2(final BaseBerrySethi $this, final Base.RegExp r) {
      $this.traverse(r);
   }

   public BaseBerrySethi() {
      this.emptySet = (Set).MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
