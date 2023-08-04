package demoregistermanagement.demoregistermanagement;

public interface IQuery {
     static String updateMemberQuery(int id){
        return "UPDATE members SET name=?, surname=?, age=?, phone=?, status=? WHERE id = '" + id + "'";
    }
     static String insertMemberQuery(){
        return "INSERT INTO members (name, surname, age, phone, status) VALUES (?, ?, ?, ?, ?)";
    }
     static String deleteQuery(int id){
        return "DELETE FROM members WHERE id = '"+ id +"';";
    }
     static String getAdminByUsername(String insertedUsername){
        return "SELECT * FROM admins WHERE username = '"+ insertedUsername +"';";
    }
    static String getMemberById(int id){
        return "select * from members WHERE id ='"+ id +"';";
    }
    static String getAdminsUsername(){
        return "SELECT username from admins;";
    }
    static String insertAdminQuery(){
        return "INSERT INTO admins (name, surname, phone, username, password) VALUES (?, ?, ?, ?, ?)";
    }
    static String getAdminById(int id){
        return "select * from admins where id = '" + id + "';";
    }
    static String updateAdminPassword(){
        return "UPDATE admins SET password = ? WHERE id = ?";
    }
    static String getAllMembers(){
        return "select * from members;";
    }
}
