package ro.tuc.pt.data_access;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//If there is any static data member in a class, it will not be serialized
public class Serializator<T> {

    public void serialize(ArrayList<T> obj, String fileName){
        try{
            //Creating stream and writing the object
            FileOutputStream fout=new FileOutputStream(fileName);
            ObjectOutputStream out=new ObjectOutputStream(fout);
            for(T t: obj){
                out.writeObject(t);
            }
            out.flush();
            //closing the stream
            out.close();
            System.out.println("success");
        }catch(Exception e){
            System.out.println(e);}
    }

    public ArrayList<T> deserialize(String fileName){
        ArrayList<T> obj = new ArrayList<>();
        int i = 0;
        try{
            //Creating stream to read the object
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(fileName));
            T o = (T)in.readObject();
            while(o != null){
                obj.add(o);
                o = (T)in.readObject();
            }
            //closing the stream
            in.close();
        }catch(Exception e){
            System.out.println(e);}
        return obj;
    }

}
