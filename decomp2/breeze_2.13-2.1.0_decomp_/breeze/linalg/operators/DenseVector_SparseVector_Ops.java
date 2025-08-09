package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.CSCMatrix;
import breeze.linalg.CSCMatrix$Builder$mcD$sp;
import breeze.linalg.CSCMatrix$Builder$mcF$sp;
import breeze.linalg.CSCMatrix$Builder$mcI$sp;
import breeze.linalg.CSCMatrix$Builder$mcJ$sp;
import breeze.linalg.DenseVector;
import breeze.linalg.SparseVector;
import breeze.linalg.Transpose;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.ZippedValues;
import breeze.linalg.ZippedValues$mcDD$sp;
import breeze.math.Complex;
import breeze.math.Complex$;
import breeze.math.PowImplicits$;
import breeze.math.Semiring$;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rfaB\u001c9!\u0003\r\ta\u0010\u0005\u0006\u001b\u0002!\tA\u0014\u0005\b%\u0002\u0011\r\u0011b\u0001T\u0011\u001dA\u0007A1A\u0005\u0004%Dq\u0001\u001d\u0001C\u0002\u0013\r\u0011\u000fC\u0004y\u0001\t\u0007I1A=\t\u0013\u0005\u0005\u0001A1A\u0005\u0004\u0005\r\u0001\"CA\u0007\u0001\t\u0007I1AA\b\u0011%\t\u0019\u0002\u0001b\u0001\n\u0007\t)\u0002C\u0005\u0002\u001a\u0001\u0011\r\u0011b\u0001\u0002\u001c!I\u0011q\u0004\u0001C\u0002\u0013\r\u0011\u0011\u0005\u0005\n\u0003W\u0001!\u0019!C\u0002\u0003[A\u0011\"!\r\u0001\u0005\u0004%\u0019!a\r\t\u0013\u0005]\u0002A1A\u0005\u0004\u0005e\u0002\"CA\u001f\u0001\t\u0007I1AA \u0011%\tI\u0005\u0001b\u0001\n\u0007\tY\u0005C\u0005\u0002P\u0001\u0011\r\u0011b\u0001\u0002R!I\u0011Q\u000b\u0001C\u0002\u0013\r\u0011q\u000b\u0005\n\u00037\u0002!\u0019!C\u0002\u0003;B\u0011\"a\u001a\u0001\u0005\u0004%\u0019!!\u001b\t\u0013\u00055\u0004A1A\u0005\u0004\u0005=\u0004\"CA:\u0001\t\u0007I1AA;\u0011%\tI\b\u0001b\u0001\n\u0007\tY\bC\u0005\u0002\u0006\u0002\u0011\r\u0011b\u0001\u0002\b\"I\u00111\u0012\u0001C\u0002\u0013\r\u0011Q\u0012\u0005\n\u0003#\u0003!\u0019!C\u0002\u0003'C\u0011\"a&\u0001\u0005\u0004%\u0019!!'\t\u0013\u0005\r\u0006A1A\u0005\u0004\u0005\u0015\u0006\"CAU\u0001\t\u0007I1AAV\u0011%\ty\u000b\u0001b\u0001\n\u0007\t\t\fC\u0005\u00026\u0002\u0011\r\u0011b\u0001\u00028\"I\u0011q\u0018\u0001C\u0002\u0013\r\u0011\u0011\u0019\u0005\n\u0003\u000b\u0004!\u0019!C\u0002\u0003\u000fD\u0011\"a3\u0001\u0005\u0004%\u0019!!4\t\u0013\u0005E\u0007A1A\u0005\u0004\u0005M\u0007\"CAl\u0001\t\u0007I1AAm\u0011%\ti\u000e\u0001b\u0001\n\u0007\ty\u000eC\u0005\u0002d\u0002\u0011\r\u0011b\u0001\u0002f\"I\u0011\u0011\u001e\u0001C\u0002\u0013\r\u00111\u001e\u0005\n\u0003k\u0004!\u0019!C\u0002\u0003oD\u0011\"a?\u0001\u0005\u0004%\u0019!!@\t\u0013\t\u0005\u0001A1A\u0005\u0004\t\r\u0001\"\u0003B\u0004\u0001\t\u0007I1\u0001B\u0005\u0011%\u0011I\u0002\u0001b\u0001\n\u0007\u0011Y\u0002C\u0005\u0003\"\u0001\u0011\r\u0011b\u0001\u0003$!I!\u0011\u0006\u0001C\u0002\u0013\r!1\u0006\u0005\n\u0005c\u0001!\u0019!C\u0002\u0005gA\u0011B!\u0011\u0001\u0005\u0004%\u0019Aa\u0011\t\u0013\t\u001d\u0003A1A\u0005\u0004\t%\u0003\"\u0003B'\u0001\t\u0007I1\u0001B(\u0011%\u0011\u0019\u0006\u0001b\u0001\n\u0007\u0011)\u0006C\u0005\u0003l\u0001\u0011\r\u0011b\u0001\u0003n!I!Q\u000f\u0001C\u0002\u0013\r!q\u000f\u0005\n\u0005\u007f\u0002!\u0019!C\u0002\u0005\u0003C\u0011B!#\u0001\u0005\u0004%\u0019Aa#\u00039\u0011+gn]3WK\u000e$xN]0Ta\u0006\u00148/\u001a,fGR|'oX(qg*\u0011\u0011HO\u0001\n_B,'/\u0019;peNT!a\u000f\u001f\u0002\r1Lg.\u00197h\u0015\u0005i\u0014A\u00022sK\u0016TXm\u0001\u0001\u0014\t\u0001\u0001eI\u0013\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0002\u0007\u0006)1oY1mC&\u0011QI\u0011\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u001dCU\"\u0001\u001d\n\u0005%C$A\u0004#f]N,g+Z2u_J|\u0005o\u001d\t\u0003\u000f.K!\u0001\u0014\u001d\u0003+M\u0003\u0018M]:f-\u0016\u001cGo\u001c:FqB\fg\u000eZ(qg\u00061A%\u001b8ji\u0012\"\u0012a\u0014\t\u0003\u0003BK!!\u0015\"\u0003\tUs\u0017\u000e^\u0001&S6\u0004HnX(q?\u00123vl\u0015,`\u0013:\u0004F.Y2f?&sGoX(q\u001bVd7kY1mCJ,\u0012\u0001\u0016\t\u0005+bsVM\u0004\u0002H-&\u0011q\u000bO\u0001\f\u001fBlU\u000f\\*dC2\f'/\u0003\u0002Z5\na\u0011J\u001c)mC\u000e,\u0017*\u001c9me%\u00111\f\u0018\u0002\u0006+\u001a+hn\u0019\u0006\u0003;r\nqaZ3oKJL7\rE\u0002`A\nl\u0011AO\u0005\u0003Cj\u00121\u0002R3og\u00164Vm\u0019;peB\u0011\u0011iY\u0005\u0003I\n\u00131!\u00138u!\ryfMY\u0005\u0003Oj\u0012Ab\u00159beN,g+Z2u_J\f\u0001&[7qY~{\u0005o\u0018#W?N3v,\u00138QY\u0006\u001cWm\u0018#pk\ndWmX(q\u001bVd7kY1mCJ,\u0012A\u001b\t\u0005+b[w\u000eE\u0002`A2\u0004\"!Q7\n\u00059\u0014%A\u0002#pk\ndW\rE\u0002`M2\fq%[7qY~{\u0005o\u0018#W?N3v,\u00138QY\u0006\u001cWm\u0018$m_\u0006$xl\u00149Nk2\u001c6-\u00197beV\t!\u000f\u0005\u0003V1N<\bcA0aiB\u0011\u0011)^\u0005\u0003m\n\u0013QA\u00127pCR\u00042a\u00184u\u0003\u0019JW\u000e\u001d7`\u001fB|FIV0T-~Ke\u000e\u00157bG\u0016|Fj\u001c8h?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0002uB!Q\u000bW>\u0000!\ry\u0006\r \t\u0003\u0003vL!A \"\u0003\t1{gn\u001a\t\u0004?\u001ad\u0018aH5na2|v\n]0E-~\u001bfkX%o!2\f7-Z0J]R|v\n\u001d#jmV\u0011\u0011Q\u0001\t\u0006\u0003\u000fAf,\u001a\b\u0004\u000f\u0006%\u0011bAA\u0006q\u0005)q\n\u001d#jm\u0006\u0011\u0013.\u001c9m?>\u0003x\f\u0012,`'Z{\u0016J\u001c)mC\u000e,w\fR8vE2,wl\u00149ESZ,\"!!\u0005\u0011\u000b\u0005\u001d\u0001l[8\u0002C%l\u0007\u000f\\0Pa~#ekX*W?&s\u0007\u000b\\1dK~3En\\1u?>\u0003H)\u001b<\u0016\u0005\u0005]\u0001#BA\u00041N<\u0018\u0001I5na2|v\n]0E-~\u001bfkX%o!2\f7-Z0M_:<wl\u00149ESZ,\"!!\b\u0011\u000b\u0005\u001d\u0001l_@\u0002?%l\u0007\u000f\\0Pa~#ekX*W?&s\u0007\u000b\\1dK~Ke\u000e^0PaN+G/\u0006\u0002\u0002$A)\u0011Q\u0005-_K:\u0019q)a\n\n\u0007\u0005%\u0002(A\u0003PaN+G/\u0001\u0012j[Bdwl\u00149`\tZ{6KV0J]Bc\u0017mY3`\t>,(\r\\3`\u001fB\u001cV\r^\u000b\u0003\u0003_\u0001R!!\nYW>\f\u0011%[7qY~{\u0005o\u0018#W?N3v,\u00138QY\u0006\u001cWm\u0018$m_\u0006$xl\u00149TKR,\"!!\u000e\u0011\u000b\u0005\u0015\u0002l]<\u0002A%l\u0007\u000f\\0Pa~#ekX*W?&s\u0007\u000b\\1dK~cuN\\4`\u001fB\u001cV\r^\u000b\u0003\u0003w\u0001R!!\nYw~\fq$[7qY~{\u0005o\u0018#W?N3v,\u00138QY\u0006\u001cWmX%oi~{\u0005/T8e+\t\t\t\u0005E\u0003\u0002DasVMD\u0002H\u0003\u000bJ1!a\u00129\u0003\u0015y\u0005/T8e\u0003\tJW\u000e\u001d7`\u001fB|FIV0T-~Ke\u000e\u00157bG\u0016|Fi\\;cY\u0016|v\n]'pIV\u0011\u0011Q\n\t\u0006\u0003\u0007B6n\\\u0001\"S6\u0004HnX(q?\u00123vl\u0015,`\u0013:\u0004F.Y2f?\u001acw.\u0019;`\u001fBlu\u000eZ\u000b\u0003\u0003'\u0002R!a\u0011Yg^\f\u0001%[7qY~{\u0005o\u0018#W?N3v,\u00138QY\u0006\u001cWm\u0018'p]\u001e|v\n]'pIV\u0011\u0011\u0011\f\t\u0006\u0003\u0007B6p`\u0001 S6\u0004HnX(q?\u00123vl\u0015,`\u0013:\u0004F.Y2f?&sGoX(q!><XCAA0!\u0015\t\t\u0007\u00170f\u001d\r9\u00151M\u0005\u0004\u0003KB\u0014!B(q!><\u0018AI5na2|v\n]0E-~\u001bfkX%o!2\f7-Z0E_V\u0014G.Z0PaB{w/\u0006\u0002\u0002lA)\u0011\u0011\r-l_\u0006\t\u0013.\u001c9m?>\u0003x\f\u0012,`'Z{\u0016J\u001c)mC\u000e,wL\u00127pCR|v\n\u001d)poV\u0011\u0011\u0011\u000f\t\u0006\u0003CB6o^\u0001!S6\u0004HnX(q?\u00123vl\u0015,`\u0013:\u0004F.Y2f?2{gnZ0PaB{w/\u0006\u0002\u0002xA)\u0011\u0011\r-|\u007f\u0006y\u0012.\u001c9m?>\u0003x\f\u0012,`'Z{\u0016J\u001c)mC\u000e,w,\u00138u?>\u0003\u0018\t\u001a3\u0016\u0005\u0005u\u0004#BA@1z+gbA$\u0002\u0002&\u0019\u00111\u0011\u001d\u0002\u000b=\u0003\u0018\t\u001a3\u0002E%l\u0007\u000f\\0Pa~#ekX*W?&s\u0007\u000b\\1dK~#u.\u001e2mK~{\u0005/\u00113e+\t\tI\tE\u0003\u0002\u0000a[w.A\u0011j[Bdwl\u00149`\tZ{6KV0J]Bc\u0017mY3`\r2|\u0017\r^0Pa\u0006#G-\u0006\u0002\u0002\u0010B)\u0011q\u0010-to\u0006\u0001\u0013.\u001c9m?>\u0003x\f\u0012,`'Z{\u0016J\u001c)mC\u000e,w\fT8oO~{\u0005/\u00113e+\t\t)\nE\u0003\u0002\u0000a[x0A\u0010j[Bdwl\u00149`\tZ{6KV0J]Bc\u0017mY3`\u0013:$xl\u00149Tk\n,\"!a'\u0011\u000b\u0005u\u0005LX3\u000f\u0007\u001d\u000by*C\u0002\u0002\"b\nQa\u00149Tk\n\f!%[7qY~{\u0005o\u0018#W?N3v,\u00138QY\u0006\u001cWm\u0018#pk\ndWmX(q'V\u0014WCAAT!\u0015\ti\nW6p\u0003\u0005JW\u000e\u001d7`\u001fB|FIV0T-~Ke\u000e\u00157bG\u0016|f\t\\8bi~{\u0005oU;c+\t\ti\u000bE\u0003\u0002\u001eb\u001bx/\u0001\u0011j[Bdwl\u00149`\tZ{6KV0J]Bc\u0017mY3`\u0019>twmX(q'V\u0014WCAAZ!\u0015\ti\nW>\u0000\u0003\rJW\u000e\u001d7`\u001fB|FIV0T-~+\u0017oX*W?&sGoX(q\u001bVd7kY1mCJ,\"!!/\u0011\rU\u000bYLX3f\u0013\r\tiL\u0017\u0002\u0006\u00136\u0004HNM\u0001'S6\u0004HnX(q?\u00123vl\u0015,`KF|6KV0E_V\u0014G.Z0Pa6+HnU2bY\u0006\u0014XCAAb!\u0019)\u00161X6p_\u0006)\u0013.\u001c9m?>\u0003x\f\u0012,`'Z{V-]0T-~3En\\1u?>\u0003X*\u001e7TG\u0006d\u0017M]\u000b\u0003\u0003\u0013\u0004b!VA^g^<\u0018\u0001J5na2|v\n]0E-~\u001bfkX3r?N3v\fT8oO~{\u0005/T;m'\u000e\fG.\u0019:\u0016\u0005\u0005=\u0007CB+\u0002<n|x0A\u000fj[Bdwl\u00149`\tZ{6KV0fc~\u001bfkX%oi~{\u0005\u000fR5w+\t\t)\u000eE\u0004\u0002\b\u0005mf,Z3\u0002A%l\u0007\u000f\\0Pa~#ekX*W?\u0016\fxl\u0015,`\t>,(\r\\3`\u001fB$\u0015N^\u000b\u0003\u00037\u0004r!a\u0002\u0002<.|w.A\u0010j[Bdwl\u00149`\tZ{6KV0fc~\u001bfk\u0018$m_\u0006$xl\u00149ESZ,\"!!9\u0011\u000f\u0005\u001d\u00111X:xo\u0006q\u0012.\u001c9m?>\u0003x\f\u0012,`'Z{V-]0T-~cuN\\4`\u001fB$\u0015N^\u000b\u0003\u0003O\u0004r!a\u0002\u0002<n|x0\u0001\u0010j[Bdwl\u00149Nk2LeN\\3s?\u00123vl\u0015,`KF|FkX%oiV\u0011\u0011Q\u001e\t\b\u0003_\fYLX3c\u001d\r9\u0015\u0011_\u0005\u0004\u0003gD\u0014AC(q\u001bVd\u0017J\u001c8fe\u0006\t\u0013.\u001c9m?>\u0003X*\u001e7J]:,'o\u0018#W?N3v,Z9`)~#u.\u001e2mKV\u0011\u0011\u0011 \t\b\u0003_\fYl[8m\u0003\u0001JW\u000e\u001d7`\u001fBlU\u000f\\%o]\u0016\u0014x\f\u0012,`'Z{V-]0U?\u001acw.\u0019;\u0016\u0005\u0005}\bcBAx\u0003w\u001bx\u000f^\u0001 S6\u0004HnX(q\u001bVd\u0017J\u001c8fe~#ekX*W?\u0016\fx\fV0M_:<WC\u0001B\u0003!\u001d\ty/a/|\u007fr\fa$[7qY~S\u0018\u000e\u001d,bYV,7o\u0018#W?N3v,Z9`5Z{\u0016J\u001c;\u0016\u0005\t-\u0001\u0003\u0003B\u0007\u0003wsVMa\u0005\u000f\u0007}\u0013y!C\u0002\u0003\u0012i\n\u0011B_5q-\u0006dW/Z:\u0011\u000b}\u0013)B\u00192\n\u0007\t]!H\u0001\u0007[SB\u0004X\r\u001a,bYV,7/A\u0011j[BdwL_5q-\u0006dW/Z:`\tZ{6KV0fc~Sfk\u0018#pk\ndW-\u0006\u0002\u0003\u001eAA!QBA^W>\u0014y\u0002E\u0003`\u0005+aG.\u0001\u0011j[BdwL_5q-\u0006dW/Z:`\tZ{6KV0fc~Sfk\u0018$m_\u0006$XC\u0001B\u0013!!\u0011i!a/to\n\u001d\u0002#B0\u0003\u0016Q$\u0018aH5na2|&0\u001b9WC2,Xm]0E-~\u001bfkX3r?j3v\fT8oOV\u0011!Q\u0006\t\t\u0005\u001b\tYl_@\u00030A)qL!\u0006}y\u0006\u0001\u0013.\u001c9m'\u000e\fG.Z!eI~#ekX*`'Z{\u0016J\u001c)mC\u000e,w,\u00138u+\t\u0011)\u0004E\u0004\u00038\tubLY3\u000f\u0007}\u0013I$C\u0002\u0003<i\n\u0001b]2bY\u0016\fE\rZ\u0005\u0004\u0005\u007fQ&\u0001D%o!2\f7-Z%na2\u001c\u0014aI5na2\u001c6-\u00197f\u0003\u0012$w\f\u0012,`'~\u001bfkX%o!2\f7-Z0E_V\u0014G.Z\u000b\u0003\u0005\u000b\u0002rAa\u000e\u0003>-dw.\u0001\u0012j[Bd7kY1mK\u0006#Gm\u0018#W?N{6KV0J]Bc\u0017mY3`\r2|\u0017\r^\u000b\u0003\u0005\u0017\u0002rAa\u000e\u0003>M$x/A\u0011j[Bd7kY1mK\u0006#Gm\u0018#W?N{6KV0J]Bc\u0017mY3`\u0019>tw-\u0006\u0002\u0003RA9!q\u0007B\u001fwr|\u0018AI5na2|v\n]'vY6\u000bGO]5y?\u00123vl\u0015,u?\u0016\fxlQ*D?&sG/\u0006\u0002\u0003XAI!\u0011LA^=\n}#Q\r\b\u0004\u000f\nm\u0013b\u0001B/q\u0005Yq\n]'vY6\u000bGO]5y!\u0011y&\u0011M3\n\u0007\t\r$HA\u0005Ue\u0006t7\u000f]8tKB!qLa\u001ac\u0013\r\u0011IG\u000f\u0002\n\u0007N\u001bU*\u0019;sSb\fQ%[7qY~{\u0005/T;m\u001b\u0006$(/\u001b=`\tZ{6K\u0016;`KF|6iU\"`\t>,(\r\\3\u0016\u0005\t=\u0004#\u0003B-\u0003w['\u0011\u000fB:!\u0011y&\u0011M8\u0011\t}\u00139\u0007\\\u0001%S6\u0004HnX(q\u001bVdW*\u0019;sSb|FIV0T-R|V-]0D'\u000e{f\t\\8biV\u0011!\u0011\u0010\t\n\u00053\nYl\u001dB>\u0005{\u0002Ba\u0018B1oB!qLa\u001au\u0003\rJW\u000e\u001d7`\u001fBlU\u000f\\'biJL\u0007p\u0018#W?N3FoX3r?\u000e\u001b6i\u0018'p]\u001e,\"Aa!\u0011\u0013\te\u00131X>\u0003\u0006\n\u001d\u0005\u0003B0\u0003b}\u0004Ba\u0018B4y\u00061\u0013.\u001c9m?>\u0003X*\u001e7NCR\u0014\u0018\u000e_0E-~\u001bf\u000b^0fc~\u001b5kQ0D_6\u0004H.\u001a=\u0016\u0005\t5\u0005C\u0003B-\u0003w\u0013yI!(\u0003\"B!q\f\u0019BI!\u0011\u0011\u0019J!'\u000e\u0005\tU%b\u0001BLy\u0005!Q.\u0019;i\u0013\u0011\u0011YJ!&\u0003\u000f\r{W\u000e\u001d7fqB)qL!\u0019\u0003 B!qL\u001aBI!\u0015y&q\rBI\u0001"
)
public interface DenseVector_SparseVector_Ops extends DenseVectorOps, SparseVectorExpandOps {
   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpMulScalar_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpDiv_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpSet_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpMod_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpPow_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpAdd_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpSub_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Int_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Double_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Float_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Long_OpMulScalar_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Int_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Double_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Float_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Long_OpDiv_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Int_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Double_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Float_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Long_$eq(final UFunc.InPlaceImpl3 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Int_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Double_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Float_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Long_$eq(final UFunc.UImpl2 x$1);

   void breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Complex_$eq(final UFunc.UImpl2 x$1);

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpMulScalar();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpDiv();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpSet();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpSet();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpSet();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpSet();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpMod();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpMod();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpMod();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpMod();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpPow();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpPow();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpPow();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpPow();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpAdd();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Int_OpSub();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Double_OpSub();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Float_OpSub();

   UFunc.InPlaceImpl2 impl_Op_DV_SV_InPlace_Long_OpSub();

   UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Int_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Double_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Float_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Long_OpMulScalar();

   UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Int_OpDiv();

   UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Double_OpDiv();

   UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Float_OpDiv();

   UFunc.UImpl2 impl_Op_DV_SV_eq_SV_Long_OpDiv();

   UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Int();

   UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Double();

   UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Float();

   UFunc.UImpl2 impl_OpMulInner_DV_SV_eq_T_Long();

   UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Int();

   UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Double();

   UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Float();

   UFunc.UImpl2 impl_zipValues_DV_SV_eq_ZV_Long();

   UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Int();

   UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Double();

   UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Float();

   UFunc.InPlaceImpl3 implScaleAdd_DV_S_SV_InPlace_Long();

   UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Int();

   UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Double();

   UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Float();

   UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Long();

   UFunc.UImpl2 impl_OpMulMatrix_DV_SVt_eq_CSC_Complex();

   static void $init$(final DenseVector_SparseVector_Ops $this) {
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int bdefault = b.array$mcI$sp().default$mcI$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               int[] bd = b.data$mcI$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] *= bdefault;
                  }

