package org.apache.spark.streaming.rdd;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.util.StateMap;
import scala.Function4;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tec!B\u000e\u001d\u0001z1\u0003\u0002\u0003 \u0001\u0005#\u0007I\u0011A \t\u0011Q\u0003!\u00111A\u0005\u0002UC\u0001b\u0017\u0001\u0003\u0012\u0003\u0006K\u0001\u0011\u0005\t9\u0002\u0011\t\u001a!C\u0001;\"AA\r\u0001BA\u0002\u0013\u0005Q\r\u0003\u0005h\u0001\tE\t\u0015)\u0003_\u0011\u0015A\u0007\u0001\"\u0001j\u0011\u001dq\u0007!!A\u0005\u0002=Dq\u0001 \u0001\u0012\u0002\u0013\u0005Q\u0010C\u0005\u0002\u001a\u0001\t\n\u0011\"\u0001\u0002\u001c!I\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0006\u0005\n\u0003w\u0001\u0011\u0011!C\u0001\u0003{A\u0011\"!\u0012\u0001\u0003\u0003%\t!a\u0012\t\u0013\u0005-\u0003!!A\u0005B\u00055\u0003\"CA.\u0001\u0005\u0005I\u0011AA/\u0011%\t9\u0007AA\u0001\n\u0003\nI\u0007C\u0005\u0002n\u0001\t\t\u0011\"\u0011\u0002p!I\u0011\u0011\u000f\u0001\u0002\u0002\u0013\u0005\u00131\u000f\u0005\n\u0003k\u0002\u0011\u0011!C!\u0003o:\u0001\"a\u001f\u001d\u0011\u0003q\u0012Q\u0010\u0004\b7qA\tAHA@\u0011\u0019AW\u0003\"\u0001\u0002\f\"9\u0011QR\u000b\u0005\u0002\u0005=\u0005\"\u0003B\n+\u0005\u0005I\u0011\u0011B\u000b\u0011%\u0011y#FA\u0001\n\u0003\u0013\t\u0004C\u0005\u0003PU\t\t\u0011\"\u0003\u0003R\t)R*\u00199XSRD7\u000b^1uKJ#EIU3d_J$'BA\u000f\u001f\u0003\r\u0011H\r\u001a\u0006\u0003?\u0001\n\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005\u0005\u0012\u0013!B:qCJ\\'BA\u0012%\u0003\u0019\t\u0007/Y2iK*\tQ%A\u0002pe\u001e,Ba\n%SEN!\u0001\u0001\u000b\u00182!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u0019\te.\u001f*fMB\u0011\u0011fL\u0005\u0003a)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00023w9\u00111'\u000f\b\u0003iaj\u0011!\u000e\u0006\u0003m]\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002W%\u0011!HK\u0001\ba\u0006\u001c7.Y4f\u0013\taTH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002;U\u0005A1\u000f^1uK6\u000b\u0007/F\u0001A!\u0011\tEIR)\u000e\u0003\tS!a\u0011\u0010\u0002\tU$\u0018\u000e\\\u0005\u0003\u000b\n\u0013\u0001b\u0015;bi\u0016l\u0015\r\u001d\t\u0003\u000f\"c\u0001\u0001B\u0003J\u0001\t\u0007!JA\u0001L#\tYe\n\u0005\u0002*\u0019&\u0011QJ\u000b\u0002\b\u001d>$\b.\u001b8h!\tIs*\u0003\u0002QU\t\u0019\u0011I\\=\u0011\u0005\u001d\u0013F!B*\u0001\u0005\u0004Q%!A*\u0002\u0019M$\u0018\r^3NCB|F%Z9\u0015\u0005YK\u0006CA\u0015X\u0013\tA&F\u0001\u0003V]&$\bb\u0002.\u0003\u0003\u0003\u0005\r\u0001Q\u0001\u0004q\u0012\n\u0014!C:uCR,W*\u00199!\u0003)i\u0017\r\u001d9fI\u0012\u000bG/Y\u000b\u0002=B\u0019!gX1\n\u0005\u0001l$aA*fcB\u0011qI\u0019\u0003\u0006G\u0002\u0011\rA\u0013\u0002\u0002\u000b\u0006qQ.\u00199qK\u0012$\u0015\r^1`I\u0015\fHC\u0001,g\u0011\u001dQV!!AA\u0002y\u000b1\"\\1qa\u0016$G)\u0019;bA\u00051A(\u001b8jiz\"2A\u001b7n!\u0015Y\u0007AR)b\u001b\u0005a\u0002\"\u0002 \b\u0001\u0004\u0001\u0005\"\u0002/\b\u0001\u0004q\u0016\u0001B2paf,B\u0001]:voR\u0019\u0011\u000f\u001f>\u0011\u000b-\u0004!\u000f\u001e<\u0011\u0005\u001d\u001bH!B%\t\u0005\u0004Q\u0005CA$v\t\u0015\u0019\u0006B1\u0001K!\t9u\u000fB\u0003d\u0011\t\u0007!\nC\u0004?\u0011A\u0005\t\u0019A=\u0011\t\u0005#%\u000f\u001e\u0005\b9\"\u0001\n\u00111\u0001|!\r\u0011tL^\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u001dq\u00181CA\u000b\u0003/)\u0012a \u0016\u0004\u0001\u0006\u00051FAA\u0002!\u0011\t)!a\u0004\u000e\u0005\u0005\u001d!\u0002BA\u0005\u0003\u0017\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u00055!&\u0001\u0006b]:|G/\u0019;j_:LA!!\u0005\u0002\b\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b%K!\u0019\u0001&\u0005\u000bMK!\u0019\u0001&\u0005\u000b\rL!\u0019\u0001&\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eUA\u0011QDA\u0011\u0003G\t)#\u0006\u0002\u0002 )\u001aa,!\u0001\u0005\u000b%S!\u0019\u0001&\u0005\u000bMS!\u0019\u0001&\u0005\u000b\rT!\u0019\u0001&\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tY\u0003\u0005\u0003\u0002.\u0005]RBAA\u0018\u0015\u0011\t\t$a\r\u0002\t1\fgn\u001a\u0006\u0003\u0003k\tAA[1wC&!\u0011\u0011HA\u0018\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011q\b\t\u0004S\u0005\u0005\u0013bAA\"U\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019a*!\u0013\t\u0011ik\u0011\u0011!a\u0001\u0003\u007f\tq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u001f\u0002R!!\u0015\u0002X9k!!a\u0015\u000b\u0007\u0005U#&\u0001\u0006d_2dWm\u0019;j_:LA!!\u0017\u0002T\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ty&!\u001a\u0011\u0007%\n\t'C\u0002\u0002d)\u0012qAQ8pY\u0016\fg\u000eC\u0004[\u001f\u0005\u0005\t\u0019\u0001(\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003W\tY\u0007\u0003\u0005[!\u0005\u0005\t\u0019AA \u0003!A\u0017m\u001d5D_\u0012,GCAA \u0003!!xn\u0015;sS:<GCAA\u0016\u0003\u0019)\u0017/^1mgR!\u0011qLA=\u0011\u001dQ6#!AA\u00029\u000bQ#T1q/&$\bn\u0015;bi\u0016\u0014F\t\u0012*fG>\u0014H\r\u0005\u0002l+M!Q\u0003KAA!\u0011\t\u0019)!#\u000e\u0005\u0005\u0015%\u0002BAD\u0003g\t!![8\n\u0007q\n)\t\u0006\u0002\u0002~\u0005!R\u000f\u001d3bi\u0016\u0014VmY8sI^KG\u000f\u001b#bi\u0006,\"\"!%\u0002\u001a\u0006m\u0016QTAQ)9\t\u0019*a3\u0002V\u0006\r\u0018q B\u0002\u0005\u001f!\"\"!&\u0002$\u0006M\u0016qXAc!!Y\u0007!a&\u0002\u001c\u0006}\u0005cA$\u0002\u001a\u0012)\u0011j\u0006b\u0001\u0015B\u0019q)!(\u0005\u000bM;\"\u0019\u0001&\u0011\u0007\u001d\u000b\t\u000bB\u0003d/\t\u0007!\nC\u0005\u0002&^\t\t\u0011q\u0001\u0002(\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005%\u0016qVAL\u001b\t\tYKC\u0002\u0002.*\nqA]3gY\u0016\u001cG/\u0003\u0003\u00022\u0006-&\u0001C\"mCN\u001cH+Y4\t\u0013\u0005Uv#!AA\u0004\u0005]\u0016AC3wS\u0012,gnY3%eA1\u0011\u0011VAX\u0003s\u00032aRA^\t\u0019\til\u0006b\u0001\u0015\n\ta\u000bC\u0005\u0002B^\t\t\u0011q\u0001\u0002D\u0006QQM^5eK:\u001cW\rJ\u001a\u0011\r\u0005%\u0016qVAN\u0011%\t9mFA\u0001\u0002\b\tI-\u0001\u0006fm&$WM\\2fIQ\u0002b!!+\u00020\u0006}\u0005bBAg/\u0001\u0007\u0011qZ\u0001\u000baJ,gOU3d_J$\u0007#B\u0015\u0002R\u0006U\u0015bAAjU\t1q\n\u001d;j_:Dq!a6\u0018\u0001\u0004\tI.\u0001\u0007eCR\f\u0017\n^3sCR|'\u000fE\u00033\u00037\fi.C\u0002\u0002Zu\u0002r!KAp\u0003/\u000bI,C\u0002\u0002b*\u0012a\u0001V;qY\u0016\u0014\u0004bBAs/\u0001\u0007\u0011q]\u0001\u0010[\u0006\u0004\b/\u001b8h\rVt7\r^5p]Bi\u0011&!;\u0002n\u0006]\u0015Q_A|\u0003{L1!a;+\u0005%1UO\\2uS>tG\u0007\u0005\u0003\u0002p\u0006EX\"\u0001\u0010\n\u0007\u0005MhD\u0001\u0003US6,\u0007#B\u0015\u0002R\u0006e\u0006CBAx\u0003s\fY*C\u0002\u0002|z\u0011Qa\u0015;bi\u0016\u0004R!KAi\u0003?CqA!\u0001\u0018\u0001\u0004\ti/A\u0005cCR\u001c\u0007\u000eV5nK\"9!QA\fA\u0002\t\u001d\u0011\u0001\u0006;j[\u0016|W\u000f\u001e+ie\u0016\u001c\bn\u001c7e)&lW\rE\u0003*\u0003#\u0014I\u0001E\u0002*\u0005\u0017I1A!\u0004+\u0005\u0011auN\\4\t\u000f\tEq\u00031\u0001\u0002`\u0005\u0011\"/Z7pm\u0016$\u0016.\\3e_V$H)\u0019;b\u0003\u0015\t\u0007\u000f\u001d7z+!\u00119B!\b\u0003\"\t\u0015BC\u0002B\r\u0005O\u0011Y\u0003\u0005\u0005l\u0001\tm!q\u0004B\u0012!\r9%Q\u0004\u0003\u0006\u0013b\u0011\rA\u0013\t\u0004\u000f\n\u0005B!B*\u0019\u0005\u0004Q\u0005cA$\u0003&\u0011)1\r\u0007b\u0001\u0015\"1a\b\u0007a\u0001\u0005S\u0001b!\u0011#\u0003\u001c\t}\u0001B\u0002/\u0019\u0001\u0004\u0011i\u0003\u0005\u00033?\n\r\u0012aB;oCB\u0004H._\u000b\t\u0005g\u0011iD!\u0011\u0003HQ!!Q\u0007B%!\u0015I\u0013\u0011\u001bB\u001c!\u001dI\u0013q\u001cB\u001d\u0005\u0007\u0002b!\u0011#\u0003<\t}\u0002cA$\u0003>\u0011)\u0011*\u0007b\u0001\u0015B\u0019qI!\u0011\u0005\u000bMK\"\u0019\u0001&\u0011\tIz&Q\t\t\u0004\u000f\n\u001dC!B2\u001a\u0005\u0004Q\u0005\"\u0003B&3\u0005\u0005\t\u0019\u0001B'\u0003\rAH\u0005\r\t\tW\u0002\u0011YDa\u0010\u0003F\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!1\u000b\t\u0005\u0003[\u0011)&\u0003\u0003\u0003X\u0005=\"AB(cU\u0016\u001cG\u000f"
)
public class MapWithStateRDDRecord implements Product, Serializable {
   private StateMap stateMap;
   private Seq mappedData;

