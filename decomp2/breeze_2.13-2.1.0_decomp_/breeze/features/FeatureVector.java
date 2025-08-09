package breeze.features;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.Transpose;
import breeze.linalg.operators.TernaryUpdateRegistry;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import java.util.Arrays;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015f\u0001\u0002!B\u0001\u0019C\u0001\"\u0016\u0001\u0003\u0006\u0004%\tA\u0016\u0005\t;\u0002\u0011\t\u0011)A\u0005/\")a\f\u0001C\u0001?\")\u0011\r\u0001C\u0001E\")1\r\u0001C\u0001I\")Q\r\u0001C\u0001M\")\u0011\u000e\u0001C!U\")a\u000f\u0001C!o\")\u0001\u0010\u0001C!s\u001e9\u0011QA!\t\u0002\u0005\u001daA\u0002!B\u0011\u0003\tI\u0001\u0003\u0004_\u0017\u0011\u0005\u00111\u0002\u0005\u0007K.!\t!!\u0004\b\u000f\u0005e1\u0002#\u0001\u0002\u001c\u00199\u0011qD\u0006\t\u0002\u0005\u0005\u0002B\u00020\u0010\t\u0003\t\u0019\u0003C\u0004\u0002&=!\u0019!a\n\b\u000f\u000552\u0002c\u0001\u00020\u00199\u0011\u0011G\u0006\t\u0002\u0005M\u0002B\u00020\u0014\t\u0003\t9\u0005\u0003\u0004f'\u0011\u0005\u0013\u0011\n\u0005\n\u0003\u001fZ!\u0019!C\u0002\u0003#B\u0001\"a\u001b\fA\u0003%\u00111\u000b\u0005\n\u0003[Z!\u0019!C\u0002\u0003_B\u0001\"a\u001f\fA\u0003%\u0011\u0011\u000f\u0005\n\u0003{Z!\u0019!C\u0002\u0003\u007fB\u0001\"a#\fA\u0003%\u0011\u0011\u0011\u0005\n\u0003\u001b[!\u0019!C\u0002\u0003\u001fC\u0001\"!*\fA\u0003%\u0011\u0011\u0013\u0005\n\u0003O[!\u0019!C\u0002\u0003SC\u0001\"a,\fA\u0003%\u00111\u0016\u0005\n\u0003c[!\u0019!C\u0002\u0003gC\u0001\"!/\fA\u0003%\u0011Q\u0017\u0005\n\u0003w[!\u0019!C\u0002\u0003{C\u0001\"!7\fA\u0003%\u0011q\u0018\u0005\n\u00037\\!\u0019!C\u0002\u0003;D\u0001\"a9\fA\u0003%\u0011q\u001c\u0005\b\u0003K\\A1AAt\u0011%\typ\u0003b\u0001\n\u0007\u0011\t\u0001\u0003\u0005\u0003\u0010-\u0001\u000b\u0011\u0002B\u0002\u0011%\u0011\tb\u0003b\u0001\n\u0007\u0011\u0019\u0002\u0003\u0005\u0003\u0018-\u0001\u000b\u0011\u0002B\u000b\u0011%\u0011Ib\u0003b\u0001\n\u0007\u0011Y\u0002\u0003\u0005\u0003 -\u0001\u000b\u0011\u0002B\u000f\u0011%\u0011\tc\u0003b\u0001\n\u0007\u0011\u0019\u0003\u0003\u0005\u0003(-\u0001\u000b\u0011\u0002B\u0013\u0011%\u0011Ic\u0003b\u0001\n\u0007\u0011Y\u0003\u0003\u0005\u00030-\u0001\u000b\u0011\u0002B\u0017\u0011%\u0011\td\u0003b\u0001\n\u0007\u0011\u0019\u0004\u0003\u0005\u00038-\u0001\u000b\u0011\u0002B\u001b\u0011\u001d\u0011Id\u0003C\u0002\u0005wA\u0011B!\u0015\f\u0005\u0004%\u0019Aa\u0015\t\u0011\t\r4\u0002)A\u0005\u0005+B\u0011B!\u001a\f\u0005\u0004%\u0019Aa\u001a\t\u0011\t54\u0002)A\u0005\u0005SB\u0011Ba\u001c\f\u0005\u0004%\u0019A!\u001d\t\u0011\t]4\u0002)A\u0005\u0005gB\u0011B!\u001f\f\u0005\u0004%\u0019Aa\u001f\t\u0011\t-5\u0002)A\u0005\u0005{B\u0011B!$\f\u0005\u0004%\u0019Aa$\t\u0011\t]5\u0002)A\u0005\u0005#C\u0011B!'\f\u0005\u0004%\u0019Aa'\t\u0011\t\r6\u0002)A\u0005\u0005;\u0013QBR3biV\u0014XMV3di>\u0014(B\u0001\"D\u0003!1W-\u0019;ve\u0016\u001c(\"\u0001#\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u00192\u0001A$N!\tA5*D\u0001J\u0015\u0005Q\u0015!B:dC2\f\u0017B\u0001'J\u0005\u0019\te.\u001f*fMB\u0019a*U*\u000e\u0003=S!\u0001U\"\u0002\r1Lg.\u00197h\u0013\t\u0011vJ\u0001\u0006Ok6,'/[2PaN\u0004\"\u0001\u0016\u0001\u000e\u0003\u0005\u000bA\u0001Z1uCV\tq\u000bE\u0002I1jK!!W%\u0003\u000b\u0005\u0013(/Y=\u0011\u0005![\u0016B\u0001/J\u0005\rIe\u000e^\u0001\u0006I\u0006$\u0018\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005M\u0003\u0007\"B+\u0004\u0001\u00049\u0016\u0001\u0002:faJ,\u0012aU\u0001\rC\u000e$\u0018N^3MK:<G\u000f[\u000b\u00025\u0006)\u0011\r\u001d9msR\u0011!l\u001a\u0005\u0006Q\u001a\u0001\rAW\u0001\u0002S\u0006AAo\\*ue&tw\rF\u0001l!\ta7O\u0004\u0002ncB\u0011a.S\u0007\u0002_*\u0011\u0001/R\u0001\u0007yI|w\u000e\u001e \n\u0005IL\u0015A\u0002)sK\u0012,g-\u0003\u0002uk\n11\u000b\u001e:j]\u001eT!A]%\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AW\u0001\u0007KF,\u0018\r\\:\u0015\u0005il\bC\u0001%|\u0013\ta\u0018JA\u0004C_>dW-\u00198\t\u000byL\u0001\u0019A@\u0002\u0005A\f\u0004c\u0001%\u0002\u0002%\u0019\u00111A%\u0003\u0007\u0005s\u00170A\u0007GK\u0006$XO]3WK\u000e$xN\u001d\t\u0003).\u0019\"aC$\u0015\u0005\u0005\u001dAcA*\u0002\u0010!9\u0011\u0011C\u0007A\u0002\u0005M\u0011\u0001B5oiN\u0004B\u0001SA\u000b5&\u0019\u0011qC%\u0003\u0015q\u0012X\r]3bi\u0016$g(A\u0005J[Bd\u0017nY5ugB\u0019\u0011QD\b\u000e\u0003-\u0011\u0011\"S7qY&\u001c\u0017\u000e^:\u0014\u0005=9ECAA\u000e\u000311'o\\7BeJ\f\u00170\u00138u)\r\u0019\u0016\u0011\u0006\u0005\u0007\u0003W\t\u0002\u0019A,\u0002\u0007\u0005\u0014(/A\u0006Ue\u0006t7\u000f]8tK\u001a3\u0006cAA\u000f'\tYAK]1ogB|7/\u001a$W'\u0011\u0019r)!\u000e\u0011\u000f\u0005]\u0012QH*\u0002B5\u0011\u0011\u0011\b\u0006\u0004\u0003wy\u0015aB:vaB|'\u000f^\u0005\u0005\u0003\u007f\tID\u0001\u0007DC:$&/\u00198ta>\u001cX\r\u0005\u0003O\u0003\u0007\u001a\u0016bAA#\u001f\nIAK]1ogB|7/\u001a\u000b\u0003\u0003_!B!!\u0011\u0002L!1\u0011QJ\u000bA\u0002M\u000bAA\u001a:p[\u0006\u0001\u0013.\u001c9m?N\u001c\u0017\r\\3BI\u0012|\u0016J\u001c)mC\u000e,wLV0T?\u001a3v,\u00138u+\t\t\u0019\u0006\u0005\u0006\u0002V\u0005m\u0013q\f.T\u0003Kj!!a\u0016\u000b\u0007\u0005es*A\u0005pa\u0016\u0014\u0018\r^8sg&!\u0011QLA,\u0005U!VM\u001d8bef,\u0006\u000fZ1uKJ+w-[:uef\u0004BATA15&\u0019\u00111M(\u0003\rY+7\r^8s\u001d\rq\u0015qM\u0005\u0004\u0003Sz\u0015\u0001C:dC2,\u0017\t\u001a3\u0002C%l\u0007\u000f\\0tG\u0006dW-\u00113e?&s\u0007\u000b\\1dK~3vlU0G-~Ke\u000e\u001e\u0011\u0002E%l\u0007\u000f\\0tG\u0006dW-\u00113e?&s\u0007\u000b\\1dK~3vlU0G-~3En\\1u+\t\t\t\bE\u0006\u0002V\u0005m\u00131OA;'\u0006\u0015\u0004#\u0002(\u0002b\u0005U\u0004c\u0001%\u0002x%\u0019\u0011\u0011P%\u0003\u000b\u0019cw.\u0019;\u0002G%l\u0007\u000f\\0tG\u0006dW-\u00113e?&s\u0007\u000b\\1dK~3vlU0G-~3En\\1uA\u0005\u0019\u0013.\u001c9m?N\u001c\u0017\r\\3BI\u0012|\u0016J\u001c)mC\u000e,wLV0T?\u001a3v\fR8vE2,WCAAA!-\t)&a\u0017\u0002\u0004\u0006\u00155+!\u001a\u0011\u000b9\u000b\t'!\"\u0011\u0007!\u000b9)C\u0002\u0002\n&\u0013a\u0001R8vE2,\u0017\u0001J5na2|6oY1mK\u0006#GmX%o!2\f7-Z0W?N{fIV0E_V\u0014G.\u001a\u0011\u0002C%l\u0007\u000f\\0tG\u0006dW-\u00113e?&s\u0007\u000b\\1dK~#ekX*`\rZ{\u0016J\u001c;\u0016\u0005\u0005E\u0005\u0003CA3\u0003'\u000byJW*\n\t\u0005U\u0015q\u0013\u0002\r\u0013:\u0004F.Y2f\u00136\u0004HnM\u0005\u0005\u00033\u000bYJA\u0003V\rVt7MC\u0002\u0002\u001e\u000e\u000bqaZ3oKJL7\r\u0005\u0003O\u0003CS\u0016bAAR\u001f\nYA)\u001a8tKZ+7\r^8s\u0003\tJW\u000e\u001d7`g\u000e\fG.Z!eI~Ke\u000e\u00157bG\u0016|FIV0T?\u001a3v,\u00138uA\u0005\u0019\u0013.\u001c9m?N\u001c\u0017\r\\3BI\u0012|\u0016J\u001c)mC\u000e,w\f\u0012,`'~3ek\u0018$m_\u0006$XCAAV!%\t)'a%\u0002.\u0006U4\u000bE\u0003O\u0003C\u000b)(\u0001\u0013j[Bdwl]2bY\u0016\fE\rZ0J]Bc\u0017mY3`\tZ{6k\u0018$W?\u001acw.\u0019;!\u0003\u0011JW\u000e\u001d7`g\u000e\fG.Z!eI~Ke\u000e\u00157bG\u0016|FIV0T?\u001a3v\fR8vE2,WCAA[!%\t)'a%\u00028\u0006\u00155\u000bE\u0003O\u0003C\u000b))A\u0013j[Bdwl]2bY\u0016\fE\rZ0J]Bc\u0017mY3`\tZ{6k\u0018$W?\u0012{WO\u00197fA\u0005\u0019\u0013.\u001c9m?N\u001c\u0017\r\\3BI\u0012|\u0016J\u001c)mC\u000e,wL\u0016\"`'~3ek\u0018$m_\u0006$XCAA`!-\t\t-a4\u0002f\u0005M\u0017QO*\u000f\t\u0005\r\u00171\u001a\b\u0005\u0003\u000b\fIMD\u0002o\u0003\u000fL\u0011\u0001R\u0005\u0004\u0003;\u001b\u0015\u0002BAg\u00037\u000bQ!\u0016$v]\u000eLA!!&\u0002R*!\u0011QZAN!\u0015q\u0015Q[A;\u0013\r\t9n\u0014\u0002\u000e-\u0016\u001cGo\u001c:Ck&dG-\u001a:\u0002I%l\u0007\u000f\\0tG\u0006dW-\u00113e?&s\u0007\u000b\\1dK~3&iX*`\rZ{f\t\\8bi\u0002\nA%[7qY~\u001b8-\u00197f\u0003\u0012$w,\u00138QY\u0006\u001cWm\u0018,C?N{fIV0E_V\u0014G.Z\u000b\u0003\u0003?\u00042\"!1\u0002P\u0006\u0015\u0014\u0011]AC'B)a*!6\u0002\u0006\u0006)\u0013.\u001c9m?N\u001c\u0017\r\\3BI\u0012|\u0016J\u001c)mC\u000e,wL\u0016\"`'~3ek\u0018#pk\ndW\rI\u0001&S6\u0004HnX:dC2,\u0017\t\u001a3`\u0013:\u0004F.Y2f?Z\u0013ulU0G-~;UM\\3sS\u000e,B!!;\u0002tV\u0011\u00111\u001e\t\f\u0003\u0003\fy-!\u001a\u0002n\u0006=8\u000bE\u0003O\u0003+\fy\u000f\u0005\u0003\u0002r\u0006MH\u0002\u0001\u0003\b\u0003k4#\u0019AA|\u0005\u0005!\u0016cAA}\u007fB\u0019\u0001*a?\n\u0007\u0005u\u0018JA\u0004O_RD\u0017N\\4\u0002;%l\u0007\u000f\\0Pa6+H.\u00138oKJ|fIV0W?\u0016\fxlU0J]R,\"Aa\u0001\u0011\u0011\t\u0015!1B*\u0002`isA!!\u0016\u0003\b%!!\u0011BA,\u0003)y\u0005/T;m\u0013:tWM]\u0005\u0005\u0005\u001b\t9JA\u0003J[Bd''\u0001\u0010j[Bdwl\u00149Nk2LeN\\3s?\u001a3vLV0fc~\u001bv,\u00138uA\u0005y\u0012.\u001c9m?>\u0003X*\u001e7J]:,'o\u0018$W?Z{V-]0T?\u001acw.\u0019;\u0016\u0005\tU\u0001#\u0003B\u0003\u0005\u0017\u0019\u00161OA;\u0003\u0001JW\u000e\u001d7`\u001fBlU\u000f\\%o]\u0016\u0014xL\u0012,`-~+\u0017oX*`\r2|\u0017\r\u001e\u0011\u0002A%l\u0007\u000f\\0Pa6+H.\u00138oKJ|fIV0W?\u0016\fxlU0E_V\u0014G.Z\u000b\u0003\u0005;\u0001\u0012B!\u0002\u0003\fM\u000b\u0019)!\"\u0002C%l\u0007\u000f\\0Pa6+H.\u00138oKJ|fIV0W?\u0016\fxlU0E_V\u0014G.\u001a\u0011\u0002=%l\u0007\u000f\\0Pa6+H.\u00138oKJ|fIV0E-~+\u0017oX*`\u0013:$XC\u0001B\u0013!!\u0011)Aa\u0003T\u0003?S\u0016aH5na2|v\n]'vY&sg.\u001a:`\rZ{FIV0fc~\u001bv,\u00138uA\u0005\u0001\u0013.\u001c9m?>\u0003X*\u001e7J]:,'o\u0018$W?\u00123v,Z9`'~3En\\1u+\t\u0011i\u0003E\u0005\u0003\u0006\t-1+!,\u0002v\u0005\t\u0013.\u001c9m?>\u0003X*\u001e7J]:,'o\u0018$W?\u00123v,Z9`'~3En\\1uA\u0005\t\u0013.\u001c9m?>\u0003X*\u001e7J]:,'o\u0018$W?\u00123v,Z9`'~#u.\u001e2mKV\u0011!Q\u0007\t\n\u0005\u000b\u0011YaUA\\\u0003\u000b\u000b!%[7qY~{\u0005/T;m\u0013:tWM]0G-~#ekX3r?N{Fi\\;cY\u0016\u0004\u0013aI5na2|v\n]'vY&sg.\u001a:`\rZ{FkX3r?N{fM]8n?R{fIV\u000b\u0007\u0005{\u0011\u0019E!\u0013\u0015\t\t}\"1\n\t\n\u0005\u000b\u0011YA!\u0011T\u0005\u000f\u0002B!!=\u0003D\u00119!QI\u001aC\u0002\u0005](!\u0001,\u0011\t\u0005E(\u0011\n\u0003\b\u0003k\u001c$\u0019AA|\u0011\u001d\u0011ie\ra\u0002\u0005\u001f\n!a\u001c9\u0011\u0013\t\u0015!1B*\u0003B\t\u001d\u0013\u0001I5na2|v\n]'vY6\u000bGO]5y?\u0012kuL\u0012,`KF|FIV0J]R,\"A!\u0016\u0011\u0013\t]#1\u0002B/'\u0006}e\u0002BA+\u00053JAAa\u0017\u0002X\u0005Yq\n]'vY6\u000bGO]5y!\u0011q%q\f.\n\u0007\t\u0005tJA\u0006EK:\u001cX-T1ue&D\u0018!I5na2|v\n]'vY6\u000bGO]5y?\u0012kuL\u0012,`KF|FIV0J]R\u0004\u0013aI5na2|v\n]'vY6\u000bGO]5y?\u0012kuL\u0012,`KF|FIV0E_V\u0014G.Z\u000b\u0003\u0005S\u0002\u0012Ba\u0016\u0003\f\t-4+a.\u0011\u000b9\u0013y&!\"\u0002I%l\u0007\u000f\\0Pa6+H.T1ue&Dx\fR'`\rZ{V-]0E-~#u.\u001e2mK\u0002\n!%[7qY~{\u0005/T;m\u001b\u0006$(/\u001b=`\t6{fIV0fc~#ek\u0018$m_\u0006$XC\u0001B:!%\u00119Fa\u0003\u0003vM\u000bi\u000bE\u0003O\u0005?\n)(A\u0012j[Bdwl\u00149Nk2l\u0015\r\u001e:jq~#Uj\u0018$W?\u0016\fx\f\u0012,`\r2|\u0017\r\u001e\u0011\u0002E%l\u0007\u000f\\0Pa6+H.T1ue&DxlQ*D?\u001a3v,Z9`\u0007N\u001bu,\u00138u+\t\u0011i\bE\u0005\u0003X\t-!qP*\u0003\u0006B!aJ!![\u0013\r\u0011\u0019i\u0014\u0002\n\u0007N\u001bU*\u0019;sSb\u0004BA\u0014BD5&\u0019!\u0011R(\u0003\u0019M\u0003\u0018M]:f-\u0016\u001cGo\u001c:\u0002G%l\u0007\u000f\\0Pa6+H.T1ue&DxlQ*D?\u001a3v,Z9`\u0007N\u001bu,\u00138uA\u0005)\u0013.\u001c9m?>\u0003X*\u001e7NCR\u0014\u0018\u000e_0D'\u000e{fIV0fc~\u001b5kQ0E_V\u0014G.Z\u000b\u0003\u0005#\u0003\u0012Ba\u0016\u0003\f\tM5K!&\u0011\u000b9\u0013\t)!\"\u0011\u000b9\u00139)!\"\u0002M%l\u0007\u000f\\0Pa6+H.T1ue&DxlQ*D?\u001a3v,Z9`\u0007N\u001bu\fR8vE2,\u0007%\u0001\u0013j[Bdwl\u00149Nk2l\u0015\r\u001e:jq~\u001b5kQ0G-~+\u0017oX\"T\u0007~3En\\1u+\t\u0011i\nE\u0005\u0003X\t-!qT*\u0003\"B)aJ!!\u0002vA)aJa\"\u0002v\u0005)\u0013.\u001c9m?>\u0003X*\u001e7NCR\u0014\u0018\u000e_0D'\u000e{fIV0fc~\u001b5kQ0GY>\fG\u000f\t"
)
public class FeatureVector implements NumericOps {
   private final int[] data;

