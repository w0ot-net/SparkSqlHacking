package org.json4s.scalap.scalasig;

import java.io.Serializable;
import org.json4s.scalap.InRule;
import org.json4s.scalap.Rule;
import org.json4s.scalap.Rules;
import org.json4s.scalap.RulesWithState;
import org.json4s.scalap.SeqRule;
import org.json4s.scalap.StateRules;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015Eu\u0001CAY\u0003gC\t!!2\u0007\u0011\u0005%\u00171\u0017E\u0001\u0003\u0017Dq!a8\u0002\t\u0003\t\t\u000fC\u0004\u0002d\u0006!\t!!:\t\u000f\u0005]\u0018\u0001\"\u0001\u0002z\"I!Q_\u0001C\u0002\u0013\u0005!q\u001f\u0005\t\u0007\u000f\t\u0001\u0015!\u0003\u0003z\"I1\u0011B\u0001C\u0002\u0013\u000511\u0002\u0005\t\u00077\t\u0001\u0015!\u0003\u0004\u000e!I1QD\u0001C\u0002\u0013\u00051q\u0004\u0005\t\u0007S\t\u0001\u0015!\u0003\u0004\"!I11F\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007o\t\u0001\u0015!\u0003\u00040!I1\u0011H\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007w\t\u0001\u0015!\u0003\u00040!I1QH\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007\u007f\t\u0001\u0015!\u0003\u00040!I1\u0011I\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007\u0007\n\u0001\u0015!\u0003\u00040!I1QI\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007\u000f\n\u0001\u0015!\u0003\u00040!I1\u0011J\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007\u0017\n\u0001\u0015!\u0003\u00040!I1QJ\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007\u001f\n\u0001\u0015!\u0003\u00040!I1\u0011K\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007'\n\u0001\u0015!\u0003\u00040!I1QK\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007/\n\u0001\u0015!\u0003\u00040!I1\u0011L\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u00077\n\u0001\u0015!\u0003\u00040!I1QL\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007?\n\u0001\u0015!\u0003\u00040!I1\u0011M\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007G\n\u0001\u0015!\u0003\u00040!I1QM\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007O\n\u0001\u0015!\u0003\u00040!I1\u0011N\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007W\n\u0001\u0015!\u0003\u00040!I1QN\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007_\n\u0001\u0015!\u0003\u00040!I1\u0011O\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007g\n\u0001\u0015!\u0003\u00040!I1QO\u0001C\u0002\u0013\u00051Q\u0006\u0005\t\u0007o\n\u0001\u0015!\u0003\u00040!I1\u0011P\u0001C\u0002\u0013\u000511\u0010\u0005\t\u0007\u0003\u000b\u0001\u0015!\u0003\u0004~!I11Q\u0001C\u0002\u0013\u00051Q\u0011\u0005\t\u0007\u001f\u000b\u0001\u0015!\u0003\u0004\b\"I1\u0011S\u0001C\u0002\u0013\u000511\u0013\u0005\t\u00073\u000b\u0001\u0015!\u0003\u0004\u0016\u001a9!QD\u0001\u0002\u0002\t}\u0001bBApg\u0011\u0005!\u0011\u0005\u0004\u0007\u0005\u0007\n\u0001I!\u0012\t\u0015\t\u001dSG!f\u0001\n\u0003\u0011\t\u0004\u0003\u0006\u0003JU\u0012\t\u0012)A\u0005\u0005gA!Ba\u00136\u0005+\u0007I\u0011\u0001B'\u0011)\u0011y%\u000eB\tB\u0003%!1\u0004\u0005\b\u0003?,D\u0011\u0001B)\u0011%\u00119&NA\u0001\n\u0003\u0011I\u0006C\u0005\u0003`U\n\n\u0011\"\u0001\u0003b!I!qO\u001b\u0012\u0002\u0013\u0005!\u0011\u0010\u0005\n\u0005{*\u0014\u0011!C!\u0005\u007fB\u0011B!%6\u0003\u0003%\tA!\r\t\u0013\tMU'!A\u0005\u0002\tU\u0005\"\u0003BQk\u0005\u0005I\u0011\tBR\u0011%\u0011\t,NA\u0001\n\u0003\u0011\u0019\fC\u0005\u0003>V\n\t\u0011\"\u0011\u0003@\"I!1Y\u001b\u0002\u0002\u0013\u0005#Q\u0019\u0005\n\u0005\u000f,\u0014\u0011!C!\u0005\u0013D\u0011Ba36\u0003\u0003%\tE!4\b\u0013\rm\u0015!!A\t\u0002\rue!\u0003B\"\u0003\u0005\u0005\t\u0012ABP\u0011\u001d\ty\u000e\u0013C\u0001\u0007oC\u0011Ba2I\u0003\u0003%)E!3\t\u0013\re\u0006*!A\u0005\u0002\u000em\u0006\"CBa\u0011\u0006\u0005I\u0011QBb\u0011%\u0019\t\u000eSA\u0001\n\u0013\u0019\u0019N\u0002\u0004\u0004\\\u0006\u00015Q\u001c\u0005\u000b\u0007?t%Q3A\u0005\u0002\tE\u0002BCBq\u001d\nE\t\u0015!\u0003\u00034!9\u0011q\u001c(\u0005\u0002\r\r\b\"\u0003B,\u001d\u0006\u0005I\u0011ABu\u0011%\u0011yFTI\u0001\n\u0003\u0011\t\u0007C\u0005\u0003~9\u000b\t\u0011\"\u0011\u0003\u0000!I!\u0011\u0013(\u0002\u0002\u0013\u0005!\u0011\u0007\u0005\n\u0005's\u0015\u0011!C\u0001\u0007[D\u0011B!)O\u0003\u0003%\tEa)\t\u0013\tEf*!A\u0005\u0002\rE\b\"\u0003B_\u001d\u0006\u0005I\u0011IB{\u0011%\u0011\u0019MTA\u0001\n\u0003\u0012)\rC\u0005\u0003H:\u000b\t\u0011\"\u0011\u0003J\"I!1\u001a(\u0002\u0002\u0013\u00053\u0011`\u0004\n\u0007{\f\u0011\u0011!E\u0001\u0007\u007f4\u0011ba7\u0002\u0003\u0003E\t\u0001\"\u0001\t\u000f\u0005}g\f\"\u0001\u0005\n!I!q\u00190\u0002\u0002\u0013\u0015#\u0011\u001a\u0005\n\u0007ss\u0016\u0011!CA\t\u0017A\u0011b!1_\u0003\u0003%\t\tb\u0004\t\u0013\rEg,!A\u0005\n\rMgA\u0002C\u000b\u0003\u0001#9\u0002\u0003\u0006\u0005\u001a\u0011\u0014)\u001a!C\u0001\u0005cA!\u0002b\u0007e\u0005#\u0005\u000b\u0011\u0002B\u001a\u0011)!i\u0002\u001aBK\u0002\u0013\u0005!\u0011\u0007\u0005\u000b\t?!'\u0011#Q\u0001\n\tM\u0002bBApI\u0012\u0005A\u0011\u0005\u0005\n\u0005/\"\u0017\u0011!C\u0001\tSA\u0011Ba\u0018e#\u0003%\tA!\u0019\t\u0013\t]D-%A\u0005\u0002\t\u0005\u0004\"\u0003B?I\u0006\u0005I\u0011\tB@\u0011%\u0011\t\nZA\u0001\n\u0003\u0011\t\u0004C\u0005\u0003\u0014\u0012\f\t\u0011\"\u0001\u00050!I!\u0011\u00153\u0002\u0002\u0013\u0005#1\u0015\u0005\n\u0005c#\u0017\u0011!C\u0001\tgA\u0011B!0e\u0003\u0003%\t\u0005b\u000e\t\u0013\t\rG-!A\u0005B\t\u0015\u0007\"\u0003BdI\u0006\u0005I\u0011\tBe\u0011%\u0011Y\rZA\u0001\n\u0003\"YdB\u0005\u0005@\u0005\t\t\u0011#\u0001\u0005B\u0019IAQC\u0001\u0002\u0002#\u0005A1\t\u0005\b\u0003?<H\u0011\u0001C$\u0011%\u00119m^A\u0001\n\u000b\u0012I\rC\u0005\u0004:^\f\t\u0011\"!\u0005J!I1\u0011Y<\u0002\u0002\u0013\u0005Eq\n\u0005\n\u0007#<\u0018\u0011!C\u0005\u0007'4a\u0001\"\u0016\u0002\u0001\u0012]\u0003BCBp{\nU\r\u0011\"\u0001\u00032!Q1\u0011]?\u0003\u0012\u0003\u0006IAa\r\t\u000f\u0005}W\u0010\"\u0001\u0005Z!I!qK?\u0002\u0002\u0013\u0005Aq\f\u0005\n\u0005?j\u0018\u0013!C\u0001\u0005CB\u0011B! ~\u0003\u0003%\tEa \t\u0013\tEU0!A\u0005\u0002\tE\u0002\"\u0003BJ{\u0006\u0005I\u0011\u0001C2\u0011%\u0011\t+`A\u0001\n\u0003\u0012\u0019\u000bC\u0005\u00032v\f\t\u0011\"\u0001\u0005h!I!QX?\u0002\u0002\u0013\u0005C1\u000e\u0005\n\u0005\u0007l\u0018\u0011!C!\u0005\u000bD\u0011Ba2~\u0003\u0003%\tE!3\t\u0013\t-W0!A\u0005B\u0011=t!\u0003C:\u0003\u0005\u0005\t\u0012\u0001C;\r%!)&AA\u0001\u0012\u0003!9\b\u0003\u0005\u0002`\u0006mA\u0011\u0001C>\u0011)\u00119-a\u0007\u0002\u0002\u0013\u0015#\u0011\u001a\u0005\u000b\u0007s\u000bY\"!A\u0005\u0002\u0012u\u0004BCBa\u00037\t\t\u0011\"!\u0005\u0002\"Q1\u0011[A\u000e\u0003\u0003%Iaa5\u0007\r\t]\u0011\u0001\u0011B\r\u0011-\u0011y#a\n\u0003\u0016\u0004%\tA!\r\t\u0017\te\u0012q\u0005B\tB\u0003%!1\u0007\u0005\f\u0005w\t9C!f\u0001\n\u0003\u0011i\u0004C\u0006\u0003R\u0006\u001d\"\u0011#Q\u0001\n\t}\u0002\u0002CAp\u0003O!\tAa5\t\u0015\t]\u0013qEA\u0001\n\u0003\u0011I\u000e\u0003\u0006\u0003`\u0005\u001d\u0012\u0013!C\u0001\u0005CB!Ba\u001e\u0002(E\u0005I\u0011\u0001Bp\u0011)\u0011i(a\n\u0002\u0002\u0013\u0005#q\u0010\u0005\u000b\u0005#\u000b9#!A\u0005\u0002\tE\u0002B\u0003BJ\u0003O\t\t\u0011\"\u0001\u0003d\"Q!\u0011UA\u0014\u0003\u0003%\tEa)\t\u0015\tE\u0016qEA\u0001\n\u0003\u00119\u000f\u0003\u0006\u0003>\u0006\u001d\u0012\u0011!C!\u0005WD!Ba1\u0002(\u0005\u0005I\u0011\tBc\u0011)\u00119-a\n\u0002\u0002\u0013\u0005#\u0011\u001a\u0005\u000b\u0005\u0017\f9#!A\u0005B\t=x!\u0003CC\u0003\u0005\u0005\t\u0012\u0001CD\r%\u00119\"AA\u0001\u0012\u0003!I\t\u0003\u0005\u0002`\u00065C\u0011\u0001CG\u0011)\u00119-!\u0014\u0002\u0002\u0013\u0015#\u0011\u001a\u0005\u000b\u0007s\u000bi%!A\u0005\u0002\u0012=\u0005BCBa\u0003\u001b\n\t\u0011\"!\u0005\u0016\"Q1\u0011[A'\u0003\u0003%Iaa5\u0007\r\u0011u\u0015\u0001\u0011CP\u0011-!\t+!\u0017\u0003\u0016\u0004%\t\u0001b)\t\u0017\u0011\u001d\u0016\u0011\fB\tB\u0003%AQ\u0015\u0005\t\u0003?\fI\u0006\"\u0001\u0005*\"Q!qKA-\u0003\u0003%\t\u0001b,\t\u0015\t}\u0013\u0011LI\u0001\n\u0003!\u0019\f\u0003\u0006\u0003~\u0005e\u0013\u0011!C!\u0005\u007fB!B!%\u0002Z\u0005\u0005I\u0011\u0001B\u0019\u0011)\u0011\u0019*!\u0017\u0002\u0002\u0013\u0005Aq\u0017\u0005\u000b\u0005C\u000bI&!A\u0005B\t\r\u0006B\u0003BY\u00033\n\t\u0011\"\u0001\u0005<\"Q!QXA-\u0003\u0003%\t\u0005b0\t\u0015\t\r\u0017\u0011LA\u0001\n\u0003\u0012)\r\u0003\u0006\u0003H\u0006e\u0013\u0011!C!\u0005\u0013D!Ba3\u0002Z\u0005\u0005I\u0011\tCb\u000f%!9-AA\u0001\u0012\u0003!IMB\u0005\u0005\u001e\u0006\t\t\u0011#\u0001\u0005L\"A\u0011q\\A=\t\u0003!y\r\u0003\u0006\u0003H\u0006e\u0014\u0011!C#\u0005\u0013D!b!/\u0002z\u0005\u0005I\u0011\u0011Ci\u0011)\u0019\t-!\u001f\u0002\u0002\u0013\u0005EQ\u001b\u0005\u000b\u0007#\fI(!A\u0005\n\rM\u0007b\u0002Cn\u0003\u0011\u0005AQ\u001c\u0005\n\tK\f!\u0019!C\u0001\tOD\u0001\u0002\"?\u0002A\u0003%A\u0011\u001e\u0005\n\u0005c\n!\u0019!C\u0001\twD\u0001\u0002b@\u0002A\u0003%AQ \u0005\n\u000b\u0003\t!\u0019!C\u0001\u000b\u0007A\u0001\"b\u0002\u0002A\u0003%QQ\u0001\u0005\n\u000b\u0013\t!\u0019!C\u0001\u000b\u0017A\u0001\"\"\u0006\u0002A\u0003%QQ\u0002\u0005\n\u000b/\t!\u0019!C\u0001\u000b3A\u0001\"b\b\u0002A\u0003%Q1\u0004\u0005\n\u000bC\t!\u0019!C\u0001\u000bGA\u0001\"\"\f\u0002A\u0003%QQ\u0005\u0005\n\u000b_\t!\u0019!C\u0001\u000bcA\u0001\"b\u000e\u0002A\u0003%Q1\u0007\u0005\n\u000bs\t!\u0019!C\u0001\u000bwA\u0001\"\"\u0012\u0002A\u0003%QQ\b\u0005\n\u000b\u000f\n!\u0019!C\u0001\u000b\u0013B\u0001\"\"\u0014\u0002A\u0003%Q1\n\u0005\b\u000b\u001f\nA\u0011AC)\u0011\u001d)9&\u0001C\u0001\u000b3Bq!b\u001f\u0002\t\u0003)i(A\bDY\u0006\u001c8OR5mKB\u000b'o]3s\u0015\u0011\t),a.\u0002\u0011M\u001c\u0017\r\\1tS\u001eTA!!/\u0002<\u000611oY1mCBTA!!0\u0002@\u00061!n]8oiMT!!!1\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0007\u0005\u001d\u0017!\u0004\u0002\u00024\ny1\t\\1tg\u001aKG.\u001a)beN,'oE\u0003\u0002\u0003\u001b\fI\u000e\u0005\u0003\u0002P\u0006UWBAAi\u0015\t\t\u0019.A\u0003tG\u0006d\u0017-\u0003\u0003\u0002X\u0006E'AB!osJ+g\r\u0005\u0003\u0002H\u0006m\u0017\u0002BAo\u0003g\u0013aBQ=uK\u000e{G-\u001a*fC\u0012,'/\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003\u000b\fQ\u0001]1sg\u0016$B!a:\u0002nB!\u0011qYAu\u0013\u0011\tY/a-\u0003\u0013\rc\u0017m]:GS2,\u0007bBAx\u0007\u0001\u0007\u0011\u0011_\u0001\tEf$XmQ8eKB!\u0011qYAz\u0013\u0011\t)0a-\u0003\u0011\tKH/Z\"pI\u0016\f\u0001\u0003]1sg\u0016\feN\\8uCRLwN\\:\u0015\t\u0005m(1\u001f\t\u0007\u0003{\u0014iAa\u0005\u000f\t\u0005}(\u0011\u0002\b\u0005\u0005\u0003\u00119!\u0004\u0002\u0003\u0004)!!QAAb\u0003\u0019a$o\\8u}%\u0011\u00111[\u0005\u0005\u0005\u0017\t\t.A\u0004qC\u000e\\\u0017mZ3\n\t\t=!\u0011\u0003\u0002\u0004'\u0016\f(\u0002\u0002B\u0006\u0003#\u0004BA!\u0006\u0002(5\t\u0011A\u0001\u0006B]:|G/\u0019;j_:\u001c\u0002\"a\n\u0003\u001c\t\r\"\u0011\u0006\t\u0004\u0005+\u0019$\u0001D#mK6,g\u000e\u001e,bYV,7cA\u001a\u0002NR\u0011!1\u0004\t\u0005\u0003\u001f\u0014)#\u0003\u0003\u0003(\u0005E'a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003{\u0014Y#\u0003\u0003\u0003.\tE!\u0001D*fe&\fG.\u001b>bE2,\u0017!\u0003;za\u0016Le\u000eZ3y+\t\u0011\u0019\u0004\u0005\u0003\u0002P\nU\u0012\u0002\u0002B\u001c\u0003#\u00141!\u00138u\u0003)!\u0018\u0010]3J]\u0012,\u0007\u0010I\u0001\u0012K2,W.\u001a8u-\u0006dW/\u001a)bSJ\u001cXC\u0001B !\u0019\tiP!\u0004\u0003BA\u0019!QC\u001b\u0003#\u0005sgn\u001c;bi&|g.\u00127f[\u0016tGoE\u00046\u0003\u001b\u0014\u0019C!\u000b\u0002!\u0015dW-\\3oi:\u000bW.Z%oI\u0016D\u0018!E3mK6,g\u000e\u001e(b[\u0016Le\u000eZ3yA\u0005aQ\r\\3nK:$h+\u00197vKV\u0011!1D\u0001\u000eK2,W.\u001a8u-\u0006dW/\u001a\u0011\u0015\r\t\u0005#1\u000bB+\u0011\u001d\u00119E\u000fa\u0001\u0005gAqAa\u0013;\u0001\u0004\u0011Y\"\u0001\u0003d_BLHC\u0002B!\u00057\u0012i\u0006C\u0005\u0003Hm\u0002\n\u00111\u0001\u00034!I!1J\u001e\u0011\u0002\u0003\u0007!1D\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011\u0019G\u000b\u0003\u00034\t\u00154F\u0001B4!\u0011\u0011IGa\u001d\u000e\u0005\t-$\u0002\u0002B7\u0005_\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\t\tE\u0014\u0011[\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002B;\u0005W\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"Aa\u001f+\t\tm!QM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t\u0005\u0005\u0003\u0002BB\u0005\u001bk!A!\"\u000b\t\t\u001d%\u0011R\u0001\u0005Y\u0006twM\u0003\u0002\u0003\f\u0006!!.\u0019<b\u0013\u0011\u0011yI!\"\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$BAa&\u0003\u001eB!\u0011q\u001aBM\u0013\u0011\u0011Y*!5\u0003\u0007\u0005s\u0017\u0010C\u0005\u0003 \u0002\u000b\t\u00111\u0001\u00034\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!*\u0011\r\t\u001d&Q\u0016BL\u001b\t\u0011IK\u0003\u0003\u0003,\u0006E\u0017AC2pY2,7\r^5p]&!!q\u0016BU\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\tU&1\u0018\t\u0005\u0003\u001f\u00149,\u0003\u0003\u0003:\u0006E'a\u0002\"p_2,\u0017M\u001c\u0005\n\u0005?\u0013\u0015\u0011!a\u0001\u0005/\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!!\u0011\u0011Ba\u0011%\u0011yjQA\u0001\u0002\u0004\u0011\u0019$\u0001\u0005iCND7i\u001c3f)\t\u0011\u0019$\u0001\u0005u_N#(/\u001b8h)\t\u0011\t)\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005k\u0013y\rC\u0005\u0003 \u001a\u000b\t\u00111\u0001\u0003\u0018\u0006\u0011R\r\\3nK:$h+\u00197vKB\u000b\u0017N]:!)\u0019\u0011\u0019B!6\u0003X\"A!qFA\u0019\u0001\u0004\u0011\u0019\u0004\u0003\u0005\u0003<\u0005E\u0002\u0019\u0001B )\u0019\u0011\u0019Ba7\u0003^\"Q!qFA\u001a!\u0003\u0005\rAa\r\t\u0015\tm\u00121\u0007I\u0001\u0002\u0004\u0011y$\u0006\u0002\u0003b*\"!q\bB3)\u0011\u00119J!:\t\u0015\t}\u0015QHA\u0001\u0002\u0004\u0011\u0019\u0004\u0006\u0003\u00036\n%\bB\u0003BP\u0003\u0003\n\t\u00111\u0001\u0003\u0018R!!\u0011\u0011Bw\u0011)\u0011y*a\u0011\u0002\u0002\u0003\u0007!1\u0007\u000b\u0005\u0005k\u0013\t\u0010\u0003\u0006\u0003 \u0006%\u0013\u0011!a\u0001\u0005/Cq!a<\u0005\u0001\u0004\t\t0A\u0006nC\u001eL7MT;nE\u0016\u0014XC\u0001B}!1\u0011YP!@\u0004\u0002\r\u0005!1\u0007BA\u001b\t\t9,\u0003\u0003\u0003\u0000\u0006]&\u0001\u0002*vY\u0016\u0004BA!\u0006\u0004\u0004%!1QAAn\u0005\u0005\u0019\u0016\u0001D7bO&\u001cg*^7cKJ\u0004\u0013a\u0002<feNLwN\\\u000b\u0003\u0007\u001b\u0001BBa?\u0003~\u000e\u00051\u0011AB\b\u0007+\u0001\u0002\"a4\u0004\u0012\tM\"1G\u0005\u0005\u0007'\t\tN\u0001\u0004UkBdWM\r\t\u0005\u0003\u001f\u001c9\"\u0003\u0003\u0004\u001a\u0005E'a\u0002(pi\"LgnZ\u0001\tm\u0016\u00148/[8oA\u0005a1m\u001c8ti\u0006tG\u000fU8pYV\u00111\u0011\u0005\t\r\u0005w\u0014ip!\u0001\u0004\u0002\r\r2Q\u0003\t\u0005\u0003\u000f\u001c)#\u0003\u0003\u0004(\u0005M&\u0001D\"p]N$\u0018M\u001c;Q_>d\u0017!D2p]N$\u0018M\u001c;Q_>d\u0007%\u0001\u0006vi\u001aD4\u000b\u001e:j]\u001e,\"aa\f\u0011\u0019\tm(Q`B\u0001\u0007\u0003\u0019\td!\u0006\u0011\u0011\u0005=71GB\u0012\u0007GIAa!\u000e\u0002R\nIa)\u001e8di&|g.M\u0001\fkR4\u0007h\u0015;sS:<\u0007%A\u0006j]R\u001cuN\\:uC:$\u0018\u0001D5oi\u000e{gn\u001d;b]R\u0004\u0013!\u00044m_\u0006$8i\u001c8ti\u0006tG/\u0001\bgY>\fGoQ8ogR\fg\u000e\u001e\u0011\u0002\u00191|gnZ\"p]N$\u0018M\u001c;\u0002\u001b1|gnZ\"p]N$\u0018M\u001c;!\u00039!w.\u001e2mK\u000e{gn\u001d;b]R\fq\u0002Z8vE2,7i\u001c8ti\u0006tG\u000fI\u0001\tG2\f7o\u001d*fM\u0006I1\r\\1tgJ+g\rI\u0001\ngR\u0014\u0018N\\4SK\u001a\f!b\u001d;sS:<'+\u001a4!\u0003!1\u0017.\u001a7e%\u00164\u0017!\u00034jK2$'+\u001a4!\u0003%iW\r\u001e5pIJ+g-\u0001\u0006nKRDw\u000e\u001a*fM\u0002\n!#\u001b8uKJ4\u0017mY3NKRDw\u000e\u001a*fM\u0006\u0019\u0012N\u001c;fe\u001a\f7-Z'fi\"|GMU3gA\u0005Ya.Y7f\u0003:$G+\u001f9f\u00031q\u0017-\\3B]\u0012$\u0016\u0010]3!\u00031iW\r\u001e5pI\"\u000bg\u000e\u001a7f\u00035iW\r\u001e5pI\"\u000bg\u000e\u001a7fA\u0005QQ.\u001a;i_\u0012$\u0016\u0010]3\u0002\u00175,G\u000f[8e)f\u0004X\rI\u0001\u000eS:4xn[3Es:\fW.[2\u0002\u001d%tgo\\6f\tft\u0017-\\5dA\u0005q1m\u001c8ti\u0006tG/T8ek2,\u0017aD2p]N$\u0018M\u001c;N_\u0012,H.\u001a\u0011\u0002\u001f\r|gn\u001d;b]R\u0004\u0016mY6bO\u0016\f\u0001cY8ogR\fg\u000e\u001e)bG.\fw-\u001a\u0011\u0002#\r|gn\u001d;b]R\u0004vn\u001c7F]R\u0014\u00180\u0001\nd_:\u001cH/\u00198u!>|G.\u00128uef\u0004\u0013AC5oi\u0016\u0014h-Y2fgV\u00111Q\u0010\t\r\u0005w\u0014ip!\u0001\u0004\u0002\r}4Q\u0003\t\u0007\u0003{\u0014iAa\r\u0002\u0017%tG/\u001a:gC\u000e,7\u000fI\u0001\nCR$(/\u001b2vi\u0016,\"aa\"\u0011\u0019\tm(Q`B\u0001\u0007\u0003\u0019Ii!\u0006\u0011\t\u0005\u001d71R\u0005\u0005\u0007\u001b\u000b\u0019LA\u0005BiR\u0014\u0018NY;uK\u0006Q\u0011\r\u001e;sS\n,H/\u001a\u0011\u0002\u0015\u0005$HO]5ckR,7/\u0006\u0002\u0004\u0016Ba!1 B\u007f\u0007\u0003\u0019\taa&\u0004\u0016A1\u0011Q B\u0007\u0007\u0013\u000b1\"\u0019;ue&\u0014W\u000f^3tA\u0005\t\u0012I\u001c8pi\u0006$\u0018n\u001c8FY\u0016lWM\u001c;\u0011\u0007\tU\u0001jE\u0003I\u0007C\u001bi\u000b\u0005\u0006\u0004$\u000e%&1\u0007B\u000e\u0005\u0003j!a!*\u000b\t\r\u001d\u0016\u0011[\u0001\beVtG/[7f\u0013\u0011\u0019Yk!*\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0005\u0003\u00040\u000eUVBABY\u0015\u0011\u0019\u0019L!#\u0002\u0005%|\u0017\u0002\u0002B\u0017\u0007c#\"a!(\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\r\t\u00053QXB`\u0011\u001d\u00119e\u0013a\u0001\u0005gAqAa\u0013L\u0001\u0004\u0011Y\"A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\r\u00157Q\u001a\t\u0007\u0003\u001f\u001c9ma3\n\t\r%\u0017\u0011\u001b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0011\u0005=7\u0011\u0003B\u001a\u00057A\u0011ba4M\u0003\u0003\u0005\rA!\u0011\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004VB!!1QBl\u0013\u0011\u0019IN!\"\u0003\r=\u0013'.Z2u\u0005=\u0019uN\\:u-\u0006dW/Z%oI\u0016D8c\u0002(\u0003\u001c\t\r\"\u0011F\u0001\u0006S:$W\r_\u0001\u0007S:$W\r\u001f\u0011\u0015\t\r\u00158q\u001d\t\u0004\u0005+q\u0005bBBp#\u0002\u0007!1\u0007\u000b\u0005\u0007K\u001cY\u000fC\u0005\u0004`J\u0003\n\u00111\u0001\u00034Q!!qSBx\u0011%\u0011yJVA\u0001\u0002\u0004\u0011\u0019\u0004\u0006\u0003\u00036\u000eM\b\"\u0003BP1\u0006\u0005\t\u0019\u0001BL)\u0011\u0011\tia>\t\u0013\t}\u0015,!AA\u0002\tMB\u0003\u0002B[\u0007wD\u0011Ba(]\u0003\u0003\u0005\rAa&\u0002\u001f\r{gn\u001d;WC2,X-\u00138eKb\u00042A!\u0006_'\u0015qF1ABW!!\u0019\u0019\u000b\"\u0002\u00034\r\u0015\u0018\u0002\u0002C\u0004\u0007K\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82)\t\u0019y\u0010\u0006\u0003\u0004f\u00125\u0001bBBpC\u0002\u0007!1\u0007\u000b\u0005\t#!\u0019\u0002\u0005\u0004\u0002P\u000e\u001d'1\u0007\u0005\n\u0007\u001f\u0014\u0017\u0011!a\u0001\u0007K\u0014a\"\u00128v[\u000e{gn\u001d;WC2,XmE\u0004e\u00057\u0011\u0019C!\u000b\u0002\u001bQL\b/\u001a(b[\u0016Le\u000eZ3y\u00039!\u0018\u0010]3OC6,\u0017J\u001c3fq\u0002\nabY8ogRt\u0015-\\3J]\u0012,\u00070A\bd_:\u001cHOT1nK&sG-\u001a=!)\u0019!\u0019\u0003\"\n\u0005(A\u0019!Q\u00033\t\u000f\u0011e\u0011\u000e1\u0001\u00034!9AQD5A\u0002\tMBC\u0002C\u0012\tW!i\u0003C\u0005\u0005\u001a)\u0004\n\u00111\u0001\u00034!IAQ\u00046\u0011\u0002\u0003\u0007!1\u0007\u000b\u0005\u0005/#\t\u0004C\u0005\u0003 >\f\t\u00111\u0001\u00034Q!!Q\u0017C\u001b\u0011%\u0011y*]A\u0001\u0002\u0004\u00119\n\u0006\u0003\u0003\u0002\u0012e\u0002\"\u0003BPe\u0006\u0005\t\u0019\u0001B\u001a)\u0011\u0011)\f\"\u0010\t\u0013\t}U/!AA\u0002\t]\u0015AD#ok6\u001cuN\\:u-\u0006dW/\u001a\t\u0004\u0005+98#B<\u0005F\r5\u0006CCBR\u0007S\u0013\u0019Da\r\u0005$Q\u0011A\u0011\t\u000b\u0007\tG!Y\u0005\"\u0014\t\u000f\u0011e!\u00101\u0001\u00034!9AQ\u0004>A\u0002\tMB\u0003\u0002C)\t'\u0002b!a4\u0004H\u000e=\u0001\"CBhw\u0006\u0005\t\u0019\u0001C\u0012\u00059\u0019E.Y:t\u0013:4w.\u00138eKb\u001cr! B\u000e\u0005G\u0011I\u0003\u0006\u0003\u0005\\\u0011u\u0003c\u0001B\u000b{\"A1q\\A\u0001\u0001\u0004\u0011\u0019\u0004\u0006\u0003\u0005\\\u0011\u0005\u0004BCBp\u0003\u0007\u0001\n\u00111\u0001\u00034Q!!q\u0013C3\u0011)\u0011y*a\u0003\u0002\u0002\u0003\u0007!1\u0007\u000b\u0005\u0005k#I\u0007\u0003\u0006\u0003 \u0006=\u0011\u0011!a\u0001\u0005/#BA!!\u0005n!Q!qTA\t\u0003\u0003\u0005\rAa\r\u0015\t\tUF\u0011\u000f\u0005\u000b\u0005?\u000b9\"!AA\u0002\t]\u0015AD\"mCN\u001c\u0018J\u001c4p\u0013:$W\r\u001f\t\u0005\u0005+\tYb\u0005\u0004\u0002\u001c\u0011e4Q\u0016\t\t\u0007G#)Aa\r\u0005\\Q\u0011AQ\u000f\u000b\u0005\t7\"y\b\u0003\u0005\u0004`\u0006\u0005\u0002\u0019\u0001B\u001a)\u0011!\t\u0002b!\t\u0015\r=\u00171EA\u0001\u0002\u0004!Y&\u0001\u0006B]:|G/\u0019;j_:\u0004BA!\u0006\u0002NM1\u0011Q\nCF\u0007[\u0003\"ba)\u0004*\nM\"q\bB\n)\t!9\t\u0006\u0004\u0003\u0014\u0011EE1\u0013\u0005\t\u0005_\t\u0019\u00061\u0001\u00034!A!1HA*\u0001\u0004\u0011y\u0004\u0006\u0003\u0005\u0018\u0012m\u0005CBAh\u0007\u000f$I\n\u0005\u0005\u0002P\u000eE!1\u0007B \u0011)\u0019y-!\u0016\u0002\u0002\u0003\u0007!1\u0003\u0002\u000b\u0003J\u0014\u0018-\u001f,bYV,7\u0003CA-\u00057\u0011\u0019C!\u000b\u0002\rY\fG.^3t+\t!)\u000b\u0005\u0004\u0002~\n5!1D\u0001\bm\u0006dW/Z:!)\u0011!Y\u000b\",\u0011\t\tU\u0011\u0011\f\u0005\t\tC\u000by\u00061\u0001\u0005&R!A1\u0016CY\u0011)!\t+!\u0019\u0011\u0002\u0003\u0007AQU\u000b\u0003\tkSC\u0001\"*\u0003fQ!!q\u0013C]\u0011)\u0011y*!\u001b\u0002\u0002\u0003\u0007!1\u0007\u000b\u0005\u0005k#i\f\u0003\u0006\u0003 \u00065\u0014\u0011!a\u0001\u0005/#BA!!\u0005B\"Q!qTA8\u0003\u0003\u0005\rAa\r\u0015\t\tUFQ\u0019\u0005\u000b\u0005?\u000b)(!AA\u0002\t]\u0015AC!se\u0006Lh+\u00197vKB!!QCA='\u0019\tI\b\"4\u0004.BA11\u0015C\u0003\tK#Y\u000b\u0006\u0002\u0005JR!A1\u0016Cj\u0011!!\t+a A\u0002\u0011\u0015F\u0003\u0002Cl\t3\u0004b!a4\u0004H\u0012\u0015\u0006BCBh\u0003\u0003\u000b\t\u00111\u0001\u0005,\u0006iQ\r\\3nK:$xL^1mk\u0016,\"\u0001b8\u0011\r\tUA\u0011\u001dB\u000e\u0013\u0011!\u0019/a7\u0003\rA\u000b'o]3s\u0003I)G.Z7f]R|f/\u00197vK~\u0003\u0018-\u001b:\u0016\u0005\u0011%\b\u0003\u0004B~\u0005{\u001c\ta!\u0001\u0003B\u0011-\b\u0003\u0002Cw\tktA\u0001b<\u0005rB!!\u0011AAi\u0013\u0011!\u00190!5\u0002\rA\u0013X\rZ3g\u0013\u0011\u0011y\tb>\u000b\t\u0011M\u0018\u0011[\u0001\u0014K2,W.\u001a8u?Z\fG.^3`a\u0006L'\u000fI\u000b\u0003\t{\u0004bA!\u0006\u0005b\nM\u0011aC1o]>$\u0018\r^5p]\u0002\n1\"\u00198o_R\fG/[8ogV\u0011QQ\u0001\t\r\u0005w\u0014ip!\u0001\u0004\u0002\u0005mH1^\u0001\rC:tw\u000e^1uS>t7\u000fI\u0001\u0006M&,G\u000eZ\u000b\u0003\u000b\u001b\u0001BBa?\u0003~\u000e\u00051\u0011AC\b\u0007+\u0001B!a2\u0006\u0012%!Q1CAZ\u0005\u00151\u0015.\u001a7e\u0003\u00191\u0017.\u001a7eA\u00051a-[3mIN,\"!b\u0007\u0011\u0019\tm(Q`B\u0001\u0007\u0003)ib!\u0006\u0011\r\u0005u(QBC\b\u0003\u001d1\u0017.\u001a7eg\u0002\na!\\3uQ>$WCAC\u0013!1\u0011YP!@\u0004\u0002\r\u0005QqEB\u000b!\u0011\t9-\"\u000b\n\t\u0015-\u00121\u0017\u0002\u0007\u001b\u0016$\bn\u001c3\u0002\u000f5,G\u000f[8eA\u00059Q.\u001a;i_\u0012\u001cXCAC\u001a!1\u0011YP!@\u0004\u0002\r\u0005QQGB\u000b!\u0019\tiP!\u0004\u0006(\u0005AQ.\u001a;i_\u0012\u001c\b%\u0001\u0004iK\u0006$WM]\u000b\u0003\u000b{\u0001BBa?\u0003~\u000e\u00051\u0011AC \u0005\u0003\u0003B!a2\u0006B%!Q1IAZ\u0005=\u0019E.Y:t\r&dW\rS3bI\u0016\u0014\u0018a\u00025fC\u0012,'\u000fI\u0001\nG2\f7o\u001d$jY\u0016,\"!b\u0013\u0011\u0019\tm(Q`B\u0001\u0007\u0003\t9O!!\u0002\u0015\rd\u0017m]:GS2,\u0007%A\u0005nK6\u0014WM\u001d*fMR!1qFC*\u0011!))&a+A\u0002\u0011-\u0018a\u00033fg\u000e\u0014\u0018\u000e\u001d;j_:\fA!\u00193ecU!Q1LC7)\u0011)i&b\u001d\u0015\t\u0015}SQ\r\u000b\u0005\u0007G)\t\u0007\u0003\u0005\u0006d\u00055\u0006\u0019AB\u0012\u0003\u0011\u0001xn\u001c7\t\u0011\u0015\u001d\u0014Q\u0016a\u0001\u000bS\n1A]1x!\u0011)Y'\"\u001c\r\u0001\u0011AQqNAW\u0005\u0004)\tHA\u0001U#\u0011\u0019)Ba&\t\u0011\u0015U\u0014Q\u0016a\u0001\u000bo\n\u0011A\u001a\t\t\u0003\u001f\u001c\u0019$\"\u001b\u0006zAA\u0011qZB\u001a\u0007G\u00119*\u0001\u0003bI\u0012\u0014T\u0003BC@\u000b\u0017#B!\"!\u0006\u000eR!Q1QCD)\u0011\u0019\u0019#\"\"\t\u0011\u0015\r\u0014q\u0016a\u0001\u0007GA\u0001\"b\u001a\u00020\u0002\u0007Q\u0011\u0012\t\u0005\u000bW*Y\t\u0002\u0005\u0006p\u0005=&\u0019AC9\u0011!))(a,A\u0002\u0015=\u0005\u0003CAh\u0007g)I)\"\u001f"
)
public final class ClassFileParser {
   public static ConstantPool add2(final Function1 f, final Object raw, final ConstantPool pool) {
      return ClassFileParser$.MODULE$.add2(f, raw, pool);
   }

