package seunghee.helloandroid._02_26_02_JDBC

import android.os.AsyncTask
import android.util.Log
import java.lang.Exception
import java.lang.StringBuilder
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class _02_27_01_SelectOneEmp : AsyncTask<Void?, Void?, Void?>() {

    companion object {
        const val selectOneSQL = "select * from Employees where employee_id = ?"
    }

    override fun doInBackground(vararg params: Void?): Void? {
        var conn : Connection? = null
        var pstmt : PreparedStatement? = null
        var rs : ResultSet? = null

        var sb = StringBuilder()

        try {
            conn = _02_26_02_JDBCUtill.makeConn()
            pstmt = conn!!.prepareStatement(selectOneSQL)
            rs = pstmt.executeQuery()
            while ( rs.next() ) {
                sb.append(rs.getString("employee_id")).append("/")
                sb.append(rs.getString("first_name")).append("/")
                sb.append(rs.getString("last_name")).append("\n")
            }
            Log.d("jdbc", sb.toString())
        } catch (ex : Exception) {
            ex.printStackTrace()
        } finally {
            _02_26_02_JDBCUtill.destoryConn(conn, pstmt, rs)
        }

        return null
    }
}