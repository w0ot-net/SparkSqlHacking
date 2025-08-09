package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Variant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import org.glassfish.jersey.internal.util.collection.Ref;

public final class VariantSelector {
   private static final DimensionChecker MEDIA_TYPE_DC = new DimensionChecker() {
      public MediaType getDimension(VariantHolder v) {
         return v.v.getMediaType();
      }

      public boolean isCompatible(AcceptableMediaType t, MediaType u) {
         return t.isCompatible(u);
      }

      public int getQualitySource(VariantHolder v, MediaType u) {
         return v.mediaTypeQs;
      }

      public String getVaryHeaderValue() {
         return "Accept";
      }
   };
   private static final DimensionChecker LANGUAGE_TAG_DC = new DimensionChecker() {
      public Locale getDimension(VariantHolder v) {
         return v.v.getLanguage();
      }

      public boolean isCompatible(AcceptableLanguageTag t, Locale u) {
         return t.isCompatible(u);
      }

      public int getQualitySource(VariantHolder qsv, Locale u) {
         return 0;
      }

      public String getVaryHeaderValue() {
         return "Accept-Language";
      }
   };
   private static final DimensionChecker CHARSET_DC = new DimensionChecker() {
      public String getDimension(VariantHolder v) {
         MediaType m = v.v.getMediaType();
         return m != null ? (String)m.getParameters().get("charset") : null;
      }

      public boolean isCompatible(AcceptableToken t, String u) {
         return t.isCompatible(u);
      }

      public int getQualitySource(VariantHolder qsv, String u) {
         return 0;
      }

      public String getVaryHeaderValue() {
         return "Accept-Charset";
      }
   };
   private static final DimensionChecker ENCODING_DC = new DimensionChecker() {
      public String getDimension(VariantHolder v) {
         return v.v.getEncoding();
      }

      public boolean isCompatible(AcceptableToken t, String u) {
         return t.isCompatible(u);
      }

      public int getQualitySource(VariantHolder qsv, String u) {
         return 0;
      }

      public String getVaryHeaderValue() {
         return "Accept-Encoding";
      }
   };

   private VariantSelector() {
   }

   private static LinkedList selectVariants(List variantHolders, List acceptableValues, DimensionChecker dimensionChecker, Set vary) {
      int cq = 0;
      int cqs = 0;
      LinkedList<VariantHolder> selected = new LinkedList();

      for(Qualified a : acceptableValues) {
         int q = a.getQuality();
         Iterator<VariantHolder> iv = variantHolders.iterator();

         while(iv.hasNext()) {
            VariantHolder v = (VariantHolder)iv.next();
            U d = (U)dimensionChecker.getDimension(v);
            if (d != null) {
               vary.add(dimensionChecker.getVaryHeaderValue());
               int qs = dimensionChecker.getQualitySource(v, d);
               if (qs >= cqs && dimensionChecker.isCompatible(a, d)) {
                  if (qs > cqs) {
                     cqs = qs;
                     cq = q;
                     selected.clear();
                     selected.add(v);
                  } else if (q > cq) {
                     cq = q;
                     selected.addFirst(v);
                  } else if (q == cq) {
                     selected.add(v);
                  }

                  iv.remove();
               }
            }
         }
      }

      for(VariantHolder v : variantHolders) {
         if (dimensionChecker.getDimension(v) == null) {
            selected.add(v);
         }
      }

      return selected;
   }

   private static LinkedList getVariantHolderList(List variants) {
      LinkedList<VariantHolder> l = new LinkedList();

      for(Variant v : variants) {
         MediaType mt = v.getMediaType();
         if (mt != null) {
            if (!(mt instanceof QualitySourceMediaType) && !mt.getParameters().containsKey("qs")) {
               l.add(new VariantHolder(v));
            } else {
               int qs = QualitySourceMediaType.getQualitySource(mt);
               l.add(new VariantHolder(v, qs));
            }
         } else {
            l.add(new VariantHolder(v));
         }
      }

      return l;
   }

   public static Variant selectVariant(InboundMessageContext context, List variants, Ref varyHeaderValue) {
      List<Variant> selectedVariants = selectVariants(context, variants, varyHeaderValue);
      return selectedVariants.isEmpty() ? null : (Variant)selectedVariants.get(0);
   }

   public static List selectVariants(InboundMessageContext context, List variants, Ref varyHeaderValue) {
      LinkedList<VariantHolder> vhs = getVariantHolderList(variants);
      Set<String> vary = new HashSet();
      vhs = selectVariants(vhs, context.getQualifiedAcceptableMediaTypes(), MEDIA_TYPE_DC, vary);
      vhs = selectVariants(vhs, context.getQualifiedAcceptableLanguages(), LANGUAGE_TAG_DC, vary);
      vhs = selectVariants(vhs, context.getQualifiedAcceptCharset(), CHARSET_DC, vary);
      vhs = selectVariants(vhs, context.getQualifiedAcceptEncoding(), ENCODING_DC, vary);
      if (vhs.isEmpty()) {
         return Collections.emptyList();
      } else {
         StringBuilder varyHeader = new StringBuilder();

         for(String v : vary) {
            if (varyHeader.length() > 0) {
               varyHeader.append(',');
            }

            varyHeader.append(v);
         }

         String varyValue = varyHeader.toString();
         if (!varyValue.isEmpty()) {
            varyHeaderValue.set(varyValue);
         }

         return (List)vhs.stream().map((variantHolder) -> variantHolder.v).collect(Collectors.toList());
      }
   }

   private static class VariantHolder {
      private final Variant v;
      private final int mediaTypeQs;

      VariantHolder(Variant v) {
         this(v, 1000);
      }

      VariantHolder(Variant v, int mediaTypeQs) {
         this.v = v;
         this.mediaTypeQs = mediaTypeQs;
      }
   }

   private interface DimensionChecker {
      Object getDimension(VariantHolder var1);

      int getQualitySource(VariantHolder var1, Object var2);

      boolean isCompatible(Object var1, Object var2);

      String getVaryHeaderValue();
   }
}