   public static ConstantPool add1(final Function1 f, final Object raw, final ConstantPool pool) {
      return ClassFileParser$.MODULE$.add1(f, raw, pool);
   }

   public static Rule memberRef(final String description) {
      return ClassFileParser$.MODULE$.memberRef(description);
   }

   public static Rule classFile() {
      return ClassFileParser$.MODULE$.classFile();
   }

   public static Rule header() {
      return ClassFileParser$.MODULE$.header();
   }

   public static Rule methods() {
      return ClassFileParser$.MODULE$.methods();
   }

   public static Rule method() {
      return ClassFileParser$.MODULE$.method();
   }

   public static Rule fields() {
      return ClassFileParser$.MODULE$.fields();
   }

   public static Rule field() {
      return ClassFileParser$.MODULE$.field();
   }

   public static Rule annotations() {
      return ClassFileParser$.MODULE$.annotations();
   }

   public static Rule annotation() {
      return ClassFileParser$.MODULE$.annotation();
   }

   public static Rule element_value_pair() {
      return ClassFileParser$.MODULE$.element_value_pair();
   }

   public static Rule element_value() {
      return ClassFileParser$.MODULE$.element_value();
   }

   public static Rule attributes() {
      return ClassFileParser$.MODULE$.attributes();
   }

   public static Rule attribute() {
      return ClassFileParser$.MODULE$.attribute();
   }

