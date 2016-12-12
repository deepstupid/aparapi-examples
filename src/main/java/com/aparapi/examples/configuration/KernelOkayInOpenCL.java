/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.aparapi.examples.configuration;

/**
 * Created by Barney on 24/08/2015.
 */
public class KernelOkayInOpenCL extends com.aparapi.Kernel {
   final char[] inChars = "KernelOkayInOpenCL".toCharArray();
   final char[] outChars = new char[inChars.length];

   @Override
   public void run() {
      int index = getGlobalId();
      oops();
      outChars[index] = inChars[index];
   }

   @NoCL
   private static void oops() {
      System.out.println("Oops, running in kernel in Java");
   }
}
