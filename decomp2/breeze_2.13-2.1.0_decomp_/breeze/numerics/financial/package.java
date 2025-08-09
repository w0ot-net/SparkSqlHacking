package breeze.numerics.financial;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcD$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcF$sp;
import breeze.linalg.support.CanTraverseValues$ValuesVisitor$mcI$sp;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.LazyRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tUx!B\"E\u0011\u0003Ye!B'E\u0011\u0003q\u0005\"B+\u0002\t\u00031f\u0001B,\u0002!aC\u0001\"W\u0002\u0003\u0006\u0004%\tA\u0017\u0005\t=\u000e\u0011\t\u0011)A\u00057\")Qk\u0001C\u0001?\u001e9\u0011qJ\u0001\t\u0002\u0006\u0015caBA \u0003!\u0005\u0015\u0011\t\u0005\u0007+\"!\t!a\u0011\t\u000fYD\u0011\u0011!C!o\"A\u0011\u0011\u0001\u0005\u0002\u0002\u0013\u0005!\fC\u0005\u0002\u0004!\t\t\u0011\"\u0001\u0002H!I\u0011\u0011\u0003\u0005\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003CA\u0011\u0011!C\u0001\u0003\u0017B\u0011\"!\f\t\u0003\u0003%\t%a\f\t\u0013\u0005E\u0002\"!A\u0005B\u0005M\u0002\"CA\u001b\u0011\u0005\u0005I\u0011BA\u001c\u000f\u0019\t\t&\u0001EAk\u001a)A-\u0001EAK\")Qk\u0005C\u0001i\"9aoEA\u0001\n\u0003:\b\u0002CA\u0001'\u0005\u0005I\u0011\u0001.\t\u0013\u0005\r1#!A\u0005\u0002\u0005\u0015\u0001\"CA\t'\u0005\u0005I\u0011IA\n\u0011%\t\tcEA\u0001\n\u0003\t\u0019\u0003C\u0005\u0002.M\t\t\u0011\"\u0011\u00020!I\u0011\u0011G\n\u0002\u0002\u0013\u0005\u00131\u0007\u0005\n\u0003k\u0019\u0012\u0011!C\u0005\u0003oAq!a\u0015\u0002\t\u0003\t)\u0006C\u0005\u0002r\u0005\t\n\u0011\"\u0001\u0002t!9\u00111N\u0001\u0005\u0002\u0005%\u0005\"CAK\u0003E\u0005I\u0011AA:\u000f\u001d\t9*\u0001E\u0001\u000333q!a'\u0002\u0011\u0003\ti\n\u0003\u0004VE\u0011\u0005\u00111\u0016\u0005\b\u0003[\u0013C1AAX\u0011\u001d\tyN\tC\u0002\u0003CDq!!>#\t\u0007\t9\u0010C\u0004\u0002h\u0005!\tA!\u0002\t\u0013\tE\u0011!%A\u0005\u0002\tM\u0001\"\u0003B\f\u0003E\u0005I\u0011AA:\u0011\u001d\u0011I\"\u0001C\u0001\u00057A\u0011B!\u000e\u0002#\u0003%\tAa\u0005\t\u0013\t]\u0012!%A\u0005\u0002\u0005M\u0004b\u0002B\u001d\u0003\u0011\u0005!1\b\u0005\n\u0005\u000f\n\u0011\u0013!C\u0001\u0005'A\u0011B!\u0013\u0002#\u0003%\t!a\u001d\t\u000f\t-\u0013\u0001\"\u0001\u0003N!I!\u0011L\u0001\u0012\u0002\u0013\u0005!1\u0003\u0005\n\u00057\n\u0011\u0013!C\u0001\u0003gBqA!\u0018\u0002\t\u0003\u0011y\u0006C\u0005\u0003l\u0005\t\n\u0011\"\u0001\u0003\u0014!I!QN\u0001\u0012\u0002\u0013\u0005\u00111\u000f\u0005\b\u0005_\nA\u0011\u0002B9\u0011\u001d\u0011))\u0001C\u0001\u0005\u000fCqAa%\u0002\t\u0003\u0011)\nC\u0005\u0003$\u0006\t\n\u0011\"\u0001\u0003\u0014!9!QU\u0001\u0005\u0002\t\u001d\u0006\"\u0003B]\u0003E\u0005I\u0011\u0001B\n\u0011%\u0011Y,AI\u0001\n\u0003\t\u0019\bC\u0004\u0003>\u0006!\tAa0\t\u0013\te\u0017!%A\u0005\u0002\u0005M\u0004\"\u0003Bn\u0003E\u0005I\u0011\u0001B\n\u0011%\u0011i.AI\u0001\n\u0003\u0011\u0019\u0002C\u0005\u0003`\u0006\t\n\u0011\"\u0001\u0003b\"9!Q]\u0001\u0005\n\t\u001d\u0018a\u00029bG.\fw-\u001a\u0006\u0003\u000b\u001a\u000b\u0011BZ5oC:\u001c\u0017.\u00197\u000b\u0005\u001dC\u0015\u0001\u00038v[\u0016\u0014\u0018nY:\u000b\u0003%\u000baA\u0019:fKj,7\u0001\u0001\t\u0003\u0019\u0006i\u0011\u0001\u0012\u0002\ba\u0006\u001c7.Y4f'\t\tq\n\u0005\u0002Q'6\t\u0011KC\u0001S\u0003\u0015\u00198-\u00197b\u0013\t!\u0016K\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u00131\u0002U1z[\u0016tG\u000fV5nKN\u00111aT\u0001\u0002iV\t1\f\u0005\u0002Q9&\u0011Q,\u0015\u0002\u0004\u0013:$\u0018A\u0001;!)\t\u0001'\r\u0005\u0002b\u00075\t\u0011\u0001C\u0003Z\r\u0001\u00071,K\u0002\u0004'!\u00111!\u00128e'\u0011\u0019\u0002MZ5\u0011\u0005A;\u0017B\u00015R\u0005\u001d\u0001&o\u001c3vGR\u0004\"A[9\u000f\u0005-\u0004hB\u00017p\u001b\u0005i'B\u00018K\u0003\u0019a$o\\8u}%\t!+\u0003\u0002D#&\u0011!o\u001d\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0007F#\u0012!\u001e\t\u0003CN\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001=\u0011\u0005etX\"\u0001>\u000b\u0005md\u0018\u0001\u00027b]\u001eT\u0011!`\u0001\u0005U\u00064\u0018-\u0003\u0002\u0000u\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002\b\u00055\u0001c\u0001)\u0002\n%\u0019\u00111B)\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002\u0010]\t\t\u00111\u0001\\\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u0003\t\u0007\u0003/\ti\"a\u0002\u000e\u0005\u0005e!bAA\u000e#\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005}\u0011\u0011\u0004\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002&\u0005-\u0002c\u0001)\u0002(%\u0019\u0011\u0011F)\u0003\u000f\t{w\u000e\\3b]\"I\u0011qB\r\u0002\u0002\u0003\u0007\u0011qA\u0001\tQ\u0006\u001c\bnQ8eKR\t1,\u0001\u0005u_N#(/\u001b8h)\u0005A\u0018\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u001d!\rI\u00181H\u0005\u0004\u0003{Q(AB(cU\u0016\u001cGOA\u0003Ti\u0006\u0014Ho\u0005\u0003\tA\u001aLGCAA#!\t\t\u0007\u0002\u0006\u0003\u0002\b\u0005%\u0003\u0002CA\b\u0019\u0005\u0005\t\u0019A.\u0015\t\u0005\u0015\u0012Q\n\u0005\n\u0003\u001fq\u0011\u0011!a\u0001\u0003\u000f\tQa\u0015;beR\f1!\u00128e\u0003-1W\u000f^;sKZ\u000bG.^3\u0015\u0019\u0005]\u0013QLA1\u0003K\nI'!\u001c\u0011\u0007A\u000bI&C\u0002\u0002\\E\u0013a\u0001R8vE2,\u0007bBA0;\u0001\u0007\u0011qK\u0001\u0005e\u0006$X\r\u0003\u0004\u0002du\u0001\raW\u0001\u000b]Vl\u0007+\u001a:j_\u0012\u001c\bbBA4;\u0001\u0007\u0011qK\u0001\ba\u0006LX.\u001a8u\u0011\u001d\tY'\ba\u0001\u0003/\nA\u0002\u001d:fg\u0016tGOV1mk\u0016D\u0001\"a\u001c\u001e!\u0003\u0005\r\u0001Y\u0001\u0005o\",g.A\u000bgkR,(/\u001a,bYV,G\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005U$f\u00011\u0002x-\u0012\u0011\u0011\u0010\t\u0005\u0003w\n))\u0004\u0002\u0002~)!\u0011qPAA\u0003%)hn\u00195fG.,GMC\u0002\u0002\u0004F\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t9)! \u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r\u0006\u0007\u0002X\u0005-\u0015QRAH\u0003#\u000b\u0019\nC\u0004\u0002`}\u0001\r!a\u0016\t\r\u0005\rt\u00041\u0001\\\u0011\u001d\t9g\ba\u0001\u0003/Bq!a\u0015 \u0001\u0004\t9\u0006\u0003\u0005\u0002p}\u0001\n\u00111\u0001a\u0003Y\u0001(/Z:f]R4\u0016\r\\;fI\u0011,g-Y;mi\u0012*\u0014a\u00048fiB\u0013Xm]3oiZ\u000bG.^3\u0011\u0005\u0005\u0014#a\u00048fiB\u0013Xm]3oiZ\u000bG.^3\u0014\t\tz\u0015q\u0014\t\u0005\u0003C\u000b9+\u0004\u0002\u0002$*\u0019\u0011Q\u0015%\u0002\u000f\u001d,g.\u001a:jG&!\u0011\u0011VAR\u0005\u0015)f)\u001e8d)\t\tI*A\u0007sK\u0012,8-Z0E_V\u0014G.Z\u000b\u0005\u0003c\u000by\f\u0006\u0003\u00024\u0006-\u0007CCA[\u0003o\u000b9&a/\u0002X5\t!%\u0003\u0003\u0002:\u0006\u001d&!B%na2\u0014\u0004\u0003BA_\u0003\u007fc\u0001\u0001B\u0004\u0002B\u0012\u0012\r!a1\u0003\u0003Q\u000bB!!2\u0002\bA\u0019\u0001+a2\n\u0007\u0005%\u0017KA\u0004O_RD\u0017N\\4\t\u000f\u00055G\u0005q\u0001\u0002P\u0006!\u0011\u000e^3s!!\t\t.a7\u0002<\u0006]SBAAj\u0015\u0011\t).a6\u0002\u000fM,\b\u000f]8si*\u0019\u0011\u0011\u001c%\u0002\r1Lg.\u00197h\u0013\u0011\ti.a5\u0003#\r\u000bg\u000e\u0016:bm\u0016\u00148/\u001a,bYV,7/\u0001\u0007sK\u0012,8-Z0GY>\fG/\u0006\u0003\u0002d\u0006%H\u0003BAs\u0003W\u0004\"\"!.\u00028\u0006]\u0013q]A,!\u0011\ti,!;\u0005\u000f\u0005\u0005WE1\u0001\u0002D\"9\u0011QZ\u0013A\u0004\u00055\b\u0003CAi\u00037\f9/a<\u0011\u0007A\u000b\t0C\u0002\u0002tF\u0013QA\u00127pCR\f!B]3ek\u000e,w,\u00138u+\u0011\tI0a@\u0015\t\u0005m(\u0011\u0001\t\u000b\u0003k\u000b9,a\u0016\u0002~\u0006]\u0003\u0003BA_\u0003\u007f$q!!1'\u0005\u0004\t\u0019\rC\u0004\u0002N\u001a\u0002\u001dAa\u0001\u0011\u000f\u0005E\u00171\\A\u007f7Ra\u0011q\u000bB\u0004\u0005\u0013\u0011YA!\u0004\u0003\u0010!9\u0011qL\u0014A\u0002\u0005]\u0003BBA2O\u0001\u00071\fC\u0004\u0002l\u001d\u0002\r!a\u0016\t\u0013\u0005Ms\u0005%AA\u0002\u0005]\u0003\u0002CA8OA\u0005\t\u0019\u00011\u0002#A\f\u00170\\3oi\u0012\"WMZ1vYR$C'\u0006\u0002\u0003\u0016)\"\u0011qKA<\u0003E\u0001\u0018-_7f]R$C-\u001a4bk2$H%N\u0001\u0012aJLgnY5qC2Le\u000e^3sKN$H\u0003\u0004B\u000f\u0005W\u0011iCa\f\u00032\tM\u0002#\u0003)\u0003 \t\r\"1\u0005B\u0012\u0013\r\u0011\t#\u0015\u0002\u0007)V\u0004H.Z\u001a\u0011\r\t\u0015\"qEA,\u001b\t\t9.\u0003\u0003\u0003*\u0005]'a\u0003#f]N,g+Z2u_JDq!a\u0018+\u0001\u0004\t9\u0006\u0003\u0004\u0002d)\u0002\ra\u0017\u0005\b\u0003WR\u0003\u0019AA,\u0011%\t\u0019F\u000bI\u0001\u0002\u0004\t9\u0006\u0003\u0005\u0002p)\u0002\n\u00111\u0001a\u0003m\u0001(/\u001b8dSB\fG.\u00138uKJ,7\u000f\u001e\u0013eK\u001a\fW\u000f\u001c;%i\u0005Y\u0002O]5oG&\u0004\u0018\r\\%oi\u0016\u0014Xm\u001d;%I\u00164\u0017-\u001e7uIU\n\u0001#\u001b8uKJ,7\u000f\u001e)bs6,g\u000e^:\u0015\u0019\t\r\"Q\bB \u0005\u0003\u0012\u0019E!\u0012\t\u000f\u0005}S\u00061\u0001\u0002X!1\u00111M\u0017A\u0002mCq!a\u001b.\u0001\u0004\t9\u0006C\u0005\u0002T5\u0002\n\u00111\u0001\u0002X!A\u0011qN\u0017\u0011\u0002\u0003\u0007\u0001-\u0001\u000ej]R,'/Z:u!\u0006LX.\u001a8ug\u0012\"WMZ1vYR$C'\u0001\u000ej]R,'/Z:u!\u0006LX.\u001a8ug\u0012\"WMZ1vYR$S'A\tqe&t7-\u001b9bYB\u000b\u00170\\3oiN$BBa\t\u0003P\tE#1\u000bB+\u0005/Bq!a\u00181\u0001\u0004\t9\u0006\u0003\u0004\u0002dA\u0002\ra\u0017\u0005\b\u0003W\u0002\u0004\u0019AA,\u0011%\t\u0019\u0006\rI\u0001\u0002\u0004\t9\u0006\u0003\u0005\u0002pA\u0002\n\u00111\u0001a\u0003m\u0001(/\u001b8dSB\fG\u000eU1z[\u0016tGo\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0005Y\u0002O]5oG&\u0004\u0018\r\u001c)bs6,g\u000e^:%I\u00164\u0017-\u001e7uIU\n!\u0003\u001d:j]\u000eL\u0007/\u00197SK6\f\u0017N\\5oORa!1\u0005B1\u0005G\u0012)Ga\u001a\u0003j!9\u0011qL\u001aA\u0002\u0005]\u0003BBA2g\u0001\u00071\fC\u0004\u0002lM\u0002\r!a\u0016\t\u0013\u0005M3\u0007%AA\u0002\u0005]\u0003\u0002CA8gA\u0005\t\u0019\u00011\u00029A\u0014\u0018N\\2ja\u0006d'+Z7bS:Lgn\u001a\u0013eK\u001a\fW\u000f\u001c;%i\u0005a\u0002O]5oG&\u0004\u0018\r\u001c*f[\u0006Lg.\u001b8hI\u0011,g-Y;mi\u0012*\u0014!\u0002:p_R\u001cH\u0003\u0002B:\u0005\u0003\u0003bA!\n\u0003(\tU\u0004\u0003\u0002B<\u0005{j!A!\u001f\u000b\u0007\tm\u0004*\u0001\u0003nCRD\u0017\u0002\u0002B@\u0005s\u0012qaQ8na2,\u0007\u0010C\u0004\u0003\u0004Z\u0002\rAa\t\u0002\r\r|WM\u001a4t\u0003EIg\u000e^3sC2\u0014\u0016\r^3SKR,(O\u001c\u000b\u0005\u0005\u0013\u0013y\tE\u0003Q\u0005\u0017\u000b9&C\u0002\u0003\u000eF\u0013aa\u00149uS>t\u0007b\u0002BIo\u0001\u0007!1E\u0001\tG\u0006\u001c\bN\u001a7po\u0006QRn\u001c3jM&,G-\u00138uKJt\u0017\r\u001c*bi\u0016\u0014V\r^;s]RA\u0011q\u000bBL\u00057\u0013y\nC\u0004\u0003\u001ab\u0002\rAa\t\u0002\rY\fG.^3t\u0011\u001d\u0011i\n\u000fa\u0001\u0003/\n1BZ5oC:\u001cWMU1uK\"I!\u0011\u0015\u001d\u0011\u0002\u0003\u0007\u0011qK\u0001\re\u0016LgN^3tiJ\u000bG/Z\u0001%[>$\u0017NZ5fI&sG/\u001a:oC2\u0014\u0016\r^3SKR,(O\u001c\u0013eK\u001a\fW\u000f\u001c;%g\u00051b.^7cKJ\u0004VM]5pI&\u001c\u0007+Y=nK:$8\u000f\u0006\u0007\u0002X\t%&1\u0016BX\u0005g\u00139\fC\u0004\u0002`i\u0002\r!a\u0016\t\u000f\t5&\b1\u0001\u0002X\u0005\u0019\u0001/\u001c;\t\u000f\tE&\b1\u0001\u0002X\u0005\u0011\u0001O\u001e\u0005\n\u0005kS\u0004\u0013!a\u0001\u0003/\n!A\u001a<\t\u0011\u0005=$\b%AA\u0002\u0001\f\u0001E\\;nE\u0016\u0014\b+\u001a:j_\u0012L7\rU1z[\u0016tGo\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0005\u0001c.^7cKJ\u0004VM]5pI&\u001c\u0007+Y=nK:$8\u000f\n3fM\u0006,H\u000e\u001e\u00136\u0003Q\u0011\u0018\r^3QKJLw\u000eZ5d!\u0006LX.\u001a8ugR\u0011\"\u0011\u0012Ba\u0005\u000b\u00149M!3\u0003L\n5'\u0011\u001bBk\u0011\u001d\u0011\u0019-\u0010a\u0001\u0003/\nAA\u001c9fe\"9!QV\u001fA\u0002\u0005]\u0003b\u0002BY{\u0001\u0007\u0011q\u000b\u0005\b\u0005kk\u0004\u0019AA,\u0011!\ty'\u0010I\u0001\u0002\u0004\u0001\u0007\"\u0003Bh{A\u0005\t\u0019AA,\u0003\u00159W/Z:t\u0011%\u0011\u0019.\u0010I\u0001\u0002\u0004\t9&A\u0002u_2D\u0001Ba6>!\u0003\u0005\raW\u0001\b[\u0006D\u0018\u000e^3s\u0003y\u0011\u0018\r^3QKJLw\u000eZ5d!\u0006LX.\u001a8ug\u0012\"WMZ1vYR$S'\u0001\u0010sCR,\u0007+\u001a:j_\u0012L7\rU1z[\u0016tGo\u001d\u0013eK\u001a\fW\u000f\u001c;%m\u0005q\"/\u0019;f!\u0016\u0014\u0018n\u001c3jGB\u000b\u00170\\3oiN$C-\u001a4bk2$HeN\u0001\u001fe\u0006$X\rU3sS>$\u0017n\u0019)bs6,g\u000e^:%I\u00164\u0017-\u001e7uIa*\"Aa9+\u0007m\u000b9(\u0001\tb]:,\u0018\u000e^=G\t&4xI]1eMRq\u0011q\u000bBu\u0005W\u0014iOa<\u0003r\nM\bb\u0002Bb\u0005\u0002\u0007\u0011q\u000b\u0005\b\u0005[\u0013\u0005\u0019AA,\u0011\u001d\u0011\tL\u0011a\u0001\u0003/BqA!.C\u0001\u0004\t9\u0006\u0003\u0004\u0002p\t\u0003\r\u0001\u0019\u0005\b\u0003?\u0012\u0005\u0019AA,\u0001"
)
public final class package {
   public static int ratePeriodicPayments$default$8() {
      return package$.MODULE$.ratePeriodicPayments$default$8();
   }

