package scala.xml.parsing;

import scala.xml.dtd.ExternalID;
import scala.xml.dtd.NoExternalID$;
import scala.xml.dtd.PublicID;
import scala.xml.dtd.SystemID;

public final class DtdBuilder$ {
   public static final DtdBuilder$ MODULE$ = new DtdBuilder$();

   public DtdBuilder apply(final String name, final String publicId, final String systemId) {
      return new DtdBuilder(name, this.scala$xml$parsing$DtdBuilder$$mkExternalID(publicId, systemId));
   }

   public ExternalID scala$xml$parsing$DtdBuilder$$mkExternalID(final String publicId, final String systemId) {
      if (publicId != null) {
         return new PublicID(publicId, systemId);
      } else {
         return (ExternalID)(systemId != null ? new SystemID(systemId) : NoExternalID$.MODULE$);
      }
   }

   private DtdBuilder$() {
   }
}
