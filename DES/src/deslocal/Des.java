
package deslocal;

/**
 *
 * @author eduwi
 */
import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Des {
public void Encripitar( String Key,String mess )  throws Exception {
          
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        DESKeySpec kspec = new DESKeySpec(Key.getBytes());
        SecretKey subkey = skf.generateSecret(kspec);
            
        Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cifrado.init(Cipher.ENCRYPT_MODE, subkey);

        byte[] buffer = new byte[1000];
        
        byte[] bufferCifrado; 
        
        FileInputStream in = new FileInputStream(mess);
        
        FileOutputStream out = new FileOutputStream(mess+".cifrado");
        
        int bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){
            
            bufferCifrado = cifrado.update(buffer, 0, bytesleidos);
  
            out.write(bufferCifrado);
         
            bytesleidos = in.read(buffer, 0, 1000);
        }

        bufferCifrado = cifrado.doFinal();
        out.write(bufferCifrado); 
        in.close();
        out.close();
}
 public void Desencriptar(String Key,String mess) throws Exception{
 

          SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        DESKeySpec kspec = new DESKeySpec(Key.getBytes());
        SecretKey subkey = skf.generateSecret(kspec);
        
       Cipher cifrado = Cipher.getInstance("DES/ECB/PKCS5Padding");
       
       cifrado.init(Cipher.DECRYPT_MODE, subkey);
        
        byte[] bufferPlano; //aqui voy almacenar el resultado descifrado
         byte[] buffer = new byte[1000];
         
         FileInputStream in  = new FileInputStream(mess);
        
         FileOutputStream out = new FileOutputStream(mess+".descifrado");
        
        int bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){
            //mientras no se llegue al final del archivo o fichero
            bufferPlano = cifrado.update(buffer, 0, bytesleidos);
            //para el texto claro leer y enviarlo al buffer cifrado
            out.write(bufferPlano);
            //escribir el archivo cifrado
            bytesleidos = in.read(buffer, 0, 1000);
        }
        //acompletar el fichero o archivo con el descifrado
        bufferPlano = cifrado.doFinal();
        out.write(bufferPlano); //escribir el final del texto descifrado si lo hay
        
        in.close();
        out.close();
 }



}
