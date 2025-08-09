package spire.algebra;

import algebra.lattice.Bool;
import algebra.ring.AdditiveCommutativeGroup;
import algebra.ring.AdditiveCommutativeMonoid;
import algebra.ring.AdditiveCommutativeSemigroup;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.CommutativeRig;
import algebra.ring.CommutativeRing;
import algebra.ring.CommutativeRng;
import algebra.ring.CommutativeSemiring;
import algebra.ring.DivisionRing;
import algebra.ring.EuclideanRing;
import algebra.ring.Field;
import algebra.ring.GCDRing;
import algebra.ring.MultiplicativeCommutativeGroup;
import algebra.ring.MultiplicativeCommutativeMonoid;
import algebra.ring.MultiplicativeCommutativeSemigroup;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import algebra.ring.TruncatedDivision;
import cats.kernel.CommutativeGroup;
import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import scala.Option;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015-w\u0001CA\b\u0003#A\t!a\u0007\u0007\u0011\u0005}\u0011\u0011\u0003E\u0001\u0003CAq!a\f\u0002\t\u0003\t\t$\u0002\u0004\u00024\u0005\u0001\u0011Q\u0007\u0005\n\u0003G\n!\u0019!C\u0001\u0003KB\u0001\"a\u001f\u0002A\u0003%\u0011qM\u0003\u0007\u0003{\n\u0001!a \t\u0013\u0005%\u0015A1A\u0005\u0002\u0005-\u0005\u0002CAI\u0003\u0001\u0006I!!$\u0006\r\u0005M\u0015\u0001AAK\u0011%\ty*\u0001b\u0001\n\u0003\t\t\u000b\u0003\u0005\u0002(\u0006\u0001\u000b\u0011BAR\u000b\u0019\tI+\u0001\u0001\u0002,\"I\u0011QW\u0001C\u0002\u0013\u0005\u0011q\u0017\u0005\t\u0003{\u000b\u0001\u0015!\u0003\u0002:\u00161\u0011qX\u0001\u0001\u0003\u0003D\u0011\"!4\u0002\u0005\u0004%\t!a4\t\u0011\u0005]\u0017\u0001)A\u0005\u0003#,a!!7\u0002\u0001\u0005m\u0007\"CAs\u0003\t\u0007I\u0011AAt\u0011!\ti/\u0001Q\u0001\n\u0005%XABAx\u0003\u0001\t\t\u0010C\u0005\u0002~\u0006\u0011\r\u0011\"\u0001\u0002\u0000\"A!qA\u0001!\u0002\u0013\u0011\t!\u0002\u0004\u0003\n\u0005\u0001!1\u0002\u0005\n\u0005+\t!\u0019!C\u0001\u0005/A\u0001B!\b\u0002A\u0003%!\u0011D\u0003\u0007\u0005?\t\u0001A!\t\t\u0013\t5\u0012A1A\u0005\u0002\t=\u0002\u0002\u0003B\u001c\u0003\u0001\u0006IA!\r\u0006\r\te\u0012\u0001\u0001B\u001e\u0011%\u0011Y%\u0001b\u0001\n\u0003\u0011i\u0005\u0003\u0005\u0003X\u0005\u0001\u000b\u0011\u0002B(\u000b\u0019\u0011I&\u0001\u0001\u0003\\!I!qM\u0001C\u0002\u0013\u0005!\u0011\u000e\u0005\t\u0005c\n\u0001\u0015!\u0003\u0003l\u00151!1O\u0001\u0001\u0005kB\u0011Ba \u0002\u0005\u0004%\tA!!\t\u0011\t\u001d\u0015\u0001)A\u0005\u0005\u0007+aA!#\u0002\u0001\t-\u0005\"\u0003BL\u0003\t\u0007I\u0011\u0001BM\u0011!\u0011\t+\u0001Q\u0001\n\tmUA\u0002BR\u0003\u0001\u0011)\u000bC\u0005\u00030\u0006\u0011\r\u0011\"\u0001\u00032\"A!qW\u0001!\u0002\u0013\u0011\u0019,\u0002\u0004\u0003:\u0006\u0001!1\u0018\u0005\n\u0005\u000f\f!\u0019!C\u0001\u0005\u0013D\u0001B!5\u0002A\u0003%!1Z\u0004\b\u0005'\f\u0001\u0012\u0001Bk\r\u001d\u0011I.\u0001E\u0001\u00057Dq!a\f2\t\u0003\u0011i\u000eC\u0004\u0003`F\"\tA!9\t\u000f\t}\u0017\u0007\"\u0001\u0003r\"9!q\\\u0019\u0005\u0002\t}\bb\u0002Bpc\u0011\u00051q\u0002\u0005\b\u0005?\fD\u0011AB\u000f\u0011\u001d\u0011y.\rC\u0001\u0007[)aaa\u000f\u0002\u0001\ru\u0002\"CB$\u0003\t\u0007I\u0011AB%\u0011!\u0019y%\u0001Q\u0001\n\r-SABB)\u0003\u0001\u0019\u0019\u0006C\u0005\u0004`\u0005\u0011\r\u0011\"\u0001\u0004b!A1\u0011N\u0001!\u0002\u0013\u0019\u0019'\u0002\u0004\u0004l\u0005\u00011Q\u000e\u0005\n\u0007o\n!\u0019!C\u0001\u0007sB\u0001ba \u0002A\u0003%11P\u0003\u0007\u0007\u0003\u000b\u0001aa!\t\u0013\r=\u0015A1A\u0005\u0002\rE\u0005\u0002CBM\u0003\u0001\u0006Iaa%\u0006\r\rm\u0015\u0001ABO\u0011%\u00199+\u0001b\u0001\n\u0003\u0019I\u000b\u0003\u0005\u00040\u0006\u0001\u000b\u0011BBV\u000b\u0019\u0019\t,\u0001\u0001\u00044\"I1qX\u0001C\u0002\u0013\u00051\u0011\u0019\u0005\t\u0007\u0013\f\u0001\u0015!\u0003\u0004D\u001e911Z\u0001\t\u0002\r5gaBBh\u0003!\u00051\u0011\u001b\u0005\b\u0003_aE\u0011ABj\u0011\u001d\u0011y\u000e\u0014C\u0001\u0007+DqAa8M\t\u0003\u0019\u0019\u000fC\u0004\u0003`2#\ta!=\t\u000f\t}G\n\"\u0001\u0004\u0000\"9!q\u001c'\u0005\u0002\u00115\u0001b\u0002Bp\u0019\u0012\u0005A1D\u0003\u0007\tS\t\u0001\u0001b\u000b\t\u0013\u0011U\u0012A1A\u0005\u0002\u0011]\u0002\u0002\u0003C\u001f\u0003\u0001\u0006I\u0001\"\u000f\u0006\r\u0011}\u0012\u0001\u0001C!\u0011%!i%\u0001b\u0001\n\u0003!y\u0005\u0003\u0005\u0005X\u0005\u0001\u000b\u0011\u0002C)\u000b\u0019!I&\u0001\u0001\u0005\\!IAQM\u0001C\u0002\u0013\u0005Aq\r\u0005\t\t[\n\u0001\u0015!\u0003\u0005j\u00151AqN\u0001\u0001\tcB\u0011\u0002\" \u0002\u0005\u0004%\t\u0001b \t\u0011\u0011\u001d\u0015\u0001)A\u0005\t\u0003+a\u0001\"#\u0002\u0001\u0011-\u0005\"\u0003CK\u0003\t\u0007I\u0011\u0001CL\u0011!!i*\u0001Q\u0001\n\u0011eUA\u0002CP\u0003\u0001!\t\u000bC\u0005\u0005.\u0006\u0011\r\u0011\"\u0001\u00050\"AAqW\u0001!\u0002\u0013!\t,\u0002\u0004\u0005:\u0006\u0001A1\u0018\u0005\n\t\u000b\f!\u0019!C\u0001\t\u000fD\u0001\u0002\"4\u0002A\u0003%A\u0011Z\u0003\u0007\t\u001f\f\u0001\u0001\"5\t\u0013\u0011u\u0017A1A\u0005\u0002\u0011}\u0007\u0002\u0003Ct\u0003\u0001\u0006I\u0001\"9\u0006\r\u0011%\u0018\u0001\u0001Cv\u0011%!)0\u0001b\u0001\n\u0003!9\u0010\u0003\u0005\u0005~\u0006\u0001\u000b\u0011\u0002C}\u000b\u0019!y0\u0001\u0001\u0006\u0002!IQ1B\u0001C\u0002\u0013\u0005QQ\u0002\u0005\t\u000b'\t\u0001\u0015!\u0003\u0006\u0010\u00151QQC\u0001\u0001\u000b/A\u0011\"b\t\u0002\u0005\u0004%\t!\"\n\t\u0011\u0015-\u0012\u0001)A\u0005\u000bO)a!\"\f\u0002\u0001\u0015=\u0002\"CC\u000f\u0003\t\u0007I\u0011AC\u001d\u0011!)Y$\u0001Q\u0001\n\u0015eQABC\u001f\u0003\u0001)y$\u0002\u0004\u0006L\u0005\u0001QQJ\u0003\u0007\u000b3\n\u0001!b\u0017\t\u0013\u0015\u0015\u0014A1A\u0005\u0002\u0015\u001d\u0004\u0002CC7\u0003\u0001\u0006I!\"\u001b\u0006\r\u0015=\u0014\u0001AC9\u000b\u0019)y(\u0001\u0001\u0006\u0002\"IQ1R\u0001C\u0002\u0013\u0005QQ\u0012\u0005\t\u000b'\u000b\u0001\u0015!\u0003\u0006\u0010\u00161QQS\u0001\u0001\u000b/C\u0011\"\")\u0002\u0005\u0004%\t!b)\t\u0011\u0015%\u0016\u0001)A\u0005\u000bK+a!b+\u0002\u0001\u00155\u0006\"CC_\u0003\t\u0007I\u0011AC`\u0011!)I-\u0001Q\u0001\n\u0015\u0005\u0017a\u00029bG.\fw-\u001a\u0006\u0005\u0003'\t)\"A\u0004bY\u001e,'M]1\u000b\u0005\u0005]\u0011!B:qSJ,7\u0001\u0001\t\u0004\u0003;\tQBAA\t\u0005\u001d\u0001\u0018mY6bO\u0016\u001c2!AA\u0012!\u0011\t)#a\u000b\u000e\u0005\u0005\u001d\"BAA\u0015\u0003\u0015\u00198-\u00197b\u0013\u0011\ti#a\n\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\u0011\u00111\u0004\u0002\u0003\u000bF,B!a\u000e\u0002RA1\u0011\u0011HA%\u0003\u001brA!a\u000f\u0002F9!\u0011QHA\"\u001b\t\tyD\u0003\u0003\u0002B\u0005e\u0011A\u0002\u001fs_>$h(\u0003\u0002\u0002\u0014%!\u0011qBA$\u0015\t\t\u0019\"\u0003\u0003\u00024\u0005-#\u0002BA\b\u0003\u000f\u0002B!a\u0014\u0002R1\u0001AaBA*\u0007\t\u0007\u0011Q\u000b\u0002\u0002\u0003F!\u0011qKA/!\u0011\t)#!\u0017\n\t\u0005m\u0013q\u0005\u0002\b\u001d>$\b.\u001b8h!\u0011\t)#a\u0018\n\t\u0005\u0005\u0014q\u0005\u0002\u0004\u0003:L\u0018AA#r+\t\t9G\u0004\u0003\u0002j\u0005]d\u0002BA6\u0003crA!!\u0010\u0002n%\u0011\u0011qN\u0001\u0005G\u0006$8/\u0003\u0003\u0002t\u0005U\u0014AB6fe:,GN\u0003\u0002\u0002p%!\u00111MA=\u0015\u0011\t\u0019(!\u001e\u0002\u0007\u0015\u000b\bE\u0001\u0007QCJ$\u0018.\u00197Pe\u0012,'/\u0006\u0003\u0002\u0002\u0006\u001d\u0005CBA\u001d\u0003\u0007\u000b))\u0003\u0003\u0002~\u0005-\u0003\u0003BA(\u0003\u000f#q!a\u0015\u0007\u0005\u0004\t)&\u0001\u0007QCJ$\u0018.\u00197Pe\u0012,'/\u0006\u0002\u0002\u000e:!\u0011\u0011NAH\u0013\u0011\tI)!\u001f\u0002\u001bA\u000b'\u000f^5bY>\u0013H-\u001a:!\u0005\u0015y%\u000fZ3s+\u0011\t9*!(\u0011\r\u0005e\u0012\u0011TAN\u0013\u0011\t\u0019*a\u0013\u0011\t\u0005=\u0013Q\u0014\u0003\b\u0003'J!\u0019AA+\u0003\u0015y%\u000fZ3s+\t\t\u0019K\u0004\u0003\u0002j\u0005\u0015\u0016\u0002BAP\u0003s\naa\u0014:eKJ\u0004#!C*f[&<'o\\;q+\u0011\ti+a-\u0011\r\u0005e\u0012qVAY\u0013\u0011\tI+a\u0013\u0011\t\u0005=\u00131\u0017\u0003\b\u0003'b!\u0019AA+\u0003%\u0019V-\\5he>,\b/\u0006\u0002\u0002::!\u0011\u0011NA^\u0013\u0011\t),!\u001f\u0002\u0015M+W.[4s_V\u0004\bE\u0001\u0006D'\u0016l\u0017n\u001a:pkB,B!a1\u0002LB1\u0011\u0011HAc\u0003\u0013LA!a2\u0002L\t!2i\\7nkR\fG/\u001b<f'\u0016l\u0017n\u001a:pkB\u0004B!a\u0014\u0002L\u00129\u00111K\bC\u0002\u0005U\u0013AC\"TK6LwM]8vaV\u0011\u0011\u0011\u001b\b\u0005\u0003S\n\u0019.\u0003\u0003\u0002V\u0006e\u0014\u0001F\"p[6,H/\u0019;jm\u0016\u001cV-\\5he>,\b/A\u0006D'\u0016l\u0017n\u001a:pkB\u0004#AB'p]>LG-\u0006\u0003\u0002^\u0006\r\bCBA\u001d\u0003?\f\t/\u0003\u0003\u0002Z\u0006-\u0003\u0003BA(\u0003G$q!a\u0015\u0013\u0005\u0004\t)&\u0001\u0004N_:|\u0017\u000eZ\u000b\u0003\u0003StA!!\u001b\u0002l&!\u0011Q]A=\u0003\u001diuN\\8jI\u0002\u0012qaQ'p]>LG-\u0006\u0003\u0002t\u0006m\bCBA\u001d\u0003k\fI0\u0003\u0003\u0002x\u0006-#!E\"p[6,H/\u0019;jm\u0016luN\\8jIB!\u0011qJA~\t\u001d\t\u0019&\u0006b\u0001\u0003+\nqaQ'p]>LG-\u0006\u0002\u0003\u00029!\u0011\u0011\u000eB\u0002\u0013\u0011\u0011)!!\u001f\u0002#\r{W.\\;uCRLg/Z'p]>LG-\u0001\u0005D\u001b>tw.\u001b3!\u0005\u00159%o\\;q+\u0011\u0011iAa\u0005\u0011\r\u0005e\"q\u0002B\t\u0013\u0011\u0011I!a\u0013\u0011\t\u0005=#1\u0003\u0003\b\u0003'B\"\u0019AA+\u0003\u00159%o\\;q+\t\u0011IB\u0004\u0003\u0002j\tm\u0011\u0002\u0002B\u000b\u0003s\naa\u0012:pkB\u0004#aB!c\u000fJ|W\u000f]\u000b\u0005\u0005G\u0011Y\u0003\u0005\u0004\u0002:\t\u0015\"\u0011F\u0005\u0005\u0005O\tYE\u0001\tD_6lW\u000f^1uSZ,wI]8vaB!\u0011q\nB\u0016\t\u001d\t\u0019f\u0007b\u0001\u0003+\nq!\u00112He>,\b/\u0006\u0002\u000329!\u0011\u0011\u000eB\u001a\u0013\u0011\u0011)$!\u001f\u0002!\r{W.\\;uCRLg/Z$s_V\u0004\u0018\u0001C!c\u000fJ|W\u000f\u001d\u0011\u0003#\u0005#G-\u001b;jm\u0016\u001cV-\\5he>,\b/\u0006\u0003\u0003>\t%\u0003C\u0002B \u0005\u000b\u00129%\u0004\u0002\u0003B)!!1IA$\u0003\u0011\u0011\u0018N\\4\n\t\te\"\u0011\t\t\u0005\u0003\u001f\u0012I\u0005B\u0004\u0002Ty\u0011\r!!\u0016\u0002#\u0005#G-\u001b;jm\u0016\u001cV-\\5he>,\b/\u0006\u0002\u0003P9!!\u0011\u000bB+\u001d\u0011\tYDa\u0015\n\t\t\r\u0013qI\u0005\u0005\u0005\u0017\u0012\t%\u0001\nBI\u0012LG/\u001b<f'\u0016l\u0017n\u001a:pkB\u0004#AE!eI&$\u0018N^3D'\u0016l\u0017n\u001a:pkB,BA!\u0018\u0003fA1!q\bB0\u0005GJAA!\u0019\u0003B\ta\u0012\t\u001a3ji&4XmQ8n[V$\u0018\r^5wKN+W.[4s_V\u0004\b\u0003BA(\u0005K\"q!a\u0015\"\u0005\u0004\t)&\u0001\nBI\u0012LG/\u001b<f\u0007N+W.[4s_V\u0004XC\u0001B6\u001d\u0011\u0011\tF!\u001c\n\t\t=$\u0011I\u0001\u001d\u0003\u0012$\u0017\u000e^5wK\u000e{W.\\;uCRLg/Z*f[&<'o\\;q\u0003M\tE\rZ5uSZ,7iU3nS\u001e\u0014x.\u001e9!\u00059\tE\rZ5uSZ,Wj\u001c8pS\u0012,BAa\u001e\u0003~A1!q\bB=\u0005wJAAa\u001d\u0003BA!\u0011q\nB?\t\u001d\t\u0019\u0006\nb\u0001\u0003+\na\"\u00113eSRLg/Z'p]>LG-\u0006\u0002\u0003\u0004:!!\u0011\u000bBC\u0013\u0011\u0011yH!\u0011\u0002\u001f\u0005#G-\u001b;jm\u0016luN\\8jI\u0002\u0012q\"\u00113eSRLg/Z\"N_:|\u0017\u000eZ\u000b\u0005\u0005\u001b\u0013)\n\u0005\u0004\u0003@\t=%1S\u0005\u0005\u0005#\u0013\tEA\rBI\u0012LG/\u001b<f\u0007>lW.\u001e;bi&4X-T8o_&$\u0007\u0003BA(\u0005+#q!a\u0015(\u0005\u0004\t)&A\bBI\u0012LG/\u001b<f\u00076{gn\\5e+\t\u0011YJ\u0004\u0003\u0003R\tu\u0015\u0002\u0002BP\u0005\u0003\n\u0011$\u00113eSRLg/Z\"p[6,H/\u0019;jm\u0016luN\\8jI\u0006\u0001\u0012\t\u001a3ji&4XmQ'p]>LG\r\t\u0002\u000e\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9\u0016\t\t\u001d&Q\u0016\t\u0007\u0005\u007f\u0011IKa+\n\t\t\r&\u0011\t\t\u0005\u0003\u001f\u0012i\u000bB\u0004\u0002T)\u0012\r!!\u0016\u0002\u001b\u0005#G-\u001b;jm\u0016<%o\\;q+\t\u0011\u0019L\u0004\u0003\u0003R\tU\u0016\u0002\u0002BX\u0005\u0003\na\"\u00113eSRLg/Z$s_V\u0004\bEA\bBI\u0012LG/\u001b<f\u0003\n<%o\\;q+\u0011\u0011iL!2\u0011\r\t}\"q\u0018Bb\u0013\u0011\u0011\tM!\u0011\u00031\u0005#G-\u001b;jm\u0016\u001cu.\\7vi\u0006$\u0018N^3He>,\b\u000f\u0005\u0003\u0002P\t\u0015GaBA*[\t\u0007\u0011QK\u0001\u0010\u0003\u0012$\u0017\u000e^5wK\u0006\u0013wI]8vaV\u0011!1\u001a\b\u0005\u0005#\u0012i-\u0003\u0003\u0003P\n\u0005\u0013\u0001G!eI&$\u0018N^3D_6lW\u000f^1uSZ,wI]8va\u0006\u0001\u0012\t\u001a3ji&4X-\u00112He>,\b\u000fI\u0001\t\u0003\u0012$\u0017\u000e^5wKB\u0019!q[\u0019\u000e\u0003\u0005\u0011\u0001\"\u00113eSRLg/Z\n\u0004c\u0005\rBC\u0001Bk\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\u0011\u0019O!;\u0015\t\t\u0015(1\u001e\t\u0006\u0005/t\"q\u001d\t\u0005\u0003\u001f\u0012I\u000fB\u0004\u0002TM\u0012\r!!\u0016\t\u000f\t58\u00071\u0001\u0003p\u0006\t1\u000fE\u0003\u0003X2\u00119/\u0006\u0003\u0003t\neH\u0003\u0002B{\u0005w\u0004RAa6\"\u0005o\u0004B!a\u0014\u0003z\u00129\u00111\u000b\u001bC\u0002\u0005U\u0003b\u0002Bwi\u0001\u0007!Q \t\u0006\u0005/|!q_\u000b\u0005\u0007\u0003\u00199\u0001\u0006\u0003\u0004\u0004\r%\u0001#\u0002BlI\r\u0015\u0001\u0003BA(\u0007\u000f!q!a\u00156\u0005\u0004\t)\u0006C\u0004\u0004\fU\u0002\ra!\u0004\u0002\u00035\u0004RAa6\u0013\u0007\u000b)Ba!\u0005\u0004\u0018Q!11CB\r!\u0015\u00119nJB\u000b!\u0011\tyea\u0006\u0005\u000f\u0005McG1\u0001\u0002V!911\u0002\u001cA\u0002\rm\u0001#\u0002Bl+\rUQ\u0003BB\u0010\u0007K!Ba!\t\u0004(A)!q\u001b\u0016\u0004$A!\u0011qJB\u0013\t\u001d\t\u0019f\u000eb\u0001\u0003+Bqa!\u000b8\u0001\u0004\u0019Y#A\u0001h!\u0015\u00119\u000eGB\u0012+\u0011\u0019yc!\u000e\u0015\t\rE2q\u0007\t\u0006\u0005/l31\u0007\t\u0005\u0003\u001f\u001a)\u0004B\u0004\u0002Ta\u0012\r!!\u0016\t\u000f\r%\u0002\b1\u0001\u0004:A)!q[\u000e\u00044\t9R*\u001e7uSBd\u0017nY1uSZ,7+Z7jOJ|W\u000f]\u000b\u0005\u0007\u007f\u0019)\u0005\u0005\u0004\u0003@\r\u000531I\u0005\u0005\u0007w\u0011\t\u0005\u0005\u0003\u0002P\r\u0015CaBA*s\t\u0007\u0011QK\u0001\u0018\u001bVdG/\u001b9mS\u000e\fG/\u001b<f'\u0016l\u0017n\u001a:pkB,\"aa\u0013\u000f\t\tE3QJ\u0005\u0005\u0007\u000f\u0012\t%\u0001\rNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3TK6LwM]8va\u0002\u0012\u0001$T;mi&\u0004H.[2bi&4XmQ*f[&<'o\\;q+\u0011\u0019)f!\u0018\u0011\r\t}2qKB.\u0013\u0011\u0019IF!\u0011\u0003E5+H\u000e^5qY&\u001c\u0017\r^5wK\u000e{W.\\;uCRLg/Z*f[&<'o\\;q!\u0011\tye!\u0018\u0005\u000f\u0005MCH1\u0001\u0002V\u0005AR*\u001e7uSBd\u0017nY1uSZ,7iU3nS\u001e\u0014x.\u001e9\u0016\u0005\r\rd\u0002\u0002B)\u0007KJAaa\u001a\u0003B\u0005\u0011S*\u001e7uSBd\u0017nY1uSZ,7i\\7nkR\fG/\u001b<f'\u0016l\u0017n\u001a:pkB\f\u0011$T;mi&\u0004H.[2bi&4XmQ*f[&<'o\\;qA\t!R*\u001e7uSBd\u0017nY1uSZ,Wj\u001c8pS\u0012,Baa\u001c\u0004vA1!qHB9\u0007gJAaa\u001b\u0003BA!\u0011qJB;\t\u001d\t\u0019f\u0010b\u0001\u0003+\nA#T;mi&\u0004H.[2bi&4X-T8o_&$WCAB>\u001d\u0011\u0011\tf! \n\t\r]$\u0011I\u0001\u0016\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u001b>tw.\u001b3!\u0005UiU\u000f\u001c;ja2L7-\u0019;jm\u0016\u001cUj\u001c8pS\u0012,Ba!\"\u0004\u000eB1!qHBD\u0007\u0017KAa!#\u0003B\tyR*\u001e7uSBd\u0017nY1uSZ,7i\\7nkR\fG/\u001b<f\u001b>tw.\u001b3\u0011\t\u0005=3Q\u0012\u0003\b\u0003'\u0012%\u0019AA+\u0003UiU\u000f\u001c;ja2L7-\u0019;jm\u0016\u001cUj\u001c8pS\u0012,\"aa%\u000f\t\tE3QS\u0005\u0005\u0007/\u0013\t%A\u0010Nk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3D_6lW\u000f^1uSZ,Wj\u001c8pS\u0012\fa#T;mi&\u0004H.[2bi&4XmQ'p]>LG\r\t\u0002\u0014\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u000fJ|W\u000f]\u000b\u0005\u0007?\u001b)\u000b\u0005\u0004\u0003@\r\u000561U\u0005\u0005\u00077\u0013\t\u0005\u0005\u0003\u0002P\r\u0015FaBA*\u000b\n\u0007\u0011QK\u0001\u0014\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u000fJ|W\u000f]\u000b\u0003\u0007WsAA!\u0015\u0004.&!1q\u0015B!\u0003QiU\u000f\u001c;ja2L7-\u0019;jm\u0016<%o\\;qA\t)R*\u001e7uSBd\u0017nY1uSZ,\u0017IY$s_V\u0004X\u0003BB[\u0007{\u0003bAa\u0010\u00048\u000em\u0016\u0002BB]\u0005\u0003\u0012a$T;mi&\u0004H.[2bi&4XmQ8n[V$\u0018\r^5wK\u001e\u0013x.\u001e9\u0011\t\u0005=3Q\u0018\u0003\b\u0003'B%\u0019AA+\u0003UiU\u000f\u001c;ja2L7-\u0019;jm\u0016\f%m\u0012:pkB,\"aa1\u000f\t\tE3QY\u0005\u0005\u0007\u000f\u0014\t%\u0001\u0010Nk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3D_6lW\u000f^1uSZ,wI]8va\u00061R*\u001e7uSBd\u0017nY1uSZ,\u0017IY$s_V\u0004\b%\u0001\bNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3\u0011\u0007\t]GJ\u0001\bNk2$\u0018\u000e\u001d7jG\u0006$\u0018N^3\u0014\u00071\u000b\u0019\u0003\u0006\u0002\u0004NV!1q[Bo)\u0011\u0019Ina8\u0011\u000b\t]\u0017ha7\u0011\t\u0005=3Q\u001c\u0003\b\u0003'r%\u0019AA+\u0011\u001d\u0011iO\u0014a\u0001\u0007C\u0004RAa6\r\u00077,Ba!:\u0004lR!1q]Bw!\u0015\u00119\u000ePBu!\u0011\tyea;\u0005\u000f\u0005MsJ1\u0001\u0002V!9!Q^(A\u0002\r=\b#\u0002Bl\u001f\r%X\u0003BBz\u0007s$Ba!>\u0004|B)!q[ \u0004xB!\u0011qJB}\t\u001d\t\u0019\u0006\u0015b\u0001\u0003+Bqaa\u0003Q\u0001\u0004\u0019i\u0010E\u0003\u0003XJ\u001990\u0006\u0003\u0005\u0002\u0011\u001dA\u0003\u0002C\u0002\t\u0013\u0001RAa6C\t\u000b\u0001B!a\u0014\u0005\b\u00119\u00111K)C\u0002\u0005U\u0003bBB\u0006#\u0002\u0007A1\u0002\t\u0006\u0005/,BQA\u000b\u0005\t\u001f!)\u0002\u0006\u0003\u0005\u0012\u0011]\u0001#\u0002Bl\u000b\u0012M\u0001\u0003BA(\t+!q!a\u0015S\u0005\u0004\t)\u0006C\u0004\u0004*I\u0003\r\u0001\"\u0007\u0011\u000b\t]\u0007\u0004b\u0005\u0016\t\u0011uA1\u0005\u000b\u0005\t?!)\u0003E\u0003\u0003X\"#\t\u0003\u0005\u0003\u0002P\u0011\rBaBA*'\n\u0007\u0011Q\u000b\u0005\b\u0007S\u0019\u0006\u0019\u0001C\u0014!\u0015\u00119n\u0007C\u0011\u0005!\u0019V-\\5sS:<W\u0003\u0002C\u0017\tg\u0001bAa\u0010\u00050\u0011E\u0012\u0002\u0002C\u0015\u0005\u0003\u0002B!a\u0014\u00054\u00119\u00111\u000b+C\u0002\u0005U\u0013\u0001C*f[&\u0014\u0018N\\4\u0016\u0005\u0011eb\u0002\u0002B)\twIA\u0001\"\u000e\u0003B\u0005I1+Z7je&tw\r\t\u0002\n\u0007N+W.\u001b:j]\u001e,B\u0001b\u0011\u0005LA1!q\bC#\t\u0013JA\u0001b\u0012\u0003B\t\u00192i\\7nkR\fG/\u001b<f'\u0016l\u0017N]5oOB!\u0011q\nC&\t\u001d\t\u0019f\u0016b\u0001\u0003+\n\u0011bQ*f[&\u0014\u0018N\\4\u0016\u0005\u0011Ec\u0002\u0002B)\t'JA\u0001\"\u0016\u0003B\u0005\u00192i\\7nkR\fG/\u001b<f'\u0016l\u0017N]5oO\u0006Q1iU3nSJLgn\u001a\u0011\u0003\u0007IKw-\u0006\u0003\u0005^\u0011\r\u0004C\u0002B \t?\"\t'\u0003\u0003\u0005Z\t\u0005\u0003\u0003BA(\tG\"q!a\u0015[\u0005\u0004\t)&A\u0002SS\u001e,\"\u0001\"\u001b\u000f\t\tEC1N\u0005\u0005\tK\u0012\t%\u0001\u0003SS\u001e\u0004#\u0001B\"SS\u001e,B\u0001b\u001d\u0005|A1!q\bC;\tsJA\u0001b\u001e\u0003B\tq1i\\7nkR\fG/\u001b<f%&<\u0007\u0003BA(\tw\"q!a\u0015^\u0005\u0004\t)&\u0001\u0003D%&<WC\u0001CA\u001d\u0011\u0011\t\u0006b!\n\t\u0011\u0015%\u0011I\u0001\u000f\u0007>lW.\u001e;bi&4XMU5h\u0003\u0015\u0019%+[4!\u0005\r\u0011fnZ\u000b\u0005\t\u001b#\u0019\n\u0005\u0004\u0003@\u0011=E\u0011S\u0005\u0005\t\u0013\u0013\t\u0005\u0005\u0003\u0002P\u0011MEaBA*A\n\u0007\u0011QK\u0001\u0004%:<WC\u0001CM\u001d\u0011\u0011\t\u0006b'\n\t\u0011U%\u0011I\u0001\u0005%:<\u0007E\u0001\u0003D%:<W\u0003\u0002CR\tW\u0003bAa\u0010\u0005&\u0012%\u0016\u0002\u0002CT\u0005\u0003\u0012abQ8n[V$\u0018\r^5wKJsw\r\u0005\u0003\u0002P\u0011-FaBA*G\n\u0007\u0011QK\u0001\u0005\u0007Jsw-\u0006\u0002\u00052:!!\u0011\u000bCZ\u0013\u0011!)L!\u0011\u0002\u001d\r{W.\\;uCRLg/\u001a*oO\u0006)1I\u00158hA\t!!+\u001b8h+\u0011!i\fb1\u0011\r\t}Bq\u0018Ca\u0013\u0011!IL!\u0011\u0011\t\u0005=C1\u0019\u0003\b\u0003'2'\u0019AA+\u0003\u0011\u0011\u0016N\\4\u0016\u0005\u0011%g\u0002\u0002B)\t\u0017LA\u0001\"2\u0003B\u0005)!+\u001b8hA\t)1IU5oOV!A1\u001bCn!\u0019\u0011y\u0004\"6\u0005Z&!Aq\u001bB!\u0005=\u0019u.\\7vi\u0006$\u0018N^3SS:<\u0007\u0003BA(\t7$q!a\u0015j\u0005\u0004\t)&A\u0003D%&tw-\u0006\u0002\u0005b:!!\u0011\u000bCr\u0013\u0011!)O!\u0011\u0002\u001f\r{W.\\;uCRLg/\u001a*j]\u001e\faa\u0011*j]\u001e\u0004#aB$D\tJKgnZ\u000b\u0005\t[$\u0019\u0010\u0005\u0004\u0003@\u0011=H\u0011_\u0005\u0005\tS\u0014\t\u0005\u0005\u0003\u0002P\u0011MHaBA*Y\n\u0007\u0011QK\u0001\b\u000f\u000e#%+\u001b8h+\t!IP\u0004\u0003\u0003R\u0011m\u0018\u0002\u0002C{\u0005\u0003\n\u0001bR\"E%&tw\r\t\u0002\u000e\u000bV\u001cG.\u001b3fC:\u0014\u0016N\\4\u0016\t\u0015\rQ\u0011\u0002\t\u0007\u0005\u007f))!b\u0002\n\t\u0011}(\u0011\t\t\u0005\u0003\u001f*I\u0001B\u0004\u0002T=\u0014\r!!\u0016\u0002\u001b\u0015+8\r\\5eK\u0006t'+\u001b8h+\t)yA\u0004\u0003\u0003R\u0015E\u0011\u0002BC\u0006\u0005\u0003\na\"R;dY&$W-\u00198SS:<\u0007E\u0001\u0003TS\u001et\u0007\u0003BC\r\u000b?qAA!\u0015\u0006\u001c%!QQ\u0004B!\u0003\u0019\u0019\u0016n\u001a8fI&!QQCC\u0011\u0015\u0011)iB!\u0011\u0002\tMKwM\\\u000b\u0003\u000bOqA!\"\u0007\u0006*%!Q1EC\u0011\u0003\u0015\u0019\u0016n\u001a8!\u0005\u0019\u0019\u0016n\u001a8fIV!Q\u0011GC\u001c!\u0019\u0011y$b\r\u00066%!QQ\u0006B!!\u0011\ty%b\u000e\u0005\u000f\u0005MSO1\u0001\u0002VU\u0011Q\u0011D\u0001\b'&<g.\u001a3!\u0005U\u0019\u0016n\u001a8fI\u0006#G-\u001b;jm\u0016\u001cUj\u001c8pS\u0012,B!\"\u0011\u0006JA1Q\u0011DC\"\u000b\u000fJA!\"\u0012\u0006\"\tabm\u001c:BI\u0012LG/\u001b<f\u0007>lW.\u001e;bi&4X-T8o_&$\u0007\u0003BA(\u000b\u0013\"q!a\u0015y\u0005\u0004\t)FA\u000bTS\u001etW\rZ!eI&$\u0018N^3BE\u001e\u0013x.\u001e9\u0016\t\u0015=Sq\u000b\t\u0007\u000b3)\t&\"\u0016\n\t\u0015MS\u0011\u0005\u0002\u001cM>\u0014\u0018\t\u001a3ji&4XmQ8n[V$\u0018\r^5wK\u001e\u0013x.\u001e9\u0011\t\u0005=Sq\u000b\u0003\b\u0003'J(\u0019AA+\u0005E!&/\u001e8dCR,G\rR5wSNLwN\\\u000b\u0005\u000b;*\u0019\u0007\u0005\u0004\u0003@\u0015}S\u0011M\u0005\u0005\u000b3\u0012\t\u0005\u0005\u0003\u0002P\u0015\rDaBA*u\n\u0007\u0011QK\u0001\u0012)J,hnY1uK\u0012$\u0015N^5tS>tWCAC5\u001d\u0011\u0011\t&b\u001b\n\t\u0015\u0015$\u0011I\u0001\u0013)J,hnY1uK\u0012$\u0015N^5tS>t\u0007E\u0001\fUeVt7-\u0019;fI\u0012Kg/[:j_:\u001c%+\u001b8h+\u0011)\u0019(\" \u0011\r\u0015%TQOC>\u0013\u0011)9(\"\u001f\u0003%\u0019|'oQ8n[V$\u0018\r^5wKJKgn\u001a\u0006\u0005\u000bK\u0012\t\u0005\u0005\u0003\u0002P\u0015uDaBA*{\n\u0007\u0011Q\u000b\u0002\r\t&4\u0018n]5p]JKgnZ\u000b\u0005\u000b\u0007+I\t\u0005\u0004\u0003@\u0015\u0015UqQ\u0005\u0005\u000b\u007f\u0012\t\u0005\u0005\u0003\u0002P\u0015%EaBA*}\n\u0007\u0011QK\u0001\r\t&4\u0018n]5p]JKgnZ\u000b\u0003\u000b\u001fsAA!\u0015\u0006\u0012&!Q1\u0012B!\u00035!\u0015N^5tS>t'+\u001b8hA\t)a)[3mIV!Q\u0011TCP!\u0019\u0011y$b'\u0006\u001e&!QQ\u0013B!!\u0011\ty%b(\u0005\u0011\u0005M\u00131\u0001b\u0001\u0003+\nQAR5fY\u0012,\"!\"*\u000f\t\tESqU\u0005\u0005\u000bC\u0013\t%\u0001\u0004GS\u0016dG\r\t\u0002\u0005\u0005>|G.\u0006\u0003\u00060\u0016m\u0006CBCY\u000bo+I,\u0004\u0002\u00064*!QQWA$\u0003\u001da\u0017\r\u001e;jG\u0016LA!b+\u00064B!\u0011qJC^\t!\t\u0019&!\u0003C\u0002\u0005U\u0013\u0001\u0002\"p_2,\"!\"1\u000f\t\u0015\rWq\u0019\b\u0005\u0003w))-\u0003\u0003\u00066\u0006\u001d\u0013\u0002BC_\u000bg\u000bQAQ8pY\u0002\u0002"
)
public final class package {
   public static Bool Bool() {
      return package$.MODULE$.Bool();
   }

