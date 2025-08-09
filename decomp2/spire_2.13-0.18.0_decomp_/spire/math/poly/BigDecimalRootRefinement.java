package spire.math.poly;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple5;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.math.Polynomial;
import spire.math.Rational;
import spire.math.Rational$;
import spire.math.package$;
import spire.std.package;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019MaaBAw\u0003_\u0004\u0015Q \u0005\u000b\u0005S\u0001!Q3A\u0005\u0002\t-\u0002BCCk\u0001\tE\t\u0015!\u0003\u0003.!QQ\u0011\u001a\u0001\u0003\u0016\u0004%\t!b6\t\u0015\u0015e\u0007A!E!\u0002\u0013)Y\rC\u0004\u0003H\u0001!\t!b7\t\u000f\u0015\u0005\b\u0001\"\u0001\u0003b\"9Q1\u001d\u0001\u0005\u0002\u0015\u0015\bbBCr\u0001\u0011\u0005Q\u0011\u001e\u0005\b\u000b[\u0004A\u0011ACx\u0011%\u0019\u0019\u0001AA\u0001\n\u0003))\u0010C\u0005\u0004\u0012\u0001\t\n\u0011\"\u0001\u0006|\"I1\u0011\u0006\u0001\u0012\u0002\u0013\u0005Qq \u0005\n\u0007k\u0001\u0011\u0011!C!\u0007oA\u0011b!\u0012\u0001\u0003\u0003%\tA!=\t\u0013\r\u001d\u0003!!A\u0005\u0002\u0019\r\u0001\"CB+\u0001\u0005\u0005I\u0011IB,\u0011%\u0019)\u0007AA\u0001\n\u000319\u0001C\u0005\u0004r\u0001\t\t\u0011\"\u0011\u0007\f!I1q\u000f\u0001\u0002\u0002\u0013\u00053\u0011\u0010\u0005\n\u0007w\u0002\u0011\u0011!C!\u0007{B\u0011ba \u0001\u0003\u0003%\tEb\u0004\b\u0011\tM\u0012q\u001eE\u0001\u0005k1\u0001\"!<\u0002p\"\u0005!q\u0007\u0005\b\u0005\u000f:B\u0011\u0001B%\u0011\u001d\u0011Ye\u0006C\u0001\u0005\u001bBqAa\u0013\u0018\t\u0003\u0011y\u0007C\u0004\u0003L]!\tA!!\t\u0013\t]uC1A\u0005\f\te\u0005\u0002\u0003Bb/\u0001\u0006IAa'\t\u0013\t\u0015wC1A\u0005\n\t\u001d\u0007\u0002\u0003Bh/\u0001\u0006IA!3\u0007\u000f\tEw#!\t\u0003T\"9!q\t\u0011\u0005\u0002\tUgABBh/\u0001\u001b\t\u000e\u0003\u0006\u0004T\n\u0012)\u001a!C\u0001\u0005CD!b!6#\u0005#\u0005\u000b\u0011\u0002B]\u0011\u001d\u00119E\tC\u0001\u0007/D\u0011ba\u0001#\u0003\u0003%\ta!8\t\u0013\rE!%%A\u0005\u0002\rM\u0001\"CB\u001bE\u0005\u0005I\u0011IB\u001c\u0011%\u0019)EIA\u0001\n\u0003\u0011\t\u0010C\u0005\u0004H\t\n\t\u0011\"\u0001\u0004b\"I1Q\u000b\u0012\u0002\u0002\u0013\u00053q\u000b\u0005\n\u0007K\u0012\u0013\u0011!C\u0001\u0007KD\u0011b!\u001d#\u0003\u0003%\te!;\t\u0013\r]$%!A\u0005B\re\u0004\"CB>E\u0005\u0005I\u0011IB?\u0011%\u0019yHIA\u0001\n\u0003\u001aioB\u0005\u0005\u0014]\t\t\u0011#\u0001\u0005\u0016\u0019I1qZ\f\u0002\u0002#\u0005Aq\u0003\u0005\b\u0005\u000f\u0012D\u0011\u0001C\u0013\u0011%\u0019YHMA\u0001\n\u000b\u001ai\bC\u0005\u0003LI\n\t\u0011\"!\u0005(!IA1\u0006\u001a\u0002\u0002\u0013\u0005EQ\u0006\u0005\n\ts\u0011\u0014\u0011!C\u0005\tw1aA!8\u0018\u0001\n}\u0007B\u0003B2q\tU\r\u0011\"\u0001\u0003b\"Q!1\u001d\u001d\u0003\u0012\u0003\u0006IA!/\t\u0015\t\u0015\bH!f\u0001\n\u0003\u0011\t\u000f\u0003\u0006\u0003hb\u0012\t\u0012)A\u0005\u0005sC!B!\u001c9\u0005+\u0007I\u0011\u0001Bq\u0011)\u0011I\u000f\u000fB\tB\u0003%!\u0011\u0018\u0005\u000b\u0005WD$Q3A\u0005\u0002\t\u0005\bB\u0003Bwq\tE\t\u0015!\u0003\u0003:\"Q!q\u001e\u001d\u0003\u0016\u0004%\tA!=\t\u0015\tM\bH!E!\u0002\u0013\u0011Y\bC\u0004\u0003Ha\"\tA!>\t\u0013\r\r\u0001(!A\u0005\u0002\r\u0015\u0001\"CB\tqE\u0005I\u0011AB\n\u0011%\u0019I\u0003OI\u0001\n\u0003\u0019\u0019\u0002C\u0005\u0004,a\n\n\u0011\"\u0001\u0004\u0014!I1Q\u0006\u001d\u0012\u0002\u0013\u000511\u0003\u0005\n\u0007_A\u0014\u0013!C\u0001\u0007cA\u0011b!\u000e9\u0003\u0003%\tea\u000e\t\u0013\r\u0015\u0003(!A\u0005\u0002\tE\b\"CB$q\u0005\u0005I\u0011AB%\u0011%\u0019)\u0006OA\u0001\n\u0003\u001a9\u0006C\u0005\u0004fa\n\t\u0011\"\u0001\u0004h!I1\u0011\u000f\u001d\u0002\u0002\u0013\u000531\u000f\u0005\n\u0007oB\u0014\u0011!C!\u0007sB\u0011ba\u001f9\u0003\u0003%\te! \t\u0013\r}\u0004(!A\u0005B\r\u0005u!\u0003C\"/\u0005\u0005\t\u0012\u0001C#\r%\u0011inFA\u0001\u0012\u0003!9\u0005C\u0004\u0003HQ#\t\u0001b\u0014\t\u0013\rmD+!A\u0005F\ru\u0004\"\u0003B&)\u0006\u0005I\u0011\u0011C)\u0011%!Y\u0003VA\u0001\n\u0003#i\u0006C\u0005\u0005:Q\u000b\t\u0011\"\u0003\u0005<\u001911QQ\fA\u0007\u000fC!Ba\u0019[\u0005+\u0007I\u0011ABE\u0011)\u0011\u0019O\u0017B\tB\u0003%!Q\r\u0005\u000b\u0005[R&Q3A\u0005\u0002\t\u0005\bB\u0003Bu5\nE\t\u0015!\u0003\u0003:\"9!q\t.\u0005\u0002\r-\u0005\"CB\u00025\u0006\u0005I\u0011ABJ\u0011%\u0019\tBWI\u0001\n\u0003\u0019I\nC\u0005\u0004*i\u000b\n\u0011\"\u0001\u0004\u0014!I1Q\u0007.\u0002\u0002\u0013\u00053q\u0007\u0005\n\u0007\u000bR\u0016\u0011!C\u0001\u0005cD\u0011ba\u0012[\u0003\u0003%\ta!(\t\u0013\rU#,!A\u0005B\r]\u0003\"CB35\u0006\u0005I\u0011ABQ\u0011%\u0019\tHWA\u0001\n\u0003\u001a)\u000bC\u0005\u0004xi\u000b\t\u0011\"\u0011\u0004z!I11\u0010.\u0002\u0002\u0013\u00053Q\u0010\u0005\n\u0007\u007fR\u0016\u0011!C!\u0007S;\u0011\u0002\"\u001b\u0018\u0003\u0003E\t\u0001b\u001b\u0007\u0013\r\u0015u#!A\t\u0002\u00115\u0004b\u0002B$[\u0012\u0005AQ\u000f\u0005\n\u0007wj\u0017\u0011!C#\u0007{B\u0011Ba\u0013n\u0003\u0003%\t\tb\u001e\t\u0013\u0011-R.!A\u0005\u0002\u0012u\u0004\"\u0003C\u001d[\u0006\u0005I\u0011\u0002C\u001e\r\u0019\u0019ik\u0006!\u00040\"Q!1M:\u0003\u0016\u0004%\tA!9\t\u0015\t\r8O!E!\u0002\u0013\u0011I\f\u0003\u0006\u0003nM\u0014)\u001a!C\u0001\u0007\u0013C!B!;t\u0005#\u0005\u000b\u0011\u0002B3\u0011\u001d\u00119e\u001dC\u0001\u0007cC\u0011ba\u0001t\u0003\u0003%\ta!/\t\u0013\rE1/%A\u0005\u0002\rM\u0001\"CB\u0015gF\u0005I\u0011ABM\u0011%\u0019)d]A\u0001\n\u0003\u001a9\u0004C\u0005\u0004FM\f\t\u0011\"\u0001\u0003r\"I1qI:\u0002\u0002\u0013\u00051q\u0018\u0005\n\u0007+\u001a\u0018\u0011!C!\u0007/B\u0011b!\u001at\u0003\u0003%\taa1\t\u0013\rE4/!A\u0005B\r\u001d\u0007\"CB<g\u0006\u0005I\u0011IB=\u0011%\u0019Yh]A\u0001\n\u0003\u001ai\bC\u0005\u0004\u0000M\f\t\u0011\"\u0011\u0004L\u001eIA\u0011R\f\u0002\u0002#\u0005A1\u0012\u0004\n\u0007[;\u0012\u0011!E\u0001\t\u001bC\u0001Ba\u0012\u0002\u000e\u0011\u0005A\u0011\u0013\u0005\u000b\u0007w\ni!!A\u0005F\ru\u0004B\u0003B&\u0003\u001b\t\t\u0011\"!\u0005\u0014\"QA1FA\u0007\u0003\u0003%\t\t\"'\t\u0015\u0011e\u0012QBA\u0001\n\u0013!YD\u0002\u0004\u0004r^\u000151\u001f\u0005\f\u0005G\nIB!f\u0001\n\u0003\u0019I\tC\u0006\u0003d\u0006e!\u0011#Q\u0001\n\t\u0015\u0004b\u0003B7\u00033\u0011)\u001a!C\u0001\u0007\u0013C1B!;\u0002\u001a\tE\t\u0015!\u0003\u0003f!A!qIA\r\t\u0003\u0019)\u0010\u0003\u0006\u0004\u0004\u0005e\u0011\u0011!C\u0001\u0007{D!b!\u0005\u0002\u001aE\u0005I\u0011ABM\u0011)\u0019I#!\u0007\u0012\u0002\u0013\u00051\u0011\u0014\u0005\u000b\u0007k\tI\"!A\u0005B\r]\u0002BCB#\u00033\t\t\u0011\"\u0001\u0003r\"Q1qIA\r\u0003\u0003%\t\u0001b\u0001\t\u0015\rU\u0013\u0011DA\u0001\n\u0003\u001a9\u0006\u0003\u0006\u0004f\u0005e\u0011\u0011!C\u0001\t\u000fA!b!\u001d\u0002\u001a\u0005\u0005I\u0011\tC\u0006\u0011)\u00199(!\u0007\u0002\u0002\u0013\u00053\u0011\u0010\u0005\u000b\u0007w\nI\"!A\u0005B\ru\u0004BCB@\u00033\t\t\u0011\"\u0011\u0005\u0010\u001dIA\u0011U\f\u0002\u0002#\u0005A1\u0015\u0004\n\u0007c<\u0012\u0011!E\u0001\tKC\u0001Ba\u0012\u0002@\u0011\u0005A\u0011\u0016\u0005\u000b\u0007w\ny$!A\u0005F\ru\u0004B\u0003B&\u0003\u007f\t\t\u0011\"!\u0005,\"QA1FA \u0003\u0003%\t\t\"-\t\u0015\u0011e\u0012qHA\u0001\n\u0013!YDB\u0004\u0005:^\t\t\u0003b/\t\u0011\t\u001d\u00131\nC\u0001\t{C\u0001\"!=\u0002L\u0019\u0005A\u0011\u0019\u0005\t\t\u0007\fYE\"\u0001\u0005F\"AA1ZA&\r\u0003!i\r\u0003\u0005\u0005R\u0006-c\u0011\u0001Cj\u0011!!9.a\u0013\u0007\u0002\u0011e\u0007\u0002\u0003Ci\u0003\u00172\t\u0001\"8\t\u0011\u0011]\u00171\nD\u0001\tC4a\u0001b:\u0018\u0001\u0012%\bbCAy\u0003;\u0012)\u001a!C\u0001\t\u0003D1\u0002b;\u0002^\tE\t\u0015!\u0003\u0003T!Y!\u0011PA/\u0005+\u0007I\u0011\u0001By\u0011-!i/!\u0018\u0003\u0012\u0003\u0006IAa\u001f\t\u0015\t\u001d\u0013Q\fC\u0001\u0003_$y\u000f\u0003\u0005\u0005D\u0006uC\u0011\u0001C|\u0011!!Y-!\u0018\u0005\u0002\u0011m\b\u0002\u0003Ci\u0003;\"\t\u0001b@\t\u0011\u0011]\u0017Q\fC\u0001\u000b\u0007A\u0001\u0002\"5\u0002^\u0011\u0005Qq\u0001\u0005\t\t/\fi\u0006\"\u0001\u0006\f!Q11AA/\u0003\u0003%\t!b\u0004\t\u0015\rE\u0011QLI\u0001\n\u0003))\u0002\u0003\u0006\u0004*\u0005u\u0013\u0013!C\u0001\u0007cA!b!\u000e\u0002^\u0005\u0005I\u0011IB\u001c\u0011)\u0019)%!\u0018\u0002\u0002\u0013\u0005!\u0011\u001f\u0005\u000b\u0007\u000f\ni&!A\u0005\u0002\u0015e\u0001BCB+\u0003;\n\t\u0011\"\u0011\u0004X!Q1QMA/\u0003\u0003%\t!\"\b\t\u0015\rE\u0014QLA\u0001\n\u0003*\t\u0003\u0003\u0006\u0004x\u0005u\u0013\u0011!C!\u0007sB!ba\u001f\u0002^\u0005\u0005I\u0011IB?\u0011)\u0019y(!\u0018\u0002\u0002\u0013\u0005SQE\u0004\n\u000bW:\u0012\u0011!E\u0001\u000b[2\u0011\u0002b:\u0018\u0003\u0003E\t!b\u001c\t\u0011\t\u001d\u0013q\u0012C\u0001\u000bgB!ba\u001f\u0002\u0010\u0006\u0005IQIB?\u0011)\u0011Y%a$\u0002\u0002\u0013\u0005UQ\u000f\u0005\u000b\u000bw\ny)%A\u0005\u0002\rE\u0002B\u0003C\u0016\u0003\u001f\u000b\t\u0011\"!\u0006~!aQQQAH#\u0003%\t!a<\u00042!QA\u0011HAH\u0003\u0003%I\u0001b\u000f\u0007\r\u0015%r\u0003QC\u0016\u0011-\t\t0a(\u0003\u0016\u0004%\t\u0001\"1\t\u0017\u0011-\u0018q\u0014B\tB\u0003%!1\u000b\u0005\f\u0005\u0017\u000byJ!f\u0001\n\u0003)i\u0003C\u0006\u00060\u0005}%\u0011#Q\u0001\n\t5\u0005B\u0003B$\u0003?#\t!a<\u00062!AA1YAP\t\u0003)I\u0004\u0003\u0005\u0005L\u0006}E\u0011AC\u001f\u0011!!\t.a(\u0005\u0002\u0015\u0005\u0003\u0002\u0003Cl\u0003?#\t!\"\u0012\t\u0011\u0011E\u0017q\u0014C\u0001\u000b\u0013B\u0001\u0002b6\u0002 \u0012\u0005QQ\n\u0005\u000b\u0007\u0007\ty*!A\u0005\u0002\u0015E\u0003BCB\t\u0003?\u000b\n\u0011\"\u0001\u0006\u0016!Q1\u0011FAP#\u0003%\t!b\u0016\t\u0015\rU\u0012qTA\u0001\n\u0003\u001a9\u0004\u0003\u0006\u0004F\u0005}\u0015\u0011!C\u0001\u0005cD!ba\u0012\u0002 \u0006\u0005I\u0011AC.\u0011)\u0019)&a(\u0002\u0002\u0013\u00053q\u000b\u0005\u000b\u0007K\ny*!A\u0005\u0002\u0015}\u0003BCB9\u0003?\u000b\t\u0011\"\u0011\u0006d!Q1qOAP\u0003\u0003%\te!\u001f\t\u0015\rm\u0014qTA\u0001\n\u0003\u001ai\b\u0003\u0006\u0004\u0000\u0005}\u0015\u0011!C!\u000bO:\u0011\"b\"\u0018\u0003\u0003E\t!\"#\u0007\u0013\u0015%r#!A\t\u0002\u0015-\u0005\u0002\u0003B$\u0003#$\t!b$\t\u0015\rm\u0014\u0011[A\u0001\n\u000b\u001ai\b\u0003\u0006\u0003L\u0005E\u0017\u0011!CA\u000b#C!\"b\u001f\u0002RF\u0005I\u0011AC,\u0011)!Y#!5\u0002\u0002\u0013\u0005Uq\u0013\u0005\r\u000b\u000b\u000b\t.%A\u0005\u0002\u0005=Xq\u000b\u0005\u000b\ts\t\t.!A\u0005\n\u0011m\u0002bBCP/\u0011%Q\u0011\u0015\u0005\b\u000b?;B\u0011BCY\u0011%)\tmFI\u0001\n\u0013\u0019\t\u0004C\u0005\u0003L]\t\t\u0011\"!\u0006D\"IA1F\f\u0002\u0002\u0013\u0005UQ\u001a\u0005\n\ts9\u0012\u0011!C\u0005\tw\u0011\u0001DQ5h\t\u0016\u001c\u0017.\\1m%>|GOU3gS:,W.\u001a8u\u0015\u0011\t\t0a=\u0002\tA|G.\u001f\u0006\u0005\u0003k\f90\u0001\u0003nCRD'BAA}\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019r\u0001AA\u0000\u0005\u0017\u0011\t\u0002\u0005\u0003\u0003\u0002\t\u001dQB\u0001B\u0002\u0015\t\u0011)!A\u0003tG\u0006d\u0017-\u0003\u0003\u0003\n\t\r!AB!osJ+g\r\u0005\u0003\u0003\u0002\t5\u0011\u0002\u0002B\b\u0005\u0007\u0011q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0003\u0014\t\rb\u0002\u0002B\u000b\u0005?qAAa\u0006\u0003\u001e5\u0011!\u0011\u0004\u0006\u0005\u00057\tY0\u0001\u0004=e>|GOP\u0005\u0003\u0005\u000bIAA!\t\u0003\u0004\u00059\u0001/Y2lC\u001e,\u0017\u0002\u0002B\u0013\u0005O\u0011AbU3sS\u0006d\u0017N_1cY\u0016TAA!\t\u0003\u0004\u000591m\u001c8uKb$XC\u0001B\u0017!\u0011\u0011y#a\u0013\u000f\u0007\tEb#\u0004\u0002\u0002p\u0006A\")[4EK\u000eLW.\u00197S_>$(+\u001a4j]\u0016lWM\u001c;\u0011\u0007\tErcE\u0003\u0018\u0003\u007f\u0014I\u0004\u0005\u0003\u0003<\t\u0015SB\u0001B\u001f\u0015\u0011\u0011yD!\u0011\u0002\u0005%|'B\u0001B\"\u0003\u0011Q\u0017M^1\n\t\t\u0015\"QH\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\tU\u0012!B1qa2LH\u0003\u0003B(\u0005#\u0012\tGa\u001b\u0011\u0007\tE\u0002\u0001C\u0004\u0002rf\u0001\rAa\u0015\u0011\r\tU#q\u000bB.\u001b\t\t\u00190\u0003\u0003\u0003Z\u0005M(A\u0003)pYftw.\\5bYB!!1\u0003B/\u0013\u0011\u0011yFa\n\u0003\u0015\tKw\rR3dS6\fG\u000eC\u0004\u0003de\u0001\rA!\u001a\u0002\u00151|w/\u001a:C_VtG\r\u0005\u0003\u0003V\t\u001d\u0014\u0002\u0002B5\u0003g\u0014\u0001BU1uS>t\u0017\r\u001c\u0005\b\u0005[J\u0002\u0019\u0001B3\u0003))\b\u000f]3s\u0005>,h\u000e\u001a\u000b\u000b\u0005\u001f\u0012\tHa\u001d\u0003v\t]\u0004bBAy5\u0001\u0007!1\u000b\u0005\b\u0005GR\u0002\u0019\u0001B3\u0011\u001d\u0011iG\u0007a\u0001\u0005KBqA!\u001f\u001b\u0001\u0004\u0011Y(A\u0003tG\u0006dW\r\u0005\u0003\u0003\u0002\tu\u0014\u0002\u0002B@\u0005\u0007\u00111!\u00138u))\u0011yEa!\u0003\u0006\n\u001d%\u0011\u0012\u0005\b\u0003c\\\u0002\u0019\u0001B*\u0011\u001d\u0011\u0019g\u0007a\u0001\u0005KBqA!\u001c\u001c\u0001\u0004\u0011)\u0007C\u0004\u0003\fn\u0001\rA!$\u0002\u00055\u001c\u0007\u0003\u0002BH\u0005'k!A!%\u000b\t\u0005U(\u0011I\u0005\u0005\u0005+\u0013\tJA\u0006NCRD7i\u001c8uKb$\u0018\u0001\u0005&CS\u001e$UmY5nC2|%\u000fZ3s+\t\u0011YJ\u0005\u0004\u0003\u001e\n\u0005&Q\u0018\u0004\u0007\u0005?;\u0002Aa'\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\r\t\r&1\u0017B]\u001d\u0011\u0011)Ka,\u000f\t\t\u001d&1\u0016\b\u0005\u0005/\u0011I+\u0003\u0002\u0002z&!!QVA|\u0003\u001d\tGnZ3ce\u0006LAA!\t\u00032*!!QVA|\u0013\u0011\u0011)La.\u0003\u000b=\u0013H-\u001a:\u000b\t\t\u0005\"\u0011\u0017\t\u0005\u0005\u001f\u0013Y,\u0003\u0003\u0003`\tE\u0005C\u0002BR\u0005\u007f\u0013I,\u0003\u0003\u0003B\n]&AB*jO:,G-A\tK\u0005&<G)Z2j[\u0006dwJ\u001d3fe\u0002\n\u0001BY5ugJ\"WmY\u000b\u0003\u0005\u0013\u0004BA!\u0001\u0003L&!!Q\u001aB\u0002\u0005\u0019!u.\u001e2mK\u0006I!-\u001b;te\u0011,7\r\t\u0002\u000e\u0003B\u0004(o\u001c=j[\u0006$\u0018n\u001c8\u0014\u0007\u0001\ny\u0010\u0006\u0002\u0003XB\u0019!\u0011\u001c\u0011\u000e\u0003]Is\u0001\t\u001d[g\n\nIBA\u0004C_VtG-\u001a3\u0014\u000fa\u00129Na\u0003\u0003\u0012U\u0011!\u0011X\u0001\fY><XM\u001d\"pk:$\u0007%A\bm_^,'OQ8v]\u00124\u0016\r\\;f\u0003Aawn^3s\u0005>,h\u000e\u001a,bYV,\u0007%A\u0006vaB,'OQ8v]\u0012\u0004\u0013aD;qa\u0016\u0014(i\\;oIZ\u000bG.^3\u0002!U\u0004\b/\u001a:C_VtGMV1mk\u0016\u0004\u0013!\u00018\u0016\u0005\tm\u0014A\u00018!)1\u00119P!?\u0003|\nu(q`B\u0001!\r\u0011I\u000e\u000f\u0005\b\u0005G\u001a\u0005\u0019\u0001B]\u0011\u001d\u0011)o\u0011a\u0001\u0005sCqA!\u001cD\u0001\u0004\u0011I\fC\u0004\u0003l\u000e\u0003\rA!/\t\u000f\t=8\t1\u0001\u0003|\u0005!1m\u001c9z)1\u00119pa\u0002\u0004\n\r-1QBB\b\u0011%\u0011\u0019\u0007\u0012I\u0001\u0002\u0004\u0011I\fC\u0005\u0003f\u0012\u0003\n\u00111\u0001\u0003:\"I!Q\u000e#\u0011\u0002\u0003\u0007!\u0011\u0018\u0005\n\u0005W$\u0005\u0013!a\u0001\u0005sC\u0011Ba<E!\u0003\u0005\rAa\u001f\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u00111Q\u0003\u0016\u0005\u0005s\u001b9b\u000b\u0002\u0004\u001aA!11DB\u0013\u001b\t\u0019iB\u0003\u0003\u0004 \r\u0005\u0012!C;oG\",7m[3e\u0015\u0011\u0019\u0019Ca\u0001\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0004(\ru!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU*\"aa\r+\t\tm4qC\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\re\u0002\u0003BB\u001e\u0007\u0003j!a!\u0010\u000b\t\r}\"\u0011I\u0001\u0005Y\u0006tw-\u0003\u0003\u0004D\ru\"AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\r-3\u0011\u000b\t\u0005\u0005\u0003\u0019i%\u0003\u0003\u0004P\t\r!aA!os\"I11\u000b'\u0002\u0002\u0003\u0007!1P\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\re\u0003CBB.\u0007C\u001aY%\u0004\u0002\u0004^)!1q\fB\u0002\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0007G\u001aiF\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BB5\u0007_\u0002BA!\u0001\u0004l%!1Q\u000eB\u0002\u0005\u001d\u0011un\u001c7fC:D\u0011ba\u0015O\u0003\u0003\u0005\raa\u0013\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0007s\u0019)\bC\u0005\u0004T=\u000b\t\u00111\u0001\u0003|\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0003|\u0005AAo\\*ue&tw\r\u0006\u0002\u0004:\u00051Q-];bYN$Ba!\u001b\u0004\u0004\"I11\u000b*\u0002\u0002\u0003\u000711\n\u0002\f\u0005>,h\u000eZ3e\u0019\u00164GoE\u0004[\u0005/\u0014YA!\u0005\u0016\u0005\t\u0015DCBBG\u0007\u001f\u001b\t\nE\u0002\u0003ZjCqAa\u0019`\u0001\u0004\u0011)\u0007C\u0004\u0003n}\u0003\rA!/\u0015\r\r55QSBL\u0011%\u0011\u0019\u0007\u0019I\u0001\u0002\u0004\u0011)\u0007C\u0005\u0003n\u0001\u0004\n\u00111\u0001\u0003:V\u001111\u0014\u0016\u0005\u0005K\u001a9\u0002\u0006\u0003\u0004L\r}\u0005\"CB*K\u0006\u0005\t\u0019\u0001B>)\u0011\u0019Iga)\t\u0013\rMs-!AA\u0002\r-C\u0003BB\u001d\u0007OC\u0011ba\u0015i\u0003\u0003\u0005\rAa\u001f\u0015\t\r%41\u0016\u0005\n\u0007'Z\u0017\u0011!a\u0001\u0007\u0017\u0012ABQ8v]\u0012,GMU5hQR\u001cra\u001dBl\u0005\u0017\u0011\t\u0002\u0006\u0004\u00044\u000eU6q\u0017\t\u0004\u00053\u001c\bb\u0002B2q\u0002\u0007!\u0011\u0018\u0005\b\u0005[B\b\u0019\u0001B3)\u0019\u0019\u0019la/\u0004>\"I!1M=\u0011\u0002\u0003\u0007!\u0011\u0018\u0005\n\u0005[J\b\u0013!a\u0001\u0005K\"Baa\u0013\u0004B\"I11\u000b@\u0002\u0002\u0003\u0007!1\u0010\u000b\u0005\u0007S\u001a)\r\u0003\u0006\u0004T\u0005\u0005\u0011\u0011!a\u0001\u0007\u0017\"Ba!\u000f\u0004J\"Q11KA\u0002\u0003\u0003\u0005\rAa\u001f\u0015\t\r%4Q\u001a\u0005\u000b\u0007'\nI!!AA\u0002\r-#!C#yC\u000e$(k\\8u'\u001d\u0011#q\u001bB\u0006\u0005#\tAA]8pi\u0006)!o\\8uAQ!1\u0011\\Bn!\r\u0011IN\t\u0005\b\u0007',\u0003\u0019\u0001B])\u0011\u0019Ina8\t\u0013\rMg\u0005%AA\u0002\teF\u0003BB&\u0007GD\u0011ba\u0015+\u0003\u0003\u0005\rAa\u001f\u0015\t\r%4q\u001d\u0005\n\u0007'b\u0013\u0011!a\u0001\u0007\u0017\"Ba!\u000f\u0004l\"I11K\u0017\u0002\u0002\u0003\u0007!1\u0010\u000b\u0005\u0007S\u001ay\u000fC\u0005\u0004TA\n\t\u00111\u0001\u0004L\tIQK\u001c2pk:$W\rZ\n\t\u00033\u00119Na\u0003\u0003\u0012Q11q_B}\u0007w\u0004BA!7\u0002\u001a!A!1MA\u0012\u0001\u0004\u0011)\u0007\u0003\u0005\u0003n\u0005\r\u0002\u0019\u0001B3)\u0019\u00199pa@\u0005\u0002!Q!1MA\u0013!\u0003\u0005\rA!\u001a\t\u0015\t5\u0014Q\u0005I\u0001\u0002\u0004\u0011)\u0007\u0006\u0003\u0004L\u0011\u0015\u0001BCB*\u0003_\t\t\u00111\u0001\u0003|Q!1\u0011\u000eC\u0005\u0011)\u0019\u0019&a\r\u0002\u0002\u0003\u000711\n\u000b\u0005\u0007s!i\u0001\u0003\u0006\u0004T\u0005U\u0012\u0011!a\u0001\u0005w\"Ba!\u001b\u0005\u0012!Q11KA\u001e\u0003\u0003\u0005\raa\u0013\u0002\u0013\u0015C\u0018m\u0019;S_>$\bc\u0001BmeM)!\u0007\"\u0007\u0003:AAA1\u0004C\u0011\u0005s\u001bI.\u0004\u0002\u0005\u001e)!Aq\u0004B\u0002\u0003\u001d\u0011XO\u001c;j[\u0016LA\u0001b\t\u0005\u001e\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0015\u0005\u0011UA\u0003BBm\tSAqaa56\u0001\u0004\u0011I,A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0011=BQ\u0007\t\u0007\u0005\u0003!\tD!/\n\t\u0011M\"1\u0001\u0002\u0007\u001fB$\u0018n\u001c8\t\u0013\u0011]b'!AA\u0002\re\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011AQ\b\t\u0005\u0007w!y$\u0003\u0003\u0005B\ru\"AB(cU\u0016\u001cG/A\u0004C_VtG-\u001a3\u0011\u0007\teGkE\u0003U\t\u0013\u0012I\u0004\u0005\t\u0005\u001c\u0011-#\u0011\u0018B]\u0005s\u0013ILa\u001f\u0003x&!AQ\nC\u000f\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\u000e\u000b\u0003\t\u000b\"BBa>\u0005T\u0011UCq\u000bC-\t7BqAa\u0019X\u0001\u0004\u0011I\fC\u0004\u0003f^\u0003\rA!/\t\u000f\t5t\u000b1\u0001\u0003:\"9!1^,A\u0002\te\u0006b\u0002Bx/\u0002\u0007!1\u0010\u000b\u0005\t?\"9\u0007\u0005\u0004\u0003\u0002\u0011EB\u0011\r\t\u000f\u0005\u0003!\u0019G!/\u0003:\ne&\u0011\u0018B>\u0013\u0011!)Ga\u0001\u0003\rQ+\b\u000f\\36\u0011%!9\u0004WA\u0001\u0002\u0004\u001190A\u0006C_VtG-\u001a3MK\u001a$\bc\u0001Bm[N)Q\u000eb\u001c\u0003:AQA1\u0004C9\u0005K\u0012Il!$\n\t\u0011MDQ\u0004\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014DC\u0001C6)\u0019\u0019i\t\"\u001f\u0005|!9!1\r9A\u0002\t\u0015\u0004b\u0002B7a\u0002\u0007!\u0011\u0018\u000b\u0005\t\u007f\"9\t\u0005\u0004\u0003\u0002\u0011EB\u0011\u0011\t\t\u0005\u0003!\u0019I!\u001a\u0003:&!AQ\u0011B\u0002\u0005\u0019!V\u000f\u001d7fe!IAqG9\u0002\u0002\u0003\u00071QR\u0001\r\u0005>,h\u000eZ3e%&<\u0007\u000e\u001e\t\u0005\u00053\fia\u0005\u0004\u0002\u000e\u0011=%\u0011\b\t\u000b\t7!\tH!/\u0003f\rMFC\u0001CF)\u0019\u0019\u0019\f\"&\u0005\u0018\"A!1MA\n\u0001\u0004\u0011I\f\u0003\u0005\u0003n\u0005M\u0001\u0019\u0001B3)\u0011!Y\nb(\u0011\r\t\u0005A\u0011\u0007CO!!\u0011\t\u0001b!\u0003:\n\u0015\u0004B\u0003C\u001c\u0003+\t\t\u00111\u0001\u00044\u0006IQK\u001c2pk:$W\r\u001a\t\u0005\u00053\fyd\u0005\u0004\u0002@\u0011\u001d&\u0011\b\t\u000b\t7!\tH!\u001a\u0003f\r]HC\u0001CR)\u0019\u00199\u0010\",\u00050\"A!1MA#\u0001\u0004\u0011)\u0007\u0003\u0005\u0003n\u0005\u0015\u0003\u0019\u0001B3)\u0011!\u0019\fb.\u0011\r\t\u0005A\u0011\u0007C[!!\u0011\t\u0001b!\u0003f\t\u0015\u0004B\u0003C\u001c\u0003\u000f\n\t\u00111\u0001\u0004x\n!\u0012\t\u001d9s_bLW.\u0019;j_:\u001cuN\u001c;fqR\u001cB!a\u0013\u0002\u0000R\u0011Aq\u0018\t\u0005\u00053\fY%\u0006\u0002\u0003T\u00051q-\u001a;FaN$BAa\u001f\u0005H\"AA\u0011ZA)\u0001\u0004\u0011I,A\u0001y\u0003%)g/\u00197Fq\u0006\u001cG\u000f\u0006\u0003\u0003:\u0012=\u0007\u0002\u0003Ce\u0003'\u0002\rA!/\u0002\u000b\u0019dwn\u001c:\u0015\t\teFQ\u001b\u0005\t\t\u0013\f)\u00061\u0001\u0003f\u0005!1-Z5m)\u0011\u0011I\fb7\t\u0011\u0011%\u0017q\u000ba\u0001\u0005K\"BA!/\u0005`\"AA\u0011ZA-\u0001\u0004\u0011I\f\u0006\u0003\u0003:\u0012\r\b\u0002\u0003Ce\u00037\u0002\rA!/*\r\u0005-\u0013QLAP\u0005=\t%m]8mkR,7i\u001c8uKb$8\u0003CA/\t\u007f\u0013YA!\u0005\u0002\u000bA|G.\u001f\u0011\u0002\rM\u001c\u0017\r\\3!)\u0019!\t\u0010b=\u0005vB!!\u0011\\A/\u0011!\t\t0a\u001aA\u0002\tM\u0003B\u0003B=\u0003O\u0002\n\u00111\u0001\u0003|Q!!1\u0010C}\u0011!!I-!\u001bA\u0002\teF\u0003\u0002B]\t{D\u0001\u0002\"3\u0002l\u0001\u0007!\u0011\u0018\u000b\u0005\u0005s+\t\u0001\u0003\u0005\u0005J\u00065\u0004\u0019\u0001B3)\u0011\u0011I,\"\u0002\t\u0011\u0011%\u0017q\u000ea\u0001\u0005K\"BA!/\u0006\n!AA\u0011ZA9\u0001\u0004\u0011I\f\u0006\u0003\u0003:\u00165\u0001\u0002\u0003Ce\u0003g\u0002\rA!/\u0015\r\u0011EX\u0011CC\n\u0011)\t\t0!\u001e\u0011\u0002\u0003\u0007!1\u000b\u0005\u000b\u0005s\n)\b%AA\u0002\tmTCAC\fU\u0011\u0011\u0019fa\u0006\u0015\t\r-S1\u0004\u0005\u000b\u0007'\ny(!AA\u0002\tmD\u0003BB5\u000b?A!ba\u0015\u0002\u0004\u0006\u0005\t\u0019AB&)\u0011\u0019I$b\t\t\u0015\rM\u0013QQA\u0001\u0002\u0004\u0011Y\b\u0006\u0003\u0004j\u0015\u001d\u0002BCB*\u0003\u0017\u000b\t\u00111\u0001\u0004L\ty!+\u001a7bi&4XmQ8oi\u0016DHo\u0005\u0005\u0002 \u0012}&1\u0002B\t+\t\u0011i)A\u0002nG\u0002\"b!b\r\u00066\u0015]\u0002\u0003\u0002Bm\u0003?C\u0001\"!=\u0002*\u0002\u0007!1\u000b\u0005\u000b\u0005\u0017\u000bI\u000b%AA\u0002\t5E\u0003\u0002B>\u000bwA\u0001\u0002\"3\u0002,\u0002\u0007!\u0011\u0018\u000b\u0005\u0005s+y\u0004\u0003\u0005\u0005J\u00065\u0006\u0019\u0001B])\u0011\u0011I,b\u0011\t\u0011\u0011%\u0017q\u0016a\u0001\u0005K\"BA!/\u0006H!AA\u0011ZAY\u0001\u0004\u0011)\u0007\u0006\u0003\u0003:\u0016-\u0003\u0002\u0003Ce\u0003g\u0003\rA!/\u0015\t\teVq\n\u0005\t\t\u0013\f)\f1\u0001\u0003:R1Q1GC*\u000b+B!\"!=\u00028B\u0005\t\u0019\u0001B*\u0011)\u0011Y)a.\u0011\u0002\u0003\u0007!QR\u000b\u0003\u000b3RCA!$\u0004\u0018Q!11JC/\u0011)\u0019\u0019&!1\u0002\u0002\u0003\u0007!1\u0010\u000b\u0005\u0007S*\t\u0007\u0003\u0006\u0004T\u0005\u0015\u0017\u0011!a\u0001\u0007\u0017\"Ba!\u000f\u0006f!Q11KAd\u0003\u0003\u0005\rAa\u001f\u0015\t\r%T\u0011\u000e\u0005\u000b\u0007'\ni-!AA\u0002\r-\u0013aD!cg>dW\u000f^3D_:$X\r\u001f;\u0011\t\te\u0017qR\n\u0007\u0003\u001f+\tH!\u000f\u0011\u0015\u0011mA\u0011\u000fB*\u0005w\"\t\u0010\u0006\u0002\u0006nQ1A\u0011_C<\u000bsB\u0001\"!=\u0002\u0016\u0002\u0007!1\u000b\u0005\u000b\u0005s\n)\n%AA\u0002\tm\u0014aD1qa2LH\u0005Z3gCVdG\u000f\n\u001a\u0015\t\u0015}T1\u0011\t\u0007\u0005\u0003!\t$\"!\u0011\u0011\t\u0005A1\u0011B*\u0005wB!\u0002b\u000e\u0002\u001a\u0006\u0005\t\u0019\u0001Cy\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005y!+\u001a7bi&4XmQ8oi\u0016DH\u000f\u0005\u0003\u0003Z\u0006E7CBAi\u000b\u001b\u0013I\u0004\u0005\u0006\u0005\u001c\u0011E$1\u000bBG\u000bg!\"!\"#\u0015\r\u0015MR1SCK\u0011!\t\t0a6A\u0002\tM\u0003B\u0003BF\u0003/\u0004\n\u00111\u0001\u0003\u000eR!Q\u0011TCO!\u0019\u0011\t\u0001\"\r\u0006\u001cBA!\u0011\u0001CB\u0005'\u0012i\t\u0003\u0006\u00058\u0005m\u0017\u0011!a\u0001\u000bg\t1!U%S)1\u00119.b)\u0006&\u0016\u001dV\u0011VCW\u0011!\u0011I#!9A\u0002\u0011}\u0006\u0002\u0003B2\u0003C\u0004\rA!\u001a\t\u0011\t5\u0014\u0011\u001da\u0001\u0005KB\u0001\"b+\u0002b\u0002\u0007!\u0011X\u0001\u0003Y\nD\u0001\"b,\u0002b\u0002\u0007!\u0011X\u0001\u0003k\n$bBa6\u00064\u0016UVqWC]\u000bw+i\f\u0003\u0005\u0003*\u0005\r\b\u0019\u0001C`\u0011!\u0011\u0019'a9A\u0002\te\u0006\u0002\u0003Bs\u0003G\u0004\rA!/\t\u0011\t5\u00141\u001da\u0001\u0005sC\u0001Ba;\u0002d\u0002\u0007!\u0011\u0018\u0005\u000b\u000b\u007f\u000b\u0019\u000f%AA\u0002\tm\u0014A\u000181\u00035\t\u0016J\u0015\u0013eK\u001a\fW\u000f\u001c;%mQ1!qJCc\u000b\u000fD\u0001B!\u000b\u0002h\u0002\u0007!Q\u0006\u0005\t\u000b\u0013\f9\u000f1\u0001\u0006L\u0006i\u0011\r\u001d9s_bLW.\u0019;j_:\u00042Aa\f!)\u0011)y-b5\u0011\r\t\u0005A\u0011GCi!!\u0011\t\u0001b!\u0003.\u0015-\u0007B\u0003C\u001c\u0003S\f\t\u00111\u0001\u0003P\u0005A1m\u001c8uKb$\b%\u0006\u0002\u0006L\u0006q\u0011\r\u001d9s_bLW.\u0019;j_:\u0004CC\u0002B(\u000b;,y\u000eC\u0004\u0003*\u0015\u0001\rA!\f\t\u000f\u0015%W\u00011\u0001\u0006L\u0006\u0001\u0012\r\u001d9s_bLW.\u0019;f-\u0006dW/Z\u0001\u0007e\u00164\u0017N\\3\u0015\t\t=Sq\u001d\u0005\b\u0005s:\u0001\u0019\u0001B>)\u0011\u0011y%b;\t\u000f\t-\u0005\u00021\u0001\u0003\u000e\u0006\u0019\"/\u001a4j]\u0016\f\u0005\u000f\u001d:pq&l\u0017\r^5p]R!Q1ZCy\u0011\u001d)\u00190\u0003a\u0001\u0005[\t1a\u0019;y)\u0019\u0011y%b>\u0006z\"I!\u0011\u0006\u0006\u0011\u0002\u0003\u0007!Q\u0006\u0005\n\u000b\u0013T\u0001\u0013!a\u0001\u000b\u0017,\"!\"@+\t\t52qC\u000b\u0003\r\u0003QC!b3\u0004\u0018Q!11\nD\u0003\u0011%\u0019\u0019fDA\u0001\u0002\u0004\u0011Y\b\u0006\u0003\u0004j\u0019%\u0001\"CB*#\u0005\u0005\t\u0019AB&)\u0011\u0019ID\"\u0004\t\u0013\rM##!AA\u0002\tmD\u0003BB5\r#A\u0011ba\u0015\u0016\u0003\u0003\u0005\raa\u0013"
)
public class BigDecimalRootRefinement implements Product, Serializable {
   private final ApproximationContext context;
   private final Approximation approximation;

