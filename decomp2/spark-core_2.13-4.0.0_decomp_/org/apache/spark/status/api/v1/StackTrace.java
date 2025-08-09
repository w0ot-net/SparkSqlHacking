package org.apache.spark.status.api.v1;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.xml.Elem;
import scala.xml.NodeSeq;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud\u0001\u0002\r\u001a\u0001\u001aB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005}!)!\n\u0001C\u0001\u0017\")q\n\u0001C!!\")\u0011\u000b\u0001C\u0001%\")\u0011\f\u0001C\u00015\"9\u0011\rAA\u0001\n\u0003\u0011\u0007b\u00023\u0001#\u0003%\t!\u001a\u0005\ba\u0002\t\t\u0011\"\u0011r\u0011\u001dI\b!!A\u0005\u0002iDqA \u0001\u0002\u0002\u0013\u0005q\u0010C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0011Q\u0004\u0005\n\u0003O\u0001\u0011\u0011!C!\u0003SA\u0011\"!\f\u0001\u0003\u0003%\t%a\f\t\u0013\u0005E\u0002!!A\u0005B\u0005Mr!CA\u001c3\u0005\u0005\t\u0012AA\u001d\r!A\u0012$!A\t\u0002\u0005m\u0002B\u0002&\u0013\t\u0003\t\u0019\u0006\u0003\u0005P%\u0005\u0005IQIA+\u0011%\t9FEA\u0001\n\u0003\u000bI\u0006C\u0005\u0002^I\t\t\u0011\"!\u0002`!I\u00111\u000e\n\u0002\u0002\u0013%\u0011Q\u000e\u0002\u000b'R\f7m\u001b+sC\u000e,'B\u0001\u000e\u001c\u0003\t1\u0018G\u0003\u0002\u001d;\u0005\u0019\u0011\r]5\u000b\u0005yy\u0012AB:uCR,8O\u0003\u0002!C\u0005)1\u000f]1sW*\u0011!eI\u0001\u0007CB\f7\r[3\u000b\u0003\u0011\n1a\u001c:h\u0007\u0001\u0019B\u0001A\u0014.aA\u0011\u0001fK\u0007\u0002S)\t!&A\u0003tG\u0006d\u0017-\u0003\u0002-S\t1\u0011I\\=SK\u001a\u0004\"\u0001\u000b\u0018\n\u0005=J#a\u0002)s_\u0012,8\r\u001e\t\u0003cer!AM\u001c\u000f\u0005M2T\"\u0001\u001b\u000b\u0005U*\u0013A\u0002\u001fs_>$h(C\u0001+\u0013\tA\u0014&A\u0004qC\u000e\\\u0017mZ3\n\u0005iZ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001d*\u0003\u0015)G.Z7t+\u0005q\u0004cA\u0019@\u0003&\u0011\u0001i\u000f\u0002\u0004'\u0016\f\bC\u0001\"G\u001d\t\u0019E\t\u0005\u00024S%\u0011Q)K\u0001\u0007!J,G-\u001a4\n\u0005\u001dC%AB*ue&twM\u0003\u0002FS\u00051Q\r\\3ng\u0002\na\u0001P5oSRtDC\u0001'O!\ti\u0005!D\u0001\u001a\u0011\u0015a4\u00011\u0001?\u0003!!xn\u0015;sS:<G#A!\u0002\t!$X\u000e\\\u000b\u0002'B\u0011AkV\u0007\u0002+*\u0011a+K\u0001\u0004q6d\u0017B\u0001-V\u0005\u001dqu\u000eZ3TKF\f\u0001\"\\6TiJLgn\u001a\u000b\u0005\u0003nkv\fC\u0003]\r\u0001\u0007\u0011)A\u0003ti\u0006\u0014H\u000fC\u0003_\r\u0001\u0007\u0011)A\u0002tKBDQ\u0001\u0019\u0004A\u0002\u0005\u000b1!\u001a8e\u0003\u0011\u0019w\u000e]=\u0015\u00051\u001b\u0007b\u0002\u001f\b!\u0003\u0005\rAP\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u00051'F\u0001 hW\u0005A\u0007CA5o\u001b\u0005Q'BA6m\u0003%)hn\u00195fG.,GM\u0003\u0002nS\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005=T'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u001d\t\u0003gbl\u0011\u0001\u001e\u0006\u0003kZ\fA\u0001\\1oO*\tq/\u0001\u0003kCZ\f\u0017BA$u\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005Y\bC\u0001\u0015}\u0013\ti\u0018FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\u0002\u0005\u001d\u0001c\u0001\u0015\u0002\u0004%\u0019\u0011QA\u0015\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\n-\t\t\u00111\u0001|\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011q\u0002\t\u0007\u0003#\t9\"!\u0001\u000e\u0005\u0005M!bAA\u000bS\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005e\u00111\u0003\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002 \u0005\u0015\u0002c\u0001\u0015\u0002\"%\u0019\u00111E\u0015\u0003\u000f\t{w\u000e\\3b]\"I\u0011\u0011B\u0007\u0002\u0002\u0003\u0007\u0011\u0011A\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002s\u0003WA\u0001\"!\u0003\u000f\u0003\u0003\u0005\ra_\u0001\tQ\u0006\u001c\bnQ8eKR\t10\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003?\t)\u0004C\u0005\u0002\nA\t\t\u00111\u0001\u0002\u0002\u0005Q1\u000b^1dWR\u0013\u0018mY3\u0011\u00055\u00132#\u0002\n\u0002>\u0005%\u0003CBA \u0003\u000brD*\u0004\u0002\u0002B)\u0019\u00111I\u0015\u0002\u000fI,h\u000e^5nK&!\u0011qIA!\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003\u0017\n\t&\u0004\u0002\u0002N)\u0019\u0011q\n<\u0002\u0005%|\u0017b\u0001\u001e\u0002NQ\u0011\u0011\u0011\b\u000b\u0002e\u0006)\u0011\r\u001d9msR\u0019A*a\u0017\t\u000bq*\u0002\u0019\u0001 \u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011MA4!\u0011A\u00131\r \n\u0007\u0005\u0015\u0014F\u0001\u0004PaRLwN\u001c\u0005\t\u0003S2\u0012\u0011!a\u0001\u0019\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005=\u0004cA:\u0002r%\u0019\u00111\u000f;\u0003\r=\u0013'.Z2u\u0001"
)
public class StackTrace implements Product, Serializable {
   private final Seq elems;

