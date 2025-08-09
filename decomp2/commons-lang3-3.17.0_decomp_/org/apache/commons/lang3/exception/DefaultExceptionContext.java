package org.apache.commons.lang3.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class DefaultExceptionContext implements ExceptionContext, Serializable {
   private static final long serialVersionUID = 20110706L;
   private final List contextValues = new ArrayList();

   public DefaultExceptionContext addContextValue(String label, Object value) {
      this.contextValues.add(new ImmutablePair(label, value));
      return this;
   }

   public List getContextEntries() {
      return this.contextValues;
   }

   public Set getContextLabels() {
      return (Set)this.stream().map(Pair::getKey).collect(Collectors.toSet());
   }

   public List getContextValues(String label) {
      return (List)this.stream().filter((pair) -> StringUtils.equals(label, (CharSequence)pair.getKey())).map(Pair::getValue).collect(Collectors.toList());
   }

   public Object getFirstContextValue(String label) {
      return this.stream().filter((pair) -> StringUtils.equals(label, (CharSequence)pair.getKey())).findFirst().map(Pair::getValue).orElse((Object)null);
   }

   public String getFormattedExceptionMessage(String baseMessage) {
      StringBuilder buffer = new StringBuilder(256);
      if (baseMessage != null) {
         buffer.append(baseMessage);
      }

      if (!this.contextValues.isEmpty()) {
         if (buffer.length() > 0) {
            buffer.append('\n');
         }

         buffer.append("Exception Context:\n");
         int i = 0;

         for(Pair pair : this.contextValues) {
            buffer.append("\t[");
            ++i;
            buffer.append(i);
            buffer.append(':');
            buffer.append((String)pair.getKey());
            buffer.append("=");
            Object value = pair.getValue();

            try {
               buffer.append(Objects.toString(value));
            } catch (Exception e) {
               buffer.append("Exception thrown on toString(): ");
               buffer.append(ExceptionUtils.getStackTrace(e));
            }

            buffer.append("]\n");
         }

         buffer.append("---------------------------------");
      }

      return buffer.toString();
   }

   public DefaultExceptionContext setContextValue(String label, Object value) {
      this.contextValues.removeIf((p) -> StringUtils.equals(label, (CharSequence)p.getKey()));
      this.addContextValue(label, value);
      return this;
   }

   private Stream stream() {
      return this.contextValues.stream();
   }
}