   public static UFunc.UImpl2 impl_OpMulMatrix_CSC_FV_eq_CSC_Float() {
      return FeatureVector$.MODULE$.impl_OpMulMatrix_CSC_FV_eq_CSC_Float();
   }

   public static UFunc.UImpl2 impl_OpMulMatrix_CSC_FV_eq_CSC_Double() {
      return FeatureVector$.MODULE$.impl_OpMulMatrix_CSC_FV_eq_CSC_Double();
   }

   public static UFunc.UImpl2 impl_OpMulMatrix_CSC_FV_eq_CSC_Int() {
      return FeatureVector$.MODULE$.impl_OpMulMatrix_CSC_FV_eq_CSC_Int();
   }

   public static UFunc.UImpl2 impl_OpMulMatrix_DM_FV_eq_DV_Float() {
      return FeatureVector$.MODULE$.impl_OpMulMatrix_DM_FV_eq_DV_Float();
   }

   public static UFunc.UImpl2 impl_OpMulMatrix_DM_FV_eq_DV_Double() {
      return FeatureVector$.MODULE$.impl_OpMulMatrix_DM_FV_eq_DV_Double();
   }

   public static UFunc.UImpl2 impl_OpMulMatrix_DM_FV_eq_DV_Int() {
      return FeatureVector$.MODULE$.impl_OpMulMatrix_DM_FV_eq_DV_Int();
   }