   public static Option unapply(final BigDecimalRootRefinement x$0) {
      return BigDecimalRootRefinement$.MODULE$.unapply(x$0);
   }

   public static BigDecimalRootRefinement apply(final ApproximationContext context, final Approximation approximation) {
      return BigDecimalRootRefinement$.MODULE$.apply(context, approximation);
   }

   public static BigDecimalRootRefinement apply(final Polynomial poly, final Rational lowerBound, final Rational upperBound, final MathContext mc) {
      return BigDecimalRootRefinement$.MODULE$.apply(poly, lowerBound, upperBound, mc);
   }

   public static BigDecimalRootRefinement apply(final Polynomial poly, final Rational lowerBound, final Rational upperBound, final int scale) {
      return BigDecimalRootRefinement$.MODULE$.apply(poly, lowerBound, upperBound, scale);
   }

   public static BigDecimalRootRefinement apply(final Polynomial poly, final Rational lowerBound, final Rational upperBound) {
      return BigDecimalRootRefinement$.MODULE$.apply(poly, lowerBound, upperBound);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ApproximationContext context() {
      return this.context;
   }

   public Approximation approximation() {
      return this.approximation;
   }

   public BigDecimal approximateValue() {
      Approximation var2 = this.approximation();
      BigDecimal var1;
      if (var2 instanceof ExactRoot) {
         ExactRoot var3 = (ExactRoot)var2;
         BigDecimal root = var3.root();
         var1 = this.context().floor(root);
      } else if (var2 instanceof Bounded) {
         Bounded var5 = (Bounded)var2;
         BigDecimal lb = var5.lowerBound();
         var1 = this.context().ceil(lb);
      } else if (var2 instanceof BoundedLeft) {
         BoundedLeft var7 = (BoundedLeft)var2;
         BigDecimal ub = var7.upperBound();
         var1 = this.context().floor(ub);
      } else if (var2 instanceof BoundedRight) {
         BoundedRight var9 = (BoundedRight)var2;
         BigDecimal lb = var9.lowerBound();
         var1 = this.context().ceil(lb);
      } else {
         if (!(var2 instanceof Unbounded)) {
            throw new MatchError(var2);
         }

         Unbounded var11 = (Unbounded)var2;
         Rational lb = var11.lowerBound();
         var1 = this.context().ceil(lb);
      }

      return var1;
   }

   public BigDecimalRootRefinement refine(final int scale) {
      ApproximationContext var3 = this.context();
      BigDecimalRootRefinement var2;
      if (var3 instanceof AbsoluteContext) {
         AbsoluteContext var4 = (AbsoluteContext)var3;
         int oldScale = var4.scale();
         if (oldScale >= scale) {
            var2 = this;
            return var2;
         }
      }

      AbsoluteContext newContext = BigDecimalRootRefinement.AbsoluteContext$.MODULE$.apply(this.context().poly(), scale);
      var2 = new BigDecimalRootRefinement(newContext, this.refineApproximation(newContext));
      return var2;
   }

   public BigDecimalRootRefinement refine(final MathContext mc) {
      ApproximationContext var3 = this.context();
      BigDecimalRootRefinement var2;
      if (var3 instanceof RelativeContext) {
         RelativeContext var4 = (RelativeContext)var3;
         MathContext oldmc = var4.mc();
         if (oldmc.getPrecision() >= mc.getPrecision()) {
            var2 = this;
            return var2;
         }
      }

      RelativeContext newContext = BigDecimalRootRefinement.RelativeContext$.MODULE$.apply(this.context().poly(), mc);
      var2 = new BigDecimalRootRefinement(newContext, this.refineApproximation(newContext));
      return var2;
   }

   public Approximation refineApproximation(final ApproximationContext ctx) {
      Approximation var3 = this.approximation();
      Approximation var2;
      if (var3 instanceof ExactRoot) {
         var2 = this.approximation();
      } else if (var3 instanceof Bounded) {
         Bounded var4 = (Bounded)var3;
         BigDecimal lb = var4.lowerBound();
         BigDecimal lby = var4.lowerBoundValue();
         BigDecimal ub = var4.upperBound();
         BigDecimal uby = var4.upperBoundValue();
         int n = var4.n();
         var2 = BigDecimalRootRefinement$.MODULE$.spire$math$poly$BigDecimalRootRefinement$$QIR(ctx, lb, lby, ub, uby, n);
      } else if (var3 instanceof BoundedLeft) {
         BoundedLeft var10 = (BoundedLeft)var3;
         Rational lb = var10.lowerBound();
         BigDecimal ubApprox = var10.upperBound();
         BigDecimal lbApprox = ctx.ceil(lb);
         Rational ub = Rational$.MODULE$.apply(new scala.math.BigDecimal(ubApprox, MathContext.UNLIMITED));
         var2 = BigDecimalRootRefinement$.MODULE$.spire$math$poly$BigDecimalRootRefinement$$QIR(ctx, lb, ub, lbApprox, ubApprox);
      } else if (var3 instanceof BoundedRight) {
         BoundedRight var15 = (BoundedRight)var3;
         BigDecimal lbApprox = var15.lowerBound();
         Rational ub = var15.upperBound();
         Rational lb = Rational$.MODULE$.apply(new scala.math.BigDecimal(lbApprox, MathContext.UNLIMITED));
         BigDecimal ubApprox = ctx.floor(ub);
         var2 = BigDecimalRootRefinement$.MODULE$.spire$math$poly$BigDecimalRootRefinement$$QIR(ctx, lb, ub, lbApprox, ubApprox);
      } else {
         if (!(var3 instanceof Unbounded)) {
            throw new MatchError(var3);
         }

         Unbounded var20 = (Unbounded)var3;
         Rational lb = var20.lowerBound();
         Rational ub = var20.upperBound();
         BigDecimal lbApprox = ctx.ceil(lb);
         BigDecimal ubApprox = ctx.floor(ub);
         var2 = BigDecimalRootRefinement$.MODULE$.spire$math$poly$BigDecimalRootRefinement$$QIR(ctx, lb, ub, lbApprox, ubApprox);
      }

      return var2;
   }

   public BigDecimalRootRefinement copy(final ApproximationContext context, final Approximation approximation) {
      return new BigDecimalRootRefinement(context, approximation);
   }

   public ApproximationContext copy$default$1() {
      return this.context();
   }

   public Approximation copy$default$2() {
      return this.approximation();
   }

   public String productPrefix() {
      return "BigDecimalRootRefinement";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.context();
            break;
         case 1:
            var10000 = this.approximation();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof BigDecimalRootRefinement;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "context";
            break;
         case 1:
            var10000 = "approximation";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof BigDecimalRootRefinement) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     BigDecimalRootRefinement var4 = (BigDecimalRootRefinement)x$1;
                     ApproximationContext var10000 = this.context();
                     ApproximationContext var5 = var4.context();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Approximation var7 = this.approximation();
                     Approximation var6 = var4.approximation();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public BigDecimalRootRefinement(final ApproximationContext context, final Approximation approximation) {
      this.context = context;
      this.approximation = approximation;
      Product.$init$(this);
   }

   public abstract static class Approximation {
   }

   public static class ExactRoot extends Approximation implements Product, Serializable {
      private final BigDecimal root;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public BigDecimal root() {
         return this.root;
      }

      public ExactRoot copy(final BigDecimal root) {
         return new ExactRoot(root);
      }

      public BigDecimal copy$default$1() {
         return this.root();
      }

      public String productPrefix() {
         return "ExactRoot";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.root();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ExactRoot;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "root";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof ExactRoot) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  ExactRoot var4 = (ExactRoot)x$1;
                  if (BoxesRunTime.equalsNumNum(this.root(), var4.root()) && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public ExactRoot(final BigDecimal root) {
         this.root = root;
         Product.$init$(this);
      }
   }

   public static class ExactRoot$ extends AbstractFunction1 implements Serializable {
      public static final ExactRoot$ MODULE$ = new ExactRoot$();

      public final String toString() {
         return "ExactRoot";
      }

      public ExactRoot apply(final BigDecimal root) {
         return new ExactRoot(root);
      }

      public Option unapply(final ExactRoot x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.root()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ExactRoot$.class);
      }
   }

   public static class Bounded extends Approximation implements Product, Serializable {
      private final BigDecimal lowerBound;
      private final BigDecimal lowerBoundValue;
      private final BigDecimal upperBound;
      private final BigDecimal upperBoundValue;
      private final int n;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public BigDecimal lowerBound() {
         return this.lowerBound;
      }

      public BigDecimal lowerBoundValue() {
         return this.lowerBoundValue;
      }

      public BigDecimal upperBound() {
         return this.upperBound;
      }

      public BigDecimal upperBoundValue() {
         return this.upperBoundValue;
      }

      public int n() {
         return this.n;
      }

      public Bounded copy(final BigDecimal lowerBound, final BigDecimal lowerBoundValue, final BigDecimal upperBound, final BigDecimal upperBoundValue, final int n) {
         return new Bounded(lowerBound, lowerBoundValue, upperBound, upperBoundValue, n);
      }

      public BigDecimal copy$default$1() {
         return this.lowerBound();
      }

      public BigDecimal copy$default$2() {
         return this.lowerBoundValue();
      }

      public BigDecimal copy$default$3() {
         return this.upperBound();
      }

      public BigDecimal copy$default$4() {
         return this.upperBoundValue();
      }

      public int copy$default$5() {
         return this.n();
      }

      public String productPrefix() {
         return "Bounded";
      }

      public int productArity() {
         return 5;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.lowerBound();
               break;
            case 1:
               var10000 = this.lowerBoundValue();
               break;
            case 2:
               var10000 = this.upperBound();
               break;
            case 3:
               var10000 = this.upperBoundValue();
               break;
            case 4:
               var10000 = BoxesRunTime.boxToInteger(this.n());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Bounded;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "lowerBound";
               break;
            case 1:
               var10000 = "lowerBoundValue";
               break;
            case 2:
               var10000 = "upperBound";
               break;
            case 3:
               var10000 = "upperBoundValue";
               break;
            case 4:
               var10000 = "n";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.lowerBound()));
         var1 = Statics.mix(var1, Statics.anyHash(this.lowerBoundValue()));
         var1 = Statics.mix(var1, Statics.anyHash(this.upperBound()));
         var1 = Statics.mix(var1, Statics.anyHash(this.upperBoundValue()));
         var1 = Statics.mix(var1, this.n());
         return Statics.finalizeHash(var1, 5);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label57: {
               boolean var2;
               if (x$1 instanceof Bounded) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Bounded var4 = (Bounded)x$1;
                  if (this.n() == var4.n() && BoxesRunTime.equalsNumNum(this.lowerBound(), var4.lowerBound()) && BoxesRunTime.equalsNumNum(this.lowerBoundValue(), var4.lowerBoundValue()) && BoxesRunTime.equalsNumNum(this.upperBound(), var4.upperBound()) && BoxesRunTime.equalsNumNum(this.upperBoundValue(), var4.upperBoundValue()) && var4.canEqual(this)) {
                     break label57;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Bounded(final BigDecimal lowerBound, final BigDecimal lowerBoundValue, final BigDecimal upperBound, final BigDecimal upperBoundValue, final int n) {
         this.lowerBound = lowerBound;
         this.lowerBoundValue = lowerBoundValue;
         this.upperBound = upperBound;
         this.upperBoundValue = upperBoundValue;
         this.n = n;
         Product.$init$(this);
      }
   }

   public static class Bounded$ extends AbstractFunction5 implements Serializable {
      public static final Bounded$ MODULE$ = new Bounded$();

      public final String toString() {
         return "Bounded";
      }

      public Bounded apply(final BigDecimal lowerBound, final BigDecimal lowerBoundValue, final BigDecimal upperBound, final BigDecimal upperBoundValue, final int n) {
         return new Bounded(lowerBound, lowerBoundValue, upperBound, upperBoundValue, n);
      }

      public Option unapply(final Bounded x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple5(x$0.lowerBound(), x$0.lowerBoundValue(), x$0.upperBound(), x$0.upperBoundValue(), BoxesRunTime.boxToInteger(x$0.n()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Bounded$.class);
      }
   }

   public static class BoundedLeft extends Approximation implements Product, Serializable {
      private final Rational lowerBound;
      private final BigDecimal upperBound;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Rational lowerBound() {
         return this.lowerBound;
      }

      public BigDecimal upperBound() {
         return this.upperBound;
      }

      public BoundedLeft copy(final Rational lowerBound, final BigDecimal upperBound) {
         return new BoundedLeft(lowerBound, upperBound);
      }

      public Rational copy$default$1() {
         return this.lowerBound();
      }

      public BigDecimal copy$default$2() {
         return this.upperBound();
      }

      public String productPrefix() {
         return "BoundedLeft";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.lowerBound();
               break;
            case 1:
               var10000 = this.upperBound();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof BoundedLeft;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "lowerBound";
               break;
            case 1:
               var10000 = "upperBound";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof BoundedLeft) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  BoundedLeft var4 = (BoundedLeft)x$1;
                  if (BoxesRunTime.equalsNumNum(this.lowerBound(), var4.lowerBound()) && BoxesRunTime.equalsNumNum(this.upperBound(), var4.upperBound()) && var4.canEqual(this)) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public BoundedLeft(final Rational lowerBound, final BigDecimal upperBound) {
         this.lowerBound = lowerBound;
         this.upperBound = upperBound;
         Product.$init$(this);
      }
   }

   public static class BoundedLeft$ extends AbstractFunction2 implements Serializable {
      public static final BoundedLeft$ MODULE$ = new BoundedLeft$();

      public final String toString() {
         return "BoundedLeft";
      }

      public BoundedLeft apply(final Rational lowerBound, final BigDecimal upperBound) {
         return new BoundedLeft(lowerBound, upperBound);
      }

      public Option unapply(final BoundedLeft x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.lowerBound(), x$0.upperBound())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(BoundedLeft$.class);
      }
   }

   public static class BoundedRight extends Approximation implements Product, Serializable {
      private final BigDecimal lowerBound;
      private final Rational upperBound;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public BigDecimal lowerBound() {
         return this.lowerBound;
      }

      public Rational upperBound() {
         return this.upperBound;
      }

      public BoundedRight copy(final BigDecimal lowerBound, final Rational upperBound) {
         return new BoundedRight(lowerBound, upperBound);
      }

      public BigDecimal copy$default$1() {
         return this.lowerBound();
      }

      public Rational copy$default$2() {
         return this.upperBound();
      }

      public String productPrefix() {
         return "BoundedRight";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.lowerBound();
               break;
            case 1:
               var10000 = this.upperBound();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof BoundedRight;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "lowerBound";
               break;
            case 1:
               var10000 = "upperBound";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof BoundedRight) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  BoundedRight var4 = (BoundedRight)x$1;
                  if (BoxesRunTime.equalsNumNum(this.lowerBound(), var4.lowerBound()) && BoxesRunTime.equalsNumNum(this.upperBound(), var4.upperBound()) && var4.canEqual(this)) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public BoundedRight(final BigDecimal lowerBound, final Rational upperBound) {
         this.lowerBound = lowerBound;
         this.upperBound = upperBound;
         Product.$init$(this);
      }
   }

   public static class BoundedRight$ extends AbstractFunction2 implements Serializable {
      public static final BoundedRight$ MODULE$ = new BoundedRight$();

      public final String toString() {
         return "BoundedRight";
      }

      public BoundedRight apply(final BigDecimal lowerBound, final Rational upperBound) {
         return new BoundedRight(lowerBound, upperBound);
      }

      public Option unapply(final BoundedRight x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.lowerBound(), x$0.upperBound())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(BoundedRight$.class);
      }
   }

   public static class Unbounded extends Approximation implements Product, Serializable {
      private final Rational lowerBound;
      private final Rational upperBound;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Rational lowerBound() {
         return this.lowerBound;
      }

      public Rational upperBound() {
         return this.upperBound;
      }

      public Unbounded copy(final Rational lowerBound, final Rational upperBound) {
         return new Unbounded(lowerBound, upperBound);
      }

      public Rational copy$default$1() {
         return this.lowerBound();
      }

      public Rational copy$default$2() {
         return this.upperBound();
      }

      public String productPrefix() {
         return "Unbounded";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.lowerBound();
               break;
            case 1:
               var10000 = this.upperBound();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Unbounded;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "lowerBound";
               break;
            case 1:
               var10000 = "upperBound";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof Unbounded) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Unbounded var4 = (Unbounded)x$1;
                  if (BoxesRunTime.equalsNumNum(this.lowerBound(), var4.lowerBound()) && BoxesRunTime.equalsNumNum(this.upperBound(), var4.upperBound()) && var4.canEqual(this)) {
                     break label51;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Unbounded(final Rational lowerBound, final Rational upperBound) {
         this.lowerBound = lowerBound;
         this.upperBound = upperBound;
         Product.$init$(this);
      }
   }

   public static class Unbounded$ extends AbstractFunction2 implements Serializable {
      public static final Unbounded$ MODULE$ = new Unbounded$();

      public final String toString() {
         return "Unbounded";
      }

      public Unbounded apply(final Rational lowerBound, final Rational upperBound) {
         return new Unbounded(lowerBound, upperBound);
      }

      public Option unapply(final Unbounded x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.lowerBound(), x$0.upperBound())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Unbounded$.class);
      }
   }

