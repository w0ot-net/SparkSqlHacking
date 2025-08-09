package org.apache.spark.api.python;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0002\f\u0018\u0001n\t\u0003\u0002\u0003\u001d\u0001\u0005+\u0007I\u0011A\u001d\t\u0011\u0005\u0003!\u0011#Q\u0001\niBQA\u0011\u0001\u0005\u0002\rCqA\u0012\u0001\u0002\u0002\u0013\u0005q\tC\u0004J\u0001E\u0005I\u0011\u0001&\t\u000fU\u0003\u0011\u0011!C!-\"9q\fAA\u0001\n\u0003\u0001\u0007b\u00023\u0001\u0003\u0003%\t!\u001a\u0005\bW\u0002\t\t\u0011\"\u0011m\u0011\u001d\u0019\b!!A\u0005\u0002QDq!\u001f\u0001\u0002\u0002\u0013\u0005#\u0010C\u0004}\u0001\u0005\u0005I\u0011I?\t\u000fy\u0004\u0011\u0011!C!\u007f\"I\u0011\u0011\u0001\u0001\u0002\u0002\u0013\u0005\u00131A\u0004\u000b\u0003\u000f9\u0012\u0011!E\u00017\u0005%a!\u0003\f\u0018\u0003\u0003E\taGA\u0006\u0011\u0019\u0011\u0005\u0003\"\u0001\u0002$!9a\u0010EA\u0001\n\u000bz\b\"CA\u0013!\u0005\u0005I\u0011QA\u0014\u0011%\tY\u0003EA\u0001\n\u0003\u000bi\u0003C\u0005\u0002:A\t\t\u0011\"\u0003\u0002<\t12\t[1j]\u0016$\u0007+\u001f;i_:4UO\\2uS>t7O\u0003\u0002\u00193\u00051\u0001/\u001f;i_:T!AG\u000e\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u001d;\u0005)1\u000f]1sW*\u0011adH\u0001\u0007CB\f7\r[3\u000b\u0003\u0001\n1a\u001c:h'\u0011\u0001!\u0005K\u0016\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g!\t\u0019\u0013&\u0003\u0002+I\t9\u0001K]8ek\u000e$\bC\u0001\u00176\u001d\ti3G\u0004\u0002/e5\tqF\u0003\u00021c\u00051AH]8piz\u001a\u0001!C\u0001&\u0013\t!D%A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001b%\u0003\u00151WO\\2t+\u0005Q\u0004c\u0001\u0017<{%\u0011Ah\u000e\u0002\u0004'\u0016\f\bC\u0001 @\u001b\u00059\u0012B\u0001!\u0018\u00059\u0001\u0016\u0010\u001e5p]\u001a+hn\u0019;j_:\faAZ;oGN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002E\u000bB\u0011a\b\u0001\u0005\u0006q\r\u0001\rAO\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002E\u0011\"9\u0001\b\u0002I\u0001\u0002\u0004Q\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0017*\u0012!\bT\u0016\u0002\u001bB\u0011ajU\u0007\u0002\u001f*\u0011\u0001+U\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u0015\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002U\u001f\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011a\u0017M\\4\u000b\u0003q\u000bAA[1wC&\u0011a,\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u0005\u0004\"a\t2\n\u0005\r$#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00014j!\t\u0019s-\u0003\u0002iI\t\u0019\u0011I\\=\t\u000f)D\u0011\u0011!a\u0001C\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001c\t\u0004]F4W\"A8\u000b\u0005A$\u0013AC2pY2,7\r^5p]&\u0011!o\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002vqB\u00111E^\u0005\u0003o\u0012\u0012qAQ8pY\u0016\fg\u000eC\u0004k\u0015\u0005\u0005\t\u0019\u00014\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003/nDqA[\u0006\u0002\u0002\u0003\u0007\u0011-\u0001\u0005iCND7i\u001c3f)\u0005\t\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003]\u000ba!Z9vC2\u001cHcA;\u0002\u0006!9!NDA\u0001\u0002\u00041\u0017AF\"iC&tW\r\u001a)zi\"|gNR;oGRLwN\\:\u0011\u0005y\u00022#\u0002\t\u0002\u000e\u0005e\u0001CBA\b\u0003+QD)\u0004\u0002\u0002\u0012)\u0019\u00111\u0003\u0013\u0002\u000fI,h\u000e^5nK&!\u0011qCA\t\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u00037\t\t#\u0004\u0002\u0002\u001e)\u0019\u0011qD.\u0002\u0005%|\u0017b\u0001\u001c\u0002\u001eQ\u0011\u0011\u0011B\u0001\u0006CB\u0004H.\u001f\u000b\u0004\t\u0006%\u0002\"\u0002\u001d\u0014\u0001\u0004Q\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003_\t)\u0004\u0005\u0003$\u0003cQ\u0014bAA\u001aI\t1q\n\u001d;j_:D\u0001\"a\u000e\u0015\u0003\u0003\u0005\r\u0001R\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u001f!\rA\u0016qH\u0005\u0004\u0003\u0003J&AB(cU\u0016\u001cG\u000f"
)
public class ChainedPythonFunctions implements Product, Serializable {
   private final Seq funcs;

   public static Option unapply(final ChainedPythonFunctions x$0) {
      return ChainedPythonFunctions$.MODULE$.unapply(x$0);
   }

   public static ChainedPythonFunctions apply(final Seq funcs) {
      return ChainedPythonFunctions$.MODULE$.apply(funcs);
   }

   public static Function1 andThen(final Function1 g) {
      return ChainedPythonFunctions$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ChainedPythonFunctions$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq funcs() {
      return this.funcs;
   }

   public ChainedPythonFunctions copy(final Seq funcs) {
      return new ChainedPythonFunctions(funcs);
   }

   public Seq copy$default$1() {
      return this.funcs();
   }

   public String productPrefix() {
      return "ChainedPythonFunctions";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.funcs();
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
      return x$1 instanceof ChainedPythonFunctions;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "funcs";
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
         label47: {
            if (x$1 instanceof ChainedPythonFunctions) {
               label40: {
                  ChainedPythonFunctions var4 = (ChainedPythonFunctions)x$1;
                  Seq var10000 = this.funcs();
                  Seq var5 = var4.funcs();
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

   public ChainedPythonFunctions(final Seq funcs) {
      this.funcs = funcs;
      Product.$init$(this);
   }
}
