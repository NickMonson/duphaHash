/*
This is an interpreted implementation of the DUPHA algorithm
constants are assumed for policy values
https://ieeexplore.ieee.org/document/6968657
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import java.util.ArrayList; //used for test
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DUPHA
{
   //constants
   static int A = 88; //number of and policy
   static int B = 57; //number of or policy
   static int C = 317; //number of any policy
   
   public DUPHA() 
   {
   
   }
   
   
   public void runTests()
   {
      ArrayList<String> hashes = new ArrayList<String>();
      //511 single character test
      System.out.println("generating test files...");
      
      for(int i = 0; i < 200; i++)
      {
         try{
            File outFile = new File("TestFile_"+i+".txt"); // 
            FileOutputStream outputStream = new FileOutputStream(outFile);
            outputStream.write(i);
            outputStream.flush();
            hashes.add(hash("TestFile_"+i+".txt"));
            outputStream.close();
            if(outFile.delete())                      //returns Boolean value  
            {  
               System.out.println(" (temp file: " + outFile.getName() + " deleted)");   //getting and printing the file name  
            }  
            else  
            {  
               System.out.println("failed");  
            }  
         } 
         catch(IOException e){
         }
      }
         
      System.out.println("done.");
      
      
      
     //small unique random file
     
      System.out.println("generating test files...");
      for(int i = 0; i < 20000; i++)
      {
         try{
            File outFile = new File("TestFile_"+i+".txt"); // 
            FileOutputStream outputStream = new FileOutputStream(outFile);
            //outputStream.write(i);
            byte[] dataTest = new byte[5000];
            dataTest[0] = (byte)(i & 0xFF);
            dataTest[1] = (byte)((i & 65280) >> 8);
            for(int j = 2; j < 5; j++){
               dataTest[j] = (byte)(Math.random()*256);
               //
            }
            outputStream.write(dataTest);
            hashes.add(hash("TestFile_"+i+".txt"));
            outputStream.close();
            if(outFile.delete())                      //returns Boolean value  
            {  
               System.out.println(" (temp file: " + outFile.getName() + " deleted)");   //getting and printing the file name  
            }  
            else  
            {  
               System.out.println("failed");  
            }  
         } 
         catch(IOException e){
         }
      }
      
      
      System.out.println("");  
      System.out.println("done.");
      
      Map<String, Integer> counter = new HashMap<>();
      List<String> duplicateList = new ArrayList<>();
      int duplicateCount = 0;
      for (String item : hashes) {
      
         if (!counter.containsKey(item)) {
            duplicateList.add(item);
            counter.put(item, 1);
         } 
         else {
            duplicateCount++;
            System.out.println(item);
         }
      }
      System.out.println("duplicate count: "+duplicateCount);
      
   }
   
   /*
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
      1,0,1,1,0,0,1,0, 1,1,1,0,0,1,0,1, 1,0,0,1,1,0,1,0, 1,0,1,1,0,1,1,0
   
   */
   //policy box 1024 bits --32-bytes
   static byte policyBox[] = {
   (byte)20, 	(byte)169, 	(byte)198,	(byte) 131, 
   (byte)192, 	(byte)51, 	(byte)47, 	(byte)138, 
   (byte)139, 	(byte)238, 	(byte)118, 	(byte)89, 
   (byte)160,	(byte) 158,	(byte)127, 	(byte)135, 
   (byte)225,	(byte) 177,	(byte)40, 	(byte)162, 
   (byte)230,	(byte) 50, 	(byte)44,	(byte) 48, 
   (byte)204,	(byte) 178, (byte)166,	(byte) 27, 
   (byte)145,	(byte) 21, 	(byte)33, 	(byte)142, 
   (byte)49, 	(byte)233, 	(byte)44, 	(byte)6, 
   (byte)205,	(byte) 248, (byte)100, 	(byte)49, 
   (byte)32, 	(byte)108, 	(byte)154, 	(byte)208, 
   (byte)30, 	(byte)21, 	(byte)19, 	(byte)219, 
   (byte)75, 	(byte)13, 	(byte)79, 	(byte)210, 
   (byte)80, 	(byte)60, 	(byte)163,	(byte) 37, 
   (byte)177, 	(byte)249, 	(byte)223, 	(byte)123, 
   (byte)16, 	(byte)102,	(byte) 182,	(byte) 150, 
   (byte)83, 	(byte)228,	(byte) 37,	(byte) 139, 
   (byte)18, 	(byte)115,	(byte) 19, 	(byte)255, 
   (byte)49, 	(byte)158,	(byte) 107, (byte)66, 
   (byte)52, 	(byte)22, 	(byte)181, 	(byte)3, 
   (byte)229, 	(byte)22,	(byte) 146, (byte)161, 
   (byte)7, 	(byte)11,	(byte) 232, (byte)96,
   (byte)35, 	(byte)148, 	(byte)44, 	(byte)135, 
   (byte)22, 	(byte)152, 	(byte)26, 	(byte)236, 
   (byte)193,	(byte) 20,	(byte) 202, (byte)114, 
   (byte)77, 	(byte)129,	(byte) 140, (byte)157, 
   (byte)229, 	(byte)114,	(byte) 53, 	(byte)90, 
   (byte)230, 	(byte)131,	(byte) 15, 	(byte)21, 
   (byte)188, 	(byte)72,	(byte) 136, (byte)57, 
   (byte)73, 	(byte)58, 	(byte)38, 	(byte)63, 
   (byte)20, 	(byte)131,	(byte) 194, (byte)181, 
   (byte)246, 	(byte)176,	(byte) 176, (byte)211
   };
   
   
   
   //AES Sbox https://en.wikipedia.org/wiki/Rijndael_S-box
   static byte sBox[][] = {
   
      {(byte)0x63, (byte)0x7c, (byte)0x77, (byte)0x7b, (byte)0xf2, (byte)0x6b, (byte)0x6f, (byte)0xc5, (byte)0x30, (byte)0x01, (byte)0x67, (byte)0x2b, (byte)0xfe, (byte)0xd7, (byte)0xab, (byte)0x76},
      {(byte)0xca, (byte)0x82, (byte)0xc9, (byte)0x7d, (byte)0xfa, (byte)0x59, (byte)0x47, (byte)0xf0, (byte)0xad, (byte)0xd4, (byte)0xa2, (byte)0xaf, (byte)0x9c, (byte)0xa4, (byte)0x72, (byte)0xc0},
      {(byte)0xb7, (byte)0xfd, (byte)0x93, (byte)0x26, (byte)0x36, (byte)0x3f, (byte)0xf7, (byte)0xcc, (byte)0x34, (byte)0xa5, (byte)0xe5, (byte)0xf1, (byte)0x71, (byte)0xd8, (byte)0x31, (byte)0x15},
      {(byte)0x04, (byte)0xc7, (byte)0x23, (byte)0xc3, (byte)0x18, (byte)0x96, (byte)0x05, (byte)0x9a, (byte)0x07, (byte)0x12, (byte)0x80, (byte)0xe2, (byte)0xeb, (byte)0x27, (byte)0xb2, (byte)0x75},
      {(byte)0x09, (byte)0x83, (byte)0x2c, (byte)0x1a, (byte)0x1b, (byte)0x6e, (byte)0x5a, (byte)0xa0, (byte)0x52, (byte)0x3b, (byte)0xd6, (byte)0xb3, (byte)0x29, (byte)0xe3, (byte)0x2f, (byte)0x84},
      {(byte)0x53, (byte)0xd1, (byte)0x00, (byte)0xed, (byte)0x20, (byte)0xfc, (byte)0xb1, (byte)0x5b, (byte)0x6a, (byte)0xcb, (byte)0xbe, (byte)0x39, (byte)0x4a, (byte)0x4c, (byte)0x58, (byte)0xcf},
      {(byte)0xd0, (byte)0xef, (byte)0xaa, (byte)0xfb, (byte)0x43, (byte)0x4d, (byte)0x33, (byte)0x85, (byte)0x45, (byte)0xf9, (byte)0x02, (byte)0x7f, (byte)0x50, (byte)0x3c, (byte)0x9f, (byte)0xa8},
      {(byte)0x51, (byte)0xa3, (byte)0x40, (byte)0x8f, (byte)0x92, (byte)0x9d, (byte)0x38, (byte)0xf5, (byte)0xbc, (byte)0xb6, (byte)0xda, (byte)0x21, (byte)0x10, (byte)0xff, (byte)0xf3, (byte)0xd2},
      {(byte)0xcd, (byte)0x0c, (byte)0x13, (byte)0xec, (byte)0x5f, (byte)0x97, (byte)0x44, (byte)0x17, (byte)0xc4, (byte)0xa7, (byte)0x7e, (byte)0x3d, (byte)0x64, (byte)0x5d, (byte)0x19, (byte)0x73},
      {(byte)0x60, (byte)0x81, (byte)0x4f, (byte)0xdc, (byte)0x22, (byte)0x2a, (byte)0x90, (byte)0x88, (byte)0x46, (byte)0xee, (byte)0xb8, (byte)0x14, (byte)0xde, (byte)0x5e, (byte)0x0b, (byte)0xdb},
      {(byte)0xe0, (byte)0x32, (byte)0x3a, (byte)0x0a, (byte)0x49, (byte)0x06, (byte)0x24, (byte)0x5c, (byte)0xc2, (byte)0xd3, (byte)0xac, (byte)0x62, (byte)0x91, (byte)0x95, (byte)0xe4, (byte)0x79},
      {(byte)0xe7, (byte)0xc8, (byte)0x37, (byte)0x6d, (byte)0x8d, (byte)0xd5, (byte)0x4e, (byte)0xa9, (byte)0x6c, (byte)0x56, (byte)0xf4, (byte)0xea, (byte)0x65, (byte)0x7a, (byte)0xae, (byte)0x08},
      {(byte)0xba, (byte)0x78, (byte)0x25, (byte)0x2e, (byte)0x1c, (byte)0xa6, (byte)0xb4, (byte)0xc6, (byte)0xe8, (byte)0xdd, (byte)0x74, (byte)0x1f, (byte)0x4b, (byte)0xbd, (byte)0x8b, (byte)0x8a},
      {(byte)0x70, (byte)0x3e, (byte)0xb5, (byte)0x66, (byte)0x48, (byte)0x03, (byte)0xf6, (byte)0x0e, (byte)0x61, (byte)0x35, (byte)0x57, (byte)0xb9, (byte)0x86, (byte)0xc1, (byte)0x1d, (byte)0x9e},
      {(byte)0xe1, (byte)0xf8, (byte)0x98, (byte)0x11, (byte)0x69, (byte)0xd9, (byte)0x8e, (byte)0x94, (byte)0x9b, (byte)0x1e, (byte)0x87, (byte)0xe9, (byte)0xce, (byte)0x55, (byte)0x28, (byte)0xdf},
      {(byte)0x8c, (byte)0xa1, (byte)0x89, (byte)0x0d, (byte)0xbf, (byte)0xe6, (byte)0x42, (byte)0x68, (byte)0x41, (byte)0x99, (byte)0x2d, (byte)0x0f, (byte)0xb0, (byte)0x54, (byte)0xbb, (byte)0x16}
      
   };
   
   
   static byte oldBytes[] = {
   
   (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
   (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
   (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
   (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
   (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
   (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
   (byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
   };

   public static void main(String[] args)
   {
      //get and convert policy partition list, data partitions
      try{
         hashgen("small.txt");
      } 
      catch(IOException e){
      
      
      }
      
   
   }
   
   //https://core.ac.uk/download/pdf/235205959.pdf
   public static void sbox(byte[] z)
   {
      byte m = 32; //our matrix is supposed to be 1024 total which is 32x32
      byte l = 0;
      double[] sb = new double[m*m];
      
      do
      {
         sb[0] = 0;
         for(byte j = 0; j < m; j++){
            sb[0] = sb[0] + z[l]*Math.pow(2,j);
            l++;
         }
      
      }while(sb[0] != 0);
   
      //for each location in the s-box(sb)
      for(int i = 1; i < m*m; i++){
         byte spr = 0;
         do{
            sb[i] = 0;
            for(byte j = 0; j < m; j++){
               sb[i] = sb[i] + z[l]*Math.pow(2,j);
               l++;
            }
         
         }while(spr == 0);
      }
   
   }
   
   
   
   //TSP is taken in as a starting point for the keymatrix generation
   private static byte[] keygen(byte seed)
   {
      byte keymatrix[] = new byte[64];
      for(int i = 0; i < 64; i++){
         keymatrix[i] = policyBox[(seed+128)%64]; //Math.random()*256
         seed = keymatrix[i];
      }
      return keymatrix;
   }
   
   public static String hash(String filename)
   {
      try{
         return hashgen(filename);
      } 
      catch(IOException e){
      
      
      }
      
      
      return "";
   }
   
   private static String hashgen(String filename) throws IOException
   {
      //64bytes = 512 bits
      //Creating FileInputStream object
      File file = new File(filename);
      FileInputStream fis = new FileInputStream(file);
      int fileLen = (int) file.length();
      int loopControl = (int)java.lang.Math.ceil((double)fileLen / 64);
      for(int i = 0; i < loopControl; i++)
      {
         byte bytes[] = new byte[64];
      
         fis.read(bytes,0,64); 
      
      /*
         File outFile = new File("CopyOfmyFile_"+i+".txt"); // 
         FileOutputStream outputStream = new FileOutputStream(outFile);
         //Writing data to the file
         outputStream.write(bytes);
         outputStream.flush();
         */
         //create transformed sub-partition TSPi
         //Non-Linear Sub-Partition Substitution
         byte TSPbyte = 0;
         for(int j = 0; j < 8; j++)
         {
            //since long is 8 bytes we will group by this.
            ByteBuffer buffSP = ByteBuffer.wrap(new byte[] {bytes[j*8+0], bytes[j*8+1], bytes[j*8+2], bytes[j*8+3], bytes[j*8+4], bytes[j*8+5], bytes[j*8+6], bytes[j*8+7]});
            long SP = buffSP.getLong();//64-bits
            long TSP = ((A*SP*SP + B*SP + C) % 2);
            TSP = TSP == -1 ? 1 : TSP;
            TSPbyte = (byte)(TSPbyte << 0x01);
            TSPbyte = (byte)(TSPbyte | TSP);
            //System.out.println(SP + " transformed sub-partition = " + TSP);
            
         }
         //System.out.println(TSPbyte);
         //System.out.println();
         
      //phase 2
      //generate s-box
      //we assume TSP is used along with user access policies to generate a pseduo-random s-box
      
      //phase 3
      //execute cipher
      //FF FF FF FF FF FF FF FF 
      //FF FF FF FF FF FF FF FF 
      //FF FF FF FF FF FF FF FF 
      //FF FF FF FF FF FF FF FF 
      //FF FF FF FF FF FF FF FF 
      //FF FF FF FF FF FF FF FF 
      //FF FF FF FF FF FF FF FF 
      //FF FF FF FF FF FF FF FF    
         for(int w = 0; w < 10; w++)
         {
            byte hashBytes[] = new byte[64];
            byte shiftBytes[] = new byte[64];
            byte shiftColBytes[] = new byte[64];
         //round step 1: sub
            for(int k = 0; k < 64; k++)
            {
            
               //bytes[k]=ff =>  f                           f
               hashBytes[k] = sBox[(bytes[k] >> 4)& 0x0F][(bytes[k] & 0x0F)];
               //System.out.print(hashBytes[k] + " ("+bytes[k]+")");
            }
            //System.out.println();
         
         
         //round step 2: shift
         //part a:right to left
            int shiftAmount[] = {2, 5, 12, 7, 1, 8, 7, 4}; //derived from policy {0, 0, 0, 0, 0, 0, 0, 0};//
            
            for(int k = 0; k < 8; k++)
            {
               shiftBytes[k*8] = hashBytes[((k*8)+shiftAmount[k]+(hashBytes[k]&0xff)) % 8];     //(hashBytes[k]&0xff) <-- this is used to actually shift the data to give variance, without this there could be hash collisions
               shiftBytes[k*8+1] = hashBytes[(k*8)+((1+shiftAmount[k]+hashBytes[k+1]&0xff) % 8)];
               shiftBytes[k*8+2] = hashBytes[(k*8)+((2+shiftAmount[k]+hashBytes[k+2]&0xff) % 8)];
               shiftBytes[k*8+3] = hashBytes[(k*8)+((3+shiftAmount[k]+hashBytes[k+3]&0xff) % 8)];
               shiftBytes[k*8+4] = hashBytes[(k*8)+((4+shiftAmount[k]+hashBytes[k+4]&0xff) % 8)];
               shiftBytes[k*8+5] = hashBytes[(k*8)+((5+shiftAmount[k]+hashBytes[k+5]&0xff) % 8)];
               shiftBytes[k*8+6] = hashBytes[(k*8)+((6+shiftAmount[k]+hashBytes[k+6]&0xff) % 8)];
               shiftBytes[k*8+7] = hashBytes[(k*8)+((7+shiftAmount[k]+hashBytes[k+7]&0xff) % 8)];
            }
         //print shift
         /*
            for(int k = 0; k < 64; k++)
            {
               System.out.print(shiftBytes[k] + " ");
            }
            System.out.println();
         */
         //part b:shift up and down
         
         //int shiftAmount[] = {0, 0, 0, 0, 0, 0, 0, 0};//{2, 5, 12, 7, 1, 8, 7, 4}; //derived from policy
            for(int k = 0; k < 8; k++)
            {
            /*
               //shiftAmount is 'reversed' so 7-k
               shiftColBytes[k] = shiftBytes[(k+8*shiftAmount[7-k])%64];      //shiftAmount
               shiftColBytes[k+8] = shiftBytes[(k+8*shiftAmount[7-k]+8)%64];
               shiftColBytes[k+8*2] = shiftBytes[(k+8*shiftAmount[7-k]+(8*2))%64];
               shiftColBytes[k+8*3] = shiftBytes[(k+8*shiftAmount[7-k]+(8*3))%64];
               shiftColBytes[k+8*4] = shiftBytes[(k+8*shiftAmount[7-k]+(8*4))%64];
               shiftColBytes[k+8*5] = shiftBytes[(k+8*shiftAmount[7-k]+(8*5))%64];
               shiftColBytes[k+8*6] = shiftBytes[(k+8*shiftAmount[7-k]+(8*6))%64];
               shiftColBytes[k+8*7] = shiftBytes[(k+8*shiftAmount[7-k]+(8*7))%64];
               */
               shiftColBytes[k] = shiftBytes[(k)%64];      //shiftAmount
               shiftColBytes[k+8] = shiftBytes[(k+8)%64];
               shiftColBytes[k+8*2] = shiftBytes[(k+(8*2))%64];
               shiftColBytes[k+8*3] = shiftBytes[(k+(8*3))%64];
               shiftColBytes[k+8*4] = shiftBytes[(k+(8*4))%64];
               shiftColBytes[k+8*5] = shiftBytes[(k+(8*5))%64];
               shiftColBytes[k+8*6] = shiftBytes[(k+(8*6))%64];
               shiftColBytes[k+8*7] = shiftBytes[(k+(8*7))%64];
            }
         //print shift
         /*
            for(int k = 0; k < 64; k++)
            {
               System.out.print(shiftColBytes[k] + " ");
            }*/
         
         //round step 3: MixColumns
         
         //DUPHA does not call out any column matrix multiplication
         
         //round step 4: Apply Key 
            byte keymatrix[] = keygen(TSPbyte);
            for(int k = 0; k < 64; k++)
            {
               bytes[k] = (byte)(shiftColBytes[k] ^ keymatrix[k]);
            }
            /*
            System.out.println();
            for(int k = 0; k < 64; k++)
            {
               System.out.print(bytes[k] + " ");
            }
            */
         
         }
      
      
         //XOR previous values
         for(int k = 0; k < 64; k++)
         {
            bytes[k] = (byte)(bytes[k] ^ oldBytes[k]);
            oldBytes[k] = bytes[k];
            //System.out.print(oldBytes[k] + " ");
         }
         //System.out.println(" ");
         
      
      
         
      
      }
      //              long                          long                          long                          long                          long                          long                          long                          long
      //P0(512BITS) = SP0|FF FF FF FF FF FF FF FF   SP1|FF FF FF FF FF FF FF FF   SP2|F FF FF FF FF FF FF FF   SP3|FF FF FF FF FF FF FF FF   SP4|FF FF FF FF FF FF FF FF   SP5|F FF FF FF FF FF FF FF   SP6|FF FF FF FF FF FF FF FF   SP7|FF FF FF FF FF FF FF FF   
      //p0 (8BITS)=     11111111            

      
      /*
      byte bytes[] = new byte[fileLen];
      //Reading data from the file
      fis.read(bytes);
      //Writing data to another file
      File out = new File("CopyOfmyFile.txt"); // 
      FileOutputStream outputStream = new FileOutputStream(out);
      //Writing data to the file
      outputStream.write(bytes);
      outputStream.flush();
      */
      String returnValue = "";
      for(int k = 0; k < 64; k++)
      {
         String result = String.format("%02X", oldBytes[k] );
         System.out.print(result);
         returnValue+=result;
      }
   
      fis.close();
      return returnValue;
   }
}