   public abstract static class ApproximationContext {
      public abstract Polynomial poly();

      public abstract int getEps(final BigDecimal x);

      public abstract BigDecimal evalExact(final BigDecimal x);

      public abstract BigDecimal floor(final Rational x);

      public abstract BigDecimal ceil(final Rational x);

      public abstract BigDecimal floor(final BigDecimal x);

      public abstract BigDecimal ceil(final BigDecimal x);
   }

   public static class AbsoluteContext extends ApproximationContext implements Product, Serializable {
      private final Polynomial poly;
      private final int scale;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Polynomial poly() {
         return this.poly;
      }

      public int scale() {
         return this.scale;
      }

      public int getEps(final BigDecimal x) {
         return this.scale();
      }

      public BigDecimal evalExact(final BigDecimal x) {
         return ((scala.math.BigDecimal)this.poly().apply(new scala.math.BigDecimal(x, MathContext.UNLIMITED), package.bigDecimal$.MODULE$.BigDecimalAlgebra())).bigDecimal().setScale(this.scale(), RoundingMode.UP);
      }

      public BigDecimal floor(final Rational x) {
         return x.toBigDecimal(this.scale(), RoundingMode.FLOOR).bigDecimal();
      }

      public BigDecimal ceil(final Rational x) {
         return x.toBigDecimal(this.scale(), RoundingMode.CEILING).bigDecimal();
      }

