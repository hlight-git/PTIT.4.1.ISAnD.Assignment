package dao.statement;

public class Query {
    public static String getById(String tblName, int id){
        String res = String.format("SELECT * FROM %s WHERE id = %d;", tblName, id);
        System.out.println("- Call query: " + res);
        return res;
    }
    public static String getById(String tblName1, String tblName2, int id){
        String res = String.format("SELECT * FROM " +
                "%s AS tb1 " +
                "INNER JOIN " +
                "%s AS tb2 " +
                "ON tb1.id = tb2.id " +
                "WHERE tb1.id = %d;", tblName1, tblName2, id);
        System.out.println("- Call query: " + res);
        return res;
    }
    public static String getAll(String tblName){
        String res = String.format("SELECT * FROM %s;", tblName);
        System.out.println("- Call query: " + res);
        return res;
    }
    public static String getAll(String tblName1, String tblName2) {
        String res = String.format("SELECT * FROM " +
                "%s AS tb1 " +
                "INNER JOIN " +
                "%s AS tb2 " +
                "ON tb1.id = tb2.id;", tblName1, tblName2);
        System.out.println("- Call query: " + res);
        return res;
    }
    public static String getByNameContains(String tblName, String keyword){
        String res = "SELECT * FROM tblName WHERE `name` LIKE \"%keyword%\";"
                .replace("tblName", tblName)
                .replace("keyword", keyword);
        System.out.println("- Call query: " + res);
        return res;
    }
    public static String getByNameContains(String tblName1, String tblName2, String keyword){
        String res = ("SELECT * FROM " +
                "tblName1 AS tb1 " +
                "INNER JOIN " +
                "tblName2 AS tb2 " +
                "ON tb1.id = tb2.id " +
                "WHERE `name` LIKE \"%keyword%\";")
                .replace("tblName1", tblName1)
                .replace("tblName2", tblName2)
                .replace("keyword", keyword);
        System.out.println("- Call query: " + res);
        return res;
    }
}