   public static Rule interfaces() {
      return ClassFileParser$.MODULE$.interfaces();
   }

   public static Rule constantPoolEntry() {
      return ClassFileParser$.MODULE$.constantPoolEntry();
   }

   public static Rule constantPackage() {
      return ClassFileParser$.MODULE$.constantPackage();
   }

   public static Rule constantModule() {
      return ClassFileParser$.MODULE$.constantModule();
   }

   public static Rule invokeDynamic() {
      return ClassFileParser$.MODULE$.invokeDynamic();
   }

   public static Rule methodType() {
      return ClassFileParser$.MODULE$.methodType();
   }

   public static Rule methodHandle() {
      return ClassFileParser$.MODULE$.methodHandle();
   }

   public static Rule nameAndType() {
      return ClassFileParser$.MODULE$.nameAndType();
   }

   public static Rule interfaceMethodRef() {
      return ClassFileParser$.MODULE$.interfaceMethodRef();
   }

   public static Rule methodRef() {
      return ClassFileParser$.MODULE$.methodRef();
   }

   public static Rule fieldRef() {
      return ClassFileParser$.MODULE$.fieldRef();
   }

   public static Rule stringRef() {
      return ClassFileParser$.MODULE$.stringRef();
   }

   public static Rule classRef() {
      return ClassFileParser$.MODULE$.classRef();
   }

