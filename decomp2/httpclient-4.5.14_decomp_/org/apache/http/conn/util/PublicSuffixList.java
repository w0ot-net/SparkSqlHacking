package org.apache.http.conn.util;

import java.util.Collections;
import java.util.List;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;

@Contract(
   threading = ThreadingBehavior.IMMUTABLE
)
public final class PublicSuffixList {
   private final DomainType type;
   private final List rules;
   private final List exceptions;

   public PublicSuffixList(DomainType type, List rules, List exceptions) {
      this.type = (DomainType)Args.notNull(type, "Domain type");
      this.rules = Collections.unmodifiableList((List)Args.notNull(rules, "Domain suffix rules"));
      this.exceptions = Collections.unmodifiableList(exceptions != null ? exceptions : Collections.emptyList());
   }

   public PublicSuffixList(List rules, List exceptions) {
      this(DomainType.UNKNOWN, rules, exceptions);
   }

   public DomainType getType() {
      return this.type;
   }

   public List getRules() {
      return this.rules;
   }

   public List getExceptions() {
      return this.exceptions;
   }
}
