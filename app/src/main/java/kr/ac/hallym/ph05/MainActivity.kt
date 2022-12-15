package kr.ac.hallym.ph05

import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        permissionCheck()
        read() // 읽어오기


    }

    private fun permissionCheck(){
        if (android.os.Build.VERSION.SDK_INT >= 23){
            val permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
            if (permissionCheck != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS), PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(this,"permission is denied", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this,"permission is granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun read(){ //읽어오기
        var cursor : Cursor? = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
        startManagingCursor(cursor)

        var from = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.CommonDataKinds.Phone._ID)

        var to = intArrayOf(R.id.name, R.id.phoneNum)



        var simple : SimpleCursorAdapter = SimpleCursorAdapter(this,R.layout.test, cursor, from, to)
        val list = findViewById<View>(R.id.listView) as ListView
        list.adapter = simple

    }


}