   public static Rule doubleConstant() {
      return ClassFileParser$.MODULE$.doubleConstant();
   }

   public static Rule longConstant() {
      return ClassFileParser$.MODULE$.longConstant();
   }

   public static Rule floatConstant() {
      return ClassFileParser$.MODULE$.floatConstant();
   }

   public static Rule intConstant() {
      return ClassFileParser$.MODULE$.intConstant();
   }

   public static Rule utf8String() {
      return ClassFileParser$.MODULE$.utf8String();
   }

   public static Rule constantPool() {
      return ClassFileParser$.MODULE$.constantPool();
   }

   public static Rule version() {
      return ClassFileParser$.MODULE$.version();
   }

   public static Rule magicNumber() {
      return ClassFileParser$.MODULE$.magicNumber();
   }

   public static Seq parseAnnotations(final ByteCode byteCode) {
      return ClassFileParser$.MODULE$.parseAnnotations(byteCode);
   }

   public static ClassFile parse(final ByteCode byteCode) {
      return ClassFileParser$.MODULE$.parse(byteCode);
   }

   public static Rule bytes(final int n) {
      return ClassFileParser$.MODULE$.bytes(n);
   }

   public static Rule u4() {
      return ClassFileParser$.MODULE$.u4();
   }

   public static Rule u2() {
      return ClassFileParser$.MODULE$.u2();
   }