                  ad[aoff] *= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] *= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Int_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double bdefault = b.array$mcD$sp().default$mcD$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               double[] bd = b.data$mcD$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] *= bdefault;
                  }

                  ad[aoff] *= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] *= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Double_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float bdefault = b.array$mcF$sp().default$mcF$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               float[] bd = b.data$mcF$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] *= bdefault;
                  }

                  ad[aoff] *= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] *= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Float_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpMulScalar_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long bdefault = b.array$mcJ$sp().default$mcJ$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               long[] bd = b.data$mcJ$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] *= bdefault;
                  }

                  ad[aoff] *= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] *= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Long_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpMulScalar())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int bdefault = b.array$mcI$sp().default$mcI$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               int[] bd = b.data$mcI$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] /= bdefault;
                  }

                  ad[aoff] /= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] /= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double bdefault = b.array$mcD$sp().default$mcD$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               double[] bd = b.data$mcD$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] /= bdefault;
                  }

                  ad[aoff] /= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] /= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float bdefault = b.array$mcF$sp().default$mcF$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               float[] bd = b.data$mcF$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] /= bdefault;
                  }

                  ad[aoff] /= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] /= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpDiv_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long bdefault = b.array$mcJ$sp().default$mcJ$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               long[] bd = b.data$mcJ$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] /= bdefault;
                  }

                  ad[aoff] /= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] /= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int bdefault = b.array$mcI$sp().default$mcI$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               int[] bd = b.data$mcI$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] = bdefault;
                  }

                  ad[aoff] = bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] = bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double bdefault = b.array$mcD$sp().default$mcD$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               double[] bd = b.data$mcD$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] = bdefault;
                  }

                  ad[aoff] = bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] = bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float bdefault = b.array$mcF$sp().default$mcF$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               float[] bd = b.data$mcF$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] = bdefault;
                  }

                  ad[aoff] = bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] = bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpSet_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long bdefault = b.array$mcJ$sp().default$mcJ$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               long[] bd = b.data$mcJ$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] = bdefault;
                  }

                  ad[aoff] = bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] = bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpSet())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int bdefault = b.array$mcI$sp().default$mcI$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               int[] bd = b.data$mcI$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] %= bdefault;
                  }

                  ad[aoff] %= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] %= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double bdefault = b.array$mcD$sp().default$mcD$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               double[] bd = b.data$mcD$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] %= bdefault;
                  }

                  ad[aoff] %= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] %= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float bdefault = b.array$mcF$sp().default$mcF$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               float[] bd = b.data$mcF$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] %= bdefault;
                  }

                  ad[aoff] %= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] %= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpMod_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long bdefault = b.array$mcJ$sp().default$mcJ$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               long[] bd = b.data$mcJ$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] %= bdefault;
                  }

                  ad[aoff] %= bd[i];
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] %= bdefault;
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpMod())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int bdefault = b.array$mcI$sp().default$mcI$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               int[] bd = b.data$mcI$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] = PowImplicits$.MODULE$.IntPow(ad[aoff]).pow(bdefault);
                  }

                  ad[aoff] = PowImplicits$.MODULE$.IntPow(ad[aoff]).pow(bd[i]);
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] = PowImplicits$.MODULE$.IntPow(ad[aoff]).pow(bdefault);
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Int_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double bdefault = b.array$mcD$sp().default$mcD$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               double[] bd = b.data$mcD$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] = PowImplicits$.MODULE$.DoublePow(ad[aoff]).pow(bdefault);
                  }

                  ad[aoff] = PowImplicits$.MODULE$.DoublePow(ad[aoff]).pow(bd[i]);
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] = PowImplicits$.MODULE$.DoublePow(ad[aoff]).pow(bdefault);
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Double_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float bdefault = b.array$mcF$sp().default$mcF$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               float[] bd = b.data$mcF$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] = PowImplicits$.MODULE$.FloatPow(ad[aoff]).pow(bdefault);
                  }

                  ad[aoff] = PowImplicits$.MODULE$.FloatPow(ad[aoff]).pow(bd[i]);
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] = PowImplicits$.MODULE$.FloatPow(ad[aoff]).pow(bdefault);
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Float_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpPow_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long bdefault = b.array$mcJ$sp().default$mcJ$sp();
               int aoff = a.offset();
               int bsize = b.activeSize();
               int astride = a.stride();
               long[] bd = b.data$mcJ$sp();
               int[] bi = b.index();

               for(int i = 0; i < bsize; ++i) {
                  for(int nextAoff = a.offset() + bi[i] * astride; aoff < nextAoff; aoff += astride) {
                     ad[aoff] = PowImplicits$.MODULE$.LongPow(ad[aoff]).pow(bdefault);
                  }

                  ad[aoff] = PowImplicits$.MODULE$.LongPow(ad[aoff]).pow(bd[i]);
                  aoff += a.stride();
               }

               while(aoff < ad.length) {
                  ad[aoff] = PowImplicits$.MODULE$.LongPow(ad[aoff]).pow(bdefault);
                  aoff += astride;
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Long_OpPow())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int[] bi = b.index();
               int bsize = b.iterableSize();

               for(int i = 0; i < bsize; ++i) {
                  int aoff = a.offset() + bi[i] * a.stride();
                  ad[aoff] += bd[i];
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_zero_idempotent_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Int_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int[] bi = b.index();
               int bsize = b.iterableSize();

               for(int i = 0; i < bsize; ++i) {
                  int aoff = a.offset() + bi[i] * a.stride();
                  ad[aoff] += bd[i];
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_zero_idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Double_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int[] bi = b.index();
               int bsize = b.iterableSize();

               for(int i = 0; i < bsize; ++i) {
                  int aoff = a.offset() + bi[i] * a.stride();
                  ad[aoff] += bd[i];
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_zero_idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Float_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpAdd_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int[] bi = b.index();
               int bsize = b.iterableSize();

               for(int i = 0; i < bsize; ++i) {
                  int aoff = a.offset() + bi[i] * a.stride();
                  ad[aoff] += bd[i];
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_zero_idempotent_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Long_OpAdd())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Int_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int[] ad = a.data$mcI$sp();
               int[] bd = b.data$mcI$sp();
               int[] bi = b.index();
               int bsize = b.iterableSize();

               for(int i = 0; i < bsize; ++i) {
                  int aoff = a.offset() + bi[i] * a.stride();
                  ad[aoff] -= bd[i];
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_zero_idempotent_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Int_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Double_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               double[] ad = a.data$mcD$sp();
               double[] bd = b.data$mcD$sp();
               int[] bi = b.index();
               int bsize = b.iterableSize();

               for(int i = 0; i < bsize; ++i) {
                  int aoff = a.offset() + bi[i] * a.stride();
                  ad[aoff] -= bd[i];
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_zero_idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Double_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Float_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               float[] ad = a.data$mcF$sp();
               float[] bd = b.data$mcF$sp();
               int[] bi = b.index();
               int bsize = b.iterableSize();

               for(int i = 0; i < bsize; ++i) {
                  int aoff = a.offset() + bi[i] * a.stride();
                  ad[aoff] -= bd[i];
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_zero_idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Float_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_InPlace_Long_OpSub_$eq(new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               long[] ad = a.data$mcJ$sp();
               long[] bd = b.data$mcJ$sp();
               int[] bi = b.index();
               int bsize = b.iterableSize();

               for(int i = 0; i < bsize; ++i) {
                  int aoff = a.offset() + bi[i] * a.stride();
                  ad[aoff] -= bd[i];
               }

            }
         }

         public {
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_DV_V_zero_idempotent_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
            ((BinaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_InPlace_V_V_Idempotent_Long_OpSub())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Int_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mIc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = b.indexAt(index$macro$4);
                  int res = a.apply$mcI$sp(ind) * b.valueAt$mcI$sp(index$macro$4);
                  if (res != 0) {
                     result.add$mcI$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Double_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mDc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = b.indexAt(index$macro$4);
                  double res = a.apply$mcD$sp(ind) * b.valueAt$mcD$sp(index$macro$4);
                  if (res != (double)0) {
                     result.add$mcD$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Float_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mFc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = b.indexAt(index$macro$4);
                  float res = a.apply$mcF$sp(ind) * b.valueAt$mcF$sp(index$macro$4);
                  if (res != (float)0) {
                     result.add$mcF$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Long_OpMulScalar_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mJc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = b.indexAt(index$macro$4);
                  long res = a.apply$mcJ$sp(ind) * b.valueAt$mcJ$sp(index$macro$4);
                  if (res != 0L) {
                     result.add$mcJ$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_nilpotent_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Int_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mIc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = b.indexAt(index$macro$4);
                  int res = a.apply$mcI$sp(ind) / b.valueAt$mcI$sp(index$macro$4);
                  if (res != 0) {
                     result.add$mcI$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcI$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Int_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Double_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mDc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = b.indexAt(index$macro$4);
                  double res = a.apply$mcD$sp(ind) / b.valueAt$mcD$sp(index$macro$4);
                  if (res != (double)0) {
                     result.add$mcD$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcD$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Double_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Float_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mFc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = b.indexAt(index$macro$4);
                  float res = a.apply$mcF$sp(ind) / b.valueAt$mcF$sp(index$macro$4);
                  if (res != (float)0) {
                     result.add$mcF$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcF$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Float_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_Op_DV_SV_eq_SV_Long_OpDiv_$eq(new UFunc.UImpl2() {
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

         public SparseVector apply(final DenseVector a, final SparseVector b) {
            int left$macro$1 = a.length();
            int right$macro$2 = b.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(82)).append("requirement failed: Vectors must have the same length: ").append("a.length == b.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               VectorBuilder result = VectorBuilder$.MODULE$.zeros$mJc$sp(a.length(), VectorBuilder$.MODULE$.zeros$default$2(), scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
               int index$macro$4 = 0;

               for(int limit$macro$6 = b.activeSize(); index$macro$4 < limit$macro$6; ++index$macro$4) {
                  int ind = b.indexAt(index$macro$4);
                  long res = a.apply$mcJ$sp(ind) / b.valueAt$mcJ$sp(index$macro$4);
                  if (res != 0L) {
                     result.add$mcJ$sp(ind, res);
                  }
               }

               return result.toSparseVector$mcJ$sp(true, true);
            }
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_Op_V_V_eq_V_Long_OpDiv())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Int_$eq(new UFunc.UImpl2() {
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

         public int apply(final DenseVector a, final SparseVector b) {
            int result = 0;
            int[] bd = b.data$mcI$sp();
            int[] bi = b.index();
            int bsize = b.iterableSize();
            int[] adata = a.data$mcI$sp();
            int aoff = a.offset();
            int stride = a.stride();
            if (stride == 1) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = bsize; index$macro$2 < limit$macro$4; ++index$macro$2) {
                  result += adata[aoff + bi[index$macro$2]] * bd[index$macro$2];
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = bsize; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  result += adata[aoff + bi[index$macro$7] * stride] * bd[index$macro$7];
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Double_$eq(new UFunc.UImpl2() {
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

         public double apply(final DenseVector a, final SparseVector b) {
            double result = (double)0.0F;
            double[] bd = b.data$mcD$sp();
            int[] bi = b.index();
            int bsize = b.iterableSize();
            double[] adata = a.data$mcD$sp();
            int aoff = a.offset();
            int stride = a.stride();
            if (stride == 1) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = bsize; index$macro$2 < limit$macro$4; ++index$macro$2) {
                  result += adata[aoff + bi[index$macro$2]] * bd[index$macro$2];
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = bsize; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  result += adata[aoff + bi[index$macro$7] * stride] * bd[index$macro$7];
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Float_$eq(new UFunc.UImpl2() {
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

         public float apply(final DenseVector a, final SparseVector b) {
            float result = 0.0F;
            float[] bd = b.data$mcF$sp();
            int[] bi = b.index();
            int bsize = b.iterableSize();
            float[] adata = a.data$mcF$sp();
            int aoff = a.offset();
            int stride = a.stride();
            if (stride == 1) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = bsize; index$macro$2 < limit$macro$4; ++index$macro$2) {
                  result += adata[aoff + bi[index$macro$2]] * bd[index$macro$2];
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = bsize; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  result += adata[aoff + bi[index$macro$7] * stride] * bd[index$macro$7];
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulInner_DV_SV_eq_T_Long_$eq(new UFunc.UImpl2() {
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

         public long apply(final DenseVector a, final SparseVector b) {
            long result = 0L;
            long[] bd = b.data$mcJ$sp();
            int[] bi = b.index();
            int bsize = b.iterableSize();
            long[] adata = a.data$mcJ$sp();
            int aoff = a.offset();
            int stride = a.stride();
            if (stride == 1) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = bsize; index$macro$2 < limit$macro$4; ++index$macro$2) {
                  result += adata[aoff + bi[index$macro$2]] * bd[index$macro$2];
               }
            } else {
               int index$macro$7 = 0;

               for(int limit$macro$9 = bsize; index$macro$7 < limit$macro$9; ++index$macro$7) {
                  result += adata[aoff + bi[index$macro$7] * stride] * bd[index$macro$7];
               }
            }

            return result;
         }

         public {
            ((BinaryRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_OpMulInner_V_V_eq_S_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Int_$eq(new UFunc.UImpl2() {
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

         public ZippedValues apply(final DenseVector du, final SparseVector sv) {
            int left$macro$1 = sv.length();
            int right$macro$2 = du.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: vector length mismatch: ").append("sv.length == du.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return new ZippedValues(du, sv) {
                  private final DenseVector du$1;
                  private final SparseVector sv$1;

                  public void foreach$mcDD$sp(final Function2 f) {
                     ZippedValues.foreach$mcDD$sp$(this, f);
                  }

                  public boolean exists(final Function2 f) {
                     return ZippedValues.exists$(this, f);
                  }

                  public boolean exists$mcDD$sp(final Function2 f) {
                     return ZippedValues.exists$mcDD$sp$(this, f);
                  }

                  public boolean forall(final Function2 f) {
                     return ZippedValues.forall$(this, f);
                  }

                  public boolean forall$mcDD$sp(final Function2 f) {
                     return ZippedValues.forall$mcDD$sp$(this, f);
                  }

                  public void foreach(final Function2 fn) {
                     int n = this.du$1.length();
                     int[] duData = this.du$1.data$mcI$sp();
                     int duStride = this.du$1.stride();
                     int duOffset = this.du$1.offset();
                     int[] svIndices = this.sv$1.index();
                     int[] svValues = this.sv$1.data$mcI$sp();
                     int svActiveSize = this.sv$1.activeSize();
                     int i = 0;

                     for(int j = 0; j < svActiveSize; ++j) {
                        for(int svIndex = svIndices[j]; i < svIndex; duOffset += duStride) {
                           fn.apply$mcVII$sp(duData[duOffset], 0);
                           ++i;
                        }

                        fn.apply$mcVII$sp(duData[duOffset], svValues[j]);
                        ++i;
                        duOffset += duStride;
                     }

                     while(i < n) {
                        fn.apply$mcVII$sp(duData[duOffset], 0);
                        ++i;
                        duOffset += duStride;
                     }

                  }

                  public {
                     this.du$1 = du$1;
                     this.sv$1 = sv$1;
                     ZippedValues.$init$(this);
                  }
               };
            }
         }

         public {
            .MODULE$.implicitly(DenseVector_SparseVector_Ops.this.zipValuesImpl_V_V_Int());
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Double_$eq(new UFunc.UImpl2() {
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

         public ZippedValues apply(final DenseVector du, final SparseVector sv) {
            int left$macro$1 = sv.length();
            int right$macro$2 = du.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: vector length mismatch: ").append("sv.length == du.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return new ZippedValues$mcDD$sp(du, sv) {
                  private final DenseVector du$2;
                  private final SparseVector sv$2;

                  public boolean exists(final Function2 f) {
                     return ZippedValues$mcDD$sp.exists$(this, f);
                  }

                  public boolean exists$mcDD$sp(final Function2 f) {
                     return ZippedValues$mcDD$sp.exists$mcDD$sp$(this, f);
                  }

                  public boolean forall(final Function2 f) {
                     return ZippedValues$mcDD$sp.forall$(this, f);
                  }

                  public boolean forall$mcDD$sp(final Function2 f) {
                     return ZippedValues$mcDD$sp.forall$mcDD$sp$(this, f);
                  }

                  public void foreach(final Function2 fn) {
                     this.foreach$mcDD$sp(fn);
                  }

                  public void foreach$mcDD$sp(final Function2 fn) {
                     int n = this.du$2.length();
                     double[] duData = this.du$2.data$mcD$sp();
                     int duStride = this.du$2.stride();
                     int duOffset = this.du$2.offset();
                     int[] svIndices = this.sv$2.index();
                     double[] svValues = this.sv$2.data$mcD$sp();
                     int svActiveSize = this.sv$2.activeSize();
                     int i = 0;

                     for(int j = 0; j < svActiveSize; ++j) {
                        for(int svIndex = svIndices[j]; i < svIndex; duOffset += duStride) {
                           fn.apply$mcVDD$sp(duData[duOffset], (double)0.0F);
                           ++i;
                        }

                        fn.apply$mcVDD$sp(duData[duOffset], svValues[j]);
                        ++i;
                        duOffset += duStride;
                     }

                     while(i < n) {
                        fn.apply$mcVDD$sp(duData[duOffset], (double)0.0F);
                        ++i;
                        duOffset += duStride;
                     }

                  }

                  public {
                     this.du$2 = du$2;
                     this.sv$2 = sv$2;
                     ZippedValues.$init$(this);
                  }
               };
            }
         }

         public {
            .MODULE$.implicitly(DenseVector_SparseVector_Ops.this.zipValuesImpl_V_V_Double());
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Float_$eq(new UFunc.UImpl2() {
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

         public ZippedValues apply(final DenseVector du, final SparseVector sv) {
            int left$macro$1 = sv.length();
            int right$macro$2 = du.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: vector length mismatch: ").append("sv.length == du.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return new ZippedValues(du, sv) {
                  private final DenseVector du$3;
                  private final SparseVector sv$3;

                  public void foreach$mcDD$sp(final Function2 f) {
                     ZippedValues.foreach$mcDD$sp$(this, f);
                  }

                  public boolean exists(final Function2 f) {
                     return ZippedValues.exists$(this, f);
                  }

                  public boolean exists$mcDD$sp(final Function2 f) {
                     return ZippedValues.exists$mcDD$sp$(this, f);
                  }

                  public boolean forall(final Function2 f) {
                     return ZippedValues.forall$(this, f);
                  }

                  public boolean forall$mcDD$sp(final Function2 f) {
                     return ZippedValues.forall$mcDD$sp$(this, f);
                  }

                  public void foreach(final Function2 fn) {
                     int n = this.du$3.length();
                     float[] duData = this.du$3.data$mcF$sp();
                     int duStride = this.du$3.stride();
                     int duOffset = this.du$3.offset();
                     int[] svIndices = this.sv$3.index();
                     float[] svValues = this.sv$3.data$mcF$sp();
                     int svActiveSize = this.sv$3.activeSize();
                     int i = 0;

                     for(int j = 0; j < svActiveSize; ++j) {
                        for(int svIndex = svIndices[j]; i < svIndex; duOffset += duStride) {
                           fn.apply(BoxesRunTime.boxToFloat(duData[duOffset]), BoxesRunTime.boxToFloat(0.0F));
                           ++i;
                        }

                        fn.apply(BoxesRunTime.boxToFloat(duData[duOffset]), BoxesRunTime.boxToFloat(svValues[j]));
                        ++i;
                        duOffset += duStride;
                     }

                     while(i < n) {
                        fn.apply(BoxesRunTime.boxToFloat(duData[duOffset]), BoxesRunTime.boxToFloat(0.0F));
                        ++i;
                        duOffset += duStride;
                     }

                  }

                  public {
                     this.du$3 = du$3;
                     this.sv$3 = sv$3;
                     ZippedValues.$init$(this);
                  }
               };
            }
         }

         public {
            .MODULE$.implicitly(DenseVector_SparseVector_Ops.this.zipValuesImpl_V_V_Float());
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_zipValues_DV_SV_eq_ZV_Long_$eq(new UFunc.UImpl2() {
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

         public ZippedValues apply(final DenseVector du, final SparseVector sv) {
            int left$macro$1 = sv.length();
            int right$macro$2 = du.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(73)).append("requirement failed: vector length mismatch: ").append("sv.length == du.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               return new ZippedValues(du, sv) {
                  private final DenseVector du$4;
                  private final SparseVector sv$4;

                  public void foreach$mcDD$sp(final Function2 f) {
                     ZippedValues.foreach$mcDD$sp$(this, f);
                  }

                  public boolean exists(final Function2 f) {
                     return ZippedValues.exists$(this, f);
                  }

                  public boolean exists$mcDD$sp(final Function2 f) {
                     return ZippedValues.exists$mcDD$sp$(this, f);
                  }

                  public boolean forall(final Function2 f) {
                     return ZippedValues.forall$(this, f);
                  }

                  public boolean forall$mcDD$sp(final Function2 f) {
                     return ZippedValues.forall$mcDD$sp$(this, f);
                  }

                  public void foreach(final Function2 fn) {
                     int n = this.du$4.length();
                     long[] duData = this.du$4.data$mcJ$sp();
                     int duStride = this.du$4.stride();
                     int duOffset = this.du$4.offset();
                     int[] svIndices = this.sv$4.index();
                     long[] svValues = this.sv$4.data$mcJ$sp();
                     int svActiveSize = this.sv$4.activeSize();
                     int i = 0;

                     for(int j = 0; j < svActiveSize; ++j) {
                        for(int svIndex = svIndices[j]; i < svIndex; duOffset += duStride) {
                           fn.apply$mcVJJ$sp(duData[duOffset], 0L);
                           ++i;
                        }

                        fn.apply$mcVJJ$sp(duData[duOffset], svValues[j]);
                        ++i;
                        duOffset += duStride;
                     }

                     while(i < n) {
                        fn.apply$mcVJJ$sp(duData[duOffset], 0L);
                        ++i;
                        duOffset += duStride;
                     }

                  }

                  public {
                     this.du$4 = du$4;
                     this.sv$4 = sv$4;
                     ZippedValues.$init$(this);
                  }
               };
            }
         }

         public {
            .MODULE$.implicitly(DenseVector_SparseVector_Ops.this.zipValuesImpl_V_V_Long());
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Int_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector y, final int a, final SparseVector x) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int xsize = x.activeSize();
               if (a != 0) {
                  for(int xoff = 0; xoff < xsize; ++xoff) {
                     int var8 = x.indexAt(xoff);
                     y.update$mcI$sp(var8, y.apply$mcI$sp(var8) + a * x.valueAt$mcI$sp(xoff));
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Int())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Double_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector y, final double a, final SparseVector x) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int xsize = x.activeSize();
               if (a != (double)0.0F) {
                  for(int xoff = 0; xoff < xsize; ++xoff) {
                     int var9 = x.indexAt(xoff);
                     y.update$mcD$sp(var9, y.apply$mcD$sp(var9) + a * x.valueAt$mcD$sp(xoff));
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Double())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Double(), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Float_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector y, final float a, final SparseVector x) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int xsize = x.activeSize();
               if (a != 0.0F) {
                  for(int xoff = 0; xoff < xsize; ++xoff) {
                     int var8 = x.indexAt(xoff);
                     y.update$mcF$sp(var8, y.apply$mcF$sp(var8) + a * x.valueAt$mcF$sp(xoff));
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Float())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Float(), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$implScaleAdd_DV_S_SV_InPlace_Long_$eq(new UFunc.InPlaceImpl3() {
         public void apply(final DenseVector y, final long a, final SparseVector x) {
            int left$macro$1 = x.length();
            int right$macro$2 = y.length();
            if (left$macro$1 != right$macro$2) {
               throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("x.length == y.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
            } else {
               int xsize = x.activeSize();
               if (a != 0L) {
                  for(int xoff = 0; xoff < xsize; ++xoff) {
                     int var9 = x.indexAt(xoff);
                     y.update$mcJ$sp(var9, y.apply$mcJ$sp(var9) + a * x.valueAt$mcJ$sp(xoff));
                  }
               }

            }
         }

         public {
            ((TernaryUpdateRegistry).MODULE$.implicitly(DenseVector_SparseVector_Ops.this.impl_scaleAdd_InPlace_V_S_V_Long())).register(this, scala.reflect.ClassTag..MODULE$.apply(DenseVector.class), scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Int_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final DenseVector a, final Transpose b) {
            SparseVector bInner = (SparseVector)b.inner();
            int sizeHint = a.size() * bInner.activeSize();
            CSCMatrix.Builder res = new CSCMatrix$Builder$mcI$sp(a.size(), bInner.size(), sizeHint, scala.reflect.ClassTag..MODULE$.Int(), Semiring$.MODULE$.semiringInt(), Zero$.MODULE$.IntZero());
            int index$macro$7 = 0;

            for(int limit$macro$9 = bInner.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  ((boff, i) -> {
                     int j = bInner.indexAt(boff);
                     int bValue = bInner.valueAt$mcI$sp(boff);
                     res.add$mcI$sp(i, j, a.apply$mcI$sp(i) * bValue);
                  }).apply$mcVII$sp(index$macro$7, index$macro$2);
               }
            }

            return res.result$mcI$sp(true, true);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Double_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final DenseVector a, final Transpose b) {
            SparseVector bInner = (SparseVector)b.inner();
            int sizeHint = a.size() * bInner.activeSize();
            CSCMatrix.Builder res = new CSCMatrix$Builder$mcD$sp(a.size(), bInner.size(), sizeHint, scala.reflect.ClassTag..MODULE$.Double(), Semiring$.MODULE$.semiringD(), Zero$.MODULE$.DoubleZero());
            int index$macro$7 = 0;

            for(int limit$macro$9 = bInner.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  ((boff, i) -> {
                     int j = bInner.indexAt(boff);
                     double bValue = bInner.valueAt$mcD$sp(boff);
                     res.add$mcD$sp(i, j, a.apply$mcD$sp(i) * bValue);
                  }).apply$mcVII$sp(index$macro$7, index$macro$2);
               }
            }

            return res.result$mcD$sp(true, true);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Float_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final DenseVector a, final Transpose b) {
            SparseVector bInner = (SparseVector)b.inner();
            int sizeHint = a.size() * bInner.activeSize();
            CSCMatrix.Builder res = new CSCMatrix$Builder$mcF$sp(a.size(), bInner.size(), sizeHint, scala.reflect.ClassTag..MODULE$.Float(), Semiring$.MODULE$.semiringFloat(), Zero$.MODULE$.FloatZero());
            int index$macro$7 = 0;

            for(int limit$macro$9 = bInner.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  ((boff, i) -> {
                     int j = bInner.indexAt(boff);
                     float bValue = bInner.valueAt$mcF$sp(boff);
                     res.add$mcF$sp(i, j, a.apply$mcF$sp(i) * bValue);
                  }).apply$mcVII$sp(index$macro$7, index$macro$2);
               }
            }

            return res.result$mcF$sp(true, true);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Long_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final DenseVector a, final Transpose b) {
            SparseVector bInner = (SparseVector)b.inner();
            int sizeHint = a.size() * bInner.activeSize();
            CSCMatrix.Builder res = new CSCMatrix$Builder$mcJ$sp(a.size(), bInner.size(), sizeHint, scala.reflect.ClassTag..MODULE$.Long(), Semiring$.MODULE$.semiringLong(), Zero$.MODULE$.LongZero());
            int index$macro$7 = 0;

            for(int limit$macro$9 = bInner.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  ((boff, i) -> {
                     int j = bInner.indexAt(boff);
                     long bValue = bInner.valueAt$mcJ$sp(boff);
                     res.add$mcJ$sp(i, j, a.apply$mcJ$sp(i) * bValue);
                  }).apply$mcVII$sp(index$macro$7, index$macro$2);
               }
            }

            return res.result$mcJ$sp(true, true);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      $this.breeze$linalg$operators$DenseVector_SparseVector_Ops$_setter_$impl_OpMulMatrix_DV_SVt_eq_CSC_Complex_$eq(new UFunc.UImpl2() {
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

         public CSCMatrix apply(final DenseVector a, final Transpose b) {
            SparseVector bInner = (SparseVector)b.inner();
            int sizeHint = a.size() * bInner.activeSize();
            CSCMatrix.Builder res = new CSCMatrix.Builder(a.size(), bInner.size(), sizeHint, scala.reflect.ClassTag..MODULE$.apply(Complex.class), Complex.scalar$.MODULE$, Complex$.MODULE$.ComplexZero());
            int index$macro$7 = 0;

            for(int limit$macro$9 = bInner.activeSize(); index$macro$7 < limit$macro$9; ++index$macro$7) {
               int index$macro$2 = 0;

               for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
                  ((boff, i) -> {
                     int j = bInner.indexAt(boff);
                     Complex bValue = (Complex)bInner.valueAt(boff);
                     res.add(i, j, ((Complex)a.apply(i)).$times(bValue));
                  }).apply$mcVII$sp(index$macro$7, index$macro$2);
               }
            }

            return res.result(true, true);
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }
}
