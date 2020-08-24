package fi.haltu.harrastuspassi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.haltu.harrastuspassi.R
import java.util.*
import kotlin.collections.ArrayList

class FilterTagsListAdapter(
    private val categoryTagsList: ArrayList<String>,
    private val clickListener: (categoryName: String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.filter_tags_item, parent, false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val filterTag: String = categoryTagsList[position]
        (holder as TagViewHolder).bind(filterTag, clickListener)
    }

    override fun getItemCount(): Int {
        return categoryTagsList.size
    }
    object translation {
        fun translateCategory(category: String) {
            val currentLanguage = Locale.getDefault().language

            if(currentLanguage == "fi") {
                return category.nameFi
            } else if(currentLanguage == "sv") {
                return category.nameSv
            } else {
                if(category.nameEn.isNullOrEmpty()) {
                    return category.name
                } else {
                    retrun category.nameEn
                }
            }
        }
    }


    class TagViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var tag: TextView = itemView.findViewById(R.id.textView)
        private var deleteButton: ImageButton = itemView.findViewById(R.id.delete_tag)
        fun bind(filterTag: String, clickListener: (String) -> Unit) {
            tag.text = translation.translateCategory(filterTag).toString()

            deleteButton.setOnClickListener { clickListener(filterTag) }
        }
    }
}
