package com.aparapi.test.runtime;

import com.aparapi.*;
import org.junit.*;

import static org.junit.Assert.*;

public class ExplicitBoolean{

   class ExplicitBooleanTestKernel extends Kernel{
      int size; // Number of work items.

      int iterations; // Number of times to execute kernel.

      public boolean[] input, output;

      public ExplicitBooleanTestKernel(int _size) {
         size = _size;
         input = new boolean[size];
         output = new boolean[size];
         setExplicit(true);
         put(output);
      }

      public void go() {
         put(input);
         execute(size);
         get(output);
      }

      @Override public void run() {
         int id = getGlobalId();
         output[id] = input[id];
      }
   }

   @Test public void test() {
      int size = 16;
      ExplicitBooleanTestKernel k1 = new ExplicitBooleanTestKernel(size);
      ExplicitBooleanTestKernel k2 = new ExplicitBooleanTestKernel(size);
      k2.input = k1.output;

      for (int i = 0; i < size; i++) {
         k1.input[i] = Math.random() > 0.5;
      }

      if (size <= 32)
         printArray(k1.input);

      k1.go();

      if (size <= 32)
         printArray(k1.output);

      assertTrue("k1.input == k1.output ", Util.same(k1.output, k1.output));

      k2.go();

      if (size <= 32)
         printArray(k2.output);

      assertTrue("k1.input == k2.input", Util.same(k1.output, k1.output));
      System.out.println(k1.getTargetDevice().getShortDescription());
   }

   private static void printArray(boolean[] a) {
      for (int i = 0; i < a.length; i++) {
         System.out.print((a[i] ? 1 : 0) + "\t");
      }
      System.out.println();
   }

}