   public static Rule u1() {
      return ClassFileParser$.MODULE$.u1();
   }

   public static Rule byte() {
      return ClassFileParser$.MODULE$.byte();
   }

   public static RulesWithState factory() {
      return ClassFileParser$.MODULE$.factory();
   }

   public static Rule repeatUntil(final Rule rule, final Function1 finished, final Object initial) {
      return ClassFileParser$.MODULE$.repeatUntil(rule, finished, initial);
   }

   public static Rule anyOf(final Seq rules) {
      return ClassFileParser$.MODULE$.anyOf(rules);
   }

   public static Function1 allOf(final Seq rules) {
      return ClassFileParser$.MODULE$.allOf(rules);
   }

   public static Rule cond(final Function1 f) {
      return ClassFileParser$.MODULE$.cond(f);
   }

   public static Rule none() {
      return ClassFileParser$.MODULE$.none();
   }

   public static Rule nil() {
      return ClassFileParser$.MODULE$.nil();
   }

   public static Rule update(final Function1 f) {
      return ClassFileParser$.MODULE$.update(f);
   }

   public static Rule set(final Function0 s) {
      return ClassFileParser$.MODULE$.set(s);
   }

   public static Rule get() {
      return ClassFileParser$.MODULE$.get();
   }

   public static Rule read(final Function1 f) {
      return ClassFileParser$.MODULE$.read(f);
   }