   public static Option unapply(final MapWithStateRDDRecord x$0) {
      return MapWithStateRDDRecord$.MODULE$.unapply(x$0);
   }

   public static MapWithStateRDDRecord apply(final StateMap stateMap, final Seq mappedData) {
      return MapWithStateRDDRecord$.MODULE$.apply(stateMap, mappedData);
   }

   public static MapWithStateRDDRecord updateRecordWithData(final Option prevRecord, final Iterator dataIterator, final Function4 mappingFunction, final Time batchTime, final Option timeoutThresholdTime, final boolean removeTimedoutData, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3, final ClassTag evidence$4) {
      return MapWithStateRDDRecord$.MODULE$.updateRecordWithData(prevRecord, dataIterator, mappingFunction, batchTime, timeoutThresholdTime, removeTimedoutData, evidence$1, evidence$2, evidence$3, evidence$4);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public StateMap stateMap() {
      return this.stateMap;
   }

   public void stateMap_$eq(final StateMap x$1) {
      this.stateMap = x$1;
   }

   public Seq mappedData() {
      return this.mappedData;
   }

   public void mappedData_$eq(final Seq x$1) {
      this.mappedData = x$1;
   }

   public MapWithStateRDDRecord copy(final StateMap stateMap, final Seq mappedData) {
      return new MapWithStateRDDRecord(stateMap, mappedData);
   }

   public StateMap copy$default$1() {
      return this.stateMap();
   }

   public Seq copy$default$2() {
      return this.mappedData();
   }

   public String productPrefix() {
      return "MapWithStateRDDRecord";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.stateMap();
         }
         case 1 -> {
            return this.mappedData();
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
      return x$1 instanceof MapWithStateRDDRecord;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stateMap";
         }
         case 1 -> {
            return "mappedData";
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
            if (x$1 instanceof MapWithStateRDDRecord) {
               label48: {
                  MapWithStateRDDRecord var4 = (MapWithStateRDDRecord)x$1;
                  StateMap var10000 = this.stateMap();
                  StateMap var5 = var4.stateMap();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Seq var7 = this.mappedData();
                  Seq var6 = var4.mappedData();
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

   public MapWithStateRDDRecord(final StateMap stateMap, final Seq mappedData) {
      this.stateMap = stateMap;
      this.mappedData = mappedData;
      super();
      Product.$init$(this);
   }
}
