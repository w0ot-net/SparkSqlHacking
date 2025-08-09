package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d!\u0002\r\u001a\u0001f)\u0003\u0002C\u001e\u0001\u0005+\u0007I\u0011\u0001\u001f\t\u0011E\u0003!\u0011#Q\u0001\nuBQA\u0015\u0001\u0005\u0002MCqA\u0016\u0001\u0002\u0002\u0013\u0005q\u000bC\u0004Z\u0001E\u0005I\u0011\u0001.\t\u000f\u0015\u0004\u0011\u0011!C!M\"9a\u000eAA\u0001\n\u0003y\u0007bB:\u0001\u0003\u0003%\t\u0001\u001e\u0005\bu\u0002\t\t\u0011\"\u0011|\u0011%\t\t\u0001AA\u0001\n\u0003\t\u0019\u0001C\u0005\u0002\u000e\u0001\t\t\u0011\"\u0011\u0002\u0010!I\u00111\u0003\u0001\u0002\u0002\u0013\u0005\u0013Q\u0003\u0005\n\u0003/\u0001\u0011\u0011!C!\u00033A\u0011\"a\u0007\u0001\u0003\u0003%\t%!\b\b\u0015\u0005\u0005\u0012$!A\t\u0002e\t\u0019CB\u0005\u00193\u0005\u0005\t\u0012A\r\u0002&!1!\u000b\u0005C\u0001\u0003{A\u0011\"a\u0006\u0011\u0003\u0003%)%!\u0007\t\u0013\u0005}\u0002#!A\u0005\u0002\u0006\u0005\u0003\u0002CA#!E\u0005I\u0011\u0001.\t\u0013\u0005\u001d\u0003#!A\u0005\u0002\u0006%\u0003\u0002CA+!E\u0005I\u0011\u0001.\t\u0013\u0005]\u0003#!A\u0005\n\u0005e#AD\"mCN\u001cxJ^3se&$Wm\u001d\u0006\u00035m\t!\"\u001b8ue>\u001c\b/Z2u\u0015\taR$A\u0003tG\u0006d\u0017M\u0003\u0002\u001f?\u00051Qn\u001c3vY\u0016T!\u0001I\u0011\u0002\u000f)\f7m[:p]*\u0011!eI\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011\u0001J\u0001\u0004G>l7\u0003\u0002\u0001'W9\u0002\"aJ\u0015\u000e\u0003!R\u0011\u0001H\u0005\u0003U!\u0012a!\u00118z%\u00164\u0007CA\u0014-\u0013\ti\u0003FA\u0004Qe>$Wo\u0019;\u0011\u0005=BdB\u0001\u00197\u001d\t\tT'D\u00013\u0015\t\u0019D'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005a\u0012BA\u001c)\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000f\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005]B\u0013!C8wKJ\u0014\u0018\u000eZ3t+\u0005i\u0004\u0003\u0002 D\u000b6k\u0011a\u0010\u0006\u0003\u0001\u0006\u000bq!\\;uC\ndWM\u0003\u0002CQ\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005\u0011{$aA'baB\u0011aI\u0013\b\u0003\u000f\"\u0003\"!\r\u0015\n\u0005%C\u0013A\u0002)sK\u0012,g-\u0003\u0002L\u0019\n11\u000b\u001e:j]\u001eT!!\u0013\u0015\u0011\u00059{U\"A\r\n\u0005AK\"aC\"mCN\u001c\bj\u001c7eKJ\f!b\u001c<feJLG-Z:!\u0003\u0019a\u0014N\\5u}Q\u0011A+\u0016\t\u0003\u001d\u0002AqaO\u0002\u0011\u0002\u0003\u0007Q(\u0001\u0003d_BLHC\u0001+Y\u0011\u001dYD\u0001%AA\u0002u\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001\\U\tiDlK\u0001^!\tq6-D\u0001`\u0015\t\u0001\u0017-A\u0005v]\u000eDWmY6fI*\u0011!\rK\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00013`\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u001d\u0004\"\u0001[7\u000e\u0003%T!A[6\u0002\t1\fgn\u001a\u0006\u0002Y\u0006!!.\u0019<b\u0013\tY\u0015.\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001q!\t9\u0013/\u0003\u0002sQ\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011Q\u000f\u001f\t\u0003OYL!a\u001e\u0015\u0003\u0007\u0005s\u0017\u0010C\u0004z\u0011\u0005\u0005\t\u0019\u00019\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005a\bcA?\u007fk6\t\u0011)\u0003\u0002\u0000\u0003\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t)!a\u0003\u0011\u0007\u001d\n9!C\u0002\u0002\n!\u0012qAQ8pY\u0016\fg\u000eC\u0004z\u0015\u0005\u0005\t\u0019A;\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004O\u0006E\u0001bB=\f\u0003\u0003\u0005\r\u0001]\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0001/\u0001\u0005u_N#(/\u001b8h)\u00059\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0006\u0005}\u0001bB=\u000f\u0003\u0003\u0005\r!^\u0001\u000f\u00072\f7o](wKJ\u0014\u0018\u000eZ3t!\tq\u0005cE\u0003\u0011\u0003O\t\u0019\u0004\u0005\u0004\u0002*\u0005=R\bV\u0007\u0003\u0003WQ1!!\f)\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\r\u0002,\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005U\u00121H\u0007\u0003\u0003oQ1!!\u000fl\u0003\tIw.C\u0002:\u0003o!\"!a\t\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007Q\u000b\u0019\u0005C\u0004<'A\u0005\t\u0019A\u001f\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIE\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002L\u0005E\u0003\u0003B\u0014\u0002NuJ1!a\u0014)\u0005\u0019y\u0005\u000f^5p]\"A\u00111K\u000b\u0002\u0002\u0003\u0007A+A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA.!\rA\u0017QL\u0005\u0004\u0003?J'AB(cU\u0016\u001cG\u000f"
)
public class ClassOverrides implements Product, Serializable {
   private final Map overrides;

   public static Map $lessinit$greater$default$1() {
      return ClassOverrides$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final ClassOverrides x$0) {
      return ClassOverrides$.MODULE$.unapply(x$0);
   }

   public static Map apply$default$1() {
      return ClassOverrides$.MODULE$.apply$default$1();
   }

   public static ClassOverrides apply(final Map overrides) {
      return ClassOverrides$.MODULE$.apply(overrides);
   }

   public static Function1 andThen(final Function1 g) {
      return ClassOverrides$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ClassOverrides$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Map overrides() {
      return this.overrides;
   }

   public ClassOverrides copy(final Map overrides) {
      return new ClassOverrides(overrides);
   }

   public Map copy$default$1() {
      return this.overrides();
   }

   public String productPrefix() {
      return "ClassOverrides";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.overrides();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ClassOverrides;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "overrides";
         default:
            return (String)Statics.ioobe(x$1);
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
            if (x$1 instanceof ClassOverrides) {
               label40: {
                  ClassOverrides var4 = (ClassOverrides)x$1;
                  Map var10000 = this.overrides();
                  Map var5 = var4.overrides();
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

   public ClassOverrides(final Map overrides) {
      this.overrides = overrides;
      Product.$init$(this);
   }
}