   public static double ratePeriodicPayments$default$7() {
      return package$.MODULE$.ratePeriodicPayments$default$7();
   }

   public static double ratePeriodicPayments$default$6() {
      return package$.MODULE$.ratePeriodicPayments$default$6();
   }

   public static PaymentTime ratePeriodicPayments$default$5() {
      return package$.MODULE$.ratePeriodicPayments$default$5();
   }

   public static Option ratePeriodicPayments(final double nper, final double pmt, final double pv, final double fv, final PaymentTime when, final double guess, final double tol, final int maxiter) {
      return package$.MODULE$.ratePeriodicPayments(nper, pmt, pv, fv, when, guess, tol, maxiter);
   }

   public static PaymentTime numberPeriodicPayments$default$5() {
      return package$.MODULE$.numberPeriodicPayments$default$5();
   }

   public static double numberPeriodicPayments$default$4() {
      return package$.MODULE$.numberPeriodicPayments$default$4();
   }

   public static double numberPeriodicPayments(final double rate, final double pmt, final double pv, final double fv, final PaymentTime when) {
      return package$.MODULE$.numberPeriodicPayments(rate, pmt, pv, fv, when);
   }

   public static double modifiedInternalRateReturn$default$3() {
      return package$.MODULE$.modifiedInternalRateReturn$default$3();
   }

