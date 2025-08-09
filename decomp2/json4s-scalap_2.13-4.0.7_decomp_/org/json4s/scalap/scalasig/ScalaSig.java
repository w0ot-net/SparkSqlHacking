package org.json4s.scalap.scalasig;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.$tilde;
import org.json4s.scalap.DefaultMemoisable;
import org.json4s.scalap.Rule;
import org.json4s.scalap.Success;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\re\u0001\u0002!B\u0001*C\u0001\u0002\u001a\u0001\u0003\u0016\u0004%\t!\u001a\u0005\tS\u0002\u0011\t\u0012)A\u0005M\"A!\u000e\u0001BK\u0002\u0013\u0005Q\r\u0003\u0005l\u0001\tE\t\u0015!\u0003g\u0011!a\u0007A!f\u0001\n\u0003i\u0007\u0002\u0003=\u0001\u0005#\u0005\u000b\u0011\u00028\t\u000be\u0004A\u0011\u0001>\u0007\u000b}\u0004\u0001)!\u0001\t\u0013\u0005\r\u0001B!f\u0001\n\u0003)\u0007\"CA\u0003\u0011\tE\t\u0015!\u0003g\u0011%\t9\u0001\u0003BK\u0002\u0013\u0005Q\rC\u0005\u0002\n!\u0011\t\u0012)A\u0005M\"Q\u00111\u0002\u0005\u0003\u0016\u0004%\t!!\u0004\t\u0013\u0005=\u0001B!E!\u0002\u0013!\bBB=\t\t\u0003\t\t\u0002C\u0004\u0002\u001e!!\t!a\b\t\u000f\u0005\u0005\u0002\u0002\"\u0001\u0002$!I\u0011q\u0005\u0005\u0002\u0002\u0013\u0005\u0011\u0011\u0006\u0005\n\u0003cA\u0011\u0013!C\u0001\u0003gA\u0011\"!\u0013\t#\u0003%\t!a\r\t\u0013\u0005-\u0003\"%A\u0005\u0002\u00055\u0003\"CA)\u0011\u0005\u0005I\u0011IA*\u0011!\t)\u0007CA\u0001\n\u0003)\u0007\"CA4\u0011\u0005\u0005I\u0011AA5\u0011%\t)\bCA\u0001\n\u0003\n9\bC\u0005\u0002\u0006\"\t\t\u0011\"\u0001\u0002\b\"I\u0011\u0011\u0013\u0005\u0002\u0002\u0013\u0005\u00131\u0013\u0005\n\u0003/C\u0011\u0011!C!\u00033C\u0011\"a'\t\u0003\u0003%\t%!(\t\u0013\u0005}\u0005\"!A\u0005B\u0005\u0005v!CAS\u0001\u0005\u0005\t\u0012AAT\r!y\b!!A\t\u0002\u0005%\u0006BB=!\t\u0003\t\t\rC\u0005\u0002\u001c\u0002\n\t\u0011\"\u0012\u0002\u001e\"I\u00111\u0019\u0011\u0002\u0002\u0013\u0005\u0015Q\u0019\u0005\n\u0003\u001b\u0004\u0013\u0011!CA\u0003\u001fDq!!9\u0001\t\u0003\t\u0019\u000fC\u0004\u0002h\u0002!\t!!;\t\u000f\u00055\b\u0001\"\u0001\u0002p\"9\u00111\u001f\u0001\u0005\u0004\u0005U\bbBAN\u0001\u0011\u0005\u0013Q\u0014\u0005\u000b\u00057\u0001\u0001R1A\u0005\u0002\tu\u0001B\u0003B\u0014\u0001!\u0015\r\u0011\"\u0001\u0003*!Q!q\u0007\u0001\t\u0006\u0004%\tA!\u000f\t\u0013\u0005\u001d\u0002!!A\u0005\u0002\t\r\u0003\"CA\u0019\u0001E\u0005I\u0011AA\u001a\u0011%\tI\u0005AI\u0001\n\u0003\t\u0019\u0004C\u0005\u0002L\u0001\t\n\u0011\"\u0001\u0003L!I\u0011\u0011\u000b\u0001\u0002\u0002\u0013\u0005\u00131\u000b\u0005\t\u0003K\u0002\u0011\u0011!C\u0001K\"I\u0011q\r\u0001\u0002\u0002\u0013\u0005!q\n\u0005\n\u0003k\u0002\u0011\u0011!C!\u0003oB\u0011\"!\"\u0001\u0003\u0003%\tAa\u0015\t\u0013\u0005E\u0005!!A\u0005B\t]\u0003\"CAL\u0001\u0005\u0005I\u0011IAM\u0011%\ty\nAA\u0001\n\u0003\u0012YfB\u0005\u0003`\u0005\u000b\t\u0011#\u0001\u0003b\u0019A\u0001)QA\u0001\u0012\u0003\u0011\u0019\u0007\u0003\u0004zu\u0011\u0005!q\r\u0005\n\u00037S\u0014\u0011!C#\u0003;C\u0011\"a1;\u0003\u0003%\tI!\u001b\t\u0013\u00055'(!A\u0005\u0002\nE\u0004\"\u0003B=u\u0005\u0005I\u0011\u0002B>\u0005!\u00196-\u00197b'&<'B\u0001\"D\u0003!\u00198-\u00197bg&<'B\u0001#F\u0003\u0019\u00198-\u00197ba*\u0011aiR\u0001\u0007UN|g\u000eN:\u000b\u0003!\u000b1a\u001c:h\u0007\u0001\u0019R\u0001A&R+b\u0003\"\u0001T(\u000e\u00035S\u0011AT\u0001\u0006g\u000e\fG.Y\u0005\u0003!6\u0013a!\u00118z%\u00164\u0007C\u0001*T\u001b\u0005\u0019\u0015B\u0001+D\u0005E!UMZ1vYRlU-\\8jg\u0006\u0014G.\u001a\t\u0003\u0019ZK!aV'\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011,\u0019\b\u00035~s!a\u00170\u000e\u0003qS!!X%\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0015B\u00011N\u0003\u001d\u0001\u0018mY6bO\u0016L!AY2\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u0001l\u0015\u0001D7bU>\u0014h+\u001a:tS>tW#\u00014\u0011\u00051;\u0017B\u00015N\u0005\rIe\u000e^\u0001\u000e[\u0006TwN\u001d,feNLwN\u001c\u0011\u0002\u00195Lgn\u001c:WKJ\u001c\u0018n\u001c8\u0002\u001b5Lgn\u001c:WKJ\u001c\u0018n\u001c8!\u0003\u0015!\u0018M\u00197f+\u0005q\u0007cA-pc&\u0011\u0001o\u0019\u0002\u0004'\u0016\f\b\u0003\u0002*sMRL!a]\"\u0003\r\u0011\"\u0018\u000e\u001c3f!\t)h/D\u0001B\u0013\t9\u0018I\u0001\u0005CsR,7i\u001c3f\u0003\u0019!\u0018M\u00197fA\u00051A(\u001b8jiz\"Ba\u001f?~}B\u0011Q\u000f\u0001\u0005\u0006I\u001e\u0001\rA\u001a\u0005\u0006U\u001e\u0001\rA\u001a\u0005\u0006Y\u001e\u0001\rA\u001c\u0002\u0006\u000b:$(/_\n\u0006\u0011-\u000bV\u000bW\u0001\u0006S:$W\r_\u0001\u0007S:$W\r\u001f\u0011\u0002\u0013\u0015tGO]=UsB,\u0017AC3oiJLH+\u001f9fA\u0005A!-\u001f;f\u0007>$W-F\u0001u\u0003%\u0011\u0017\u0010^3D_\u0012,\u0007\u0005\u0006\u0005\u0002\u0014\u0005]\u0011\u0011DA\u000e!\r\t)\u0002C\u0007\u0002\u0001!1\u00111A\bA\u0002\u0019Da!a\u0002\u0010\u0001\u00041\u0007BBA\u0006\u001f\u0001\u0007A/\u0001\u0005tG\u0006d\u0017mU5h+\u0005Y\u0018aC:fi\nKH/Z\"pI\u0016$B!a\u0005\u0002&!1\u00111B\tA\u0002Q\fAaY8qsRA\u00111CA\u0016\u0003[\ty\u0003\u0003\u0005\u0002\u0004I\u0001\n\u00111\u0001g\u0011!\t9A\u0005I\u0001\u0002\u00041\u0007\u0002CA\u0006%A\u0005\t\u0019\u0001;\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011Q\u0007\u0016\u0004M\u0006]2FAA\u001d!\u0011\tY$!\u0012\u000e\u0005\u0005u\"\u0002BA \u0003\u0003\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\rS*\u0001\u0006b]:|G/\u0019;j_:LA!a\u0012\u0002>\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCAA(U\r!\u0018qG\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005U\u0003\u0003BA,\u0003Cj!!!\u0017\u000b\t\u0005m\u0013QL\u0001\u0005Y\u0006twM\u0003\u0002\u0002`\u0005!!.\u0019<b\u0013\u0011\t\u0019'!\u0017\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u001b\u0002rA\u0019A*!\u001c\n\u0007\u0005=TJA\u0002B]fD\u0001\"a\u001d\u0019\u0003\u0003\u0005\rAZ\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005e\u0004CBA>\u0003\u0003\u000bY'\u0004\u0002\u0002~)\u0019\u0011qP'\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0004\u0006u$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!#\u0002\u0010B\u0019A*a#\n\u0007\u00055UJA\u0004C_>dW-\u00198\t\u0013\u0005M$$!AA\u0002\u0005-\u0014A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0016\u0002\u0016\"A\u00111O\u000e\u0002\u0002\u0003\u0007a-\u0001\u0005iCND7i\u001c3f)\u00051\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005U\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0002\n\u0006\r\u0006\"CA:=\u0005\u0005\t\u0019AA6\u0003\u0015)e\u000e\u001e:z!\r\t)\u0002I\n\u0006A\u0005-\u0016q\u0017\t\n\u0003[\u000b\u0019L\u001a4u\u0003'i!!a,\u000b\u0007\u0005EV*A\u0004sk:$\u0018.\\3\n\t\u0005U\u0016q\u0016\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BA]\u0003\u007fk!!a/\u000b\t\u0005u\u0016QL\u0001\u0003S>L1AYA^)\t\t9+A\u0003baBd\u0017\u0010\u0006\u0005\u0002\u0014\u0005\u001d\u0017\u0011ZAf\u0011\u0019\t\u0019a\ta\u0001M\"1\u0011qA\u0012A\u0002\u0019Da!a\u0003$\u0001\u0004!\u0018aB;oCB\u0004H.\u001f\u000b\u0005\u0003#\fi\u000eE\u0003M\u0003'\f9.C\u0002\u0002V6\u0013aa\u00149uS>t\u0007C\u0002'\u0002Z\u001a4G/C\u0002\u0002\\6\u0013a\u0001V;qY\u0016\u001c\u0004\"CApI\u0005\u0005\t\u0019AA\n\u0003\rAH\u0005M\u0001\tQ\u0006\u001cXI\u001c;ssR!\u0011\u0011RAs\u0011\u0019\t\u0019!\na\u0001M\u0006Aq-\u001a;F]R\u0014\u0018\u0010\u0006\u0003\u0002\u0014\u0005-\bBBA\u0002M\u0001\u0007a-\u0001\u0006qCJ\u001cX-\u00128uef$B!a\u001b\u0002r\"1\u00111A\u0014A\u0002\u0019\f\u0011\"\u00199qYf\u0014V\u000f\\3\u0016\t\u0005]\u0018Q \u000b\u0005\u0003s\u0014I\u0001\u0005\u0003\u0002|\u0006uH\u0002\u0001\u0003\b\u0003\u007fD#\u0019\u0001B\u0001\u0005\u0005\t\u0015\u0003\u0002B\u0002\u0003W\u00022\u0001\u0014B\u0003\u0013\r\u00119!\u0014\u0002\b\u001d>$\b.\u001b8h\u0011\u001d\u0011Y\u0001\u000ba\u0001\u0005\u001b\ta\u0001]1sg\u0016\u0014\bC\u0002B\b\u0005+\tIPD\u0002v\u0005#I1Aa\u0005B\u0003=\u00196-\u00197b'&<\u0007+\u0019:tKJ\u001c\u0018\u0002\u0002B\f\u00053\u0011a\u0001U1sg\u0016\u0014(b\u0001B\n\u0003\u000691/_7c_2\u001cXC\u0001B\u0010!\u0011IvN!\t\u0011\u0007U\u0014\u0019#C\u0002\u0003&\u0005\u0013aaU=nE>d\u0017a\u0004;pa2+g/\u001a7DY\u0006\u001c8/Z:\u0016\u0005\t-\u0002#B-\u0003.\tE\u0012b\u0001B\u0018G\n!A*[:u!\r)(1G\u0005\u0004\u0005k\t%aC\"mCN\u001c8+_7c_2\fq\u0002^8q\u0019\u00164X\r\\(cU\u0016\u001cGo]\u000b\u0003\u0005w\u0001R!\u0017B\u0017\u0005{\u00012!\u001eB \u0013\r\u0011\t%\u0011\u0002\r\u001f\nTWm\u0019;Ts6\u0014w\u000e\u001c\u000b\bw\n\u0015#q\tB%\u0011\u001d!W\u0006%AA\u0002\u0019DqA[\u0017\u0011\u0002\u0003\u0007a\rC\u0004m[A\u0005\t\u0019\u00018\u0016\u0005\t5#f\u00018\u00028Q!\u00111\u000eB)\u0011!\t\u0019hMA\u0001\u0002\u00041G\u0003BAE\u0005+B\u0011\"a\u001d6\u0003\u0003\u0005\r!a\u001b\u0015\t\u0005U#\u0011\f\u0005\t\u0003g2\u0014\u0011!a\u0001MR!\u0011\u0011\u0012B/\u0011%\t\u0019\bOA\u0001\u0002\u0004\tY'\u0001\u0005TG\u0006d\u0017mU5h!\t)(hE\u0003;\u0005K\n9\f\u0005\u0005\u0002.\u0006MfM\u001a8|)\t\u0011\t\u0007F\u0004|\u0005W\u0012iGa\u001c\t\u000b\u0011l\u0004\u0019\u00014\t\u000b)l\u0004\u0019\u00014\t\u000b1l\u0004\u0019\u00018\u0015\t\tM$q\u000f\t\u0006\u0019\u0006M'Q\u000f\t\u0007\u0019\u0006egM\u001a8\t\u0011\u0005}g(!AA\u0002m\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"A! \u0011\t\u0005]#qP\u0005\u0005\u0005\u0003\u000bIF\u0001\u0004PE*,7\r\u001e"
)
public class ScalaSig implements DefaultMemoisable, Product, Serializable {
   private volatile Entry$ Entry$module;
   private Seq symbols;
   private List topLevelClasses;
   private List topLevelObjects;
   private final int majorVersion;
   private final int minorVersion;
   private final Seq table;
   private HashMap map;
   private volatile byte bitmap$0;

