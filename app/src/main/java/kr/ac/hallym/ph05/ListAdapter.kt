package kr.ac.hallym.ph05

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(val context: Context, val list: ArrayList<PhoneNum>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.main_phone_item, parent,false)

        val name = view.findViewById<TextView>(R.id.name)
        val phoneNum = view.findViewById<TextView>(R.id.phoneNum)
        val photo = view.findViewById<ImageView>(R.id.profile)

        val phone = list[position]
        val resourceId = context.resources.getIdentifier(phone.photo,"drawable",context.packageName)

        photo.setImageResource(resourceId)
        name.text = phone.name
        phoneNum.text = phone.number

        return view
    }
    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getCount(): Int {
        return list.size
    }
}