package fi.haltu.harrastuspassi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fi.haltu.harrastuspassi.R
import fi.haltu.harrastuspassi.models.Hobby
import fi.haltu.harrastuspassi.models.HobbyEvent


class HobbyEventListAdapter(private val list: List<HobbyEvent>, private val clickListener: (HobbyEvent, ImageView) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.adapter_hobby_event_list_item, parent, false)
        return HobbyListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hobbyEvent: HobbyEvent = list[position]
        (holder as HobbyListViewHolder).bind(hobbyEvent, clickListener)
    }

    override fun getItemCount(): Int = list.size

    class HobbyListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.title)
        private var place: TextView = itemView.findViewById(R.id.place)
        private var image: ImageView = itemView.findViewById(R.id.image)
        private var duration: TextView = itemView.findViewById(R.id.dateTime)

        fun bind(hobbyEvent: HobbyEvent, clickListener: (HobbyEvent, ImageView) -> Unit) {
            title.text = hobbyEvent.hobby.name
            place.text = hobbyEvent.hobby.location.name
            Picasso.with(itemView.context)
                .load(hobbyEvent.hobby.imageUrl)
                .placeholder(R.drawable.image_placeholder_icon)
                .error(R.drawable.image_placeholder_icon)
                .into(image)
            duration.text = "${hobbyEvent.startDate} - ${hobbyEvent.endDate}"
            itemView.setOnClickListener { clickListener(hobbyEvent, image) }
        }
    }
}