package fi.haltu.harrastuspassi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import androidx.annotation.LayoutRes
import fi.haltu.harrastuspassi.models.Category
import java.util.*

class CategorySearchAdapter(
    context: Context, @LayoutRes private val layoutResource: Int,
    categoryList: List<Category>
) :
    ArrayAdapter<Category>(context, layoutResource, categoryList) {
    private var categories: List<Category> = categoryList
    private var categoriesOrigin: List<Category> = categoryList
    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(p0: Int): Category? {
        return categories[p0]
    }

    override fun getItemId(p0: Int): Long {
        return categories[p0].id!!.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(
            layoutResource,
            parent,
            false
        ) as TextView
        val category = categories[position]
        val currentLanguage = Locale.getDefault().language

        if(currentLanguage == "fi") {
            view.text = category.nameFi
        } else if(currentLanguage == "sv") {
            view.text = category.nameSv
        } else {
            if(category.nameEn.isNullOrEmpty()) {
                view.text = category.name
            } else {
                view.text = category.nameEn
            }
        }
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                categories = results.values as List<Category>

                notifyDataSetChanged()
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint?.toString()?.toLowerCase()
                val filterResults = FilterResults()
                filterResults.values = if (queryString == null || queryString.isEmpty()) {
                    categoriesOrigin
                } else {
                    categories.filter {
                        val currentLanguage = Locale.getDefault().language
                        if(currentLanguage == "fi") {
                            it.nameFi.toLowerCase().contains(queryString)
                        } else if(currentLanguage == "sv") {
                            it.nameSv.toLowerCase().contains(queryString)
                        } else {
                            if(it.nameEn.isNullOrEmpty()) {
                                it.name.toLowerCase().contains(queryString)
                            } else {
                                it.nameEn.toLowerCase().contains(queryString)
                            }
                        }
                    }
                }

                return filterResults
            }
        }
    }
}