package org.apache.spark.sql.expressions;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.Encoder;
import scala.Option;
import scala.Product;
import scala.Option.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tEe!\u0002\u0017.\u0001>:\u0004\u0002\u0003)\u0001\u0005+\u0007I\u0011A)\t\u0011\u0019\u0004!\u0011#Q\u0001\nIC\u0001b\u001a\u0001\u0003\u0016\u0004%\t\u0001\u001b\u0005\t[\u0002\u0011\t\u0012)A\u0005S\"Aa\u000e\u0001BK\u0002\u0013\u0005q\u000e\u0003\u0005|\u0001\tE\t\u0015!\u0003q\u0011!a\bA!f\u0001\n\u0003i\b\"CA\u0002\u0001\tE\t\u0015!\u0003\u007f\u0011%\t)\u0001\u0001BK\u0002\u0013\u0005Q\u0010C\u0005\u0002\b\u0001\u0011\t\u0012)A\u0005}\"9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-\u0001bBA\r\u0001\u0011\u0005\u00131\u0004\u0005\b\u0003C\u0001A\u0011IA\u0012\u0011\u001d\t)\u0003\u0001C!\u0003GAq!a\b\u0001\t\u0003\n9\u0003C\u0005\u0002*\u0001\t\t\u0011\"\u0001\u0002,!I\u00111\n\u0001\u0012\u0002\u0013\u0005\u0011Q\n\u0005\n\u0003W\u0002\u0011\u0013!C\u0001\u0003[B\u0011\"!\u001f\u0001#\u0003%\t!a\u001f\t\u0013\u0005\u001d\u0005!%A\u0005\u0002\u0005%\u0005\"CAK\u0001E\u0005I\u0011AAL\u0011%\ty\nAA\u0001\n\u0003\n\t\u000bC\u0005\u00022\u0002\t\t\u0011\"\u0001\u00024\"I\u00111\u0018\u0001\u0002\u0002\u0013\u0005\u0011Q\u0018\u0005\n\u0003\u0007\u0004\u0011\u0011!C!\u0003\u000bD\u0011\"a5\u0001\u0003\u0003%\t!!6\t\u0013\u0005e\u0007!!A\u0005B\u0005m\u0007\"CAp\u0001\u0005\u0005I\u0011IAq\u0011%\t\u0019\u000fAA\u0001\n\u0003\n)\u000fC\u0005\u0002h\u0002\t\t\u0011\"\u0011\u0002j\u001eQ\u0011Q^\u0017\u0002\u0002#\u0005q&a<\u0007\u00131j\u0013\u0011!E\u0001_\u0005E\bbBA\u0005A\u0011\u0005!1\u0001\u0005\n\u0003G\u0004\u0013\u0011!C#\u0003KD\u0011B!\u0002!\u0003\u0003%\tIa\u0002\t\u0013\t\u001d\u0002%%A\u0005\u0002\t%\u0002\"\u0003B\u0019AE\u0005I\u0011\u0001B\u001a\u0011%\u0011Y\u0004II\u0001\n\u0003\u0011i\u0004C\u0005\u0003F\u0001\n\t\u0011\"!\u0003H!I!\u0011\u000e\u0011\u0012\u0002\u0013\u0005!1\u000e\u0005\n\u0005g\u0002\u0013\u0013!C\u0001\u0005kB\u0011B! !#\u0003%\tAa \t\u0013\t\u001d\u0005%!A\u0005\n\t%%!F+tKJ$UMZ5oK\u0012\fum\u001a:fO\u0006$xN\u001d\u0006\u0003]=\n1\"\u001a=qe\u0016\u001c8/[8og*\u0011\u0001'M\u0001\u0004gFd'B\u0001\u001a4\u0003\u0015\u0019\b/\u0019:l\u0015\t!T'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002m\u0005\u0019qN]4\u0016\ta:\u0016\rZ\n\u0005\u0001ej4\t\u0005\u0002;w5\tQ&\u0003\u0002=[\t\u0019Rk]3s\t\u00164\u0017N\\3e\rVt7\r^5p]B\u0011a(Q\u0007\u0002\u007f)\t\u0001)A\u0003tG\u0006d\u0017-\u0003\u0002C\u007f\t9\u0001K]8ek\u000e$\bC\u0001#N\u001d\t)5J\u0004\u0002G\u00156\tqI\u0003\u0002I\u0013\u00061AH]8piz\u001a\u0001!C\u0001A\u0013\tau(A\u0004qC\u000e\\\u0017mZ3\n\u00059{%\u0001D*fe&\fG.\u001b>bE2,'B\u0001'@\u0003)\twm\u001a:fO\u0006$xN]\u000b\u0002%B)!hU+aG&\u0011A+\f\u0002\u000b\u0003\u001e<'/Z4bi>\u0014\bC\u0001,X\u0019\u0001!Q\u0001\u0017\u0001C\u0002e\u0013!!\u0013(\u0012\u0005ik\u0006C\u0001 \\\u0013\tavHA\u0004O_RD\u0017N\\4\u0011\u0005yr\u0016BA0@\u0005\r\te.\u001f\t\u0003-\u0006$QA\u0019\u0001C\u0002e\u00131AQ+G!\t1F\rB\u0003f\u0001\t\u0007\u0011LA\u0002P+R\u000b1\"Y4he\u0016<\u0017\r^8sA\u0005a\u0011N\u001c9vi\u0016s7m\u001c3feV\t\u0011\u000eE\u0002kWVk\u0011aL\u0005\u0003Y>\u0012q!\u00128d_\u0012,'/A\u0007j]B,H/\u00128d_\u0012,'\u000fI\u0001\nO&4XM\u001c(b[\u0016,\u0012\u0001\u001d\t\u0004}E\u001c\u0018B\u0001:@\u0005\u0019y\u0005\u000f^5p]B\u0011A\u000f\u001f\b\u0003kZ\u0004\"AR \n\u0005]|\u0014A\u0002)sK\u0012,g-\u0003\u0002zu\n11\u000b\u001e:j]\u001eT!a^ \u0002\u0015\u001dLg/\u001a8OC6,\u0007%\u0001\u0005ok2d\u0017M\u00197f+\u0005q\bC\u0001 \u0000\u0013\r\t\ta\u0010\u0002\b\u0005>|G.Z1o\u0003%qW\u000f\u001c7bE2,\u0007%A\u0007eKR,'/\\5oSN$\u0018nY\u0001\u000fI\u0016$XM]7j]&\u001cH/[2!\u0003\u0019a\u0014N\\5u}Qa\u0011QBA\b\u0003#\t\u0019\"!\u0006\u0002\u0018A)!\bA+aG\")\u0001k\u0003a\u0001%\")qm\u0003a\u0001S\"9an\u0003I\u0001\u0002\u0004\u0001\bb\u0002?\f!\u0003\u0005\rA \u0005\t\u0003\u000bY\u0001\u0013!a\u0001}\u0006Aq/\u001b;i\u001d\u0006lW\r\u0006\u0003\u0002\u000e\u0005u\u0001BBA\u0010\u0019\u0001\u00071/\u0001\u0003oC6,\u0017!D1t\u001d>tg*\u001e7mC\ndW\r\u0006\u0002\u0002\u000e\u0005\u0011\u0012m\u001d(p]\u0012,G/\u001a:nS:L7\u000f^5d+\u0005\u0019\u0018\u0001B2paf,\u0002\"!\f\u00024\u0005]\u00121\b\u000b\r\u0003_\ti$!\u0011\u0002F\u0005\u001d\u0013\u0011\n\t\tu\u0001\t\t$!\u000e\u0002:A\u0019a+a\r\u0005\u000ba\u0003\"\u0019A-\u0011\u0007Y\u000b9\u0004B\u0003c!\t\u0007\u0011\fE\u0002W\u0003w!Q!\u001a\tC\u0002eC\u0001\u0002\u0015\t\u0011\u0002\u0003\u0007\u0011q\b\t\tuM\u000b\t$!\u000e\u0002:!Aq\r\u0005I\u0001\u0002\u0004\t\u0019\u0005\u0005\u0003kW\u0006E\u0002b\u00028\u0011!\u0003\u0005\r\u0001\u001d\u0005\byB\u0001\n\u00111\u0001\u007f\u0011!\t)\u0001\u0005I\u0001\u0002\u0004q\u0018AD2paf$C-\u001a4bk2$H%M\u000b\t\u0003\u001f\n)'a\u001a\u0002jU\u0011\u0011\u0011\u000b\u0016\u0004%\u0006M3FAA+!\u0011\t9&!\u0019\u000e\u0005\u0005e#\u0002BA.\u0003;\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005}s(\u0001\u0006b]:|G/\u0019;j_:LA!a\u0019\u0002Z\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000ba\u000b\"\u0019A-\u0005\u000b\t\f\"\u0019A-\u0005\u000b\u0015\f\"\u0019A-\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eUA\u0011qNA:\u0003k\n9(\u0006\u0002\u0002r)\u001a\u0011.a\u0015\u0005\u000ba\u0013\"\u0019A-\u0005\u000b\t\u0014\"\u0019A-\u0005\u000b\u0015\u0014\"\u0019A-\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gUA\u0011QPAA\u0003\u0007\u000b))\u0006\u0002\u0002\u0000)\u001a\u0001/a\u0015\u0005\u000ba\u001b\"\u0019A-\u0005\u000b\t\u001c\"\u0019A-\u0005\u000b\u0015\u001c\"\u0019A-\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iUA\u00111RAH\u0003#\u000b\u0019*\u0006\u0002\u0002\u000e*\u001aa0a\u0015\u0005\u000ba#\"\u0019A-\u0005\u000b\t$\"\u0019A-\u0005\u000b\u0015$\"\u0019A-\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kUA\u00111RAM\u00037\u000bi\nB\u0003Y+\t\u0007\u0011\fB\u0003c+\t\u0007\u0011\fB\u0003f+\t\u0007\u0011,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003G\u0003B!!*\u000206\u0011\u0011q\u0015\u0006\u0005\u0003S\u000bY+\u0001\u0003mC:<'BAAW\u0003\u0011Q\u0017M^1\n\u0007e\f9+\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u00026B\u0019a(a.\n\u0007\u0005evHA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002^\u0003\u007fC\u0011\"!1\u0019\u0003\u0003\u0005\r!!.\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t9\rE\u0003\u0002J\u0006=W,\u0004\u0002\u0002L*\u0019\u0011QZ \u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002R\u0006-'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2A`Al\u0011!\t\tMGA\u0001\u0002\u0004i\u0016A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a)\u0002^\"I\u0011\u0011Y\u000e\u0002\u0002\u0003\u0007\u0011QW\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011QW\u0001\ti>\u001cFO]5oOR\u0011\u00111U\u0001\u0007KF,\u0018\r\\:\u0015\u0007y\fY\u000f\u0003\u0005\u0002Bz\t\t\u00111\u0001^\u0003U)6/\u001a:EK\u001aLg.\u001a3BO\u001e\u0014XmZ1u_J\u0004\"A\u000f\u0011\u0014\u000b\u0001\n\u00190!?\u0011\u0007y\n)0C\u0002\u0002x~\u0012a!\u00118z%\u00164\u0007\u0003BA~\u0005\u0003i!!!@\u000b\t\u0005}\u00181V\u0001\u0003S>L1ATA\u007f)\t\ty/A\u0003baBd\u00170\u0006\u0005\u0003\n\t=!1\u0003B\f)1\u0011YA!\u0007\u0003\u001e\t\u0005\"1\u0005B\u0013!!Q\u0004A!\u0004\u0003\u0012\tU\u0001c\u0001,\u0003\u0010\u0011)\u0001l\tb\u00013B\u0019aKa\u0005\u0005\u000b\t\u001c#\u0019A-\u0011\u0007Y\u00139\u0002B\u0003fG\t\u0007\u0011\f\u0003\u0004QG\u0001\u0007!1\u0004\t\tuM\u0013iA!\u0005\u0003\u0016!1qm\ta\u0001\u0005?\u0001BA[6\u0003\u000e!9an\tI\u0001\u0002\u0004\u0001\bb\u0002?$!\u0003\u0005\rA \u0005\t\u0003\u000b\u0019\u0003\u0013!a\u0001}\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$3'\u0006\u0005\u0002~\t-\"Q\u0006B\u0018\t\u0015AFE1\u0001Z\t\u0015\u0011GE1\u0001Z\t\u0015)GE1\u0001Z\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\"T\u0003CAF\u0005k\u00119D!\u000f\u0005\u000ba+#\u0019A-\u0005\u000b\t,#\u0019A-\u0005\u000b\u0015,#\u0019A-\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIU*\u0002\"a#\u0003@\t\u0005#1\t\u0003\u00061\u001a\u0012\r!\u0017\u0003\u0006E\u001a\u0012\r!\u0017\u0003\u0006K\u001a\u0012\r!W\u0001\bk:\f\u0007\u000f\u001d7z+!\u0011IEa\u0016\u0003\\\t}C\u0003\u0002B&\u0005G\u0002BAP9\u0003NAQaHa\u0014\u0003T\t\u0005\u0004O @\n\u0007\tEsH\u0001\u0004UkBdW-\u000e\t\tuM\u0013)F!\u0017\u0003^A\u0019aKa\u0016\u0005\u000ba;#\u0019A-\u0011\u0007Y\u0013Y\u0006B\u0003cO\t\u0007\u0011\fE\u0002W\u0005?\"Q!Z\u0014C\u0002e\u0003BA[6\u0003V!I!QM\u0014\u0002\u0002\u0003\u0007!qM\u0001\u0004q\u0012\u0002\u0004\u0003\u0003\u001e\u0001\u0005+\u0012IF!\u0018\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+!\tiH!\u001c\u0003p\tED!\u0002-)\u0005\u0004IF!\u00022)\u0005\u0004IF!B3)\u0005\u0004I\u0016a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0005\u0002\f\n]$\u0011\u0010B>\t\u0015A\u0016F1\u0001Z\t\u0015\u0011\u0017F1\u0001Z\t\u0015)\u0017F1\u0001Z\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%kUA\u00111\u0012BA\u0005\u0007\u0013)\tB\u0003YU\t\u0007\u0011\fB\u0003cU\t\u0007\u0011\fB\u0003fU\t\u0007\u0011,\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\fB!\u0011Q\u0015BG\u0013\u0011\u0011y)a*\u0003\r=\u0013'.Z2u\u0001"
)
public class UserDefinedAggregator extends UserDefinedFunction implements Product, Serializable {
   private final Aggregator aggregator;
   private final Encoder inputEncoder;
   private final Option givenName;
   private final boolean nullable;
   private final boolean deterministic;