   public static Field Field() {
      return package$.MODULE$.Field();
   }

   public static DivisionRing DivisionRing() {
      return package$.MODULE$.DivisionRing();
   }

   public static TruncatedDivision TruncatedDivision() {
      return package$.MODULE$.TruncatedDivision();
   }

   public static Signed Signed() {
      return package$.MODULE$.Signed();
   }

   public static Signed.Sign Sign() {
      return package$.MODULE$.Sign();
   }

   public static EuclideanRing EuclideanRing() {
      return package$.MODULE$.EuclideanRing();
   }

   public static GCDRing GCDRing() {
      return package$.MODULE$.GCDRing();
   }

   public static CommutativeRing CRing() {
      return package$.MODULE$.CRing();
   }

   public static Ring Ring() {
      return package$.MODULE$.Ring();
   }

   public static CommutativeRng CRng() {
      return package$.MODULE$.CRng();
   }

   public static Rng Rng() {
      return package$.MODULE$.Rng();
   }

   public static CommutativeRig CRig() {
      return package$.MODULE$.CRig();
   }

   public static Rig Rig() {
      return package$.MODULE$.Rig();
   }

   public static CommutativeSemiring CSemiring() {
      return package$.MODULE$.CSemiring();
   }

   public static Semiring Semiring() {
      return package$.MODULE$.Semiring();
   }

