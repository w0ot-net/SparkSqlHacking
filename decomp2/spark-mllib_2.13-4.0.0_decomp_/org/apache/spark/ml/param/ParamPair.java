package org.apache.spark.ml.param;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001df\u0001B\r\u001b\u0001\u0016B\u0001b\u0007\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t+\u0002\u0011\t\u0012)A\u0005{!Aq\u000b\u0001BK\u0002\u0013\u0005\u0001\f\u0003\u0005[\u0001\tE\t\u0015!\u0003B\u0011\u0015a\u0006\u0001\"\u0001^\u0011\u001d!\u0007!!A\u0005\u0002\u0015Dq!\u001c\u0001\u0012\u0002\u0013\u0005a\u000eC\u0004{\u0001E\u0005I\u0011A>\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0005\u0001\u0003\u0003%\t!!\u0006\t\u0013\u0005u\u0001!!A\u0005\u0002\u0005}\u0001\"CA\u0013\u0001\u0005\u0005I\u0011IA\u0014\u0011%\t)\u0004AA\u0001\n\u0003\t9\u0004C\u0005\u0002B\u0001\t\t\u0011\"\u0011\u0002D!I\u0011q\t\u0001\u0002\u0002\u0013\u0005\u0013\u0011\n\u0005\n\u0003\u0017\u0002\u0011\u0011!C!\u0003\u001bB\u0011\"a\u0014\u0001\u0003\u0003%\t%!\u0015\b\u0013\u0005]#$!A\t\u0002\u0005ec\u0001C\r\u001b\u0003\u0003E\t!a\u0017\t\rq\u001bB\u0011AA4\u0011%\tYeEA\u0001\n\u000b\ni\u0005C\u0005\u0002jM\t\t\u0011\"!\u0002l!I\u0011qP\n\u0002\u0002\u0013\u0005\u0015\u0011\u0011\u0005\n\u0003;\u001b\u0012\u0011!C\u0005\u0003?\u0013\u0011\u0002U1sC6\u0004\u0016-\u001b:\u000b\u0005ma\u0012!\u00029be\u0006l'BA\u000f\u001f\u0003\tiGN\u0003\u0002 A\u0005)1\u000f]1sW*\u0011\u0011EI\u0001\u0007CB\f7\r[3\u000b\u0003\r\n1a\u001c:h\u0007\u0001)\"AJ\"\u0014\t\u00019S\u0006\r\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005!r\u0013BA\u0018*\u0005\u001d\u0001&o\u001c3vGR\u0004\"!M\u001d\u000f\u0005I:dBA\u001a7\u001b\u0005!$BA\u001b%\u0003\u0019a$o\\8u}%\t!&\u0003\u00029S\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001e<\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tA\u0014&F\u0001>!\rqt(Q\u0007\u00025%\u0011\u0001I\u0007\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u0003\u0005\u000ec\u0001\u0001B\u0003E\u0001\t\u0007QIA\u0001U#\t1\u0015\n\u0005\u0002)\u000f&\u0011\u0001*\u000b\u0002\b\u001d>$\b.\u001b8h!\tA#*\u0003\u0002LS\t\u0019\u0011I\\=)\u0007\u0005i5\u000b\u0005\u0002O#6\tqJ\u0003\u0002Q=\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005I{%!B*j]\u000e,\u0017%\u0001+\u0002\u000bEr#G\f\u0019\u0002\rA\f'/Y7!Q\r\u0011QjU\u0001\u0006m\u0006dW/Z\u000b\u0002\u0003\"\u001a1!T*\u0002\rY\fG.^3!Q\r!QjU\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007y{\u0016\rE\u0002?\u0001\u0005CQaG\u0003A\u0002uB3aX'T\u0011\u00159V\u00011\u0001BQ\r\tWj\u0015\u0015\u0004\u000b5\u001b\u0016\u0001B2paf,\"AZ5\u0015\u0007\u001dTG\u000eE\u0002?\u0001!\u0004\"AQ5\u0005\u000b\u00113!\u0019A#\t\u000fm1\u0001\u0013!a\u0001WB\u0019ah\u00105\t\u000f]3\u0001\u0013!a\u0001Q\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCA8z+\u0005\u0001(FA\u001frW\u0005\u0011\bCA:x\u001b\u0005!(BA;w\u0003%)hn\u00195fG.,GM\u0003\u0002QS%\u0011\u0001\u0010\u001e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!\u0002#\b\u0005\u0004)\u0015AD2paf$C-\u001a4bk2$HEM\u000b\u0003yz,\u0012! \u0016\u0003\u0003F$Q\u0001\u0012\u0005C\u0002\u0015\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0002!\u0011\t)!a\u0004\u000e\u0005\u0005\u001d!\u0002BA\u0005\u0003\u0017\tA\u0001\\1oO*\u0011\u0011QB\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0012\u0005\u001d!AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0018A\u0019\u0001&!\u0007\n\u0007\u0005m\u0011FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002J\u0003CA\u0011\"a\t\f\u0003\u0003\u0005\r!a\u0006\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\u0003E\u0003\u0002,\u0005E\u0012*\u0004\u0002\u0002.)\u0019\u0011qF\u0015\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u00024\u00055\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u000f\u0002@A\u0019\u0001&a\u000f\n\u0007\u0005u\u0012FA\u0004C_>dW-\u00198\t\u0011\u0005\rR\"!AA\u0002%\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111AA#\u0011%\t\u0019CDA\u0001\u0002\u0004\t9\"\u0001\u0005iCND7i\u001c3f)\t\t9\"\u0001\u0005u_N#(/\u001b8h)\t\t\u0019!\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003s\t\u0019\u0006\u0003\u0005\u0002$E\t\t\u00111\u0001JQ\r\u0001QjU\u0001\n!\u0006\u0014\u0018-\u001c)bSJ\u0004\"AP\n\u0014\tM9\u0013Q\f\t\u0005\u0003?\n)'\u0004\u0002\u0002b)!\u00111MA\u0006\u0003\tIw.C\u0002;\u0003C\"\"!!\u0017\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u00055\u00141\u000f\u000b\u0007\u0003_\n)(a\u001f\u0011\ty\u0002\u0011\u0011\u000f\t\u0004\u0005\u0006MD!\u0002#\u0017\u0005\u0004)\u0005BB\u000e\u0017\u0001\u0004\t9\b\u0005\u0003?\u007f\u0005E\u0004\u0006BA;\u001bNCaa\u0016\fA\u0002\u0005E\u0004\u0006BA>\u001bN\u000bq!\u001e8baBd\u00170\u0006\u0003\u0002\u0004\u0006UE\u0003BAC\u0003/\u0003R\u0001KAD\u0003\u0017K1!!#*\u0005\u0019y\u0005\u000f^5p]B9\u0001&!$\u0002\u0012\u0006M\u0015bAAHS\t1A+\u001e9mKJ\u0002BAP \u0002\u0014B\u0019!)!&\u0005\u000b\u0011;\"\u0019A#\t\u0013\u0005eu#!AA\u0002\u0005m\u0015a\u0001=%aA!a\bAAJ\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u000b\u0005\u0003\u0002\u0006\u0005\r\u0016\u0002BAS\u0003\u000f\u0011aa\u00142kK\u000e$\b"
)
public class ParamPair implements Product, Serializable {
   private final Param param;
   private final Object value;

   public static Option unapply(final ParamPair x$0) {
      return ParamPair$.MODULE$.unapply(x$0);
   }

   public static ParamPair apply(final Param param, final Object value) {
      return ParamPair$.MODULE$.apply(param, value);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Param param() {
      return this.param;
   }

   public Object value() {
      return this.value;
   }

   public ParamPair copy(final Param param, final Object value) {
      return new ParamPair(param, value);
   }

   public Param copy$default$1() {
      return this.param();
   }

   public Object copy$default$2() {
      return this.value();
   }

   public String productPrefix() {
      return "ParamPair";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.param();
         }
         case 1 -> {
            return this.value();
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
      return x$1 instanceof ParamPair;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "param";
         }
         case 1 -> {
            return "value";
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
         label49: {
            if (x$1 instanceof ParamPair) {
               label42: {
                  ParamPair var4 = (ParamPair)x$1;
                  Param var10000 = this.param();
                  Param var5 = var4.param();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label42;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label42;
                  }

                  if (BoxesRunTime.equals(this.value(), var4.value()) && var4.canEqual(this)) {
                     break label49;
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

   public ParamPair(final Param param, final Object value) {
      this.param = param;
      this.value = value;
      Product.$init$(this);
      param.validate(value);
   }
}
