package seunghee.helloandroid._02_26_02_JDBC

import java.sql.*

object _02_26_02_JDBCUtill {

    private var DRV: String ="org.mariadb.jdbc.Driver"
    private var URL: String ="jdbc:mariadb://maria-db.coqecfviusin.ap-northeast-2.rds.amazonaws.com:3306/playground4_2"
    private var USR: String ="playground4_2"
    private var PWD: String ="tmdgml201"

    fun makeConn(): Connection? {
        var conn: Connection? = null
        try {
            Class.forName(DRV)
            conn = DriverManager.getConnection(URL, USR, PWD)
            println("JDBC 드라이버 연결")
        } catch (e: ClassNotFoundException) {
            println("JDBC 드라이버가 없어요!")
        } catch (e: SQLException) {
            println("JDBC 연결 실패!")
            println(e.message)
        }
        return conn
    }

    fun destoryConn(conn: Connection?, pstmt: PreparedStatement?, rs: ResultSet?) {
        if (rs != null) try {
            rs.close()
        } catch (ex: SQLException) {
        }
        destoryConn(conn, pstmt)
    }

    fun destoryConn(conn: Connection?, pstmt: PreparedStatement?) {
        if (pstmt != null) try {
            pstmt.close()
        } catch (ex: SQLException) {
        }
        if (conn != null) try {
            conn.close()
        } catch (ex: SQLException) {
        }
    }


}