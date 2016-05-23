package com.jdappel.beerinvestigator.ui.beers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.jdappel.beerinvestigator.R;
import com.jdappel.beerinvestigator.databinding.ListItemDetailBinding;
import com.jdappel.beerinvestigator.rest.Beer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Extension of {@link android.support.v7.widget.RecyclerView.Adapter} to construct the necessary views and populate the
 * {@link RecyclerView}
 */
class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemDetailBinding binding;

        ViewHolder(ListItemDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ListItemDetailBinding binding = ListItemDetailBinding
                    .inflate(inflater, parent, false);
            return new ViewHolder(binding);
        }

        void bindTo(Beer item) {
            binding.setBeer(item);
            binding.executePendingBindings();
        }
    }

    private final LayoutInflater inflater;
    private List<Beer> beerList;

    BeerListAdapter(List<Beer> list, LayoutInflater inflater) {
        beerList = list;
        this.inflater = inflater;
    }

    @Override
    public BeerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        return ViewHolder.create(inflater, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(beerList.get(position));
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public void setBeers(List<Beer> beers) {
        beerList = beers;
        notifyDataSetChanged();
    }
}