      public BigDecimal floor(final BigDecimal x) {
         return x.setScale(this.scale(), RoundingMode.FLOOR);
      }

      public BigDecimal ceil(final BigDecimal x) {
         return x.setScale(this.scale(), RoundingMode.CEILING);
      }

      public AbsoluteContext copy(final Polynomial poly, final int scale) {
         return new AbsoluteContext(poly, scale);
      }

      public Polynomial copy$default$1() {
         return this.poly();
      }

      public int copy$default$2() {
         return this.scale();
      }

      public String productPrefix() {
         return "AbsoluteContext";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.poly();
               break;
            case 1:
               var10000 = BoxesRunTime.boxToInteger(this.scale());
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof AbsoluteContext;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "poly";
               break;
            case 1:
               var10000 = "scale";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.poly()));
         var1 = Statics.mix(var1, this.scale());
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label55: {
               boolean var2;
               if (x$1 instanceof AbsoluteContext) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label38: {
                     AbsoluteContext var4 = (AbsoluteContext)x$1;
                     if (this.scale() == var4.scale()) {
                        label36: {
                           Polynomial var10000 = this.poly();
                           Polynomial var5 = var4.poly();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label36;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label36;
                           }

                           if (var4.canEqual(this)) {
                              var7 = true;
                              break label38;
                           }
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label55;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public AbsoluteContext(final Polynomial poly, final int scale) {
         this.poly = poly;
         this.scale = scale;
         Product.$init$(this);
      }
   }

   public static class AbsoluteContext$ extends AbstractFunction2 implements Serializable {
      public static final AbsoluteContext$ MODULE$ = new AbsoluteContext$();

      public int $lessinit$greater$default$2() {
         return Integer.MIN_VALUE;
      }

      public final String toString() {
         return "AbsoluteContext";
      }

      public AbsoluteContext apply(final Polynomial poly, final int scale) {
         return new AbsoluteContext(poly, scale);
      }

      public int apply$default$2() {
         return Integer.MIN_VALUE;
      }

      public Option unapply(final AbsoluteContext x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.poly(), BoxesRunTime.boxToInteger(x$0.scale()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AbsoluteContext$.class);
      }
   }

   public static class RelativeContext extends ApproximationContext implements Product, Serializable {
      private final Polynomial poly;
      private final MathContext mc;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Polynomial poly() {
         return this.poly;
      }

      public MathContext mc() {
         return this.mc;
      }

      public int getEps(final BigDecimal x) {
         return x.scale() - (int)package$.MODULE$.ceil((double)x.unscaledValue().bitLength() * BigDecimalRootRefinement$.MODULE$.spire$math$poly$BigDecimalRootRefinement$$bits2dec()) + this.mc().getPrecision() + 1;
      }

      public BigDecimal evalExact(final BigDecimal x) {
         return ((scala.math.BigDecimal)this.poly().apply(new scala.math.BigDecimal(x, MathContext.UNLIMITED), package.bigDecimal$.MODULE$.BigDecimalAlgebra())).bigDecimal().round(this.mc());
      }

      public BigDecimal floor(final Rational x) {
         return x.toBigDecimal(new MathContext(this.mc().getPrecision(), RoundingMode.FLOOR)).bigDecimal();
      }

      public BigDecimal ceil(final Rational x) {
         return x.toBigDecimal(new MathContext(this.mc().getPrecision(), RoundingMode.CEILING)).bigDecimal();
      }

      public BigDecimal floor(final BigDecimal x) {
         return x.round(new MathContext(this.mc().getPrecision(), RoundingMode.FLOOR));
      }

      public BigDecimal ceil(final BigDecimal x) {
         return x.round(new MathContext(this.mc().getPrecision(), RoundingMode.CEILING));
      }

      public RelativeContext copy(final Polynomial poly, final MathContext mc) {
         return new RelativeContext(poly, mc);
      }

      public Polynomial copy$default$1() {
         return this.poly();
      }

      public MathContext copy$default$2() {
         return this.mc();
      }

      public String productPrefix() {
         return "RelativeContext";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.poly();
               break;
            case 1:
               var10000 = this.mc();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof RelativeContext;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "poly";
               break;
            case 1:
               var10000 = "mc";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var9;
         if (this != x$1) {
            label63: {
               boolean var2;
               if (x$1 instanceof RelativeContext) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label45: {
                     label54: {
                        RelativeContext var4 = (RelativeContext)x$1;
                        Polynomial var10000 = this.poly();
                        Polynomial var5 = var4.poly();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label54;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label54;
                        }

                        MathContext var7 = this.mc();
                        MathContext var6 = var4.mc();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label54;
                           }
                        } else if (!var7.equals(var6)) {
                           break label54;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label45;
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label63;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      public RelativeContext(final Polynomial poly, final MathContext mc) {
         this.poly = poly;
         this.mc = mc;
         Product.$init$(this);
      }
   }

   public static class RelativeContext$ extends AbstractFunction2 implements Serializable {
      public static final RelativeContext$ MODULE$ = new RelativeContext$();

      public MathContext $lessinit$greater$default$2() {
         return new MathContext(0);
      }

      public final String toString() {
         return "RelativeContext";
      }

      public RelativeContext apply(final Polynomial poly, final MathContext mc) {
         return new RelativeContext(poly, mc);
      }

      public MathContext apply$default$2() {
         return new MathContext(0);
      }

      public Option unapply(final RelativeContext x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.poly(), x$0.mc())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(RelativeContext$.class);
      }
   }
}
