package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.HashVector;
import breeze.math.PowImplicits$;
import breeze.storage.Zero$;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\teea\u0002 @!\u0003\r\tA\u0012\u0005\u0006#\u0002!\tA\u0015\u0005\b-\u0002\u0011\r\u0011b\u0001X\u0011\u001da\u0007A1A\u0005\u00045Dq\u0001\u001e\u0001C\u0002\u0013\rQ\u000fC\u0004}\u0001\t\u0007I1A?\t\u0013\u0005%\u0001A1A\u0005\u0004\u0005-\u0001\"CA\u000b\u0001\t\u0007I1AA\f\u0011%\tY\u0002\u0001b\u0001\n\u0007\ti\u0002C\u0005\u0002\"\u0001\u0011\r\u0011b\u0001\u0002$!I\u0011q\u0005\u0001C\u0002\u0013\r\u0011\u0011\u0006\u0005\n\u0003g\u0001!\u0019!C\u0002\u0003kA\u0011\"!\u000f\u0001\u0005\u0004%\u0019!a\u000f\t\u0013\u0005}\u0002A1A\u0005\u0004\u0005\u0005\u0003\"CA#\u0001\t\u0007I1AA$\u0011%\t\t\u0006\u0001b\u0001\n\u0007\t\u0019\u0006C\u0005\u0002X\u0001\u0011\r\u0011b\u0001\u0002Z!I\u0011Q\f\u0001C\u0002\u0013\r\u0011q\f\u0005\n\u0003G\u0002!\u0019!C\u0002\u0003KB\u0011\"a\u001c\u0001\u0005\u0004%\u0019!!\u001d\t\u0013\u0005U\u0004A1A\u0005\u0004\u0005]\u0004\"CA>\u0001\t\u0007I1AA?\u0011%\t\t\t\u0001b\u0001\n\u0007\t\u0019\tC\u0005\u0002\u000e\u0002\u0011\r\u0011b\u0001\u0002\u0010\"I\u00111\u0013\u0001C\u0002\u0013\r\u0011Q\u0013\u0005\n\u00033\u0003!\u0019!C\u0002\u00037C\u0011\"a(\u0001\u0005\u0004%\u0019!!)\t\u0013\u0005-\u0006A1A\u0005\u0004\u00055\u0006\"CAY\u0001\t\u0007I1AAZ\u0011%\t9\f\u0001b\u0001\n\u0007\tI\fC\u0005\u0002>\u0002\u0011\r\u0011b\u0001\u0002@\"I\u0011q\u0019\u0001C\u0002\u0013\r\u0011\u0011\u001a\u0005\n\u0003\u001b\u0004!\u0019!C\u0002\u0003\u001fD\u0011\"a5\u0001\u0005\u0004%\u0019!!6\t\u0013\u0005e\u0007A1A\u0005\u0004\u0005m\u0007\"CAp\u0001\t\u0007I1AAq\u0011%\t)\u000f\u0001b\u0001\n\u0007\t9\u000fC\u0005\u0002l\u0002\u0011\r\u0011b\u0001\u0002n\"I\u0011\u0011\u001f\u0001C\u0002\u0013\r\u00111\u001f\u0005\n\u0003o\u0004!\u0019!C\u0002\u0003sD\u0011\"!@\u0001\u0005\u0004%\u0019!a@\t\u0013\t\r\u0001A1A\u0005\u0004\t\u0015\u0001\"\u0003B\u0005\u0001\t\u0007I1\u0001B\u0006\u0011%\u0011y\u0001\u0001b\u0001\n\u0007\u0011\t\u0002C\u0005\u0003\u0016\u0001\u0011\r\u0011b\u0001\u0003\u0018!I!1\u0004\u0001C\u0002\u0013\r!Q\u0004\u0005\n\u0005C\u0001!\u0019!C\u0002\u0005GA\u0011Ba\n\u0001\u0005\u0004%\u0019A!\u000b\t\u0013\t5\u0002A1A\u0005\u0004\t=\u0002\"\u0003B\u001a\u0001\t\u0007I1\u0001B\u001b\u0011%\u0011I\u0004\u0001b\u0001\n\u0007\u0011Y\u0004C\u0005\u0003@\u0001\u0011\r\u0011b\u0001\u0003B!I!Q\t\u0001C\u0002\u0013\r!q\t\u0005\n\u0005\u0017\u0002!\u0019!C\u0002\u0005\u001bB\u0011B!\u0015\u0001\u0005\u0004%\u0019Aa\u0015\t\u0013\t]\u0003A1A\u0005\u0004\te\u0003\"\u0003B/\u0001\t\u0007I1\u0001B0\u0011%\u0011\u0019\u0007\u0001b\u0001\n\u0007\u0011)\u0007C\u0004\u0003j\u0001!\u0019Aa\u001b\t\u000f\t\u001d\u0005\u0001b\u0001\u0003\n\"9!Q\u0012\u0001\u0005\u0004\t=\u0005b\u0002BJ\u0001\u0011\r!Q\u0013\u0002\u001b\u0011\u0006\u001c\bNV3di>\u0014x\fR3og\u00164Vm\u0019;pe~{\u0005o\u001d\u0006\u0003\u0001\u0006\u000b\u0011b\u001c9fe\u0006$xN]:\u000b\u0005\t\u001b\u0015A\u00027j]\u0006dwMC\u0001E\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001H\u001bB\u0011\u0001jS\u0007\u0002\u0013*\t!*A\u0003tG\u0006d\u0017-\u0003\u0002M\u0013\n1\u0011I\\=SK\u001a\u0004\"AT(\u000e\u0003}J!\u0001U \u00035\u0011+gn]3WK\u000e$xN]0ICNDg+Z2u_J|v\n]:\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0006C\u0001%U\u0013\t)\u0016J\u0001\u0003V]&$\u0018aH5na2|v\n]0J]Bc\u0017mY3`\u0011Z{FIV0J]R|v\n]!eIV\t\u0001\f\u0005\u0003Z9\nLgB\u0001([\u0013\tYv(A\u0003Pa\u0006#G-\u0003\u0002^=\na\u0011J\u001c)mC\u000e,\u0017*\u001c9me%\u0011q\f\u0019\u0002\u0006+\u001a+hn\u0019\u0006\u0003C\u000e\u000bqaZ3oKJL7\rE\u0002dI\u001al\u0011!Q\u0005\u0003K\u0006\u0013!\u0002S1tQZ+7\r^8s!\tAu-\u0003\u0002i\u0013\n\u0019\u0011J\u001c;\u0011\u0007\rTg-\u0003\u0002l\u0003\nYA)\u001a8tKZ+7\r^8s\u0003\tJW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0013,`\tZ{Fi\\;cY\u0016|v\n]!eIV\ta\u000e\u0005\u0003Z9>\u001c\bcA2eaB\u0011\u0001*]\u0005\u0003e&\u0013a\u0001R8vE2,\u0007cA2ka\u0006\t\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018%W?\u00123vL\u00127pCR|v\n]!eIV\ta\u000f\u0005\u0003Z9^\\\bcA2eqB\u0011\u0001*_\u0005\u0003u&\u0013QA\u00127pCR\u00042a\u00196y\u0003\u0001JW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0013,`\tZ{Fj\u001c8h?>\u0003\u0018\t\u001a3\u0016\u0003y\u0004R!\u0017/\u0000\u0003\u000f\u0001Ba\u00193\u0002\u0002A\u0019\u0001*a\u0001\n\u0007\u0005\u0015\u0011J\u0001\u0003M_:<\u0007\u0003B2k\u0003\u0003\tq$[7qY~{\u0005oX%o!2\f7-Z0I-~#ekX%oi~{\u0005oU;c+\t\ti\u0001E\u0003\u0002\u0010q\u0013\u0017ND\u0002O\u0003#I1!a\u0005@\u0003\u0015y\u0005oU;c\u0003\tJW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0013,`\tZ{Fi\\;cY\u0016|v\n]*vEV\u0011\u0011\u0011\u0004\t\u0006\u0003\u001favn]\u0001\"S6\u0004HnX(q?&s\u0007\u000b\\1dK~Cek\u0018#W?\u001acw.\u0019;`\u001fB\u001cVOY\u000b\u0003\u0003?\u0001R!a\u0004]on\f\u0001%[7qY~{\u0005oX%o!2\f7-Z0I-~#ek\u0018'p]\u001e|v\n]*vEV\u0011\u0011Q\u0005\t\u0007\u0003\u001fav0a\u0002\u0002K%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|\u0006JV0E-~Ke\u000e^0Pa6+HnU2bY\u0006\u0014XCAA\u0016!\u0015\ti\u0003\u00182j\u001d\rq\u0015qF\u0005\u0004\u0003cy\u0014aC(q\u001bVd7kY1mCJ\f\u0001&[7qY~{\u0005oX%o!2\f7-Z0I-~#ek\u0018#pk\ndWmX(q\u001bVd7kY1mCJ,\"!a\u000e\u0011\u000b\u00055Bl\\:\u0002O%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|\u0006JV0E-~3En\\1u?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0003\u0003{\u0001R!!\f]on\fa%[7qY~{\u0005oX%o!2\f7-Z0I-~#ek\u0018'p]\u001e|v\n]'vYN\u001b\u0017\r\\1s+\t\t\u0019\u0005\u0005\u0004\u0002.q{\u0018qA\u0001 S6\u0004HnX(q?&s\u0007\u000b\\1dK~Cek\u0018#W?&sGoX(q\t&4XCAA%!\u0015\tY\u0005\u00182j\u001d\rq\u0015QJ\u0005\u0004\u0003\u001fz\u0014!B(q\t&4\u0018AI5na2|v\n]0J]Bc\u0017mY3`\u0011Z{FIV0E_V\u0014G.Z0Pa\u0012Kg/\u0006\u0002\u0002VA)\u00111\n/pg\u0006\t\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018%W?\u00123vL\u00127pCR|v\n\u001d#jmV\u0011\u00111\f\t\u0006\u0003\u0017bvo_\u0001!S6\u0004HnX(q?&s\u0007\u000b\\1dK~Cek\u0018#W?2{gnZ0Pa\u0012Kg/\u0006\u0002\u0002bA1\u00111\n/\u0000\u0003\u000f\tq$[7qY~{\u0005oX%o!2\f7-Z0I-~#ekX%oi~{\u0005oU3u+\t\t9\u0007E\u0003\u0002jq\u0013\u0017ND\u0002O\u0003WJ1!!\u001c@\u0003\u0015y\u0005oU3u\u0003\tJW\u000e\u001d7`\u001fB|\u0016J\u001c)mC\u000e,w\f\u0013,`\tZ{Fi\\;cY\u0016|v\n]*fiV\u0011\u00111\u000f\t\u0006\u0003Sbvn]\u0001\"S6\u0004HnX(q?&s\u0007\u000b\\1dK~Cek\u0018#W?\u001acw.\u0019;`\u001fB\u001cV\r^\u000b\u0003\u0003s\u0002R!!\u001b]on\f\u0001%[7qY~{\u0005oX%o!2\f7-Z0I-~#ek\u0018'p]\u001e|v\n]*fiV\u0011\u0011q\u0010\t\u0007\u0003Sbv0a\u0002\u0002?%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|\u0006JV0E-~Ke\u000e^0Pa6{G-\u0006\u0002\u0002\u0006B)\u0011q\u0011/cS:\u0019a*!#\n\u0007\u0005-u(A\u0003Pa6{G-\u0001\u0012j[Bdwl\u00149`\u0013:\u0004F.Y2f?\"3v\f\u0012,`\t>,(\r\\3`\u001fBlu\u000eZ\u000b\u0003\u0003#\u0003R!a\"]_N\f\u0011%[7qY~{\u0005oX%o!2\f7-Z0I-~#ek\u0018$m_\u0006$xl\u00149N_\u0012,\"!a&\u0011\u000b\u0005\u001dEl^>\u0002A%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|\u0006JV0E-~cuN\\4`\u001fBlu\u000eZ\u000b\u0003\u0003;\u0003b!a\"]\u007f\u0006\u001d\u0011aH5na2|v\n]0J]Bc\u0017mY3`\u0011Z{FIV0J]R|v\n\u001d)poV\u0011\u00111\u0015\t\u0006\u0003Kc&-\u001b\b\u0004\u001d\u0006\u001d\u0016bAAU\u007f\u0005)q\n\u001d)po\u0006\u0011\u0013.\u001c9m?>\u0003x,\u00138QY\u0006\u001cWm\u0018%W?\u00123v\fR8vE2,wl\u00149Q_^,\"!a,\u0011\u000b\u0005\u0015Fl\\:\u0002C%l\u0007\u000f\\0Pa~Ke\u000e\u00157bG\u0016|\u0006JV0E-~3En\\1u?>\u0003\bk\\<\u0016\u0005\u0005U\u0006#BAS9^\\\u0018\u0001I5na2|v\n]0J]Bc\u0017mY3`\u0011Z{FIV0M_:<wl\u00149Q_^,\"!a/\u0011\r\u0005\u0015Fl`A\u0004\u0003uIW\u000e\u001d7`\u001fB|\u0006JV0E-~+\u0017o\u0018%W?&sGoX(q\u0003\u0012$WCAAa!\u0019I\u00161\u00192jS&\u0019\u0011Q\u00190\u0003\u000b%k\u0007\u000f\u001c\u001a\u0002A%l\u0007\u000f\\0Pa~Cek\u0018#W?\u0016\fx\f\u0013,`\t>,(\r\\3`\u001fB\fE\rZ\u000b\u0003\u0003\u0017\u0004b!WAb_N\u001c\u0018aH5na2|v\n]0I-~#ekX3r?\"3vL\u00127pCR|v\n]!eIV\u0011\u0011\u0011\u001b\t\u00073\u0006\rwo_>\u0002=%l\u0007\u000f\\0Pa~Cek\u0018#W?\u0016\fx\f\u0013,`\u0019>twmX(q\u0003\u0012$WCAAl!!I\u00161Y@\u0002\b\u0005\u001d\u0011!H5na2|v\n]0I-~#ekX3r?\"3v,\u00138u?>\u00038+\u001e2\u0016\u0005\u0005u\u0007cBA\b\u0003\u0007\u0014\u0017.[\u0001!S6\u0004HnX(q?\"3v\f\u0012,`KF|\u0006JV0E_V\u0014G.Z0PaN+(-\u0006\u0002\u0002dB9\u0011qBAb_N\u001c\u0018aH5na2|v\n]0I-~#ekX3r?\"3vL\u00127pCR|v\n]*vEV\u0011\u0011\u0011\u001e\t\b\u0003\u001f\t\u0019m^>|\u0003yIW\u000e\u001d7`\u001fB|\u0006JV0E-~+\u0017o\u0018%W?2{gnZ0PaN+(-\u0006\u0002\u0002pBI\u0011qBAb\u007f\u0006\u001d\u0011qA\u0001$S6\u0004HnX(q?\"3v\f\u0012,`KF|\u0006JV0J]R|v\n]'vYN\u001b\u0017\r\\1s+\t\t)\u0010E\u0004\u0002.\u0005\r'-[5\u0002M%l\u0007\u000f\\0Pa~Cek\u0018#W?\u0016\fx\f\u0013,`\t>,(\r\\3`\u001fBlU\u000f\\*dC2\f'/\u0006\u0002\u0002|B9\u0011QFAb_N\u001c\u0018!J5na2|v\n]0I-~#ekX3r?\"3vL\u00127pCR|v\n]'vYN\u001b\u0017\r\\1s+\t\u0011\t\u0001E\u0004\u0002.\u0005\rwo_>\u0002I%l\u0007\u000f\\0Pa~Cek\u0018#W?\u0016\fx\f\u0013,`\u0019>twmX(q\u001bVd7kY1mCJ,\"Aa\u0002\u0011\u0013\u00055\u00121Y@\u0002\b\u0005\u001d\u0011!H5na2|v\n]0I-~#ekX3r?\"3v,\u00138u?>\u0003H)\u001b<\u0016\u0005\t5\u0001cBA&\u0003\u0007\u0014\u0017.[\u0001!S6\u0004HnX(q?\"3v\f\u0012,`KF|\u0006JV0E_V\u0014G.Z0Pa\u0012Kg/\u0006\u0002\u0003\u0014A9\u00111JAb_N\u001c\u0018aH5na2|v\n]0I-~#ekX3r?\"3vL\u00127pCR|v\n\u001d#jmV\u0011!\u0011\u0004\t\b\u0003\u0017\n\u0019m^>|\u0003yIW\u000e\u001d7`\u001fB|\u0006JV0E-~+\u0017o\u0018%W?2{gnZ0Pa\u0012Kg/\u0006\u0002\u0003 AI\u00111JAb\u007f\u0006\u001d\u0011qA\u0001\u001eS6\u0004HnX(q?\"3v\f\u0012,`KF|\u0006JV0J]R|v\n]*fiV\u0011!Q\u0005\t\b\u0003S\n\u0019MY5j\u0003\u0001JW\u000e\u001d7`\u001fB|\u0006JV0E-~+\u0017o\u0018%W?\u0012{WO\u00197f?>\u00038+\u001a;\u0016\u0005\t-\u0002cBA5\u0003\u0007|7o]\u0001 S6\u0004HnX(q?\"3v\f\u0012,`KF|\u0006JV0GY>\fGoX(q'\u0016$XC\u0001B\u0019!\u001d\tI'a1xwn\fa$[7qY~{\u0005o\u0018%W?\u00123v,Z9`\u0011Z{Fj\u001c8h?>\u00038+\u001a;\u0016\u0005\t]\u0002#CA5\u0003\u0007|\u0018qAA\u0004\u0003uIW\u000e\u001d7`\u001fB|\u0006JV0E-~+\u0017o\u0018%W?&sGoX(q\u001b>$WC\u0001B\u001f!\u001d\t9)a1cS&\f\u0001%[7qY~{\u0005o\u0018%W?\u00123v,Z9`\u0011Z{Fi\\;cY\u0016|v\n]'pIV\u0011!1\t\t\b\u0003\u000f\u000b\u0019m\\:t\u0003}IW\u000e\u001d7`\u001fB|\u0006JV0E-~+\u0017o\u0018%W?\u001acw.\u0019;`\u001fBlu\u000eZ\u000b\u0003\u0005\u0013\u0002r!a\"\u0002D^\\80\u0001\u0010j[Bdwl\u00149`\u0011Z{FIV0fc~Cek\u0018'p]\u001e|v\n]'pIV\u0011!q\n\t\n\u0003\u000f\u000b\u0019m`A\u0004\u0003\u000f\tQ$[7qY~{\u0005o\u0018%W?\u00123v,Z9`\u0011Z{\u0016J\u001c;`\u001fB\u0004vn^\u000b\u0003\u0005+\u0002r!!*\u0002D\nL\u0017.\u0001\u0011j[Bdwl\u00149`\u0011Z{FIV0fc~Cek\u0018#pk\ndWmX(q!><XC\u0001B.!\u001d\t)+a1pgN\fq$[7qY~{\u0005o\u0018%W?\u00123v,Z9`\u0011Z{f\t\\8bi~{\u0005\u000fU8x+\t\u0011\t\u0007E\u0004\u0002&\u0006\rwo_>\u0002=%l\u0007\u000f\\0Pa~Cek\u0018#W?\u0016\fx\f\u0013,`\u0019>twmX(q!><XC\u0001B4!%\t)+a1\u0000\u0003\u000f\t9!\u0001\u0010j[Bdwl\u00149Nk2LeN\\3s?\"3v\f\u0012,`KF|FkX%oiV\u0011!Q\u000e\t\b\u0005_\n\u0019MY5g\u001d\u0011\u0011\tHa!\u000f\t\tM$\u0011\u0011\b\u0005\u0005k\u0012yH\u0004\u0003\u0003x\tuTB\u0001B=\u0015\r\u0011Y(R\u0001\u0007yI|w\u000e\u001e \n\u0003\u0011K!AQ\"\n\u0005\u0001\u000b\u0015b\u0001BC\u007f\u0005Qq\n]'vY&sg.\u001a:\u0002A%l\u0007\u000f\\0Pa6+H.\u00138oKJ|\u0006JV0E-~+\u0017o\u0018+`\r2|\u0017\r^\u000b\u0003\u0005\u0017\u0003rAa\u001c\u0002D^\\\b0A\u0011j[Bdwl\u00149Nk2LeN\\3s?\"3v\f\u0012,`KF|Fk\u0018#pk\ndW-\u0006\u0002\u0003\u0012B9!qNAb_N\u0004\u0018aH5na2|v\n]'vY&sg.\u001a:`\u0011Z{FIV0fc~#v\fT8oOV\u0011!q\u0013\t\n\u0005_\n\u0019m`A\u0004\u0003\u0003\u0001"
)
public interface HashVector_DenseVector_Ops extends DenseVector_HashVector_Ops {
   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpAdd_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpSub_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpSet_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpMod_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpPow_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpPow_$eq(final UFunc.UImpl2 x$1);

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpSub();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpSet();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpMod();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Int_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Double_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Float_OpPow();

