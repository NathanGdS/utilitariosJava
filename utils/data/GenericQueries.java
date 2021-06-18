package utils.data;

import java.sql.Connection;
import java.sql.ResultSet;

import connections.MySQLConnection;

public class GenericQueries {
    private static java.sql.PreparedStatement statement = null;
    private static Connection connection = MySQLConnection.getConexaoMySQL();

    public static void create(String table_name, Object[] columns, Object[] values) {
        String columnsFormatted = "";
        String valuesFormatted = "";

        for(int i = 0; i < columns.length; i++){
            if((i+1) == columns.length){
                columnsFormatted += columns[i];
            }else {
                columnsFormatted += columns[i]+", ";
            }
        }

        for(int i = 0; i < values.length; i++){
            if((i+1) == values.length){
                valuesFormatted += '?';
            }else {
                valuesFormatted += "?, ";
            }
        }
        // INSERT INTO table_name (COLUMN1, COLUMNS2, COLUMNS3) VALUES (?, ?, ?)
        // INSERT INTO table_name (COLUMN1, COLUMNS2, COLUMNS3) VALUES ('Nathan', true, 10)
        try {
            String query = 
                "INSERT INTO "
                + table_name + " (" + columnsFormatted +
                ") VALUES (" + valuesFormatted + ")";
            
            statement = connection.prepareStatement(query);
            
            for(int i = 0; i < values.length; i++){
                if(values[i] instanceof String){

                    String aux = String.valueOf(values[i]);
                    statement.setString(i+1, aux);
                }else if(values[i] instanceof Integer) {

                    int aux = (int) values[i];
                    statement.setInt(i+1, aux);
                }else if(values[i] instanceof Boolean) {
                    
                    boolean aux = (boolean) values[i];
                    statement.setBoolean(i+1, aux);
                }
            }

            statement.execute();
            MySQLConnection.CloseConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String findRegisterByFilter(String table_name, String fieldInWhereClausule, String fieldToFilter, String WhereClausule) {
        try {
            statement = connection.prepareStatement(
                "SELECT "+ fieldToFilter + " FROM " + table_name + " WHERE " + WhereClausule + " = '" + fieldInWhereClausule+"'"
            );

            ResultSet result = statement.executeQuery();
            result.next();

            return result.getString(1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ResultSet selectAllTableData(String tableName) {
        try {
            statement = connection.prepareStatement("select * from "+tableName+";");
    
            return statement.executeQuery();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void update(String table_name, String fieldToSet, String fieldToFilter, Object[] manipulateFields){
        try {
            // "update users set num_points = ? where first_name = ?"
            String query = "UPDATE " + table_name + " SET " + fieldToSet + " = ? WHERE " + fieldToFilter + " = ?";
            statement = connection.prepareStatement(query);
            // [0] => valor a ser setado, [1] => valor da clausula where
            Object newValue = manipulateFields[0];
            Object valueInFilter = manipulateFields[1];


            //Caso necessario, adicionar mais verificações de instancia
            //Verificando o tipo da instancia passado no valor a ser setado
            if(newValue instanceof Integer) {
                statement.setInt(1, (int) newValue);
            }
            if(newValue instanceof String) {
                statement.setString(1, String.valueOf(newValue));
            }
            if(newValue instanceof Boolean) {
                statement.setBoolean(1, (boolean) newValue);
            }
            if(newValue instanceof Double) {
                statement.setDouble(1, (double) newValue);
            }

            //verificando o tipo da instancia passado no filtro
            if(valueInFilter instanceof Integer) {
                statement.setInt(2, (int) valueInFilter);
            }
            if(valueInFilter instanceof String) {
                statement.setString(2, String.valueOf(valueInFilter));
            }
            if(valueInFilter instanceof Boolean) {
                statement.setBoolean(2, (boolean) valueInFilter);
            }
            if(valueInFilter instanceof Double) {
                statement.setDouble(2, (double) valueInFilter);
            }

            statement.executeUpdate();
            MySQLConnection.CloseConnection();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(String table_name, int id, String fieldToFilter) {
        try {
            String query = "DELETE FROM " + table_name + " WHERE " + fieldToFilter+" = ?";

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
    
            statement.execute();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