   public static MultiplicativeCommutativeGroup MultiplicativeAbGroup() {
      return package$.MODULE$.MultiplicativeAbGroup();
   }

   public static MultiplicativeGroup MultiplicativeGroup() {
      return package$.MODULE$.MultiplicativeGroup();
   }

   public static MultiplicativeCommutativeMonoid MultiplicativeCMonoid() {
      return package$.MODULE$.MultiplicativeCMonoid();
   }

   public static MultiplicativeMonoid MultiplicativeMonoid() {
      return package$.MODULE$.MultiplicativeMonoid();
   }

   public static MultiplicativeCommutativeSemigroup MultiplicativeCSemigroup() {
      return package$.MODULE$.MultiplicativeCSemigroup();
   }

   public static MultiplicativeSemigroup MultiplicativeSemigroup() {
      return package$.MODULE$.MultiplicativeSemigroup();
   }

   public static AdditiveCommutativeGroup AdditiveAbGroup() {
      return package$.MODULE$.AdditiveAbGroup();
   }

   public static AdditiveGroup AdditiveGroup() {
      return package$.MODULE$.AdditiveGroup();
   }

   public static AdditiveCommutativeMonoid AdditiveCMonoid() {
      return package$.MODULE$.AdditiveCMonoid();
   }

   public static AdditiveMonoid AdditiveMonoid() {
      return package$.MODULE$.AdditiveMonoid();
   }

