package breeze.generic;

import breeze.linalg.operators.HasOps;
import java.io.Serializable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019\rca\u0002 @!\u0003\r\t\u0001\u0012\u0005\u0006'\u0002!\t\u0001\u0016\u0005\u00061\u0002!)!\u0017\u0005\u00071\u0002!)\u0001b)\t\ra\u0003AQ\u0001C~\u0011\u0019A\u0006\u0001\"\u0002\u0006\\!9Q1\u0014\u0001\u0005\u0006\u0015u\u0005bBCN\u0001\u0011\u0015QQ\u0016\u0005\b\u000b7\u0003AQACb\u0011\u001d)y\u000e\u0001C\u0003\u000bC,a!a\u0002\u0001\u0001\u0005%QA\u0002C`\u0001\u0001!\t-\u0002\u0004\u0006\u0018\u0001\u0001Q\u0011D\u0003\u0007\u000bS\u0002\u0001!b\u001b\u0006\r\tU\u0006\u0001ACx\u000b\u0019\u0011)\r\u0001\u0001\u0006x\u00161!\u0011\u001e\u0001\u0001\r\u0007)aa!\u0002\u0001\u0001\u0019MQABB\u0010\u0001\u00011y\"\u0002\u0004\u0004J\u0001\u0001aqF\u0004\b\u0003#y\u0004\u0012AA\n\r\u0019qt\b#\u0001\u0002\u0016!9\u0011qC\u000b\u0005\u0002\u0005e\u0001B\u0002-\u0016\t\u0003\tY\u0002\u0003\u0004Y+\u0011\u0005\u00111\b\u0004\n\u00037*\u0002\u0013aI\u0001\u0003;Ba\u0001W\r\u0007\u0002\u0005=d!CAP+A\u0005\u0019\u0013AAQ\u0011\u0019A6D\"\u0001\u0002&\u001aI\u0011\u0011^\u000b\u0011\u0002G\u0005\u00111\u001e\u0005\u00071v1\t!a<\u0007\u0013\t\u0015S\u0003%A\u0012\u0002\t\u001d\u0003B\u0002- \r\u0003\u0011YEB\u0005\u00036V\u0001\n1%\u0001\u00038\"1\u0001,\tD\u0001\u0005w3\u0011B!2\u0016!\u0003\r\nAa2\t\ra\u001bc\u0011\u0001Bf\r%\u0011I/\u0006I\u0001$\u0003\u0011Y\u000f\u0003\u0004YK\u0019\u0005!q\u001e\u0004\n\u0007\u000b)\u0002\u0013aI\u0001\u0007\u000fAa\u0001W\u0014\u0007\u0002\r-a!CB\u0010+A\u0005\u0019\u0013AB\u0011\u0011\u0019A\u0016F\"\u0001\u0004&\u0019I1\u0011J\u000b\u0011\u0002G\u000511\n\u0005\u00071.2\taa\u0014\u0007\r\r-TCAB7\u0011)\u00199(\fBC\u0002\u0013\u00051\u0011\u0010\u0005\u000b\u0007\u007fj#\u0011!Q\u0001\n\rm\u0004bBA\f[\u0011\u00051\u0011\u0011\u0005\u000716\"\ta!$\t\rakC\u0011ABO\u0011\u0019AV\u0006\"\u0001\u00044\"I1qZ\u0017\u0002\u0002\u0013\u00053\u0011\u001b\u0005\n\u00073l\u0013\u0011!C!\u00077<\u0011ba:\u0016\u0003\u0003E\ta!;\u0007\u0013\r-T#!A\t\u0002\r-\bbBA\fo\u0011\u00051Q\u001e\u0005\b\u0007_<DQABy\u0011\u001d\u0019yo\u000eC\u0003\t#Aqaa<8\t\u000b!)\u0004C\u0005\u0005`]\n\t\u0011\"\u0002\u0005b!IA\u0011O\u001c\u0002\u0002\u0013\u0015A1\u000f\u0002\u0006+\u001a+hn\u0019\u0006\u0003\u0001\u0006\u000bqaZ3oKJL7MC\u0001C\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001F\u0017B\u0011a)S\u0007\u0002\u000f*\t\u0001*A\u0003tG\u0006d\u0017-\u0003\u0002K\u000f\n1\u0011I\\=SK\u001a\u0004\"\u0001T)\u000e\u00035S!AT(\u0002\u0013=\u0004XM]1u_J\u001c(B\u0001)B\u0003\u0019a\u0017N\\1mO&\u0011!+\u0014\u0002\u0007\u0011\u0006\u001cx\n]:\u0002\r\u0011Jg.\u001b;%)\u0005)\u0006C\u0001$W\u0013\t9vI\u0001\u0003V]&$\u0018!B1qa2LX\u0003\u0002.\u0005\u0012z#2a\u0017CQ)\tav\u0010\u0005\u0002^=2\u0001A!C0\u0003A\u0003\u0005\tQ1\u0001a\u0005\t1&+\u0005\u0002bIB\u0011aIY\u0005\u0003G\u001e\u0013qAT8uQ&tw\r\u0005\u0002GK&\u0011am\u0012\u0002\u0004\u0003:L\b&\u00020iWVT\bC\u0001$j\u0013\tQwIA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012m[>tgB\u0001$n\u0013\tqw)A\u0002J]R\fD\u0001\n9u\u0011:\u0011\u0011\u000f^\u0007\u0002e*\u00111oQ\u0001\u0007yI|w\u000e\u001e \n\u0003!\u000bTa\t<xsbt!AR<\n\u0005a<\u0015A\u0002#pk\ndW-\r\u0003%aRD\u0015'B\u0012|yzlhB\u0001$}\u0013\tix)A\u0003GY>\fG/\r\u0003%aRD\u0005bBA\u0001\u0005\u0001\u000f\u00111A\u0001\u0005S6\u0004H\u000e\u0005\u0004\u0002\u0006)!y\tX\u0007\u0002\u0001\t!\u0011*\u001c9m+\u0019\tY\u0001\"#\u0005\u000eBI\u0011QB\r\u0002\u0006\u0011\u001dE1\u0012\b\u0004\u0003\u001f!R\"A \u0002\u000bU3UO\\2\u0011\u0007\u0005=Qc\u0005\u0002\u0016\u000b\u00061A(\u001b8jiz\"\"!a\u0005\u0016\r\u0005u\u0011qEA\u0017)\u0011\ty\"!\r\u0011\u0011\u0005=\u0011\u0011EA\u0013\u0003WI1!a\t@\u000559&/\u00199qK\u0012,f)\u001e8dcA\u0019Q,a\n\u0005\r\u0005%rC1\u0001a\u0005\t\t\u0015\u0007E\u0002^\u0003[!a!a\f\u0018\u0005\u0004\u0001'!\u0001*\t\u000f\u0005Mr\u00031\u0001\u00026\u0005\ta\rE\u0004G\u0003o\t)#a\u000b\n\u0007\u0005erIA\u0005Gk:\u001cG/[8ocUA\u0011QHA$\u0003\u0017\n\t\u0006\u0006\u0003\u0002@\u0005M\u0003CCA\b\u0003\u0003\n)%!\u0013\u0002P%\u0019\u00111I \u0003\u001b]\u0013\u0018\r\u001d9fIV3UO\\23!\ri\u0016q\t\u0003\u0007\u0003SA\"\u0019\u00011\u0011\u0007u\u000bY\u0005\u0002\u0004\u0002Na\u0011\r\u0001\u0019\u0002\u0003\u0003J\u00022!XA)\t\u0019\ty\u0003\u0007b\u0001A\"9\u00111\u0007\rA\u0002\u0005U\u0003#\u0003$\u0002X\u0005\u0015\u0013\u0011JA(\u0013\r\tIf\u0012\u0002\n\rVt7\r^5p]J\u0012Q!V%na2,\u0002\"a\u0018\u0002\u001c\u0006%\u00151O\n\u00053\u0015\u000b\t\u0007\u0005\u0003\u0002d\u0005%db\u00019\u0002f%\u0019\u0011qM$\u0002\u000fA\f7m[1hK&!\u00111NA7\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\r\t9g\u0012\u000b\u0005\u0003c\n\u0019\tE\u0002^\u0003g\"\u0011bX\r!\u0002\u0003%)\u0019\u00011)\u0013\u0005M\u0004.a\u001e\u0002|\u0005}\u0014GB\u0012m[\u0006ed.\r\u0003%aRD\u0015GB\u0012wo\u0006u\u00040\r\u0003%aRD\u0015GB\u0012|y\u0006\u0005U0\r\u0003%aRD\u0005bBAC5\u0001\u0007\u0011qQ\u0001\u0002mB\u0019Q,!#\u0005\u0015\u0005-\u0015\u0004)A\u0001\u0002\u000b\u0007\u0001MA\u0001WQ%\tI\t[AH\u0003'\u000b9*\r\u0004$Y6\f\tJ\\\u0019\u0005IA$\b*\r\u0004$m^\f)\n_\u0019\u0005IA$\b*\r\u0004$wr\fI*`\u0019\u0005IA$\b\n\u0002\u0004\u0002\u001ef\u0011\r\u0001\u0019\u0002\u0004)\u0006<'AB+J[Bd''\u0006\u0006\u0002$\u0006\u001d\u0018QXAk\u0003S\u001bBaG#\u0002bQ1\u0011qUA]\u0003\u001f\u00042!XAU\t%y6\u0004)A\u0001\n\u000b\u0007\u0001\rK\u0005\u0002*\"\fi+!-\u00026F21\u0005\\7\u00020:\fD\u0001\n9u\u0011F21E^<\u00024b\fD\u0001\n9u\u0011F21e\u001f?\u00028v\fD\u0001\n9u\u0011\"9\u0011Q\u0011\u000fA\u0002\u0005m\u0006cA/\u0002>\u0012Q\u0011qX\u000e!\u0002\u0003\u0005)\u0019\u00011\u0003\u0005Y\u000b\u0004&CA_Q\u0006\r\u0017qYAfc\u0019\u0019C.\\Ac]F\"A\u0005\u001d;Ic\u0019\u0019co^AeqF\"A\u0005\u001d;Ic\u0019\u00193\u0010`Ag{F\"A\u0005\u001d;I\u0011\u001d\t\t\u000e\ba\u0001\u0003'\f!A\u001e\u001a\u0011\u0007u\u000b)\u000e\u0002\u0006\u0002Xn\u0001\u000b\u0011!AC\u0002\u0001\u0014!A\u0016\u001a)\u0013\u0005U\u0007.a7\u0002`\u0006\r\u0018GB\u0012m[\u0006ug.\r\u0003%aRD\u0015GB\u0012wo\u0006\u0005\b0\r\u0003%aRD\u0015GB\u0012|y\u0006\u0015X0\r\u0003%aRDEABAO7\t\u0007\u0001M\u0001\u0004V\u00136\u0004HnM\u000b\r\u0003[\u0014\u0019Ea\u0002\u0003\u001c\tE\u00121_\n\u0005;\u0015\u000b\t\u0007\u0006\u0005\u0002r\n\r!q\u0003B\u0016!\ri\u00161\u001f\u0003\n?v\u0001\u000b\u0011!CC\u0002\u0001D\u0013\"a=i\u0003o\fY0a@2\r\rbW.!?oc\u0011!\u0003\u000f\u001e%2\r\r2x/!@yc\u0011!\u0003\u000f\u001e%2\r\rZHP!\u0001~c\u0011!\u0003\u000f\u001e%\t\u000f\u0005\u0015e\u00041\u0001\u0003\u0006A\u0019QLa\u0002\u0005\u0015\u0005}V\u0004)A\u0001\u0002\u000b\u0007\u0001\rK\u0005\u0003\b!\u0014YAa\u0004\u0003\u0014E21\u0005\\7\u0003\u000e9\fD\u0001\n9u\u0011F21E^<\u0003\u0012a\fD\u0001\n9u\u0011F21e\u001f?\u0003\u0016u\fD\u0001\n9u\u0011\"9\u0011\u0011\u001b\u0010A\u0002\te\u0001cA/\u0003\u001c\u0011Q\u0011q[\u000f!\u0002\u0003\u0005)\u0019\u00011)\u0013\tm\u0001Na\b\u0003$\t\u001d\u0012GB\u0012m[\n\u0005b.\r\u0003%aRD\u0015GB\u0012wo\n\u0015\u00020\r\u0003%aRD\u0015GB\u0012|y\n%R0\r\u0003%aRD\u0005b\u0002B\u0017=\u0001\u0007!qF\u0001\u0003mN\u00022!\u0018B\u0019\t)\u0011\u0019$\bQ\u0001\u0002\u0003\u0015\r\u0001\u0019\u0002\u0003-NB\u0013B!\ri\u0005o\u0011YDa\u00102\r\rbWN!\u000foc\u0011!\u0003\u000f\u001e%2\r\r2xO!\u0010yc\u0011!\u0003\u000f\u001e%2\r\rZHP!\u0011~c\u0011!\u0003\u000f\u001e%\u0005\r\u0005uUD1\u0001a\u0005\u0019)\u0016*\u001c9miUq!\u0011\nBZ\u0005G\u00129Ha#\u0003\"\n=3\u0003B\u0010F\u0003C\"\"B!\u0014\u0003`\tM$q\u0011BN!\ri&q\n\u0003\n?~\u0001\u000b\u0011!CC\u0002\u0001D\u0013Ba\u0014i\u0005'\u00129Fa\u00172\r\rbWN!\u0016oc\u0011!\u0003\u000f\u001e%2\r\r2xO!\u0017yc\u0011!\u0003\u000f\u001e%2\r\rZHP!\u0018~c\u0011!\u0003\u000f\u001e%\t\u000f\u0005\u0015\u0005\u00051\u0001\u0003bA\u0019QLa\u0019\u0005\u0015\u0005}v\u0004)A\u0001\u0002\u000b\u0007\u0001\rK\u0005\u0003d!\u00149Ga\u001b\u0003pE21\u0005\\7\u0003j9\fD\u0001\n9u\u0011F21E^<\u0003na\fD\u0001\n9u\u0011F21e\u001f?\u0003ru\fD\u0001\n9u\u0011\"9\u0011\u0011\u001b\u0011A\u0002\tU\u0004cA/\u0003x\u0011Q\u0011q[\u0010!\u0002\u0003\u0005)\u0019\u00011)\u0013\t]\u0004Na\u001f\u0003\u0000\t\r\u0015GB\u0012m[\nud.\r\u0003%aRD\u0015GB\u0012wo\n\u0005\u00050\r\u0003%aRD\u0015GB\u0012|y\n\u0015U0\r\u0003%aRD\u0005b\u0002B\u0017A\u0001\u0007!\u0011\u0012\t\u0004;\n-EA\u0003B\u001a?\u0001\u0006\t\u0011!b\u0001A\"J!1\u00125\u0003\u0010\nM%qS\u0019\u0007G1l'\u0011\u001382\t\u0011\u0002H\u000fS\u0019\u0007GY<(Q\u0013=2\t\u0011\u0002H\u000fS\u0019\u0007Gmd(\u0011T?2\t\u0011\u0002H\u000f\u0013\u0005\b\u0005;\u0003\u0003\u0019\u0001BP\u0003\t1H\u0007E\u0002^\u0005C#!Ba) A\u0003\u0005\tQ1\u0001a\u0005\t1F\u0007K\u0005\u0003\"\"\u00149Ka+\u00030F21\u0005\\7\u0003*:\fD\u0001\n9u\u0011F21E^<\u0003.b\fD\u0001\n9u\u0011F21e\u001f?\u00032v\fD\u0001\n9u\u0011\u00121\u0011QT\u0010C\u0002\u0001\u00141\"\u00138QY\u0006\u001cW-S7qYV1!\u0011\u0018Bb\u0005\u0003\u001cB!I#\u0002bQ\u0019QK!0\t\u000f\u0005\u0015%\u00051\u0001\u0003@B\u0019QL!1\u0005\r\u0005-\u0015E1\u0001a\t\u0019\ti*\tb\u0001A\na\u0011J\u001c)mC\u000e,\u0017*\u001c9meUA!\u0011\u001aBt\u0005#\u00149n\u0005\u0003$\u000b\u0006\u0005D#B+\u0003N\nM\u0007bBACI\u0001\u0007!q\u001a\t\u0004;\nEGABAFG\t\u0007\u0001\rC\u0004\u0002R\u0012\u0002\rA!6\u0011\u0007u\u00139\u000e\u0002\u0006\u0002X\u000e\u0002\u000b\u0011!AC\u0002\u0001D\u0013Ba6i\u00057\u0014yNa92\r\rbWN!8oc\u0011!\u0003\u000f\u001e%2\r\r2xO!9yc\u0011!\u0003\u000f\u001e%2\r\rZHP!:~c\u0011!\u0003\u000f\u001e%\u0005\r\u0005u5E1\u0001a\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d74+)\u0011ioa\u0001\u0003v\nm8\u0011A\n\u0005K\u0015\u000b\t\u0007F\u0004V\u0005c\u00149P!@\t\u000f\u0005\u0015e\u00051\u0001\u0003tB\u0019QL!>\u0005\r\u0005-UE1\u0001a\u0011\u001d\t\tN\na\u0001\u0005s\u00042!\u0018B~\t\u0019\t9.\nb\u0001A\"9!Q\u0006\u0014A\u0002\t}\bcA/\u0004\u0002\u00111!1G\u0013C\u0002\u0001$a!!(&\u0005\u0004\u0001'\u0001C*j].LU\u000e\u001d7\u0016\u0011\r%1QDB\n\u00077\u0019BaJ#\u0002bQ)Qk!\u0004\u0004\u0018!91q\u0002\u0015A\u0002\rE\u0011\u0001B:j].\u00042!XB\n\t\u0019\u0019)b\nb\u0001A\n\t1\u000bC\u0004\u0002\u0006\"\u0002\ra!\u0007\u0011\u0007u\u001bY\u0002\u0002\u0004\u0002\f\u001e\u0012\r\u0001\u0019\u0003\u0007\u0003;;#\u0019\u00011\u0003\u0013MKgn[%na2\u0014TCCB\u0012\u0007\u000f\u001aYc!\r\u00048M!\u0011&RA1)\u001d)6qEB\u0017\u0007gAqaa\u0004+\u0001\u0004\u0019I\u0003E\u0002^\u0007W!aa!\u0006*\u0005\u0004\u0001\u0007bBACU\u0001\u00071q\u0006\t\u0004;\u000eEBABAFS\t\u0007\u0001\rC\u0004\u0002R*\u0002\ra!\u000e\u0011\u0007u\u001b9\u0004\u0002\u0006\u0002X&\u0002\u000b\u0011!AC\u0002\u0001D\u0013ba\u000ei\u0007w\u0019yda\u00112\r\rbWn!\u0010oc\u0011!\u0003\u000f\u001e%2\r\r2xo!\u0011yc\u0011!\u0003\u000f\u001e%2\r\rZHp!\u0012~c\u0011!\u0003\u000f\u001e%\u0005\r\u0005u\u0015F1\u0001a\u0005%\u0019\u0016N\\6J[Bd7'\u0006\u0007\u0004N\r%4QKB.\u0007C\u001a9g\u0005\u0003,\u000b\u0006\u0005D#C+\u0004R\r]3QLB2\u0011\u001d\u0019y\u0001\fa\u0001\u0007'\u00022!XB+\t\u0019\u0019)b\u000bb\u0001A\"9\u0011Q\u0011\u0017A\u0002\re\u0003cA/\u0004\\\u00111\u00111R\u0016C\u0002\u0001Dq!!5-\u0001\u0004\u0019y\u0006E\u0002^\u0007C\"a!a6,\u0005\u0004\u0001\u0007b\u0002B\u0017Y\u0001\u00071Q\r\t\u0004;\u000e\u001dDA\u0002B\u001aW\t\u0007\u0001\r\u0002\u0004\u0002\u001e.\u0012\r\u0001\u0019\u0002\r/&$\bnU5oW\"+G\u000e]\u000b\u0007\u0007_\u001aIi! \u0014\u00075\u001a\t\bE\u0002G\u0007gJ1a!\u001eH\u0005\u0019\te.\u001f,bY\u0006\u0019qlX:\u0016\u0005\rm\u0004cA/\u0004~\u001111QC\u0017C\u0002\u0001\fAaX0tAQ!11QBF!\u001d\u0019))LBD\u0007wj\u0011!\u0006\t\u0004;\u000e%EABAO[\t\u0007\u0001\rC\u0004\u0004xA\u0002\raa\u001f\u0016\t\r=5\u0011\u0014\u000b\u0005\u0007#\u001bY\n\u0006\u0003\u0004|\rM\u0005bBA\u0001c\u0001\u000f1Q\u0013\t\n\u0003\u001b93qQB>\u0007/\u00032!XBM\t\u0019\tY)\rb\u0001A\"9\u0011QQ\u0019A\u0002\r]UCBBP\u0007S\u001bi\u000b\u0006\u0004\u0004\"\u000e=6\u0011\u0017\u000b\u0005\u0007w\u001a\u0019\u000bC\u0004\u0002\u0002I\u0002\u001da!*\u0011\u0017\u00055\u0011fa\"\u0004|\r\u001d61\u0016\t\u0004;\u000e%FABAFe\t\u0007\u0001\rE\u0002^\u0007[#a!a63\u0005\u0004\u0001\u0007bBACe\u0001\u00071q\u0015\u0005\b\u0003#\u0014\u0004\u0019ABV+!\u0019)la0\u0004D\u000e\u001dG\u0003CB\\\u0007\u0013\u001cYm!4\u0015\t\rm4\u0011\u0018\u0005\b\u0003\u0003\u0019\u00049AB^!5\tiaKBD\u0007w\u001ail!1\u0004FB\u0019Qla0\u0005\r\u0005-5G1\u0001a!\ri61\u0019\u0003\u0007\u0003/\u001c$\u0019\u00011\u0011\u0007u\u001b9\r\u0002\u0004\u00034M\u0012\r\u0001\u0019\u0005\b\u0003\u000b\u001b\u0004\u0019AB_\u0011\u001d\t\tn\ra\u0001\u0007\u0003DqA!\f4\u0001\u0004\u0019)-\u0001\u0005iCND7i\u001c3f)\t\u0019\u0019\u000eE\u0002G\u0007+L1aa6H\u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\t\ru71\u001d\t\u0004\r\u000e}\u0017bABq\u000f\n9!i\\8mK\u0006t\u0007\u0002CBsk\u0005\u0005\t\u0019\u00013\u0002\u0007a$\u0013'\u0001\u0007XSRD7+\u001b8l\u0011\u0016d\u0007\u000fE\u0002\u0004\u0006^\u001a\"aN#\u0015\u0005\r%\u0018aD1qa2LH%\u001a=uK:\u001c\u0018n\u001c8\u0016\u0011\rMHq\u0001C\u0002\u0007w$Ba!>\u0005\fQ!1q\u001fC\u0005)\u0011\u0019Ip!@\u0011\u0007u\u001bY\u0010\u0002\u0004\u0004\u0016e\u0012\r\u0001\u0019\u0005\b\u0003\u0003I\u00049AB\u0000!%\tia\nC\u0001\u0007s$)\u0001E\u0002^\t\u0007!a!!(:\u0005\u0004\u0001\u0007cA/\u0005\b\u00111\u00111R\u001dC\u0002\u0001Dq!!\":\u0001\u0004!)\u0001C\u0004\u0005\u000ee\u0002\r\u0001b\u0004\u0002\u000b\u0011\"\b.[:\u0011\u000f\r\u0015U\u0006\"\u0001\u0004zVQA1\u0003C\u0014\tW!\u0019\u0003b\u0007\u0015\t\u0011UA\u0011\u0007\u000b\u0007\t/!i\u0003b\f\u0015\t\u0011eAQ\u0004\t\u0004;\u0012mAABB\u000bu\t\u0007\u0001\rC\u0004\u0002\u0002i\u0002\u001d\u0001b\b\u0011\u0017\u00055\u0011\u0006\"\t\u0005\u001a\u0011\u0015B\u0011\u0006\t\u0004;\u0012\rBABAOu\t\u0007\u0001\rE\u0002^\tO!a!a#;\u0005\u0004\u0001\u0007cA/\u0005,\u00111\u0011q\u001b\u001eC\u0002\u0001Dq!!\";\u0001\u0004!)\u0003C\u0004\u0002Rj\u0002\r\u0001\"\u000b\t\u000f\u00115!\b1\u0001\u00054A91QQ\u0017\u0005\"\u0011eQ\u0003\u0004C\u001c\t\u0017\"y\u0005b\u0015\u0005H\u0011}B\u0003\u0002C\u001d\t7\"\u0002\u0002b\u000f\u0005V\u0011]C\u0011\f\u000b\u0005\t{!\t\u0005E\u0002^\t\u007f!aa!\u0006<\u0005\u0004\u0001\u0007bBA\u0001w\u0001\u000fA1\t\t\u000e\u0003\u001bYCQ\tC\u001f\t\u0013\"i\u0005\"\u0015\u0011\u0007u#9\u0005\u0002\u0004\u0002\u001en\u0012\r\u0001\u0019\t\u0004;\u0012-CABAFw\t\u0007\u0001\rE\u0002^\t\u001f\"a!a6<\u0005\u0004\u0001\u0007cA/\u0005T\u00111!1G\u001eC\u0002\u0001Dq!!\"<\u0001\u0004!I\u0005C\u0004\u0002Rn\u0002\r\u0001\"\u0014\t\u000f\t52\b1\u0001\u0005R!9AQB\u001eA\u0002\u0011u\u0003cBBC[\u0011\u0015CQH\u0001\u0013Q\u0006\u001c\bnQ8eK\u0012*\u0007\u0010^3og&|g.\u0006\u0004\u0005d\u0011-Dq\u000e\u000b\u0005\u0007#$)\u0007C\u0004\u0005\u000eq\u0002\r\u0001b\u001a\u0011\u000f\r\u0015U\u0006\"\u001b\u0005nA\u0019Q\fb\u001b\u0005\r\u0005uEH1\u0001a!\riFq\u000e\u0003\u0007\u0007+a$\u0019\u00011\u0002!\u0015\fX/\u00197tI\u0015DH/\u001a8tS>tWC\u0002C;\t\u0003#)\t\u0006\u0003\u0005x\u0011mD\u0003BBo\tsB\u0001b!:>\u0003\u0003\u0005\r\u0001\u001a\u0005\b\t\u001bi\u0004\u0019\u0001C?!\u001d\u0019))\fC@\t\u0007\u00032!\u0018CA\t\u0019\ti*\u0010b\u0001AB\u0019Q\f\"\"\u0005\r\rUQH1\u0001a!\riF\u0011\u0012\u0003\u0007\u0003\u0017S!\u0019\u00011\u0011\u0007u#i\tB\u0003`\u0015\t\u0007\u0001\rE\u0002^\t##!\"a#\u0003A\u0003\u0005\tQ1\u0001aQ%!\t\n\u001bCK\t3#i*\r\u0004$Y6$9J\\\u0019\u0005IA$\b*\r\u0004$m^$Y\n_\u0019\u0005IA$\b*\r\u0004$wr$y*`\u0019\u0005IA$\b\nC\u0004\u0002\u0006\n\u0001\r\u0001b$\u0016\u0011\u0011\u0015F1\u001bCs\tW#b\u0001b*\u0005v\u0012eH\u0003\u0002CU\tw\u00032!\u0018CV\t%y6\u0001)A\u0001\u0002\u000b\u0007\u0001\rK\u0005\u0005,\"$y\u000bb-\u00058F21\u0005\\7\u00052:\fD\u0001\n9u\u0011F21E^<\u00056b\fD\u0001\n9u\u0011F21e\u001f?\u0005:v\fD\u0001\n9u\u0011\"9\u0011\u0011A\u0002A\u0004\u0011u\u0006#CA\u0003\u0017\u0011EG1\u001dCU\u0005\u0015IU\u000e\u001d73+!!\u0019\rb2\u0005L\u0012=\u0007cCA\u00077\u0005\u0015AQ\u0019Ce\t\u001b\u00042!\u0018Cd\t\u0019\tyl\u0003b\u0001AB\u0019Q\fb3\u0005\r\u0005]7B1\u0001a!\riFq\u001a\u0003\u0006?.\u0011\r\u0001\u0019\t\u0004;\u0012MGACA`\u0007\u0001\u0006\t\u0011!b\u0001A\"JA1\u001b5\u0005X\u0012mGq\\\u0019\u0007G1lG\u0011\u001c82\t\u0011\u0002H\u000fS\u0019\u0007GY<HQ\u001c=2\t\u0011\u0002H\u000fS\u0019\u0007GmdH\u0011]?2\t\u0011\u0002H\u000f\u0013\t\u0004;\u0012\u0015HACAl\u0007\u0001\u0006\t\u0011!b\u0001A\"JAQ\u001d5\u0005j\u00125H\u0011_\u0019\u0007G1lG1\u001e82\t\u0011\u0002H\u000fS\u0019\u0007GY<Hq\u001e=2\t\u0011\u0002H\u000fS\u0019\u0007GmdH1_?2\t\u0011\u0002H\u000f\u0013\u0005\b\to\u001c\u0001\u0019\u0001Ci\u0003\t1\u0018\u0007C\u0004\u0002R\u000e\u0001\r\u0001b9\u0016\u0015\u0011uXqFC\u001a\u000b\u000b*\u0019\u0001\u0006\u0005\u0005\u0000\u0016USqKC-)\u0011)\t!b\u0005\u0011\u0007u+\u0019\u0001B\u0005`\t\u0001\u0006\t\u0011!b\u0001A\"JQ1\u00015\u0006\b\u0015-QqB\u0019\u0007G1lW\u0011\u000282\t\u0011\u0002H\u000fS\u0019\u0007GY<XQ\u0002=2\t\u0011\u0002H\u000fS\u0019\u0007GmdX\u0011C?2\t\u0011\u0002H\u000f\u0013\u0005\b\u0003\u0003!\u00019AC\u000b!-\t)\u0001DC\u0017\u000bc)\u0019%\"\u0001\u0003\u000b%k\u0007\u000f\\\u001a\u0016\u0015\u0015mQqDC\u0012\u000bO)Y\u0003E\u0007\u0002\u000eu\t)!\"\b\u0006\"\u0015\u0015R\u0011\u0006\t\u0004;\u0016}AABA`\u0019\t\u0007\u0001\rE\u0002^\u000bG!a!a6\r\u0005\u0004\u0001\u0007cA/\u0006(\u00111!1\u0007\u0007C\u0002\u0001\u00042!XC\u0016\t\u0015yFB1\u0001a!\riVq\u0006\u0003\u0007\u0003\u007f#!\u0019\u00011\u0011\u0007u+\u0019\u0004\u0002\u0006\u0002X\u0012\u0001\u000b\u0011!AC\u0002\u0001D\u0013\"b\ri\u000bo)Y$b\u00102\r\rbW.\"\u000foc\u0011!\u0003\u000f\u001e%2\r\r2x/\"\u0010yc\u0011!\u0003\u000f\u001e%2\r\rZH0\"\u0011~c\u0011!\u0003\u000f\u001e%\u0011\u0007u+)\u0005\u0002\u0006\u00034\u0011\u0001\u000b\u0011!AC\u0002\u0001D\u0013\"\"\u0012i\u000b\u0013*i%\"\u00152\r\rbW.b\u0013oc\u0011!\u0003\u000f\u001e%2\r\r2x/b\u0014yc\u0011!\u0003\u000f\u001e%2\r\rZH0b\u0015~c\u0011!\u0003\u000f\u001e%\t\u000f\u0011]H\u00011\u0001\u0006.!9\u0011\u0011\u001b\u0003A\u0002\u0015E\u0002b\u0002B\u0017\t\u0001\u0007Q1I\u000b\r\u000b;*))\"#\u0006\u000e\u0016EU1\r\u000b\u000b\u000b?*\u0019*\"&\u0006\u0018\u0016eE\u0003BC1\u000bK\u00022!XC2\t\u0015yVA1\u0001a\u0011\u001d\t\t!\u0002a\u0002\u000bO\u0002R\"!\u0002\u000e\u000b\u0007+9)b#\u0006\u0010\u0016\u0005$!B%na2$T\u0003DC7\u000bc*)(\"\u001f\u0006~\u0015\u0005\u0005cDA\u0007?\u0005\u0015QqNC:\u000bo*Y(b \u0011\u0007u+\t\b\u0002\u0004\u0002@6\u0011\r\u0001\u0019\t\u0004;\u0016UDABAl\u001b\t\u0007\u0001\rE\u0002^\u000bs\"aAa\r\u000e\u0005\u0004\u0001\u0007cA/\u0006~\u00111!1U\u0007C\u0002\u0001\u00042!XCA\t\u0015yVB1\u0001a!\riVQ\u0011\u0003\u0007\u0003\u007f+!\u0019\u00011\u0011\u0007u+I\t\u0002\u0004\u0002X\u0016\u0011\r\u0001\u0019\t\u0004;\u00165EA\u0002B\u001a\u000b\t\u0007\u0001\rE\u0002^\u000b##aAa)\u0006\u0005\u0004\u0001\u0007b\u0002C|\u000b\u0001\u0007Q1\u0011\u0005\b\u0003#,\u0001\u0019ACD\u0011\u001d\u0011i#\u0002a\u0001\u000b\u0017CqA!(\u0006\u0001\u0004)y)A\u0004j]Bc\u0017mY3\u0016\t\u0015}UQ\u0015\u000b\u0005\u000bC+Y\u000b\u0006\u0003\u0006$\u0016\u001d\u0006cA/\u0006&\u00121\u00111\u0012\u0004C\u0002\u0001Dq!!\u0001\u0007\u0001\b)I\u000bE\u0004\u0002\u000e\u0005\n)!b)\t\u000f\u0005\u0015e\u00011\u0001\u0006$V1QqVC[\u000b{#b!\"-\u0006@\u0016\u0005G\u0003BCZ\u000bo\u00032!XC[\t\u0019\tYi\u0002b\u0001A\"9\u0011\u0011A\u0004A\u0004\u0015e\u0006#CA\u0007G\u0005\u0015Q1WC^!\riVQ\u0018\u0003\u0007\u0003/<!\u0019\u00011\t\u000f\u0005\u0015u\u00011\u0001\u00064\"9\u0011\u0011[\u0004A\u0002\u0015mV\u0003CCc\u000b\u0017,\u0019.b6\u0015\u0011\u0015\u001dW\u0011\\Cn\u000b;$B!\"3\u0006NB\u0019Q,b3\u0005\r\u0005-\u0005B1\u0001a\u0011\u001d\t\t\u0001\u0003a\u0002\u000b\u001f\u00042\"!\u0004&\u0003\u000b)I-\"5\u0006VB\u0019Q,b5\u0005\r\u0005]\u0007B1\u0001a!\riVq\u001b\u0003\u0007\u0005gA!\u0019\u00011\t\u000f\u0005\u0015\u0005\u00021\u0001\u0006J\"9\u0011\u0011\u001b\u0005A\u0002\u0015E\u0007b\u0002B\u0017\u0011\u0001\u0007QQ[\u0001\to&$\bnU5oWV!Q1]Cu)\u0011))/b;\u0011\u000f\u00055Q&!\u0002\u0006hB\u0019Q,\";\u0005\r\rU\u0011B1\u0001a\u0011\u001d)i/\u0003a\u0001\u000bO\f\u0011a]\u000b\u0005\u000bc,)\u0010E\u0004\u0002\u000e\u0005\n)!b=\u0011\u0007u+)\u0010\u0002\u0004\u0002\f:\u0011\r\u0001Y\u000b\u0007\u000bs,iP\"\u0001\u0011\u0013\u000551%!\u0002\u0006|\u0016}\bcA/\u0006~\u00121\u0011qX\bC\u0002\u0001\u00042!\u0018D\u0001\t\u0019\t9n\u0004b\u0001AVAaQ\u0001D\u0005\r\u001b1\t\u0002E\u0006\u0002\u000e\u0015\n)Ab\u0002\u0007\f\u0019=\u0001cA/\u0007\n\u00111\u0011q\u0018\tC\u0002\u0001\u00042!\u0018D\u0007\t\u0019\t9\u000e\u0005b\u0001AB\u0019QL\"\u0005\u0005\r\tM\u0002C1\u0001a+\u00191)B\"\u0007\u0007\u001eAI\u0011QB\u0014\u0002\u0006\u0019]a1\u0004\t\u0004;\u001aeAABB\u000b#\t\u0007\u0001\rE\u0002^\r;!a!a#\u0012\u0005\u0004\u0001W\u0003\u0003D\u0011\rK1IC\"\f\u0011\u0017\u00055\u0011&!\u0002\u0007$\u0019\u001db1\u0006\t\u0004;\u001a\u0015BABB\u000b%\t\u0007\u0001\rE\u0002^\rS!a!a0\u0013\u0005\u0004\u0001\u0007cA/\u0007.\u00111\u0011q\u001b\nC\u0002\u0001,\"B\"\r\u00076\u0019ebQ\bD!!5\tiaKA\u0003\rg19Db\u000f\u0007@A\u0019QL\"\u000e\u0005\r\rU1C1\u0001a!\rif\u0011\b\u0003\u0007\u0003\u007f\u001b\"\u0019\u00011\u0011\u0007u3i\u0004\u0002\u0004\u0002XN\u0011\r\u0001\u0019\t\u0004;\u001a\u0005CA\u0002B\u001a'\t\u0007\u0001\r"
)
public interface UFunc extends HasOps {
   // $FF: synthetic method
   static Object apply$(final UFunc $this, final Object v, final UImpl impl) {
      return $this.apply(v, impl);
   }

