/*
 * Copyright 2017 Xuqiang ZHENG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.nerd_is.recycler_simplification;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Xuqiang ZHENG on 2016/11/23.
 */
public abstract class AbstractAdapter extends RecyclerView.Adapter<ViewHolder> {

    @SuppressWarnings("WeakerAccess")
    protected final TypeFactory typeFactory;

    @SuppressWarnings("WeakerAccess")
    public AbstractAdapter(@NonNull TypeFactory typeFactory) {
        this.typeFactory = typeFactory;
    }

    abstract List<?> getData();

    @Override
    public int getItemCount() {
        return getData().size();
    }

    @Override
    public int getItemViewType(int position) {
        return typeFactory.getType(getData().get(position).getClass());
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return typeFactory.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        typeFactory.bindViewHolder(holder, getData().get(position));
    }
}