   public static UFunc.UImpl2 impl_OpMulInner_FV_T_eq_S_from_T_FV(final UFunc.UImpl2 op) {
      return FeatureVector$.MODULE$.impl_OpMulInner_FV_T_eq_S_from_T_FV(op);
   }

   public static UFunc.UImpl2 impl_OpMulInner_FV_DV_eq_S_Double() {
      return FeatureVector$.MODULE$.impl_OpMulInner_FV_DV_eq_S_Double();
   }

   public static UFunc.UImpl2 impl_OpMulInner_FV_DV_eq_S_Float() {
      return FeatureVector$.MODULE$.impl_OpMulInner_FV_DV_eq_S_Float();
   }

   public static UFunc.UImpl2 impl_OpMulInner_FV_DV_eq_S_Int() {
      return FeatureVector$.MODULE$.impl_OpMulInner_FV_DV_eq_S_Int();
   }

   public static UFunc.UImpl2 impl_OpMulInner_FV_V_eq_S_Double() {
      return FeatureVector$.MODULE$.impl_OpMulInner_FV_V_eq_S_Double();
   }

   public static UFunc.UImpl2 impl_OpMulInner_FV_V_eq_S_Float() {
      return FeatureVector$.MODULE$.impl_OpMulInner_FV_V_eq_S_Float();
   }

