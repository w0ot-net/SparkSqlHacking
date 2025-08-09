package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableFactory;
import scala.collection.IterableOnceOps;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.WeakHashMap;
import scala.collection.mutable.ListBuffer.;
import scala.ref.WeakReference;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Universe;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015a\u0001\u0003\u001a4!\u0003\r\tA\u000f2\t\u000b}\u0002A\u0011\u0001!\t\u000b\u0011\u0003A\u0011I#\u0007\u000bq\u0003\u0011\u0011A/\t\u000by\u001bA\u0011A0\t\u000fI\u001b!\u0019!D\u0001C\"Aam\u0001EC\u0002\u0013Eq\r\u0003\u0006\u00026\rA)\u0019!C\t\u0003o1AA[\u0002\tW\"1a\f\u0003C\u0001\u0003\u0017Aq!a\u0004\t\t\u0003\t\t\u0002C\u0004\u0002\u001e!!\t!a\b\t\u0013\u0005\u001d3\u00011A\u0005\u0002\u0005%\u0003\"CA)\u0007\u0001\u0007I\u0011AA*\u0011!\tIf\u0001Q!\n\u0005-\u0003\"CA.\u0007\u0001\u0007I\u0011AA%\u0011%\tif\u0001a\u0001\n\u0003\ty\u0006\u0003\u0005\u0002d\r\u0001\u000b\u0015BA&\u0011)\t)g\u0001EC\u0002\u0013\u0005\u0011q\r\u0005\b\u0003k\u001aA\u0011AA<\u0011\u0019\t\u0019i\u0001C\u0001\u0001\u001e9\u0011QQ\u0002\t\u0002\u0005\u001deaBAE\u0007!\u0005\u00111\u0012\u0005\u0007=Z!\t!a$\t\u0011I3\"\u0019!C\u0001\u0003#Cq!a%\u0017A\u0003%!\nC\u0004\u0002\u0016\u000e!\t\"a&\t\u000f\u0005E6\u0001\"\u0005\u00024\"9\u0011qW\u0002\u0005\u0002\u0005e\u0006bBA`\u0007\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003\u000b\u001cA\u0011AAd\u0011\u001d\tYm\u0001C\u0001\u0003\u001bDq!a8\u0004\t\u0003\t\t\u000fC\u0004\u0002f\u000e!\t!a:\t\u000f\u0005-8\u0001\"\u0001\u0002n\"9!qB\u0002\u0005\u0002\tE\u0001b\u0002B\u0012\u0007\u0011\u0005!Q\u0005\u0005\b\u0005g\u0019A\u0011\u0001B\u001b\u0011\u001d\u0011)e\u0001C\u0001\u0005\u000fBqA!\u0017\u0004\t\u0003\u0011Y\u0006C\u0004\u0003n\r!\tAa\u001c\t\u000f\tu4\u0001\"\u0001\u0003\u0000!9!QR\u0002\u0005\u0002\t=\u0005b\u0002BO\u0007\u0011\u0005!q\u0014\u0005\b\u0005W\u001bA\u0011\u0001BW\u0011\u001d\u0011Il\u0001C\u0001\u0005wCqAa2\u0004\t\u0003\u0011I\rC\u0004\u0003V\u000e!\tAa6\t\u000f\t\r8\u0001\"\u0001\u0003f\"9!\u0011_\u0002\u0005\u0002\tM(!C%na>\u0014H/\u001a:t\u0015\t!T'\u0001\u0005j]R,'O\\1m\u0015\t1t'A\u0004sK\u001adWm\u0019;\u000b\u0003a\nQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001wA\u0011A(P\u0007\u0002o%\u0011ah\u000e\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\t\u0005C\u0001\u001fC\u0013\t\u0019uG\u0001\u0003V]&$\u0018AC7l\u00136\u0004xN\u001d;feR\u0011aI\u0016\n\u0003\u000f&3A\u0001\u0013\u0001\u0001\r\naAH]3gS:,W.\u001a8u}A\u0011!jS\u0007\u0002\u0001%\u0011A*\u0014\u0002\t\u00136\u0004xN\u001d;fe&\u0011aj\u0014\u0002\n\u0013:$XM\u001d8bYNT!\u0001U\u001b\u0002\u0007\u0005\u0004\u0018\u000eC\u0004S\u000f\n\u0007i\u0011I*\u0002\t\u0019\u0014x.\\\u000b\u0002):\u0011QK\u0016\u0007\u0001\u0011\u00159&\u00011\u0001Y\u0003\u00151'o\\71!\tI&,D\u0001P\u0013\tYvJ\u0001\u0005V]&4XM]:f\u0005A\u0019F/\u00198eCJ$\u0017*\u001c9peR,'oE\u0002\u0004w%\u000ba\u0001P5oSRtD#\u00011\u0011\u0005)\u001bQ#\u00012\u0011\u0005\r$W\"A\u001a\n\u0005\u0015\u001c$aC*z[\n|G\u000eV1cY\u0016\faa]=n\u001b\u0006\u0004X#\u00015\u0011\r%D\u0011qEA\u001a\u001b\u0005\u0019!!B\"bG\",W\u0003\u00027w\u0003\u000f\u0019\"\u0001C7\u0011\t9\u001cX\u000f`\u0007\u0002_*\u0011\u0001/]\u0001\b[V$\u0018M\u00197f\u0015\t\u0011x'\u0001\u0006d_2dWm\u0019;j_:L!\u0001^8\u0003\u0017]+\u0017m\u001b%bg\"l\u0015\r\u001d\t\u0003+Z$Qa\u001e\u0005C\u0002a\u0014\u0011aS\t\u0003sn\u0002\"\u0001\u0010>\n\u0005m<$a\u0002(pi\"Lgn\u001a\t\u0006{\u0006\u0005\u0011QA\u0007\u0002}*\u0011qpN\u0001\u0004e\u00164\u0017bAA\u0002}\niq+Z1l%\u00164WM]3oG\u0016\u00042!VA\u0004\t\u0019\tI\u0001\u0003b\u0001q\n\ta\u000b\u0006\u0002\u0002\u000eA)\u0011\u000eC;\u0002\u0006\u00059q/Z1l\u000f\u0016$H\u0003BA\n\u00033\u0001R\u0001PA\u000b\u0003\u000bI1!a\u00068\u0005\u0019y\u0005\u000f^5p]\"1\u00111\u0004\u0006A\u0002U\f1a[3z\u0003)9X-Y6Va\u0012\fG/\u001a\u000b\u0006\u0003\u0006\u0005\u00121\u0005\u0005\u0007\u00037Y\u0001\u0019A;\t\u000f\u0005\u00152\u00021\u0001\u0002\u0006\u0005)a/\u00197vKB!\u0011\u0011FA\u0016\u001d\tIW!\u0003\u0003\u0002.\u0005=\"AB*z[\n|G.C\u0002\u00022M\u0012qaU=nE>d7\u000fE\u0002K\u0003W\ta\u0001\u001e9f\u001b\u0006\u0004XCAA\u001d!\u0019I\u0007\"a\u000f\u0002FA!\u0011\u0011FA\u001f\u0013\u0011\ty$!\u0011\u0003\tQK\b/Z\u0005\u0004\u0003\u0007\u001a$!\u0002+za\u0016\u001c\bc\u0001&\u0002>\u0005Y\u0001/\u001a8eS:<7+_7t+\t\tY\u0005E\u0002=\u0003\u001bJ1!a\u00148\u0005\rIe\u000e^\u0001\u0010a\u0016tG-\u001b8h'fl7o\u0018\u0013fcR\u0019\u0011)!\u0016\t\u0013\u0005]S\"!AA\u0002\u0005-\u0013a\u0001=%c\u0005a\u0001/\u001a8eS:<7+_7tA\u0005Y\u0001/\u001a8eS:<G\u000b]3t\u0003=\u0001XM\u001c3j]\u001e$\u0006/Z:`I\u0015\fHcA!\u0002b!I\u0011q\u000b\t\u0002\u0002\u0003\u0007\u00111J\u0001\ra\u0016tG-\u001b8h)B,7\u000fI\u0001\u0007M&DX\u000f]:\u0016\u0005\u0005%\u0004#\u00028\u0002l\u0005=\u0014bAA7_\nQA*[:u\u0005V4g-\u001a:\u0011\tq\n\t(Q\u0005\u0004\u0003g:$!\u0003$v]\u000e$\u0018n\u001c81\u0003!\tG\r\u001a$jqV\u0004HcA!\u0002z!A\u00111P\n\u0005\u0002\u0004\ti(A\u0003gSb,\b\u000f\u0005\u0003=\u0003\u007f\n\u0015bAAAo\tAAHY=oC6,g(\u0001\u0005uef4\u0015\u000e_;q\u0003\u001d\u0011XM^3sg\u0016\u0004\"!\u001b\f\u0003\u000fI,g/\u001a:tKN\u0019a#!$\u0011\u0007\u0005%2\u0001\u0006\u0002\u0002\bV\t!*A\u0003ge>l\u0007%\u0001\rsK\u000e\u0014X-\u0019;fINKXNY8m\u0007>l\u0007\u000f\\3uKJ$b!!'\u0002*\u00065&CBAN\u0003;\u000b\u0019KB\u0003I5\u0001\tI\nE\u0002K\u0003?KA!!)\u0002B\taA*\u0019>z!>d\u0017\u0010V=qKB\u0019!*!*\n\t\u0005\u001d\u0016\u0011\t\u0002\u0016\r2\fw-Q4o_N$\u0018nY\"p[BdW\r^3s\u0011\u001d\tYK\u0007a\u0001\u0003g\t!!\\=\t\u000f\u0005=&\u00041\u0001\u0002(\u0005)A\u000f[3je\u0006q!/Z2sK\u0006$XmU=nE>dG\u0003BA\u001a\u0003kCq!a,\u001c\u0001\u0004\t9#\u0001\u0007j[B|'\u000f^*z[\n|G\u000e\u0006\u0003\u00024\u0005m\u0006bBA_9\u0001\u0007\u0011qE\u0001\u0007i\",\u0017N\u001d\u0019\u0002\u0019I,7M]3bi\u0016$\u0016\u0010]3\u0015\t\u0005\u0015\u00131\u0019\u0005\b\u0003_k\u0002\u0019AA\u001e\u0003)IW\u000e]8siRK\b/\u001a\u000b\u0005\u0003\u000b\nI\rC\u0004\u00020z\u0001\r!a\u000f\u0002-I,7M]3bi\u0016$GK]3f\u0007>l\u0007\u000f\\3uKJ$R!QAh\u00037Dq!a, \u0001\u0004\t\t\u000e\u0005\u0003\u0002*\u0005M\u0017\u0002BAk\u0003/\u0014A\u0001\u0016:fK&\u0019\u0011\u0011\\\u001a\u0003\u000bQ\u0013X-Z:\t\u000f\u0005-v\u00041\u0001\u0002^B\u0019!*a5\u0002\u0019I,7M]3bi\u0016$&/Z3\u0015\t\u0005u\u00171\u001d\u0005\b\u0003_\u0003\u0003\u0019AAi\u0003)IW\u000e]8siR\u0013X-\u001a\u000b\u0005\u0003;\fI\u000fC\u0004\u00020\u0006\u0002\r!!5\u0002#%l\u0007o\u001c:u\u0003R$\u0018m\u00195nK:$8\u000f\u0006\u0003\u0002p\n-\u0001CBAy\u0003\u007f\u0014)A\u0004\u0003\u0002t\u0006m\bcAA{o5\u0011\u0011q\u001f\u0006\u0004\u0003sL\u0014A\u0002\u001fs_>$h(C\u0002\u0002~^\na\u0001\u0015:fI\u00164\u0017\u0002\u0002B\u0001\u0005\u0007\u00111aU3u\u0015\r\tip\u000e\t\u0004y\t\u001d\u0011b\u0001B\u0005o\t\u0019\u0011I\\=\t\u000f\t5!\u00051\u0001\u0002p\u0006Y\u0011\r\u001e;bG\"lWM\u001c;t\u0003QIW\u000e]8si\u0006sgn\u001c;bi&|g.\u00138g_R!!1\u0003B\u000f!\rQ%QC\u0005\u0005\u0005/\u0011IB\u0001\bB]:|G/\u0019;j_:LeNZ8\n\u0007\tm1GA\bB]:|G/\u0019;j_:LeNZ8t\u0011\u001d\u0011yb\ta\u0001\u0005C\t1!\u00198o!\u0011\tIC!\u0006\u0002\u001d%l\u0007o\u001c:u\u0003:tw\u000e^!sOR!!q\u0005B\u0017!\rQ%\u0011F\u0005\u0005\u0005W\u0011IBA\tDY\u0006\u001c8OZ5mK\u0006sgn\u001c;Be\u001eDqAa\f%\u0001\u0004\u0011\t$A\u0002be\u001e\u0004B!!\u000b\u0003*\u0005q\u0011.\u001c9peR\u0004vn]5uS>tG\u0003\u0002B\u001c\u0005\u0003\u00022A\u0013B\u001d\u0013\u0011\u0011YD!\u0010\u0003\u0011A{7/\u001b;j_:L1Aa\u00104\u0005%\u0001vn]5uS>t7\u000fC\u0004\u00020\u0016\u0002\rAa\u0011\u0011\t\u0005%\"\u0011H\u0001\fS6\u0004xN\u001d;TG>\u0004X\r\u0006\u0003\u0003J\tM\u0003c\u0001&\u0003L%!!Q\nB(\u0005\u0015\u00196m\u001c9f\u0013\r\u0011\tf\r\u0002\u0007'\u000e|\u0007/Z:\t\u000f\tUc\u00051\u0001\u0003X\u0005)A-Z2mgB!\u0011\u0011\u0006B&\u0003)IW\u000e]8si:\u000bW.\u001a\u000b\u0005\u0005;\u00129\u0007E\u0002K\u0005?JAA!\u0019\u0003d\t!a*Y7f\u0013\r\u0011)g\r\u0002\u0006\u001d\u0006lWm\u001d\u0005\b\u0005S:\u0003\u0019\u0001B6\u0003\u0011q\u0017-\\3\u0011\t\u0005%\"qL\u0001\u0010S6\u0004xN\u001d;N_\u0012Lg-[3sgR!!\u0011\u000fB<!\rQ%1O\u0005\u0005\u0005k\n9NA\u0005N_\u0012Lg-[3sg\"9!\u0011\u0010\u0015A\u0002\tm\u0014\u0001B7pIN\u0004B!!\u000b\u0003t\u0005!\u0012.\u001c9peRLU\u000e]8siN+G.Z2u_J$BA!!\u0003\bB\u0019!Ja!\n\t\t\u0015\u0015q\u001b\u0002\u000f\u00136\u0004xN\u001d;TK2,7\r^8s\u0011\u001d\u0011I)\u000ba\u0001\u0005\u0017\u000b1a]3m!\u0011\tICa!\u0002\u0019%l\u0007o\u001c:u-\u0006dG)\u001a4\u0015\t\tE%q\u0013\t\u0004\u0015\nM\u0015\u0002\u0002BK\u0003/\u0014aAV1m\t\u00164\u0007b\u0002BMU\u0001\u0007!1T\u0001\u0005iJ,W\r\u0005\u0003\u0002*\tM\u0015!D5na>\u0014H\u000fV=qK\u0012+g\r\u0006\u0003\u0003\"\n\u001d\u0006c\u0001&\u0003$&!!QUAl\u0005\u001d!\u0016\u0010]3EK\u001aDqA!',\u0001\u0004\u0011I\u000b\u0005\u0003\u0002*\t\r\u0016aD5na>\u0014H/T3nE\u0016\u0014H)\u001a4\u0015\t\t=&Q\u0017\t\u0004\u0015\nE\u0016\u0002\u0002BZ\u0003/\u0014\u0011\"T3nE\u0016\u0014H)\u001a4\t\u000f\teE\u00061\u0001\u00038B!\u0011\u0011\u0006BY\u00039IW\u000e]8siR+W\u000e\u001d7bi\u0016$BA!0\u0003DB\u0019!Ja0\n\t\t\u0005\u0017q\u001b\u0002\t)\u0016l\u0007\u000f\\1uK\"9!\u0011T\u0017A\u0002\t\u0015\u0007\u0003BA\u0015\u0005\u007f\u000bQ\"[7q_J$(+\u001a4Ue\u0016,G\u0003\u0002Bf\u0005#\u00042A\u0013Bg\u0013\u0011\u0011y-a6\u0003\u000fI+g\r\u0016:fK\"9!\u0011\u0014\u0018A\u0002\tM\u0007\u0003BA\u0015\u0005\u001b\f1\"[7q_J$\u0018\nZ3oiR!!\u0011\u001cBp!\rQ%1\\\u0005\u0005\u0005;\f9NA\u0003JI\u0016tG\u000fC\u0004\u0003\u001a>\u0002\rA!9\u0011\t\u0005%\"1\\\u0001\u000eS6\u0004xN\u001d;DCN,G)\u001a4\u0015\t\t\u001d(Q\u001e\t\u0004\u0015\n%\u0018\u0002\u0002Bv\u0003/\u0014qaQ1tK\u0012+g\rC\u0004\u0003\u001aB\u0002\rAa<\u0011\t\u0005%\"\u0011^\u0001\u000fS6\u0004xN\u001d;D_:\u001cH/\u00198u)\u0011\u0011)Pa@\u0011\u0007)\u001390\u0003\u0003\u0003z\nm(\u0001C\"p]N$\u0018M\u001c;\n\u0007\tu8GA\u0005D_:\u001cH/\u00198ug\"91\u0011A\u0019A\u0002\r\r\u0011\u0001C2p]N$\u0018M\u001c;\u0011\t\u0005%\"q\u001f"
)
public interface Importers {
   // $FF: synthetic method
   static scala.reflect.api.Internals.Importer mkImporter$(final Importers $this, final Universe from0) {
      return $this.mkImporter(from0);
   }