   UFunc.InPlaceImpl2 impl_Op_InPlace_HV_DV_Long_OpPow();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpAdd();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpAdd();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpAdd();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpAdd();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpSub();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpSub();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpSub();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpSub();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpDiv();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpSet();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpSet();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpSet();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpSet();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpMod();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpMod();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpMod();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpMod();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Int_OpPow();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Double_OpPow();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Float_OpPow();

   UFunc.UImpl2 impl_Op_HV_DV_eq_HV_Long_OpPow();

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Int$(final HashVector_DenseVector_Ops $this) {
      return $this.impl_OpMulInner_HV_DV_eq_T_Int();
   }

   default UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Int() {
      return new UFunc.UImpl2() {
         // $FF: synthetic field
         private final HashVector_DenseVector_Ops $outer;

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

         public int apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return BoxesRunTime.unboxToInt(b.dot(a, this.$outer.impl_OpMulInner_DV_HV_eq_S_Int()));
            }
         }

         public {
            if (HashVector_DenseVector_Ops.this == null) {
               throw null;
            } else {
               this.$outer = HashVector_DenseVector_Ops.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Float$(final HashVector_DenseVector_Ops $this) {
      return $this.impl_OpMulInner_HV_DV_eq_T_Float();
   }

   default UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Float() {
      return new UFunc.UImpl2() {
         // $FF: synthetic field
         private final HashVector_DenseVector_Ops $outer;

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

         public float apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return BoxesRunTime.unboxToFloat(b.dot(a, this.$outer.impl_OpMulInner_DV_HV_eq_S_Float()));
            }
         }

         public {
            if (HashVector_DenseVector_Ops.this == null) {
               throw null;
            } else {
               this.$outer = HashVector_DenseVector_Ops.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Double$(final HashVector_DenseVector_Ops $this) {
      return $this.impl_OpMulInner_HV_DV_eq_T_Double();
   }

   default UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Double() {
      return new UFunc.UImpl2() {
         // $FF: synthetic field
         private final HashVector_DenseVector_Ops $outer;

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

         public double apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return BoxesRunTime.unboxToDouble(b.dot(a, this.$outer.impl_OpMulInner_DV_HV_eq_S_Double()));
            }
         }

         public {
            if (HashVector_DenseVector_Ops.this == null) {
               throw null;
            } else {
               this.$outer = HashVector_DenseVector_Ops.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Long$(final HashVector_DenseVector_Ops $this) {
      return $this.impl_OpMulInner_HV_DV_eq_T_Long();
   }

   default UFunc.UImpl2 impl_OpMulInner_HV_DV_eq_T_Long() {
      return new UFunc.UImpl2() {
         // $FF: synthetic field
         private final HashVector_DenseVector_Ops $outer;

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

         public long apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = b.length();
            int right$macro$2 = a.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return BoxesRunTime.unboxToLong(b.dot(a, this.$outer.impl_OpMulInner_DV_HV_eq_S_Long()));
            }
         }

         public {
            if (HashVector_DenseVector_Ops.this == null) {
               throw null;
            } else {
               this.$outer = HashVector_DenseVector_Ops.this;
            }
         }
      };
   }

   static void $init$(final HashVector_DenseVector_Ops $this) {
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcI$sp(i, a.apply$mcI$sp(i) + b.apply$mcI$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcD$sp(i, a.apply$mcD$sp(i) + b.apply$mcD$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcF$sp(i, a.apply$mcF$sp(i) + b.apply$mcF$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcJ$sp(i, a.apply$mcJ$sp(i) + b.apply$mcJ$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcI$sp(i, a.apply$mcI$sp(i) - b.apply$mcI$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcD$sp(i, a.apply$mcD$sp(i) - b.apply$mcD$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcF$sp(i, a.apply$mcF$sp(i) - b.apply$mcF$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcJ$sp(i, a.apply$mcJ$sp(i) - b.apply$mcJ$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcI$sp(i, a.apply$mcI$sp(i) * b.apply$mcI$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcD$sp(i, a.apply$mcD$sp(i) * b.apply$mcD$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcF$sp(i, a.apply$mcF$sp(i) * b.apply$mcF$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcJ$sp(i, a.apply$mcJ$sp(i) * b.apply$mcJ$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcI$sp(i, a.apply$mcI$sp(i) / b.apply$mcI$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcD$sp(i, a.apply$mcD$sp(i) / b.apply$mcD$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcF$sp(i, a.apply$mcF$sp(i) / b.apply$mcF$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcJ$sp(i, a.apply$mcJ$sp(i) / b.apply$mcJ$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcI$sp(i, b.apply$mcI$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcD$sp(i, b.apply$mcD$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcF$sp(i, b.apply$mcF$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcJ$sp(i, b.apply$mcJ$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcI$sp(i, a.apply$mcI$sp(i) % b.apply$mcI$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcD$sp(i, a.apply$mcD$sp(i) % b.apply$mcD$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcF$sp(i, a.apply$mcF$sp(i) % b.apply$mcF$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcJ$sp(i, a.apply$mcJ$sp(i) % b.apply$mcJ$sp(i));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Int_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcI$sp(i, PowImplicits$.MODULE$.IntPow(a.apply$mcI$sp(i)).pow(b.apply$mcI$sp(i)));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Double_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcD$sp(i, PowImplicits$.MODULE$.DoublePow(a.apply$mcD$sp(i)).pow(b.apply$mcD$sp(i)));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Float_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcF$sp(i, PowImplicits$.MODULE$.FloatPow(a.apply$mcF$sp(i)).pow(b.apply$mcF$sp(i)));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_InPlace_HV_DV_Long_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               for(int i = 0; i < b.length(); ++i) {
                  a.update$mcJ$sp(i, PowImplicits$.MODULE$.LongPow(a.apply$mcJ$sp(i)).pow(b.apply$mcJ$sp(i)));
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) + b.apply$mcI$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) + b.apply$mcD$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) + b.apply$mcF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpAdd_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) + b.apply$mcJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) - b.apply$mcI$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) - b.apply$mcD$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) - b.apply$mcF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpSub_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) - b.apply$mcJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_idempotent_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) * b.apply$mcI$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) * b.apply$mcD$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) * b.apply$mcF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) * b.apply$mcJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) / b.apply$mcI$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) / b.apply$mcD$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) / b.apply$mcF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) / b.apply$mcJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcI$sp(i, b.apply$mcI$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcD$sp(i, b.apply$mcD$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcF$sp(i, b.apply$mcF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpSet_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcJ$sp(i, b.apply$mcJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcI$sp(i, a.apply$mcI$sp(i) % b.apply$mcI$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcD$sp(i, a.apply$mcD$sp(i) % b.apply$mcD$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcF$sp(i, a.apply$mcF$sp(i) % b.apply$mcF$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpMod_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcJ$sp(i, a.apply$mcJ$sp(i) % b.apply$mcJ$sp(i));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Int_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mIc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Int(), Zero$.MODULE$.IntZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcI$sp(i, PowImplicits$.MODULE$.IntPow(a.apply$mcI$sp(i)).pow(b.apply$mcI$sp(i)));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Double_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mDc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Double(), Zero$.MODULE$.DoubleZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcD$sp(i, PowImplicits$.MODULE$.DoublePow(a.apply$mcD$sp(i)).pow(b.apply$mcD$sp(i)));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Float_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mFc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Float(), Zero$.MODULE$.FloatZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcF$sp(i, PowImplicits$.MODULE$.FloatPow(a.apply$mcF$sp(i)).pow(b.apply$mcF$sp(i)));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
      $this.breeze$linalg$operators$HashVector_DenseVector_Ops$_setter_$impl_Op_HV_DV_eq_HV_Long_OpPow_$eq(new UFunc.UImpl2() {
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

         public DenseVector apply(final HashVector a, final DenseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               DenseVector result = DenseVector$.MODULE$.zeros$mJc$sp(a.length(), scala.reflect.ClassTag..MODULE$.Long(), Zero$.MODULE$.LongZero());

               for(int i = 0; i < b.length(); ++i) {
                  result.update$mcJ$sp(i, PowImplicits$.MODULE$.LongPow(a.apply$mcJ$sp(i)).pow(b.apply$mcJ$sp(i)));
               }

               return result;
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(HashVector_DenseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(HashVector.class), scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
         }
      });
   }
}
