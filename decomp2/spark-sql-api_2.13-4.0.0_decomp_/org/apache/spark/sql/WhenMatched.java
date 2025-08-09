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
   bytes = "\u0006\u0005\u0005Mf\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u007f!Aq\n\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005X\u0001\tE\t\u0015!\u0003R\u0011\u0019A\u0006\u0001\"\u0001\u001e3\")Q\f\u0001C\u0001=\")q\f\u0001C\u0001A\")a\u000e\u0001C\u0001=\"9q\u000eAA\u0001\n\u0003\u0001\bb\u0002=\u0001#\u0003%\t!\u001f\u0005\n\u0003\u001b\u0001\u0011\u0013!C\u0001\u0003\u001fA\u0011\"a\u0006\u0001\u0003\u0003%\t%!\u0007\t\u0013\u0005%\u0002!!A\u0005\u0002\u0005-\u0002\"CA\u001a\u0001\u0005\u0005I\u0011AA\u001b\u0011%\tY\u0004AA\u0001\n\u0003\ni\u0004C\u0005\u0002L\u0001\t\t\u0011\"\u0001\u0002N!I\u0011q\u000b\u0001\u0002\u0002\u0013\u0005\u0013\u0011\f\u0005\n\u0003;\u0002\u0011\u0011!C!\u0003?B\u0011\"!\u0019\u0001\u0003\u0003%\t%a\u0019\t\u0013\u0005\u0015\u0004!!A\u0005B\u0005\u001dt!CA6;\u0005\u0005\t\u0012AA7\r!aR$!A\t\u0002\u0005=\u0004B\u0002-\u0017\t\u0003\tY\bC\u0005\u0002bY\t\t\u0011\"\u0012\u0002d!I\u0011Q\u0010\f\u0002\u0002\u0013\u0005\u0015q\u0010\u0005\n\u0003\u001f3\u0012\u0011!CA\u0003#C\u0011\"!+\u0017\u0003\u0003%I!a+\u0003\u0017]CWM\\'bi\u000eDW\r\u001a\u0006\u0003=}\t1a]9m\u0015\t\u0001\u0013%A\u0003ta\u0006\u00148N\u0003\u0002#G\u00051\u0011\r]1dQ\u0016T\u0011\u0001J\u0001\u0004_J<7\u0001A\u000b\u0003O\u0015\u001bB\u0001\u0001\u0015/cA\u0011\u0011\u0006L\u0007\u0002U)\t1&A\u0003tG\u0006d\u0017-\u0003\u0002.U\t1\u0011I\\=SK\u001a\u0004\"!K\u0018\n\u0005AR#a\u0002)s_\u0012,8\r\u001e\t\u0003eir!a\r\u001d\u000f\u0005Q:T\"A\u001b\u000b\u0005Y*\u0013A\u0002\u001fs_>$h(C\u0001,\u0013\tI$&A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d+\u0003=iWM]4f\u0013:$xn\u0016:ji\u0016\u0014X#A \u0011\u0007\u0001\u000b5)D\u0001\u001e\u0013\t\u0011UDA\bNKJ<W-\u00138u_^\u0013\u0018\u000e^3s!\t!U\t\u0004\u0001\u0005\u000b\u0019\u0003!\u0019A$\u0003\u0003Q\u000b\"\u0001S&\u0011\u0005%J\u0015B\u0001&+\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u000b'\n\u00055S#aA!os\u0006\u0001R.\u001a:hK&sGo\\,sSR,'\u000fI\u0001\nG>tG-\u001b;j_:,\u0012!\u0015\t\u0004SI#\u0016BA*+\u0005\u0019y\u0005\u000f^5p]B\u0011\u0001)V\u0005\u0003-v\u0011aaQ8mk6t\u0017AC2p]\u0012LG/[8oA\u00051A(\u001b8jiz\"2AW.]!\r\u0001\u0005a\u0011\u0005\u0006{\u0015\u0001\ra\u0010\u0005\u0006\u001f\u0016\u0001\r!U\u0001\nkB$\u0017\r^3BY2$\u0012aP\u0001\u0007kB$\u0017\r^3\u0015\u0005}\n\u0007\"\u00022\b\u0001\u0004\u0019\u0017aA7baB!A\r[6U\u001d\t)g\r\u0005\u00025U%\u0011qMK\u0001\u0007!J,G-\u001a4\n\u0005%T'aA'ba*\u0011qM\u000b\t\u0003I2L!!\u001c6\u0003\rM#(/\u001b8h\u0003\u0019!W\r\\3uK\u0006!1m\u001c9z+\t\tH\u000fF\u0002sk^\u00042\u0001\u0011\u0001t!\t!E\u000fB\u0003G\u0013\t\u0007q\tC\u0004>\u0013A\u0005\t\u0019\u0001<\u0011\u0007\u0001\u000b5\u000fC\u0004P\u0013A\u0005\t\u0019A)\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0019!0a\u0003\u0016\u0003mT#a\u0010?,\u0003u\u00042A`A\u0004\u001b\u0005y(\u0002BA\u0001\u0003\u0007\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0015!&\u0001\u0006b]:|G/\u0019;j_:L1!!\u0003\u0000\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006\r*\u0011\raR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011\t\t\"!\u0006\u0016\u0005\u0005M!FA)}\t\u001515B1\u0001H\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u00111\u0004\t\u0005\u0003;\t9#\u0004\u0002\u0002 )!\u0011\u0011EA\u0012\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u0015\u0012\u0001\u00026bm\u0006L1!\\A\u0010\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\ti\u0003E\u0002*\u0003_I1!!\r+\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rY\u0015q\u0007\u0005\n\u0003sq\u0011\u0011!a\u0001\u0003[\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA !\u0015\t\t%a\u0012L\u001b\t\t\u0019EC\u0002\u0002F)\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI%a\u0011\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u001f\n)\u0006E\u0002*\u0003#J1!a\u0015+\u0005\u001d\u0011un\u001c7fC:D\u0001\"!\u000f\u0011\u0003\u0003\u0005\raS\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\u001c\u0005m\u0003\"CA\u001d#\u0005\u0005\t\u0019AA\u0017\u0003!A\u0017m\u001d5D_\u0012,GCAA\u0017\u0003!!xn\u0015;sS:<GCAA\u000e\u0003\u0019)\u0017/^1mgR!\u0011qJA5\u0011!\tI\u0004FA\u0001\u0002\u0004Y\u0015aC,iK:l\u0015\r^2iK\u0012\u0004\"\u0001\u0011\f\u0014\tYA\u0013\u0011\u000f\t\u0005\u0003g\nI(\u0004\u0002\u0002v)!\u0011qOA\u0012\u0003\tIw.C\u0002<\u0003k\"\"!!\u001c\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005\u0005\u0015q\u0011\u000b\u0007\u0003\u0007\u000bI)!$\u0011\t\u0001\u0003\u0011Q\u0011\t\u0004\t\u0006\u001dE!\u0002$\u001a\u0005\u00049\u0005BB\u001f\u001a\u0001\u0004\tY\t\u0005\u0003A\u0003\u0006\u0015\u0005\"B(\u001a\u0001\u0004\t\u0016aB;oCB\u0004H._\u000b\u0005\u0003'\u000b\t\u000b\u0006\u0003\u0002\u0016\u0006\r\u0006\u0003B\u0015S\u0003/\u0003b!KAM\u0003;\u000b\u0016bAANU\t1A+\u001e9mKJ\u0002B\u0001Q!\u0002 B\u0019A)!)\u0005\u000b\u0019S\"\u0019A$\t\u0013\u0005\u0015&$!AA\u0002\u0005\u001d\u0016a\u0001=%aA!\u0001\tAAP\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\u000b\u0005\u0003\u0002\u001e\u0005=\u0016\u0002BAY\u0003?\u0011aa\u00142kK\u000e$\b"
)
public class WhenMatched implements Product, Serializable {
   private final MergeIntoWriter mergeIntoWriter;
   private final Option condition;

   public static Option unapply(final WhenMatched x$0) {
      return WhenMatched$.MODULE$.unapply(x$0);
   }

   public static WhenMatched apply(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      return WhenMatched$.MODULE$.apply(mergeIntoWriter, condition);
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
      return this.mergeIntoWriter().updateAll(this.condition(), false);
   }

   public MergeIntoWriter update(final Map map) {
      return this.mergeIntoWriter().update(this.condition(), map, false);
   }

   public MergeIntoWriter delete() {
      return this.mergeIntoWriter().delete(this.condition(), false);
   }

   public WhenMatched copy(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      return new WhenMatched(mergeIntoWriter, condition);
   }

   public MergeIntoWriter copy$default$1() {
      return this.mergeIntoWriter();
   }

   public Option copy$default$2() {
      return this.condition();
   }

   public String productPrefix() {
      return "WhenMatched";
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
      return x$1 instanceof WhenMatched;
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
            if (x$1 instanceof WhenMatched) {
               label48: {
                  WhenMatched var4 = (WhenMatched)x$1;
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

   public WhenMatched(final MergeIntoWriter mergeIntoWriter, final Option condition) {
      this.mergeIntoWriter = mergeIntoWriter;
      this.condition = condition;
      Product.$init$(this);
   }
}