   default scala.reflect.api.Internals.Importer mkImporter(final Universe from0) {
      Object var10000;
      if (this == from0) {
         var10000 = (SymbolTable)this.new Importer(from0) {
            private final Universe from;
            private final scala.reflect.api.Internals.Importer reverse;

            public Universe from() {
               return this.from;
            }

            public scala.reflect.api.Internals.Importer reverse() {
               return this.reverse;
            }

            public Symbols.Symbol importSymbol(final scala.reflect.api.Symbols.SymbolApi their) {
               return (Symbols.Symbol)their;
            }

            public Types.Type importType(final scala.reflect.api.Types.TypeApi their) {
               return (Types.Type)their;
            }

            public Trees.Tree importTree(final scala.reflect.api.Trees.TreeApi their) {
               return (Trees.Tree)their;
            }

            public Position importPosition(final scala.reflect.api.Position their) {
               return (Position)their;
            }

            public {
               this.from = from0$1;
               this.reverse = this;
            }
         };
      } else {
         SymbolTable var4 = (SymbolTable)this;
         boolean assert_assertion = from0 instanceof SymbolTable;
         SymbolTable assert_this = var4;
         if (!assert_assertion) {
            throw assert_this.throwAssertionError("`from` should be an instance of scala.reflect.internal.SymbolTable");
         }

         var10000 = (SymbolTable)this.new StandardImporter(from0) {
            private final SymbolTable from;

            public SymbolTable from() {
               return this.from;
            }

            public {
               this.from = (SymbolTable)from0$1;
            }
         };
      }

      return (scala.reflect.api.Internals.Importer)var10000;
   }

   // $FF: synthetic method
   static String $anonfun$mkImporter$1() {
      return "`from` should be an instance of scala.reflect.internal.SymbolTable";
   }

   static void $init$(final Importers $this) {
   }

   public abstract class StandardImporter implements scala.reflect.api.Internals.Importer {
      private Cache symMap;
      private Cache tpeMap;
      private ListBuffer fixups;
      private volatile reverse$ reverse$module;
      private int pendingSyms;
      private int pendingTpes;
      private volatile byte bitmap$0;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public reverse$ reverse() {
         if (this.reverse$module == null) {
            this.reverse$lzycompute$1();
         }

         return this.reverse$module;
      }

      public abstract SymbolTable from();