   public static Rule unit(final Function0 a) {
      return ClassFileParser$.MODULE$.unit(a);
   }

   public static Rule apply(final Function1 f) {
      return ClassFileParser$.MODULE$.apply(f);
   }

   public static Function1 expect(final Rule rule) {
      return ClassFileParser$.MODULE$.expect(rule);
   }

   public static Rule ruleWithName(final String _name, final Function1 f) {
      return ClassFileParser$.MODULE$.ruleWithName(_name, f);
   }

   public static Rule oneOf(final Seq rules) {
      return ClassFileParser$.MODULE$.oneOf(rules);
   }

   public static Rule error(final Object err) {
      return ClassFileParser$.MODULE$.error(err);
   }

   public static Rule error() {
      return ClassFileParser$.MODULE$.error();
   }

   public static Rule failure() {
      return ClassFileParser$.MODULE$.failure();
   }

   public static Rule success(final Object out, final Object a) {
      return ClassFileParser$.MODULE$.success(out, a);
   }

   public static StateRules state() {
      return ClassFileParser$.MODULE$.state();
   }

   public static Rules.FromRule from() {
      return ClassFileParser$.MODULE$.from();
   }

   public static SeqRule seqRule(final Rule rule) {
      return ClassFileParser$.MODULE$.seqRule(rule);
   }

