package org.apache.parquet.filter2.recordlevel;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.predicate.Operators;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.io.PrimitiveColumnIO;
import org.apache.parquet.io.api.Binary;
import org.apache.parquet.schema.PrimitiveComparator;

public class IncrementallyUpdatedFilterPredicateBuilder extends IncrementallyUpdatedFilterPredicateBuilderBase {
   public IncrementallyUpdatedFilterPredicateBuilder(List leaves) {
      super(leaves);
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.Eq pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      if (clazz.equals(Integer.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(int value) {
                  this.setResult(false);
               }
            };
         } else {
            final int target = (Integer)pred.getValue();
            final PrimitiveComparator<Integer> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(int value) {
                  this.setResult(comparator.compare(value, target) == 0);
               }
            };
         }
      }

      if (clazz.equals(Long.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(long value) {
                  this.setResult(false);
               }
            };
         } else {
            final long target = (Long)pred.getValue();
            final PrimitiveComparator<Long> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(long value) {
                  this.setResult(comparator.compare(value, target) == 0);
               }
            };
         }
      }

      if (clazz.equals(Boolean.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(boolean value) {
                  this.setResult(false);
               }
            };
         } else {
            final boolean target = (Boolean)pred.getValue();
            final PrimitiveComparator<Boolean> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(boolean value) {
                  this.setResult(comparator.compare(value, target) == 0);
               }
            };
         }
      }

      if (clazz.equals(Float.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(float value) {
                  this.setResult(false);
               }
            };
         } else {
            final float target = (Float)pred.getValue();
            final PrimitiveComparator<Float> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(float value) {
                  this.setResult(comparator.compare(value, target) == 0);
               }
            };
         }
      }

      if (clazz.equals(Double.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(double value) {
                  this.setResult(false);
               }
            };
         } else {
            final double target = (Double)pred.getValue();
            final PrimitiveComparator<Double> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(double value) {
                  this.setResult(comparator.compare(value, target) == 0);
               }
            };
         }
      }

      if (clazz.equals(Binary.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(Binary value) {
                  this.setResult(false);
               }
            };
         } else {
            final Binary target = (Binary)pred.getValue();
            final PrimitiveComparator<Binary> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(Binary value) {
                  this.setResult(comparator.compare(value, target) == 0);
               }
            };
         }
      }

      if (valueInspector == null) {
         throw new IllegalArgumentException("Encountered unknown type " + clazz);
      } else {
         this.addValueInspector(columnPath, valueInspector);
         return valueInspector;
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.NotEq pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      if (clazz.equals(Integer.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(int value) {
                  this.setResult(true);
               }
            };
         } else {
            final int target = (Integer)pred.getValue();
            final PrimitiveComparator<Integer> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(int value) {
                  this.setResult(comparator.compare(value, target) != 0);
               }
            };
         }
      }

      if (clazz.equals(Long.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(long value) {
                  this.setResult(true);
               }
            };
         } else {
            final long target = (Long)pred.getValue();
            final PrimitiveComparator<Long> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(long value) {
                  this.setResult(comparator.compare(value, target) != 0);
               }
            };
         }
      }

      if (clazz.equals(Boolean.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(boolean value) {
                  this.setResult(true);
               }
            };
         } else {
            final boolean target = (Boolean)pred.getValue();
            final PrimitiveComparator<Boolean> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(boolean value) {
                  this.setResult(comparator.compare(value, target) != 0);
               }
            };
         }
      }

      if (clazz.equals(Float.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(float value) {
                  this.setResult(true);
               }
            };
         } else {
            final float target = (Float)pred.getValue();
            final PrimitiveComparator<Float> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(float value) {
                  this.setResult(comparator.compare(value, target) != 0);
               }
            };
         }
      }

      if (clazz.equals(Double.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(double value) {
                  this.setResult(true);
               }
            };
         } else {
            final double target = (Double)pred.getValue();
            final PrimitiveComparator<Double> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(double value) {
                  this.setResult(comparator.compare(value, target) != 0);
               }
            };
         }
      }

      if (clazz.equals(Binary.class)) {
         if (pred.getValue() == null) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(Binary value) {
                  this.setResult(true);
               }
            };
         } else {
            final Binary target = (Binary)pred.getValue();
            final PrimitiveComparator<Binary> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(Binary value) {
                  this.setResult(comparator.compare(value, target) != 0);
               }
            };
         }
      }

      if (valueInspector == null) {
         throw new IllegalArgumentException("Encountered unknown type " + clazz);
      } else {
         this.addValueInspector(columnPath, valueInspector);
         return valueInspector;
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.In pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      if (clazz.equals(Integer.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(int value) {
                  this.setResult(false);
               }
            };
         } else {
            final Set<Integer> target = pred.getValues();
            final PrimitiveComparator<Integer> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(int value) {
                  for(int i : target) {
                     if (comparator.compare(value, i) == 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Long.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(long value) {
                  this.setResult(false);
               }
            };
         } else {
            final Set<Long> target = pred.getValues();
            final PrimitiveComparator<Long> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(long value) {
                  for(long i : target) {
                     if (comparator.compare(value, i) == 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Boolean.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(boolean value) {
                  this.setResult(false);
               }
            };
         } else {
            final Set<Boolean> target = pred.getValues();
            final PrimitiveComparator<Boolean> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(boolean value) {
                  for(boolean i : target) {
                     if (comparator.compare(value, i) == 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Float.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(float value) {
                  this.setResult(false);
               }
            };
         } else {
            final Set<Float> target = pred.getValues();
            final PrimitiveComparator<Float> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(float value) {
                  for(float i : target) {
                     if (comparator.compare(value, i) == 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Double.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(double value) {
                  this.setResult(false);
               }
            };
         } else {
            final Set<Double> target = pred.getValues();
            final PrimitiveComparator<Double> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(double value) {
                  for(double i : target) {
                     if (comparator.compare(value, i) == 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Binary.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(Binary value) {
                  this.setResult(false);
               }
            };
         } else {
            final Set<Binary> target = pred.getValues();
            final PrimitiveComparator<Binary> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(Binary value) {
                  for(Binary i : target) {
                     if (comparator.compare(value, i) == 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (valueInspector == null) {
         throw new IllegalArgumentException("Encountered unknown type " + clazz);
      } else {
         this.addValueInspector(columnPath, valueInspector);
         return valueInspector;
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.NotIn pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      if (clazz.equals(Integer.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(int value) {
                  this.setResult(true);
               }
            };
         } else {
            final Set<Integer> target = pred.getValues();
            final PrimitiveComparator<Integer> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(int value) {
                  for(int i : target) {
                     if (comparator.compare(value, i) != 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Long.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(long value) {
                  this.setResult(true);
               }
            };
         } else {
            final Set<Long> target = pred.getValues();
            final PrimitiveComparator<Long> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(long value) {
                  for(long i : target) {
                     if (comparator.compare(value, i) != 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Boolean.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(boolean value) {
                  this.setResult(true);
               }
            };
         } else {
            final Set<Boolean> target = pred.getValues();
            final PrimitiveComparator<Boolean> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(boolean value) {
                  for(boolean i : target) {
                     if (comparator.compare(value, i) != 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Float.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(float value) {
                  this.setResult(true);
               }
            };
         } else {
            final Set<Float> target = pred.getValues();
            final PrimitiveComparator<Float> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(float value) {
                  for(float i : target) {
                     if (comparator.compare(value, i) != 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Double.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(double value) {
                  this.setResult(true);
               }
            };
         } else {
            final Set<Double> target = pred.getValues();
            final PrimitiveComparator<Double> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(double value) {
                  for(double i : target) {
                     if (comparator.compare(value, i) != 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (clazz.equals(Binary.class)) {
         if (pred.getValues().contains((Object)null)) {
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(Binary value) {
                  this.setResult(true);
               }
            };
         } else {
            final Set<Binary> target = pred.getValues();
            final PrimitiveComparator<Binary> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(Binary value) {
                  for(Binary i : target) {
                     if (comparator.compare(value, i) != 0) {
                        this.setResult(true);
                        return;
                     }
                  }

                  this.setResult(false);
               }
            };
         }
      }

      if (valueInspector == null) {
         throw new IllegalArgumentException("Encountered unknown type " + clazz);
      } else {
         this.addValueInspector(columnPath, valueInspector);
         return valueInspector;
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.Contains pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      IncrementallyUpdatedFilterPredicate.ValueInspector var5 = (new ContainsInspectorVisitor()).visit(pred);
      if (var5 == null) {
         throw new IllegalArgumentException("Encountered unknown type " + clazz);
      } else {
         this.addValueInspector(columnPath, var5);
         return var5;
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.Lt pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      if (clazz.equals(Integer.class)) {
         final int target = (Integer)pred.getValue();
         final PrimitiveComparator<Integer> comparator = this.getComparator(columnPath);
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(false);
            }

            public void update(int value) {
               this.setResult(comparator.compare(value, target) < 0);
            }
         };
      }

      if (clazz.equals(Long.class)) {
         final long target = (Long)pred.getValue();
         final PrimitiveComparator<Long> comparator = this.getComparator(columnPath);
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(false);
            }

            public void update(long value) {
               this.setResult(comparator.compare(value, target) < 0);
            }
         };
      }

      if (clazz.equals(Boolean.class)) {
         throw new IllegalArgumentException("Operator < not supported for Boolean");
      } else {
         if (clazz.equals(Float.class)) {
            final float target = (Float)pred.getValue();
            final PrimitiveComparator<Float> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(float value) {
                  this.setResult(comparator.compare(value, target) < 0);
               }
            };
         }

         if (clazz.equals(Double.class)) {
            final double target = (Double)pred.getValue();
            final PrimitiveComparator<Double> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(double value) {
                  this.setResult(comparator.compare(value, target) < 0);
               }
            };
         }

         if (clazz.equals(Binary.class)) {
            final Binary target = (Binary)pred.getValue();
            final PrimitiveComparator<Binary> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(Binary value) {
                  this.setResult(comparator.compare(value, target) < 0);
               }
            };
         }

         if (valueInspector == null) {
            throw new IllegalArgumentException("Encountered unknown type " + clazz);
         } else {
            this.addValueInspector(columnPath, valueInspector);
            return valueInspector;
         }
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.LtEq pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      if (clazz.equals(Integer.class)) {
         final int target = (Integer)pred.getValue();
         final PrimitiveComparator<Integer> comparator = this.getComparator(columnPath);
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(false);
            }

            public void update(int value) {
               this.setResult(comparator.compare(value, target) <= 0);
            }
         };
      }

      if (clazz.equals(Long.class)) {
         final long target = (Long)pred.getValue();
         final PrimitiveComparator<Long> comparator = this.getComparator(columnPath);
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(false);
            }

            public void update(long value) {
               this.setResult(comparator.compare(value, target) <= 0);
            }
         };
      }

      if (clazz.equals(Boolean.class)) {
         throw new IllegalArgumentException("Operator <= not supported for Boolean");
      } else {
         if (clazz.equals(Float.class)) {
            final float target = (Float)pred.getValue();
            final PrimitiveComparator<Float> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(float value) {
                  this.setResult(comparator.compare(value, target) <= 0);
               }
            };
         }

         if (clazz.equals(Double.class)) {
            final double target = (Double)pred.getValue();
            final PrimitiveComparator<Double> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(double value) {
                  this.setResult(comparator.compare(value, target) <= 0);
               }
            };
         }

         if (clazz.equals(Binary.class)) {
            final Binary target = (Binary)pred.getValue();
            final PrimitiveComparator<Binary> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(Binary value) {
                  this.setResult(comparator.compare(value, target) <= 0);
               }
            };
         }

         if (valueInspector == null) {
            throw new IllegalArgumentException("Encountered unknown type " + clazz);
         } else {
            this.addValueInspector(columnPath, valueInspector);
            return valueInspector;
         }
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.Gt pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      if (clazz.equals(Integer.class)) {
         final int target = (Integer)pred.getValue();
         final PrimitiveComparator<Integer> comparator = this.getComparator(columnPath);
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(false);
            }

            public void update(int value) {
               this.setResult(comparator.compare(value, target) > 0);
            }
         };
      }

      if (clazz.equals(Long.class)) {
         final long target = (Long)pred.getValue();
         final PrimitiveComparator<Long> comparator = this.getComparator(columnPath);
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(false);
            }

            public void update(long value) {
               this.setResult(comparator.compare(value, target) > 0);
            }
         };
      }

      if (clazz.equals(Boolean.class)) {
         throw new IllegalArgumentException("Operator > not supported for Boolean");
      } else {
         if (clazz.equals(Float.class)) {
            final float target = (Float)pred.getValue();
            final PrimitiveComparator<Float> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(float value) {
                  this.setResult(comparator.compare(value, target) > 0);
               }
            };
         }

         if (clazz.equals(Double.class)) {
            final double target = (Double)pred.getValue();
            final PrimitiveComparator<Double> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(double value) {
                  this.setResult(comparator.compare(value, target) > 0);
               }
            };
         }

         if (clazz.equals(Binary.class)) {
            final Binary target = (Binary)pred.getValue();
            final PrimitiveComparator<Binary> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(Binary value) {
                  this.setResult(comparator.compare(value, target) > 0);
               }
            };
         }

         if (valueInspector == null) {
            throw new IllegalArgumentException("Encountered unknown type " + clazz);
         } else {
            this.addValueInspector(columnPath, valueInspector);
            return valueInspector;
         }
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.GtEq pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      if (clazz.equals(Integer.class)) {
         final int target = (Integer)pred.getValue();
         final PrimitiveComparator<Integer> comparator = this.getComparator(columnPath);
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(false);
            }

            public void update(int value) {
               this.setResult(comparator.compare(value, target) >= 0);
            }
         };
      }

      if (clazz.equals(Long.class)) {
         final long target = (Long)pred.getValue();
         final PrimitiveComparator<Long> comparator = this.getComparator(columnPath);
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(false);
            }

            public void update(long value) {
               this.setResult(comparator.compare(value, target) >= 0);
            }
         };
      }

      if (clazz.equals(Boolean.class)) {
         throw new IllegalArgumentException("Operator >= not supported for Boolean");
      } else {
         if (clazz.equals(Float.class)) {
            final float target = (Float)pred.getValue();
            final PrimitiveComparator<Float> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(float value) {
                  this.setResult(comparator.compare(value, target) >= 0);
               }
            };
         }

         if (clazz.equals(Double.class)) {
            final double target = (Double)pred.getValue();
            final PrimitiveComparator<Double> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(double value) {
                  this.setResult(comparator.compare(value, target) >= 0);
               }
            };
         }

         if (clazz.equals(Binary.class)) {
            final Binary target = (Binary)pred.getValue();
            final PrimitiveComparator<Binary> comparator = this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(Binary value) {
                  this.setResult(comparator.compare(value, target) >= 0);
               }
            };
         }

         if (valueInspector == null) {
            throw new IllegalArgumentException("Encountered unknown type " + clazz);
         } else {
            this.addValueInspector(columnPath, valueInspector);
            return valueInspector;
         }
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.UserDefined pred) {
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      final U udp = (U)pred.getUserDefinedPredicate();
      if (clazz.equals(Integer.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(udp.acceptsNullValue());
            }

            public void update(int value) {
               this.setResult(udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Long.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(udp.acceptsNullValue());
            }

            public void update(long value) {
               this.setResult(udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Boolean.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(udp.acceptsNullValue());
            }

            public void update(boolean value) {
               this.setResult(udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Float.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(udp.acceptsNullValue());
            }

            public void update(float value) {
               this.setResult(udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Double.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(udp.acceptsNullValue());
            }

            public void update(double value) {
               this.setResult(udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Binary.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(udp.acceptsNullValue());
            }

            public void update(Binary value) {
               this.setResult(udp.keep(value));
            }
         };
      }

      if (valueInspector == null) {
         throw new IllegalArgumentException("Encountered unknown type " + clazz);
      } else {
         this.addValueInspector(columnPath, valueInspector);
         return valueInspector;
      }
   }

   public IncrementallyUpdatedFilterPredicate visit(Operators.LogicalNotUserDefined notPred) {
      Operators.UserDefined<T, U> pred = notPred.getUserDefined();
      ColumnPath columnPath = pred.getColumn().getColumnPath();
      Class<T> clazz = pred.getColumn().getColumnType();
      IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
      final U udp = (U)pred.getUserDefinedPredicate();
      if (clazz.equals(Integer.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(!udp.acceptsNullValue());
            }

            public void update(int value) {
               this.setResult(!udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Long.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(!udp.acceptsNullValue());
            }

            public void update(long value) {
               this.setResult(!udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Boolean.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(!udp.acceptsNullValue());
            }

            public void update(boolean value) {
               this.setResult(!udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Float.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(!udp.acceptsNullValue());
            }

            public void update(float value) {
               this.setResult(!udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Double.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(!udp.acceptsNullValue());
            }

            public void update(double value) {
               this.setResult(!udp.keep((Comparable)value));
            }
         };
      }

      if (clazz.equals(Binary.class)) {
         valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
            public void updateNull() {
               this.setResult(!udp.acceptsNullValue());
            }

            public void update(Binary value) {
               this.setResult(!udp.keep(value));
            }
         };
      }

      if (valueInspector == null) {
         throw new IllegalArgumentException("Encountered unknown type " + clazz);
      } else {
         this.addValueInspector(columnPath, valueInspector);
         return valueInspector;
      }
   }

   private abstract static class ContainsPredicate extends IncrementallyUpdatedFilterPredicate.DelegatingValueInspector {
      ContainsPredicate(IncrementallyUpdatedFilterPredicate.ValueInspector... delegates) {
         super(delegates);
      }

      abstract ContainsPredicate not();
   }

   private static class ContainsSinglePredicate extends ContainsPredicate {
      private final boolean isNot;

      private ContainsSinglePredicate(IncrementallyUpdatedFilterPredicate.ValueInspector inspector, boolean isNot) {
         super(inspector);
         this.isNot = isNot;
      }

      ContainsPredicate not() {
         return new ContainsSinglePredicate((IncrementallyUpdatedFilterPredicate.ValueInspector)this.getDelegates().iterator().next(), true);
      }

      void onUpdate() {
         if (!this.isKnown()) {
            for(IncrementallyUpdatedFilterPredicate.ValueInspector inspector : this.getDelegates()) {
               if (inspector.isKnown() && inspector.getResult()) {
                  this.setResult(!this.isNot);
                  return;
               }
            }

         }
      }

      void onNull() {
         this.setResult(this.isNot);
      }
   }

   private static class ContainsAndPredicate extends ContainsPredicate {
      private ContainsAndPredicate(ContainsPredicate left, ContainsPredicate right) {
         super(left, right);
      }

      void onUpdate() {
         if (!this.isKnown()) {
            boolean allKnown = true;

            for(IncrementallyUpdatedFilterPredicate.ValueInspector delegate : this.getDelegates()) {
               if (delegate.isKnown() && !delegate.getResult()) {
                  this.setResult(false);
                  return;
               }

               allKnown = allKnown && delegate.isKnown();
            }

            if (allKnown) {
               this.setResult(true);
            }

         }
      }

      void onNull() {
         for(IncrementallyUpdatedFilterPredicate.ValueInspector delegate : this.getDelegates()) {
            if (!delegate.getResult()) {
               this.setResult(false);
               return;
            }
         }

         this.setResult(true);
      }

      ContainsPredicate not() {
         Iterator<IncrementallyUpdatedFilterPredicate.ValueInspector> it = this.getDelegates().iterator();
         return new ContainsAndPredicate(((ContainsPredicate)it.next()).not(), ((ContainsPredicate)it.next()).not());
      }
   }

   private static class ContainsOrPredicate extends ContainsPredicate {
      private ContainsOrPredicate(ContainsPredicate left, ContainsPredicate right) {
         super(left, right);
      }

      void onUpdate() {
         if (!this.isKnown()) {
            for(IncrementallyUpdatedFilterPredicate.ValueInspector delegate : this.getDelegates()) {
               if (delegate.isKnown() && delegate.getResult()) {
                  this.setResult(true);
               }
            }

         }
      }

      void onNull() {
         for(IncrementallyUpdatedFilterPredicate.ValueInspector delegate : this.getDelegates()) {
            if (delegate.getResult()) {
               this.setResult(true);
               return;
            }
         }

         this.setResult(false);
      }

      ContainsPredicate not() {
         Iterator<IncrementallyUpdatedFilterPredicate.ValueInspector> it = this.getDelegates().iterator();
         return new ContainsOrPredicate(((ContainsPredicate)it.next()).not(), ((ContainsPredicate)it.next()).not());
      }
   }

   private class ContainsInspectorVisitor implements FilterPredicate.Visitor {
      private ContainsInspectorVisitor() {
      }

      public ContainsPredicate visit(Operators.Contains contains) {
         return (ContainsPredicate)contains.filter(this, (x$0, x$1) -> new ContainsAndPredicate(x$0, x$1), (x$0, x$1) -> new ContainsOrPredicate(x$0, x$1), ContainsPredicate::not);
      }

      public ContainsPredicate visit(Operators.Eq pred) {
         ColumnPath columnPath = pred.getColumn().getColumnPath();
         Class<T> clazz = pred.getColumn().getColumnType();
         IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
         if (clazz.equals(Integer.class)) {
            final int target = (Integer)pred.getValue();
            final PrimitiveComparator<Integer> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(int value) {
                  if (!this.isKnown() && comparator.compare(value, target) == 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Long.class)) {
            final long target = (Long)pred.getValue();
            final PrimitiveComparator<Long> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(long value) {
                  if (!this.isKnown() && comparator.compare(value, target) == 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Boolean.class)) {
            final boolean target = (Boolean)pred.getValue();
            final PrimitiveComparator<Boolean> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(boolean value) {
                  if (!this.isKnown() && comparator.compare(value, target) == 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Float.class)) {
            final float target = (Float)pred.getValue();
            final PrimitiveComparator<Float> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(float value) {
                  if (!this.isKnown() && comparator.compare(value, target) == 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Double.class)) {
            final double target = (Double)pred.getValue();
            final PrimitiveComparator<Double> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(double value) {
                  if (!this.isKnown() && comparator.compare(value, target) == 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Binary.class)) {
            final Binary target = (Binary)pred.getValue();
            final PrimitiveComparator<Binary> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(Binary value) {
                  if (!this.isKnown() && comparator.compare(value, target) == 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         return new ContainsSinglePredicate(valueInspector, false);
      }

      public ContainsPredicate visit(Operators.NotEq pred) {
         ColumnPath columnPath = pred.getColumn().getColumnPath();
         Class<T> clazz = pred.getColumn().getColumnType();
         IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
         if (clazz.equals(Integer.class)) {
            final int target = (Integer)pred.getValue();
            final PrimitiveComparator<Integer> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(int value) {
                  if (!this.isKnown() && comparator.compare(value, target) != 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Long.class)) {
            final long target = (Long)pred.getValue();
            final PrimitiveComparator<Long> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(long value) {
                  if (!this.isKnown() && comparator.compare(value, target) != 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Boolean.class)) {
            final boolean target = (Boolean)pred.getValue();
            final PrimitiveComparator<Boolean> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(boolean value) {
                  if (!this.isKnown() && comparator.compare(value, target) != 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Float.class)) {
            final float target = (Float)pred.getValue();
            final PrimitiveComparator<Float> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(float value) {
                  if (!this.isKnown() && comparator.compare(value, target) != 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Double.class)) {
            final double target = (Double)pred.getValue();
            final PrimitiveComparator<Double> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(double value) {
                  if (!this.isKnown() && comparator.compare(value, target) != 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Binary.class)) {
            final Binary target = (Binary)pred.getValue();
            final PrimitiveComparator<Binary> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(true);
               }

               public void update(Binary value) {
                  if (!this.isKnown() && comparator.compare(value, target) != 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         return new ContainsSinglePredicate(valueInspector, false);
      }

      public ContainsPredicate visit(Operators.Lt pred) {
         ColumnPath columnPath = pred.getColumn().getColumnPath();
         Class<T> clazz = pred.getColumn().getColumnType();
         IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
         if (clazz.equals(Integer.class)) {
            final int target = (Integer)pred.getValue();
            final PrimitiveComparator<Integer> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(int value) {
                  if (!this.isKnown() && comparator.compare(value, target) < 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Long.class)) {
            final long target = (Long)pred.getValue();
            final PrimitiveComparator<Long> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(long value) {
                  if (!this.isKnown() && comparator.compare(value, target) < 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Boolean.class)) {
            throw new IllegalArgumentException("Operator < not supported for Boolean");
         } else {
            if (clazz.equals(Float.class)) {
               final float target = (Float)pred.getValue();
               final PrimitiveComparator<Float> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(float value) {
                     if (!this.isKnown() && comparator.compare(value, target) < 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            if (clazz.equals(Double.class)) {
               final double target = (Double)pred.getValue();
               final PrimitiveComparator<Double> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(double value) {
                     if (!this.isKnown() && comparator.compare(value, target) < 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            if (clazz.equals(Binary.class)) {
               final Binary target = (Binary)pred.getValue();
               final PrimitiveComparator<Binary> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(Binary value) {
                     if (!this.isKnown() && comparator.compare(value, target) < 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            return new ContainsSinglePredicate(valueInspector, false);
         }
      }

      public ContainsPredicate visit(Operators.LtEq pred) {
         ColumnPath columnPath = pred.getColumn().getColumnPath();
         Class<T> clazz = pred.getColumn().getColumnType();
         IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
         if (clazz.equals(Integer.class)) {
            final int target = (Integer)pred.getValue();
            final PrimitiveComparator<Integer> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(int value) {
                  if (!this.isKnown() && comparator.compare(value, target) <= 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Long.class)) {
            final long target = (Long)pred.getValue();
            final PrimitiveComparator<Long> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(long value) {
                  if (!this.isKnown() && comparator.compare(value, target) <= 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Boolean.class)) {
            throw new IllegalArgumentException("Operator <= not supported for Boolean");
         } else {
            if (clazz.equals(Float.class)) {
               final float target = (Float)pred.getValue();
               final PrimitiveComparator<Float> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(float value) {
                     if (!this.isKnown() && comparator.compare(value, target) <= 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            if (clazz.equals(Double.class)) {
               final double target = (Double)pred.getValue();
               final PrimitiveComparator<Double> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(double value) {
                     if (!this.isKnown() && comparator.compare(value, target) <= 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            if (clazz.equals(Binary.class)) {
               final Binary target = (Binary)pred.getValue();
               final PrimitiveComparator<Binary> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(Binary value) {
                     if (!this.isKnown() && comparator.compare(value, target) <= 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            return new ContainsSinglePredicate(valueInspector, false);
         }
      }

      public ContainsPredicate visit(Operators.Gt pred) {
         ColumnPath columnPath = pred.getColumn().getColumnPath();
         Class<T> clazz = pred.getColumn().getColumnType();
         IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
         if (clazz.equals(Integer.class)) {
            final int target = (Integer)pred.getValue();
            final PrimitiveComparator<Integer> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(int value) {
                  if (!this.isKnown() && comparator.compare(value, target) > 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Long.class)) {
            final long target = (Long)pred.getValue();
            final PrimitiveComparator<Long> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(long value) {
                  if (!this.isKnown() && comparator.compare(value, target) > 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Boolean.class)) {
            throw new IllegalArgumentException("Operator > not supported for Boolean");
         } else {
            if (clazz.equals(Float.class)) {
               final float target = (Float)pred.getValue();
               final PrimitiveComparator<Float> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(float value) {
                     if (!this.isKnown() && comparator.compare(value, target) > 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            if (clazz.equals(Double.class)) {
               final double target = (Double)pred.getValue();
               final PrimitiveComparator<Double> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(double value) {
                     if (!this.isKnown() && comparator.compare(value, target) > 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            if (clazz.equals(Binary.class)) {
               final Binary target = (Binary)pred.getValue();
               final PrimitiveComparator<Binary> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(Binary value) {
                     if (!this.isKnown() && comparator.compare(value, target) > 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            return new ContainsSinglePredicate(valueInspector, false);
         }
      }

      public ContainsPredicate visit(Operators.GtEq pred) {
         ColumnPath columnPath = pred.getColumn().getColumnPath();
         Class<T> clazz = pred.getColumn().getColumnType();
         IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
         if (clazz.equals(Integer.class)) {
            final int target = (Integer)pred.getValue();
            final PrimitiveComparator<Integer> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(int value) {
                  if (!this.isKnown() && comparator.compare(value, target) >= 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Long.class)) {
            final long target = (Long)pred.getValue();
            final PrimitiveComparator<Long> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
            valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
               public void updateNull() {
                  this.setResult(false);
               }

               public void update(long value) {
                  if (!this.isKnown() && comparator.compare(value, target) >= 0) {
                     this.setResult(true);
                  }

               }
            };
         }

         if (clazz.equals(Boolean.class)) {
            throw new IllegalArgumentException("Operator >= not supported for Boolean");
         } else {
            if (clazz.equals(Float.class)) {
               final float target = (Float)pred.getValue();
               final PrimitiveComparator<Float> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(float value) {
                     if (!this.isKnown() && comparator.compare(value, target) >= 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            if (clazz.equals(Double.class)) {
               final double target = (Double)pred.getValue();
               final PrimitiveComparator<Double> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(double value) {
                     if (!this.isKnown() && comparator.compare(value, target) >= 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            if (clazz.equals(Binary.class)) {
               final Binary target = (Binary)pred.getValue();
               final PrimitiveComparator<Binary> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(Binary value) {
                     if (!this.isKnown() && comparator.compare(value, target) >= 0) {
                        this.setResult(true);
                     }

                  }
               };
            }

            return new ContainsSinglePredicate(valueInspector, false);
         }
      }

      public ContainsPredicate visit(Operators.In pred) {
         ColumnPath columnPath = pred.getColumn().getColumnPath();
         Class<T> clazz = pred.getColumn().getColumnType();
         IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
         if (clazz.equals(Integer.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(int value) {
                     this.setResult(false);
                  }
               };
            } else {
               final Set<Integer> target = pred.getValues();
               final PrimitiveComparator<Integer> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(int value) {
                     if (!this.isKnown()) {
                        for(int i : target) {
                           if (comparator.compare(value, i) == 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Long.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(long value) {
                     this.setResult(false);
                  }
               };
            } else {
               final Set<Long> target = pred.getValues();
               final PrimitiveComparator<Long> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(long value) {
                     if (!this.isKnown()) {
                        for(long i : target) {
                           if (comparator.compare(value, i) == 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Boolean.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(boolean value) {
                     this.setResult(false);
                  }
               };
            } else {
               final Set<Boolean> target = pred.getValues();
               final PrimitiveComparator<Boolean> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(boolean value) {
                     if (!this.isKnown()) {
                        for(boolean i : target) {
                           if (comparator.compare(value, i) == 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Float.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(float value) {
                     this.setResult(false);
                  }
               };
            } else {
               final Set<Float> target = pred.getValues();
               final PrimitiveComparator<Float> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(float value) {
                     if (!this.isKnown()) {
                        for(float i : target) {
                           if (comparator.compare(value, i) == 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Double.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(double value) {
                     this.setResult(false);
                  }
               };
            } else {
               final Set<Double> target = pred.getValues();
               final PrimitiveComparator<Double> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(double value) {
                     if (!this.isKnown()) {
                        for(double i : target) {
                           if (comparator.compare(value, i) == 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Binary.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(Binary value) {
                     this.setResult(false);
                  }
               };
            } else {
               final Set<Binary> target = pred.getValues();
               final PrimitiveComparator<Binary> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(Binary value) {
                     if (!this.isKnown()) {
                        for(Binary i : target) {
                           if (comparator.compare(value, i) == 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         return new ContainsSinglePredicate(valueInspector, false);
      }

      public ContainsPredicate visit(Operators.NotIn pred) {
         ColumnPath columnPath = pred.getColumn().getColumnPath();
         Class<T> clazz = pred.getColumn().getColumnType();
         IncrementallyUpdatedFilterPredicate.ValueInspector valueInspector = null;
         if (clazz.equals(Integer.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(int value) {
                     this.setResult(true);
                  }
               };
            } else {
               final Set<Integer> target = pred.getValues();
               final PrimitiveComparator<Integer> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(int value) {
                     if (!this.isKnown()) {
                        for(int i : target) {
                           if (comparator.compare(value, i) != 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Long.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(long value) {
                     this.setResult(true);
                  }
               };
            } else {
               final Set<Long> target = pred.getValues();
               final PrimitiveComparator<Long> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(long value) {
                     if (!this.isKnown()) {
                        for(long i : target) {
                           if (comparator.compare(value, i) != 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Boolean.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(boolean value) {
                     this.setResult(true);
                  }
               };
            } else {
               final Set<Boolean> target = pred.getValues();
               final PrimitiveComparator<Boolean> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(boolean value) {
                     if (!this.isKnown()) {
                        for(boolean i : target) {
                           if (comparator.compare(value, i) != 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Float.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(float value) {
                     this.setResult(true);
                  }
               };
            } else {
               final Set<Float> target = pred.getValues();
               final PrimitiveComparator<Float> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(float value) {
                     if (!this.isKnown()) {
                        for(float i : target) {
                           if (comparator.compare(value, i) != 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Double.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(double value) {
                     this.setResult(true);
                  }
               };
            } else {
               final Set<Double> target = pred.getValues();
               final PrimitiveComparator<Double> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(double value) {
                     if (!this.isKnown()) {
                        for(double i : target) {
                           if (comparator.compare(value, i) != 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         if (clazz.equals(Binary.class)) {
            if (pred.getValues().contains((Object)null)) {
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(false);
                  }

                  public void update(Binary value) {
                     this.setResult(true);
                  }
               };
            } else {
               final Set<Binary> target = pred.getValues();
               final PrimitiveComparator<Binary> comparator = IncrementallyUpdatedFilterPredicateBuilder.this.getComparator(columnPath);
               valueInspector = new IncrementallyUpdatedFilterPredicate.ValueInspector() {
                  public void updateNull() {
                     this.setResult(true);
                  }

                  public void update(Binary value) {
                     if (!this.isKnown()) {
                        for(Binary i : target) {
                           if (comparator.compare(value, i) != 0) {
                              this.setResult(true);
                              return;
                           }
                        }

                     }
                  }
               };
            }
         }

         return new ContainsSinglePredicate(valueInspector, false);
      }

      public ContainsPredicate visit(Operators.And pred) {
         throw new UnsupportedOperationException("Operators.And not supported for Contains predicate");
      }

      public ContainsPredicate visit(Operators.Or pred) {
         throw new UnsupportedOperationException("Operators.Or not supported for Contains predicate");
      }

      public ContainsPredicate visit(Operators.Not pred) {
         throw new UnsupportedOperationException("Operators.Not not supported for Contains predicate");
      }

      public ContainsPredicate visit(Operators.UserDefined pred) {
         throw new UnsupportedOperationException("UserDefinedPredicate not supported for Contains predicate");
      }

      public ContainsPredicate visit(Operators.LogicalNotUserDefined pred) {
         throw new UnsupportedOperationException("LogicalNotUserDefined not supported for Contains predicate");
      }
   }
}
