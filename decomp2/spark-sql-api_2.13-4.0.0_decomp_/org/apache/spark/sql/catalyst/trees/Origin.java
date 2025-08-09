package org.apache.spark.sql.catalyst.trees;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.QueryContext;
import org.apache.spark.util.ArrayImplicits.;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t%d\u0001\u0002\"D\u0001BC\u0001B\u001a\u0001\u0003\u0016\u0004%\ta\u001a\u0005\t]\u0002\u0011\t\u0012)A\u0005Q\"Aq\u000e\u0001BK\u0002\u0013\u0005q\r\u0003\u0005q\u0001\tE\t\u0015!\u0003i\u0011!\t\bA!f\u0001\n\u00039\u0007\u0002\u0003:\u0001\u0005#\u0005\u000b\u0011\u00025\t\u0011M\u0004!Q3A\u0005\u0002\u001dD\u0001\u0002\u001e\u0001\u0003\u0012\u0003\u0006I\u0001\u001b\u0005\tk\u0002\u0011)\u001a!C\u0001m\"I\u0011\u0011\u0001\u0001\u0003\u0012\u0003\u0006Ia\u001e\u0005\n\u0003\u0007\u0001!Q3A\u0005\u0002YD\u0011\"!\u0002\u0001\u0005#\u0005\u000b\u0011B<\t\u0013\u0005\u001d\u0001A!f\u0001\n\u00031\b\"CA\u0005\u0001\tE\t\u0015!\u0003x\u0011)\tY\u0001\u0001BK\u0002\u0013\u0005\u0011Q\u0002\u0005\u000b\u0003O\u0001!\u0011#Q\u0001\n\u0005=\u0001BCA\u0015\u0001\tU\r\u0011\"\u0001\u0002,!Q\u0011Q\u0007\u0001\u0003\u0012\u0003\u0006I!!\f\t\u000f\u0005]\u0002\u0001\"\u0001\u0002:!Q\u0011\u0011\u000b\u0001\t\u0006\u0004%\t!a\u0015\t\u000f\u0005u\u0003\u0001\"\u0001\u0002`!I\u00111\r\u0001\u0002\u0002\u0013\u0005\u0011Q\r\u0005\n\u0003s\u0002\u0011\u0013!C\u0001\u0003wB\u0011\"!%\u0001#\u0003%\t!a\u001f\t\u0013\u0005M\u0005!%A\u0005\u0002\u0005m\u0004\"CAK\u0001E\u0005I\u0011AA>\u0011%\t9\nAI\u0001\n\u0003\tI\nC\u0005\u0002\u001e\u0002\t\n\u0011\"\u0001\u0002\u001a\"I\u0011q\u0014\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0014\u0005\n\u0003C\u0003\u0011\u0013!C\u0001\u0003GC\u0011\"a*\u0001#\u0003%\t!!+\t\u0013\u00055\u0006!!A\u0005B\u0005=\u0006\"CA[\u0001\u0005\u0005I\u0011AA\\\u0011%\tI\fAA\u0001\n\u0003\tY\fC\u0005\u0002H\u0002\t\t\u0011\"\u0011\u0002J\"I\u0011q\u001b\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u001c\u0005\n\u0003G\u0004\u0011\u0011!C!\u0003KD\u0011\"!;\u0001\u0003\u0003%\t%a;\t\u0013\u00055\b!!A\u0005B\u0005=\b\"CAy\u0001\u0005\u0005I\u0011IAz\u000f%\t9pQA\u0001\u0012\u0003\tIP\u0002\u0005C\u0007\u0006\u0005\t\u0012AA~\u0011\u001d\t9D\u000bC\u0001\u0005'A\u0011\"!<+\u0003\u0003%)%a<\t\u0013\tU!&!A\u0005\u0002\n]\u0001\"\u0003B\u0016UE\u0005I\u0011AA>\u0011%\u0011iCKI\u0001\n\u0003\tY\bC\u0005\u00030)\n\n\u0011\"\u0001\u0002|!I!\u0011\u0007\u0016\u0012\u0002\u0013\u0005\u00111\u0010\u0005\n\u0005gQ\u0013\u0013!C\u0001\u00033C\u0011B!\u000e+#\u0003%\t!!'\t\u0013\t]\"&%A\u0005\u0002\u0005e\u0005\"\u0003B\u001dUE\u0005I\u0011AAR\u0011%\u0011YDKI\u0001\n\u0003\tI\u000bC\u0005\u0003>)\n\t\u0011\"!\u0003@!I!Q\n\u0016\u0012\u0002\u0013\u0005\u00111\u0010\u0005\n\u0005\u001fR\u0013\u0013!C\u0001\u0003wB\u0011B!\u0015+#\u0003%\t!a\u001f\t\u0013\tM#&%A\u0005\u0002\u0005m\u0004\"\u0003B+UE\u0005I\u0011AAM\u0011%\u00119FKI\u0001\n\u0003\tI\nC\u0005\u0003Z)\n\n\u0011\"\u0001\u0002\u001a\"I!1\f\u0016\u0012\u0002\u0013\u0005\u00111\u0015\u0005\n\u0005;R\u0013\u0013!C\u0001\u0003SC\u0011Ba\u0018+\u0003\u0003%IA!\u0019\u0003\r=\u0013\u0018nZ5o\u0015\t!U)A\u0003ue\u0016,7O\u0003\u0002G\u000f\u0006A1-\u0019;bYf\u001cHO\u0003\u0002I\u0013\u0006\u00191/\u001d7\u000b\u0005)[\u0015!B:qCJ\\'B\u0001'N\u0003\u0019\t\u0007/Y2iK*\ta*A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001#^S\u0006C\u0001*V\u001b\u0005\u0019&\"\u0001+\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001b&AB!osJ+g\r\u0005\u0002S1&\u0011\u0011l\u0015\u0002\b!J|G-^2u!\tY6M\u0004\u0002]C:\u0011Q\fY\u0007\u0002=*\u0011qlT\u0001\u0007yI|w\u000e\u001e \n\u0003QK!AY*\u0002\u000fA\f7m[1hK&\u0011A-\u001a\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003EN\u000bA\u0001\\5oKV\t\u0001\u000eE\u0002SS.L!A[*\u0003\r=\u0003H/[8o!\t\u0011F.\u0003\u0002n'\n\u0019\u0011J\u001c;\u0002\u000b1Lg.\u001a\u0011\u0002\u001bM$\u0018M\u001d;Q_NLG/[8o\u00039\u0019H/\u0019:u!>\u001c\u0018\u000e^5p]\u0002\n!b\u001d;beRLe\u000eZ3y\u0003-\u0019H/\u0019:u\u0013:$W\r\u001f\u0011\u0002\u0013M$x\u000e]%oI\u0016D\u0018AC:u_BLe\u000eZ3yA\u000591/\u001d7UKb$X#A<\u0011\u0007IK\u0007\u0010\u0005\u0002z{:\u0011!p\u001f\t\u0003;NK!\u0001`*\u0002\rA\u0013X\rZ3g\u0013\tqxP\u0001\u0004TiJLgn\u001a\u0006\u0003yN\u000b\u0001b]9m)\u0016DH\u000fI\u0001\u000b_\nTWm\u0019;UsB,\u0017aC8cU\u0016\u001cG\u000fV=qK\u0002\n!b\u001c2kK\u000e$h*Y7f\u0003-y'M[3di:\u000bW.\u001a\u0011\u0002\u0015M$\u0018mY6Ue\u0006\u001cW-\u0006\u0002\u0002\u0010A!!+[A\t!\u0015\u0011\u00161CA\f\u0013\r\t)b\u0015\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0005\u00033\t\u0019#\u0004\u0002\u0002\u001c)!\u0011QDA\u0010\u0003\u0011a\u0017M\\4\u000b\u0005\u0005\u0005\u0012\u0001\u00026bm\u0006LA!!\n\u0002\u001c\t\t2\u000b^1dWR\u0013\u0018mY3FY\u0016lWM\u001c;\u0002\u0017M$\u0018mY6Ue\u0006\u001cW\rI\u0001\u0014af\u001c\b/\u0019:l\u000bJ\u0014xN]\"p]R,\u0007\u0010^\u000b\u0003\u0003[\u0001BAU5\u00020A)!+!\ryq&\u0019\u00111G*\u0003\rQ+\b\u000f\\33\u0003Q\u0001\u0018p\u001d9be.,%O]8s\u0007>tG/\u001a=uA\u00051A(\u001b8jiz\"B#a\u000f\u0002@\u0005\u0005\u00131IA#\u0003\u000f\nI%a\u0013\u0002N\u0005=\u0003cAA\u001f\u00015\t1\tC\u0004g'A\u0005\t\u0019\u00015\t\u000f=\u001c\u0002\u0013!a\u0001Q\"9\u0011o\u0005I\u0001\u0002\u0004A\u0007bB:\u0014!\u0003\u0005\r\u0001\u001b\u0005\bkN\u0001\n\u00111\u0001x\u0011!\t\u0019a\u0005I\u0001\u0002\u00049\b\u0002CA\u0004'A\u0005\t\u0019A<\t\u0013\u0005-1\u0003%AA\u0002\u0005=\u0001\"CA\u0015'A\u0005\t\u0019AA\u0017\u0003\u001d\u0019wN\u001c;fqR,\"!!\u0016\u0011\t\u0005]\u0013\u0011L\u0007\u0002\u0013&\u0019\u00111L%\u0003\u0019E+XM]=D_:$X\r\u001f;\u0002\u001f\u001d,G/U;fef\u001cuN\u001c;fqR,\"!!\u0019\u0011\u000bI\u000b\u0019\"!\u0016\u0002\t\r|\u0007/\u001f\u000b\u0015\u0003w\t9'!\u001b\u0002l\u00055\u0014qNA9\u0003g\n)(a\u001e\t\u000f\u00194\u0002\u0013!a\u0001Q\"9qN\u0006I\u0001\u0002\u0004A\u0007bB9\u0017!\u0003\u0005\r\u0001\u001b\u0005\bgZ\u0001\n\u00111\u0001i\u0011\u001d)h\u0003%AA\u0002]D\u0001\"a\u0001\u0017!\u0003\u0005\ra\u001e\u0005\t\u0003\u000f1\u0002\u0013!a\u0001o\"I\u00111\u0002\f\u0011\u0002\u0003\u0007\u0011q\u0002\u0005\n\u0003S1\u0002\u0013!a\u0001\u0003[\tabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002~)\u001a\u0001.a ,\u0005\u0005\u0005\u0005\u0003BAB\u0003\u001bk!!!\"\u000b\t\u0005\u001d\u0015\u0011R\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a#T\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u001f\u000b)IA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%i\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*TCAANU\r9\u0018qP\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]\nabY8qs\u0012\"WMZ1vYR$\u0003(\u0006\u0002\u0002&*\"\u0011qBA@\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIe*\"!a++\t\u00055\u0012qP\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005E\u0006\u0003BA\r\u0003gK1A`A\u000e\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005Y\u0017A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003{\u000b\u0019\rE\u0002S\u0003\u007fK1!!1T\u0005\r\te.\u001f\u0005\t\u0003\u000b\u0014\u0013\u0011!a\u0001W\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a3\u0011\r\u00055\u00171[A_\u001b\t\tyMC\u0002\u0002RN\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\t).a4\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u00037\f\t\u000fE\u0002S\u0003;L1!a8T\u0005\u001d\u0011un\u001c7fC:D\u0011\"!2%\u0003\u0003\u0005\r!!0\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003c\u000b9\u000f\u0003\u0005\u0002F\u0016\n\t\u00111\u0001l\u0003!A\u0017m\u001d5D_\u0012,G#A6\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!-\u0002\r\u0015\fX/\u00197t)\u0011\tY.!>\t\u0013\u0005\u0015\u0007&!AA\u0002\u0005u\u0016AB(sS\u001eLg\u000eE\u0002\u0002>)\u001aRAKA\u007f\u0005\u0013\u0001\u0012#a@\u0003\u0006!D\u0007\u000e[<xo\u0006=\u0011QFA\u001e\u001b\t\u0011\tAC\u0002\u0003\u0004M\u000bqA];oi&lW-\u0003\u0003\u0003\b\t\u0005!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8osA!!1\u0002B\t\u001b\t\u0011iA\u0003\u0003\u0003\u0010\u0005}\u0011AA5p\u0013\r!'Q\u0002\u000b\u0003\u0003s\fQ!\u00199qYf$B#a\u000f\u0003\u001a\tm!Q\u0004B\u0010\u0005C\u0011\u0019C!\n\u0003(\t%\u0002b\u00024.!\u0003\u0005\r\u0001\u001b\u0005\b_6\u0002\n\u00111\u0001i\u0011\u001d\tX\u0006%AA\u0002!Dqa]\u0017\u0011\u0002\u0003\u0007\u0001\u000eC\u0004v[A\u0005\t\u0019A<\t\u0011\u0005\rQ\u0006%AA\u0002]D\u0001\"a\u0002.!\u0003\u0005\ra\u001e\u0005\n\u0003\u0017i\u0003\u0013!a\u0001\u0003\u001fA\u0011\"!\u000b.!\u0003\u0005\r!!\f\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIE\nq\"\u00199qYf$C-\u001a4bk2$HEM\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$C'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00136\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u00122\u0014aD1qa2LH\u0005Z3gCVdG\u000fJ\u001c\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIa\nq\"\u00199qYf$C-\u001a4bk2$H%O\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011\tE!\u0013\u0011\tIK'1\t\t\u000f%\n\u0015\u0003\u000e\u001b5io^<\u0018qBA\u0017\u0013\r\u00119e\u0015\u0002\u0007)V\u0004H.Z\u001d\t\u0013\t-s'!AA\u0002\u0005m\u0012a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001b\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00137\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%o\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIa\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012J\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B2!\u0011\tIB!\u001a\n\t\t\u001d\u00141\u0004\u0002\u0007\u001f\nTWm\u0019;"
)
public class Origin implements Product, Serializable {
   private QueryContext context;
   private final Option line;
   private final Option startPosition;
   private final Option startIndex;
   private final Option stopIndex;
   private final Option sqlText;
   private final Option objectType;
   private final Option objectName;
   private final Option stackTrace;
   private final Option pysparkErrorContext;
   private volatile boolean bitmap$0;

