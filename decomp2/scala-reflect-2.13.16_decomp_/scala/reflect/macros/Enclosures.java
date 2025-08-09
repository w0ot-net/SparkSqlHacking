package scala.reflect.macros;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.reflect.api.Trees;
import scala.reflect.macros.blackbox.Context;
import scala.runtime.AbstractFunction2;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\tec\u0001\u0003\u0013&!\u0003\r\t\u0001L$\t\u000bE\u0002A\u0011\u0001\u001a\t\u000bY\u0002a\u0011A\u001c\t\u000by\u0002a\u0011A \t\u000b5\u0003a\u0011\u0001(\t\u000bI\u0003a\u0011A\u001c\t\u000bu\u0003a\u0011A\u001c\t\u000b}\u0003a\u0011\u00011\t\u000b1\u0004a\u0011A7\t\u000bI\u0004a\u0011A:\t\u000ba\u0004a\u0011A=\t\u000by\u0004a\u0011A@\t\u000f\u0005%\u0001A\"\u0001\u0002\f\u00191\u0011Q\u0003\u0001A\u0003/A!\"a\u000e\u000e\u0005+\u0007I\u0011AA\u001d\u0011)\t)&\u0004B\tB\u0003%\u00111\b\u0005\u000b\u0003Kj!Q3A\u0005\u0002\u0005\u001d\u0004BCA6\u001b\tE\t\u0015!\u0003\u0002j!9\u0011QN\u0007\u0005\u0002\u0005=\u0004\"CA@\u001b\u0005\u0005I\u0011AAA\u0011%\t9)DI\u0001\n\u0003\tI\tC\u0005\u000246\t\n\u0011\"\u0001\u00026\"I\u0011\u0011X\u0007\u0002\u0002\u0013\u0005\u00131\u0018\u0005\n\u0003\u0007l\u0011\u0011!C\u0001\u0003\u000bD\u0011\"!4\u000e\u0003\u0003%\t!a4\t\u0013\u0005UW\"!A\u0005B\u0005]\u0007\"CAs\u001b\u0005\u0005I\u0011AAt\u0011%\t\t0DA\u0001\n\u0003\n\u0019\u0010C\u0005\u0002x6\t\t\u0011\"\u0011\u0002z\"I\u00111`\u0007\u0002\u0002\u0013\u0005\u0013Q`\u0004\n\u0005\u0007\u0001\u0011\u0011!E\u0001\u0005\u000b1\u0011\"!\u0006\u0001\u0003\u0003E\tAa\u0002\t\u000f\u00055t\u0004\"\u0001\u0003(!I!\u0011F\u0010\u0002\u0002\u0013\u0015#1\u0006\u0005\n\u0005[y\u0012\u0011!CA\u0005_A\u0011B!\u0010 \u0003\u0003%\tIa\u0010\u0003\u0015\u0015s7\r\\8tkJ,7O\u0003\u0002'O\u00051Q.Y2s_NT!\u0001K\u0015\u0002\u000fI,g\r\\3di*\t!&A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001i\u0003C\u0001\u00180\u001b\u0005I\u0013B\u0001\u0019*\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012a\r\t\u0003]QJ!!N\u0015\u0003\tUs\u0017\u000e^\u0001\u0011[\u0006\u001c'o\\!qa2L7-\u0019;j_:,\u0012\u0001\u000f\t\u0003sij\u0011\u0001A\u0005\u0003wq\u0012A\u0001\u0016:fK&\u0011Q(\n\u0002\b\u00032L\u0017m]3t\u0003=)gn\u00197pg&tw-T1de>\u001cX#\u0001!\u0011\u0007\u0005#uI\u0004\u0002/\u0005&\u00111)K\u0001\ba\u0006\u001c7.Y4f\u0013\t)eI\u0001\u0003MSN$(BA\"*!\tA5*D\u0001J\u0015\tQU%\u0001\u0005cY\u0006\u001c7NY8y\u0013\ta\u0015JA\u0004D_:$X\r\u001f;\u0002#\u0015t7\r\\8tS:<\u0007k\\:ji&|g.F\u0001P!\tI\u0004+\u0003\u0002Ry\tA\u0001k\\:ji&|g.A\bf]\u000edwn]5oO6+G\u000f[8eQ\u0019)Ak\u0016-[7B\u0011a&V\u0005\u0003-&\u0012!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f\u0013!W\u0001YG:*gn\u00197pg&tw\r\u0016:fK6\u001aH/\u001f7fA\u0005\u0003\u0016j\u001d\u0011be\u0016\u0004cn\\<!I\u0016\u0004(/Z2bi\u0016$7\bI2p]N,H\u000e\u001e\u0011uQ\u0016\u00043oY1mC\u0012|7\r\t4pe\u0002jwN]3!S:4wN]7bi&|g.A\u0003tS:\u001cW-I\u0001]\u0003\u0019\u0011d&M\u0019/a\u0005qQM\\2m_NLgnZ\"mCN\u001c\bF\u0002\u0004U/bS6,\u0001\u0007f]\u000edwn]5oO\u0012+g-F\u0001b!\t\u0011WM\u0004\u0002:G&\u0011AmS\u0001\tk:Lg/\u001a:tK&\u0011am\u001a\u0002\u0007\t\u00164G)\u001a4\n\u0005!L'!\u0002+sK\u0016\u001c(B\u00016(\u0003\r\t\u0007/\u001b\u0015\u0007\u000fQ;\u0006LW.\u0002#\u0015t7\r\\8tS:<G+Z7qY\u0006$X-F\u0001o!\t\u0011w.\u0003\u0002qO\nAA+Z7qY\u0006$X\r\u000b\u0004\t)^C&lW\u0001\u000eK:\u001cGn\\:j]\u001eLU\u000e\u001d7\u0016\u0003Q\u0004\"AY;\n\u0005Y<'aB%na2$UM\u001a\u0015\u0007\u0013Q;\u0006LW.\u0002!\u0015t7\r\\8tS:<\u0007+Y2lC\u001e,W#\u0001>\u0011\u0005\t\\\u0018B\u0001?h\u0005)\u0001\u0016mY6bO\u0016$UM\u001a\u0015\u0007\u0015Q;\u0006LW.\u0002\u001b\u0015t7\r\\8tS:<WK\\5u+\t\t\t\u0001E\u0002:\u0003\u0007I1!!\u0002=\u0005=\u0019u.\u001c9jY\u0006$\u0018n\u001c8V]&$\bFB\u0006U/bS6,\u0001\u0007f]\u000edwn]5oOJ+h.\u0006\u0002\u0002\u000eA\u0019\u0011(a\u0004\n\u0007\u0005EAHA\u0002Sk:Dc\u0001\u0004+X1j[&AE#oG2|7/\u001e:f\u000bb\u001cW\r\u001d;j_:\u001cr!DA\r\u0003?\t)\u0003E\u0002B\u00037I1!!\bG\u0005%)\u0005pY3qi&|g\u000eE\u0002/\u0003CI1!a\t*\u0005\u001d\u0001&o\u001c3vGR\u0004B!a\n\u000249\u0019\u0011\u0011\u0006\"\u000f\t\u0005-\u0012\u0011G\u0007\u0003\u0003[Q1!a\f,\u0003\u0019a$o\\8u}%\t!&C\u0002\u00026\u0019\u0013AbU3sS\u0006d\u0017N_1cY\u0016\f\u0001\"\u001a=qK\u000e$X\rZ\u000b\u0003\u0003w\u0001D!!\u0010\u0002RA1\u0011qHA$\u0003\u001brA!!\u0011\u0002DA\u0019\u00111F\u0015\n\u0007\u0005\u0015\u0013&\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u0013\nYEA\u0003DY\u0006\u001c8OC\u0002\u0002F%\u0002B!a\u0014\u0002R1\u0001AaCA*\u001f\u0005\u0005\t\u0011!B\u0001\u0003/\u00121a\u0018\u00132\u0003%)\u0007\u0010]3di\u0016$\u0007%\u0005\u0003\u0002Z\u0005}\u0003c\u0001\u0018\u0002\\%\u0019\u0011QL\u0015\u0003\u000f9{G\u000f[5oOB\u0019a&!\u0019\n\u0007\u0005\r\u0014FA\u0002B]f\fa\"\u001a8dY>\u001c\u0018N\\4Ue\u0016,7/\u0006\u0002\u0002jA\u0019\u0011\t\u0012\u001d\u0002\u001f\u0015t7\r\\8tS:<GK]3fg\u0002\na\u0001P5oSRtDCBA9\u0003g\ni\b\u0005\u0002:\u001b!9\u0011q\u0007\nA\u0002\u0005U\u0004\u0007BA<\u0003w\u0002b!a\u0010\u0002H\u0005e\u0004\u0003BA(\u0003w\"A\"a\u0015\u0002t\u0005\u0005\t\u0011!B\u0001\u0003/Bq!!\u001a\u0013\u0001\u0004\tI'\u0001\u0003d_BLHCBA9\u0003\u0007\u000b)\tC\u0005\u00028M\u0001\n\u00111\u0001\u0002v!I\u0011QM\n\u0011\u0002\u0003\u0007\u0011\u0011N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tY\t\r\u0003\u0002\u000e\u0006}%\u0006BAH\u0003C\u0003b!!%\u0002\u001c\u0006uUBAAJ\u0015\u0011\t)*a&\u0002\t1\fgn\u001a\u0006\u0003\u00033\u000bAA[1wC&!\u0011\u0011JAJ!\u0011\ty%a(\u0005\u0017\u0005MC#!A\u0001\u0002\u000b\u0005\u0011qK\u0016\u0003\u0003G\u0003B!!*\u000206\u0011\u0011q\u0015\u0006\u0005\u0003S\u000bY+A\u0005v]\u000eDWmY6fI*\u0019\u0011QV\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u00022\u0006\u001d&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\\U\u0011\tI'!)\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ti\f\u0005\u0003\u0002\u0012\u0006}\u0016\u0002BAa\u0003'\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAAd!\rq\u0013\u0011Z\u0005\u0004\u0003\u0017L#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA0\u0003#D\u0011\"a5\u0019\u0003\u0003\u0005\r!a2\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\u000e\u0005\u0004\u0002\\\u0006\u0005\u0018qL\u0007\u0003\u0003;T1!a8*\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003G\fiN\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAu\u0003_\u00042ALAv\u0013\r\ti/\u000b\u0002\b\u0005>|G.Z1o\u0011%\t\u0019NGA\u0001\u0002\u0004\ty&\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA_\u0003kD\u0011\"a5\u001c\u0003\u0003\u0005\r!a2\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a2\u0002\r\u0015\fX/\u00197t)\u0011\tI/a@\t\u0013\u0005MW$!AA\u0002\u0005}\u0003FB\u0007U/bS6,\u0001\nF]\u000edwn];sK\u0016C8-\u001a9uS>t\u0007CA\u001d '\u0015y\"\u0011\u0002B\u000f!)\u0011YA!\u0005\u0003\u0016\u0005%\u0014\u0011O\u0007\u0003\u0005\u001bQ1Aa\u0004*\u0003\u001d\u0011XO\u001c;j[\u0016LAAa\u0005\u0003\u000e\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a1\t\t]!1\u0004\t\u0007\u0003\u007f\t9E!\u0007\u0011\t\u0005=#1\u0004\u0003\f\u0003'z\u0012\u0011!A\u0001\u0006\u0003\t9\u0006\u0005\u0003\u0003 \t\u0015RB\u0001B\u0011\u0015\u0011\u0011\u0019#a&\u0002\u0005%|\u0017\u0002BA\u001b\u0005C!\"A!\u0002\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!0\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\r\u0005E$\u0011\u0007B\u001e\u0011\u001d\t9D\ta\u0001\u0005g\u0001DA!\u000e\u0003:A1\u0011qHA$\u0005o\u0001B!a\u0014\u0003:\u0011a\u00111\u000bB\u0019\u0003\u0003\u0005\tQ!\u0001\u0002X!9\u0011Q\r\u0012A\u0002\u0005%\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0005\u0003\u0012)\u0006E\u0003/\u0005\u0007\u00129%C\u0002\u0003F%\u0012aa\u00149uS>t\u0007c\u0002\u0018\u0003J\t5\u0013\u0011N\u0005\u0004\u0005\u0017J#A\u0002+va2,'\u0007\r\u0003\u0003P\tM\u0003CBA \u0003\u000f\u0012\t\u0006\u0005\u0003\u0002P\tMCaCA*G\u0005\u0005\t\u0011!B\u0001\u0003/B\u0011Ba\u0016$\u0003\u0003\u0005\r!!\u001d\u0002\u0007a$\u0003\u0007"
)
public interface Enclosures {
   EnclosureException$ EnclosureException();