   public static AdditiveCommutativeSemigroup AdditiveCSemigroup() {
      return package$.MODULE$.AdditiveCSemigroup();
   }

   public static AdditiveSemigroup AdditiveSemigroup() {
      return package$.MODULE$.AdditiveSemigroup();
   }

   public static CommutativeGroup AbGroup() {
      return package$.MODULE$.AbGroup();
   }

   public static Group Group() {
      return package$.MODULE$.Group();
   }

   public static CommutativeMonoid CMonoid() {
      return package$.MODULE$.CMonoid();
   }

   public static Monoid Monoid() {
      return package$.MODULE$.Monoid();
   }

   public static CommutativeSemigroup CSemigroup() {
      return package$.MODULE$.CSemigroup();
   }

   public static Semigroup Semigroup() {
      return package$.MODULE$.Semigroup();
   }

   public static Order Order() {
      return package$.MODULE$.Order();
   }

   public static PartialOrder PartialOrder() {
      return package$.MODULE$.PartialOrder();
   }

   public static Eq Eq() {
      return package$.MODULE$.Eq();
   }

   public static class Additive$ {
      public static final Additive$ MODULE$ = new Additive$();

      public AdditiveSemigroup apply(final Semigroup s) {
         return new AdditiveSemigroup(s) {
            private final Semigroup s$1;

            public Semigroup additive() {
               return AdditiveSemigroup.additive$(this);
            }

            public Semigroup additive$mcD$sp() {
               return AdditiveSemigroup.additive$mcD$sp$(this);
            }

            public Semigroup additive$mcF$sp() {
               return AdditiveSemigroup.additive$mcF$sp$(this);
            }

            public Semigroup additive$mcI$sp() {
               return AdditiveSemigroup.additive$mcI$sp$(this);
            }

            public Semigroup additive$mcJ$sp() {
               return AdditiveSemigroup.additive$mcJ$sp$(this);
            }

            public double plus$mcD$sp(final double x, final double y) {
               return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
            }

            public float plus$mcF$sp(final float x, final float y) {
               return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
            }

            public int plus$mcI$sp(final int x, final int y) {
               return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
            }

            public long plus$mcJ$sp(final long x, final long y) {
               return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
            }

            public Object sumN(final Object a, final int n) {
               return AdditiveSemigroup.sumN$(this, a, n);
            }

            public double sumN$mcD$sp(final double a, final int n) {
               return AdditiveSemigroup.sumN$mcD$sp$(this, a, n);
            }

            public float sumN$mcF$sp(final float a, final int n) {
               return AdditiveSemigroup.sumN$mcF$sp$(this, a, n);
            }

            public int sumN$mcI$sp(final int a, final int n) {
               return AdditiveSemigroup.sumN$mcI$sp$(this, a, n);
            }

            public long sumN$mcJ$sp(final long a, final int n) {
               return AdditiveSemigroup.sumN$mcJ$sp$(this, a, n);
            }

            public Object positiveSumN(final Object a, final int n) {
               return AdditiveSemigroup.positiveSumN$(this, a, n);
            }

            public double positiveSumN$mcD$sp(final double a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
            }

            public float positiveSumN$mcF$sp(final float a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
            }

            public int positiveSumN$mcI$sp(final int a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
            }

            public long positiveSumN$mcJ$sp(final long a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
            }

            public Option trySum(final IterableOnce as) {
               return AdditiveSemigroup.trySum$(this, as);
            }

            public Object plus(final Object x, final Object y) {
               return this.s$1.combine(x, y);
            }

            public {
               this.s$1 = s$1;
               AdditiveSemigroup.$init$(this);
            }
         };
      }

