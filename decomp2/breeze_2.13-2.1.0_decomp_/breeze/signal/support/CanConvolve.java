package breeze.signal.support;

import breeze.signal.OptMethod;
import breeze.signal.OptOverhang;
import breeze.signal.OptPadding;
import breeze.signal.OptRange;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mgaB\u0011#!\u0003\r\n!\u000b\u0005\u0006c\u00011\tAM\u0004\u0006E\nB\ta\u0019\u0004\u0006C\tB\t!\u001a\u0005\u0006Y\u000e!\t!\u001c\u0005\b]\u000e\u0011\r\u0011b\u0001p\u0011\u0019Q8\u0001)A\u0005a\"91p\u0001b\u0001\n\u0007a\bbBA\u0003\u0007\u0001\u0006I! \u0005\n\u0003\u000f\u0019!\u0019!C\u0002\u0003\u0013A\u0001\"!\u0006\u0004A\u0003%\u00111\u0002\u0005\n\u0003/\u0019!\u0019!C\u0002\u00033A\u0001\"!\n\u0004A\u0003%\u00111\u0004\u0005\n\u0003O\u0019!\u0019!C\u0002\u0003SA\u0001\"a\r\u0004A\u0003%\u00111\u0006\u0005\n\u0003k\u0019!\u0019!C\u0002\u0003oA\u0001\"!\u0010\u0004A\u0003%\u0011\u0011\b\u0005\n\u0003\u007f\u0019!\u0019!C\u0002\u0003\u0003B\u0001\"a\u0012\u0004A\u0003%\u00111\t\u0005\n\u0003\u0013\u001a!\u0019!C\u0002\u0003\u0017B\u0001\"!\u0015\u0004A\u0003%\u0011Q\n\u0004\n\u0003'\u001a\u0001\u0013aI\u0001\u0003+Ba!M\u000b\u0007\u0002\u0005e\u0003bBAC\u0007\u0011\u0005\u0011q\u0011\u0005\n\u0003O\u001b!\u0019!C\u0002\u0003SC\u0001\"!,\u0004A\u0003%\u00111\u0016\u0005\n\u0003_\u001b!\u0019!C\u0002\u0003cC\u0001\"!.\u0004A\u0003%\u00111\u0017\u0005\n\u0003o\u001b!\u0019!C\u0002\u0003sC\u0001\"!0\u0004A\u0003%\u00111\u0018\u0005\n\u0003\u007f\u001b!\u0019!C\u0002\u0003\u0003D\u0001\"!2\u0004A\u0003%\u00111\u0019\u0005\n\u0003\u000f\u001c\u0011\u0011!C\u0005\u0003\u0013\u00141bQ1o\u0007>tgo\u001c7wK*\u00111\u0005J\u0001\bgV\u0004\bo\u001c:u\u0015\t)c%\u0001\u0004tS\u001et\u0017\r\u001c\u0006\u0002O\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0003+\u0003\u001a+4C\u0001\u0001,!\tas&D\u0001.\u0015\u0005q\u0013!B:dC2\f\u0017B\u0001\u0019.\u0005\u0019\te.\u001f*fM\u0006)\u0011\r\u001d9msRA1GP\"I\u001dNCV\f\u0005\u00025k1\u0001A!\u0002\u001c\u0001\u0005\u00049$AB(viB,H/\u0005\u00029wA\u0011A&O\u0005\u0003u5\u0012qAT8uQ&tw\r\u0005\u0002-y%\u0011Q(\f\u0002\u0004\u0003:L\b\"B \u0002\u0001\u0004\u0001\u0015\u0001\u00023bi\u0006\u0004\"\u0001N!\u0005\u000b\t\u0003!\u0019A\u001c\u0003\u000b%s\u0007/\u001e;\t\u000b\u0011\u000b\u0001\u0019A#\u0002\r-,'O\\3m!\t!d\tB\u0003H\u0001\t\u0007qG\u0001\u0006LKJtW\r\u001c+za\u0016DQ!S\u0001A\u0002)\u000bQA]1oO\u0016\u0004\"a\u0013'\u000e\u0003\u0011J!!\u0014\u0013\u0003\u0011=\u0003HOU1oO\u0016DQaT\u0001A\u0002A\u000b\u0011bY8se\u0016d\u0017\r^3\u0011\u00051\n\u0016B\u0001*.\u0005\u001d\u0011un\u001c7fC:DQ\u0001V\u0001A\u0002U\u000b\u0001b\u001c<fe\"\fgn\u001a\t\u0003\u0017ZK!a\u0016\u0013\u0003\u0017=\u0003Ho\u0014<fe\"\fgn\u001a\u0005\u00063\u0006\u0001\rAW\u0001\ba\u0006$G-\u001b8h!\tY5,\u0003\u0002]I\tQq\n\u001d;QC\u0012$\u0017N\\4\t\u000by\u000b\u0001\u0019A0\u0002\r5,G\u000f[8e!\tY\u0005-\u0003\u0002bI\tIq\n\u001d;NKRDw\u000eZ\u0001\f\u0007\u0006t7i\u001c8w_24X\r\u0005\u0002e\u00075\t!eE\u0002\u0004W\u0019\u0004\"a\u001a6\u000e\u0003!T!!\u001b\u0014\u0002\tU$\u0018\u000e\\\u0005\u0003W\"\u00141cU3sS\u0006d\u0017N_1cY\u0016dunZ4j]\u001e\fa\u0001P5oSRtD#A2\u0002#\u00114H+\r#D_:4x\u000e\u001c<f?&sG/F\u0001q!\u0015!\u0007!]9r!\r\u0011Xo^\u0007\u0002g*\u0011AOJ\u0001\u0007Y&t\u0017\r\\4\n\u0005Y\u001c(a\u0003#f]N,g+Z2u_J\u0004\"\u0001\f=\n\u0005el#aA%oi\u0006\u0011BM\u001e+2\t\u000e{gN^8mm\u0016|\u0016J\u001c;!\u0003I!g\u000fV\u0019E\u0007>tgo\u001c7wK~cuN\\4\u0016\u0003u\u0004R\u0001\u001a\u0001\u007f}z\u00042A];\u0000!\ra\u0013\u0011A\u0005\u0004\u0003\u0007i#\u0001\u0002'p]\u001e\f1\u0003\u001a<Uc\u0011\u001buN\u001c<pYZ,w\fT8oO\u0002\n1\u0003\u001a<Uc\u0011\u001buN\u001c<pYZ,wL\u00127pCR,\"!a\u0003\u0011\u0011\u0011\u0004\u0011QBA\u0007\u0003\u001b\u0001BA];\u0002\u0010A\u0019A&!\u0005\n\u0007\u0005MQFA\u0003GY>\fG/\u0001\u000bemR\u000bDiQ8om>dg/Z0GY>\fG\u000fI\u0001\u0015IZ$\u0016\u0007R\"p]Z|GN^3`\t>,(\r\\3\u0016\u0005\u0005m\u0001\u0003\u00033\u0001\u0003;\ti\"!\b\u0011\tI,\u0018q\u0004\t\u0004Y\u0005\u0005\u0012bAA\u0012[\t1Ai\\;cY\u0016\fQ\u0003\u001a<Uc\u0011\u001buN\u001c<pYZ,w\fR8vE2,\u0007%A\femR[UM\u001d8fYF\"5i\u001c8w_24XmX%oiV\u0011\u00111\u0006\t\u0007I\u0002\t\u0018QF9\u0011\t\u0011\fyc^\u0005\u0004\u0003c\u0011#a\u0003$J%.+'O\\3mc\u0011\u000b\u0001\u0004\u001a<U\u0017\u0016\u0014h.\u001a72\t\u000e{gN^8mm\u0016|\u0016J\u001c;!\u0003a!g\u000fV&fe:,G.\r#D_:4x\u000e\u001c<f?2{gnZ\u000b\u0003\u0003s\u0001b\u0001\u001a\u0001\u007f\u0003wq\b\u0003\u00023\u00020}\f\u0011\u0004\u001a<U\u0017\u0016\u0014h.\u001a72\t\u000e{gN^8mm\u0016|Fj\u001c8hA\u0005IBM\u001e+LKJtW\r\\\u0019E\u0007>tgo\u001c7wK~3En\\1u+\t\t\u0019\u0005\u0005\u0005e\u0001\u00055\u0011QIA\u0007!\u0015!\u0017qFA\b\u0003i!g\u000fV&fe:,G.\r#D_:4x\u000e\u001c<f?\u001acw.\u0019;!\u0003i!g\u000fV&fe:,G.\r#D_:4x\u000e\u001c<f?\u0012{WO\u00197f+\t\ti\u0005\u0005\u0005e\u0001\u0005u\u0011qJA\u000f!\u0015!\u0017qFA\u0010\u0003m!g\u000fV&fe:,G.\r#D_:4x\u000e\u001c<f?\u0012{WO\u00197fA\t12)\u00198D_J\u0014X\r\\1uK:{wJ^3sQ\u0006tw-\u0006\u0005\u0002X\u0005\r\u0014\u0011NA/'\t)2\u0006\u0006\u0005\u0002\\\u0005}\u0013QMA6!\r!\u0014Q\f\u0003\u0006mU\u0011\ra\u000e\u0005\u0007\u007fY\u0001\r!!\u0019\u0011\u0007Q\n\u0019\u0007B\u0003C+\t\u0007q\u0007\u0003\u0004E-\u0001\u0007\u0011q\r\t\u0004i\u0005%D!B$\u0016\u0005\u00049\u0004BB%\u0017\u0001\u0004\ti\u0007\u0005\u0003\u0002p\u0005}d\u0002BA9\u0003wrA!a\u001d\u0002z5\u0011\u0011Q\u000f\u0006\u0004\u0003oB\u0013A\u0002\u001fs_>$h(C\u0001/\u0013\r\ti(L\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t\t)a!\u0003\u000bI\u000bgnZ3\u000b\u0007\u0005uT&A\fd_J\u0014X\r\\1uK2{w\u000e\u001d(p\u001fZ,'\u000f[1oOVA\u0011\u0011RAN\u0003?\u000by\t\u0006\u0005\u0002\f\u0006\u0005\u00161UAS)\u0011\ti)!%\u0011\u0007Q\ny\tB\u00037/\t\u0007q\u0007C\u0004\u0002\u0014^\u0001\u001d!!&\u0002-\r\fgnQ8se\u0016d\u0017\r^3O_>3XM\u001d5b]\u001e\u0004\u0012\"a&\u0016\u00033\u000bi*!$\u000e\u0003\r\u00012\u0001NAN\t\u0015\u0011uC1\u00018!\r!\u0014q\u0014\u0003\u0006\u000f^\u0011\ra\u000e\u0005\u0007\u007f]\u0001\r!!'\t\r\u0011;\u0002\u0019AAO\u0011\u0019Iu\u00031\u0001\u0002n\u0005!3m\u001c:sK2\fG/\u001a'p_Btun\u0014<fe\"\fgn\u001a*b]\u001e,Gk\u0018#pk\ndW-\u0006\u0002\u0002,BI\u0011qS\u000b\u0002\u001e\u0005u\u0011QD\u0001&G>\u0014(/\u001a7bi\u0016dun\u001c9O_>3XM\u001d5b]\u001e\u0014\u0016M\\4f)~#u.\u001e2mK\u0002\n1eY8se\u0016d\u0017\r^3M_>\u0004hj\\(wKJD\u0017M\\4SC:<W\rV0GY>\fG/\u0006\u0002\u00024BI\u0011qS\u000b\u0002\u000e\u00055\u0011QB\u0001%G>\u0014(/\u001a7bi\u0016dun\u001c9O_>3XM\u001d5b]\u001e\u0014\u0016M\\4f)~3En\\1uA\u0005\u00113m\u001c:sK2\fG/\u001a'p_Btun\u0014<fe\"\fgn\u001a*b]\u001e,Gk\u0018'p]\u001e,\"!a/\u0011\r\u0005]UC @\u007f\u0003\r\u001awN\u001d:fY\u0006$X\rT8pa:{wJ^3sQ\u0006twMU1oO\u0016$v\fT8oO\u0002\nqdY8se\u0016d\u0017\r^3M_>\u0004hj\\(wKJD\u0017M\\4SC:<W-\u00138u+\t\t\u0019\r\u0005\u0004\u0002\u0018V\t\u0018/]\u0001!G>\u0014(/\u001a7bi\u0016dun\u001c9O_>3XM\u001d5b]\u001e\u0014\u0016M\\4f\u0013:$\b%\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002LB!\u0011QZAl\u001b\t\tyM\u0003\u0003\u0002R\u0006M\u0017\u0001\u00027b]\u001eT!!!6\u0002\t)\fg/Y\u0005\u0005\u00033\fyM\u0001\u0004PE*,7\r\u001e"
)
public interface CanConvolve {
   static CanCorrelateNoOverhang correlateLoopNoOverhangRangeInt() {
      return CanConvolve$.MODULE$.correlateLoopNoOverhangRangeInt();
   }

