package org.apache.ivy.osgi.obr.xml;

import java.text.ParseException;
import org.apache.ivy.osgi.core.BundleInfo;
import org.apache.ivy.osgi.core.BundleRequirement;
import org.apache.ivy.osgi.filter.AndFilter;
import org.apache.ivy.osgi.filter.CompareFilter;
import org.apache.ivy.osgi.filter.NotFilter;
import org.apache.ivy.osgi.filter.OSGiFilter;
import org.apache.ivy.osgi.util.Version;
import org.apache.ivy.osgi.util.VersionRange;

public class RequirementAdapter {
   private Version startVersion = null;
   private boolean startExclusive = false;
   private Version endVersion = null;
   private boolean endExclusive = false;
   private String type = null;
   private String name = null;

   public static void adapt(BundleInfo info, Requirement requirement) throws UnsupportedFilterException, ParseException {
      RequirementAdapter adapter = new RequirementAdapter();
      adapter.extractFilter(requirement.getFilter());
      adapter.adapt(info, requirement.isOptional());
   }

   private void extractFilter(OSGiFilter filter) throws UnsupportedFilterException {
      if (filter instanceof AndFilter) {
         AndFilter andFilter = (AndFilter)filter;

         for(OSGiFilter subFilter : andFilter.getSubFilters()) {
            this.extractFilter(subFilter);
         }
      } else if (filter instanceof CompareFilter) {
         CompareFilter compareFilter = (CompareFilter)filter;
         this.parseCompareFilter(compareFilter, false);
      } else {
         if (!(filter instanceof NotFilter)) {
            throw new UnsupportedFilterException("Unsupported filter: " + filter.getClass().getName());
         }

         NotFilter notFilter = (NotFilter)filter;
         if (notFilter.getSubFilter() instanceof CompareFilter) {
            CompareFilter compareFilter = (CompareFilter)notFilter.getSubFilter();
            this.parseCompareFilter(compareFilter, true);
         }
      }

   }

   private void adapt(BundleInfo info, boolean optional) throws ParseException {
      VersionRange range = this.getVersionRange();
      String resolution = optional ? "optional" : null;
      if (this.type == null) {
         throw new ParseException("No requirement actually specified", 0);
      } else {
         BundleRequirement requirement = new BundleRequirement(this.type, this.name, range, resolution);
         info.addRequirement(requirement);
         if ("ee".equals(this.type)) {
            info.addExecutionEnvironment(this.name);
         }

      }
   }

   private VersionRange getVersionRange() {
      VersionRange range = null;
      if (this.startVersion != null || this.endVersion != null) {
         range = new VersionRange(this.startExclusive, this.startVersion, this.endExclusive, this.endVersion);
      }

      return range;
   }

   private void parseCompareFilter(CompareFilter compareFilter, boolean not) throws UnsupportedFilterException {
      String att = compareFilter.getLeftValue();
      if ("symbolicname".equals(att)) {
         att = "bundle";
      }

      switch (att) {
         case "bundle":
         case "ee":
         case "package":
         case "service":
            if (not) {
               throw new UnsupportedFilterException("Not filter on requirement comparison is not supported");
            } else if (compareFilter.getOperator() != CompareFilter.Operator.EQUALS) {
               throw new UnsupportedFilterException("Filtering is only supported with the operator '='");
            } else {
               if (this.type != null) {
                  throw new UnsupportedFilterException("Multiple requirement type are not supported");
               }

               this.type = att;
               this.name = compareFilter.getRightValue();
               return;
            }
         case "version":
            Version version = new Version(compareFilter.getRightValue());
            CompareFilter.Operator operator = compareFilter.getOperator();
            if (not) {
               switch (operator) {
                  case EQUALS:
                     throw new UnsupportedFilterException("Not filter on equals comparison is not supported");
                  case GREATER_OR_EQUAL:
                     operator = CompareFilter.Operator.LOWER_THAN;
                     break;
                  case GREATER_THAN:
                     operator = CompareFilter.Operator.LOWER_OR_EQUAL;
                     break;
                  case LOWER_OR_EQUAL:
                     operator = CompareFilter.Operator.GREATER_THAN;
                     break;
                  case LOWER_THAN:
                     operator = CompareFilter.Operator.GREATER_OR_EQUAL;
               }
            }

            switch (operator) {
               case EQUALS:
                  if (this.startVersion != null || this.endVersion != null) {
                     throw new UnsupportedFilterException("Multiple version matching is not supported");
                  }

                  this.startVersion = version;
                  this.startExclusive = false;
                  this.endVersion = version;
                  this.endExclusive = false;
                  return;
               case GREATER_OR_EQUAL:
                  if (this.startVersion != null) {
                     throw new UnsupportedFilterException("Multiple version matching is not supported");
                  }

                  this.startVersion = version;
                  this.startExclusive = false;
                  return;
               case GREATER_THAN:
                  if (this.startVersion != null) {
                     throw new UnsupportedFilterException("Multiple version matching is not supported");
                  }

                  this.startVersion = version;
                  this.startExclusive = true;
                  return;
               case LOWER_OR_EQUAL:
                  if (this.endVersion != null) {
                     throw new UnsupportedFilterException("Multiple version matching is not supported");
                  }

                  this.endVersion = version;
                  this.endExclusive = false;
                  return;
               case LOWER_THAN:
                  if (this.endVersion != null) {
                     throw new UnsupportedFilterException("Multiple version matching is not supported");
                  }

                  this.endVersion = version;
                  this.endExclusive = true;
                  return;
               default:
                  return;
            }
         default:
            throw new UnsupportedFilterException("Unsupported attribute: " + att);
      }
   }
}
