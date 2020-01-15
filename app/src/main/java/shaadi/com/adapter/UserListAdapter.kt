package shaadi.com.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView
import shaadi.com.R
import shaadi.com.db.ShaadiUsers


class UserListAdapter(var context: Context, var user_list: MutableList<ShaadiUsers>,
                      val listener: (ShaadiUsers,String,Int) -> Unit) :
    RecyclerView.Adapter<UserListAdapter.DealViewHolder>() {


    fun updateData(new_userlist: ShaadiUsers,position : Int){

        user_list[position] = new_userlist

        notifyItemChanged(position)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DealViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder : DealViewHolder, position: Int) {
        val shaadiUsers : ShaadiUsers = user_list[position]
        holder.bind(shaadiUsers, listener,position)
    }

    override fun getItemCount(): Int = user_list.size


    inner class DealViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(
            inflater.inflate(
                R.layout.shaadi_users_list_item, parent,
                false
            )
        ) {

        private var txt_fullname: TextView? = null
        private var txt_age: TextView? = null
        private var txt_location: TextView? = null
        private var btn_accept: Button? = null
        private var btn_decline: Button? = null
        private var userImage: CircleImageView? = null


        init {

            txt_fullname = itemView.findViewById(R.id.txt_fullname)
            txt_age = itemView.findViewById(R.id.txt_userage)
            txt_location = itemView.findViewById(R.id.txt_location)
            btn_accept = itemView.findViewById(R.id.btn_accept)
            btn_decline = itemView.findViewById(R.id.btn_decline)
            userImage = itemView.findViewById(R.id.userImage)

        }

        @SuppressLint("SetTextI18n")
        fun bind(
            shaadiUsers: ShaadiUsers,
            listener: (ShaadiUsers, String,Int) -> Unit,
            position: Int
        ) {
            shaadiUsers.name.let {
                txt_fullname?.text = it.title.plus(it.first).plus(it.last)
            }

            shaadiUsers.dob.let {

                txt_age?.text = ""+it.age+"".plus(" years")
            }

            txt_location?.text = shaadiUsers.location.city.plus("\n").plus(shaadiUsers.location.country)


            shaadiUsers.status.let {status->
                if(status.equals("Yes")){
                    btn_accept?.setText(R.string.member_accept)
                }else if(status.equals("No")){
                    btn_decline?.setText(R.string.member_decline)
                }else{
                    btn_accept?.setText(R.string.accept)
                    btn_decline?.setText(R.string.decline)
                }
            }

            btn_accept?.setOnClickListener {
                listener(shaadiUsers,"Yes",position)
            }
            btn_decline?.setOnClickListener {
                listener(shaadiUsers,"No",position)
            }

            userImage?.let { it1 ->
                shaadiUsers.picture.let {
                    picture ->
                    Glide.with(context).load(picture.thumbnail)
                        .thumbnail(0.5f)
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(it1)
                }

            }


        }

    }



}