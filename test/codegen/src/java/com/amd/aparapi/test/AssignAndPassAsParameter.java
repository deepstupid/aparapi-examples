package com.aparapi.test;

public class AssignAndPassAsParameter{

   final static int START_SIZE = 128;

   public int[] values = new int[START_SIZE];

   public int[] results = new int[START_SIZE];

   int actuallyDoIt(int a) {
      return 1;
   }

   int y = 2;

   public void run() {
      actuallyDoIt(results[y] = actuallyDoIt(y));
   }
}
/**{OpenCL{
typedef struct This_s{
   __global int *results;
   int y;
   int passid;
}This;
int get_pass_id(This *this){
   return this->passid;
   }

int com_amd_aparapi_test_AssignAndPassAsParameter__actuallyDoIt(This *this, int a){
   return(1);
}
__kernel void run(
   __global int *results, 
   int y,
   int passid
){
   This thisStruct;
   This* this=&thisStruct;
   this->results = results;
   this->y = y;
   this->passid = passid;
   {
      com_amd_aparapi_test_AssignAndPassAsParameter__actuallyDoIt(this, this->results[this->y]  = com_amd_aparapi_test_AssignAndPassAsParameter__actuallyDoIt(this, this->y));
      return;
   }
}

}OpenCL}**/