   Trees.TreeApi macroApplication();

   List enclosingMacros();

   Position enclosingPosition();

   /** @deprecated */
   Trees.TreeApi enclosingMethod();

   /** @deprecated */
   Trees.TreeApi enclosingClass();

   /** @deprecated */
   Trees.DefDefApi enclosingDef();

   /** @deprecated */
   Trees.TemplateApi enclosingTemplate();

   /** @deprecated */
   Trees.ImplDefApi enclosingImpl();

   /** @deprecated */
   Trees.PackageDefApi enclosingPackage();

   /** @deprecated */
   Universe.CompilationUnitContextApi enclosingUnit();

   /** @deprecated */
   Universe.RunContextApi enclosingRun();

   static void $init$(final Enclosures $this) {
   }

   /** @deprecated */
   public class EnclosureException extends Exception implements Product {
      private final Class expected;
      private final List enclosingTrees;
      // $FF: synthetic field
      public final Context $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Class expected() {
         return this.expected;
      }

      public List enclosingTrees() {
         return this.enclosingTrees;
      }

      public EnclosureException copy(final Class expected, final List enclosingTrees) {
         return this.scala$reflect$macros$Enclosures$EnclosureException$$$outer().new EnclosureException(expected, enclosingTrees);
      }

      public Class copy$default$1() {
         return this.expected();
      }