   default Object apply(final Object v, final UImpl impl) {
      return impl.apply(v);
   }

   // $FF: synthetic method
   static Object apply$(final UFunc $this, final Object v1, final Object v2, final UImpl2 impl) {
      return $this.apply(v1, v2, impl);
   }

   default Object apply(final Object v1, final Object v2, final UImpl2 impl) {
      return impl.apply(v1, v2);
   }

   // $FF: synthetic method
   static Object apply$(final UFunc $this, final Object v1, final Object v2, final Object v3, final UImpl3 impl) {
      return $this.apply(v1, v2, v3, impl);
   }

   default Object apply(final Object v1, final Object v2, final Object v3, final UImpl3 impl) {
      return impl.apply(v1, v2, v3);
   }

   // $FF: synthetic method
   static Object apply$(final UFunc $this, final Object v1, final Object v2, final Object v3, final Object v4, final UImpl4 impl) {
      return $this.apply(v1, v2, v3, v4, impl);
   }

   default Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UImpl4 impl) {
      return impl.apply(v1, v2, v3, v4);
   }

   // $FF: synthetic method
   static Object inPlace$(final UFunc $this, final Object v, final InPlaceImpl impl) {
      return $this.inPlace(v, impl);
   }

   default Object inPlace(final Object v, final InPlaceImpl impl) {
      impl.apply(v);
      return v;
   }

   // $FF: synthetic method
   static Object inPlace$(final UFunc $this, final Object v, final Object v2, final InPlaceImpl2 impl) {
      return $this.inPlace(v, v2, impl);
   }

   default Object inPlace(final Object v, final Object v2, final InPlaceImpl2 impl) {
      impl.apply(v, v2);
      return v;
   }

   // $FF: synthetic method
   static Object inPlace$(final UFunc $this, final Object v, final Object v2, final Object v3, final InPlaceImpl3 impl) {
      return $this.inPlace(v, v2, v3, impl);
   }

   default Object inPlace(final Object v, final Object v2, final Object v3, final InPlaceImpl3 impl) {
      impl.apply(v, v2, v3);
      return v;
   }

   // $FF: synthetic method
   static Object withSink$(final UFunc $this, final Object s) {
      return $this.withSink(s);
   }

   default Object withSink(final Object s) {
      return s;
   }

   // $FF: synthetic method
   static double apply$mDDc$sp$(final UFunc $this, final double v, final UImpl impl) {
      return $this.apply$mDDc$sp(v, impl);
   }

   default double apply$mDDc$sp(final double v, final UImpl impl) {
      return impl.apply$mcDD$sp(v);
   }

   // $FF: synthetic method
   static float apply$mDFc$sp$(final UFunc $this, final double v, final UImpl impl) {
      return $this.apply$mDFc$sp(v, impl);
   }

   default float apply$mDFc$sp(final double v, final UImpl impl) {
      return impl.apply$mcDF$sp(v);
   }

   // $FF: synthetic method
   static int apply$mDIc$sp$(final UFunc $this, final double v, final UImpl impl) {
      return $this.apply$mDIc$sp(v, impl);
   }

   default int apply$mDIc$sp(final double v, final UImpl impl) {
      return impl.apply$mcDI$sp(v);
   }

   // $FF: synthetic method
   static double apply$mFDc$sp$(final UFunc $this, final float v, final UImpl impl) {
      return $this.apply$mFDc$sp(v, impl);
   }

   default double apply$mFDc$sp(final float v, final UImpl impl) {
      return impl.apply$mcFD$sp(v);
   }

   // $FF: synthetic method
   static float apply$mFFc$sp$(final UFunc $this, final float v, final UImpl impl) {
      return $this.apply$mFFc$sp(v, impl);
   }

   default float apply$mFFc$sp(final float v, final UImpl impl) {
      return impl.apply$mcFF$sp(v);
   }

   // $FF: synthetic method
   static int apply$mFIc$sp$(final UFunc $this, final float v, final UImpl impl) {
      return $this.apply$mFIc$sp(v, impl);
   }

   default int apply$mFIc$sp(final float v, final UImpl impl) {
      return impl.apply$mcFI$sp(v);
   }

   // $FF: synthetic method
   static double apply$mIDc$sp$(final UFunc $this, final int v, final UImpl impl) {
      return $this.apply$mIDc$sp(v, impl);
   }

   default double apply$mIDc$sp(final int v, final UImpl impl) {
      return impl.apply$mcID$sp(v);
   }

   // $FF: synthetic method
   static float apply$mIFc$sp$(final UFunc $this, final int v, final UImpl impl) {
      return $this.apply$mIFc$sp(v, impl);
   }

   default float apply$mIFc$sp(final int v, final UImpl impl) {
      return impl.apply$mcIF$sp(v);
   }

   // $FF: synthetic method
   static int apply$mIIc$sp$(final UFunc $this, final int v, final UImpl impl) {
      return $this.apply$mIIc$sp(v, impl);
   }

   default int apply$mIIc$sp(final int v, final UImpl impl) {
      return impl.apply$mcII$sp(v);
   }

   // $FF: synthetic method
   static double apply$mDDDc$sp$(final UFunc $this, final double v1, final double v2, final UImpl2 impl) {
      return $this.apply$mDDDc$sp(v1, v2, impl);
   }

   default double apply$mDDDc$sp(final double v1, final double v2, final UImpl2 impl) {
      return impl.apply$mcDDD$sp(v1, v2);
   }

   // $FF: synthetic method
   static float apply$mDDFc$sp$(final UFunc $this, final double v1, final double v2, final UImpl2 impl) {
      return $this.apply$mDDFc$sp(v1, v2, impl);
   }

   default float apply$mDDFc$sp(final double v1, final double v2, final UImpl2 impl) {
      return impl.apply$mcDDF$sp(v1, v2);
   }

   // $FF: synthetic method
   static int apply$mDDIc$sp$(final UFunc $this, final double v1, final double v2, final UImpl2 impl) {
      return $this.apply$mDDIc$sp(v1, v2, impl);
   }

   default int apply$mDDIc$sp(final double v1, final double v2, final UImpl2 impl) {
      return impl.apply$mcDDI$sp(v1, v2);
   }

   // $FF: synthetic method
   static double apply$mDFDc$sp$(final UFunc $this, final double v1, final float v2, final UImpl2 impl) {
      return $this.apply$mDFDc$sp(v1, v2, impl);
   }

   default double apply$mDFDc$sp(final double v1, final float v2, final UImpl2 impl) {
      return impl.apply$mcDFD$sp(v1, v2);
   }

   // $FF: synthetic method
   static float apply$mDFFc$sp$(final UFunc $this, final double v1, final float v2, final UImpl2 impl) {
      return $this.apply$mDFFc$sp(v1, v2, impl);
   }

   default float apply$mDFFc$sp(final double v1, final float v2, final UImpl2 impl) {
      return impl.apply$mcDFF$sp(v1, v2);
   }

   // $FF: synthetic method
   static int apply$mDFIc$sp$(final UFunc $this, final double v1, final float v2, final UImpl2 impl) {
      return $this.apply$mDFIc$sp(v1, v2, impl);
   }

   default int apply$mDFIc$sp(final double v1, final float v2, final UImpl2 impl) {
      return impl.apply$mcDFI$sp(v1, v2);
   }

   // $FF: synthetic method
   static double apply$mDIDc$sp$(final UFunc $this, final double v1, final int v2, final UImpl2 impl) {
      return $this.apply$mDIDc$sp(v1, v2, impl);
   }

   default double apply$mDIDc$sp(final double v1, final int v2, final UImpl2 impl) {
      return impl.apply$mcDID$sp(v1, v2);
   }

   // $FF: synthetic method
   static float apply$mDIFc$sp$(final UFunc $this, final double v1, final int v2, final UImpl2 impl) {
      return $this.apply$mDIFc$sp(v1, v2, impl);
   }

   default float apply$mDIFc$sp(final double v1, final int v2, final UImpl2 impl) {
      return impl.apply$mcDIF$sp(v1, v2);
   }

   // $FF: synthetic method
   static int apply$mDIIc$sp$(final UFunc $this, final double v1, final int v2, final UImpl2 impl) {
      return $this.apply$mDIIc$sp(v1, v2, impl);
   }

   default int apply$mDIIc$sp(final double v1, final int v2, final UImpl2 impl) {
      return impl.apply$mcDII$sp(v1, v2);
   }

   // $FF: synthetic method
   static double apply$mFDDc$sp$(final UFunc $this, final float v1, final double v2, final UImpl2 impl) {
      return $this.apply$mFDDc$sp(v1, v2, impl);
   }

   default double apply$mFDDc$sp(final float v1, final double v2, final UImpl2 impl) {
      return impl.apply$mcFDD$sp(v1, v2);
   }

   // $FF: synthetic method
   static float apply$mFDFc$sp$(final UFunc $this, final float v1, final double v2, final UImpl2 impl) {
      return $this.apply$mFDFc$sp(v1, v2, impl);
   }

   default float apply$mFDFc$sp(final float v1, final double v2, final UImpl2 impl) {
      return impl.apply$mcFDF$sp(v1, v2);
   }

   // $FF: synthetic method
   static int apply$mFDIc$sp$(final UFunc $this, final float v1, final double v2, final UImpl2 impl) {
      return $this.apply$mFDIc$sp(v1, v2, impl);
   }

   default int apply$mFDIc$sp(final float v1, final double v2, final UImpl2 impl) {
      return impl.apply$mcFDI$sp(v1, v2);
   }

   // $FF: synthetic method
   static double apply$mFFDc$sp$(final UFunc $this, final float v1, final float v2, final UImpl2 impl) {
      return $this.apply$mFFDc$sp(v1, v2, impl);
   }

   default double apply$mFFDc$sp(final float v1, final float v2, final UImpl2 impl) {
      return impl.apply$mcFFD$sp(v1, v2);
   }

   // $FF: synthetic method
   static float apply$mFFFc$sp$(final UFunc $this, final float v1, final float v2, final UImpl2 impl) {
      return $this.apply$mFFFc$sp(v1, v2, impl);
   }

   default float apply$mFFFc$sp(final float v1, final float v2, final UImpl2 impl) {
      return impl.apply$mcFFF$sp(v1, v2);
   }

   // $FF: synthetic method
   static int apply$mFFIc$sp$(final UFunc $this, final float v1, final float v2, final UImpl2 impl) {
      return $this.apply$mFFIc$sp(v1, v2, impl);
   }

   default int apply$mFFIc$sp(final float v1, final float v2, final UImpl2 impl) {
      return impl.apply$mcFFI$sp(v1, v2);
   }

   // $FF: synthetic method
   static double apply$mFIDc$sp$(final UFunc $this, final float v1, final int v2, final UImpl2 impl) {
      return $this.apply$mFIDc$sp(v1, v2, impl);
   }

   default double apply$mFIDc$sp(final float v1, final int v2, final UImpl2 impl) {
      return impl.apply$mcFID$sp(v1, v2);
   }

   // $FF: synthetic method
   static float apply$mFIFc$sp$(final UFunc $this, final float v1, final int v2, final UImpl2 impl) {
      return $this.apply$mFIFc$sp(v1, v2, impl);
   }

   default float apply$mFIFc$sp(final float v1, final int v2, final UImpl2 impl) {
      return impl.apply$mcFIF$sp(v1, v2);
   }

   // $FF: synthetic method
   static int apply$mFIIc$sp$(final UFunc $this, final float v1, final int v2, final UImpl2 impl) {
      return $this.apply$mFIIc$sp(v1, v2, impl);
   }

   default int apply$mFIIc$sp(final float v1, final int v2, final UImpl2 impl) {
      return impl.apply$mcFII$sp(v1, v2);
   }

   // $FF: synthetic method
   static double apply$mIDDc$sp$(final UFunc $this, final int v1, final double v2, final UImpl2 impl) {
      return $this.apply$mIDDc$sp(v1, v2, impl);
   }

   default double apply$mIDDc$sp(final int v1, final double v2, final UImpl2 impl) {
      return impl.apply$mcIDD$sp(v1, v2);
   }

   // $FF: synthetic method
   static float apply$mIDFc$sp$(final UFunc $this, final int v1, final double v2, final UImpl2 impl) {
      return $this.apply$mIDFc$sp(v1, v2, impl);
   }

   default float apply$mIDFc$sp(final int v1, final double v2, final UImpl2 impl) {
      return impl.apply$mcIDF$sp(v1, v2);
   }

   // $FF: synthetic method
   static int apply$mIDIc$sp$(final UFunc $this, final int v1, final double v2, final UImpl2 impl) {
      return $this.apply$mIDIc$sp(v1, v2, impl);
   }

   default int apply$mIDIc$sp(final int v1, final double v2, final UImpl2 impl) {
      return impl.apply$mcIDI$sp(v1, v2);
   }

   // $FF: synthetic method
   static double apply$mIFDc$sp$(final UFunc $this, final int v1, final float v2, final UImpl2 impl) {
      return $this.apply$mIFDc$sp(v1, v2, impl);
   }

   default double apply$mIFDc$sp(final int v1, final float v2, final UImpl2 impl) {
      return impl.apply$mcIFD$sp(v1, v2);
   }

   // $FF: synthetic method
   static float apply$mIFFc$sp$(final UFunc $this, final int v1, final float v2, final UImpl2 impl) {
      return $this.apply$mIFFc$sp(v1, v2, impl);
   }

   default float apply$mIFFc$sp(final int v1, final float v2, final UImpl2 impl) {
      return impl.apply$mcIFF$sp(v1, v2);
   }

   // $FF: synthetic method
   static int apply$mIFIc$sp$(final UFunc $this, final int v1, final float v2, final UImpl2 impl) {
      return $this.apply$mIFIc$sp(v1, v2, impl);
   }

   default int apply$mIFIc$sp(final int v1, final float v2, final UImpl2 impl) {
      return impl.apply$mcIFI$sp(v1, v2);
   }

   // $FF: synthetic method
   static double apply$mIIDc$sp$(final UFunc $this, final int v1, final int v2, final UImpl2 impl) {
      return $this.apply$mIIDc$sp(v1, v2, impl);
   }

   default double apply$mIIDc$sp(final int v1, final int v2, final UImpl2 impl) {
      return impl.apply$mcIID$sp(v1, v2);
   }

   // $FF: synthetic method
   static float apply$mIIFc$sp$(final UFunc $this, final int v1, final int v2, final UImpl2 impl) {
      return $this.apply$mIIFc$sp(v1, v2, impl);
   }

   default float apply$mIIFc$sp(final int v1, final int v2, final UImpl2 impl) {
      return impl.apply$mcIIF$sp(v1, v2);
   }

   // $FF: synthetic method
   static int apply$mIIIc$sp$(final UFunc $this, final int v1, final int v2, final UImpl2 impl) {
      return $this.apply$mIIIc$sp(v1, v2, impl);
   }

   default int apply$mIIIc$sp(final int v1, final int v2, final UImpl2 impl) {
      return impl.apply$mcIII$sp(v1, v2);
   }

   // $FF: synthetic method
   static double apply$mDDDc$sp$(final UFunc $this, final Object v1, final double v2, final double v3, final UImpl3 impl) {
      return $this.apply$mDDDc$sp(v1, v2, v3, impl);
   }

   default double apply$mDDDc$sp(final Object v1, final double v2, final double v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(v1, BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
   }

   // $FF: synthetic method
   static float apply$mDDFc$sp$(final UFunc $this, final Object v1, final double v2, final double v3, final UImpl3 impl) {
      return $this.apply$mDDFc$sp(v1, v2, v3, impl);
   }

   default float apply$mDDFc$sp(final Object v1, final double v2, final double v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(v1, BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
   }

   // $FF: synthetic method
   static int apply$mDDIc$sp$(final UFunc $this, final Object v1, final double v2, final double v3, final UImpl3 impl) {
      return $this.apply$mDDIc$sp(v1, v2, v3, impl);
   }

   default int apply$mDDIc$sp(final Object v1, final double v2, final double v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(v1, BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
   }

   // $FF: synthetic method
   static double apply$mDFDc$sp$(final UFunc $this, final Object v1, final double v2, final float v3, final UImpl3 impl) {
      return $this.apply$mDFDc$sp(v1, v2, v3, impl);
   }

   default double apply$mDFDc$sp(final Object v1, final double v2, final float v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(v1, BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
   }

   // $FF: synthetic method
   static float apply$mDFFc$sp$(final UFunc $this, final Object v1, final double v2, final float v3, final UImpl3 impl) {
      return $this.apply$mDFFc$sp(v1, v2, v3, impl);
   }

   default float apply$mDFFc$sp(final Object v1, final double v2, final float v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(v1, BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
   }

   // $FF: synthetic method
   static int apply$mDFIc$sp$(final UFunc $this, final Object v1, final double v2, final float v3, final UImpl3 impl) {
      return $this.apply$mDFIc$sp(v1, v2, v3, impl);
   }

   default int apply$mDFIc$sp(final Object v1, final double v2, final float v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(v1, BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
   }

   // $FF: synthetic method
   static double apply$mDIDc$sp$(final UFunc $this, final Object v1, final double v2, final int v3, final UImpl3 impl) {
      return $this.apply$mDIDc$sp(v1, v2, v3, impl);
   }

   default double apply$mDIDc$sp(final Object v1, final double v2, final int v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(v1, BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
   }

   // $FF: synthetic method
   static float apply$mDIFc$sp$(final UFunc $this, final Object v1, final double v2, final int v3, final UImpl3 impl) {
      return $this.apply$mDIFc$sp(v1, v2, v3, impl);
   }

   default float apply$mDIFc$sp(final Object v1, final double v2, final int v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(v1, BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
   }

   // $FF: synthetic method
   static int apply$mDIIc$sp$(final UFunc $this, final Object v1, final double v2, final int v3, final UImpl3 impl) {
      return $this.apply$mDIIc$sp(v1, v2, v3, impl);
   }

   default int apply$mDIIc$sp(final Object v1, final double v2, final int v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(v1, BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
   }

   // $FF: synthetic method
   static double apply$mFDDc$sp$(final UFunc $this, final Object v1, final float v2, final double v3, final UImpl3 impl) {
      return $this.apply$mFDDc$sp(v1, v2, v3, impl);
   }

   default double apply$mFDDc$sp(final Object v1, final float v2, final double v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(v1, BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
   }

   // $FF: synthetic method
   static float apply$mFDFc$sp$(final UFunc $this, final Object v1, final float v2, final double v3, final UImpl3 impl) {
      return $this.apply$mFDFc$sp(v1, v2, v3, impl);
   }

   default float apply$mFDFc$sp(final Object v1, final float v2, final double v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(v1, BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
   }

   // $FF: synthetic method
   static int apply$mFDIc$sp$(final UFunc $this, final Object v1, final float v2, final double v3, final UImpl3 impl) {
      return $this.apply$mFDIc$sp(v1, v2, v3, impl);
   }

   default int apply$mFDIc$sp(final Object v1, final float v2, final double v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(v1, BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
   }

   // $FF: synthetic method
   static double apply$mFFDc$sp$(final UFunc $this, final Object v1, final float v2, final float v3, final UImpl3 impl) {
      return $this.apply$mFFDc$sp(v1, v2, v3, impl);
   }

   default double apply$mFFDc$sp(final Object v1, final float v2, final float v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(v1, BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
   }

   // $FF: synthetic method
   static float apply$mFFFc$sp$(final UFunc $this, final Object v1, final float v2, final float v3, final UImpl3 impl) {
      return $this.apply$mFFFc$sp(v1, v2, v3, impl);
   }

   default float apply$mFFFc$sp(final Object v1, final float v2, final float v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(v1, BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
   }

   // $FF: synthetic method
   static int apply$mFFIc$sp$(final UFunc $this, final Object v1, final float v2, final float v3, final UImpl3 impl) {
      return $this.apply$mFFIc$sp(v1, v2, v3, impl);
   }

   default int apply$mFFIc$sp(final Object v1, final float v2, final float v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(v1, BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
   }

   // $FF: synthetic method
   static double apply$mFIDc$sp$(final UFunc $this, final Object v1, final float v2, final int v3, final UImpl3 impl) {
      return $this.apply$mFIDc$sp(v1, v2, v3, impl);
   }

   default double apply$mFIDc$sp(final Object v1, final float v2, final int v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(v1, BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
   }

   // $FF: synthetic method
   static float apply$mFIFc$sp$(final UFunc $this, final Object v1, final float v2, final int v3, final UImpl3 impl) {
      return $this.apply$mFIFc$sp(v1, v2, v3, impl);
   }

   default float apply$mFIFc$sp(final Object v1, final float v2, final int v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(v1, BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
   }

   // $FF: synthetic method
   static int apply$mFIIc$sp$(final UFunc $this, final Object v1, final float v2, final int v3, final UImpl3 impl) {
      return $this.apply$mFIIc$sp(v1, v2, v3, impl);
   }

   default int apply$mFIIc$sp(final Object v1, final float v2, final int v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(v1, BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
   }

   // $FF: synthetic method
   static double apply$mIDDc$sp$(final UFunc $this, final Object v1, final int v2, final double v3, final UImpl3 impl) {
      return $this.apply$mIDDc$sp(v1, v2, v3, impl);
   }

   default double apply$mIDDc$sp(final Object v1, final int v2, final double v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(v1, BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
   }

   // $FF: synthetic method
   static float apply$mIDFc$sp$(final UFunc $this, final Object v1, final int v2, final double v3, final UImpl3 impl) {
      return $this.apply$mIDFc$sp(v1, v2, v3, impl);
   }

   default float apply$mIDFc$sp(final Object v1, final int v2, final double v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(v1, BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
   }

   // $FF: synthetic method
   static int apply$mIDIc$sp$(final UFunc $this, final Object v1, final int v2, final double v3, final UImpl3 impl) {
      return $this.apply$mIDIc$sp(v1, v2, v3, impl);
   }

   default int apply$mIDIc$sp(final Object v1, final int v2, final double v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(v1, BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
   }

   // $FF: synthetic method
   static double apply$mIFDc$sp$(final UFunc $this, final Object v1, final int v2, final float v3, final UImpl3 impl) {
      return $this.apply$mIFDc$sp(v1, v2, v3, impl);
   }

   default double apply$mIFDc$sp(final Object v1, final int v2, final float v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(v1, BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
   }

   // $FF: synthetic method
   static float apply$mIFFc$sp$(final UFunc $this, final Object v1, final int v2, final float v3, final UImpl3 impl) {
      return $this.apply$mIFFc$sp(v1, v2, v3, impl);
   }

   default float apply$mIFFc$sp(final Object v1, final int v2, final float v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(v1, BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
   }

   // $FF: synthetic method
   static int apply$mIFIc$sp$(final UFunc $this, final Object v1, final int v2, final float v3, final UImpl3 impl) {
      return $this.apply$mIFIc$sp(v1, v2, v3, impl);
   }

   default int apply$mIFIc$sp(final Object v1, final int v2, final float v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(v1, BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
   }

   // $FF: synthetic method
   static double apply$mIIDc$sp$(final UFunc $this, final Object v1, final int v2, final int v3, final UImpl3 impl) {
      return $this.apply$mIIDc$sp(v1, v2, v3, impl);
   }

   default double apply$mIIDc$sp(final Object v1, final int v2, final int v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToDouble(impl.apply(v1, BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
   }

   // $FF: synthetic method
   static float apply$mIIFc$sp$(final UFunc $this, final Object v1, final int v2, final int v3, final UImpl3 impl) {
      return $this.apply$mIIFc$sp(v1, v2, v3, impl);
   }

   default float apply$mIIFc$sp(final Object v1, final int v2, final int v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToFloat(impl.apply(v1, BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
   }

   // $FF: synthetic method
   static int apply$mIIIc$sp$(final UFunc $this, final Object v1, final int v2, final int v3, final UImpl3 impl) {
      return $this.apply$mIIIc$sp(v1, v2, v3, impl);
   }

   default int apply$mIIIc$sp(final Object v1, final int v2, final int v3, final UImpl3 impl) {
      return BoxesRunTime.unboxToInt(impl.apply(v1, BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
   }

   static void $init$(final UFunc $this) {
   }

   public interface UImpl extends Serializable {
      Object apply(final Object v);

      // $FF: synthetic method
      static double apply$mcDD$sp$(final UImpl $this, final double v) {
         return $this.apply$mcDD$sp(v);
      }

      default double apply$mcDD$sp(final double v) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v)));
      }

      // $FF: synthetic method
      static float apply$mcDF$sp$(final UImpl $this, final double v) {
         return $this.apply$mcDF$sp(v);
      }

      default float apply$mcDF$sp(final double v) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v)));
      }

      // $FF: synthetic method
      static int apply$mcDI$sp$(final UImpl $this, final double v) {
         return $this.apply$mcDI$sp(v);
      }

      default int apply$mcDI$sp(final double v) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v)));
      }

      // $FF: synthetic method
      static double apply$mcFD$sp$(final UImpl $this, final float v) {
         return $this.apply$mcFD$sp(v);
      }

      default double apply$mcFD$sp(final float v) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v)));
      }

      // $FF: synthetic method
      static float apply$mcFF$sp$(final UImpl $this, final float v) {
         return $this.apply$mcFF$sp(v);
      }

      default float apply$mcFF$sp(final float v) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v)));
      }

      // $FF: synthetic method
      static int apply$mcFI$sp$(final UImpl $this, final float v) {
         return $this.apply$mcFI$sp(v);
      }

      default int apply$mcFI$sp(final float v) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v)));
      }

      // $FF: synthetic method
      static double apply$mcID$sp$(final UImpl $this, final int v) {
         return $this.apply$mcID$sp(v);
      }

      default double apply$mcID$sp(final int v) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v)));
      }

      // $FF: synthetic method
      static float apply$mcIF$sp$(final UImpl $this, final int v) {
         return $this.apply$mcIF$sp(v);
      }

      default float apply$mcIF$sp(final int v) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v)));
      }

      // $FF: synthetic method
      static int apply$mcII$sp$(final UImpl $this, final int v) {
         return $this.apply$mcII$sp(v);
      }

      default int apply$mcII$sp(final int v) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v)));
      }
   }

   public interface UImpl2 extends Serializable {
      Object apply(final Object v, final Object v2);

      // $FF: synthetic method
      static double apply$mcDDD$sp$(final UImpl2 $this, final double v, final double v2) {
         return $this.apply$mcDDD$sp(v, v2);
      }

      default double apply$mcDDD$sp(final double v, final double v2) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2)));
      }

      // $FF: synthetic method
      static float apply$mcDDF$sp$(final UImpl2 $this, final double v, final double v2) {
         return $this.apply$mcDDF$sp(v, v2);
      }

      default float apply$mcDDF$sp(final double v, final double v2) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2)));
      }

      // $FF: synthetic method
      static int apply$mcDDI$sp$(final UImpl2 $this, final double v, final double v2) {
         return $this.apply$mcDDI$sp(v, v2);
      }

      default int apply$mcDDI$sp(final double v, final double v2) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2)));
      }

      // $FF: synthetic method
      static double apply$mcDFD$sp$(final UImpl2 $this, final double v, final float v2) {
         return $this.apply$mcDFD$sp(v, v2);
      }

      default double apply$mcDFD$sp(final double v, final float v2) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2)));
      }

      // $FF: synthetic method
      static float apply$mcDFF$sp$(final UImpl2 $this, final double v, final float v2) {
         return $this.apply$mcDFF$sp(v, v2);
      }

      default float apply$mcDFF$sp(final double v, final float v2) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2)));
      }

      // $FF: synthetic method
      static int apply$mcDFI$sp$(final UImpl2 $this, final double v, final float v2) {
         return $this.apply$mcDFI$sp(v, v2);
      }

      default int apply$mcDFI$sp(final double v, final float v2) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2)));
      }

      // $FF: synthetic method
      static double apply$mcDID$sp$(final UImpl2 $this, final double v, final int v2) {
         return $this.apply$mcDID$sp(v, v2);
      }

      default double apply$mcDID$sp(final double v, final int v2) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2)));
      }

      // $FF: synthetic method
      static float apply$mcDIF$sp$(final UImpl2 $this, final double v, final int v2) {
         return $this.apply$mcDIF$sp(v, v2);
      }

      default float apply$mcDIF$sp(final double v, final int v2) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2)));
      }

      // $FF: synthetic method
      static int apply$mcDII$sp$(final UImpl2 $this, final double v, final int v2) {
         return $this.apply$mcDII$sp(v, v2);
      }

      default int apply$mcDII$sp(final double v, final int v2) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2)));
      }

      // $FF: synthetic method
      static double apply$mcFDD$sp$(final UImpl2 $this, final float v, final double v2) {
         return $this.apply$mcFDD$sp(v, v2);
      }

      default double apply$mcFDD$sp(final float v, final double v2) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2)));
      }

      // $FF: synthetic method
      static float apply$mcFDF$sp$(final UImpl2 $this, final float v, final double v2) {
         return $this.apply$mcFDF$sp(v, v2);
      }

      default float apply$mcFDF$sp(final float v, final double v2) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2)));
      }

      // $FF: synthetic method
      static int apply$mcFDI$sp$(final UImpl2 $this, final float v, final double v2) {
         return $this.apply$mcFDI$sp(v, v2);
      }

      default int apply$mcFDI$sp(final float v, final double v2) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2)));
      }

      // $FF: synthetic method
      static double apply$mcFFD$sp$(final UImpl2 $this, final float v, final float v2) {
         return $this.apply$mcFFD$sp(v, v2);
      }

      default double apply$mcFFD$sp(final float v, final float v2) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2)));
      }

      // $FF: synthetic method
      static float apply$mcFFF$sp$(final UImpl2 $this, final float v, final float v2) {
         return $this.apply$mcFFF$sp(v, v2);
      }

      default float apply$mcFFF$sp(final float v, final float v2) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2)));
      }

      // $FF: synthetic method
      static int apply$mcFFI$sp$(final UImpl2 $this, final float v, final float v2) {
         return $this.apply$mcFFI$sp(v, v2);
      }

      default int apply$mcFFI$sp(final float v, final float v2) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2)));
      }

      // $FF: synthetic method
      static double apply$mcFID$sp$(final UImpl2 $this, final float v, final int v2) {
         return $this.apply$mcFID$sp(v, v2);
      }

      default double apply$mcFID$sp(final float v, final int v2) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2)));
      }

      // $FF: synthetic method
      static float apply$mcFIF$sp$(final UImpl2 $this, final float v, final int v2) {
         return $this.apply$mcFIF$sp(v, v2);
      }

      default float apply$mcFIF$sp(final float v, final int v2) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2)));
      }

      // $FF: synthetic method
      static int apply$mcFII$sp$(final UImpl2 $this, final float v, final int v2) {
         return $this.apply$mcFII$sp(v, v2);
      }

      default int apply$mcFII$sp(final float v, final int v2) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2)));
      }

      // $FF: synthetic method
      static double apply$mcIDD$sp$(final UImpl2 $this, final int v, final double v2) {
         return $this.apply$mcIDD$sp(v, v2);
      }

      default double apply$mcIDD$sp(final int v, final double v2) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2)));
      }

      // $FF: synthetic method
      static float apply$mcIDF$sp$(final UImpl2 $this, final int v, final double v2) {
         return $this.apply$mcIDF$sp(v, v2);
      }

      default float apply$mcIDF$sp(final int v, final double v2) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2)));
      }

      // $FF: synthetic method
      static int apply$mcIDI$sp$(final UImpl2 $this, final int v, final double v2) {
         return $this.apply$mcIDI$sp(v, v2);
      }

      default int apply$mcIDI$sp(final int v, final double v2) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2)));
      }

      // $FF: synthetic method
      static double apply$mcIFD$sp$(final UImpl2 $this, final int v, final float v2) {
         return $this.apply$mcIFD$sp(v, v2);
      }

      default double apply$mcIFD$sp(final int v, final float v2) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2)));
      }

      // $FF: synthetic method
      static float apply$mcIFF$sp$(final UImpl2 $this, final int v, final float v2) {
         return $this.apply$mcIFF$sp(v, v2);
      }

      default float apply$mcIFF$sp(final int v, final float v2) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2)));
      }

      // $FF: synthetic method
      static int apply$mcIFI$sp$(final UImpl2 $this, final int v, final float v2) {
         return $this.apply$mcIFI$sp(v, v2);
      }

      default int apply$mcIFI$sp(final int v, final float v2) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2)));
      }

      // $FF: synthetic method
      static double apply$mcIID$sp$(final UImpl2 $this, final int v, final int v2) {
         return $this.apply$mcIID$sp(v, v2);
      }

      default double apply$mcIID$sp(final int v, final int v2) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2)));
      }

      // $FF: synthetic method
      static float apply$mcIIF$sp$(final UImpl2 $this, final int v, final int v2) {
         return $this.apply$mcIIF$sp(v, v2);
      }

      default float apply$mcIIF$sp(final int v, final int v2) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2)));
      }

      // $FF: synthetic method
      static int apply$mcIII$sp$(final UImpl2 $this, final int v, final int v2) {
         return $this.apply$mcIII$sp(v, v2);
      }

      default int apply$mcIII$sp(final int v, final int v2) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2)));
      }
   }

   public interface UImpl3 extends Serializable {
      Object apply(final Object v, final Object v2, final Object v3);

      // $FF: synthetic method
      static double apply$mcDDDD$sp$(final UImpl3 $this, final double v, final double v2, final double v3) {
         return $this.apply$mcDDDD$sp(v, v2, v3);
      }

      default double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static float apply$mcDDDF$sp$(final UImpl3 $this, final double v, final double v2, final double v3) {
         return $this.apply$mcDDDF$sp(v, v2, v3);
      }

      default float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static int apply$mcDDDI$sp$(final UImpl3 $this, final double v, final double v2, final double v3) {
         return $this.apply$mcDDDI$sp(v, v2, v3);
      }

      default int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static double apply$mcDDFD$sp$(final UImpl3 $this, final double v, final double v2, final float v3) {
         return $this.apply$mcDDFD$sp(v, v2, v3);
      }

      default double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static float apply$mcDDFF$sp$(final UImpl3 $this, final double v, final double v2, final float v3) {
         return $this.apply$mcDDFF$sp(v, v2, v3);
      }

      default float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static int apply$mcDDFI$sp$(final UImpl3 $this, final double v, final double v2, final float v3) {
         return $this.apply$mcDDFI$sp(v, v2, v3);
      }

      default int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static double apply$mcDDID$sp$(final UImpl3 $this, final double v, final double v2, final int v3) {
         return $this.apply$mcDDID$sp(v, v2, v3);
      }

      default double apply$mcDDID$sp(final double v, final double v2, final int v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static float apply$mcDDIF$sp$(final UImpl3 $this, final double v, final double v2, final int v3) {
         return $this.apply$mcDDIF$sp(v, v2, v3);
      }

      default float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static int apply$mcDDII$sp$(final UImpl3 $this, final double v, final double v2, final int v3) {
         return $this.apply$mcDDII$sp(v, v2, v3);
      }

      default int apply$mcDDII$sp(final double v, final double v2, final int v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static double apply$mcDFDD$sp$(final UImpl3 $this, final double v, final float v2, final double v3) {
         return $this.apply$mcDFDD$sp(v, v2, v3);
      }

      default double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static float apply$mcDFDF$sp$(final UImpl3 $this, final double v, final float v2, final double v3) {
         return $this.apply$mcDFDF$sp(v, v2, v3);
      }

      default float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static int apply$mcDFDI$sp$(final UImpl3 $this, final double v, final float v2, final double v3) {
         return $this.apply$mcDFDI$sp(v, v2, v3);
      }

      default int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static double apply$mcDFFD$sp$(final UImpl3 $this, final double v, final float v2, final float v3) {
         return $this.apply$mcDFFD$sp(v, v2, v3);
      }

      default double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static float apply$mcDFFF$sp$(final UImpl3 $this, final double v, final float v2, final float v3) {
         return $this.apply$mcDFFF$sp(v, v2, v3);
      }

      default float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static int apply$mcDFFI$sp$(final UImpl3 $this, final double v, final float v2, final float v3) {
         return $this.apply$mcDFFI$sp(v, v2, v3);
      }

      default int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static double apply$mcDFID$sp$(final UImpl3 $this, final double v, final float v2, final int v3) {
         return $this.apply$mcDFID$sp(v, v2, v3);
      }

      default double apply$mcDFID$sp(final double v, final float v2, final int v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static float apply$mcDFIF$sp$(final UImpl3 $this, final double v, final float v2, final int v3) {
         return $this.apply$mcDFIF$sp(v, v2, v3);
      }

      default float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static int apply$mcDFII$sp$(final UImpl3 $this, final double v, final float v2, final int v3) {
         return $this.apply$mcDFII$sp(v, v2, v3);
      }

      default int apply$mcDFII$sp(final double v, final float v2, final int v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static double apply$mcDIDD$sp$(final UImpl3 $this, final double v, final int v2, final double v3) {
         return $this.apply$mcDIDD$sp(v, v2, v3);
      }

      default double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static float apply$mcDIDF$sp$(final UImpl3 $this, final double v, final int v2, final double v3) {
         return $this.apply$mcDIDF$sp(v, v2, v3);
      }

      default float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static int apply$mcDIDI$sp$(final UImpl3 $this, final double v, final int v2, final double v3) {
         return $this.apply$mcDIDI$sp(v, v2, v3);
      }

      default int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static double apply$mcDIFD$sp$(final UImpl3 $this, final double v, final int v2, final float v3) {
         return $this.apply$mcDIFD$sp(v, v2, v3);
      }

      default double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static float apply$mcDIFF$sp$(final UImpl3 $this, final double v, final int v2, final float v3) {
         return $this.apply$mcDIFF$sp(v, v2, v3);
      }

      default float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static int apply$mcDIFI$sp$(final UImpl3 $this, final double v, final int v2, final float v3) {
         return $this.apply$mcDIFI$sp(v, v2, v3);
      }

      default int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static double apply$mcDIID$sp$(final UImpl3 $this, final double v, final int v2, final int v3) {
         return $this.apply$mcDIID$sp(v, v2, v3);
      }

      default double apply$mcDIID$sp(final double v, final int v2, final int v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static float apply$mcDIIF$sp$(final UImpl3 $this, final double v, final int v2, final int v3) {
         return $this.apply$mcDIIF$sp(v, v2, v3);
      }

      default float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static int apply$mcDIII$sp$(final UImpl3 $this, final double v, final int v2, final int v3) {
         return $this.apply$mcDIII$sp(v, v2, v3);
      }

      default int apply$mcDIII$sp(final double v, final int v2, final int v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static double apply$mcFDDD$sp$(final UImpl3 $this, final float v, final double v2, final double v3) {
         return $this.apply$mcFDDD$sp(v, v2, v3);
      }

      default double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static float apply$mcFDDF$sp$(final UImpl3 $this, final float v, final double v2, final double v3) {
         return $this.apply$mcFDDF$sp(v, v2, v3);
      }

      default float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static int apply$mcFDDI$sp$(final UImpl3 $this, final float v, final double v2, final double v3) {
         return $this.apply$mcFDDI$sp(v, v2, v3);
      }

      default int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static double apply$mcFDFD$sp$(final UImpl3 $this, final float v, final double v2, final float v3) {
         return $this.apply$mcFDFD$sp(v, v2, v3);
      }

      default double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static float apply$mcFDFF$sp$(final UImpl3 $this, final float v, final double v2, final float v3) {
         return $this.apply$mcFDFF$sp(v, v2, v3);
      }

      default float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static int apply$mcFDFI$sp$(final UImpl3 $this, final float v, final double v2, final float v3) {
         return $this.apply$mcFDFI$sp(v, v2, v3);
      }

      default int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static double apply$mcFDID$sp$(final UImpl3 $this, final float v, final double v2, final int v3) {
         return $this.apply$mcFDID$sp(v, v2, v3);
      }

      default double apply$mcFDID$sp(final float v, final double v2, final int v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static float apply$mcFDIF$sp$(final UImpl3 $this, final float v, final double v2, final int v3) {
         return $this.apply$mcFDIF$sp(v, v2, v3);
      }

      default float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static int apply$mcFDII$sp$(final UImpl3 $this, final float v, final double v2, final int v3) {
         return $this.apply$mcFDII$sp(v, v2, v3);
      }

      default int apply$mcFDII$sp(final float v, final double v2, final int v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static double apply$mcFFDD$sp$(final UImpl3 $this, final float v, final float v2, final double v3) {
         return $this.apply$mcFFDD$sp(v, v2, v3);
      }

      default double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static float apply$mcFFDF$sp$(final UImpl3 $this, final float v, final float v2, final double v3) {
         return $this.apply$mcFFDF$sp(v, v2, v3);
      }

      default float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static int apply$mcFFDI$sp$(final UImpl3 $this, final float v, final float v2, final double v3) {
         return $this.apply$mcFFDI$sp(v, v2, v3);
      }

      default int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static double apply$mcFFFD$sp$(final UImpl3 $this, final float v, final float v2, final float v3) {
         return $this.apply$mcFFFD$sp(v, v2, v3);
      }

      default double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static float apply$mcFFFF$sp$(final UImpl3 $this, final float v, final float v2, final float v3) {
         return $this.apply$mcFFFF$sp(v, v2, v3);
      }

      default float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static int apply$mcFFFI$sp$(final UImpl3 $this, final float v, final float v2, final float v3) {
         return $this.apply$mcFFFI$sp(v, v2, v3);
      }

      default int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static double apply$mcFFID$sp$(final UImpl3 $this, final float v, final float v2, final int v3) {
         return $this.apply$mcFFID$sp(v, v2, v3);
      }

      default double apply$mcFFID$sp(final float v, final float v2, final int v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static float apply$mcFFIF$sp$(final UImpl3 $this, final float v, final float v2, final int v3) {
         return $this.apply$mcFFIF$sp(v, v2, v3);
      }

      default float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static int apply$mcFFII$sp$(final UImpl3 $this, final float v, final float v2, final int v3) {
         return $this.apply$mcFFII$sp(v, v2, v3);
      }

      default int apply$mcFFII$sp(final float v, final float v2, final int v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static double apply$mcFIDD$sp$(final UImpl3 $this, final float v, final int v2, final double v3) {
         return $this.apply$mcFIDD$sp(v, v2, v3);
      }

      default double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static float apply$mcFIDF$sp$(final UImpl3 $this, final float v, final int v2, final double v3) {
         return $this.apply$mcFIDF$sp(v, v2, v3);
      }

      default float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static int apply$mcFIDI$sp$(final UImpl3 $this, final float v, final int v2, final double v3) {
         return $this.apply$mcFIDI$sp(v, v2, v3);
      }

      default int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static double apply$mcFIFD$sp$(final UImpl3 $this, final float v, final int v2, final float v3) {
         return $this.apply$mcFIFD$sp(v, v2, v3);
      }

      default double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static float apply$mcFIFF$sp$(final UImpl3 $this, final float v, final int v2, final float v3) {
         return $this.apply$mcFIFF$sp(v, v2, v3);
      }

      default float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static int apply$mcFIFI$sp$(final UImpl3 $this, final float v, final int v2, final float v3) {
         return $this.apply$mcFIFI$sp(v, v2, v3);
      }

      default int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static double apply$mcFIID$sp$(final UImpl3 $this, final float v, final int v2, final int v3) {
         return $this.apply$mcFIID$sp(v, v2, v3);
      }

      default double apply$mcFIID$sp(final float v, final int v2, final int v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static float apply$mcFIIF$sp$(final UImpl3 $this, final float v, final int v2, final int v3) {
         return $this.apply$mcFIIF$sp(v, v2, v3);
      }

      default float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static int apply$mcFIII$sp$(final UImpl3 $this, final float v, final int v2, final int v3) {
         return $this.apply$mcFIII$sp(v, v2, v3);
      }

      default int apply$mcFIII$sp(final float v, final int v2, final int v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static double apply$mcIDDD$sp$(final UImpl3 $this, final int v, final double v2, final double v3) {
         return $this.apply$mcIDDD$sp(v, v2, v3);
      }

      default double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static float apply$mcIDDF$sp$(final UImpl3 $this, final int v, final double v2, final double v3) {
         return $this.apply$mcIDDF$sp(v, v2, v3);
      }

      default float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static int apply$mcIDDI$sp$(final UImpl3 $this, final int v, final double v2, final double v3) {
         return $this.apply$mcIDDI$sp(v, v2, v3);
      }

      default int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static double apply$mcIDFD$sp$(final UImpl3 $this, final int v, final double v2, final float v3) {
         return $this.apply$mcIDFD$sp(v, v2, v3);
      }

      default double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static float apply$mcIDFF$sp$(final UImpl3 $this, final int v, final double v2, final float v3) {
         return $this.apply$mcIDFF$sp(v, v2, v3);
      }

      default float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static int apply$mcIDFI$sp$(final UImpl3 $this, final int v, final double v2, final float v3) {
         return $this.apply$mcIDFI$sp(v, v2, v3);
      }

      default int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static double apply$mcIDID$sp$(final UImpl3 $this, final int v, final double v2, final int v3) {
         return $this.apply$mcIDID$sp(v, v2, v3);
      }

      default double apply$mcIDID$sp(final int v, final double v2, final int v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static float apply$mcIDIF$sp$(final UImpl3 $this, final int v, final double v2, final int v3) {
         return $this.apply$mcIDIF$sp(v, v2, v3);
      }

      default float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static int apply$mcIDII$sp$(final UImpl3 $this, final int v, final double v2, final int v3) {
         return $this.apply$mcIDII$sp(v, v2, v3);
      }

      default int apply$mcIDII$sp(final int v, final double v2, final int v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static double apply$mcIFDD$sp$(final UImpl3 $this, final int v, final float v2, final double v3) {
         return $this.apply$mcIFDD$sp(v, v2, v3);
      }

      default double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static float apply$mcIFDF$sp$(final UImpl3 $this, final int v, final float v2, final double v3) {
         return $this.apply$mcIFDF$sp(v, v2, v3);
      }

      default float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static int apply$mcIFDI$sp$(final UImpl3 $this, final int v, final float v2, final double v3) {
         return $this.apply$mcIFDI$sp(v, v2, v3);
      }

      default int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static double apply$mcIFFD$sp$(final UImpl3 $this, final int v, final float v2, final float v3) {
         return $this.apply$mcIFFD$sp(v, v2, v3);
      }

      default double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static float apply$mcIFFF$sp$(final UImpl3 $this, final int v, final float v2, final float v3) {
         return $this.apply$mcIFFF$sp(v, v2, v3);
      }

      default float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static int apply$mcIFFI$sp$(final UImpl3 $this, final int v, final float v2, final float v3) {
         return $this.apply$mcIFFI$sp(v, v2, v3);
      }

      default int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static double apply$mcIFID$sp$(final UImpl3 $this, final int v, final float v2, final int v3) {
         return $this.apply$mcIFID$sp(v, v2, v3);
      }

      default double apply$mcIFID$sp(final int v, final float v2, final int v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static float apply$mcIFIF$sp$(final UImpl3 $this, final int v, final float v2, final int v3) {
         return $this.apply$mcIFIF$sp(v, v2, v3);
      }

      default float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static int apply$mcIFII$sp$(final UImpl3 $this, final int v, final float v2, final int v3) {
         return $this.apply$mcIFII$sp(v, v2, v3);
      }

      default int apply$mcIFII$sp(final int v, final float v2, final int v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static double apply$mcIIDD$sp$(final UImpl3 $this, final int v, final int v2, final double v3) {
         return $this.apply$mcIIDD$sp(v, v2, v3);
      }

      default double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static float apply$mcIIDF$sp$(final UImpl3 $this, final int v, final int v2, final double v3) {
         return $this.apply$mcIIDF$sp(v, v2, v3);
      }

      default float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static int apply$mcIIDI$sp$(final UImpl3 $this, final int v, final int v2, final double v3) {
         return $this.apply$mcIIDI$sp(v, v2, v3);
      }

      default int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3)));
      }

      // $FF: synthetic method
      static double apply$mcIIFD$sp$(final UImpl3 $this, final int v, final int v2, final float v3) {
         return $this.apply$mcIIFD$sp(v, v2, v3);
      }

      default double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static float apply$mcIIFF$sp$(final UImpl3 $this, final int v, final int v2, final float v3) {
         return $this.apply$mcIIFF$sp(v, v2, v3);
      }

      default float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static int apply$mcIIFI$sp$(final UImpl3 $this, final int v, final int v2, final float v3) {
         return $this.apply$mcIIFI$sp(v, v2, v3);
      }

      default int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3)));
      }

      // $FF: synthetic method
      static double apply$mcIIID$sp$(final UImpl3 $this, final int v, final int v2, final int v3) {
         return $this.apply$mcIIID$sp(v, v2, v3);
      }

      default double apply$mcIIID$sp(final int v, final int v2, final int v3) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static float apply$mcIIIF$sp$(final UImpl3 $this, final int v, final int v2, final int v3) {
         return $this.apply$mcIIIF$sp(v, v2, v3);
      }

      default float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
      }

      // $FF: synthetic method
      static int apply$mcIIII$sp$(final UImpl3 $this, final int v, final int v2, final int v3) {
         return $this.apply$mcIIII$sp(v, v2, v3);
      }

      default int apply$mcIIII$sp(final int v, final int v2, final int v3) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3)));
      }
   }

   public interface UImpl4 extends Serializable {
      Object apply(final Object v, final Object v2, final Object v3, final Object v4);

      // $FF: synthetic method
      static double apply$mcDDDDD$sp$(final UImpl4 $this, final double v, final double v2, final double v3, final double v4) {
         return $this.apply$mcDDDDD$sp(v, v2, v3, v4);
      }

      default double apply$mcDDDDD$sp(final double v, final double v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDDDDF$sp$(final UImpl4 $this, final double v, final double v2, final double v3, final double v4) {
         return $this.apply$mcDDDDF$sp(v, v2, v3, v4);
      }

      default float apply$mcDDDDF$sp(final double v, final double v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDDDDI$sp$(final UImpl4 $this, final double v, final double v2, final double v3, final double v4) {
         return $this.apply$mcDDDDI$sp(v, v2, v3, v4);
      }

      default int apply$mcDDDDI$sp(final double v, final double v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDDDFD$sp$(final UImpl4 $this, final double v, final double v2, final double v3, final float v4) {
         return $this.apply$mcDDDFD$sp(v, v2, v3, v4);
      }

      default double apply$mcDDDFD$sp(final double v, final double v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDDDFF$sp$(final UImpl4 $this, final double v, final double v2, final double v3, final float v4) {
         return $this.apply$mcDDDFF$sp(v, v2, v3, v4);
      }

      default float apply$mcDDDFF$sp(final double v, final double v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDDDFI$sp$(final UImpl4 $this, final double v, final double v2, final double v3, final float v4) {
         return $this.apply$mcDDDFI$sp(v, v2, v3, v4);
      }

      default int apply$mcDDDFI$sp(final double v, final double v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDDDID$sp$(final UImpl4 $this, final double v, final double v2, final double v3, final int v4) {
         return $this.apply$mcDDDID$sp(v, v2, v3, v4);
      }

      default double apply$mcDDDID$sp(final double v, final double v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDDDIF$sp$(final UImpl4 $this, final double v, final double v2, final double v3, final int v4) {
         return $this.apply$mcDDDIF$sp(v, v2, v3, v4);
      }

      default float apply$mcDDDIF$sp(final double v, final double v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDDDII$sp$(final UImpl4 $this, final double v, final double v2, final double v3, final int v4) {
         return $this.apply$mcDDDII$sp(v, v2, v3, v4);
      }

      default int apply$mcDDDII$sp(final double v, final double v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDDFDD$sp$(final UImpl4 $this, final double v, final double v2, final float v3, final double v4) {
         return $this.apply$mcDDFDD$sp(v, v2, v3, v4);
      }

      default double apply$mcDDFDD$sp(final double v, final double v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDDFDF$sp$(final UImpl4 $this, final double v, final double v2, final float v3, final double v4) {
         return $this.apply$mcDDFDF$sp(v, v2, v3, v4);
      }

      default float apply$mcDDFDF$sp(final double v, final double v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDDFDI$sp$(final UImpl4 $this, final double v, final double v2, final float v3, final double v4) {
         return $this.apply$mcDDFDI$sp(v, v2, v3, v4);
      }

      default int apply$mcDDFDI$sp(final double v, final double v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDDFFD$sp$(final UImpl4 $this, final double v, final double v2, final float v3, final float v4) {
         return $this.apply$mcDDFFD$sp(v, v2, v3, v4);
      }

      default double apply$mcDDFFD$sp(final double v, final double v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDDFFF$sp$(final UImpl4 $this, final double v, final double v2, final float v3, final float v4) {
         return $this.apply$mcDDFFF$sp(v, v2, v3, v4);
      }

      default float apply$mcDDFFF$sp(final double v, final double v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDDFFI$sp$(final UImpl4 $this, final double v, final double v2, final float v3, final float v4) {
         return $this.apply$mcDDFFI$sp(v, v2, v3, v4);
      }

      default int apply$mcDDFFI$sp(final double v, final double v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDDFID$sp$(final UImpl4 $this, final double v, final double v2, final float v3, final int v4) {
         return $this.apply$mcDDFID$sp(v, v2, v3, v4);
      }

      default double apply$mcDDFID$sp(final double v, final double v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDDFIF$sp$(final UImpl4 $this, final double v, final double v2, final float v3, final int v4) {
         return $this.apply$mcDDFIF$sp(v, v2, v3, v4);
      }

      default float apply$mcDDFIF$sp(final double v, final double v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDDFII$sp$(final UImpl4 $this, final double v, final double v2, final float v3, final int v4) {
         return $this.apply$mcDDFII$sp(v, v2, v3, v4);
      }

      default int apply$mcDDFII$sp(final double v, final double v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDDIDD$sp$(final UImpl4 $this, final double v, final double v2, final int v3, final double v4) {
         return $this.apply$mcDDIDD$sp(v, v2, v3, v4);
      }

      default double apply$mcDDIDD$sp(final double v, final double v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDDIDF$sp$(final UImpl4 $this, final double v, final double v2, final int v3, final double v4) {
         return $this.apply$mcDDIDF$sp(v, v2, v3, v4);
      }

      default float apply$mcDDIDF$sp(final double v, final double v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDDIDI$sp$(final UImpl4 $this, final double v, final double v2, final int v3, final double v4) {
         return $this.apply$mcDDIDI$sp(v, v2, v3, v4);
      }

      default int apply$mcDDIDI$sp(final double v, final double v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDDIFD$sp$(final UImpl4 $this, final double v, final double v2, final int v3, final float v4) {
         return $this.apply$mcDDIFD$sp(v, v2, v3, v4);
      }

      default double apply$mcDDIFD$sp(final double v, final double v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDDIFF$sp$(final UImpl4 $this, final double v, final double v2, final int v3, final float v4) {
         return $this.apply$mcDDIFF$sp(v, v2, v3, v4);
      }

      default float apply$mcDDIFF$sp(final double v, final double v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDDIFI$sp$(final UImpl4 $this, final double v, final double v2, final int v3, final float v4) {
         return $this.apply$mcDDIFI$sp(v, v2, v3, v4);
      }

      default int apply$mcDDIFI$sp(final double v, final double v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDDIID$sp$(final UImpl4 $this, final double v, final double v2, final int v3, final int v4) {
         return $this.apply$mcDDIID$sp(v, v2, v3, v4);
      }

      default double apply$mcDDIID$sp(final double v, final double v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDDIIF$sp$(final UImpl4 $this, final double v, final double v2, final int v3, final int v4) {
         return $this.apply$mcDDIIF$sp(v, v2, v3, v4);
      }

      default float apply$mcDDIIF$sp(final double v, final double v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDDIII$sp$(final UImpl4 $this, final double v, final double v2, final int v3, final int v4) {
         return $this.apply$mcDDIII$sp(v, v2, v3, v4);
      }

      default int apply$mcDDIII$sp(final double v, final double v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDFDDD$sp$(final UImpl4 $this, final double v, final float v2, final double v3, final double v4) {
         return $this.apply$mcDFDDD$sp(v, v2, v3, v4);
      }

      default double apply$mcDFDDD$sp(final double v, final float v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDFDDF$sp$(final UImpl4 $this, final double v, final float v2, final double v3, final double v4) {
         return $this.apply$mcDFDDF$sp(v, v2, v3, v4);
      }

      default float apply$mcDFDDF$sp(final double v, final float v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDFDDI$sp$(final UImpl4 $this, final double v, final float v2, final double v3, final double v4) {
         return $this.apply$mcDFDDI$sp(v, v2, v3, v4);
      }

      default int apply$mcDFDDI$sp(final double v, final float v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDFDFD$sp$(final UImpl4 $this, final double v, final float v2, final double v3, final float v4) {
         return $this.apply$mcDFDFD$sp(v, v2, v3, v4);
      }

      default double apply$mcDFDFD$sp(final double v, final float v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDFDFF$sp$(final UImpl4 $this, final double v, final float v2, final double v3, final float v4) {
         return $this.apply$mcDFDFF$sp(v, v2, v3, v4);
      }

      default float apply$mcDFDFF$sp(final double v, final float v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDFDFI$sp$(final UImpl4 $this, final double v, final float v2, final double v3, final float v4) {
         return $this.apply$mcDFDFI$sp(v, v2, v3, v4);
      }

      default int apply$mcDFDFI$sp(final double v, final float v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDFDID$sp$(final UImpl4 $this, final double v, final float v2, final double v3, final int v4) {
         return $this.apply$mcDFDID$sp(v, v2, v3, v4);
      }

      default double apply$mcDFDID$sp(final double v, final float v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDFDIF$sp$(final UImpl4 $this, final double v, final float v2, final double v3, final int v4) {
         return $this.apply$mcDFDIF$sp(v, v2, v3, v4);
      }

      default float apply$mcDFDIF$sp(final double v, final float v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDFDII$sp$(final UImpl4 $this, final double v, final float v2, final double v3, final int v4) {
         return $this.apply$mcDFDII$sp(v, v2, v3, v4);
      }

      default int apply$mcDFDII$sp(final double v, final float v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDFFDD$sp$(final UImpl4 $this, final double v, final float v2, final float v3, final double v4) {
         return $this.apply$mcDFFDD$sp(v, v2, v3, v4);
      }

      default double apply$mcDFFDD$sp(final double v, final float v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDFFDF$sp$(final UImpl4 $this, final double v, final float v2, final float v3, final double v4) {
         return $this.apply$mcDFFDF$sp(v, v2, v3, v4);
      }

      default float apply$mcDFFDF$sp(final double v, final float v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDFFDI$sp$(final UImpl4 $this, final double v, final float v2, final float v3, final double v4) {
         return $this.apply$mcDFFDI$sp(v, v2, v3, v4);
      }

      default int apply$mcDFFDI$sp(final double v, final float v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDFFFD$sp$(final UImpl4 $this, final double v, final float v2, final float v3, final float v4) {
         return $this.apply$mcDFFFD$sp(v, v2, v3, v4);
      }

      default double apply$mcDFFFD$sp(final double v, final float v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDFFFF$sp$(final UImpl4 $this, final double v, final float v2, final float v3, final float v4) {
         return $this.apply$mcDFFFF$sp(v, v2, v3, v4);
      }

      default float apply$mcDFFFF$sp(final double v, final float v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDFFFI$sp$(final UImpl4 $this, final double v, final float v2, final float v3, final float v4) {
         return $this.apply$mcDFFFI$sp(v, v2, v3, v4);
      }

      default int apply$mcDFFFI$sp(final double v, final float v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDFFID$sp$(final UImpl4 $this, final double v, final float v2, final float v3, final int v4) {
         return $this.apply$mcDFFID$sp(v, v2, v3, v4);
      }

      default double apply$mcDFFID$sp(final double v, final float v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDFFIF$sp$(final UImpl4 $this, final double v, final float v2, final float v3, final int v4) {
         return $this.apply$mcDFFIF$sp(v, v2, v3, v4);
      }

      default float apply$mcDFFIF$sp(final double v, final float v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDFFII$sp$(final UImpl4 $this, final double v, final float v2, final float v3, final int v4) {
         return $this.apply$mcDFFII$sp(v, v2, v3, v4);
      }

      default int apply$mcDFFII$sp(final double v, final float v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDFIDD$sp$(final UImpl4 $this, final double v, final float v2, final int v3, final double v4) {
         return $this.apply$mcDFIDD$sp(v, v2, v3, v4);
      }

      default double apply$mcDFIDD$sp(final double v, final float v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDFIDF$sp$(final UImpl4 $this, final double v, final float v2, final int v3, final double v4) {
         return $this.apply$mcDFIDF$sp(v, v2, v3, v4);
      }

      default float apply$mcDFIDF$sp(final double v, final float v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDFIDI$sp$(final UImpl4 $this, final double v, final float v2, final int v3, final double v4) {
         return $this.apply$mcDFIDI$sp(v, v2, v3, v4);
      }

      default int apply$mcDFIDI$sp(final double v, final float v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDFIFD$sp$(final UImpl4 $this, final double v, final float v2, final int v3, final float v4) {
         return $this.apply$mcDFIFD$sp(v, v2, v3, v4);
      }

      default double apply$mcDFIFD$sp(final double v, final float v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDFIFF$sp$(final UImpl4 $this, final double v, final float v2, final int v3, final float v4) {
         return $this.apply$mcDFIFF$sp(v, v2, v3, v4);
      }

      default float apply$mcDFIFF$sp(final double v, final float v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDFIFI$sp$(final UImpl4 $this, final double v, final float v2, final int v3, final float v4) {
         return $this.apply$mcDFIFI$sp(v, v2, v3, v4);
      }

      default int apply$mcDFIFI$sp(final double v, final float v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDFIID$sp$(final UImpl4 $this, final double v, final float v2, final int v3, final int v4) {
         return $this.apply$mcDFIID$sp(v, v2, v3, v4);
      }

      default double apply$mcDFIID$sp(final double v, final float v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDFIIF$sp$(final UImpl4 $this, final double v, final float v2, final int v3, final int v4) {
         return $this.apply$mcDFIIF$sp(v, v2, v3, v4);
      }

      default float apply$mcDFIIF$sp(final double v, final float v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDFIII$sp$(final UImpl4 $this, final double v, final float v2, final int v3, final int v4) {
         return $this.apply$mcDFIII$sp(v, v2, v3, v4);
      }

      default int apply$mcDFIII$sp(final double v, final float v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDIDDD$sp$(final UImpl4 $this, final double v, final int v2, final double v3, final double v4) {
         return $this.apply$mcDIDDD$sp(v, v2, v3, v4);
      }

      default double apply$mcDIDDD$sp(final double v, final int v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDIDDF$sp$(final UImpl4 $this, final double v, final int v2, final double v3, final double v4) {
         return $this.apply$mcDIDDF$sp(v, v2, v3, v4);
      }

      default float apply$mcDIDDF$sp(final double v, final int v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDIDDI$sp$(final UImpl4 $this, final double v, final int v2, final double v3, final double v4) {
         return $this.apply$mcDIDDI$sp(v, v2, v3, v4);
      }

      default int apply$mcDIDDI$sp(final double v, final int v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDIDFD$sp$(final UImpl4 $this, final double v, final int v2, final double v3, final float v4) {
         return $this.apply$mcDIDFD$sp(v, v2, v3, v4);
      }

      default double apply$mcDIDFD$sp(final double v, final int v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDIDFF$sp$(final UImpl4 $this, final double v, final int v2, final double v3, final float v4) {
         return $this.apply$mcDIDFF$sp(v, v2, v3, v4);
      }

      default float apply$mcDIDFF$sp(final double v, final int v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDIDFI$sp$(final UImpl4 $this, final double v, final int v2, final double v3, final float v4) {
         return $this.apply$mcDIDFI$sp(v, v2, v3, v4);
      }

      default int apply$mcDIDFI$sp(final double v, final int v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDIDID$sp$(final UImpl4 $this, final double v, final int v2, final double v3, final int v4) {
         return $this.apply$mcDIDID$sp(v, v2, v3, v4);
      }

      default double apply$mcDIDID$sp(final double v, final int v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDIDIF$sp$(final UImpl4 $this, final double v, final int v2, final double v3, final int v4) {
         return $this.apply$mcDIDIF$sp(v, v2, v3, v4);
      }

      default float apply$mcDIDIF$sp(final double v, final int v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDIDII$sp$(final UImpl4 $this, final double v, final int v2, final double v3, final int v4) {
         return $this.apply$mcDIDII$sp(v, v2, v3, v4);
      }

      default int apply$mcDIDII$sp(final double v, final int v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDIFDD$sp$(final UImpl4 $this, final double v, final int v2, final float v3, final double v4) {
         return $this.apply$mcDIFDD$sp(v, v2, v3, v4);
      }

      default double apply$mcDIFDD$sp(final double v, final int v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDIFDF$sp$(final UImpl4 $this, final double v, final int v2, final float v3, final double v4) {
         return $this.apply$mcDIFDF$sp(v, v2, v3, v4);
      }

      default float apply$mcDIFDF$sp(final double v, final int v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDIFDI$sp$(final UImpl4 $this, final double v, final int v2, final float v3, final double v4) {
         return $this.apply$mcDIFDI$sp(v, v2, v3, v4);
      }

      default int apply$mcDIFDI$sp(final double v, final int v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDIFFD$sp$(final UImpl4 $this, final double v, final int v2, final float v3, final float v4) {
         return $this.apply$mcDIFFD$sp(v, v2, v3, v4);
      }

      default double apply$mcDIFFD$sp(final double v, final int v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDIFFF$sp$(final UImpl4 $this, final double v, final int v2, final float v3, final float v4) {
         return $this.apply$mcDIFFF$sp(v, v2, v3, v4);
      }

      default float apply$mcDIFFF$sp(final double v, final int v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDIFFI$sp$(final UImpl4 $this, final double v, final int v2, final float v3, final float v4) {
         return $this.apply$mcDIFFI$sp(v, v2, v3, v4);
      }

      default int apply$mcDIFFI$sp(final double v, final int v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDIFID$sp$(final UImpl4 $this, final double v, final int v2, final float v3, final int v4) {
         return $this.apply$mcDIFID$sp(v, v2, v3, v4);
      }

      default double apply$mcDIFID$sp(final double v, final int v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDIFIF$sp$(final UImpl4 $this, final double v, final int v2, final float v3, final int v4) {
         return $this.apply$mcDIFIF$sp(v, v2, v3, v4);
      }

      default float apply$mcDIFIF$sp(final double v, final int v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDIFII$sp$(final UImpl4 $this, final double v, final int v2, final float v3, final int v4) {
         return $this.apply$mcDIFII$sp(v, v2, v3, v4);
      }

      default int apply$mcDIFII$sp(final double v, final int v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDIIDD$sp$(final UImpl4 $this, final double v, final int v2, final int v3, final double v4) {
         return $this.apply$mcDIIDD$sp(v, v2, v3, v4);
      }

      default double apply$mcDIIDD$sp(final double v, final int v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDIIDF$sp$(final UImpl4 $this, final double v, final int v2, final int v3, final double v4) {
         return $this.apply$mcDIIDF$sp(v, v2, v3, v4);
      }

      default float apply$mcDIIDF$sp(final double v, final int v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDIIDI$sp$(final UImpl4 $this, final double v, final int v2, final int v3, final double v4) {
         return $this.apply$mcDIIDI$sp(v, v2, v3, v4);
      }

      default int apply$mcDIIDI$sp(final double v, final int v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDIIFD$sp$(final UImpl4 $this, final double v, final int v2, final int v3, final float v4) {
         return $this.apply$mcDIIFD$sp(v, v2, v3, v4);
      }

      default double apply$mcDIIFD$sp(final double v, final int v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDIIFF$sp$(final UImpl4 $this, final double v, final int v2, final int v3, final float v4) {
         return $this.apply$mcDIIFF$sp(v, v2, v3, v4);
      }

      default float apply$mcDIIFF$sp(final double v, final int v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDIIFI$sp$(final UImpl4 $this, final double v, final int v2, final int v3, final float v4) {
         return $this.apply$mcDIIFI$sp(v, v2, v3, v4);
      }

      default int apply$mcDIIFI$sp(final double v, final int v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcDIIID$sp$(final UImpl4 $this, final double v, final int v2, final int v3, final int v4) {
         return $this.apply$mcDIIID$sp(v, v2, v3, v4);
      }

      default double apply$mcDIIID$sp(final double v, final int v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcDIIIF$sp$(final UImpl4 $this, final double v, final int v2, final int v3, final int v4) {
         return $this.apply$mcDIIIF$sp(v, v2, v3, v4);
      }

      default float apply$mcDIIIF$sp(final double v, final int v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcDIIII$sp$(final UImpl4 $this, final double v, final int v2, final int v3, final int v4) {
         return $this.apply$mcDIIII$sp(v, v2, v3, v4);
      }

      default int apply$mcDIIII$sp(final double v, final int v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToDouble(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFDDDD$sp$(final UImpl4 $this, final float v, final double v2, final double v3, final double v4) {
         return $this.apply$mcFDDDD$sp(v, v2, v3, v4);
      }

      default double apply$mcFDDDD$sp(final float v, final double v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFDDDF$sp$(final UImpl4 $this, final float v, final double v2, final double v3, final double v4) {
         return $this.apply$mcFDDDF$sp(v, v2, v3, v4);
      }

      default float apply$mcFDDDF$sp(final float v, final double v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFDDDI$sp$(final UImpl4 $this, final float v, final double v2, final double v3, final double v4) {
         return $this.apply$mcFDDDI$sp(v, v2, v3, v4);
      }

      default int apply$mcFDDDI$sp(final float v, final double v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFDDFD$sp$(final UImpl4 $this, final float v, final double v2, final double v3, final float v4) {
         return $this.apply$mcFDDFD$sp(v, v2, v3, v4);
      }

      default double apply$mcFDDFD$sp(final float v, final double v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFDDFF$sp$(final UImpl4 $this, final float v, final double v2, final double v3, final float v4) {
         return $this.apply$mcFDDFF$sp(v, v2, v3, v4);
      }

      default float apply$mcFDDFF$sp(final float v, final double v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFDDFI$sp$(final UImpl4 $this, final float v, final double v2, final double v3, final float v4) {
         return $this.apply$mcFDDFI$sp(v, v2, v3, v4);
      }

      default int apply$mcFDDFI$sp(final float v, final double v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFDDID$sp$(final UImpl4 $this, final float v, final double v2, final double v3, final int v4) {
         return $this.apply$mcFDDID$sp(v, v2, v3, v4);
      }

      default double apply$mcFDDID$sp(final float v, final double v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFDDIF$sp$(final UImpl4 $this, final float v, final double v2, final double v3, final int v4) {
         return $this.apply$mcFDDIF$sp(v, v2, v3, v4);
      }

      default float apply$mcFDDIF$sp(final float v, final double v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFDDII$sp$(final UImpl4 $this, final float v, final double v2, final double v3, final int v4) {
         return $this.apply$mcFDDII$sp(v, v2, v3, v4);
      }

      default int apply$mcFDDII$sp(final float v, final double v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFDFDD$sp$(final UImpl4 $this, final float v, final double v2, final float v3, final double v4) {
         return $this.apply$mcFDFDD$sp(v, v2, v3, v4);
      }

      default double apply$mcFDFDD$sp(final float v, final double v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFDFDF$sp$(final UImpl4 $this, final float v, final double v2, final float v3, final double v4) {
         return $this.apply$mcFDFDF$sp(v, v2, v3, v4);
      }

      default float apply$mcFDFDF$sp(final float v, final double v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFDFDI$sp$(final UImpl4 $this, final float v, final double v2, final float v3, final double v4) {
         return $this.apply$mcFDFDI$sp(v, v2, v3, v4);
      }

      default int apply$mcFDFDI$sp(final float v, final double v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFDFFD$sp$(final UImpl4 $this, final float v, final double v2, final float v3, final float v4) {
         return $this.apply$mcFDFFD$sp(v, v2, v3, v4);
      }

      default double apply$mcFDFFD$sp(final float v, final double v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFDFFF$sp$(final UImpl4 $this, final float v, final double v2, final float v3, final float v4) {
         return $this.apply$mcFDFFF$sp(v, v2, v3, v4);
      }

      default float apply$mcFDFFF$sp(final float v, final double v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFDFFI$sp$(final UImpl4 $this, final float v, final double v2, final float v3, final float v4) {
         return $this.apply$mcFDFFI$sp(v, v2, v3, v4);
      }

      default int apply$mcFDFFI$sp(final float v, final double v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFDFID$sp$(final UImpl4 $this, final float v, final double v2, final float v3, final int v4) {
         return $this.apply$mcFDFID$sp(v, v2, v3, v4);
      }

      default double apply$mcFDFID$sp(final float v, final double v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFDFIF$sp$(final UImpl4 $this, final float v, final double v2, final float v3, final int v4) {
         return $this.apply$mcFDFIF$sp(v, v2, v3, v4);
      }

      default float apply$mcFDFIF$sp(final float v, final double v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFDFII$sp$(final UImpl4 $this, final float v, final double v2, final float v3, final int v4) {
         return $this.apply$mcFDFII$sp(v, v2, v3, v4);
      }

      default int apply$mcFDFII$sp(final float v, final double v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFDIDD$sp$(final UImpl4 $this, final float v, final double v2, final int v3, final double v4) {
         return $this.apply$mcFDIDD$sp(v, v2, v3, v4);
      }

      default double apply$mcFDIDD$sp(final float v, final double v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFDIDF$sp$(final UImpl4 $this, final float v, final double v2, final int v3, final double v4) {
         return $this.apply$mcFDIDF$sp(v, v2, v3, v4);
      }

      default float apply$mcFDIDF$sp(final float v, final double v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFDIDI$sp$(final UImpl4 $this, final float v, final double v2, final int v3, final double v4) {
         return $this.apply$mcFDIDI$sp(v, v2, v3, v4);
      }

      default int apply$mcFDIDI$sp(final float v, final double v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFDIFD$sp$(final UImpl4 $this, final float v, final double v2, final int v3, final float v4) {
         return $this.apply$mcFDIFD$sp(v, v2, v3, v4);
      }

      default double apply$mcFDIFD$sp(final float v, final double v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFDIFF$sp$(final UImpl4 $this, final float v, final double v2, final int v3, final float v4) {
         return $this.apply$mcFDIFF$sp(v, v2, v3, v4);
      }

      default float apply$mcFDIFF$sp(final float v, final double v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFDIFI$sp$(final UImpl4 $this, final float v, final double v2, final int v3, final float v4) {
         return $this.apply$mcFDIFI$sp(v, v2, v3, v4);
      }

      default int apply$mcFDIFI$sp(final float v, final double v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFDIID$sp$(final UImpl4 $this, final float v, final double v2, final int v3, final int v4) {
         return $this.apply$mcFDIID$sp(v, v2, v3, v4);
      }

      default double apply$mcFDIID$sp(final float v, final double v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFDIIF$sp$(final UImpl4 $this, final float v, final double v2, final int v3, final int v4) {
         return $this.apply$mcFDIIF$sp(v, v2, v3, v4);
      }

      default float apply$mcFDIIF$sp(final float v, final double v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFDIII$sp$(final UImpl4 $this, final float v, final double v2, final int v3, final int v4) {
         return $this.apply$mcFDIII$sp(v, v2, v3, v4);
      }

      default int apply$mcFDIII$sp(final float v, final double v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFFDDD$sp$(final UImpl4 $this, final float v, final float v2, final double v3, final double v4) {
         return $this.apply$mcFFDDD$sp(v, v2, v3, v4);
      }

      default double apply$mcFFDDD$sp(final float v, final float v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFFDDF$sp$(final UImpl4 $this, final float v, final float v2, final double v3, final double v4) {
         return $this.apply$mcFFDDF$sp(v, v2, v3, v4);
      }

      default float apply$mcFFDDF$sp(final float v, final float v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFFDDI$sp$(final UImpl4 $this, final float v, final float v2, final double v3, final double v4) {
         return $this.apply$mcFFDDI$sp(v, v2, v3, v4);
      }

      default int apply$mcFFDDI$sp(final float v, final float v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFFDFD$sp$(final UImpl4 $this, final float v, final float v2, final double v3, final float v4) {
         return $this.apply$mcFFDFD$sp(v, v2, v3, v4);
      }

      default double apply$mcFFDFD$sp(final float v, final float v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFFDFF$sp$(final UImpl4 $this, final float v, final float v2, final double v3, final float v4) {
         return $this.apply$mcFFDFF$sp(v, v2, v3, v4);
      }

      default float apply$mcFFDFF$sp(final float v, final float v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFFDFI$sp$(final UImpl4 $this, final float v, final float v2, final double v3, final float v4) {
         return $this.apply$mcFFDFI$sp(v, v2, v3, v4);
      }

      default int apply$mcFFDFI$sp(final float v, final float v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFFDID$sp$(final UImpl4 $this, final float v, final float v2, final double v3, final int v4) {
         return $this.apply$mcFFDID$sp(v, v2, v3, v4);
      }

      default double apply$mcFFDID$sp(final float v, final float v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFFDIF$sp$(final UImpl4 $this, final float v, final float v2, final double v3, final int v4) {
         return $this.apply$mcFFDIF$sp(v, v2, v3, v4);
      }

      default float apply$mcFFDIF$sp(final float v, final float v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFFDII$sp$(final UImpl4 $this, final float v, final float v2, final double v3, final int v4) {
         return $this.apply$mcFFDII$sp(v, v2, v3, v4);
      }

      default int apply$mcFFDII$sp(final float v, final float v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFFFDD$sp$(final UImpl4 $this, final float v, final float v2, final float v3, final double v4) {
         return $this.apply$mcFFFDD$sp(v, v2, v3, v4);
      }

      default double apply$mcFFFDD$sp(final float v, final float v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFFFDF$sp$(final UImpl4 $this, final float v, final float v2, final float v3, final double v4) {
         return $this.apply$mcFFFDF$sp(v, v2, v3, v4);
      }

      default float apply$mcFFFDF$sp(final float v, final float v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFFFDI$sp$(final UImpl4 $this, final float v, final float v2, final float v3, final double v4) {
         return $this.apply$mcFFFDI$sp(v, v2, v3, v4);
      }

      default int apply$mcFFFDI$sp(final float v, final float v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFFFFD$sp$(final UImpl4 $this, final float v, final float v2, final float v3, final float v4) {
         return $this.apply$mcFFFFD$sp(v, v2, v3, v4);
      }

      default double apply$mcFFFFD$sp(final float v, final float v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFFFFF$sp$(final UImpl4 $this, final float v, final float v2, final float v3, final float v4) {
         return $this.apply$mcFFFFF$sp(v, v2, v3, v4);
      }

      default float apply$mcFFFFF$sp(final float v, final float v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFFFFI$sp$(final UImpl4 $this, final float v, final float v2, final float v3, final float v4) {
         return $this.apply$mcFFFFI$sp(v, v2, v3, v4);
      }

      default int apply$mcFFFFI$sp(final float v, final float v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFFFID$sp$(final UImpl4 $this, final float v, final float v2, final float v3, final int v4) {
         return $this.apply$mcFFFID$sp(v, v2, v3, v4);
      }

      default double apply$mcFFFID$sp(final float v, final float v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFFFIF$sp$(final UImpl4 $this, final float v, final float v2, final float v3, final int v4) {
         return $this.apply$mcFFFIF$sp(v, v2, v3, v4);
      }

      default float apply$mcFFFIF$sp(final float v, final float v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFFFII$sp$(final UImpl4 $this, final float v, final float v2, final float v3, final int v4) {
         return $this.apply$mcFFFII$sp(v, v2, v3, v4);
      }

      default int apply$mcFFFII$sp(final float v, final float v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFFIDD$sp$(final UImpl4 $this, final float v, final float v2, final int v3, final double v4) {
         return $this.apply$mcFFIDD$sp(v, v2, v3, v4);
      }

      default double apply$mcFFIDD$sp(final float v, final float v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFFIDF$sp$(final UImpl4 $this, final float v, final float v2, final int v3, final double v4) {
         return $this.apply$mcFFIDF$sp(v, v2, v3, v4);
      }

      default float apply$mcFFIDF$sp(final float v, final float v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFFIDI$sp$(final UImpl4 $this, final float v, final float v2, final int v3, final double v4) {
         return $this.apply$mcFFIDI$sp(v, v2, v3, v4);
      }

      default int apply$mcFFIDI$sp(final float v, final float v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFFIFD$sp$(final UImpl4 $this, final float v, final float v2, final int v3, final float v4) {
         return $this.apply$mcFFIFD$sp(v, v2, v3, v4);
      }

      default double apply$mcFFIFD$sp(final float v, final float v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFFIFF$sp$(final UImpl4 $this, final float v, final float v2, final int v3, final float v4) {
         return $this.apply$mcFFIFF$sp(v, v2, v3, v4);
      }

      default float apply$mcFFIFF$sp(final float v, final float v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFFIFI$sp$(final UImpl4 $this, final float v, final float v2, final int v3, final float v4) {
         return $this.apply$mcFFIFI$sp(v, v2, v3, v4);
      }

      default int apply$mcFFIFI$sp(final float v, final float v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFFIID$sp$(final UImpl4 $this, final float v, final float v2, final int v3, final int v4) {
         return $this.apply$mcFFIID$sp(v, v2, v3, v4);
      }

      default double apply$mcFFIID$sp(final float v, final float v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFFIIF$sp$(final UImpl4 $this, final float v, final float v2, final int v3, final int v4) {
         return $this.apply$mcFFIIF$sp(v, v2, v3, v4);
      }

      default float apply$mcFFIIF$sp(final float v, final float v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFFIII$sp$(final UImpl4 $this, final float v, final float v2, final int v3, final int v4) {
         return $this.apply$mcFFIII$sp(v, v2, v3, v4);
      }

      default int apply$mcFFIII$sp(final float v, final float v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFIDDD$sp$(final UImpl4 $this, final float v, final int v2, final double v3, final double v4) {
         return $this.apply$mcFIDDD$sp(v, v2, v3, v4);
      }

      default double apply$mcFIDDD$sp(final float v, final int v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFIDDF$sp$(final UImpl4 $this, final float v, final int v2, final double v3, final double v4) {
         return $this.apply$mcFIDDF$sp(v, v2, v3, v4);
      }

      default float apply$mcFIDDF$sp(final float v, final int v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFIDDI$sp$(final UImpl4 $this, final float v, final int v2, final double v3, final double v4) {
         return $this.apply$mcFIDDI$sp(v, v2, v3, v4);
      }

      default int apply$mcFIDDI$sp(final float v, final int v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFIDFD$sp$(final UImpl4 $this, final float v, final int v2, final double v3, final float v4) {
         return $this.apply$mcFIDFD$sp(v, v2, v3, v4);
      }

      default double apply$mcFIDFD$sp(final float v, final int v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFIDFF$sp$(final UImpl4 $this, final float v, final int v2, final double v3, final float v4) {
         return $this.apply$mcFIDFF$sp(v, v2, v3, v4);
      }

      default float apply$mcFIDFF$sp(final float v, final int v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFIDFI$sp$(final UImpl4 $this, final float v, final int v2, final double v3, final float v4) {
         return $this.apply$mcFIDFI$sp(v, v2, v3, v4);
      }

      default int apply$mcFIDFI$sp(final float v, final int v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFIDID$sp$(final UImpl4 $this, final float v, final int v2, final double v3, final int v4) {
         return $this.apply$mcFIDID$sp(v, v2, v3, v4);
      }

      default double apply$mcFIDID$sp(final float v, final int v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFIDIF$sp$(final UImpl4 $this, final float v, final int v2, final double v3, final int v4) {
         return $this.apply$mcFIDIF$sp(v, v2, v3, v4);
      }

      default float apply$mcFIDIF$sp(final float v, final int v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFIDII$sp$(final UImpl4 $this, final float v, final int v2, final double v3, final int v4) {
         return $this.apply$mcFIDII$sp(v, v2, v3, v4);
      }

      default int apply$mcFIDII$sp(final float v, final int v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFIFDD$sp$(final UImpl4 $this, final float v, final int v2, final float v3, final double v4) {
         return $this.apply$mcFIFDD$sp(v, v2, v3, v4);
      }

      default double apply$mcFIFDD$sp(final float v, final int v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFIFDF$sp$(final UImpl4 $this, final float v, final int v2, final float v3, final double v4) {
         return $this.apply$mcFIFDF$sp(v, v2, v3, v4);
      }

      default float apply$mcFIFDF$sp(final float v, final int v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFIFDI$sp$(final UImpl4 $this, final float v, final int v2, final float v3, final double v4) {
         return $this.apply$mcFIFDI$sp(v, v2, v3, v4);
      }

      default int apply$mcFIFDI$sp(final float v, final int v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFIFFD$sp$(final UImpl4 $this, final float v, final int v2, final float v3, final float v4) {
         return $this.apply$mcFIFFD$sp(v, v2, v3, v4);
      }

      default double apply$mcFIFFD$sp(final float v, final int v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFIFFF$sp$(final UImpl4 $this, final float v, final int v2, final float v3, final float v4) {
         return $this.apply$mcFIFFF$sp(v, v2, v3, v4);
      }

      default float apply$mcFIFFF$sp(final float v, final int v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFIFFI$sp$(final UImpl4 $this, final float v, final int v2, final float v3, final float v4) {
         return $this.apply$mcFIFFI$sp(v, v2, v3, v4);
      }

      default int apply$mcFIFFI$sp(final float v, final int v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFIFID$sp$(final UImpl4 $this, final float v, final int v2, final float v3, final int v4) {
         return $this.apply$mcFIFID$sp(v, v2, v3, v4);
      }

      default double apply$mcFIFID$sp(final float v, final int v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFIFIF$sp$(final UImpl4 $this, final float v, final int v2, final float v3, final int v4) {
         return $this.apply$mcFIFIF$sp(v, v2, v3, v4);
      }

      default float apply$mcFIFIF$sp(final float v, final int v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFIFII$sp$(final UImpl4 $this, final float v, final int v2, final float v3, final int v4) {
         return $this.apply$mcFIFII$sp(v, v2, v3, v4);
      }

      default int apply$mcFIFII$sp(final float v, final int v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFIIDD$sp$(final UImpl4 $this, final float v, final int v2, final int v3, final double v4) {
         return $this.apply$mcFIIDD$sp(v, v2, v3, v4);
      }

      default double apply$mcFIIDD$sp(final float v, final int v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFIIDF$sp$(final UImpl4 $this, final float v, final int v2, final int v3, final double v4) {
         return $this.apply$mcFIIDF$sp(v, v2, v3, v4);
      }

      default float apply$mcFIIDF$sp(final float v, final int v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFIIDI$sp$(final UImpl4 $this, final float v, final int v2, final int v3, final double v4) {
         return $this.apply$mcFIIDI$sp(v, v2, v3, v4);
      }

      default int apply$mcFIIDI$sp(final float v, final int v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFIIFD$sp$(final UImpl4 $this, final float v, final int v2, final int v3, final float v4) {
         return $this.apply$mcFIIFD$sp(v, v2, v3, v4);
      }

      default double apply$mcFIIFD$sp(final float v, final int v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFIIFF$sp$(final UImpl4 $this, final float v, final int v2, final int v3, final float v4) {
         return $this.apply$mcFIIFF$sp(v, v2, v3, v4);
      }

      default float apply$mcFIIFF$sp(final float v, final int v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFIIFI$sp$(final UImpl4 $this, final float v, final int v2, final int v3, final float v4) {
         return $this.apply$mcFIIFI$sp(v, v2, v3, v4);
      }

      default int apply$mcFIIFI$sp(final float v, final int v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcFIIID$sp$(final UImpl4 $this, final float v, final int v2, final int v3, final int v4) {
         return $this.apply$mcFIIID$sp(v, v2, v3, v4);
      }

      default double apply$mcFIIID$sp(final float v, final int v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcFIIIF$sp$(final UImpl4 $this, final float v, final int v2, final int v3, final int v4) {
         return $this.apply$mcFIIIF$sp(v, v2, v3, v4);
      }

      default float apply$mcFIIIF$sp(final float v, final int v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcFIIII$sp$(final UImpl4 $this, final float v, final int v2, final int v3, final int v4) {
         return $this.apply$mcFIIII$sp(v, v2, v3, v4);
      }

      default int apply$mcFIIII$sp(final float v, final int v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToFloat(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIDDDD$sp$(final UImpl4 $this, final int v, final double v2, final double v3, final double v4) {
         return $this.apply$mcIDDDD$sp(v, v2, v3, v4);
      }

      default double apply$mcIDDDD$sp(final int v, final double v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIDDDF$sp$(final UImpl4 $this, final int v, final double v2, final double v3, final double v4) {
         return $this.apply$mcIDDDF$sp(v, v2, v3, v4);
      }

      default float apply$mcIDDDF$sp(final int v, final double v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIDDDI$sp$(final UImpl4 $this, final int v, final double v2, final double v3, final double v4) {
         return $this.apply$mcIDDDI$sp(v, v2, v3, v4);
      }

      default int apply$mcIDDDI$sp(final int v, final double v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIDDFD$sp$(final UImpl4 $this, final int v, final double v2, final double v3, final float v4) {
         return $this.apply$mcIDDFD$sp(v, v2, v3, v4);
      }

      default double apply$mcIDDFD$sp(final int v, final double v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIDDFF$sp$(final UImpl4 $this, final int v, final double v2, final double v3, final float v4) {
         return $this.apply$mcIDDFF$sp(v, v2, v3, v4);
      }

      default float apply$mcIDDFF$sp(final int v, final double v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIDDFI$sp$(final UImpl4 $this, final int v, final double v2, final double v3, final float v4) {
         return $this.apply$mcIDDFI$sp(v, v2, v3, v4);
      }

      default int apply$mcIDDFI$sp(final int v, final double v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIDDID$sp$(final UImpl4 $this, final int v, final double v2, final double v3, final int v4) {
         return $this.apply$mcIDDID$sp(v, v2, v3, v4);
      }

      default double apply$mcIDDID$sp(final int v, final double v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIDDIF$sp$(final UImpl4 $this, final int v, final double v2, final double v3, final int v4) {
         return $this.apply$mcIDDIF$sp(v, v2, v3, v4);
      }

      default float apply$mcIDDIF$sp(final int v, final double v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIDDII$sp$(final UImpl4 $this, final int v, final double v2, final double v3, final int v4) {
         return $this.apply$mcIDDII$sp(v, v2, v3, v4);
      }

      default int apply$mcIDDII$sp(final int v, final double v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIDFDD$sp$(final UImpl4 $this, final int v, final double v2, final float v3, final double v4) {
         return $this.apply$mcIDFDD$sp(v, v2, v3, v4);
      }

      default double apply$mcIDFDD$sp(final int v, final double v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIDFDF$sp$(final UImpl4 $this, final int v, final double v2, final float v3, final double v4) {
         return $this.apply$mcIDFDF$sp(v, v2, v3, v4);
      }

      default float apply$mcIDFDF$sp(final int v, final double v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIDFDI$sp$(final UImpl4 $this, final int v, final double v2, final float v3, final double v4) {
         return $this.apply$mcIDFDI$sp(v, v2, v3, v4);
      }

      default int apply$mcIDFDI$sp(final int v, final double v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIDFFD$sp$(final UImpl4 $this, final int v, final double v2, final float v3, final float v4) {
         return $this.apply$mcIDFFD$sp(v, v2, v3, v4);
      }

      default double apply$mcIDFFD$sp(final int v, final double v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIDFFF$sp$(final UImpl4 $this, final int v, final double v2, final float v3, final float v4) {
         return $this.apply$mcIDFFF$sp(v, v2, v3, v4);
      }

      default float apply$mcIDFFF$sp(final int v, final double v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIDFFI$sp$(final UImpl4 $this, final int v, final double v2, final float v3, final float v4) {
         return $this.apply$mcIDFFI$sp(v, v2, v3, v4);
      }

      default int apply$mcIDFFI$sp(final int v, final double v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIDFID$sp$(final UImpl4 $this, final int v, final double v2, final float v3, final int v4) {
         return $this.apply$mcIDFID$sp(v, v2, v3, v4);
      }

      default double apply$mcIDFID$sp(final int v, final double v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIDFIF$sp$(final UImpl4 $this, final int v, final double v2, final float v3, final int v4) {
         return $this.apply$mcIDFIF$sp(v, v2, v3, v4);
      }

      default float apply$mcIDFIF$sp(final int v, final double v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIDFII$sp$(final UImpl4 $this, final int v, final double v2, final float v3, final int v4) {
         return $this.apply$mcIDFII$sp(v, v2, v3, v4);
      }

      default int apply$mcIDFII$sp(final int v, final double v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIDIDD$sp$(final UImpl4 $this, final int v, final double v2, final int v3, final double v4) {
         return $this.apply$mcIDIDD$sp(v, v2, v3, v4);
      }

      default double apply$mcIDIDD$sp(final int v, final double v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIDIDF$sp$(final UImpl4 $this, final int v, final double v2, final int v3, final double v4) {
         return $this.apply$mcIDIDF$sp(v, v2, v3, v4);
      }

      default float apply$mcIDIDF$sp(final int v, final double v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIDIDI$sp$(final UImpl4 $this, final int v, final double v2, final int v3, final double v4) {
         return $this.apply$mcIDIDI$sp(v, v2, v3, v4);
      }

      default int apply$mcIDIDI$sp(final int v, final double v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIDIFD$sp$(final UImpl4 $this, final int v, final double v2, final int v3, final float v4) {
         return $this.apply$mcIDIFD$sp(v, v2, v3, v4);
      }

      default double apply$mcIDIFD$sp(final int v, final double v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIDIFF$sp$(final UImpl4 $this, final int v, final double v2, final int v3, final float v4) {
         return $this.apply$mcIDIFF$sp(v, v2, v3, v4);
      }

      default float apply$mcIDIFF$sp(final int v, final double v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIDIFI$sp$(final UImpl4 $this, final int v, final double v2, final int v3, final float v4) {
         return $this.apply$mcIDIFI$sp(v, v2, v3, v4);
      }

      default int apply$mcIDIFI$sp(final int v, final double v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIDIID$sp$(final UImpl4 $this, final int v, final double v2, final int v3, final int v4) {
         return $this.apply$mcIDIID$sp(v, v2, v3, v4);
      }

      default double apply$mcIDIID$sp(final int v, final double v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIDIIF$sp$(final UImpl4 $this, final int v, final double v2, final int v3, final int v4) {
         return $this.apply$mcIDIIF$sp(v, v2, v3, v4);
      }

      default float apply$mcIDIIF$sp(final int v, final double v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIDIII$sp$(final UImpl4 $this, final int v, final double v2, final int v3, final int v4) {
         return $this.apply$mcIDIII$sp(v, v2, v3, v4);
      }

      default int apply$mcIDIII$sp(final int v, final double v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToDouble(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIFDDD$sp$(final UImpl4 $this, final int v, final float v2, final double v3, final double v4) {
         return $this.apply$mcIFDDD$sp(v, v2, v3, v4);
      }

      default double apply$mcIFDDD$sp(final int v, final float v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIFDDF$sp$(final UImpl4 $this, final int v, final float v2, final double v3, final double v4) {
         return $this.apply$mcIFDDF$sp(v, v2, v3, v4);
      }

      default float apply$mcIFDDF$sp(final int v, final float v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIFDDI$sp$(final UImpl4 $this, final int v, final float v2, final double v3, final double v4) {
         return $this.apply$mcIFDDI$sp(v, v2, v3, v4);
      }

      default int apply$mcIFDDI$sp(final int v, final float v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIFDFD$sp$(final UImpl4 $this, final int v, final float v2, final double v3, final float v4) {
         return $this.apply$mcIFDFD$sp(v, v2, v3, v4);
      }

      default double apply$mcIFDFD$sp(final int v, final float v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIFDFF$sp$(final UImpl4 $this, final int v, final float v2, final double v3, final float v4) {
         return $this.apply$mcIFDFF$sp(v, v2, v3, v4);
      }

      default float apply$mcIFDFF$sp(final int v, final float v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIFDFI$sp$(final UImpl4 $this, final int v, final float v2, final double v3, final float v4) {
         return $this.apply$mcIFDFI$sp(v, v2, v3, v4);
      }

      default int apply$mcIFDFI$sp(final int v, final float v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIFDID$sp$(final UImpl4 $this, final int v, final float v2, final double v3, final int v4) {
         return $this.apply$mcIFDID$sp(v, v2, v3, v4);
      }

      default double apply$mcIFDID$sp(final int v, final float v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIFDIF$sp$(final UImpl4 $this, final int v, final float v2, final double v3, final int v4) {
         return $this.apply$mcIFDIF$sp(v, v2, v3, v4);
      }

      default float apply$mcIFDIF$sp(final int v, final float v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIFDII$sp$(final UImpl4 $this, final int v, final float v2, final double v3, final int v4) {
         return $this.apply$mcIFDII$sp(v, v2, v3, v4);
      }

      default int apply$mcIFDII$sp(final int v, final float v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIFFDD$sp$(final UImpl4 $this, final int v, final float v2, final float v3, final double v4) {
         return $this.apply$mcIFFDD$sp(v, v2, v3, v4);
      }

      default double apply$mcIFFDD$sp(final int v, final float v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIFFDF$sp$(final UImpl4 $this, final int v, final float v2, final float v3, final double v4) {
         return $this.apply$mcIFFDF$sp(v, v2, v3, v4);
      }

      default float apply$mcIFFDF$sp(final int v, final float v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIFFDI$sp$(final UImpl4 $this, final int v, final float v2, final float v3, final double v4) {
         return $this.apply$mcIFFDI$sp(v, v2, v3, v4);
      }

      default int apply$mcIFFDI$sp(final int v, final float v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIFFFD$sp$(final UImpl4 $this, final int v, final float v2, final float v3, final float v4) {
         return $this.apply$mcIFFFD$sp(v, v2, v3, v4);
      }

      default double apply$mcIFFFD$sp(final int v, final float v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIFFFF$sp$(final UImpl4 $this, final int v, final float v2, final float v3, final float v4) {
         return $this.apply$mcIFFFF$sp(v, v2, v3, v4);
      }

      default float apply$mcIFFFF$sp(final int v, final float v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIFFFI$sp$(final UImpl4 $this, final int v, final float v2, final float v3, final float v4) {
         return $this.apply$mcIFFFI$sp(v, v2, v3, v4);
      }

      default int apply$mcIFFFI$sp(final int v, final float v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIFFID$sp$(final UImpl4 $this, final int v, final float v2, final float v3, final int v4) {
         return $this.apply$mcIFFID$sp(v, v2, v3, v4);
      }

      default double apply$mcIFFID$sp(final int v, final float v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIFFIF$sp$(final UImpl4 $this, final int v, final float v2, final float v3, final int v4) {
         return $this.apply$mcIFFIF$sp(v, v2, v3, v4);
      }

      default float apply$mcIFFIF$sp(final int v, final float v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIFFII$sp$(final UImpl4 $this, final int v, final float v2, final float v3, final int v4) {
         return $this.apply$mcIFFII$sp(v, v2, v3, v4);
      }

      default int apply$mcIFFII$sp(final int v, final float v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIFIDD$sp$(final UImpl4 $this, final int v, final float v2, final int v3, final double v4) {
         return $this.apply$mcIFIDD$sp(v, v2, v3, v4);
      }

      default double apply$mcIFIDD$sp(final int v, final float v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIFIDF$sp$(final UImpl4 $this, final int v, final float v2, final int v3, final double v4) {
         return $this.apply$mcIFIDF$sp(v, v2, v3, v4);
      }

      default float apply$mcIFIDF$sp(final int v, final float v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIFIDI$sp$(final UImpl4 $this, final int v, final float v2, final int v3, final double v4) {
         return $this.apply$mcIFIDI$sp(v, v2, v3, v4);
      }

      default int apply$mcIFIDI$sp(final int v, final float v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIFIFD$sp$(final UImpl4 $this, final int v, final float v2, final int v3, final float v4) {
         return $this.apply$mcIFIFD$sp(v, v2, v3, v4);
      }

      default double apply$mcIFIFD$sp(final int v, final float v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIFIFF$sp$(final UImpl4 $this, final int v, final float v2, final int v3, final float v4) {
         return $this.apply$mcIFIFF$sp(v, v2, v3, v4);
      }

      default float apply$mcIFIFF$sp(final int v, final float v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIFIFI$sp$(final UImpl4 $this, final int v, final float v2, final int v3, final float v4) {
         return $this.apply$mcIFIFI$sp(v, v2, v3, v4);
      }

      default int apply$mcIFIFI$sp(final int v, final float v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIFIID$sp$(final UImpl4 $this, final int v, final float v2, final int v3, final int v4) {
         return $this.apply$mcIFIID$sp(v, v2, v3, v4);
      }

      default double apply$mcIFIID$sp(final int v, final float v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIFIIF$sp$(final UImpl4 $this, final int v, final float v2, final int v3, final int v4) {
         return $this.apply$mcIFIIF$sp(v, v2, v3, v4);
      }

      default float apply$mcIFIIF$sp(final int v, final float v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIFIII$sp$(final UImpl4 $this, final int v, final float v2, final int v3, final int v4) {
         return $this.apply$mcIFIII$sp(v, v2, v3, v4);
      }

      default int apply$mcIFIII$sp(final int v, final float v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToFloat(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIIDDD$sp$(final UImpl4 $this, final int v, final int v2, final double v3, final double v4) {
         return $this.apply$mcIIDDD$sp(v, v2, v3, v4);
      }

      default double apply$mcIIDDD$sp(final int v, final int v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIIDDF$sp$(final UImpl4 $this, final int v, final int v2, final double v3, final double v4) {
         return $this.apply$mcIIDDF$sp(v, v2, v3, v4);
      }

      default float apply$mcIIDDF$sp(final int v, final int v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIIDDI$sp$(final UImpl4 $this, final int v, final int v2, final double v3, final double v4) {
         return $this.apply$mcIIDDI$sp(v, v2, v3, v4);
      }

      default int apply$mcIIDDI$sp(final int v, final int v2, final double v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIIDFD$sp$(final UImpl4 $this, final int v, final int v2, final double v3, final float v4) {
         return $this.apply$mcIIDFD$sp(v, v2, v3, v4);
      }

      default double apply$mcIIDFD$sp(final int v, final int v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIIDFF$sp$(final UImpl4 $this, final int v, final int v2, final double v3, final float v4) {
         return $this.apply$mcIIDFF$sp(v, v2, v3, v4);
      }

      default float apply$mcIIDFF$sp(final int v, final int v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIIDFI$sp$(final UImpl4 $this, final int v, final int v2, final double v3, final float v4) {
         return $this.apply$mcIIDFI$sp(v, v2, v3, v4);
      }

      default int apply$mcIIDFI$sp(final int v, final int v2, final double v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIIDID$sp$(final UImpl4 $this, final int v, final int v2, final double v3, final int v4) {
         return $this.apply$mcIIDID$sp(v, v2, v3, v4);
      }

      default double apply$mcIIDID$sp(final int v, final int v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIIDIF$sp$(final UImpl4 $this, final int v, final int v2, final double v3, final int v4) {
         return $this.apply$mcIIDIF$sp(v, v2, v3, v4);
      }

      default float apply$mcIIDIF$sp(final int v, final int v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIIDII$sp$(final UImpl4 $this, final int v, final int v2, final double v3, final int v4) {
         return $this.apply$mcIIDII$sp(v, v2, v3, v4);
      }

      default int apply$mcIIDII$sp(final int v, final int v2, final double v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToDouble(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIIFDD$sp$(final UImpl4 $this, final int v, final int v2, final float v3, final double v4) {
         return $this.apply$mcIIFDD$sp(v, v2, v3, v4);
      }

      default double apply$mcIIFDD$sp(final int v, final int v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIIFDF$sp$(final UImpl4 $this, final int v, final int v2, final float v3, final double v4) {
         return $this.apply$mcIIFDF$sp(v, v2, v3, v4);
      }

      default float apply$mcIIFDF$sp(final int v, final int v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIIFDI$sp$(final UImpl4 $this, final int v, final int v2, final float v3, final double v4) {
         return $this.apply$mcIIFDI$sp(v, v2, v3, v4);
      }

      default int apply$mcIIFDI$sp(final int v, final int v2, final float v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIIFFD$sp$(final UImpl4 $this, final int v, final int v2, final float v3, final float v4) {
         return $this.apply$mcIIFFD$sp(v, v2, v3, v4);
      }

      default double apply$mcIIFFD$sp(final int v, final int v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIIFFF$sp$(final UImpl4 $this, final int v, final int v2, final float v3, final float v4) {
         return $this.apply$mcIIFFF$sp(v, v2, v3, v4);
      }

      default float apply$mcIIFFF$sp(final int v, final int v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIIFFI$sp$(final UImpl4 $this, final int v, final int v2, final float v3, final float v4) {
         return $this.apply$mcIIFFI$sp(v, v2, v3, v4);
      }

      default int apply$mcIIFFI$sp(final int v, final int v2, final float v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIIFID$sp$(final UImpl4 $this, final int v, final int v2, final float v3, final int v4) {
         return $this.apply$mcIIFID$sp(v, v2, v3, v4);
      }

      default double apply$mcIIFID$sp(final int v, final int v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIIFIF$sp$(final UImpl4 $this, final int v, final int v2, final float v3, final int v4) {
         return $this.apply$mcIIFIF$sp(v, v2, v3, v4);
      }

      default float apply$mcIIFIF$sp(final int v, final int v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIIFII$sp$(final UImpl4 $this, final int v, final int v2, final float v3, final int v4) {
         return $this.apply$mcIIFII$sp(v, v2, v3, v4);
      }

      default int apply$mcIIFII$sp(final int v, final int v2, final float v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToFloat(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIIIDD$sp$(final UImpl4 $this, final int v, final int v2, final int v3, final double v4) {
         return $this.apply$mcIIIDD$sp(v, v2, v3, v4);
      }

      default double apply$mcIIIDD$sp(final int v, final int v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIIIDF$sp$(final UImpl4 $this, final int v, final int v2, final int v3, final double v4) {
         return $this.apply$mcIIIDF$sp(v, v2, v3, v4);
      }

      default float apply$mcIIIDF$sp(final int v, final int v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIIIDI$sp$(final UImpl4 $this, final int v, final int v2, final int v3, final double v4) {
         return $this.apply$mcIIIDI$sp(v, v2, v3, v4);
      }

      default int apply$mcIIIDI$sp(final int v, final int v2, final int v3, final double v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToDouble(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIIIFD$sp$(final UImpl4 $this, final int v, final int v2, final int v3, final float v4) {
         return $this.apply$mcIIIFD$sp(v, v2, v3, v4);
      }

      default double apply$mcIIIFD$sp(final int v, final int v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIIIFF$sp$(final UImpl4 $this, final int v, final int v2, final int v3, final float v4) {
         return $this.apply$mcIIIFF$sp(v, v2, v3, v4);
      }

      default float apply$mcIIIFF$sp(final int v, final int v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIIIFI$sp$(final UImpl4 $this, final int v, final int v2, final int v3, final float v4) {
         return $this.apply$mcIIIFI$sp(v, v2, v3, v4);
      }

      default int apply$mcIIIFI$sp(final int v, final int v2, final int v3, final float v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToFloat(v4)));
      }

      // $FF: synthetic method
      static double apply$mcIIIID$sp$(final UImpl4 $this, final int v, final int v2, final int v3, final int v4) {
         return $this.apply$mcIIIID$sp(v, v2, v3, v4);
      }

      default double apply$mcIIIID$sp(final int v, final int v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static float apply$mcIIIIF$sp$(final UImpl4 $this, final int v, final int v2, final int v3, final int v4) {
         return $this.apply$mcIIIIF$sp(v, v2, v3, v4);
      }

      default float apply$mcIIIIF$sp(final int v, final int v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }

      // $FF: synthetic method
      static int apply$mcIIIII$sp$(final UImpl4 $this, final int v, final int v2, final int v3, final int v4) {
         return $this.apply$mcIIIII$sp(v, v2, v3, v4);
      }

      default int apply$mcIIIII$sp(final int v, final int v2, final int v3, final int v4) {
         return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(v), BoxesRunTime.boxToInteger(v2), BoxesRunTime.boxToInteger(v3), BoxesRunTime.boxToInteger(v4)));
      }
   }

   public interface InPlaceImpl2 extends Serializable {
      void apply(final Object v, final Object v2);

      // $FF: synthetic method
      static void apply$mcD$sp$(final InPlaceImpl2 $this, final Object v, final double v2) {
         $this.apply$mcD$sp(v, v2);
      }

      default void apply$mcD$sp(final Object v, final double v2) {
         this.apply(v, BoxesRunTime.boxToDouble(v2));
      }

      // $FF: synthetic method
      static void apply$mcF$sp$(final InPlaceImpl2 $this, final Object v, final float v2) {
         $this.apply$mcF$sp(v, v2);
      }

      default void apply$mcF$sp(final Object v, final float v2) {
         this.apply(v, BoxesRunTime.boxToFloat(v2));
      }

      // $FF: synthetic method
      static void apply$mcI$sp$(final InPlaceImpl2 $this, final Object v, final int v2) {
         $this.apply$mcI$sp(v, v2);
      }

      default void apply$mcI$sp(final Object v, final int v2) {
         this.apply(v, BoxesRunTime.boxToInteger(v2));
      }
   }

   public interface SinkImpl2 extends Serializable {
      void apply(final Object sink, final Object v, final Object v2);

      // $FF: synthetic method
      static void apply$mcD$sp$(final SinkImpl2 $this, final Object sink, final Object v, final double v2) {
         $this.apply$mcD$sp(sink, v, v2);
      }

      default void apply$mcD$sp(final Object sink, final Object v, final double v2) {
         this.apply(sink, v, BoxesRunTime.boxToDouble(v2));
      }

      // $FF: synthetic method
      static void apply$mcF$sp$(final SinkImpl2 $this, final Object sink, final Object v, final float v2) {
         $this.apply$mcF$sp(sink, v, v2);
      }

      default void apply$mcF$sp(final Object sink, final Object v, final float v2) {
         this.apply(sink, v, BoxesRunTime.boxToFloat(v2));
      }

      // $FF: synthetic method
      static void apply$mcI$sp$(final SinkImpl2 $this, final Object sink, final Object v, final int v2) {
         $this.apply$mcI$sp(sink, v, v2);
      }

      default void apply$mcI$sp(final Object sink, final Object v, final int v2) {
         this.apply(sink, v, BoxesRunTime.boxToInteger(v2));
      }
   }

   public static final class WithSinkHelp {
      private final Object __s;

      public Object __s() {
         return this.__s;
      }

      public Object apply(final Object v, final SinkImpl impl) {
         return UFunc.WithSinkHelp$.MODULE$.apply$extension(this.__s(), v, impl);
      }

      public Object apply(final Object v, final Object v2, final SinkImpl2 impl) {
         return UFunc.WithSinkHelp$.MODULE$.apply$extension(this.__s(), v, v2, impl);
      }

      public Object apply(final Object v, final Object v2, final Object v3, final SinkImpl3 impl) {
         return UFunc.WithSinkHelp$.MODULE$.apply$extension(this.__s(), v, v2, v3, impl);
      }

      public int hashCode() {
         return UFunc.WithSinkHelp$.MODULE$.hashCode$extension(this.__s());
      }

      public boolean equals(final Object x$1) {
         return UFunc.WithSinkHelp$.MODULE$.equals$extension(this.__s(), x$1);
      }

      public WithSinkHelp(final Object __s) {
         this.__s = __s;
      }
   }

   public static class WithSinkHelp$ {
      public static final WithSinkHelp$ MODULE$ = new WithSinkHelp$();

      public final Object apply$extension(final Object $this, final Object v, final SinkImpl impl) {
         impl.apply($this, v);
         return $this;
      }

      public final Object apply$extension(final Object $this, final Object v, final Object v2, final SinkImpl2 impl) {
         impl.apply($this, v, v2);
         return $this;
      }

      public final Object apply$extension(final Object $this, final Object v, final Object v2, final Object v3, final SinkImpl3 impl) {
         impl.apply($this, v, v2, v3);
         return $this;
      }

      public final int hashCode$extension(final Object $this) {
         return $this.hashCode();
      }

      public final boolean equals$extension(final Object $this, final Object x$1) {
         boolean var3;
         if (x$1 instanceof WithSinkHelp) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var10000;
         if (var3) {
            Object var5 = x$1 == null ? null : ((WithSinkHelp)x$1).__s();
            if (BoxesRunTime.equals($this, var5)) {
               var10000 = true;
               return var10000;
            }
         }

         var10000 = false;
         return var10000;
      }
   }

   public interface InPlaceImpl extends Serializable {
      void apply(final Object v);
   }

   public interface InPlaceImpl3 extends Serializable {
      void apply(final Object v, final Object v2, final Object v3);
   }

   public interface SinkImpl extends Serializable {
      void apply(final Object sink, final Object v);
   }

   public interface SinkImpl3 extends Serializable {
      void apply(final Object sink, final Object v, final Object v2, final Object v3);
   }
}