      public AdditiveCommutativeSemigroup apply(final CommutativeSemigroup s) {
         return new AdditiveCommutativeSemigroup(s) {
            private final CommutativeSemigroup s$2;

            public CommutativeSemigroup additive() {
               return AdditiveCommutativeSemigroup.additive$(this);
            }

            public CommutativeSemigroup additive$mcD$sp() {
               return AdditiveCommutativeSemigroup.additive$mcD$sp$(this);
            }

            public CommutativeSemigroup additive$mcF$sp() {
               return AdditiveCommutativeSemigroup.additive$mcF$sp$(this);
            }

            public CommutativeSemigroup additive$mcI$sp() {
               return AdditiveCommutativeSemigroup.additive$mcI$sp$(this);
            }

            public CommutativeSemigroup additive$mcJ$sp() {
               return AdditiveCommutativeSemigroup.additive$mcJ$sp$(this);
            }

            public double plus$mcD$sp(final double x, final double y) {
               return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
            }

            public float plus$mcF$sp(final float x, final float y) {
               return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
            }

            public int plus$mcI$sp(final int x, final int y) {
               return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
            }

            public long plus$mcJ$sp(final long x, final long y) {
               return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
            }

            public Object sumN(final Object a, final int n) {
               return AdditiveSemigroup.sumN$(this, a, n);
            }

            public double sumN$mcD$sp(final double a, final int n) {
               return AdditiveSemigroup.sumN$mcD$sp$(this, a, n);
            }

            public float sumN$mcF$sp(final float a, final int n) {
               return AdditiveSemigroup.sumN$mcF$sp$(this, a, n);
            }

            public int sumN$mcI$sp(final int a, final int n) {
               return AdditiveSemigroup.sumN$mcI$sp$(this, a, n);
            }

            public long sumN$mcJ$sp(final long a, final int n) {
               return AdditiveSemigroup.sumN$mcJ$sp$(this, a, n);
            }

            public Object positiveSumN(final Object a, final int n) {
               return AdditiveSemigroup.positiveSumN$(this, a, n);
            }

            public double positiveSumN$mcD$sp(final double a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
            }

            public float positiveSumN$mcF$sp(final float a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
            }

            public int positiveSumN$mcI$sp(final int a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
            }

            public long positiveSumN$mcJ$sp(final long a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
            }

            public Option trySum(final IterableOnce as) {
               return AdditiveSemigroup.trySum$(this, as);
            }

            public Object plus(final Object x, final Object y) {
               return this.s$2.combine(x, y);
            }

            public {
               this.s$2 = s$2;
               AdditiveSemigroup.$init$(this);
               AdditiveCommutativeSemigroup.$init$(this);
            }
         };
      }

      public AdditiveMonoid apply(final Monoid m) {
         return new AdditiveMonoid(m) {
            private final Monoid m$1;

            public Monoid additive() {
               return AdditiveMonoid.additive$(this);
            }

            public Monoid additive$mcD$sp() {
               return AdditiveMonoid.additive$mcD$sp$(this);
            }

            public Monoid additive$mcF$sp() {
               return AdditiveMonoid.additive$mcF$sp$(this);
            }

            public Monoid additive$mcI$sp() {
               return AdditiveMonoid.additive$mcI$sp$(this);
            }

            public Monoid additive$mcJ$sp() {
               return AdditiveMonoid.additive$mcJ$sp$(this);
            }

            public double zero$mcD$sp() {
               return AdditiveMonoid.zero$mcD$sp$(this);
            }

            public float zero$mcF$sp() {
               return AdditiveMonoid.zero$mcF$sp$(this);
            }

            public int zero$mcI$sp() {
               return AdditiveMonoid.zero$mcI$sp$(this);
            }

            public long zero$mcJ$sp() {
               return AdditiveMonoid.zero$mcJ$sp$(this);
            }

            public boolean isZero(final Object a, final Eq ev) {
               return AdditiveMonoid.isZero$(this, a, ev);
            }

            public boolean isZero$mcD$sp(final double a, final Eq ev) {
               return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
            }

            public boolean isZero$mcF$sp(final float a, final Eq ev) {
               return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
            }

            public boolean isZero$mcI$sp(final int a, final Eq ev) {
               return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
            }

            public boolean isZero$mcJ$sp(final long a, final Eq ev) {
               return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
            }

            public Object sumN(final Object a, final int n) {
               return AdditiveMonoid.sumN$(this, a, n);
            }

            public double sumN$mcD$sp(final double a, final int n) {
               return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
            }

            public float sumN$mcF$sp(final float a, final int n) {
               return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
            }

            public int sumN$mcI$sp(final int a, final int n) {
               return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
            }

            public long sumN$mcJ$sp(final long a, final int n) {
               return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
            }

            public Object sum(final IterableOnce as) {
               return AdditiveMonoid.sum$(this, as);
            }

            public double sum$mcD$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcD$sp$(this, as);
            }

            public float sum$mcF$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcF$sp$(this, as);
            }

            public int sum$mcI$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcI$sp$(this, as);
            }