   public static UFunc.UImpl2 impl_OpMulInner_FV_V_eq_S_Int() {
      return FeatureVector$.MODULE$.impl_OpMulInner_FV_V_eq_S_Int();
   }

   public static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_VB_S_FV_Generic() {
      return FeatureVector$.MODULE$.impl_scaleAdd_InPlace_VB_S_FV_Generic();
   }

   public static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_VB_S_FV_Double() {
      return FeatureVector$.MODULE$.impl_scaleAdd_InPlace_VB_S_FV_Double();
   }

   public static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_VB_S_FV_Float() {
      return FeatureVector$.MODULE$.impl_scaleAdd_InPlace_VB_S_FV_Float();
   }

   public static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_FV_Double() {
      return FeatureVector$.MODULE$.impl_scaleAdd_InPlace_DV_S_FV_Double();
   }

   public static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_FV_Float() {
      return FeatureVector$.MODULE$.impl_scaleAdd_InPlace_DV_S_FV_Float();
   }

   public static UFunc.InPlaceImpl3 impl_scaleAdd_InPlace_DV_S_FV_Int() {
      return FeatureVector$.MODULE$.impl_scaleAdd_InPlace_DV_S_FV_Int();
   }

   public static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_FV_Double() {
      return FeatureVector$.MODULE$.impl_scaleAdd_InPlace_V_S_FV_Double();
   }