      private Cache symMap$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 1) == 0) {
               this.symMap = new Cache();
               this.bitmap$0 = (byte)(this.bitmap$0 | 1);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.symMap;
      }

      public Cache symMap() {
         return (byte)(this.bitmap$0 & 1) == 0 ? this.symMap$lzycompute() : this.symMap;
      }

      private Cache tpeMap$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 2) == 0) {
               this.tpeMap = new Cache();
               this.bitmap$0 = (byte)(this.bitmap$0 | 2);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.tpeMap;
      }

      public Cache tpeMap() {
         return (byte)(this.bitmap$0 & 2) == 0 ? this.tpeMap$lzycompute() : this.tpeMap;
      }

      public int pendingSyms() {
         return this.pendingSyms;
      }

      public void pendingSyms_$eq(final int x$1) {
         this.pendingSyms = x$1;
      }

      public int pendingTpes() {
         return this.pendingTpes;
      }

      public void pendingTpes_$eq(final int x$1) {
         this.pendingTpes = x$1;
      }

      private ListBuffer fixups$lzycompute() {
         synchronized(this){}

         try {
            if ((byte)(this.bitmap$0 & 4) == 0) {
               this.fixups = (ListBuffer)IterableFactory.apply$(.MODULE$, scala.collection.immutable.Nil..MODULE$);
               this.bitmap$0 = (byte)(this.bitmap$0 | 4);
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.fixups;
      }

      public ListBuffer fixups() {
         return (byte)(this.bitmap$0 & 4) == 0 ? this.fixups$lzycompute() : this.fixups;
      }

      public void addFixup(final Function0 fixup) {
         ListBuffer var10000 = this.fixups();
         if (var10000 == null) {
            throw null;
         } else {
            var10000.addOne(fixup);
         }
      }

      public void tryFixup() {
         if (this.pendingSyms() == 0 && this.pendingTpes() == 0) {
            List fixups = this.fixups().toList();
            this.fixups().clear();
            if (fixups == null) {
               throw null;
            } else {
               for(List foreach_these = fixups; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                  ((Function0)foreach_these.head()).apply$mcV$sp();
               }

            }
         }
      }

      public Types.LazyPolyType recreatedSymbolCompleter(final Symbols.Symbol my, final Symbols.Symbol their) {
         Types.FlagAgnosticCompleter var20;
         try {
            my.setFlag(549755813888L);
            List var10000 = their.typeParams();
            if (var10000 == null) {
               throw null;
            }

            List map_this = var10000;
            Object var19;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var19 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Symbols.Symbol var10 = (Symbols.Symbol)map_this.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importSymbol(var10), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var10 = (Symbols.Symbol)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importSymbol(var10), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var19 = map_h;
            }

            Object var13 = null;
            Object var14 = null;
            Object var15 = null;
            Object var16 = null;
            Object var17 = null;
            List mytypeParams = (List)var19;
            var20 = new Types.FlagAgnosticCompleter(mytypeParams, their) {
               // $FF: synthetic field
               private final StandardImporter $outer;
               private final Symbols.Symbol their$1;
               private final List mytypeParams$1;

               public void complete(final Symbols.Symbol my) {
                  Types.Type var3 = this.their$1.info();
                  Types.Type theirCore = var3 instanceof Types.PolyType ? ((Types.PolyType)var3).resultType() : var3;
                  my.setInfo(this.$outer.scala$reflect$internal$Importers$StandardImporter$$$outer().GenPolyType().apply(this.mytypeParams$1, this.$outer.importType(theirCore)));
                  List var10001 = this.their$1.annotations();
                  if (var10001 == null) {
                     throw null;
                  } else {
                     List map_this = var10001;
                     Object var16;
                     if (map_this == scala.collection.immutable.Nil..MODULE$) {
                        var16 = scala.collection.immutable.Nil..MODULE$;
                     } else {
                        AnnotationInfos.AnnotationInfo var9 = (AnnotationInfos.AnnotationInfo)map_this.head();
                        scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$complete$1(this, var9), scala.collection.immutable.Nil..MODULE$);
                        scala.collection.immutable..colon.colon map_t = map_h;

                        for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                           var9 = (AnnotationInfos.AnnotationInfo)map_rest.head();
                           scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$complete$1(this, var9), scala.collection.immutable.Nil..MODULE$);
                           map_t.next_$eq(map_nx);
                           map_t = map_nx;
                        }

                        Statics.releaseFence();
                        var16 = map_h;
                     }

                     Object var10 = null;
                     Object var11 = null;
                     Object var12 = null;
                     Object var13 = null;
                     Object var14 = null;
                     my.setAnnotations((List)var16);
                     SymbolTable var10000 = this.$outer.scala$reflect$internal$Importers$StandardImporter$$$outer();
                     if (var10000 == null) {
                        throw null;
                     } else {
                        Symbols.markAllCompleted$(var10000, my);
                     }
                  }
               }

               // $FF: synthetic method
               public static final AnnotationInfos.AnnotationInfo $anonfun$complete$1(final Object $this, final AnnotationInfos.AnnotationInfo ann) {
                  return $this.$outer.importAnnotationInfo(ann);
               }

               public {
                  if (StandardImporter.this == null) {
                     throw null;
                  } else {
                     this.$outer = StandardImporter.this;
                     this.their$1 = their$1;
                     this.mytypeParams$1 = mytypeParams$1;
                  }
               }
            };
         } finally {
            my.resetFlag(549755813888L);
         }

         return var20;
      }

      public Symbols.Symbol recreateSymbol(final Symbols.Symbol their) {
         Symbols.Symbol myowner = this.importSymbol(their.owner());
         Position mypos = this.importPosition(their.pos());
         Names.Name myname = this.importName(their.name());
         long myflags = their.flags();
         Object var45;
         if (their instanceof Symbols.MethodSymbol) {
            Symbols.MethodSymbol var8 = (Symbols.MethodSymbol)their;
            Names.TermName newMethod_name = myname.toTermName();
            if (myowner == null) {
               throw null;
            }

            Symbols.MethodSymbol var51 = myowner.createMethodSymbol(newMethod_name, mypos, 64L | myflags);
            newMethod_name = null;
            Symbols.TermSymbol linkReferenced$1_my = var51;
            this.symMap().weakUpdate(var8, linkReferenced$1_my);
            Symbols.Symbol var34 = ((Symbols.TermSymbol)var8).referenced();
            linkReferenced$1_my.referenced_$eq(this.importSymbol(var34));
            var45 = linkReferenced$1_my;
            linkReferenced$1_my = null;
         } else if (their instanceof Symbols.ModuleSymbol) {
            Symbols.ModuleSymbol var9 = (Symbols.ModuleSymbol)their;
            Names.TermName newModuleSymbol_name = myname.toTermName();
            if (myowner == null) {
               throw null;
            }

            Symbols.ModuleSymbol var49 = (Symbols.ModuleSymbol)myowner.newTermSymbol(newModuleSymbol_name, mypos, myflags);
            newModuleSymbol_name = null;
            Symbols.TermSymbol linkReferenced$1_my = var49;
            this.symMap().weakUpdate(var9, linkReferenced$1_my);
            Symbols.Symbol var35 = ((Symbols.TermSymbol)var9).referenced();
            linkReferenced$1_my.referenced_$eq(this.importSymbol(var35));
            var49 = linkReferenced$1_my;
            linkReferenced$1_my = null;
            Symbols.Symbol ret = var49;
            ret.associatedFile_$eq(var9.associatedFile());
            var45 = ret;
         } else if (their instanceof Symbols.FreeTermSymbol) {
            Symbols.FreeTermSymbol var11 = (Symbols.FreeTermSymbol)their;
            var45 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().newFreeTermSymbol(myname.toTermName(), () -> var11.value(), var11.flags(), var11.origin()).setInfo(this.importType(var11.info()));
         } else if (their instanceof Symbols.FreeTypeSymbol) {
            Symbols.FreeTypeSymbol var12 = (Symbols.FreeTypeSymbol)their;
            var45 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().newFreeTypeSymbol(myname.toTypeName(), var12.flags(), var12.origin());
         } else if (their instanceof Symbols.TermSymbol) {
            Symbols.TermSymbol var13 = (Symbols.TermSymbol)their;
            Names.TermName newValue_name = myname.toTermName();
            if (myowner == null) {
               throw null;
            }

            Symbols.TermSymbol var48 = myowner.newTermSymbol(newValue_name, mypos, myflags);
            newValue_name = null;
            Symbols.TermSymbol linkReferenced$1_my = var48;
            this.symMap().weakUpdate(var13, linkReferenced$1_my);
            Symbols.Symbol var36 = var13.referenced();
            linkReferenced$1_my.referenced_$eq(this.importSymbol(var36));
            var45 = linkReferenced$1_my;
            Object var39 = null;
         } else if (!(their instanceof Symbols.TypeSkolem)) {
            if (their instanceof Symbols.ModuleClassSymbol) {
               Symbols.ModuleClassSymbol var18 = (Symbols.ModuleClassSymbol)their;
               Names.TypeName newModuleClass_name = myname.toTypeName();
               if (myowner == null) {
                  throw null;
               }

               long newModuleClass_newModuleClassSymbol_newFlags = myflags | 256L;
               Symbols.ModuleClassSymbol var46 = (Symbols.ModuleClassSymbol)myowner.newClassSymbol(newModuleClass_name, mypos, newModuleClass_newModuleClassSymbol_newFlags);
               newModuleClass_name = null;
               Symbols.ModuleClassSymbol my = var46;
               this.symMap().weakUpdate(var18, my);
               my.sourceModule_$eq(this.importSymbol(var18.sourceModule()));
               var45 = my;
            } else if (their instanceof Symbols.ClassSymbol) {
               Symbols.ClassSymbol var20;
               Symbols.ClassSymbol my;
               label66: {
                  var20 = (Symbols.ClassSymbol)their;
                  my = myowner.newClassSymbol(myname.toTypeName(), mypos, myflags);
                  this.symMap().weakUpdate(var20, my);
                  Symbols.Symbol var47 = var20.thisSym();
                  if (var47 != null) {
                     if (var47.equals(var20)) {
                        break label66;
                     }
                  }

                  my.typeOfThis_$eq(this.importType(var20.typeOfThis()));
                  my.thisSym().setName(this.importName(var20.thisSym().name()));
               }

               my.associatedFile_$eq(var20.associatedFile());
               var45 = my;
            } else {
               if (!(their instanceof Symbols.TypeSymbol)) {
                  throw new MatchError(their);
               }

               var45 = myowner.newTypeSymbol(myname.toTypeName(), mypos, myflags);
            }
         } else {
            Object var15 = ((Symbols.TypeSkolem)their).unpackLocation();
            if (var15 == null) {
               var45 = null;
            } else if (var15 instanceof Trees.Tree && ((Trees.Tree)var15).scala$reflect$internal$Trees$Tree$$$outer() == this.from()) {
               Trees.Tree var16 = (Trees.Tree)var15;
               var45 = this.importTree(var16);
            } else {
               if (!(var15 instanceof Symbols.Symbol) || ((Symbols.Symbol)var15).scala$reflect$internal$Symbols$Symbol$$$outer() != this.from()) {
                  throw new MatchError(var15);
               }

               Symbols.Symbol var17 = (Symbols.Symbol)var15;
               var45 = this.importSymbol(var17);
            }

            StdAttachments.Attachable origin = (StdAttachments.Attachable)var45;
            Names.TypeName newTypeSkolemSymbol_name = myname.toTypeName();
            if (myowner == null) {
               throw null;
            }

            var45 = myowner.createTypeSkolemSymbol(newTypeSkolemSymbol_name, origin, mypos, myflags);
            newTypeSkolemSymbol_name = null;
         }

         Symbols.Symbol my = (Symbols.Symbol)var45;
         this.symMap().weakUpdate(their, my);
         SymbolTable var52 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
         long markFlagsCompleted_mask = -1L;
         if (var52 == null) {
            throw null;
         } else {
            Symbols.markFlagsCompleted$(var52, my, markFlagsCompleted_mask);
            return my.setInfo(this.recreatedSymbolCompleter(my, their));
         }
      }

      public Symbols.Symbol importSymbol(final Symbols.Symbol their0) {
         Option var2 = this.symMap().weakGet(their0);
         if (var2 instanceof Some) {
            return (Symbols.Symbol)((Some)var2).value();
         } else if (scala.None..MODULE$.equals(var2)) {
            this.pendingSyms_$eq(this.pendingSyms() + 1);

            Symbols.Symbol var10000;
            try {
               Symbols.Symbol result = this.recreateOrRelink$1(their0);
               this.symMap().weakUpdate(their0, result);
               var10000 = result;
            } finally {
               this.pendingSyms_$eq(this.pendingSyms() - 1);
               this.tryFixup();
            }

            return var10000;
         } else {
            throw new MatchError(var2);
         }
      }

      public Types.Type recreateType(final Types.Type their) {
         if (their instanceof Types.TypeRef) {
            Types.TypeRef var2 = (Types.TypeRef)their;
            Types.Type pre = var2.pre();
            Symbols.Symbol sym = var2.sym();
            List args = var2.args();
            Types.TypeRef$ var201 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().TypeRef();
            Types.Type var204 = this.importType(pre);
            Symbols.Symbol var211 = this.importSymbol(sym);
            if (args == null) {
               throw null;
            } else {
               Object var221;
               if (args == scala.collection.immutable.Nil..MODULE$) {
                  var221 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Types.Type var110 = (Types.Type)args.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importType(var110), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)args.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var110 = (Types.Type)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importType(var110), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var221 = map_h;
               }

               Object var123 = null;
               Object var124 = null;
               Object var125 = null;
               Object var126 = null;
               return var201.apply(var204, var211, (List)var221);
            }
         } else if (their instanceof Types.ThisType) {
            Symbols.Symbol clazz = ((Types.ThisType)their).sym();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().ThisType().apply(this.importSymbol(clazz));
         } else if (their instanceof Types.SingleType) {
            Types.SingleType var7 = (Types.SingleType)their;
            Types.Type pre = var7.pre();
            Symbols.Symbol sym = var7.sym();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().SingleType().apply(this.importType(pre), this.importSymbol(sym));
         } else if (their instanceof Types.MethodType) {
            Types.MethodType var10 = (Types.MethodType)their;
            List params = var10.params();
            Types.Type result = var10.resultType();
            Types.MethodType var200 = new Types.MethodType;
            SymbolTable var210 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            if (params == null) {
               throw null;
            } else {
               Object var220;
               if (params == scala.collection.immutable.Nil..MODULE$) {
                  var220 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Symbols.Symbol var111 = (Symbols.Symbol)params.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importSymbol(var111), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)params.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var111 = (Symbols.Symbol)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importSymbol(var111), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var220 = map_h;
               }

               Object var127 = null;
               Object var128 = null;
               Object var129 = null;
               Object var130 = null;
               var200.<init>((List)var220, this.importType(result));
               return var200;
            }
         } else if (their instanceof Types.PolyType) {
            Types.PolyType var13 = (Types.PolyType)their;
            List tparams = var13.typeParams();
            Types.Type result = var13.resultType();
            Types.PolyType var199 = new Types.PolyType;
            SymbolTable var209 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            if (tparams == null) {
               throw null;
            } else {
               Object var219;
               if (tparams == scala.collection.immutable.Nil..MODULE$) {
                  var219 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Symbols.Symbol var112 = (Symbols.Symbol)tparams.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importSymbol(var112), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)tparams.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var112 = (Symbols.Symbol)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importSymbol(var112), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var219 = map_h;
               }

               Object var131 = null;
               Object var132 = null;
               Object var133 = null;
               Object var134 = null;
               var199.<init>((List)var219, this.importType(result));
               return var199;
            }
         } else if (their instanceof Types.NullaryMethodType) {
            Types.Type result = ((Types.NullaryMethodType)their).resultType();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new NullaryMethodType(this.importType(result));
         } else {
            if (their instanceof Types.ConstantType) {
               Types.ConstantType var17 = (Types.ConstantType)their;
               Some var18 = this.from().ConstantType().unapply(var17);
               if (!var18.isEmpty()) {
                  Constants.Constant constant = (Constants.Constant)var18.get();
                  if (constant != null) {
                     return this.scala$reflect$internal$Importers$StandardImporter$$$outer().ConstantType().apply(this.importConstant(constant));
                  }
               }
            }

            if (their instanceof Types.SuperType) {
               Types.SuperType var20 = (Types.SuperType)their;
               Types.Type thistpe = var20.thistpe();
               Types.Type supertpe = var20.supertpe();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().SuperType().apply(this.importType(thistpe), this.importType(supertpe));
            } else if (their instanceof Types.TypeBounds) {
               Types.TypeBounds var23 = (Types.TypeBounds)their;
               Types.Type lo = var23.lo();
               Types.Type hi = var23.hi();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().TypeBounds().apply(this.importType(lo), this.importType(hi));
            } else if (their instanceof Types.BoundedWildcardType) {
               Types.TypeBounds bounds = ((Types.BoundedWildcardType)their).bounds();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new BoundedWildcardType((Types.TypeBounds)this.importType((Types.Type)bounds));
            } else if (their instanceof Types.ClassInfoType) {
               Types.ClassInfoType var27 = (Types.ClassInfoType)their;
               List parents = var27.parents();
               Scopes.Scope decls = var27.decls();
               Symbols.Symbol clazz = var27.typeSymbol();
               Symbols.Symbol myclazz = this.importSymbol(clazz);
               Scopes.Scope myscope = myclazz.isPackageClass() ? this.scala$reflect$internal$Importers$StandardImporter$$$outer().newPackageScope(myclazz) : this.scala$reflect$internal$Importers$StandardImporter$$$outer().newScope();
               Types.ClassInfoType var198 = new Types.ClassInfoType;
               SymbolTable var208 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
               if (parents == null) {
                  throw null;
               } else {
                  Object var218;
                  if (parents == scala.collection.immutable.Nil..MODULE$) {
                     var218 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Types.Type var113 = (Types.Type)parents.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importType(var113), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)parents.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var113 = (Types.Type)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importType(var113), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var218 = map_h;
                  }

                  Object var135 = null;
                  Object var136 = null;
                  Object var137 = null;
                  Object var138 = null;
                  var198.<init>((List)var218, myscope, myclazz);
                  Types.ClassInfoType myclazzTpe = var198;
                  myclazz.setInfo(this.scala$reflect$internal$Importers$StandardImporter$$$outer().GenPolyType().apply(myclazz.typeParams(), myclazzTpe));
                  decls.foreach((their0) -> this.importSymbol(their0));
                  return myclazzTpe;
               }
            } else if (their instanceof Types.RefinedType) {
               Types.RefinedType var34 = (Types.RefinedType)their;
               List parents = var34.parents();
               Scopes.Scope decls = var34.decls();
               Types.RefinedType$ var197 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().RefinedType();
               if (parents == null) {
                  throw null;
               } else {
                  Object var203;
                  if (parents == scala.collection.immutable.Nil..MODULE$) {
                     var203 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Types.Type var114 = (Types.Type)parents.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importType(var114), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)parents.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var114 = (Types.Type)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importType(var114), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var203 = map_h;
                  }

                  Object var139 = null;
                  Object var140 = null;
                  Object var141 = null;
                  Object var142 = null;
                  return var197.apply((List)var203, this.importScope(decls), this.importSymbol(their.typeSymbol()));
               }
            } else if (their instanceof Types.ExistentialType) {
               Types.ExistentialType var37 = (Types.ExistentialType)their;
               List tparams = var37.quantified();
               Types.Type result = var37.underlying();
               SymbolTable var196 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
               if (tparams == null) {
                  throw null;
               } else {
                  Object var202;
                  if (tparams == scala.collection.immutable.Nil..MODULE$) {
                     var202 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Symbols.Symbol var115 = (Symbols.Symbol)tparams.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importSymbol(var115), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)tparams.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var115 = (Symbols.Symbol)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importSymbol(var115), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var202 = map_h;
                  }

                  Object var143 = null;
                  Object var144 = null;
                  Object var145 = null;
                  Object var146 = null;
                  Types.Type newExistentialType_underlying = this.importType(result);
                  Object newExistentialType_quantified = var202;
                  if (var196 == null) {
                     throw null;
                  } else {
                     return Types.newExistentialType$(var196, (List)newExistentialType_quantified, newExistentialType_underlying);
                  }
               }
            } else if (their instanceof Types.OverloadedType) {
               Types.OverloadedType var40 = (Types.OverloadedType)their;
               Types.Type pre = var40.pre();
               List alts = var40.alternatives();
               Types.OverloadedType var195 = new Types.OverloadedType;
               SymbolTable var207 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
               Types.Type var217 = this.importType(pre);
               if (alts == null) {
                  throw null;
               } else {
                  Object var226;
                  if (alts == scala.collection.immutable.Nil..MODULE$) {
                     var226 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Symbols.Symbol var116 = (Symbols.Symbol)alts.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importSymbol(var116), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)alts.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var116 = (Symbols.Symbol)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importSymbol(var116), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var226 = map_h;
                  }

                  Object var147 = null;
                  Object var148 = null;
                  Object var149 = null;
                  Object var150 = null;
                  var195.<init>(var217, (List)var226);
                  return var195;
               }
            } else if (their instanceof Types.ImportType) {
               Trees.Tree qual = ((Types.ImportType)their).expr();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new ImportType(this.importTree(qual));
            } else if (their instanceof Types.AntiPolyType) {
               Types.AntiPolyType var44 = (Types.AntiPolyType)their;
               Types.Type pre = var44.pre();
               List targs = var44.targs();
               Types.AntiPolyType var194 = new Types.AntiPolyType;
               SymbolTable var206 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
               Types.Type var216 = this.importType(pre);
               if (targs == null) {
                  throw null;
               } else {
                  Object var225;
                  if (targs == scala.collection.immutable.Nil..MODULE$) {
                     var225 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Types.Type var117 = (Types.Type)targs.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importType(var117), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)targs.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var117 = (Types.Type)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importType(var117), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var225 = map_h;
                  }

                  Object var151 = null;
                  Object var152 = null;
                  Object var153 = null;
                  Object var154 = null;
                  var194.<init>(var216, (List)var225);
                  return var194;
               }
            } else if (their instanceof Types.TypeVar) {
               Types.TypeVar var47 = (Types.TypeVar)their;
               TypeConstraints.TypeConstraint var192 = new TypeConstraints.TypeConstraint;
               SymbolTable var205 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
               List var212 = var47.constr().loBounds();
               if (var212 == null) {
                  throw null;
               } else {
                  List map_this = var212;
                  Object var213;
                  if (map_this == scala.collection.immutable.Nil..MODULE$) {
                     var213 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Types.Type var118 = (Types.Type)map_this.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importType(var118), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var118 = (Types.Type)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importType(var118), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var213 = map_h;
                  }

                  Object var155 = null;
                  Object var156 = null;
                  Object var157 = null;
                  Object var158 = null;
                  Object var159 = null;
                  List var10004 = var47.constr().hiBounds();
                  if (var10004 == null) {
                     throw null;
                  } else {
                     List map_this = var10004;
                     Object var222;
                     if (map_this == scala.collection.immutable.Nil..MODULE$) {
                        var222 = scala.collection.immutable.Nil..MODULE$;
                     } else {
                        Types.Type var119 = (Types.Type)map_this.head();
                        scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importType(var119), scala.collection.immutable.Nil..MODULE$);
                        scala.collection.immutable..colon.colon map_t = map_h;

                        for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                           var119 = (Types.Type)map_rest.head();
                           scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importType(var119), scala.collection.immutable.Nil..MODULE$);
                           map_t.next_$eq(map_nx);
                           map_t = map_nx;
                        }

                        Statics.releaseFence();
                        var222 = map_h;
                     }

                     Object var160 = null;
                     Object var161 = null;
                     Object var162 = null;
                     Object var163 = null;
                     Object var164 = null;
                     var192.<init>((List)var213, (List)var222);
                     TypeConstraints.TypeConstraint myconstr = var192;
                     myconstr.inst_$eq(this.importType(var47.constr().inst()));
                     Types.TypeVar$ var193 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().TypeVar();
                     Types.Type var10001 = this.importType(var47.origin());
                     List var214 = var47.typeArgs();
                     if (var214 == null) {
                        throw null;
                     } else {
                        List map_this = var214;
                        Object var215;
                        if (map_this == scala.collection.immutable.Nil..MODULE$) {
                           var215 = scala.collection.immutable.Nil..MODULE$;
                        } else {
                           Types.Type var120 = (Types.Type)map_this.head();
                           scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importType(var120), scala.collection.immutable.Nil..MODULE$);
                           scala.collection.immutable..colon.colon map_t = map_h;

                           for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                              var120 = (Types.Type)map_rest.head();
                              scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importType(var120), scala.collection.immutable.Nil..MODULE$);
                              map_t.next_$eq(map_nx);
                              map_t = map_nx;
                           }

                           Statics.releaseFence();
                           var215 = map_h;
                        }

                        Object var165 = null;
                        Object var166 = null;
                        Object var167 = null;
                        Object var168 = null;
                        Object var169 = null;
                        List var223 = var47.params();
                        if (var223 == null) {
                           throw null;
                        } else {
                           List map_this = var223;
                           Object var224;
                           if (map_this == scala.collection.immutable.Nil..MODULE$) {
                              var224 = scala.collection.immutable.Nil..MODULE$;
                           } else {
                              Symbols.Symbol var121 = (Symbols.Symbol)map_this.head();
                              scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importSymbol(var121), scala.collection.immutable.Nil..MODULE$);
                              scala.collection.immutable..colon.colon map_t = map_h;

                              for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                                 var121 = (Symbols.Symbol)map_rest.head();
                                 scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importSymbol(var121), scala.collection.immutable.Nil..MODULE$);
                                 map_t.next_$eq(map_nx);
                                 map_t = map_nx;
                              }

                              Statics.releaseFence();
                              var224 = map_h;
                           }

                           Object var170 = null;
                           Object var171 = null;
                           Object var172 = null;
                           Object var173 = null;
                           Object var174 = null;
                           return var193.apply(var10001, myconstr, (List)var215, (List)var224);
                        }
                     }
                  }
               }
            } else if (their instanceof Types.AnnotatedType) {
               Types.AnnotatedType var49 = (Types.AnnotatedType)their;
               List annots = var49.annotations();
               Types.Type result = var49.underlying();
               Types.AnnotatedType var10000 = new Types.AnnotatedType;
               SymbolTable var10002 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
               if (annots == null) {
                  throw null;
               } else {
                  Object var10003;
                  if (annots == scala.collection.immutable.Nil..MODULE$) {
                     var10003 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     AnnotationInfos.AnnotationInfo var122 = (AnnotationInfos.AnnotationInfo)annots.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importAnnotationInfo(var122), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)annots.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var122 = (AnnotationInfos.AnnotationInfo)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importAnnotationInfo(var122), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var10003 = map_h;
                  }

                  Object var175 = null;
                  Object var176 = null;
                  Object var177 = null;
                  Object var178 = null;
                  var10000.<init>((List)var10003, this.importType(result));
                  return var10000;
               }
            } else if (this.from().ErrorType().equals(their)) {
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().ErrorType();
            } else if (this.from().WildcardType().equals(their)) {
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().WildcardType();
            } else if (this.from().NoType().equals(their)) {
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoType();
            } else if (this.from().NoPrefix().equals(their)) {
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoPrefix();
            } else if (their == null) {
               return null;
            } else {
               throw new MatchError(their);
            }
         }
      }

      public Types.Type importType(final Types.Type their) {
         Option var2 = this.tpeMap().weakGet(their);
         if (var2 instanceof Some) {
            return (Types.Type)((Some)var2).value();
         } else if (scala.None..MODULE$.equals(var2)) {
            this.pendingTpes_$eq(this.pendingTpes() + 1);

            Types.Type var10000;
            try {
               Types.Type result = this.recreateType(their);
               this.tpeMap().weakUpdate(their, result);
               var10000 = result;
            } finally {
               this.pendingTpes_$eq(this.pendingTpes() - 1);
               this.tryFixup();
            }

            return var10000;
         } else {
            throw new MatchError(var2);
         }
      }

      public void recreatedTreeCompleter(final Trees.Tree their, final Trees.Tree my) {
         if (their.canHaveAttrs()) {
            if (my.hasSymbolField()) {
               my.symbol_$eq(this.importSymbol(their.symbol()));
            }

            my.pos_$eq(this.importPosition(their.pos()));
            if (their instanceof Trees.TypeTree) {
               Trees.TypeTree var3 = (Trees.TypeTree)their;
               if (my instanceof Trees.TypeTree) {
                  Trees.TypeTree var4 = (Trees.TypeTree)my;
                  if (var3.wasEmpty()) {
                     var4.defineType(this.importType(var3.tpe()));
                     return;
                  }

                  var4.setType(this.importType(var3.tpe()));
                  return;
               }
            }

            my.setType(this.importType(their.tpe()));
         }
      }

      public Trees.Tree recreateTree(final Trees.Tree their) {
         if (their instanceof Trees.ClassDef) {
            Trees.ClassDef var2 = (Trees.ClassDef)their;
            Trees.Modifiers mods = var2.mods();
            Names.TypeName name = var2.name();
            List tparams = var2.tparams();
            Trees.Template impl = var2.impl();
            Trees.ClassDef var321 = new Trees.ClassDef;
            SymbolTable var337 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.Modifiers var353 = this.importModifiers(mods);
            Names.TypeName var366 = this.importName(name).toTypeName();
            if (tparams == null) {
               throw null;
            } else {
               Object var369;
               if (tparams == scala.collection.immutable.Nil..MODULE$) {
                  var369 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.TypeDef var197 = (Trees.TypeDef)tparams.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$1(this, var197), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)tparams.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var197 = (Trees.TypeDef)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$1(this, var197), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var369 = map_h;
               }

               Object var215 = null;
               Object var216 = null;
               Object var217 = null;
               Object var218 = null;
               var321.<init>(var353, var366, (List)var369, this.importTemplate(impl));
               return var321;
            }
         } else if (their instanceof Trees.PackageDef) {
            Trees.PackageDef var7 = (Trees.PackageDef)their;
            Trees.RefTree pid = var7.pid();
            List stats = var7.stats();
            Trees.PackageDef var320 = new Trees.PackageDef;
            SymbolTable var336 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.RefTree var352 = this.importRefTree(pid);
            if (stats == null) {
               throw null;
            } else {
               Object var365;
               if (stats == scala.collection.immutable.Nil..MODULE$) {
                  var365 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.Tree var198 = (Trees.Tree)stats.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$2(this, var198), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)stats.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var198 = (Trees.Tree)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$2(this, var198), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var365 = map_h;
               }

               Object var219 = null;
               Object var220 = null;
               Object var221 = null;
               Object var222 = null;
               var320.<init>(var352, (List)var365);
               return var320;
            }
         } else if (their instanceof Trees.ModuleDef) {
            Trees.ModuleDef var10 = (Trees.ModuleDef)their;
            Trees.Modifiers mods = var10.mods();
            Names.TermName name = var10.name();
            Trees.Template impl = var10.impl();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new ModuleDef(this.importModifiers(mods), this.importName(name).toTermName(), this.importTemplate(impl));
         } else if (this.from().noSelfType().equals(their)) {
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().noSelfType();
         } else if (this.from().pendingSuperCall().equals(their)) {
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().pendingSuperCall();
         } else if (their instanceof Trees.ValDef) {
            Trees.ValDef var14 = (Trees.ValDef)their;
            Trees.Modifiers mods = var14.mods();
            Names.TermName name = var14.name();
            Trees.Tree tpt = var14.tpt();
            Trees.Tree rhs = var14.rhs();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new ValDef(this.importModifiers(mods), this.importName(name).toTermName(), this.importTree(tpt), this.importTree(rhs));
         } else if (their instanceof Trees.DefDef) {
            Trees.DefDef var19 = (Trees.DefDef)their;
            Trees.Modifiers mods = var19.mods();
            Names.TermName name = var19.name();
            List tparams = var19.tparams();
            List vparamss = var19.vparamss();
            Trees.Tree tpt = var19.tpt();
            Trees.Tree rhs = var19.rhs();
            Trees.DefDef var319 = new Trees.DefDef;
            SymbolTable var335 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.Modifiers var351 = this.importModifiers(mods);
            Names.TermName var364 = this.importName(name).toTermName();
            if (tparams == null) {
               throw null;
            } else {
               Object var368;
               if (tparams == scala.collection.immutable.Nil..MODULE$) {
                  var368 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.TypeDef var199 = (Trees.TypeDef)tparams.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$3(this, var199), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)tparams.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var199 = (Trees.TypeDef)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$3(this, var199), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var368 = map_h;
               }

               Object var223 = null;
               Object var224 = null;
               Object var225 = null;
               Object var226 = null;
               SymbolTable var10006 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
               Function1 mmap_f = (tree) -> this.importValDef(tree);
               if (var10006 == null) {
                  throw null;
               } else {
                  List var370 = Collections.mmap$(var10006, vparamss, mmap_f);
                  Object var227 = null;
                  var319.<init>(var351, var364, (List)var368, var370, this.importTree(tpt), this.importTree(rhs));
                  return var319;
               }
            }
         } else if (their instanceof Trees.TypeDef) {
            Trees.TypeDef var26 = (Trees.TypeDef)their;
            Trees.Modifiers mods = var26.mods();
            Names.TypeName name = var26.name();
            List tparams = var26.tparams();
            Trees.Tree rhs = var26.rhs();
            Trees.TypeDef var318 = new Trees.TypeDef;
            SymbolTable var334 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.Modifiers var350 = this.importModifiers(mods);
            Names.TypeName var363 = this.importName(name).toTypeName();
            if (tparams == null) {
               throw null;
            } else {
               Object var367;
               if (tparams == scala.collection.immutable.Nil..MODULE$) {
                  var367 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.TypeDef var200 = (Trees.TypeDef)tparams.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$5(this, var200), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)tparams.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var200 = (Trees.TypeDef)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$5(this, var200), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var367 = map_h;
               }

               Object var228 = null;
               Object var229 = null;
               Object var230 = null;
               Object var231 = null;
               var318.<init>(var350, var363, (List)var367, this.importTree(rhs));
               return var318;
            }
         } else if (their instanceof Trees.LabelDef) {
            Trees.LabelDef var31 = (Trees.LabelDef)their;
            Names.TermName name = var31.name();
            List params = var31.params();
            Trees.Tree rhs = var31.rhs();
            Trees.LabelDef var317 = new Trees.LabelDef;
            SymbolTable var333 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Names.TermName var349 = this.importName(name).toTermName();
            if (params == null) {
               throw null;
            } else {
               Object var362;
               if (params == scala.collection.immutable.Nil..MODULE$) {
                  var362 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.Ident var201 = (Trees.Ident)params.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$6(this, var201), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)params.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var201 = (Trees.Ident)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$6(this, var201), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var362 = map_h;
               }

               Object var232 = null;
               Object var233 = null;
               Object var234 = null;
               Object var235 = null;
               var317.<init>(var349, (List)var362, this.importTree(rhs));
               return var317;
            }
         } else if (their instanceof Trees.Import) {
            Trees.Import var35 = (Trees.Import)their;
            Trees.Tree expr = var35.expr();
            List selectors = var35.selectors();
            Trees.Import var316 = new Trees.Import;
            SymbolTable var332 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.Tree var348 = this.importTree(expr);
            if (selectors == null) {
               throw null;
            } else {
               Object var361;
               if (selectors == scala.collection.immutable.Nil..MODULE$) {
                  var361 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.ImportSelector var202 = (Trees.ImportSelector)selectors.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$7(this, var202), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)selectors.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var202 = (Trees.ImportSelector)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$7(this, var202), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var361 = map_h;
               }

               Object var236 = null;
               Object var237 = null;
               Object var238 = null;
               Object var239 = null;
               var316.<init>(var348, (List)var361);
               return var316;
            }
         } else if (their instanceof Trees.Template) {
            Trees.Template var38 = (Trees.Template)their;
            List parents = var38.parents();
            Trees.ValDef self = var38.self();
            List body = var38.body();
            Trees.Template var315 = new Trees.Template;
            SymbolTable var331 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            if (parents == null) {
               throw null;
            } else {
               Object var347;
               if (parents == scala.collection.immutable.Nil..MODULE$) {
                  var347 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.Tree var203 = (Trees.Tree)parents.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$8(this, var203), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)parents.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var203 = (Trees.Tree)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$8(this, var203), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var347 = map_h;
               }

               Object var240 = null;
               Object var241 = null;
               Object var242 = null;
               Object var243 = null;
               Trees.ValDef var360 = this.importValDef(self);
               if (body == null) {
                  throw null;
               } else {
                  Object var10005;
                  if (body == scala.collection.immutable.Nil..MODULE$) {
                     var10005 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Trees.Tree var204 = (Trees.Tree)body.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$9(this, var204), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)body.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var204 = (Trees.Tree)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$9(this, var204), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var10005 = map_h;
                  }

                  Object var244 = null;
                  Object var245 = null;
                  Object var246 = null;
                  Object var247 = null;
                  var315.<init>((List)var347, var360, (List)var10005);
                  return var315;
               }
            }
         } else if (their instanceof Trees.Block) {
            Trees.Block var42 = (Trees.Block)their;
            List stats = var42.stats();
            Trees.Tree expr = var42.expr();
            Trees.Block var314 = new Trees.Block;
            SymbolTable var330 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            if (stats == null) {
               throw null;
            } else {
               Object var346;
               if (stats == scala.collection.immutable.Nil..MODULE$) {
                  var346 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.Tree var205 = (Trees.Tree)stats.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$10(this, var205), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)stats.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var205 = (Trees.Tree)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$10(this, var205), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var346 = map_h;
               }

               Object var248 = null;
               Object var249 = null;
               Object var250 = null;
               Object var251 = null;
               var314.<init>((List)var346, this.importTree(expr));
               return var314;
            }
         } else if (their instanceof Trees.CaseDef) {
            Trees.CaseDef var45 = (Trees.CaseDef)their;
            Trees.Tree pat = var45.pat();
            Trees.Tree guard = var45.guard();
            Trees.Tree body = var45.body();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new CaseDef(this.importTree(pat), this.importTree(guard), this.importTree(body));
         } else if (their instanceof Trees.Alternative) {
            List trees = ((Trees.Alternative)their).trees();
            Trees.Alternative var313 = new Trees.Alternative;
            SymbolTable var329 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            if (trees == null) {
               throw null;
            } else {
               Object var345;
               if (trees == scala.collection.immutable.Nil..MODULE$) {
                  var345 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.Tree var206 = (Trees.Tree)trees.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$11(this, var206), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)trees.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var206 = (Trees.Tree)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$11(this, var206), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var345 = map_h;
               }

               Object var252 = null;
               Object var253 = null;
               Object var254 = null;
               Object var255 = null;
               var313.<init>((List)var345);
               return var313;
            }
         } else if (their instanceof Trees.Star) {
            Trees.Tree elem = ((Trees.Star)their).elem();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Star(this.importTree(elem));
         } else if (their instanceof Trees.Bind) {
            Trees.Bind var51 = (Trees.Bind)their;
            Names.Name name = var51.name();
            Trees.Tree body = var51.body();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Bind(this.importName(name), this.importTree(body));
         } else if (their instanceof Trees.UnApply) {
            Trees.UnApply var54 = (Trees.UnApply)their;
            Trees.Tree fun = var54.fun();
            List args = var54.args();
            Trees.UnApply var312 = new Trees.UnApply;
            SymbolTable var328 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.Tree var344 = this.importTree(fun);
            if (args == null) {
               throw null;
            } else {
               Object var359;
               if (args == scala.collection.immutable.Nil..MODULE$) {
                  var359 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.Tree var207 = (Trees.Tree)args.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$12(this, var207), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)args.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var207 = (Trees.Tree)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$12(this, var207), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var359 = map_h;
               }

               Object var256 = null;
               Object var257 = null;
               Object var258 = null;
               Object var259 = null;
               var312.<init>(var344, (List)var359);
               return var312;
            }
         } else if (their instanceof Trees.ArrayValue) {
            Trees.ArrayValue var57 = (Trees.ArrayValue)their;
            Trees.Tree elemtpt = var57.elemtpt();
            List elems = var57.elems();
            Trees.ArrayValue var311 = new Trees.ArrayValue;
            SymbolTable var327 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.Tree var343 = this.importTree(elemtpt);
            if (elems == null) {
               throw null;
            } else {
               Object var358;
               if (elems == scala.collection.immutable.Nil..MODULE$) {
                  var358 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.Tree var208 = (Trees.Tree)elems.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$13(this, var208), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)elems.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var208 = (Trees.Tree)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$13(this, var208), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var358 = map_h;
               }

               Object var260 = null;
               Object var261 = null;
               Object var262 = null;
               Object var263 = null;
               var311.<init>(var343, (List)var358);
               return var311;
            }
         } else if (their instanceof Trees.Function) {
            Trees.Function var60 = (Trees.Function)their;
            List vparams = var60.vparams();
            Trees.Tree body = var60.body();
            Trees.Function var310 = new Trees.Function;
            SymbolTable var326 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            if (vparams == null) {
               throw null;
            } else {
               Object var342;
               if (vparams == scala.collection.immutable.Nil..MODULE$) {
                  var342 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.ValDef var209 = (Trees.ValDef)vparams.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$14(this, var209), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)vparams.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var209 = (Trees.ValDef)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$14(this, var209), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var342 = map_h;
               }

               Object var264 = null;
               Object var265 = null;
               Object var266 = null;
               Object var267 = null;
               var310.<init>((List)var342, this.importTree(body));
               return var310;
            }
         } else if (their instanceof Trees.Assign) {
            Trees.Assign var63 = (Trees.Assign)their;
            Trees.Tree lhs = var63.lhs();
            Trees.Tree rhs = var63.rhs();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Assign(this.importTree(lhs), this.importTree(rhs));
         } else if (their instanceof Trees.NamedArg) {
            Trees.NamedArg var66 = (Trees.NamedArg)their;
            Trees.Tree lhs = var66.lhs();
            Trees.Tree rhs = var66.rhs();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new NamedArg(this.importTree(lhs), this.importTree(rhs));
         } else if (their instanceof Trees.If) {
            Trees.If var69 = (Trees.If)their;
            Trees.Tree cond = var69.cond();
            Trees.Tree thenp = var69.thenp();
            Trees.Tree elsep = var69.elsep();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new If(this.importTree(cond), this.importTree(thenp), this.importTree(elsep));
         } else if (their instanceof Trees.Match) {
            Trees.Match var73 = (Trees.Match)their;
            Trees.Tree selector = var73.selector();
            List cases = var73.cases();
            Trees.Match var309 = new Trees.Match;
            SymbolTable var325 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.Tree var341 = this.importTree(selector);
            if (cases == null) {
               throw null;
            } else {
               Object var357;
               if (cases == scala.collection.immutable.Nil..MODULE$) {
                  var357 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.CaseDef var210 = (Trees.CaseDef)cases.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$15(this, var210), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)cases.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var210 = (Trees.CaseDef)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$15(this, var210), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var357 = map_h;
               }

               Object var268 = null;
               Object var269 = null;
               Object var270 = null;
               Object var271 = null;
               var309.<init>(var341, (List)var357);
               return var309;
            }
         } else if (their instanceof Trees.Return) {
            Trees.Tree expr = ((Trees.Return)their).expr();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Return(this.importTree(expr));
         } else if (their instanceof Trees.Try) {
            Trees.Try var77 = (Trees.Try)their;
            Trees.Tree block = var77.block();
            List catches = var77.catches();
            Trees.Tree finalizer = var77.finalizer();
            Trees.Try var308 = new Trees.Try;
            SymbolTable var324 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.Tree var340 = this.importTree(block);
            if (catches == null) {
               throw null;
            } else {
               Object var356;
               if (catches == scala.collection.immutable.Nil..MODULE$) {
                  var356 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.CaseDef var211 = (Trees.CaseDef)catches.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$16(this, var211), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)catches.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var211 = (Trees.CaseDef)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$16(this, var211), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var356 = map_h;
               }

               Object var272 = null;
               Object var273 = null;
               Object var274 = null;
               Object var275 = null;
               var308.<init>(var340, (List)var356, this.importTree(finalizer));
               return var308;
            }
         } else if (their instanceof Trees.Throw) {
            Trees.Tree expr = ((Trees.Throw)their).expr();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Throw(this.importTree(expr));
         } else if (their instanceof Trees.New) {
            Trees.Tree tpt = ((Trees.New)their).tpt();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new New(this.importTree(tpt));
         } else if (their instanceof Trees.Typed) {
            Trees.Typed var83 = (Trees.Typed)their;
            Trees.Tree expr = var83.expr();
            Trees.Tree tpt = var83.tpt();
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Typed(this.importTree(expr), this.importTree(tpt));
         } else if (their instanceof Trees.TypeApply) {
            Trees.TypeApply var86 = (Trees.TypeApply)their;
            Trees.Tree fun = var86.fun();
            List args = var86.args();
            Trees.TypeApply var307 = new Trees.TypeApply;
            SymbolTable var323 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            Trees.Tree var339 = this.importTree(fun);
            if (args == null) {
               throw null;
            } else {
               Object var355;
               if (args == scala.collection.immutable.Nil..MODULE$) {
                  var355 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Trees.Tree var212 = (Trees.Tree)args.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$17(this, var212), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)args.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var212 = (Trees.Tree)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$17(this, var212), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var355 = map_h;
               }

               Object var276 = null;
               Object var277 = null;
               Object var278 = null;
               Object var279 = null;
               var307.<init>(var339, (List)var355);
               return var307;
            }
         } else if (!(their instanceof Trees.Apply)) {
            if (their instanceof Trees.ApplyDynamic) {
               Trees.ApplyDynamic var92 = (Trees.ApplyDynamic)their;
               Trees.Tree qual = var92.qual();
               List args = var92.args();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new ApplyDynamic(this.importTree(qual), args.map((theirx) -> this.importTree(theirx)));
            } else if (their instanceof Trees.Super) {
               Trees.Super var95 = (Trees.Super)their;
               Trees.Tree qual = var95.qual();
               Names.TypeName mix = var95.mix();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Super(this.importTree(qual), this.importName(mix).toTypeName());
            } else if (their instanceof Trees.This) {
               Names.TypeName qual = ((Trees.This)their).qual();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new This(this.importName(qual).toTypeName());
            } else if (their instanceof Trees.Select) {
               Trees.Select var99 = (Trees.Select)their;
               Trees.Tree qual = var99.qualifier();
               Names.Name name = var99.name();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Select(this.importTree(qual), this.importName(name));
            } else if (their instanceof Trees.Ident) {
               Names.Name name = ((Trees.Ident)their).name();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Ident(this.importName(name));
            } else if (their instanceof Trees.ReferenceToBoxed) {
               Trees.Ident ident = ((Trees.ReferenceToBoxed)their).ident();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new ReferenceToBoxed((Trees.Ident)this.importTree((Trees.Tree)ident));
            } else {
               if (their instanceof Trees.Literal) {
                  Constants.Constant constant = ((Trees.Literal)their).value();
                  if (constant != null) {
                     return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Literal(this.importConstant(constant));
                  }
               }

               if (their instanceof Trees.TypeTree) {
                  Trees.TypeTree var105 = (Trees.TypeTree)their;
                  Trees.TypeTree mytt = this.scala$reflect$internal$Importers$StandardImporter$$$outer().new TypeTree();
                  if (var105.original() != null) {
                     mytt.setOriginal(this.importTree(var105.original()));
                  }

                  return mytt;
               } else if (their instanceof Trees.Annotated) {
                  Trees.Annotated var107 = (Trees.Annotated)their;
                  Trees.Tree annot = var107.annot();
                  Trees.Tree arg = var107.arg();
                  return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Annotated(this.importTree(annot), this.importTree(arg));
               } else if (their instanceof Trees.SingletonTypeTree) {
                  Trees.Tree ref = ((Trees.SingletonTypeTree)their).ref();
                  return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new SingletonTypeTree(this.importTree(ref));
               } else if (their instanceof Trees.SelectFromTypeTree) {
                  Trees.SelectFromTypeTree var111 = (Trees.SelectFromTypeTree)their;
                  Trees.Tree qual = var111.qualifier();
                  Names.TypeName name = var111.name();
                  return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new SelectFromTypeTree(this.importTree(qual), this.importName(name).toTypeName());
               } else if (their instanceof Trees.CompoundTypeTree) {
                  Trees.Template templ = ((Trees.CompoundTypeTree)their).templ();
                  return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new CompoundTypeTree(this.importTemplate(templ));
               } else if (their instanceof Trees.AppliedTypeTree) {
                  Trees.AppliedTypeTree var115 = (Trees.AppliedTypeTree)their;
                  Trees.Tree tpt = var115.tpt();
                  List args = var115.args();
                  return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new AppliedTypeTree(this.importTree(tpt), args.map((theirx) -> this.importTree(theirx)));
               } else if (their instanceof Trees.TypeBoundsTree) {
                  Trees.TypeBoundsTree var118 = (Trees.TypeBoundsTree)their;
                  Trees.Tree lo = var118.lo();
                  Trees.Tree hi = var118.hi();
                  return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new TypeBoundsTree(this.importTree(lo), this.importTree(hi));
               } else if (their instanceof Trees.ExistentialTypeTree) {
                  Trees.ExistentialTypeTree var121 = (Trees.ExistentialTypeTree)their;
                  Trees.Tree tpt = var121.tpt();
                  List whereClauses = var121.whereClauses();
                  return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new ExistentialTypeTree(this.importTree(tpt), whereClauses.map((tree) -> this.importMemberDef(tree)));
               } else if (this.from().EmptyTree().equals(their)) {
                  return this.scala$reflect$internal$Importers$StandardImporter$$$outer().EmptyTree();
               } else if (their == null) {
                  return null;
               } else {
                  throw new MatchError(their);
               }
            }
         } else {
            Trees.Apply var89 = (Trees.Apply)their;
            Trees.Tree fun = var89.fun();
            List args = var89.args();
            if (their instanceof Trees.ApplyToImplicitArgs) {
               Trees.ApplyToImplicitArgs var306 = new Trees.ApplyToImplicitArgs;
               SymbolTable var322 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
               Trees.Tree var338 = this.importTree(fun);
               if (args == null) {
                  throw null;
               } else {
                  Object var354;
                  if (args == scala.collection.immutable.Nil..MODULE$) {
                     var354 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Trees.Tree var213 = (Trees.Tree)args.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$18(this, var213), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)args.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var213 = (Trees.Tree)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$18(this, var213), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var354 = map_h;
                  }

                  Object var280 = null;
                  Object var281 = null;
                  Object var282 = null;
                  Object var283 = null;
                  var306.<init>(var338, (List)var354);
                  return var306;
               }
            } else if (their instanceof Trees.ApplyImplicitView) {
               Trees.ApplyImplicitView var10000 = new Trees.ApplyImplicitView;
               SymbolTable var10002 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
               Trees.Tree var10003 = this.importTree(fun);
               if (args == null) {
                  throw null;
               } else {
                  Object var10004;
                  if (args == scala.collection.immutable.Nil..MODULE$) {
                     var10004 = scala.collection.immutable.Nil..MODULE$;
                  } else {
                     Trees.Tree var214 = (Trees.Tree)args.head();
                     scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$recreateTree$19(this, var214), scala.collection.immutable.Nil..MODULE$);
                     scala.collection.immutable..colon.colon map_t = map_h;

                     for(List map_rest = (List)args.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                        var214 = (Trees.Tree)map_rest.head();
                        scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$recreateTree$19(this, var214), scala.collection.immutable.Nil..MODULE$);
                        map_t.next_$eq(map_nx);
                        map_t = map_nx;
                     }

                     Statics.releaseFence();
                     var10004 = map_h;
                  }

                  Object var284 = null;
                  Object var285 = null;
                  Object var286 = null;
                  Object var287 = null;
                  var10000.<init>(var10003, (List)var10004);
                  return var10000;
               }
            } else {
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new Apply(this.importTree(fun), args.map((theirx) -> this.importTree(theirx)));
            }
         }
      }

      public Trees.Tree importTree(final Trees.Tree their) {
         Trees.Tree my = this.recreateTree(their);
         if (my != null) {
            label15: {
               this.addFixup((JFunction0.mcV.sp)() -> this.recreatedTreeCompleter(their, my));
               this.tryFixup();
               Position var10000 = their.pos();
               NoPosition$ var3 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoPosition();
               if (var10000 == null) {
                  if (var3 == null) {
                     break label15;
                  }
               } else if (var10000.equals(var3)) {
                  break label15;
               }

               my.setPos(this.importPosition(their.pos()));
            }
         }

         this.importAttachments(their.attachments().all()).foreach((x$7) -> (Trees.Tree)my.updateAttachment(x$7, scala.reflect.ClassTag..MODULE$.Any()));
         return my;
      }

      public Set importAttachments(final Set attachments) {
         return (Set)attachments.collect(new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final StandardImporter $outer;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               return x1 instanceof StdAttachments.ImportableAttachment ? ((StdAttachments.ImportableAttachment)x1).importAttachment(this.$outer) : default.apply(x1);
            }

            public final boolean isDefinedAt(final Object x1) {
               return x1 instanceof StdAttachments.ImportableAttachment;
            }

            public {
               if (StandardImporter.this == null) {
                  throw null;
               } else {
                  this.$outer = StandardImporter.this;
               }
            }
         });
      }

      public AnnotationInfos.AnnotationInfo importAnnotationInfo(final AnnotationInfos.AnnotationInfo ann) {
         Types.Type atp1 = this.importType(ann.atp());
         List var10000 = ann.args();
         if (var10000 == null) {
            throw null;
         } else {
            List map_this = var10000;
            Object var30;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var30 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Trees.Tree var16 = (Trees.Tree)map_this.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importTree(var16), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var16 = (Trees.Tree)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importTree(var16), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var30 = map_h;
            }

            Object var18 = null;
            Object var19 = null;
            Object var20 = null;
            Object var21 = null;
            Object var22 = null;
            List args1 = (List)var30;
            List var31 = ann.assocs();
            if (var31 == null) {
               throw null;
            } else {
               List map_this = var31;
               Object var32;
               if (map_this == scala.collection.immutable.Nil..MODULE$) {
                  var32 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Tuple2 var17 = (Tuple2)map_this.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$importAnnotationInfo$2(this, var17), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var17 = (Tuple2)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$importAnnotationInfo$2(this, var17), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var32 = map_h;
               }

               Object var23 = null;
               Object var24 = null;
               Object var25 = null;
               Object var26 = null;
               Object var27 = null;
               List assocs1 = (List)var32;
               Trees.Tree original1 = this.importTree(ann.original());
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().AnnotationInfo().apply(atp1, args1, assocs1).setOriginal(original1);
            }
         }
      }

      public AnnotationInfos.ClassfileAnnotArg importAnnotArg(final AnnotationInfos.ClassfileAnnotArg arg) {
         if (arg instanceof AnnotationInfos.LiteralAnnotArg) {
            Constants.Constant constant = ((AnnotationInfos.LiteralAnnotArg)arg).const();
            if (constant != null) {
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new LiteralAnnotArg(this.importConstant(constant));
            }
         }

         if (!(arg instanceof AnnotationInfos.ArrayAnnotArg)) {
            if (arg instanceof AnnotationInfos.NestedAnnotArg) {
               AnnotationInfos.AnnotationInfo annInfo = ((AnnotationInfos.NestedAnnotArg)arg).annInfo();
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new NestedAnnotArg(this.importAnnotationInfo(annInfo));
            } else if (this.from().UnmappableAnnotArg().equals(arg)) {
               return this.scala$reflect$internal$Importers$StandardImporter$$$outer().UnmappableAnnotArg();
            } else {
               throw new MatchError(arg);
            }
         } else {
            AnnotationInfos.ClassfileAnnotArg[] args = ((AnnotationInfos.ArrayAnnotArg)arg).args();
            AnnotationInfos.ArrayAnnotArg var10000 = new AnnotationInfos.ArrayAnnotArg;
            SymbolTable var10002 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
            ClassTag map$extension_ct = this.scala$reflect$internal$Importers$StandardImporter$$$outer().JavaArgumentTag();
            int map$extension_len = args.length;
            Object map$extension_ys = map$extension_ct.newArray(map$extension_len);
            if (map$extension_len > 0) {
               for(int map$extension_i = 0; map$extension_i < map$extension_len; ++map$extension_i) {
                  AnnotationInfos.ClassfileAnnotArg var9 = args[map$extension_i];
                  scala.runtime.ScalaRunTime..MODULE$.array_update(map$extension_ys, map$extension_i, this.importAnnotArg(var9));
               }
            }

            Object var10 = null;
            Object var11 = null;
            var10000.<init>((AnnotationInfos.ClassfileAnnotArg[])map$extension_ys);
            return var10000;
         }
      }

      public Position importPosition(final Position their) {
         return their;
      }

      public Scopes.Scope importScope(final Scopes.Scope decls) {
         SymbolTable var10000 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
         List var10001 = decls.toList();
         if (var10001 == null) {
            throw null;
         } else {
            List map_this = var10001;
            Object var14;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var14 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Symbols.Symbol var7 = (Symbols.Symbol)map_this.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importSymbol(var7), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var7 = (Symbols.Symbol)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importSymbol(var7), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var14 = map_h;
            }

            Object var8 = null;
            Object var9 = null;
            Object var10 = null;
            Object var11 = null;
            Object var12 = null;
            return var10000.newScopeWith((Seq)var14);
         }
      }

      public Names.Name importName(final Names.Name name) {
         return (Names.Name)(name.isTypeName() ? this.scala$reflect$internal$Importers$StandardImporter$$$outer().newTypeName(name.toString()) : this.scala$reflect$internal$Importers$StandardImporter$$$outer().newTermName(name.toString()));
      }

      public Trees.Modifiers importModifiers(final Trees.Modifiers mods) {
         Trees.Modifiers var10000 = new Trees.Modifiers;
         SymbolTable var10002 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
         long var10003 = mods.flags();
         Names.Name var10004 = this.importName(mods.privateWithin());
         List var10005 = mods.annotations();
         if (var10005 == null) {
            throw null;
         } else {
            List map_this = var10005;
            Object var14;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var14 = scala.collection.immutable.Nil..MODULE$;
            } else {
               Trees.Tree var7 = (Trees.Tree)map_this.head();
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importTree(var7), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  var7 = (Trees.Tree)map_rest.head();
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importTree(var7), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var14 = map_h;
            }

            Object var8 = null;
            Object var9 = null;
            Object var10 = null;
            Object var11 = null;
            Object var12 = null;
            var10000.<init>(var10003, var10004, (List)var14);
            return var10000;
         }
      }

      public Trees.ImportSelector importImportSelector(final Trees.ImportSelector sel) {
         return this.scala$reflect$internal$Importers$StandardImporter$$$outer().new ImportSelector(this.importName(sel.name()), sel.namePos(), sel.rename() != null ? this.importName(sel.rename()) : null, sel.renamePos());
      }

      public Trees.ValDef importValDef(final Trees.ValDef tree) {
         return (Trees.ValDef)this.importTree((Trees.Tree)tree);
      }

      public Trees.TypeDef importTypeDef(final Trees.TypeDef tree) {
         return (Trees.TypeDef)this.importTree((Trees.Tree)tree);
      }

      public Trees.MemberDef importMemberDef(final Trees.MemberDef tree) {
         return (Trees.MemberDef)this.importTree((Trees.Tree)tree);
      }

      public Trees.Template importTemplate(final Trees.Template tree) {
         return (Trees.Template)this.importTree((Trees.Tree)tree);
      }

      public Trees.RefTree importRefTree(final Trees.RefTree tree) {
         return (Trees.RefTree)this.importTree((Trees.Tree)tree);
      }

      public Trees.Ident importIdent(final Trees.Ident tree) {
         return (Trees.Ident)this.importTree((Trees.Tree)tree);
      }

      public Trees.CaseDef importCaseDef(final Trees.CaseDef tree) {
         return (Trees.CaseDef)this.importTree((Trees.Tree)tree);
      }

      public Constants.Constant importConstant(final Constants.Constant constant) {
         Constants.Constant var10000 = new Constants.Constant;
         SymbolTable var10002 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
         Object var10003;
         switch (constant.tag()) {
            case 12:
               var10003 = this.importType((Types.Type)constant.value());
               break;
            case 13:
               var10003 = this.importSymbol((Symbols.Symbol)constant.value());
               break;
            default:
               var10003 = constant.value();
         }

         var10000.<init>(var10003);
         return var10000;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Importers$StandardImporter$$$outer() {
         return this.$outer;
      }

      private final void reverse$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.reverse$module == null) {
               this.reverse$module = new reverse$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      // $FF: synthetic method
      public static final void $anonfun$tryFixup$1(final Function0 x$1) {
         x$1.apply$mcV$sp();
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$recreatedSymbolCompleter$1(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      private final Symbols.Symbol linkReferenced$1(final Symbols.TermSymbol my, final Symbols.TermSymbol their, final Function1 op) {
         this.symMap().weakUpdate(their, my);
         my.referenced_$eq((Symbols.Symbol)op.apply(their.referenced()));
         return my;
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$recreateSymbol$1(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$recreateSymbol$2(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$recreateSymbol$4(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      private final Symbols.Symbol cachedRecreateSymbol$1(final Symbols.Symbol their) {
         Option var2 = this.symMap().weakGet(their);
         return var2 instanceof Some ? (Symbols.Symbol)((Some)var2).value() : this.recreateSymbol(their);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$importSymbol$1(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$importSymbol$2(final Symbols.Symbol localCopy$1, final Symbols.Symbol x$4) {
         if (x$4 == null) {
            throw null;
         } else {
            return x$4.tpe_$times().matches(localCopy$1.tpe_$times());
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$importSymbol$3(final Symbols.Symbol x$5) {
         return !x$5.isMethod();
      }

      // $FF: synthetic method
      public static final String $anonfun$importSymbol$5(final Symbols.Symbol x$6) {
         return x$6.defString();
      }

      // $FF: synthetic method
      public static final String $anonfun$importSymbol$4(final Symbols.Symbol result$1, final Symbols.Symbol their$2) {
         StringBuilder var10000 = (new StringBuilder(92)).append("import failure: cannot determine unique overloaded method alternative from\n ");
         List var10001 = result$1.alternatives();
         if (var10001 == null) {
            throw null;
         } else {
            List map_this = var10001;
            Object var15;
            if (map_this == scala.collection.immutable.Nil..MODULE$) {
               var15 = scala.collection.immutable.Nil..MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_this.head()).defString(), scala.collection.immutable.Nil..MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_rest.head()).defString(), scala.collection.immutable.Nil..MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var15 = map_h;
            }

            Object var9 = null;
            Object var10 = null;
            Object var11 = null;
            Object var12 = null;
            Object var13 = null;
            String mkString_sep = "\n";
            String var16 = ((IterableOnceOps)var15).mkString("", mkString_sep, "");
            Object var8 = null;
            var10000 = var10000.append(var16).append("\n that matches ").append(their$2).append(":");
            if (their$2 == null) {
               throw null;
            } else {
               return var10000.append(their$2.tpe_$times()).toString();
            }
         }
      }

      private final Symbols.Symbol disambiguate$1(final Symbols.Symbol my, final Symbols.Symbol their$2) {
         Symbols.Symbol var10000;
         if (their$2.isMethod()) {
            Symbols.Symbol localCopy = this.cachedRecreateSymbol$1(their$2);
            var10000 = my.filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$importSymbol$2(localCopy, x$4)));
         } else {
            var10000 = my.filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$importSymbol$3(x$5)));
         }

         Symbols.Symbol result = var10000;
         SymbolTable var7 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
         boolean assert_assertion = !result.isOverloaded();
         if (var7 == null) {
            throw null;
         } else {
            SymbolTable assert_this = var7;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError($anonfun$importSymbol$4(result, their$2));
            } else {
               return result;
            }
         }
      }

      // $FF: synthetic method
      public static final String $anonfun$importSymbol$7(final Names.Name myname$1, final Types.Type myscope$1, final Symbols.Symbol myexisting$1) {
         return (new StringBuilder(2)).append(myname$1).append(" ").append(myscope$1.decl(myname$1)).append(" ").append(myexisting$1).toString();
      }

      private final Symbols.Symbol recreateOrRelink$1(final Symbols.Symbol their0$1) {
         if (their0$1 == null) {
            return null;
         } else if (their0$1.equals(this.from().NoSymbol())) {
            return this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoSymbol();
         } else if (their0$1.isRoot()) {
            return ((Mirrors.RootsBase)this.scala$reflect$internal$Importers$StandardImporter$$$outer().rootMirror()).RootClass();
         } else {
            boolean isModuleClass = their0$1.isModuleClass();
            boolean isTparam = their0$1.isTypeParameter() && their0$1.paramPos() >= 0;
            boolean isOverloaded = their0$1.isOverloaded();
            Types.Type theirscope = (Types.Type)(their0$1.owner().isClass() && !their0$1.owner().isRefinementClass() ? their0$1.owner().info() : this.from().NoType());
            if (!(isModuleClass ? theirscope.decl(their0$1.name()).moduleClass() : theirscope.decl(their0$1.name())).exists()) {
               theirscope = this.from().NoType();
            }

            Names.Name myname;
            Symbols.Symbol myowner;
            Object var10000;
            label98: {
               label97: {
                  myname = this.importName(their0$1.name());
                  myowner = this.importSymbol(their0$1.owner());
                  Types.NoType$ var9 = this.from().NoType();
                  if (theirscope == null) {
                     if (var9 == null) {
                        break label97;
                     }
                  } else if (theirscope.equals(var9)) {
                     break label97;
                  }

                  if (!myowner.hasFlag(549755813888L)) {
                     var10000 = myowner.info();
                     break label98;
                  }
               }

               var10000 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoType();
            }

            Types.Type myscope = (Types.Type)var10000;
            if (isModuleClass) {
               var10000 = this.importSymbol(their0$1.sourceModule()).moduleClass();
            } else if (isTparam) {
               if (myowner.hasFlag(549755813888L)) {
                  var10000 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoSymbol();
               } else {
                  List var27 = myowner.typeParams();
                  int apply_n = their0$1.paramPos();
                  if (var27 == null) {
                     throw null;
                  }

                  var10000 = (Symbols.Symbol)LinearSeqOps.apply$(var27, apply_n);
               }
            } else if (isOverloaded) {
               Types.Type var10001 = myowner.thisType();
               List var10002 = their0$1.alternatives();
               if (var10002 == null) {
                  throw null;
               }

               List map_this = var10002;
               Object var29;
               if (map_this == scala.collection.immutable.Nil..MODULE$) {
                  var29 = scala.collection.immutable.Nil..MODULE$;
               } else {
                  Symbols.Symbol var19 = (Symbols.Symbol)map_this.head();
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(this.importSymbol(var19), scala.collection.immutable.Nil..MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                     var19 = (Symbols.Symbol)map_rest.head();
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(this.importSymbol(var19), scala.collection.immutable.Nil..MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var29 = map_h;
               }

               Object var20 = null;
               Object var21 = null;
               Object var22 = null;
               Object var23 = null;
               Object var24 = null;
               var10000 = myowner.newOverloaded(var10001, (List)var29);
            } else {
               label74: {
                  label73: {
                     Types.NoType$ var12 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoType();
                     if (myscope == null) {
                        if (var12 != null) {
                           break label73;
                        }
                     } else if (!myscope.equals(var12)) {
                        break label73;
                     }

                     var10000 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoSymbol();
                     break label74;
                  }

                  var10000 = myscope.decl(myname);
               }

               Symbols.Symbol myexisting = (Symbols.Symbol)var10000;
               var10000 = myexisting.isOverloaded() ? this.disambiguate$1(myexisting, their0$1) : myexisting;
            }

            Symbols.Symbol myexisting = (Symbols.Symbol)var10000;
            return myexisting.orElse(() -> {
               Symbols.Symbol my = this.cachedRecreateSymbol$1(their0$1);
               Types.NoType$ var6 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoType();
               if (myscope == null) {
                  if (var6 == null) {
                     return my;
                  }
               } else if (myscope.equals(var6)) {
                  return my;
               }

               boolean var11;
               SymbolTable var10000;
               label30: {
                  label29: {
                     var10000 = this.scala$reflect$internal$Importers$StandardImporter$$$outer();
                     Symbols.Symbol var10001 = myscope.decls().lookup(myname);
                     Symbols.NoSymbol var7 = this.scala$reflect$internal$Importers$StandardImporter$$$outer().NoSymbol();
                     if (var10001 == null) {
                        if (var7 == null) {
                           break label29;
                        }
                     } else if (var10001.equals(var7)) {
                        break label29;
                     }

                     var11 = false;
                     break label30;
                  }

                  var11 = true;
               }

               boolean assert_assertion = var11;
               if (var10000 == null) {
                  throw null;
               } else {
                  SymbolTable assert_this = var10000;
                  if (!assert_assertion) {
                     throw assert_this.throwAssertionError($anonfun$importSymbol$7(myname, myscope, myexisting));
                  } else {
                     assert_this = null;
                     myscope.decls().enter(my);
                     return my;
                  }
               }
            });
         }
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$recreateType$1(final StandardImporter $this, final Types.Type their) {
         return $this.importType(their);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$recreateType$2(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$recreateType$3(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$recreateType$4(final StandardImporter $this, final Types.Type their) {
         return $this.importType(their);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$recreateType$6(final StandardImporter $this, final Types.Type their) {
         return $this.importType(their);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$recreateType$7(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$recreateType$8(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$recreateType$9(final StandardImporter $this, final Types.Type their) {
         return $this.importType(their);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$recreateType$10(final StandardImporter $this, final Types.Type their) {
         return $this.importType(their);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$recreateType$11(final StandardImporter $this, final Types.Type their) {
         return $this.importType(their);
      }

      // $FF: synthetic method
      public static final Types.Type $anonfun$recreateType$12(final StandardImporter $this, final Types.Type their) {
         return $this.importType(their);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$recreateType$13(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      // $FF: synthetic method
      public static final AnnotationInfos.AnnotationInfo $anonfun$recreateType$14(final StandardImporter $this, final AnnotationInfos.AnnotationInfo ann) {
         return $this.importAnnotationInfo(ann);
      }

      // $FF: synthetic method
      public static final Trees.TypeDef $anonfun$recreateTree$1(final StandardImporter $this, final Trees.TypeDef tree) {
         return $this.importTypeDef(tree);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$2(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.TypeDef $anonfun$recreateTree$3(final StandardImporter $this, final Trees.TypeDef tree) {
         return $this.importTypeDef(tree);
      }

      // $FF: synthetic method
      public static final Trees.TypeDef $anonfun$recreateTree$5(final StandardImporter $this, final Trees.TypeDef tree) {
         return $this.importTypeDef(tree);
      }

      // $FF: synthetic method
      public static final Trees.Ident $anonfun$recreateTree$6(final StandardImporter $this, final Trees.Ident tree) {
         return $this.importIdent(tree);
      }

      // $FF: synthetic method
      public static final Trees.ImportSelector $anonfun$recreateTree$7(final StandardImporter $this, final Trees.ImportSelector sel) {
         return $this.importImportSelector(sel);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$8(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$9(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$10(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$11(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$12(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$13(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.ValDef $anonfun$recreateTree$14(final StandardImporter $this, final Trees.ValDef tree) {
         return $this.importValDef(tree);
      }

      // $FF: synthetic method
      public static final Trees.CaseDef $anonfun$recreateTree$15(final StandardImporter $this, final Trees.CaseDef tree) {
         return $this.importCaseDef(tree);
      }

      // $FF: synthetic method
      public static final Trees.CaseDef $anonfun$recreateTree$16(final StandardImporter $this, final Trees.CaseDef tree) {
         return $this.importCaseDef(tree);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$17(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$18(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$recreateTree$19(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$importAnnotationInfo$1(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      // $FF: synthetic method
      public static final Tuple2 $anonfun$importAnnotationInfo$2(final StandardImporter $this, final Tuple2 x0$1) {
         if (x0$1 != null) {
            Names.Name name = (Names.Name)x0$1._1();
            AnnotationInfos.ClassfileAnnotArg arg = (AnnotationInfos.ClassfileAnnotArg)x0$1._2();
            return new Tuple2($this.importName(name), $this.importAnnotArg(arg));
         } else {
            throw new MatchError((Object)null);
         }
      }

      // $FF: synthetic method
      public static final AnnotationInfos.ClassfileAnnotArg $anonfun$importAnnotArg$1(final StandardImporter $this, final AnnotationInfos.ClassfileAnnotArg arg) {
         return $this.importAnnotArg(arg);
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$importScope$1(final StandardImporter $this, final Symbols.Symbol their0) {
         return $this.importSymbol(their0);
      }

      // $FF: synthetic method
      public static final Trees.Tree $anonfun$importModifiers$1(final StandardImporter $this, final Trees.Tree their) {
         return $this.importTree(their);
      }

      public StandardImporter() {
         if (Importers.this == null) {
            throw null;
         } else {
            this.$outer = Importers.this;
            super();
            this.pendingSyms = 0;
            this.pendingTpes = 0;
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$tryFixup$1$adapted(final Function0 x$1) {
         $anonfun$tryFixup$1(x$1);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      public class Cache extends WeakHashMap {
         // $FF: synthetic field
         public final StandardImporter $outer;

         public Option weakGet(final Object key) {
            Option var10000 = this.get(key);
            if (var10000 == null) {
               throw null;
            } else {
               Option flatMap_this = var10000;
               if (flatMap_this.isEmpty()) {
                  return scala.None..MODULE$;
               } else {
                  WeakReference var3 = (WeakReference)flatMap_this.get();
                  return scala.ref.WeakReference..MODULE$.unapply(var3);
               }
            }
         }

         public void weakUpdate(final Object key, final Object value) {
            WeakReference var10002 = scala.ref.WeakReference..MODULE$;
            this.update(key, new WeakReference(value));
         }

         // $FF: synthetic method
         public StandardImporter scala$reflect$internal$Importers$StandardImporter$Cache$$$outer() {
            return this.$outer;
         }

         // $FF: synthetic method
         public static final Option $anonfun$weakGet$1(final WeakReference wr) {
            return scala.ref.WeakReference..MODULE$.unapply(wr);
         }

         public Cache() {
            if (StandardImporter.this == null) {
               throw null;
            } else {
               this.$outer = StandardImporter.this;
               super();
            }
         }
      }

      public class reverse$ extends StandardImporter {
         private final SymbolTable from = StandardImporter.this.scala$reflect$internal$Importers$StandardImporter$$$outer();

         public SymbolTable from() {
            return this.from;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$new$1(final Tuple2 check$ifrefutable$1) {
            if (check$ifrefutable$1 != null) {
               WeakReference var1 = (WeakReference)check$ifrefutable$1._2();
               if (var1 != null && !scala.ref.WeakReference..MODULE$.unapply(var1).isEmpty()) {
                  return true;
               }
            }

            return false;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$new$3(final Tuple2 check$ifrefutable$2) {
            if (check$ifrefutable$2 != null) {
               WeakReference var1 = (WeakReference)check$ifrefutable$2._2();
               if (var1 != null && !scala.ref.WeakReference..MODULE$.unapply(var1).isEmpty()) {
                  return true;
               }
            }

            return false;
         }

         public reverse$() {
            StandardImporter.this.symMap().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$new$1(check$ifrefutable$1))).foreach((x$2) -> {
               if (x$2 != null) {
                  Symbols.Symbol theirsym = (Symbols.Symbol)x$2._1();
                  WeakReference var3 = (WeakReference)x$2._2();
                  if (var3 != null) {
                     Option var4 = scala.ref.WeakReference..MODULE$.unapply(var3);
                     if (!var4.isEmpty()) {
                        Symbols.Symbol mysym = (Symbols.Symbol)var4.get();
                        Cache var10000 = this.symMap();
                        WeakReference var10003 = scala.ref.WeakReference..MODULE$;
                        WeakReference var7 = new WeakReference(theirsym);
                        Tuple2 $plus$eq_elem = new Tuple2(mysym, var7);
                        if (var10000 == null) {
                           throw null;
                        }

                        return (Cache)var10000.addOne($plus$eq_elem);
                     }
                  }
               }

               throw new MatchError(x$2);
            });
            StandardImporter.this.tpeMap().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$new$3(check$ifrefutable$2))).foreach((x$3) -> {
               if (x$3 != null) {
                  Types.Type theirtpe = (Types.Type)x$3._1();
                  WeakReference var3 = (WeakReference)x$3._2();
                  if (var3 != null) {
                     Option var4 = scala.ref.WeakReference..MODULE$.unapply(var3);
                     if (!var4.isEmpty()) {
                        Types.Type mytpe = (Types.Type)var4.get();
                        Cache var10000 = this.tpeMap();
                        WeakReference var10003 = scala.ref.WeakReference..MODULE$;
                        WeakReference var7 = new WeakReference(theirtpe);
                        Tuple2 $plus$eq_elem = new Tuple2(mytpe, var7);
                        if (var10000 == null) {
                           throw null;
                        }

                        return (Cache)var10000.addOne($plus$eq_elem);
                     }
                  }
               }

               throw new MatchError(x$3);
            });
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      }
   }
}
