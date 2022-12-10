import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
            return;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        /// if you have a error in this part, check jdbc driver(.jar file)

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/project_movie", "postgres", "cse3207");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        /// if you have a error in this part, check DB information (db_name, user name, password)

        if (connection != null) {
            System.out.println(connection);
            System.out.println("You made it, take control your database now!\n");
        } else {
            System.out.println("Failed to make connection!");
        }
        
        Statement stmt = connection.createStatement();
        String sql;
        //1 
        sql = "create table actor (actorid varchar(5) primary key not null, actorname varchar(20) not null, "
        		+ "dateofbirth varchar(10), dateofdeath varchar(10), gender varchar(6))";
        stmt.executeUpdate(sql);
        
        sql = "create table director (directorid varchar(5) primary key not null, directorname varchar(20) not null,"
        		+ "dateofbirth varchar(10), dateofdeath varchar(10))";
        stmt.executeUpdate(sql);
        
        sql = "create table movie (movieid varchar(5) primary key not null, moviename varchar(40), "
        		+ "releaseyear numeric(4,0), releasemonth numeric(2,0), releasedate varchar(10),"
        		+ "publishername varchar(40), avgrate numeric(5, 1))";
        stmt.executeUpdate(sql);
        
        sql = "create table award (awardid varchar(5) primary key not null, awardname varchar(30) not null)";
        stmt.executeUpdate(sql);
        
        sql = "create table genre ( genrename varchar(30) primary key not null)";
        stmt.executeUpdate(sql);
        
        sql = "create table moviegenre(movieid varchar(5), genrename varchar(30), "
        		+ "primary key(movieid, genrename), "
        		+ "foreign key(movieid) references movie on delete cascade, "
        		+ "foreign key(genrename) references genre)";
        stmt.executeUpdate(sql);
        
        sql = "create table movieobtain(movieid varchar(5), awardid varchar(5), year numeric(4,0), "
        		+ "primary key(movieid, awardid), "
        		+ "foreign key(movieid) references movie on delete cascade, "
        		+ "foreign key(awardid) references award)";
        stmt.executeUpdate(sql);
        
        sql = "create table actorobtain(actorid varchar(5), awardid varchar(5), year numeric(4,0), "
        		+ "primary key(actorid, awardid), "
        		+ "foreign key(actorid) references actor, "
        		+ "foreign key(awardid) references award)";
        stmt.executeUpdate(sql);
        
        sql = "create table directorobtain(directorid varchar(5), awardid varchar(5), year numeric(4, 0),"
        		+ "primary key(directorid, awardid), "
        		+ "foreign key(directorid) references director, "
        		+ "foreign key(awardid) references award)";
        stmt.executeUpdate(sql);
        
        sql = "create table casting(movieid varchar(5), actorid varchar(5), role varchar(30), "
        		+ "primary key(movieid, actorid),"
        		+ "foreign key(movieid) references movie on delete cascade,"
        		+ "foreign key(actorid) references actor)";
        stmt.executeUpdate(sql);
        

        sql = "create table make(movieid varchar(5), directorid varchar(5), "
        		+ "primary key(movieid, directorid),"
        		+ "foreign key(movieid) references movie on delete cascade,"
        		+ "foreign key(directorid) references director)";
        stmt.executeUpdate(sql);
        
        sql = "create table customer(customerid varchar(5), customername varchar(20), dateofbirth varchar(10), gender varchar(6), "
        		+ "primary key(customerid))";
        stmt.executeUpdate(sql);
        
        sql = "create table customerrate(customerid varchar(5), movieid varchar(5), rate numeric(3,1),"
        		+ "primary key(customerid, movieid),"
        		+ "foreign key(customerid) references customer on delete cascade, "
        		+ "foreign key(movieid) references movie on delete cascade)";
        stmt.executeUpdate(sql);
        
        
        sql = "insert into director values('12101', 'Lee isaac Chung', '1978.10.19', null)";
        stmt.executeUpdate(sql);
        sql = "insert into director values('13101', 'Tim Burton', '1958.8.25', null)";
        stmt.executeUpdate(sql);
        sql = "insert into director values('14101', 'David Fincher', '1962.8.28', null)";
        stmt.executeUpdate(sql);
        sql = "insert into director values('15101', 'Christopher Nolan', '1970.7.30', null)";
        stmt.executeUpdate(sql);
        
        sql = "insert into actor values('21202', 'Steven Yeun', '1983.12.21', null, 'Male')";
        stmt.executeUpdate(sql);
        sql = "insert into actor values('22202', 'Youn Yuhjung', '1947.6.19', null, 'Female')";
        stmt.executeUpdate(sql);
        sql = "insert into actor values('23202', 'Johnny Depp', '1963.6.9', null, 'Male')";
        stmt.executeUpdate(sql);
        sql = "insert into actor values('24202', 'Winona Ryder', '1971.10.29', null, 'Female')";
        stmt.executeUpdate(sql);
        sql = "insert into actor values('25202', 'Anne Hathaway', '1982.11.12', null, 'Female')";
        stmt.executeUpdate(sql);
        sql = "insert into actor values('26202', 'Christian Bale', '1974.1.30', null, 'Male')";
        stmt.executeUpdate(sql);
        sql = "insert into actor values('27202', 'Heath Ledger', '1979.4.4', '2008.1.22', 'Male')";
        stmt.executeUpdate(sql);
        sql = "insert into actor values('28202', 'Jesse Eisenberg', '1983.10.05', null, 'Male')";
        stmt.executeUpdate(sql);
        sql = "insert into actor values('29202', 'Andrew Garfield', '1983.08.20', null, 'Male')";
        stmt.executeUpdate(sql);

        sql = "insert into movie values('1', 'Minari', '2020', '12', '2020.12.11', 'A24', null)";
        stmt.executeUpdate(sql);
        sql = "insert into movie values('2', 'Edward Scissorhands', '1991', '06', '1991.06.29', '20th Centruy Fox Presents', null)";
        stmt.executeUpdate(sql);
        sql = "insert into movie values('3', 'Alice in Wonderland', '2010', '03', '2010.03.04', 'Korea Sony Pictures', null)"; 
        stmt.executeUpdate(sql);
        sql = "insert into movie values('4', 'The Social Network', '2010', '11', '2010.11.18', 'Korea Sony Pictures', null)"; 
        stmt.executeUpdate(sql);
        sql = "insert into movie values('5', 'The Dark Knight', '2008', '08', '2008.08.06', 'Warner Brothers Korea', null)";
        stmt.executeUpdate(sql);

        sql = "insert into genre values('Drama')";
        stmt.executeUpdate(sql);
        sql = "insert into genre values('Fantasy')";
        stmt.executeUpdate(sql);
        sql = "insert into genre values('Romance')";
        stmt.executeUpdate(sql);
        sql = "insert into genre values('Adventure')";
        stmt.executeUpdate(sql);
        sql = "insert into genre values('Family')";
        stmt.executeUpdate(sql);
        sql = "insert into genre values('Action')";
        stmt.executeUpdate(sql);
        sql = "insert into genre values('Mystery')";
        stmt.executeUpdate(sql);
        sql = "insert into genre values('Thriller')";
        stmt.executeUpdate(sql);

        sql = "insert into movieGenre values('1', 'Drama')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('2', 'Fantasy')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('2', 'Romance')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('3', 'Fantasy')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('3', 'Adventure')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('3', 'Family')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('4', 'Drama')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('5', 'Action')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('5', 'Drama')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('5', 'Mystery')";
        stmt.executeUpdate(sql);
        sql = "insert into movieGenre values('5', 'Thriller')";
        stmt.executeUpdate(sql);
        
        sql = "insert into casting values('1' , '21202', 'Main actor')";
        stmt.executeUpdate(sql);
        sql = "insert into casting values('1' , '22202', 'Supporting Actor')";
        stmt.executeUpdate(sql);
        sql = "insert into casting values('2', '23202', 'Main actor')";
        stmt.executeUpdate(sql);
        sql = "insert into casting values('2', '24202', 'Main actor')";
        stmt.executeUpdate(sql);
        sql = "insert into casting values('3', '23202', 'Main actor')";
        stmt.executeUpdate(sql);
        sql = "insert into casting values('3', '25202', 'Main actor')";
        stmt.executeUpdate(sql);
        sql = "insert into casting values('4', '28202', 'Main actor')";
        stmt.executeUpdate(sql);
        sql = "insert into casting values('4', '29202', 'Supporting Actor')";
        stmt.executeUpdate(sql);
        sql = "insert into casting values('5', '26202', 'Main actor')";
        stmt.executeUpdate(sql);
        sql = "insert into casting values('5', '27202', 'Main actor')";
        stmt.executeUpdate(sql);

        sql = "insert into make values('1', '12101')";
        stmt.executeUpdate(sql);
        sql = "insert into make values('2', '13101')";
        stmt.executeUpdate(sql);
        sql = "insert into make values('3', '13101')";
        stmt.executeUpdate(sql);
        sql = "insert into make values('4', '14101')";
        stmt.executeUpdate(sql);
        sql = "insert into make values('5', '15101')";
        stmt.executeUpdate(sql);

        sql = "insert into customer values('41404', 'Bob', '1997.11.14', 'Male')";
        stmt.executeUpdate(sql);
        sql ="insert into customer values('42404', 'John', '1978.01.23', 'Male')";
        stmt.executeUpdate(sql);
        sql ="insert into customer values('43404', 'Jack', '1980.05.04', 'Male')";
        stmt.executeUpdate(sql);
        sql = "insert into customer values('44404', 'Jill', '1981.04.17', 'Female')";
        stmt.executeUpdate(sql);
        sql = "insert into customer values('45404', 'Bell', '1990.05.14', 'Female')";
        stmt.executeUpdate(sql);
        
        
        
        
        //2
        stmt.executeUpdate("insert into award values('31303', 'Best supporting actor')");
        ResultSet rs = stmt.executeQuery("select awardid, awardname from award");
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println("Statement: Winona Ryder won the \"Best supporting actor\" award in 1994");
        System.out.println("Translated SQL: Insert into award values('31303', 'Best supporting actor')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t");
        while (rs.next()) {
        	System.out.println(" " + rs.getString("awardid") + "\t\t" + " " + rs.getString(2) + "\t");
        }
        System.out.println("\n");
        
        stmt.executeUpdate("insert into actorobtain values('24202', '31303', '1994')");
        rs = stmt.executeQuery("select * from actorobtain");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: Insert into actorObtain values('24202', '31303', '1994')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t\t " + rs.getInt(3) + "\t");
        }
        System.out.println("\n");
        
        
        stmt.executeUpdate("insert into actorObtain values('29202', '31303', '2011')");
        rs = stmt.executeQuery("select * from award");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Andrew Garfield won the \"Best supporting actor\" award in 2011");
        System.out.println("Translated SQL: Insert ignore into award values('31303', 'Best supporting actor')");
        System.out.println("Show the tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnClassName(2) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t");
        }
        System.out.println('\n');
        
        rs = stmt.executeQuery("select * from actorObtain");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: Insert into actorObtain values('29202', '31303', '2011')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t\t " + rs.getInt(3) + "\t" );
        }
        System.out.println('\n');
      
        
        stmt.executeUpdate("insert into award values('32303', 'Best main actor')");
        rs = stmt.executeQuery("select * from award");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Jesse Eisenberg won the \"Best main actor\" award in 2011");
        System.out.println("Translated SQL: Insert into award values('32303', 'Best main actor')");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t");
        }
        System.out.println('\n');
        
        stmt.executeUpdate("insert into actorObtain values('28202', '32303', '2011')");
        rs = stmt.executeQuery("select * from actorObtain");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: Insert into actorObtain values('28202', '32303', '2011')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t\t " + rs.getInt(3) + "\t" );
        }
        System.out.println('\n');
        
        
        stmt.executeUpdate("insert into award values('33303', 'Best villain actor')");
        rs = stmt.executeQuery("select * from award");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Johnny Depp won the \"Best villain actor\" award in 2011");
        System.out.println("Translated SQL: Insert into award values('33303', 'Best villain actor')");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t");
        }
        System.out.println('\n');
        
        stmt.executeUpdate("insert into actorObtain values('23202', '33303', '2011')");
        rs = stmt.executeQuery("select * from actorObtain");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: Insert into actorObtain values('23202', '33303', '2011')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t\t " + rs.getInt(3) + "\t" );
        }
        System.out.println('\n');
        
        
        stmt.executeUpdate("insert into award values('34303', 'Best fantasy movie')");
        rs = stmt.executeQuery("select * from award");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Edward Scissorhands won the \"Best fantasy movie\" award in 1991");
        System.out.println("Translated SQL: Insert into award values('34303', 'Best fantasy movie')");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t" );
        }
        System.out.println("\n");
        
        stmt.executeUpdate("insert into movieObtain values('2', '34303', '1991')");
        rs = stmt.executeQuery("select * from movieObtain");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: Insert into movieObtain values('2', '34303', '1911')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t\t " + rs.getInt(3) + "\t" );
        }
        System.out.println('\n');
        
        
        rs = stmt.executeQuery("select * from award");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Alice In Wonderland won the \"Best fantasy movie\" award in 2011");
        System.out.println("Translated SQL: Insert ignore into award values('34303', 'Best fantasy movie')");
        System.out.println("Show the tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t" );
        }
        System.out.println("\n");
        
        stmt.executeUpdate("insert into movieObtain values('3', '34303', '2011')");
        rs = stmt.executeQuery("select * from movieObtain");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: Insert into movieObtain values('3', '34303', '2011')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t\t " + rs.getInt(3) + "\t" );
        }
        System.out.println('\n');
        
        
        rs = stmt.executeQuery("select * from award");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Youn Yuhjung won the \"Best supporting actor\" award in 2021");
        System.out.println("Translated SQL: Insert ignore into award values('31303', 'Best supporting actor')");
        System.out.println("Show the tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t" );
        }
        System.out.println("\n");
        
        stmt.executeUpdate("insert into actorObtain values('22202', '31303', '2021')");
        rs = stmt.executeQuery("select * from actorObtain");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: Insert into actorObtain values('22202', '31303', '2021')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t\t " + rs.getInt(3) + "\t" );
        }
        System.out.println('\n');
        
        
        stmt.executeUpdate("insert into award values('35303', 'Best Foreign Language Film')");
        rs = stmt.executeQuery("select * from award");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Minari won the \"Best Foreign Language Film\" award in 2021");
        System.out.println("Translated SQL: Insert into award values('35303', 'Best Foreign Language Film')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t" );
        }
        System.out.println("\n");
        
        stmt.executeUpdate("insert into movieObtain values('1', '35303', '2021')");
        rs = stmt.executeQuery("select * from movieObtain");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: Insert into movieObtain values('1', '35303', '2021')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t" + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t " + rs.getString(2) + "\t\t " + rs.getInt(3) + "\t" );
        }
        System.out.println('\n');
        
        
        
        //3
        PreparedStatement pStmt = connection.prepareStatement(
        		"insert into customerrate values(?, ?, ?)");
        pStmt.setString(1, "41404");
        pStmt.setString(2, "5");
        pStmt.setInt(3, 3);
        pStmt.executeUpdate();
        rs = stmt.executeQuery("select * from customerrate");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Bob rates 3 to \"The Dark Knight\".");
        System.out.println("Translated SQL: Insert into customerrate values('41404', '5', 3)");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t\t " + rs.getInt(3));
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "		from customerrate\r\n"
        		+ "		group by movieid)\r\n"
        		+ "		where moviename = 'The Dark Knight'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate grop by movieid) where moviename = 'The Dark Knight'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        
        
        pStmt.setString(1, "45404");
        pStmt.setString(2, "2");
        pStmt.setInt(3, 5);
        pStmt.executeUpdate();
        pStmt.setString(1, "45404");
        pStmt.setString(2, "3");
        pStmt.setInt(3, 5);
        pStmt.executeUpdate();
        rs = stmt.executeQuery("select * from customerrate");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Bell rates 5 to the movies whose director is \"Tim Burton\".");
        System.out.println("Translated SQL: Select directorid from direcotr where directorname = \'Tim Burton\'");
        System.out.println("Translated SQL: Select movieid from make where directorid = \'13101\'");
        System.out.println("Translated SQL: Insert into customerrate values('45404', '2', 5)");
        System.out.println("Translated SQL: Insert into customerrate values('45404', '3', 5)");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t\t " + rs.getInt(3));
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "				  from customerrate\r\n"
        		+ "				  where movieid = '2'\r\n"
        		+ "				  group by movieid)\r\n"
        		+ "	where movieid = '2'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate where movieid = '2' grop by movieid)"
        		+ "where movieid = '2'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "				  from customerrate\r\n"
        		+ "				  where movieid = '3'\r\n"
        		+ "				  group by movieid)\r\n"
        		+ "	where movieid = '3'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate where movieid = '3' grop by movieid)"
        		+ "where movieid = '3'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        
        pStmt.setString(1, "44404");
        pStmt.setString(2, "2");
        pStmt.setInt(3, 4);
        pStmt.executeUpdate();
        pStmt.setString(1, "44404");
        pStmt.setString(2, "3");
        pStmt.setInt(3, 4);
        pStmt.executeUpdate();
        rs = stmt.executeQuery("select * from customerrate");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Jill rates 4 to the movies whose main actor is female.");
        System.out.println("Translated SQL: insert into customerrate" + " select customerid, movieid, 5" + " from customer, casting natural join actor" + " where customername = 'Jill' and actor.gender = 'Female' and role = 'Main actor'");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t\t " + rs.getInt(3));
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "				  from customerrate\r\n"
        		+ "				  where movieid = '2'\r\n"
        		+ "				  group by movieid)\r\n"
        		+ "	where movieid = '2'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate where movieid = '2' grop by movieid)"
        		+ "where movieid = '2'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "				  from customerrate\r\n"
        		+ "				  where movieid = '3'\r\n"
        		+ "				  group by movieid)\r\n"
        		+ "	where movieid = '3'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate where movieid = '3' grop by movieid)"
        		+ "where movieid = '3'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        
        
        stmt.executeUpdate("insert into customerrate\r\n"
        		+ "	select customerid, movieid, 4\r\n"
        		+ "	from customer, moviegenre\r\n"
        		+ "	where customername = 'Jack' and genrename = 'Fantasy'");
        rs = stmt.executeQuery("select * from customerrate");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Jack rates 4 to the fantasy movies.");
        System.out.println("Translated SQL: select customerid from customer where customername = 'Jack'");
        System.out.println("Translated SQL: select movieid from moviegenre where genrename = 'Fantasy'");
        System.out.println("Translated SQL: insert into customerrate values('43404', '2', '4')");
        System.out.println("Translated SQL: insert into customerrate values('43404', '3', '4')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t\t " + rs.getInt(3));
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "				  from customerrate\r\n"
        		+ "				  where movieid = '2'\r\n"
        		+ "				  group by movieid)\r\n"
        		+ "	where movieid = '2'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate where movieid = '2' grop by movieid)"
        		+ "where movieid = '2'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "				  from customerrate\r\n"
        		+ "				  where movieid = '3'\r\n"
        		+ "				  group by movieid)\r\n"
        		+ "	where movieid = '3'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate where movieid = '3' grop by movieid)"
        		+ "where movieid = '3'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        
        stmt.executeUpdate("with awarded_actor(actorid) as\r\n"
        		+ "	(select actorid\r\n"
        		+ "	 from award natural join actorobtain\r\n"
        		+ "	 where awardname = 'Best supporting actor'),\r\n"
        		+ "movie_awardedActor(value) as	 \r\n"
        		+ "	(select movieid\r\n"
        		+ "	from awarded_actor join casting on awarded_actor.actorid = casting.actorid)\r\n"
        		+ "insert into customerrate\r\n"
        		+ "	select customerid, value, 5\r\n"
        		+ "	from customer, movie_awardedActor\r\n"
        		+ "	where customername = 'John'");
        rs = stmt.executeQuery("select * from customerrate");
        rsmd = rs.getMetaData();
        System.out.println("Statement: John rates 5 to the movies whose actor won the \"Best supporting actor\" award");
        System.out.println("Translated SQL: select customerid from customer where customername = 'John'");
        System.out.println("Translated SQL: select awardid from award where awardname = 'Best supporting actor'");
        System.out.println("Translated SQL: select actorid from actorobtain where awardid = '31303'");
        System.out.println("Translated SQL: select movieid from casting where actorid = '22202'");
        System.out.println("Translated SQL: select movieid from casting where actorid = '24202'");
        System.out.println("Translated SQL: select movieid from casting where actorid = '29202'");
        System.out.println("Translated SQL: insert into customerrate values('42404', '1', '5')");
        System.out.println("Translated SQL: insert into customerrate values('42404', '2', '5')");
        System.out.println("Translated SQL: insert into customerrate values('42404', '4', '5')");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t" + "|" + rsmd.getColumnName(3) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t\t " + rs.getInt(3));
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "				  from customerrate\r\n"
        		+ "				  where movieid = '1'\r\n"
        		+ "				  group by movieid)\r\n"
        		+ "	where movieid = '1'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate where movieid = '1' grop by movieid)"
        		+ "where movieid = '1'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "				  from customerrate\r\n"
        		+ "				  where movieid = '2'\r\n"
        		+ "				  group by movieid)\r\n"
        		+ "	where movieid = '2'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate where movieid = '2' grop by movieid)"
        		+ "where movieid = '2'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        stmt.executeUpdate("update movie\r\n"
        		+ "	set avgrate = (select avg(rate)\r\n"
        		+ "				  from customerrate\r\n"
        		+ "				  where movieid = '4'\r\n"
        		+ "				  group by movieid)\r\n"
        		+ "	where movieid = '4'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: update movie set avgrate = (select avg(rate) "
        		+ "from customerrate where movieid = '4' grop by movieid)"
        		+ "where movieid = '4'");
        System.out.println("Translated SQL: select movieid, moviename, avgrate from movie");
        System.out.println("Update tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        
        
        //4
        rs = stmt.executeQuery("with movieid_actorid(value) as\r\n"
        		+ "	(select movieid\r\n"
        		+ "	from actor join casting on actor.actorid = casting.actorid\r\n"
        		+ "	where dateofdeath is not null)\r\n"
        		+ "select moviename\r\n"
        		+ "from movie, movieid_actorid\r\n"
        		+ "where movieid = movieid_actorid.value");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Select the names of the movies whose actor are dead.");
        System.out.println("Translated SQL: with movieid_actorid(value) as (select movieid from actor join casting on actor.actorid = casting.actorid where dateofdeath is not null)"
        		+ "select moviename from movie, movieid_actorid where movieid = movieid_actorid.value");
        System.out.println("Select Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t ");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1));
        }
        System.out.println("\n");
        
        
        
        //5
        stmt.executeQuery("select movieid\r\n"
        		+ "from casting\r\n"
        		+ "where actorid in (select actorid\r\n"
        		+ "				  from casting\r\n"
        		+ "				  group by actorid\r\n"
        		+ "				  having count(actorid) > 1)");
        rs = stmt.executeQuery("select directorname\r\n"
        		+ "from director\r\n"
        		+ "where directorid in (select directorid\r\n"
        		+ "					 from make\r\n"
        		+ "					 where movieid = '2' and directorid in\r\n"
        		+ "					 (select directorid\r\n"
        		+ "					 from make\r\n"
        		+ "					 where movieid = '3'))");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Select the names of the directors who cast the same actor more than once.");
        System.out.println("Translated SQL: select movieid from casting where actorid in "
        		+ "(select actorid from casting group by actorid having count(actorid) > 1)");
        System.out.println("Translated SQL: select directorname from director where directorid in"
        		+ "(select directorid from make where movieid = '2' and directorid in"
        		+ "(select directorid from make where movieid = '3'))");
        System.out.println("Select Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t ");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1));
        }
        System.out.println("\n");
        
        
        
        //6
        stmt.executeQuery("select genrename, count(*)\r\n"
        		+ "from moviegenre\r\n"
        		+ "group by genrename");
        rs = stmt.executeQuery("(select moviename, genrename from movie natural join moviegenre where genrename = 'Fantasy')\r\n"
        		+ "union\r\n"
        		+ "(select moviename, genrename from movie natural join moviegenre where genrename = 'Drama')");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Select the names of the movies and the genres, where movies have the common genre.");
        System.out.println("Translated SQL: select genrename, count(*) from moviegenre group by genrename");
        System.out.println("Translated SQL: select moviename, genrename from movie natural join moviegenre where genrename = 'Fantasy'"
        		+ "union"
        		+ "(select moviename, genrename from movie natural join moviegenre where genrename = 'Drama')");
        System.out.println("Select Tables");
        System.out.println("-----< commonGenreMovie >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t\t " + "|" + rsmd.getColumnName(2));
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t  " + rs.getString(2) + "\t");
        }
        System.out.println("\n");
        
        
        
        //7
        stmt.executeQuery("select movie.movieid\r\n"
        		+ "from movie\r\n"
        		+ "where movie.movieid not in ((select distinct movieid\r\n"
        		+ "							from casting \r\n"
        		+ "							where actorid in (select actorid\r\n"
        		+ "											  from actorobtain))\r\n"
        		+ "							union\r\n"
        		+ "							(select distinct movieid\r\n"
        		+ "							 from make\r\n"
        		+ "							 where directorid in (select directorid\r\n"
        		+ "												  from directorobtain))\r\n"
        		+ "							)");
        stmt.executeUpdate("delete from movie where movieid = '5'");
        rs = stmt.executeQuery("select * from movie");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Delete the movies whose director or actor did not get any award and delete data from related tables.");
        System.out.println("Translated SQL: select movie.movieid from movie where movie.movieid not in\n\t"
        		+ "((select distinct movieid from casting where actorid in (select actorid from actorobtain)" 
        		+ "union (select distinct movieid from make  where directorid in (select directorid from directorobtain)))");
        System.out.println("delete from movie where movieid = '5'");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t\t " + "|" + rsmd.getColumnName(3) + "\t "
        		+ "|" + rsmd.getColumnName(4) + "\t " + "|" + rsmd.getColumnName(5) + "\t " + "|" + rsmd.getColumnName(6) + "\t\t\t " + "|" + rsmd.getColumnName(7) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  " + rs.getInt(3) + "\t\t  " + rs.getInt(4) + "\t\t  "
        			+ rs.getString(5) + "\t  " + rs.getString(6) + "\t\t  " + rs.getFloat(7) + "\t");
        }
        System.out.println("\n");
        
        stmt.executeQuery("select * from moviegenre");
        rs = stmt.executeQuery("select * from moviegenre");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: delete ingnore(because of operating 'delete cascade' of movieid in movie) from moviegenre where movieid = '5'");
        System.out.println("Translated SQL: select * from moviegenre");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t ");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  ");
        }
        System.out.println("\n");
        
        stmt.executeQuery("select * from movieobtain");
        rs = stmt.executeQuery("select * from movieobtain");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: delete ingnore(because of operating 'delete cascade' of movieid in movie) from movieobtain where movieid = '5'");
        System.out.println("Translated SQL: select * from movieobtain");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t ");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  ");
        }
        System.out.println("\n");
        
        stmt.executeQuery("select * from casting");
        rs = stmt.executeQuery("select * from casting");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: delete ingnore(because of operating 'delete cascade' of movieid in movie) from casting where movieid = '5'");
        System.out.println("Translated SQL: select * from casting");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t " + "|" + rsmd.getColumnName(3) + "\t ");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t\t  " + rs.getString(3) +"\t");
        }
        System.out.println("\n");
        
        stmt.executeQuery("select * from make");
        rs = stmt.executeQuery("select * from make");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: delete ingnore(because of operating 'delete cascade' of movieid in movie) from make where movieid = '5'");
        System.out.println("Translated SQL: select * from make");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t ");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t  ");
        }
        System.out.println("\n");
        
        stmt.executeQuery("select * from customerrate");
        rs = stmt.executeQuery("select * from customerrate");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: delete ingnore(because of operating 'delete cascade' of movieid in movie) from customerrate where movieid = '5'");
        System.out.println("Translated SQL: select * from customerrate");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t " + "|" + rsmd.getColumnName(3) + "\t ");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t\t  " + rs.getInt(3) + "\t");
        }
        System.out.println("\n");
        
        
        
        //8
        stmt.executeUpdate("delete from customer");
        rs = stmt.executeQuery("select * from customer");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Delete all customers and delete data from related tables.");
        System.out.println("Translated SQL: delete from customer");
        System.out.println("Update Tables");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t " + "|" + rsmd.getColumnName(3) + "\t " + "|" + rsmd.getColumnName(4) + "\t");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t\t  " + rs.getString(3) + "\t\t" + rs.getString(4) + "\t");
        }
        System.out.println("\n");
        
        stmt.executeQuery("select * from customerrate");
        rs = stmt.executeQuery("select * from customerrate");
        rsmd = rs.getMetaData();
        System.out.println("Translated SQL: delete ingnore(because of operating 'delete cascade' of customerid in customer) from customerrate");
        System.out.println("Translated SQL: select * from customerrate");
        System.out.println("Update Tables");
        System.out.println("-----< " + rsmd.getTableName(1) + " >-----");
        System.out.println("|" + rsmd.getColumnName(1) + "\t " + "|" + rsmd.getColumnName(2) + "\t " + "|" + rsmd.getColumnName(3) + "\t ");
        while(rs.next()) {
        	System.out.println(" " + rs.getString(1) + "\t\t  " + rs.getString(2) + "\t\t  " + rs.getInt(3) + "\t");
        }
        System.out.println("\n");
        
        stmt.executeUpdate("drop schema public cascade");
        stmt.executeUpdate("create schema public;");
        rs = stmt.executeQuery("select count(distinct table_name) as TotalNumberOfTables from information_schema.columns where table_schema = 'project_movie'");
        rsmd = rs.getMetaData();
        System.out.println("Statement: Delete all tables and data (make the database empty).");
        System.out.println("Translated SQL: delete schema public cascade");
        System.out.println("Translated SQL: select count(distinct table_name) as TotalNumberOfTables from information_schema.columns where table_schema = 'project_movie'");
        System.out.println("Update Database");
        System.out.println("-----< " + rsmd.getColumnName(1) + " >-----");
        while(rs.next()) {
        	System.out.println("The number of tables is '" + rs.getInt(1) +"'."
        			+ "\t");
        }
        connection.close();
        
        
    }
}