   public static InRule inRule(final Rule rule) {
      return ClassFileParser$.MODULE$.inRule(rule);
   }

   public static Rule rule(final Function1 f) {
      return ClassFileParser$.MODULE$.rule(f);
   }

   public abstract static class ElementValue {
   }

   public static class AnnotationElement implements Product, Serializable {
      private final int elementNameIndex;
      private final ElementValue elementValue;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int elementNameIndex() {
         return this.elementNameIndex;
      }

      public ElementValue elementValue() {
         return this.elementValue;
      }

      public AnnotationElement copy(final int elementNameIndex, final ElementValue elementValue) {
         return new AnnotationElement(elementNameIndex, elementValue);
      }

      public int copy$default$1() {
         return this.elementNameIndex();
      }

      public ElementValue copy$default$2() {
         return this.elementValue();
      }

      public String productPrefix() {
         return "AnnotationElement";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.elementNameIndex());
               break;
            case 1:
               var10000 = this.elementValue();
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
         return x$1 instanceof AnnotationElement;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "elementNameIndex";
               break;
            case 1:
               var10000 = "elementValue";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.elementNameIndex());
         var1 = Statics.mix(var1, Statics.anyHash(this.elementValue()));
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
               if (x$1 instanceof AnnotationElement) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label38: {
                     AnnotationElement var4 = (AnnotationElement)x$1;
                     if (this.elementNameIndex() == var4.elementNameIndex()) {
                        label36: {
                           ElementValue var10000 = this.elementValue();
                           ElementValue var5 = var4.elementValue();
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

      public AnnotationElement(final int elementNameIndex, final ElementValue elementValue) {
         this.elementNameIndex = elementNameIndex;
         this.elementValue = elementValue;
         Product.$init$(this);
      }
   }

   public static class AnnotationElement$ extends AbstractFunction2 implements Serializable {
      public static final AnnotationElement$ MODULE$ = new AnnotationElement$();

      public final String toString() {
         return "AnnotationElement";
      }

      public AnnotationElement apply(final int elementNameIndex, final ElementValue elementValue) {
         return new AnnotationElement(elementNameIndex, elementValue);
      }

      public Option unapply(final AnnotationElement x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.elementNameIndex()), x$0.elementValue())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AnnotationElement$.class);
      }
   }

   public static class ConstValueIndex extends ElementValue implements Product, Serializable {
      private final int index;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int index() {
         return this.index;
      }

      public ConstValueIndex copy(final int index) {
         return new ConstValueIndex(index);
      }

      public int copy$default$1() {
         return this.index();
      }

      public String productPrefix() {
         return "ConstValueIndex";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.index());
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
         return x$1 instanceof ConstValueIndex;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "index";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.index());
         return Statics.finalizeHash(var1, 1);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof ConstValueIndex) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  ConstValueIndex var4 = (ConstValueIndex)x$1;
                  if (this.index() == var4.index() && var4.canEqual(this)) {
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

      public ConstValueIndex(final int index) {
         this.index = index;
         Product.$init$(this);
      }
   }

   public static class ConstValueIndex$ extends AbstractFunction1 implements Serializable {
      public static final ConstValueIndex$ MODULE$ = new ConstValueIndex$();

      public final String toString() {
         return "ConstValueIndex";
      }

      public ConstValueIndex apply(final int index) {
         return new ConstValueIndex(index);
      }

      public Option unapply(final ConstValueIndex x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.index())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ConstValueIndex$.class);
      }
   }

   public static class EnumConstValue extends ElementValue implements Product, Serializable {
      private final int typeNameIndex;
      private final int constNameIndex;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int typeNameIndex() {
         return this.typeNameIndex;
      }

      public int constNameIndex() {
         return this.constNameIndex;
      }

      public EnumConstValue copy(final int typeNameIndex, final int constNameIndex) {
         return new EnumConstValue(typeNameIndex, constNameIndex);
      }

      public int copy$default$1() {
         return this.typeNameIndex();
      }

      public int copy$default$2() {
         return this.constNameIndex();
      }

      public String productPrefix() {
         return "EnumConstValue";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.typeNameIndex());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToInteger(this.constNameIndex());
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
         return x$1 instanceof EnumConstValue;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "typeNameIndex";
               break;
            case 1:
               var10000 = "constNameIndex";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.typeNameIndex());
         var1 = Statics.mix(var1, this.constNameIndex());
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label51: {
               boolean var2;
               if (x$1 instanceof EnumConstValue) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  EnumConstValue var4 = (EnumConstValue)x$1;
                  if (this.typeNameIndex() == var4.typeNameIndex() && this.constNameIndex() == var4.constNameIndex() && var4.canEqual(this)) {
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

      public EnumConstValue(final int typeNameIndex, final int constNameIndex) {
         this.typeNameIndex = typeNameIndex;
         this.constNameIndex = constNameIndex;
         Product.$init$(this);
      }
   }

   public static class EnumConstValue$ extends AbstractFunction2 implements Serializable {
      public static final EnumConstValue$ MODULE$ = new EnumConstValue$();

      public final String toString() {
         return "EnumConstValue";
      }

      public EnumConstValue apply(final int typeNameIndex, final int constNameIndex) {
         return new EnumConstValue(typeNameIndex, constNameIndex);
      }

      public Option unapply(final EnumConstValue x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcII.sp(x$0.typeNameIndex(), x$0.constNameIndex())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EnumConstValue$.class);
      }
   }

   public static class ClassInfoIndex extends ElementValue implements Product, Serializable {
      private final int index;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int index() {
         return this.index;
      }

      public ClassInfoIndex copy(final int index) {
         return new ClassInfoIndex(index);
      }

      public int copy$default$1() {
         return this.index();
      }

      public String productPrefix() {
         return "ClassInfoIndex";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.index());
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
         return x$1 instanceof ClassInfoIndex;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "index";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.index());
         return Statics.finalizeHash(var1, 1);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof ClassInfoIndex) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  ClassInfoIndex var4 = (ClassInfoIndex)x$1;
                  if (this.index() == var4.index() && var4.canEqual(this)) {
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

      public ClassInfoIndex(final int index) {
         this.index = index;
         Product.$init$(this);
      }
   }

   public static class ClassInfoIndex$ extends AbstractFunction1 implements Serializable {
      public static final ClassInfoIndex$ MODULE$ = new ClassInfoIndex$();

      public final String toString() {
         return "ClassInfoIndex";
      }

      public ClassInfoIndex apply(final int index) {
         return new ClassInfoIndex(index);
      }

      public Option unapply(final ClassInfoIndex x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.index())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ClassInfoIndex$.class);
      }
   }

   public static class Annotation extends ElementValue implements Product, Serializable {
      private final int typeIndex;
      private final Seq elementValuePairs;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int typeIndex() {
         return this.typeIndex;
      }

      public Seq elementValuePairs() {
         return this.elementValuePairs;
      }

      public Annotation copy(final int typeIndex, final Seq elementValuePairs) {
         return new Annotation(typeIndex, elementValuePairs);
      }

      public int copy$default$1() {
         return this.typeIndex();
      }

      public Seq copy$default$2() {
         return this.elementValuePairs();
      }

      public String productPrefix() {
         return "Annotation";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.typeIndex());
               break;
            case 1:
               var10000 = this.elementValuePairs();
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
         return x$1 instanceof Annotation;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "typeIndex";
               break;
            case 1:
               var10000 = "elementValuePairs";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.typeIndex());
         var1 = Statics.mix(var1, Statics.anyHash(this.elementValuePairs()));
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
               if (x$1 instanceof Annotation) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label38: {
                     Annotation var4 = (Annotation)x$1;
                     if (this.typeIndex() == var4.typeIndex()) {
                        label36: {
                           Seq var10000 = this.elementValuePairs();
                           Seq var5 = var4.elementValuePairs();
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

      public Annotation(final int typeIndex, final Seq elementValuePairs) {
         this.typeIndex = typeIndex;
         this.elementValuePairs = elementValuePairs;
         Product.$init$(this);
      }
   }

   public static class Annotation$ extends AbstractFunction2 implements Serializable {
      public static final Annotation$ MODULE$ = new Annotation$();

      public final String toString() {
         return "Annotation";
      }

      public Annotation apply(final int typeIndex, final Seq elementValuePairs) {
         return new Annotation(typeIndex, elementValuePairs);
      }

      public Option unapply(final Annotation x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToInteger(x$0.typeIndex()), x$0.elementValuePairs())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Annotation$.class);
      }
   }

   public static class ArrayValue extends ElementValue implements Product, Serializable {
      private final Seq values;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Seq values() {
         return this.values;
      }

      public ArrayValue copy(final Seq values) {
         return new ArrayValue(values);
      }

      public Seq copy$default$1() {
         return this.values();
      }

      public String productPrefix() {
         return "ArrayValue";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.values();
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
         return x$1 instanceof ArrayValue;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "values";
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
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof ArrayValue) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        ArrayValue var4 = (ArrayValue)x$1;
                        Seq var10000 = this.values();
                        Seq var5 = var4.values();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public ArrayValue(final Seq values) {
         this.values = values;
         Product.$init$(this);
      }
   }

   public static class ArrayValue$ extends AbstractFunction1 implements Serializable {
      public static final ArrayValue$ MODULE$ = new ArrayValue$();

      public final String toString() {
         return "ArrayValue";
      }

      public ArrayValue apply(final Seq values) {
         return new ArrayValue(values);
      }

      public Option unapply(final ArrayValue x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.values()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ArrayValue$.class);
      }
   }
}
