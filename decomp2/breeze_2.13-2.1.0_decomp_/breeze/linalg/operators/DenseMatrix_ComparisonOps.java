package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.Matrix;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t}ga\u0002&L!\u0003\r\tA\u0015\u0005\u0006;\u0002!\tA\u0018\u0005\u0006E\u0002!\u0019a\u0019\u0005\u0006s\u0002!\u0019A\u001f\u0005\b\u0003\u0003\u0001A1AA\u0002\u0011\u001d\ty\u0001\u0001C\u0002\u0003#Aq!!\b\u0001\t\u0007\ty\u0002C\u0004\u0002*\u0001!\u0019!a\u000b\t\u000f\u0005=\u0002\u0001b\u0001\u00022!9\u0011Q\u0007\u0001\u0005\u0004\u0005]\u0002bBA\u001e\u0001\u0011\r\u0011Q\b\u0005\b\u0003\u000f\u0002A1AA%\u0011\u001d\ti\u0005\u0001C\u0002\u0003\u001fBq!a\u0015\u0001\t\u0007\t)\u0006C\u0004\u0002Z\u0001!\u0019!a\u0017\t\u000f\u0005\u0015\u0004\u0001b\u0001\u0002h!9\u00111\u000e\u0001\u0005\u0004\u00055\u0004bBA9\u0001\u0011\r\u00111\u000f\u0005\b\u0003o\u0002A1AA=\u0011\u001d\t\u0019\t\u0001C\u0002\u0003\u000bCq!!#\u0001\t\u0007\tY\tC\u0004\u0002\u0010\u0002!\u0019!!%\t\u000f\u0005U\u0005\u0001b\u0001\u0002\u0018\"9\u0011\u0011\u0015\u0001\u0005\u0004\u0005\r\u0006bBAT\u0001\u0011\r\u0011\u0011\u0016\u0005\b\u0003[\u0003A1AAX\u0011\u001d\t\u0019\f\u0001C\u0002\u0003kCq!a0\u0001\t\u0007\t\t\rC\u0004\u0002H\u0002!\u0019!!3\t\u000f\u0005=\u0007\u0001b\u0001\u0002R\"9\u0011q\u001b\u0001\u0005\u0004\u0005e\u0007bBAo\u0001\u0011\r\u0011q\u001c\u0005\b\u0003G\u0004A1AAs\u0011\u001d\tI\u000f\u0001C\u0002\u0003WDq!a<\u0001\t\u0007\t\t\u0010C\u0004\u0002v\u0002!\u0019!a>\t\u000f\u0005m\b\u0001b\u0001\u0002~\"9!\u0011\u0001\u0001\u0005\u0004\t\r\u0001b\u0002B\u0004\u0001\u0011\r!\u0011\u0002\u0005\b\u0005\u001b\u0001A1\u0001B\b\u0011\u001d\u0011\u0019\u0002\u0001C\u0002\u0005+AqA!\u0007\u0001\t\u0007\u0011Y\u0002C\u0004\u0003 \u0001!\u0019A!\t\t\u000f\t\u0015\u0002\u0001b\u0001\u0003(!9!1\u0006\u0001\u0005\u0004\t5\u0002b\u0002B\u0019\u0001\u0011\r!1\u0007\u0005\b\u0005o\u0001A1\u0001B\u001d\u0011\u001d\u0011i\u0004\u0001C\u0002\u0005\u007fAqAa\u0011\u0001\t\u0007\u0011)\u0005C\u0004\u0003J\u0001!\u0019Aa\u0013\t\u000f\t=\u0003\u0001b\u0001\u0003R!9!Q\u000b\u0001\u0005\u0004\t]\u0003b\u0002B.\u0001\u0011\r!Q\f\u0005\b\u0005C\u0002A1\u0001B2\u0011\u001d\u00119\u0007\u0001C\u0002\u0005SBqA!\u001c\u0001\t\u0007\u0011y\u0007C\u0004\u0003t\u0001!\u0019A!\u001e\t\u000f\te\u0004\u0001b\u0001\u0003|!9!q\u0010\u0001\u0005\u0004\t\u0005\u0005b\u0002BC\u0001\u0011\r!q\u0011\u0005\b\u0005\u0017\u0003A1\u0001BG\u0011\u001d\u0011\t\n\u0001C\u0002\u0005'CqAa&\u0001\t\u0007\u0011I\nC\u0004\u0003\u001e\u0002!\u0019Aa(\t\u000f\t\r\u0006\u0001b\u0001\u0003&\"9!\u0011\u0016\u0001\u0005\u0004\t-\u0006b\u0002BX\u0001\u0011\r!\u0011\u0017\u0005\b\u0005k\u0003A1\u0001B\\\u0011\u001d\u0011Y\f\u0001C\u0002\u0005{CqA!1\u0001\t\u0007\u0011\u0019\rC\u0004\u0003H\u0002!\u0019A!3\t\u000f\t5\u0007\u0001b\u0001\u0003P\"9!1\u001b\u0001\u0005\u0004\tU\u0007b\u0002Bm\u0001\u0011\r!1\u001c\u0002\u001a\t\u0016t7/Z'biJL\u0007pX\"p[B\f'/[:p]>\u00038O\u0003\u0002M\u001b\u0006Iq\u000e]3sCR|'o\u001d\u0006\u0003\u001d>\u000ba\u0001\\5oC2<'\"\u0001)\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u00192\u0001A*Z!\t!v+D\u0001V\u0015\u00051\u0016!B:dC2\f\u0017B\u0001-V\u0005\u0019\te.\u001f*fMB\u0011!lW\u0007\u0002\u0017&\u0011Al\u0013\u0002\u0017\t\u0016t7/Z'biJL\u00070\u0012=qC:$W\rZ(qg\u00061A%\u001b8ji\u0012\"\u0012a\u0018\t\u0003)\u0002L!!Y+\u0003\tUs\u0017\u000e^\u0001!S6\u0004HnX(q?\u0012ku\fR'`KF|F)\u0014\"p_2|\u0016J\u001c;`\u001fB<E+F\u0001e!\u0015)\u0007N\u001c8v\u001d\tQf-\u0003\u0002h\u0017\u0006!q\n]$U\u0013\tI'NA\u0003J[Bd''\u0003\u0002lY\n)QKR;oG*\u0011QnT\u0001\bO\u0016tWM]5d!\ry\u0007O]\u0007\u0002\u001b&\u0011\u0011/\u0014\u0002\f\t\u0016t7/Z'biJL\u0007\u0010\u0005\u0002Ug&\u0011A/\u0016\u0002\u0004\u0013:$\bcA8qmB\u0011Ak^\u0005\u0003qV\u0013qAQ8pY\u0016\fg.A\u0012j[Bdwl\u00149`\t6{F)T0fc~#UJQ8pY~#u.\u001e2mK~{\u0005o\u0012+\u0016\u0003m\u0004R!\u001a5}yV\u00042a\u001c9~!\t!f0\u0003\u0002\u0000+\n1Ai\\;cY\u0016\f!%[7qY~{\u0005o\u0018#N?\u0012ku,Z9`\t6\u0013un\u001c7`\r2|\u0017\r^0Pa\u001e#VCAA\u0003!\u001d)\u0007.a\u0002\u0002\bU\u0004Ba\u001c9\u0002\nA\u0019A+a\u0003\n\u0007\u00055QKA\u0003GY>\fG/A\u0011j[Bdwl\u00149`\t6{F)T0fc~#UJQ8pY~cuN\\4`\u001fB<E+\u0006\u0002\u0002\u0014A9Q\r[A\u000b\u0003+)\b\u0003B8q\u0003/\u00012\u0001VA\r\u0013\r\tY\"\u0016\u0002\u0005\u0019>tw-A\u0011j[Bdwl\u00149`\t6{F)T0fc~#UJQ8pY~Ke\u000e^0Pa\u001e#V)\u0006\u0002\u0002\"A1\u00111\u00055o]Vt1AWA\u0013\u0013\r\t9cS\u0001\u0006\u001fB<E+R\u0001%S6\u0004HnX(q?\u0012ku\fR'`KF|F)\u0014\"p_2|Fi\\;cY\u0016|v\n]$U\u000bV\u0011\u0011Q\u0006\t\u0007\u0003GAG\u0010`;\u0002G%l\u0007\u000f\\0Pa~#Uj\u0018#N?\u0016\fx\fR'C_>dwL\u00127pCR|v\n]$U\u000bV\u0011\u00111\u0007\t\t\u0003GA\u0017qAA\u0004k\u0006\u0011\u0013.\u001c9m?>\u0003x\fR'`\t6{V-]0E\u001b\n{w\u000e\\0M_:<wl\u00149H)\u0016+\"!!\u000f\u0011\u0011\u0005\r\u0002.!\u0006\u0002\u0016U\f\u0011%[7qY~{\u0005o\u0018#N?\u0012ku,Z9`\t6\u0013un\u001c7`\u0013:$xl\u00149M)\u0016+\"!a\u0010\u0011\r\u0005\u0005\u0003N\u001c8v\u001d\rQ\u00161I\u0005\u0004\u0003\u000bZ\u0015!B(q\u0019R+\u0015\u0001J5na2|v\n]0E\u001b~#UjX3r?\u0012k%i\\8m?\u0012{WO\u00197f?>\u0003H\nV#\u0016\u0005\u0005-\u0003CBA!QrdX/A\u0012j[Bdwl\u00149`\t6{F)T0fc~#UJQ8pY~3En\\1u?>\u0003H\nV#\u0016\u0005\u0005E\u0003\u0003CA!Q\u0006\u001d\u0011qA;\u0002E%l\u0007\u000f\\0Pa~#Uj\u0018#N?\u0016\fx\fR'C_>dw\fT8oO~{\u0005\u000f\u0014+F+\t\t9\u0006\u0005\u0005\u0002B!\f)\"!\u0006v\u0003\u0001JW\u000e\u001d7`\u001fB|F)T0E\u001b~+\u0017o\u0018#N\u0005>|GnX%oi~{\u0005\u000f\u0014+\u0016\u0005\u0005u\u0003CBA0Q:tWOD\u0002[\u0003CJ1!a\u0019L\u0003\u0011y\u0005\u000f\u0014+\u0002G%l\u0007\u000f\\0Pa~#Uj\u0018#N?\u0016\fx\fR'C_>dw\fR8vE2,wl\u00149M)V\u0011\u0011\u0011\u000e\t\u0007\u0003?BG\u0010`;\u0002E%l\u0007\u000f\\0Pa~#Uj\u0018#N?\u0016\fx\fR'C_>dwL\u00127pCR|v\n\u001d'U+\t\ty\u0007\u0005\u0005\u0002`!\f9!a\u0002v\u0003\u0005JW\u000e\u001d7`\u001fB|F)T0E\u001b~+\u0017o\u0018#N\u0005>|Gn\u0018'p]\u001e|v\n\u001d'U+\t\t)\b\u0005\u0005\u0002`!\f)\"!\u0006v\u0003\u0001JW\u000e\u001d7`\u001fB|F)T0E\u001b~+\u0017o\u0018#N\u0005>|GnX%oi~{\u0005/R9\u0016\u0005\u0005m\u0004CBA?Q:tWOD\u0002[\u0003\u007fJ1!!!L\u0003\u0011y\u0005/R9\u0002G%l\u0007\u000f\\0Pa~#Uj\u0018#N?\u0016\fx\fR'C_>dw\fR8vE2,wl\u00149FcV\u0011\u0011q\u0011\t\u0007\u0003{BG\u0010`;\u0002E%l\u0007\u000f\\0Pa~#Uj\u0018#N?\u0016\fx\fR'C_>dwL\u00127pCR|v\n]#r+\t\ti\t\u0005\u0005\u0002~!\f9!a\u0002v\u0003\u0005JW\u000e\u001d7`\u001fB|F)T0E\u001b~+\u0017o\u0018#N\u0005>|Gn\u0018'p]\u001e|v\n]#r+\t\t\u0019\n\u0005\u0005\u0002~!\f)\"!\u0006v\u0003\u0001JW\u000e\u001d7`\u001fB|F)T0E\u001b~+\u0017o\u0018#N\u0005>|GnX%oi~{\u0005OT3\u0016\u0005\u0005e\u0005CBANQ:tWOD\u0002[\u0003;K1!a(L\u0003\u0011y\u0005OT3\u0002G%l\u0007\u000f\\0Pa~#Uj\u0018#N?\u0016\fx\fR'C_>dw\fR8vE2,wl\u00149OKV\u0011\u0011Q\u0015\t\u0007\u00037CG\u0010`;\u0002E%l\u0007\u000f\\0Pa~#Uj\u0018#N?\u0016\fx\fR'C_>dwL\u00127pCR|v\n\u001d(f+\t\tY\u000b\u0005\u0005\u0002\u001c\"\f9!a\u0002v\u0003\u0005JW\u000e\u001d7`\u001fB|F)T0E\u001b~+\u0017o\u0018#N\u0005>|Gn\u0018'p]\u001e|v\n\u001d(f+\t\t\t\f\u0005\u0005\u0002\u001c\"\f)\"!\u0006v\u0003)JW\u000e\u001d7`\u001fB|F)T0N?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?&sGoX(q\u000fR+\"!a.\u0011\r\u0015Dg.!/v!\u0011y\u00171\u0018:\n\u0007\u0005uVJ\u0001\u0004NCR\u0014\u0018\u000e_\u0001.S6\u0004HnX(q?\u0012ku,T0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0E_V\u0014G.Z0Pa\u001e#VCAAb!\u0019)\u0007\u000e`AckB!q.a/~\u00031JW\u000e\u001d7`\u001fB|F)T0N?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?\u001acw.\u0019;`\u001fB<E+\u0006\u0002\u0002LB9Q\r[A\u0004\u0003\u001b,\b#B8\u0002<\u0006%\u0011aK5na2|v\n]0E\u001b~ku,Z9`\t6\u0013un\u001c7`\u0007>l\u0007/\u0019:jg>tw\fT8oO~{\u0005o\u0012+\u0016\u0005\u0005M\u0007cB3i\u0003+\t).\u001e\t\u0006_\u0006m\u0016qC\u0001,S6\u0004HnX(q?\u0012ku,T0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0J]R|v\n]$U\u000bV\u0011\u00111\u001c\t\b\u0003GAg.!/v\u00039JW\u000e\u001d7`\u001fB|F)T0N?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?\u0012{WO\u00197f?>\u0003x\tV#\u0016\u0005\u0005\u0005\bcBA\u0012Qr\f)-^\u0001.S6\u0004HnX(q?\u0012ku,T0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0GY>\fGoX(q\u000fR+UCAAt!!\t\u0019\u0003[A\u0004\u0003\u001b,\u0018\u0001L5na2|v\n]0E\u001b~ku,Z9`\t6\u0013un\u001c7`\u0007>l\u0007/\u0019:jg>tw\fT8oO~{\u0005o\u0012+F+\t\ti\u000f\u0005\u0005\u0002$!\f)\"!6v\u0003-JW\u000e\u001d7`\u001fB|F)T0N?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?&sGoX(q\u0019R+UCAAz!\u001d\t\t\u0005\u001b8\u0002:V\fa&[7qY~{\u0005o\u0018#N?6{V-]0E\u001b\n{w\u000e\\0D_6\u0004\u0018M]5t_:|Fi\\;cY\u0016|v\n\u001d'U\u000bV\u0011\u0011\u0011 \t\b\u0003\u0003BG0!2v\u00035JW\u000e\u001d7`\u001fB|F)T0N?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?\u001acw.\u0019;`\u001fBdE+R\u000b\u0003\u0003\u007f\u0004\u0002\"!\u0011i\u0003\u000f\ti-^\u0001-S6\u0004HnX(q?\u0012ku,T0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0M_:<wl\u00149M)\u0016+\"A!\u0002\u0011\u0011\u0005\u0005\u0003.!\u0006\u0002VV\f!&[7qY~{\u0005o\u0018#N?6{V-]0E\u001b\n{w\u000e\\0D_6\u0004\u0018M]5t_:|\u0016J\u001c;`\u001fBdE+\u0006\u0002\u0003\fA9\u0011q\f5o\u0003s+\u0018!L5na2|v\n]0E\u001b~ku,Z9`\t6\u0013un\u001c7`\u0007>l\u0007/\u0019:jg>tw\fR8vE2,wl\u00149M)V\u0011!\u0011\u0003\t\b\u0003?BG0!2v\u00031JW\u000e\u001d7`\u001fB|F)T0N?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?\u001acw.\u0019;`\u001fBdE+\u0006\u0002\u0003\u0018AA\u0011q\f5\u0002\b\u00055W/A\u0016j[Bdwl\u00149`\t6{VjX3r?\u0012k%i\\8m?\u000e{W\u000e]1sSN|gn\u0018'p]\u001e|v\n\u001d'U+\t\u0011i\u0002\u0005\u0005\u0002`!\f)\"!6v\u0003)JW\u000e\u001d7`\u001fB|F)T0N?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?&sGoX(q\u000bF,\"Aa\t\u0011\u000f\u0005u\u0004N\\A]k\u0006i\u0013.\u001c9m?>\u0003x\fR'`\u001b~+\u0017o\u0018#N\u0005>|GnX\"p[B\f'/[:p]~#u.\u001e2mK~{\u0005/R9\u0016\u0005\t%\u0002cBA?Qr\f)-^\u0001-S6\u0004HnX(q?\u0012ku,T0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0GY>\fGoX(q\u000bF,\"Aa\f\u0011\u0011\u0005u\u0004.a\u0002\u0002NV\f1&[7qY~{\u0005o\u0018#N?6{V-]0E\u001b\n{w\u000e\\0D_6\u0004\u0018M]5t_:|Fj\u001c8h?>\u0003X)]\u000b\u0003\u0005k\u0001\u0002\"! i\u0003+\t).^\u0001+S6\u0004HnX(q?\u0012ku,T0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0J]R|v\n\u001d(f+\t\u0011Y\u0004E\u0004\u0002\u001c\"t\u0017\u0011X;\u0002[%l\u0007\u000f\\0Pa~#UjX'`KF|F)\u0014\"p_2|6i\\7qCJL7o\u001c8`\t>,(\r\\3`\u001fBtU-\u0006\u0002\u0003BA9\u00111\u00145}\u0003\u000b,\u0018\u0001L5na2|v\n]0E\u001b~ku,Z9`\t6\u0013un\u001c7`\u0007>l\u0007/\u0019:jg>twL\u00127pCR|v\n\u001d(f+\t\u00119\u0005\u0005\u0005\u0002\u001c\"\f9!!4v\u0003-JW\u000e\u001d7`\u001fB|F)T0N?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?2{gnZ0Pa:+WC\u0001B'!!\tY\n[A\u000b\u0003+,\u0018AK5na2|v\n]0E\u001b~\u001bv,Z9`\t6\u0013un\u001c7`\u0007>l\u0007/\u0019:jg>tw,\u00138u?>\u0003x\tV\u000b\u0003\u0005'\u0002R!\u001a5oeV\fQ&[7qY~{\u0005o\u0018#N?N{V-]0E\u001b\n{w\u000e\\0D_6\u0004\u0018M]5t_:|Fi\\;cY\u0016|v\n]$U+\t\u0011I\u0006E\u0003fQrlX/\u0001\u0017j[Bdwl\u00149`\t6{6kX3r?\u0012k%i\\8m?\u000e{W\u000e]1sSN|gn\u0018$m_\u0006$xl\u00149H)V\u0011!q\f\t\bK\"\f9!!\u0003v\u0003-JW\u000e\u001d7`\u001fB|F)T0T?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?2{gnZ0Pa\u001e#VC\u0001B3!\u001d)\u0007.!\u0006\u0002\u0018U\f1&[7qY~{\u0005o\u0018#N?N{V-]0E\u001b\n{w\u000e\\0D_6\u0004\u0018M]5t_:|\u0016J\u001c;`\u001fB<E+R\u000b\u0003\u0005W\u0002b!a\ti]J,\u0018AL5na2|v\n]0E\u001b~\u001bv,Z9`\t6\u0013un\u001c7`\u0007>l\u0007/\u0019:jg>tw\fR8vE2,wl\u00149H)\u0016+\"A!\u001d\u0011\r\u0005\r\u0002\u000e`?v\u00035JW\u000e\u001d7`\u001fB|F)T0T?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?\u001acw.\u0019;`\u001fB<E+R\u000b\u0003\u0005o\u0002\u0002\"a\ti\u0003\u000f\tI!^\u0001-S6\u0004HnX(q?\u0012kulU0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0M_:<wl\u00149H)\u0016+\"A! \u0011\u0011\u0005\r\u0002.!\u0006\u0002\u0018U\f1&[7qY~{\u0005o\u0018#N?N{V-]0E\u001b\n{w\u000e\\0D_6\u0004\u0018M]5t_:|\u0016J\u001c;`\u001fBdE+R\u000b\u0003\u0005\u0007\u0003b!!\u0011i]J,\u0018AL5na2|v\n]0E\u001b~\u001bv,Z9`\t6\u0013un\u001c7`\u0007>l\u0007/\u0019:jg>tw\fR8vE2,wl\u00149M)\u0016+\"A!#\u0011\r\u0005\u0005\u0003\u000e`?v\u00035JW\u000e\u001d7`\u001fB|F)T0T?\u0016\fx\fR'C_>dwlQ8na\u0006\u0014\u0018n]8o?\u001acw.\u0019;`\u001fBdE+R\u000b\u0003\u0005\u001f\u0003\u0002\"!\u0011i\u0003\u000f\tI!^\u0001-S6\u0004HnX(q?\u0012kulU0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0M_:<wl\u00149M)\u0016+\"A!&\u0011\u0011\u0005\u0005\u0003.!\u0006\u0002\u0018U\f!&[7qY~{\u0005o\u0018#N?N{V-]0E\u001b\n{w\u000e\\0D_6\u0004\u0018M]5t_:|\u0016J\u001c;`\u001fBdE+\u0006\u0002\u0003\u001cB1\u0011q\f5oeV\fQ&[7qY~{\u0005o\u0018#N?N{V-]0E\u001b\n{w\u000e\\0D_6\u0004\u0018M]5t_:|Fi\\;cY\u0016|v\n\u001d'U+\t\u0011\t\u000b\u0005\u0004\u0002`!dX0^\u0001-S6\u0004HnX(q?\u0012kulU0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0GY>\fGoX(q\u0019R+\"Aa*\u0011\u0011\u0005}\u0003.a\u0002\u0002\nU\f1&[7qY~{\u0005o\u0018#N?N{V-]0E\u001b\n{w\u000e\\0D_6\u0004\u0018M]5t_:|Fj\u001c8h?>\u0003H\nV\u000b\u0003\u0005[\u0003\u0002\"a\u0018i\u0003+\t9\"^\u0001+S6\u0004HnX(q?\u0012kulU0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0J]R|v\n]#r+\t\u0011\u0019\f\u0005\u0004\u0002~!t'/^\u0001.S6\u0004HnX(q?\u0012kulU0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0E_V\u0014G.Z0Pa\u0016\u000bXC\u0001B]!\u0019\ti\b\u001b?~k\u0006a\u0013.\u001c9m?>\u0003x\fR'`'~+\u0017o\u0018#N\u0005>|GnX\"p[B\f'/[:p]~3En\\1u?>\u0003X)]\u000b\u0003\u0005\u007f\u0003\u0002\"! i\u0003\u000f\tI!^\u0001,S6\u0004HnX(q?\u0012kulU0fc~#UJQ8pY~\u001bu.\u001c9be&\u001cxN\\0M_:<wl\u00149FcV\u0011!Q\u0019\t\t\u0003{B\u0017QCA\fk\u0006Q\u0013.\u001c9m?>\u0003x\fR'`'~+\u0017o\u0018#N\u0005>|GnX\"p[B\f'/[:p]~Ke\u000e^0Pa:+WC\u0001Bf!\u0019\tY\n\u001b8sk\u0006i\u0013.\u001c9m?>\u0003x\fR'`'~+\u0017o\u0018#N\u0005>|GnX\"p[B\f'/[:p]~#u.\u001e2mK~{\u0005OT3\u0016\u0005\tE\u0007CBANQrlX/\u0001\u0017j[Bdwl\u00149`\t6{6kX3r?\u0012k%i\\8m?\u000e{W\u000e]1sSN|gn\u0018$m_\u0006$xl\u00149OKV\u0011!q\u001b\t\t\u00037C\u0017qAA\u0005k\u0006Y\u0013.\u001c9m?>\u0003x\fR'`'~+\u0017o\u0018#N\u0005>|GnX\"p[B\f'/[:p]~cuN\\4`\u001fBtU-\u0006\u0002\u0003^BA\u00111\u00145\u0002\u0016\u0005]Q\u000f"
)
public interface DenseMatrix_ComparisonOps extends DenseMatrixExpandedOps {
   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Int_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) > b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Double_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) > b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Float_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) > b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Long_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) > b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Int_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) >= b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Double_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) >= b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Float_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) >= b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Long_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) >= b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Int_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) <= b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Double_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) <= b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Float_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) <= b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Long_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) <= b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Int_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) < b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Double_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) < b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Float_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) < b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Long_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) < b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Int_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) == b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Double_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) == b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Float_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) == b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Long_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) == b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Int_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Int_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) != b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Double_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Double_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) != b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Float_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Float_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) != b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_DM_eq_DMBool_Long_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_DM_eq_DMBool_Long_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final DenseMatrix b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), (DenseMatrix)b.t(HasOps$.MODULE$.canTranspose_DM())).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               if (a.rows() != b.rows()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
               }

               if (a.cols() != b.cols()) {
                  throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
               }

               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) != b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) > b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) > b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) > b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) > b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) >= b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) >= b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) >= b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpGTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) >= b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) <= b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) <= b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) <= b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLTE$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) <= b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) < b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) < b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) < b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpLT$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) < b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) == b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) == b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) == b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpEq$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) == b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Int_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Int_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) != b.apply$mcI$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Double_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Double_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) != b.apply$mcD$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Float_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Float_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) != b.apply$mcF$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_M_eq_DMBool_Comparison_Long_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_M_eq_DMBool_Comparison_Long_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final Matrix b) {
            if (a.rows() != b.rows()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Rows don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.rows()).append(" ").append(b.rows()).toString());
            } else if (a.cols() != b.cols()) {
               throw new ArrayIndexOutOfBoundsException((new StringBuilder(32)).append("Cols don't match for operator ").append(OpNe$.MODULE$).append(" ").append(a.cols()).append(" ").append(b.cols()).toString());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) != b.apply$mcJ$sp(i, j)))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               return result;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final int b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) > b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final double b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) > b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final float b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) > b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGT();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final long b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) > b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final int b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) >= b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final double b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) >= b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final float b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) >= b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGTE();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpGTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final long b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) >= b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final int b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) <= b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final double b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) <= b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final float b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) <= b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLTE$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLTE();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLTE() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final long b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) <= b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final int b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) < b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final double b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) < b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final float b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) < b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLT$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLT();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpLT() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final long b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) < b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final int b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) == b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final double b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) == b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final float b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) == b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpEq$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpEq();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpEq() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final long b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) == b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Int_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Int_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final int b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcI$sp(i, j) != b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Double_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Double_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final double b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcD$sp(i, j) != b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Float_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Float_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final float b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcF$sp(i, j) != b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpNe$(final DenseMatrix_ComparisonOps $this) {
      return $this.impl_Op_DM_S_eq_DMBool_Comparison_Long_OpNe();
   }

   default UFunc.UImpl2 impl_Op_DM_S_eq_DMBool_Comparison_Long_OpNe() {
      return new UFunc.UImpl2() {
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

         public DenseMatrix apply(final DenseMatrix a, final long b) {
            DenseMatrix var10000;
            if (a.isTranspose()) {
               var10000 = (DenseMatrix)this.apply((DenseMatrix)a.t(HasOps$.MODULE$.canTranspose_DM()), b).t(HasOps$.MODULE$.canTranspose_DM());
            } else {
               DenseMatrix result = DenseMatrix$.MODULE$.zeros(a.rows(), a.cols(), .MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               int index$macro$7 = 0;

               for(int limit$macro$9 = a.cols(); index$macro$7 < limit$macro$9; ++index$macro$7) {
                  int index$macro$2 = 0;

                  for(int limit$macro$4 = a.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                     ((j, i) -> result.update(i, j, BoxesRunTime.boxToBoolean(a.apply$mcJ$sp(i, j) != b))).apply$mcVII$sp(index$macro$7, index$macro$2);
                  }
               }

               var10000 = result;
            }

            return var10000;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final DenseMatrix_ComparisonOps $this) {
   }
}
