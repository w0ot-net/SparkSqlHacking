package org.apache.spark.graphx.impl;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t%b!B\u0012%\u0001\u0012r\u0003\u0002\u0003$\u0001\u0005+\u0007I\u0011A$\t\u0011Y\u0003!\u0011#Q\u0001\n!C\u0001b\u0016\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t1\u0002\u0011\t\u0012)A\u0005\u0011\"A\u0011\f\u0001BK\u0002\u0013\u0005!\f\u0003\u0005_\u0001\tE\t\u0015!\u0003\\\u0011!y\u0006A!f\u0001\n\u0003Q\u0006\u0002\u00031\u0001\u0005#\u0005\u000b\u0011B.\t\u0011\u0005\u0004!Q3A\u0005\u0002\tD\u0001B\u001d\u0001\u0003\u0012\u0003\u0006Ia\u0019\u0005\u0006g\u0002!\t\u0001\u001e\u0005\by\u0002\t\t\u0011\"\u0001~\u0011%\t\t\u0002AI\u0001\n\u0003\t\u0019\u0002C\u0005\u00020\u0001\t\n\u0011\"\u0001\u00022!I\u0011q\u0007\u0001\u0012\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u0003\u0007\u0002\u0011\u0013!C\u0001\u0003\u000bB\u0011\"a\u0013\u0001#\u0003%\t!!\u0014\t\u0013\u0005]\u0003!!A\u0005B\u0005e\u0003\u0002CA6\u0001\u0005\u0005I\u0011\u0001.\t\u0013\u00055\u0004!!A\u0005\u0002\u0005=\u0004\"CA;\u0001\u0005\u0005I\u0011IA<\u0011%\t)\tAA\u0001\n\u0003\t9\tC\u0005\u0002\u0012\u0002\t\t\u0011\"\u0011\u0002\u0014\"I\u0011q\u0013\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0014\u0005\n\u00037\u0003\u0011\u0011!C!\u0003;C\u0011\"a(\u0001\u0003\u0003%\t%!)\b\u0011\u0005\u0015F\u0005#\u0001%\u0003O3qa\t\u0013\t\u0002\u0011\nI\u000b\u0003\u0004t9\u0011\u0005\u0011Q\u0017\u0005\b\u0003ocB1AA]\u0011!\tI\r\bC\u0001M\u0005-\u0007\"CAu9\u0005\u0005I\u0011QAv\u0011%\u0011\t\u0001HA\u0001\n\u0003\u0013\u0019\u0001C\u0005\u0003 q\t\t\u0011\"\u0003\u0003\"\t\u0001R\tZ4f/&$\b\u000eT8dC2LEm\u001d\u0006\u0003K\u0019\nA![7qY*\u0011q\u0005K\u0001\u0007OJ\f\u0007\u000f\u001b=\u000b\u0005%R\u0013!B:qCJ\\'BA\u0016-\u0003\u0019\t\u0007/Y2iK*\tQ&A\u0002pe\u001e,\"aL3\u0014\t\u0001\u0001d'\u000f\t\u0003cQj\u0011A\r\u0006\u0002g\u0005)1oY1mC&\u0011QG\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E:\u0014B\u0001\u001d3\u0005\u001d\u0001&o\u001c3vGR\u0004\"AO\"\u000f\u0005m\neB\u0001\u001fA\u001b\u0005i$B\u0001 @\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u001a\n\u0005\t\u0013\u0014a\u00029bG.\fw-Z\u0005\u0003\t\u0016\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0011\u001a\u0002\u000bM\u00148-\u00133\u0016\u0003!\u0003\"!S*\u000f\u0005)\u0013fBA&R\u001d\ta\u0005K\u0004\u0002N\u001f:\u0011AHT\u0005\u0002[%\u00111\u0006L\u0005\u0003S)J!a\n\u0015\n\u0005\t3\u0013B\u0001+V\u0005!1VM\u001d;fq&#'B\u0001\"'\u0003\u0019\u0019(oY%eA\u0005)Am\u001d;JI\u00061Am\u001d;JI\u0002\n!\u0002\\8dC2\u001c&oY%e+\u0005Y\u0006CA\u0019]\u0013\ti&GA\u0002J]R\f1\u0002\\8dC2\u001c&oY%eA\u0005QAn\\2bY\u0012\u001bH/\u00133\u0002\u00171|7-\u00197EgRLE\rI\u0001\u0005CR$(/F\u0001d!\t!W\r\u0004\u0001\u0005\u0013\u0019\u0004\u0001\u0015!A\u0001\u0006\u00049'AA#E#\tA7\u000e\u0005\u00022S&\u0011!N\r\u0002\b\u001d>$\b.\u001b8h!\t\tD.\u0003\u0002ne\t\u0019\u0011I\\=)\u0005\u0015|\u0007CA\u0019q\u0013\t\t(GA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017!B1uiJ\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0004vobL(p\u001f\t\u0004m\u0002\u0019W\"\u0001\u0013\t\u000b\u0019[\u0001\u0019\u0001%\t\u000b][\u0001\u0019\u0001%\t\u000be[\u0001\u0019A.\t\u000b}[\u0001\u0019A.\t\u000b\u0005\\\u0001\u0019A2\u0002\t\r|\u0007/_\u000b\u0004}\u0006\rAcC@\u0002\b\u0005%\u00111BA\u0007\u0003\u001f\u0001BA\u001e\u0001\u0002\u0002A\u0019A-a\u0001\u0005\u0013\u0019d\u0001\u0015!A\u0001\u0006\u00049\u0007fAA\u0002_\"9a\t\u0004I\u0001\u0002\u0004A\u0005bB,\r!\u0003\u0005\r\u0001\u0013\u0005\b32\u0001\n\u00111\u0001\\\u0011\u001dyF\u0002%AA\u0002mC\u0001\"\u0019\u0007\u0011\u0002\u0003\u0007\u0011\u0011A\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0011\t)\"a\u000b\u0016\u0005\u0005]!f\u0001%\u0002\u001a-\u0012\u00111\u0004\t\u0005\u0003;\t9#\u0004\u0002\u0002 )!\u0011\u0011EA\u0012\u0003%)hn\u00195fG.,GMC\u0002\u0002&I\n!\"\u00198o_R\fG/[8o\u0013\u0011\tI#a\b\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0005g\u001b\u0001\u0006\t\u0011!b\u0001O\"\u001a\u00111F8\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU!\u0011QCA\u001a\t%1g\u0002)A\u0001\u0002\u000b\u0007q\rK\u0002\u00024=\fabY8qs\u0012\"WMZ1vYR$3'\u0006\u0003\u0002<\u0005}RCAA\u001fU\rY\u0016\u0011\u0004\u0003\nM>\u0001\u000b\u0011!AC\u0002\u001dD3!a\u0010p\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*B!a\u000f\u0002H\u0011Ia\r\u0005Q\u0001\u0002\u0003\u0015\ra\u001a\u0015\u0004\u0003\u000fz\u0017AD2paf$C-\u001a4bk2$H%N\u000b\u0005\u0003\u001f\n\u0019&\u0006\u0002\u0002R)\u001a1-!\u0007\u0005\u0013\u0019\f\u0002\u0015!A\u0001\u0006\u00049\u0007fAA*_\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!a\u0017\u0011\t\u0005u\u0013qM\u0007\u0003\u0003?RA!!\u0019\u0002d\u0005!A.\u00198h\u0015\t\t)'\u0001\u0003kCZ\f\u0017\u0002BA5\u0003?\u0012aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004W\u0006E\u0004\u0002CA:)\u0005\u0005\t\u0019A.\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\bE\u0003\u0002|\u0005\u00055.\u0004\u0002\u0002~)\u0019\u0011q\u0010\u001a\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0004\u0006u$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!#\u0002\u0010B\u0019\u0011'a#\n\u0007\u00055%GA\u0004C_>dW-\u00198\t\u0011\u0005Md#!AA\u0002-\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111LAK\u0011!\t\u0019hFA\u0001\u0002\u0004Y\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003m\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u00037\na!Z9vC2\u001cH\u0003BAE\u0003GC\u0001\"a\u001d\u001b\u0003\u0003\u0005\ra[\u0001\u0011\u000b\u0012<WmV5uQ2{7-\u00197JIN\u0004\"A\u001e\u000f\u0014\tq\u0001\u00141\u0016\t\u0005\u0003[\u000b\u0019,\u0004\u0002\u00020*!\u0011\u0011WA2\u0003\tIw.C\u0002E\u0003_#\"!a*\u0002+1,\u00070[2pOJ\f\u0007\u000f[5d\u001fJ$WM]5oOV!\u00111XAd+\t\ti\fE\u0003;\u0003\u007f\u000b\u0019-C\u0002\u0002B\u0016\u0013\u0001b\u0014:eKJLgn\u001a\t\u0005m\u0002\t)\rE\u0002e\u0003\u000f$QA\u001a\u0010C\u0002\u001d\fq#\u001a3hK\u0006\u0013(/Y=T_J$H)\u0019;b\r>\u0014X.\u0019;\u0016\t\u00055\u0017\u0011]\u000b\u0003\u0003\u001f\u0004\u0002\"!5\u0002Z\u0006u\u00171]\u0007\u0003\u0003'TA!a \u0002V*\u0019\u0011q\u001b\u0015\u0002\tU$\u0018\u000e\\\u0005\u0005\u00037\f\u0019N\u0001\bT_J$H)\u0019;b\r>\u0014X.\u0019;\u0011\tY\u0004\u0011q\u001c\t\u0004I\u0006\u0005H!\u00024 \u0005\u00049\u0007#B\u0019\u0002f\u0006u\u0017bAAte\t)\u0011I\u001d:bs\u0006)\u0011\r\u001d9msV!\u0011Q^Az)1\ty/a>\u0002z\u0006m\u0018Q`A\u0000!\u00111\b!!=\u0011\u0007\u0011\f\u0019\u0010B\u0005gA\u0001\u0006\t\u0011!b\u0001O\"\u001a\u00111_8\t\u000b\u0019\u0003\u0003\u0019\u0001%\t\u000b]\u0003\u0003\u0019\u0001%\t\u000be\u0003\u0003\u0019A.\t\u000b}\u0003\u0003\u0019A.\t\r\u0005\u0004\u0003\u0019AAy\u0003\u001d)h.\u00199qYf,BA!\u0002\u0003\u0016Q!!q\u0001B\r!\u0015\t$\u0011\u0002B\u0007\u0013\r\u0011YA\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0013E\u0012y\u0001\u0013%\\7\nM\u0011b\u0001B\te\t1A+\u001e9mKV\u00022\u0001\u001aB\u000b\t%1\u0017\u0005)A\u0001\u0002\u000b\u0007q\rK\u0002\u0003\u0016=D\u0011Ba\u0007\"\u0003\u0003\u0005\rA!\b\u0002\u0007a$\u0003\u0007\u0005\u0003w\u0001\tM\u0011\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B\u0012!\u0011\tiF!\n\n\t\t\u001d\u0012q\f\u0002\u0007\u001f\nTWm\u0019;"
)
public class EdgeWithLocalIds implements Product, Serializable {
   private final long srcId;
   private final long dstId;
   private final int localSrcId;
   private final int localDstId;
   public final Object attr;

