package org.apache.spark.sql;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mf\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u007f!Aq\n\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005X\u0001\tE\t\u0015!\u0003R\u0011\u0019A\u0006\u0001\"\u0001\u001e3\")Q\f\u0001C\u0001=\")q\f\u0001C\u0001A\")a\u000e\u0001C\u0001=\"9q\u000eAA\u0001\n\u0003\u0001\bb\u0002=\u0001#\u0003%\t!\u001f\u0005\n\u0003\u001b\u0001\u0011\u0013!C\u0001\u0003\u001fA\u0011\"a\u0006\u0001\u0003\u0003%\t%!\u0007\t\u0013\u0005%\u0002!!A\u0005\u0002\u0005-\u0002\"CA\u001a\u0001\u0005\u0005I\u0011AA\u001b\u0011%\tY\u0004AA\u0001\n\u0003\ni\u0004C\u0005\u0002L\u0001\t\t\u0011\"\u0001\u0002N!I\u0011q\u000b\u0001\u0002\u0002\u0013\u0005\u0013\u0011\f\u0005\n\u0003;\u0002\u0011\u0011!C!\u0003?B\u0011\"!\u0019\u0001\u0003\u0003%\t%a\u0019\t\u0013\u0005\u0015\u0004!!A\u0005B\u0005\u001dt!CA6;\u0005\u0005\t\u0012AA7\r!aR$!A\t\u0002\u0005=\u0004B\u0002-\u0017\t\u0003\tY\bC\u0005\u0002bY\t\t\u0011\"\u0012\u0002d!I\u0011Q\u0010\f\u0002\u0002\u0013\u0005\u0015q\u0010\u0005\n\u0003\u001f3\u0012\u0011!CA\u0003#C\u0011\"!+\u0017\u0003\u0003%I!a+\u0003-]CWM\u001c(pi6\u000bGo\u00195fI\nK8k\\;sG\u0016T!AH\u0010\u0002\u0007M\fHN\u0003\u0002!C\u0005)1\u000f]1sW*\u0011!eI\u0001\u0007CB\f7\r[3\u000b\u0003\u0011\n1a\u001c:h\u0007\u0001)\"aJ#\u0014\t\u0001Ac&\r\t\u0003S1j\u0011A\u000b\u0006\u0002W\u0005)1oY1mC&\u0011QF\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005%z\u0013B\u0001\u0019+\u0005\u001d\u0001&o\u001c3vGR\u0004\"A\r\u001e\u000f\u0005MBdB\u0001\u001b8\u001b\u0005)$B\u0001\u001c&\u0003\u0019a$o\\8u}%\t1&\u0003\u0002:U\u00059\u0001/Y2lC\u001e,\u0017BA\u001e=\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tI$&A\bnKJ<W-\u00138u_^\u0013\u0018\u000e^3s+\u0005y\u0004c\u0001!B\u00076\tQ$\u0003\u0002C;\tyQ*\u001a:hK&sGo\\,sSR,'\u000f\u0005\u0002E\u000b2\u0001A!\u0002$\u0001\u0005\u00049%!\u0001+\u0012\u0005![\u0005CA\u0015J\u0013\tQ%FA\u0004O_RD\u0017N\\4\u0011\u0005%b\u0015BA'+\u0005\r\te._\u0001\u0011[\u0016\u0014x-Z%oi><&/\u001b;fe\u0002\n\u0011bY8oI&$\u0018n\u001c8\u0016\u0003E\u00032!\u000b*U\u0013\t\u0019&F\u0001\u0004PaRLwN\u001c\t\u0003\u0001VK!AV\u000f\u0003\r\r{G.^7o\u0003)\u0019wN\u001c3ji&|g\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007i[F\fE\u0002A\u0001\rCQ!P\u0003A\u0002}BQaT\u0003A\u0002E\u000b\u0011\"\u001e9eCR,\u0017\t\u001c7\u0015\u0003}\na!\u001e9eCR,GCA b\u0011\u0015\u0011w\u00011\u0001d\u0003\ri\u0017\r\u001d\t\u0005I\"\\GK\u0004\u0002fMB\u0011AGK\u0005\u0003O*\na\u0001\u0015:fI\u00164\u0017BA5k\u0005\ri\u0015\r\u001d\u0006\u0003O*\u0002\"\u0001\u001a7\n\u00055T'AB*ue&tw-\u0001\u0004eK2,G/Z\u0001\u0005G>\u0004\u00180\u0006\u0002riR\u0019!/^<\u0011\u0007\u0001\u00031\u000f\u0005\u0002Ei\u0012)a)\u0003b\u0001\u000f\"9Q(\u0003I\u0001\u0002\u00041\bc\u0001!Bg\"9q*\u0003I\u0001\u0002\u0004\t\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0004u\u0006-Q#A>+\u0005}b8&A?\u0011\u0007y\f9!D\u0001\u0000\u0015\u0011\t\t!a\u0001\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0003U\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0007\u0005%qPA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$QA\u0012\u0006C\u0002\u001d\u000babY8qs\u0012\"WMZ1vYR$#'\u0006\u0003\u0002\u0012\u0005UQCAA\nU\t\tF\u0010B\u0003G\u0017\t\u0007q)A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u00037\u0001B!!\b\u0002(5\u0011\u0011q\u0004\u0006\u0005\u0003C\t\u0019#\u0001\u0003mC:<'BAA\u0013\u0003\u0011Q\u0017M^1\n\u00075\fy\"\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002.A\u0019\u0011&a\f\n\u0007\u0005E\"FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002L\u0003oA\u0011\"!\u000f\u000f\u0003\u0003\u0005\r!!\f\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0004E\u0003\u0002B\u0005\u001d3*\u0004\u0002\u0002D)\u0019\u0011Q\t\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002J\u0005\r#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0014\u0002VA\u0019\u0011&!\u0015\n\u0007\u0005M#FA\u0004C_>dW-\u00198\t\u0011\u0005e\u0002#!AA\u0002-\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111DA.\u0011%\tI$EA\u0001\u0002\u0004\ti#\u0001\u0005iCND7i\u001c3f)\t\ti#\u0001\u0005u_N#(/\u001b8h)\t\tY\"\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u001f\nI\u0007\u0003\u0005\u0002:Q\t\t\u00111\u0001L\u0003Y9\u0006.\u001a8O_Rl\u0015\r^2iK\u0012\u0014\u0015pU8ve\u000e,\u0007C\u0001!\u0017'\u00111\u0002&!\u001d\u0011\t\u0005M\u0014\u0011P\u0007\u0003\u0003kRA!a\u001e\u0002$\u0005\u0011\u0011n\\\u0005\u0004w\u0005UDCAA7\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\t\t)a\"\u0015\r\u0005\r\u0015\u0011RAG!\u0011\u0001\u0005!!\"\u0011\u0007\u0011\u000b9\tB\u0003G3\t\u0007q\t\u0003\u0004>3\u0001\u0007\u00111\u0012\t\u0005\u0001\u0006\u000b)\tC\u0003P3\u0001\u0007\u0011+A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\u0005M\u0015\u0011\u0015\u000b\u0005\u0003+\u000b\u0019\u000b\u0005\u0003*%\u0006]\u0005CB\u0015\u0002\u001a\u0006u\u0015+C\u0002\u0002\u001c*\u0012a\u0001V;qY\u0016\u0014\u0004\u0003\u0002!B\u0003?\u00032\u0001RAQ\t\u00151%D1\u0001H\u0011%\t)KGA\u0001\u0002\u0004\t9+A\u0002yIA\u0002B\u0001\u0011\u0001\u0002 \u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0016\t\u0005\u0003;\ty+\u0003\u0003\u00022\u0006}!AB(cU\u0016\u001cG\u000f"
)
public class WhenNotMatchedBySource implements Product, Serializable {
   private final MergeIntoWriter mergeIntoWriter;
   private final Option condition;