      public List copy$default$2() {
         return this.enclosingTrees();
      }

      public String productPrefix() {
         return "EnclosureException";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.expected();
            case 1:
               return this.enclosingTrees();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof EnclosureException;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "expected";
            case 1:
               return "enclosingTrees";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$.productHash(this, -889275714, false);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof EnclosureException && ((EnclosureException)x$1).scala$reflect$macros$Enclosures$EnclosureException$$$outer() == this.scala$reflect$macros$Enclosures$EnclosureException$$$outer()) {
               EnclosureException var2 = (EnclosureException)x$1;
               Class var10000 = this.expected();
               Class var3 = var2.expected();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               List var5 = this.enclosingTrees();
               List var4 = var2.enclosingTrees();
               if (var5 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var5.equals(var4)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public Context scala$reflect$macros$Enclosures$EnclosureException$$$outer() {
         return this.$outer;
      }

      public EnclosureException(final Class expected, final List enclosingTrees) {
         this.expected = expected;
         this.enclosingTrees = enclosingTrees;
         if (Enclosures.this == null) {
            throw null;
         } else {
            this.$outer = Enclosures.this;
            super((new StringBuilder(52)).append("Couldn't find a tree of type ").append(expected).append(" among enclosing trees ").append(enclosingTrees).toString());
         }
      }
   }

   public class EnclosureException$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final Context $outer;

      public final String toString() {
         return "EnclosureException";
      }

      public EnclosureException apply(final Class expected, final List enclosingTrees) {
         return this.$outer.new EnclosureException(expected, enclosingTrees);
      }

      public Option unapply(final EnclosureException x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.expected(), x$0.enclosingTrees())));
      }

      public EnclosureException$() {
         if (Enclosures.this == null) {
            throw null;
         } else {
            this.$outer = Enclosures.this;
            super();
         }
      }
   }
}