   public static Option unapply(final EdgeWithLocalIds x$0) {
      return EdgeWithLocalIds$.MODULE$.unapply(x$0);
   }

   public static EdgeWithLocalIds apply(final long srcId, final long dstId, final int localSrcId, final int localDstId, final Object attr) {
      return EdgeWithLocalIds$.MODULE$.apply(srcId, dstId, localSrcId, localDstId, attr);
   }

   public static Ordering lexicographicOrdering() {
      return EdgeWithLocalIds$.MODULE$.lexicographicOrdering();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long srcId() {
      return this.srcId;
   }

   public long dstId() {
      return this.dstId;
   }

   public int localSrcId() {
      return this.localSrcId;
   }

   public int localDstId() {
      return this.localDstId;
   }

   public Object attr() {
      return this.attr;
   }

   public EdgeWithLocalIds copy(final long srcId, final long dstId, final int localSrcId, final int localDstId, final Object attr) {
      return new EdgeWithLocalIds(srcId, dstId, localSrcId, localDstId, attr);
   }

   public long copy$default$1() {
      return this.srcId();
   }

   public long copy$default$2() {
      return this.dstId();
   }

   public int copy$default$3() {
      return this.localSrcId();
   }

   public int copy$default$4() {
      return this.localDstId();
   }

   public Object copy$default$5() {
      return this.attr();
   }

   public String productPrefix() {
      return "EdgeWithLocalIds";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.srcId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.dstId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.localSrcId());
         }
         case 3 -> {
            return BoxesRunTime.boxToInteger(this.localDstId());
         }
         case 4 -> {
            return this.attr();
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
      return x$1 instanceof EdgeWithLocalIds;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "srcId";
         }
         case 1 -> {
            return "dstId";
         }
         case 2 -> {
            return "localSrcId";
         }
         case 3 -> {
            return "localDstId";
         }
         case 4 -> {
            return "attr";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.srcId()));
      var1 = Statics.mix(var1, Statics.longHash(this.dstId()));
      var1 = Statics.mix(var1, this.localSrcId());
      var1 = Statics.mix(var1, this.localDstId());
      var1 = Statics.mix(var1, Statics.anyHash(this.attr()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label44: {
            if (x$1 instanceof EdgeWithLocalIds) {
               EdgeWithLocalIds var4 = (EdgeWithLocalIds)x$1;
               if (this.srcId() == var4.srcId() && this.dstId() == var4.dstId() && this.localSrcId() == var4.localSrcId() && this.localDstId() == var4.localDstId() && BoxesRunTime.equals(this.attr(), var4.attr()) && var4.canEqual(this)) {
                  break label44;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean attr$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this.attr());
   }

   public byte attr$mcB$sp() {
      return BoxesRunTime.unboxToByte(this.attr());
   }

   public char attr$mcC$sp() {
      return BoxesRunTime.unboxToChar(this.attr());
   }

   public double attr$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.attr());
   }

   public float attr$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.attr());
   }

   public int attr$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.attr());
   }

   public long attr$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.attr());
   }

   public short attr$mcS$sp() {
      return BoxesRunTime.unboxToShort(this.attr());
   }

   public void attr$mcV$sp() {
      this.attr();
   }

   public EdgeWithLocalIds copy$mZc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final boolean attr) {
      return new EdgeWithLocalIds$mcZ$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds copy$mBc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final byte attr) {
      return new EdgeWithLocalIds$mcB$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds copy$mCc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final char attr) {
      return new EdgeWithLocalIds$mcC$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds copy$mDc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final double attr) {
      return new EdgeWithLocalIds$mcD$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds copy$mFc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final float attr) {
      return new EdgeWithLocalIds$mcF$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds copy$mIc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final int attr) {
      return new EdgeWithLocalIds$mcI$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds copy$mJc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final long attr) {
      return new EdgeWithLocalIds$mcJ$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds copy$mSc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final short attr) {
      return new EdgeWithLocalIds$mcS$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public EdgeWithLocalIds copy$mVc$sp(final long srcId, final long dstId, final int localSrcId, final int localDstId, final BoxedUnit attr) {
      return new EdgeWithLocalIds$mcV$sp(srcId, dstId, localSrcId, localDstId, attr);
   }

   public boolean copy$default$5$mcZ$sp() {
      return BoxesRunTime.unboxToBoolean(this.copy$default$5());
   }

   public byte copy$default$5$mcB$sp() {
      return BoxesRunTime.unboxToByte(this.copy$default$5());
   }

   public char copy$default$5$mcC$sp() {
      return BoxesRunTime.unboxToChar(this.copy$default$5());
   }

   public double copy$default$5$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$5());
   }

   public float copy$default$5$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$5());
   }

   public int copy$default$5$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.copy$default$5());
   }

   public long copy$default$5$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.copy$default$5());
   }

   public short copy$default$5$mcS$sp() {
      return BoxesRunTime.unboxToShort(this.copy$default$5());
   }

   public void copy$default$5$mcV$sp() {
      this.copy$default$5();
   }

   public boolean specInstance$() {
      return false;
   }

   public EdgeWithLocalIds(final long srcId, final long dstId, final int localSrcId, final int localDstId, final Object attr) {
      this.srcId = srcId;
      this.dstId = dstId;
      this.localSrcId = localSrcId;
      this.localDstId = localDstId;
      this.attr = attr;
      Product.$init$(this);
   }
}
