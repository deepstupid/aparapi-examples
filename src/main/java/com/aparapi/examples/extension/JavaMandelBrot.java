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
package com.aparapi.examples.extension;

import com.aparapi.*;
import com.aparapi.opencl.*;

public class JavaMandelBrot extends OpenCLAdapter<MandelBrot> implements MandelBrot{
   final int MAX_ITERATIONS = 64;

   final int pallette[] = new int[] {
         -65536,
         -59392,
         -53248,
         -112640,
         -106752,
         -166144,
         -160256,
         -219904,
         -279552,
         -339200,
         -399104,
         -985344,
         -2624000,
         -4197376,
         -5770496,
         -7343872,
         -8851712,
         -10425088,
         -11932928,
         -13375232,
         -14817792,
         -16260096,
         -16719602,
         -16720349,
         -16721097,
         -16721846,
         -16722595,
         -16723345,
         -16724351,
         -16725102,
         -16726110,
         -16727119,
         -16728129,
         -16733509,
         -16738889,
         -16744269,
         -16749138,
         -16754006,
         -16758619,
         -16762976,
         -16767077,
         -16771178,
         -16774767,
         -16514932,
         -15662970,
         -14942079,
         -14221189,
         -13631371,
         -13107088,
         -12648342,
         -12320669,
         -11992995,
         -11796393,
         -11665328,
         -11993019,
         -12386248,
         -12845011,
         -13303773,
         -13762534,
         -14286830,
         -14745588,
         -15269881,
         -15728637,
         -16252927,
         0
   };

   @Override public MandelBrot createMandleBrot(Range range, float scale, float offsetx, float offsety, int[] rgb) {

      final int width = range.getGlobalSize(0);
      final int height = range.getGlobalSize(1);
      for (int gridy = 0; gridy < height; gridy++) {
         for (int gridx = 0; gridx < width; gridx++) {
            final float x = ((((gridx) * scale) - ((scale / 2.0f) * width)) / width) + offsetx;
            final float y = ((((gridy) * scale) - ((scale / 2.0f) * height)) / height) + offsety;
            int count = 0;
            float zx = x;
            float zy = y;
            float new_zx = 0.0f;
            for (; (count < MAX_ITERATIONS) && (((zx * zx) + (zy * zy)) < 8.0f); count++) {
               new_zx = ((zx * zx) - (zy * zy)) + x;
               zy = ((2.0f * zx) * zy) + y;
               zx = new_zx;
            }
            rgb[gridx + (gridy * width)] = pallette[count];

         }
      }
      return (this);
   }

}
