package com.jdappel.beerinvestigator.ui.beers

import android.view.LayoutInflater
import android.view.View
import android.widget.BaseExpandableListAdapter
import com.jdappel.beerinvestigator.rest.Beer
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.jdappel.beerinvestigator.R
import com.jdappel.beerinvestigator.databinding.ListItemDetailBinding
import com.jdappel.beerinvestigator.databinding.ListItemHeaderBinding

/**
 * Extension of [BaseExpandableListAdapter] to construct the necessary views and populate the
 * [android.widget.ExpandableListView]
 */
internal class ExpandableListAdapter(private val inflater: LayoutInflater) :
    BaseExpandableListAdapter() {
    private var headerList = mutableListOf<String>()
    private var titleToBeer: MutableMap<String, Beer> = hashMapOf()

    fun setBeers(beers: List<Beer>) {
        notifyDataSetChanged()
        titleToBeer = beers.filter { it.name != null }.associateBy { it.name!! }.toMutableMap()
        headerList = titleToBeer.keys.toMutableList()
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {
        val child = getChild(groupPosition, childPosition) as Beer
        val view =
            if (convertView != null) {
                convertView as TextView
            } else {
                val binding: ListItemDetailBinding = DataBindingUtil.inflate(
                    inflater, R.layout.list_item_detail, parent, false
                )
                binding.beerListItem
            }
        view.text = child.description
        return view
    }

    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return titleToBeer[headerList[groupPosition]]!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return headerList[groupPosition]
    }

    override fun getGroupCount(): Int {
        return headerList.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean, convertView: View?,
        parent: ViewGroup
    ): View {
        val headerTitle = getGroup(groupPosition) as String
        val view = if (convertView != null) {
            convertView as TextView
        } else {
            val binding: ListItemHeaderBinding = DataBindingUtil.inflate(
                inflater, R.layout.list_item_header, parent, false
            )
            binding.beerListHeader
        }
        view.text = headerTitle
        return view
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    internal class DetailViewHolder(var beerDetail: TextView)
    internal class HeaderViewHolder(var beerHeader: TextView)
}