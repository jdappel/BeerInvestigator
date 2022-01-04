package com.jdappel.beerinvestigator.ui.beers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.jdappel.beerinvestigator.R;
import com.jdappel.beerinvestigator.databinding.ListItemDetailBinding;
import com.jdappel.beerinvestigator.databinding.ListItemHeaderBinding;
import com.jdappel.beerinvestigator.rest.Beer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Extension of {@link BaseExpandableListAdapter} to construct the necessary views and populate the
 * {@link android.widget.ExpandableListView}
 */
class ExpandableListAdapter extends BaseExpandableListAdapter {

    private List<String> headerList = Collections.emptyList();
    private Map<String, Beer> titleToBeer = Collections.emptyMap();
    private final LayoutInflater inflater;

    ExpandableListAdapter(final LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setBeers(List<Beer> beers) {
        notifyDataSetChanged();
        headerList = beers.stream().map(beer -> beer.getName()).collect(Collectors.toList());
        titleToBeer = new HashMap<>(beers.size());
        for (Beer beer : beers) {
            titleToBeer.put(beer.getName(), beer);
        }
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        Beer child = (Beer) getChild(groupPosition, childPosition);
        TextView view;
        if (convertView != null) {
            view = (TextView) convertView;
        } else {
            ListItemDetailBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_detail, parent, false);
            view = binding.beerListItem;
        }
        view.setText(child.getDescription());
        return view;
    }

    @Override public Object getChild(int groupPosition, int childPosititon) {
        return titleToBeer.get(headerList.get(groupPosition));
    }

    @Override public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override public Object getGroup(int groupPosition) {
        return headerList.get(groupPosition);
    }

    @Override public int getGroupCount() {
        return this.headerList.size();
    }

    @Override public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        TextView view;
        if (convertView != null) {
            view = (TextView) convertView;
        } else {
            ListItemHeaderBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_header, parent, false);
            view = binding.beerListHeader;
        }
        view.setText(headerTitle);
        return view;
    }

    @Override public boolean hasStableIds() {
        return false;
    }

    @Override public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class DetailViewHolder {
        TextView beerDetail;

        DetailViewHolder(TextView view) {
            beerDetail = view;
        }
    }

    static class HeaderViewHolder {
        TextView beerHeader;

        HeaderViewHolder(TextView view) {
            beerHeader = view;
        }
    }
}