   public static Option $lessinit$greater$default$9() {
      return Origin$.MODULE$.$lessinit$greater$default$9();
   }

   public static Option $lessinit$greater$default$8() {
      return Origin$.MODULE$.$lessinit$greater$default$8();
   }

   public static Option $lessinit$greater$default$7() {
      return Origin$.MODULE$.$lessinit$greater$default$7();
   }

   public static Option $lessinit$greater$default$6() {
      return Origin$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option $lessinit$greater$default$5() {
      return Origin$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return Origin$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option $lessinit$greater$default$3() {
      return Origin$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option $lessinit$greater$default$2() {
      return Origin$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option $lessinit$greater$default$1() {
      return Origin$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final Origin x$0) {
      return Origin$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$9() {
      return Origin$.MODULE$.apply$default$9();
   }

   public static Option apply$default$8() {
      return Origin$.MODULE$.apply$default$8();
   }

   public static Option apply$default$7() {
      return Origin$.MODULE$.apply$default$7();
   }

   public static Option apply$default$6() {
      return Origin$.MODULE$.apply$default$6();
   }

   public static Option apply$default$5() {
      return Origin$.MODULE$.apply$default$5();
   }

   public static Option apply$default$4() {
      return Origin$.MODULE$.apply$default$4();
   }

   public static Option apply$default$3() {
      return Origin$.MODULE$.apply$default$3();
   }

   public static Option apply$default$2() {
      return Origin$.MODULE$.apply$default$2();
   }

   public static Option apply$default$1() {
      return Origin$.MODULE$.apply$default$1();
   }

   public static Origin apply(final Option line, final Option startPosition, final Option startIndex, final Option stopIndex, final Option sqlText, final Option objectType, final Option objectName, final Option stackTrace, final Option pysparkErrorContext) {
      return Origin$.MODULE$.apply(line, startPosition, startIndex, stopIndex, sqlText, objectType, objectName, stackTrace, pysparkErrorContext);
   }

   public static Function1 tupled() {
      return Origin$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return Origin$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Option line() {
      return this.line;
   }

   public Option startPosition() {
      return this.startPosition;
   }

   public Option startIndex() {
      return this.startIndex;
   }

   public Option stopIndex() {
      return this.stopIndex;
   }

   public Option sqlText() {
      return this.sqlText;
   }

   public Option objectType() {
      return this.objectType;
   }

   public Option objectName() {
      return this.objectName;
   }

   public Option stackTrace() {
      return this.stackTrace;
   }

   public Option pysparkErrorContext() {
      return this.pysparkErrorContext;
   }

   private QueryContext context$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.context = (QueryContext)(this.stackTrace().isDefined() ? new DataFrameQueryContext(.MODULE$.SparkArrayOps(this.stackTrace().get()).toImmutableArraySeq(), this.pysparkErrorContext()) : new SQLQueryContext(this.line(), this.startPosition(), this.startIndex(), this.stopIndex(), this.sqlText(), this.objectType(), this.objectName()));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.context;
   }

   public QueryContext context() {
      return !this.bitmap$0 ? this.context$lzycompute() : this.context;
   }

   public QueryContext[] getQueryContext() {
      return (QueryContext[])scala.Option..MODULE$.option2Iterable((new Some(this.context())).filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getQueryContext$1(x0$1)))).toArray(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class));
   }

   public Origin copy(final Option line, final Option startPosition, final Option startIndex, final Option stopIndex, final Option sqlText, final Option objectType, final Option objectName, final Option stackTrace, final Option pysparkErrorContext) {
      return new Origin(line, startPosition, startIndex, stopIndex, sqlText, objectType, objectName, stackTrace, pysparkErrorContext);
   }

   public Option copy$default$1() {
      return this.line();
   }

   public Option copy$default$2() {
      return this.startPosition();
   }

   public Option copy$default$3() {
      return this.startIndex();
   }

   public Option copy$default$4() {
      return this.stopIndex();
   }

   public Option copy$default$5() {
      return this.sqlText();
   }

   public Option copy$default$6() {
      return this.objectType();
   }

   public Option copy$default$7() {
      return this.objectName();
   }

   public Option copy$default$8() {
      return this.stackTrace();
   }

   public Option copy$default$9() {
      return this.pysparkErrorContext();
   }

   public String productPrefix() {
      return "Origin";
   }

   public int productArity() {
      return 9;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.line();
         }
         case 1 -> {
            return this.startPosition();
         }
         case 2 -> {
            return this.startIndex();
         }
         case 3 -> {
            return this.stopIndex();
         }
         case 4 -> {
            return this.sqlText();
         }
         case 5 -> {
            return this.objectType();
         }
         case 6 -> {
            return this.objectName();
         }
         case 7 -> {
            return this.stackTrace();
         }
         case 8 -> {
            return this.pysparkErrorContext();
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
      return x$1 instanceof Origin;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "line";
         }
         case 1 -> {
            return "startPosition";
         }
         case 2 -> {
            return "startIndex";
         }
         case 3 -> {
            return "stopIndex";
         }
         case 4 -> {
            return "sqlText";
         }
         case 5 -> {
            return "objectType";
         }
         case 6 -> {
            return "objectName";
         }
         case 7 -> {
            return "stackTrace";
         }
         case 8 -> {
            return "pysparkErrorContext";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var22;
      if (this != x$1) {
         label111: {
            if (x$1 instanceof Origin) {
               label104: {
                  Origin var4 = (Origin)x$1;
                  Option var10000 = this.line();
                  Option var5 = var4.line();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label104;
                  }

                  var10000 = this.startPosition();
                  Option var6 = var4.startPosition();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label104;
                  }

                  var10000 = this.startIndex();
                  Option var7 = var4.startIndex();
                  if (var10000 == null) {
                     if (var7 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var7)) {
                     break label104;
                  }

                  var10000 = this.stopIndex();
                  Option var8 = var4.stopIndex();
                  if (var10000 == null) {
                     if (var8 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var8)) {
                     break label104;
                  }

                  var10000 = this.sqlText();
                  Option var9 = var4.sqlText();
                  if (var10000 == null) {
                     if (var9 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var9)) {
                     break label104;
                  }

                  var10000 = this.objectType();
                  Option var10 = var4.objectType();
                  if (var10000 == null) {
                     if (var10 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var10)) {
                     break label104;
                  }

                  var10000 = this.objectName();
                  Option var11 = var4.objectName();
                  if (var10000 == null) {
                     if (var11 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var11)) {
                     break label104;
                  }

                  var10000 = this.stackTrace();
                  Option var12 = var4.stackTrace();
                  if (var10000 == null) {
                     if (var12 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var12)) {
                     break label104;
                  }

                  var10000 = this.pysparkErrorContext();
                  Option var13 = var4.pysparkErrorContext();
                  if (var10000 == null) {
                     if (var13 != null) {
                        break label104;
                     }
                  } else if (!var10000.equals(var13)) {
                     break label104;
                  }

                  if (var4.canEqual(this)) {
                     break label111;
                  }
               }
            }

            var22 = false;
            return var22;
         }
      }

      var22 = true;
      return var22;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getQueryContext$1(final QueryContext x0$1) {
      if (x0$1 instanceof SQLQueryContext var3) {
         return var3.isValid();
      } else {
         return true;
      }
   }

   public Origin(final Option line, final Option startPosition, final Option startIndex, final Option stopIndex, final Option sqlText, final Option objectType, final Option objectName, final Option stackTrace, final Option pysparkErrorContext) {
      this.line = line;
      this.startPosition = startPosition;
      this.startIndex = startIndex;
      this.stopIndex = stopIndex;
      this.sqlText = sqlText;
      this.objectType = objectType;
      this.objectName = objectName;
      this.stackTrace = stackTrace;
      this.pysparkErrorContext = pysparkErrorContext;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