   public static Option unapply(final ScalaSig x$0) {
      return ScalaSig$.MODULE$.unapply(x$0);
   }

   public static ScalaSig apply(final int majorVersion, final int minorVersion, final Seq table) {
      return ScalaSig$.MODULE$.apply(majorVersion, minorVersion, table);
   }

   public static Function1 tupled() {
      return ScalaSig$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ScalaSig$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object memo(final Object key, final Function0 a) {
      return DefaultMemoisable.memo$(this, key, a);
   }

   public Object compute(final Object key, final Function0 a) {
      return DefaultMemoisable.compute$(this, key, a);
   }

   public void onSuccess(final Object key, final Success result) {
      DefaultMemoisable.onSuccess$(this, key, result);
   }

   public Entry$ Entry() {
      if (this.Entry$module == null) {
         this.Entry$lzycompute$1();
      }

      return this.Entry$module;
   }

   public HashMap map() {
      return this.map;
   }

   public void org$json4s$scalap$DefaultMemoisable$_setter_$map_$eq(final HashMap x$1) {
      this.map = x$1;
   }

   public int majorVersion() {
      return this.majorVersion;
   }

   public int minorVersion() {
      return this.minorVersion;
   }

   public Seq table() {
      return this.table;
   }

   public boolean hasEntry(final int index) {
      return this.table().isDefinedAt(index);
   }

   public Entry getEntry(final int index) {
      $tilde var4 = ($tilde)this.table().apply(index);
      if (var4 != null) {
         int entryType = BoxesRunTime.unboxToInt(var4._1());
         ByteCode byteCode = (ByteCode)var4._2();
         Tuple2 var2 = new Tuple2(BoxesRunTime.boxToInteger(entryType), byteCode);
         int entryType = var2._1$mcI$sp();
         ByteCode byteCode = (ByteCode)var2._2();
         return new Entry(index, entryType, byteCode);
      } else {
         throw new MatchError(var4);
      }
   }

   public Object parseEntry(final int index) {
      return this.applyRule(ScalaSigParsers$.MODULE$.parseEntry(ScalaSigEntryParsers$.MODULE$.entry(), index));
   }

   public Object applyRule(final Rule parser) {
      return ScalaSigParsers$.MODULE$.expect(parser).apply(this);
   }

   public String toString() {
      return (new StringBuilder(18)).append("ScalaSig version ").append(this.majorVersion()).append(".").append(this.minorVersion()).append(.MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.table().size()).map((i) -> $anonfun$toString$1(this, BoxesRunTime.unboxToInt(i))).mkString("\n", "\n", "")).toString();
   }