   public static Option unapply(final StackTrace x$0) {
      return StackTrace$.MODULE$.unapply(x$0);
   }

   public static StackTrace apply(final Seq elems) {
      return StackTrace$.MODULE$.apply(elems);
   }

   public static Function1 andThen(final Function1 g) {
      return StackTrace$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return StackTrace$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq elems() {
      return this.elems;
   }

   public String toString() {
      return this.elems().mkString();
   }

   public NodeSeq html() {
      NodeSeq withNewLine = (NodeSeq)((IterableOnceOps)this.elems().map((x$2) -> .MODULE$.stripLineEnd$extension(scala.Predef..MODULE$.augmentString(x$2)))).foldLeft(scala.xml.NodeSeq..MODULE$.Empty(), (acc, elem) -> acc.isEmpty() ? scala.xml.NodeSeq..MODULE$.seqToNodeSeq((scala.collection.Seq)acc.$colon$plus(scala.xml.Text..MODULE$.apply(elem))) : scala.xml.NodeSeq..MODULE$.seqToNodeSeq((scala.collection.Seq)((SeqOps)acc.$colon$plus(new Elem((String)null, "br", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$))).$colon$plus(scala.xml.Text..MODULE$.apply(elem))));
      return withNewLine;
   }

   public String mkString(final String start, final String sep, final String end) {
      return this.elems().mkString(start, sep, end);
   }

   public StackTrace copy(final Seq elems) {
      return new StackTrace(elems);
   }

   public Seq copy$default$1() {
      return this.elems();
   }

   public String productPrefix() {
      return "StackTrace";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.elems();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof StackTrace;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "elems";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof StackTrace) {
               label40: {
                  StackTrace var4 = (StackTrace)x$1;
                  Seq var10000 = this.elems();
                  Seq var5 = var4.elems();
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

   public StackTrace(final Seq elems) {
      this.elems = elems;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