   public static double modifiedInternalRateReturn(final DenseVector values, final double financeRate, final double reinvestRate) {
      return package$.MODULE$.modifiedInternalRateReturn(values, financeRate, reinvestRate);
   }

   public static Option interalRateReturn(final DenseVector cashflow) {
      return package$.MODULE$.interalRateReturn(cashflow);
   }

   public static PaymentTime principalRemaining$default$5() {
      return package$.MODULE$.principalRemaining$default$5();
   }

   public static double principalRemaining$default$4() {
      return package$.MODULE$.principalRemaining$default$4();
   }

   public static DenseVector principalRemaining(final double rate, final int numPeriods, final double presentValue, final double futureValue, final PaymentTime when) {
      return package$.MODULE$.principalRemaining(rate, numPeriods, presentValue, futureValue, when);
   }

   public static PaymentTime principalPayments$default$5() {
      return package$.MODULE$.principalPayments$default$5();
   }

   public static double principalPayments$default$4() {
      return package$.MODULE$.principalPayments$default$4();
   }

   public static DenseVector principalPayments(final double rate, final int numPeriods, final double presentValue, final double futureValue, final PaymentTime when) {
      return package$.MODULE$.principalPayments(rate, numPeriods, presentValue, futureValue, when);
   }

   public static PaymentTime interestPayments$default$5() {
      return package$.MODULE$.interestPayments$default$5();
   }