   public static boolean $lessinit$greater$default$5() {
      return UserDefinedAggregator$.MODULE$.$lessinit$greater$default$5();
   }

   public static boolean $lessinit$greater$default$4() {
      return UserDefinedAggregator$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return UserDefinedAggregator$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final UserDefinedAggregator x$0) {
      return UserDefinedAggregator$.MODULE$.unapply(x$0);
   }

   public static boolean apply$default$5() {
      return UserDefinedAggregator$.MODULE$.apply$default$5();
   }

   public static boolean apply$default$4() {
      return UserDefinedAggregator$.MODULE$.apply$default$4();
   }

   public static Option apply$default$3() {
      return UserDefinedAggregator$.MODULE$.apply$default$3();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Aggregator aggregator() {
      return this.aggregator;
   }

   public Encoder inputEncoder() {
      return this.inputEncoder;
   }

   public Option givenName() {
      return this.givenName;
   }

   public boolean nullable() {
      return this.nullable;
   }

   public boolean deterministic() {
      return this.deterministic;
   }

   public UserDefinedAggregator withName(final String name) {
      Option x$1 = .MODULE$.apply(name);
      Aggregator x$2 = this.copy$default$1();
      Encoder x$3 = this.copy$default$2();
      boolean x$4 = this.copy$default$4();
      boolean x$5 = this.copy$default$5();
      return this.copy(x$2, x$3, x$1, x$4, x$5);
   }

   public UserDefinedAggregator asNonNullable() {
      if (!this.nullable()) {
         return this;
      } else {
         boolean x$1 = false;
         Aggregator x$2 = this.copy$default$1();
         Encoder x$3 = this.copy$default$2();
         Option x$4 = this.copy$default$3();
         boolean x$5 = this.copy$default$5();
         return this.copy(x$2, x$3, x$4, false, x$5);
      }
   }

   public UserDefinedAggregator asNondeterministic() {
      if (!this.deterministic()) {
         return this;
      } else {
         boolean x$1 = false;
         Aggregator x$2 = this.copy$default$1();
         Encoder x$3 = this.copy$default$2();
         Option x$4 = this.copy$default$3();
         boolean x$5 = this.copy$default$4();
         return this.copy(x$2, x$3, x$4, x$5, false);
      }
   }

   public String name() {
      return (String)this.givenName().getOrElse(() -> this.aggregator().name());
   }

   public UserDefinedAggregator copy(final Aggregator aggregator, final Encoder inputEncoder, final Option givenName, final boolean nullable, final boolean deterministic) {
      return new UserDefinedAggregator(aggregator, inputEncoder, givenName, nullable, deterministic);
   }

   public Aggregator copy$default$1() {
      return this.aggregator();
   }

   public Encoder copy$default$2() {
      return this.inputEncoder();
   }

   public Option copy$default$3() {
      return this.givenName();
   }

   public boolean copy$default$4() {
      return this.nullable();
   }

   public boolean copy$default$5() {
      return this.deterministic();
   }

   public String productPrefix() {
      return "UserDefinedAggregator";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.aggregator();
         }
         case 1 -> {
            return this.inputEncoder();
         }
         case 2 -> {
            return this.givenName();
         }
         case 3 -> {
            return BoxesRunTime.boxToBoolean(this.nullable());
         }
         case 4 -> {
            return BoxesRunTime.boxToBoolean(this.deterministic());
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
      return x$1 instanceof UserDefinedAggregator;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "aggregator";
         }
         case 1 -> {
            return "inputEncoder";
         }
         case 2 -> {
            return "givenName";
         }
         case 3 -> {
            return "nullable";
         }
         case 4 -> {
            return "deterministic";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.aggregator()));
      var1 = Statics.mix(var1, Statics.anyHash(this.inputEncoder()));
      var1 = Statics.mix(var1, Statics.anyHash(this.givenName()));
      var1 = Statics.mix(var1, this.nullable() ? 1231 : 1237);
      var1 = Statics.mix(var1, this.deterministic() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof UserDefinedAggregator) {
               UserDefinedAggregator var4 = (UserDefinedAggregator)x$1;
               if (this.nullable() == var4.nullable() && this.deterministic() == var4.deterministic()) {
                  label64: {
                     Aggregator var10000 = this.aggregator();
                     Aggregator var5 = var4.aggregator();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label64;
                     }

                     Encoder var8 = this.inputEncoder();
                     Encoder var6 = var4.inputEncoder();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label64;
                        }
                     } else if (!var8.equals(var6)) {
                        break label64;
                     }

                     Option var9 = this.givenName();
                     Option var7 = var4.givenName();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label64;
                        }
                     } else if (!var9.equals(var7)) {
                        break label64;
                     }

                     if (var4.canEqual(this)) {
                        break label71;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public UserDefinedAggregator(final Aggregator aggregator, final Encoder inputEncoder, final Option givenName, final boolean nullable, final boolean deterministic) {
      this.aggregator = aggregator;
      this.inputEncoder = inputEncoder;
      this.givenName = givenName;
      this.nullable = nullable;
      this.deterministic = deterministic;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
