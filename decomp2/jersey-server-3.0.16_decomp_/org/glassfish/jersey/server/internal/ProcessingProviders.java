package org.glassfish.jersey.server.internal;

import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.DynamicFeature;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.WriterInterceptor;
import java.lang.annotation.Annotation;
import java.util.List;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedProvider;

public class ProcessingProviders {
   private final MultivaluedMap nameBoundRequestFilters;
   private final MultivaluedMap nameBoundResponseFilters;
   private final MultivaluedMap nameBoundReaderInterceptors;
   private final MultivaluedMap nameBoundWriterInterceptors;
   private final MultivaluedMap nameBoundRequestFiltersInverse;
   private final MultivaluedMap nameBoundResponseFiltersInverse;
   private final MultivaluedMap nameBoundReaderInterceptorsInverse;
   private final MultivaluedMap nameBoundWriterInterceptorsInverse;
   private final Iterable globalRequestFilters;
   private final Iterable sortedGlobalRequestFilters;
   private final List preMatchFilters;
   private final Iterable globalResponseFilters;
   private final Iterable sortedGlobalResponseFilters;
   private final Iterable globalReaderInterceptors;
   private final Iterable sortedGlobalReaderInterceptors;
   private final Iterable globalWriterInterceptors;
   private final Iterable sortedGlobalWriterInterceptors;
   private final Iterable dynamicFeatures;

   public ProcessingProviders(MultivaluedMap nameBoundRequestFilters, MultivaluedMap nameBoundRequestFiltersInverse, MultivaluedMap nameBoundResponseFilters, MultivaluedMap nameBoundResponseFiltersInverse, MultivaluedMap nameBoundReaderInterceptors, MultivaluedMap nameBoundReaderInterceptorsInverse, MultivaluedMap nameBoundWriterInterceptors, MultivaluedMap nameBoundWriterInterceptorsInverse, Iterable globalRequestFilters, List preMatchFilters, Iterable globalResponseFilters, Iterable globalReaderInterceptors, Iterable globalWriterInterceptors, Iterable dynamicFeatures) {
      this.nameBoundReaderInterceptors = nameBoundReaderInterceptors;
      this.nameBoundReaderInterceptorsInverse = nameBoundReaderInterceptorsInverse;
      this.nameBoundRequestFilters = nameBoundRequestFilters;
      this.nameBoundRequestFiltersInverse = nameBoundRequestFiltersInverse;
      this.nameBoundResponseFilters = nameBoundResponseFilters;
      this.nameBoundResponseFiltersInverse = nameBoundResponseFiltersInverse;
      this.nameBoundWriterInterceptors = nameBoundWriterInterceptors;
      this.nameBoundWriterInterceptorsInverse = nameBoundWriterInterceptorsInverse;
      this.globalRequestFilters = globalRequestFilters;
      this.preMatchFilters = preMatchFilters;
      this.globalResponseFilters = globalResponseFilters;
      this.globalReaderInterceptors = globalReaderInterceptors;
      this.globalWriterInterceptors = globalWriterInterceptors;
      this.dynamicFeatures = dynamicFeatures;
      this.sortedGlobalReaderInterceptors = Providers.sortRankedProviders(new RankedComparator(), globalReaderInterceptors);
      this.sortedGlobalWriterInterceptors = Providers.sortRankedProviders(new RankedComparator(), globalWriterInterceptors);
      this.sortedGlobalRequestFilters = Providers.sortRankedProviders(new RankedComparator(), globalRequestFilters);
      this.sortedGlobalResponseFilters = Providers.sortRankedProviders(new RankedComparator(), globalResponseFilters);
   }

   public MultivaluedMap getNameBoundRequestFilters() {
      return this.nameBoundRequestFilters;
   }

   public MultivaluedMap getNameBoundRequestFiltersInverse() {
      return this.nameBoundRequestFiltersInverse;
   }

   public MultivaluedMap getNameBoundResponseFilters() {
      return this.nameBoundResponseFilters;
   }

   public MultivaluedMap getNameBoundResponseFiltersInverse() {
      return this.nameBoundResponseFiltersInverse;
   }

   public MultivaluedMap getNameBoundReaderInterceptors() {
      return this.nameBoundReaderInterceptors;
   }

   public MultivaluedMap getNameBoundReaderInterceptorsInverse() {
      return this.nameBoundReaderInterceptorsInverse;
   }

   public MultivaluedMap getNameBoundWriterInterceptors() {
      return this.nameBoundWriterInterceptors;
   }

   public MultivaluedMap getNameBoundWriterInterceptorsInverse() {
      return this.nameBoundWriterInterceptorsInverse;
   }

   public Iterable getGlobalRequestFilters() {
      return this.globalRequestFilters;
   }

   public Iterable getGlobalResponseFilters() {
      return this.globalResponseFilters;
   }

   public Iterable getSortedGlobalRequestFilters() {
      return this.sortedGlobalRequestFilters;
   }

   public Iterable getSortedGlobalResponseFilters() {
      return this.sortedGlobalResponseFilters;
   }

   public Iterable getGlobalReaderInterceptors() {
      return this.globalReaderInterceptors;
   }

   public Iterable getGlobalWriterInterceptors() {
      return this.globalWriterInterceptors;
   }

   public Iterable getSortedGlobalReaderInterceptors() {
      return this.sortedGlobalReaderInterceptors;
   }

   public Iterable getSortedGlobalWriterInterceptors() {
      return this.sortedGlobalWriterInterceptors;
   }

   public Iterable getDynamicFeatures() {
      return this.dynamicFeatures;
   }

   public List getPreMatchFilters() {
      return this.preMatchFilters;
   }
}