   public static double interestPayments$default$4() {
      return package$.MODULE$.interestPayments$default$4();
   }

   public static DenseVector interestPayments(final double rate, final int numPeriods, final double presentValue, final double futureValue, final PaymentTime when) {
      return package$.MODULE$.interestPayments(rate, numPeriods, presentValue, futureValue, when);
   }

   public static PaymentTime principalInterest$default$5() {
      return package$.MODULE$.principalInterest$default$5();
   }

   public static double principalInterest$default$4() {
      return package$.MODULE$.principalInterest$default$4();
   }

   public static Tuple3 principalInterest(final double rate, final int numPeriods, final double presentValue, final double futureValue, final PaymentTime when) {
      return package$.MODULE$.principalInterest(rate, numPeriods, presentValue, futureValue, when);
   }

   public static PaymentTime payment$default$5() {
      return package$.MODULE$.payment$default$5();
   }

   public static double payment$default$4() {
      return package$.MODULE$.payment$default$4();
   }

   public static double payment(final double rate, final int numPeriods, final double presentValue, final double futureValue, final PaymentTime when) {
      return package$.MODULE$.payment(rate, numPeriods, presentValue, futureValue, when);
   }

   public static PaymentTime presentValue$default$5() {
      return package$.MODULE$.presentValue$default$5();
   }