   static CanCorrelateNoOverhang correlateLoopNoOverhangRangeT_Long() {
      return CanConvolve$.MODULE$.correlateLoopNoOverhangRangeT_Long();
   }

   static CanCorrelateNoOverhang correlateLoopNoOverhangRangeT_Float() {
      return CanConvolve$.MODULE$.correlateLoopNoOverhangRangeT_Float();
   }

   static CanCorrelateNoOverhang correlateLoopNoOverhangRangeT_Double() {
      return CanConvolve$.MODULE$.correlateLoopNoOverhangRangeT_Double();
   }

   static Object correlateLoopNoOverhang(final Object data, final Object kernel, final Range range, final CanCorrelateNoOverhang canCorrelateNoOverhang) {
      return CanConvolve$.MODULE$.correlateLoopNoOverhang(data, kernel, range, canCorrelateNoOverhang);
   }

   static CanConvolve dvTKernel1DConvolve_Double() {
      return CanConvolve$.MODULE$.dvTKernel1DConvolve_Double();
   }

   static CanConvolve dvTKernel1DConvolve_Float() {
      return CanConvolve$.MODULE$.dvTKernel1DConvolve_Float();
   }

   static CanConvolve dvTKernel1DConvolve_Long() {
      return CanConvolve$.MODULE$.dvTKernel1DConvolve_Long();
   }

   static CanConvolve dvTKernel1DConvolve_Int() {
      return CanConvolve$.MODULE$.dvTKernel1DConvolve_Int();
   }

   static CanConvolve dvT1DConvolve_Double() {
      return CanConvolve$.MODULE$.dvT1DConvolve_Double();
   }

   static CanConvolve dvT1DConvolve_Float() {
      return CanConvolve$.MODULE$.dvT1DConvolve_Float();
   }

   static CanConvolve dvT1DConvolve_Long() {
      return CanConvolve$.MODULE$.dvT1DConvolve_Long();
   }

   static CanConvolve dvT1DConvolve_Int() {
      return CanConvolve$.MODULE$.dvT1DConvolve_Int();
   }

   Object apply(final Object data, final Object kernel, final OptRange range, final boolean correlate, final OptOverhang overhang, final OptPadding padding, final OptMethod method);

   public interface CanCorrelateNoOverhang {
      Object apply(final Object data, final Object kernel, final Range range);
   }
}