   public static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_FV_Float() {
      return FeatureVector$.MODULE$.impl_scaleAdd_InPlace_V_S_FV_Float();
   }

   public static TernaryUpdateRegistry impl_scaleAdd_InPlace_V_S_FV_Int() {
      return FeatureVector$.MODULE$.impl_scaleAdd_InPlace_V_S_FV_Int();
   }

   public final Object $plus(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$plus$(this, b, op);
   }

   public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$eq$(this, b, op);
   }

   public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$plus$eq$(this, b, op);
   }

   public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$times$eq$(this, b, op);
   }

   public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$plus$eq$(this, b, op);
   }

   public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$times$eq$(this, b, op);
   }

   public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$minus$eq$(this, b, op);
   }

   public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$percent$eq$(this, b, op);
   }

   public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$percent$eq$(this, b, op);
   }

   public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$minus$eq$(this, b, op);
   }

   public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$div$eq$(this, b, op);
   }

   public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$eq$(this, b, op);
   }

   public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$div$eq$(this, b, op);
   }

   public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$less$(this, b, op);
   }

   public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$eq$(this, b, op);
   }

   public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$greater$(this, b, op);
   }

   public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$eq$(this, b, op);
   }

   public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$amp$eq$(this, b, op);
   }

   public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$bar$eq$(this, b, op);
   }

   public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$up$eq$(this, b, op);
   }

   public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$amp$eq$(this, b, op);
   }

   public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$bar$eq$(this, b, op);
   }

   public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$up$up$eq$(this, b, op);
   }

   public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
   }

   public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$colon$times$(this, b, op);
   }

   public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
   }

   public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
   }

   public final Object unary_$minus(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$minus$(this, op);
   }

   public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
   }

   public final Object $minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$(this, b, op);
   }

   public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
   }

   public final Object $percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$(this, b, op);
   }

   public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$colon$div$(this, b, op);
   }

   public final Object $div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$(this, b, op);
   }

   public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$colon$up$(this, b, op);
   }

   public final Object dot(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.dot$(this, b, op);
   }

   public final Object unary_$bang(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$bang$(this, op);
   }

   public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
   }

   public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
   }

   public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
   }

   public final Object $amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$(this, b, op);
   }

   public final Object $bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$(this, b, op);
   }

   public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$(this, b, op);
   }

   public final Object $times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$(this, b, op);
   }

   public final Object t(final CanTranspose op) {
      return ImmutableNumericOps.t$(this, op);
   }

   public Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bslash$(this, b, op);
   }

   public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return ImmutableNumericOps.t$(this, a, b, op, canSlice);
   }

   public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return ImmutableNumericOps.t$(this, a, op, canSlice);
   }

   public int[] data() {
      return this.data;
   }

   public FeatureVector repr() {
      return this;
   }

   public int activeLength() {
      return this.data().length;
   }

   public int apply(final int i) {
      return this.data()[i];
   }

   public String toString() {
      return .MODULE$.wrapIntArray(this.data()).mkString("FeatureVector(", ", ", ")");
   }

   public int hashCode() {
      return Arrays.hashCode(this.data());
   }

   public boolean equals(final Object p1) {
      boolean var2;
      if (p1 instanceof FeatureVector) {
         FeatureVector var4 = (FeatureVector)p1;
         var2 = Arrays.equals(var4.data(), this.data());
      } else {
         var2 = false;
      }

      return var2;
   }

   public FeatureVector(final int[] data) {
      this.data = data;
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
   }

   public static class Implicits$ {
      public static final Implicits$ MODULE$ = new Implicits$();

      public FeatureVector fromArrayInt(final int[] arr) {
         return new FeatureVector(arr);
      }
   }

   public static class TransposeFV$ implements CanTranspose {
      public static final TransposeFV$ MODULE$ = new TransposeFV$();

      public Transpose apply(final FeatureVector from) {
         return new Transpose(from);
      }
   }
}