   public static double presentValue(final double rate, final int numPeriods, final double payment, final double futureValue, final PaymentTime when) {
      return package$.MODULE$.presentValue(rate, numPeriods, payment, futureValue, when);
   }

   public static PaymentTime futureValue$default$5() {
      return package$.MODULE$.futureValue$default$5();
   }

   public static double futureValue(final double rate, final int numPeriods, final double payment, final double presentValue, final PaymentTime when) {
      return package$.MODULE$.futureValue(rate, numPeriods, payment, presentValue, when);
   }

   public static class PaymentTime {
      private final int t;

      public int t() {
         return this.t;
      }

      public PaymentTime(final int t) {
         this.t = t;
      }
   }

   public static class Start$ extends PaymentTime implements Product, Serializable {
      public static final Start$ MODULE$ = new Start$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Start";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Start$;
      }

      public int hashCode() {
         return 80204866;
      }

      public String toString() {
         return "Start";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Start$.class);
      }

      public Start$() {
         super(1);
      }
   }

   public static class End$ extends PaymentTime implements Product, Serializable {
      public static final End$ MODULE$ = new End$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "End";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof End$;
      }

      public int hashCode() {
         return 69819;
      }

      public String toString() {
         return "End";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(End$.class);
      }

      public End$() {
         super(0);
      }
   }

   public static class netPresentValue$ implements UFunc {
      public static final netPresentValue$ MODULE$ = new netPresentValue$();

      static {
         UFunc.$init$(MODULE$);
      }

      public final Object apply(final Object v, final UFunc.UImpl impl) {
         return UFunc.apply$(this, v, impl);
      }

      public final double apply$mDDc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDDc$sp$(this, v, impl);
      }

      public final float apply$mDFc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDFc$sp$(this, v, impl);
      }

      public final int apply$mDIc$sp(final double v, final UFunc.UImpl impl) {
         return UFunc.apply$mDIc$sp$(this, v, impl);
      }

      public final double apply$mFDc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFDc$sp$(this, v, impl);
      }

      public final float apply$mFFc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFFc$sp$(this, v, impl);
      }

      public final int apply$mFIc$sp(final float v, final UFunc.UImpl impl) {
         return UFunc.apply$mFIc$sp$(this, v, impl);
      }

      public final double apply$mIDc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIDc$sp$(this, v, impl);
      }

      public final float apply$mIFc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIFc$sp$(this, v, impl);
      }

      public final int apply$mIIc$sp(final int v, final UFunc.UImpl impl) {
         return UFunc.apply$mIIc$sp$(this, v, impl);
      }

      public final Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$(this, v1, v2, impl);
      }

      public final double apply$mDDDc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDDFc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDDIc$sp(final double v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDFDc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDFFc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDFIc$sp(final double v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mDIDc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mDIFc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mDIIc$sp(final double v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFDDc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFDFc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFDIc$sp(final float v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFFDc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFFFc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFFIc$sp(final float v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mFIDc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mFIFc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mFIIc$sp(final float v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIDDc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIDFc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIDIc$sp(final int v1, final double v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIFDc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIFFc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIFIc$sp(final int v1, final float v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, impl);
      }

      public final double apply$mIIDc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, impl);
      }

      public final float apply$mIIFc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, impl);
      }

      public final int apply$mIIIc$sp(final int v1, final int v2, final UFunc.UImpl2 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$(this, v1, v2, v3, impl);
      }

      public final double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mDIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mFIIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIDIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIFIc$sp$(this, v1, v2, v3, impl);
      }

      public final double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIDc$sp$(this, v1, v2, v3, impl);
      }

      public final float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIFc$sp$(this, v1, v2, v3, impl);
      }

      public final int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UFunc.UImpl3 impl) {
         return UFunc.apply$mIIIc$sp$(this, v1, v2, v3, impl);
      }

      public final Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
         return UFunc.apply$(this, v1, v2, v3, v4, impl);
      }

      public final Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
         return UFunc.inPlace$(this, v, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
         return UFunc.inPlace$(this, v, v2, impl);
      }

      public final Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
         return UFunc.inPlace$(this, v, v2, v3, impl);
      }

      public final Object withSink(final Object s) {
         return UFunc.withSink$(this, s);
      }

      public UFunc.UImpl2 reduce_Double(final CanTraverseValues iter) {
         return new UFunc.UImpl2(iter) {
            private final CanTraverseValues iter$1;

            public double apply$mcDDD$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
            }

            public float apply$mcDDF$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
            }

            public int apply$mcDDI$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
            }

            public double apply$mcDFD$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
            }

            public float apply$mcDFF$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
            }

            public int apply$mcDFI$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
            }

            public double apply$mcDID$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
            }

            public float apply$mcDIF$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
            }

            public int apply$mcDII$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
            }

            public double apply$mcFDD$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
            }

            public float apply$mcFDF$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
            }

            public int apply$mcFDI$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
            }

            public double apply$mcFFD$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
            }

            public float apply$mcFFF$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
            }

            public int apply$mcFFI$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
            }

            public double apply$mcFID$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
            }

            public float apply$mcFIF$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
            }

            public int apply$mcFII$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
            }

            public double apply$mcIDD$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
            }

            public float apply$mcIDF$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
            }

            public int apply$mcIDI$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
            }

            public double apply$mcIFD$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
            }

            public float apply$mcIFF$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
            }

            public int apply$mcIFI$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
            }

            public double apply$mcIID$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
            }

            public float apply$mcIIF$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
            }

            public int apply$mcIII$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
            }

            public double apply(final double rate, final Object revenueStream) {
               LazyRef visit$module = new LazyRef();
               this.iter$1.traverse(revenueStream, this.visit$2(visit$module, rate));
               return this.visit$2(visit$module, rate).sum();
            }

            // $FF: synthetic method
            private static final visit$1$ visit$lzycompute$1(final LazyRef visit$module$1, final double rate$1) {
               synchronized(visit$module$1){}

               visit$1$ var4;
               try {
                  class visit$1$ implements CanTraverseValues$ValuesVisitor$mcD$sp {
                     private final double decayConst;
                     private double decayUntilNow;
                     private double sum;

                     public void visitArray(final double[] arr) {
                        CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$(this, arr);
                     }

                     public void visitArray$mcD$sp(final double[] arr) {
                        CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$mcD$sp$(this, arr);
                     }

                     public void visitArray(final double[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcD$sp.visitArray$mcD$sp$(this, arr, offset, length, stride);
                     }

                     public void visit$mcZ$sp(final boolean a) {
                        CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
                     }

                     public void visit$mcB$sp(final byte a) {
                        CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
                     }

                     public void visit$mcC$sp(final char a) {
                        CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
                     }

                     public void visit$mcF$sp(final float a) {
                        CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
                     }

                     public void visit$mcI$sp(final int a) {
                        CanTraverseValues.ValuesVisitor.visit$mcI$sp$(this, a);
                     }

                     public void visit$mcJ$sp(final long a) {
                        CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
                     }

                     public void visit$mcS$sp(final short a) {
                        CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
                     }

                     public void visit$mcV$sp(final BoxedUnit a) {
                        CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
                     }

                     public void visitArray$mcZ$sp(final boolean[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
                     }

                     public void visitArray$mcB$sp(final byte[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
                     }

                     public void visitArray$mcC$sp(final char[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
                     }

                     public void visitArray$mcF$sp(final float[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
                     }

                     public void visitArray$mcI$sp(final int[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr);
                     }

                     public void visitArray$mcJ$sp(final long[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
                     }

                     public void visitArray$mcS$sp(final short[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
                     }

                     public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
                     }

                     public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
                     }

                     public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcF$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcI$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
                     }

                     public final double decayConst() {
                        return this.decayConst;
                     }

                     public double decayUntilNow() {
                        return this.decayUntilNow;
                     }

                     public void decayUntilNow_$eq(final double x$1) {
                        this.decayUntilNow = x$1;
                     }

                     public double sum() {
                        return this.sum;
                     }

                     public void sum_$eq(final double x$1) {
                        this.sum = x$1;
                     }

                     public void visit(final double a) {
                        this.visit$mcD$sp(a);
                     }

                     public void zeros(final int numZero, final double zeroValue) {
                        this.zeros$mcD$sp(numZero, zeroValue);
                     }

                     public void visit$mcD$sp(final double a) {
                        this.sum_$eq(this.sum() + this.decayUntilNow() * a);
                        this.decayUntilNow_$eq(this.decayUntilNow() * this.decayConst());
                     }

                     public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                     }

                     public visit$1$(final double rate$1) {
                        CanTraverseValues.ValuesVisitor.$init$(this);
                        this.decayConst = (double)1.0F / ((double)1.0F + rate$1);
                        this.decayUntilNow = (double)1.0F;
                        this.sum = (double)0.0F;
                     }
                  }

                  var4 = visit$module$1.initialized() ? (visit$1$)visit$module$1.value() : (visit$1$)visit$module$1.initialize(new visit$1$(rate$1));
               } catch (Throwable var6) {
                  throw var6;
               }

               return var4;
            }

            private final visit$1$ visit$2(final LazyRef visit$module$1, final double rate$1) {
               return visit$module$1.initialized() ? (visit$1$)visit$module$1.value() : visit$lzycompute$1(visit$module$1, rate$1);
            }

            public {
               this.iter$1 = iter$1;
            }
         };
      }

      public UFunc.UImpl2 reduce_Float(final CanTraverseValues iter) {
         return new UFunc.UImpl2(iter) {
            private final CanTraverseValues iter$2;

            public double apply$mcDDD$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
            }

            public float apply$mcDDF$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
            }

            public int apply$mcDDI$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
            }

            public double apply$mcDFD$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
            }

            public float apply$mcDFF$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
            }

            public int apply$mcDFI$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
            }

            public double apply$mcDID$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
            }

            public float apply$mcDIF$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
            }

            public int apply$mcDII$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
            }

            public double apply$mcFDD$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
            }

            public float apply$mcFDF$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
            }

            public int apply$mcFDI$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
            }

            public double apply$mcFFD$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
            }

            public float apply$mcFFF$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
            }

            public int apply$mcFFI$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
            }

            public double apply$mcFID$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
            }

            public float apply$mcFIF$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
            }

            public int apply$mcFII$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
            }

            public double apply$mcIDD$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
            }

            public float apply$mcIDF$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
            }

            public int apply$mcIDI$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
            }

            public double apply$mcIFD$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
            }

            public float apply$mcIFF$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
            }

            public int apply$mcIFI$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
            }

            public double apply$mcIID$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
            }

            public float apply$mcIIF$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
            }

            public int apply$mcIII$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
            }

            public double apply(final double rate, final Object revenueStream) {
               LazyRef visit$module = new LazyRef();
               this.iter$2.traverse(revenueStream, this.visit$4(visit$module, rate));
               return this.visit$4(visit$module, rate).sum();
            }

            // $FF: synthetic method
            private static final visit$3$ visit$lzycompute$2(final LazyRef visit$module$2, final double rate$2) {
               synchronized(visit$module$2){}

               visit$3$ var4;
               try {
                  class visit$3$ implements CanTraverseValues$ValuesVisitor$mcF$sp {
                     private final double decayConst;
                     private double decayUntilNow;
                     private double sum;

                     public void visitArray(final float[] arr) {
                        CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$(this, arr);
                     }

                     public void visitArray$mcF$sp(final float[] arr) {
                        CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$mcF$sp$(this, arr);
                     }

                     public void visitArray(final float[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcF$sp.visitArray$mcF$sp$(this, arr, offset, length, stride);
                     }

                     public void visit$mcZ$sp(final boolean a) {
                        CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
                     }

                     public void visit$mcB$sp(final byte a) {
                        CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
                     }

                     public void visit$mcC$sp(final char a) {
                        CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
                     }

                     public void visit$mcD$sp(final double a) {
                        CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
                     }

                     public void visit$mcI$sp(final int a) {
                        CanTraverseValues.ValuesVisitor.visit$mcI$sp$(this, a);
                     }

                     public void visit$mcJ$sp(final long a) {
                        CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
                     }

                     public void visit$mcS$sp(final short a) {
                        CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
                     }

                     public void visit$mcV$sp(final BoxedUnit a) {
                        CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
                     }

                     public void visitArray$mcZ$sp(final boolean[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
                     }

                     public void visitArray$mcB$sp(final byte[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
                     }

                     public void visitArray$mcC$sp(final char[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
                     }

                     public void visitArray$mcD$sp(final double[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
                     }

                     public void visitArray$mcI$sp(final int[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr);
                     }

                     public void visitArray$mcJ$sp(final long[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
                     }

                     public void visitArray$mcS$sp(final short[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
                     }

                     public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
                     }

                     public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
                     }

                     public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcD$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcI$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
                     }

                     public final double decayConst() {
                        return this.decayConst;
                     }

                     public double decayUntilNow() {
                        return this.decayUntilNow;
                     }

                     public void decayUntilNow_$eq(final double x$1) {
                        this.decayUntilNow = x$1;
                     }

                     public double sum() {
                        return this.sum;
                     }

                     public void sum_$eq(final double x$1) {
                        this.sum = x$1;
                     }

                     public void visit(final float a) {
                        this.visit$mcF$sp(a);
                     }

                     public void zeros(final int numZero, final float zeroValue) {
                        this.zeros$mcF$sp(numZero, zeroValue);
                     }

                     public void visit$mcF$sp(final float a) {
                        this.sum_$eq(this.sum() + this.decayUntilNow() * (double)a);
                        this.decayUntilNow_$eq(this.decayUntilNow() * this.decayConst());
                     }

                     public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                     }

                     public visit$3$(final double rate$2) {
                        CanTraverseValues.ValuesVisitor.$init$(this);
                        this.decayConst = (double)1.0F / ((double)1.0F + rate$2);
                        this.decayUntilNow = (double)1.0F;
                        this.sum = (double)0.0F;
                     }
                  }

                  var4 = visit$module$2.initialized() ? (visit$3$)visit$module$2.value() : (visit$3$)visit$module$2.initialize(new visit$3$(rate$2));
               } catch (Throwable var6) {
                  throw var6;
               }

               return var4;
            }

            private final visit$3$ visit$4(final LazyRef visit$module$2, final double rate$2) {
               return visit$module$2.initialized() ? (visit$3$)visit$module$2.value() : visit$lzycompute$2(visit$module$2, rate$2);
            }

            public {
               this.iter$2 = iter$2;
            }
         };
      }

      public UFunc.UImpl2 reduce_Int(final CanTraverseValues iter) {
         return new UFunc.UImpl2(iter) {
            private final CanTraverseValues iter$3;

            public double apply$mcDDD$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
            }

            public float apply$mcDDF$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
            }

            public int apply$mcDDI$sp(final double v, final double v2) {
               return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
            }

            public double apply$mcDFD$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
            }

            public float apply$mcDFF$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
            }

            public int apply$mcDFI$sp(final double v, final float v2) {
               return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
            }

            public double apply$mcDID$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
            }

            public float apply$mcDIF$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
            }

            public int apply$mcDII$sp(final double v, final int v2) {
               return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
            }

            public double apply$mcFDD$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
            }

            public float apply$mcFDF$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
            }

            public int apply$mcFDI$sp(final float v, final double v2) {
               return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
            }

            public double apply$mcFFD$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
            }

            public float apply$mcFFF$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
            }

            public int apply$mcFFI$sp(final float v, final float v2) {
               return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
            }

            public double apply$mcFID$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
            }

            public float apply$mcFIF$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
            }

            public int apply$mcFII$sp(final float v, final int v2) {
               return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
            }

            public double apply$mcIDD$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
            }

            public float apply$mcIDF$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
            }

            public int apply$mcIDI$sp(final int v, final double v2) {
               return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
            }

            public double apply$mcIFD$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
            }

            public float apply$mcIFF$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
            }

            public int apply$mcIFI$sp(final int v, final float v2) {
               return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
            }

            public double apply$mcIID$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
            }

            public float apply$mcIIF$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
            }

            public int apply$mcIII$sp(final int v, final int v2) {
               return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
            }

            public double apply(final double rate, final Object revenueStream) {
               LazyRef visit$module = new LazyRef();
               this.iter$3.traverse(revenueStream, this.visit$6(visit$module, rate));
               return this.visit$6(visit$module, rate).sum();
            }

            // $FF: synthetic method
            private static final visit$5$ visit$lzycompute$3(final LazyRef visit$module$3, final double rate$3) {
               synchronized(visit$module$3){}

               visit$5$ var4;
               try {
                  class visit$5$ implements CanTraverseValues$ValuesVisitor$mcI$sp {
                     private final double decayConst;
                     private double decayUntilNow;
                     private double sum;

                     public void visitArray(final int[] arr) {
                        CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$(this, arr);
                     }

                     public void visitArray$mcI$sp(final int[] arr) {
                        CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$mcI$sp$(this, arr);
                     }

                     public void visitArray(final int[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues$ValuesVisitor$mcI$sp.visitArray$mcI$sp$(this, arr, offset, length, stride);
                     }

                     public void visit$mcZ$sp(final boolean a) {
                        CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
                     }

                     public void visit$mcB$sp(final byte a) {
                        CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
                     }

                     public void visit$mcC$sp(final char a) {
                        CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
                     }

                     public void visit$mcD$sp(final double a) {
                        CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
                     }

                     public void visit$mcF$sp(final float a) {
                        CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
                     }

                     public void visit$mcJ$sp(final long a) {
                        CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
                     }

                     public void visit$mcS$sp(final short a) {
                        CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
                     }

                     public void visit$mcV$sp(final BoxedUnit a) {
                        CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
                     }

                     public void visitArray$mcZ$sp(final boolean[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
                     }

                     public void visitArray$mcB$sp(final byte[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
                     }

                     public void visitArray$mcC$sp(final char[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
                     }

                     public void visitArray$mcD$sp(final double[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
                     }

                     public void visitArray$mcF$sp(final float[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
                     }

                     public void visitArray$mcJ$sp(final long[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
                     }

                     public void visitArray$mcS$sp(final short[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
                     }

                     public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
                     }

                     public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
                     }

                     public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                        CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
                     }

                     public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcD$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcF$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
                     }

                     public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                        CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
                     }

                     public final double decayConst() {
                        return this.decayConst;
                     }

                     public double decayUntilNow() {
                        return this.decayUntilNow;
                     }

                     public void decayUntilNow_$eq(final double x$1) {
                        this.decayUntilNow = x$1;
                     }

                     public double sum() {
                        return this.sum;
                     }

                     public void sum_$eq(final double x$1) {
                        this.sum = x$1;
                     }

                     public void visit(final int a) {
                        this.visit$mcI$sp(a);
                     }

                     public void zeros(final int numZero, final int zeroValue) {
                        this.zeros$mcI$sp(numZero, zeroValue);
                     }

                     public void visit$mcI$sp(final int a) {
                        this.sum_$eq(this.sum() + this.decayUntilNow() * (double)a);
                        this.decayUntilNow_$eq(this.decayUntilNow() * this.decayConst());
                     }

                     public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                     }

                     public visit$5$(final double rate$3) {
                        CanTraverseValues.ValuesVisitor.$init$(this);
                        this.decayConst = (double)1.0F / ((double)1.0F + rate$3);
                        this.decayUntilNow = (double)1.0F;
                        this.sum = (double)0.0F;
                     }
                  }

                  var4 = visit$module$3.initialized() ? (visit$5$)visit$module$3.value() : (visit$5$)visit$module$3.initialize(new visit$5$(rate$3));
               } catch (Throwable var6) {
                  throw var6;
               }

               return var4;
            }

            private final visit$5$ visit$6(final LazyRef visit$module$3, final double rate$3) {
               return visit$module$3.initialized() ? (visit$5$)visit$module$3.value() : visit$lzycompute$3(visit$module$3, rate$3);
            }

            public {
               this.iter$3 = iter$3;
            }
         };
      }
   }
}