   public static Option unapply(final WhenNotMatchedBySource x$0) {
      return WhenNotMatchedBySource$.MODULE$.unapply(x$0);
   }

   public static WhenNotMatchedBySource apply(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      return WhenNotMatchedBySource$.MODULE$.apply(mergeIntoWriter, condition);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public MergeIntoWriter mergeIntoWriter() {
      return this.mergeIntoWriter;
   }

   public Option condition() {
      return this.condition;
   }

   public MergeIntoWriter updateAll() {
      return this.mergeIntoWriter().updateAll(this.condition(), true);
   }

   public MergeIntoWriter update(final Map map) {
      return this.mergeIntoWriter().update(this.condition(), map, true);
   }

   public MergeIntoWriter delete() {
      return this.mergeIntoWriter().delete(this.condition(), true);
   }

   public WhenNotMatchedBySource copy(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      return new WhenNotMatchedBySource(mergeIntoWriter, condition);
   }

   public MergeIntoWriter copy$default$1() {
      return this.mergeIntoWriter();
   }

   public Option copy$default$2() {
      return this.condition();
   }

   public String productPrefix() {
      return "WhenNotMatchedBySource";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.mergeIntoWriter();
         }
         case 1 -> {
            return this.condition();
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
      return x$1 instanceof WhenNotMatchedBySource;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "mergeIntoWriter";
         }
         case 1 -> {
            return "condition";
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
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof WhenNotMatchedBySource) {
               label48: {
                  WhenNotMatchedBySource var4 = (WhenNotMatchedBySource)x$1;
                  MergeIntoWriter var10000 = this.mergeIntoWriter();
                  MergeIntoWriter var5 = var4.mergeIntoWriter();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Option var7 = this.condition();
                  Option var6 = var4.condition();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public WhenNotMatchedBySource(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      this.mergeIntoWriter = mergeIntoWriter;
      this.condition = condition;
      Product.$init$(this);
   }
}