   private Seq symbols$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.symbols = (Seq)this.applyRule(ScalaSigParsers$.MODULE$.symbols());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.symbols;
   }

   public Seq symbols() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.symbols$lzycompute() : this.symbols;
   }

   private List topLevelClasses$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.topLevelClasses = (List)this.applyRule(ScalaSigParsers$.MODULE$.topLevelClasses());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.topLevelClasses;
   }

   public List topLevelClasses() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.topLevelClasses$lzycompute() : this.topLevelClasses;
   }

   private List topLevelObjects$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.topLevelObjects = (List)this.applyRule(ScalaSigParsers$.MODULE$.topLevelObjects());
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.topLevelObjects;
   }

   public List topLevelObjects() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.topLevelObjects$lzycompute() : this.topLevelObjects;
   }

   public ScalaSig copy(final int majorVersion, final int minorVersion, final Seq table) {
      return new ScalaSig(majorVersion, minorVersion, table);
   }

   public int copy$default$1() {
      return this.majorVersion();
   }

   public int copy$default$2() {
      return this.minorVersion();
   }

   public Seq copy$default$3() {
      return this.table();
   }

   public String productPrefix() {
      return "ScalaSig";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.majorVersion());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.minorVersion());
            break;
         case 2:
            var10000 = this.table();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ScalaSig;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "majorVersion";
            break;
         case 1:
            var10000 = "minorVersion";
            break;
         case 2:
            var10000 = "table";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.majorVersion());
      var1 = Statics.mix(var1, this.minorVersion());
      var1 = Statics.mix(var1, Statics.anyHash(this.table()));
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label57: {
            boolean var2;
            if (x$1 instanceof ScalaSig) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label40: {
                  ScalaSig var4 = (ScalaSig)x$1;
                  if (this.majorVersion() == var4.majorVersion() && this.minorVersion() == var4.minorVersion()) {
                     label37: {
                        Seq var10000 = this.table();
                        Seq var5 = var4.table();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label37;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label37;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label40;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label57;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   private final void Entry$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Entry$module == null) {
            this.Entry$module = new Entry$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final String $anonfun$toString$1(final ScalaSig $this, final int i) {
      return (new StringBuilder(2)).append(i).append(":\t").append($this.parseEntry(i)).toString();
   }

   public ScalaSig(final int majorVersion, final int minorVersion, final Seq table) {
      this.majorVersion = majorVersion;
      this.minorVersion = minorVersion;
      this.table = table;
      DefaultMemoisable.$init$(this);
      Product.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class Entry implements DefaultMemoisable, Product, Serializable {
      private final int index;
      private final int entryType;
      private final ByteCode byteCode;
      private HashMap map;
      // $FF: synthetic field
      public final ScalaSig $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object memo(final Object key, final Function0 a) {
         return DefaultMemoisable.memo$(this, key, a);
      }

      public Object compute(final Object key, final Function0 a) {
         return DefaultMemoisable.compute$(this, key, a);
      }

      public void onSuccess(final Object key, final Success result) {
         DefaultMemoisable.onSuccess$(this, key, result);
      }

      public HashMap map() {
         return this.map;
      }

      public void org$json4s$scalap$DefaultMemoisable$_setter_$map_$eq(final HashMap x$1) {
         this.map = x$1;
      }

      public int index() {
         return this.index;
      }

      public int entryType() {
         return this.entryType;
      }

      public ByteCode byteCode() {
         return this.byteCode;
      }

      public ScalaSig scalaSig() {
         return this.org$json4s$scalap$scalasig$ScalaSig$Entry$$$outer();
      }

      public Entry setByteCode(final ByteCode byteCode) {
         return this.org$json4s$scalap$scalasig$ScalaSig$Entry$$$outer().new Entry(this.index(), this.entryType(), byteCode);
      }

      public Entry copy(final int index, final int entryType, final ByteCode byteCode) {
         return this.org$json4s$scalap$scalasig$ScalaSig$Entry$$$outer().new Entry(index, entryType, byteCode);
      }

      public int copy$default$1() {
         return this.index();
      }

      public int copy$default$2() {
         return this.entryType();
      }

      public ByteCode copy$default$3() {
         return this.byteCode();
      }

      public String productPrefix() {
         return "Entry";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.index());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToInteger(this.entryType());
               break;
            case 2:
               var10000 = this.byteCode();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Entry;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "index";
               break;
            case 1:
               var10000 = "entryType";
               break;
            case 2:
               var10000 = "byteCode";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.index());
         var1 = Statics.mix(var1, this.entryType());
         var1 = Statics.mix(var1, Statics.anyHash(this.byteCode()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label62: {
               boolean var2;
               if (x$1 instanceof Entry && ((Entry)x$1).org$json4s$scalap$scalasig$ScalaSig$Entry$$$outer() == this.org$json4s$scalap$scalasig$ScalaSig$Entry$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label39: {
                     Entry var4 = (Entry)x$1;
                     if (this.index() == var4.index() && this.entryType() == var4.entryType()) {
                        label36: {
                           ByteCode var10000 = this.byteCode();
                           ByteCode var5 = var4.byteCode();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label36;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label36;
                           }

                           if (var4.canEqual(this)) {
                              var7 = true;
                              break label39;
                           }
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label62;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      // $FF: synthetic method
      public ScalaSig org$json4s$scalap$scalasig$ScalaSig$Entry$$$outer() {
         return this.$outer;
      }

      public Entry(final int index, final int entryType, final ByteCode byteCode) {
         this.index = index;
         this.entryType = entryType;
         this.byteCode = byteCode;
         if (ScalaSig.this == null) {
            throw null;
         } else {
            this.$outer = ScalaSig.this;
            super();
            DefaultMemoisable.$init$(this);
            Product.$init$(this);
            Statics.releaseFence();
         }
      }
   }

   public class Entry$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final ScalaSig $outer;

      public final String toString() {
         return "Entry";
      }

      public Entry apply(final int index, final int entryType, final ByteCode byteCode) {
         return this.$outer.new Entry(index, entryType, byteCode);
      }

      public Option unapply(final Entry x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.index()), BoxesRunTime.boxToInteger(x$0.entryType()), x$0.byteCode())));
      }

      public Entry$() {
         if (ScalaSig.this == null) {
            throw null;
         } else {
            this.$outer = ScalaSig.this;
            super();
         }
      }
   }
}
