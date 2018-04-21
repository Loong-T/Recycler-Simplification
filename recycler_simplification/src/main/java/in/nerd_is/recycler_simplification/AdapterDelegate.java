/*
 *    Copyright 2018 Xuqiang ZHENG
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package in.nerd_is.recycler_simplification;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Xuqiang ZHENG on 18/4/21.
 */
public class AdapterDelegate {

    private TypeFactory typeFactory;
    private HasListData data;

    public AdapterDelegate(TypeFactory typeFactory, HasListData data) {
        this.typeFactory = typeFactory;
        this.data = data;
    }

    public int getItemViewType(int position) {
        return typeFactory.getType(data.getData().get(position).getClass());
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return typeFactory.createViewHolder(parent, viewType);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        typeFactory.bindViewHolder(holder, data.getData().get(position));
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position,
                                 @NonNull List<Object> payloads) {
        typeFactory.bindViewHolder(holder, data.getData().get(position), payloads);
    }
}
