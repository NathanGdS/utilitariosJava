package utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

public class Helper {

    public void ShowObject(Object obj, String name){
        if(obj != null){
            System.out.println("\n---"+name+"---");
            System.out.println(
                obj.toString()
            );
        }
    }

    // metodo generico que aceita qualquer tipo de lista (object)
    public void ShowListObject(List<?> obj, String name){
        System.out.println("\n---"+name+"---");
        if(obj.size() > 0){
            for(Object o : obj){
                System.out.println(
                    o.toString()
                );
            }
        }else{
            System.out.println("Nao ha registros cadastrados!");
        }
    }

    public static void PrintAllData(ResultSet result) {
        try {
            ResultSetMetaData rsmd = result.getMetaData();

            for(int i = 1; i <= rsmd.getColumnCount(); i++){
                System.out.print(rsmd.getColumnName(i)+"\t");
            }
            System.out.println();

            while(result.next()) {
                for(int i = 1; i <= rsmd.getColumnCount(); i++){
                    System.out.print(result.getString(i)+"\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cls(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E){
            System.out.println(E);
        }
    }
}