            public long sum$mcJ$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcJ$sp$(this, as);
            }

            public Option trySum(final IterableOnce as) {
               return AdditiveMonoid.trySum$(this, as);
            }

            public double plus$mcD$sp(final double x, final double y) {
               return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
            }

            public float plus$mcF$sp(final float x, final float y) {
               return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
            }

            public int plus$mcI$sp(final int x, final int y) {
               return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
            }

            public long plus$mcJ$sp(final long x, final long y) {
               return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
            }

            public Object positiveSumN(final Object a, final int n) {
               return AdditiveSemigroup.positiveSumN$(this, a, n);
            }

            public double positiveSumN$mcD$sp(final double a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
            }

            public float positiveSumN$mcF$sp(final float a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
            }

            public int positiveSumN$mcI$sp(final int a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
            }

            public long positiveSumN$mcJ$sp(final long a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
            }

            public Object plus(final Object x, final Object y) {
               return this.m$1.combine(x, y);
            }

            public Object zero() {
               return this.m$1.empty();
            }

            public {
               this.m$1 = m$1;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
            }
         };
      }

      public AdditiveCommutativeMonoid apply(final CommutativeMonoid m) {
         return new AdditiveCommutativeMonoid(m) {
            private final CommutativeMonoid m$2;

            public CommutativeMonoid additive() {
               return AdditiveCommutativeMonoid.additive$(this);
            }

            public CommutativeMonoid additive$mcD$sp() {
               return AdditiveCommutativeMonoid.additive$mcD$sp$(this);
            }

            public CommutativeMonoid additive$mcF$sp() {
               return AdditiveCommutativeMonoid.additive$mcF$sp$(this);
            }

            public CommutativeMonoid additive$mcI$sp() {
               return AdditiveCommutativeMonoid.additive$mcI$sp$(this);
            }

            public CommutativeMonoid additive$mcJ$sp() {
               return AdditiveCommutativeMonoid.additive$mcJ$sp$(this);
            }

            public double zero$mcD$sp() {
               return AdditiveMonoid.zero$mcD$sp$(this);
            }

            public float zero$mcF$sp() {
               return AdditiveMonoid.zero$mcF$sp$(this);
            }

            public int zero$mcI$sp() {
               return AdditiveMonoid.zero$mcI$sp$(this);
            }

            public long zero$mcJ$sp() {
               return AdditiveMonoid.zero$mcJ$sp$(this);
            }

            public boolean isZero(final Object a, final Eq ev) {
               return AdditiveMonoid.isZero$(this, a, ev);
            }

            public boolean isZero$mcD$sp(final double a, final Eq ev) {
               return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
            }

            public boolean isZero$mcF$sp(final float a, final Eq ev) {
               return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
            }

            public boolean isZero$mcI$sp(final int a, final Eq ev) {
               return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
            }

            public boolean isZero$mcJ$sp(final long a, final Eq ev) {
               return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
            }

            public Object sumN(final Object a, final int n) {
               return AdditiveMonoid.sumN$(this, a, n);
            }

            public double sumN$mcD$sp(final double a, final int n) {
               return AdditiveMonoid.sumN$mcD$sp$(this, a, n);
            }

            public float sumN$mcF$sp(final float a, final int n) {
               return AdditiveMonoid.sumN$mcF$sp$(this, a, n);
            }

            public int sumN$mcI$sp(final int a, final int n) {
               return AdditiveMonoid.sumN$mcI$sp$(this, a, n);
            }

            public long sumN$mcJ$sp(final long a, final int n) {
               return AdditiveMonoid.sumN$mcJ$sp$(this, a, n);
            }

            public Object sum(final IterableOnce as) {
               return AdditiveMonoid.sum$(this, as);
            }

            public double sum$mcD$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcD$sp$(this, as);
            }

            public float sum$mcF$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcF$sp$(this, as);
            }

            public int sum$mcI$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcI$sp$(this, as);
            }

            public long sum$mcJ$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcJ$sp$(this, as);
            }

            public Option trySum(final IterableOnce as) {
               return AdditiveMonoid.trySum$(this, as);
            }

            public double plus$mcD$sp(final double x, final double y) {
               return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
            }

            public float plus$mcF$sp(final float x, final float y) {
               return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
            }

            public int plus$mcI$sp(final int x, final int y) {
               return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
            }

            public long plus$mcJ$sp(final long x, final long y) {
               return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
            }

            public Object positiveSumN(final Object a, final int n) {
               return AdditiveSemigroup.positiveSumN$(this, a, n);
            }

            public double positiveSumN$mcD$sp(final double a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
            }

            public float positiveSumN$mcF$sp(final float a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
            }

            public int positiveSumN$mcI$sp(final int a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
            }

            public long positiveSumN$mcJ$sp(final long a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
            }

            public Object plus(final Object x, final Object y) {
               return this.m$2.combine(x, y);
            }

            public Object zero() {
               return this.m$2.empty();
            }

            public {
               this.m$2 = m$2;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
               AdditiveCommutativeSemigroup.$init$(this);
               AdditiveCommutativeMonoid.$init$(this);
            }
         };
      }

      public AdditiveGroup apply(final Group g) {
         return new AdditiveGroup(g) {
            private final Group g$1;

            public Group additive() {
               return AdditiveGroup.additive$(this);
            }

            public Group additive$mcD$sp() {
               return AdditiveGroup.additive$mcD$sp$(this);
            }

            public Group additive$mcF$sp() {
               return AdditiveGroup.additive$mcF$sp$(this);
            }

            public Group additive$mcI$sp() {
               return AdditiveGroup.additive$mcI$sp$(this);
            }

            public Group additive$mcJ$sp() {
               return AdditiveGroup.additive$mcJ$sp$(this);
            }

            public double negate$mcD$sp(final double x) {
               return AdditiveGroup.negate$mcD$sp$(this, x);
            }

            public float negate$mcF$sp(final float x) {
               return AdditiveGroup.negate$mcF$sp$(this, x);
            }

            public int negate$mcI$sp(final int x) {
               return AdditiveGroup.negate$mcI$sp$(this, x);
            }

            public long negate$mcJ$sp(final long x) {
               return AdditiveGroup.negate$mcJ$sp$(this, x);
            }

            public double minus$mcD$sp(final double x, final double y) {
               return AdditiveGroup.minus$mcD$sp$(this, x, y);
            }

            public float minus$mcF$sp(final float x, final float y) {
               return AdditiveGroup.minus$mcF$sp$(this, x, y);
            }

            public int minus$mcI$sp(final int x, final int y) {
               return AdditiveGroup.minus$mcI$sp$(this, x, y);
            }

            public long minus$mcJ$sp(final long x, final long y) {
               return AdditiveGroup.minus$mcJ$sp$(this, x, y);
            }

            public Object sumN(final Object a, final int n) {
               return AdditiveGroup.sumN$(this, a, n);
            }

            public double sumN$mcD$sp(final double a, final int n) {
               return AdditiveGroup.sumN$mcD$sp$(this, a, n);
            }

            public float sumN$mcF$sp(final float a, final int n) {
               return AdditiveGroup.sumN$mcF$sp$(this, a, n);
            }

            public int sumN$mcI$sp(final int a, final int n) {
               return AdditiveGroup.sumN$mcI$sp$(this, a, n);
            }

            public long sumN$mcJ$sp(final long a, final int n) {
               return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
            }

            public double zero$mcD$sp() {
               return AdditiveMonoid.zero$mcD$sp$(this);
            }

            public float zero$mcF$sp() {
               return AdditiveMonoid.zero$mcF$sp$(this);
            }

            public int zero$mcI$sp() {
               return AdditiveMonoid.zero$mcI$sp$(this);
            }

            public long zero$mcJ$sp() {
               return AdditiveMonoid.zero$mcJ$sp$(this);
            }

            public boolean isZero(final Object a, final Eq ev) {
               return AdditiveMonoid.isZero$(this, a, ev);
            }

            public boolean isZero$mcD$sp(final double a, final Eq ev) {
               return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
            }

            public boolean isZero$mcF$sp(final float a, final Eq ev) {
               return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
            }

            public boolean isZero$mcI$sp(final int a, final Eq ev) {
               return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
            }

            public boolean isZero$mcJ$sp(final long a, final Eq ev) {
               return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
            }

            public Object sum(final IterableOnce as) {
               return AdditiveMonoid.sum$(this, as);
            }

            public double sum$mcD$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcD$sp$(this, as);
            }

            public float sum$mcF$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcF$sp$(this, as);
            }

            public int sum$mcI$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcI$sp$(this, as);
            }

            public long sum$mcJ$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcJ$sp$(this, as);
            }

            public Option trySum(final IterableOnce as) {
               return AdditiveMonoid.trySum$(this, as);
            }

            public double plus$mcD$sp(final double x, final double y) {
               return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
            }

            public float plus$mcF$sp(final float x, final float y) {
               return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
            }

            public int plus$mcI$sp(final int x, final int y) {
               return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
            }

            public long plus$mcJ$sp(final long x, final long y) {
               return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
            }

            public Object positiveSumN(final Object a, final int n) {
               return AdditiveSemigroup.positiveSumN$(this, a, n);
            }

            public double positiveSumN$mcD$sp(final double a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
            }

            public float positiveSumN$mcF$sp(final float a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
            }

            public int positiveSumN$mcI$sp(final int a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
            }

            public long positiveSumN$mcJ$sp(final long a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
            }

            public Object plus(final Object x, final Object y) {
               return this.g$1.combine(x, y);
            }

            public Object minus(final Object x, final Object y) {
               return this.g$1.remove(x, y);
            }

            public Object zero() {
               return this.g$1.empty();
            }

            public Object negate(final Object x) {
               return this.g$1.inverse(x);
            }

            public {
               this.g$1 = g$1;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
               AdditiveGroup.$init$(this);
            }
         };
      }

      public AdditiveCommutativeGroup apply(final CommutativeGroup g) {
         return new AdditiveCommutativeGroup(g) {
            private final CommutativeGroup g$2;

            public CommutativeGroup additive() {
               return AdditiveCommutativeGroup.additive$(this);
            }

            public CommutativeGroup additive$mcD$sp() {
               return AdditiveCommutativeGroup.additive$mcD$sp$(this);
            }

            public CommutativeGroup additive$mcF$sp() {
               return AdditiveCommutativeGroup.additive$mcF$sp$(this);
            }

            public CommutativeGroup additive$mcI$sp() {
               return AdditiveCommutativeGroup.additive$mcI$sp$(this);
            }

            public CommutativeGroup additive$mcJ$sp() {
               return AdditiveCommutativeGroup.additive$mcJ$sp$(this);
            }

            public double negate$mcD$sp(final double x) {
               return AdditiveGroup.negate$mcD$sp$(this, x);
            }

            public float negate$mcF$sp(final float x) {
               return AdditiveGroup.negate$mcF$sp$(this, x);
            }

            public int negate$mcI$sp(final int x) {
               return AdditiveGroup.negate$mcI$sp$(this, x);
            }

            public long negate$mcJ$sp(final long x) {
               return AdditiveGroup.negate$mcJ$sp$(this, x);
            }

            public double minus$mcD$sp(final double x, final double y) {
               return AdditiveGroup.minus$mcD$sp$(this, x, y);
            }

            public float minus$mcF$sp(final float x, final float y) {
               return AdditiveGroup.minus$mcF$sp$(this, x, y);
            }

            public int minus$mcI$sp(final int x, final int y) {
               return AdditiveGroup.minus$mcI$sp$(this, x, y);
            }

            public long minus$mcJ$sp(final long x, final long y) {
               return AdditiveGroup.minus$mcJ$sp$(this, x, y);
            }

            public Object sumN(final Object a, final int n) {
               return AdditiveGroup.sumN$(this, a, n);
            }

            public double sumN$mcD$sp(final double a, final int n) {
               return AdditiveGroup.sumN$mcD$sp$(this, a, n);
            }

            public float sumN$mcF$sp(final float a, final int n) {
               return AdditiveGroup.sumN$mcF$sp$(this, a, n);
            }

            public int sumN$mcI$sp(final int a, final int n) {
               return AdditiveGroup.sumN$mcI$sp$(this, a, n);
            }

            public long sumN$mcJ$sp(final long a, final int n) {
               return AdditiveGroup.sumN$mcJ$sp$(this, a, n);
            }

            public double zero$mcD$sp() {
               return AdditiveMonoid.zero$mcD$sp$(this);
            }

            public float zero$mcF$sp() {
               return AdditiveMonoid.zero$mcF$sp$(this);
            }

            public int zero$mcI$sp() {
               return AdditiveMonoid.zero$mcI$sp$(this);
            }

            public long zero$mcJ$sp() {
               return AdditiveMonoid.zero$mcJ$sp$(this);
            }

            public boolean isZero(final Object a, final Eq ev) {
               return AdditiveMonoid.isZero$(this, a, ev);
            }

            public boolean isZero$mcD$sp(final double a, final Eq ev) {
               return AdditiveMonoid.isZero$mcD$sp$(this, a, ev);
            }

            public boolean isZero$mcF$sp(final float a, final Eq ev) {
               return AdditiveMonoid.isZero$mcF$sp$(this, a, ev);
            }

            public boolean isZero$mcI$sp(final int a, final Eq ev) {
               return AdditiveMonoid.isZero$mcI$sp$(this, a, ev);
            }

            public boolean isZero$mcJ$sp(final long a, final Eq ev) {
               return AdditiveMonoid.isZero$mcJ$sp$(this, a, ev);
            }

            public Object sum(final IterableOnce as) {
               return AdditiveMonoid.sum$(this, as);
            }

            public double sum$mcD$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcD$sp$(this, as);
            }

            public float sum$mcF$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcF$sp$(this, as);
            }

            public int sum$mcI$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcI$sp$(this, as);
            }

            public long sum$mcJ$sp(final IterableOnce as) {
               return AdditiveMonoid.sum$mcJ$sp$(this, as);
            }

            public Option trySum(final IterableOnce as) {
               return AdditiveMonoid.trySum$(this, as);
            }

            public double plus$mcD$sp(final double x, final double y) {
               return AdditiveSemigroup.plus$mcD$sp$(this, x, y);
            }

            public float plus$mcF$sp(final float x, final float y) {
               return AdditiveSemigroup.plus$mcF$sp$(this, x, y);
            }

            public int plus$mcI$sp(final int x, final int y) {
               return AdditiveSemigroup.plus$mcI$sp$(this, x, y);
            }

            public long plus$mcJ$sp(final long x, final long y) {
               return AdditiveSemigroup.plus$mcJ$sp$(this, x, y);
            }

            public Object positiveSumN(final Object a, final int n) {
               return AdditiveSemigroup.positiveSumN$(this, a, n);
            }

            public double positiveSumN$mcD$sp(final double a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcD$sp$(this, a, n);
            }

            public float positiveSumN$mcF$sp(final float a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcF$sp$(this, a, n);
            }

            public int positiveSumN$mcI$sp(final int a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcI$sp$(this, a, n);
            }

            public long positiveSumN$mcJ$sp(final long a, final int n) {
               return AdditiveSemigroup.positiveSumN$mcJ$sp$(this, a, n);
            }

            public Object plus(final Object x, final Object y) {
               return this.g$2.combine(x, y);
            }

            public Object minus(final Object x, final Object y) {
               return this.g$2.remove(x, y);
            }

            public Object zero() {
               return this.g$2.empty();
            }

            public Object negate(final Object x) {
               return this.g$2.inverse(x);
            }

            public {
               this.g$2 = g$2;
               AdditiveSemigroup.$init$(this);
               AdditiveMonoid.$init$(this);
               AdditiveGroup.$init$(this);
               AdditiveCommutativeSemigroup.$init$(this);
               AdditiveCommutativeMonoid.$init$(this);
               AdditiveCommutativeGroup.$init$(this);
            }
         };
      }
   }

   public static class Multiplicative$ {
      public static final Multiplicative$ MODULE$ = new Multiplicative$();

      public MultiplicativeSemigroup apply(final Semigroup s) {
         return new MultiplicativeSemigroup(s) {
            private final Semigroup s$3;

            public Semigroup multiplicative() {
               return MultiplicativeSemigroup.multiplicative$(this);
            }

            public Semigroup multiplicative$mcD$sp() {
               return MultiplicativeSemigroup.multiplicative$mcD$sp$(this);
            }

            public Semigroup multiplicative$mcF$sp() {
               return MultiplicativeSemigroup.multiplicative$mcF$sp$(this);
            }

            public Semigroup multiplicative$mcI$sp() {
               return MultiplicativeSemigroup.multiplicative$mcI$sp$(this);
            }

            public Semigroup multiplicative$mcJ$sp() {
               return MultiplicativeSemigroup.multiplicative$mcJ$sp$(this);
            }

            public double times$mcD$sp(final double x, final double y) {
               return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
            }

            public float times$mcF$sp(final float x, final float y) {
               return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
            }

            public int times$mcI$sp(final int x, final int y) {
               return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
            }

            public long times$mcJ$sp(final long x, final long y) {
               return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
            }

            public Object pow(final Object a, final int n) {
               return MultiplicativeSemigroup.pow$(this, a, n);
            }

            public double pow$mcD$sp(final double a, final int n) {
               return MultiplicativeSemigroup.pow$mcD$sp$(this, a, n);
            }

            public float pow$mcF$sp(final float a, final int n) {
               return MultiplicativeSemigroup.pow$mcF$sp$(this, a, n);
            }

            public int pow$mcI$sp(final int a, final int n) {
               return MultiplicativeSemigroup.pow$mcI$sp$(this, a, n);
            }

            public long pow$mcJ$sp(final long a, final int n) {
               return MultiplicativeSemigroup.pow$mcJ$sp$(this, a, n);
            }

            public Object positivePow(final Object a, final int n) {
               return MultiplicativeSemigroup.positivePow$(this, a, n);
            }

            public double positivePow$mcD$sp(final double a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
            }

            public float positivePow$mcF$sp(final float a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
            }

            public int positivePow$mcI$sp(final int a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
            }

            public long positivePow$mcJ$sp(final long a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
            }

            public Option tryProduct(final IterableOnce as) {
               return MultiplicativeSemigroup.tryProduct$(this, as);
            }

            public Object times(final Object x, final Object y) {
               return this.s$3.combine(x, y);
            }

            public {
               this.s$3 = s$3;
               MultiplicativeSemigroup.$init$(this);
            }
         };
      }

      public MultiplicativeCommutativeSemigroup apply(final CommutativeSemigroup s) {
         return new MultiplicativeCommutativeSemigroup(s) {
            private final CommutativeSemigroup s$4;

            public CommutativeSemigroup multiplicative() {
               return MultiplicativeCommutativeSemigroup.multiplicative$(this);
            }

            public CommutativeSemigroup multiplicative$mcD$sp() {
               return MultiplicativeCommutativeSemigroup.multiplicative$mcD$sp$(this);
            }

            public CommutativeSemigroup multiplicative$mcF$sp() {
               return MultiplicativeCommutativeSemigroup.multiplicative$mcF$sp$(this);
            }

            public CommutativeSemigroup multiplicative$mcI$sp() {
               return MultiplicativeCommutativeSemigroup.multiplicative$mcI$sp$(this);
            }

            public CommutativeSemigroup multiplicative$mcJ$sp() {
               return MultiplicativeCommutativeSemigroup.multiplicative$mcJ$sp$(this);
            }

            public double times$mcD$sp(final double x, final double y) {
               return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
            }

            public float times$mcF$sp(final float x, final float y) {
               return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
            }

            public int times$mcI$sp(final int x, final int y) {
               return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
            }

            public long times$mcJ$sp(final long x, final long y) {
               return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
            }

            public Object pow(final Object a, final int n) {
               return MultiplicativeSemigroup.pow$(this, a, n);
            }

            public double pow$mcD$sp(final double a, final int n) {
               return MultiplicativeSemigroup.pow$mcD$sp$(this, a, n);
            }

            public float pow$mcF$sp(final float a, final int n) {
               return MultiplicativeSemigroup.pow$mcF$sp$(this, a, n);
            }

            public int pow$mcI$sp(final int a, final int n) {
               return MultiplicativeSemigroup.pow$mcI$sp$(this, a, n);
            }

            public long pow$mcJ$sp(final long a, final int n) {
               return MultiplicativeSemigroup.pow$mcJ$sp$(this, a, n);
            }

            public Object positivePow(final Object a, final int n) {
               return MultiplicativeSemigroup.positivePow$(this, a, n);
            }

            public double positivePow$mcD$sp(final double a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
            }

            public float positivePow$mcF$sp(final float a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
            }

            public int positivePow$mcI$sp(final int a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
            }

            public long positivePow$mcJ$sp(final long a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
            }

            public Option tryProduct(final IterableOnce as) {
               return MultiplicativeSemigroup.tryProduct$(this, as);
            }

            public Object times(final Object x, final Object y) {
               return this.s$4.combine(x, y);
            }

            public {
               this.s$4 = s$4;
               MultiplicativeSemigroup.$init$(this);
               MultiplicativeCommutativeSemigroup.$init$(this);
            }
         };
      }

      public MultiplicativeMonoid apply(final Monoid m) {
         return new MultiplicativeMonoid(m) {
            private final Monoid m$3;

            public Monoid multiplicative() {
               return MultiplicativeMonoid.multiplicative$(this);
            }

            public Monoid multiplicative$mcD$sp() {
               return MultiplicativeMonoid.multiplicative$mcD$sp$(this);
            }

            public Monoid multiplicative$mcF$sp() {
               return MultiplicativeMonoid.multiplicative$mcF$sp$(this);
            }

            public Monoid multiplicative$mcI$sp() {
               return MultiplicativeMonoid.multiplicative$mcI$sp$(this);
            }

            public Monoid multiplicative$mcJ$sp() {
               return MultiplicativeMonoid.multiplicative$mcJ$sp$(this);
            }

            public double one$mcD$sp() {
               return MultiplicativeMonoid.one$mcD$sp$(this);
            }

            public float one$mcF$sp() {
               return MultiplicativeMonoid.one$mcF$sp$(this);
            }

            public int one$mcI$sp() {
               return MultiplicativeMonoid.one$mcI$sp$(this);
            }

            public long one$mcJ$sp() {
               return MultiplicativeMonoid.one$mcJ$sp$(this);
            }

            public boolean isOne(final Object a, final Eq ev) {
               return MultiplicativeMonoid.isOne$(this, a, ev);
            }

            public boolean isOne$mcD$sp(final double a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
            }

            public boolean isOne$mcF$sp(final float a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
            }

            public boolean isOne$mcI$sp(final int a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
            }

            public boolean isOne$mcJ$sp(final long a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
            }

            public Object pow(final Object a, final int n) {
               return MultiplicativeMonoid.pow$(this, a, n);
            }

            public double pow$mcD$sp(final double a, final int n) {
               return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
            }

            public float pow$mcF$sp(final float a, final int n) {
               return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
            }

            public int pow$mcI$sp(final int a, final int n) {
               return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
            }

            public long pow$mcJ$sp(final long a, final int n) {
               return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
            }

            public Object product(final IterableOnce as) {
               return MultiplicativeMonoid.product$(this, as);
            }

            public double product$mcD$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcD$sp$(this, as);
            }

            public float product$mcF$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcF$sp$(this, as);
            }

            public int product$mcI$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcI$sp$(this, as);
            }

            public long product$mcJ$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcJ$sp$(this, as);
            }

            public Option tryProduct(final IterableOnce as) {
               return MultiplicativeMonoid.tryProduct$(this, as);
            }

            public double times$mcD$sp(final double x, final double y) {
               return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
            }

            public float times$mcF$sp(final float x, final float y) {
               return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
            }

            public int times$mcI$sp(final int x, final int y) {
               return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
            }

            public long times$mcJ$sp(final long x, final long y) {
               return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
            }

            public Object positivePow(final Object a, final int n) {
               return MultiplicativeSemigroup.positivePow$(this, a, n);
            }

            public double positivePow$mcD$sp(final double a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
            }

            public float positivePow$mcF$sp(final float a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
            }

            public int positivePow$mcI$sp(final int a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
            }

            public long positivePow$mcJ$sp(final long a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
            }

            public Object times(final Object x, final Object y) {
               return this.m$3.combine(x, y);
            }

            public Object one() {
               return this.m$3.empty();
            }

            public {
               this.m$3 = m$3;
               MultiplicativeSemigroup.$init$(this);
               MultiplicativeMonoid.$init$(this);
            }
         };
      }

      public MultiplicativeCommutativeMonoid apply(final CommutativeMonoid m) {
         return new MultiplicativeCommutativeMonoid(m) {
            private final CommutativeMonoid m$4;

            public CommutativeMonoid multiplicative() {
               return MultiplicativeCommutativeMonoid.multiplicative$(this);
            }

            public CommutativeMonoid multiplicative$mcD$sp() {
               return MultiplicativeCommutativeMonoid.multiplicative$mcD$sp$(this);
            }

            public CommutativeMonoid multiplicative$mcF$sp() {
               return MultiplicativeCommutativeMonoid.multiplicative$mcF$sp$(this);
            }

            public CommutativeMonoid multiplicative$mcI$sp() {
               return MultiplicativeCommutativeMonoid.multiplicative$mcI$sp$(this);
            }

            public CommutativeMonoid multiplicative$mcJ$sp() {
               return MultiplicativeCommutativeMonoid.multiplicative$mcJ$sp$(this);
            }

            public double one$mcD$sp() {
               return MultiplicativeMonoid.one$mcD$sp$(this);
            }

            public float one$mcF$sp() {
               return MultiplicativeMonoid.one$mcF$sp$(this);
            }

            public int one$mcI$sp() {
               return MultiplicativeMonoid.one$mcI$sp$(this);
            }

            public long one$mcJ$sp() {
               return MultiplicativeMonoid.one$mcJ$sp$(this);
            }

            public boolean isOne(final Object a, final Eq ev) {
               return MultiplicativeMonoid.isOne$(this, a, ev);
            }

            public boolean isOne$mcD$sp(final double a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
            }

            public boolean isOne$mcF$sp(final float a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
            }

            public boolean isOne$mcI$sp(final int a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
            }

            public boolean isOne$mcJ$sp(final long a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
            }

            public Object pow(final Object a, final int n) {
               return MultiplicativeMonoid.pow$(this, a, n);
            }

            public double pow$mcD$sp(final double a, final int n) {
               return MultiplicativeMonoid.pow$mcD$sp$(this, a, n);
            }

            public float pow$mcF$sp(final float a, final int n) {
               return MultiplicativeMonoid.pow$mcF$sp$(this, a, n);
            }

            public int pow$mcI$sp(final int a, final int n) {
               return MultiplicativeMonoid.pow$mcI$sp$(this, a, n);
            }

            public long pow$mcJ$sp(final long a, final int n) {
               return MultiplicativeMonoid.pow$mcJ$sp$(this, a, n);
            }

            public Object product(final IterableOnce as) {
               return MultiplicativeMonoid.product$(this, as);
            }

            public double product$mcD$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcD$sp$(this, as);
            }

            public float product$mcF$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcF$sp$(this, as);
            }

            public int product$mcI$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcI$sp$(this, as);
            }

            public long product$mcJ$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcJ$sp$(this, as);
            }

            public Option tryProduct(final IterableOnce as) {
               return MultiplicativeMonoid.tryProduct$(this, as);
            }

            public double times$mcD$sp(final double x, final double y) {
               return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
            }

            public float times$mcF$sp(final float x, final float y) {
               return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
            }

            public int times$mcI$sp(final int x, final int y) {
               return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
            }

            public long times$mcJ$sp(final long x, final long y) {
               return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
            }

            public Object positivePow(final Object a, final int n) {
               return MultiplicativeSemigroup.positivePow$(this, a, n);
            }

            public double positivePow$mcD$sp(final double a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
            }

            public float positivePow$mcF$sp(final float a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
            }

            public int positivePow$mcI$sp(final int a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
            }

            public long positivePow$mcJ$sp(final long a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
            }

            public Object times(final Object x, final Object y) {
               return this.m$4.combine(x, y);
            }

            public Object one() {
               return this.m$4.empty();
            }

            public {
               this.m$4 = m$4;
               MultiplicativeSemigroup.$init$(this);
               MultiplicativeMonoid.$init$(this);
               MultiplicativeCommutativeSemigroup.$init$(this);
               MultiplicativeCommutativeMonoid.$init$(this);
            }
         };
      }

      public MultiplicativeGroup apply(final Group g) {
         return new MultiplicativeGroup(g) {
            private final Group g$3;

            public Group multiplicative() {
               return MultiplicativeGroup.multiplicative$(this);
            }

            public Group multiplicative$mcD$sp() {
               return MultiplicativeGroup.multiplicative$mcD$sp$(this);
            }

            public Group multiplicative$mcF$sp() {
               return MultiplicativeGroup.multiplicative$mcF$sp$(this);
            }

            public Group multiplicative$mcI$sp() {
               return MultiplicativeGroup.multiplicative$mcI$sp$(this);
            }

            public Group multiplicative$mcJ$sp() {
               return MultiplicativeGroup.multiplicative$mcJ$sp$(this);
            }

            public double reciprocal$mcD$sp(final double x) {
               return MultiplicativeGroup.reciprocal$mcD$sp$(this, x);
            }

            public float reciprocal$mcF$sp(final float x) {
               return MultiplicativeGroup.reciprocal$mcF$sp$(this, x);
            }

            public int reciprocal$mcI$sp(final int x) {
               return MultiplicativeGroup.reciprocal$mcI$sp$(this, x);
            }

            public long reciprocal$mcJ$sp(final long x) {
               return MultiplicativeGroup.reciprocal$mcJ$sp$(this, x);
            }

            public double div$mcD$sp(final double x, final double y) {
               return MultiplicativeGroup.div$mcD$sp$(this, x, y);
            }

            public float div$mcF$sp(final float x, final float y) {
               return MultiplicativeGroup.div$mcF$sp$(this, x, y);
            }

            public int div$mcI$sp(final int x, final int y) {
               return MultiplicativeGroup.div$mcI$sp$(this, x, y);
            }

            public long div$mcJ$sp(final long x, final long y) {
               return MultiplicativeGroup.div$mcJ$sp$(this, x, y);
            }

            public Object pow(final Object a, final int n) {
               return MultiplicativeGroup.pow$(this, a, n);
            }

            public double pow$mcD$sp(final double a, final int n) {
               return MultiplicativeGroup.pow$mcD$sp$(this, a, n);
            }

            public float pow$mcF$sp(final float a, final int n) {
               return MultiplicativeGroup.pow$mcF$sp$(this, a, n);
            }

            public int pow$mcI$sp(final int a, final int n) {
               return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
            }

            public long pow$mcJ$sp(final long a, final int n) {
               return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
            }

            public double one$mcD$sp() {
               return MultiplicativeMonoid.one$mcD$sp$(this);
            }

            public float one$mcF$sp() {
               return MultiplicativeMonoid.one$mcF$sp$(this);
            }

            public int one$mcI$sp() {
               return MultiplicativeMonoid.one$mcI$sp$(this);
            }

            public long one$mcJ$sp() {
               return MultiplicativeMonoid.one$mcJ$sp$(this);
            }

            public boolean isOne(final Object a, final Eq ev) {
               return MultiplicativeMonoid.isOne$(this, a, ev);
            }

            public boolean isOne$mcD$sp(final double a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
            }

            public boolean isOne$mcF$sp(final float a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
            }

            public boolean isOne$mcI$sp(final int a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
            }

            public boolean isOne$mcJ$sp(final long a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
            }

            public Object product(final IterableOnce as) {
               return MultiplicativeMonoid.product$(this, as);
            }

            public double product$mcD$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcD$sp$(this, as);
            }

            public float product$mcF$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcF$sp$(this, as);
            }

            public int product$mcI$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcI$sp$(this, as);
            }

            public long product$mcJ$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcJ$sp$(this, as);
            }

            public Option tryProduct(final IterableOnce as) {
               return MultiplicativeMonoid.tryProduct$(this, as);
            }

            public double times$mcD$sp(final double x, final double y) {
               return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
            }

            public float times$mcF$sp(final float x, final float y) {
               return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
            }

            public int times$mcI$sp(final int x, final int y) {
               return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
            }

            public long times$mcJ$sp(final long x, final long y) {
               return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
            }

            public Object positivePow(final Object a, final int n) {
               return MultiplicativeSemigroup.positivePow$(this, a, n);
            }

            public double positivePow$mcD$sp(final double a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
            }

            public float positivePow$mcF$sp(final float a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
            }

            public int positivePow$mcI$sp(final int a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
            }

            public long positivePow$mcJ$sp(final long a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
            }

            public Object times(final Object x, final Object y) {
               return this.g$3.combine(x, y);
            }

            public Object div(final Object x, final Object y) {
               return this.g$3.remove(x, y);
            }

            public Object one() {
               return this.g$3.empty();
            }

            public Object reciprocal(final Object x) {
               return this.g$3.inverse(x);
            }

            public {
               this.g$3 = g$3;
               MultiplicativeSemigroup.$init$(this);
               MultiplicativeMonoid.$init$(this);
               MultiplicativeGroup.$init$(this);
            }
         };
      }

      public MultiplicativeCommutativeGroup apply(final CommutativeGroup g) {
         return new MultiplicativeCommutativeGroup(g) {
            private final CommutativeGroup g$4;

            public CommutativeGroup multiplicative() {
               return MultiplicativeCommutativeGroup.multiplicative$(this);
            }

            public CommutativeGroup multiplicative$mcD$sp() {
               return MultiplicativeCommutativeGroup.multiplicative$mcD$sp$(this);
            }

            public CommutativeGroup multiplicative$mcF$sp() {
               return MultiplicativeCommutativeGroup.multiplicative$mcF$sp$(this);
            }

            public CommutativeGroup multiplicative$mcI$sp() {
               return MultiplicativeCommutativeGroup.multiplicative$mcI$sp$(this);
            }

            public CommutativeGroup multiplicative$mcJ$sp() {
               return MultiplicativeCommutativeGroup.multiplicative$mcJ$sp$(this);
            }

            public double reciprocal$mcD$sp(final double x) {
               return MultiplicativeGroup.reciprocal$mcD$sp$(this, x);
            }

            public float reciprocal$mcF$sp(final float x) {
               return MultiplicativeGroup.reciprocal$mcF$sp$(this, x);
            }

            public int reciprocal$mcI$sp(final int x) {
               return MultiplicativeGroup.reciprocal$mcI$sp$(this, x);
            }

            public long reciprocal$mcJ$sp(final long x) {
               return MultiplicativeGroup.reciprocal$mcJ$sp$(this, x);
            }

            public double div$mcD$sp(final double x, final double y) {
               return MultiplicativeGroup.div$mcD$sp$(this, x, y);
            }

            public float div$mcF$sp(final float x, final float y) {
               return MultiplicativeGroup.div$mcF$sp$(this, x, y);
            }

            public int div$mcI$sp(final int x, final int y) {
               return MultiplicativeGroup.div$mcI$sp$(this, x, y);
            }

            public long div$mcJ$sp(final long x, final long y) {
               return MultiplicativeGroup.div$mcJ$sp$(this, x, y);
            }

            public Object pow(final Object a, final int n) {
               return MultiplicativeGroup.pow$(this, a, n);
            }

            public double pow$mcD$sp(final double a, final int n) {
               return MultiplicativeGroup.pow$mcD$sp$(this, a, n);
            }

            public float pow$mcF$sp(final float a, final int n) {
               return MultiplicativeGroup.pow$mcF$sp$(this, a, n);
            }

            public int pow$mcI$sp(final int a, final int n) {
               return MultiplicativeGroup.pow$mcI$sp$(this, a, n);
            }

            public long pow$mcJ$sp(final long a, final int n) {
               return MultiplicativeGroup.pow$mcJ$sp$(this, a, n);
            }

            public double one$mcD$sp() {
               return MultiplicativeMonoid.one$mcD$sp$(this);
            }

            public float one$mcF$sp() {
               return MultiplicativeMonoid.one$mcF$sp$(this);
            }

            public int one$mcI$sp() {
               return MultiplicativeMonoid.one$mcI$sp$(this);
            }

            public long one$mcJ$sp() {
               return MultiplicativeMonoid.one$mcJ$sp$(this);
            }

            public boolean isOne(final Object a, final Eq ev) {
               return MultiplicativeMonoid.isOne$(this, a, ev);
            }

            public boolean isOne$mcD$sp(final double a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcD$sp$(this, a, ev);
            }

            public boolean isOne$mcF$sp(final float a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcF$sp$(this, a, ev);
            }

            public boolean isOne$mcI$sp(final int a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcI$sp$(this, a, ev);
            }

            public boolean isOne$mcJ$sp(final long a, final Eq ev) {
               return MultiplicativeMonoid.isOne$mcJ$sp$(this, a, ev);
            }

            public Object product(final IterableOnce as) {
               return MultiplicativeMonoid.product$(this, as);
            }

            public double product$mcD$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcD$sp$(this, as);
            }

            public float product$mcF$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcF$sp$(this, as);
            }

            public int product$mcI$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcI$sp$(this, as);
            }

            public long product$mcJ$sp(final IterableOnce as) {
               return MultiplicativeMonoid.product$mcJ$sp$(this, as);
            }

            public Option tryProduct(final IterableOnce as) {
               return MultiplicativeMonoid.tryProduct$(this, as);
            }

            public double times$mcD$sp(final double x, final double y) {
               return MultiplicativeSemigroup.times$mcD$sp$(this, x, y);
            }

            public float times$mcF$sp(final float x, final float y) {
               return MultiplicativeSemigroup.times$mcF$sp$(this, x, y);
            }

            public int times$mcI$sp(final int x, final int y) {
               return MultiplicativeSemigroup.times$mcI$sp$(this, x, y);
            }

            public long times$mcJ$sp(final long x, final long y) {
               return MultiplicativeSemigroup.times$mcJ$sp$(this, x, y);
            }

            public Object positivePow(final Object a, final int n) {
               return MultiplicativeSemigroup.positivePow$(this, a, n);
            }

            public double positivePow$mcD$sp(final double a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcD$sp$(this, a, n);
            }

            public float positivePow$mcF$sp(final float a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcF$sp$(this, a, n);
            }

            public int positivePow$mcI$sp(final int a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcI$sp$(this, a, n);
            }

            public long positivePow$mcJ$sp(final long a, final int n) {
               return MultiplicativeSemigroup.positivePow$mcJ$sp$(this, a, n);
            }

            public Object times(final Object x, final Object y) {
               return this.g$4.combine(x, y);
            }

            public Object div(final Object x, final Object y) {
               return this.g$4.remove(x, y);
            }

            public Object one() {
               return this.g$4.empty();
            }

            public Object reciprocal(final Object x) {
               return this.g$4.inverse(x);
            }

            public {
               this.g$4 = g$4;
               MultiplicativeSemigroup.$init$(this);
               MultiplicativeMonoid.$init$(this);
               MultiplicativeGroup.$init$(this);
               MultiplicativeCommutativeSemigroup.$init$(this);
               MultiplicativeCommutativeMonoid.$init$(this);
               MultiplicativeCommutativeGroup.$init$(this);
            }
         };
      }
   }
}
