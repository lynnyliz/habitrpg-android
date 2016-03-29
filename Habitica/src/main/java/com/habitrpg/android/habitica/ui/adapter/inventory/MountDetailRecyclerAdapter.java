package com.habitrpg.android.habitica.ui.adapter.inventory;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitrpg.android.habitica.R;
import com.habitrpg.android.habitica.ui.helpers.DataBindingUtils;
import com.magicmicky.habitrpgwrapper.lib.models.inventory.Mount;
import com.magicmicky.habitrpgwrapper.lib.models.inventory.Pet;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MountDetailRecyclerAdapter extends RecyclerView.Adapter<MountDetailRecyclerAdapter.MountViewHolder> {

    private List<Mount> itemList;
    private HashMap<String, Boolean> ownedMapping;
    public String itemType;

    public Context context;

    public void setItemList(List<Mount> itemList) {
        this.itemList = itemList;
        this.notifyDataSetChanged();
    }

    public void setOwnedMapping(HashMap<String, Boolean> map) {
        this.ownedMapping = map;
        this.notifyDataSetChanged();
    }

    @Override
    public MountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animal_overview_item, parent, false);

        return new MountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MountViewHolder holder, int position) {
        holder.bind(this.itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    class MountViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Mount animal;

        @Bind(R.id.card_view)
        CardView cardView;

        @Bind(R.id.imageView)
        ImageView imageView;

        @Bind(R.id.titleTextView)
        TextView titleView;

        @Bind(R.id.ownedTextView)
        TextView ownedTextView;

        Resources resources;

        public MountViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            resources = itemView.getResources();

            itemView.setOnClickListener(this);
        }

        public void bind(Mount item) {
            titleView.setText(item.getColor());
            ownedTextView.setVisibility(View.GONE);
            this.imageView.setAlpha(1.0f);
            if (ownedMapping != null) {
                if (ownedMapping.containsKey(item.getKey()) && ownedMapping.get(item.getKey())) {
                    DataBindingUtils.loadImage(this.imageView, "Mount_Icon_" + itemType + "-" + item.getColor());
                } else {
                    ownedTextView.setText(null);
                    DataBindingUtils.loadImage(this.imageView, "PixelPaw");
                    this.imageView.setAlpha(0.4f);
                }
            } else {
                DataBindingUtils.loadImage(this.imageView, "PixelPaw");
                this.imageView.setAlpha(0.4f);
            }
        }

        @Override
        public void onClick(View v) {

        }
    }
}
