package com.jdappel.beerinvestigator.ui.beers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.jdappel.beerinvestigator.R;
import com.jdappel.beerinvestigator.rest.Beer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Extension of {@link BaseExpandableListAdapter} to construct the necessary views and populate the
 * {@link android.widget.ExpandableListView}
 */
class ExpandableListAdapter extends BaseExpandableListAdapter {

    private List<String> headerList = Collections.emptyList();
    private Map<String, Beer> titleToBeer = Collections.emptyMap();
    private LayoutInflater inflater;

    ExpandableListAdapter(final LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setBeers(List<Beer> beers) {
        notifyDataSetChanged();
        headerList = Stream.of(beers).map(Beer::getName).collect(Collectors.toList());
        titleToBeer = new HashMap<>(beers.size());
        Stream.of(beers).forEach(beer -> titleToBeer.put(beer.getName(), beer));
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        Beer child = (Beer) getChild(groupPosition, childPosition);
        DetailViewHolder holder;
        if (convertView != null) {
            holder = (DetailViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.list_item_detail, parent, false);
            holder = new DetailViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.beerDetail.setText(child.getDescription());
        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return titleToBeer.get(headerList.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.headerList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        HeaderViewHolder holder;
        if (convertView != null) {
            holder = (HeaderViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.list_item_header, parent, false);
            holder = new HeaderViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.beerHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class DetailViewHolder {
        @Bind(R.id.beerListItem)
        TextView beerDetail;

        DetailViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class HeaderViewHolder {
        @Bind(R.id.beerListHeader)
        TextView beerHeader;

        HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
